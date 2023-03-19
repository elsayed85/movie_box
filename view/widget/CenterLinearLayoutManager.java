package com.movieboxpro.android.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
/* loaded from: classes3.dex */
public class CenterLinearLayoutManager extends LinearLayoutManager {
    private Context context;

    public CenterLinearLayoutManager(Context context) {
        super(context);
        this.context = context;
    }

    public CenterLinearLayoutManager(Context context, int i, boolean z) {
        super(context, i, z);
        this.context = context;
    }

    public CenterLinearLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.context = context;
    }

    /* loaded from: classes3.dex */
    private static class CenterSmoothScroller extends LinearSmoothScroller {
        public CenterSmoothScroller(Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        public int calculateDtToFit(int i, int i2, int i3, int i4, int i5) {
            return (i3 + ((i4 - i3) / 2)) - (i + ((i2 - i) / 2));
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
            return 100.0f / displayMetrics.densityDpi;
        }
    }
}
