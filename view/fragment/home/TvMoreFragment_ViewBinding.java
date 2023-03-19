package com.movieboxpro.android.view.fragment.home;

import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.movieboxpro.android.R;
import com.movieboxpro.android.view.widget.MyRecyclerView;
/* loaded from: classes3.dex */
public class TvMoreFragment_ViewBinding implements Unbinder {
    private TvMoreFragment target;
    private View view7f090559;

    public TvMoreFragment_ViewBinding(final TvMoreFragment tvMoreFragment, View view) {
        this.target = tvMoreFragment;
        tvMoreFragment.mEmptyView = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.recycler_empty, "field 'mEmptyView'", ConstraintLayout.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.recycler_empty_text, "field 'mEmptyText' and method 'onEmptyClick'");
        tvMoreFragment.mEmptyText = (TextView) Utils.castView(findRequiredView, R.id.recycler_empty_text, "field 'mEmptyText'", TextView.class);
        this.view7f090559 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.fragment.home.TvMoreFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                tvMoreFragment.onEmptyClick();
            }
        });
        tvMoreFragment.mTabSegment = (TabLayout) Utils.findRequiredViewAsType(view, R.id.tabSegment, "field 'mTabSegment'", TabLayout.class);
        tvMoreFragment.mTabSegment1 = (TabLayout) Utils.findRequiredViewAsType(view, R.id.tabSegment1, "field 'mTabSegment1'", TabLayout.class);
        tvMoreFragment.mTabSegment2 = (TabLayout) Utils.findRequiredViewAsType(view, R.id.tabSegment2, "field 'mTabSegment2'", TabLayout.class);
        tvMoreFragment.mTabSegment3 = (TabLayout) Utils.findRequiredViewAsType(view, R.id.tabSegment3, "field 'mTabSegment3'", TabLayout.class);
        tvMoreFragment.mRecycler = (MyRecyclerView) Utils.findRequiredViewAsType(view, R.id.contentViewPager, "field 'mRecycler'", MyRecyclerView.class);
        tvMoreFragment.mAppBarLayout = (AppBarLayout) Utils.findRequiredViewAsType(view, R.id.barlayout, "field 'mAppBarLayout'", AppBarLayout.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        TvMoreFragment tvMoreFragment = this.target;
        if (tvMoreFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        tvMoreFragment.mEmptyView = null;
        tvMoreFragment.mEmptyText = null;
        tvMoreFragment.mTabSegment = null;
        tvMoreFragment.mTabSegment1 = null;
        tvMoreFragment.mTabSegment2 = null;
        tvMoreFragment.mTabSegment3 = null;
        tvMoreFragment.mRecycler = null;
        tvMoreFragment.mAppBarLayout = null;
        this.view7f090559.setOnClickListener(null);
        this.view7f090559 = null;
    }
}
