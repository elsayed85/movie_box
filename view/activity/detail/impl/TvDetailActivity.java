package com.movieboxpro.android.view.activity.detail.impl;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.adorkable.iosdialog.ActionSheetDialog;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.interfaces.SimpleCallback;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.ActorAdapter;
import com.movieboxpro.android.adapter.TvEdisodeDiffCallback;
import com.movieboxpro.android.adapter.TvEpisodeAdapter;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.app.RxManager;
import com.movieboxpro.android.base.BaseActivity;
import com.movieboxpro.android.base.BaseAdapter;
import com.movieboxpro.android.base.LoadView;
import com.movieboxpro.android.event.OnPlayFinishEvent;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.listener.AddWatchPlanListener;
import com.movieboxpro.android.listener.OnFavoriteStatusChangedListener;
import com.movieboxpro.android.model.ActorModel;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.DownloadInfo;
import com.movieboxpro.android.model.DownloadModel;
import com.movieboxpro.android.model.PlayerStrategy;
import com.movieboxpro.android.model.TvSeasonList;
import com.movieboxpro.android.model.common.Feedback;
import com.movieboxpro.android.model.common.Problem;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.presenter.activity.PsTvDetail;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.EventUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.LayoutManagerItemDecoration;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.utils.ParamsUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SettingManager;
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
import com.movieboxpro.android.view.activity.detail.ITvDetail;
import com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity;
import com.movieboxpro.android.view.activity.movie.MovieChooseActivity;
import com.movieboxpro.android.view.activity.review.ReviewActivity;
import com.movieboxpro.android.view.activity.settings.InputChildModePasswordActivity;
import com.movieboxpro.android.view.activity.tv.TvDownloadActivity;
import com.movieboxpro.android.view.activity.user.Login2Activity;
import com.movieboxpro.android.view.activity.videoplayer.TvPlayerActivity;
import com.movieboxpro.android.view.dialog.AddTvWatchDialog;
import com.movieboxpro.android.view.dialog.ChildModeHintDialog;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.FeedbackDialog;
import com.movieboxpro.android.view.dialog.ListDialog;
import com.movieboxpro.android.view.dialog.ReleasedHintDialog;
import com.movieboxpro.android.view.dialog.ReviewDialogFragment;
import com.movieboxpro.android.view.dialog.ScreenManageDialog;
import com.movieboxpro.android.view.dialog.SmartDownloadHintDialog;
import com.movieboxpro.android.view.dialog.TsDefinitionHintDialog;
import com.movieboxpro.android.view.dialog.VideoDescTextDialog;
import com.movieboxpro.android.view.fragment.AddVideoToFavoriteFragment;
import com.movieboxpro.android.view.videocontroller.PopPlayerManager;
import com.movieboxpro.android.view.widget.AntiShakeUtils;
import com.movieboxpro.android.view.widget.DetailMoreActionDialog;
import com.movieboxpro.android.view.widget.MyGridLayoutManager;
import com.movieboxpro.android.view.widget.TransparentClickableSpan;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.validator.Field;
import org.eclipse.jetty.servlet.ServletHandler;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
/* loaded from: classes.dex */
public class TvDetailActivity extends BaseActivity implements ITvDetail, OnFavoriteStatusChangedListener, LoadView {
    public static final String IS_AUTO_NEXT = "IS_AUTO_NEXT";
    public static final String SEASON_TV_LIST = "season_tv_list";
    public static final String TRANSION = "tv_poster";
    public static final String TV_BANNER = "tv_banner";
    public static final String TV_LANG = "tv_lang";
    public static final String TV_POSTER = "tv_poster";
    public static final String TV_TID = "tv_tid";
    public int EpisodeProblem;
    public int SeasonProblem;
    private String Tvtid;
    private ActorAdapter actorAdapter;
    private List<ActorModel> actorModels;
    private String banner;
    @BindView(R.id.detail_tv_avatar)
    ImageView detailTvAvatar;
    @BindView(R.id.detail_tv_banner)
    ImageView detailTvBanner;
    @BindView(R.id.detail_tv_language)
    TextView detailTvLanguage;
    @BindView(R.id.detail_tv_name)
    TextView detailTvName;
    @BindView(R.id.detail_tv_play)
    RelativeLayout detailTvPlay;
    @BindView(R.id.detail_tv_play_btn)
    TextView detailTvPlayBtn;
    @BindView(R.id.detail_tv_play_progress)
    ProgressBar detailTvPlayPGS;
    @BindView(R.id.detail_tv_rate)
    TextView detailTvRate;
    @BindView(R.id.detail_tv_related)
    RelativeLayout detailTvRelated;
    @BindView(R.id.detail_tv_season)
    TextView detailTvSeason;
    @BindView(R.id.detail_tv_tab_season)
    TextView detailTvTabseason;
    @BindView(R.id.tvdetail_sort_name)
    ConstraintLayout detailTvTabsort;
    @BindView(R.id.detail_movie_span)
    LinearLayout detailTvspan;
    @BindView(R.id.tvdetail_drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.tvdetail_sort_recycler)
    RecyclerView drawerRecycler;
    private String enPoster;
    public EpisodeProblemAdapter episodeAdapter;
    public List<Problem> episodeList;
    private View footView;
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
    @BindView(R.id.detail_tv_comments)
    LinearLayout llComments;
    @BindView(R.id.ll_hide_part)
    LinearLayout llHidePart;
    private TvEpisodeAdapter mAdapter;
    private String mImdbUrl;
    @BindView(R.id.iv_arrow)
    ImageView mIvArrow;
    @BindView(R.id.iv_imdb)
    ImageView mIvImdb;
    private PsTvDetail mPresenter;
    @BindView(R.id.detail_tv_recycler)
    RecyclerView mRecycler;
    @BindView(R.id.tv_titlebar_left_icon)
    ImageView mTvBack;
    @BindView(R.id.tv_cast)
    TextView mTvCast;
    @BindView(R.id.tv_category)
    TextView mTvCategory;
    @BindView(R.id.tv_desc)
    TextView mTvDesc;
    @BindView(R.id.tv_director)
    TextView mTvDirector;
    @BindView(R.id.tv_titlebar_title)
    TextView mTvTitle;
    private ListPopupWindow popupMenu;
    private String poster;
    @BindView(R.id.rlTitleBar)
    RelativeLayout rlTitleBar;
    @BindView(R.id.rvActors)
    RecyclerView rvActors;
    public SeasonProblemAdapter seasonAdapter;
    public List<Problem> seasonList;
    @BindView(R.id.tvdetail_sort_choose)
    ImageView sortImageView;
    private TvDetail tvDetail;
    @BindView(R.id.tvDislike)
    TextView tvDislike;
    @BindView(R.id.tvImdbRating)
    TextView tvImdbRating;
    @BindView(R.id.tvLike)
    TextView tvLike;
    @BindView(R.id.tvReviewNum)
    TextView tvReviewNum;
    private Handler mHandler = new Handler();
    private int Season = 1;
    private int episode = 1;
    private String selectLanguage = "";
    OnItemClickListener onItemClickListener = new AnonymousClass9();
    private OnItemLongClickListener onItemLongClickListener = new OnItemLongClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity.10
        @Override // com.chad.library.adapter.base.listener.OnItemLongClickListener
        public boolean onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
            TvDetail.SeasonDetail item = TvDetailActivity.this.mAdapter.getItem(i);
            if (item != null) {
                TvDetailActivity tvDetailActivity = TvDetailActivity.this;
                tvDetailActivity.showAddWatchedFlagDialog(tvDetailActivity.tvDetail.id, String.valueOf(item.season), String.valueOf(item.episode));
                return true;
            }
            return false;
        }
    };
    private Runnable mRun = new Runnable() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity.16
        @Override // java.lang.Runnable
        public void run() {
            if (TvDetailActivity.this.mPresenter == null || TvDetailActivity.this.Tvtid == null) {
                return;
            }
            TvDetailActivity.this.mPresenter.getInfo(TvDetailActivity.this.Tvtid, TvDetailActivity.this.lang);
            TvDetailActivity.this.mPresenter.requestActors(TvDetailActivity.this.Tvtid);
        }
    };
    private boolean firstLoadPoster = true;
    private int seasonSelect = -1;

    /* loaded from: classes3.dex */
    interface OnClickListener extends com.movieboxpro.android.base.OnItemClickListener {
        void onTvClicked(int i, View view);
    }

    /* loaded from: classes3.dex */
    interface OnClickListener2 extends com.movieboxpro.android.base.OnItemClickListener {
        void sort_select(int i);
    }

    @Override // com.movieboxpro.android.view.activity.detail.ITvDetail
    public void addVideoCallback() {
    }

    @Override // com.movieboxpro.android.base.LoadView
    public void hideLoadingPage() {
    }

    @Override // com.movieboxpro.android.base.BaseActivity
    protected boolean isFullScreen() {
        return true;
    }

    @Override // com.movieboxpro.android.base.LoadView
    public void showErrorPage() {
    }

    @Override // com.movieboxpro.android.base.LoadView
    public void showLoadingPage() {
    }

    /* loaded from: classes3.dex */
    public class ItemProblem_Season_Holder_ViewBinding implements Unbinder {
        private ItemProblem_Season_Holder target;

        public ItemProblem_Season_Holder_ViewBinding(ItemProblem_Season_Holder itemProblem_Season_Holder, View view) {
            this.target = itemProblem_Season_Holder;
            itemProblem_Season_Holder.textView = (TextView) Utils.findRequiredViewAsType(view, R.id.item_problem_season, "field 'textView'", TextView.class);
        }

        @Override // butterknife.Unbinder
        public void unbind() {
            ItemProblem_Season_Holder itemProblem_Season_Holder = this.target;
            if (itemProblem_Season_Holder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            itemProblem_Season_Holder.textView = null;
        }
    }

    /* loaded from: classes3.dex */
    public class Item_Season_Holder_ViewBinding implements Unbinder {
        private Item_Season_Holder target;

        public Item_Season_Holder_ViewBinding(Item_Season_Holder item_Season_Holder, View view) {
            this.target = item_Season_Holder;
            item_Season_Holder.textView = (TextView) Utils.findRequiredViewAsType(view, R.id.item_choose_season, "field 'textView'", TextView.class);
        }

        @Override // butterknife.Unbinder
        public void unbind() {
            Item_Season_Holder item_Season_Holder = this.target;
            if (item_Season_Holder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            item_Season_Holder.textView = null;
        }
    }

    /* loaded from: classes3.dex */
    public class Item_Year_Holder_ViewBinding implements Unbinder {
        private Item_Year_Holder target;

        public Item_Year_Holder_ViewBinding(Item_Year_Holder item_Year_Holder, View view) {
            this.target = item_Year_Holder;
            item_Year_Holder.textView = (TextView) Utils.findRequiredViewAsType(view, R.id.item_choose_episode, "field 'textView'", TextView.class);
        }

        @Override // butterknife.Unbinder
        public void unbind() {
            Item_Year_Holder item_Year_Holder = this.target;
            if (item_Year_Holder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            item_Year_Holder.textView = null;
        }
    }

    /* loaded from: classes3.dex */
    public class ItemProblem_Episode_Holder_ViewBinding implements Unbinder {
        private ItemProblem_Episode_Holder target;

        public ItemProblem_Episode_Holder_ViewBinding(ItemProblem_Episode_Holder itemProblem_Episode_Holder, View view) {
            this.target = itemProblem_Episode_Holder;
            itemProblem_Episode_Holder.textView = (TextView) Utils.findRequiredViewAsType(view, R.id.item_problem_episode, "field 'textView'", TextView.class);
            itemProblem_Episode_Holder.tvStatus = (TextView) Utils.findRequiredViewAsType(view, R.id.tvUnRelease, "field 'tvStatus'", TextView.class);
            itemProblem_Episode_Holder.linearLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.item_container, "field 'linearLayout'", LinearLayout.class);
        }

        @Override // butterknife.Unbinder
        public void unbind() {
            ItemProblem_Episode_Holder itemProblem_Episode_Holder = this.target;
            if (itemProblem_Episode_Holder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            itemProblem_Episode_Holder.textView = null;
            itemProblem_Episode_Holder.tvStatus = null;
            itemProblem_Episode_Holder.linearLayout = null;
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

    @OnClick({R.id.detail_tv_season, R.id.iv_arrow})
    public void onInfoClicked(View view) {
        if (view.getId() == R.id.iv_arrow) {
            if (isScreenPortrait()) {
                boolean z = !this.isShowDesc;
                this.isShowDesc = z;
                if (z) {
                    this.mIvArrow.setImageResource(R.mipmap.ic_white_up_arrow);
                    this.llHidePart.setVisibility(0);
                    return;
                }
                this.mIvArrow.setImageResource(R.mipmap.ic_white_down_arrow);
                this.llHidePart.setVisibility(8);
            } else if (this.tvDetail != null) {
                MoreActorsActivity.Companion.start(this, this.Tvtid, "tv", this.tvDetail.title);
            } else {
                MoreActorsActivity.Companion.start(this, this.Tvtid, "tv", "");
            }
        } else if (this.tvDetail != null) {
            MoreActorsActivity.Companion.start(this, this.Tvtid, "tv", this.tvDetail.title);
        } else {
            MoreActorsActivity.Companion.start(this, this.Tvtid, "tv", "");
        }
    }

    @OnClick({R.id.tvdetail_sort_name, R.id.detail_tv_language, R.id.detail_tv_avatar, R.id.detail_movie_span, R.id.detail_tv_play, R.id.ivCollect, R.id.detail_tv_download, R.id.detail_tv_comments, R.id.iv_imdb, R.id.detail_tv_related, R.id.ivTomato, R.id.tv_desc, R.id.detail_tv_banner_background, R.id.llLike, R.id.llDislike, R.id.ivFavorite, R.id.ivMore, R.id.llAddMovieList})
    public void onViewClicked(final View view) {
        List<ActorModel> list;
        final int i;
        ImageView imageView;
        if (this.mPresenter == null || this.tvDetail == null) {
            return;
        }
        r8 = 1;
        final int i2 = 1;
        switch (view.getId()) {
            case R.id.detail_movie_span /* 2131296627 */:
                if (isScreenLandscape()) {
                    if (this.tvDetail == null || (list = this.actorModels) == null || list.size() <= 0) {
                        return;
                    }
                    MoreActorsActivity.Companion.start(this, this.Tvtid, "tv", this.tvDetail.title);
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
            case R.id.detail_tv_avatar /* 2131296629 */:
                new XPopup.Builder(this.context).asImageViewer(this.detailTvAvatar, this.poster, new XpopImageLoader()).show();
                return;
            case R.id.detail_tv_banner_background /* 2131296631 */:
                if (this.tvDetail != null) {
                    new XPopup.Builder(this.context).asImageViewer(this.detailTvBanner, this.tvDetail.banner_mini, new XpopImageLoader()).show();
                    return;
                }
                return;
            case R.id.detail_tv_comments /* 2131296633 */:
                if (!App.isLogin()) {
                    Login2Activity.start(this);
                    return;
                }
                TvDetail tvDetail = this.tvDetail;
                if (tvDetail != null) {
                    ReviewDialogFragment.newInstance(tvDetail.id, 2).show(getSupportFragmentManager(), ReviewDialogFragment.class.getSimpleName());
                    return;
                }
                return;
            case R.id.detail_tv_download /* 2131296635 */:
                if (SettingManager.INSTANCE.neverShowSmartDownloadDialog()) {
                    TvDetail tvDetail2 = this.tvDetail;
                    if (tvDetail2 != null) {
                        if (tvDetail2.episode.size() <= 50) {
                            TvDownloadActivity.Companion companion = TvDownloadActivity.Companion;
                            TvDetail tvDetail3 = this.tvDetail;
                            companion.start(this, tvDetail3, tvDetail3.episode, this.tvDetail.years, this.tvDetail.season, this.Season, this.tvDetail.title);
                            return;
                        }
                        TvDownloadActivity.Companion.start(this, this.tvDetail, new ArrayList(), this.tvDetail.years, this.tvDetail.season, this.Season, this.tvDetail.title);
                        return;
                    }
                    return;
                } else if (!SettingManager.INSTANCE.getSmartDownload()) {
                    SmartDownloadHintDialog smartDownloadHintDialog = new SmartDownloadHintDialog();
                    smartDownloadHintDialog.setListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity.3
                        @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                        public void onClick() {
                            if (TvDetailActivity.this.tvDetail != null) {
                                if (TvDetailActivity.this.tvDetail.episode.size() <= 50) {
                                    TvDownloadActivity.Companion companion2 = TvDownloadActivity.Companion;
                                    TvDetailActivity tvDetailActivity = TvDetailActivity.this;
                                    companion2.start(tvDetailActivity, tvDetailActivity.tvDetail, TvDetailActivity.this.tvDetail.episode, TvDetailActivity.this.tvDetail.years, TvDetailActivity.this.tvDetail.season, TvDetailActivity.this.Season, TvDetailActivity.this.tvDetail.title);
                                    return;
                                }
                                TvDownloadActivity.Companion companion3 = TvDownloadActivity.Companion;
                                TvDetailActivity tvDetailActivity2 = TvDetailActivity.this;
                                companion3.start(tvDetailActivity2, tvDetailActivity2.tvDetail, new ArrayList(), TvDetailActivity.this.tvDetail.years, TvDetailActivity.this.tvDetail.season, TvDetailActivity.this.Season, TvDetailActivity.this.tvDetail.title);
                            }
                        }
                    });
                    smartDownloadHintDialog.show(getSupportFragmentManager(), SmartDownloadHintDialog.class.getSimpleName());
                    return;
                } else {
                    TvDetail tvDetail4 = this.tvDetail;
                    if (tvDetail4 != null) {
                        if (tvDetail4.episode.size() <= 50) {
                            TvDownloadActivity.Companion companion2 = TvDownloadActivity.Companion;
                            TvDetail tvDetail5 = this.tvDetail;
                            companion2.start(this, tvDetail5, tvDetail5.episode, this.tvDetail.years, this.tvDetail.season, this.Season, this.tvDetail.title);
                            return;
                        }
                        TvDownloadActivity.Companion.start(this, this.tvDetail, new ArrayList(), this.tvDetail.years, this.tvDetail.season, this.Season, this.tvDetail.title);
                        return;
                    }
                    return;
                }
            case R.id.detail_tv_language /* 2131296637 */:
                showLanguage();
                return;
            case R.id.detail_tv_play /* 2131296639 */:
                if (AntiShakeUtils.isInvalidClick(view)) {
                    return;
                }
                if (!App.isLogin()) {
                    route(Login2Activity.class);
                    return;
                }
                TvDetail tvDetail6 = this.tvDetail;
                if (tvDetail6 == null || checkChildMode(tvDetail6.content_rating)) {
                    return;
                }
                if ("tc".equalsIgnoreCase(this.tvDetail.quality_tag_new)) {
                    new TsDefinitionHintDialog.Builder(this).setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.-$$Lambda$TvDetailActivity$SgzhD3DgIFU6cppKd5Mcefs6y58
                        @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                        public final void onClick() {
                            TvDetailActivity.this.getVideoDefinition();
                        }
                    }).create().show();
                    return;
                } else {
                    getVideoDefinition();
                    return;
                }
            case R.id.detail_tv_related /* 2131296644 */:
                if (this.drawer.isDrawerOpen(GravityCompat.END)) {
                    return;
                }
                this.drawer.openDrawer(GravityCompat.END);
                return;
            case R.id.ivCollect /* 2131296940 */:
                AddTvWatchDialog newInstance = AddTvWatchDialog.newInstance(this.tvDetail.plan_watched, this.tvDetail.plan_watched_mark_time, this.tvDetail.id);
                newInstance.setListener(new AddWatchPlanListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity.4
                    @Override // com.movieboxpro.android.listener.AddWatchPlanListener
                    public void onWatchChanged(int i3) {
                        TvDetailActivity.this.onWatchedChanged(i3);
                    }

                    @Override // com.movieboxpro.android.listener.AddWatchPlanListener
                    public void onWatchPlan(int i3) {
                        if (i3 == 0) {
                            if (TvDetailActivity.this.tvDetail.plan_watched == 0) {
                                PsTvDetail psTvDetail = TvDetailActivity.this.mPresenter;
                                TvDetailActivity tvDetailActivity = TvDetailActivity.this;
                                psTvDetail.cancelWatch(tvDetailActivity, tvDetailActivity.tvDetail.id);
                            } else if (TvDetailActivity.this.tvDetail.plan_watched == 1) {
                                PsTvDetail psTvDetail2 = TvDetailActivity.this.mPresenter;
                                TvDetailActivity tvDetailActivity2 = TvDetailActivity.this;
                                psTvDetail2.changeWatched(tvDetailActivity2, tvDetailActivity2.tvDetail.id, 0);
                            } else {
                                PsTvDetail psTvDetail3 = TvDetailActivity.this.mPresenter;
                                TvDetailActivity tvDetailActivity3 = TvDetailActivity.this;
                                psTvDetail3.markWatched(tvDetailActivity3, tvDetailActivity3.tvDetail.id, 0);
                            }
                        } else if (TvDetailActivity.this.tvDetail.plan_watched == 1) {
                            PsTvDetail psTvDetail4 = TvDetailActivity.this.mPresenter;
                            TvDetailActivity tvDetailActivity4 = TvDetailActivity.this;
                            psTvDetail4.cancelWatch(tvDetailActivity4, tvDetailActivity4.tvDetail.id);
                        } else if (TvDetailActivity.this.tvDetail.plan_watched == 0) {
                            PsTvDetail psTvDetail5 = TvDetailActivity.this.mPresenter;
                            TvDetailActivity tvDetailActivity5 = TvDetailActivity.this;
                            psTvDetail5.changeWatched(tvDetailActivity5, tvDetailActivity5.tvDetail.id, 1);
                        } else {
                            PsTvDetail psTvDetail6 = TvDetailActivity.this.mPresenter;
                            TvDetailActivity tvDetailActivity6 = TvDetailActivity.this;
                            psTvDetail6.markWatched(tvDetailActivity6, tvDetailActivity6.tvDetail.id, 1);
                        }
                    }
                });
                newInstance.show(getSupportFragmentManager(), AddTvWatchDialog.class.getSimpleName());
                return;
            case R.id.ivFavorite /* 2131296955 */:
                this.mPresenter.addFavorite(this.tvDetail.id, this.tvDetail.is_collect == 1);
                return;
            case R.id.ivMore /* 2131296970 */:
                new DetailMoreActionDialog(this, new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity.1
                    @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                    public void onClick() {
                        if (TvDetailActivity.this.tvDetail != null) {
                            PsTvDetail psTvDetail = TvDetailActivity.this.mPresenter;
                            TvDetailActivity tvDetailActivity = TvDetailActivity.this;
                            psTvDetail.getShareLink(tvDetailActivity, tvDetailActivity.tvDetail.id);
                        }
                    }
                }, new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity.2
                    @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                    public void onClick() {
                        if (AntiShakeUtils.isInvalidClick(view)) {
                            return;
                        }
                        if (App.isLogin()) {
                            TvDetailActivity.this.mPresenter.getFeedBack();
                        } else {
                            TvDetailActivity.this.route(Login2Activity.class);
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
                if (this.tvDetail.like_status == 1 || this.tvDetail.like_status == 0) {
                    i = 2;
                } else {
                    int i3 = this.tvDetail.like_status;
                    i = 0;
                }
                ((ObservableSubscribeProxy) HttpRequest.post("Movie_like").param("box_type", (Object) 2).param("mid", this.tvDetail.id).param("season", (Object) 0).param("episode", (Object) 0).param(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(i)).asRequest().compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity.6
                    @Override // io.reactivex.Observer
                    public void onComplete() {
                    }

                    @Override // io.reactivex.Observer
                    public void onSubscribe(Disposable disposable) {
                        TvDetailActivity.this.showLoadLoading();
                    }

                    @Override // io.reactivex.Observer
                    public void onNext(String str) {
                        TvDetailActivity.this.hideLoadLoading();
                        TvDetailActivity.this.tvDetail.like_status = i;
                        int i4 = i;
                        if (i4 == 2) {
                            TvDetailActivity.this.tvDetail.dislike_total++;
                        } else if (i4 == 0) {
                            TvDetailActivity.this.tvDetail.dislike_total--;
                        }
                        TvDetailActivity tvDetailActivity = TvDetailActivity.this;
                        tvDetailActivity.initLikeStatus(tvDetailActivity.tvDetail.like_status);
                    }

                    @Override // io.reactivex.Observer
                    public void onError(Throwable th) {
                        TvDetailActivity.this.hideLoadLoading();
                        ToastUtils.showShort("Load failed:" + th.getMessage());
                    }
                });
                return;
            case R.id.llLike /* 2131297190 */:
                if (this.tvDetail.like_status == 1 || (this.tvDetail.like_status != 0 && this.tvDetail.like_status != 2)) {
                    i2 = 0;
                }
                ((ObservableSubscribeProxy) HttpRequest.post("Movie_like").param("box_type", (Object) 2).param("mid", this.tvDetail.id).param("season", (Object) 0).param("episode", (Object) 0).param(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(i2)).asRequest().compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity.5
                    @Override // io.reactivex.Observer
                    public void onComplete() {
                    }

                    @Override // io.reactivex.Observer
                    public void onSubscribe(Disposable disposable) {
                        TvDetailActivity.this.showLoadLoading();
                    }

                    @Override // io.reactivex.Observer
                    public void onNext(String str) {
                        TvDetailActivity.this.hideLoadLoading();
                        TvDetailActivity.this.tvDetail.like_status = i2;
                        int i4 = i2;
                        if (i4 == 1) {
                            TvDetailActivity.this.tvDetail.like_total++;
                        } else if (i4 == 0) {
                            TvDetailActivity.this.tvDetail.like_total--;
                        }
                        TvDetailActivity tvDetailActivity = TvDetailActivity.this;
                        tvDetailActivity.initLikeStatus(tvDetailActivity.tvDetail.like_status);
                    }

                    @Override // io.reactivex.Observer
                    public void onError(Throwable th) {
                        TvDetailActivity.this.hideLoadLoading();
                        ToastUtils.showShort("Load failed:" + th.getMessage());
                    }
                });
                return;
            case R.id.tv_desc /* 2131298270 */:
                new VideoDescTextDialog.Builder(this).setContent(this.mTvDesc.getText().toString()).create().show();
                return;
            case R.id.tvdetail_sort_name /* 2131298343 */:
                this.sortImageView.setSelected(!imageView.isSelected());
                boolean z2 = PrefsUtils.getInstance().getBoolean(Constant.Prefs.TVDETAIL_SORT + this.Tvtid, false);
                PrefsUtils.getInstance().putBoolean(Constant.Prefs.TVDETAIL_SORT + this.Tvtid, !z2);
                Collections.reverse(this.mAdapter.getData());
                this.mAdapter.notifyDataSetChanged();
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goTomatoWebsite() {
        if (this.activity == null || this.activity.isFinishing() || this.tvDetail == null) {
            return;
        }
        SystemUtils.startBrowser(this.activity, this.tvDetail.tomato_url);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getVideoDefinition() {
        if (this.episode == this.tvDetail.episode.size()) {
            this.mPresenter.getPlayPath(this.Tvtid, this.episode, this.Season, false);
        } else if (!TextUtils.isEmpty(this.tvDetail.history) && !TextUtils.equals(this.tvDetail.history, Field.TOKEN_INDEXED)) {
            if (((JSONObject) JSONObject.parse(this.tvDetail.history)).getInteger(DownloadInfo.DOWNLOAD_OVER).intValue() == 1) {
                this.mPresenter.getPlayPath(this.Tvtid, this.episode + 1, this.Season, true);
            } else {
                this.mPresenter.getPlayPath(this.Tvtid, this.episode, this.Season, false);
            }
        } else {
            this.mPresenter.getPlayPath(this.Tvtid, this.episode, this.Season, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkChildMode(String str) {
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.CHILD_MODE, false) && CommonUtils.isChildModeVideo(str)) {
            ChildModeHintDialog childModeHintDialog = new ChildModeHintDialog(this);
            childModeHintDialog.setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity.7
                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                public void onClick() {
                    InputChildModePasswordActivity.Companion.start(TvDetailActivity.this, 100);
                }
            });
            childModeHintDialog.show();
            return true;
        }
        return false;
    }

    public static void start(Context context, String str) {
        Intent intent = new Intent(context, TvDetailActivity.class);
        intent.putExtra(TV_TID, str);
        context.startActivity(intent);
    }

    public static void start(Context context, String str, String str2, String str3) {
        Intent intent = new Intent(context, TvDetailActivity.class);
        intent.putExtra(TV_TID, str);
        intent.putExtra(TV_BANNER, str2);
        intent.putExtra("tv_poster", str3);
        context.startActivity(intent);
    }

    public static void startWithFlag(Context context, String str) {
        Intent intent = new Intent(context, TvDetailActivity.class);
        intent.setFlags(268435456);
        intent.putExtra(TV_TID, str);
        context.startActivity(intent);
    }

    private void showLanguage() {
        BaseQuickAdapter<BaseMediaModel.MoreLanguage, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<BaseMediaModel.MoreLanguage, BaseViewHolder>(R.layout.layout_language_menu_item, this.tvDetail.language) { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity.8
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder baseViewHolder, BaseMediaModel.MoreLanguage moreLanguage) {
                baseViewHolder.setText(R.id.language_name_text, moreLanguage.title);
                baseViewHolder.setText(R.id.language_name_lang, moreLanguage.lang);
            }
        };
        baseQuickAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.-$$Lambda$TvDetailActivity$CPbWc_prgxrrLfwjUWjvSzPl9zg
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                TvDetailActivity.this.lambda$showLanguage$0$TvDetailActivity(baseQuickAdapter2, view, i);
            }
        });
        ListDialog create = new ListDialog.Builder(this).setAdapter(baseQuickAdapter).setTitleVisible(true).create();
        this.listDialog = create;
        create.show();
    }

    public /* synthetic */ void lambda$showLanguage$0$TvDetailActivity(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        if (this.mPresenter != null && this.Tvtid != null) {
            String str = this.tvDetail.language.get(i).lang;
            this.mPresenter.getInfo(this.Tvtid, (str == null || TextUtils.equals(str, "")) ? ServletHandler.__DEFAULT_SERVLET : this.tvDetail.language.get(i).lang);
            if (TextUtils.isEmpty(str)) {
                this.selectLanguage = ServletHandler.__DEFAULT_SERVLET;
            } else {
                this.selectLanguage = str;
            }
            this.lang = str;
        }
        ListDialog listDialog = this.listDialog;
        if (listDialog != null) {
            listDialog.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity$9  reason: invalid class name */
    /* loaded from: classes3.dex */
    public class AnonymousClass9 implements OnItemClickListener {
        AnonymousClass9() {
        }

        @Override // com.chad.library.adapter.base.listener.OnItemClickListener
        public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
            if (App.isLogin()) {
                TvDetail.SeasonDetail item = TvDetailActivity.this.mAdapter.getItem(i);
                if (item != null) {
                    final boolean z = true;
                    if (item.code_file != 1) {
                        final boolean z2 = false;
                        if (item.released_timestamp > System.currentTimeMillis() / 1000) {
                            z = (TvDetailActivity.this.tvDetail == null || TvDetailActivity.this.tvDetail.is_collect != 1) ? false : false;
                            item.setStatus("Unreleased");
                            new ReleasedHintDialog.Builder(TvDetailActivity.this).setPositiveText(z).setBoxType(2).setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.-$$Lambda$TvDetailActivity$9$nInD1w_cSaE0WeFUYLNSXP6Mvbc
                                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                                public final void onClick() {
                                    TvDetailActivity.AnonymousClass9.this.lambda$onItemClick$0$TvDetailActivity$9(z);
                                }
                            }).create().show();
                            return;
                        } else if (item.released_timestamp == 0) {
                            if (TvDetailActivity.this.tvDetail != null && TvDetailActivity.this.tvDetail.is_collect == 1) {
                                z2 = true;
                            }
                            item.setStatus("Unknown");
                            new ReleasedHintDialog.Builder(TvDetailActivity.this).setPositiveText(z2).setUnknown(true).setBoxType(2).setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.-$$Lambda$TvDetailActivity$9$cQtNwXK3B9PJke4cFjxndDN7New
                                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                                public final void onClick() {
                                    TvDetailActivity.AnonymousClass9.this.lambda$onItemClick$1$TvDetailActivity$9(z2);
                                }
                            }).create().show();
                            return;
                        } else if (item.released_timestamp <= 0 || item.released_timestamp >= System.currentTimeMillis() / 1000) {
                            return;
                        } else {
                            TvDetailActivity.this.showToast("Not Available");
                            return;
                        }
                    }
                }
                if (TvDetailActivity.this.tvDetail != null) {
                    TvDetailActivity tvDetailActivity = TvDetailActivity.this;
                    if (tvDetailActivity.checkChildMode(tvDetailActivity.tvDetail.content_rating)) {
                        return;
                    }
                }
                CastSession castSession = null;
                try {
                    castSession = CastContext.getSharedInstance(TvDetailActivity.this.context).getSessionManager().getCurrentCastSession();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (castSession == null || !castSession.isConnected()) {
                    if (item != null) {
                        TvDetailActivity.this.mPresenter.getPath(TvDetailActivity.this.Tvtid, item.episode, item.season, i, false);
                        return;
                    }
                    return;
                } else if (item != null) {
                    TvDetailActivity.this.mPresenter.getPath(TvDetailActivity.this.Tvtid, item.episode, item.season, i, false);
                    return;
                } else {
                    return;
                }
            }
            TvDetailActivity.this.route(Login2Activity.class);
        }

        public /* synthetic */ void lambda$onItemClick$0$TvDetailActivity$9(boolean z) {
            if (z) {
                return;
            }
            TvDetailActivity.this.mPresenter.addFavorite(TvDetailActivity.this.tvDetail.id, false);
        }

        public /* synthetic */ void lambda$onItemClick$1$TvDetailActivity$9(boolean z) {
            if (z) {
                return;
            }
            TvDetailActivity.this.mPresenter.addFavorite(TvDetailActivity.this.tvDetail.id, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAddWatchedFlagDialog(final String str, final String str2, final String str3) {
        new ActionSheetDialog(this).builder().setCancelable(true).setCanceledOnTouchOutside(true).addSheetItems(Arrays.asList(getResources().getStringArray(R.array.AddWatchedFlagArray)), new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity.11
            @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
            public void onClick(int i) {
                String str4;
                String str5;
                if (App.isLogin()) {
                    int i2 = 1;
                    int i3 = i - 1;
                    String str6 = "";
                    if (i3 != 0) {
                        if (i3 == 1) {
                            str5 = str2;
                        } else if (i3 != 2) {
                            if (i3 == 3) {
                                str6 = str2;
                                str4 = str3;
                            } else if (i3 != 4) {
                                str4 = "";
                            } else {
                                str5 = str2;
                                i2 = 0;
                            }
                            i2 = 0;
                        } else {
                            str4 = "";
                        }
                        str6 = str5;
                        str4 = "";
                    } else {
                        str6 = str2;
                        str4 = str3;
                    }
                    TvDetailActivity.this.mPresenter.addWatchedFlag(str, i2, str6, str4);
                    return;
                }
                TvDetailActivity.this.route(Login2Activity.class);
            }
        }).show();
    }

    @Override // com.movieboxpro.android.base.BaseActivity, com.movieboxpro.android.view.listener.IViewController
    public void initPresenter() {
        if (this.mPresenter == null) {
            this.mPresenter = new PsTvDetail(this.activity, this);
        }
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public View loadView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.activity_tv_details2, viewGroup, false);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initView() {
        setTitleBar(false);
        this.mTvTitle.setVisibility(4);
        if (this.mAdapter == null) {
            TvEpisodeAdapter tvEpisodeAdapter = new TvEpisodeAdapter(new ArrayList());
            this.mAdapter = tvEpisodeAdapter;
            tvEpisodeAdapter.setDiffCallback(new TvEdisodeDiffCallback());
            this.mRecycler.setLayoutManager(new LinearLayoutManager(this));
            this.mRecycler.setAdapter(this.mAdapter);
            this.mAdapter.setOnItemClickListener(this.onItemClickListener);
            this.mAdapter.setOnItemLongClickListener(this.onItemLongClickListener);
            this.mAdapter.addChildClickViewIds(R.id.llLike, R.id.llDislike);
            this.mAdapter.setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.-$$Lambda$TvDetailActivity$EFrHR8dsW4Y43d-2YeD_lQRE2h4
                @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
                public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    TvDetailActivity.this.lambda$initView$1$TvDetailActivity(baseQuickAdapter, view, i);
                }
            });
            RecyclerView.ItemAnimator itemAnimator = this.mRecycler.getItemAnimator();
            if (itemAnimator instanceof SimpleItemAnimator) {
                ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
            }
        }
        DensityUtils.addMarginTopEqualStatusBarHeight(this.rlTitleBar, this);
        this.mTvBack.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.-$$Lambda$TvDetailActivity$goa1IBFY5WiPlCkzCjvO8BMdLBY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TvDetailActivity.this.lambda$initView$2$TvDetailActivity(view);
            }
        });
        if (isScreenLandscape()) {
            this.llHidePart.setVisibility(0);
        } else {
            this.llHidePart.setVisibility(8);
        }
    }

    public /* synthetic */ void lambda$initView$1$TvDetailActivity(BaseQuickAdapter baseQuickAdapter, View view, final int i) {
        final TvDetail.SeasonDetail item = this.mAdapter.getItem(i);
        final int i2 = 2;
        final int i3 = 1;
        if (view.getId() == R.id.llLike) {
            if (item.like_status == 1 || (item.like_status != 0 && item.like_status != 2)) {
                i3 = 0;
            }
            ((ObservableSubscribeProxy) HttpRequest.post("Movie_like").param("box_type", (Object) 3).param("mid", this.tvDetail.id).param("season", Integer.valueOf(item.season)).param("episode", Integer.valueOf(item.episode)).param(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(i3)).asRequest().compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity.12
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable disposable) {
                    TvDetailActivity.this.showLoadLoading();
                }

                @Override // io.reactivex.Observer
                public void onNext(String str) {
                    TvDetailActivity.this.hideLoadLoading();
                    item.like_status = i3;
                    int i4 = i3;
                    if (i4 == 1) {
                        item.like_total++;
                    } else if (i4 == 0) {
                        item.like_total--;
                    }
                    TvDetailActivity.this.mAdapter.notifyItemChanged(i);
                }

                @Override // io.reactivex.Observer
                public void onError(Throwable th) {
                    TvDetailActivity.this.hideLoadLoading();
                    ToastUtils.showShort("Load failed:" + th.getMessage());
                }
            });
        } else if (view.getId() == R.id.llDislike) {
            if (item.like_status != 1 && item.like_status != 0) {
                int i4 = item.like_status;
                i2 = 0;
            }
            ((ObservableSubscribeProxy) HttpRequest.post("Movie_like").param("box_type", (Object) 3).param("mid", this.tvDetail.id).param("season", Integer.valueOf(item.season)).param("episode", Integer.valueOf(item.episode)).param(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(i2)).asRequest().compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity.13
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable disposable) {
                    TvDetailActivity.this.showLoadLoading();
                }

                @Override // io.reactivex.Observer
                public void onNext(String str) {
                    TvDetailActivity.this.hideLoadLoading();
                    item.like_status = i2;
                    int i5 = i2;
                    if (i5 == 2) {
                        item.dislike_total++;
                    } else if (i5 == 0) {
                        TvDetail.SeasonDetail seasonDetail = item;
                        seasonDetail.dislike_total--;
                    }
                    TvDetailActivity.this.mAdapter.notifyItemChanged(i);
                }

                @Override // io.reactivex.Observer
                public void onError(Throwable th) {
                    TvDetailActivity.this.hideLoadLoading();
                    ToastUtils.showShort("Load failed:" + th.getMessage());
                }
            });
        }
    }

    public /* synthetic */ void lambda$initView$2$TvDetailActivity(View view) {
        finish();
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initData() {
        EventUtils.event("进入电视详情");
        this.Tvtid = getStringParam(TV_TID, "64");
        this.banner = getStringParam(TV_BANNER, "");
        this.lang = App.deviceLang;
        this.poster = getStringParam("tv_poster", "");
        GlideUtils.loadSkipMemory(this.activity, this.banner, this.detailTvBanner);
        if (!TextUtils.isEmpty(this.poster)) {
            GlideUtils.loadCornerPortraitGifHolder(this.activity, this.poster, this.detailTvAvatar, 8);
        }
        this.mHandler.postDelayed(this.mRun, 200L);
        getLoadingPopupView(new SimpleCallback() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity.14
            @Override // com.lxj.xpopup.interfaces.SimpleCallback, com.lxj.xpopup.interfaces.XPopupCallback
            public boolean onBackPressed(BasePopupView basePopupView) {
                if (TvDetailActivity.this.mPresenter != null) {
                    TvDetailActivity.this.mPresenter.cancelRequest();
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

    @Override // com.movieboxpro.android.view.activity.detail.ITvDetail
    public void showBottomMovieListDialog(List<MovieListModel.MovieListItem> list) {
        AddVideoToFavoriteFragment newInstance = AddVideoToFavoriteFragment.newInstance(list, this.tvDetail.id, this.tvDetail.is_collect == 1, 2, this.tvDetail.plan_watched);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.add(newInstance, "AddVideoToFavoriteFragment");
        beginTransaction.commitAllowingStateLoss();
        beginTransaction.show(newInstance);
    }

    @Override // com.movieboxpro.android.view.activity.detail.ITvDetail
    public void markWatchedComplete() {
        this.mPresenter.getInfo(this.Tvtid, this.lang);
    }

    @Override // com.movieboxpro.android.view.activity.detail.ITvDetail
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
        this.actorAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.-$$Lambda$TvDetailActivity$XB2_k28_xpSNk-R5bjipMIIzw50
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                TvDetailActivity.this.lambda$showActors$3$TvDetailActivity(baseQuickAdapter, view, i);
            }
        });
    }

    public /* synthetic */ void lambda$showActors$3$TvDetailActivity(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        ActorModel item = this.actorAdapter.getItem(i);
        if (item != null) {
            if (item.isMore()) {
                MoreActorsActivity.Companion.start(this, this.Tvtid, "tv", this.tvDetail.title);
            } else {
                ActorDetailActivity.Companion.start(this, item.getActor_id());
            }
        }
    }

    @Override // com.movieboxpro.android.view.activity.detail.ITvDetail
    public void showReviewCount(String str) {
        this.tvReviewNum.setText(str);
    }

    @Override // com.movieboxpro.android.view.activity.detail.ITvDetail
    public void showPoster(String str) {
        this.enPoster = str;
        TvDetail tvDetail = this.tvDetail;
        if (tvDetail != null) {
            if (tvDetail.language != null && this.tvDetail.language.size() > 0) {
                ReviewActivity.Companion.start(this, this.tvDetail.id, 2, this.tvDetail.language.get(0).title, str, this.tvDetail.year, this.tvDetail.imdb_rating);
            } else {
                ReviewActivity.Companion.start(this, this.tvDetail.id, 2, this.tvDetail.title, str, this.tvDetail.year, this.tvDetail.imdb_rating);
            }
        }
    }

    @Override // com.movieboxpro.android.view.activity.detail.ITvDetail
    public void showScreenManage(final String str, ArrayList<DeviceModelResponse.DeviceModel> arrayList, String str2, final int i, final int i2, final int i3, final boolean z) {
        ScreenManageDialog newInstance = ScreenManageDialog.newInstance(arrayList, str2);
        newInstance.setListener(new ScreenManageDialog.OnStopDeviceListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity.15
            @Override // com.movieboxpro.android.view.dialog.ScreenManageDialog.OnStopDeviceListener
            public void onStopDevice() {
                TvDetailActivity.this.mPresenter.getPath(str, i2, i, i3, z);
            }
        });
        newInstance.show(getSupportFragmentManager(), ScreenManageDialog.class.getSimpleName());
    }

    @Override // com.movieboxpro.android.view.activity.detail.ITvDetail
    public void onWatchedChanged(int i) {
        this.tvDetail.plan_watched = i;
        this.tvDetail.plan_watched_mark_time = System.currentTimeMillis() / 1000;
        if (i == -1) {
            this.ivCollect.setImageResource(R.mipmap.ic_watch_plan);
        } else if (i == 0) {
            this.ivCollect.setImageResource(R.mipmap.ic_waiting);
        } else if (i == 1) {
            this.ivCollect.setImageResource(R.mipmap.ic_watched);
        }
    }

    @Override // com.movieboxpro.android.view.activity.detail.ITvDetail
    public void setDateList(List<TvDetail.SeasonDetail> list) {
        this.tvDetail.episode = list;
        if (list != null) {
            PrefsUtils prefsUtils = PrefsUtils.getInstance();
            if (prefsUtils.getBoolean(Constant.Prefs.TVDETAIL_SORT + this.Tvtid, false)) {
                Collections.reverse(list);
            }
            this.mAdapter.setList(list);
        }
    }

    @Override // com.movieboxpro.android.view.activity.detail.ITvDetail
    public void getVideoInfo(TvDetail tvDetail) {
        String str;
        this.tvDetail = tvDetail;
        this.mImdbUrl = tvDetail.imdb_link;
        if (TextUtils.isEmpty(this.poster)) {
            GlideUtils.loadCornerPortraitGifHolder(this.activity, tvDetail.poster, this.detailTvAvatar, 8);
        } else if (!this.firstLoadPoster) {
            GlideUtils.loadCornerPortraitGifHolder(this.activity, tvDetail.poster, this.detailTvAvatar, 8);
            GlideUtils.loadSkipMemory(this.activity, tvDetail.banner_mini, this.detailTvBanner);
        }
        this.firstLoadPoster = false;
        this.poster = tvDetail.poster;
        if (!TextUtils.isEmpty(this.tvDetail.history) && !TextUtils.equals(this.tvDetail.history, Field.TOKEN_INDEXED)) {
            JSONObject jSONObject = (JSONObject) JSONObject.parse(this.tvDetail.history);
            this.episode = jSONObject.getInteger("episode").intValue();
            this.Season = jSONObject.getInteger("season").intValue();
            int intValue = jSONObject.getInteger("seconds").intValue();
            this.mPresenter.SaveInDao(this.tvDetail, intValue, this.Season, this.episode);
            if (jSONObject.getInteger(DownloadInfo.DOWNLOAD_OVER).intValue() == 1) {
                TvDetail tvDetail2 = this.tvDetail;
                if (tvDetail2 != null && tvDetail2.episode != null) {
                    if (this.tvDetail.episode.size() == this.episode) {
                        int i = this.Season;
                        if (i > 1000) {
                            this.detailTvPlayBtn.setText(String.format("  LAST PLAY Y%s:E%s", Integer.valueOf(i), Integer.valueOf(this.episode)));
                        } else {
                            this.detailTvPlayBtn.setText(String.format("  LAST PLAY S%s:E%s", Integer.valueOf(i), Integer.valueOf(this.episode)));
                        }
                    } else {
                        int i2 = this.Season;
                        if (i2 > 1000) {
                            this.detailTvPlayBtn.setText(String.format("  CONTINUE PLAY Y%s:E%s", Integer.valueOf(i2), Integer.valueOf(this.episode + 1)));
                        } else {
                            this.detailTvPlayBtn.setText(String.format("  CONTINUE PLAY S%s:E%s", Integer.valueOf(i2), Integer.valueOf(this.episode + 1)));
                        }
                    }
                }
            } else if (this.Season > 1000) {
                TextView textView = this.detailTvPlayBtn;
                StringBuilder sb = new StringBuilder();
                sb.append("  RESUME Y");
                sb.append(this.Season);
                sb.append(":E");
                sb.append(this.episode);
                sb.append("  ");
                sb.append(TimeUtils.getTime(intValue));
                textView.setText(sb);
            } else {
                TextView textView2 = this.detailTvPlayBtn;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("  RESUME S");
                sb2.append(this.Season);
                sb2.append(":E");
                sb2.append(this.episode);
                sb2.append("  ");
                sb2.append(TimeUtils.getTime(intValue));
                textView2.setText(sb2);
            }
            if (this.activity != null && !this.activity.isFinishing()) {
                this.detailTvPlayBtn.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this.activity, R.drawable.ic_movie_play), (Drawable) null, (Drawable) null, (Drawable) null);
            }
        } else {
            TextView textView3 = this.detailTvPlayBtn;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("  PLAY");
            textView3.setText(sb3);
            if (this.activity != null && !this.activity.isFinishing()) {
                this.detailTvPlayBtn.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this.activity, R.drawable.ic_movie_play), (Drawable) null, (Drawable) null, (Drawable) null);
            }
        }
        if (TextUtils.isEmpty(this.selectLanguage)) {
            if (this.tvDetail.language.size() > 1) {
                this.detailTvLanguage.setVisibility(0);
                String str2 = App.deviceLang;
                Iterator<BaseMediaModel.MoreLanguage> it = this.tvDetail.language.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    } else if (str2.equals(it.next().lang)) {
                        this.detailTvLanguage.setText(str2);
                        break;
                    } else {
                        this.detailTvLanguage.setText("en");
                    }
                }
            } else {
                this.detailTvLanguage.setVisibility(8);
            }
        } else if (ServletHandler.__DEFAULT_SERVLET.equals(this.selectLanguage)) {
            this.detailTvLanguage.setText("");
        } else {
            this.detailTvLanguage.setText(this.selectLanguage);
        }
        this.mTvTitle.setText(this.tvDetail.title);
        if (this.tvDetail.is_collect == 1) {
            this.ivFavorite.setImageResource(R.mipmap.ic_collected);
        } else {
            this.ivFavorite.setImageResource(R.mipmap.ic_add_to_favorite);
        }
        if (this.tvDetail.plan_watched == -1) {
            this.ivCollect.setImageResource(R.mipmap.ic_watch_plan);
        } else if (this.tvDetail.plan_watched == 0) {
            this.ivCollect.setImageResource(R.mipmap.ic_waiting);
        } else if (this.tvDetail.plan_watched == 1) {
            this.ivCollect.setImageResource(R.mipmap.ic_watched);
        }
        this.detailTvName.setText(this.tvDetail.title);
        this.detailTvName.requestLayout();
        TextView textView4 = this.detailTvSeason;
        StringBuilder sb4 = new StringBuilder();
        sb4.append(this.tvDetail.season_episode);
        textView4.setText(sb4);
        this.mTvDesc.setText(this.tvDetail.description);
        this.mTvDirector.setText(String.format("Director:%s", this.tvDetail.director));
        this.mTvCast.setText(String.format("Cast:%s", this.tvDetail.actors));
        this.mTvCategory.setText(String.format("Category:%s", this.tvDetail.cats));
        this.ivStar.setVisibility(0);
        if (TextUtils.isEmpty(this.tvDetail.imdb_rating)) {
            CommonUtils.textShadow(this.tvImdbRating, "-.-", 12, R.color.white);
        } else {
            CommonUtils.textShadow(this.tvImdbRating, this.tvDetail.imdb_rating, 12, R.color.white);
        }
        String[] split = this.tvDetail.cats.split(",");
        String str3 = split.length > 0 ? split[0] : "";
        if (this.tvDetail.tomato_meter == 0) {
            str = "-.-%";
        } else {
            str = this.tvDetail.tomato_meter + "%";
        }
        SpanUtils.with(this.detailTvRate).append(str3.toUpperCase()).setFontSize(12, true).setForegroundColor(ContextCompat.getColor(this, R.color.white_30alpha)).appendSpace(35).append(this.tvDetail.year).setFontSize(12, true).setForegroundColor(ContextCompat.getColor(this, R.color.white_30alpha)).appendSpace(35).append(this.tvDetail.content_rating).setFontSize(12, true).setForegroundColor(ContextCompat.getColor(this, R.color.white_30alpha)).appendSpace(35).appendImage(CommonUtils.getTomatoImg(this.tvDetail.tomato_meter), 2).setClickSpan(new TransparentClickableSpan() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity.18
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                TvDetailActivity.this.goTomatoWebsite();
            }
        }).appendSpace(10).append(str).setFontSize(12, true).setForegroundColor(ContextCompat.getColor(this, R.color.white_30alpha)).setClickSpan(new TransparentClickableSpan() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity.17
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                TvDetailActivity.this.goTomatoWebsite();
            }
        }).create();
        if (TextUtils.isEmpty(this.banner)) {
            GlideUtils.loads(this.activity, this.tvDetail.banner_mini, this.detailTvBanner);
        }
        int i3 = this.Season;
        if (i3 > 1000) {
            this.detailTvTabseason.setText(String.format("Year %s", Integer.valueOf(i3)));
        } else {
            this.detailTvTabseason.setText(String.format("Season %s/%s", Integer.valueOf(i3), Integer.valueOf(this.tvDetail.season.length)));
        }
        initDrawer();
        int i4 = PrefsUtils.getInstance().getInt(Constant.Prefs.TVDETAIL_YEARS + this.Tvtid, -1);
        if (this.Season == i4 || i4 == -1) {
            if (this.tvDetail.episode != null) {
                if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.TVDETAIL_SORT + this.Tvtid, false)) {
                    Collections.reverse(this.tvDetail.episode);
                }
                this.mAdapter.setDiffNewData(this.tvDetail.episode);
            }
            int i5 = this.Season;
            if (i5 > 1000) {
                this.detailTvTabseason.setText(String.format("Year %s", Integer.valueOf(i5)));
            } else {
                this.detailTvTabseason.setText(String.format("Season %s/%s", Integer.valueOf(i5), Integer.valueOf(this.tvDetail.season.length)));
            }
        } else if (i4 < 1000) {
            this.mPresenter.getSeason(this.Tvtid, i4, null, 0, App.getUserData().uid_v2);
            this.detailTvTabseason.setText(String.format("Season %s/%s", Integer.valueOf(i4), Integer.valueOf(this.tvDetail.season.length)));
        } else {
            this.mPresenter.getSeason(this.Tvtid, -1, String.valueOf(i4), 0, App.getUserData().uid_v2);
            this.detailTvTabseason.setText("Year " + i4);
        }
        if (this.tvDetail.code_file == 0 && this.tvDetail.released_timestamp > 0 && this.tvDetail.released_timestamp > System.currentTimeMillis() / 1000) {
            switchPlayButtonStage(3);
        }
        if (this.footView == null) {
            View inflate = LayoutInflater.from(this).inflate(R.layout.tv_foot_view, (ViewGroup) this.mRecycler.getParent(), false);
            this.footView = inflate;
            this.mAdapter.addFooterView(inflate);
        }
        initLikeStatus(this.tvDetail.like_status);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initLikeStatus(int i) {
        if (i == 0) {
            this.ivLike.setImageResource(R.mipmap.ic_like);
            this.ivDislike.setImageResource(R.mipmap.ic_yellow_dislike);
            this.tvLike.setTextColor(getResources().getColor(R.color.color_text_light_pressed));
            if (this.tvDetail.like_total == 0) {
                this.tvLike.setText("LIKE");
            } else {
                this.tvLike.setText(String.valueOf(this.tvDetail.like_total));
            }
            this.tvDislike.setTextColor(getResources().getColor(R.color.color_text_light_pressed));
            if (this.tvDetail.dislike_total == 0) {
                this.tvDislike.setText("DISLIKE");
            } else {
                this.tvDislike.setText(String.valueOf(this.tvDetail.dislike_total));
            }
        } else if (i == 1) {
            this.ivLike.setImageResource(R.mipmap.ic_yellow_liked);
            this.ivDislike.setImageResource(R.mipmap.ic_yellow_dislike);
            this.tvLike.setTextColor(Color.parseColor("#FFEFC001"));
            this.tvLike.setText(String.valueOf(this.tvDetail.like_total));
            this.tvDislike.setTextColor(getResources().getColor(R.color.color_text_light_pressed));
            if (this.tvDetail.dislike_total == 0) {
                this.tvDislike.setText("DISLIKE");
            } else {
                this.tvDislike.setText(String.valueOf(this.tvDetail.dislike_total));
            }
        } else if (i == 2) {
            this.ivLike.setImageResource(R.mipmap.ic_like);
            this.ivDislike.setImageResource(R.mipmap.ic_yellow_disliked);
            this.tvDislike.setTextColor(Color.parseColor("#FFEFC001"));
            this.tvDislike.setText(String.valueOf(this.tvDetail.dislike_total));
            this.tvLike.setTextColor(getResources().getColor(R.color.color_text_light_pressed));
            if (this.tvDetail.like_total == 0) {
                this.tvLike.setText("LIKE");
            } else {
                this.tvLike.setText(String.valueOf(this.tvDetail.like_total));
            }
        }
    }

    @Override // com.movieboxpro.android.view.activity.detail.ITvDetail
    public void switchPlayButtonStage(int i) {
        if (i == 0) {
            this.detailTvPlayPGS.setVisibility(0);
            TextView textView = this.detailTvPlayBtn;
            StringBuilder sb = new StringBuilder();
            sb.append("LOADING...");
            textView.setText(sb);
            this.detailTvPlayBtn.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
        } else if (i == 1) {
            this.detailTvPlayPGS.setVisibility(8);
        } else if (i == 2) {
            TextView textView2 = this.detailTvPlayBtn;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Error...");
            textView2.setText(sb2);
            this.detailTvPlayPGS.setVisibility(8);
            this.detailTvPlayBtn.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
        } else if (i != 3) {
        } else {
            TvDetail tvDetail = this.tvDetail;
            if (tvDetail != null) {
                this.detailTvPlayBtn.setText(String.format("Released in %s", tvDetail.released));
            }
            this.detailTvPlayBtn.setTextColor(ContextCompat.getColor(App.getContext(), R.color.white_30alpha));
            this.detailTvPlayPGS.setVisibility(8);
            this.detailTvPlayBtn.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
            this.detailTvPlay.setBackgroundResource(R.drawable.comming_soon_bg_shape);
            this.detailTvPlay.setEnabled(false);
        }
    }

    @Override // com.movieboxpro.android.view.activity.detail.ITvDetail
    public void setFavorite(boolean z) {
        if (z) {
            this.tvDetail.is_collect = 1;
            ToastUtils.showShort("Add favorite successfully");
        } else {
            this.tvDetail.is_collect = 0;
        }
        if (z) {
            this.ivFavorite.setImageResource(R.mipmap.ic_collected);
        } else {
            this.ivFavorite.setImageResource(R.mipmap.ic_add_to_favorite);
        }
    }

    @Override // com.movieboxpro.android.view.activity.detail.ITvDetail
    public void ChoosePlayer(TvDetail tvDetail, int i, int i2, boolean z) {
        CastSession castSession;
        TvDetail.SeasonDetail seasonDetail;
        if (X86HintUtils.checkX86(this)) {
            return;
        }
        if (!App.isLogin()) {
            route(Login2Activity.class);
        }
        TvDetail tvDetail2 = this.tvDetail;
        if (tvDetail2 != null) {
            tvDetail2.addDonwload(tvDetail);
            if (this.tvDetail.list == null || this.tvDetail.list.size() <= 0) {
                showToast(String.format("Episode %s has no resources", Integer.valueOf(i2)));
                return;
            }
            Iterator<TvDetail.SeasonDetail> it = this.mAdapter.getData().iterator();
            while (true) {
                castSession = null;
                if (!it.hasNext()) {
                    seasonDetail = null;
                    break;
                }
                seasonDetail = it.next();
                if (seasonDetail.episode == i2) {
                    break;
                }
            }
            this.tvDetail.seasonDetail = seasonDetail;
            try {
                castSession = CastContext.getSharedInstance(this.context).getSessionManager().getCurrentCastSession();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (castSession != null && castSession.isConnected()) {
                PlayerStrategy playerStrategy = new PlayerStrategy();
                playerStrategy.setInstace(this.tvDetail);
                Bundle build = ParamsUtils.newBuilder().addParam(MovieChooseActivity.CHOOSEMOVIE, playerStrategy).build();
                MLog.d(this.TAG, "moviebox!!!!" + ((TvDetail) playerStrategy.getInstace()).seasonDetail.season + "###" + ((TvDetail) playerStrategy.getInstace()).seasonDetail.episode);
                route(ChooseActivity.class, build);
                return;
            }
            goVideoPlayer(i2, z);
        }
    }

    @Override // com.movieboxpro.android.view.activity.detail.ITvDetail
    public void goTvPlayer(TvDetail tvDetail, int i, boolean z) {
        if (X86HintUtils.checkX86(this)) {
            return;
        }
        if (!App.isLogin()) {
            route(Login2Activity.class);
        }
        TvDetail tvDetail2 = this.tvDetail;
        if (tvDetail2 != null) {
            tvDetail2.addDonwload(tvDetail);
            if (this.tvDetail.list == null || this.tvDetail.list.size() <= 0) {
                showToast(String.format("Episode %s has no resources", Integer.valueOf(i)));
                return;
            }
            this.tvDetail.seasonDetail = tvDetail.seasonDetail;
            CastSession castSession = null;
            try {
                castSession = CastContext.getSharedInstance(this.context).getSessionManager().getCurrentCastSession();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (castSession != null && castSession.isConnected()) {
                PlayerStrategy playerStrategy = new PlayerStrategy();
                playerStrategy.setInstace(this.tvDetail);
                Bundle build = ParamsUtils.newBuilder().addParam(MovieChooseActivity.CHOOSEMOVIE, playerStrategy).build();
                String str = this.TAG;
                MLog.d(str, "moviebox!!!!" + ((TvDetail) playerStrategy.getInstace()).seasonDetail.season + "###" + ((TvDetail) playerStrategy.getInstace()).seasonDetail.episode);
                route(ChooseActivity.class, build);
                return;
            }
            goVideoPlayer(i, z, tvDetail.episode);
        }
    }

    private void goVideoPlayer(int i, boolean z, List<TvDetail.SeasonDetail> list) {
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
        if (list != null) {
            for (TvDetail.SeasonDetail seasonDetail : list) {
                TvSeasonList tvSeasonList = new TvSeasonList();
                tvSeasonList.setEpisode(seasonDetail.episode);
                tvSeasonList.setSeason(seasonDetail.season);
                tvSeasonList.setTid(this.Tvtid);
                tvSeasonList.setId(seasonDetail.id);
                if (seasonDetail.play_progress != null) {
                    tvSeasonList.setOver(Long.parseLong(String.valueOf(seasonDetail.play_progress.get(DownloadInfo.DOWNLOAD_OVER))));
                    tvSeasonList.setSeconds(Long.parseLong(String.valueOf(seasonDetail.play_progress.get("seconds"))));
                }
                arrayList.add(tvSeasonList);
            }
        }
        TvDetail tvDetail = this.tvDetail;
        tvDetail.imdb_id = tvDetail.seasonDetail.imdb_id;
        Bundle build = ParamsUtils.newBuilder().addParam(SEASON_TV_LIST, arrayList).addParam("videoplayer_params", this.tvDetail).addParam(VideoPlayerActivity.VIDEO_ID, this.tvDetail.seasonDetail.season).addParam("videoplayer_episode", i).addParam(IS_AUTO_NEXT, z).build();
        if (PopPlayerManager.getInstance().isPopShow()) {
            PopPlayerManager.getInstance().setNewPlay(build, this.tvDetail);
        } else {
            route(TvPlayerActivity.class, build, 100);
        }
    }

    private void goVideoPlayer(int i, boolean z) {
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
        if (this.mAdapter.getData().size() < 100) {
            for (TvDetail.SeasonDetail seasonDetail : this.mAdapter.getData()) {
                TvSeasonList tvSeasonList = new TvSeasonList();
                tvSeasonList.setEpisode(seasonDetail.episode);
                tvSeasonList.setSeason(seasonDetail.season);
                tvSeasonList.setTid(this.Tvtid);
                tvSeasonList.setId(seasonDetail.id);
                if (seasonDetail.play_progress != null) {
                    tvSeasonList.setOver(Long.parseLong(String.valueOf(seasonDetail.play_progress.get(DownloadInfo.DOWNLOAD_OVER))));
                    tvSeasonList.setSeconds(Long.parseLong(String.valueOf(seasonDetail.play_progress.get("seconds"))));
                }
                arrayList.add(tvSeasonList);
            }
        } else {
            for (TvDetail.SeasonDetail seasonDetail2 : this.mAdapter.getData().subList(0, 99)) {
                TvSeasonList tvSeasonList2 = new TvSeasonList();
                tvSeasonList2.setEpisode(seasonDetail2.episode);
                tvSeasonList2.setSeason(seasonDetail2.season);
                tvSeasonList2.setTid(this.Tvtid);
                tvSeasonList2.setId(seasonDetail2.id);
                if (seasonDetail2.play_progress != null) {
                    tvSeasonList2.setOver(Long.parseLong(String.valueOf(seasonDetail2.play_progress.get(DownloadInfo.DOWNLOAD_OVER))));
                    tvSeasonList2.setSeconds(Long.parseLong(String.valueOf(seasonDetail2.play_progress.get("seconds"))));
                }
                arrayList.add(tvSeasonList2);
            }
        }
        TvDetail tvDetail = this.tvDetail;
        tvDetail.imdb_id = tvDetail.seasonDetail.imdb_id;
        Bundle build = ParamsUtils.newBuilder().addParam(SEASON_TV_LIST, arrayList).addParam("videoplayer_params", this.tvDetail).addParam(VideoPlayerActivity.VIDEO_ID, this.tvDetail.seasonDetail.season).addParam("videoplayer_episode", i).addParam(IS_AUTO_NEXT, z).build();
        if (PopPlayerManager.getInstance().isPopShow()) {
            PopPlayerManager.getInstance().setNewPlay(build, this.tvDetail);
        } else {
            route(TvPlayerActivity.class, build, 100);
        }
    }

    @Override // com.movieboxpro.android.view.activity.detail.ITvDetail
    public void getFeedBack(List<Feedback> list) {
        if (this.tvDetail == null || !App.isLogin()) {
            return;
        }
        View inflate = LayoutInflater.from(this.activity).inflate(R.layout.layout_dialog_problem, (ViewGroup) null);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.tv_problem_season);
        RecyclerView recyclerView2 = (RecyclerView) inflate.findViewById(R.id.tv_problem_episode);
        this.seasonList = new ArrayList();
        this.episodeList = new ArrayList();
        int i = 0;
        while (i < this.tvDetail.season.length) {
            Problem problem = new Problem();
            StringBuilder sb = new StringBuilder();
            sb.append("Season");
            int i2 = i + 1;
            sb.append(i2);
            problem.Season = String.valueOf(sb);
            int i3 = this.Season;
            this.SeasonProblem = i3;
            this.EpisodeProblem = -1;
            if (i == i3 - 1) {
                problem.isSelected = true;
            } else {
                problem.isSelected = false;
            }
            this.seasonList.add(problem);
            i = i2;
        }
        int i4 = 0;
        while (i4 < this.mAdapter.getData().size()) {
            Problem problem2 = new Problem();
            int i5 = i4 + 1;
            problem2.episode = i5;
            problem2.isSelected = false;
            problem2.releasedTime = this.mAdapter.getData().get(i4).released_timestamp;
            problem2.codeFile = this.mAdapter.getData().get(i4).code_file;
            this.episodeList.add(problem2);
            i4 = i5;
        }
        this.seasonAdapter = new SeasonProblemAdapter(this.seasonList);
        this.episodeAdapter = new EpisodeProblemAdapter(this.episodeList);
        recyclerView.setLayoutManager(new MyGridLayoutManager(this.context, 1));
        recyclerView.setAdapter(this.seasonAdapter);
        this.seasonAdapter.setListener(new com.movieboxpro.android.base.OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity.19
            @Override // com.movieboxpro.android.base.OnItemClickListener
            public void onItemClick(int i6) {
                if (i6 < 0 || i6 > TvDetailActivity.this.seasonAdapter.getItemCount() - 1) {
                    return;
                }
                TvDetailActivity.this.seasonAdapter.getModel(i6).isSelected = true;
                for (int i7 = 0; i7 < TvDetailActivity.this.seasonList.size(); i7++) {
                    if (i7 == i6) {
                        TvDetailActivity.this.SeasonProblem = i6 + 1;
                        TvDetailActivity.this.seasonList.get(i7).isSelected = true;
                    } else {
                        TvDetailActivity.this.seasonList.get(i7).isSelected = false;
                    }
                }
                TvDetailActivity.this.seasonAdapter.notifyDataSetChanged();
                TvDetailActivity.this.mPresenter.getSeason(TvDetailActivity.this.Tvtid, TvDetailActivity.this.SeasonProblem, null, 1, App.getUserData().uid_v2);
            }
        });
        recyclerView2.setLayoutManager(new MyGridLayoutManager(this.context, 5));
        recyclerView2.setAdapter(this.episodeAdapter);
        this.episodeAdapter.setListener(new com.movieboxpro.android.base.OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity.20
            @Override // com.movieboxpro.android.base.OnItemClickListener
            public void onItemClick(int i6) {
                if (i6 < 0 || i6 > TvDetailActivity.this.episodeAdapter.getItemCount() - 1) {
                    return;
                }
                TvDetailActivity.this.episodeAdapter.getModel(i6).isSelected = true;
                for (int i7 = 0; i7 < TvDetailActivity.this.episodeList.size(); i7++) {
                    if (i7 == i6) {
                        TvDetailActivity.this.EpisodeProblem = i6 + 1;
                        TvDetailActivity.this.episodeList.get(i7).isSelected = true;
                    } else {
                        TvDetailActivity.this.episodeList.get(i7).isSelected = false;
                    }
                }
                TvDetailActivity.this.episodeAdapter.notifyDataSetChanged();
            }
        });
        final ActionSheetDialog canceledOnTouchOutside = new ActionSheetDialog(this.context).builder().setMyView(inflate).setCancelable(true).setCanceledOnTouchOutside(true);
        for (final Feedback feedback : list) {
            canceledOnTouchOutside.addSheetItem(feedback.name, ActionSheetDialog.SheetItemColor.White, new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity.21
                @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
                public void onClick(int i6) {
                    if (TvDetailActivity.this.EpisodeProblem == -1) {
                        TvDetailActivity.this.showToast("Select an episode to report");
                    } else if (feedback.ftid == 0) {
                        FeedbackDialog feedbackDialog = new FeedbackDialog(TvDetailActivity.this.activity, "");
                        feedbackDialog.setMessageListener(new FeedbackDialog.OnMessageListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity.21.1
                            @Override // com.movieboxpro.android.view.dialog.FeedbackDialog.OnMessageListener
                            public void onMessage(String str) {
                                PsTvDetail psTvDetail = TvDetailActivity.this.mPresenter;
                                int i7 = feedback.state;
                                String str2 = TvDetailActivity.this.Tvtid;
                                int i8 = feedback.ftid;
                                psTvDetail.setFeedBack(i7, 2, str2, null, i8, str + SystemUtils.getMsg(), TvDetailActivity.this.SeasonProblem, TvDetailActivity.this.EpisodeProblem);
                            }
                        });
                        feedbackDialog.show();
                    } else {
                        canceledOnTouchOutside.dismiss();
                        TvDetailActivity.this.mPresenter.setFeedBack(feedback.state, 2, TvDetailActivity.this.Tvtid, null, feedback.ftid, SystemUtils.getMsg(TvDetailActivity.this.activity), TvDetailActivity.this.SeasonProblem, TvDetailActivity.this.EpisodeProblem);
                    }
                }
            });
        }
        canceledOnTouchOutside.show();
    }

    @Override // com.movieboxpro.android.view.activity.detail.ITvDetail
    public void getProblem(List<TvDetail.SeasonDetail> list) {
        this.episodeList.clear();
        this.episodeAdapter.notifyDataSetChanged();
        this.EpisodeProblem = -1;
        if (list != null) {
            int i = 0;
            while (i < list.size()) {
                Problem problem = new Problem();
                int i2 = i + 1;
                problem.episode = i2;
                problem.isSelected = false;
                problem.codeFile = list.get(i).code_file;
                problem.releasedTime = list.get(i).released_timestamp;
                this.episodeList.add(problem);
                i = i2;
            }
            this.episodeAdapter.notifyDataSetChanged();
        }
    }

    private void showDownload(final DownloadModel downloadModel) {
        new ActionSheetDialog(this.context).builder().setCancelable(true).setCanceledOnTouchOutside(true).addSheetItem("Cancel Download", ActionSheetDialog.SheetItemColor.White, new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity.22
            @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
            public void onClick(int i) {
                downloadModel.startService(Constant.ACTION.MOVIE_DELETE, TvDetailActivity.this.activity);
                TvDetailActivity.this.mAdapter.notifyDataSetChanged();
            }
        }).show();
    }

    private void initDrawer() {
        final ArrayList arrayList = new ArrayList();
        Problem problem = new Problem();
        problem.viewtype = 0;
        problem.Season = "Season";
        arrayList.add(problem);
        PrefsUtils prefsUtils = PrefsUtils.getInstance();
        int i = prefsUtils.getInt(Constant.Prefs.TVDETAIL_YEARS + this.Tvtid, -1);
        this.seasonSelect = i;
        if (i == -1) {
            this.seasonSelect = this.Season;
        }
        for (int i2 = 0; i2 < this.tvDetail.season.length; i2++) {
            Problem problem2 = new Problem();
            problem2.viewtype = 1;
            problem2.episode = Integer.parseInt(this.tvDetail.season[i2]);
            if (Integer.parseInt(this.tvDetail.season[i2]) == this.seasonSelect) {
                problem2.isSelected = true;
            } else {
                problem2.isSelected = false;
            }
            arrayList.add(problem2);
        }
        Problem problem3 = new Problem();
        problem3.viewtype = 0;
        problem3.Season = "Year";
        arrayList.add(problem3);
        for (int i3 = 0; i3 < this.tvDetail.years.length; i3++) {
            Problem problem4 = new Problem();
            problem4.viewtype = 1;
            problem4.episode = Integer.parseInt(this.tvDetail.years[i3]);
            if (Integer.parseInt(this.tvDetail.years[i3]) == this.seasonSelect) {
                problem4.isSelected = true;
            } else {
                problem4.isSelected = false;
            }
            arrayList.add(problem4);
        }
        final DrawerAdapter drawerAdapter = new DrawerAdapter(arrayList);
        MyGridLayoutManager myGridLayoutManager = new MyGridLayoutManager(this.context, 4);
        this.drawerRecycler.setLayoutManager(myGridLayoutManager);
        myGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity.23
            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int i4) {
                return drawerAdapter.getItemViewType(i4) != 0 ? 1 : 4;
            }
        });
        this.drawerRecycler.setLayoutManager(myGridLayoutManager);
        this.drawerRecycler.setAdapter(drawerAdapter);
        drawerAdapter.setListener(new OnClickListener2() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity.24
            @Override // com.movieboxpro.android.base.OnItemClickListener
            public void onItemClick(int i4) {
            }

            @Override // com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity.OnClickListener2
            public void sort_select(int i4) {
                Problem model = drawerAdapter.getModel(i4);
                if (model.episode > 100 || model.episode == -1) {
                    TvDetailActivity.this.mPresenter.getSeason(TvDetailActivity.this.Tvtid, -1, String.valueOf(model.episode), 0, App.getUserData().uid_v2);
                    if (model.episode == -1) {
                        TvDetailActivity.this.detailTvTabseason.setText("Unreleased");
                    } else {
                        TextView textView = TvDetailActivity.this.detailTvTabseason;
                        textView.setText("Year " + model.episode);
                    }
                } else {
                    TvDetailActivity.this.mPresenter.getSeason(TvDetailActivity.this.Tvtid, model.episode, null, 0, App.getUserData().uid_v2);
                    if (model.episode == -1) {
                        TvDetailActivity.this.detailTvTabseason.setText("Unreleased");
                    } else {
                        TvDetailActivity.this.detailTvTabseason.setText(String.format("Season %s/%s", Integer.valueOf(model.episode), Integer.valueOf(TvDetailActivity.this.tvDetail.season.length)));
                    }
                }
                for (int i5 = 0; i5 < arrayList.size(); i5++) {
                    if (i5 == i4) {
                        TvDetailActivity.this.seasonSelect = ((Problem) arrayList.get(i5)).episode;
                        ((Problem) arrayList.get(i5)).isSelected = true;
                    } else {
                        ((Problem) arrayList.get(i5)).isSelected = false;
                    }
                }
                drawerAdapter.notifyDataSetChanged();
                PrefsUtils prefsUtils2 = PrefsUtils.getInstance();
                prefsUtils2.putInt(Constant.Prefs.TVDETAIL_YEARS + TvDetailActivity.this.Tvtid, TvDetailActivity.this.seasonSelect);
                TvDetailActivity.this.closeDrawer(true);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeDrawer(boolean z) {
        if (this.drawer.isDrawerOpen(GravityCompat.END)) {
            this.drawer.closeDrawer(GravityCompat.END, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.drawer.isDrawerOpen(GravityCompat.END)) {
            this.drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        PsTvDetail psTvDetail = this.mPresenter;
        if (psTvDetail != null) {
            psTvDetail.detachView();
        }
        this.mHandler.removeCallbacks(this.mRun);
        this.mPresenter = null;
        this.mHandler = null;
        RxManager.remove(this.TAG);
        ImageView imageView = this.detailTvAvatar;
        if (imageView != null) {
            imageView.setImageDrawable(null);
        }
        ImageView imageView2 = this.detailTvBanner;
        if (imageView2 != null) {
            imageView2.setImageDrawable(null);
        }
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPlayFinish(OnPlayFinishEvent onPlayFinishEvent) {
        String str;
        PsTvDetail psTvDetail = this.mPresenter;
        if (psTvDetail == null || (str = this.Tvtid) == null) {
            return;
        }
        psTvDetail.getInfo(str, App.deviceLang);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    @Override // com.movieboxpro.android.listener.OnFavoriteStatusChangedListener
    public void onFavoriteStatusChanged(boolean z, int i) {
        if (z) {
            this.tvDetail.is_collect = 1;
        } else {
            this.tvDetail.is_collect = 0;
        }
        this.tvDetail.plan_watched = i;
        if (i == 0) {
            this.ivCollect.setImageResource(R.mipmap.ic_waiting);
        } else if (i == 1) {
            this.ivCollect.setImageResource(R.mipmap.ic_watched);
        }
        this.tvDetail.collect_watched = i;
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

    @Override // com.movieboxpro.android.base.LoadView
    public void showLoadingView() {
        showLoading();
    }

    @Override // com.movieboxpro.android.base.LoadView
    public void hideLoadingView() {
        hideLoading();
    }

    /* loaded from: classes3.dex */
    private class SeasonProblemAdapter extends BaseAdapter<Problem> {
        public SeasonProblemAdapter(List<Problem> list) {
            super(list);
        }

        @Override // com.movieboxpro.android.base.BaseAdapter
        public com.movieboxpro.android.base.BaseViewHolder getHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i, com.movieboxpro.android.base.OnItemClickListener onItemClickListener) {
            return new ItemProblem_Season_Holder(layoutInflater.inflate(R.layout.layout_season_problem_item, viewGroup, false), onItemClickListener);
        }

        @Override // com.movieboxpro.android.base.BaseAdapter
        public void setView(com.movieboxpro.android.base.BaseViewHolder baseViewHolder, int i) {
            if (i < 0 || i > getItemCount() - 1) {
                return;
            }
            Problem model = getModel(i);
            ItemProblem_Season_Holder itemProblem_Season_Holder = (ItemProblem_Season_Holder) baseViewHolder;
            itemProblem_Season_Holder.textView.setText(model.Season);
            itemProblem_Season_Holder.textView.setSelected(model.isSelected);
        }
    }

    /* loaded from: classes3.dex */
    static class ItemProblem_Season_Holder extends com.movieboxpro.android.base.BaseViewHolder {
        @BindView(R.id.item_problem_season)
        TextView textView;

        public ItemProblem_Season_Holder(View view, com.movieboxpro.android.base.OnItemClickListener onItemClickListener) {
            super(view, onItemClickListener);
        }
    }

    /* loaded from: classes3.dex */
    private class EpisodeProblemAdapter extends BaseAdapter<Problem> {
        public EpisodeProblemAdapter(List<Problem> list) {
            super(list);
        }

        @Override // com.movieboxpro.android.base.BaseAdapter
        public com.movieboxpro.android.base.BaseViewHolder getHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i, com.movieboxpro.android.base.OnItemClickListener onItemClickListener) {
            return new ItemProblem_Episode_Holder(layoutInflater.inflate(R.layout.layout_episode_problem_item, viewGroup, false), onItemClickListener);
        }

        @Override // com.movieboxpro.android.base.BaseAdapter
        public void setView(com.movieboxpro.android.base.BaseViewHolder baseViewHolder, int i) {
            if (i < 0 || i > getItemCount() - 1) {
                return;
            }
            Problem model = getModel(i);
            ItemProblem_Episode_Holder itemProblem_Episode_Holder = (ItemProblem_Episode_Holder) baseViewHolder;
            TextView textView = itemProblem_Episode_Holder.textView;
            StringBuilder sb = new StringBuilder();
            sb.append(model.episode);
            textView.setText(sb);
            itemProblem_Episode_Holder.linearLayout.setSelected(model.isSelected);
            if (model.codeFile != 1) {
                ViewGroup.LayoutParams layoutParams = itemProblem_Episode_Holder.textView.getLayoutParams();
                layoutParams.width = -1;
                layoutParams.height = -2;
                itemProblem_Episode_Holder.textView.setLayoutParams(layoutParams);
                itemProblem_Episode_Holder.tvStatus.setVisibility(0);
                if (model.releasedTime > System.currentTimeMillis() / 1000) {
                    itemProblem_Episode_Holder.tvStatus.setText("Unreleased");
                    return;
                } else if (model.releasedTime == 0) {
                    itemProblem_Episode_Holder.tvStatus.setText("Unknown");
                    return;
                } else if (model.releasedTime <= 0 || model.releasedTime >= System.currentTimeMillis() / 1000) {
                    return;
                } else {
                    itemProblem_Episode_Holder.tvStatus.setText("Not Available");
                    return;
                }
            }
            itemProblem_Episode_Holder.tvStatus.setVisibility(8);
            ViewGroup.LayoutParams layoutParams2 = itemProblem_Episode_Holder.textView.getLayoutParams();
            layoutParams2.width = -1;
            layoutParams2.height = -1;
            itemProblem_Episode_Holder.textView.setLayoutParams(layoutParams2);
        }
    }

    /* loaded from: classes3.dex */
    static class ItemProblem_Episode_Holder extends com.movieboxpro.android.base.BaseViewHolder {
        @BindView(R.id.item_container)
        LinearLayout linearLayout;
        @BindView(R.id.item_problem_episode)
        TextView textView;
        @BindView(R.id.tvUnRelease)
        TextView tvStatus;

        public ItemProblem_Episode_Holder(View view, com.movieboxpro.android.base.OnItemClickListener onItemClickListener) {
            super(view, onItemClickListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class DrawerAdapter extends BaseAdapter<Problem> {
        public DrawerAdapter(List<Problem> list) {
            super(list);
        }

        @Override // com.movieboxpro.android.base.BaseAdapter
        public com.movieboxpro.android.base.BaseViewHolder getHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i, com.movieboxpro.android.base.OnItemClickListener onItemClickListener) {
            com.movieboxpro.android.base.BaseViewHolder item_Season_Holder;
            if (i == 0) {
                item_Season_Holder = new Item_Season_Holder(layoutInflater.inflate(R.layout.layout_season_choose_item, viewGroup, false), onItemClickListener);
            } else if (i != 1) {
                return null;
            } else {
                item_Season_Holder = new Item_Year_Holder(layoutInflater.inflate(R.layout.layout_episode_choose_item, viewGroup, false), onItemClickListener);
            }
            return item_Season_Holder;
        }

        @Override // com.movieboxpro.android.base.BaseAdapter
        public void setView(com.movieboxpro.android.base.BaseViewHolder baseViewHolder, int i) {
            if (i < 0 || i > getItemCount() - 1) {
                return;
            }
            Problem model = getModel(i);
            int i2 = model.viewtype;
            if (i2 == 0) {
                Item_Season_Holder item_Season_Holder = (Item_Season_Holder) baseViewHolder;
                item_Season_Holder.textView.setText(model.Season);
                item_Season_Holder.textView.setSelected(model.isSelected);
            } else if (i2 != 1) {
            } else {
                Item_Year_Holder item_Year_Holder = (Item_Year_Holder) baseViewHolder;
                if (model.episode == -1) {
                    item_Year_Holder.textView.setTextSize(7.0f);
                    item_Year_Holder.textView.setText("Unreleased");
                } else {
                    item_Year_Holder.textView.setTextSize(16.0f);
                    TextView textView = item_Year_Holder.textView;
                    StringBuilder sb = new StringBuilder();
                    sb.append(model.episode);
                    textView.setText(sb);
                }
                item_Year_Holder.textView.setSelected(model.isSelected);
            }
        }

        @Override // com.movieboxpro.android.base.BaseAdapter
        public int getType(int i) {
            return getModel(i).viewtype;
        }
    }

    /* loaded from: classes3.dex */
    static class Item_Season_Holder extends com.movieboxpro.android.base.BaseViewHolder {
        @BindView(R.id.item_choose_season)
        TextView textView;

        public Item_Season_Holder(View view, com.movieboxpro.android.base.OnItemClickListener onItemClickListener) {
            super(view, onItemClickListener);
        }
    }

    /* loaded from: classes3.dex */
    static class Item_Year_Holder extends com.movieboxpro.android.base.BaseViewHolder {
        @BindView(R.id.item_choose_episode)
        TextView textView;

        public Item_Year_Holder(View view, com.movieboxpro.android.base.OnItemClickListener onItemClickListener) {
            super(view, onItemClickListener);
            this.mContainer.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity.Item_Year_Holder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    int adapterPosition;
                    if (!(Item_Year_Holder.this.listener instanceof OnClickListener2) || (adapterPosition = Item_Year_Holder.this.getAdapterPosition()) == -1) {
                        return;
                    }
                    ((OnClickListener2) Item_Year_Holder.this.listener).sort_select(adapterPosition);
                }
            });
        }
    }
}
