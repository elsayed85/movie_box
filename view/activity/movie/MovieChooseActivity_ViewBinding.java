package com.movieboxpro.android.view.activity.movie;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public class MovieChooseActivity_ViewBinding implements Unbinder {
    private MovieChooseActivity target;
    private View view7f090109;
    private View view7f09010a;

    public MovieChooseActivity_ViewBinding(MovieChooseActivity movieChooseActivity) {
        this(movieChooseActivity, movieChooseActivity.getWindow().getDecorView());
    }

    public MovieChooseActivity_ViewBinding(final MovieChooseActivity movieChooseActivity, View view) {
        this.target = movieChooseActivity;
        movieChooseActivity.mRecycler = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_normal, "field 'mRecycler'", RecyclerView.class);
        movieChooseActivity.mBackRound = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.choose_backround, "field 'mBackRound'", RelativeLayout.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.choose_close, "field 'mClose' and method 'onViewClicked'");
        movieChooseActivity.mClose = (TextView) Utils.castView(findRequiredView, R.id.choose_close, "field 'mClose'", TextView.class);
        this.view7f090109 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.movie.MovieChooseActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                movieChooseActivity.onViewClicked(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.choose_download, "field 'mDowload' and method 'onViewClicked'");
        movieChooseActivity.mDowload = (TextView) Utils.castView(findRequiredView2, R.id.choose_download, "field 'mDowload'", TextView.class);
        this.view7f09010a = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.movie.MovieChooseActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                movieChooseActivity.onViewClicked(view2);
            }
        });
        movieChooseActivity.mSize = (TextView) Utils.findRequiredViewAsType(view, R.id.choose_size, "field 'mSize'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        MovieChooseActivity movieChooseActivity = this.target;
        if (movieChooseActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        movieChooseActivity.mRecycler = null;
        movieChooseActivity.mBackRound = null;
        movieChooseActivity.mClose = null;
        movieChooseActivity.mDowload = null;
        movieChooseActivity.mSize = null;
        this.view7f090109.setOnClickListener(null);
        this.view7f090109 = null;
        this.view7f09010a.setOnClickListener(null);
        this.view7f09010a = null;
    }
}
