package com.movieboxpro.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.android.R;
import com.movieboxpro.android.view.videocontroller.TransCodingSubtitleController;
/* loaded from: classes3.dex */
public abstract class FragmentTranscodeSubtitleBinding extends ViewDataBinding {
    public final TransCodingSubtitleController controller;
    public final FrameLayout flContainer;

    /* JADX INFO: Access modifiers changed from: protected */
    public FragmentTranscodeSubtitleBinding(Object obj, View view, int i, TransCodingSubtitleController transCodingSubtitleController, FrameLayout frameLayout) {
        super(obj, view, i);
        this.controller = transCodingSubtitleController;
        this.flContainer = frameLayout;
    }

    public static FragmentTranscodeSubtitleBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentTranscodeSubtitleBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentTranscodeSubtitleBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_transcode_subtitle, viewGroup, z, obj);
    }

    public static FragmentTranscodeSubtitleBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentTranscodeSubtitleBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentTranscodeSubtitleBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_transcode_subtitle, null, false, obj);
    }

    public static FragmentTranscodeSubtitleBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentTranscodeSubtitleBinding bind(View view, Object obj) {
        return (FragmentTranscodeSubtitleBinding) bind(obj, view, R.layout.fragment_transcode_subtitle);
    }
}
