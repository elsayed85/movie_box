package com.movieboxpro.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.android.R;
import de.hdodenhof.circleimageview.CircleImageView;
/* loaded from: classes3.dex */
public abstract class ActivityAccount2Binding extends ViewDataBinding {
    public final CircleImageView ivAvatar;
    public final AppCompatImageView ivVipArrow;
    public final LinearLayout llAccountSecurity;
    public final LinearLayout llAvatar;
    public final LinearLayout llBlockList;
    public final LinearLayout llClearWatched;
    public final LinearLayout llEmail;
    public final LinearLayout llFamilyPlan;
    public final LinearLayout llMyOrder;
    public final LinearLayout llName;
    public final LinearLayout llSubscriptions;
    public final LinearLayout llUid;
    public final CommonTitleBarLayoutBinding toolBar;
    public final TextView tvEmail;
    public final TextView tvFamilyNum;
    public final TextView tvSignOut;
    public final TextView tvUid;
    public final TextView tvUsername;
    public final TextView tvVipExpire;

    /* JADX INFO: Access modifiers changed from: protected */
    public ActivityAccount2Binding(Object obj, View view, int i, CircleImageView circleImageView, AppCompatImageView appCompatImageView, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5, LinearLayout linearLayout6, LinearLayout linearLayout7, LinearLayout linearLayout8, LinearLayout linearLayout9, LinearLayout linearLayout10, CommonTitleBarLayoutBinding commonTitleBarLayoutBinding, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6) {
        super(obj, view, i);
        this.ivAvatar = circleImageView;
        this.ivVipArrow = appCompatImageView;
        this.llAccountSecurity = linearLayout;
        this.llAvatar = linearLayout2;
        this.llBlockList = linearLayout3;
        this.llClearWatched = linearLayout4;
        this.llEmail = linearLayout5;
        this.llFamilyPlan = linearLayout6;
        this.llMyOrder = linearLayout7;
        this.llName = linearLayout8;
        this.llSubscriptions = linearLayout9;
        this.llUid = linearLayout10;
        this.toolBar = commonTitleBarLayoutBinding;
        setContainedBinding(commonTitleBarLayoutBinding);
        this.tvEmail = textView;
        this.tvFamilyNum = textView2;
        this.tvSignOut = textView3;
        this.tvUid = textView4;
        this.tvUsername = textView5;
        this.tvVipExpire = textView6;
    }

    public static ActivityAccount2Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAccount2Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityAccount2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_account2, viewGroup, z, obj);
    }

    public static ActivityAccount2Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAccount2Binding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityAccount2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_account2, null, false, obj);
    }

    public static ActivityAccount2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAccount2Binding bind(View view, Object obj) {
        return (ActivityAccount2Binding) bind(obj, view, R.layout.activity_account2);
    }
}
