package com.movieboxpro.android.base;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.movieboxpro.android.R;
import java.util.List;
/* loaded from: classes3.dex */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    private static final int ITEM_LOADING_FOOTER = 1001;
    private static final int ITEM_LOADING_HEADER = 1000;
    protected final List<T> list;
    protected OnItemClickListener listener;
    private boolean isShowFooter = false;
    private boolean noMoreData = false;

    public abstract BaseViewHolder getHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i, OnItemClickListener onItemClickListener);

    public int getType(int i) {
        return 0;
    }

    public abstract void setView(BaseViewHolder baseViewHolder, int i);

    public BaseAdapter(List<T> list) {
        setShowFooter(false);
        this.list = list;
    }

    public void setNoMoreData(boolean z) {
        this.noMoreData = z;
    }

    public boolean isNoMoreData() {
        return this.noMoreData;
    }

    public void setListener(OnItemClickListener onItemClickListener) {
        this.listener = onItemClickListener;
    }

    protected OnItemClickListener getListener() {
        return this.listener;
    }

    public boolean isShowFooter() {
        return this.isShowFooter;
    }

    public void setShowFooter(boolean z) {
        this.isShowFooter = z;
    }

    public void setData(List<T> list) {
        List<T> list2;
        if (list == null || (list2 = this.list) == null) {
            return;
        }
        list2.clear();
        this.list.addAll(list);
        notifyItemRangeChanged(0, this.list.size());
    }

    public T getModel(int i) {
        return this.list.get(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public BaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (this.isShowFooter) {
            if (i == 1001) {
                return new FooterViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_loading_footer, viewGroup, false), null);
            }
            return getHolder(LayoutInflater.from(viewGroup.getContext()), viewGroup, i, this.listener);
        }
        return getHolder(LayoutInflater.from(viewGroup.getContext()), viewGroup, i, this.listener);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(BaseViewHolder baseViewHolder, int i) {
        if (this.isShowFooter) {
            if (i == getItemCount() - 1) {
                FooterViewHolder footerViewHolder = (FooterViewHolder) baseViewHolder;
                if (i != 0) {
                    footerViewHolder.mLoadingText.setVisibility(0);
                    if (this.noMoreData) {
                        footerViewHolder.mLoadingText.setText("No More Data");
                        footerViewHolder.mLoading.setVisibility(8);
                        return;
                    }
                    footerViewHolder.mLoadingText.setText("Loading...");
                    footerViewHolder.mLoading.setVisibility(0);
                    return;
                }
                footerViewHolder.mLoading.setVisibility(8);
                footerViewHolder.mLoadingText.setVisibility(8);
                return;
            }
            setView(baseViewHolder, i);
            return;
        }
        setView(baseViewHolder, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.isShowFooter) {
            return this.list.size() + 1;
        }
        return this.list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (this.isShowFooter) {
            if (i == getItemCount() - 1) {
                return 1001;
            }
            return getType(i);
        }
        return getType(i);
    }
}
