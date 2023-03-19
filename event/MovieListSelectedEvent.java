package com.movieboxpro.android.event;

import com.movieboxpro.android.model.movie.MovieListModel;
/* loaded from: classes3.dex */
public class MovieListSelectedEvent {
    private MovieListModel.MovieListItem item;

    public MovieListSelectedEvent(MovieListModel.MovieListItem movieListItem) {
        this.item = movieListItem;
    }

    public MovieListModel.MovieListItem getItem() {
        return this.item;
    }

    public void setItem(MovieListModel.MovieListItem movieListItem) {
        this.item = movieListItem;
    }
}
