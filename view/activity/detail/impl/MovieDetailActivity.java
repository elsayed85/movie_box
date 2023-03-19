package com.movieboxpro.android.view.activity.detail.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.util.Pair;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.adorkable.iosdialog.ActionSheetDialog;
import com.airbnb.lottie.LottieAnimationView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.interfaces.SimpleCallback;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.ActorAdapter;
import com.movieboxpro.android.adapter.MovieListItemAdapter;
import com.movieboxpro.android.adapter.TrailerAdapter;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseActivity;
import com.movieboxpro.android.base.BaseAdapter;
import com.movieboxpro.android.base.OnItemClickListener;
import com.movieboxpro.android.db.entity.Download;
import com.movieboxpro.android.event.ChildModeChangedEvent;
import com.movieboxpro.android.event.LowMemoryEvent;
import com.movieboxpro.android.event.OnPlayFinishEvent;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.listener.AddWatchPlanListener;
import com.movieboxpro.android.listener.OnFavoriteStatusChangedListener;
import com.movieboxpro.android.livedata.RefreshWaitingLiveData;
import com.movieboxpro.android.livedata.RefreshWatchedLiveData;
import com.movieboxpro.android.model.ActorModel;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.PlayerStrategy;
import com.movieboxpro.android.model.QualityTag;
import com.movieboxpro.android.model.TrailerItem;
import com.movieboxpro.android.model.common.Feedback;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.presenter.activity.PsMovieDetail;
import com.movieboxpro.android.service.DownloadService;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.EventUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.LayoutManagerItemDecoration;
import com.movieboxpro.android.utils.LinearItemDecoration;
import com.movieboxpro.android.utils.ParamsUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SpanUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.utils.X86HintUtils;
import com.movieboxpro.android.utils.XpopImageLoader;
import com.movieboxpro.android.view.activity.Video.VideoPlayerActivity;
import com.movieboxpro.android.view.activity.actor.ActorDetailActivity;
import com.movieboxpro.android.view.activity.actor.MoreActorsActivity;
import com.movieboxpro.android.view.activity.choose.impl.ChooseActivity;
import com.movieboxpro.android.view.activity.detail.IMovieDetail;
import com.movieboxpro.android.view.activity.detail.impl.TrailerPlayerActivity;
import com.movieboxpro.android.view.activity.movie.MovieChooseActivity;
import com.movieboxpro.android.view.activity.movielist.MovieListDetailActivity;
import com.movieboxpro.android.view.activity.review.ReviewActivity;
import com.movieboxpro.android.view.activity.settings.InputChildModePasswordActivity;
import com.movieboxpro.android.view.activity.user.Login2Activity;
import com.movieboxpro.android.view.activity.videoplayer.MoviePlayerActivity;
import com.movieboxpro.android.view.dialog.AddMovieWatchDialog;
import com.movieboxpro.android.view.dialog.ChildModeHintDialog;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.FeedbackDialog;
import com.movieboxpro.android.view.dialog.ListDialog;
import com.movieboxpro.android.view.dialog.NoResourceDialog;
import com.movieboxpro.android.view.dialog.ReleasedHintDialog;
import com.movieboxpro.android.view.dialog.ReviewDialogFragment;
import com.movieboxpro.android.view.dialog.ScreenManageDialog;
import com.movieboxpro.android.view.dialog.TsDefinitionHintDialog;
import com.movieboxpro.android.view.dialog.VideoDescTextDialog;
import com.movieboxpro.android.view.fragment.AddVideoToFavoriteFragment;
import com.movieboxpro.android.view.videocontroller.PopPlayerManager;
import com.movieboxpro.android.view.widget.AntiShakeUtils;
import com.movieboxpro.android.view.widget.DetailMoreActionDialog;
import com.movieboxpro.android.view.widget.TransparentClickableSpan;
import com.uber.autodispose.ObservableSubscribeProxy;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.eclipse.jetty.servlet.ServletHandler;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
/* loaded from: classes.dex */
public class MovieDetailActivity extends BaseActivity implements IMovieDetail, OnFavoriteStatusChangedListener {
    public static final String MOVIE_ID = "movie_id";
    public static final String MOVIE_LANG = "movie_lang";
    public static final String MOVIE_POSTER = "movie_poster";
    private static final String[] TAB_TITLES = {"Related"};
    public static final String TRANSION = "movie_poster";
    private ActorAdapter actorAdapter;
    private List<ActorModel> actorModels;
    @BindView(R.id.detail_movie_audio)
    TagFlowLayout detailMovieAudio;
    @BindView(R.id.detail_movie_avatar)
    ImageView detailMovieAvatar;
    @BindView(R.id.detail_movie_avatar_backround)
    ConstraintLayout detailMovieAvatarBack;
    @BindView(R.id.detail_movie_avatar_shadow)
    ConstraintLayout detailMovieAvatarShadow;
    @BindView(R.id.detail_movie_controller)
    LinearLayout detailMovieController;
    @BindView(R.id.detail_movie_desc)
    ImageView detailMovieDesc;
    @BindView(R.id.detail_movie_download)
    LottieAnimationView detailMovieDownload;
    @BindView(R.id.detail_movie_language)
    TextView detailMovieLanguage;
    @BindView(R.id.detail_movie_name)
    TextView detailMovieName;
    @BindView(R.id.detail_movie_play)
    RelativeLayout detailMoviePlay;
    @BindView(R.id.detail_movie_play_btn)
    TextView detailMoviePlayBtn;
    @BindView(R.id.detail_movie_play_progress)
    ProgressBar detailMoviePlayPGS;
    @BindView(R.id.detail_movie_related)
    RelativeLayout detailMovieRelated;
    @BindView(R.id.detail_movie_avatar_backrounds)
    NestedScrollView detailMovieScroll;
    @BindView(R.id.detail_movie_span)
    LinearLayout detailMovieSpan;
    private String enPoster;
    private TrailerItem firstTrailer;
    private boolean isShowDesc;
    @BindView(R.id.ivCollect)
    ImageView ivCollect;
    @BindView(R.id.ivDislike)
    ImageView ivDislike;
    @BindView(R.id.ivFavorite)
    ImageView ivFavorite;
    @BindView(R.id.ivLike)
    ImageView ivLike;
    @BindView(R.id.ivMore)
    ImageView ivMore;
    @BindView(R.id.ivStar)
    ImageView ivStar;
    private String lang;
    private ListDialog listDialog;
    @BindView(R.id.detail_movie_comments)
    LinearLayout llComments;
    @BindView(R.id.ll_hide_part)
    LinearLayout llHidePart;
    private MovieDetailAdapter mAdapter;
    private String mImdbUrl;
    @BindView(R.id.iv_arrow)
    ImageView mIvArrow;
    @BindView(R.id.iv_imdb)
    ImageView mIvImdb;
    @BindView(R.id.movie_titlebar_left_icon)
    ImageView mMovieBack;
    @BindView(R.id.movie_titlebar_title)
    TextView mMovieTitle;
    private PsMovieDetail mPresenter;
    @BindView(R.id.detail_movie_recycler)
    RecyclerView mRecycler;
    @BindView(R.id.tv_cast)
    TextView mTvCast;
    @BindView(R.id.tv_category)
    TextView mTvCategory;
    @BindView(R.id.tv_desc)
    TextView mTvDesc;
    @BindView(R.id.tv_director)
    TextView mTvDirector;
    private MovieDetail movieDetail;
    private MovieListItemAdapter relatedMovieListAdapter;
    @BindView(R.id.ll_related_movie_list)
    LinearLayout relatedMovieListLinearLayout;
    @BindView(R.id.related_movie_list_recycler)
    RecyclerView relatedMovieListRecycler;
    @BindView(R.id.rlTitleBar)
    RelativeLayout rlTitleBar;
    @BindView(R.id.rvActors)
    RecyclerView rvActors;
    @BindView(R.id.rvTrailer)
    RecyclerView rvTrailer;
    private TrailerAdapter trailerAdapter;
    @BindView(R.id.tvDislike)
    TextView tvDislike;
    @BindView(R.id.tvImdbRating)
    TextView tvImdbRating;
    @BindView(R.id.tvInformation)
    TextView tvInformation;
    @BindView(R.id.tvLike)
    TextView tvLike;
    @BindView(R.id.tvRelated)
    TextView tvRelated;
    @BindView(R.id.tvReviewNum)
    TextView tvReviewNum;
    @BindView(R.id.tvTrailer)
    TextView tvTrailer;
    private String selectLanguage = "";
    private boolean isMusicType = false;
    private String poster = "";
    private Handler mHandler = new Handler();
    private String movie_uid = "";
    private List<MovieDetail.Recommend> list = new ArrayList();
    OnItemClickListener onItemClickListener = new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity.10
        @Override // com.movieboxpro.android.base.OnItemClickListener
        public void onItemClick(int i) {
            if (i < 0 || i > MovieDetailActivity.this.mAdapter.getItemCount() - 1) {
                return;
            }
            MovieDetail.Recommend model = MovieDetailActivity.this.mAdapter.getModel(i);
            MovieDetailActivity.start(MovieDetailActivity.this, model.mid, model.poster);
        }
    };
    private Runnable mRun = new Runnable() { // from class: com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity.12
        @Override // java.lang.Runnable
        public void run() {
            if (MovieDetailActivity.this.mPresenter != null) {
                MovieDetailActivity.this.mPresenter.getInfo(MovieDetailActivity.this.movie_uid, MovieDetailActivity.this.lang);
                if (App.isLogin() && App.getUserData().getHide_tv_movielist() == 0) {
                    MovieDetailActivity.this.mPresenter.requestActors(MovieDetailActivity.this.movie_uid);
                }
            }
        }
    };
    private boolean firstLoadPoster = true;

    @Override // com.movieboxpro.android.view.activity.detail.IMovieDetail
    public void addVideoCallback() {
    }

    @Override // com.movieboxpro.android.base.BaseActivity
    protected boolean isFullScreen() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLowMemory(LowMemoryEvent lowMemoryEvent) {
    }

    /* loaded from: classes3.dex */
    public class Item1ViewHolder_ViewBinding implements Unbinder {
        private Item1ViewHolder target;

        public Item1ViewHolder_ViewBinding(Item1ViewHolder item1ViewHolder, View view) {
            this.target = item1ViewHolder;
            item1ViewHolder.mCover = (ImageView) Utils.findRequiredViewAsType(view, R.id.favorite_poster, "field 'mCover'", ImageView.class);
            item1ViewHolder.mDesc = (ImageView) Utils.findRequiredViewAsType(view, R.id.favorite_desc, "field 'mDesc'", ImageView.class);
            item1ViewHolder.tvImdbRating = (TextView) Utils.findRequiredViewAsType(view, R.id.tvImdbRating, "field 'tvImdbRating'", TextView.class);
        }

        @Override // butterknife.Unbinder
        public void unbind() {
            Item1ViewHolder item1ViewHolder = this.target;
            if (item1ViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            item1ViewHolder.mCover = null;
            item1ViewHolder.mDesc = null;
            item1ViewHolder.tvImdbRating = null;
        }
    }

    @OnClick({R.id.ivStar, R.id.tvImdbRating})
    public void onRatingClicked(View view) {
        int id = view.getId();
        if ((id != R.id.ivStar && id != R.id.tvImdbRating) || this.activity == null || this.activity.isFinishing() || TextUtils.isEmpty(this.mImdbUrl)) {
            return;
        }
        SystemUtils.startBrowser(this.activity, this.mImdbUrl);
    }

    @OnClick({R.id.detail_movie_language, R.id.detail_movie_span, R.id.ivCollect, R.id.detail_movie_download, R.id.detail_movie_comments, R.id.detail_movie_play, R.id.detail_movie_avatar, R.id.iv_imdb, R.id.ivTomato, R.id.tv_desc, R.id.llLike, R.id.llDislike, R.id.ivFavorite, R.id.ivMore, R.id.llAddMovieList})
    public void onViewClicked(final View view) {
        if (this.mPresenter == null || this.movieDetail == null) {
            return;
        }
        final int i = 2;
        switch (view.getId()) {
            case R.id.detail_movie_avatar /* 2131296608 */:
                new XPopup.Builder(this.context).asImageViewer(this.detailMovieAvatar, this.movieDetail.poster, new XpopImageLoader()).show();
                return;
            case R.id.detail_movie_comments /* 2131296612 */:
                if (!App.isLogin()) {
                    Login2Activity.start(this);
                    return;
                } else if (this.movieDetail != null) {
                    ReviewDialogFragment.Companion.newInstance(this.movieDetail.id, 1).show(getSupportFragmentManager(), ReviewDialogFragment.class.getSimpleName());
                    return;
                } else {
                    return;
                }
            case R.id.detail_movie_download /* 2131296617 */:
                if (App.getDB().downloadDao().findByType(1, this.movieDetail.id) == null) {
                    PlayerStrategy playerStrategy = new PlayerStrategy();
                    playerStrategy.setInstace(this.movieDetail);
                    route(ChooseActivity.class, ParamsUtils.newBuilder().addParam(MovieChooseActivity.CHOOSEMOVIE, playerStrategy).addParam(ChooseActivity.ISDOWNLOAD, true).build());
                    return;
                }
                showDownload(this.movieDetail.id);
                return;
            case R.id.detail_movie_language /* 2131296620 */:
                showLanguage();
                return;
            case R.id.detail_movie_play /* 2131296622 */:
                if (AntiShakeUtils.isInvalidClick(view)) {
                    return;
                }
                if (!App.isLogin()) {
                    route(Login2Activity.class);
                    return;
                }
                MovieDetail movieDetail = this.movieDetail;
                if (movieDetail != null && movieDetail.code_file != 1) {
                    if (this.movieDetail.released_timestamp > System.currentTimeMillis() / 1000) {
                        MovieDetail movieDetail2 = this.movieDetail;
                        if (movieDetail2 != null && movieDetail2.is_collect == 1) {
                            r8 = true;
                        }
                        if (this.firstTrailer != null) {
                            new NoResourceDialog(this, true, new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity.4
                                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                                public void onClick() {
                                    TrailerPlayerActivity.Companion companion = TrailerPlayerActivity.Companion;
                                    MovieDetailActivity movieDetailActivity = MovieDetailActivity.this;
                                    companion.start(movieDetailActivity, movieDetailActivity.firstTrailer.getVideoId());
                                }
                            }).show();
                            return;
                        } else {
                            new ReleasedHintDialog.Builder(this).setPositiveText(r8).setBoxType(1).setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.-$$Lambda$MovieDetailActivity$NOKxkU1ls7WlGu9c2N3xxSg3JmM
                                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                                public final void onClick() {
                                    MovieDetailActivity.this.lambda$onViewClicked$0$MovieDetailActivity(r2);
                                }
                            }).create().show();
                            return;
                        }
                    } else if (this.movieDetail.released_timestamp == 0) {
                        r8 = this.movieDetail.is_collect == 1;
                        if (this.firstTrailer != null) {
                            new NoResourceDialog(this, true, new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity.5
                                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                                public void onClick() {
                                    TrailerPlayerActivity.Companion companion = TrailerPlayerActivity.Companion;
                                    MovieDetailActivity movieDetailActivity = MovieDetailActivity.this;
                                    companion.start(movieDetailActivity, movieDetailActivity.firstTrailer.getVideoId());
                                }
                            }).show();
                            return;
                        } else {
                            new ReleasedHintDialog.Builder(this).setPositiveText(r8).setUnknown(true).setBoxType(1).setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.-$$Lambda$MovieDetailActivity$7JsHkQhxYbtnMmkUs_DKoTCEe1c
                                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                                public final void onClick() {
                                    MovieDetailActivity.this.lambda$onViewClicked$1$MovieDetailActivity(r2);
                                }
                            }).create().show();
                            return;
                        }
                    } else if (this.movieDetail.released_timestamp <= 0 || this.movieDetail.released_timestamp >= System.currentTimeMillis() / 1000) {
                        return;
                    } else {
                        if (this.firstTrailer != null) {
                            new NoResourceDialog(this, false, new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity.6
                                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                                public void onClick() {
                                    TrailerPlayerActivity.Companion companion = TrailerPlayerActivity.Companion;
                                    MovieDetailActivity movieDetailActivity = MovieDetailActivity.this;
                                    companion.start(movieDetailActivity, movieDetailActivity.firstTrailer.getVideoId());
                                }
                            }).show();
                            return;
                        } else {
                            showToast("Not Available");
                            return;
                        }
                    }
                }
                MovieDetail movieDetail3 = this.movieDetail;
                if (movieDetail3 == null || checkChildMode(movieDetail3.content_rating)) {
                    return;
                }
                if ("tc".equalsIgnoreCase(this.movieDetail.quality_tag_new)) {
                    new TsDefinitionHintDialog.Builder(this).setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.-$$Lambda$MovieDetailActivity$bIQZ-ZeMaVbZheXN19Ey9VKKGVw
                        @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                        public final void onClick() {
                            MovieDetailActivity.this.getVideoDefinition();
                        }
                    }).create().show();
                    return;
                } else {
                    getVideoDefinition();
                    return;
                }
            case R.id.detail_movie_span /* 2131296627 */:
                if (!isScreenPortrait()) {
                    List<ActorModel> list = this.actorModels;
                    if (list == null || list.size() <= 0 || this.movieDetail == null) {
                        return;
                    }
                    MoreActorsActivity.Companion.start(this, this.movie_uid, "movie", this.movieDetail.title);
                    return;
                }
                boolean z = !this.isShowDesc;
                this.isShowDesc = z;
                if (z) {
                    this.mIvArrow.setImageResource(R.mipmap.ic_white_up_arrow);
                    this.llHidePart.setVisibility(0);
                    return;
                }
                this.mIvArrow.setImageResource(R.mipmap.ic_white_down_arrow);
                this.llHidePart.setVisibility(8);
                return;
            case R.id.ivCollect /* 2131296940 */:
                AddMovieWatchDialog newInstance = AddMovieWatchDialog.newInstance(this.movieDetail.plan_watched, this.movieDetail.plan_watched_mark_time, this.movieDetail.id);
                newInstance.setListener(new AddWatchPlanListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity.3
                    @Override // com.movieboxpro.android.listener.AddWatchPlanListener
                    public void onWatchChanged(int i2) {
                        MovieDetailActivity.this.onWatchedChanged(i2);
                    }

                    @Override // com.movieboxpro.android.listener.AddWatchPlanListener
                    public void onWatchPlan(int i2) {
                        if (i2 == 0) {
                            if (MovieDetailActivity.this.movieDetail.plan_watched == 0) {
                                PsMovieDetail psMovieDetail = MovieDetailActivity.this.mPresenter;
                                MovieDetailActivity movieDetailActivity = MovieDetailActivity.this;
                                psMovieDetail.cancelWatch(movieDetailActivity, movieDetailActivity.movieDetail.id);
                            } else if (MovieDetailActivity.this.movieDetail.plan_watched == 1) {
                                PsMovieDetail psMovieDetail2 = MovieDetailActivity.this.mPresenter;
                                MovieDetailActivity movieDetailActivity2 = MovieDetailActivity.this;
                                psMovieDetail2.changeWatched(movieDetailActivity2, movieDetailActivity2.movieDetail.id, 0);
                            } else {
                                PsMovieDetail psMovieDetail3 = MovieDetailActivity.this.mPresenter;
                                MovieDetailActivity movieDetailActivity3 = MovieDetailActivity.this;
                                psMovieDetail3.markWatched(movieDetailActivity3, movieDetailActivity3.movieDetail.id, 0);
                            }
                        } else if (MovieDetailActivity.this.movieDetail.plan_watched == 1) {
                            PsMovieDetail psMovieDetail4 = MovieDetailActivity.this.mPresenter;
                            MovieDetailActivity movieDetailActivity4 = MovieDetailActivity.this;
                            psMovieDetail4.cancelWatch(movieDetailActivity4, movieDetailActivity4.movieDetail.id);
                        } else if (MovieDetailActivity.this.movieDetail.plan_watched == 0) {
                            PsMovieDetail psMovieDetail5 = MovieDetailActivity.this.mPresenter;
                            MovieDetailActivity movieDetailActivity5 = MovieDetailActivity.this;
                            psMovieDetail5.changeWatched(movieDetailActivity5, movieDetailActivity5.movieDetail.id, 1);
                        } else {
                            PsMovieDetail psMovieDetail6 = MovieDetailActivity.this.mPresenter;
                            MovieDetailActivity movieDetailActivity6 = MovieDetailActivity.this;
                            psMovieDetail6.markWatched(movieDetailActivity6, movieDetailActivity6.movieDetail.id, 1);
                        }
                    }
                });
                newInstance.show(getSupportFragmentManager(), AddMovieWatchDialog.class.getSimpleName());
                return;
            case R.id.ivFavorite /* 2131296955 */:
                this.mPresenter.addFavorite(this.movieDetail.id, this.movieDetail.is_collect == 1);
                return;
            case R.id.ivMore /* 2131296970 */:
                new DetailMoreActionDialog(this, new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity.1
                    @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                    public void onClick() {
                        if (MovieDetailActivity.this.movieDetail != null) {
                            PsMovieDetail psMovieDetail = MovieDetailActivity.this.mPresenter;
                            MovieDetailActivity movieDetailActivity = MovieDetailActivity.this;
                            psMovieDetail.getShareLink(movieDetailActivity, movieDetailActivity.movieDetail.id);
                        }
                    }
                }, new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity.2
                    @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                    public void onClick() {
                        if (AntiShakeUtils.isInvalidClick(view)) {
                            return;
                        }
                        if (App.isLogin()) {
                            MovieDetailActivity.this.mPresenter.getFeedBack();
                        } else {
                            MovieDetailActivity.this.route(Login2Activity.class);
                        }
                    }
                }).show();
                return;
            case R.id.ivTomato /* 2131297011 */:
                goTomatoWebsite();
                return;
            case R.id.iv_imdb /* 2131297050 */:
                if (this.activity == null || this.activity.isFinishing() || TextUtils.isEmpty(this.mImdbUrl)) {
                    return;
                }
                SystemUtils.startBrowser(this.activity, this.mImdbUrl);
                return;
            case R.id.llAddMovieList /* 2131297142 */:
                if (!App.isLogin()) {
                    route(Login2Activity.class);
                    return;
                } else {
                    this.mPresenter.getMovieList(App.getUserData().uid_v2);
                    return;
                }
            case R.id.llDislike /* 2131297175 */:
                if (this.movieDetail.like_status != 1 && this.movieDetail.like_status != 0) {
                    int i2 = this.movieDetail.like_status;
                    i = 0;
                }
                ((ObservableSubscribeProxy) HttpRequest.post("Movie_like").param("box_type", (Object) 1).param("mid", this.movieDetail.id).param("season", (Object) 0).param("episode", (Object) 0).param(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(i)).asRequest().compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity.8
                    @Override // io.reactivex.Observer
                    public void onComplete() {
                    }

                    @Override // io.reactivex.Observer
                    public void onSubscribe(Disposable disposable) {
                        MovieDetailActivity.this.showLoadLoading();
                    }

                    @Override // io.reactivex.Observer
                    public void onNext(String str) {
                        MovieDetailActivity.this.hideLoadLoading();
                        MovieDetailActivity.this.movieDetail.like_status = i;
                        int i3 = i;
                        if (i3 == 2) {
                            MovieDetailActivity.this.movieDetail.dislike_total++;
                        } else if (i3 == 0) {
                            MovieDetailActivity.this.movieDetail.dislike_total--;
                        }
                        MovieDetailActivity movieDetailActivity = MovieDetailActivity.this;
                        movieDetailActivity.initLikeStatus(movieDetailActivity.movieDetail.like_status);
                    }

                    @Override // io.reactivex.Observer
                    public void onError(Throwable th) {
                        MovieDetailActivity.this.hideLoadLoading();
                        ToastUtils.showShort("Load failed:" + th.getMessage());
                    }
                });
                return;
            case R.id.llLike /* 2131297190 */:
                final int i3 = (this.movieDetail.like_status != 1 && (this.movieDetail.like_status == 0 || this.movieDetail.like_status == 2)) ? 1 : 0;
                ((ObservableSubscribeProxy) HttpRequest.post("Movie_like").param("box_type", (Object) 1).param("mid", this.movieDetail.id).param("season", (Object) 0).param("episode", (Object) 0).param(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(i3)).asRequest().compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity.7
                    @Override // io.reactivex.Observer
                    public void onComplete() {
                    }

                    @Override // io.reactivex.Observer
                    public void onSubscribe(Disposable disposable) {
                        MovieDetailActivity.this.showLoadLoading();
                    }

                    @Override // io.reactivex.Observer
                    public void onNext(String str) {
                        MovieDetailActivity.this.hideLoadLoading();
                        MovieDetailActivity.this.movieDetail.like_status = i3;
                        int i4 = i3;
                        if (i4 == 1) {
                            MovieDetailActivity.this.movieDetail.like_total++;
                        } else if (i4 == 0) {
                            MovieDetailActivity.this.movieDetail.like_total--;
                        }
                        MovieDetailActivity movieDetailActivity = MovieDetailActivity.this;
                        movieDetailActivity.initLikeStatus(movieDetailActivity.movieDetail.like_status);
                    }

                    @Override // io.reactivex.Observer
                    public void onError(Throwable th) {
                        MovieDetailActivity.this.hideLoadLoading();
                        ToastUtils.showShort("Load failed:" + th.getMessage());
                    }
                });
                return;
            case R.id.tv_desc /* 2131298270 */:
                new VideoDescTextDialog.Builder(this).setContent(this.mTvDesc.getText().toString()).create().show();
                return;
            default:
                return;
        }
    }

    public /* synthetic */ void lambda$onViewClicked$0$MovieDetailActivity(boolean z) {
        if (z) {
            return;
        }
        this.mPresenter.addFavorite(this.movieDetail.id, false);
    }

    public /* synthetic */ void lambda$onViewClicked$1$MovieDetailActivity(boolean z) {
        if (z) {
            return;
        }
        this.mPresenter.addFavorite(this.movieDetail.id, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goTomatoWebsite() {
        if (this.activity == null || this.activity.isFinishing() || this.movieDetail == null) {
            return;
        }
        SystemUtils.startBrowser(this.activity, this.movieDetail.tomato_url);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getVideoDefinition() {
        CastSession castSession;
        try {
            castSession = CastContext.getSharedInstance(this.context).getSessionManager().getCurrentCastSession();
        } catch (RuntimeException e) {
            e.printStackTrace();
            castSession = null;
        }
        if (castSession != null && castSession.isConnected()) {
            MovieDetail movieDetail = this.movieDetail;
            if (movieDetail != null) {
                this.mPresenter.getPath(movieDetail.id);
                return;
            }
            return;
        }
        MovieDetail movieDetail2 = this.movieDetail;
        if (movieDetail2 != null) {
            this.mPresenter.getPath(movieDetail2.id);
        }
    }

    public static void start(Activity activity, View view, String str, String str2, String str3) {
        Intent intent = new Intent(activity, MovieDetailActivity.class);
        intent.putExtra(MOVIE_ID, str2);
        intent.putExtra("movie_poster", str3);
        if (Build.VERSION.SDK_INT > 21) {
            ActivityCompat.startActivity(activity, intent, ActivityOptionsCompat.makeSceneTransitionAnimation(activity, Pair.create(view, str)).toBundle());
        } else {
            activity.startActivity(intent);
        }
    }

    public static void start(Context context, String str) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(MOVIE_ID, str);
        context.startActivity(intent);
    }

    public static void start(Context context, String str, String str2) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(MOVIE_ID, str);
        intent.putExtra("movie_poster", str2);
        context.startActivity(intent);
    }

    public static void startWithFlag(Context context, String str) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(MOVIE_ID, str);
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    private void showLanguage() {
        BaseQuickAdapter<BaseMediaModel.MoreLanguage, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<BaseMediaModel.MoreLanguage, BaseViewHolder>(R.layout.layout_language_menu_item, this.movieDetail.language) { // from class: com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity.9
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder baseViewHolder, BaseMediaModel.MoreLanguage moreLanguage) {
                baseViewHolder.setText(R.id.language_name_text, moreLanguage.title);
                baseViewHolder.setText(R.id.language_name_lang, moreLanguage.lang);
            }
        };
        baseQuickAdapter.setOnItemClickListener(new com.chad.library.adapter.base.listener.OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.-$$Lambda$MovieDetailActivity$kOKqC5OOU5GS7mtaJrtVRb8DwGE
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                MovieDetailActivity.this.lambda$showLanguage$2$MovieDetailActivity(baseQuickAdapter2, view, i);
            }
        });
        ListDialog create = new ListDialog.Builder(this).setAdapter(baseQuickAdapter).setTitleVisible(true).create();
        this.listDialog = create;
        create.show();
    }

    public /* synthetic */ void lambda$showLanguage$2$MovieDetailActivity(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        String str = this.movieDetail.language.get(i).lang;
        this.mPresenter.getInfo(this.movie_uid, (str == null || TextUtils.equals(str, "")) ? ServletHandler.__DEFAULT_SERVLET : this.movieDetail.language.get(i).lang);
        if (TextUtils.isEmpty(str)) {
            this.selectLanguage = ServletHandler.__DEFAULT_SERVLET;
        } else {
            this.selectLanguage = str;
        }
        this.lang = str;
        ListDialog listDialog = this.listDialog;
        if (listDialog != null) {
            listDialog.dismiss();
        }
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public View loadView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.activity_movie_detail, (ViewGroup) null);
    }

    @Override // com.movieboxpro.android.base.BaseActivity, com.movieboxpro.android.view.listener.IViewController
    public void initPresenter() {
        if (this.mPresenter == null) {
            this.mPresenter = new PsMovieDetail(this);
        }
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initView() {
        setTitleBar(false);
        this.mMovieTitle.setVisibility(4);
        DensityUtils.addMarginTopEqualStatusBarHeight(this.rlTitleBar, this);
        if (this.mAdapter == null) {
            this.mAdapter = new MovieDetailAdapter(this.list);
            this.mRecycler.setLayoutManager(new LinearLayoutManager(this, 0, false));
            this.mRecycler.setAdapter(this.mAdapter);
            this.mAdapter.setListener(this.onItemClickListener);
        }
        if (!PrefsUtils.getInstance().getBoolean(Constant.Prefs.SUPER_CHILD_MODE)) {
            this.mRecycler.setVisibility(0);
        } else {
            this.mRecycler.setVisibility(8);
            this.tvRelated.setVisibility(8);
        }
        this.mMovieBack.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.-$$Lambda$MovieDetailActivity$4ueoHqjyPaVup8WXLV7QUV7I8i8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MovieDetailActivity.this.lambda$initView$3$MovieDetailActivity(view);
            }
        });
    }

    public /* synthetic */ void lambda$initView$3$MovieDetailActivity(View view) {
        finish();
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initData() {
        EventUtils.event("进入电影详情");
        this.movie_uid = getStringParam(MOVIE_ID, "");
        String stringParam = getStringParam("movie_poster", "");
        this.poster = stringParam;
        if (!TextUtils.isEmpty(stringParam)) {
            GlideUtils.loadPortraitSkipMemCache(this.activity, this.poster, this.detailMovieAvatar);
            GlideUtils.loadWithBlur(this.activity, this.poster, (View) this.detailMovieAvatarBack);
        }
        this.lang = App.deviceLang;
        this.mHandler.postDelayed(this.mRun, 200L);
        this.relatedMovieListAdapter = new MovieListItemAdapter(new ArrayList());
        this.relatedMovieListRecycler.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.relatedMovieListRecycler.addItemDecoration(new LinearItemDecoration(0, 5, false));
        this.relatedMovieListRecycler.setAdapter(this.relatedMovieListAdapter);
        this.relatedMovieListAdapter.setOnItemClickListener(new com.chad.library.adapter.base.listener.OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.-$$Lambda$MovieDetailActivity$r3b1Q23o3roqZuBZpH_Bxxx1uxU
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MovieDetailActivity.this.lambda$initData$4$MovieDetailActivity(baseQuickAdapter, view, i);
            }
        });
        getLoadingPopupView(new SimpleCallback() { // from class: com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity.11
            @Override // com.lxj.xpopup.interfaces.SimpleCallback, com.lxj.xpopup.interfaces.XPopupCallback
            public boolean onBackPressed(BasePopupView basePopupView) {
                if (MovieDetailActivity.this.mPresenter != null) {
                    MovieDetailActivity.this.mPresenter.cancelRequest();
                }
                return super.onBackPressed(basePopupView);
            }
        });
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.CHILD_MODE)) {
            this.llComments.setVisibility(8);
        } else {
            this.llComments.setVisibility(0);
        }
    }

    public /* synthetic */ void lambda$initData$4$MovieDetailActivity(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        MovieListModel.MovieListItem item = this.relatedMovieListAdapter.getItem(i);
        if (item != null) {
            MovieListDetailActivity.start(this, item.getLid(), item.getUsername(), (item.getImgArr() == null || item.getImgArr().size() < 1) ? "" : item.getImgArr().get(0));
        }
    }

    @Override // com.movieboxpro.android.view.activity.detail.IMovieDetail
    public void getVideoInfo(MovieDetail movieDetail) {
        if ((this.activity != null && !isFinishing() && !this.firstLoadPoster) || TextUtils.isEmpty(this.poster)) {
            GlideUtils.loadSkipMemory(this.activity, movieDetail.poster, this.detailMovieAvatar, R.drawable.ic_portrait_loading_holder);
            GlideUtils.loadWithBlur(this.activity, movieDetail.poster, (View) this.detailMovieAvatarBack);
        }
        this.poster = movieDetail.poster;
        this.firstLoadPoster = false;
        this.movieDetail = movieDetail;
        this.list.clear();
        this.list.addAll(this.movieDetail.recommend);
        this.mAdapter.notifyDataSetChanged();
        if (!PrefsUtils.getInstance().getBoolean(Constant.Prefs.SUPER_CHILD_MODE) && !PrefsUtils.getInstance().getBoolean(Constant.Prefs.HIDE_MOVIELIST)) {
            if (App.isLogin() && App.getUserData().getHide_tv_movielist() == 0 && movieDetail.playlists != null && movieDetail.playlists.size() > 0) {
                this.relatedMovieListLinearLayout.setVisibility(0);
                this.relatedMovieListAdapter.setList(movieDetail.playlists);
            }
        } else {
            this.relatedMovieListLinearLayout.setVisibility(8);
        }
        refreshView();
        if (movieDetail.getTrailer_url_arr() != null && movieDetail.getTrailer_url_arr().size() > 0) {
            this.tvTrailer.setVisibility(0);
            if (movieDetail.getTrailer_url_arr().size() == 1) {
                this.tvTrailer.setText("Trailer");
            } else {
                this.tvTrailer.setText("Trailers");
            }
            this.rvTrailer.setVisibility(0);
            this.firstTrailer = movieDetail.getTrailer_url_arr().get(0);
            for (TrailerItem trailerItem : movieDetail.getTrailer_url_arr()) {
                trailerItem.setVideoId(Uri.parse(trailerItem.getUrl()).getQueryParameter("v"));
            }
            this.trailerAdapter = new TrailerAdapter(movieDetail.getTrailer_url_arr());
            this.rvTrailer.addItemDecoration(new LinearItemDecoration(0, 5, false));
            this.rvTrailer.setLayoutManager(new LinearLayoutManager(this, 0, false));
            this.rvTrailer.setAdapter(this.trailerAdapter);
            this.trailerAdapter.setOnItemClickListener(new com.chad.library.adapter.base.listener.OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.-$$Lambda$MovieDetailActivity$uedeuy77_cCSt1d3aix09-80PVs
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    MovieDetailActivity.this.lambda$getVideoInfo$5$MovieDetailActivity(baseQuickAdapter, view, i);
                }
            });
        }
        initLikeStatus(this.movieDetail.like_status);
    }

    public /* synthetic */ void lambda$getVideoInfo$5$MovieDetailActivity(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        TrailerPlayerActivity.Companion.start(this, this.trailerAdapter.getItem(i).getVideoId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initLikeStatus(int i) {
        if (i == 0) {
            this.ivLike.setImageResource(R.mipmap.ic_like);
            this.ivDislike.setImageResource(R.mipmap.ic_yellow_dislike);
            this.tvLike.setTextColor(getResources().getColor(R.color.color_text_light_pressed));
            if (this.movieDetail.like_total == 0) {
                this.tvLike.setText("LIKE");
            } else {
                this.tvLike.setText(String.valueOf(this.movieDetail.like_total));
            }
            this.tvDislike.setTextColor(getResources().getColor(R.color.color_text_light_pressed));
            if (this.movieDetail.dislike_total == 0) {
                this.tvDislike.setText("DISLIKE");
            } else {
                this.tvDislike.setText(String.valueOf(this.movieDetail.dislike_total));
            }
        } else if (i == 1) {
            this.ivLike.setImageResource(R.mipmap.ic_yellow_liked);
            this.ivDislike.setImageResource(R.mipmap.ic_yellow_dislike);
            this.tvLike.setTextColor(Color.parseColor("#FFEFC001"));
            this.tvLike.setText(String.valueOf(this.movieDetail.like_total));
            this.tvDislike.setTextColor(getResources().getColor(R.color.color_text_light_pressed));
            if (this.movieDetail.dislike_total == 0) {
                this.tvDislike.setText("DISLIKE");
            } else {
                this.tvDislike.setText(String.valueOf(this.movieDetail.dislike_total));
            }
        } else if (i == 2) {
            this.ivLike.setImageResource(R.mipmap.ic_like);
            this.ivDislike.setImageResource(R.mipmap.ic_yellow_disliked);
            this.tvDislike.setTextColor(Color.parseColor("#FFEFC001"));
            this.tvDislike.setText(String.valueOf(this.movieDetail.dislike_total));
            this.tvLike.setTextColor(getResources().getColor(R.color.color_text_light_pressed));
            if (this.movieDetail.like_total == 0) {
                this.tvLike.setText("LIKE");
            } else {
                this.tvLike.setText(String.valueOf(this.movieDetail.like_total));
            }
        }
    }

    @Override // com.movieboxpro.android.view.activity.detail.IMovieDetail
    public void switchPlayButtonStage(int i) {
        if (i == 0) {
            this.detailMoviePlayPGS.setVisibility(0);
            TextView textView = this.detailMoviePlayBtn;
            StringBuilder sb = new StringBuilder();
            sb.append("LOADING...");
            textView.setText(sb);
            this.detailMoviePlayBtn.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
            this.detailMoviePlay.setBackgroundResource(R.drawable.bg_round_btn_red);
            this.detailMoviePlay.setEnabled(true);
            this.detailMovieDownload.setEnabled(true);
        } else if (i == 1) {
            this.detailMoviePlayPGS.setVisibility(8);
        } else if (i == 2) {
            TextView textView2 = this.detailMoviePlayBtn;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Error...");
            textView2.setText(sb2);
            this.detailMoviePlayPGS.setVisibility(8);
            this.detailMoviePlayBtn.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
            this.detailMoviePlay.setBackgroundResource(R.drawable.bg_round_btn_red);
            this.detailMoviePlay.setEnabled(true);
            this.detailMovieDownload.setEnabled(true);
        } else if (i != 3) {
        } else {
            MovieDetail movieDetail = this.movieDetail;
            if (movieDetail != null) {
                this.detailMoviePlayBtn.setText(String.format("Released in %s", movieDetail.released));
            }
            this.detailMoviePlayBtn.setTextColor(ContextCompat.getColor(App.getContext(), R.color.white_30alpha));
            this.detailMoviePlayPGS.setVisibility(8);
            this.detailMoviePlayBtn.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
            this.detailMoviePlay.setBackgroundResource(R.drawable.comming_soon_bg_shape);
            this.detailMoviePlay.setEnabled(true);
            this.detailMovieDownload.setEnabled(false);
        }
    }

    @Override // com.movieboxpro.android.view.activity.detail.IMovieDetail
    public void setFavorite(boolean z) {
        MovieDetail movieDetail = this.movieDetail;
        if (movieDetail != null) {
            if (z) {
                movieDetail.is_collect = 1;
                ToastUtils.showShort("Add favorite successfully");
            } else {
                movieDetail.is_collect = 0;
            }
        }
        if (z) {
            this.ivFavorite.setImageResource(R.mipmap.ic_collected);
        } else {
            this.ivFavorite.setImageResource(R.mipmap.ic_add_to_favorite);
        }
    }

    @Override // com.movieboxpro.android.view.activity.detail.IMovieDetail
    public void ChoosePlayer(MovieDetail movieDetail) {
        if (X86HintUtils.checkX86(this)) {
            return;
        }
        this.movieDetail.addDonwload(movieDetail);
        if (this.movieDetail.list != null && this.movieDetail.list.size() > 0) {
            CastSession castSession = null;
            try {
                castSession = CastContext.getSharedInstance(this.context).getSessionManager().getCurrentCastSession();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (castSession != null && castSession.isConnected()) {
                PlayerStrategy playerStrategy = new PlayerStrategy();
                playerStrategy.setInstace(this.movieDetail);
                route(ChooseActivity.class, ParamsUtils.newBuilder().addParam(MovieChooseActivity.CHOOSEMOVIE, playerStrategy).build());
                return;
            } else if (PopPlayerManager.getInstance().isPopShow()) {
                PopPlayerManager.getInstance().setNewPlay(this.movieDetail);
                return;
            } else {
                route(MoviePlayerActivity.class, ParamsUtils.newBuilder().addParam("videoplayer_params", this.movieDetail).addParam(VideoPlayerActivity.VIDEO_ID, movieDetail.id).build(), 101);
                return;
            }
        }
        showToast("NO RESOURCE");
    }

    @Override // com.movieboxpro.android.view.activity.detail.IMovieDetail
    public void getFeedBack(List<Feedback> list) {
        ActionSheetDialog canceledOnTouchOutside = new ActionSheetDialog(this.context).builder().setCancelable(true).setCanceledOnTouchOutside(true);
        for (final Feedback feedback : list) {
            canceledOnTouchOutside.addSheetItem(feedback.name, ActionSheetDialog.SheetItemColor.White, new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.-$$Lambda$MovieDetailActivity$epQcdn-QP6jg13gMlFW29o31fB8
                @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
                public final void onClick(int i) {
                    MovieDetailActivity.this.lambda$getFeedBack$7$MovieDetailActivity(feedback, i);
                }
            });
        }
        canceledOnTouchOutside.show();
    }

    public /* synthetic */ void lambda$getFeedBack$7$MovieDetailActivity(final Feedback feedback, int i) {
        if (feedback.ftid == 0) {
            FeedbackDialog feedbackDialog = new FeedbackDialog(this.activity, "");
            feedbackDialog.setMessageListener(new FeedbackDialog.OnMessageListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.-$$Lambda$MovieDetailActivity$pJXjhuC6PEEDCxHnhF8Cw7wuAsc
                @Override // com.movieboxpro.android.view.dialog.FeedbackDialog.OnMessageListener
                public final void onMessage(String str) {
                    MovieDetailActivity.this.lambda$getFeedBack$6$MovieDetailActivity(feedback, str);
                }
            });
            feedbackDialog.show();
            return;
        }
        this.mPresenter.setFeedBack(feedback.state, 1, this.movie_uid, null, feedback.ftid, SystemUtils.getMsg(this.activity));
    }

    public /* synthetic */ void lambda$getFeedBack$6$MovieDetailActivity(Feedback feedback, String str) {
        PsMovieDetail psMovieDetail = this.mPresenter;
        int i = feedback.state;
        String str2 = this.movie_uid;
        int i2 = feedback.ftid;
        psMovieDetail.setFeedBack(i, 1, str2, null, i2, str + SystemUtils.getMsg());
    }

    @Override // com.movieboxpro.android.view.activity.detail.IMovieDetail
    public void showBottomMovieListDialog(List<MovieListModel.MovieListItem> list) {
        AddVideoToFavoriteFragment newInstance = AddVideoToFavoriteFragment.newInstance(list, this.movieDetail.id, this.movieDetail.is_collect == 1, 1, this.movieDetail.plan_watched);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.add(newInstance, "AddVideoToFavoriteFragment");
        beginTransaction.commitAllowingStateLoss();
        beginTransaction.show(newInstance);
    }

    @Override // com.movieboxpro.android.view.activity.detail.IMovieDetail
    public void showActors(List<ActorModel> list) {
        this.actorModels = list;
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            if (list.size() >= 10) {
                arrayList.addAll(list.subList(0, 10));
            } else {
                arrayList.addAll(list);
            }
        }
        if (arrayList.size() > 0) {
            ActorModel actorModel = new ActorModel();
            actorModel.setName("MORE");
            actorModel.setMore(true);
            arrayList.add(actorModel);
        }
        this.actorAdapter = new ActorAdapter(arrayList);
        this.rvActors.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.rvActors.addItemDecoration(new LayoutManagerItemDecoration(0, 10));
        this.rvActors.setAdapter(this.actorAdapter);
        this.actorAdapter.setOnItemClickListener(new com.chad.library.adapter.base.listener.OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.-$$Lambda$MovieDetailActivity$S_JH-itWCd70PSMV6BUl1PTBgZg
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MovieDetailActivity.this.lambda$showActors$8$MovieDetailActivity(baseQuickAdapter, view, i);
            }
        });
    }

    public /* synthetic */ void lambda$showActors$8$MovieDetailActivity(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        ActorModel item = this.actorAdapter.getItem(i);
        if (item != null) {
            if (item.isMore()) {
                MoreActorsActivity.Companion.start(this, this.movie_uid, "movie", this.movieDetail.title);
            } else {
                ActorDetailActivity.Companion.start(this, item.getActor_id());
            }
        }
    }

    @Override // com.movieboxpro.android.view.activity.detail.IMovieDetail
    public void showReviewCount(String str) {
        this.tvReviewNum.setText(str);
    }

    @Override // com.movieboxpro.android.view.activity.detail.IMovieDetail
    public void showPoster(String str) {
        this.enPoster = str;
        MovieDetail movieDetail = this.movieDetail;
        if (movieDetail != null) {
            if (movieDetail.language != null && this.movieDetail.language.size() > 0) {
                ReviewActivity.Companion.start(this, this.movieDetail.id, 1, this.movieDetail.language.get(0).title, str, this.movieDetail.year, this.movieDetail.imdb_rating, this.isMusicType);
            } else {
                ReviewActivity.Companion.start(this, this.movieDetail.id, 1, this.movieDetail.title, str, this.movieDetail.year, this.movieDetail.imdb_rating, this.isMusicType);
            }
        }
    }

    @Override // com.movieboxpro.android.view.activity.detail.IMovieDetail
    public void showScreenManage(final String str, ArrayList<DeviceModelResponse.DeviceModel> arrayList, String str2) {
        ScreenManageDialog newInstance = ScreenManageDialog.newInstance(arrayList, str2);
        newInstance.setListener(new ScreenManageDialog.OnStopDeviceListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity.13
            @Override // com.movieboxpro.android.view.dialog.ScreenManageDialog.OnStopDeviceListener
            public void onStopDevice() {
                MovieDetailActivity.this.mPresenter.getPath(str);
            }
        });
        newInstance.show(getSupportFragmentManager(), ScreenManageDialog.class.getSimpleName());
    }

    @Override // com.movieboxpro.android.view.activity.detail.IMovieDetail
    public void onWatchedChanged(int i) {
        this.movieDetail.plan_watched = i;
        this.movieDetail.plan_watched_mark_time = System.currentTimeMillis() / 1000;
        if (i == -1) {
            this.ivCollect.setImageResource(R.mipmap.ic_watch_plan);
        } else if (i == 0) {
            this.ivCollect.setImageResource(R.mipmap.ic_waiting);
        } else if (i == 1) {
            this.ivCollect.setImageResource(R.mipmap.ic_watched);
        }
        RefreshWatchedLiveData.Companion.get().setValue(true);
        RefreshWaitingLiveData.Companion.get().setValue(true);
    }

    public void refreshView() {
        if (TextUtils.isEmpty(this.selectLanguage)) {
            if (this.movieDetail.language.size() > 1) {
                this.detailMovieLanguage.setVisibility(0);
                String str = App.deviceLang;
                Iterator<BaseMediaModel.MoreLanguage> it = this.movieDetail.language.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    } else if (str.equals(it.next().lang)) {
                        this.detailMovieLanguage.setText(str);
                        break;
                    } else {
                        this.detailMovieLanguage.setText("en");
                    }
                }
            } else {
                this.detailMovieLanguage.setVisibility(8);
            }
        } else if (ServletHandler.__DEFAULT_SERVLET.equals(this.selectLanguage)) {
            this.detailMovieLanguage.setText("");
        } else {
            this.detailMovieLanguage.setText(this.selectLanguage);
        }
        if (this.movieDetail.plan_watched == 0) {
            this.ivCollect.setImageResource(R.mipmap.ic_waiting);
        } else if (this.movieDetail.plan_watched == 1) {
            this.ivCollect.setImageResource(R.mipmap.ic_watched);
        }
        if (this.movieDetail.is_collect == 1) {
            this.ivFavorite.setImageResource(R.mipmap.ic_collected);
        } else {
            this.ivFavorite.setImageResource(R.mipmap.ic_add_to_favorite);
        }
        this.mMovieTitle.setText(this.movieDetail.title);
        this.detailMovieName.setText(this.movieDetail.title);
        this.detailMovieName.requestLayout();
        String str2 = this.movieDetail.tomato_meter == 0 ? "-.-%" : this.movieDetail.tomato_meter + "%";
        String str3 = this.movieDetail.audience_score != 0 ? this.movieDetail.audience_score + "%" : "-.-%";
        this.ivStar.setVisibility(0);
        if (!TextUtils.isEmpty(this.movieDetail.imdb_rating)) {
            CommonUtils.textShadow(this.tvImdbRating, this.movieDetail.imdb_rating, 12, R.color.white);
        } else {
            CommonUtils.textShadow(this.tvImdbRating, "-.-", 12, R.color.white);
        }
        String[] split = this.movieDetail.cats.split(",");
        String str4 = split.length > 0 ? split[0] : "";
        SpanUtils with = SpanUtils.with(this.tvInformation);
        this.tvInformation.setMovementMethod(LinkMovementMethod.getInstance());
        int i = CommonUtils.isTablet() ? 50 : 30;
        if (!TextUtils.isEmpty(str4)) {
            with.append(str4.toUpperCase()).setFontSize(13, true).setForegroundColor(ContextCompat.getColor(this, R.color.white_30alpha)).appendSpace(i);
        }
        with.append(this.movieDetail.year).setFontSize(13, true).setForegroundColor(ContextCompat.getColor(this, R.color.white_30alpha)).appendSpace(i).append(this.movieDetail.content_rating).setFontSize(13, true).setForegroundColor(ContextCompat.getColor(this, R.color.white_30alpha)).appendSpace(i).append(this.movieDetail.runtime + "min").setFontSize(13, true).setForegroundColor(ContextCompat.getColor(this, R.color.white_30alpha)).appendSpace(i).appendImage(CommonUtils.getTomatoImg(this.movieDetail.tomato_meter), 2).setClickSpan(new TransparentClickableSpan() { // from class: com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity.17
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                MovieDetailActivity.this.goTomatoWebsite();
            }
        }).appendSpace(10).append(str2).setFontSize(13, true).setForegroundColor(ContextCompat.getColor(this, R.color.white_30alpha)).setClickSpan(new TransparentClickableSpan() { // from class: com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity.16
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                MovieDetailActivity.this.goTomatoWebsite();
            }
        }).appendSpace(i).appendImage(CommonUtils.getTomatoScore(this.movieDetail.audience_score), 2).setClickSpan(new TransparentClickableSpan() { // from class: com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity.15
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                MovieDetailActivity.this.goTomatoWebsite();
            }
        }).appendSpace(10).append(str3).setFontSize(13, true).setForegroundColor(ContextCompat.getColor(this, R.color.white_30alpha)).setClickSpan(new TransparentClickableSpan() { // from class: com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity.14
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                MovieDetailActivity.this.goTomatoWebsite();
            }
        }).create();
        this.mImdbUrl = this.movieDetail.imdb_link;
        this.mTvDesc.setText(this.movieDetail.description);
        this.mTvDirector.setText(String.format("Director:%s", this.movieDetail.director));
        this.mTvCast.setText(String.format("Cast:%s", this.movieDetail.actors));
        this.mTvCategory.setText(String.format("Category:%s", this.movieDetail.cats));
        if (this.movieDetail.cats.contains("music")) {
            this.isMusicType = true;
        }
        this.mPresenter.getReviewNum(this.movieDetail.id, this.isMusicType);
        this.detailMovieDesc.setImageResource(CommonUtils.getMovieTag(this.movieDetail.quality_tag_new));
        this.detailMovieAudio.setMaxSelectCount(this.movieDetail.audio_lang.length);
        this.detailMovieAudio.setPadding(0, 0, 0, 0);
        QualityTag[] qualityTagArr = new QualityTag[this.movieDetail.audio_lang.length + this.movieDetail.quality_tags_new.length];
        for (int i2 = 0; i2 < this.movieDetail.audio_lang.length; i2++) {
            qualityTagArr[i2] = new QualityTag(1, this.movieDetail.audio_lang[i2]);
        }
        for (int i3 = 0; i3 < this.movieDetail.quality_tags_new.length; i3++) {
            qualityTagArr[this.movieDetail.audio_lang.length + i3] = new QualityTag(2, this.movieDetail.quality_tags_new[i3]);
        }
        this.detailMovieAudio.setAdapter(new TagAdapter<QualityTag>(qualityTagArr) { // from class: com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity.18
            @Override // com.zhy.view.flowlayout.TagAdapter
            public View getView(FlowLayout flowLayout, int i4, QualityTag qualityTag) {
                if (qualityTag.getType() == 1) {
                    TextView textView = new TextView(MovieDetailActivity.this);
                    String upperCase = qualityTag.getTag().substring(0, 2).trim().toUpperCase();
                    StringBuilder sb = new StringBuilder();
                    sb.append(upperCase);
                    sb.append("  ");
                    textView.setText(sb);
                    textView.setTextColor(MovieDetailActivity.this.getResources().getColor(R.color.white));
                    textView.setBackgroundResource(R.color.tag_color);
                    textView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, ContextCompat.getDrawable(MovieDetailActivity.this.activity, R.drawable.ic_audio), (Drawable) null);
                    textView.setGravity(17);
                    textView.setTextSize(10.0f);
                    textView.setPadding(DensityUtils.dp2px(MovieDetailActivity.this, 2.0f), 0, 0, 0);
                    return textView;
                }
                ImageView imageView = new ImageView(MovieDetailActivity.this);
                imageView.setImageResource(CommonUtils.getDetailQualityTag(qualityTag.getTag()));
                ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-2, DensityUtils.dp2px(16.0f));
                marginLayoutParams.topMargin = DensityUtils.dp2px(3.0f);
                marginLayoutParams.leftMargin = DensityUtils.dp2px(5.0f);
                imageView.setLayoutParams(marginLayoutParams);
                return imageView;
            }
        });
        if (this.movieDetail.seconds != 0) {
            TextView textView = this.detailMoviePlayBtn;
            StringBuilder sb = new StringBuilder();
            sb.append("  RESUME  ");
            sb.append(TimeUtils.getTime(this.movieDetail.seconds));
            textView.setText(sb);
            this.detailMoviePlayBtn.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this.activity, R.drawable.ic_movie_play), (Drawable) null, (Drawable) null, (Drawable) null);
            this.mPresenter.SaveInDao(this.movieDetail);
        } else {
            TextView textView2 = this.detailMoviePlayBtn;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("  PLAY");
            textView2.setText(sb2);
            this.detailMoviePlayBtn.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this.activity, R.drawable.ic_movie_play), (Drawable) null, (Drawable) null, (Drawable) null);
        }
        addAnima();
        if (this.movieDetail.code_file != 0 || this.movieDetail.released_timestamp <= 0 || this.movieDetail.released_timestamp <= System.currentTimeMillis() / 1000) {
            return;
        }
        switchPlayButtonStage(3);
    }

    private void showDownload(final String str) {
        new ActionSheetDialog(this.context).builder().setCancelable(true).setCanceledOnTouchOutside(true).addSheetItem("Cancel Download", ActionSheetDialog.SheetItemColor.White, new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity.19
            @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
            public void onClick(int i) {
                Intent intent = new Intent(MovieDetailActivity.this.context, DownloadService.class);
                Bundle build = ParamsUtils.newBuilder().addParam(Constant.Download.PARAMS_KEY_MOVIE, str).addParam(Constant.Download.PARAMS_KEY_TYPE, 1).build();
                intent.setAction(Constant.ACTION.MOVIE_DELETE);
                intent.putExtras(build);
                MovieDetailActivity.this.activity.startService(intent);
                if (MovieDetailActivity.this.detailMovieDownload != null) {
                    MovieDetailActivity.this.detailMovieDownload.cancelAnimation();
                }
            }
        }).show();
    }

    private void addAnima() {
        Download findByType = App.getDB().downloadDao().findByType(1, this.movie_uid);
        if (findByType == null) {
            this.detailMovieDownload.setAnimation("download.json");
        } else if (findByType.getStatue() == 1 || findByType.getStatue() == 3) {
            this.detailMovieDownload.setAnimation("download.json");
            this.detailMovieDownload.playAnimation();
        } else {
            this.detailMovieDownload.setAnimation("downloaded.json");
            this.detailMovieDownload.pauseAnimation();
        }
    }

    private boolean checkChildMode(String str) {
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.CHILD_MODE, false) && CommonUtils.isChildModeVideo(str)) {
            ChildModeHintDialog childModeHintDialog = new ChildModeHintDialog(this);
            childModeHintDialog.setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity.20
                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                public void onClick() {
                    InputChildModePasswordActivity.Companion.start(MovieDetailActivity.this, 100);
                }
            });
            childModeHintDialog.show();
            return true;
        }
        return false;
    }

    @Override // com.movieboxpro.android.listener.OnFavoriteStatusChangedListener
    public void onFavoriteStatusChanged(boolean z, int i) {
        if (z) {
            this.movieDetail.is_collect = 1;
        } else {
            this.movieDetail.is_collect = 0;
        }
        if (i == 0) {
            this.ivCollect.setImageResource(R.mipmap.ic_waiting);
        } else if (i == 1) {
            this.ivCollect.setImageResource(R.mipmap.ic_watched);
        }
        this.movieDetail.plan_watched = i;
    }

    @Override // com.movieboxpro.android.listener.OnFavoriteStatusChangedListener
    public void showLoadLoading() {
        showLoading();
    }

    @Override // com.movieboxpro.android.listener.OnFavoriteStatusChangedListener
    public void hideLoadLoading() {
        hideLoading();
    }

    @Override // com.movieboxpro.android.listener.OnFavoriteStatusChangedListener
    public void showViewToast(String str) {
        showToast(str);
    }

    /* loaded from: classes3.dex */
    public class MovieDetailAdapter extends BaseAdapter<MovieDetail.Recommend> {
        public MovieDetailAdapter(List<MovieDetail.Recommend> list) {
            super(list);
        }

        @Override // com.movieboxpro.android.base.BaseAdapter
        public com.movieboxpro.android.base.BaseViewHolder getHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i, OnItemClickListener onItemClickListener) {
            return new Item1ViewHolder(layoutInflater.inflate(R.layout.movie_detail_related_movie_item, viewGroup, false), onItemClickListener);
        }

        @Override // com.movieboxpro.android.base.BaseAdapter
        public void setView(com.movieboxpro.android.base.BaseViewHolder baseViewHolder, int i) {
            if (i < 0 || i > getItemCount() - 1) {
                return;
            }
            MovieDetail.Recommend model = getModel(i);
            Item1ViewHolder item1ViewHolder = (Item1ViewHolder) baseViewHolder;
            item1ViewHolder.tvImdbRating.setVisibility(0);
            if (TextUtils.isEmpty(model.imdb_rating)) {
                SpanUtils.with(item1ViewHolder.tvImdbRating).append("-.-").setFontSize(12, true).setForegroundColor(ContextCompat.getColor(App.getContext(), R.color.white)).setTypeface(Typeface.DEFAULT_BOLD).setShadow(3.0f, 0.0f, 4.0f, Color.parseColor("#80000000")).create();
            } else {
                SpanUtils.with(item1ViewHolder.tvImdbRating).append(model.imdb_rating).setFontSize(12, true).setForegroundColor(ContextCompat.getColor(App.getContext(), R.color.white)).setTypeface(Typeface.DEFAULT_BOLD).setShadow(3.0f, 0.0f, 4.0f, Color.parseColor("#80000000")).create();
            }
            if (MovieDetailActivity.this.activity == null || MovieDetailActivity.this.activity.isFinishing()) {
                return;
            }
            GlideUtils.loadPortraitGifHolder(MovieDetailActivity.this.activity, model.poster, item1ViewHolder.mCover);
            GlideUtils.load(MovieDetailActivity.this.activity, CommonUtils.getMovieTag(model.quality_tag_new), item1ViewHolder.mDesc);
        }
    }

    /* loaded from: classes3.dex */
    static class Item1ViewHolder extends com.movieboxpro.android.base.BaseViewHolder {
        @BindView(R.id.favorite_poster)
        ImageView mCover;
        @BindView(R.id.favorite_desc)
        ImageView mDesc;
        @BindView(R.id.tvImdbRating)
        TextView tvImdbRating;

        Item1ViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view, onItemClickListener);
        }

        public ImageView getImageView() {
            return this.mCover;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPlayFinish(OnPlayFinishEvent onPlayFinishEvent) {
        this.mPresenter.getInfo(this.movie_uid, App.deviceLang);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onChildModeChanged(ChildModeChangedEvent childModeChangedEvent) {
        this.mPresenter.getInfo(this.movie_uid, App.deviceLang);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        addAnima();
        super.onResume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        PsMovieDetail psMovieDetail = this.mPresenter;
        if (psMovieDetail != null) {
            psMovieDetail.detachView();
        }
        this.mHandler.removeCallbacks(this.mRun);
        this.mHandler = null;
        LottieAnimationView lottieAnimationView = this.detailMovieDownload;
        if (lottieAnimationView != null) {
            lottieAnimationView.cancelAnimation();
        }
        ImageView imageView = this.detailMovieAvatar;
        if (imageView != null) {
            imageView.setImageDrawable(null);
        }
        ConstraintLayout constraintLayout = this.detailMovieAvatarBack;
        if (constraintLayout != null) {
            constraintLayout.setBackground(null);
        }
        super.onDestroy();
    }
}
