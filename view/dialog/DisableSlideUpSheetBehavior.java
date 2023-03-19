package com.movieboxpro.android.view.dialog;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
/* loaded from: classes3.dex */
public class DisableSlideUpSheetBehavior<V extends View> extends BottomSheetBehavior<V> {
    public DisableSlideUpSheetBehavior() {
    }

    public DisableSlideUpSheetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int[] iArr) {
        if (i2 <= 0) {
            super.onNestedPreScroll(coordinatorLayout, v, view, i, i2, iArr);
        }
    }
}
