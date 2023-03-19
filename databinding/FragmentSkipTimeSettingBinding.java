package com.movieboxpro.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.movieboxpro.android.R;
import com.movieboxpro.android.view.widget.LoadingStatusView;
/* loaded from: classes3.dex */
public abstract class FragmentSkipTimeSettingBinding extends ViewDataBinding {
    public final AppCompatImageView ivCheck;
    public final AppCompatImageView ivOpen;
    public final LinearLayout llAll;
    public final LoadingStatusView loadingView;
    public final RecyclerView rvImage;
    public final RecyclerView rvOpening;
    public final TextView tvSeasonInfo;

    /* JADX INFO: Access modifiers changed from: protected */
    public FragmentSkipTimeSettingBinding(Object obj, View view, int i, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, LinearLayout linearLayout, LoadingStatusView loadingStatusView, RecyclerView recyclerView, RecyclerView recyclerView2, TextView textView) {
        super(obj, view, i);
        this.ivCheck = appCompatImageView;
        this.ivOpen = appCompatImageView2;
        this.llAll = linearLayout;
        this.loadingView = loadingStatusView;
        this.rvImage = recyclerView;
        this.rvOpening = recyclerView2;
        this.tvSeasonInfo = textView;
    }

    public static FragmentSkipTimeSettingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSkipTimeSettingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentSkipTimeSettingBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_skip_time_setting, viewGroup, z, obj);
    }

    public static FragmentSkipTimeSettingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSkipTimeSettingBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentSkipTimeSettingBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_skip_time_setting, null, false, obj);
    }

    public static FragmentSkipTimeSettingBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSkipTimeSettingBinding bind(View view, Object obj) {
        return (FragmentSkipTimeSettingBinding) bind(obj, view, R.layout.fragment_skip_time_setting);
    }
}
