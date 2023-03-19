package com.movieboxpro.android.view.activity.download;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.movieboxpro.android.R;
import com.movieboxpro.android.view.widget.MyRecyclerView;
/* loaded from: classes3.dex */
public class TvDownlaodActivity_ViewBinding implements Unbinder {
    private TvDownlaodActivity target;
    private View view7f09006e;
    private View view7f090210;
    private View view7f090211;

    public TvDownlaodActivity_ViewBinding(TvDownlaodActivity tvDownlaodActivity) {
        this(tvDownlaodActivity, tvDownlaodActivity.getWindow().getDecorView());
    }

    public TvDownlaodActivity_ViewBinding(final TvDownlaodActivity tvDownlaodActivity, View view) {
        this.target = tvDownlaodActivity;
        tvDownlaodActivity.mRecycler = (MyRecyclerView) Utils.findRequiredViewAsType(view, R.id.activity_tvdownload_recycler, "field 'mRecycler'", MyRecyclerView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.activity_tvdownload_more, "field 'mMore' and method 'onClicked'");
        tvDownlaodActivity.mMore = (TextView) Utils.castView(findRequiredView, R.id.activity_tvdownload_more, "field 'mMore'", TextView.class);
        this.view7f09006e = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.download.TvDownlaodActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                tvDownlaodActivity.onClicked(view2);
            }
        });
        tvDownlaodActivity.mEmptyText = (TextView) Utils.findRequiredViewAsType(view, R.id.text_empty, "field 'mEmptyText'", TextView.class);
        tvDownlaodActivity.mTabs = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.fragment_download_tabs, "field 'mTabs'", LinearLayout.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.fragmen_download_select, "field 'mSelect' and method 'onViewClicked'");
        tvDownlaodActivity.mSelect = (TextView) Utils.castView(findRequiredView2, R.id.fragmen_download_select, "field 'mSelect'", TextView.class);
        this.view7f090211 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.download.TvDownlaodActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                tvDownlaodActivity.onViewClicked(view2);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.fragmen_download_delete, "field 'mDelete' and method 'onViewClicked'");
        tvDownlaodActivity.mDelete = (TextView) Utils.castView(findRequiredView3, R.id.fragmen_download_delete, "field 'mDelete'", TextView.class);
        this.view7f090210 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.download.TvDownlaodActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                tvDownlaodActivity.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        TvDownlaodActivity tvDownlaodActivity = this.target;
        if (tvDownlaodActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        tvDownlaodActivity.mRecycler = null;
        tvDownlaodActivity.mMore = null;
        tvDownlaodActivity.mEmptyText = null;
        tvDownlaodActivity.mTabs = null;
        tvDownlaodActivity.mSelect = null;
        tvDownlaodActivity.mDelete = null;
        this.view7f09006e.setOnClickListener(null);
        this.view7f09006e = null;
        this.view7f090211.setOnClickListener(null);
        this.view7f090211 = null;
        this.view7f090210.setOnClickListener(null);
        this.view7f090210 = null;
    }
}
