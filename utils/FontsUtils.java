package com.movieboxpro.android.utils;

import android.content.Context;
import android.graphics.Typeface;
import java.lang.reflect.Field;
/* loaded from: classes3.dex */
public class FontsUtils {
    public static void setDefaultFont(Context context, String str, String str2) {
        try {
            replaceFont(str, Typeface.createFromAsset(context.getAssets(), str2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void replaceFont(String str, Typeface typeface) {
        try {
            Field declaredField = Typeface.class.getDeclaredField(str);
            declaredField.setAccessible(true);
            declaredField.set(null, typeface);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
        }
    }
}
