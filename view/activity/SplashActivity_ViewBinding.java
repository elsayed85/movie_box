package com.movieboxpro.android.view.activity;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public class SplashActivity_ViewBinding implements Unbinder {
    private SplashActivity target;

    public SplashActivity_ViewBinding(SplashActivity splashActivity) {
        this(splashActivity, splashActivity.getWindow().getDecorView());
    }

    public SplashActivity_ViewBinding(SplashActivity splashActivity, View view) {
        this.target = splashActivity;
        splashActivity.splashTop = (ImageView) Utils.findRequiredViewAsType(view, R.id.splash_top, "field 'splashTop'", ImageView.class);
        splashActivity.splashBottom = (ImageView) Utils.findRequiredViewAsType(view, R.id.splash_bottom, "field 'splashBottom'", ImageView.class);
        splashActivity.adContainer = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.adContainer, "field 'adContainer'", FrameLayout.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SplashActivity splashActivity = this.target;
        if (splashActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        splashActivity.splashTop = null;
        splashActivity.splashBottom = null;
        splashActivity.adContainer = null;
    }
}
