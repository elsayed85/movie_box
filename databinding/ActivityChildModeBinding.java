package com.movieboxpro.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public abstract class ActivityChildModeBinding extends ViewDataBinding {
    public final FrameLayout flManage;
    public final LinearLayout flTvDevice;
    public final AppCompatImageView imageView;
    public final AppCompatImageView imageView2;
    public final AppCompatImageView ivChildMode;
    public final AppCompatImageView ivSuperChildMode;
    public final LinearLayout llChildMode;
    public final LinearLayout llSuperChildMode;
    public final TextView textView;
    public final TextView textView2;
    public final CommonTitleBarLayoutBinding toolBar;
    public final TextView tvTvNum;

    /* JADX INFO: Access modifiers changed from: protected */
    public ActivityChildModeBinding(Object obj, View view, int i, FrameLayout frameLayout, LinearLayout linearLayout, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, AppCompatImageView appCompatImageView3, AppCompatImageView appCompatImageView4, LinearLayout linearLayout2, LinearLayout linearLayout3, TextView textView, TextView textView2, CommonTitleBarLayoutBinding commonTitleBarLayoutBinding, TextView textView3) {
        super(obj, view, i);
        this.flManage = frameLayout;
        this.flTvDevice = linearLayout;
        this.imageView = appCompatImageView;
        this.imageView2 = appCompatImageView2;
        this.ivChildMode = appCompatImageView3;
        this.ivSuperChildMode = appCompatImageView4;
        this.llChildMode = linearLayout2;
        this.llSuperChildMode = linearLayout3;
        this.textView = textView;
        this.textView2 = textView2;
        this.toolBar = commonTitleBarLayoutBinding;
        setContainedBinding(commonTitleBarLayoutBinding);
        this.tvTvNum = textView3;
    }

    public static ActivityChildModeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityChildModeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityChildModeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_child_mode, viewGroup, z, obj);
    }

    public static ActivityChildModeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityChildModeBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityChildModeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_child_mode, null, false, obj);
    }

    public static ActivityChildModeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityChildModeBinding bind(View view, Object obj) {
        return (ActivityChildModeBinding) bind(obj, view, R.layout.activity_child_mode);
    }
}
