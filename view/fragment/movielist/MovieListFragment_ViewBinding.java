package com.movieboxpro.android.view.fragment.movielist;

import android.view.View;
import android.widget.RelativeLayout;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.movieboxpro.android.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
/* loaded from: classes3.dex */
public class MovieListFragment_ViewBinding implements Unbinder {
    private MovieListFragment target;
    private View view7f0902fd;

    public MovieListFragment_ViewBinding(final MovieListFragment movieListFragment, View view) {
        this.target = movieListFragment;
        movieListFragment.tabLayout = (SmartTabLayout) Utils.findRequiredViewAsType(view, R.id.tabLayout, "field 'tabLayout'", SmartTabLayout.class);
        movieListFragment.viewPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.viewPager, "field 'viewPager'", ViewPager.class);
        movieListFragment.rlTitle = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.homelist_view_title, "field 'rlTitle'", RelativeLayout.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.iv_add, "method 'onAddMovieList'");
        this.view7f0902fd = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.fragment.movielist.MovieListFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                movieListFragment.onAddMovieList();
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        MovieListFragment movieListFragment = this.target;
        if (movieListFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        movieListFragment.tabLayout = null;
        movieListFragment.viewPager = null;
        movieListFragment.rlTitle = null;
        this.view7f0902fd.setOnClickListener(null);
        this.view7f0902fd = null;
    }
}
