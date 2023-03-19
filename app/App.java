package com.movieboxpro.android.app;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.LocaleList;
import android.provider.Settings;
import android.text.TextUtils;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;
import androidx.room.Room;
import com.alibaba.fastjson.JSONObject;
import com.ares.downloader.jarvis.Jarvis;
import com.ares.downloader.jarvis.db.DefaultDownloadHistoryDBHelper;
import com.billy.android.swipe.SmartSwipeBack;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.module.LoadMoreModuleConfig;
import com.flurry.android.FlurryAgent;
import com.flurry.android.FlurryPerformance;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.kingja.loadsir.core.LoadSir;
import com.koushikdutta.async.http.cache.ResponseCacheMiddleware;
import com.movieboxpro.android.BuildConfig;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.cache.ACache;
import com.movieboxpro.android.config.ConfigKey;
import com.movieboxpro.android.config.ConfigUtils;
import com.movieboxpro.android.config.OnlineConfigAgent;
import com.movieboxpro.android.db.Migrations;
import com.movieboxpro.android.db.ReaderDB;
import com.movieboxpro.android.event.ConfigReceiveEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.CallManager;
import com.movieboxpro.android.http.CipherKeys;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.DownloadInfo;
import com.movieboxpro.android.model.user.OldUserModel;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.CipherUtils;
import com.movieboxpro.android.utils.FileUtil;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.HexDump;
import com.movieboxpro.android.utils.MD5Util;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.Utils;
import com.movieboxpro.android.view.activity.MainActivity;
import com.movieboxpro.android.view.activity.SplashActivity;
import com.movieboxpro.android.view.activity.detail.impl.TrailerPlayerActivity;
import com.movieboxpro.android.view.activity.user.Login2Activity;
import com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory;
import com.movieboxpro.android.view.widget.CustomLoadMoreView;
import com.movieboxpro.android.view.widget.loadcallback.EmptyCallbackView;
import com.movieboxpro.android.view.widget.loadcallback.ErrorCallbackView;
import com.movieboxpro.android.view.widget.loadcallback.LoadingCallbackView;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import org.greenrobot.eventbus.EventBus;
import org.videolan.libvlc.FactoryManager;
import org.videolan.libvlc.LibVLCFactory;
import org.videolan.libvlc.interfaces.ILibVLCFactory;
/* loaded from: classes.dex */
public class App extends MultiDexApplication implements Application.ActivityLifecycleCallbacks, OnlineConfigAgent.OnlineConfigListener, ComponentCallbacks2 {
    public static int DEBUG_LEVEL = 0;
    private static final String TAG = "App";
    private static int activityCount = 0;
    public static String channel = null;
    private static ReaderDB db = null;
    public static String deviceLang = "";
    public static String deviceToken = "";
    private static boolean hasExitCall = false;
    private static App instance = null;
    public static boolean isDebug = false;
    private static boolean isLogin = false;
    private static ACache mCache = null;
    public static String netGroupId = "";
    private static AppObservable observable = null;
    public static String osType = "android";
    public static String packageName = null;
    private static UserModel user = null;
    public static int versionCode = 1;
    public static String versionName;

    private void configSpash() {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        try {
            ProviderInstaller.installIfNeeded(this);
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesRepairableException e2) {
            e2.printStackTrace();
        }
        configLog();
        MLog.d(TAG, "onCreate");
        registerActivityLifecycleCallbacks(this);
        instance = this;
        mCache = ACache.get(this);
        osType = SystemUtils.getOSType();
        deviceToken = SystemUtils.getUniqueId(this);
        String processName = SystemUtils.getProcessName(this);
        packageName = getPackageName();
        MLog.d(TAG, "package:" + packageName + ",process:" + processName);
        if (packageName.equals(processName) || "".equals(processName)) {
            RxJavaPlugins.setErrorHandler($$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
            getVersions();
            mkDirs();
            initPrefs();
            autoLogin();
            initDB();
            configJarvis();
            configTokne();
            initSwipeBack();
            initLoadSir();
            Utils.init((Application) this);
            initLogger();
            initBaseAdapter();
            FactoryManager.registerFactory(ILibVLCFactory.factoryId, new LibVLCFactory());
            initFlurry();
            if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.BLACK_MODE, false)) {
                AppCompatDelegate.setDefaultNightMode(2);
            } else {
                AppCompatDelegate.setDefaultNightMode(1);
            }
        } else if (Build.VERSION.SDK_INT >= 28) {
            WebView.setDataDirectorySuffix(processName);
        }
    }

    private void initFlurry() {
        new FlurryAgent.Builder().withDataSaleOptOut(false).withCaptureUncaughtExceptions(true).withIncludeBackgroundSessionsInMetrics(true).withLogLevel(2).withPerformanceMetrics(FlurryPerformance.ALL).withReportLocation(true).build(this, Constant.FLURRY_API_KEY);
    }

    private void initBaseAdapter() {
        LoadMoreModuleConfig.setDefLoadMoreView(new CustomLoadMoreView());
    }

    private void initLogger() {
        Logger.addLogAdapter(new AndroidLogAdapter() { // from class: com.movieboxpro.android.app.App.1
            @Override // com.orhanobut.logger.AndroidLogAdapter, com.orhanobut.logger.LogAdapter
            public boolean isLoggable(int i, String str) {
                return false;
            }
        });
    }

    private void initSwipeBack() {
        SmartSwipeBack.activitySlidingBack(this, $$Lambda$App$TLVrb0jOZnQNCUu2xQC_tuftVuk.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$initSwipeBack$0(Activity activity) {
        return ((activity instanceof MainActivity) || (activity instanceof VideoActivityFactory) || (activity instanceof SplashActivity) || (activity instanceof Login2Activity) || (activity instanceof TrailerPlayerActivity)) ? false : true;
    }

    private void initLanguage() {
        Locale locale;
        if (Build.VERSION.SDK_INT >= 24) {
            locale = LocaleList.getDefault().get(0);
        } else {
            locale = Locale.getDefault();
        }
        if (locale != null) {
            String language = locale.getLanguage();
            String country = locale.getCountry();
            if ("zh".equalsIgnoreCase(language)) {
                if ("CN".equalsIgnoreCase(country)) {
                    deviceLang = "zh-CN";
                    return;
                } else {
                    deviceLang = "zh-TW";
                    return;
                }
            }
            deviceLang = language;
            return;
        }
        deviceLang = "en";
    }

    private void initLoadSir() {
        LoadSir.beginBuilder().addCallback(new ErrorCallbackView()).addCallback(new EmptyCallbackView()).addCallback(new LoadingCallbackView()).setDefaultCallback(LoadingCallbackView.class).commit();
    }

    private void configLog() {
        MLog.config(isDebug);
    }

    private void getVersions() {
        channel = BuildConfig.CHANNEL;
        versionName = SystemUtils.getVersionName(this);
        versionCode = SystemUtils.getVersionCode(this);
    }

    private void mkDirs() {
        File externalFilesDir = getExternalFilesDir("");
        if (externalFilesDir != null) {
            Constant.DIR = externalFilesDir.getParent();
            Constant.DIR_CACHE = Constant.DIR + File.separator + ResponseCacheMiddleware.CACHE;
            Constant.DIR_NET_DATA_CACHE = Constant.DIR + File.separator + "netcache";
            Constant.DIR_LOG = Constant.DIR + File.separator + "log";
            Constant.DIR_CONFIG = Constant.DIR + File.separator + "config";
            if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.INTERNAL_STORAGE, true)) {
                Constant.DIR_DOWNLOAD = Constant.DIR + File.separator + DownloadInfo.DOWNLOAD;
            } else {
                Constant.DIR_DOWNLOAD = PrefsUtils.getInstance().getString(Constant.Prefs.DOWNLOAD_DIR);
            }
            Constant.DIR_DOWNLOAD_TEST_SPEED = Constant.DIR + File.separator + "testspeed";
            Constant.DIR_PICTURE = Constant.DIR + File.separator + "picture";
            Constant.DIR_CROP = Constant.DIR + File.separator + "crop";
            Constant.DIR_THUMB = Constant.DIR + File.separator + "thumb";
            Constant.DIR_VIDEO = Constant.DIR + File.separator + "video";
            Constant.DIR_TRANS_CODE_SUBTITLE = Constant.DIR + File.separator + "TransSubtitle";
            Constant.DIR_TXT = Constant.DIR + File.separator + "txt";
            Constant.DIR_FONT = Constant.DIR + File.separator + "font";
            Constant.DIR_NOVEL = Constant.DIR_CACHE + File.separator + HexDump.toHexString(MD5Util.md5(packageName));
            Constant.DIR_SUBTITLE = Constant.DIR + File.separator + "Subtitle";
            Constant.DIR_UPLOAD_SUBTITLE = Constant.DIR_SUBTITLE + File.separator + "uploadSubtitles";
            Constant.DIR_DOLOAD_SUBTITLE = Constant.DIR_SUBTITLE + File.separator + "downloadSubtitles";
            Constant.DIR_UPLOAD_MOVIE_SUBTITLE = Constant.DIR_UPLOAD_SUBTITLE + File.separator + "movie";
            Constant.DIR_UPLOAD_TV_SUBTITLE = Constant.DIR_UPLOAD_SUBTITLE + File.separator + "tv";
            Constant.DIR_DOWNLOAD_MOVIE_SUBTITLE = Constant.DIR_DOLOAD_SUBTITLE + File.separator + "movie";
            Constant.DIR_DOWNLOAD_TV_SUBTITLE = Constant.DIR_DOLOAD_SUBTITLE + File.separator + "tv";
            Constant.DIR_WEB_DOWNLOAD_SUBTITLE = Constant.DIR_DOWNLOAD + File.separator + "webDownload";
        }
        if (Environment.getExternalStorageState().equals("mounted")) {
            Constant.DIRS = new String[]{Constant.DIR, Constant.DIR_CACHE, Constant.DIR_LOG, Constant.DIR_CONFIG, Constant.DIR_DOWNLOAD, Constant.DIR_PICTURE, Constant.DIR_CROP, Constant.DIR_THUMB, Constant.DIR_VIDEO, Constant.DIR_TXT, Constant.DIR_NOVEL, Constant.DIR_FONT};
            Constant.DIRS_CLEAR = new String[]{Constant.DIR_CACHE, Constant.DIR_LOG, Constant.DIR_CONFIG, Constant.DIR_PICTURE, Constant.DIR_CROP, Constant.DIR_THUMB, Constant.DIR_VIDEO, Constant.DIR_TXT, Constant.DIR_NOVEL};
            Observable.just("").map(new Function<String, String>() { // from class: com.movieboxpro.android.app.App.3
                @Override // io.reactivex.functions.Function
                public String apply(String str) throws Exception {
                    for (String str2 : Constant.DIRS) {
                        FileUtils.createOrExistsDir(new File(str2));
                    }
                    return null;
                }
            }).compose(RxUtils.rxSchedulerHelper()).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.app.App.2
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
    }

    private void autoLogin() {
        MLog.d(TAG, "autoLogin");
        ACache old = ACache.getOld(this);
        OldUserModel oldUserModel = (OldUserModel) old.getAsObject("user");
        if (oldUserModel != null) {
            MLog.d(TAG, "autoLogin: old cache");
            login((UserModel) JSONObject.parseObject(JSONObject.toJSONString(oldUserModel)).toJavaObject((Class<Object>) UserModel.class));
            old.remove("user");
            ACache.clearOldCache(this);
            return;
        }
        ACache.clearOldCache(this);
        UserModel userModel = null;
        File userFile = getUserFile();
        if (userFile.exists() && userFile.isFile()) {
            userModel = (UserModel) JSONObject.toJavaObject(JSONObject.parseObject(CipherUtils.decrypt(FileUtil.getFileOutputString(userFile.getAbsolutePath()), CipherKeys.getDefaultKeys())), UserModel.class);
        }
        if (userModel != null && userModel.member != null) {
            login(userModel);
        } else {
            logout();
        }
    }

    private void initPrefs() {
        PrefsUtils.init(getContext(), Constant.Prefs.SETTING, 4);
        if (!PrefsUtils.getInstance().getBoolean("app_48", false)) {
            PrefsUtils.getInstance().putBoolean("app_48", true);
            PrefsUtils.getInstance().remove(Constant.Prefs.SHOW_READ_TIPS);
        }
        String string = PrefsUtils.getInstance().getString("language", "");
        if (!TextUtils.isEmpty(string)) {
            deviceLang = string;
        } else {
            initLanguage();
        }
    }

    private static void initDB() {
        db = (ReaderDB) Room.databaseBuilder(getContext(), ReaderDB.class, "movie_box.db").addMigrations(Migrations.migration1_2, Migrations.migration4_5, Migrations.migration5_6, Migrations.migration6_7, Migrations.migration7_8, Migrations.migration8_9, Migrations.migration9_10).fallbackToDestructiveMigration().allowMainThreadQueries().build();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.multidex.MultiDexApplication, android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(context);
    }

    private void configLeakCanary() {
        MLog.d(TAG, "configLeakCanary");
    }

    private void configJarvis() {
        MLog.d(TAG, "configJarvis");
        Jarvis.init(new DefaultDownloadHistoryDBHelper(this));
    }

    private void configTokne() {
        MLog.d(TAG, "configTokne" + (Settings.Secure.getString(getContentResolver(), "android_id") + Build.SERIAL));
    }

    public static App getInstance() {
        return instance;
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }

    public static ReaderDB getDB() {
        ReaderDB readerDB = db;
        if (readerDB == null || !readerDB.isOpen()) {
            initDB();
        }
        return db;
    }

    public static ACache getCache() {
        if (mCache == null) {
            mCache = ACache.get(getContext());
        }
        return mCache;
    }

    public static boolean isLogin() {
        UserModel userModel;
        return (!isLogin || (userModel = user) == null || userModel.member == null) ? false : true;
    }

    public static UserModel.UserData getUserData() {
        if (isLogin()) {
            return user.member;
        }
        return new UserModel.UserData();
    }

    public static UserModel.BBsInfo getBBsInfo() {
        if (isLogin()) {
            return user.member.getBbs_bind();
        }
        return null;
    }

    public static UserModel getUserModel() {
        if (isLogin()) {
            return user;
        }
        return new UserModel();
    }

    public static void login(UserModel userModel) {
        isLogin = true;
        user = userModel;
        saveUser();
        FirebaseCrashlytics.getInstance().setUserId(user.member.getMaster_uid());
        FirebaseCrashlytics.getInstance().setCustomKey("VERSION_NAME", BuildConfig.VERSION_NAME);
        FirebaseCrashlytics.getInstance().setCustomKey("VERSION_CODE", 169);
        if (observable == null) {
            observable = new AppObservable();
        }
    }

    private static void saveUser() {
        FileUtils.write(getUserFile(), CipherUtils.encrypt(JSONObject.toJSONString(user), CipherKeys.getDefaultKeys()), false);
    }

    private static File getUserFile() {
        return new File(getContext().getFilesDir(), "user.json");
    }

    public void addObserver(java.util.Observer observer) {
        MLog.d(TAG, "addObserver");
        AppObservable appObservable = observable;
        if (appObservable == null || observer == null) {
            return;
        }
        appObservable.addObserver(observer);
    }

    public void delObserver(java.util.Observer observer) {
        MLog.d(TAG, "delObserver");
        AppObservable appObservable = observable;
        if (appObservable == null || observer == null) {
            return;
        }
        appObservable.deleteObserver(observer);
    }

    public static void updateUser(UserModel.UserData userData) {
        MLog.d(TAG, "updateUser");
        if (isLogin()) {
            userData.uid_v2 = user.member.uid_v2;
            user.member = userData;
            saveUser();
            if (observable != null) {
                MLog.d(TAG, "updateUser: notifyObservers");
                observable.notifyObservers();
                return;
            }
            return;
        }
        logout();
    }

    public static void updateUser(UserModel.BBsInfo bBsInfo) {
        MLog.d(TAG, "updateUser");
        if (isLogin()) {
            user.member.setBbs_bind(bBsInfo);
            saveUser();
            if (observable != null) {
                MLog.d(TAG, "updateUser: notifyObservers");
                observable.notifyObservers();
                return;
            }
            return;
        }
        logout();
    }

    public static void updateUser(String str) {
        MLog.d(TAG, "updateUser");
        if (isLogin()) {
            user.member.avatar = str;
            saveUser();
            if (observable != null) {
                MLog.d(TAG, "updateUser: notifyObservers");
                observable.notifyObservers();
                return;
            }
            return;
        }
        logout();
    }

    public static void updateUser(String str, int i) {
        MLog.d(TAG, "updateUser");
        if (isLogin()) {
            user.member.email = str;
            saveUser();
            if (observable != null) {
                MLog.d(TAG, "updateUser: notifyObservers");
                observable.notifyObservers();
                return;
            }
            return;
        }
        logout();
    }

    public static void updateUsername(String str, int i) {
        MLog.d(TAG, "updateUser");
        if (isLogin()) {
            user.member.username = str;
            saveUser();
            if (observable != null) {
                MLog.d(TAG, "updateUser: notifyObservers");
                observable.notifyObservers();
                return;
            }
            return;
        }
        logout();
    }

    public static void logout() {
        delUser();
        clearDatas();
        isLogin = false;
    }

    private static void delUser() {
        user = null;
        FileUtils.deleteFile(getUserFile());
    }

    private static void clearDatas() {
        Observable.just("").map(new Function<String, String>() { // from class: com.movieboxpro.android.app.App.5
            @Override // io.reactivex.functions.Function
            public String apply(String str) throws Exception {
                String[] strArr;
                try {
                    App.getDB().clearPlayRecord();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                PrefsUtils.getInstance().removeAll();
                GlideUtils.clearImageAllCache(App.getContext());
                if (Constant.DIRS_CLEAR != null) {
                    for (String str2 : Constant.DIRS_CLEAR) {
                        if (str2 != null) {
                            FileUtils.deleteFile(new File(str2));
                        }
                    }
                    for (String str3 : Constant.DIRS) {
                        try {
                            FileUtils.forceMkdir(new File(str3));
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    return "";
                }
                return "";
            }
        }).compose(RxUtils.rxSchedulerHelper()).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.app.App.4
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

    public static void clearCache() {
        AsyncTask.execute(new Runnable() { // from class: com.movieboxpro.android.app.App.6
            @Override // java.lang.Runnable
            public void run() {
                PrefsUtils.getInstance().removeAll();
                GlideUtils.clearImageAllCache(App.getContext());
            }
        });
    }

    public static void clearMemory() {
        AsyncTask.execute(new Runnable() { // from class: com.movieboxpro.android.app.App.7
            @Override // java.lang.Runnable
            public void run() {
                GlideUtils.clearImageAllCache(App.getContext());
            }
        });
    }

    public void updateOnline() {
        MLog.d(TAG, "updateOnline");
        OnlineConfigAgent.getInstance().setOnlineConfigListener(this);
        OnlineConfigAgent.getInstance().updateOnlineConfig();
    }

    @Override // com.movieboxpro.android.config.OnlineConfigAgent.OnlineConfigListener
    public void onDataReceived(String str) {
        MLog.d(TAG, "onDataReceived: " + str);
        if (!TextUtils.isEmpty(str)) {
            readOnline();
        }
        EventBus.getDefault().postSticky(new ConfigReceiveEvent());
    }

    public void readOnline() {
        MLog.d(TAG, "readOnline");
        String readStringConfig = ConfigUtils.readStringConfig(ConfigKey.URL_VALUE);
        if (!TextUtils.isEmpty(readStringConfig)) {
            String[] split = readStringConfig.split("\\|");
            if (split.length == 1) {
                if (!API.MOVIE_HOST.equals(split[0])) {
                    MLog.d(TAG, "readOnline: base url changed");
                    API.MOVIE_HOST = split[0];
                    API.REAL_MOVIE_HOST = "";
                    API.BASE_URL = API.MOVIE_HOST + API.STONE_MOVIE_SUFFIX;
                    API.TRANSLATE_URL = API.MOVIE_HOST + "/api/srttrans/index/";
                    Http.baseUrlChanged();
                }
            } else if (split.length == 2) {
                String str = split[0];
                String str2 = split[1];
                if (!API.MOVIE_HOST.equals(str) || !API.REAL_MOVIE_HOST.equals(str2)) {
                    API.MOVIE_HOST = str;
                    API.REAL_MOVIE_HOST = str2;
                    API.BASE_URL = API.MOVIE_HOST + API.STONE_MOVIE_SUFFIX;
                    API.TRANSLATE_URL = API.MOVIE_HOST + "/api/srttrans/index/";
                    Http.baseUrlChanged();
                }
            }
        }
        String readStringConfig2 = ConfigUtils.readStringConfig(ConfigKey.BBS_URL);
        if (TextUtils.isEmpty(readStringConfig2)) {
            return;
        }
        API.BBS_URL = readStringConfig2;
    }

    public static void exit() {
        MLog.d(TAG, "exit");
        hasExitCall = true;
        AppManager.finishAll();
        CallManager.cancelAll();
        RxManager.removeAll();
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        Glide.get(this).clearMemory();
        MLog.d(TAG, "onLowMemory");
    }

    @Override // android.app.Application
    public void onTerminate() {
        super.onTerminate();
        MLog.d(TAG, "onTerminate");
        ReaderDB readerDB = db;
        if (readerDB != null) {
            readerDB.close();
            db = null;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        activityCount++;
        MLog.d(TAG, "onActivityStarted: " + activityCount + ", " + activity.getClass().getSimpleName());
        if (activityCount <= 1) {
            MLog.d(TAG, "onActivityStarted: app enter from background: " + TimeUtils.getFormatedTime(TimeUtils.DETAULT_PATTERN));
            return;
        }
        MLog.d(TAG, "onActivityStarted: app jump internal");
        PrefsUtils.getInstance().putLong(Constant.Prefs.ENTER_BACK_TIME, TimeUtils.getCurrentTime());
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        activityCount--;
        MLog.d(TAG, "onActivityStopped: " + activityCount + ", " + activity.getClass().getSimpleName());
        if (activityCount == 0) {
            if (hasExitCall) {
                MLog.d(TAG, "onActivityStopped: app going to finish");
                PrefsUtils.getInstance().putLong(Constant.Prefs.ENTER_BACK_TIME, 0L);
                return;
            }
            PrefsUtils.getInstance().putLong(Constant.Prefs.ENTER_BACK_TIME, TimeUtils.getCurrentTime());
            MLog.d(TAG, "onActivityStopped: app enter background: " + TimeUtils.getFormatedTime(TimeUtils.DETAULT_PATTERN));
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        if (activityCount == 0) {
            MLog.d(TAG, "onActivityDestroyed: app has finished");
        }
        hasExitCall = false;
    }

    @Override // android.app.Application, android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        if (i == 20) {
            Glide.get(getContext()).clearMemory();
        }
        Glide.get(getContext()).trimMemory(i);
    }
}
