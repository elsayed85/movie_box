package com.movieboxpro.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public abstract class ActivityListLayoutBinding extends ViewDataBinding {
    public final FrameLayout flContainer;
    public final CommonTitleBarLayoutBinding toolBar;

    /* JADX INFO: Access modifiers changed from: protected */
    public ActivityListLayoutBinding(Object obj, View view, int i, FrameLayout frameLayout, CommonTitleBarLayoutBinding commonTitleBarLayoutBinding) {
        super(obj, view, i);
        this.flContainer = frameLayout;
        this.toolBar = commonTitleBarLayoutBinding;
        setContainedBinding(commonTitleBarLayoutBinding);
    }

    public static ActivityListLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityListLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityListLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_list_layout, viewGroup, z, obj);
    }

    public static ActivityListLayoutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityListLayoutBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityListLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_list_layout, null, false, obj);
    }

    public static ActivityListLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityListLayoutBinding bind(View view, Object obj) {
        return (ActivityListLayoutBinding) bind(obj, view, R.layout.activity_list_layout);
    }
}
