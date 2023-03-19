package com.movieboxpro.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public abstract class FragmentRecommendBinding extends ViewDataBinding {
    public final FrameLayout flContainer;
    public final AppCompatImageView ivManager;
    public final AppCompatImageView ivRefresh;

    /* JADX INFO: Access modifiers changed from: protected */
    public FragmentRecommendBinding(Object obj, View view, int i, FrameLayout frameLayout, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2) {
        super(obj, view, i);
        this.flContainer = frameLayout;
        this.ivManager = appCompatImageView;
        this.ivRefresh = appCompatImageView2;
    }

    public static FragmentRecommendBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentRecommendBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentRecommendBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_recommend, viewGroup, z, obj);
    }

    public static FragmentRecommendBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentRecommendBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentRecommendBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_recommend, null, false, obj);
    }

    public static FragmentRecommendBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentRecommendBinding bind(View view, Object obj) {
        return (FragmentRecommendBinding) bind(obj, view, R.layout.fragment_recommend);
    }
}
