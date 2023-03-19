package com.movieboxpro.android.utils;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.movieboxpro.android.app.App;
/* loaded from: classes3.dex */
public class LayoutManagerItemDecoration extends RecyclerView.ItemDecoration {
    private final int decoration;
    private int orientation;

    public LayoutManagerItemDecoration(int i, int i2) {
        this.orientation = i;
        this.decoration = DensityUtils.dp2px(App.getContext(), i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int itemCount = state.getItemCount() - 1;
        int childLayoutPosition = recyclerView.getChildLayoutPosition(view);
        if (childLayoutPosition == -1 || !(layoutManager instanceof LinearLayoutManager) || (layoutManager instanceof GridLayoutManager)) {
            return;
        }
        if (this.orientation != 1) {
            if (childLayoutPosition == itemCount) {
                rect.set(0, 0, 0, 0);
                return;
            } else {
                rect.set(0, 0, this.decoration, 0);
                return;
            }
        }
        rect.set(0, 0, 0, this.decoration);
        if (childLayoutPosition == 0) {
            int i = this.decoration;
            rect.set(0, i, 0, i);
        }
    }
}
