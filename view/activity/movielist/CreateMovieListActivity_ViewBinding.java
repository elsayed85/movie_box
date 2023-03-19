package com.movieboxpro.android.view.activity.movielist;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public class CreateMovieListActivity_ViewBinding implements Unbinder {
    private CreateMovieListActivity target;
    private View view7f0902a7;
    private View view7f090307;
    private View view7f0907e2;

    public CreateMovieListActivity_ViewBinding(CreateMovieListActivity createMovieListActivity) {
        this(createMovieListActivity, createMovieListActivity.getWindow().getDecorView());
    }

    public CreateMovieListActivity_ViewBinding(final CreateMovieListActivity createMovieListActivity, View view) {
        this.target = createMovieListActivity;
        View findRequiredView = Utils.findRequiredView(view, R.id.tv_done, "field 'tvDone' and method 'onViewClicked'");
        createMovieListActivity.tvDone = (TextView) Utils.castView(findRequiredView, R.id.tv_done, "field 'tvDone'", TextView.class);
        this.view7f0907e2 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.movielist.CreateMovieListActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                createMovieListActivity.onViewClicked(view2);
            }
        });
        createMovieListActivity.rlTop = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.rl_top, "field 'rlTop'", RelativeLayout.class);
        createMovieListActivity.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recyclerView, "field 'recyclerView'", RecyclerView.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.ivChooseImage, "field 'ivChooseImage' and method 'onViewClicked'");
        createMovieListActivity.ivChooseImage = (ImageView) Utils.castView(findRequiredView2, R.id.ivChooseImage, "field 'ivChooseImage'", ImageView.class);
        this.view7f0902a7 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.movielist.CreateMovieListActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                createMovieListActivity.onViewClicked(view2);
            }
        });
        createMovieListActivity.ivCover = (ImageView) Utils.findRequiredViewAsType(view, R.id.ivBg, "field 'ivCover'", ImageView.class);
        createMovieListActivity.llEmptyView = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.llEmptyView, "field 'llEmptyView'", LinearLayout.class);
        createMovieListActivity.tvTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tvTitle, "field 'tvTitle'", TextView.class);
        View findRequiredView3 = Utils.findRequiredView(view, R.id.iv_cancel, "method 'onViewClicked'");
        this.view7f090307 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.movielist.CreateMovieListActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                createMovieListActivity.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        CreateMovieListActivity createMovieListActivity = this.target;
        if (createMovieListActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        createMovieListActivity.tvDone = null;
        createMovieListActivity.rlTop = null;
        createMovieListActivity.recyclerView = null;
        createMovieListActivity.ivChooseImage = null;
        createMovieListActivity.ivCover = null;
        createMovieListActivity.llEmptyView = null;
        createMovieListActivity.tvTitle = null;
        this.view7f0907e2.setOnClickListener(null);
        this.view7f0907e2 = null;
        this.view7f0902a7.setOnClickListener(null);
        this.view7f0902a7 = null;
        this.view7f090307.setOnClickListener(null);
        this.view7f090307 = null;
    }
}
