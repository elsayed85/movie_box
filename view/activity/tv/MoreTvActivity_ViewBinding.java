package com.movieboxpro.android.view.activity.tv;

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
public class MoreTvActivity_ViewBinding implements Unbinder {
    private MoreTvActivity target;
    private View view7f090559;

    public MoreTvActivity_ViewBinding(MoreTvActivity moreTvActivity) {
        this(moreTvActivity, moreTvActivity.getWindow().getDecorView());
    }

    public MoreTvActivity_ViewBinding(final MoreTvActivity moreTvActivity, View view) {
        this.target = moreTvActivity;
        moreTvActivity.mEmptyView = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.recycler_empty, "field 'mEmptyView'", ConstraintLayout.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.recycler_empty_text, "field 'mEmptyText' and method 'onEmptyClick'");
        moreTvActivity.mEmptyText = (TextView) Utils.castView(findRequiredView, R.id.recycler_empty_text, "field 'mEmptyText'", TextView.class);
        this.view7f090559 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.tv.MoreTvActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                moreTvActivity.onEmptyClick();
            }
        });
        moreTvActivity.mTabSegment = (TabLayout) Utils.findRequiredViewAsType(view, R.id.tabSegment, "field 'mTabSegment'", TabLayout.class);
        moreTvActivity.mTabSegment1 = (TabLayout) Utils.findRequiredViewAsType(view, R.id.tabSegment1, "field 'mTabSegment1'", TabLayout.class);
        moreTvActivity.mTabSegment2 = (TabLayout) Utils.findRequiredViewAsType(view, R.id.tabSegment2, "field 'mTabSegment2'", TabLayout.class);
        moreTvActivity.mTabSegment3 = (TabLayout) Utils.findRequiredViewAsType(view, R.id.tabSegment3, "field 'mTabSegment3'", TabLayout.class);
        moreTvActivity.mRecycler = (MyRecyclerView) Utils.findRequiredViewAsType(view, R.id.contentViewPager, "field 'mRecycler'", MyRecyclerView.class);
        moreTvActivity.mAppBarLayout = (AppBarLayout) Utils.findRequiredViewAsType(view, R.id.barlayout, "field 'mAppBarLayout'", AppBarLayout.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        MoreTvActivity moreTvActivity = this.target;
        if (moreTvActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        moreTvActivity.mEmptyView = null;
        moreTvActivity.mEmptyText = null;
        moreTvActivity.mTabSegment = null;
        moreTvActivity.mTabSegment1 = null;
        moreTvActivity.mTabSegment2 = null;
        moreTvActivity.mTabSegment3 = null;
        moreTvActivity.mRecycler = null;
        moreTvActivity.mAppBarLayout = null;
        this.view7f090559.setOnClickListener(null);
        this.view7f090559 = null;
    }
}
