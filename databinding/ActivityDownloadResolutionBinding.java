package com.movieboxpro.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public abstract class ActivityDownloadResolutionBinding extends ViewDataBinding {
    public final RecyclerView recyclerView;
    public final CommonTitleBarLayoutBinding toolBar;

    /* JADX INFO: Access modifiers changed from: protected */
    public ActivityDownloadResolutionBinding(Object obj, View view, int i, RecyclerView recyclerView, CommonTitleBarLayoutBinding commonTitleBarLayoutBinding) {
        super(obj, view, i);
        this.recyclerView = recyclerView;
        this.toolBar = commonTitleBarLayoutBinding;
        setContainedBinding(commonTitleBarLayoutBinding);
    }

    public static ActivityDownloadResolutionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDownloadResolutionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityDownloadResolutionBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_download_resolution, viewGroup, z, obj);
    }

    public static ActivityDownloadResolutionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDownloadResolutionBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityDownloadResolutionBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_download_resolution, null, false, obj);
    }

    public static ActivityDownloadResolutionBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDownloadResolutionBinding bind(View view, Object obj) {
        return (ActivityDownloadResolutionBinding) bind(obj, view, R.layout.activity_download_resolution);
    }
}
