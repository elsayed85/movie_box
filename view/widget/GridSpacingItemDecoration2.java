package com.movieboxpro.android.view.widget;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.utils.DensityUtils;
/* loaded from: classes3.dex */
public class GridSpacingItemDecoration2 extends RecyclerView.ItemDecoration {
    private boolean includeEdge;
    private int spacing;
    private int spanCount;

    public GridSpacingItemDecoration2(int i, int i2, boolean z) {
        this.spanCount = i;
        this.spacing = i2;
        this.includeEdge = z;
    }

    public GridSpacingItemDecoration2(int i, boolean z) {
        this.spacing = DensityUtils.dp2px(App.getContext(), i);
        this.includeEdge = z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            this.spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        }
        int itemCount = layoutManager != null ? layoutManager.getItemCount() : 0;
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
        int i4 = childAdapterPosition % i;
        if (i4 == 0) {
            rect.left = 0;
            rect.right = this.spacing / 2;
        } else if (i4 == i - 1) {
            rect.right = 0;
            rect.left = this.spacing / 2;
        } else {
            rect.right = this.spacing / 2;
            rect.left = this.spacing / 2;
        }
        int i5 = this.spanCount;
        if (childAdapterPosition < i5) {
            rect.top = 0;
            rect.bottom = this.spacing / 2;
        } else if (childAdapterPosition + 1 > (itemCount / i5) * i5) {
            rect.top = this.spacing / 2;
            rect.bottom = 0;
        } else {
            rect.top = this.spacing / 2;
            rect.bottom = this.spacing / 2;
        }
        Log.d("GridSpacingItem", "position" + childAdapterPosition + " top" + rect.top + " bottom" + rect.bottom + " left" + rect.left + " right" + rect.right);
    }
}
