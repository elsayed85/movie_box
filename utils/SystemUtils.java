package com.movieboxpro.android.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.movieboxpro.android.BuildConfig;
import com.movieboxpro.android.app.App;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import org.eclipse.jetty.http.MimeTypes;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class SystemUtils {
    private static final String KEY_EMUI_API_LEVEL = "ro.build.hw_emui_api_level";
    private static final String KEY_EMUI_CONFIG_HW_SYS_VERSION = "ro.confg.hw_systemversion";
    private static final String KEY_EMUI_VERSION = "ro.build.version.emui";
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";
    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    public static final String SYS_DEFAULT = "android";
    public static final String SYS_EMUI = "sys_emui";
    public static final String SYS_FLYME = "sys_flyme";
    public static final String SYS_MIUI = "sys_miui";

    public static String checkSystem(Activity activity) {
        return null;
    }

    public static int getVersionCode(Context context) {
        return 169;
    }

    public static String getVersionName(Context context) {
        return BuildConfig.VERSION_NAME;
    }

    public static boolean isEmulator() {
        return Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.startsWith("unknown") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || "google_sdk".equals(Build.PRODUCT);
    }

    public static boolean hasNavBar(Context context) {
        String str;
        if (context == null) {
            context = App.getContext();
        }
        if (context != null) {
            Resources resources = context.getResources();
            int identifier = resources.getIdentifier("config_showNavigationBar", "bool", "android");
            boolean z = identifier > 0 ? resources.getBoolean(identifier) : false;
            try {
                Class<?> cls = Class.forName("android.os.SystemProperties");
                str = (String) cls.getMethod("get", String.class).invoke(cls, "qemu.hw.mainkeys");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE.equals(str)) {
                return false;
            }
            if ("0".equals(str)) {
                return true;
            }
            return z;
        }
        return false;
    }

    public static int getNavBarHeight(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier <= 0 || !hasNavBar(context)) {
            return 0;
        }
        return resources.getDimensionPixelOffset(identifier);
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getRealScreenHeight(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            Class.forName("android.view.Display").getMethod("getRealMetrics", DisplayMetrics.class).invoke(defaultDisplay, displayMetrics);
            return displayMetrics.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getMetaData(Context context, String str) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString(str);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getProcessName(Context context) {
        int myPid = Process.myPid();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        String str = "";
        if (runningAppProcesses != null) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == myPid) {
                    str = runningAppProcessInfo.processName;
                }
            }
        }
        return str;
    }

    private static String MD5(Context context) {
        try {
            Signature signature = context.getPackageManager().getPackageInfo(App.packageName, 64).signatures[0];
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(signature.toByteArray());
            return HexDump.toHexString(messageDigest.digest()).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String SHA1(Context context) {
        try {
            return HexDump.toHexString(MessageDigest.getInstance("SHA1").digest(context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0].toByteArray())).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap getVideoThumbnail(String str) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            try {
                mediaMetadataRetriever.setDataSource(str);
                Bitmap frameAtTime = mediaMetadataRetriever.getFrameAtTime(TimeUnit.MILLISECONDS.toMicros(1L));
                try {
                    mediaMetadataRetriever.release();
                    return frameAtTime;
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    return frameAtTime;
                }
            } catch (IllegalArgumentException e2) {
                e2.printStackTrace();
                try {
                    mediaMetadataRetriever.release();
                } catch (RuntimeException e3) {
                    e3.printStackTrace();
                }
                return null;
            }
        } catch (Throwable th) {
            try {
                mediaMetadataRetriever.release();
            } catch (RuntimeException e4) {
                e4.printStackTrace();
            }
            throw th;
        }
    }

    public static String getOSType() {
        return Build.MANUFACTURER.equals("HUAWEI") ? SYS_EMUI : Build.MANUFACTURER.equals("Xiaomi") ? SYS_MIUI : "android";
    }

    private static String getMeizuFlymeOSFlag() {
        return getSystemProperty("ro.build.display.id", "");
    }

    private static String getSystemProperty(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class, String.class).invoke(cls, str, str2);
        } catch (Exception e) {
            e.printStackTrace();
            return str2;
        }
    }

    public static boolean isServiceRunning(Context context, Class<?> cls) {
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) context.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE)) {
            if (cls.getName().equals(runningServiceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static String getMIMEType(String str) {
        if ("epub".equalsIgnoreCase(str)) {
            return "application/epub+zip";
        }
        if ("txt".equalsIgnoreCase(str) || "text".equalsIgnoreCase(str)) {
            return "text/plain";
        }
        if ("html".equalsIgnoreCase(str) || "htm".equalsIgnoreCase(str)) {
            return MimeTypes.TEXT_HTML;
        }
        return "application/" + str;
    }

    private static void checkBrowsers(Context context) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse("http://www.baidu.com"));
        for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(intent, 0)) {
            if (resolveInfo != null && resolveInfo.activityInfo != null) {
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                MLog.d("SystemUtils", "checkBrowsers: " + activityInfo.packageName + "/" + activityInfo.name + "/" + activityInfo.targetActivity);
            }
        }
    }

    public static void startBrowser(Context context, String str) {
        if (context == null) {
            return;
        }
        try {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            intent.setFlags(268435456);
            String str2 = "com.android.browser.BrowserActivity";
            List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
            String str3 = "com.android.browser";
            if (queryIntentActivities != null && !queryIntentActivities.isEmpty()) {
                String str4 = "com.android.browser";
                for (ResolveInfo resolveInfo : queryIntentActivities) {
                    if (resolveInfo != null && resolveInfo.activityInfo != null) {
                        ActivityInfo activityInfo = resolveInfo.activityInfo;
                        if (activityInfo.packageName != null && activityInfo.name != null) {
                            str4 = activityInfo.packageName;
                            str2 = activityInfo.name;
                            if (str4.equals("com.android.browser") || str4.equals("com.tencent.mtt") || str4.equals("com.UCMobile") || str4.equals("org.mozilla.firefox") || str4.equals("com.microsoft.emmx") || str4.equals("com.android.chrome")) {
                                break;
                            }
                        }
                    }
                }
                str3 = str4;
            }
            intent.setClassName(str3, str2);
            context.startActivity(intent);
        } catch (Exception unused) {
            Intent intent2 = new Intent();
            intent2.setFlags(268435456);
            intent2.setAction("android.intent.action.VIEW");
            intent2.setData(Uri.parse(str));
            try {
                context.startActivity(intent2);
            } catch (Exception unused2) {
                ToastUtils.showShort("No web browser");
            }
        }
    }

    public static void startBrowser(Activity activity, String str) {
        if (activity == null) {
            return;
        }
        try {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            intent.setFlags(268435456);
            String str2 = "com.android.browser.BrowserActivity";
            List<ResolveInfo> queryIntentActivities = activity.getPackageManager().queryIntentActivities(intent, 0);
            String str3 = "com.android.browser";
            if (queryIntentActivities != null && !queryIntentActivities.isEmpty()) {
                String str4 = "com.android.browser";
                for (ResolveInfo resolveInfo : queryIntentActivities) {
                    if (resolveInfo != null && resolveInfo.activityInfo != null) {
                        ActivityInfo activityInfo = resolveInfo.activityInfo;
                        if (activityInfo.packageName != null && activityInfo.name != null) {
                            str4 = activityInfo.packageName;
                            str2 = activityInfo.name;
                            if (str4.equals("com.android.browser") || str4.equals("com.tencent.mtt") || str4.equals("com.UCMobile") || str4.equals("org.mozilla.firefox") || str4.equals("com.microsoft.emmx") || str4.equals("com.android.chrome")) {
                                break;
                            }
                        }
                    }
                }
                str3 = str4;
            }
            intent.setClassName(str3, str2);
            activity.startActivity(intent);
        } catch (Exception unused) {
            Intent intent2 = new Intent();
            intent2.setFlags(268435456);
            intent2.setAction("android.intent.action.VIEW");
            intent2.setData(Uri.parse(str));
            activity.startActivity(Intent.createChooser(intent2, "Please select browser"));
        }
    }

    public static void installAPK(File file) {
        installAPK(App.getContext(), file);
    }

    public static void installAPK(Context context, File file) {
        Intent intent = new Intent();
        intent.setFlags(268435456);
        intent.setAction("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void openFile(Context context, File file) {
        Intent intent = new Intent();
        intent.setFlags(268435456);
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.fromFile(file));
        context.startActivity(Intent.createChooser(intent, "请选择应用打开"));
    }

    public static String getUniqueId(Context context) {
        if (context == null) {
            return "";
        }
        String str = Settings.Secure.getString(context.getContentResolver(), "android_id") + Build.SERIAL;
        try {
            return toMD5(str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return str;
        }
    }

    private static String toMD5(String str) throws NoSuchAlgorithmException {
        byte[] md5 = MD5Util.md5(str);
        StringBuilder sb = new StringBuilder();
        for (byte b : md5) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static void sendEmail(Activity activity) {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("mailto:support@movieboxpro.app"));
        intent.putExtra("android.intent.extra.CC", new String[]{"support@movieboxpro.app"});
        intent.putExtra("android.intent.extra.SUBJECT", "Error msg");
        intent.putExtra("android.intent.extra.TEXT", getMsg(activity));
        activity.startActivity(Intent.createChooser(intent, "Choose"));
    }

    public static String getMsg(Activity activity) {
        String str = "\n\napp_version:15.5\nappid:" + App.packageName + "\nplatform:Android\nAndroid:" + Build.VERSION.RELEASE + "\napi:" + Build.VERSION.SDK_INT + "\ndevice_name:" + Build.MODEL + "\nisEmulator:" + isEmulator() + "\nuser_name:" + App.getUserData().nickname + "\nuid:" + App.getUserData().getUid() + "\nis_vip:" + App.getUserData().isvip;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("app_version", BuildConfig.VERSION_NAME);
            jSONObject.putOpt("appid", App.packageName);
            jSONObject.putOpt("platform", "Android");
            HashMap hashMap = new HashMap();
            hashMap.put("Android", Build.VERSION.RELEASE + "\\\n");
            hashMap.put("api", Build.VERSION.SDK_INT + "\\\n");
            hashMap.put("device_name", Build.MODEL + "\\\n");
            hashMap.put("isEmulator", isEmulator() + "\\\n");
            hashMap.put("app_version", "15.5\\\n");
            hashMap.put("user_name", App.getUserData().name + "\\\n");
            hashMap.put("uid", App.getUserData().uid_v2 + "\\\n");
            hashMap.put("is_vip", App.getUserData().isvip + "\\\n");
            jSONObject.putOpt("env", "\\\n\\\n" + com.alibaba.fastjson.JSONObject.toJSONString(hashMap));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String getMsg() {
        String str = "\n\napp_version:15.5\nappid:" + App.packageName + "\nplatform:Android\nAndroid:" + Build.VERSION.RELEASE + "\napi:" + Build.VERSION.SDK_INT + "\ndevice_name:" + Build.MODEL + "\nisEmulator:" + isEmulator() + "\nuser_name:" + App.getUserData().nickname + "\nuid:" + App.getUserData().getUid() + "\nis_vip:" + App.getUserData().isvip;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("app_version", BuildConfig.VERSION_NAME);
            jSONObject.putOpt("appid", App.packageName);
            jSONObject.putOpt("platform", "Android");
            HashMap hashMap = new HashMap();
            hashMap.put("Android", Build.VERSION.RELEASE + "\\\n");
            hashMap.put("api", Build.VERSION.SDK_INT + "\\\n");
            hashMap.put("device_name", Build.MODEL + "\\\n");
            hashMap.put("isEmulator", isEmulator() + "\\\n");
            hashMap.put("app_version", "15.5\\\n");
            hashMap.put("user_name", App.getUserData().name + "\\\n");
            hashMap.put("uid", App.getUserData().uid_v2 + "\\\n");
            hashMap.put("is_vip", App.getUserData().isvip + "\\\n");
            jSONObject.putOpt("env", "\\\n\\\n" + com.alibaba.fastjson.JSONObject.toJSONString(hashMap));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String getMsg2() {
        String str = "app_version:15.5\nappid:" + App.packageName + "\nplatform:Android\nAndroid:" + Build.VERSION.RELEASE + "\napi:" + Build.VERSION.SDK_INT + "\ndevice_name:" + Build.MODEL + "\nisEmulator:" + isEmulator() + "\nuser_name:" + App.getUserData().nickname + "\nuid:" + App.getUserData().getUid() + "\nis_vip:" + App.getUserData().isvip;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("app_version", BuildConfig.VERSION_NAME);
            jSONObject.putOpt("appid", App.packageName);
            jSONObject.putOpt("platform", "Android");
            HashMap hashMap = new HashMap();
            hashMap.put("Android", Build.VERSION.RELEASE + "\\\n");
            hashMap.put("api", Build.VERSION.SDK_INT + "\\\n");
            hashMap.put("device_name", Build.MODEL + "\\\n");
            hashMap.put("isEmulator", isEmulator() + "\\\n");
            hashMap.put("app_version", "15.5\\\n");
            hashMap.put("user_name", App.getUserData().name + "\\\n");
            hashMap.put("uid", App.getUserData().uid_v2 + "\\\n");
            hashMap.put("is_vip", App.getUserData().isvip + "\\\n");
            jSONObject.putOpt("env", "\\\n\\\n" + com.alibaba.fastjson.JSONObject.toJSONString(hashMap));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static boolean isEmail(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        return Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*").matcher(str).matches();
    }

    public static boolean isMobileNO(String str) {
        return Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$").matcher(str).matches();
    }
}
