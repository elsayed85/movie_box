package com.movieboxpro.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public abstract class ActivityPreferBinding extends ViewDataBinding {
    public final FrameLayout flContainer;
    public final RadioButton rbDislikes;
    public final RadioButton rbEpisode;
    public final RadioButton rbLikes;
    public final RadioButton rbMovies;
    public final RadioButton rbTv;
    public final RadioGroup rgLike;
    public final RadioGroup rgType;
    public final CommonTitleBarLayoutBinding toolBar;

    /* JADX INFO: Access modifiers changed from: protected */
    public ActivityPreferBinding(Object obj, View view, int i, FrameLayout frameLayout, RadioButton radioButton, RadioButton radioButton2, RadioButton radioButton3, RadioButton radioButton4, RadioButton radioButton5, RadioGroup radioGroup, RadioGroup radioGroup2, CommonTitleBarLayoutBinding commonTitleBarLayoutBinding) {
        super(obj, view, i);
        this.flContainer = frameLayout;
        this.rbDislikes = radioButton;
        this.rbEpisode = radioButton2;
        this.rbLikes = radioButton3;
        this.rbMovies = radioButton4;
        this.rbTv = radioButton5;
        this.rgLike = radioGroup;
        this.rgType = radioGroup2;
        this.toolBar = commonTitleBarLayoutBinding;
        setContainedBinding(commonTitleBarLayoutBinding);
    }

    public static ActivityPreferBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityPreferBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityPreferBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_prefer, viewGroup, z, obj);
    }

    public static ActivityPreferBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityPreferBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityPreferBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_prefer, null, false, obj);
    }

    public static ActivityPreferBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityPreferBinding bind(View view, Object obj) {
        return (ActivityPreferBinding) bind(obj, view, R.layout.activity_prefer);
    }
}
