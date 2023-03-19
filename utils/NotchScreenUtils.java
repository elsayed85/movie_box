package com.movieboxpro.android.utils;

import android.app.Activity;
import android.os.Build;
import android.text.TextUtils;
/* loaded from: classes3.dex */
public class NotchScreenUtils {
    public static boolean hasNotchInScreen(Activity activity) {
        if (Build.VERSION.SDK_INT >= 28) {
            return activity.getWindow().getDecorView().getRootWindowInsets().getDisplayCutout() != null;
        }
        String str = Build.MANUFACTURER;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.equalsIgnoreCase("HUAWEI")) {
            return hasNotchHw(activity);
        }
        if (str.equalsIgnoreCase("xiaomi")) {
            return hasNotchXiaoMi(activity);
        }
        if (str.equalsIgnoreCase("oppo")) {
            return hasNotchOPPO(activity);
        }
        if (str.equalsIgnoreCase("vivo")) {
            return hasNotchVIVO(activity);
        }
        return false;
    }

    private static boolean hasNotchVIVO(Activity activity) {
        try {
            Class<?> cls = Class.forName("android.util.FtFeature");
            return ((Boolean) cls.getMethod("isFeatureSupport", Integer.TYPE).invoke(cls, 32)).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean hasNotchOPPO(Activity activity) {
        return activity.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }

    private static boolean hasNotchXiaoMi(Activity activity) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return ((Integer) cls.getMethod("getInt", String.class, Integer.TYPE).invoke(cls, "ro.miui.notch", 0)).intValue() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean hasNotchHw(Activity activity) {
        try {
            Class<?> loadClass = activity.getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
            return ((Boolean) loadClass.getMethod("hasNotchInScreen", new Class[0]).invoke(loadClass, new Object[0])).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }
}
