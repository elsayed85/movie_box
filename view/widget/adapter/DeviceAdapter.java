package com.movieboxpro.android.view.widget.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.BaseAdapter;
import com.movieboxpro.android.base.BaseViewHolder;
import com.movieboxpro.android.base.OnItemClickListener;
import com.movieboxpro.android.model.common.Device;
import com.movieboxpro.android.utils.TimeUtils;
import java.util.List;
/* loaded from: classes3.dex */
public class DeviceAdapter extends BaseAdapter<Device> {
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
            item1ViewHolder.mName = (TextView) Utils.findRequiredViewAsType(view, R.id.ic_device_name, "field 'mName'", TextView.class);
            item1ViewHolder.mModel = (TextView) Utils.findRequiredViewAsType(view, R.id.ic_device_Model, "field 'mModel'", TextView.class);
            item1ViewHolder.mStart = (TextView) Utils.findRequiredViewAsType(view, R.id.ic_device_start_time, "field 'mStart'", TextView.class);
            item1ViewHolder.mLast = (TextView) Utils.findRequiredViewAsType(view, R.id.ic_device_last_time, "field 'mLast'", TextView.class);
            item1ViewHolder.tvDetele = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_detele, "field 'tvDetele'", TextView.class);
        }

        @Override // butterknife.Unbinder
        public void unbind() {
            Item1ViewHolder item1ViewHolder = this.target;
            if (item1ViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            item1ViewHolder.mName = null;
            item1ViewHolder.mModel = null;
            item1ViewHolder.mStart = null;
            item1ViewHolder.mLast = null;
            item1ViewHolder.tvDetele = null;
        }
    }

    public DeviceAdapter(List<Device> list) {
        super(list);
    }

    public DeviceAdapter(List<Device> list, Activity activity) {
        super(list);
        this.activity = activity;
    }

    @Override // com.movieboxpro.android.base.BaseAdapter
    public BaseViewHolder getHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i, OnItemClickListener onItemClickListener) {
        return new Item1ViewHolder(layoutInflater.inflate(R.layout.layout_device_list, viewGroup, false), onItemClickListener);
    }

    @Override // com.movieboxpro.android.base.BaseAdapter
    public void setView(BaseViewHolder baseViewHolder, int i) {
        if (i < 0 || i > getItemCount() - 1) {
            return;
        }
        Device model = getModel(i);
        Item1ViewHolder item1ViewHolder = (Item1ViewHolder) baseViewHolder;
        item1ViewHolder.mName.setText(model.name);
        item1ViewHolder.mModel.setText(model.model);
        if (model.start_time.longValue() != 0) {
            item1ViewHolder.mStart.setText(TimeUtils.formatTime3(model.start_time.longValue() * 1000));
        }
        if (model.end_time.longValue() != 0) {
            item1ViewHolder.mLast.setText(TimeUtils.formatTime3(model.end_time.longValue() * 1000));
        }
    }

    /* loaded from: classes3.dex */
    public static class Item1ViewHolder extends BaseViewHolder {
        @BindView(R.id.ic_device_last_time)
        TextView mLast;
        @BindView(R.id.ic_device_Model)
        TextView mModel;
        @BindView(R.id.ic_device_name)
        TextView mName;
        @BindView(R.id.ic_device_start_time)
        TextView mStart;
        @BindView(R.id.tv_detele)
        public TextView tvDetele;

        Item1ViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view, onItemClickListener);
        }
    }
}
