package com.movieboxpro.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.movieboxpro.android.R;
import com.movieboxpro.android.viewmodel.UserInfoViewModel;
import de.hdodenhof.circleimageview.CircleImageView;
/* loaded from: classes3.dex */
public abstract class FragmentUserInfo2Binding extends ViewDataBinding {
    public final ConstraintLayout clTopInfo;
    public final FrameLayout flAvatar;
    public final FrameLayout flName;
    public final FrameLayout frameLayout;
    public final CircleImageView ivAvatar;
    public final ImageView ivScan;
    public final ImageView ivSignOut;
    public final ImageView ivVip;
    public final LinearLayout llHistory;
    public final LinearLayout llNotification;
    public final LinearLayout llTv;
    public final LinearLayout llVip;
    @Bindable
    protected UserInfoViewModel mVm;
    public final RecyclerView rvFunction;
    public final TextView tabCount;
    public final TextView tvEmail;
    public final TextView tvNextBill;
    public final TextView tvUid;
    public final TextView tvUsername;
    public final TextView tvVipExpire;

    public abstract void setVm(UserInfoViewModel userInfoViewModel);

    /* JADX INFO: Access modifiers changed from: protected */
    public FragmentUserInfo2Binding(Object obj, View view, int i, ConstraintLayout constraintLayout, FrameLayout frameLayout, FrameLayout frameLayout2, FrameLayout frameLayout3, CircleImageView circleImageView, ImageView imageView, ImageView imageView2, ImageView imageView3, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, RecyclerView recyclerView, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6) {
        super(obj, view, i);
        this.clTopInfo = constraintLayout;
        this.flAvatar = frameLayout;
        this.flName = frameLayout2;
        this.frameLayout = frameLayout3;
        this.ivAvatar = circleImageView;
        this.ivScan = imageView;
        this.ivSignOut = imageView2;
        this.ivVip = imageView3;
        this.llHistory = linearLayout;
        this.llNotification = linearLayout2;
        this.llTv = linearLayout3;
        this.llVip = linearLayout4;
        this.rvFunction = recyclerView;
        this.tabCount = textView;
        this.tvEmail = textView2;
        this.tvNextBill = textView3;
        this.tvUid = textView4;
        this.tvUsername = textView5;
        this.tvVipExpire = textView6;
    }

    public UserInfoViewModel getVm() {
        return this.mVm;
    }

    public static FragmentUserInfo2Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentUserInfo2Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentUserInfo2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_user_info2, viewGroup, z, obj);
    }

    public static FragmentUserInfo2Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentUserInfo2Binding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentUserInfo2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_user_info2, null, false, obj);
    }

    public static FragmentUserInfo2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentUserInfo2Binding bind(View view, Object obj) {
        return (FragmentUserInfo2Binding) bind(obj, view, R.layout.fragment_user_info2);
    }
}
