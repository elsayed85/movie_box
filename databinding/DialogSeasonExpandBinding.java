package com.movieboxpro.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public abstract class DialogSeasonExpandBinding extends ViewDataBinding {
    public final RecyclerView recyclerView;

    /* JADX INFO: Access modifiers changed from: protected */
    public DialogSeasonExpandBinding(Object obj, View view, int i, RecyclerView recyclerView) {
        super(obj, view, i);
        this.recyclerView = recyclerView;
    }

    public static DialogSeasonExpandBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogSeasonExpandBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogSeasonExpandBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_season_expand, viewGroup, z, obj);
    }

    public static DialogSeasonExpandBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogSeasonExpandBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogSeasonExpandBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_season_expand, null, false, obj);
    }

    public static DialogSeasonExpandBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogSeasonExpandBinding bind(View view, Object obj) {
        return (DialogSeasonExpandBinding) bind(obj, view, R.layout.dialog_season_expand);
    }
}
