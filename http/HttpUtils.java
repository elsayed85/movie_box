package com.movieboxpro.android.http;

import android.text.TextUtils;
import android.util.Base64;
import androidx.core.app.NotificationCompat;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.utils.CipherUtils;
import com.movieboxpro.android.utils.HexDump;
import com.movieboxpro.android.utils.MD5Util;
import com.movieboxpro.android.utils.MLog;
import java.io.PrintStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.eclipse.jetty.servlet.ServletHandler;
/* loaded from: classes3.dex */
public class HttpUtils {
    private static CipherKeys DEFAULT_KEYS = CipherKeys.getDefaultKeys();
    private static final String TAG = "HttpUtils";

    public static String md5(String str) {
        return HexDump.toHexString(MD5Util.md5(str)).toLowerCase();
    }

    private static String getVerify(String str, String str2, String str3) {
        if (str != null) {
            return md5(md5(str2) + str3 + str);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String formToJson(String str) {
        if (str != null) {
            String[] split = str.split("&");
            if (split.length > 0) {
                JSONObject jSONObject = new JSONObject();
                boolean z = false;
                boolean z2 = false;
                boolean z3 = false;
                for (String str2 : split) {
                    String[] split2 = str2.split("=");
                    if (split2.length == 2) {
                        if (split2[0].equals("appid")) {
                            split2[1] = App.packageName;
                            z = true;
                        } else if (split2[0].equals("app_version")) {
                            split2[1] = App.versionName;
                            z2 = true;
                        } else if (split2[0].equals("mids")) {
                            jSONObject.put(split2[0], (Object) ((JSONObject) JSONObject.parse(split2[1])).getJSONArray("mid"));
                        } else if (split2[0].equals("tids")) {
                            jSONObject.put(split2[0], (Object) ((JSONObject) JSONObject.parse(split2[1])).getJSONArray("tid"));
                        } else if (split2[0].equals("lang")) {
                            if (TextUtils.equals(ServletHandler.__DEFAULT_SERVLET, split2[1])) {
                                split2[1] = "";
                            }
                            z3 = true;
                        }
                        if (!split2[0].equals("mids") && !split2[0].equals("tids")) {
                            jSONObject.put(split2[0], (Object) split2[1]);
                        }
                    } else if (split2.length == 1) {
                        jSONObject.put(split2[0], (Object) "");
                    }
                }
                if (!z) {
                    jSONObject.put("appid", (Object) App.packageName);
                }
                if (!z2) {
                    jSONObject.put("app_version", (Object) App.versionName);
                }
                if (!z3) {
                    jSONObject.put("lang", (Object) App.deviceLang);
                }
                return jSONObject.toJSONString();
            }
            return null;
        }
        return null;
    }

    public static String encodeBody(String str) {
        return encodeBody(str, DEFAULT_KEYS);
    }

    public static String encodeBody(String str, CipherKeys cipherKeys) {
        if (str != null) {
            MLog.d(TAG, "before encrypt:" + str);
            String encrypt = CipherUtils.encrypt(str, cipherKeys);
            MLog.d(TAG, "after encrypt:" + encrypt);
            String verify = getVerify(encrypt, cipherKeys.getAppKey(), cipherKeys.getKey());
            String md5 = md5(cipherKeys.getAppKey());
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("app_key", (Object) md5);
            jSONObject.put("encrypt_data", (Object) encrypt);
            jSONObject.put("verify", (Object) verify);
            String jSONString = jSONObject.toJSONString();
            MLog.d(TAG, "new body json:" + jSONString);
            String str2 = new String(Base64.encode(jSONString.getBytes(), 2), StandardCharsets.UTF_8);
            MLog.d(TAG, "after encoded:" + str2);
            return str2;
        }
        return null;
    }

    public static String decodeBody(String str, CipherKeys cipherKeys) {
        if (str != null) {
            try {
                String str2 = new String(Base64.decode(URLDecoder.decode(str, "utf-8"), 2), "utf-8");
                MLog.d(TAG, "decodeBody:" + str2);
                JSONObject parseObject = JSONObject.parseObject(str2);
                boolean containsKey = parseObject.containsKey("app_key");
                boolean containsKey2 = parseObject.containsKey("encrypt_data");
                boolean containsKey3 = parseObject.containsKey("verify");
                if (containsKey && containsKey2 && containsKey3) {
                    String string = parseObject.getString("app_key");
                    String string2 = parseObject.getString("encrypt_data");
                    String string3 = parseObject.getString("verify");
                    if (!md5(string + cipherKeys.getKey() + string2).equalsIgnoreCase(string3)) {
                        MLog.d(TAG, "decodeBody: 校验失败！");
                        return null;
                    }
                    String decrypt = CipherUtils.decrypt(string2, cipherKeys);
                    MLog.d(TAG, "decodeBody: " + decrypt);
                    return decrypt;
                }
                MLog.d(TAG, "decodeBody: 数据错误！");
                return null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v22 */
    public static <T> BaseResponse<T> parse(String str, Class<T> cls) {
        if (str != null) {
            try {
                PrintStream printStream = System.out;
                printStream.println("哇卡卡卡" + str);
                JSONObject parseObject = JSONObject.parseObject(str);
                BaseResponse<T> baseResponse = new BaseResponse<>();
                int intValue = parseObject.getIntValue("code");
                PrintStream printStream2 = System.out;
                printStream2.println("哇卡卡卡" + intValue);
                baseResponse.success = intValue;
                if (intValue == 0) {
                    Object obj = parseObject.get(NotificationCompat.CATEGORY_MESSAGE);
                    if (obj != null) {
                        if (obj instanceof String) {
                            baseResponse.msg = parseObject.getString(NotificationCompat.CATEGORY_MESSAGE);
                        } else if (obj instanceof JSONObject) {
                            baseResponse.msg = ((JSONObject) obj).getJSONObject("Message").getString("messagestr");
                        } else {
                            baseResponse.msg = "未知错误";
                        }
                    } else {
                        baseResponse.msg = "未知错误";
                    }
                } else if (intValue == 1) {
                    Object obj2 = parseObject.get(NotificationCompat.CATEGORY_MESSAGE);
                    if (obj2 != null) {
                        if (obj2 instanceof String) {
                            baseResponse.msg = parseObject.getString(NotificationCompat.CATEGORY_MESSAGE);
                        } else if (obj2 instanceof JSONObject) {
                            baseResponse.msg = parseObject.getJSONObject(NotificationCompat.CATEGORY_MESSAGE).getJSONObject("Message").getString("messagestr");
                        } else {
                            baseResponse.msg = "请求成功";
                        }
                    } else {
                        baseResponse.msg = "请求成功";
                    }
                } else {
                    baseResponse.msg = parseObject.getString(NotificationCompat.CATEGORY_MESSAGE);
                    if (baseResponse.msg == null) {
                        baseResponse.msg = "未知错误";
                    }
                }
                T t = (T) parseObject.get("data");
                if (t == null) {
                    t = (T) parseObject.get("dataList");
                }
                if (t != null) {
                    if (t instanceof JSONObject) {
                        if (!t.isEmpty()) {
                            baseResponse.code = 1000;
                            baseResponse.data = (T) JSONObject.toJavaObject((JSONObject) t, cls);
                        } else if (intValue == 1) {
                            baseResponse.code = 1002;
                            baseResponse.data = null;
                        } else {
                            baseResponse.code = 2002;
                            baseResponse.data = null;
                        }
                        baseResponse.list = null;
                    } else if (t instanceof JSONArray) {
                        JSONArray jSONArray = (JSONArray) t;
                        if (jSONArray.isEmpty()) {
                            if (intValue == 1) {
                                baseResponse.code = 1002;
                                baseResponse.data = null;
                                baseResponse.list = null;
                            } else {
                                baseResponse.code = 2002;
                                baseResponse.data = null;
                                baseResponse.list = null;
                            }
                        } else if (intValue == 1) {
                            baseResponse.code = 1001;
                            baseResponse.data = null;
                            baseResponse.list = jSONArray.toJavaList(cls);
                        } else {
                            baseResponse.code = 2002;
                            baseResponse.data = null;
                            baseResponse.list = null;
                        }
                    } else if (t instanceof String) {
                        baseResponse.code = 1000;
                        baseResponse.data = t;
                        baseResponse.list = null;
                    } else {
                        baseResponse.code = 2000;
                        baseResponse.data = null;
                        baseResponse.list = null;
                    }
                } else {
                    if (intValue != 1) {
                        baseResponse.code = 2001;
                    } else {
                        baseResponse.code = 1000;
                    }
                    baseResponse.data = null;
                    baseResponse.list = null;
                }
                return baseResponse;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String toURLEncoded(String str) {
        if (str != null && !str.equals("")) {
            try {
                return URLEncoder.encode(new String(str.getBytes(), "UTF-8"), "UTF-8");
            } catch (Exception unused) {
            }
        }
        return "";
    }
}
