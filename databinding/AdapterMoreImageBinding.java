package com.movieboxpro.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.android.R;
import com.movieboxpro.android.view.widget.LoadingStatusView;
/* loaded from: classes3.dex */
public abstract class AdapterMoreImageBinding extends ViewDataBinding {
    public final FrameLayout frameLayout;
    public final LoadingStatusView loadingView;
    public final TextView tvMore;

    /* JADX INFO: Access modifiers changed from: protected */
    public AdapterMoreImageBinding(Object obj, View view, int i, FrameLayout frameLayout, LoadingStatusView loadingStatusView, TextView textView) {
        super(obj, view, i);
        this.frameLayout = frameLayout;
        this.loadingView = loadingStatusView;
        this.tvMore = textView;
    }

    public static AdapterMoreImageBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AdapterMoreImageBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (AdapterMoreImageBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.adapter_more_image, viewGroup, z, obj);
    }

    public static AdapterMoreImageBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AdapterMoreImageBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (AdapterMoreImageBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.adapter_more_image, null, false, obj);
    }

    public static AdapterMoreImageBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AdapterMoreImageBinding bind(View view, Object obj) {
        return (AdapterMoreImageBinding) bind(obj, view, R.layout.adapter_more_image);
    }
}
