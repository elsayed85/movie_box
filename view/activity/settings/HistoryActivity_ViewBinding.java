package com.movieboxpro.android.view.activity.settings;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public class HistoryActivity_ViewBinding implements Unbinder {
    private HistoryActivity target;
    private View view7f0903d7;
    private View view7f0903eb;

    public HistoryActivity_ViewBinding(HistoryActivity historyActivity) {
        this(historyActivity, historyActivity.getWindow().getDecorView());
    }

    public HistoryActivity_ViewBinding(final HistoryActivity historyActivity, View view) {
        this.target = historyActivity;
        View findRequiredView = Utils.findRequiredView(view, R.id.ll_back, "field 'llBack' and method 'onViewClicked'");
        historyActivity.llBack = (LinearLayout) Utils.castView(findRequiredView, R.id.ll_back, "field 'llBack'", LinearLayout.class);
        this.view7f0903d7 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.HistoryActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                historyActivity.onViewClicked(view2);
            }
        });
        historyActivity.tvTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title, "field 'tvTitle'", TextView.class);
        historyActivity.tvRight = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_right, "field 'tvRight'", TextView.class);
        historyActivity.relativeLayout = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.relative_layout, "field 'relativeLayout'", RelativeLayout.class);
        historyActivity.frameLayout = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.frameLayout, "field 'frameLayout'", FrameLayout.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.ll_right, "method 'onViewClicked'");
        this.view7f0903eb = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.HistoryActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                historyActivity.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        HistoryActivity historyActivity = this.target;
        if (historyActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        historyActivity.llBack = null;
        historyActivity.tvTitle = null;
        historyActivity.tvRight = null;
        historyActivity.relativeLayout = null;
        historyActivity.frameLayout = null;
        this.view7f0903d7.setOnClickListener(null);
        this.view7f0903d7 = null;
        this.view7f0903eb.setOnClickListener(null);
        this.view7f0903eb = null;
    }
}
