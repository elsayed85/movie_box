package com.movieboxpro.android.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.flurry.android.FlurryAgent;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.http.HttpUtils;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import retrofit2.HttpException;
/* loaded from: classes3.dex */
public class CommonUtils {
    public static final String REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    public static int getTomatoImg(int i) {
        return i == 0 ? R.mipmap.ic_tomato : i < 60 ? R.mipmap.ic_tomato_low : i < 75 ? R.mipmap.ic_tomato : R.mipmap.ic_tomato_high;
    }

    public static int getTomatoScore(int i) {
        return i < 60 ? R.mipmap.ic_tomato_score_low : R.mipmap.ic_tomato_score_high;
    }

    public static boolean isEmail(CharSequence charSequence) {
        return isMatch(REGEX_EMAIL, charSequence);
    }

    public static boolean isMatch(String str, CharSequence charSequence) {
        return charSequence != null && charSequence.length() > 0 && Pattern.matches(str, charSequence);
    }

    public static boolean canExportVideos() {
        return Build.VERSION.SDK_INT > 29;
    }

    public static String getErrorContent(Throwable th) {
        if (th instanceof HttpException) {
            try {
                return ((HttpException) th).response().errorBody().string();
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        }
        return "";
    }

    public static void onEvent(String str) {
        FlurryAgent.logEvent(str);
    }

    public static boolean isAppInstalled(String str) {
        try {
            return Utils.getApp().getPackageManager().getApplicationInfo(str, 0).enabled;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    public static String divide(String str, String str2, int i) {
        if (i < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        return new BigDecimal(str).divide(new BigDecimal(str2), i, 4).toString();
    }

    public static boolean isServiceRunning(Class<?> cls) {
        return isServiceRunning(cls.getName());
    }

    public static boolean isServiceRunning(String str) {
        List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) Utils.getApp().getSystemService("activity")).getRunningServices(Integer.MAX_VALUE);
        if (runningServices != null && runningServices.size() != 0) {
            for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServices) {
                if (str.equals(runningServiceInfo.service.getClassName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String multiply(String str, String str2, int i) {
        if (i < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        return new BigDecimal(str).multiply(new BigDecimal(str2)).setScale(i, 4).toString();
    }

    public static String urlEncode(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public static String getNameFirstLetter(String str) {
        String[] split = str.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String str2 : split) {
            if (str2.length() > 0) {
                sb.append(str2.charAt(0));
            }
        }
        return sb.toString().toUpperCase();
    }

    public static String getBBSApiAPPID() {
        return String.format(Locale.CHINA, "%d%s", Long.valueOf(TimeUtils.getCurrentTime() / 1000), HttpUtils.md5("27"));
    }

    public static void textShadow(TextView textView, String str, int i, int i2) {
        SpanUtils.with(textView).append(str).setFontSize(i, true).setForegroundColor(ContextCompat.getColor(App.getContext(), i2)).setShadow(3.0f, 0.0f, 4.0f, Color.parseColor("#80000000")).create();
    }

    public static int getMovieTag(String str) {
        if (TextUtils.isEmpty(str)) {
            return R.drawable.ic_blu_ray;
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -17498936:
                if (str.equals("blu-ray")) {
                    c = 2;
                    break;
                }
                break;
            case 1681:
                if (str.equals("3d")) {
                    c = 0;
                    break;
                }
                break;
            case 1719:
                if (str.equals("4k")) {
                    c = 1;
                    break;
                }
                break;
            case 3695:
                if (str.equals("tc")) {
                    c = 5;
                    break;
                }
                break;
            case 99858:
                if (str.equals("dvd")) {
                    c = 3;
                    break;
                }
                break;
            case 3198078:
                if (str.equals("hdtv")) {
                    c = 4;
                    break;
                }
                break;
            case 113005276:
                if (str.equals("webdl")) {
                    c = 6;
                    break;
                }
                break;
            case 1590465902:
                if (str.equals("4k_hdr")) {
                    c = 7;
                    break;
                }
                break;
            case 1704982506:
                if (str.equals("8k_hdr")) {
                    c = '\b';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return R.drawable.ic_3d;
            case 1:
                return R.drawable.ic_4k;
            case 2:
            default:
                return R.drawable.ic_blu_ray;
            case 3:
                return R.drawable.ic_dvd;
            case 4:
                return R.drawable.ic_hdtv;
            case 5:
                return R.drawable.ic_tc;
            case 6:
                return R.drawable.ic_webdl;
            case 7:
                return R.mipmap.ic_4k_hdr_tag;
            case '\b':
                return R.mipmap.ic_8k_hdr_tag;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int getMovieQualityTag(String str) {
        char c;
        switch (str.hashCode()) {
            case 1687:
                if (str.equals("4K")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 110308:
                if (str.equals("org")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1572835:
                if (str.equals("360p")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1688155:
                if (str.equals("720p")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 46737913:
                if (str.equals("1080p")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        return c != 0 ? c != 1 ? c != 2 ? c != 3 ? c != 4 ? R.drawable.ic_choose_sd : R.mipmap.ic_origin_rate : R.drawable.ic_choose_4k : R.drawable.ic_choose_fullhd : R.drawable.ic_choose_hd : R.drawable.ic_choose_sd;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int getDetailQualityTag(String str) {
        char c;
        switch (str.hashCode()) {
            case -1385972001:
                if (str.equals("bluray")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1719:
                if (str.equals("4k")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 3695:
                if (str.equals("tc")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 99811:
                if (str.equals("dts")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 95765848:
                if (str.equals("dolby")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1590465902:
                if (str.equals("4k_hdr")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1704982506:
                if (str.equals("8k_hdr")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return R.mipmap.detail_quality_8k_hdr;
            case 1:
                return R.mipmap.detail_quality_4k;
            case 2:
                return R.mipmap.detail_quality_4k_hdr;
            case 3:
                return R.mipmap.detail_quality_bluray;
            case 4:
                return R.mipmap.detail_quality_tc;
            case 5:
                return R.mipmap.detail_quality_dts;
            case 6:
                return R.mipmap.detail_quality_dolby;
            default:
                return -1;
        }
    }

    public static boolean isChildModeVideo(String str) {
        return TextUtils.isEmpty(str) || "R".equalsIgnoreCase(str) || "X".equalsIgnoreCase(str) || "NC-17".equalsIgnoreCase(str) || "TV-MA".equalsIgnoreCase(str);
    }

    public static String toUpperCaseFirstOne(String str) {
        if ("zh-tw".equals(str)) {
            return "Chinese(traditional)";
        }
        if ("zh-cn".equals(str)) {
            return "Chinese(simplified)";
        }
        if (Character.isUpperCase(str.charAt(0))) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    public static String replace(String str, String str2, String str3) {
        return str.replace(str2, str3);
    }

    public static String formatInteger(int i) {
        if (i < 10) {
            return "0" + i;
        }
        return String.valueOf(i);
    }

    public static boolean isTablet() {
        return getScreenWidthDP() > 600;
    }

    public static int getScreenWidthDP() {
        return DensityUtils.px2dp(App.getContext(), App.getContext().getResources().getDisplayMetrics().widthPixels);
    }

    public static boolean isScreenLandscape(Context context) {
        return context != null && context.getResources().getConfiguration().orientation == 2;
    }

    public static void initSwipeColor(SwipeRefreshLayout swipeRefreshLayout) {
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.parseColor("#464646"));
        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#FFDD00"));
    }

    public static boolean havePermissions(Context context, String[] strArr) {
        for (String str : strArr) {
            if (!havePermission(context, str)) {
                return false;
            }
        }
        return true;
    }

    private static boolean havePermission(Context context, String str) {
        return ContextCompat.checkSelfPermission(context, str) == 0;
    }

    public static String convertStreamToString(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                try {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        sb.append(readLine + ShellUtil.COMMAND_LINE_END);
                    } catch (Throwable th) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        throw th;
                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                    inputStream.close();
                }
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
        inputStream.close();
        return sb.toString();
    }

    public static String inputStream2String(InputStream inputStream, String str) {
        if (inputStream != null && !isSpace(str)) {
            try {
                ByteArrayOutputStream input2OutputStream = input2OutputStream(inputStream);
                return input2OutputStream == null ? "" : input2OutputStream.toString(str);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static ByteArrayOutputStream input2OutputStream(InputStream inputStream) {
        try {
            if (inputStream == null) {
                return null;
            }
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr, 0, 1024);
                    if (read != -1) {
                        byteArrayOutputStream.write(bArr, 0, read);
                    } else {
                        try {
                            break;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                inputStream.close();
                return byteArrayOutputStream;
            } catch (IOException e2) {
                e2.printStackTrace();
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                return null;
            }
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            throw th;
        }
    }

    private static boolean isSpace(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
