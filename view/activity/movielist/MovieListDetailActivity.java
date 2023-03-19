package com.movieboxpro.android.view.activity.movielist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Html;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.movieboxpro.android.BuildConfig;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.BaseLoadMoreAdapter;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseObserver;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.base.mvp.BaseMvpActivity;
import com.movieboxpro.android.event.RefreshMovieListEvent;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.livedata.RefreshChildModeListLiveData;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.DownloadInfo;
import com.movieboxpro.android.model.PlayerStrategy;
import com.movieboxpro.android.model.TvSeasonList;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.model.movie.MovieListDetailModel;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.EventUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.LinearItemCustomDecoration;
import com.movieboxpro.android.utils.ParamsUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SpanUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.Video.VideoPlayerActivity;
import com.movieboxpro.android.view.activity.choose.impl.ChooseActivity;
import com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity;
import com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity;
import com.movieboxpro.android.view.activity.movielist.MovieListDetailContract;
import com.movieboxpro.android.view.activity.user.Login2Activity;
import com.movieboxpro.android.view.activity.videoplayer.MoviePlayerActivity;
import com.movieboxpro.android.view.activity.videoplayer.TvPlayerActivity;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.FilterVideoDialog;
import com.movieboxpro.android.view.dialog.MovieListSortDialog;
import com.movieboxpro.android.view.dialog.MsgHintDialog;
import com.movieboxpro.android.view.dialog.ReviewDialogFragment;
import com.movieboxpro.android.view.dialog.ScreenManageDialog;
import com.movieboxpro.android.view.videocontroller.PopPlayerManager;
import com.movieboxpro.android.view.widget.CustomLoadMoreView;
import com.uber.autodispose.ObservableSubscribeProxy;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import org.greenrobot.eventbus.EventBus;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* loaded from: classes3.dex */
public class MovieListDetailActivity extends BaseMvpActivity<MovieListDetailPresenter> implements MovieListDetailContract.View {
    private BaseLoadMoreAdapter<MovieListDetailModel.ListBean, BaseViewHolder> adapter;
    private List<MovieListDetailModel.ListBean> allVideo;
    private UserModel.BBsInfo bBsInfo;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    private String cover;
    private String email;
    private FrameLayout flFilter;
    private boolean isMine;
    @BindView(R.id.ivAddToList)
    ImageView ivAddToList;
    private AppCompatImageView ivAddWatch;
    private CircleImageView ivAvatar;
    @BindView(R.id.ivBg)
    ImageView ivBg;
    private ImageView ivCollect;
    private ImageView ivFilter;
    private String lid;
    private String listDesc;
    private String listImg;
    private String listTitle;
    private LinearLayout llCollect;
    private LinearLayout llRank;
    private boolean originCollect;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.toolBar)
    Toolbar toolbar;
    private TextView tvCollectNum;
    private TextView tvDesc;
    @BindView(R.id.tvName)
    TextView tvName;
    private TextView tvNickname;
    private TextView tvRank;
    private TextView tvReviewNum;
    private TextView tvTime;
    private TextView tvVideoNum;
    private String uid;
    private String userAvatar;
    private String userUid;
    private String username;
    private int page = 1;
    private boolean isCollect = false;
    private int currPage = 1;
    private int pageSize = 15;
    private String filterTitle = "Default";
    private boolean addedToList = false;
    private boolean fromAdd = false;

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected int getLayoutResId() {
        return R.layout.activity_movie_list_detail;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected boolean isFullScreen() {
        return true;
    }

    public static void start(Context context, String str, String str2, String str3, boolean z) {
        Intent intent = new Intent(context, MovieListDetailActivity.class);
        intent.putExtra("lid", str);
        intent.putExtra("username", str2);
        intent.putExtra("cover", str3);
        intent.putExtra("fromAdd", z);
        context.startActivity(intent);
    }

    public static void start(Context context, String str, String str2, String str3) {
        Intent intent = new Intent(context, MovieListDetailActivity.class);
        intent.putExtra("lid", str);
        intent.putExtra("username", str2);
        intent.putExtra("cover", str3);
        context.startActivity(intent);
    }

    public static void startWithFlag(Context context, String str, String str2, String str3) {
        Intent intent = new Intent(context, MovieListDetailActivity.class);
        intent.setFlags(268435456);
        intent.putExtra("lid", str);
        intent.putExtra("username", str2);
        intent.putExtra("cover", str3);
        context.startActivity(intent);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initListener() {
        this.adapter.getLoadMore().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.movieboxpro.android.view.activity.movielist.-$$Lambda$MovieListDetailActivity$fPBl2S9t0xQmGvFj0v810tLGVbM
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                MovieListDetailActivity.this.lambda$initListener$0$MovieListDetailActivity();
            }
        });
        this.ivAddWatch.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.movielist.-$$Lambda$MovieListDetailActivity$-pzRFXi6as4mNsK9FRT8iaPOp9Y
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MovieListDetailActivity.this.lambda$initListener$1$MovieListDetailActivity(view);
            }
        });
        this.adapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.movielist.-$$Lambda$MovieListDetailActivity$qYIVCwrUU6t2Bn5oib3HM7EMF1w
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MovieListDetailActivity.this.lambda$initListener$2$MovieListDetailActivity(baseQuickAdapter, view, i);
            }
        });
        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.movielist.-$$Lambda$MovieListDetailActivity$dHRddFKT2K2rxCliQYCYUcEIRZc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MovieListDetailActivity.this.lambda$initListener$3$MovieListDetailActivity(view);
            }
        });
        this.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() { // from class: com.movieboxpro.android.view.activity.movielist.-$$Lambda$MovieListDetailActivity$crFsbQM6CIuBgzEiiqFonupn89I
            @Override // androidx.appcompat.widget.Toolbar.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return MovieListDetailActivity.this.lambda$initListener$4$MovieListDetailActivity(menuItem);
            }
        });
        this.ivAddToList.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.movielist.MovieListDetailActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MovieListDetailActivity.this.addedToList) {
                    ((ObservableSubscribeProxy) HttpRequest.post("Playlists_delete_super_child").param("lid", MovieListDetailActivity.this.lid).asRequest().compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(MovieListDetailActivity.this))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.movielist.MovieListDetailActivity.2.1
                        @Override // io.reactivex.Observer
                        public void onSubscribe(Disposable disposable) {
                            MovieListDetailActivity.this.showLoadingView();
                        }

                        @Override // io.reactivex.Observer
                        public void onNext(String str) {
                            MovieListDetailActivity.this.hideLoadingView();
                            ToastUtils.showShort("Removed");
                            MovieListDetailActivity.this.addedToList = false;
                            MovieListDetailActivity.this.initAddIcon(false);
                            RefreshChildModeListLiveData.Companion.get().setValue(true);
                        }

                        @Override // io.reactivex.Observer
                        public void onError(Throwable th) {
                            MovieListDetailActivity.this.hideLoadingView();
                            ToastUtils.showShort("Add failed:" + th.getMessage());
                        }

                        @Override // io.reactivex.Observer
                        public void onComplete() {
                            MovieListDetailActivity.this.hideLoadingView();
                        }
                    });
                } else {
                    ((ObservableSubscribeProxy) HttpRequest.post("Playlists_add_super_child").param("lid", MovieListDetailActivity.this.lid).asRequest().compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(MovieListDetailActivity.this))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.movielist.MovieListDetailActivity.2.2
                        @Override // io.reactivex.Observer
                        public void onSubscribe(Disposable disposable) {
                            MovieListDetailActivity.this.showLoadingView();
                        }

                        @Override // io.reactivex.Observer
                        public void onNext(String str) {
                            MovieListDetailActivity.this.hideLoadingView();
                            ToastUtils.showShort("Added");
                            MovieListDetailActivity.this.addedToList = true;
                            MovieListDetailActivity.this.initAddIcon(true);
                            RefreshChildModeListLiveData.Companion.get().setValue(true);
                        }

                        @Override // io.reactivex.Observer
                        public void onError(Throwable th) {
                            MovieListDetailActivity.this.hideLoadingView();
                            ToastUtils.showShort("Add failed:" + th.getMessage());
                        }

                        @Override // io.reactivex.Observer
                        public void onComplete() {
                            MovieListDetailActivity.this.hideLoadingView();
                        }
                    });
                }
            }
        });
    }

    public /* synthetic */ void lambda$initListener$0$MovieListDetailActivity() {
        this.page++;
        requestData();
    }

    public /* synthetic */ void lambda$initListener$1$MovieListDetailActivity(View view) {
        new MsgHintDialog.Builder(this).setTitle("Add to Waiting to Watch").setContent("Are you sure to add all movies and tv shows of this movielist to waiting to watch?").setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.movielist.MovieListDetailActivity.1
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public void onClick() {
                ((ObservableSubscribeProxy) HttpRequest.post("User_watch_plan_add_from_playlist").param("pid", MovieListDetailActivity.this.lid).param("watched", (Object) 0).asRequest().compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(MovieListDetailActivity.this))).subscribe(new BaseObserver<String>() { // from class: com.movieboxpro.android.view.activity.movielist.MovieListDetailActivity.1.1
                    @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
                    public void onSubscribe(Disposable disposable) {
                        super.onSubscribe(disposable);
                        MovieListDetailActivity.this.showLoadingView();
                    }

                    @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
                    public void onNext(String str) {
                        super.onNext((C00901) str);
                        MovieListDetailActivity.this.hideLoadingView();
                        ToastUtils.showShort("Added");
                    }

                    @Override // com.movieboxpro.android.base.BaseObserver
                    public void onError(ApiException apiException) {
                        MovieListDetailActivity.this.hideLoadingView();
                        ToastUtils.showShort("Add failed:" + apiException.getMessage());
                    }
                });
            }
        }).create().show();
    }

    public /* synthetic */ void lambda$initListener$2$MovieListDetailActivity(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        MovieListDetailModel.ListBean item = this.adapter.getItem(i);
        if (item != null) {
            if (item.getBox_type() == 1) {
                MovieDetailActivity.start(this, String.valueOf(item.getId()), item.getPoster());
            } else {
                TvDetailActivity.start(this, String.valueOf(item.getId()));
            }
        }
    }

    public /* synthetic */ void lambda$initListener$3$MovieListDetailActivity(View view) {
        finish();
    }

    public /* synthetic */ boolean lambda$initListener$4$MovieListDetailActivity(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.edit) {
            CreateMovieListActivity.start(this, this.lid, this.cover, 1);
        } else {
            menuItem.getItemId();
        }
        return true;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initView() {
        setSupportActionBar(this.toolbar);
        ((ActionBar) Objects.requireNonNull(getSupportActionBar())).setDisplayHomeAsUpEnabled(true);
        this.collapsingToolbarLayout.setExpandedTitleColor(0);
        this.collapsingToolbarLayout.setCollapsedTitleTextColor(-1);
        this.collapsingToolbarLayout.setExpandedTitleTypeface(Typeface.DEFAULT_BOLD);
        this.collapsingToolbarLayout.setCollapsedTitleTypeface(Typeface.DEFAULT_BOLD);
        DensityUtils.addMarginTopEqualStatusBarHeight(this.toolbar, this);
        View inflate = getLayoutInflater().inflate(R.layout.movie_list_top_layout, (ViewGroup) this.recyclerView.getParent(), false);
        this.ivCollect = (ImageView) inflate.findViewById(R.id.ivCollect);
        this.ivAddWatch = (AppCompatImageView) inflate.findViewById(R.id.ivAddWatch);
        this.tvNickname = (TextView) inflate.findViewById(R.id.tvNickname);
        this.tvRank = (TextView) inflate.findViewById(R.id.tvRank);
        this.tvDesc = (TextView) inflate.findViewById(R.id.tvDesc);
        this.ivAvatar = (CircleImageView) inflate.findViewById(R.id.ivAvatar);
        this.tvTime = (TextView) inflate.findViewById(R.id.tvTime);
        this.tvVideoNum = (TextView) inflate.findViewById(R.id.tvVideoNum);
        this.llCollect = (LinearLayout) inflate.findViewById(R.id.llCollect);
        this.tvCollectNum = (TextView) inflate.findViewById(R.id.tvCollectNum);
        this.llRank = (LinearLayout) inflate.findViewById(R.id.llRank);
        this.tvReviewNum = (TextView) inflate.findViewById(R.id.tvReviewNum);
        this.flFilter = (FrameLayout) inflate.findViewById(R.id.flFilter);
        this.ivFilter = (ImageView) inflate.findViewById(R.id.ivFilter);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.llRandomPlay);
        this.adapter.setHeaderView(inflate);
        if (CommonUtils.isTablet()) {
            this.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
            this.recyclerView.addItemDecoration(new LinearItemCustomDecoration(0, 0, 0, 0));
        }
        this.recyclerView.setAdapter(this.adapter);
        ((LinearLayout) inflate.findViewById(R.id.llReview)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.movielist.-$$Lambda$MovieListDetailActivity$mxvy1o8IiHcRkEls2DqCA0VG6DI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MovieListDetailActivity.this.lambda$initView$5$MovieListDetailActivity(view);
            }
        });
        inflate.findViewById(R.id.llShare).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.movielist.MovieListDetailActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MovieListDetailActivity.this.getShareLink();
            }
        });
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.movielist.MovieListDetailActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if ("Default".equals(MovieListDetailActivity.this.filterTitle)) {
                    if (MovieListDetailActivity.this.allVideo == null) {
                        ((MovieListDetailPresenter) MovieListDetailActivity.this.mPresenter).requestListToPlay(MovieListDetailActivity.this.lid, "name", "asc");
                        return;
                    }
                    MovieListDetailActivity movieListDetailActivity = MovieListDetailActivity.this;
                    movieListDetailActivity.playRandomItem(movieListDetailActivity.allVideo);
                    return;
                }
                MovieListDetailActivity movieListDetailActivity2 = MovieListDetailActivity.this;
                movieListDetailActivity2.playRandomItem(movieListDetailActivity2.adapter.getData());
            }
        });
        this.flFilter.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.movielist.MovieListDetailActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MovieListSortDialog newInstance = MovieListSortDialog.Companion.newInstance(MovieListDetailActivity.this.filterTitle);
                newInstance.setListener(new DialogAction.EditActionListener() { // from class: com.movieboxpro.android.view.activity.movielist.MovieListDetailActivity.5.1
                    @Override // com.movieboxpro.android.view.dialog.DialogAction.EditActionListener
                    public void onClick(String str) {
                        MovieListDetailActivity.this.filterTitle = str;
                        MovieListDetailActivity.this.refreshMovieList();
                    }
                });
                newInstance.show(MovieListDetailActivity.this.getSupportFragmentManager(), MovieListSortDialog.class.getSimpleName());
            }
        });
        this.llCollect.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.movielist.-$$Lambda$MovieListDetailActivity$-X7R7kwLBmJS7_g8SdKf_wQwpSo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MovieListDetailActivity.this.lambda$initView$6$MovieListDetailActivity(view);
            }
        });
        this.llRank.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.movielist.-$$Lambda$MovieListDetailActivity$59IWG7Oub7mRmm9sYAUNIWkEk-w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MovieListDetailActivity.this.lambda$initView$7$MovieListDetailActivity(view);
            }
        });
    }

    public /* synthetic */ void lambda$initView$5$MovieListDetailActivity(View view) {
        if (!App.isLogin()) {
            Login2Activity.start(this);
        } else {
            ReviewDialogFragment.newInstance(this.lid, 3).show(getSupportFragmentManager(), ReviewDialogFragment.class.getSimpleName());
        }
    }

    public /* synthetic */ void lambda$initView$6$MovieListDetailActivity(View view) {
        if (!App.isLogin()) {
            Login2Activity.start(this);
        } else {
            ((MovieListDetailPresenter) this.mPresenter).collect(this.uid, this.lid, this.ivCollect.isSelected() ? "del" : "add", BuildConfig.VERSION_NAME, this.userUid);
        }
    }

    public /* synthetic */ void lambda$initView$7$MovieListDetailActivity(View view) {
        PlayListRankActivity.Companion.start(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playRandomItem(List<MovieListDetailModel.ListBean> list) {
        MovieListDetailModel.ListBean listBean = list.get(new Random().nextInt(list.size()));
        if (listBean.getBox_type() == 1) {
            ((MovieListDetailPresenter) this.mPresenter).getPlayPath(String.valueOf(listBean.getId()), listBean.getBox_type(), 0, 0);
        } else {
            ((MovieListDetailPresenter) this.mPresenter).getPlayPath(String.valueOf(listBean.getId()), listBean.getBox_type(), 1, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getShareLink() {
        ((ObservableSubscribeProxy) HttpRequest.post("Share_url").param(IjkMediaMeta.IJKM_KEY_TYPE, "playlist").param("id", this.lid).asRequest().compose(RxUtils.rxTranslate2Bean(HashMap.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<HashMap>() { // from class: com.movieboxpro.android.view.activity.movielist.MovieListDetailActivity.6
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                MovieListDetailActivity.this.showLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(HashMap hashMap) {
                MovieListDetailActivity.this.hideLoadingView();
                Intent intent = new Intent();
                intent.setAction("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.TEXT", (String) hashMap.get("url"));
                try {
                    MovieListDetailActivity.this.startActivity(Intent.createChooser(intent, "Share the detail link"));
                } catch (Exception unused) {
                    ToastUtils.showShort("Share failed,the link is copied");
                }
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException apiException) {
                MovieListDetailActivity.this.hideLoadingView();
                ToastUtils.showShort("Share failed:" + apiException.getMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0058, code lost:
        if (r1.equals("Name from A-Z") != false) goto L6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void refreshMovieList() {
        /*
            Method dump skipped, instructions count: 276
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.view.activity.movielist.MovieListDetailActivity.refreshMovieList():void");
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initData() {
        EventUtils.event("Movielist详情");
        this.lid = getIntent().getStringExtra("lid");
        this.username = getIntent().getStringExtra("username");
        this.uid = App.isLogin() ? App.getUserData().uid_v2 : "";
        String stringExtra = getIntent().getStringExtra("cover");
        this.cover = stringExtra;
        if (stringExtra != null && !stringExtra.isEmpty()) {
            GlideUtils.load((Activity) this, this.cover, this.ivBg, (int) R.drawable.default_movie_list_pic);
        }
        BaseLoadMoreAdapter<MovieListDetailModel.ListBean, BaseViewHolder> baseLoadMoreAdapter = new BaseLoadMoreAdapter<MovieListDetailModel.ListBean, BaseViewHolder>(R.layout.adapter_movielist_detail_item, new ArrayList()) { // from class: com.movieboxpro.android.view.activity.movielist.MovieListDetailActivity.7
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder baseViewHolder, MovieListDetailModel.ListBean listBean) {
                GlideUtils.loadCornerPortraitGifHolder(MovieListDetailActivity.this, listBean.getPoster(), (ImageView) baseViewHolder.getView(R.id.iv_avatar), 8);
                TextView textView = (TextView) baseViewHolder.getView(R.id.tv_note);
                if (TextUtils.isEmpty(listBean.getMark())) {
                    textView.setVisibility(8);
                } else {
                    textView.setVisibility(0);
                    textView.setText(listBean.getMark());
                }
                if (TextUtils.isEmpty(listBean.getImdb_rating())) {
                    baseViewHolder.setText(R.id.tv_rate, "-.-");
                } else {
                    baseViewHolder.setText(R.id.tv_rate, listBean.getImdb_rating());
                }
                if (listBean.getRuntime() != 0) {
                    baseViewHolder.setText(R.id.tv_time, String.format("%s min  %s", Integer.valueOf(listBean.getRuntime()), listBean.getCats()));
                } else {
                    baseViewHolder.setText(R.id.tv_time, listBean.getCats());
                }
                TextView textView2 = (TextView) baseViewHolder.getView(R.id.tv_name);
                SpanUtils.with(textView2).append(listBean.getTitle()).setForegroundColor(-1).setFontSize(20, true).setBold().create();
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) textView2.getLayoutParams();
                if (textView2.getLineCount() == 2) {
                    marginLayoutParams.setMargins(marginLayoutParams.leftMargin, 0, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
                } else {
                    marginLayoutParams.setMargins(marginLayoutParams.leftMargin, DensityUtils.dp2px(App.getContext(), 10.0f), marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
                }
            }
        };
        this.adapter = baseLoadMoreAdapter;
        baseLoadMoreAdapter.getLoadMoreModule().setEnableLoadMore(true);
        this.adapter.getLoadMore().setLoadMoreView(new CustomLoadMoreView());
        boolean booleanExtra = getIntent().getBooleanExtra("fromAdd", false);
        this.fromAdd = booleanExtra;
        if (booleanExtra) {
            checkIsAdded();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initAddIcon(boolean z) {
        if (z) {
            this.ivAddToList.setImageResource(R.mipmap.ic_added_to_list);
        } else {
            this.ivAddToList.setImageResource(R.mipmap.ic_add_to_list);
        }
    }

    private void checkIsAdded() {
        ((ObservableSubscribeProxy) HttpRequest.post("Playlists_add_super_child_query").param("lid", this.lid).asRequest().compose(RxUtils.rxTranslate2Bean(HashMap.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<HashMap>() { // from class: com.movieboxpro.android.view.activity.movielist.MovieListDetailActivity.8
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(HashMap hashMap) {
                if (MovieListDetailActivity.this.fromAdd) {
                    MovieListDetailActivity.this.ivAddToList.setVisibility(0);
                }
                if (((Integer) hashMap.get("is_add")).intValue() == 1) {
                    MovieListDetailActivity.this.initAddIcon(true);
                    MovieListDetailActivity.this.addedToList = true;
                    return;
                }
                MovieListDetailActivity.this.addedToList = false;
                MovieListDetailActivity.this.initAddIcon(false);
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                ToastUtils.showShort(th.getMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    public MovieListDetailPresenter bindPresenter() {
        return new MovieListDetailPresenter(this);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void requestData() {
        char c;
        String str = this.filterTitle;
        switch (str.hashCode()) {
            case -1696925139:
                if (str.equals("Name from A-Z")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1696901139:
                if (str.equals("Name from Z-A")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1085510111:
                if (str.equals("Default")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1065097433:
                if (str.equals("Latest Added")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 201004019:
                if (str.equals("Earliest Added")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1580322230:
                if (str.equals("Latest Released")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1605885290:
                if (str.equals("Earliest Released")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                ((MovieListDetailPresenter) this.mPresenter).requestList(this.uid, this.lid, this.page, BuildConfig.VERSION_NAME);
                return;
            case 1:
                ((MovieListDetailPresenter) this.mPresenter).requestFilterList(this.lid, "name", "asc", this.page, this.pageSize);
                return;
            case 2:
                ((MovieListDetailPresenter) this.mPresenter).requestFilterList(this.lid, "name", "desc", this.page, this.pageSize);
                return;
            case 3:
                ((MovieListDetailPresenter) this.mPresenter).requestFilterList(this.lid, "add_time", "desc", this.page, this.pageSize);
                return;
            case 4:
                ((MovieListDetailPresenter) this.mPresenter).requestFilterList(this.lid, "add_time", "asc", this.page, this.pageSize);
                return;
            case 5:
                ((MovieListDetailPresenter) this.mPresenter).requestFilterList(this.lid, FilterVideoDialog.DEFAULT_SORT, "desc", this.page, this.pageSize);
                return;
            case 6:
                ((MovieListDetailPresenter) this.mPresenter).requestFilterList(this.lid, FilterVideoDialog.DEFAULT_SORT, "asc", this.page, this.pageSize);
                return;
            default:
                return;
        }
    }

    @Override // com.movieboxpro.android.view.activity.movielist.MovieListDetailContract.View
    public void showFilterData(MovieListDetailModel movieListDetailModel) {
        this.page = 1;
        this.adapter.setList(movieListDetailModel.getList());
        this.ivFilter.setImageResource(R.mipmap.ic_movie_list_filter_select);
    }

    @Override // com.movieboxpro.android.view.activity.movielist.MovieListDetailContract.View
    public void randomPlay(MovieListDetailModel movieListDetailModel) {
        List<MovieListDetailModel.ListBean> list = movieListDetailModel.getList();
        this.allVideo = list;
        playRandomItem(list);
    }

    @Override // com.movieboxpro.android.view.activity.movielist.MovieListDetailContract.View
    public void showData(MovieListDetailModel movieListDetailModel) {
        if (this.page == 1) {
            this.ivFilter.setImageResource(R.mipmap.ic_movie_list_filter);
            this.email = movieListDetailModel.getEmail();
            this.userAvatar = movieListDetailModel.getAvatar();
            this.bBsInfo = movieListDetailModel.getBbs_bind();
            this.listTitle = movieListDetailModel.getName();
            if (movieListDetailModel.getImgArr() != null && movieListDetailModel.getImgArr().size() > 0) {
                this.listImg = movieListDetailModel.getImgArr().get(0);
            }
            this.listDesc = movieListDetailModel.getDesc();
            if (movieListDetailModel.getIsmine() == 1) {
                this.isMine = true;
                MenuItem findItem = this.toolbar.getMenu().findItem(R.id.edit);
                if (findItem != null) {
                    findItem.setVisible(true);
                }
            }
            this.userUid = movieListDetailModel.getUid();
            this.tvCollectNum.setText(String.valueOf(movieListDetailModel.getCollect_num()));
            this.tvName.setText(movieListDetailModel.getName());
            this.collapsingToolbarLayout.setTitle(movieListDetailModel.getName());
            this.tvDesc.setText(Html.fromHtml(movieListDetailModel.getDesc()));
            GlideUtils.load((Activity) this, movieListDetailModel.getAvatar(), (ImageView) this.ivAvatar, (int) R.drawable.ic_default_avatar);
            if ("0".equals(movieListDetailModel.getRank())) {
                this.tvRank.setText("-");
            } else {
                this.tvRank.setText(movieListDetailModel.getRank());
            }
            if (movieListDetailModel.getImgArr() != null && movieListDetailModel.getImgArr().size() > 0) {
                this.cover = movieListDetailModel.getImgArr().get(0);
            }
            GlideUtils.load((Activity) this, this.cover, this.ivBg, (int) R.drawable.default_movie_list_pic);
            this.tvTime.setText(TimeUtils.formatTime3(movieListDetailModel.getDateline() * 1000));
            this.tvNickname.setText(movieListDetailModel.getUsername());
            this.username = movieListDetailModel.getUsername();
            this.tvVideoNum.setText(String.format("%s videos", Integer.valueOf(movieListDetailModel.getCount())));
            if (movieListDetailModel.getIs_collect() == 0) {
                this.isCollect = false;
            } else {
                this.isCollect = true;
            }
            boolean z = this.isCollect;
            this.originCollect = z;
            this.ivCollect.setSelected(z);
        }
        if (this.page == 1) {
            this.adapter.setList(movieListDetailModel.getList());
        } else {
            this.adapter.addData(movieListDetailModel.getList());
        }
        if (movieListDetailModel.getList().size() < 15) {
            if (this.page == 1) {
                this.adapter.getLoadMore().loadMoreEnd(true);
                return;
            } else {
                this.adapter.getLoadMore().loadMoreEnd();
                return;
            }
        }
        this.adapter.getLoadMore().loadMoreComplete();
    }

    @Override // com.movieboxpro.android.view.activity.movielist.MovieListDetailContract.View
    public void doCollectComplete(boolean z) {
        if (z) {
            boolean z2 = !this.isCollect;
            this.isCollect = z2;
            this.ivCollect.setSelected(z2);
            return;
        }
        ToastUtils.showShort("collect error");
    }

    @Override // com.movieboxpro.android.view.activity.movielist.MovieListDetailContract.View
    public void showReviewCount(String str) {
        this.tvReviewNum.setText(str);
    }

    @Override // com.movieboxpro.android.view.activity.movielist.MovieListDetailContract.View
    public void movieListDeleted(String str) {
        MsgHintDialog.Builder builder = new MsgHintDialog.Builder(this);
        builder.setContent(str + ",removed from collect list?").setNegativeText("Cancel").setPositiveText("Ok").setCancelable(false).setNegativeActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.movielist.MovieListDetailActivity.9
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public void onClick() {
                MovieListDetailActivity.this.finish();
            }
        }).setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.movielist.-$$Lambda$MovieListDetailActivity$uBNZLEeb_jh6YFpkkoInmkPg6Uo
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public final void onClick() {
                MovieListDetailActivity.this.lambda$movieListDeleted$8$MovieListDetailActivity();
            }
        }).create().show();
    }

    public /* synthetic */ void lambda$movieListDeleted$8$MovieListDetailActivity() {
        ((MovieListDetailPresenter) this.mPresenter).deleteCollect(this.uid, this.lid, "del");
    }

    @Override // com.movieboxpro.android.view.activity.movielist.MovieListDetailContract.View
    public void removeCollectComplete() {
        finish();
        ToastUtils.showShort("Remove successfully");
        EventBus.getDefault().post(new RefreshMovieListEvent());
    }

    @Override // com.movieboxpro.android.view.activity.movielist.MovieListDetailContract.View
    public void goTvPlayer(TvDetail tvDetail) {
        CastSession castSession;
        try {
            castSession = CastContext.getSharedInstance(this).getSessionManager().getCurrentCastSession();
        } catch (Exception unused) {
            castSession = null;
        }
        if (castSession != null && castSession.isConnected()) {
            PlayerStrategy playerStrategy = new PlayerStrategy();
            playerStrategy.setInstace(tvDetail);
            ChooseActivity.Companion.start(this, false, playerStrategy);
            return;
        }
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
        for (TvDetail.SeasonDetail seasonDetail : tvDetail.episode) {
            TvSeasonList tvSeasonList = new TvSeasonList();
            tvSeasonList.setEpisode(seasonDetail.episode);
            tvSeasonList.setSeason(seasonDetail.season);
            if (seasonDetail.play_progress != null) {
                tvSeasonList.setOver(Long.parseLong(String.valueOf(seasonDetail.play_progress.get(DownloadInfo.DOWNLOAD_OVER))));
                tvSeasonList.setSeconds(Long.parseLong(String.valueOf(seasonDetail.play_progress.get("seconds"))));
            }
            tvSeasonList.setTid(seasonDetail.tid);
            tvSeasonList.setId(seasonDetail.id);
            arrayList.add(tvSeasonList);
        }
        Bundle build = ParamsUtils.newBuilder().addParam(TvDetailActivity.SEASON_TV_LIST, arrayList).addParam("videoplayer_params", tvDetail).addParam(VideoPlayerActivity.VIDEO_ID, tvDetail.seasonDetail.season).addParam("videoplayer_episode", tvDetail.seasonDetail.episode).addParam("FeaturedFragment", false).build();
        if (PopPlayerManager.getInstance().isPopShow()) {
            PopPlayerManager.getInstance().setNewPlay(build, tvDetail);
            return;
        }
        Intent intent = new Intent(this, TvPlayerActivity.class);
        intent.putExtras(build);
        startActivity(intent);
    }

    @Override // com.movieboxpro.android.view.activity.movielist.MovieListDetailContract.View
    public void goMoviePlayer(MovieDetail movieDetail) {
        CastSession castSession;
        try {
            castSession = CastContext.getSharedInstance(this).getSessionManager().getCurrentCastSession();
        } catch (Exception unused) {
            castSession = null;
        }
        if (castSession != null && castSession.isConnected()) {
            PlayerStrategy playerStrategy = new PlayerStrategy();
            playerStrategy.setInstace(movieDetail);
            ChooseActivity.Companion.start(this, false, playerStrategy);
        } else if (PopPlayerManager.getInstance().isPopShow()) {
            PopPlayerManager.getInstance().setNewPlay(movieDetail);
        } else {
            MoviePlayerActivity.start(this, movieDetail, movieDetail.id);
        }
    }

    @Override // com.movieboxpro.android.view.activity.movielist.MovieListDetailContract.View
    public void showScreenManage(ArrayList<DeviceModelResponse.DeviceModel> arrayList, String str, final String str2, final int i, final int i2, final int i3) {
        ScreenManageDialog newInstance = ScreenManageDialog.newInstance(arrayList, str);
        newInstance.setListener(new ScreenManageDialog.OnStopDeviceListener() { // from class: com.movieboxpro.android.view.activity.movielist.-$$Lambda$MovieListDetailActivity$tM7B66QXwP68PG-q1NbJRr48qPY
            @Override // com.movieboxpro.android.view.dialog.ScreenManageDialog.OnStopDeviceListener
            public final void onStopDevice() {
                MovieListDetailActivity.this.lambda$showScreenManage$9$MovieListDetailActivity(str2, i, i2, i3);
            }
        });
        newInstance.show(getSupportFragmentManager(), ScreenManageDialog.class.getSimpleName());
    }

    public /* synthetic */ void lambda$showScreenManage$9$MovieListDetailActivity(String str, int i, int i2, int i3) {
        ((MovieListDetailPresenter) this.mPresenter).getPlayPath(str, i, i2, i3);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        if (this.isCollect != this.originCollect) {
            EventBus.getDefault().post(new RefreshMovieListEvent());
        }
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1 && i2 == -1) {
            finish();
        }
    }
}
