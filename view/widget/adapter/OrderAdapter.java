package com.movieboxpro.android.view.widget.adapter;

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
import com.movieboxpro.android.model.common.Order;
import com.movieboxpro.android.utils.TimeUtils;
import java.util.List;
/* loaded from: classes3.dex */
public class OrderAdapter extends BaseAdapter<Order> {

    /* loaded from: classes3.dex */
    public class ItemViewHolder_ViewBinding implements Unbinder {
        private ItemViewHolder target;

        public ItemViewHolder_ViewBinding(ItemViewHolder itemViewHolder, View view) {
            this.target = itemViewHolder;
            itemViewHolder.mType = (TextView) Utils.findRequiredViewAsType(view, R.id.order_pay_type, "field 'mType'", TextView.class);
            itemViewHolder.mTime = (TextView) Utils.findRequiredViewAsType(view, R.id.order_pay_time, "field 'mTime'", TextView.class);
            itemViewHolder.mMoney = (TextView) Utils.findRequiredViewAsType(view, R.id.order_pay_money, "field 'mMoney'", TextView.class);
            itemViewHolder.mPay = (TextView) Utils.findRequiredViewAsType(view, R.id.order_pay_pay, "field 'mPay'", TextView.class);
            itemViewHolder.mId = (TextView) Utils.findRequiredViewAsType(view, R.id.order_pay_id, "field 'mId'", TextView.class);
        }

        @Override // butterknife.Unbinder
        public void unbind() {
            ItemViewHolder itemViewHolder = this.target;
            if (itemViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            itemViewHolder.mType = null;
            itemViewHolder.mTime = null;
            itemViewHolder.mMoney = null;
            itemViewHolder.mPay = null;
            itemViewHolder.mId = null;
        }
    }

    public OrderAdapter(List<Order> list) {
        super(list);
    }

    @Override // com.movieboxpro.android.base.BaseAdapter
    public BaseViewHolder getHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i, OnItemClickListener onItemClickListener) {
        return new ItemViewHolder(layoutInflater.inflate(R.layout.layout_order_item, viewGroup, false), onItemClickListener);
    }

    @Override // com.movieboxpro.android.base.BaseAdapter
    public void setView(BaseViewHolder baseViewHolder, int i) {
        Order model = getModel(i);
        ItemViewHolder itemViewHolder = (ItemViewHolder) baseViewHolder;
        TextView textView = itemViewHolder.mType;
        textView.setText(model.num + " " + model.unit);
        itemViewHolder.mTime.setText(TimeUtils.formatTime3(model.dateline * 1000));
        TextView textView2 = itemViewHolder.mMoney;
        textView2.setText(model.total + " " + model.money);
        itemViewHolder.mPay.setText(model.state == 0 ? "Unpaid" : "Paid");
        TextView textView3 = itemViewHolder.mId;
        textView3.setText("Order ID: " + model.orderid);
    }

    /* loaded from: classes3.dex */
    static class ItemViewHolder extends BaseViewHolder {
        @BindView(R.id.order_pay_id)
        TextView mId;
        @BindView(R.id.order_pay_money)
        TextView mMoney;
        @BindView(R.id.order_pay_pay)
        TextView mPay;
        @BindView(R.id.order_pay_time)
        TextView mTime;
        @BindView(R.id.order_pay_type)
        TextView mType;

        ItemViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view, onItemClickListener);
        }
    }
}
