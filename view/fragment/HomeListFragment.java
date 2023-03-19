package com.movieboxpro.android.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import androidx.mediarouter.app.MediaRouteButton;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.google.android.gms.cast.framework.CastButtonFactory;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.CastStateListener;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.google.android.gms.common.GoogleApiAvailability;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.TabLayoutPagerAdapter;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseLazyFragment;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.event.AvatarClickEvent;
import com.movieboxpro.android.event.ChangeTabPosEvent;
import com.movieboxpro.android.event.ChildModeChangedEvent;
import com.movieboxpro.android.event.LoginEvent;
import com.movieboxpro.android.event.LogoutEvent;
import com.movieboxpro.android.event.OnFilterCompleteEvent;
import com.movieboxpro.android.event.OnNoticeCountEvent;
import com.movieboxpro.android.event.OnNoticeRefreshEvent;
import com.movieboxpro.android.event.OpenFilterEvent;
import com.movieboxpro.android.event.OpenStickFilterEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.livedata.IncognitoModeLiveData;
import com.movieboxpro.android.model.NoticeCountModel;
import com.movieboxpro.android.model.common.Gener;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.view.activity.settings.InputChildModePasswordActivity;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.MsgHintDialog;
import com.movieboxpro.android.view.fragment.home.MoviesFragment;
import com.movieboxpro.android.view.fragment.home.TvGuideFragment;
import com.movieboxpro.android.view.fragment.home.TvShowsFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
/* loaded from: classes.dex */
public class HomeListFragment extends BaseLazyFragment {
    private static final String[] TAB_TITLES_NOTICE = {"post", "pcomment", "activity", "reward", "goods", "at"};
    private static final int childModeRequestCode = 10;
    private View childModeView;
    @BindView(R.id.childModeViewStub)
    ViewStub childModeViewStub;
    private Context context;
    @BindView(R.id.dot)
    View dot;
    @BindView(R.id.flCast)
    FrameLayout flCast;
    private List<Gener> geners;
    @BindView(R.id.homelist_view_logo)
    ImageView homelistViewLogo;
    @BindView(R.id.homelist_view_more)
    ImageView homelistViewMore;
    private KProgressHUD hud;
    private View incognitoModeView;
    @BindView(R.id.incognitoModeViewStub)
    ViewStub incognitoModeViewStub;
    @BindView(R.id.ivFilter)
    ImageView ivFilter;
    private CastContext mCastContext;
    private CastSession mCastSession;
    private CastStateListener mCastStateListener;
    @BindView(R.id.search_result_pager)
    ViewPager mPager;
    @BindView(R.id.search_result_tab)
    SmartTabLayout mTabs;
    @BindView(R.id.media_route_button)
    MediaRouteButton mediaRouteButton;
    private boolean movieInFilter;
    private boolean tvInFilter;
    private Unbinder unbinder;
    private int selectPosition = 0;
    private String[] TAB_TITLES = {"Featured", "Movies", "TV Shows", "TV Guide"};
    private int type = 0;
    private final SessionManagerListener<CastSession> mSessionManagerListener = new MySessionManagerListener();
    boolean gCastable = false;
    boolean gCastTested = false;

    @OnClick({R.id.homelist_view_more})
    public void onViewClicked() {
        EventBus.getDefault().post(new AvatarClickEvent());
    }

    @OnClick({R.id.ivFilter})
    public void onFilterClicked() {
        int i = this.selectPosition;
        if (i == 0 || i == 3) {
            if (this.TAB_TITLES.length == 4) {
                this.mPager.setCurrentItem(1, false);
            } else {
                this.mPager.setCurrentItem(1, false);
            }
            if (this.geners == null) {
                getCats();
            } else {
                EventBus.getDefault().postSticky(new OpenStickFilterEvent(1, this.geners));
            }
        } else if (this.geners == null) {
            getCats();
        } else if (i == 1) {
            EventBus.getDefault().post(new OpenFilterEvent(1, this.geners));
        } else {
            EventBus.getDefault().post(new OpenFilterEvent(2, this.geners));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_home, viewGroup, false);
        this.unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initView();
        initData();
    }

    private void getCats() {
        ((ObservableSubscribeProxy) Http.getService().Cat_list(API.BASE_URL, "Cat_list").compose(RxUtils.rxTranslate2List(Gener.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<List<Gener>>() { // from class: com.movieboxpro.android.view.fragment.HomeListFragment.1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                HomeListFragment.this.showLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(List<Gener> list) {
                HomeListFragment.this.hideLoading();
                HomeListFragment.this.geners = list;
                if (HomeListFragment.this.selectPosition == 1) {
                    EventBus.getDefault().post(new OpenFilterEvent(1, HomeListFragment.this.geners));
                } else if (HomeListFragment.this.selectPosition == 2) {
                    EventBus.getDefault().post(new OpenFilterEvent(2, HomeListFragment.this.geners));
                } else {
                    EventBus.getDefault().postSticky(new OpenStickFilterEvent(1, HomeListFragment.this.geners));
                }
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException apiException) {
                HomeListFragment.this.hideLoading();
            }
        });
    }

    @Override // com.movieboxpro.android.base.BaseLazyFragment
    public void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
    }

    @Override // com.movieboxpro.android.base.BaseLazyFragment
    public void onFragmentResume() {
        super.onFragmentResume();
        getNoticeCount();
        EventBus.getDefault().post(new OnNoticeRefreshEvent());
    }

    private void getNoticeCount() {
        if (App.isLogin()) {
            ((ObservableSubscribeProxy) HttpRequest.post("User_activity_unread_count").asRequest().compose(RxUtils.rxTranslate2Bean(NoticeCountModel.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<NoticeCountModel>() { // from class: com.movieboxpro.android.view.fragment.HomeListFragment.2
                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onError(ApiException apiException) {
                }

                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onStart(Disposable disposable) {
                }

                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onSuccess(NoticeCountModel noticeCountModel) {
                    if (noticeCountModel.getCount() > 0) {
                        HomeListFragment.this.dot.setVisibility(0);
                    } else {
                        HomeListFragment.this.dot.setVisibility(8);
                    }
                }
            });
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFilterComplete(OnFilterCompleteEvent onFilterCompleteEvent) {
        if (this.selectPosition != 0) {
            if (onFilterCompleteEvent.isComplete()) {
                this.ivFilter.setImageResource(R.mipmap.ic_filter_seleted);
                if (onFilterCompleteEvent.isMovie()) {
                    this.movieInFilter = true;
                    return;
                } else {
                    this.tvInFilter = true;
                    return;
                }
            }
            this.ivFilter.setImageResource(R.mipmap.ic_filter);
            if (onFilterCompleteEvent.isMovie()) {
                this.movieInFilter = false;
            } else {
                this.tvInFilter = false;
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeTab(ChangeTabPosEvent changeTabPosEvent) {
        if (changeTabPosEvent.getPos() == 0) {
            this.mPager.setCurrentItem(1, false);
        } else {
            this.mPager.setCurrentItem(2, false);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void childModeChanged(ChildModeChangedEvent childModeChangedEvent) {
        if (childModeChangedEvent.isOpen()) {
            View view = this.childModeView;
            if (view != null) {
                view.setVisibility(0);
                return;
            }
            View inflate = this.childModeViewStub.inflate();
            this.childModeView = inflate;
            inflate.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.HomeListFragment.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    if (HomeListFragment.this.getActivity() != null) {
                        InputChildModePasswordActivity.Companion.start(HomeListFragment.this.getActivity(), 10);
                    }
                }
            });
            return;
        }
        View view2 = this.childModeView;
        if (view2 != null) {
            view2.setVisibility(8);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNoticeCount(OnNoticeCountEvent onNoticeCountEvent) {
        if (onNoticeCountEvent.getCount() > 0) {
            this.dot.setVisibility(0);
        } else {
            this.dot.setVisibility(8);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogin(LoginEvent loginEvent) {
        initPages();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogout(LogoutEvent logoutEvent) {
        View view = this.childModeView;
        if (view != null) {
            view.setVisibility(8);
        }
        initPages();
    }

    public void initView() {
        this.context = getContext();
        if (getActivity() != null) {
            if (isCastAvailable(getActivity(), 100)) {
                initChromeCast();
            } else {
                this.flCast.setVisibility(8);
            }
        }
        initChildModeHintView();
    }

    private void initChildModeHintView() {
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.CHILD_MODE, false)) {
            View inflate = this.childModeViewStub.inflate();
            this.childModeView = inflate;
            inflate.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.HomeListFragment.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (HomeListFragment.this.getActivity() != null) {
                        InputChildModePasswordActivity.Companion.start(HomeListFragment.this.getActivity(), 10);
                    }
                }
            });
        }
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.Incognito_Mode, false)) {
            View inflate2 = this.incognitoModeViewStub.inflate();
            this.incognitoModeView = inflate2;
            inflate2.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.HomeListFragment.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    new MsgHintDialog.Builder(HomeListFragment.this.context).setContent("Are you sure to quit private mode?").setCancelable(false).setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.fragment.HomeListFragment.5.1
                        @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                        public void onClick() {
                            PrefsUtils.getInstance().putBoolean(Constant.Prefs.Incognito_Mode, false);
                            IncognitoModeLiveData.Companion.get().postValue(false);
                        }
                    }).create().show();
                }
            });
            ((TextView) this.incognitoModeView.findViewById(R.id.textView)).setText("- Private Mode -");
        }
        IncognitoModeLiveData.Companion.get().observe(getViewLifecycleOwner(), new Observer<Boolean>() { // from class: com.movieboxpro.android.view.fragment.HomeListFragment.6
            @Override // androidx.lifecycle.Observer
            public void onChanged(Boolean bool) {
                if (bool.booleanValue()) {
                    if (HomeListFragment.this.incognitoModeView != null) {
                        HomeListFragment.this.incognitoModeView.setVisibility(0);
                        return;
                    }
                    HomeListFragment homeListFragment = HomeListFragment.this;
                    homeListFragment.incognitoModeView = homeListFragment.incognitoModeViewStub.inflate();
                    HomeListFragment.this.incognitoModeView.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.HomeListFragment.6.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            new MsgHintDialog.Builder(HomeListFragment.this.context).setContent("Are you sure to quit private mode?").setCancelable(false).setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.fragment.HomeListFragment.6.1.1
                                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                                public void onClick() {
                                    PrefsUtils.getInstance().putBoolean(Constant.Prefs.Incognito_Mode, false);
                                    IncognitoModeLiveData.Companion.get().postValue(false);
                                }
                            }).create().show();
                        }
                    });
                    ((TextView) HomeListFragment.this.incognitoModeView.findViewById(R.id.textView)).setText("- Private Mode -");
                } else if (HomeListFragment.this.incognitoModeView != null) {
                    HomeListFragment.this.incognitoModeView.setVisibility(8);
                }
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        try {
            if (this.mCastContext != null) {
                this.mCastContext.getSessionManager().addSessionManagerListener(this.mSessionManagerListener, CastSession.class);
                this.mCastContext.addCastStateListener(this.mCastStateListener);
                if (this.mCastSession == null && this.context != null) {
                    this.mCastSession = CastContext.getSharedInstance(this.context).getSessionManager().getCurrentCastSession();
                }
                if (this.mCastContext != null && this.mCastContext.getCastState() != 1) {
                    this.flCast.setVisibility(0);
                }
                refreshCastStatus();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onStart();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        CastContext castContext = this.mCastContext;
        if (castContext != null) {
            try {
                castContext.removeCastStateListener(this.mCastStateListener);
                this.mCastContext.getSessionManager().removeSessionManagerListener(this.mSessionManagerListener, CastSession.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onStop();
    }

    private void initChromeCast() {
        if (getContext() != null) {
            try {
                CastButtonFactory.setUpMediaRouteButton(getContext().getApplicationContext(), this.mediaRouteButton);
                this.mediaRouteButton.setRemoteIndicatorDrawable(getResources().getDrawable(R.drawable.mr_button_dark));
                this.mCastStateListener = new CastStateListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$HomeListFragment$hXhI99Q5Ol-2ct8HNcUS5PyKZxk
                    @Override // com.google.android.gms.cast.framework.CastStateListener
                    public final void onCastStateChanged(int i) {
                        HomeListFragment.this.lambda$initChromeCast$0$HomeListFragment(i);
                    }
                };
                this.mCastContext = CastContext.getSharedInstance(this.context);
            } catch (Exception e) {
                this.flCast.setVisibility(8);
                e.printStackTrace();
            }
        }
    }

    public /* synthetic */ void lambda$initChromeCast$0$HomeListFragment(int i) {
        if (i != 1) {
            this.flCast.setVisibility(0);
        } else {
            this.flCast.setVisibility(8);
        }
    }

    public void initData() {
        initTabs(this.TAB_TITLES);
    }

    private void checkTvDevice() {
        Context context = this.context;
        if (context != null) {
            PackageManager packageManager = context.getPackageManager();
            packageManager.hasSystemFeature("android.software.leanback");
            packageManager.hasSystemFeature("org.chromium.arc.device_management");
            packageManager.hasSystemFeature("android.hardware.touchscreen");
        }
    }

    public void showLoading() {
        KProgressHUD kProgressHUD = this.hud;
        if (kProgressHUD == null || !kProgressHUD.isShowing()) {
            KProgressHUD kProgressHUD2 = this.hud;
            if (kProgressHUD2 == null || !kProgressHUD2.isShowing()) {
                this.hud = KProgressHUD.create(getContext()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(1).setDimAmount(0.5f).show();
            }
        }
    }

    public void hideLoading() {
        KProgressHUD kProgressHUD = this.hud;
        if (kProgressHUD == null || !kProgressHUD.isShowing()) {
            return;
        }
        this.hud.dismiss();
    }

    private void initTabs(String[] strArr) {
        initPages();
    }

    private static String makeFragmentName(long j) {
        return "android:switcher:2131297751:" + j;
    }

    private void initTab() {
        if (App.isLogin() && App.getUserData().getHide_tv_movielist() == 0) {
            ArrayList arrayList = new ArrayList(4);
            FeaturedFragment featuredFragment = (FeaturedFragment) getChildFragmentManager().findFragmentByTag(makeFragmentName(0L));
            if (featuredFragment == null) {
                featuredFragment = new FeaturedFragment();
            }
            MoviesFragment moviesFragment = (MoviesFragment) getChildFragmentManager().findFragmentByTag(makeFragmentName(1L));
            if (moviesFragment == null) {
                moviesFragment = new MoviesFragment();
            }
            TvShowsFragment tvShowsFragment = (TvShowsFragment) getChildFragmentManager().findFragmentByTag(makeFragmentName(2L));
            if (tvShowsFragment == null) {
                tvShowsFragment = new TvShowsFragment();
            }
            TvGuideFragment tvGuideFragment = (TvGuideFragment) getChildFragmentManager().findFragmentByTag(makeFragmentName(3L));
            if (tvGuideFragment == null) {
                tvGuideFragment = TvGuideFragment.Companion.newInstance();
            }
            arrayList.add(featuredFragment);
            arrayList.add(moviesFragment);
            arrayList.add(tvShowsFragment);
            arrayList.add(tvGuideFragment);
            this.TAB_TITLES = new String[]{"Featured", "Movies", "TV Shows", "TV Guide"};
            TabLayoutPagerAdapter tabLayoutPagerAdapter = new TabLayoutPagerAdapter(getChildFragmentManager(), arrayList, this.TAB_TITLES);
            this.mPager.setOffscreenPageLimit(arrayList.size());
            this.mPager.setAdapter(tabLayoutPagerAdapter);
            return;
        }
        ArrayList arrayList2 = new ArrayList(2);
        FeaturedFragment featuredFragment2 = (FeaturedFragment) getChildFragmentManager().findFragmentByTag(makeFragmentName(0L));
        if (featuredFragment2 == null) {
            featuredFragment2 = new FeaturedFragment();
        }
        MoviesFragment moviesFragment2 = (MoviesFragment) getChildFragmentManager().findFragmentByTag(makeFragmentName(1L));
        if (moviesFragment2 == null) {
            moviesFragment2 = new MoviesFragment();
        }
        arrayList2.add(featuredFragment2);
        arrayList2.add(moviesFragment2);
        this.TAB_TITLES = new String[]{"Featured", "Movies"};
        TabLayoutPagerAdapter tabLayoutPagerAdapter2 = new TabLayoutPagerAdapter(getChildFragmentManager(), arrayList2, this.TAB_TITLES);
        this.mPager.setOffscreenPageLimit(arrayList2.size());
        this.mPager.setAdapter(tabLayoutPagerAdapter2);
    }

    private void initPages() {
        initTab();
        this.mTabs.setViewPager(this.mPager);
        this.mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.movieboxpro.android.view.fragment.HomeListFragment.7
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                HomeListFragment.this.selectPosition = i;
                if (HomeListFragment.this.TAB_TITLES.length != 4) {
                    if (HomeListFragment.this.TAB_TITLES.length == 2) {
                        if (i == 0) {
                            HomeListFragment.this.ivFilter.setImageResource(R.mipmap.ic_filter);
                        } else if (i == 1) {
                            if (HomeListFragment.this.movieInFilter) {
                                HomeListFragment.this.ivFilter.setImageResource(R.mipmap.ic_filter_seleted);
                            } else {
                                HomeListFragment.this.ivFilter.setImageResource(R.mipmap.ic_filter);
                            }
                        }
                    }
                } else if (i == 0 || i == 3) {
                    HomeListFragment.this.ivFilter.setImageResource(R.mipmap.ic_filter);
                } else if (i == 1) {
                    if (HomeListFragment.this.movieInFilter) {
                        HomeListFragment.this.ivFilter.setImageResource(R.mipmap.ic_filter_seleted);
                    } else {
                        HomeListFragment.this.ivFilter.setImageResource(R.mipmap.ic_filter);
                    }
                } else if (i == 2) {
                    if (HomeListFragment.this.tvInFilter) {
                        HomeListFragment.this.ivFilter.setImageResource(R.mipmap.ic_filter_seleted);
                    } else {
                        HomeListFragment.this.ivFilter.setImageResource(R.mipmap.ic_filter);
                    }
                }
            }
        });
    }

    /* loaded from: classes3.dex */
    private class MySessionManagerListener implements SessionManagerListener<CastSession> {
        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionEnding(CastSession castSession) {
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionResumeFailed(CastSession castSession, int i) {
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionResuming(CastSession castSession, String str) {
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionStartFailed(CastSession castSession, int i) {
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionStarting(CastSession castSession) {
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionSuspended(CastSession castSession, int i) {
        }

        private MySessionManagerListener() {
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionEnded(CastSession castSession, int i) {
            if (castSession == HomeListFragment.this.mCastSession) {
                HomeListFragment.this.mCastSession = null;
            }
            HomeListFragment.this.refreshCastStatus();
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionResumed(CastSession castSession, boolean z) {
            HomeListFragment.this.mCastSession = castSession;
            HomeListFragment.this.refreshCastStatus();
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionStarted(CastSession castSession, String str) {
            HomeListFragment.this.mCastSession = castSession;
            HomeListFragment.this.refreshCastStatus();
        }
    }

    public boolean isCastAvailable(Activity activity, int i) {
        if (this.gCastTested) {
            return this.gCastable;
        }
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int isGooglePlayServicesAvailable = googleApiAvailability.isGooglePlayServicesAvailable(activity);
        if (isGooglePlayServicesAvailable == 0) {
            this.gCastable = true;
            this.gCastTested = true;
            return true;
        } else if (isGooglePlayServicesAvailable == 9) {
            this.gCastable = false;
            this.gCastTested = true;
            return false;
        } else if (googleApiAvailability.isUserResolvableError(isGooglePlayServicesAvailable)) {
            googleApiAvailability.getErrorDialog(activity, isGooglePlayServicesAvailable, i, new DialogInterface.OnCancelListener() { // from class: com.movieboxpro.android.view.fragment.HomeListFragment.8
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    HomeListFragment.this.gCastable = false;
                    HomeListFragment.this.gCastTested = false;
                }
            }).show();
            return this.gCastable;
        } else {
            this.gCastTested = true;
            this.gCastable = false;
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshCastStatus() {
        CastSession castSession = this.mCastSession;
        if (castSession == null || !castSession.isConnected()) {
            return;
        }
        this.flCast.setVisibility(0);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        this.mCastContext = null;
        this.mCastSession = null;
        this.mediaRouteButton = null;
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }

    @Override // com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.unbinder.unbind();
        super.onDestroyView();
    }
}
