package com.movieboxpro.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.movieboxpro.android.R;
import com.movieboxpro.android.view.widget.LoadingStatusView;
/* loaded from: classes3.dex */
public abstract class AddTvWatchPlanPopLayoutBinding extends ViewDataBinding {
    public final LinearLayout llWaiting;
    public final LinearLayout llWatched;
    public final LoadingStatusView loadingView;
    public final RecyclerView recyclerView;
    public final TextView tvWaitingAdded;
    public final TextView tvWaitingTime;
    public final TextView tvWatchedAdded;
    public final TextView tvWatchedTime;

    /* JADX INFO: Access modifiers changed from: protected */
    public AddTvWatchPlanPopLayoutBinding(Object obj, View view, int i, LinearLayout linearLayout, LinearLayout linearLayout2, LoadingStatusView loadingStatusView, RecyclerView recyclerView, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        super(obj, view, i);
        this.llWaiting = linearLayout;
        this.llWatched = linearLayout2;
        this.loadingView = loadingStatusView;
        this.recyclerView = recyclerView;
        this.tvWaitingAdded = textView;
        this.tvWaitingTime = textView2;
        this.tvWatchedAdded = textView3;
        this.tvWatchedTime = textView4;
    }

    public static AddTvWatchPlanPopLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AddTvWatchPlanPopLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (AddTvWatchPlanPopLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.add_tv_watch_plan_pop_layout, viewGroup, z, obj);
    }

    public static AddTvWatchPlanPopLayoutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AddTvWatchPlanPopLayoutBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (AddTvWatchPlanPopLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.add_tv_watch_plan_pop_layout, null, false, obj);
    }

    public static AddTvWatchPlanPopLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AddTvWatchPlanPopLayoutBinding bind(View view, Object obj) {
        return (AddTvWatchPlanPopLayoutBinding) bind(obj, view, R.layout.add_tv_watch_plan_pop_layout);
    }
}
