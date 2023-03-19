package com.movieboxpro.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.mediarouter.app.MediaRouteButton;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public abstract class ActivitySuperChildModeMainBinding extends ViewDataBinding {
    public final View dot;
    public final FrameLayout drawerFrameLayout;
    public final DrawerLayout drawerLayout;
    public final FrameLayout flCast;
    public final FrameLayout flContainer;
    public final FrameLayout flDrawer;
    public final FrameLayout flMode;
    public final ImageView homelistViewLogo;
    public final ImageView homelistViewMore;
    public final LinearLayout homelistViewTitle;
    public final MediaRouteButton mediaRouteButton;
    public final TextView textView;

    /* JADX INFO: Access modifiers changed from: protected */
    public ActivitySuperChildModeMainBinding(Object obj, View view, int i, View view2, FrameLayout frameLayout, DrawerLayout drawerLayout, FrameLayout frameLayout2, FrameLayout frameLayout3, FrameLayout frameLayout4, FrameLayout frameLayout5, ImageView imageView, ImageView imageView2, LinearLayout linearLayout, MediaRouteButton mediaRouteButton, TextView textView) {
        super(obj, view, i);
        this.dot = view2;
        this.drawerFrameLayout = frameLayout;
        this.drawerLayout = drawerLayout;
        this.flCast = frameLayout2;
        this.flContainer = frameLayout3;
        this.flDrawer = frameLayout4;
        this.flMode = frameLayout5;
        this.homelistViewLogo = imageView;
        this.homelistViewMore = imageView2;
        this.homelistViewTitle = linearLayout;
        this.mediaRouteButton = mediaRouteButton;
        this.textView = textView;
    }

    public static ActivitySuperChildModeMainBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySuperChildModeMainBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivitySuperChildModeMainBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_super_child_mode_main, viewGroup, z, obj);
    }

    public static ActivitySuperChildModeMainBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySuperChildModeMainBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivitySuperChildModeMainBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_super_child_mode_main, null, false, obj);
    }

    public static ActivitySuperChildModeMainBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySuperChildModeMainBinding bind(View view, Object obj) {
        return (ActivitySuperChildModeMainBinding) bind(obj, view, R.layout.activity_super_child_mode_main);
    }
}
