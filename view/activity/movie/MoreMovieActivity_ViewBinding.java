package com.movieboxpro.android.view.activity.movie;

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
public class MoreMovieActivity_ViewBinding implements Unbinder {
    private MoreMovieActivity target;
    private View view7f090559;

    public MoreMovieActivity_ViewBinding(MoreMovieActivity moreMovieActivity) {
        this(moreMovieActivity, moreMovieActivity.getWindow().getDecorView());
    }

    public MoreMovieActivity_ViewBinding(final MoreMovieActivity moreMovieActivity, View view) {
        this.target = moreMovieActivity;
        moreMovieActivity.mEmptyView = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.recycler_empty, "field 'mEmptyView'", ConstraintLayout.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.recycler_empty_text, "field 'mEmptyText' and method 'onEmptyClick'");
        moreMovieActivity.mEmptyText = (TextView) Utils.castView(findRequiredView, R.id.recycler_empty_text, "field 'mEmptyText'", TextView.class);
        this.view7f090559 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.movie.MoreMovieActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                moreMovieActivity.onEmptyClick();
            }
        });
        moreMovieActivity.mTabSegment = (TabLayout) Utils.findRequiredViewAsType(view, R.id.tabSegment, "field 'mTabSegment'", TabLayout.class);
        moreMovieActivity.mTabSegment1 = (TabLayout) Utils.findRequiredViewAsType(view, R.id.tabSegment1, "field 'mTabSegment1'", TabLayout.class);
        moreMovieActivity.mTabSegment2 = (TabLayout) Utils.findRequiredViewAsType(view, R.id.tabSegment2, "field 'mTabSegment2'", TabLayout.class);
        moreMovieActivity.mTabSegment3 = (TabLayout) Utils.findRequiredViewAsType(view, R.id.tabSegment3, "field 'mTabSegment3'", TabLayout.class);
        moreMovieActivity.mRecycler = (MyRecyclerView) Utils.findRequiredViewAsType(view, R.id.contentViewPager, "field 'mRecycler'", MyRecyclerView.class);
        moreMovieActivity.mAppBarLayout = (AppBarLayout) Utils.findRequiredViewAsType(view, R.id.barlayout, "field 'mAppBarLayout'", AppBarLayout.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        MoreMovieActivity moreMovieActivity = this.target;
        if (moreMovieActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        moreMovieActivity.mEmptyView = null;
        moreMovieActivity.mEmptyText = null;
        moreMovieActivity.mTabSegment = null;
        moreMovieActivity.mTabSegment1 = null;
        moreMovieActivity.mTabSegment2 = null;
        moreMovieActivity.mTabSegment3 = null;
        moreMovieActivity.mRecycler = null;
        moreMovieActivity.mAppBarLayout = null;
        this.view7f090559.setOnClickListener(null);
        this.view7f090559 = null;
    }
}
