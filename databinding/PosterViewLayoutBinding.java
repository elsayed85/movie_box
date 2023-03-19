package com.movieboxpro.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public abstract class PosterViewLayoutBinding extends ViewDataBinding {
    public final AppCompatImageView ivPoster;
    public final AppCompatImageView ivTag;
    public final TextView tvName;

    /* JADX INFO: Access modifiers changed from: protected */
    public PosterViewLayoutBinding(Object obj, View view, int i, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, TextView textView) {
        super(obj, view, i);
        this.ivPoster = appCompatImageView;
        this.ivTag = appCompatImageView2;
        this.tvName = textView;
    }

    public static PosterViewLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PosterViewLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (PosterViewLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.poster_view_layout, viewGroup, z, obj);
    }

    public static PosterViewLayoutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PosterViewLayoutBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (PosterViewLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.poster_view_layout, null, false, obj);
    }

    public static PosterViewLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PosterViewLayoutBinding bind(View view, Object obj) {
        return (PosterViewLayoutBinding) bind(obj, view, R.layout.poster_view_layout);
    }
}
