package com.movieboxpro.android.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes3.dex */
public class MyRecyclerView extends RecyclerView {
    private View mEmptyView;
    private RecyclerView.AdapterDataObserver mObserver;

    public MyRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mObserver = new RecyclerView.AdapterDataObserver() { // from class: com.movieboxpro.android.view.widget.MyRecyclerView.1
            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onChanged() {
                if (MyRecyclerView.this.getAdapter().getItemCount() == 0) {
                    MyRecyclerView.this.mEmptyView.setVisibility(0);
                    MyRecyclerView.this.setVisibility(8);
                    return;
                }
                MyRecyclerView.this.mEmptyView.setVisibility(8);
                MyRecyclerView.this.setVisibility(0);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeChanged(int i, int i2) {
                onChanged();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeMoved(int i, int i2, int i3) {
                onChanged();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeRemoved(int i, int i2) {
                onChanged();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeInserted(int i, int i2) {
                onChanged();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeChanged(int i, int i2, Object obj) {
                onChanged();
            }
        };
    }

    public void setEmptyView(View view) {
        this.mEmptyView = view;
    }

    public View getEmptyView() {
        return this.mEmptyView;
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setAdapter(RecyclerView.Adapter adapter) {
        super.setAdapter(adapter);
        adapter.registerAdapterDataObserver(this.mObserver);
    }
}
