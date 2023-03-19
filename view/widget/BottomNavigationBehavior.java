package com.movieboxpro.android.view.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
/* loaded from: classes3.dex */
public class BottomNavigationBehavior extends CoordinatorLayout.Behavior<View> {
    private ObjectAnimator inAnimator;
    private ObjectAnimator outAnimator;

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i) {
        return i == 2;
    }

    public BottomNavigationBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int i2, int[] iArr) {
        if (i2 > 0) {
            if (this.outAnimator == null) {
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "translationY", 0.0f, view.getHeight());
                this.outAnimator = ofFloat;
                ofFloat.setDuration(200L);
            }
            if (this.outAnimator.isRunning() || view.getTranslationY() > 0.0f) {
                return;
            }
            this.outAnimator.start();
        } else if (i2 < 0) {
            if (this.inAnimator == null) {
                ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, "translationY", view.getHeight(), 0.0f);
                this.inAnimator = ofFloat2;
                ofFloat2.setDuration(200L);
            }
            if (this.inAnimator.isRunning() || view.getTranslationY() < view.getHeight()) {
                return;
            }
            this.inAnimator.start();
        }
    }
}
