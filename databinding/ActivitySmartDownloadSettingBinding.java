package com.movieboxpro.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.android.R;
import com.movieboxpro.android.view.widget.CustomSwitchView;
/* loaded from: classes3.dex */
public abstract class ActivitySmartDownloadSettingBinding extends ViewDataBinding {
    public final LinearLayout llResolution;
    public final CustomSwitchView switchAutoDelete;
    public final CustomSwitchView switchDownload;
    public final CustomSwitchView switchWifiOnly;
    public final TextView textView;
    public final CommonTitleBarLayoutBinding toolBar;
    public final TextView tvResolution;

    /* JADX INFO: Access modifiers changed from: protected */
    public ActivitySmartDownloadSettingBinding(Object obj, View view, int i, LinearLayout linearLayout, CustomSwitchView customSwitchView, CustomSwitchView customSwitchView2, CustomSwitchView customSwitchView3, TextView textView, CommonTitleBarLayoutBinding commonTitleBarLayoutBinding, TextView textView2) {
        super(obj, view, i);
        this.llResolution = linearLayout;
        this.switchAutoDelete = customSwitchView;
        this.switchDownload = customSwitchView2;
        this.switchWifiOnly = customSwitchView3;
        this.textView = textView;
        this.toolBar = commonTitleBarLayoutBinding;
        setContainedBinding(commonTitleBarLayoutBinding);
        this.tvResolution = textView2;
    }

    public static ActivitySmartDownloadSettingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySmartDownloadSettingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivitySmartDownloadSettingBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_smart_download_setting, viewGroup, z, obj);
    }

    public static ActivitySmartDownloadSettingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySmartDownloadSettingBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivitySmartDownloadSettingBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_smart_download_setting, null, false, obj);
    }

    public static ActivitySmartDownloadSettingBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySmartDownloadSettingBinding bind(View view, Object obj) {
        return (ActivitySmartDownloadSettingBinding) bind(obj, view, R.layout.activity_smart_download_setting);
    }
}
