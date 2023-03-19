package com.movieboxpro.android.view.activity.choose.impl;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public final class ChooseActivity_ViewBinding implements Unbinder {
    private ChooseActivity target;
    private View view7f090109;
    private View view7f09010a;
    private View view7f0901f5;
    private View view7f0902b7;

    public ChooseActivity_ViewBinding(ChooseActivity chooseActivity) {
        this(chooseActivity, chooseActivity.getWindow().getDecorView());
    }

    public ChooseActivity_ViewBinding(final ChooseActivity chooseActivity, View view) {
        this.target = chooseActivity;
        chooseActivity.mRecycler = (RecyclerView) Utils.findOptionalViewAsType(view, R.id.recycler_normal, "field 'mRecycler'", RecyclerView.class);
        chooseActivity.mBackRound = (LinearLayout) Utils.findOptionalViewAsType(view, R.id.choose_backround, "field 'mBackRound'", LinearLayout.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.choose_close, "method 'onViewClicked'");
        chooseActivity.mClose = (TextView) Utils.castView(findRequiredView, R.id.choose_close, "field 'mClose'", TextView.class);
        this.view7f090109 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.choose.impl.ChooseActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                chooseActivity.onViewClicked(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.choose_download, "method 'onViewClicked'");
        chooseActivity.mDowload = (TextView) Utils.castView(findRequiredView2, R.id.choose_download, "field 'mDowload'", TextView.class);
        this.view7f09010a = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.choose.impl.ChooseActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                chooseActivity.onViewClicked(view2);
            }
        });
        chooseActivity.mSize = (TextView) Utils.findOptionalViewAsType(view, R.id.choose_size, "field 'mSize'", TextView.class);
        chooseActivity.swipeRefreshLayout = (SwipeRefreshLayout) Utils.findOptionalViewAsType(view, R.id.swipeRefreshLayout, "field 'swipeRefreshLayout'", SwipeRefreshLayout.class);
        chooseActivity.ivMoreDefinition = (ImageView) Utils.findOptionalViewAsType(view, R.id.ivMoreDefinition, "field 'ivMoreDefinition'", ImageView.class);
        View findRequiredView3 = Utils.findRequiredView(view, R.id.flShowOrg, "method 'onViewClicked'");
        chooseActivity.flShowOrg = (FrameLayout) Utils.castView(findRequiredView3, R.id.flShowOrg, "field 'flShowOrg'", FrameLayout.class);
        this.view7f0901f5 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.choose.impl.ChooseActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                chooseActivity.onViewClicked(view2);
            }
        });
        chooseActivity.tvPath = (TextView) Utils.findOptionalViewAsType(view, R.id.tvPath, "field 'tvPath'", TextView.class);
        View findRequiredView4 = Utils.findRequiredView(view, R.id.ivEdit, "method 'onViewClicked'");
        chooseActivity.ivEdit = (AppCompatImageView) Utils.castView(findRequiredView4, R.id.ivEdit, "field 'ivEdit'", AppCompatImageView.class);
        this.view7f0902b7 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.choose.impl.ChooseActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                chooseActivity.onViewClicked(view2);
            }
        });
        chooseActivity.llPath = (LinearLayout) Utils.findOptionalViewAsType(view, R.id.llPath, "field 'llPath'", LinearLayout.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        ChooseActivity chooseActivity = this.target;
        if (chooseActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        chooseActivity.mRecycler = null;
        chooseActivity.mBackRound = null;
        chooseActivity.mClose = null;
        chooseActivity.mDowload = null;
        chooseActivity.mSize = null;
        chooseActivity.swipeRefreshLayout = null;
        chooseActivity.ivMoreDefinition = null;
        chooseActivity.flShowOrg = null;
        chooseActivity.tvPath = null;
        chooseActivity.ivEdit = null;
        chooseActivity.llPath = null;
        this.view7f090109.setOnClickListener(null);
        this.view7f090109 = null;
        this.view7f09010a.setOnClickListener(null);
        this.view7f09010a = null;
        this.view7f0901f5.setOnClickListener(null);
        this.view7f0901f5 = null;
        this.view7f0902b7.setOnClickListener(null);
        this.view7f0902b7 = null;
    }
}
