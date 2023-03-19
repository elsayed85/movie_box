package com.movieboxpro.android.utils;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.movieboxpro.android.app.App;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.videolan.libvlc.MediaDiscoverer;
/* loaded from: classes3.dex */
public class ScreenUtils {
    public static final int DEFAULT_DAY_BRIGHTNESS = 80;
    public static final int DEFAULT_NIGHT_BRIGHTNESS = 10;

    /* loaded from: classes3.dex */
    public enum BrightType {
        USER,
        SYSTEM,
        DEFAULT
    }

    /* loaded from: classes3.dex */
    public enum EScreenDensity {
        XXHDPI,
        XHDPI,
        HDPI,
        MDPI
    }

    public static int convertFactorToProgress(float f) {
        return (int) (((f + 1.0f) / 2.0f) * 255.0f);
    }

    public static float convertProgressToFactor(int i) {
        return ((i / 255.0f) * 2.0f) - 1.0f;
    }

    public static EScreenDensity getDisply(Context context) {
        int i = context.getApplicationContext().getResources().getDisplayMetrics().densityDpi;
        if (i <= 160) {
            return EScreenDensity.MDPI;
        }
        if (i <= 240) {
            return EScreenDensity.HDPI;
        }
        if (i < 400) {
            return EScreenDensity.XHDPI;
        }
        return EScreenDensity.XXHDPI;
    }

    public static int getScreenWidth() {
        return App.getContext().getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return App.getContext().getResources().getDisplayMetrics().heightPixels;
    }

    public static float dpToPx(float f) {
        return f * App.getContext().getResources().getDisplayMetrics().density;
    }

    public static int dpToPxInt(float f) {
        return (int) (dpToPx(f) + 0.5f);
    }

    public static float pxToDp(float f) {
        return f / App.getContext().getResources().getDisplayMetrics().density;
    }

    public static int pxToDpInt(float f) {
        return (int) (pxToDp(f) + 0.5f);
    }

    public static float pxToSp(float f) {
        return f / App.getContext().getResources().getDisplayMetrics().scaledDensity;
    }

    public static float spToPx(float f) {
        return f * App.getContext().getResources().getDisplayMetrics().scaledDensity;
    }

    public static int getStatusBarHeight(Context context) {
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static int getActionBarSize(Context context) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(16843499, typedValue, true)) {
            return TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
        }
        return 0;
    }

    private int getStatusBarHeight() {
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            return App.getContext().getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static final boolean isLandscape(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }

    public static final boolean isPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == 1;
    }

    public static void dimBackground(float f, float f2, Activity activity) {
        final Window window = activity.getWindow();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f, f2);
        ofFloat.setDuration(500L);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.movieboxpro.android.utils.ScreenUtils.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.alpha = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                window.setAttributes(attributes);
            }
        });
        ofFloat.start();
    }

    public static boolean isAutoBrightness(Context context) {
        try {
            return Settings.System.getInt(context.getContentResolver(), "screen_brightness_mode") == 1;
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void disableAutoBrightness(Context context) {
        try {
            Settings.System.putInt(context.getContentResolver(), "screen_brightness_mode", 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void enableAutoBrightness(Context context) {
        try {
            Settings.System.putInt(context.getContentResolver(), "screen_brightness_mode", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getSystemBrightness(Context context) {
        try {
            return Settings.System.getInt(context.getContentResolver(), "screen_brightness");
        } catch (Exception e) {
            e.printStackTrace();
            return 80;
        }
    }

    public static void setSystemBrightness(int i, Context context) {
        try {
            Settings.System.putInt(context.getContentResolver(), "screen_brightness", i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static float getAutoBrightnessFactor(Context context) {
        try {
            return Settings.System.getFloat(context.getContentResolver(), "screen_auto_brightness_adj");
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return -0.5f;
        }
    }

    public static void setAutoBrightnessFactor(float f, Context context) {
        try {
            Settings.System.putFloat(context.getContentResolver(), "screen_auto_brightness_adj", f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setActivityBrightness(Activity activity, int i) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.screenBrightness = i / 255.0f;
        window.setAttributes(attributes);
    }

    public static void resetActivityBrightness(Activity activity) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.screenBrightness = -1.0f;
        window.setAttributes(attributes);
    }

    public static int getViewMeasuredHeight(View view) {
        calculateViewMeasure(view);
        return view.getMeasuredHeight();
    }

    public static int getViewMeasuredWidth(View view) {
        calculateViewMeasure(view);
        return view.getMeasuredWidth();
    }

    public static int getTextWidth(String str) {
        Paint paint = new Paint();
        if (str == null || str.length() <= 0) {
            return 0;
        }
        int length = str.length();
        float[] fArr = new float[length];
        paint.getTextWidths(str, fArr);
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            i += (int) Math.ceil(fArr[i2]);
        }
        return i;
    }

    private static void calculateViewMeasure(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    public static int getStatusHeigh(Activity activity) {
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.top;
    }

    public static boolean checkIsEmpty(EditText editText) {
        return editText == null || editText.getText().toString().trim().equals("");
    }

    public static String getEditString(EditText editText) {
        if (editText == null) {
            return null;
        }
        return editText.getText().toString().trim();
    }

    public static boolean IsSameStr(EditText editText, EditText editText2) {
        return getEditString(editText).equals(getEditString(editText2));
    }

    public static void setDrawableLeft(View view, int i) {
        try {
            Drawable drawable = view.getContext().getResources().getDrawable(i);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            if (view instanceof Button) {
                ((Button) view).setCompoundDrawables(drawable, null, null, null);
            }
            if (view instanceof TextView) {
                ((TextView) view).setCompoundDrawables(drawable, null, null, null);
            }
            if (view instanceof EditText) {
                ((EditText) view).setCompoundDrawables(drawable, null, null, null);
            }
        } catch (Exception unused) {
        }
    }

    public static void setDrawableLeft(View view, int i, int i2, int i3) {
        try {
            Drawable drawable = view.getContext().getResources().getDrawable(i);
            drawable.setBounds(i2, i3, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            if (view instanceof Button) {
                ((Button) view).setCompoundDrawables(drawable, null, null, null);
            }
            if (view instanceof TextView) {
                ((TextView) view).setCompoundDrawables(drawable, null, null, null);
            }
            if (view instanceof EditText) {
                ((EditText) view).setCompoundDrawables(drawable, null, null, null);
            }
        } catch (Exception unused) {
        }
    }

    public static void setDrawableRight(View view, int i) {
        try {
            Drawable drawable = view.getContext().getResources().getDrawable(i);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            if (view instanceof Button) {
                ((Button) view).setCompoundDrawables(null, null, drawable, null);
            }
            if (view instanceof TextView) {
                ((TextView) view).setCompoundDrawables(null, null, drawable, null);
            }
            if (view instanceof EditText) {
                ((EditText) view).setCompoundDrawables(null, null, drawable, null);
            }
        } catch (Exception unused) {
        }
    }

    public static int StatusBarLightMode(Activity activity) {
        if (Build.VERSION.SDK_INT >= 19) {
            if (MIUISetStatusBarLightMode(activity.getWindow(), true)) {
                return 1;
            }
            if (FlymeSetStatusBarLightMode(activity.getWindow(), true)) {
                return 2;
            }
            if (Build.VERSION.SDK_INT >= 23) {
                activity.getWindow().getDecorView().setSystemUiVisibility(9216);
                return 3;
            }
        }
        return 0;
    }

    public static void StatusBarLightMode(Activity activity, int i) {
        if (i == 1) {
            MIUISetStatusBarLightMode(activity.getWindow(), true);
        } else if (i == 2) {
            FlymeSetStatusBarLightMode(activity.getWindow(), true);
        } else if (i == 3) {
            activity.getWindow().getDecorView().setSystemUiVisibility(9216);
        }
    }

    public static void StatusBarDarkMode(Activity activity, int i) {
        if (i == 1) {
            MIUISetStatusBarLightMode(activity.getWindow(), false);
        } else if (i == 2) {
            FlymeSetStatusBarLightMode(activity.getWindow(), false);
        } else if (i == 3) {
            activity.getWindow().getDecorView().setSystemUiVisibility(0);
        }
    }

    public static boolean FlymeSetStatusBarLightMode(Window window, boolean z) {
        if (window != null) {
            try {
                WindowManager.LayoutParams attributes = window.getAttributes();
                Field declaredField = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field declaredField2 = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                declaredField.setAccessible(true);
                declaredField2.setAccessible(true);
                int i = declaredField.getInt(null);
                int i2 = declaredField2.getInt(attributes);
                declaredField2.setInt(attributes, z ? i2 | i : (i ^ (-1)) & i2);
                window.setAttributes(attributes);
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public static boolean MIUISetStatusBarLightMode(Window window, boolean z) {
        if (window != null) {
            Class<?> cls = window.getClass();
            try {
                Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
                Method method = cls.getMethod("setExtraFlags", Integer.TYPE, Integer.TYPE);
                if (z) {
                    method.invoke(window, Integer.valueOf(i), Integer.valueOf(i));
                } else {
                    method.invoke(window, 0, Integer.valueOf(i));
                }
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public static void setImmersionStateMode(Activity activity) {
        StatusBarLightMode(activity);
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT != 21) {
            activity.getWindow().addFlags(67108864);
        } else if (Build.VERSION.SDK_INT == 21) {
            Window window = activity.getWindow();
            window.clearFlags(201326592);
            window.getDecorView().setSystemUiVisibility(MediaDiscoverer.Event.Started);
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(0);
            window.setNavigationBarColor(0);
        }
    }

    public static ViewGroup.LayoutParams setViewMargin(View view, boolean z, int i, int i2, int i3, int i4) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        if (view == null) {
            return null;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        } else {
            marginLayoutParams = new ViewGroup.MarginLayoutParams(layoutParams);
        }
        marginLayoutParams.setMargins(i, i3, i2, i4);
        view.setLayoutParams(marginLayoutParams);
        return marginLayoutParams;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dip(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2sp(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static int sp2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }
}
