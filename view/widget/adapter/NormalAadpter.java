package com.movieboxpro.android.view.widget.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bumptech.glide.Glide;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.BaseAdapter;
import com.movieboxpro.android.base.BaseViewHolder;
import com.movieboxpro.android.base.OnItemClickListener;
import com.movieboxpro.android.model.movie.MovelList;
import java.util.List;
/* loaded from: classes3.dex */
public class NormalAadpter extends BaseAdapter<MovelList.MovieFrist> {
    public Activity activity;

    /* loaded from: classes3.dex */
    public interface OnClickListener extends OnItemClickListener {
        void onLongDeleteClick(int i);
    }

    /* loaded from: classes3.dex */
    public class Item1ViewHolder_ViewBinding implements Unbinder {
        private Item1ViewHolder target;

        public Item1ViewHolder_ViewBinding(Item1ViewHolder item1ViewHolder, View view) {
            this.target = item1ViewHolder;
            item1ViewHolder.mCover = (ImageView) Utils.findRequiredViewAsType(view, R.id.movie_item_poster, "field 'mCover'", ImageView.class);
        }

        @Override // butterknife.Unbinder
        public void unbind() {
            Item1ViewHolder item1ViewHolder = this.target;
            if (item1ViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            item1ViewHolder.mCover = null;
        }
    }

    public NormalAadpter(List<MovelList.MovieFrist> list) {
        super(list);
    }

    public NormalAadpter(List<MovelList.MovieFrist> list, Activity activity) {
        super(list);
        this.activity = activity;
    }

    @Override // com.movieboxpro.android.base.BaseAdapter
    public BaseViewHolder getHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i, OnItemClickListener onItemClickListener) {
        return new Item1ViewHolder(layoutInflater.inflate(R.layout.layout_movelist_item, viewGroup, false), onItemClickListener);
    }

    @Override // com.movieboxpro.android.base.BaseAdapter
    public void setView(BaseViewHolder baseViewHolder, int i) {
        if (i < 0 || i > getItemCount() - 1) {
            return;
        }
        MovelList.MovieFrist model = getModel(i);
        Item1ViewHolder item1ViewHolder = (Item1ViewHolder) baseViewHolder;
        Activity activity = this.activity;
        if (activity == null || activity.isFinishing()) {
            return;
        }
        Glide.with(this.activity).load(model.poster).into(item1ViewHolder.mCover);
    }

    @Override // com.movieboxpro.android.base.BaseAdapter
    public int getType(int i) {
        if (i < 0 || i > getItemCount() - 1) {
            return super.getType(i);
        }
        return getModel(i).box_type;
    }

    /* loaded from: classes3.dex */
    static class Item1ViewHolder extends BaseViewHolder {
        @BindView(R.id.movie_item_poster)
        ImageView mCover;

        Item1ViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view, onItemClickListener);
            this.mContainer.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.movieboxpro.android.view.widget.adapter.NormalAadpter.Item1ViewHolder.1
                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View view2) {
                    int adapterPosition;
                    if (!(Item1ViewHolder.this.listener instanceof OnClickListener) || (adapterPosition = Item1ViewHolder.this.getAdapterPosition()) == -1) {
                        return false;
                    }
                    ((OnClickListener) Item1ViewHolder.this.listener).onLongDeleteClick(adapterPosition);
                    return true;
                }
            });
        }
    }
}
