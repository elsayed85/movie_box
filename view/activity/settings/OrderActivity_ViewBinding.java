package com.movieboxpro.android.view.activity.settings;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.movieboxpro.android.R;
import com.movieboxpro.android.view.widget.MyRecyclerView;
/* loaded from: classes3.dex */
public class OrderActivity_ViewBinding implements Unbinder {
    private OrderActivity target;

    public OrderActivity_ViewBinding(OrderActivity orderActivity) {
        this(orderActivity, orderActivity.getWindow().getDecorView());
    }

    public OrderActivity_ViewBinding(OrderActivity orderActivity, View view) {
        this.target = orderActivity;
        orderActivity.mRecycler = (MyRecyclerView) Utils.findRequiredViewAsType(view, R.id.fragment_normal_recycler, "field 'mRecycler'", MyRecyclerView.class);
        orderActivity.mEmptyText = (TextView) Utils.findRequiredViewAsType(view, R.id.text_empty, "field 'mEmptyText'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        OrderActivity orderActivity = this.target;
        if (orderActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        orderActivity.mRecycler = null;
        orderActivity.mEmptyText = null;
    }
}
