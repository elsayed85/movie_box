package com.movieboxpro.android.view.fragment.movielist;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public class BottomDialogFragment_ViewBinding implements Unbinder {
    private BottomDialogFragment target;
    private View view7f0907d7;
    private View view7f0907e2;

    public BottomDialogFragment_ViewBinding(final BottomDialogFragment bottomDialogFragment, View view) {
        this.target = bottomDialogFragment;
        View findRequiredView = Utils.findRequiredView(view, R.id.tv_cancel, "field 'tvCancel' and method 'onViewClicked'");
        bottomDialogFragment.tvCancel = (TextView) Utils.castView(findRequiredView, R.id.tv_cancel, "field 'tvCancel'", TextView.class);
        this.view7f0907d7 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.fragment.movielist.BottomDialogFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                bottomDialogFragment.onViewClicked(view2);
            }
        });
        bottomDialogFragment.tvTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title, "field 'tvTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.tv_done, "field 'tvDone' and method 'onViewClicked'");
        bottomDialogFragment.tvDone = (TextView) Utils.castView(findRequiredView2, R.id.tv_done, "field 'tvDone'", TextView.class);
        this.view7f0907e2 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.fragment.movielist.BottomDialogFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                bottomDialogFragment.onViewClicked(view2);
            }
        });
        bottomDialogFragment.rlTop = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.rl_top, "field 'rlTop'", RelativeLayout.class);
        bottomDialogFragment.frameLayout = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.frame_layout, "field 'frameLayout'", FrameLayout.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        BottomDialogFragment bottomDialogFragment = this.target;
        if (bottomDialogFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        bottomDialogFragment.tvCancel = null;
        bottomDialogFragment.tvTitle = null;
        bottomDialogFragment.tvDone = null;
        bottomDialogFragment.rlTop = null;
        bottomDialogFragment.frameLayout = null;
        this.view7f0907d7.setOnClickListener(null);
        this.view7f0907d7 = null;
        this.view7f0907e2.setOnClickListener(null);
        this.view7f0907e2 = null;
    }
}
