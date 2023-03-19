package com.movieboxpro.android.utils;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.movieboxpro.android.app.App;
/* loaded from: classes3.dex */
public class GridInsetDecoration extends RecyclerView.ItemDecoration {
    private int mHeaderCount;
    private boolean mIncludeEdge;
    private int mSpacing;
    private int mSpanCount;

    public GridInsetDecoration(int i, int i2, boolean z) {
        this.mSpacing = DensityUtils.dp2px(App.getContext(), i);
        this.mIncludeEdge = z;
        this.mHeaderCount = i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        if (childAdapterPosition <= this.mHeaderCount - 1) {
            return;
        }
        Log.d("GridInsetDecoration", "position:" + childAdapterPosition);
        int i = childAdapterPosition - this.mHeaderCount;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            this.mSpanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        }
        int i2 = this.mSpanCount;
        int i3 = i % i2;
        if (this.mIncludeEdge) {
            int i4 = this.mSpacing;
            rect.left = i4 - ((i3 * i4) / i2);
            rect.right = ((i3 + 1) * this.mSpacing) / this.mSpanCount;
            if (i < this.mSpanCount) {
                rect.top = this.mSpacing;
            }
            rect.bottom = this.mSpacing;
            Log.d("GridInsetDecoration", "left:" + rect.left + " right:" + rect.right + " top:" + rect.top + " bottom:" + rect.bottom);
            return;
        }
        rect.left = (this.mSpacing * i3) / i2;
        int i5 = this.mSpacing;
        rect.right = i5 - (((i3 + 1) * i5) / this.mSpanCount);
        if (i >= this.mSpanCount) {
            rect.top = this.mSpacing;
        }
    }
}
