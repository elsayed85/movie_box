package com.movieboxpro.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.tabs.TabLayout;
import com.movieboxpro.android.R;
import com.movieboxpro.android.view.widget.NoScrollViewPager;
/* loaded from: classes3.dex */
public abstract class FragmentSkipTimeBinding extends ViewDataBinding {
    public final AppCompatImageView ivCheck;
    public final AppCompatImageView ivClose;
    public final LinearLayout llEnableSkip;
    public final TabLayout tabLayout;
    public final TextView tvDisableHint;
    public final TextView tvOk;
    public final NoScrollViewPager viewPager;

    /* JADX INFO: Access modifiers changed from: protected */
    public FragmentSkipTimeBinding(Object obj, View view, int i, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, LinearLayout linearLayout, TabLayout tabLayout, TextView textView, TextView textView2, NoScrollViewPager noScrollViewPager) {
        super(obj, view, i);
        this.ivCheck = appCompatImageView;
        this.ivClose = appCompatImageView2;
        this.llEnableSkip = linearLayout;
        this.tabLayout = tabLayout;
        this.tvDisableHint = textView;
        this.tvOk = textView2;
        this.viewPager = noScrollViewPager;
    }

    public static FragmentSkipTimeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSkipTimeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentSkipTimeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_skip_time, viewGroup, z, obj);
    }

    public static FragmentSkipTimeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSkipTimeBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentSkipTimeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_skip_time, null, false, obj);
    }

    public static FragmentSkipTimeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSkipTimeBinding bind(View view, Object obj) {
        return (FragmentSkipTimeBinding) bind(obj, view, R.layout.fragment_skip_time);
    }
}
