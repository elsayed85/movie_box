package com.movieboxpro.android.utils;

import android.util.Log;
/* loaded from: classes.dex */
public class MLog {
    private static boolean isDebug;

    public static void config(boolean z) {
        isDebug = z;
    }

    public static void v(String str, String str2) {
        if (isDebug) {
            Log.v(str, str2);
        }
    }

    public static void d(String str, String str2) {
        if (isDebug) {
            Log.d(str, str2);
        }
    }

    public static void i(String str, String str2) {
        if (isDebug) {
            Log.i(str, str2);
        }
    }

    public static void w(String str, String str2) {
        if (isDebug) {
            Log.w(str, str2);
        }
    }

    public static void e(String str, String str2) {
        if (isDebug) {
            Log.e(str, str2);
        }
    }
}
