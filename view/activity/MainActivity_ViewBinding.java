package com.movieboxpro.android.view.activity;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.google.android.material.tabs.TabLayout;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public class MainActivity_ViewBinding implements Unbinder {
    private MainActivity target;

    public MainActivity_ViewBinding(MainActivity mainActivity) {
        this(mainActivity, mainActivity.getWindow().getDecorView());
    }

    public MainActivity_ViewBinding(MainActivity mainActivity, View view) {
        this.target = mainActivity;
        mainActivity.mTab = (TabLayout) Utils.findRequiredViewAsType(view, R.id.main_tabs, "field 'mTab'", TabLayout.class);
        mainActivity.content = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.content, "field 'content'", FrameLayout.class);
        mainActivity.container = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.container, "field 'container'", LinearLayout.class);
        mainActivity.drawer = (DrawerLayout) Utils.findRequiredViewAsType(view, R.id.drawer_layout, "field 'drawer'", DrawerLayout.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        MainActivity mainActivity = this.target;
        if (mainActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        mainActivity.mTab = null;
        mainActivity.content = null;
        mainActivity.container = null;
        mainActivity.drawer = null;
    }
}
