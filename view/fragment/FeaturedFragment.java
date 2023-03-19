package com.movieboxpro.android.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.interfaces.SimpleCallback;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.FeaturedAdapter;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.base.mvp.BaseContract;
import com.movieboxpro.android.base.mvp.BaseMvpFragment;
import com.movieboxpro.android.event.ChildModeChangedEvent;
import com.movieboxpro.android.event.LoginEvent;
import com.movieboxpro.android.event.LogoutEvent;
import com.movieboxpro.android.event.LowMemoryEvent;
import com.movieboxpro.android.event.OnFeaturedFirstLoadEvent;
import com.movieboxpro.android.event.OnPlayFinishEvent;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.livedata.IncognitoModeLiveData;
import com.movieboxpro.android.model.AdvertisementModel;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.DownloadInfo;
import com.movieboxpro.android.model.FeaturedDataModel;
import com.movieboxpro.android.model.PlayerStrategy;
import com.movieboxpro.android.model.TvSeasonList;
import com.movieboxpro.android.model.common.Homelist;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.utils.CacheDiskUtils;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.ParamsUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.utils.X86HintUtils;
import com.movieboxpro.android.view.activity.MainActivity;
import com.movieboxpro.android.view.activity.Video.VideoPlayerActivity;
import com.movieboxpro.android.view.activity.choose.impl.ChooseActivity;
import com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity;
import com.movieboxpro.android.view.activity.settings.InputChildModePasswordActivity;
import com.movieboxpro.android.view.activity.videoplayer.MoviePlayerActivity;
import com.movieboxpro.android.view.activity.videoplayer.TvPlayerActivity;
import com.movieboxpro.android.view.dialog.ChildModeHintDialog;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.ScreenManageDialog;
import com.movieboxpro.android.view.fragment.FeaturedContract;
import com.movieboxpro.android.view.videocontroller.PopPlayerManager;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: FeaturedFragment.kt */
@Metadata(d1 = {"\u0000\u009f\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007*\u0001\r\u0018\u0000 J2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001JB\u0005¢\u0006\u0002\u0010\u0004J.\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017H\u0002J\b\u0010\u0019\u001a\u00020\u0012H\u0014J\b\u0010\u001a\u001a\u00020\u0002H\u0014J\u0012\u0010\u001b\u001a\u00020\b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0017H\u0002J\u0010\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u001fH\u0007J\b\u0010 \u001a\u00020\bH\u0014J\u0010\u0010!\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020#H\u0016J\u0010\u0010$\u001a\u00020\u00102\u0006\u0010%\u001a\u00020&H\u0016J\b\u0010'\u001a\u00020\u0010H\u0016J\b\u0010(\u001a\u00020\u0010H\u0016J\b\u0010)\u001a\u00020\u0010H\u0016J\b\u0010*\u001a\u00020\u0010H\u0014J\b\u0010+\u001a\u00020\u0010H\u0014J\b\u0010,\u001a\u00020\u0010H\u0014J\b\u0010-\u001a\u00020\u0010H\u0014J\u0010\u0010.\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020/H\u0007J\u0010\u00100\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u000201H\u0007J\b\u00102\u001a\u00020\u0010H\u0016J\b\u00103\u001a\u00020\u0010H\u0016J\u0010\u00104\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u000205H\u0007J\u0010\u00106\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u000207H\u0007J\u0012\u00108\u001a\u00020\u00102\b\u00109\u001a\u0004\u0018\u00010:H\u0002J\u001e\u0010;\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150<H\u0002J\u0016\u0010=\u001a\u00020\u00102\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150<H\u0016J\u0010\u0010>\u001a\u00020\u00102\u0006\u0010?\u001a\u00020\u0017H\u0016J\u0010\u0010@\u001a\u00020\u00102\u0006\u00109\u001a\u00020:H\u0016JL\u0010A\u001a\u00020\u00102\u0016\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020C0Bj\b\u0012\u0004\u0012\u00020C`D2\b\u0010E\u001a\u0004\u0018\u00010\u00172\b\u0010F\u001a\u0004\u0018\u00010\u00172\u0006\u0010G\u001a\u00020\u00122\u0006\u0010H\u001a\u00020\u00122\u0006\u0010I\u001a\u00020\u0012H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000e¨\u0006K"}, d2 = {"Lcom/movieboxpro/android/view/fragment/FeaturedFragment;", "Lcom/movieboxpro/android/base/mvp/BaseMvpFragment;", "Lcom/movieboxpro/android/view/fragment/FeaturedPresenter;", "Lcom/movieboxpro/android/view/fragment/FeaturedContract$View;", "()V", "adapter", "Lcom/movieboxpro/android/adapter/FeaturedAdapter;", "continuePlaying", "", "errorView", "Landroid/view/View;", "isFirstLoad", "scrollListener", "com/movieboxpro/android/view/fragment/FeaturedFragment$scrollListener$1", "Lcom/movieboxpro/android/view/fragment/FeaturedFragment$scrollListener$1;", "addMoreItem", "", "itemType", "", "list", "", "Lcom/movieboxpro/android/model/common/Homelist$Typelist;", "name", "", IjkMediaMeta.IJKM_KEY_TYPE, "bindLayout", "bindPresenter", "checkChildMode", "contentRating", "childModeChanged", NotificationCompat.CATEGORY_EVENT, "Lcom/movieboxpro/android/event/ChildModeChangedEvent;", "enableEventBus", "goMoviePlayer", "movieDetail", "Lcom/movieboxpro/android/model/movie/MovieDetail;", "goTvPlayer", "tvDetail", "Lcom/movieboxpro/android/model/tv/TvDetail;", "hideErrorView", "hideLoadView", "hideLoadingView", "initData", "initListener", "initView", "loadData", "loginSuccess", "Lcom/movieboxpro/android/event/LoginEvent;", "logoutSuccess", "Lcom/movieboxpro/android/event/LogoutEvent;", "onDestroyView", "onFragmentResumeNoFirst", "onLowMemory", "Lcom/movieboxpro/android/event/LowMemoryEvent;", "onPlayFinish", "Lcom/movieboxpro/android/event/OnPlayFinishEvent;", "refreshData", "model", "Lcom/movieboxpro/android/model/FeaturedDataModel;", "setListItemType", "", "showContinueList", "showErrorView", "text", "showFeaturedData", "showScreenManage", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/DeviceModelResponse$DeviceModel;", "Lkotlin/collections/ArrayList;", NotificationCompat.CATEGORY_MESSAGE, "id", "boxType", "currSeason", "currEpisode", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class FeaturedFragment extends BaseMvpFragment<FeaturedPresenter> implements FeaturedContract.View {
    public static final Companion Companion = new Companion(null);
    public static final String FEATURED_CACHE = "featured_cache";
    private FeaturedAdapter adapter;
    private boolean continuePlaying;
    private View errorView;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private final FeaturedFragment$scrollListener$1 scrollListener = new RecyclerView.OnScrollListener() { // from class: com.movieboxpro.android.view.fragment.FeaturedFragment$scrollListener$1
        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            Context context;
            Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
            super.onScrollStateChanged(recyclerView, i);
            if (i == 0) {
                Context context2 = FeaturedFragment.this.getContext();
                if (context2 == null) {
                    return;
                }
                FragmentActivity activity = FeaturedFragment.this.getActivity();
                if ((activity == null || activity.isDestroyed()) ? false : true) {
                    Glide.with(context2).resumeRequests();
                }
            } else if (i != 1) {
                if (i == 2 && (context = FeaturedFragment.this.getContext()) != null) {
                    FragmentActivity activity2 = FeaturedFragment.this.getActivity();
                    if ((activity2 == null || activity2.isDestroyed()) ? false : true) {
                        Glide.with(context).pauseRequests();
                    }
                }
            } else {
                Context context3 = FeaturedFragment.this.getContext();
                if (context3 == null) {
                    return;
                }
                FragmentActivity activity3 = FeaturedFragment.this.getActivity();
                if ((activity3 == null || activity3.isDestroyed()) ? false : true) {
                    Glide.with(context3).resumeRequests();
                }
            }
        }
    };
    private boolean isFirstLoad = true;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showFeaturedData$lambda-16  reason: not valid java name */
    public static final void m1188showFeaturedData$lambda16(Throwable th) {
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected int bindLayout() {
        return R.layout.fragment_featured;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected boolean enableEventBus() {
        return true;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void initListener() {
        FeaturedAdapter featuredAdapter = this.adapter;
        if (featuredAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            featuredAdapter = null;
        }
        featuredAdapter.setFeaturedItemClickListener(new FeaturedFragment$initListener$1(this));
        ((SwipeRefreshLayout) _$_findCachedViewById(R.id.swipeRefreshLayout)).setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$FeaturedFragment$0R6_G9zMc-WEafMfI_0bxwQMO1I
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                FeaturedFragment.m1179initListener$lambda0(FeaturedFragment.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m1179initListener$lambda0(FeaturedFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.loadData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean checkChildMode(String str) {
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.CHILD_MODE, false) && CommonUtils.isChildModeVideo(str)) {
            ChildModeHintDialog childModeHintDialog = new ChildModeHintDialog(getContext());
            childModeHintDialog.setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$FeaturedFragment$LBxXvVeQNb0AEiRj-ucMamLotzo
                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                public final void onClick() {
                    FeaturedFragment.m1176checkChildMode$lambda2(FeaturedFragment.this);
                }
            });
            childModeHintDialog.show();
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: checkChildMode$lambda-2  reason: not valid java name */
    public static final void m1176checkChildMode$lambda2(FeaturedFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        if (activity == null) {
            return;
        }
        InputChildModePasswordActivity.Companion.start(activity, 100);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment, com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.recyclerView);
        if (recyclerView != null) {
            recyclerView.removeOnScrollListener(this.scrollListener);
        }
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void initData() {
        this.adapter = new FeaturedAdapter();
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.recyclerView);
        if (recyclerView != null) {
            CommonExtKt.disableRefreshAnimation(recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            FeaturedAdapter featuredAdapter = this.adapter;
            if (featuredAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                featuredAdapter = null;
            }
            recyclerView.setAdapter(featuredAdapter);
        }
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.recyclerView);
        if (recyclerView2 != null) {
            recyclerView2.addOnScrollListener(this.scrollListener);
        }
        getLoadingPopupView(new SimpleCallback() { // from class: com.movieboxpro.android.view.fragment.FeaturedFragment$initData$2
            @Override // com.lxj.xpopup.interfaces.SimpleCallback, com.lxj.xpopup.interfaces.XPopupCallback
            public boolean onBackPressed(BasePopupView basePopupView) {
                BaseContract.BasePresenter basePresenter;
                basePresenter = FeaturedFragment.this.mPresenter;
                ((FeaturedPresenter) basePresenter).cancelRequest();
                return super.onBackPressed(basePopupView);
            }
        });
        IncognitoModeLiveData.Companion.get().observe(this, new Observer() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$FeaturedFragment$YaPZrafu3BkVlGn-DkXk9KKtPSc
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FeaturedFragment.m1178initData$lambda4(FeaturedFragment.this, (Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-4  reason: not valid java name */
    public static final void m1178initData$lambda4(FeaturedFragment this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((FeaturedPresenter) this$0.mPresenter).requestFeaturedData(App.getUserData().uid_v2);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void initView() {
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) _$_findCachedViewById(R.id.swipeRefreshLayout);
        Intrinsics.checkNotNullExpressionValue(swipeRefreshLayout, "swipeRefreshLayout");
        CommonExtKt.initColor(swipeRefreshLayout);
        ((SmartRefreshLayout) _$_findCachedViewById(R.id.smartRefreshLayout)).setEnablePureScrollMode(true);
        ((SmartRefreshLayout) _$_findCachedViewById(R.id.smartRefreshLayout)).setEnableRefresh(false);
        ((SmartRefreshLayout) _$_findCachedViewById(R.id.smartRefreshLayout)).setEnableLoadMore(true);
        ((SmartRefreshLayout) _$_findCachedViewById(R.id.smartRefreshLayout)).setRefreshFooter(new ClassicsFooter(getContext()));
    }

    @Override // com.movieboxpro.android.view.fragment.FeaturedContract.View
    public void goTvPlayer(TvDetail tvDetail) {
        Intrinsics.checkNotNullParameter(tvDetail, "tvDetail");
        if (X86HintUtils.checkX86(getContext())) {
            return;
        }
        Context context = getContext();
        CastSession castSession = null;
        if (context != null) {
            try {
                castSession = CastContext.getSharedInstance(context).getSessionManager().getCurrentCastSession();
            } catch (RuntimeException e) {
                e.printStackTrace();
                castSession = null;
            }
        }
        if (castSession != null && castSession.isConnected()) {
            PlayerStrategy playerStrategy = new PlayerStrategy();
            playerStrategy.setInstace(tvDetail);
            Context context2 = getContext();
            if (context2 == null) {
                return;
            }
            ChooseActivity.Companion.start(context2, false, playerStrategy);
            return;
        }
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
        List<TvDetail.SeasonDetail> list = tvDetail.episode;
        Intrinsics.checkNotNullExpressionValue(list, "tvDetail.episode");
        for (TvDetail.SeasonDetail seasonDetail : list) {
            TvSeasonList tvSeasonList = new TvSeasonList();
            tvSeasonList.setEpisode(seasonDetail.episode);
            tvSeasonList.setSeason(seasonDetail.season);
            Long l = seasonDetail.play_progress.get(DownloadInfo.DOWNLOAD_OVER);
            long j = 0;
            tvSeasonList.setOver(l == null ? 0L : l.longValue());
            Long l2 = seasonDetail.play_progress.get("seconds");
            if (l2 != null) {
                j = l2.longValue();
            }
            tvSeasonList.setSeconds(j);
            tvSeasonList.setTid(seasonDetail.tid);
            tvSeasonList.setId(seasonDetail.id);
            arrayList.add(tvSeasonList);
        }
        Bundle build = ParamsUtils.newBuilder().addParam(TvDetailActivity.SEASON_TV_LIST, arrayList).addParam("videoplayer_params", tvDetail).addParam(VideoPlayerActivity.VIDEO_ID, tvDetail.seasonDetail.season).addParam("videoplayer_episode", tvDetail.seasonDetail.episode).addParam("FeaturedFragment", false).build();
        if (PopPlayerManager.Companion.getInstance().isPopShow()) {
            PopPlayerManager.Companion.getInstance().setNewPlay(build, tvDetail);
            return;
        }
        Intent intent = new Intent(getContext(), TvPlayerActivity.class);
        intent.putExtras(build);
        startActivity(intent);
    }

    @Override // com.movieboxpro.android.view.fragment.FeaturedContract.View
    public void goMoviePlayer(MovieDetail movieDetail) {
        Intrinsics.checkNotNullParameter(movieDetail, "movieDetail");
        Context context = getContext();
        CastSession castSession = null;
        if (context != null) {
            try {
                castSession = CastContext.getSharedInstance(context).getSessionManager().getCurrentCastSession();
            } catch (RuntimeException e) {
                e.printStackTrace();
                castSession = null;
            }
        }
        if (castSession != null && castSession.isConnected()) {
            PlayerStrategy playerStrategy = new PlayerStrategy();
            playerStrategy.setInstace(movieDetail);
            Context context2 = getContext();
            if (context2 == null) {
                return;
            }
            ChooseActivity.Companion.start(context2, false, playerStrategy);
            return;
        }
        Context context3 = getContext();
        if (PopPlayerManager.Companion.getInstance().isPopShow()) {
            PopPlayerManager.Companion.getInstance().setNewPlay(movieDetail);
        } else {
            MoviePlayerActivity.start(context3, movieDetail, movieDetail.id);
        }
    }

    @Override // com.movieboxpro.android.view.fragment.FeaturedContract.View
    public void showFeaturedData(final FeaturedDataModel model) {
        Intrinsics.checkNotNullParameter(model, "model");
        if (this.isFirstLoad) {
            boolean z = MainActivity.isFirst;
            EventBus.getDefault().post(new OnFeaturedFirstLoadEvent());
            MainActivity.isFirst = false;
            this.isFirstLoad = false;
            ((ObservableSubscribeProxy) Observable.just(model).map(new Function() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$FeaturedFragment$bn9tr2AIH9kFdLwcLpoK3EdMkCM
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    Unit m1186showFeaturedData$lambda11;
                    m1186showFeaturedData$lambda11 = FeaturedFragment.m1186showFeaturedData$lambda11(FeaturedDataModel.this, (FeaturedDataModel) obj);
                    return m1186showFeaturedData$lambda11;
                }
            }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Consumer() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$FeaturedFragment$11g9vQvyPaHjYBWtgPEhvZaxRfg
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    FeaturedFragment.m1187showFeaturedData$lambda15(FeaturedFragment.this, model, (Unit) obj);
                }
            }, $$Lambda$FeaturedFragment$xR5jO6akO2uwdLysgltsLA7JGWs.INSTANCE);
            return;
        }
        refreshData(model);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showFeaturedData$lambda-11  reason: not valid java name */
    public static final Unit m1186showFeaturedData$lambda11(FeaturedDataModel model, FeaturedDataModel it) {
        Intrinsics.checkNotNullParameter(model, "$model");
        Intrinsics.checkNotNullParameter(it, "it");
        CacheDiskUtils.getInstance().put(FEATURED_CACHE, JSON.toJSONString(model));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showFeaturedData$lambda-15  reason: not valid java name */
    public static final void m1187showFeaturedData$lambda15(FeaturedFragment this$0, FeaturedDataModel model, Unit unit) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        this$0.refreshData(model);
        if (MainActivity.continuePlaying) {
            MainActivity.continuePlaying = false;
            List<Homelist> homelists = model.getHomelists();
            if (homelists == null) {
                return;
            }
            for (Homelist homelist : homelists) {
                if (Intrinsics.areEqual(homelist.type, "continue")) {
                    List<Homelist.Typelist> list = homelist.list;
                    Intrinsics.checkNotNullExpressionValue(list, "it.list");
                    Homelist.Typelist typelist = (Homelist.Typelist) CollectionsKt.getOrNull(list, 0);
                    if (typelist == null) {
                        return;
                    }
                    FeaturedPresenter featuredPresenter = (FeaturedPresenter) this$0.mPresenter;
                    String str = typelist.id;
                    Intrinsics.checkNotNullExpressionValue(str, "item.id");
                    int i = typelist.box_type;
                    Homelist.History history = typelist.getHistory();
                    int season = history == null ? 0 : history.getSeason();
                    Homelist.History history2 = typelist.getHistory();
                    featuredPresenter.getPlayPath(str, i, season, history2 != null ? history2.getEpisode() : 0);
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void refreshData(FeaturedDataModel featuredDataModel) {
        List<Homelist> homelists;
        List<Homelist> homelists2;
        int i;
        List<Homelist> homelists3;
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.HIDE_MOVIELIST)) {
            if (featuredDataModel != null && (homelists2 = featuredDataModel.getHomelists()) != null) {
                i = 0;
                for (Homelist homelist : homelists2) {
                    if (Intrinsics.areEqual(homelist.type, "playlist")) {
                        break;
                    }
                    i++;
                }
            }
            i = -1;
            if (i != -1 && featuredDataModel != null && (homelists3 = featuredDataModel.getHomelists()) != null) {
                homelists3.remove(i);
            }
        }
        if (featuredDataModel != null && (homelists = featuredDataModel.getHomelists()) != null) {
            for (Homelist homelist2 : homelists) {
                int i2 = homelist2.box_type;
                if (i2 != 1) {
                    if (i2 == 2) {
                        String str = homelist2.type;
                        if (str == null || str.length() == 0) {
                            homelist2.list.clear();
                            List<AdvertisementModel> advertisementModels = featuredDataModel.getAdvertisementModels();
                            if (advertisementModels != null) {
                                for (AdvertisementModel advertisementModel : advertisementModels) {
                                    Homelist.Typelist typelist = new Homelist.Typelist();
                                    typelist.poster = advertisementModel.getImg();
                                    typelist.itemType = 8;
                                    typelist.setUrl(advertisementModel.getUrl());
                                    typelist.setType(advertisementModel.getType());
                                    typelist.id = String.valueOf(advertisementModel.getId());
                                    typelist.setTid(advertisementModel.getTid());
                                    homelist2.list.add(typelist);
                                }
                            }
                        } else {
                            List<Homelist.Typelist> list = homelist2.list;
                            Intrinsics.checkNotNullExpressionValue(list, "homeList.list");
                            setListItemType(4, list);
                            if (homelist2.ismore == 1) {
                                List<Homelist.Typelist> list2 = homelist2.list;
                                Intrinsics.checkNotNullExpressionValue(list2, "homeList.list");
                                String str2 = homelist2.name;
                                Intrinsics.checkNotNullExpressionValue(str2, "homeList.name");
                                String str3 = homelist2.type;
                                Intrinsics.checkNotNullExpressionValue(str3, "homeList.type");
                                addMoreItem(4, list2, str2, str3);
                            }
                        }
                    } else if (i2 == 4) {
                        List<Homelist.Typelist> list3 = homelist2.list;
                        Intrinsics.checkNotNullExpressionValue(list3, "homeList.list");
                        setListItemType(2, list3);
                        if (homelist2.ismore == 1) {
                            List<Homelist.Typelist> list4 = homelist2.list;
                            Intrinsics.checkNotNullExpressionValue(list4, "homeList.list");
                            String str4 = homelist2.name;
                            Intrinsics.checkNotNullExpressionValue(str4, "homeList.name");
                            String str5 = homelist2.type;
                            Intrinsics.checkNotNullExpressionValue(str5, "homeList.type");
                            addMoreItem(2, list4, str4, str5);
                        }
                    } else if (i2 == 5) {
                        List<Homelist.Typelist> list5 = homelist2.list;
                        Intrinsics.checkNotNullExpressionValue(list5, "homeList.list");
                        setListItemType(5, list5);
                    } else if (i2 == 6) {
                        homelist2.name = "";
                        List<Homelist.Typelist> list6 = homelist2.list;
                        Intrinsics.checkNotNullExpressionValue(list6, "homeList.list");
                        setListItemType(6, list6);
                    } else if (i2 == 7) {
                        List<Homelist.Typelist> list7 = homelist2.list;
                        Intrinsics.checkNotNullExpressionValue(list7, "homeList.list");
                        setListItemType(7, list7);
                    } else {
                        List<Homelist.Typelist> list8 = homelist2.list;
                        Intrinsics.checkNotNullExpressionValue(list8, "homeList.list");
                        setListItemType(3, list8);
                        if (homelist2.ismore == 1) {
                            List<Homelist.Typelist> list9 = homelist2.list;
                            Intrinsics.checkNotNullExpressionValue(list9, "homeList.list");
                            String str6 = homelist2.name;
                            Intrinsics.checkNotNullExpressionValue(str6, "homeList.name");
                            String str7 = homelist2.type;
                            Intrinsics.checkNotNullExpressionValue(str7, "homeList.type");
                            addMoreItem(3, list9, str6, str7);
                        }
                    }
                } else if (Intrinsics.areEqual(homelist2.type, "maybe_like")) {
                    List<Homelist.Typelist> list10 = homelist2.list;
                    Intrinsics.checkNotNullExpressionValue(list10, "homeList.list");
                    setListItemType(9, list10);
                    if (homelist2.ismore == 1) {
                        List<Homelist.Typelist> list11 = homelist2.list;
                        Intrinsics.checkNotNullExpressionValue(list11, "homeList.list");
                        String str8 = homelist2.name;
                        Intrinsics.checkNotNullExpressionValue(str8, "homeList.name");
                        String str9 = homelist2.type;
                        Intrinsics.checkNotNullExpressionValue(str9, "homeList.type");
                        addMoreItem(9, list11, str8, str9);
                    }
                } else {
                    List<Homelist.Typelist> list12 = homelist2.list;
                    Intrinsics.checkNotNullExpressionValue(list12, "homeList.list");
                    setListItemType(3, list12);
                    if (homelist2.ismore == 1) {
                        List<Homelist.Typelist> list13 = homelist2.list;
                        Intrinsics.checkNotNullExpressionValue(list13, "homeList.list");
                        String str10 = homelist2.name;
                        Intrinsics.checkNotNullExpressionValue(str10, "homeList.name");
                        String str11 = homelist2.type;
                        Intrinsics.checkNotNullExpressionValue(str11, "homeList.type");
                        addMoreItem(3, list13, str10, str11);
                    }
                }
            }
        }
        FeaturedAdapter featuredAdapter = this.adapter;
        if (featuredAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            featuredAdapter = null;
        }
        featuredAdapter.setList(featuredDataModel != null ? featuredDataModel.getHomelists() : null);
        ((SwipeRefreshLayout) _$_findCachedViewById(R.id.swipeRefreshLayout)).setRefreshing(false);
    }

    private final void addMoreItem(int i, List<Homelist.Typelist> list, String str, String str2) {
        Homelist.Typelist typelist = new Homelist.Typelist();
        typelist.id = "";
        typelist.setName(str);
        typelist.setFeaturedType(str2);
        typelist.itemType = i;
        list.add(typelist);
    }

    private final void setListItemType(int i, List<? extends Homelist.Typelist> list) {
        for (Homelist.Typelist typelist : list) {
            typelist.itemType = i;
        }
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void loadData() {
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) _$_findCachedViewById(R.id.swipeRefreshLayout);
        if (swipeRefreshLayout != null) {
            CommonExtKt.show(swipeRefreshLayout);
        }
        if (getContext() != null) {
            if (Network.isConnected(getContext())) {
                ((FeaturedPresenter) this.mPresenter).requestFeaturedData(App.getUserData().uid_v2);
                return;
            }
            ((ObservableSubscribeProxy) Observable.create(new ObservableOnSubscribe() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$FeaturedFragment$ceUddopXMlZ_09drx_sdNQTD46A
                @Override // io.reactivex.ObservableOnSubscribe
                public final void subscribe(ObservableEmitter observableEmitter) {
                    FeaturedFragment.m1184loadData$lambda21(FeaturedFragment.this, observableEmitter);
                }
            }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<FeaturedDataModel>() { // from class: com.movieboxpro.android.view.fragment.FeaturedFragment$loadData$2
                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onError(ApiException e) {
                    Intrinsics.checkNotNullParameter(e, "e");
                }

                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onStart(Disposable d) {
                    Intrinsics.checkNotNullParameter(d, "d");
                }

                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onSuccess(FeaturedDataModel model) {
                    Intrinsics.checkNotNullParameter(model, "model");
                    FeaturedFragment.this.refreshData(model);
                }
            });
            SwipeRefreshLayout swipeRefreshLayout2 = (SwipeRefreshLayout) _$_findCachedViewById(R.id.swipeRefreshLayout);
            if (swipeRefreshLayout2 != null) {
                CommonExtKt.hide(swipeRefreshLayout2);
            }
            ToastUtils.showShort("no internet", new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadData$lambda-21  reason: not valid java name */
    public static final void m1184loadData$lambda21(FeaturedFragment this$0, ObservableEmitter it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        String cacheData = CacheDiskUtils.getInstance().getString(FEATURED_CACHE, "");
        Intrinsics.checkNotNullExpressionValue(cacheData, "cacheData");
        if (cacheData.length() > 0) {
            it.onNext(JSON.parseObject(cacheData, FeaturedDataModel.class));
            it.onComplete();
            return;
        }
        this$0.showErrorView("no internet");
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment, com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void hideLoadingView() {
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) _$_findCachedViewById(R.id.swipeRefreshLayout);
        if (swipeRefreshLayout == null) {
            return;
        }
        CommonExtKt.hide(swipeRefreshLayout);
    }

    @Override // com.movieboxpro.android.view.fragment.FeaturedContract.View
    public void showErrorView(String text) {
        TextView textView;
        Intrinsics.checkNotNullParameter(text, "text");
        if (((ViewStub) _$_findCachedViewById(R.id.viewStub)) == null) {
            return;
        }
        View view = this.errorView;
        if (view == null) {
            View inflate = ((ViewStub) _$_findCachedViewById(R.id.viewStub)).inflate();
            this.errorView = inflate;
            TextView textView2 = inflate == null ? null : (TextView) inflate.findViewById(R.id.empty_text);
            if (textView2 != null) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("Load failed:%s", Arrays.copyOf(new Object[]{text}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                textView2.setText(format);
            }
            View view2 = this.errorView;
            textView = view2 != null ? (TextView) view2.findViewById(R.id.tvTryAgain) : null;
            if (textView != null) {
                textView.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$FeaturedFragment$78pe1_3EibDApqWMu8OGmywLtdY
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view3) {
                        FeaturedFragment.m1185showErrorView$lambda22(FeaturedFragment.this, view3);
                    }
                });
            }
        } else {
            textView = view != null ? (TextView) view.findViewById(R.id.empty_text) : null;
            if (textView != null) {
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                String format2 = String.format("Load failed:%s", Arrays.copyOf(new Object[]{text}, 1));
                Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
                textView.setText(format2);
            }
        }
        View view3 = this.errorView;
        if (view3 == null) {
            return;
        }
        CommonExtKt.visible(view3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showErrorView$lambda-22  reason: not valid java name */
    public static final void m1185showErrorView$lambda22(FeaturedFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.hideErrorView();
        this$0.loadData();
    }

    @Override // com.movieboxpro.android.view.fragment.FeaturedContract.View
    public void hideErrorView() {
        View view = this.errorView;
        if (view == null) {
            return;
        }
        CommonExtKt.gone(view);
    }

    @Override // com.movieboxpro.android.view.fragment.FeaturedContract.View
    public void showContinueList(List<? extends Homelist.Typelist> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        setListItemType(2, list);
        ArrayList arrayList = new ArrayList(list);
        if (arrayList.size() >= 10) {
            Homelist.Typelist typelist = new Homelist.Typelist();
            typelist.id = "";
            typelist.itemType = 2;
            arrayList.add(typelist);
        }
        FeaturedAdapter featuredAdapter = this.adapter;
        FeaturedAdapter featuredAdapter2 = null;
        if (featuredAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            featuredAdapter = null;
        }
        int i = 0;
        for (Object obj : featuredAdapter.getData()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            Homelist homelist = (Homelist) obj;
            if (Intrinsics.areEqual("continue", homelist.type)) {
                homelist.list = arrayList;
                FeaturedAdapter featuredAdapter3 = this.adapter;
                if (featuredAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                } else {
                    featuredAdapter2 = featuredAdapter3;
                }
                featuredAdapter2.notifyItemChanged(i);
                return;
            }
            i = i2;
        }
    }

    @Override // com.movieboxpro.android.view.fragment.FeaturedContract.View
    public void hideLoadView() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$FeaturedFragment$avKB7p1Z6-ZRzkMy_nJL2JSyou8
            @Override // java.lang.Runnable
            public final void run() {
                FeaturedFragment.m1177hideLoadView$lambda25(FeaturedFragment.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: hideLoadView$lambda-25  reason: not valid java name */
    public static final void m1177hideLoadView$lambda25(FeaturedFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.hideLoading();
    }

    @Override // com.movieboxpro.android.view.fragment.FeaturedContract.View
    public void showScreenManage(ArrayList<DeviceModelResponse.DeviceModel> list, String str, final String str2, final int i, final int i2, final int i3) {
        Intrinsics.checkNotNullParameter(list, "list");
        ScreenManageDialog newInstance$default = ScreenManageDialog.Companion.newInstance$default(ScreenManageDialog.Companion, list, str, false, 4, null);
        newInstance$default.setListener(new ScreenManageDialog.OnStopDeviceListener() { // from class: com.movieboxpro.android.view.fragment.FeaturedFragment$showScreenManage$1
            @Override // com.movieboxpro.android.view.dialog.ScreenManageDialog.OnStopDeviceListener
            public void onStopDevice() {
                BaseContract.BasePresenter basePresenter;
                basePresenter = FeaturedFragment.this.mPresenter;
                FeaturedPresenter featuredPresenter = (FeaturedPresenter) basePresenter;
                String str3 = str2;
                if (str3 == null) {
                    str3 = "";
                }
                featuredPresenter.getPlayPath(str3, i, i2, i3);
            }
        });
        newInstance$default.show(getChildFragmentManager(), ScreenManageDialog.class.getSimpleName());
    }

    @Override // com.movieboxpro.android.base.BaseLazyFragment
    public void onFragmentResumeNoFirst() {
        super.onFragmentResumeNoFirst();
        FeaturedAdapter featuredAdapter = this.adapter;
        if (featuredAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            featuredAdapter = null;
        }
        if (!featuredAdapter.getData().isEmpty() || ((SwipeRefreshLayout) _$_findCachedViewById(R.id.swipeRefreshLayout)).isRefreshing()) {
            return;
        }
        loadData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onPlayFinish(OnPlayFinishEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        ((FeaturedPresenter) this.mPresenter).getContinueList();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onLowMemory(LowMemoryEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (this.isVisibleToUser) {
            return;
        }
        FeaturedAdapter featuredAdapter = this.adapter;
        if (featuredAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            featuredAdapter = null;
        }
        featuredAdapter.setNewData(new ArrayList());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void childModeChanged(ChildModeChangedEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) _$_findCachedViewById(R.id.swipeRefreshLayout);
        Intrinsics.checkNotNullExpressionValue(swipeRefreshLayout, "swipeRefreshLayout");
        CommonExtKt.hide(swipeRefreshLayout);
        loadData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void loginSuccess(LoginEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) _$_findCachedViewById(R.id.swipeRefreshLayout);
        Intrinsics.checkNotNullExpressionValue(swipeRefreshLayout, "swipeRefreshLayout");
        CommonExtKt.hide(swipeRefreshLayout);
        loadData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void logoutSuccess(LogoutEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) _$_findCachedViewById(R.id.swipeRefreshLayout);
        Intrinsics.checkNotNullExpressionValue(swipeRefreshLayout, "swipeRefreshLayout");
        CommonExtKt.hide(swipeRefreshLayout);
        loadData();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    public FeaturedPresenter bindPresenter() {
        return new FeaturedPresenter(this);
    }

    /* compiled from: FeaturedFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/movieboxpro/android/view/fragment/FeaturedFragment$Companion;", "", "()V", "FEATURED_CACHE", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
