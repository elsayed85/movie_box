package com.movieboxpro.android.view.widget.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;
/* loaded from: classes3.dex */
public class MyScrollerView extends ScrollView {
    public OnScrollListener scrollListener;

    /* loaded from: classes3.dex */
    public interface OnScrollListener {
        void onScroll(int i, int i2);
    }

    public void setScrollListener(OnScrollListener onScrollListener) {
        this.scrollListener = onScrollListener;
    }

    public MyScrollerView(Context context) {
        super(context);
    }

    public MyScrollerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MyScrollerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public MyScrollerView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    @Override // android.view.View
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        OnScrollListener onScrollListener = this.scrollListener;
        if (onScrollListener != null) {
            onScrollListener.onScroll(i2, i4);
        }
    }
}
