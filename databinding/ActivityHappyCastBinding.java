package com.movieboxpro.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public abstract class ActivityHappyCastBinding extends ViewDataBinding {
    public final FrameLayout flContainer;

    /* JADX INFO: Access modifiers changed from: protected */
    public ActivityHappyCastBinding(Object obj, View view, int i, FrameLayout frameLayout) {
        super(obj, view, i);
        this.flContainer = frameLayout;
    }

    public static ActivityHappyCastBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityHappyCastBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityHappyCastBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_happy_cast, viewGroup, z, obj);
    }

    public static ActivityHappyCastBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityHappyCastBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityHappyCastBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_happy_cast, null, false, obj);
    }

    public static ActivityHappyCastBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityHappyCastBinding bind(View view, Object obj) {
        return (ActivityHappyCastBinding) bind(obj, view, R.layout.activity_happy_cast);
    }
}
