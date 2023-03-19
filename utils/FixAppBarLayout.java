package com.movieboxpro.android.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.OverScroller;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import java.lang.reflect.Field;
/* loaded from: classes3.dex */
public class FixAppBarLayout extends AppBarLayout.Behavior {
    private static final String TAG = "AppbarLayoutBehavior";
    private static final int TYPE_FLING = 1;
    private boolean isFlinging;
    private boolean shouldBlockNestedScroll;

    @Override // com.google.android.material.appbar.HeaderBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public /* bridge */ /* synthetic */ boolean onTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        return super.onTouchEvent(coordinatorLayout, view, motionEvent);
    }

    public FixAppBarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.google.android.material.appbar.HeaderBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, MotionEvent motionEvent) {
        LogUtil.d(TAG, "onInterceptTouchEvent:" + appBarLayout.getTotalScrollRange());
        this.shouldBlockNestedScroll = this.isFlinging;
        if (motionEvent.getActionMasked() == 0) {
            stopAppbarLayoutFling(appBarLayout);
        }
        return super.onInterceptTouchEvent(coordinatorLayout, (CoordinatorLayout) appBarLayout, motionEvent);
    }

    private Field getFlingRunnableField() throws NoSuchFieldException {
        Class<? super Object> superclass;
        Class<? super Object> superclass2 = getClass().getSuperclass();
        if (superclass2 != null) {
            try {
                superclass = superclass2.getSuperclass();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                Class<? super Object> superclass3 = superclass2.getSuperclass().getSuperclass();
                if (superclass3 != null) {
                    return superclass3.getDeclaredField("flingRunnable");
                }
            }
        } else {
            superclass = null;
        }
        if (superclass != null) {
            return superclass.getDeclaredField("mFlingRunnable");
        }
        return null;
    }

    private Field getScrollerField() throws NoSuchFieldException {
        Class<? super Object> superclass;
        Class<? super Object> superclass2 = getClass().getSuperclass();
        if (superclass2 != null) {
            try {
                superclass = superclass2.getSuperclass();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                Class<? super Object> superclass3 = superclass2.getSuperclass().getSuperclass();
                if (superclass3 != null) {
                    return superclass3.getDeclaredField("scroller");
                }
            }
        } else {
            superclass = null;
        }
        if (superclass != null) {
            return superclass.getDeclaredField("mScroller");
        }
        return null;
    }

    private void stopAppbarLayoutFling(AppBarLayout appBarLayout) {
        try {
            Field flingRunnableField = getFlingRunnableField();
            Field scrollerField = getScrollerField();
            if (flingRunnableField != null) {
                flingRunnableField.setAccessible(true);
            }
            if (scrollerField != null) {
                scrollerField.setAccessible(true);
            }
            Runnable runnable = flingRunnableField != null ? (Runnable) flingRunnableField.get(this) : null;
            OverScroller overScroller = (OverScroller) scrollerField.get(this);
            if (runnable != null) {
                LogUtil.d(TAG, "存在flingRunnable");
                appBarLayout.removeCallbacks(runnable);
                flingRunnableField.set(this, null);
            }
            if (overScroller == null || overScroller.isFinished()) {
                return;
            }
            overScroller.abortAnimation();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, View view2, int i, int i2) {
        LogUtil.d(TAG, "onStartNestedScroll");
        stopAppbarLayoutFling(appBarLayout);
        return super.onStartNestedScroll(coordinatorLayout, appBarLayout, view, view2, i, i2);
    }

    @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i, int i2, int[] iArr, int i3) {
        LogUtil.d(TAG, "onNestedPreScroll:" + appBarLayout.getTotalScrollRange() + " ,dx:" + i + " ,dy:" + i2 + " ,type:" + i3);
        if (i3 == 1) {
            this.isFlinging = true;
        }
        if (this.shouldBlockNestedScroll) {
            return;
        }
        super.onNestedPreScroll(coordinatorLayout, appBarLayout, view, i, i2, iArr, i3);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i, int i2, int i3, int i4, int i5) {
        LogUtil.d(TAG, "onNestedScroll: target:" + view.getClass() + " ," + appBarLayout.getTotalScrollRange() + " ,dxConsumed:" + i + " ,dyConsumed:" + i2 + " ,type:" + i5);
        if (this.shouldBlockNestedScroll) {
            return;
        }
        super.onNestedScroll(coordinatorLayout, (CoordinatorLayout) appBarLayout, view, i, i2, i3, i4, i5);
    }

    @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i) {
        LogUtil.d(TAG, "onStopNestedScroll");
        super.onStopNestedScroll(coordinatorLayout, appBarLayout, view, i);
        this.isFlinging = false;
        this.shouldBlockNestedScroll = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class LogUtil {
        private LogUtil() {
        }

        static void d(String str, String str2) {
            Log.d(str, str2);
        }
    }
}
