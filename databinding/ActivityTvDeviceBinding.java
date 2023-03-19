package com.movieboxpro.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.movieboxpro.android.R;
import com.movieboxpro.android.view.widget.LoadingStatusView;
/* loaded from: classes3.dex */
public abstract class ActivityTvDeviceBinding extends ViewDataBinding {
    public final LoadingStatusView loadingView;
    public final RecyclerView recyclerView;
    public final CommonTitleBarLayoutBinding toolBar;
    public final TextView tvManager;

    /* JADX INFO: Access modifiers changed from: protected */
    public ActivityTvDeviceBinding(Object obj, View view, int i, LoadingStatusView loadingStatusView, RecyclerView recyclerView, CommonTitleBarLayoutBinding commonTitleBarLayoutBinding, TextView textView) {
        super(obj, view, i);
        this.loadingView = loadingStatusView;
        this.recyclerView = recyclerView;
        this.toolBar = commonTitleBarLayoutBinding;
        setContainedBinding(commonTitleBarLayoutBinding);
        this.tvManager = textView;
    }

    public static ActivityTvDeviceBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityTvDeviceBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityTvDeviceBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_tv_device, viewGroup, z, obj);
    }

    public static ActivityTvDeviceBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityTvDeviceBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityTvDeviceBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_tv_device, null, false, obj);
    }

    public static ActivityTvDeviceBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityTvDeviceBinding bind(View view, Object obj) {
        return (ActivityTvDeviceBinding) bind(obj, view, R.layout.activity_tv_device);
    }
}
