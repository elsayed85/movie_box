package com.movieboxpro.android.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
/* loaded from: classes3.dex */
public class InputMethodUtils {
    private static int sDecorViewDelta;

    /* loaded from: classes3.dex */
    public interface OnSoftInputChangedListener {
        void onSoftInputChanged(int i);
    }

    public static void toggleSoftInput() {
        InputMethodManager inputMethodManager = (InputMethodManager) Utils.getApp().getSystemService("input_method");
        if (inputMethodManager == null) {
            return;
        }
        inputMethodManager.toggleSoftInput(0, 0);
    }

    public static void showSoftInput(View view) {
        InputMethodManager inputMethodManager;
        if (view == null || (inputMethodManager = (InputMethodManager) Utils.getApp().getSystemService("input_method")) == null) {
            return;
        }
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, 0, new ResultReceiver(new Handler()) { // from class: com.movieboxpro.android.utils.InputMethodUtils.1
            @Override // android.os.ResultReceiver
            protected void onReceiveResult(int i, Bundle bundle) {
                if (i == 1 || i == 3) {
                    InputMethodUtils.toggleSoftInput();
                }
            }
        });
        inputMethodManager.toggleSoftInput(2, 1);
    }

    public static void showSoftInput(Activity activity) {
        View currentFocus = activity.getCurrentFocus();
        if (currentFocus == null) {
            View decorView = activity.getWindow().getDecorView();
            View findViewWithTag = decorView.findViewWithTag("keyboardTagView");
            if (findViewWithTag == null) {
                findViewWithTag = new EditText(activity);
                findViewWithTag.setTag("keyboardTagView");
                ((ViewGroup) decorView).addView(findViewWithTag, 0, 0);
            }
            currentFocus = findViewWithTag;
            currentFocus.requestFocus();
        }
        hideSoftInput(currentFocus);
    }

    public static void hideSoftInput(View view) {
        InputMethodManager inputMethodManager;
        if (view == null || (inputMethodManager = (InputMethodManager) Utils.getApp().getSystemService("input_method")) == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static boolean hideSoftInput(Activity activity) {
        if (activity.getCurrentFocus() != null) {
            return ((InputMethodManager) activity.getSystemService("input_method")).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
        return false;
    }

    public static boolean isActive(Context context) {
        return ((InputMethodManager) context.getSystemService("input_method")).isActive();
    }

    public static void registerSoftInputChangedListener(Activity activity, OnSoftInputChangedListener onSoftInputChangedListener) {
        registerSoftInputChangedListener(activity.getWindow(), onSoftInputChangedListener);
    }

    public static void registerSoftInputChangedListener(final Window window, final OnSoftInputChangedListener onSoftInputChangedListener) {
        if ((window.getAttributes().flags & 512) != 0) {
            window.clearFlags(512);
        }
        final int[] iArr = {getDecorViewInvisibleHeight(window)};
        ((FrameLayout) window.findViewById(16908290)).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.movieboxpro.android.utils.-$$Lambda$InputMethodUtils$3XfNaVG5DE-DuDEOGKZYx2eyJU4
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                InputMethodUtils.lambda$registerSoftInputChangedListener$0(window, iArr, onSoftInputChangedListener);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$registerSoftInputChangedListener$0(Window window, int[] iArr, OnSoftInputChangedListener onSoftInputChangedListener) {
        int decorViewInvisibleHeight = getDecorViewInvisibleHeight(window);
        if (iArr[0] != decorViewInvisibleHeight) {
            onSoftInputChangedListener.onSoftInputChanged(decorViewInvisibleHeight);
            iArr[0] = decorViewInvisibleHeight;
        }
    }

    private static int getDecorViewInvisibleHeight(Window window) {
        View decorView = window.getDecorView();
        if (decorView == null) {
            return 0;
        }
        Rect rect = new Rect();
        decorView.getWindowVisibleDisplayFrame(rect);
        Log.d("KeyboardUtils", "getDecorViewInvisibleHeight: " + (decorView.getBottom() - rect.bottom));
        int abs = Math.abs(decorView.getBottom() - rect.bottom);
        if (abs <= getNavBarHeight() + getStatusBarHeight()) {
            sDecorViewDelta = abs;
            return 0;
        }
        return abs - sDecorViewDelta;
    }

    private static int getStatusBarHeight() {
        Resources resources = Utils.getApp().getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", "android"));
    }

    private static int getNavBarHeight() {
        Resources resources = Utils.getApp().getResources();
        int identifier = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier != 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }
}
