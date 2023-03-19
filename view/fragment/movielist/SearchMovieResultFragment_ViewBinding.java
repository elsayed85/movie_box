package com.movieboxpro.android.view.fragment.movielist;

import android.view.View;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.movieboxpro.android.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
/* loaded from: classes3.dex */
public class SearchMovieResultFragment_ViewBinding implements Unbinder {
    private SearchMovieResultFragment target;

    public SearchMovieResultFragment_ViewBinding(SearchMovieResultFragment searchMovieResultFragment, View view) {
        this.target = searchMovieResultFragment;
        searchMovieResultFragment.searchResultTab = (SmartTabLayout) Utils.findRequiredViewAsType(view, R.id.search_result_tab, "field 'searchResultTab'", SmartTabLayout.class);
        searchMovieResultFragment.searchResultPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.search_result_pager, "field 'searchResultPager'", ViewPager.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SearchMovieResultFragment searchMovieResultFragment = this.target;
        if (searchMovieResultFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        searchMovieResultFragment.searchResultTab = null;
        searchMovieResultFragment.searchResultPager = null;
    }
}
