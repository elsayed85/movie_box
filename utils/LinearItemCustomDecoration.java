package com.movieboxpro.android.utils;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes3.dex */
public class LinearItemCustomDecoration extends RecyclerView.ItemDecoration {
    private int bottom;
    private int left;
    private int orientation;
    private int right;

    /* renamed from: top  reason: collision with root package name */
    private int f55top;

    public LinearItemCustomDecoration(int i, int i2, int i3, int i4) {
        this.orientation = 1;
        this.f55top = DensityUtils.dp2px(i);
        this.left = DensityUtils.dp2px(i2);
        this.right = DensityUtils.dp2px(i3);
        this.bottom = DensityUtils.dp2px(i4);
    }

    public LinearItemCustomDecoration(int i, int i2, int i3, int i4, int i5) {
        this.orientation = 1;
        this.orientation = i;
        this.f55top = DensityUtils.dp2px(i2);
        this.left = DensityUtils.dp2px(i3);
        this.right = DensityUtils.dp2px(i4);
        this.bottom = DensityUtils.dp2px(i5);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        state.getItemCount();
        int childLayoutPosition = recyclerView.getChildLayoutPosition(view);
        if (childLayoutPosition == -1 || !(layoutManager instanceof LinearLayoutManager) || (layoutManager instanceof GridLayoutManager)) {
            return;
        }
        if (this.orientation == 0) {
            if (childLayoutPosition == 0) {
                rect.set(this.left, this.f55top, this.right, this.bottom);
            } else {
                rect.set(this.left / 2, this.f55top, this.right / 2, this.bottom);
            }
        } else if (childLayoutPosition == 0) {
            rect.set(this.left, this.f55top, this.right, this.bottom / 2);
        } else {
            rect.set(this.left, this.f55top / 2, this.right, this.bottom / 2);
        }
    }
}
