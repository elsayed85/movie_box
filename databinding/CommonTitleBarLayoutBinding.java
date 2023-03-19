package com.movieboxpro.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public abstract class CommonTitleBarLayoutBinding extends ViewDataBinding {
    public final FrameLayout frameLayout;
    public final ImageView ivRight;
    public final ImageView ivTitleBack;
    public final LinearLayout llBack;
    public final LinearLayout llRight;
    public final RelativeLayout relativeLayout;
    public final TextView tvRight;
    public final TextView tvTitle;

    /* JADX INFO: Access modifiers changed from: protected */
    public CommonTitleBarLayoutBinding(Object obj, View view, int i, FrameLayout frameLayout, ImageView imageView, ImageView imageView2, LinearLayout linearLayout, LinearLayout linearLayout2, RelativeLayout relativeLayout, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.frameLayout = frameLayout;
        this.ivRight = imageView;
        this.ivTitleBack = imageView2;
        this.llBack = linearLayout;
        this.llRight = linearLayout2;
        this.relativeLayout = relativeLayout;
        this.tvRight = textView;
        this.tvTitle = textView2;
    }

    public static CommonTitleBarLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CommonTitleBarLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (CommonTitleBarLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.common_title_bar_layout, viewGroup, z, obj);
    }

    public static CommonTitleBarLayoutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CommonTitleBarLayoutBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (CommonTitleBarLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.common_title_bar_layout, null, false, obj);
    }

    public static CommonTitleBarLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CommonTitleBarLayoutBinding bind(View view, Object obj) {
        return (CommonTitleBarLayoutBinding) bind(obj, view, R.layout.common_title_bar_layout);
    }
}
