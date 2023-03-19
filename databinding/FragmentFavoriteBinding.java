package com.movieboxpro.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public abstract class FragmentFavoriteBinding extends ViewDataBinding {
    public final AppCompatImageView ivAdd;
    public final AppCompatImageView ivFilter;
    public final RelativeLayout rlTitleBar;
    public final TabLayout tabLayout;
    public final TextView tvTitle;
    public final ViewPager viewPager;

    /* JADX INFO: Access modifiers changed from: protected */
    public FragmentFavoriteBinding(Object obj, View view, int i, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, RelativeLayout relativeLayout, TabLayout tabLayout, TextView textView, ViewPager viewPager) {
        super(obj, view, i);
        this.ivAdd = appCompatImageView;
        this.ivFilter = appCompatImageView2;
        this.rlTitleBar = relativeLayout;
        this.tabLayout = tabLayout;
        this.tvTitle = textView;
        this.viewPager = viewPager;
    }

    public static FragmentFavoriteBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentFavoriteBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentFavoriteBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_favorite, viewGroup, z, obj);
    }

    public static FragmentFavoriteBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentFavoriteBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentFavoriteBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_favorite, null, false, obj);
    }

    public static FragmentFavoriteBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentFavoriteBinding bind(View view, Object obj) {
        return (FragmentFavoriteBinding) bind(obj, view, R.layout.fragment_favorite);
    }
}
