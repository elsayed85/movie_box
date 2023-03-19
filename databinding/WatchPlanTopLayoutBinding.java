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
/* loaded from: classes3.dex */
public abstract class WatchPlanTopLayoutBinding extends ViewDataBinding {
    public final LinearLayout llWaiting;
    public final LinearLayout llWatching;
    public final RecyclerView recyclerView;
    public final TextView tvName;
    public final TextView tvWaitingCount;
    public final TextView tvWatchingCount;
    public final View view;

    /* JADX INFO: Access modifiers changed from: protected */
    public WatchPlanTopLayoutBinding(Object obj, View view, int i, LinearLayout linearLayout, LinearLayout linearLayout2, RecyclerView recyclerView, TextView textView, TextView textView2, TextView textView3, View view2) {
        super(obj, view, i);
        this.llWaiting = linearLayout;
        this.llWatching = linearLayout2;
        this.recyclerView = recyclerView;
        this.tvName = textView;
        this.tvWaitingCount = textView2;
        this.tvWatchingCount = textView3;
        this.view = view2;
    }

    public static WatchPlanTopLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WatchPlanTopLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (WatchPlanTopLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.watch_plan_top_layout, viewGroup, z, obj);
    }

    public static WatchPlanTopLayoutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WatchPlanTopLayoutBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (WatchPlanTopLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.watch_plan_top_layout, null, false, obj);
    }

    public static WatchPlanTopLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WatchPlanTopLayoutBinding bind(View view, Object obj) {
        return (WatchPlanTopLayoutBinding) bind(obj, view, R.layout.watch_plan_top_layout);
    }
}
