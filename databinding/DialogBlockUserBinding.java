package com.movieboxpro.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public abstract class DialogBlockUserBinding extends ViewDataBinding {
    public final TextView tvBlock;
    public final TextView tvBlockDesc;
    public final TextView tvCancel;

    /* JADX INFO: Access modifiers changed from: protected */
    public DialogBlockUserBinding(Object obj, View view, int i, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.tvBlock = textView;
        this.tvBlockDesc = textView2;
        this.tvCancel = textView3;
    }

    public static DialogBlockUserBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogBlockUserBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogBlockUserBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_block_user, viewGroup, z, obj);
    }

    public static DialogBlockUserBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogBlockUserBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogBlockUserBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_block_user, null, false, obj);
    }

    public static DialogBlockUserBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogBlockUserBinding bind(View view, Object obj) {
        return (DialogBlockUserBinding) bind(obj, view, R.layout.dialog_block_user);
    }
}
