package com.movieboxpro.android.view.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public class AddVideoToFavoriteFragment_ViewBinding implements Unbinder {
    private AddVideoToFavoriteFragment target;
    private View view7f090377;
    private View view7f090378;
    private View view7f090394;

    public AddVideoToFavoriteFragment_ViewBinding(final AddVideoToFavoriteFragment addVideoToFavoriteFragment, View view) {
        this.target = addVideoToFavoriteFragment;
        addVideoToFavoriteFragment.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recyclerView, "field 'recyclerView'", RecyclerView.class);
        addVideoToFavoriteFragment.tvWatchedAdded = (TextView) Utils.findRequiredViewAsType(view, R.id.tvWatchedAdded, "field 'tvWatchedAdded'", TextView.class);
        addVideoToFavoriteFragment.tvWaitingAdded = (TextView) Utils.findRequiredViewAsType(view, R.id.tvWaitingAdded, "field 'tvWaitingAdded'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.llCreate, "field 'llCreate' and method 'onViewClicked'");
        addVideoToFavoriteFragment.llCreate = (LinearLayout) Utils.castView(findRequiredView, R.id.llCreate, "field 'llCreate'", LinearLayout.class);
        this.view7f090394 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.fragment.AddVideoToFavoriteFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                addVideoToFavoriteFragment.onViewClicked(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.llAddWaiting, "method 'onViewClicked'");
        this.view7f090377 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.fragment.AddVideoToFavoriteFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                addVideoToFavoriteFragment.onViewClicked(view2);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.llAddWatched, "method 'onViewClicked'");
        this.view7f090378 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.fragment.AddVideoToFavoriteFragment_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                addVideoToFavoriteFragment.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        AddVideoToFavoriteFragment addVideoToFavoriteFragment = this.target;
        if (addVideoToFavoriteFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        addVideoToFavoriteFragment.recyclerView = null;
        addVideoToFavoriteFragment.tvWatchedAdded = null;
        addVideoToFavoriteFragment.tvWaitingAdded = null;
        addVideoToFavoriteFragment.llCreate = null;
        this.view7f090394.setOnClickListener(null);
        this.view7f090394 = null;
        this.view7f090377.setOnClickListener(null);
        this.view7f090377 = null;
        this.view7f090378.setOnClickListener(null);
        this.view7f090378 = null;
    }
}
