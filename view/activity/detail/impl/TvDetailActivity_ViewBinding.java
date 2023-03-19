package com.movieboxpro.android.view.activity.detail.impl;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public class TvDetailActivity_ViewBinding implements Unbinder {
    private TvDetailActivity target;
    private View view7f090173;
    private View view7f090175;
    private View view7f090177;
    private View view7f090179;
    private View view7f09017b;
    private View view7f09017d;
    private View view7f09017f;
    private View view7f090184;
    private View view7f090185;
    private View view7f0902ac;
    private View view7f0902bb;
    private View view7f0902ca;
    private View view7f0902eb;
    private View view7f0902f3;
    private View view7f090300;
    private View view7f09031a;
    private View view7f090376;
    private View view7f090397;
    private View view7f0903a6;
    private View view7f090730;
    private View view7f0907de;
    private View view7f090827;

    public TvDetailActivity_ViewBinding(TvDetailActivity tvDetailActivity) {
        this(tvDetailActivity, tvDetailActivity.getWindow().getDecorView());
    }

    public TvDetailActivity_ViewBinding(final TvDetailActivity tvDetailActivity, View view) {
        this.target = tvDetailActivity;
        tvDetailActivity.mTvBack = (ImageView) Utils.findRequiredViewAsType(view, R.id.tv_titlebar_left_icon, "field 'mTvBack'", ImageView.class);
        tvDetailActivity.mTvTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_titlebar_title, "field 'mTvTitle'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.detail_tv_avatar, "field 'detailTvAvatar' and method 'onViewClicked'");
        tvDetailActivity.detailTvAvatar = (ImageView) Utils.castView(findRequiredView, R.id.detail_tv_avatar, "field 'detailTvAvatar'", ImageView.class);
        this.view7f090175 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                tvDetailActivity.onViewClicked(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.detail_tv_season, "field 'detailTvSeason' and method 'onInfoClicked'");
        tvDetailActivity.detailTvSeason = (TextView) Utils.castView(findRequiredView2, R.id.detail_tv_season, "field 'detailTvSeason'", TextView.class);
        this.view7f090185 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                tvDetailActivity.onInfoClicked(view2);
            }
        });
        tvDetailActivity.detailTvPlayBtn = (TextView) Utils.findRequiredViewAsType(view, R.id.detail_tv_play_btn, "field 'detailTvPlayBtn'", TextView.class);
        tvDetailActivity.detailTvPlayPGS = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.detail_tv_play_progress, "field 'detailTvPlayPGS'", ProgressBar.class);
        View findRequiredView3 = Utils.findRequiredView(view, R.id.detail_tv_play, "field 'detailTvPlay' and method 'onViewClicked'");
        tvDetailActivity.detailTvPlay = (RelativeLayout) Utils.castView(findRequiredView3, R.id.detail_tv_play, "field 'detailTvPlay'", RelativeLayout.class);
        this.view7f09017f = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                tvDetailActivity.onViewClicked(view2);
            }
        });
        tvDetailActivity.detailTvRate = (TextView) Utils.findRequiredViewAsType(view, R.id.detail_tv_rate, "field 'detailTvRate'", TextView.class);
        tvDetailActivity.mRecycler = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.detail_tv_recycler, "field 'mRecycler'", RecyclerView.class);
        View findRequiredView4 = Utils.findRequiredView(view, R.id.detail_tv_related, "field 'detailTvRelated' and method 'onViewClicked'");
        tvDetailActivity.detailTvRelated = (RelativeLayout) Utils.castView(findRequiredView4, R.id.detail_tv_related, "field 'detailTvRelated'", RelativeLayout.class);
        this.view7f090184 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                tvDetailActivity.onViewClicked(view2);
            }
        });
        tvDetailActivity.detailTvName = (TextView) Utils.findRequiredViewAsType(view, R.id.detail_tv_name, "field 'detailTvName'", TextView.class);
        View findRequiredView5 = Utils.findRequiredView(view, R.id.detail_tv_language, "field 'detailTvLanguage' and method 'onViewClicked'");
        tvDetailActivity.detailTvLanguage = (TextView) Utils.castView(findRequiredView5, R.id.detail_tv_language, "field 'detailTvLanguage'", TextView.class);
        this.view7f09017d = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                tvDetailActivity.onViewClicked(view2);
            }
        });
        tvDetailActivity.detailTvBanner = (ImageView) Utils.findRequiredViewAsType(view, R.id.detail_tv_banner, "field 'detailTvBanner'", ImageView.class);
        View findRequiredView6 = Utils.findRequiredView(view, R.id.detail_movie_span, "field 'detailTvspan' and method 'onViewClicked'");
        tvDetailActivity.detailTvspan = (LinearLayout) Utils.castView(findRequiredView6, R.id.detail_movie_span, "field 'detailTvspan'", LinearLayout.class);
        this.view7f090173 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                tvDetailActivity.onViewClicked(view2);
            }
        });
        tvDetailActivity.drawer = (DrawerLayout) Utils.findRequiredViewAsType(view, R.id.tvdetail_drawer_layout, "field 'drawer'", DrawerLayout.class);
        tvDetailActivity.drawerRecycler = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.tvdetail_sort_recycler, "field 'drawerRecycler'", RecyclerView.class);
        tvDetailActivity.detailTvTabseason = (TextView) Utils.findRequiredViewAsType(view, R.id.detail_tv_tab_season, "field 'detailTvTabseason'", TextView.class);
        View findRequiredView7 = Utils.findRequiredView(view, R.id.tvdetail_sort_name, "field 'detailTvTabsort' and method 'onViewClicked'");
        tvDetailActivity.detailTvTabsort = (ConstraintLayout) Utils.castView(findRequiredView7, R.id.tvdetail_sort_name, "field 'detailTvTabsort'", ConstraintLayout.class);
        this.view7f090827 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                tvDetailActivity.onViewClicked(view2);
            }
        });
        tvDetailActivity.sortImageView = (ImageView) Utils.findRequiredViewAsType(view, R.id.tvdetail_sort_choose, "field 'sortImageView'", ImageView.class);
        View findRequiredView8 = Utils.findRequiredView(view, R.id.tv_desc, "field 'mTvDesc' and method 'onViewClicked'");
        tvDetailActivity.mTvDesc = (TextView) Utils.castView(findRequiredView8, R.id.tv_desc, "field 'mTvDesc'", TextView.class);
        this.view7f0907de = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity_ViewBinding.8
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                tvDetailActivity.onViewClicked(view2);
            }
        });
        tvDetailActivity.mTvDirector = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_director, "field 'mTvDirector'", TextView.class);
        tvDetailActivity.mTvCast = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_cast, "field 'mTvCast'", TextView.class);
        tvDetailActivity.mTvCategory = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_category, "field 'mTvCategory'", TextView.class);
        tvDetailActivity.llHidePart = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_hide_part, "field 'llHidePart'", LinearLayout.class);
        View findRequiredView9 = Utils.findRequiredView(view, R.id.iv_imdb, "field 'mIvImdb' and method 'onViewClicked'");
        tvDetailActivity.mIvImdb = (ImageView) Utils.castView(findRequiredView9, R.id.iv_imdb, "field 'mIvImdb'", ImageView.class);
        this.view7f09031a = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity_ViewBinding.9
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                tvDetailActivity.onViewClicked(view2);
            }
        });
        View findRequiredView10 = Utils.findRequiredView(view, R.id.iv_arrow, "field 'mIvArrow' and method 'onInfoClicked'");
        tvDetailActivity.mIvArrow = (ImageView) Utils.castView(findRequiredView10, R.id.iv_arrow, "field 'mIvArrow'", ImageView.class);
        this.view7f090300 = findRequiredView10;
        findRequiredView10.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity_ViewBinding.10
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                tvDetailActivity.onInfoClicked(view2);
            }
        });
        tvDetailActivity.rvActors = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rvActors, "field 'rvActors'", RecyclerView.class);
        View findRequiredView11 = Utils.findRequiredView(view, R.id.tvImdbRating, "field 'tvImdbRating' and method 'onRatingClicked'");
        tvDetailActivity.tvImdbRating = (TextView) Utils.castView(findRequiredView11, R.id.tvImdbRating, "field 'tvImdbRating'", TextView.class);
        this.view7f090730 = findRequiredView11;
        findRequiredView11.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity_ViewBinding.11
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                tvDetailActivity.onRatingClicked(view2);
            }
        });
        View findRequiredView12 = Utils.findRequiredView(view, R.id.ivStar, "field 'ivStar' and method 'onRatingClicked'");
        tvDetailActivity.ivStar = (ImageView) Utils.castView(findRequiredView12, R.id.ivStar, "field 'ivStar'", ImageView.class);
        this.view7f0902eb = findRequiredView12;
        findRequiredView12.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity_ViewBinding.12
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                tvDetailActivity.onRatingClicked(view2);
            }
        });
        tvDetailActivity.rlTitleBar = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.rlTitleBar, "field 'rlTitleBar'", RelativeLayout.class);
        tvDetailActivity.tvReviewNum = (TextView) Utils.findRequiredViewAsType(view, R.id.tvReviewNum, "field 'tvReviewNum'", TextView.class);
        View findRequiredView13 = Utils.findRequiredView(view, R.id.ivCollect, "field 'ivCollect' and method 'onViewClicked'");
        tvDetailActivity.ivCollect = (ImageView) Utils.castView(findRequiredView13, R.id.ivCollect, "field 'ivCollect'", ImageView.class);
        this.view7f0902ac = findRequiredView13;
        findRequiredView13.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity_ViewBinding.13
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                tvDetailActivity.onViewClicked(view2);
            }
        });
        tvDetailActivity.ivLike = (ImageView) Utils.findRequiredViewAsType(view, R.id.ivLike, "field 'ivLike'", ImageView.class);
        tvDetailActivity.ivDislike = (ImageView) Utils.findRequiredViewAsType(view, R.id.ivDislike, "field 'ivDislike'", ImageView.class);
        tvDetailActivity.tvLike = (TextView) Utils.findRequiredViewAsType(view, R.id.tvLike, "field 'tvLike'", TextView.class);
        tvDetailActivity.tvDislike = (TextView) Utils.findRequiredViewAsType(view, R.id.tvDislike, "field 'tvDislike'", TextView.class);
        View findRequiredView14 = Utils.findRequiredView(view, R.id.ivFavorite, "field 'ivFavorite' and method 'onViewClicked'");
        tvDetailActivity.ivFavorite = (ImageView) Utils.castView(findRequiredView14, R.id.ivFavorite, "field 'ivFavorite'", ImageView.class);
        this.view7f0902bb = findRequiredView14;
        findRequiredView14.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity_ViewBinding.14
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                tvDetailActivity.onViewClicked(view2);
            }
        });
        View findRequiredView15 = Utils.findRequiredView(view, R.id.ivMore, "field 'ivMore' and method 'onViewClicked'");
        tvDetailActivity.ivMore = (ImageView) Utils.castView(findRequiredView15, R.id.ivMore, "field 'ivMore'", ImageView.class);
        this.view7f0902ca = findRequiredView15;
        findRequiredView15.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity_ViewBinding.15
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                tvDetailActivity.onViewClicked(view2);
            }
        });
        View findRequiredView16 = Utils.findRequiredView(view, R.id.detail_tv_comments, "field 'llComments' and method 'onViewClicked'");
        tvDetailActivity.llComments = (LinearLayout) Utils.castView(findRequiredView16, R.id.detail_tv_comments, "field 'llComments'", LinearLayout.class);
        this.view7f090179 = findRequiredView16;
        findRequiredView16.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity_ViewBinding.16
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                tvDetailActivity.onViewClicked(view2);
            }
        });
        View findRequiredView17 = Utils.findRequiredView(view, R.id.detail_tv_download, "method 'onViewClicked'");
        this.view7f09017b = findRequiredView17;
        findRequiredView17.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity_ViewBinding.17
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                tvDetailActivity.onViewClicked(view2);
            }
        });
        View findRequiredView18 = Utils.findRequiredView(view, R.id.ivTomato, "method 'onViewClicked'");
        this.view7f0902f3 = findRequiredView18;
        findRequiredView18.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity_ViewBinding.18
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                tvDetailActivity.onViewClicked(view2);
            }
        });
        View findRequiredView19 = Utils.findRequiredView(view, R.id.detail_tv_banner_background, "method 'onViewClicked'");
        this.view7f090177 = findRequiredView19;
        findRequiredView19.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity_ViewBinding.19
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                tvDetailActivity.onViewClicked(view2);
            }
        });
        View findRequiredView20 = Utils.findRequiredView(view, R.id.llLike, "method 'onViewClicked'");
        this.view7f0903a6 = findRequiredView20;
        findRequiredView20.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity_ViewBinding.20
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                tvDetailActivity.onViewClicked(view2);
            }
        });
        View findRequiredView21 = Utils.findRequiredView(view, R.id.llDislike, "method 'onViewClicked'");
        this.view7f090397 = findRequiredView21;
        findRequiredView21.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity_ViewBinding.21
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                tvDetailActivity.onViewClicked(view2);
            }
        });
        View findRequiredView22 = Utils.findRequiredView(view, R.id.llAddMovieList, "method 'onViewClicked'");
        this.view7f090376 = findRequiredView22;
        findRequiredView22.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity_ViewBinding.22
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                tvDetailActivity.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        TvDetailActivity tvDetailActivity = this.target;
        if (tvDetailActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        tvDetailActivity.mTvBack = null;
        tvDetailActivity.mTvTitle = null;
        tvDetailActivity.detailTvAvatar = null;
        tvDetailActivity.detailTvSeason = null;
        tvDetailActivity.detailTvPlayBtn = null;
        tvDetailActivity.detailTvPlayPGS = null;
        tvDetailActivity.detailTvPlay = null;
        tvDetailActivity.detailTvRate = null;
        tvDetailActivity.mRecycler = null;
        tvDetailActivity.detailTvRelated = null;
        tvDetailActivity.detailTvName = null;
        tvDetailActivity.detailTvLanguage = null;
        tvDetailActivity.detailTvBanner = null;
        tvDetailActivity.detailTvspan = null;
        tvDetailActivity.drawer = null;
        tvDetailActivity.drawerRecycler = null;
        tvDetailActivity.detailTvTabseason = null;
        tvDetailActivity.detailTvTabsort = null;
        tvDetailActivity.sortImageView = null;
        tvDetailActivity.mTvDesc = null;
        tvDetailActivity.mTvDirector = null;
        tvDetailActivity.mTvCast = null;
        tvDetailActivity.mTvCategory = null;
        tvDetailActivity.llHidePart = null;
        tvDetailActivity.mIvImdb = null;
        tvDetailActivity.mIvArrow = null;
        tvDetailActivity.rvActors = null;
        tvDetailActivity.tvImdbRating = null;
        tvDetailActivity.ivStar = null;
        tvDetailActivity.rlTitleBar = null;
        tvDetailActivity.tvReviewNum = null;
        tvDetailActivity.ivCollect = null;
        tvDetailActivity.ivLike = null;
        tvDetailActivity.ivDislike = null;
        tvDetailActivity.tvLike = null;
        tvDetailActivity.tvDislike = null;
        tvDetailActivity.ivFavorite = null;
        tvDetailActivity.ivMore = null;
        tvDetailActivity.llComments = null;
        this.view7f090175.setOnClickListener(null);
        this.view7f090175 = null;
        this.view7f090185.setOnClickListener(null);
        this.view7f090185 = null;
        this.view7f09017f.setOnClickListener(null);
        this.view7f09017f = null;
        this.view7f090184.setOnClickListener(null);
        this.view7f090184 = null;
        this.view7f09017d.setOnClickListener(null);
        this.view7f09017d = null;
        this.view7f090173.setOnClickListener(null);
        this.view7f090173 = null;
        this.view7f090827.setOnClickListener(null);
        this.view7f090827 = null;
        this.view7f0907de.setOnClickListener(null);
        this.view7f0907de = null;
        this.view7f09031a.setOnClickListener(null);
        this.view7f09031a = null;
        this.view7f090300.setOnClickListener(null);
        this.view7f090300 = null;
        this.view7f090730.setOnClickListener(null);
        this.view7f090730 = null;
        this.view7f0902eb.setOnClickListener(null);
        this.view7f0902eb = null;
        this.view7f0902ac.setOnClickListener(null);
        this.view7f0902ac = null;
        this.view7f0902bb.setOnClickListener(null);
        this.view7f0902bb = null;
        this.view7f0902ca.setOnClickListener(null);
        this.view7f0902ca = null;
        this.view7f090179.setOnClickListener(null);
        this.view7f090179 = null;
        this.view7f09017b.setOnClickListener(null);
        this.view7f09017b = null;
        this.view7f0902f3.setOnClickListener(null);
        this.view7f0902f3 = null;
        this.view7f090177.setOnClickListener(null);
        this.view7f090177 = null;
        this.view7f0903a6.setOnClickListener(null);
        this.view7f0903a6 = null;
        this.view7f090397.setOnClickListener(null);
        this.view7f090397 = null;
        this.view7f090376.setOnClickListener(null);
        this.view7f090376 = null;
    }
}
