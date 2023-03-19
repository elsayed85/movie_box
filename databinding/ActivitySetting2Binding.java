package com.movieboxpro.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.android.R;
import com.movieboxpro.android.view.widget.CustomSwitchView;
import de.hdodenhof.circleimageview.CircleImageView;
/* loaded from: classes3.dex */
public abstract class ActivitySetting2Binding extends ViewDataBinding {
    public final ConstraintLayout clDownloadLocation;
    public final ConstraintLayout clInfo;
    public final AppCompatImageView ivArrow;
    public final AppCompatImageView ivArrow2;
    public final CircleImageView ivAvatar;
    public final AppCompatImageView ivVip;
    public final LinearLayout llEmptyCache;
    public final LinearLayout llLanguage;
    public final LinearLayout llPlayer;
    public final LinearLayout llSmartDownload;
    public final LinearLayout llSpeedTest;
    public final CustomSwitchView switchAutoPlayNext;
    public final CustomSwitchView switchAutoSelectSubtitle;
    public final CustomSwitchView switchBlack;
    public final CustomSwitchView switchDownloadCellular;
    public final CustomSwitchView switchEmail;
    public final CustomSwitchView switchFullScreen;
    public final CustomSwitchView switchHideMovieList;
    public final CustomSwitchView switchPush;
    public final CustomSwitchView switchRememberOrg;
    public final CommonTitleBarLayoutBinding toolBar;
    public final TextView tvDesc;
    public final TextView tvDownloadDirName;
    public final TextView tvDownloadLocation;
    public final TextView tvDownloadSpace;
    public final TextView tvLanguage;
    public final TextView tvNickname;
    public final TextView tvPlayer;
    public final TextView tvSmartDownload;

    /* JADX INFO: Access modifiers changed from: protected */
    public ActivitySetting2Binding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, CircleImageView circleImageView, AppCompatImageView appCompatImageView3, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5, CustomSwitchView customSwitchView, CustomSwitchView customSwitchView2, CustomSwitchView customSwitchView3, CustomSwitchView customSwitchView4, CustomSwitchView customSwitchView5, CustomSwitchView customSwitchView6, CustomSwitchView customSwitchView7, CustomSwitchView customSwitchView8, CustomSwitchView customSwitchView9, CommonTitleBarLayoutBinding commonTitleBarLayoutBinding, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8) {
        super(obj, view, i);
        this.clDownloadLocation = constraintLayout;
        this.clInfo = constraintLayout2;
        this.ivArrow = appCompatImageView;
        this.ivArrow2 = appCompatImageView2;
        this.ivAvatar = circleImageView;
        this.ivVip = appCompatImageView3;
        this.llEmptyCache = linearLayout;
        this.llLanguage = linearLayout2;
        this.llPlayer = linearLayout3;
        this.llSmartDownload = linearLayout4;
        this.llSpeedTest = linearLayout5;
        this.switchAutoPlayNext = customSwitchView;
        this.switchAutoSelectSubtitle = customSwitchView2;
        this.switchBlack = customSwitchView3;
        this.switchDownloadCellular = customSwitchView4;
        this.switchEmail = customSwitchView5;
        this.switchFullScreen = customSwitchView6;
        this.switchHideMovieList = customSwitchView7;
        this.switchPush = customSwitchView8;
        this.switchRememberOrg = customSwitchView9;
        this.toolBar = commonTitleBarLayoutBinding;
        setContainedBinding(commonTitleBarLayoutBinding);
        this.tvDesc = textView;
        this.tvDownloadDirName = textView2;
        this.tvDownloadLocation = textView3;
        this.tvDownloadSpace = textView4;
        this.tvLanguage = textView5;
        this.tvNickname = textView6;
        this.tvPlayer = textView7;
        this.tvSmartDownload = textView8;
    }

    public static ActivitySetting2Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySetting2Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivitySetting2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_setting2, viewGroup, z, obj);
    }

    public static ActivitySetting2Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySetting2Binding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivitySetting2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_setting2, null, false, obj);
    }

    public static ActivitySetting2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySetting2Binding bind(View view, Object obj) {
        return (ActivitySetting2Binding) bind(obj, view, R.layout.activity_setting2);
    }
}
