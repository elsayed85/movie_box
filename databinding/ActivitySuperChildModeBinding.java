package com.movieboxpro.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public abstract class ActivitySuperChildModeBinding extends ViewDataBinding {
    public final FrameLayout flContainer;
    public final LinearLayout llAdd;
    public final SwitchCompat switchCompat;
    public final TextView textView;
    public final CommonTitleBarLayoutBinding toolBar;

    /* JADX INFO: Access modifiers changed from: protected */
    public ActivitySuperChildModeBinding(Object obj, View view, int i, FrameLayout frameLayout, LinearLayout linearLayout, SwitchCompat switchCompat, TextView textView, CommonTitleBarLayoutBinding commonTitleBarLayoutBinding) {
        super(obj, view, i);
        this.flContainer = frameLayout;
        this.llAdd = linearLayout;
        this.switchCompat = switchCompat;
        this.textView = textView;
        this.toolBar = commonTitleBarLayoutBinding;
        setContainedBinding(commonTitleBarLayoutBinding);
    }

    public static ActivitySuperChildModeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySuperChildModeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivitySuperChildModeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_super_child_mode, viewGroup, z, obj);
    }

    public static ActivitySuperChildModeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySuperChildModeBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivitySuperChildModeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_super_child_mode, null, false, obj);
    }

    public static ActivitySuperChildModeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySuperChildModeBinding bind(View view, Object obj) {
        return (ActivitySuperChildModeBinding) bind(obj, view, R.layout.activity_super_child_mode);
    }
}
