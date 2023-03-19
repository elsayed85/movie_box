package com.movieboxpro.android.view.activity.download;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public class DownloadingActivity_ViewBinding implements Unbinder {
    private DownloadingActivity target;
    private View view7f090065;
    private View view7f090067;
    private View view7f090068;
    private View view7f090195;

    public DownloadingActivity_ViewBinding(DownloadingActivity downloadingActivity) {
        this(downloadingActivity, downloadingActivity.getWindow().getDecorView());
    }

    public DownloadingActivity_ViewBinding(final DownloadingActivity downloadingActivity, View view) {
        this.target = downloadingActivity;
        downloadingActivity.mRecycler = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.download_recycler_normal, "field 'mRecycler'", RecyclerView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.activity_download_start, "field 'mStart' and method 'onViewClicked'");
        downloadingActivity.mStart = (TextView) Utils.castView(findRequiredView, R.id.activity_download_start, "field 'mStart'", TextView.class);
        this.view7f090068 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.download.DownloadingActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                downloadingActivity.onViewClicked(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.donwmload_cache_size, "field 'mCacheSize' and method 'onViewClicked'");
        downloadingActivity.mCacheSize = (TextView) Utils.castView(findRequiredView2, R.id.donwmload_cache_size, "field 'mCacheSize'", TextView.class);
        this.view7f090195 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.download.DownloadingActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                downloadingActivity.onViewClicked(view2);
            }
        });
        downloadingActivity.mMemory = (TextView) Utils.findRequiredViewAsType(view, R.id.activity_download_memory, "field 'mMemory'", TextView.class);
        downloadingActivity.mTabs = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.activity_download_tabs, "field 'mTabs'", LinearLayout.class);
        View findRequiredView3 = Utils.findRequiredView(view, R.id.activity_download_select, "field 'mSelect' and method 'onSelectClicked'");
        downloadingActivity.mSelect = (TextView) Utils.castView(findRequiredView3, R.id.activity_download_select, "field 'mSelect'", TextView.class);
        this.view7f090067 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.download.DownloadingActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                downloadingActivity.onSelectClicked(view2);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.activity_download_delete, "field 'mDelete' and method 'onSelectClicked'");
        downloadingActivity.mDelete = (TextView) Utils.castView(findRequiredView4, R.id.activity_download_delete, "field 'mDelete'", TextView.class);
        this.view7f090065 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.download.DownloadingActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                downloadingActivity.onSelectClicked(view2);
            }
        });
        downloadingActivity.sizeProgressBar = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.sizeProgressBar, "field 'sizeProgressBar'", ProgressBar.class);
        downloadingActivity.clTotal = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.clTotal, "field 'clTotal'", ConstraintLayout.class);
        downloadingActivity.tvTotal = (TextView) Utils.findRequiredViewAsType(view, R.id.tvTotal, "field 'tvTotal'", TextView.class);
        downloadingActivity.tvSize = (TextView) Utils.findRequiredViewAsType(view, R.id.tvSize, "field 'tvSize'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        DownloadingActivity downloadingActivity = this.target;
        if (downloadingActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        downloadingActivity.mRecycler = null;
        downloadingActivity.mStart = null;
        downloadingActivity.mCacheSize = null;
        downloadingActivity.mMemory = null;
        downloadingActivity.mTabs = null;
        downloadingActivity.mSelect = null;
        downloadingActivity.mDelete = null;
        downloadingActivity.sizeProgressBar = null;
        downloadingActivity.clTotal = null;
        downloadingActivity.tvTotal = null;
        downloadingActivity.tvSize = null;
        this.view7f090068.setOnClickListener(null);
        this.view7f090068 = null;
        this.view7f090195.setOnClickListener(null);
        this.view7f090195 = null;
        this.view7f090067.setOnClickListener(null);
        this.view7f090067 = null;
        this.view7f090065.setOnClickListener(null);
        this.view7f090065 = null;
    }
}
