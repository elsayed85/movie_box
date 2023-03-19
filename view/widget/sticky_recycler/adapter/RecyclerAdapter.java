package com.movieboxpro.android.view.widget.sticky_recycler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.movieboxpro.android.R;
import com.movieboxpro.android.view.widget.sticky_recycler.bean.Basedata;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes3.dex */
public class RecyclerAdapter<T extends Basedata> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public Context mContext;
    public T mData;
    public List<T> mDatas;
    private int loadState = 2;
    public final int LOADING = 1;
    public final int LOADING_COMPLETE = 2;
    public final int LOADING_END = 3;

    public RecyclerAdapter(Context context, List<T> list) {
        this.mDatas = new ArrayList();
        this.mContext = context;
        this.mDatas = list;
    }

    /* loaded from: classes3.dex */
    static class ViewHolde extends RecyclerView.ViewHolder {
        TextView txt;

        public ViewHolde(View view) {
            super(view);
            this.txt = (TextView) view.findViewById(R.id.txt);
        }
    }

    /* loaded from: classes3.dex */
    static class ViewHolderFoot extends RecyclerView.ViewHolder {
        TextView textfoot;

        public ViewHolderFoot(View view) {
            super(view);
            this.textfoot = (TextView) view.findViewById(R.id.list_item_foot);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1) {
            View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.layout_sticky_item, viewGroup, false);
            inflate.setTag(true);
            return new ViewHolde(inflate);
        } else if (i == 0) {
            View inflate2 = LayoutInflater.from(this.mContext).inflate(R.layout.layout_sticky_foot, viewGroup, false);
            inflate2.setTag(false);
            return new ViewHolderFoot(inflate2);
        } else {
            View inflate3 = LayoutInflater.from(this.mContext).inflate(R.layout.layout_sticky_item, viewGroup, false);
            inflate3.setTag(false);
            return new ViewHolde(inflate3);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ViewHolde) {
            this.mData = this.mDatas.get(i);
            ViewHolde viewHolde = (ViewHolde) viewHolder;
            viewHolde.txt.setText(this.mData.getName());
            if (this.mData.getType() == 1) {
                viewHolde.txt.setTextColor(this.mContext.getResources().getColor(R.color.colorAccent));
                viewHolde.txt.setBackgroundColor(this.mContext.getResources().getColor(R.color.red));
                viewHolde.txt.setTextSize(20.0f);
                return;
            }
            return;
        }
        int i2 = this.loadState;
        if (i2 == 1) {
            ViewHolderFoot viewHolderFoot = (ViewHolderFoot) viewHolder;
            viewHolderFoot.textfoot.setText("正在加载。。");
            viewHolderFoot.textfoot.setVisibility(0);
        } else if (i2 == 2) {
            ((ViewHolderFoot) viewHolder).textfoot.setVisibility(8);
        } else if (i2 != 3) {
        } else {
            ViewHolderFoot viewHolderFoot2 = (ViewHolderFoot) viewHolder;
            viewHolderFoot2.textfoot.setText("没有更多数据");
            viewHolderFoot2.textfoot.setVisibility(0);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (i == getItemCount() - 1) {
            return 0;
        }
        return this.mDatas.get(i).getType();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mDatas.size() + 1;
    }

    public void setLoadState(int i) {
        this.loadState = i;
        notifyDataSetChanged();
    }

    public boolean isLoadState() {
        return this.loadState == 2;
    }
}
