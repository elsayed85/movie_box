package com.movieboxpro.android.view.fragment;

import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.mediarouter.app.MediaRouteButton;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.movieboxpro.android.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
/* loaded from: classes3.dex */
public class HomeListFragment_ViewBinding implements Unbinder {
    private HomeListFragment target;
    private View view7f090237;
    private View view7f0902bc;

    public HomeListFragment_ViewBinding(final HomeListFragment homeListFragment, View view) {
        this.target = homeListFragment;
        homeListFragment.homelistViewLogo = (ImageView) Utils.findRequiredViewAsType(view, R.id.homelist_view_logo, "field 'homelistViewLogo'", ImageView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.homelist_view_more, "field 'homelistViewMore' and method 'onViewClicked'");
        homeListFragment.homelistViewMore = (ImageView) Utils.castView(findRequiredView, R.id.homelist_view_more, "field 'homelistViewMore'", ImageView.class);
        this.view7f090237 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.fragment.HomeListFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                homeListFragment.onViewClicked();
            }
        });
        homeListFragment.mTabs = (SmartTabLayout) Utils.findRequiredViewAsType(view, R.id.search_result_tab, "field 'mTabs'", SmartTabLayout.class);
        homeListFragment.mPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.search_result_pager, "field 'mPager'", ViewPager.class);
        homeListFragment.flCast = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.flCast, "field 'flCast'", FrameLayout.class);
        homeListFragment.mediaRouteButton = (MediaRouteButton) Utils.findRequiredViewAsType(view, R.id.media_route_button, "field 'mediaRouteButton'", MediaRouteButton.class);
        homeListFragment.childModeViewStub = (ViewStub) Utils.findRequiredViewAsType(view, R.id.childModeViewStub, "field 'childModeViewStub'", ViewStub.class);
        homeListFragment.incognitoModeViewStub = (ViewStub) Utils.findRequiredViewAsType(view, R.id.incognitoModeViewStub, "field 'incognitoModeViewStub'", ViewStub.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.ivFilter, "field 'ivFilter' and method 'onFilterClicked'");
        homeListFragment.ivFilter = (ImageView) Utils.castView(findRequiredView2, R.id.ivFilter, "field 'ivFilter'", ImageView.class);
        this.view7f0902bc = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.fragment.HomeListFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                homeListFragment.onFilterClicked();
            }
        });
        homeListFragment.dot = Utils.findRequiredView(view, R.id.dot, "field 'dot'");
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        HomeListFragment homeListFragment = this.target;
        if (homeListFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        homeListFragment.homelistViewLogo = null;
        homeListFragment.homelistViewMore = null;
        homeListFragment.mTabs = null;
        homeListFragment.mPager = null;
        homeListFragment.flCast = null;
        homeListFragment.mediaRouteButton = null;
        homeListFragment.childModeViewStub = null;
        homeListFragment.incognitoModeViewStub = null;
        homeListFragment.ivFilter = null;
        homeListFragment.dot = null;
        this.view7f090237.setOnClickListener(null);
        this.view7f090237 = null;
        this.view7f0902bc.setOnClickListener(null);
        this.view7f0902bc = null;
    }
}
