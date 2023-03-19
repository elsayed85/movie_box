package com.movieboxpro.android.utils;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.movieboxpro.android.app.App;
/* loaded from: classes3.dex */
public class LinearItemDecoration extends RecyclerView.ItemDecoration {
    private final int decoration;
    private boolean includeEdge;
    private boolean includeSide;
    private int orientation;

    public LinearItemDecoration(int i, int i2) {
        this.includeEdge = true;
        this.includeSide = false;
        this.orientation = i;
        this.decoration = DensityUtils.dp2px(App.getContext(), i2);
    }

    public LinearItemDecoration(int i, int i2, boolean z) {
        this.includeEdge = true;
        this.includeSide = false;
        this.orientation = i;
        this.decoration = DensityUtils.dp2px(App.getContext(), i2);
        this.includeEdge = z;
    }

    public LinearItemDecoration(int i, boolean z) {
        this.includeEdge = true;
        this.includeSide = false;
        this.orientation = 1;
        this.decoration = DensityUtils.dp2px(App.getContext(), i);
        this.includeEdge = z;
    }

    public LinearItemDecoration(int i) {
        this.includeEdge = true;
        this.includeSide = false;
        this.orientation = 1;
        this.decoration = DensityUtils.dp2px(App.getContext(), i);
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
        if (this.orientation == 1) {
            if (!this.includeEdge) {
                if (childLayoutPosition == 0) {
                    int i = this.decoration;
                    rect.set(0, i, 0, i);
                    return;
                }
                rect.set(0, 0, 0, this.decoration);
            } else if (childLayoutPosition == 0) {
                int i2 = this.decoration;
                rect.set(i2, i2, i2, i2);
            } else {
                int i3 = this.decoration;
                rect.set(i3, 0, i3, i3);
            }
        } else if (!this.includeEdge) {
            if (childLayoutPosition == 0) {
                int i4 = this.decoration;
                rect.set(i4, 0, i4, 0);
                return;
            }
            rect.set(0, 0, this.decoration, 0);
        } else if (childLayoutPosition == 0) {
            int i5 = this.decoration;
            rect.set(i5, i5, i5, i5);
        } else {
            int i6 = this.decoration;
            rect.set(0, i6, i6, i6);
        }
    }
}
