package com.movieboxpro.android.base;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.ButterKnife;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder {
    protected OnItemClickListener listener;
    protected View mContainer;

    public BaseViewHolder(View view, OnItemClickListener onItemClickListener) {
        super(view);
        this.listener = onItemClickListener;
        ButterKnife.bind(this, view);
        View findViewById = view.findViewById(R.id.item_container);
        this.mContainer = findViewById;
        if (findViewById != null) {
            findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.base.BaseViewHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    if (BaseViewHolder.this.listener == null || BaseViewHolder.this.getAdapterPosition() == -1) {
                        return;
                    }
                    BaseViewHolder.this.listener.onItemClick(BaseViewHolder.this.getAdapterPosition());
                }
            });
        }
    }
}
