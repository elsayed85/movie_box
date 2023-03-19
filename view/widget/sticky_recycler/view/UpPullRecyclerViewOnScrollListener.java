package com.movieboxpro.android.view.widget.sticky_recycler.view;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes3.dex */
public abstract class UpPullRecyclerViewOnScrollListener extends RecyclerView.OnScrollListener {
    boolean isUpPull = false;

    public abstract void onLoadMoreData();

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
        super.onScrollStateChanged(recyclerView, i);
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        if (i == 0) {
            if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == linearLayoutManager.getItemCount() - 1 && this.isUpPull) {
                onLoadMoreData();
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        super.onScrolled(recyclerView, i, i2);
        this.isUpPull = i2 > 0;
    }
}
