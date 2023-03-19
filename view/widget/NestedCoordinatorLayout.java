package com.movieboxpro.android.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.ViewCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes3.dex */
public class NestedCoordinatorLayout extends CoordinatorLayout implements NestedScrollingChild {
    public static final int PASS_MODE_BOTH = 0;
    public static final int PASS_MODE_PARENT_FIRST = 1;
    private static final String TAG = NestedCoordinatorLayout.class.getSimpleName();
    private DummyBehavior dummyBehavior;
    private NestedScrollingChildHelper helper;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PassMode {
    }

    public NestedCoordinatorLayout(Context context) {
        super(context);
        i();
    }

    public NestedCoordinatorLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        i();
    }

    public NestedCoordinatorLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        i();
    }

    private void i() {
        this.helper = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
        View view = new View(getContext());
        this.dummyBehavior = new DummyBehavior();
        ViewCompat.setElevation(view, ViewCompat.getElevation(this));
        view.setFitsSystemWindows(false);
        CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(-1, -1);
        layoutParams.setBehavior(this.dummyBehavior);
        addView(view, layoutParams);
    }

    public void setPassMode(int i) {
        DummyBehavior dummyBehavior = this.dummyBehavior;
        if (dummyBehavior != null) {
            dummyBehavior.setPassMode(i);
        }
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public void setNestedScrollingEnabled(boolean z) {
        this.helper.setNestedScrollingEnabled(z);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean isNestedScrollingEnabled() {
        return this.helper.isNestedScrollingEnabled();
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean startNestedScroll(int i) {
        return this.helper.startNestedScroll(i);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public void stopNestedScroll() {
        this.helper.stopNestedScroll();
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean hasNestedScrollingParent() {
        return this.helper.hasNestedScrollingParent();
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return this.helper.dispatchNestedScroll(i, i2, i3, i4, iArr);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return this.helper.dispatchNestedPreScroll(i, i2, iArr, iArr2);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return this.helper.dispatchNestedFling(f, f2, z);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedPreFling(float f, float f2) {
        return this.helper.dispatchNestedPreFling(f, f2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class DummyBehavior<DummyView extends View> extends CoordinatorLayout.Behavior<DummyView> {
        private int mode = 0;
        private final int[] cache = new int[2];

        DummyBehavior() {
        }

        void setPassMode(int i) {
            this.mode = i;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, DummyView dummyview, View view, View view2, int i, int i2) {
            return ((NestedCoordinatorLayout) coordinatorLayout).startNestedScroll(i);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, DummyView dummyview, View view, int i) {
            ((NestedCoordinatorLayout) coordinatorLayout).stopNestedScroll();
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, DummyView dummyview, View view, int i, int i2, int[] iArr, int i3) {
            NestedCoordinatorLayout nestedCoordinatorLayout = (NestedCoordinatorLayout) coordinatorLayout;
            int i4 = this.mode;
            if (i4 == 1) {
                nestedCoordinatorLayout.dispatchNestedPreScroll(i, i2, iArr, null);
            } else if (i4 == 0) {
                int[] iArr2 = this.cache;
                iArr2[0] = iArr[0];
                iArr2[1] = iArr[1];
                nestedCoordinatorLayout.dispatchNestedPreScroll(i, i2, iArr2, null);
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, DummyView dummyview, View view, float f, float f2) {
            boolean dispatchNestedPreFling = ((NestedCoordinatorLayout) coordinatorLayout).dispatchNestedPreFling(f, f2);
            if (this.mode == 1) {
                return dispatchNestedPreFling;
            }
            return false;
        }
    }
}
