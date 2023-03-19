package com.movieboxpro.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public abstract class DialogAudioSynBinding extends ViewDataBinding {
    public final ImageView ivAudioDelayAdd;
    public final ImageView ivAudioDelaySub;
    public final AppCompatImageView ivClose;
    public final LinearLayout linearLayout;
    public final TextView tvAudioDelay;

    /* JADX INFO: Access modifiers changed from: protected */
    public DialogAudioSynBinding(Object obj, View view, int i, ImageView imageView, ImageView imageView2, AppCompatImageView appCompatImageView, LinearLayout linearLayout, TextView textView) {
        super(obj, view, i);
        this.ivAudioDelayAdd = imageView;
        this.ivAudioDelaySub = imageView2;
        this.ivClose = appCompatImageView;
        this.linearLayout = linearLayout;
        this.tvAudioDelay = textView;
    }

    public static DialogAudioSynBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAudioSynBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogAudioSynBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_audio_syn, viewGroup, z, obj);
    }

    public static DialogAudioSynBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAudioSynBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogAudioSynBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_audio_syn, null, false, obj);
    }

    public static DialogAudioSynBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAudioSynBinding bind(View view, Object obj) {
        return (DialogAudioSynBinding) bind(obj, view, R.layout.dialog_audio_syn);
    }
}
