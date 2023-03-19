package com.movieboxpro.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public abstract class DialogChooseDownloadPathBinding extends ViewDataBinding {
    public final LinearLayout llPath;
    public final TextView tvCancel;
    public final TextView tvPath;
    public final TextView tvSave;

    /* JADX INFO: Access modifiers changed from: protected */
    public DialogChooseDownloadPathBinding(Object obj, View view, int i, LinearLayout linearLayout, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.llPath = linearLayout;
        this.tvCancel = textView;
        this.tvPath = textView2;
        this.tvSave = textView3;
    }

    public static DialogChooseDownloadPathBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogChooseDownloadPathBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogChooseDownloadPathBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_choose_download_path, viewGroup, z, obj);
    }

    public static DialogChooseDownloadPathBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogChooseDownloadPathBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogChooseDownloadPathBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_choose_download_path, null, false, obj);
    }

    public static DialogChooseDownloadPathBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogChooseDownloadPathBinding bind(View view, Object obj) {
        return (DialogChooseDownloadPathBinding) bind(obj, view, R.layout.dialog_choose_download_path);
    }
}
