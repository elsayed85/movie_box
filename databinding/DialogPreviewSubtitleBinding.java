package com.movieboxpro.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public abstract class DialogPreviewSubtitleBinding extends ViewDataBinding {
    public final AppCompatImageView ivClose;
    public final LinearLayout llConfirm;
    public final RecyclerView recyclerView;
    public final TextView tvDone;
    public final TextView tvTranscode;

    /* JADX INFO: Access modifiers changed from: protected */
    public DialogPreviewSubtitleBinding(Object obj, View view, int i, AppCompatImageView appCompatImageView, LinearLayout linearLayout, RecyclerView recyclerView, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.ivClose = appCompatImageView;
        this.llConfirm = linearLayout;
        this.recyclerView = recyclerView;
        this.tvDone = textView;
        this.tvTranscode = textView2;
    }

    public static DialogPreviewSubtitleBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogPreviewSubtitleBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogPreviewSubtitleBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_preview_subtitle, viewGroup, z, obj);
    }

    public static DialogPreviewSubtitleBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogPreviewSubtitleBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogPreviewSubtitleBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_preview_subtitle, null, false, obj);
    }

    public static DialogPreviewSubtitleBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogPreviewSubtitleBinding bind(View view, Object obj) {
        return (DialogPreviewSubtitleBinding) bind(obj, view, R.layout.dialog_preview_subtitle);
    }
}
