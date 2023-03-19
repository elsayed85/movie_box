package com.movieboxpro.android.view.activity.settings;

import android.view.View;
import android.widget.TextView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.movieboxpro.android.R;
import com.movieboxpro.android.view.widget.MyRecyclerView;
/* loaded from: classes3.dex */
public class DeviceActivity_ViewBinding implements Unbinder {
    private DeviceActivity target;

    public DeviceActivity_ViewBinding(DeviceActivity deviceActivity) {
        this(deviceActivity, deviceActivity.getWindow().getDecorView());
    }

    public DeviceActivity_ViewBinding(DeviceActivity deviceActivity, View view) {
        this.target = deviceActivity;
        deviceActivity.mRecycler = (MyRecyclerView) Utils.findRequiredViewAsType(view, R.id.fragment_normal_recycler, "field 'mRecycler'", MyRecyclerView.class);
        deviceActivity.swipeRefreshLayout = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.swipeRefreshLayout, "field 'swipeRefreshLayout'", SwipeRefreshLayout.class);
        deviceActivity.mEmptyText = (TextView) Utils.findRequiredViewAsType(view, R.id.text_empty, "field 'mEmptyText'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        DeviceActivity deviceActivity = this.target;
        if (deviceActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        deviceActivity.mRecycler = null;
        deviceActivity.swipeRefreshLayout = null;
        deviceActivity.mEmptyText = null;
    }
}
