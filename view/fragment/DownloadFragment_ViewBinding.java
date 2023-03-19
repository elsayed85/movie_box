package com.movieboxpro.android.view.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.movieboxpro.android.R;
import com.movieboxpro.android.view.widget.MyRecyclerView;
/* loaded from: classes3.dex */
public class DownloadFragment_ViewBinding implements Unbinder {
    private DownloadFragment target;
    private View view7f090210;
    private View view7f090211;
    private View view7f0903c5;
    private View view7f090721;

    public DownloadFragment_ViewBinding(final DownloadFragment downloadFragment, View view) {
        this.target = downloadFragment;
        downloadFragment.mRecycler = (MyRecyclerView) Utils.findRequiredViewAsType(view, R.id.fragment_download_recycler, "field 'mRecycler'", MyRecyclerView.class);
        downloadFragment.swipeRefreshLayout = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.swipeRefreshLayout, "field 'swipeRefreshLayout'", SwipeRefreshLayout.class);
        downloadFragment.mLlEmptyView = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_empty_view, "field 'mLlEmptyView'", LinearLayout.class);
        downloadFragment.mCacheText = (TextView) Utils.findRequiredViewAsType(view, R.id.fragment_download_memory, "field 'mCacheText'", TextView.class);
        downloadFragment.mTabs = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.fragment_download_tabs, "field 'mTabs'", LinearLayout.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.fragmen_download_select, "field 'mSelect' and method 'onViewClicked'");
        downloadFragment.mSelect = (TextView) Utils.castView(findRequiredView, R.id.fragmen_download_select, "field 'mSelect'", TextView.class);
        this.view7f090211 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                downloadFragment.onViewClicked(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.fragmen_download_delete, "field 'mDelete' and method 'onViewClicked'");
        downloadFragment.mDelete = (TextView) Utils.castView(findRequiredView2, R.id.fragmen_download_delete, "field 'mDelete'", TextView.class);
        this.view7f090210 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                downloadFragment.onViewClicked(view2);
            }
        });
        downloadFragment.ivEdit = (ImageView) Utils.findRequiredViewAsType(view, R.id.ivEdit, "field 'ivEdit'", ImageView.class);
        downloadFragment.sizeProgressBar = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.sizeProgressBar, "field 'sizeProgressBar'", ProgressBar.class);
        View findRequiredView3 = Utils.findRequiredView(view, R.id.tvExport, "field 'tvExport' and method 'onViewClicked'");
        downloadFragment.tvExport = (TextView) Utils.castView(findRequiredView3, R.id.tvExport, "field 'tvExport'", TextView.class);
        this.view7f090721 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                downloadFragment.onViewClicked(view2);
            }
        });
        downloadFragment.tvSmartDownload = (TextView) Utils.findRequiredViewAsType(view, R.id.tvSmartDownload, "field 'tvSmartDownload'", TextView.class);
        View findRequiredView4 = Utils.findRequiredView(view, R.id.llSmartDownload, "method 'onViewClicked'");
        this.view7f0903c5 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                downloadFragment.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        DownloadFragment downloadFragment = this.target;
        if (downloadFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        downloadFragment.mRecycler = null;
        downloadFragment.swipeRefreshLayout = null;
        downloadFragment.mLlEmptyView = null;
        downloadFragment.mCacheText = null;
        downloadFragment.mTabs = null;
        downloadFragment.mSelect = null;
        downloadFragment.mDelete = null;
        downloadFragment.ivEdit = null;
        downloadFragment.sizeProgressBar = null;
        downloadFragment.tvExport = null;
        downloadFragment.tvSmartDownload = null;
        this.view7f090211.setOnClickListener(null);
        this.view7f090211 = null;
        this.view7f090210.setOnClickListener(null);
        this.view7f090210 = null;
        this.view7f090721.setOnClickListener(null);
        this.view7f090721 = null;
        this.view7f0903c5.setOnClickListener(null);
        this.view7f0903c5 = null;
    }
}
