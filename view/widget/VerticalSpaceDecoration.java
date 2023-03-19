package com.movieboxpro.android.view.widget;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes3.dex */
public class VerticalSpaceDecoration extends RecyclerView.ItemDecoration {
    private boolean lastHasSpace;
    private final int mVerticalSpaceHeight;

    public VerticalSpaceDecoration(int i) {
        this(i, false);
    }

    public VerticalSpaceDecoration(int i, boolean z) {
        this.lastHasSpace = false;
        this.mVerticalSpaceHeight = i;
        this.lastHasSpace = z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        if (recyclerView.getChildAdapterPosition(view) != recyclerView.getAdapter().getItemCount() - 1) {
            rect.bottom = this.mVerticalSpaceHeight;
        } else if (this.lastHasSpace) {
            rect.bottom = this.mVerticalSpaceHeight;
        }
    }
}
