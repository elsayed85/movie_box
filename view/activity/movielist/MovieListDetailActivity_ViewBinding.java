package com.movieboxpro.android.view.activity.movielist;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public class MovieListDetailActivity_ViewBinding implements Unbinder {
    private MovieListDetailActivity target;

    public MovieListDetailActivity_ViewBinding(MovieListDetailActivity movieListDetailActivity) {
        this(movieListDetailActivity, movieListDetailActivity.getWindow().getDecorView());
    }

    public MovieListDetailActivity_ViewBinding(MovieListDetailActivity movieListDetailActivity, View view) {
        this.target = movieListDetailActivity;
        movieListDetailActivity.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recyclerView, "field 'recyclerView'", RecyclerView.class);
        movieListDetailActivity.toolbar = (Toolbar) Utils.findRequiredViewAsType(view, R.id.toolBar, "field 'toolbar'", Toolbar.class);
        movieListDetailActivity.collapsingToolbarLayout = (CollapsingToolbarLayout) Utils.findRequiredViewAsType(view, R.id.collapsing_toolbar, "field 'collapsingToolbarLayout'", CollapsingToolbarLayout.class);
        movieListDetailActivity.ivBg = (ImageView) Utils.findRequiredViewAsType(view, R.id.ivBg, "field 'ivBg'", ImageView.class);
        movieListDetailActivity.tvName = (TextView) Utils.findRequiredViewAsType(view, R.id.tvName, "field 'tvName'", TextView.class);
        movieListDetailActivity.ivAddToList = (ImageView) Utils.findRequiredViewAsType(view, R.id.ivAddToList, "field 'ivAddToList'", ImageView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        MovieListDetailActivity movieListDetailActivity = this.target;
        if (movieListDetailActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        movieListDetailActivity.recyclerView = null;
        movieListDetailActivity.toolbar = null;
        movieListDetailActivity.collapsingToolbarLayout = null;
        movieListDetailActivity.ivBg = null;
        movieListDetailActivity.tvName = null;
        movieListDetailActivity.ivAddToList = null;
    }
}
