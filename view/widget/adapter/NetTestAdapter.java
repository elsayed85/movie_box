package com.movieboxpro.android.view.widget.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseAdapter;
import com.movieboxpro.android.base.BaseViewHolder;
import com.movieboxpro.android.base.OnItemClickListener;
import com.movieboxpro.android.model.common.NetTestModel;
import com.movieboxpro.android.utils.MLog;
import java.util.List;
/* loaded from: classes3.dex */
public class NetTestAdapter extends BaseAdapter<NetTestModel> {

    /* loaded from: classes3.dex */
    public interface OnNetClickListener extends OnItemClickListener {
        void getReload(int i, ImageView imageView);
    }

    /* loaded from: classes3.dex */
    public class ItemViewHolder_ViewBinding implements Unbinder {
        private ItemViewHolder target;

        public ItemViewHolder_ViewBinding(ItemViewHolder itemViewHolder, View view) {
            this.target = itemViewHolder;
            itemViewHolder.mName = (TextView) Utils.findRequiredViewAsType(view, R.id.nettest_name, "field 'mName'", TextView.class);
            itemViewHolder.mDescrib = (TextView) Utils.findRequiredViewAsType(view, R.id.nettest_describ, "field 'mDescrib'", TextView.class);
            itemViewHolder.mProgress = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.nettest_progress, "field 'mProgress'", ProgressBar.class);
            itemViewHolder.mReload = (ImageView) Utils.findRequiredViewAsType(view, R.id.nettest_reload, "field 'mReload'", ImageView.class);
            itemViewHolder.mSpeed = (TextView) Utils.findRequiredViewAsType(view, R.id.nettest_speed, "field 'mSpeed'", TextView.class);
            itemViewHolder.mBack = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.backround, "field 'mBack'", ConstraintLayout.class);
        }

        @Override // butterknife.Unbinder
        public void unbind() {
            ItemViewHolder itemViewHolder = this.target;
            if (itemViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            itemViewHolder.mName = null;
            itemViewHolder.mDescrib = null;
            itemViewHolder.mProgress = null;
            itemViewHolder.mReload = null;
            itemViewHolder.mSpeed = null;
            itemViewHolder.mBack = null;
        }
    }

    public NetTestAdapter(List<NetTestModel> list) {
        super(list);
    }

    @Override // com.movieboxpro.android.base.BaseAdapter
    public BaseViewHolder getHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i, OnItemClickListener onItemClickListener) {
        return new ItemViewHolder(layoutInflater.inflate(R.layout.layout_nettest_item, viewGroup, false), onItemClickListener);
    }

    @Override // com.movieboxpro.android.base.BaseAdapter
    public void setView(BaseViewHolder baseViewHolder, int i) {
        NetTestModel model = getModel(i);
        ItemViewHolder itemViewHolder = (ItemViewHolder) baseViewHolder;
        itemViewHolder.mName.setText(model.country);
        itemViewHolder.mDescrib.setText(model.description);
        if (model.state != null && !model.state.equals(TtmlNode.START)) {
            itemViewHolder.mSpeed.setText(model.state);
            if (model.state.equals("Failed")) {
                itemViewHolder.mSpeed.setTextColor(App.getContext().getResources().getColor(R.color.red));
            } else {
                itemViewHolder.mSpeed.setTextColor(App.getContext().getResources().getColor(R.color.green));
            }
        }
        MLog.d("yyy", "ssdawda : " + model.startTime);
        if (!model.startTime.equals(-1L)) {
            itemViewHolder.mProgress.setVisibility(0);
            itemViewHolder.mReload.setVisibility(8);
            itemViewHolder.mSpeed.setVisibility(8);
        }
        if (model.state != null) {
            itemViewHolder.mProgress.setVisibility(8);
            itemViewHolder.mReload.setVisibility(0);
            itemViewHolder.mSpeed.setVisibility(0);
        }
        if (model.state != null && model.state.equals(TtmlNode.START)) {
            itemViewHolder.mProgress.setVisibility(8);
            itemViewHolder.mReload.setVisibility(8);
            itemViewHolder.mSpeed.setVisibility(8);
        }
        if (model.isSelect) {
            itemViewHolder.mBack.setSelected(true);
        } else {
            itemViewHolder.mBack.setSelected(false);
        }
    }

    /* loaded from: classes3.dex */
    static class ItemViewHolder extends BaseViewHolder {
        @BindView(R.id.backround)
        ConstraintLayout mBack;
        @BindView(R.id.nettest_describ)
        TextView mDescrib;
        @BindView(R.id.nettest_name)
        TextView mName;
        @BindView(R.id.nettest_progress)
        ProgressBar mProgress;
        @BindView(R.id.nettest_reload)
        ImageView mReload;
        @BindView(R.id.nettest_speed)
        TextView mSpeed;

        ItemViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view, onItemClickListener);
            this.mReload.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.widget.adapter.NetTestAdapter.ItemViewHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    int adapterPosition;
                    if (!(ItemViewHolder.this.listener instanceof OnNetClickListener) || (adapterPosition = ItemViewHolder.this.getAdapterPosition()) == -1) {
                        return;
                    }
                    ((OnNetClickListener) ItemViewHolder.this.listener).getReload(adapterPosition, ItemViewHolder.this.mReload);
                }
            });
        }
    }
}
