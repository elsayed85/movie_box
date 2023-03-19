package com.movieboxpro.android.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.gyf.immersionbar.ImmersionBar;
import com.movieboxpro.android.app.App;
/* loaded from: classes3.dex */
public class DensityUtils {
    private static final int KEY_OFFSET = -123;
    private static final String TAG_OFFSET = "TAG_OFFSET";

    public static int dp2px(Context context, float f) {
        return (int) TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }

    public static int dp2px(float f) {
        return (int) TypedValue.applyDimension(1, f, App.getContext().getResources().getDisplayMetrics());
    }

    public static int px2dp(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int sp2px(float f) {
        return (int) TypedValue.applyDimension(2, f, App.getContext().getResources().getDisplayMetrics());
    }

    public static int px2sp(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static void addMarginTopEqualStatusBarHeight(View view, Activity activity) {
        if (Build.VERSION.SDK_INT < 19) {
            return;
        }
        view.setTag(TAG_OFFSET);
        Object tag = view.getTag(KEY_OFFSET);
        if (tag == null || !((Boolean) tag).booleanValue()) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            marginLayoutParams.setMargins(marginLayoutParams.leftMargin, marginLayoutParams.topMargin + ImmersionBar.getStatusBarHeight(activity), marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
            view.setTag(KEY_OFFSET, true);
        }
    }

    public static void addMargin(View view, int i, int i2, int i3, int i4) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        marginLayoutParams.setMargins(marginLayoutParams.leftMargin + dp2px(App.getContext(), i), marginLayoutParams.topMargin + dp2px(App.getContext(), i2), marginLayoutParams.rightMargin + dp2px(App.getContext(), i3), marginLayoutParams.bottomMargin + dp2px(App.getContext(), i4));
    }

    public static void setMarginPx(View view, int i, int i2, int i3, int i4) {
        ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).setMargins(i, i2, i3, i4);
        view.requestLayout();
    }

    public static void addMarginPx(View view, int i, int i2, int i3, int i4) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        marginLayoutParams.setMargins(marginLayoutParams.leftMargin + i, marginLayoutParams.topMargin + i2, marginLayoutParams.rightMargin + i3, marginLayoutParams.bottomMargin + i4);
        view.requestLayout();
    }

    public static ViewGroup.MarginLayoutParams getMarginLayoutParams(View view) {
        return (ViewGroup.MarginLayoutParams) view.getLayoutParams();
    }

    public static int getStatusBarHeight() {
        Resources system = Resources.getSystem();
        return system.getDimensionPixelSize(system.getIdentifier("status_bar_height", "dimen", "android"));
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context) {
        if (context == null) {
            return 0;
        }
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static WindowManager getWindowManager(Context context) {
        return (WindowManager) context.getSystemService("window");
    }
}
