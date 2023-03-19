package com.movieboxpro.android.view.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import butterknife.BindView;
import com.alibaba.fastjson.JSONObject;
import com.ares.downloader.jarvis.core.StartAllDownloadEvent;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.gyf.immersionbar.ImmersionBar;
import com.movieboxpro.android.BuildConfig;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.app.RxManager;
import com.movieboxpro.android.base.BaseActivity;
import com.movieboxpro.android.base.BaseFragment;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.config.ConfigKey;
import com.movieboxpro.android.config.ConfigUtils;
import com.movieboxpro.android.db.entity.Download;
import com.movieboxpro.android.event.AvatarClickEvent;
import com.movieboxpro.android.event.ChildModeChangedEvent;
import com.movieboxpro.android.event.ConfigReceiveEvent;
import com.movieboxpro.android.event.DownloadChangedEvent;
import com.movieboxpro.android.event.LoginEvent;
import com.movieboxpro.android.event.LogoutEvent;
import com.movieboxpro.android.event.OnFeaturedFirstLoadEvent;
import com.movieboxpro.android.event.OnScanResultEvent;
import com.movieboxpro.android.event.SuperChildModeChangedEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.APIService;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.CallManager;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.http.HttpUtils;
import com.movieboxpro.android.livedata.HideMovieListLiveData;
import com.movieboxpro.android.livedata.SwitchRecommendLiveData;
import com.movieboxpro.android.livedata.SwitchWaitingLiveData;
import com.movieboxpro.android.model.ServiceInfo;
import com.movieboxpro.android.model.TestSpeedModel;
import com.movieboxpro.android.model.UserAgreementModel;
import com.movieboxpro.android.model.common.NetTestModel;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.receiver.MovieDownloadReceiver;
import com.movieboxpro.android.service.DownloadService;
import com.movieboxpro.android.service.MemoryMonitorService;
import com.movieboxpro.android.utils.ClickCounter;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.ReviewRecordUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.utils.Utils;
import com.movieboxpro.android.utils.tool.ActivityUtils;
import com.movieboxpro.android.view.activity.user.Login2Activity;
import com.movieboxpro.android.view.activity.user.VipActivity;
import com.movieboxpro.android.view.dialog.BottomSheetMsgDialog;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.TimeCheckDialog;
import com.movieboxpro.android.view.dialog.UpdateSheetDialog;
import com.movieboxpro.android.view.dialog.VipExpireDialog;
import com.movieboxpro.android.view.fragment.CollectFragment;
import com.movieboxpro.android.view.fragment.CollectFragment2;
import com.movieboxpro.android.view.fragment.DownloadFragment;
import com.movieboxpro.android.view.fragment.HomeListFragment;
import com.movieboxpro.android.view.fragment.SearchFragment;
import com.movieboxpro.android.view.fragment.movielist.MovieListFragment;
import com.movieboxpro.android.view.fragment.userinfo.UserInfoFragment3;
import com.movieboxpro.android.view.listener.ITabListener;
import com.movieboxpro.android.view.widget.UpdateChecker;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;
import okhttp3.Request;
import okhttp3.Response;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
/* loaded from: classes.dex */
public class MainActivity extends BaseActivity implements Observer, ITabListener {
    public static final String TAG_DOWNLOAD = "DOWNLOAD";
    public static final String TAG_FAVORITE = "FAVORITE";
    public static final String TAG_HOME = "HOME";
    public static final String TAG_MOVIE_LIST = "MOVIE_LIST";
    public static final String TAG_SEARCH = "SEARCH";
    public static boolean continuePlaying = false;
    public static boolean isFirst = true;
    @BindView(R.id.container)
    LinearLayout container;
    @BindView(R.id.content)
    FrameLayout content;
    private ClickCounter counter;
    private int current;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    private FragmentManager fragmentManager;
    private List<Fragment> fragments;
    private int[] icons;
    @BindView(R.id.main_tabs)
    TabLayout mTab;
    private MovieDownloadReceiver receiver;
    private UpdateSheetDialog systemDialog;
    private String[] titles;
    private boolean upgradeChecked = false;
    private long lastHintVipTime = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$statService$2(Throwable th) throws Exception {
    }

    @Override // com.movieboxpro.android.base.BaseActivity
    protected boolean isRequireNetWork() {
        return false;
    }

    @Override // com.movieboxpro.android.base.BaseActivity
    protected boolean isShowWarning() {
        return false;
    }

    @Override // com.movieboxpro.android.base.BaseActivity
    protected boolean isStatusBarTint() {
        return false;
    }

    @Override // com.movieboxpro.android.base.BaseActivity
    protected boolean isSupportBack() {
        return false;
    }

    @Override // com.movieboxpro.android.base.BaseActivity
    protected boolean isTitleBarShow() {
        return false;
    }

    @Override // java.util.Observer
    public void update(Observable observable, Object obj) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT >= 21) {
            if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.BLACK_MODE)) {
                ImmersionBar.with(this).statusBarColor(R.color.color_main_black).navigationBarColor(R.color.color_main_black).init();
            } else {
                ImmersionBar.with(this).statusBarColor(R.color.color_main).navigationBarColor(R.color.color_main).init();
            }
        }
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public View loadView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        if (Build.VERSION.SDK_INT >= 21) {
            return layoutInflater.inflate(R.layout.activity_main, viewGroup, false);
        }
        return layoutInflater.inflate(R.layout.activity_main_kitkat, viewGroup, false);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initView() {
        if (Build.VERSION.SDK_INT < 21 && !isScreenLandscape()) {
            DensityUtils.addMargin(this.container, 0, 0, 0, 40);
        }
        this.app.addObserver(this);
        PrefsUtils.getInstance().putBoolean(Constant.Prefs.IS_FIRST_OPEN, false);
        ClickCounter clickCounter = new ClickCounter(2, ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
        this.counter = clickCounter;
        clickCounter.setListener(new ClickCounter.OnCountListener() { // from class: com.movieboxpro.android.view.activity.MainActivity.1
            @Override // com.movieboxpro.android.utils.ClickCounter.OnCountListener
            public void onCount(int i) {
                ToastUtils.showShort("Press again to quit");
            }

            @Override // com.movieboxpro.android.utils.ClickCounter.OnCountListener
            public void onFinish() {
                MainActivity.this.finish();
                App.exit();
            }
        });
        this.fragmentManager = getSupportFragmentManager();
        this.mTab.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.movieboxpro.android.view.activity.MainActivity.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                MainActivity.this.mTab.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        if (this.savedInstanceState == null) {
            FragmentUtils.add(getSupportFragmentManager(), new UserInfoFragment3(), UserInfoFragment3.class.getSimpleName(), (int) R.id.drawerFrameLayout);
        } else {
            UserInfoFragment3 userInfoFragment3 = (UserInfoFragment3) getSupportFragmentManager().findFragmentByTag(UserInfoFragment3.class.getSimpleName());
            if (userInfoFragment3 != null) {
                FragmentUtils.show(userInfoFragment3);
            } else {
                FragmentUtils.add(getSupportFragmentManager(), new UserInfoFragment3(), UserInfoFragment3.class.getSimpleName(), (int) R.id.drawerFrameLayout);
            }
        }
        checkServiceAvailable();
    }

    private void checkServiceAvailable() {
        ((ObservableSubscribeProxy) Http.getService().Register_NeedCode(API.BASE_URL, API.Common.TEST_NETWORK).compose(RxUtils.rxTranslate2Bean(TestSpeedModel.class)).map(new Function<TestSpeedModel, Boolean>() { // from class: com.movieboxpro.android.view.activity.MainActivity.4
            @Override // io.reactivex.functions.Function
            public Boolean apply(TestSpeedModel testSpeedModel) throws Exception {
                String string = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, "");
                if (!TextUtils.isEmpty(string)) {
                    for (NetTestModel netTestModel : testSpeedModel.getOur()) {
                        if (string.equals(netTestModel.group_id)) {
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new io.reactivex.Observer<Boolean>() { // from class: com.movieboxpro.android.view.activity.MainActivity.3
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(Boolean bool) {
                if (bool.booleanValue()) {
                    PrefsUtils.getInstance().remove(Constant.Prefs.NETWORK_GROUP);
                    PrefsUtils.getInstance().remove(Constant.Prefs.NETWORK_STATE);
                }
            }
        });
    }

    private void showUpdateAlrt() {
        String readStringConfig = ConfigUtils.readStringConfig(ConfigKey.APK_INFO_MIN);
        final String readStringConfig2 = ConfigUtils.readStringConfig(ConfigKey.APK_URL);
        new BottomSheetMsgDialog.Builder(this).setContent(readStringConfig).setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$MainActivity$ZWBbKI7JXRHI6YIpsq4s9XU2k4E
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public final void onClick() {
                MainActivity.lambda$showUpdateAlrt$0(readStringConfig2);
            }
        }).setNegativeGone().setCancelable(false).create().show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$showUpdateAlrt$0(String str) {
        SystemUtils.startBrowser(App.getInstance(), str);
        System.exit(0);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onStartDownload(StartAllDownloadEvent startAllDownloadEvent) {
        io.reactivex.Observable.timer(3L, TimeUnit.SECONDS).compose(RxUtils.rxSchedulerHelper()).subscribe(new io.reactivex.Observer<Long>() { // from class: com.movieboxpro.android.view.activity.MainActivity.5
            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onNext(Long l) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                if (Utils.isAppForeground()) {
                    Intent intent = new Intent(MainActivity.this, DownloadService.class);
                    intent.setAction(Constant.ACTION.MOVIE_ALLDOWNLOAD);
                    MainActivity.this.startService(intent);
                }
            }
        });
    }

    public void statService() {
        List<Download> findByStatue = App.getDB().downloadDao().findByStatue(1);
        List<Download> findByStatue2 = App.getDB().downloadDao().findByStatue(4);
        List<Download> findByStatue3 = App.getDB().downloadDao().findByStatue(0);
        int size = findByStatue != null ? findByStatue.size() + 0 : 0;
        if (findByStatue2 != null) {
            size += findByStatue2.size();
        }
        if (findByStatue3 != null) {
            size += findByStatue3.size();
        }
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.USER_CELLUAR_DOWNLOAD, false)) {
            if (size > 0 && Utils.isAppForeground()) {
                Intent intent = new Intent(this.context, DownloadService.class);
                intent.setAction(Constant.ACTION.MOVIE_ALLDOWNLOAD);
                this.context.startService(intent);
            }
        } else if (Network.isWifiConnected(this) && size > 0 && Utils.isAppForeground()) {
            Intent intent2 = new Intent(this.context, DownloadService.class);
            intent2.setAction(Constant.ACTION.MOVIE_ALLDOWNLOAD);
            this.context.startService(intent2);
        }
        this.receiver = new MovieDownloadReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.ACTION.DOWNLOAD_MOVIE_FAILURE);
        LocalBroadcastManager.getInstance(this.context).registerReceiver(this.receiver, intentFilter);
        ((ObservableSubscribeProxy) io.reactivex.Observable.timer(5L, TimeUnit.SECONDS).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Consumer() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$MainActivity$SJZU-eglZVCoUWq-Fy1IuwMCGZI
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                MainActivity.this.lambda$statService$1$MainActivity((Long) obj);
            }
        }, $$Lambda$MainActivity$FH5U_ldrdTfpVBZOYP9eXT0AyuY.INSTANCE);
    }

    public /* synthetic */ void lambda$statService$1$MainActivity(Long l) throws Exception {
        getFireBaseToken();
    }

    private void getFireBaseToken() {
        if (App.isLogin()) {
            FirebaseMessaging.getInstance().getToken().addOnCompleteListener(this, new OnCompleteListener<String>() { // from class: com.movieboxpro.android.view.activity.MainActivity.6
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public void onComplete(Task<String> task) {
                    if (task.isSuccessful()) {
                        MainActivity.this.updateDevice(task.getResult());
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDevice(String str) {
        APIService aPIService = this.service;
        String str2 = API.BASE_URL;
        String str3 = App.getUserData().uid_v2;
        String oSType = SystemUtils.getOSType();
        String str4 = Build.MODEL;
        String str5 = App.deviceToken;
        String uniqueId = SystemUtils.getUniqueId(App.getContext());
        ((ObservableSubscribeProxy) aPIService.Device(str2, API.USER.DEVICE, str, str3, oSType, str4, str5, "", uniqueId, BuildConfig.APPLICATION_ID, (TimeUtils.getCurrentTime() / 1000) + "", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, BuildConfig.VERSION_NAME).subscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new io.reactivex.Observer<String>() { // from class: com.movieboxpro.android.view.activity.MainActivity.7
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onNext(String str6) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                RxManager.addDisposable(MainActivity.this.TAG, disposable);
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("selectTabPosition", this.mTab.getSelectedTabPosition());
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initData() {
        Intent intent = getIntent();
        if (intent.getData() != null || intent.getExtras() != null) {
            handleParam(intent);
        }
        if (Network.isConnected(this.context)) {
            for (Download download : App.getDB().downloadDao().findByType(0)) {
                download.setPrivateid(download.getMovieId());
                download.setBox_type(1);
                App.getDB().downloadDao().update(download);
            }
            statService();
            if (!App.isLogin()) {
                CheckNeedInvate();
            }
        }
        this.titles = r3;
        String[] strArr = {TAG_HOME, TAG_MOVIE_LIST, TAG_FAVORITE, TAG_SEARCH, TAG_DOWNLOAD};
        this.icons = r0;
        int[] iArr = {R.drawable.nav_movie, R.drawable.nav_movie_list, R.drawable.nav_favorite, R.drawable.nav_search, R.drawable.nav_download};
        if (this.savedInstanceState == null) {
            HomeListFragment homeListFragment = new HomeListFragment();
            DownloadFragment downloadFragment = new DownloadFragment();
            CollectFragment2 collectFragment2 = new CollectFragment2();
            SearchFragment searchFragment = new SearchFragment();
            MovieListFragment newInstance = MovieListFragment.newInstance();
            ArrayList arrayList = new ArrayList(this.titles.length);
            this.fragments = arrayList;
            arrayList.add(homeListFragment);
            this.fragments.add(newInstance);
            this.fragments.add(collectFragment2);
            this.fragments.add(searchFragment);
            this.fragments.add(downloadFragment);
            FragmentTransaction beginTransaction = this.fragmentManager.beginTransaction();
            int i = 0;
            for (Fragment fragment : this.fragments) {
                if (fragment instanceof BaseFragment) {
                    BaseFragment baseFragment = (BaseFragment) fragment;
                    baseFragment.setAddIn(true);
                    baseFragment.setName(this.titles[i]);
                    baseFragment.setListener(this);
                }
                beginTransaction.add(R.id.content, fragment, fragment.getClass().getSimpleName()).hide(fragment);
                i++;
            }
            this.current = 0;
            beginTransaction.show(this.fragments.get(0));
            beginTransaction.commit();
            setTitle(this.titles[this.current]);
        } else {
            ArrayList arrayList2 = new ArrayList(this.titles.length);
            this.fragments = arrayList2;
            arrayList2.add((HomeListFragment) getSupportFragmentManager().findFragmentByTag(HomeListFragment.class.getSimpleName()));
            this.fragments.add((MovieListFragment) getSupportFragmentManager().findFragmentByTag(MovieListFragment.class.getSimpleName()));
            this.fragments.add((CollectFragment2) getSupportFragmentManager().findFragmentByTag(CollectFragment2.class.getSimpleName()));
            this.fragments.add((BaseFragment) getSupportFragmentManager().findFragmentByTag(SearchFragment.class.getSimpleName()));
            this.fragments.add((BaseFragment) getSupportFragmentManager().findFragmentByTag(DownloadFragment.class.getSimpleName()));
        }
        initTabs();
        if (App.isLogin() && App.getUserData().getHide_tv_movielist() == 0) {
            boolean z = PrefsUtils.getInstance().getBoolean(Constant.Prefs.HIDE_MOVIELIST);
            if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.CHILD_MODE, false) || z) {
                removeMovieListFragment();
            }
        } else {
            removeMovieListFragment();
        }
        setDownloadTaskNum();
        if (App.isLogin() && App.getUserData().isvip == 1) {
            getAutoPaymentData(App.getUserData().uid_v2);
        }
        checkTime();
        startLowMemoryService();
        dealShortCutsActions();
        HideMovieListLiveData.Companion.get().observeInActivity(this, new androidx.lifecycle.Observer() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$MainActivity$Ux18lOxt26bnH3PXo0FGhCfuJfA
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MainActivity.this.lambda$initData$3$MainActivity((Boolean) obj);
            }
        });
        SwitchWaitingLiveData.Companion.get().observeInActivity(this, new androidx.lifecycle.Observer() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$MainActivity$0JszlCuH-50MdRV6XVweaYh8nJo
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MainActivity.this.lambda$initData$4$MainActivity((Boolean) obj);
            }
        });
        SwitchRecommendLiveData.Companion.get().observeInActivity(this, new androidx.lifecycle.Observer() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$MainActivity$mP8rxbmCwFckTv3Qm4Vgg6J21-k
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MainActivity.this.lambda$initData$5$MainActivity((Boolean) obj);
            }
        });
    }

    public /* synthetic */ void lambda$initData$3$MainActivity(Boolean bool) {
        if (bool.booleanValue()) {
            removeMovieListFragment();
        } else {
            addMovieListFragment();
        }
    }

    public /* synthetic */ void lambda$initData$4$MainActivity(Boolean bool) {
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i2 >= this.fragments.size()) {
                break;
            } else if (this.fragments.get(i2) instanceof CollectFragment2) {
                i = i2;
                break;
            } else {
                i2++;
            }
        }
        this.mTab.getTabAt(i).select();
    }

    public /* synthetic */ void lambda$initData$5$MainActivity(Boolean bool) {
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i2 >= this.fragments.size()) {
                break;
            } else if (this.fragments.get(i2) instanceof CollectFragment2) {
                i = i2;
                break;
            } else {
                i2++;
            }
        }
        this.mTab.getTabAt(i).select();
    }

    public void copyfile(String str, String str2, String str3) {
        try {
            if (new File(str + "/" + str2).exists()) {
                return;
            }
            InputStream open = getResources().getAssets().open(str3);
            FileOutputStream fileOutputStream = new FileOutputStream(str + "/" + str2);
            byte[] bArr = new byte[7168];
            while (true) {
                int read = open.read(bArr);
                if (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.close();
                    open.close();
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dealShortCutsActions() {
        String action = getIntent().getAction();
        if (action != null) {
            char c = 65535;
            int hashCode = action.hashCode();
            int i = 0;
            if (hashCode != -648965692) {
                if (hashCode != 1255020496) {
                    if (hashCode == 2028008655 && action.equals("com.movieboxpro.android.continue")) {
                        c = 0;
                    }
                } else if (action.equals("com.movieboxpro.android.search")) {
                    c = 2;
                }
            } else if (action.equals("com.movieboxpro.android.favorite")) {
                c = 1;
            }
            if (c == 0) {
                continuePlaying = true;
            } else if (c == 1) {
                while (i < this.fragments.size()) {
                    if (this.fragments.get(i) instanceof CollectFragment) {
                        TabLayout.Tab tabAt = this.mTab.getTabAt(i);
                        if (tabAt != null) {
                            this.mTab.selectTab(tabAt);
                            return;
                        }
                        return;
                    }
                    i++;
                }
            } else if (c != 2) {
            } else {
                while (i < this.fragments.size()) {
                    if (this.fragments.get(i) instanceof SearchFragment) {
                        TabLayout.Tab tabAt2 = this.mTab.getTabAt(i);
                        if (tabAt2 != null) {
                            this.mTab.selectTab(tabAt2);
                            return;
                        }
                        return;
                    }
                    i++;
                }
            }
        }
    }

    private void startLowMemoryService() {
        if (Utils.isAppForeground()) {
            startService(new Intent(this, MemoryMonitorService.class));
        }
    }

    private void checkTime() {
        ((ObservableSubscribeProxy) io.reactivex.Observable.just("http://time.movieboxpro.app/time.php").map($$Lambda$MainActivity$OXICb3ZOQ7QJnopqDfuUJLqac.INSTANCE).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<Boolean>() { // from class: com.movieboxpro.android.view.activity.MainActivity.8
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException apiException) {
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(Boolean bool) {
                if (bool.booleanValue()) {
                    return;
                }
                new TimeCheckDialog(MainActivity.this).show();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Boolean lambda$checkTime$6(String str) throws Exception {
        Response execute = Http.getOkHttpClient().newCall(new Request.Builder().url(str).build()).execute();
        boolean z = true;
        if (execute.isSuccessful()) {
            long parseLong = Long.parseLong(execute.body().string());
            long currentTime = TimeUtils.getCurrentTime() / 1000;
            return Boolean.valueOf((currentTime >= parseLong + 600 || currentTime <= parseLong - 600) ? false : false);
        }
        return true;
    }

    private void getAutoPaymentData(String str) {
        ((ObservableSubscribeProxy) this.service.User_agreement(API.BASE_URL, "User_agreement", str).compose(RxUtils.rxTranslate2Bean(UserAgreementModel.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<UserAgreementModel>() { // from class: com.movieboxpro.android.view.activity.MainActivity.9
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(UserAgreementModel userAgreementModel) {
                if ("Active".equals(userAgreementModel.getStatus()) || (App.getUserData().dead_time * 1000) - System.currentTimeMillis() > 604800000) {
                    return;
                }
                MainActivity.this.showHintVipDate();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException apiException) {
                if ((App.getUserData().dead_time * 1000) - System.currentTimeMillis() <= 604800000) {
                    MainActivity.this.showHintVipDate();
                }
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFeaturedLoadComplete(OnFeaturedFirstLoadEvent onFeaturedFirstLoadEvent) {
        if (App.getUserData().getIsvip() != 1 && !App.isLogin()) {
        }
    }

    private void deleteExtraFile() {
        ((ObservableSubscribeProxy) io.reactivex.Observable.create(new ObservableOnSubscribe() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$MainActivity$uy8DLTQQa-iMNXiemdMCHpOsxKg
            @Override // io.reactivex.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                MainActivity.this.lambda$deleteExtraFile$8$MainActivity(observableEmitter);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new io.reactivex.Observer<String>() { // from class: com.movieboxpro.android.view.activity.MainActivity.10
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onNext(String str) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }
        });
    }

    public /* synthetic */ void lambda$deleteExtraFile$8$MainActivity(ObservableEmitter observableEmitter) throws Exception {
        List<Download> all = App.getDB().downloadDao().getAll();
        List<File> listFilesInDirWithFilter = FileUtils.listFilesInDirWithFilter(new File(Constant.DIR_DOWNLOAD), $$Lambda$MainActivity$ciyU20oH4zgh2OJpEkDzXCgoyI.INSTANCE, false);
        if (listFilesInDirWithFilter != null) {
            for (File file : listFilesInDirWithFilter) {
                if (!findExist(file.getName(), all)) {
                    file.delete();
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x000a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean findExist(java.lang.String r4, java.util.List<com.movieboxpro.android.db.entity.Download> r5) {
        /*
            r3 = this;
            java.util.Iterator r5 = r5.iterator()
        L4:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L30
            java.lang.Object r0 = r5.next()
            com.movieboxpro.android.db.entity.Download r0 = (com.movieboxpro.android.db.entity.Download) r0
            java.lang.String r1 = r0.getFileName()
            boolean r1 = r4.equals(r1)
            if (r1 != 0) goto L2e
            java.lang.String r1 = r0.getPath()
            java.lang.String r0 = r0.getTitle()
            java.lang.String r2 = com.movieboxpro.android.app.Constant.DIR_DOWNLOAD
            java.lang.String r0 = com.ares.downloader.jarvis.core.RemoteFileUtil.getFileName(r1, r0, r2)
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L4
        L2e:
            r4 = 1
            return r4
        L30:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.view.activity.MainActivity.findExist(java.lang.String, java.util.List):boolean");
    }

    private void initTabs() {
        TabLayout.Tab tabAt;
        int i;
        TabLayout.Tab tabAt2;
        if (this.titles.length == 1) {
            this.mTab.setVisibility(8);
            return;
        }
        for (int i2 = 0; i2 < this.titles.length; i2++) {
            TabLayout.Tab newTab = this.mTab.newTab();
            View inflate = View.inflate(this.context, R.layout.layout_tab_image, null);
            if (inflate != null) {
                ImageView imageView = (ImageView) inflate.findViewById(R.id.tab_image);
                if (imageView != null) {
                    imageView.setImageResource(this.icons[i2]);
                }
                newTab.setCustomView(inflate);
            }
            newTab.setTag(this.titles[i2]);
            this.mTab.addTab(newTab, i2);
        }
        if (App.isLogin() && App.getUserData().getHide_tv_movielist() == 0) {
            if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.CHILD_MODE, false)) {
                removeNeedHideTag();
            }
        } else {
            removeNeedHideTag();
        }
        this.mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.movieboxpro.android.view.activity.MainActivity.11
            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab) {
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position != MainActivity.this.current) {
                    Fragment findFragmentByTag = MainActivity.this.findFragmentByTag((String) tab.getTag());
                    Fragment fragment = null;
                    if (MainActivity.this.mTab.getTabAt(MainActivity.this.current) != null) {
                        MainActivity mainActivity = MainActivity.this;
                        fragment = mainActivity.findFragmentByTag((String) mainActivity.mTab.getTabAt(MainActivity.this.current).getTag());
                    }
                    if (MainActivity.this.fragmentManager == null) {
                        MainActivity mainActivity2 = MainActivity.this;
                        mainActivity2.fragmentManager = mainActivity2.getSupportFragmentManager();
                    }
                    if (findFragmentByTag != null && fragment != null) {
                        MainActivity.this.fragmentManager.beginTransaction().hide(fragment).show(findFragmentByTag).commitAllowingStateLoss();
                    } else if (findFragmentByTag != null) {
                        MainActivity.this.fragmentManager.beginTransaction().show(findFragmentByTag).commitAllowingStateLoss();
                    }
                    if (findFragmentByTag instanceof DownloadFragment) {
                        MainActivity.this.mTab.setKeepScreenOn(true);
                    } else {
                        MainActivity.this.mTab.setKeepScreenOn(false);
                    }
                    MainActivity.this.current = position;
                }
            }
        });
        if (this.savedInstanceState != null && (tabAt2 = this.mTab.getTabAt((i = this.savedInstanceState.getInt("selectTabPosition")))) != null) {
            tabAt2.select();
            this.current = i;
        }
        if (!getIntent().getBooleanExtra("checkDownloaded", false) || (tabAt = this.mTab.getTabAt(4)) == null) {
            return;
        }
        tabAt.select();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Fragment findFragmentByTag(String str) {
        Fragment next;
        if (TAG_HOME.equals(str)) {
            Iterator<Fragment> it = this.fragments.iterator();
            while (it.hasNext()) {
                next = it.next();
                if (next instanceof HomeListFragment) {
                }
            }
            return null;
        } else if (TAG_MOVIE_LIST.equals(str)) {
            Iterator<Fragment> it2 = this.fragments.iterator();
            while (it2.hasNext()) {
                next = it2.next();
                if (next instanceof MovieListFragment) {
                }
            }
            return null;
        } else if (TAG_FAVORITE.equals(str)) {
            Iterator<Fragment> it3 = this.fragments.iterator();
            while (it3.hasNext()) {
                next = it3.next();
                if (next instanceof CollectFragment2) {
                }
            }
            return null;
        } else if (TAG_SEARCH.equals(str)) {
            Iterator<Fragment> it4 = this.fragments.iterator();
            while (it4.hasNext()) {
                next = it4.next();
                if (next instanceof SearchFragment) {
                }
            }
            return null;
        } else if (TAG_DOWNLOAD.equals(str)) {
            Iterator<Fragment> it5 = this.fragments.iterator();
            while (it5.hasNext()) {
                next = it5.next();
                if (next instanceof DownloadFragment) {
                }
            }
            return null;
        } else {
            return null;
        }
        return next;
    }

    private void removeNeedHideTag() {
        int tabCount = this.mTab.getTabCount();
        for (int i = 0; i < tabCount; i++) {
            TabLayout.Tab tabAt = this.mTab.getTabAt(i);
            if (tabAt != null && TAG_MOVIE_LIST.equals(tabAt.getTag())) {
                this.mTab.removeTabAt(i);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public TabLayout.Tab findTabByTag(String str) {
        int tabCount = this.mTab.getTabCount();
        for (int i = 0; i < tabCount; i++) {
            TabLayout.Tab tabAt = this.mTab.getTabAt(i);
            if (tabAt != null && str.equals(tabAt.getTag())) {
                return tabAt;
            }
        }
        return null;
    }

    @Override // com.movieboxpro.android.base.BaseActivity, com.movieboxpro.android.view.listener.IViewController
    public <T> void updateView(T t) {
        super.updateView(t);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onConfigReceive(ConfigReceiveEvent configReceiveEvent) {
        if (!this.upgradeChecked) {
            if (ConfigUtils.readIntegerConfig(ConfigKey.APK_VERSION_CODE_MIN, 0) > 169) {
                showUpdateAlrt();
            } else {
                UpdateChecker.INSTANCE.checkUpdate(this);
                this.upgradeChecked = true;
            }
        }
        try {
            if (this.systemDialog == null) {
                String readStringConfig = ConfigUtils.readStringConfig(ConfigKey.SERVICE_INFO);
                if (TextUtils.isEmpty(readStringConfig)) {
                    return;
                }
                ServiceInfo serviceInfo = (ServiceInfo) JSONObject.parseObject(readStringConfig, ServiceInfo.class);
                if (serviceInfo.getStatus() == 1) {
                    UpdateSheetDialog create = new UpdateSheetDialog.Builder(this).setTitle(serviceInfo.getTitle()).setContent(URLDecoder.decode(serviceInfo.getInfo())).setPositiveText("OK").setNegativeGone().create();
                    this.systemDialog = create;
                    create.show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (App.isLogin()) {
            updateView(App.getUserData());
            if (Network.isConnected(this.context)) {
                loadData();
            }
        } else {
            updateView(null);
        }
        if (Network.isConnected(this.context)) {
            this.app.updateOnline();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        TabLayout tabLayout;
        TabLayout.Tab tabAt;
        super.onNewIntent(intent);
        handleParam(intent);
        if (!intent.getBooleanExtra("checkDownloaded", false) || (tabLayout = this.mTab) == null || (tabAt = tabLayout.getTabAt(4)) == null) {
            return;
        }
        tabAt.select();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:109:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:121:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x015f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void handleParam(android.content.Intent r19) {
        /*
            Method dump skipped, instructions count: 504
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.view.activity.MainActivity.handleParam(android.content.Intent):void");
    }

    private void loadData() {
        ((ObservableSubscribeProxy) Http.getService().getUserInfo(API.BASE_URL, API.USER.USERINFO, TextUtils.isEmpty(App.getUserData().uid_v2) ? "" : App.getUserData().uid_v2, SystemUtils.getUniqueId(App.getContext()), BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslate2Bean(UserModel.UserData.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<UserModel.UserData>() { // from class: com.movieboxpro.android.view.activity.MainActivity.13
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(UserModel.UserData userData) {
                String str = MainActivity.this.TAG;
                MLog.d(str, "登录状态 ： " + userData.name);
                App.updateUser(userData);
                MainActivity.this.updateView(userData);
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException apiException) {
                if (apiException.getCode() == 0) {
                    if ("no uid".equals(apiException.getMessage())) {
                        ToastUtils.showShort("You need to sign in again");
                    }
                    App.logout();
                    MainActivity.this.route(Login2Activity.class);
                } else if (apiException.getCode() == -10001) {
                    ToastUtils.showShort("You need to sign in again:" + apiException.getCode());
                    App.logout();
                    MainActivity.this.route(Login2Activity.class);
                }
            }
        });
        if (App.isLogin()) {
            ((ObservableSubscribeProxy) HttpRequest.post("User_watching_update_count_v2").asRequest().compose(RxUtils.rxTranslate2Bean(HashMap.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<HashMap>() { // from class: com.movieboxpro.android.view.activity.MainActivity.14
                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onError(ApiException apiException) {
                }

                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onStart(Disposable disposable) {
                }

                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onSuccess(HashMap hashMap) {
                    View customView;
                    int parseInt = Integer.parseInt(hashMap.get("new_update").toString());
                    TabLayout.Tab findTabByTag = MainActivity.this.findTabByTag(MainActivity.TAG_FAVORITE);
                    if (findTabByTag == null || (customView = findTabByTag.getCustomView()) == null) {
                        return;
                    }
                    View findViewById = customView.findViewById(R.id.viewDot);
                    if (parseInt == 0) {
                        findViewById.setVisibility(8);
                    } else {
                        findViewById.setVisibility(0);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showHintVipDate() {
        new VipExpireDialog.Builder(this).setCancelable(false).setTitle("Your VIP will soon expire.").setContent(String.format("Expire: %s", TimeUtils.formatTime3(App.getUserData().dead_time * 1000))).setNegativeText("Next Time").setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$MainActivity$BnULe_uB-NkR6SPuw-zYGWWqZfU
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public final void onClick() {
                MainActivity.this.lambda$showHintVipDate$9$MainActivity();
            }
        }).create().show();
    }

    public /* synthetic */ void lambda$showHintVipDate$9$MainActivity() {
        route(VipActivity.class);
    }

    public void CheckNeedInvate() {
        ((ObservableSubscribeProxy) this.service.Register_NeedCode(API.BASE_URL, API.USER.REGISTER_CODE).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new io.reactivex.Observer<String>() { // from class: com.movieboxpro.android.view.activity.MainActivity.15
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                RxManager.addDisposable(MainActivity.this.TAG, disposable);
            }

            @Override // io.reactivex.Observer
            public void onNext(String str) {
                JSONObject jSONObject = (JSONObject) JSONObject.parse(str);
                String str2 = MainActivity.this.TAG;
                MLog.d(str2, "邀请码测试 : " + str);
                if (jSONObject.getInteger("code").intValue() == 1 && jSONObject.getJSONObject("data").getBoolean("invite_code").booleanValue()) {
                    MainActivity.this.route(Login2Activity.class);
                }
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.drawer.isDrawerOpen(GravityCompat.END)) {
            this.drawer.closeDrawer(GravityCompat.END);
            return;
        }
        boolean z = false;
        Iterator<Fragment> it = this.fragments.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Fragment next = it.next();
            if (next instanceof BaseFragment) {
                BaseFragment baseFragment = (BaseFragment) next;
                if (baseFragment.isVisible()) {
                    z = baseFragment.onBackPressed();
                    break;
                }
            }
        }
        if (z) {
            return;
        }
        ClickCounter clickCounter = this.counter;
        if (clickCounter != null) {
            clickCounter.countClick();
        } else {
            super.onBackPressed();
        }
    }

    private String buildData(String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("env", (Object) str);
        jSONObject.put("uid", (Object) (App.isLogin() ? App.getUserData().uid_v2 : ""));
        jSONObject.put("expired_date", (Object) Long.valueOf((TimeUtils.getCurrentTime() / 1000) + 43200));
        return HttpUtils.encodeBody(jSONObject.toJSONString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 49374 && i2 == -1) {
            IntentResult parseActivityResult = IntentIntegrator.parseActivityResult(i, i2, intent);
            if (parseActivityResult != null) {
                EventBus.getDefault().post(new OnScanResultEvent(parseActivityResult.getContents()));
            } else {
                ToastUtils.showShort("Scan Failed");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        if (this.app != null) {
            this.app.delObserver(this);
        }
        if (this.receiver != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(this.receiver);
        }
        RxManager.remove(this.TAG);
        CallManager.cancelAll(getClass().getSimpleName());
        clearReviewRecord();
        stopMemoryService();
        super.onDestroy();
    }

    private void stopMemoryService() {
        stopService(new Intent(this, MemoryMonitorService.class));
    }

    private void clearReviewRecord() {
        if (Constant.DIR_THUMB != null) {
            FileUtils.deleteAllInDir(new File(Constant.DIR_THUMB));
            ReviewRecordUtils.Companion.get().clearReviewRecord();
        }
    }

    @Override // com.movieboxpro.android.view.listener.ITabListener
    public void TabSlect(boolean z) {
        if (z) {
            this.mTab.setVisibility(0);
        } else {
            this.mTab.setVisibility(8);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogin(LoginEvent loginEvent) {
        addMovieListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.METHOD, "google");
        FirebaseAnalytics.getInstance(this).logEvent(FirebaseAnalytics.Event.LOGIN, bundle);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuperChildMode(SuperChildModeChangedEvent superChildModeChangedEvent) {
        if (superChildModeChangedEvent.isOpen()) {
            ActivityUtils.finishAllActivities();
            Intent intent = new Intent(this, SuperChildModeMainActivity.class);
            intent.addFlags(268435456);
            startActivity(intent);
        }
    }

    private void addMovieListFragment() {
        if (App.isLogin() && App.getUserData().getHide_tv_movielist() == 0) {
            TabLayout.Tab newTab = this.mTab.newTab();
            View inflate = View.inflate(this.context, R.layout.layout_tab_image, null);
            if (inflate != null) {
                ImageView imageView = (ImageView) inflate.findViewById(R.id.tab_image);
                if (imageView != null) {
                    imageView.setImageResource(this.icons[1]);
                }
                newTab.setCustomView(inflate);
            }
            newTab.setTag(TAG_MOVIE_LIST);
            this.mTab.addTab(newTab, 1);
            MovieListFragment newInstance = MovieListFragment.newInstance();
            this.fragments.add(1, newInstance);
            FragmentTransaction beginTransaction = this.fragmentManager.beginTransaction();
            beginTransaction.add(R.id.content, newInstance, newInstance.getClass().getSimpleName()).hide(newInstance);
            beginTransaction.commitAllowingStateLoss();
            int i = this.current;
            if (i > 0) {
                this.current = i + 1;
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogout(LogoutEvent logoutEvent) {
        PrefsUtils.getInstance().putBoolean(Constant.Prefs.CHILD_MODE, false);
        removeMovieListFragment();
        CheckNeedInvate();
    }

    private void removeMovieListFragment() {
        if (this.mTab.getTabCount() == 5) {
            this.mTab.removeTabAt(1);
            this.fragments.remove(1);
            int i = this.current;
            if (i > 1) {
                this.current = i - 1;
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onChildModeChanged(ChildModeChangedEvent childModeChangedEvent) {
        if (App.isLogin() && App.getUserData().getHide_tv_movielist() == 0) {
            if (childModeChangedEvent.isOpen()) {
                this.mTab.removeTabAt(1);
                this.fragments.remove(1);
                int i = this.current;
                if (i > 1) {
                    this.current = i - 1;
                    return;
                }
                return;
            }
            TabLayout.Tab newTab = this.mTab.newTab();
            View inflate = View.inflate(this.context, R.layout.layout_tab_image, null);
            if (inflate != null) {
                ImageView imageView = (ImageView) inflate.findViewById(R.id.tab_image);
                if (imageView != null) {
                    imageView.setImageResource(this.icons[1]);
                }
                newTab.setCustomView(inflate);
            }
            newTab.setTag(TAG_MOVIE_LIST);
            this.mTab.addTab(newTab, 1);
            MovieListFragment newInstance = MovieListFragment.newInstance();
            this.fragments.add(1, newInstance);
            FragmentTransaction beginTransaction = this.fragmentManager.beginTransaction();
            beginTransaction.add(R.id.content, newInstance, newInstance.getClass().getSimpleName()).hide(newInstance);
            beginTransaction.commitAllowingStateLoss();
            int i2 = this.current;
            if (i2 > 0) {
                this.current = i2 + 1;
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAvatarClickEvent(AvatarClickEvent avatarClickEvent) {
        if (this.drawer.isDrawerOpen(GravityCompat.END)) {
            return;
        }
        this.drawer.openDrawer(GravityCompat.END);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDownloadChanged(DownloadChangedEvent downloadChangedEvent) {
        setDownloadTaskNum();
    }

    private void setDownloadTaskNum() {
        View customView;
        Iterator<Download> it = App.getDB().downloadDao().getAll().iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            int statue = it.next().getStatue();
            if (statue == 1 || statue == 3 || statue == 0 || statue == 4) {
                i++;
            }
        }
        TabLayout.Tab tabAt = this.mTab.getTabAt(PrefsUtils.getInstance().getBoolean(Constant.Prefs.CHILD_MODE, false) ? 3 : 4);
        if (tabAt == null || (customView = tabAt.getCustomView()) == null) {
            return;
        }
        TextView textView = (TextView) customView.findViewById(R.id.tab_count);
        if (i > 0) {
            textView.setVisibility(0);
            if (i > 99) {
                textView.setText("99+");
                return;
            } else {
                textView.setText(String.valueOf(i));
                return;
            }
        }
        textView.setVisibility(8);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginSuccess(LoginEvent loginEvent) {
        getFireBaseToken();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSignOut(LogoutEvent logoutEvent) {
        View customView;
        TabLayout.Tab tabAt = this.mTab.getTabAt(2);
        if (tabAt == null || (customView = tabAt.getCustomView()) == null) {
            return;
        }
        TextView textView = (TextView) customView.findViewById(R.id.tab_count);
        textView.setVisibility(8);
        textView.setText("");
    }
}
