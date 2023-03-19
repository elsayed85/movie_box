package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import butterknife.BindView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bumptech.glide.Glide;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.BaseAdapter;
import com.movieboxpro.android.base.BaseViewHolder;
import com.movieboxpro.android.base.OnItemClickListener;
import com.movieboxpro.android.model.movie.MovelList;
import com.movieboxpro.android.view.widget.MyGridLayoutManager;
import com.movieboxpro.android.view.widget.MyRecyclerView;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes3.dex */
public class DownloadDialog extends Dialog {
    private Context context;
    private Dialog dialog;
    private MyGridLayoutManager layoutManager;
    public List<MovelList.MovieDownload> list;
    private CollectAdapter mAdapter;
    private TextView mEmptyText;
    private MyRecyclerView mRecycler;
    private MovelList.MovieDownload movieDownload;

    /* loaded from: classes3.dex */
    interface OnClickListener extends OnItemClickListener {
        void onContainerClick(int i, ImageView imageView);
    }

    /* loaded from: classes3.dex */
    public class Item1ViewHolder_ViewBinding implements Unbinder {
        private Item1ViewHolder target;

        public Item1ViewHolder_ViewBinding(Item1ViewHolder item1ViewHolder, View view) {
            this.target = item1ViewHolder;
            item1ViewHolder.mCover = (ImageView) Utils.findRequiredViewAsType(view, R.id.favorite_poster, "field 'mCover'", ImageView.class);
            item1ViewHolder.mCheck = (ImageView) Utils.findRequiredViewAsType(view, R.id.favorite_poster_delete, "field 'mCheck'", ImageView.class);
        }

        @Override // butterknife.Unbinder
        public void unbind() {
            Item1ViewHolder item1ViewHolder = this.target;
            if (item1ViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            item1ViewHolder.mCover = null;
            item1ViewHolder.mCheck = null;
        }
    }

    public DownloadDialog(Context context) {
        super(context);
        this.list = new ArrayList();
        this.context = context;
    }

    public DownloadDialog(Context context, MovelList.MovieDownload movieDownload) {
        super(context);
        ArrayList arrayList = new ArrayList();
        this.list = arrayList;
        this.context = context;
        this.movieDownload = movieDownload;
        arrayList.add(movieDownload);
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_recycler_normal);
        this.mRecycler = (MyRecyclerView) findViewById(R.id.fragment_normal_recycler);
        this.mEmptyText = (TextView) findViewById(R.id.text_empty);
        this.list.add(this.movieDownload);
        if (this.mAdapter == null) {
            this.mAdapter = new CollectAdapter(this.list);
            MyGridLayoutManager myGridLayoutManager = new MyGridLayoutManager(this.context, 3);
            this.layoutManager = myGridLayoutManager;
            this.mRecycler.setLayoutManager(myGridLayoutManager);
            this.mAdapter.setShowFooter(true);
            this.layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.movieboxpro.android.view.dialog.DownloadDialog.1
                @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
                public int getSpanSize(int i) {
                    return 1;
                }
            });
            this.mRecycler.setEmptyView(this.mEmptyText);
            this.mRecycler.setLayoutManager(this.layoutManager);
            this.mRecycler.setAdapter(this.mAdapter);
        }
    }

    /* loaded from: classes3.dex */
    public class CollectAdapter extends BaseAdapter<MovelList.MovieDownload> {
        public CollectAdapter(List<MovelList.MovieDownload> list) {
            super(list);
        }

        @Override // com.movieboxpro.android.base.BaseAdapter
        public BaseViewHolder getHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i, OnItemClickListener onItemClickListener) {
            return new Item1ViewHolder(layoutInflater.inflate(R.layout.layout_favoritemovie_item, viewGroup, false), onItemClickListener);
        }

        @Override // com.movieboxpro.android.base.BaseAdapter
        public void setView(BaseViewHolder baseViewHolder, int i) {
            if (i < 0 || i > getItemCount() - 1) {
                return;
            }
            MovelList.MovieDownload model = getModel(i);
            Item1ViewHolder item1ViewHolder = (Item1ViewHolder) baseViewHolder;
            if (DownloadDialog.this.context != null) {
                Glide.with(DownloadDialog.this.context).load(model.poster).into(item1ViewHolder.mCover);
            }
            item1ViewHolder.mCheck.setVisibility(4);
        }
    }

    /* loaded from: classes3.dex */
    static class Item1ViewHolder extends BaseViewHolder {
        @BindView(R.id.favorite_poster_delete)
        ImageView mCheck;
        @BindView(R.id.favorite_poster)
        ImageView mCover;

        Item1ViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view, onItemClickListener);
            this.mContainer.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.DownloadDialog.Item1ViewHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    int adapterPosition;
                    if (!(Item1ViewHolder.this.listener instanceof OnClickListener) || (adapterPosition = Item1ViewHolder.this.getAdapterPosition()) == -1) {
                        return;
                    }
                    ((OnClickListener) Item1ViewHolder.this.listener).onContainerClick(adapterPosition, Item1ViewHolder.this.mCheck);
                }
            });
        }
    }
}
