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
public abstract class DialogPreviewLocalSubtitleBinding extends ViewDataBinding {
    public final AppCompatImageView ivClose;
    public final LinearLayout llChooseLanguage;
    public final LinearLayout llConfirm;
    public final RecyclerView recyclerView;
    public final TextView tvDone;
    public final TextView tvLanguage;
    public final TextView tvTranscode;

    /* JADX INFO: Access modifiers changed from: protected */
    public DialogPreviewLocalSubtitleBinding(Object obj, View view, int i, AppCompatImageView appCompatImageView, LinearLayout linearLayout, LinearLayout linearLayout2, RecyclerView recyclerView, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.ivClose = appCompatImageView;
        this.llChooseLanguage = linearLayout;
        this.llConfirm = linearLayout2;
        this.recyclerView = recyclerView;
        this.tvDone = textView;
        this.tvLanguage = textView2;
        this.tvTranscode = textView3;
    }

    public static DialogPreviewLocalSubtitleBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogPreviewLocalSubtitleBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogPreviewLocalSubtitleBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_preview_local_subtitle, viewGroup, z, obj);
    }

    public static DialogPreviewLocalSubtitleBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogPreviewLocalSubtitleBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogPreviewLocalSubtitleBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_preview_local_subtitle, null, false, obj);
    }

    public static DialogPreviewLocalSubtitleBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogPreviewLocalSubtitleBinding bind(View view, Object obj) {
        return (DialogPreviewLocalSubtitleBinding) bind(obj, view, R.layout.dialog_preview_local_subtitle);
    }
}
