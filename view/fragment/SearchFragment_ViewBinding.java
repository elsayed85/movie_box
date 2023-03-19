package com.movieboxpro.android.view.fragment;

import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public class SearchFragment_ViewBinding implements Unbinder {
    private SearchFragment target;
    private View view7f0905bf;

    public SearchFragment_ViewBinding(final SearchFragment searchFragment, View view) {
        this.target = searchFragment;
        searchFragment.mSearchEdit = (EditText) Utils.findRequiredViewAsType(view, R.id.search_bar_edit, "field 'mSearchEdit'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.search_bar_clear, "field 'mSearchClear' and method 'clearKeyWord'");
        searchFragment.mSearchClear = (ImageView) Utils.castView(findRequiredView, R.id.search_bar_clear, "field 'mSearchClear'", ImageView.class);
        this.view7f0905bf = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.fragment.SearchFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                searchFragment.clearKeyWord(view2);
            }
        });
        searchFragment.mRecycler = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.activity_search_recycler, "field 'mRecycler'", RecyclerView.class);
        searchFragment.mFrame = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.activity_search_frame, "field 'mFrame'", FrameLayout.class);
        searchFragment.swipeRefreshLayout = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.swipeRefreshLayout, "field 'swipeRefreshLayout'", SwipeRefreshLayout.class);
        searchFragment.tvCancel = (TextView) Utils.findRequiredViewAsType(view, R.id.tvCancel, "field 'tvCancel'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SearchFragment searchFragment = this.target;
        if (searchFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        searchFragment.mSearchEdit = null;
        searchFragment.mSearchClear = null;
        searchFragment.mRecycler = null;
        searchFragment.mFrame = null;
        searchFragment.swipeRefreshLayout = null;
        searchFragment.tvCancel = null;
        this.view7f0905bf.setOnClickListener(null);
        this.view7f0905bf = null;
    }
}
