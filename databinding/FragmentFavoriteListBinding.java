package com.movieboxpro.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public abstract class FragmentFavoriteListBinding extends ViewDataBinding {
    public final RecyclerView recyclerView;

    /* JADX INFO: Access modifiers changed from: protected */
    public FragmentFavoriteListBinding(Object obj, View view, int i, RecyclerView recyclerView) {
        super(obj, view, i);
        this.recyclerView = recyclerView;
    }

    public static FragmentFavoriteListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentFavoriteListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentFavoriteListBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_favorite_list, viewGroup, z, obj);
    }

    public static FragmentFavoriteListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentFavoriteListBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentFavoriteListBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_favorite_list, null, false, obj);
    }

    public static FragmentFavoriteListBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentFavoriteListBinding bind(View view, Object obj) {
        return (FragmentFavoriteListBinding) bind(obj, view, R.layout.fragment_favorite_list);
    }
}
