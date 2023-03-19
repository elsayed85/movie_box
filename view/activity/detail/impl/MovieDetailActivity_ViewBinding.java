package com.movieboxpro.android.view.activity.detail.impl;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.lottie.LottieAnimationView;
import com.movieboxpro.android.R;
import com.zhy.view.flowlayout.TagFlowLayout;
/* loaded from: classes3.dex */
public class MovieDetailActivity_ViewBinding implements Unbinder {
    private MovieDetailActivity target;
    private View view7f090160;
    private View view7f090164;
    private View view7f090169;
    private View view7f09016c;
    private View view7f09016e;
    private View view7f090173;
    private View view7f0902ac;
    private View view7f0902bb;
    private View view7f0902ca;
    private View view7f0902eb;
    private View view7f0902f3;
    private View view7f09031a;
    private View view7f090376;
    private View view7f090397;
    private View view7f0903a6;
    private View view7f090730;
    private View view7f0907de;

    public MovieDetailActivity_ViewBinding(MovieDetailActivity movieDetailActivity) {
        this(movieDetailActivity, movieDetailActivity.getWindow().getDecorView());
    }

    public MovieDetailActivity_ViewBinding(final MovieDetailActivity movieDetailActivity, View view) {
        this.target = movieDetailActivity;
        movieDetailActivity.detailMovieScroll = (NestedScrollView) Utils.findRequiredViewAsType(view, R.id.detail_movie_avatar_backrounds, "field 'detailMovieScroll'", NestedScrollView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.detail_movie_avatar, "field 'detailMovieAvatar' and method 'onViewClicked'");
        movieDetailActivity.detailMovieAvatar = (ImageView) Utils.castView(findRequiredView, R.id.detail_movie_avatar, "field 'detailMovieAvatar'", ImageView.class);
        this.view7f090160 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                movieDetailActivity.onViewClicked(view2);
            }
        });
        movieDetailActivity.detailMovieAvatarBack = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.detail_movie_avatar_backround, "field 'detailMovieAvatarBack'", ConstraintLayout.class);
        movieDetailActivity.detailMoviePlayBtn = (TextView) Utils.findRequiredViewAsType(view, R.id.detail_movie_play_btn, "field 'detailMoviePlayBtn'", TextView.class);
        movieDetailActivity.detailMoviePlayPGS = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.detail_movie_play_progress, "field 'detailMoviePlayPGS'", ProgressBar.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.detail_movie_play, "field 'detailMoviePlay' and method 'onViewClicked'");
        movieDetailActivity.detailMoviePlay = (RelativeLayout) Utils.castView(findRequiredView2, R.id.detail_movie_play, "field 'detailMoviePlay'", RelativeLayout.class);
        this.view7f09016e = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                movieDetailActivity.onViewClicked(view2);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.detail_movie_language, "field 'detailMovieLanguage' and method 'onViewClicked'");
        movieDetailActivity.detailMovieLanguage = (TextView) Utils.castView(findRequiredView3, R.id.detail_movie_language, "field 'detailMovieLanguage'", TextView.class);
        this.view7f09016c = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                movieDetailActivity.onViewClicked(view2);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.detail_movie_download, "field 'detailMovieDownload' and method 'onViewClicked'");
        movieDetailActivity.detailMovieDownload = (LottieAnimationView) Utils.castView(findRequiredView4, R.id.detail_movie_download, "field 'detailMovieDownload'", LottieAnimationView.class);
        this.view7f090169 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                movieDetailActivity.onViewClicked(view2);
            }
        });
        movieDetailActivity.mMovieTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.movie_titlebar_title, "field 'mMovieTitle'", TextView.class);
        movieDetailActivity.mMovieBack = (ImageView) Utils.findRequiredViewAsType(view, R.id.movie_titlebar_left_icon, "field 'mMovieBack'", ImageView.class);
        movieDetailActivity.mRecycler = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.detail_movie_recycler, "field 'mRecycler'", RecyclerView.class);
        movieDetailActivity.detailMovieController = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.detail_movie_controller, "field 'detailMovieController'", LinearLayout.class);
        movieDetailActivity.detailMovieAvatarShadow = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.detail_movie_avatar_shadow, "field 'detailMovieAvatarShadow'", ConstraintLayout.class);
        movieDetailActivity.detailMovieRelated = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.detail_movie_related, "field 'detailMovieRelated'", RelativeLayout.class);
        movieDetailActivity.detailMovieName = (TextView) Utils.findRequiredViewAsType(view, R.id.detail_movie_name, "field 'detailMovieName'", TextView.class);
        movieDetailActivity.detailMovieDesc = (ImageView) Utils.findRequiredViewAsType(view, R.id.detail_movie_desc, "field 'detailMovieDesc'", ImageView.class);
        movieDetailActivity.detailMovieAudio = (TagFlowLayout) Utils.findRequiredViewAsType(view, R.id.detail_movie_audio, "field 'detailMovieAudio'", TagFlowLayout.class);
        View findRequiredView5 = Utils.findRequiredView(view, R.id.detail_movie_span, "field 'detailMovieSpan' and method 'onViewClicked'");
        movieDetailActivity.detailMovieSpan = (LinearLayout) Utils.castView(findRequiredView5, R.id.detail_movie_span, "field 'detailMovieSpan'", LinearLayout.class);
        this.view7f090173 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                movieDetailActivity.onViewClicked(view2);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.tv_desc, "field 'mTvDesc' and method 'onViewClicked'");
        movieDetailActivity.mTvDesc = (TextView) Utils.castView(findRequiredView6, R.id.tv_desc, "field 'mTvDesc'", TextView.class);
        this.view7f0907de = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                movieDetailActivity.onViewClicked(view2);
            }
        });
        movieDetailActivity.mTvDirector = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_director, "field 'mTvDirector'", TextView.class);
        movieDetailActivity.mTvCast = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_cast, "field 'mTvCast'", TextView.class);
        movieDetailActivity.mTvCategory = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_category, "field 'mTvCategory'", TextView.class);
        movieDetailActivity.llHidePart = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_hide_part, "field 'llHidePart'", LinearLayout.class);
        View findRequiredView7 = Utils.findRequiredView(view, R.id.iv_imdb, "field 'mIvImdb' and method 'onViewClicked'");
        movieDetailActivity.mIvImdb = (ImageView) Utils.castView(findRequiredView7, R.id.iv_imdb, "field 'mIvImdb'", ImageView.class);
        this.view7f09031a = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                movieDetailActivity.onViewClicked(view2);
            }
        });
        movieDetailActivity.mIvArrow = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_arrow, "field 'mIvArrow'", ImageView.class);
        movieDetailActivity.relatedMovieListLinearLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_related_movie_list, "field 'relatedMovieListLinearLayout'", LinearLayout.class);
        movieDetailActivity.relatedMovieListRecycler = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.related_movie_list_recycler, "field 'relatedMovieListRecycler'", RecyclerView.class);
        movieDetailActivity.rvActors = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rvActors, "field 'rvActors'", RecyclerView.class);
        movieDetailActivity.tvInformation = (TextView) Utils.findRequiredViewAsType(view, R.id.tvInformation, "field 'tvInformation'", TextView.class);
        View findRequiredView8 = Utils.findRequiredView(view, R.id.ivStar, "field 'ivStar' and method 'onRatingClicked'");
        movieDetailActivity.ivStar = (ImageView) Utils.castView(findRequiredView8, R.id.ivStar, "field 'ivStar'", ImageView.class);
        this.view7f0902eb = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity_ViewBinding.8
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                movieDetailActivity.onRatingClicked(view2);
            }
        });
        View findRequiredView9 = Utils.findRequiredView(view, R.id.tvImdbRating, "field 'tvImdbRating' and method 'onRatingClicked'");
        movieDetailActivity.tvImdbRating = (TextView) Utils.castView(findRequiredView9, R.id.tvImdbRating, "field 'tvImdbRating'", TextView.class);
        this.view7f090730 = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity_ViewBinding.9
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                movieDetailActivity.onRatingClicked(view2);
            }
        });
        movieDetailActivity.rlTitleBar = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.rlTitleBar, "field 'rlTitleBar'", RelativeLayout.class);
        movieDetailActivity.tvReviewNum = (TextView) Utils.findRequiredViewAsType(view, R.id.tvReviewNum, "field 'tvReviewNum'", TextView.class);
        View findRequiredView10 = Utils.findRequiredView(view, R.id.detail_movie_comments, "field 'llComments' and method 'onViewClicked'");
        movieDetailActivity.llComments = (LinearLayout) Utils.castView(findRequiredView10, R.id.detail_movie_comments, "field 'llComments'", LinearLayout.class);
        this.view7f090164 = findRequiredView10;
        findRequiredView10.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity_ViewBinding.10
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                movieDetailActivity.onViewClicked(view2);
            }
        });
        movieDetailActivity.rvTrailer = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rvTrailer, "field 'rvTrailer'", RecyclerView.class);
        movieDetailActivity.tvTrailer = (TextView) Utils.findRequiredViewAsType(view, R.id.tvTrailer, "field 'tvTrailer'", TextView.class);
        movieDetailActivity.tvRelated = (TextView) Utils.findRequiredViewAsType(view, R.id.tvRelated, "field 'tvRelated'", TextView.class);
        View findRequiredView11 = Utils.findRequiredView(view, R.id.ivCollect, "field 'ivCollect' and method 'onViewClicked'");
        movieDetailActivity.ivCollect = (ImageView) Utils.castView(findRequiredView11, R.id.ivCollect, "field 'ivCollect'", ImageView.class);
        this.view7f0902ac = findRequiredView11;
        findRequiredView11.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity_ViewBinding.11
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                movieDetailActivity.onViewClicked(view2);
            }
        });
        movieDetailActivity.ivLike = (ImageView) Utils.findRequiredViewAsType(view, R.id.ivLike, "field 'ivLike'", ImageView.class);
        movieDetailActivity.ivDislike = (ImageView) Utils.findRequiredViewAsType(view, R.id.ivDislike, "field 'ivDislike'", ImageView.class);
        movieDetailActivity.tvLike = (TextView) Utils.findRequiredViewAsType(view, R.id.tvLike, "field 'tvLike'", TextView.class);
        movieDetailActivity.tvDislike = (TextView) Utils.findRequiredViewAsType(view, R.id.tvDislike, "field 'tvDislike'", TextView.class);
        View findRequiredView12 = Utils.findRequiredView(view, R.id.ivFavorite, "field 'ivFavorite' and method 'onViewClicked'");
        movieDetailActivity.ivFavorite = (ImageView) Utils.castView(findRequiredView12, R.id.ivFavorite, "field 'ivFavorite'", ImageView.class);
        this.view7f0902bb = findRequiredView12;
        findRequiredView12.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity_ViewBinding.12
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                movieDetailActivity.onViewClicked(view2);
            }
        });
        View findRequiredView13 = Utils.findRequiredView(view, R.id.ivMore, "field 'ivMore' and method 'onViewClicked'");
        movieDetailActivity.ivMore = (ImageView) Utils.castView(findRequiredView13, R.id.ivMore, "field 'ivMore'", ImageView.class);
        this.view7f0902ca = findRequiredView13;
        findRequiredView13.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity_ViewBinding.13
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                movieDetailActivity.onViewClicked(view2);
            }
        });
        View findRequiredView14 = Utils.findRequiredView(view, R.id.ivTomato, "method 'onViewClicked'");
        this.view7f0902f3 = findRequiredView14;
        findRequiredView14.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity_ViewBinding.14
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                movieDetailActivity.onViewClicked(view2);
            }
        });
        View findRequiredView15 = Utils.findRequiredView(view, R.id.llLike, "method 'onViewClicked'");
        this.view7f0903a6 = findRequiredView15;
        findRequiredView15.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity_ViewBinding.15
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                movieDetailActivity.onViewClicked(view2);
            }
        });
        View findRequiredView16 = Utils.findRequiredView(view, R.id.llDislike, "method 'onViewClicked'");
        this.view7f090397 = findRequiredView16;
        findRequiredView16.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity_ViewBinding.16
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                movieDetailActivity.onViewClicked(view2);
            }
        });
        View findRequiredView17 = Utils.findRequiredView(view, R.id.llAddMovieList, "method 'onViewClicked'");
        this.view7f090376 = findRequiredView17;
        findRequiredView17.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity_ViewBinding.17
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                movieDetailActivity.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        MovieDetailActivity movieDetailActivity = this.target;
        if (movieDetailActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        movieDetailActivity.detailMovieScroll = null;
        movieDetailActivity.detailMovieAvatar = null;
        movieDetailActivity.detailMovieAvatarBack = null;
        movieDetailActivity.detailMoviePlayBtn = null;
        movieDetailActivity.detailMoviePlayPGS = null;
        movieDetailActivity.detailMoviePlay = null;
        movieDetailActivity.detailMovieLanguage = null;
        movieDetailActivity.detailMovieDownload = null;
        movieDetailActivity.mMovieTitle = null;
        movieDetailActivity.mMovieBack = null;
        movieDetailActivity.mRecycler = null;
        movieDetailActivity.detailMovieController = null;
        movieDetailActivity.detailMovieAvatarShadow = null;
        movieDetailActivity.detailMovieRelated = null;
        movieDetailActivity.detailMovieName = null;
        movieDetailActivity.detailMovieDesc = null;
        movieDetailActivity.detailMovieAudio = null;
        movieDetailActivity.detailMovieSpan = null;
        movieDetailActivity.mTvDesc = null;
        movieDetailActivity.mTvDirector = null;
        movieDetailActivity.mTvCast = null;
        movieDetailActivity.mTvCategory = null;
        movieDetailActivity.llHidePart = null;
        movieDetailActivity.mIvImdb = null;
        movieDetailActivity.mIvArrow = null;
        movieDetailActivity.relatedMovieListLinearLayout = null;
        movieDetailActivity.relatedMovieListRecycler = null;
        movieDetailActivity.rvActors = null;
        movieDetailActivity.tvInformation = null;
        movieDetailActivity.ivStar = null;
        movieDetailActivity.tvImdbRating = null;
        movieDetailActivity.rlTitleBar = null;
        movieDetailActivity.tvReviewNum = null;
        movieDetailActivity.llComments = null;
        movieDetailActivity.rvTrailer = null;
        movieDetailActivity.tvTrailer = null;
        movieDetailActivity.tvRelated = null;
        movieDetailActivity.ivCollect = null;
        movieDetailActivity.ivLike = null;
        movieDetailActivity.ivDislike = null;
        movieDetailActivity.tvLike = null;
        movieDetailActivity.tvDislike = null;
        movieDetailActivity.ivFavorite = null;
        movieDetailActivity.ivMore = null;
        this.view7f090160.setOnClickListener(null);
        this.view7f090160 = null;
        this.view7f09016e.setOnClickListener(null);
        this.view7f09016e = null;
        this.view7f09016c.setOnClickListener(null);
        this.view7f09016c = null;
        this.view7f090169.setOnClickListener(null);
        this.view7f090169 = null;
        this.view7f090173.setOnClickListener(null);
        this.view7f090173 = null;
        this.view7f0907de.setOnClickListener(null);
        this.view7f0907de = null;
        this.view7f09031a.setOnClickListener(null);
        this.view7f09031a = null;
        this.view7f0902eb.setOnClickListener(null);
        this.view7f0902eb = null;
        this.view7f090730.setOnClickListener(null);
        this.view7f090730 = null;
        this.view7f090164.setOnClickListener(null);
        this.view7f090164 = null;
        this.view7f0902ac.setOnClickListener(null);
        this.view7f0902ac = null;
        this.view7f0902bb.setOnClickListener(null);
        this.view7f0902bb = null;
        this.view7f0902ca.setOnClickListener(null);
        this.view7f0902ca = null;
        this.view7f0902f3.setOnClickListener(null);
        this.view7f0902f3 = null;
        this.view7f0903a6.setOnClickListener(null);
        this.view7f0903a6 = null;
        this.view7f090397.setOnClickListener(null);
        this.view7f090397 = null;
        this.view7f090376.setOnClickListener(null);
        this.view7f090376 = null;
    }
}
