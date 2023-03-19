package com.movieboxpro.android.view.widget;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.utils.DensityUtils;
/* loaded from: classes3.dex */
public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
    private boolean includeEdge;
    private int spacing;
    private int spanCount;

    public GridSpacingItemDecoration(int i, int i2, boolean z) {
        this.spanCount = i;
        this.spacing = i2;
        this.includeEdge = z;
    }

    public GridSpacingItemDecoration(int i, boolean z) {
        this.spacing = DensityUtils.dp2px(App.getContext(), i);
        this.includeEdge = z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            this.spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        }
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        int i = this.spanCount;
        int i2 = childAdapterPosition % i;
        if (this.includeEdge) {
            int i3 = this.spacing;
            rect.left = i3 - ((i2 * i3) / i);
            rect.right = ((i2 + 1) * this.spacing) / this.spanCount;
            if (childAdapterPosition < this.spanCount) {
                rect.top = this.spacing;
            }
            rect.bottom = this.spacing;
            return;
        }
        rect.left = (this.spacing * i2) / i;
        int i4 = this.spacing;
        rect.right = i4 - (((i2 + 1) * i4) / this.spanCount);
        if (childAdapterPosition < this.spanCount) {
            rect.top = this.spacing;
        }
        rect.bottom = this.spacing;
    }
}
