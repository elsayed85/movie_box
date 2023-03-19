package com.movieboxpro.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public abstract class ActivityAccountBinding extends ViewDataBinding {
    /* JADX INFO: Access modifiers changed from: protected */
    public ActivityAccountBinding(Object obj, View view, int i) {
        super(obj, view, i);
    }

    public static ActivityAccountBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAccountBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityAccountBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_account, viewGroup, z, obj);
    }

    public static ActivityAccountBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAccountBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityAccountBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_account, null, false, obj);
    }

    public static ActivityAccountBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAccountBinding bind(View view, Object obj) {
        return (ActivityAccountBinding) bind(obj, view, R.layout.activity_account);
    }
}
