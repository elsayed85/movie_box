package com.movieboxpro.android.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
/* loaded from: classes3.dex */
public class WrapContentScrollView extends ScrollView {
    private int mMaxHeight;

    public WrapContentScrollView(Context context) {
        super(context);
        this.mMaxHeight = 536870911;
    }

    public WrapContentScrollView(Context context, int i) {
        super(context);
        this.mMaxHeight = 536870911;
        this.mMaxHeight = i;
    }

    public WrapContentScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMaxHeight = 536870911;
    }

    public WrapContentScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mMaxHeight = 536870911;
    }

    public void setMaxHeight(int i) {
        if (this.mMaxHeight != i) {
            this.mMaxHeight = i;
            requestLayout();
        }
    }

    @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int makeMeasureSpec;
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams.height > 0 && layoutParams.height <= this.mMaxHeight) {
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824);
        } else {
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.mMaxHeight, Integer.MIN_VALUE);
        }
        super.onMeasure(i, makeMeasureSpec);
    }
}
