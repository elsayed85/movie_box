package com.movieboxpro.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public abstract class DialogSmartDownloadHintBinding extends ViewDataBinding {
    public final TextView tvDismiss;
    public final TextView tvNoShow;
    public final TextView tvOn;

    /* JADX INFO: Access modifiers changed from: protected */
    public DialogSmartDownloadHintBinding(Object obj, View view, int i, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.tvDismiss = textView;
        this.tvNoShow = textView2;
        this.tvOn = textView3;
    }

    public static DialogSmartDownloadHintBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogSmartDownloadHintBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogSmartDownloadHintBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_smart_download_hint, viewGroup, z, obj);
    }

    public static DialogSmartDownloadHintBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogSmartDownloadHintBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogSmartDownloadHintBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_smart_download_hint, null, false, obj);
    }

    public static DialogSmartDownloadHintBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogSmartDownloadHintBinding bind(View view, Object obj) {
        return (DialogSmartDownloadHintBinding) bind(obj, view, R.layout.dialog_smart_download_hint);
    }
}
