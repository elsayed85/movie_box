package com.movieboxpro.android.view.activity.settings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.alibaba.fastjson.JSONObject;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseActivity;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.config.ConfigKey;
import com.movieboxpro.android.config.ConfigUtils;
import com.movieboxpro.android.db.entity.Download;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.HttpUtils;
import com.movieboxpro.android.livedata.DownloadPathLiveData;
import com.movieboxpro.android.livedata.HideMovieListLiveData;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.EventUtils;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.LogUtils;
import com.movieboxpro.android.utils.MemoryUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.settings.LocationWebViewActivity;
import com.movieboxpro.android.view.activity.user.Login2Activity;
import com.movieboxpro.android.view.dialog.ChooseDownloadPathDialog;
import com.movieboxpro.android.view.dialog.ListDialog;
import com.movieboxpro.android.view.widget.UpdateChecker;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.io.File;
import java.util.List;
/* loaded from: classes3.dex */
public class SettingActivity extends BaseActivity {
    private static final int CHILD_MODE_CODE = 1;
    private static final int CHILD_MODE_CODE_CHANGE = 4;
    private static final int INPUT_CHILD_MODE_CODE = 2;
    private static final int INPUT_CHILD_MODE_CODE_CHANGE = 3;
    private static final int PANDA_FORUM_BIND_STATUS = 5;
    private boolean childModeEnable = true;
    @BindView(R.id.switch_child_mode)
    SwitchCompat childModeSwitch;
    @BindView(R.id.clEmailRemind)
    ConstraintLayout clEmailRemind;
    @BindView(R.id.switch_email_remind)
    SwitchCompat emailRemind;
    @BindView(R.id.ivNewVersion)
    ImageView ivNewVersion;
    @BindView(R.id.setting_play_fullscreen)
    ConstraintLayout setting_play_fullscreen;
    @BindView(R.id.switchAutoNext)
    SwitchCompat switchAutoNext;
    @BindView(R.id.switchAutoSelectSubtitle)
    SwitchCompat switchAutoSelectSubtitle;
    @BindView(R.id.switchBlackMode)
    SwitchCompat switchBlackMode;
    @BindView(R.id.switch_hide_movielist)
    SwitchCompat switchHideMovielist;
    @BindView(R.id.switch_play_fullscreen)
    SwitchCompat switchPlayFullscreen;
    @BindView(R.id.switch_remember_org)
    SwitchCompat switchRememberOrg;
    @BindView(R.id.tvChangeChildModePsw)
    TextView tvChangeChildModePsw;
    @BindView(R.id.tvCheckUpdate)
    TextView tvCheckUpdate;
    @BindView(R.id.tvDownloadDirName)
    TextView tvDownloadDirName;
    @BindView(R.id.tvDownloadSpace)
    TextView tvDownloadSpace;
    @BindView(R.id.tvPlayerEngine)
    TextView tvPlayerEngine;
    @BindView(R.id.setting_download_more)
    SwitchCompat userCelluarDownload;

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initData() {
    }

    @OnClick({R.id.setting_language, R.id.setting_download, R.id.setting_cache, R.id.setting_faq, R.id.setting_about, R.id.setting_feedback, R.id.setting_mytickets, R.id.tvAbout, R.id.tvPrivacyPolicy, R.id.tvInviteCode, R.id.tvChangeEmail, R.id.tvMyOrder, R.id.tvChangeChildModePsw, R.id.setting_player_engine, R.id.clDownloadLocation, R.id.tvDeleteAccount, R.id.tvUploadVlcLog, R.id.tvUploadIJKLog})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.clDownloadLocation /* 2131296531 */:
                new ChooseDownloadPathDialog().show(getSupportFragmentManager(), ChooseDownloadPathDialog.class.getSimpleName());
                return;
            case R.id.setting_about /* 2131297764 */:
                route(AboutActivity.class);
                return;
            case R.id.setting_cache /* 2131297767 */:
                clearAppCache();
                return;
            case R.id.setting_faq /* 2131297777 */:
                if (!App.isLogin()) {
                    route(Login2Activity.class);
                }
                SystemUtils.startBrowser(this.activity, "https://www.movieboxpro.app/index/index/faq");
                return;
            case R.id.setting_feedback /* 2131297780 */:
                if (!App.isLogin()) {
                    route(Login2Activity.class);
                }
                Context context = App.getContext();
                SystemUtils.startBrowser(context, "https://www.movieboxpro.app/index/order/feed?auth=" + buildData(SystemUtils.getMsg(this.activity)));
                return;
            case R.id.setting_language /* 2131297785 */:
                SwitchLanguageActivity.Companion.start(this);
                return;
            case R.id.setting_mytickets /* 2131297787 */:
                if (!App.isLogin()) {
                    route(Login2Activity.class);
                }
                Context context2 = App.getContext();
                SystemUtils.startBrowser(context2, "https://www.movieboxpro.app/index/order/index?auth=" + buildData(""));
                return;
            case R.id.setting_player_engine /* 2131297792 */:
                new ListDialog.Builder(this).setItems(new String[]{"AUTO", "IJK", "VLC", "EXO"}).addAction(new ListDialog.OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$SettingActivity$AiBB9H5x3KVnBdkEgc7W4JpMvvw
                    @Override // com.movieboxpro.android.view.dialog.ListDialog.OnItemClickListener
                    public final void onClick(View view2, int i) {
                        SettingActivity.this.lambda$onViewClicked$0$SettingActivity(view2, i);
                    }
                }).setTitleVisible(true).create().show();
                return;
            case R.id.tvAbout /* 2131298023 */:
                AboutActivity.start(this);
                return;
            case R.id.tvChangeChildModePsw /* 2131298043 */:
                InputChildModePasswordActivity.Companion.start(this, 3);
                return;
            case R.id.tvChangeEmail /* 2131298044 */:
                if (App.isLogin()) {
                    ChangeEmilActivity.Companion.start(this);
                    return;
                } else {
                    Login2Activity.start(this);
                    return;
                }
            case R.id.tvDeleteAccount /* 2131298061 */:
                SystemUtils.startBrowser((Activity) this, "https://www.movieboxpro.app/index/index/confirm_logout");
                return;
            case R.id.tvInviteCode /* 2131298102 */:
                LocationWebViewActivity.Companion companion = LocationWebViewActivity.Companion;
                companion.start(this, "https://www.movieboxpro.app/index/invite/code?auth=" + buildData(SystemUtils.getMsg(this)));
                return;
            case R.id.tvMyOrder /* 2131298123 */:
                if (App.isLogin()) {
                    OrderActivity.start(this);
                    return;
                } else {
                    Login2Activity.start(this);
                    return;
                }
            case R.id.tvPrivacyPolicy /* 2131298156 */:
                SystemUtils.startBrowser(App.getContext(), "https://www.movieboxpro.app/index/article?id=6");
                return;
            case R.id.tvUploadIJKLog /* 2131298240 */:
                LogUtils.INSTANCE.uploadError2(false, 1, 1, 1, "100");
                return;
            case R.id.tvUploadVlcLog /* 2131298241 */:
                LogUtils.INSTANCE.uploadError2(true, 1, 1, 1, "100");
                return;
            default:
                return;
        }
    }

    public /* synthetic */ void lambda$onViewClicked$0$SettingActivity(View view, int i) {
        if (i == 0) {
            this.tvPlayerEngine.setText("AUTO");
            PrefsUtils.getInstance().putInt(Constant.Prefs.PLAYER_ENGINE, 0);
            CommonUtils.onEvent("Setting_Auto_Engine");
            EventUtils.event("设置里切换Auto引擎");
        } else if (i == 1) {
            this.tvPlayerEngine.setText("IJK");
            PrefsUtils.getInstance().putInt(Constant.Prefs.PLAYER_ENGINE, 1);
            CommonUtils.onEvent("Setting_Ijk_Engine");
            EventUtils.event("设置里切换ijk引擎");
        } else if (i == 2) {
            this.tvPlayerEngine.setText("VLC");
            PrefsUtils.getInstance().putInt(Constant.Prefs.PLAYER_ENGINE, 2);
            CommonUtils.onEvent("Setting_vlc_Engine");
            EventUtils.event("设置里切换vlc引擎");
        } else if (i == 3) {
            this.tvPlayerEngine.setText("EXO");
            PrefsUtils.getInstance().putInt(Constant.Prefs.PLAYER_ENGINE, 3);
            CommonUtils.onEvent("Setting_exo_Engine");
            EventUtils.event("设置里切换exo引擎");
        }
    }

    @Override // com.movieboxpro.android.base.BaseActivity
    protected int getStatusTintColor() {
        return PrefsUtils.getInstance().getBoolean(Constant.Prefs.BLACK_MODE) ? R.color.color_main_black : R.color.color_main;
    }

    private void clearAppCache() {
        ((ObservableSubscribeProxy) Observable.create(new ObservableOnSubscribe() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$SettingActivity$uyj5SG03WBiqRGubPgJ3kwRsH8I
            @Override // io.reactivex.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                SettingActivity.this.lambda$clearAppCache$3$SettingActivity(observableEmitter);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.settings.SettingActivity.1
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
            public void onNext(String str) {
                ToastUtils.showShort("Cache Empty");
            }
        });
    }

    public /* synthetic */ void lambda$clearAppCache$3$SettingActivity(ObservableEmitter observableEmitter) throws Exception {
        GlideUtils.clearImageAllCache(this);
        FileUtils.deleteAllInDir(new File(Constant.DIR_CROP));
        FileUtils.deleteAllInDir(new File(Constant.DIR_LOG));
        FileUtils.deleteFilesInDirWithFilter(new File(Constant.DIR_DOWNLOAD), $$Lambda$SettingActivity$CTl9gI9FBJNWzSYXTdQFJ2VEPo.INSTANCE);
        List<Download> all = App.getDB().downloadDao().getAll();
        List<File> listFilesInDirWithFilter = FileUtils.listFilesInDirWithFilter(new File(Constant.DIR_DOWNLOAD), $$Lambda$SettingActivity$myY_1BgKzHy99YZRsqcGYbzfkjo.INSTANCE, false);
        if (listFilesInDirWithFilter != null) {
            for (File file : listFilesInDirWithFilter) {
                if (!findExist(file.getName(), all)) {
                    file.delete();
                }
            }
        }
        observableEmitter.onNext("");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$clearAppCache$1(File file) {
        String extensionName = FileUtils.getExtensionName(file.getPath());
        return "srt".equalsIgnoreCase(extensionName) || "ass".equalsIgnoreCase(extensionName);
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
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.view.activity.settings.SettingActivity.findExist(java.lang.String, java.util.List):boolean");
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, SettingActivity.class));
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public View loadView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.activity_setting, (ViewGroup) null);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initView() {
        int i = PrefsUtils.getInstance().getInt(Constant.Prefs.PLAYER_ENGINE, 0);
        if (i == 0) {
            this.tvPlayerEngine.setText("AUTO");
        } else if (i == 1) {
            this.tvPlayerEngine.setText("Ijk");
        } else if (i == 2) {
            this.tvPlayerEngine.setText("VLC");
        } else if (i == 3) {
            this.tvPlayerEngine.setText("EXO");
        }
        setTitle("Settings");
        if (App.isLogin()) {
            if (App.getUserData().getMovie_update_remind_status() == 1) {
                this.emailRemind.setChecked(true);
            } else {
                this.emailRemind.setChecked(false);
            }
            this.clEmailRemind.setVisibility(0);
        } else {
            this.clEmailRemind.setVisibility(8);
        }
        this.switchAutoNext.setChecked(PrefsUtils.getInstance().getBoolean(Constant.Prefs.AUTO_PLAY_NEXT, false));
        this.userCelluarDownload.setChecked(PrefsUtils.getInstance().getBoolean(Constant.Prefs.USER_CELLUAR_DOWNLOAD, false));
        this.userCelluarDownload.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.movieboxpro.android.view.activity.settings.SettingActivity.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    PrefsUtils.getInstance().putBoolean(Constant.Prefs.USER_CELLUAR_DOWNLOAD, true);
                } else {
                    PrefsUtils.getInstance().putBoolean(Constant.Prefs.USER_CELLUAR_DOWNLOAD, false);
                }
            }
        });
        this.emailRemind.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.movieboxpro.android.view.activity.settings.SettingActivity.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    SettingActivity.this.setRemindStatus(1);
                } else {
                    SettingActivity.this.setRemindStatus(0);
                }
            }
        });
        this.switchBlackMode.setChecked(PrefsUtils.getInstance().getBoolean(Constant.Prefs.BLACK_MODE, false));
        this.switchBlackMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.movieboxpro.android.view.activity.settings.SettingActivity.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                PrefsUtils.getInstance().putBoolean(Constant.Prefs.BLACK_MODE, z);
            }
        });
        this.switchAutoNext.setOnCheckedChangeListener($$Lambda$SettingActivity$t49jdaSsjcSR_RWrfshfhaU4hfI.INSTANCE);
        this.childModeSwitch.setChecked(PrefsUtils.getInstance().getBoolean(Constant.Prefs.CHILD_MODE, false));
        this.childModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.movieboxpro.android.view.activity.settings.SettingActivity.5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (SettingActivity.this.childModeEnable) {
                    if (z) {
                        ChildModePasswordActivity.Companion.start(SettingActivity.this, 1);
                    } else {
                        InputChildModePasswordActivity.Companion.start(SettingActivity.this, 2);
                    }
                }
            }
        });
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.FULLSCREEN_PLAY, true)) {
            this.switchPlayFullscreen.setChecked(true);
        } else {
            this.switchPlayFullscreen.setChecked(false);
        }
        this.switchPlayFullscreen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.movieboxpro.android.view.activity.settings.SettingActivity.6
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                PrefsUtils.getInstance().putBoolean(Constant.Prefs.FULLSCREEN_PLAY, z);
            }
        });
        if (Build.VERSION.SDK_INT >= 28) {
            this.setting_play_fullscreen.setVisibility(0);
        } else {
            this.setting_play_fullscreen.setVisibility(8);
        }
        this.tvCheckUpdate.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$SettingActivity$HtZDdKhYHM5huOP6VV4UocT1-kI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingActivity.this.lambda$initView$5$SettingActivity(view);
            }
        });
        if (ConfigUtils.readIntegerConfig(ConfigKey.WEB_APK_LATEST_VERSION, 1) > 169) {
            this.ivNewVersion.setVisibility(0);
        }
        this.switchRememberOrg.setChecked(PrefsUtils.getInstance().getBoolean(Constant.Prefs.REMEMBER_ORG_QUALITY, false));
        this.switchRememberOrg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.movieboxpro.android.view.activity.settings.SettingActivity.7
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                PrefsUtils.getInstance().putBoolean(Constant.Prefs.REMEMBER_ORG_QUALITY, z);
            }
        });
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.INTERNAL_STORAGE, true)) {
            this.tvDownloadDirName.setText("Internal Storage");
            this.tvDownloadSpace.setText(String.format("%s Free, %s Total", FileUtils.byte2FitMemorySize(MemoryUtils.getAvailSpaceSize(this.context)), FileUtils.byte2FitMemorySize(MemoryUtils.getInternalToatalSize(this.context))));
        } else {
            this.tvDownloadDirName.setText("SDCARD");
            String parent = new File(PrefsUtils.getInstance().getString(Constant.Prefs.DOWNLOAD_DIR)).getParent();
            this.tvDownloadSpace.setText(String.format("%s Free, %s Total", FileUtils.byte2FitMemorySize(FileUtils.getFsAvailableSize(parent)), FileUtils.byte2FitMemorySize(FileUtils.getFsTotalSize(parent))));
        }
        this.switchAutoSelectSubtitle.setChecked(PrefsUtils.getInstance().getBoolean(Constant.Prefs.AUTO_SELECT_SUBTITLE, false));
        this.switchAutoSelectSubtitle.setOnCheckedChangeListener($$Lambda$SettingActivity$oFMShRiXn8T1rBHUW_sKjDvIL5k.INSTANCE);
        DownloadPathLiveData.Companion.get().observe(this, new androidx.lifecycle.Observer<String>() { // from class: com.movieboxpro.android.view.activity.settings.SettingActivity.8
            @Override // androidx.lifecycle.Observer
            public void onChanged(String str) {
                SettingActivity.this.initDownloadPath();
            }
        });
        initDownloadPath();
        this.switchHideMovielist.setChecked(PrefsUtils.getInstance().getBoolean(Constant.Prefs.HIDE_MOVIELIST, false));
        this.switchHideMovielist.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.movieboxpro.android.view.activity.settings.SettingActivity.9
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                PrefsUtils.getInstance().putBoolean(Constant.Prefs.HIDE_MOVIELIST, z);
                HideMovieListLiveData.Companion.get().setValue(Boolean.valueOf(z));
            }
        });
    }

    public /* synthetic */ void lambda$initView$5$SettingActivity(View view) {
        if (ConfigUtils.readIntegerConfig(ConfigKey.WEB_APK_LATEST_VERSION, App.versionCode) <= App.versionCode) {
            ToastUtils.showShort("MovieBoxPro is up to date.");
            return;
        }
        UpdateChecker.INSTANCE.checkWebUpdate(this);
        CommonUtils.onEvent("check_upgrade");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initDownloadPath() {
        String byte2FitMemorySize;
        String str;
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.INTERNAL_STORAGE, true)) {
            byte2FitMemorySize = FileUtils.byte2FitMemorySize(MemoryUtils.getAvailSpaceSize(this.context));
            str = "Internal Storage";
        } else {
            byte2FitMemorySize = FileUtils.byte2FitMemorySize(FileUtils.getFsTotalSize(PrefsUtils.getInstance().getString(Constant.Prefs.DOWNLOAD_DIR)));
            str = "SD Card";
        }
        this.tvDownloadDirName.setText(String.format("%s", str));
        this.tvDownloadSpace.setText(String.format("Free:%s", byte2FitMemorySize));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1) {
            if (i2 == -1) {
                PrefsUtils.getInstance().putBoolean(Constant.Prefs.CHILD_MODE, true);
                this.tvChangeChildModePsw.setVisibility(0);
                return;
            }
            this.childModeEnable = false;
            this.childModeSwitch.setChecked(false);
            this.childModeEnable = true;
        } else if (i == 2) {
            if (i2 == -1) {
                PrefsUtils.getInstance().putString(Constant.Prefs.CHILD_MODE_PASSWORD, "");
                PrefsUtils.getInstance().putBoolean(Constant.Prefs.CHILD_MODE, false);
                this.tvChangeChildModePsw.setVisibility(8);
                return;
            }
            this.childModeEnable = false;
            this.childModeSwitch.setChecked(true);
            this.childModeEnable = true;
        } else if (i == 3) {
            if (i2 == -1) {
                ChildModePasswordActivity.Companion.start(this, 4);
            }
        } else if (i == 4 && i2 == -1) {
            ToastUtils.showShort("Change Successfully");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRemindStatus(final int i) {
        ((ObservableSubscribeProxy) this.service.Movie_update_remind(API.BASE_URL, "Movie_update_remind", App.getUserData().uid_v2, i).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<String>() { // from class: com.movieboxpro.android.view.activity.settings.SettingActivity.10
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                SettingActivity.this.showLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(String str) {
                UserModel.UserData userData = App.getUserData();
                userData.movie_update_remind_status = i;
                App.updateUser(userData);
                SettingActivity.this.hideLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException apiException) {
                SettingActivity.this.hideLoading();
            }
        });
    }

    private String buildData(String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("env", (Object) str);
        jSONObject.put("uid", (Object) (App.isLogin() ? App.getUserData().uid_v2 : ""));
        jSONObject.put("open_udid", (Object) SystemUtils.getUniqueId(App.getContext()));
        jSONObject.put("expired_date", (Object) Long.valueOf((TimeUtils.getCurrentTime() / 1000) + 43200));
        return HttpUtils.encodeBody(jSONObject.toJSONString());
    }
}
