package com.movieboxpro.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public abstract class ActivityAccountSecurityBinding extends ViewDataBinding {
    public final ConstraintLayout clDelete;
    public final TextView textView;
    public final CommonTitleBarLayoutBinding toolBar;

    /* JADX INFO: Access modifiers changed from: protected */
    public ActivityAccountSecurityBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, TextView textView, CommonTitleBarLayoutBinding commonTitleBarLayoutBinding) {
        super(obj, view, i);
        this.clDelete = constraintLayout;
        this.textView = textView;
        this.toolBar = commonTitleBarLayoutBinding;
        setContainedBinding(commonTitleBarLayoutBinding);
    }

    public static ActivityAccountSecurityBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAccountSecurityBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityAccountSecurityBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_account_security, viewGroup, z, obj);
    }

    public static ActivityAccountSecurityBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAccountSecurityBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityAccountSecurityBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_account_security, null, false, obj);
    }

    public static ActivityAccountSecurityBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAccountSecurityBinding bind(View view, Object obj) {
        return (ActivityAccountSecurityBinding) bind(obj, view, R.layout.activity_account_security);
    }
}
