package com.movieboxpro.android.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentManager;
import com.hi.dhl.jdatabinding.ActivityDataBindingDelegate;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseBindingActivity;
import com.movieboxpro.android.databinding.ActivitySetting2Binding;
import com.movieboxpro.android.db.entity.Download;
import com.movieboxpro.android.event.LogoutEvent;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.livedata.DownloadPathLiveData;
import com.movieboxpro.android.livedata.HideMovieListLiveData;
import com.movieboxpro.android.livedata.SmartDownloadChangedLiveData;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.EventUtils;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.MemoryUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SettingManager;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.settings.SwitchLanguageActivity;
import com.movieboxpro.android.view.activity.settings.TestSpeedActivity;
import com.movieboxpro.android.view.dialog.ChooseDownloadPathDialog;
import com.movieboxpro.android.view.dialog.ListDialog;
import com.movieboxpro.android.view.widget.CustomSwitchView;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: SettingActivity.kt */
@Metadata(d1 = {"\u0000Q\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000*\u0001\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0014J\u001e\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0002J\b\u0010\u0016\u001a\u00020\rH\u0016J\b\u0010\u0017\u001a\u00020\rH\u0002J\b\u0010\u0018\u001a\u00020\rH\u0016J\b\u0010\u0019\u001a\u00020\rH\u0002J\b\u0010\u001a\u001a\u00020\rH\u0002J\b\u0010\u001b\u001a\u00020\rH\u0016J\"\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001e2\b\u0010 \u001a\u0004\u0018\u00010!H\u0014J\b\u0010\"\u001a\u00020\rH\u0014J\u0010\u0010#\u001a\u00020\r2\u0006\u0010$\u001a\u00020%H\u0007R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u0010\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000b¨\u0006&"}, d2 = {"Lcom/movieboxpro/android/view/activity/SettingActivity;", "Lcom/movieboxpro/android/base/BaseBindingActivity;", "()V", "binding", "Lcom/movieboxpro/android/databinding/ActivitySetting2Binding;", "getBinding", "()Lcom/movieboxpro/android/databinding/ActivitySetting2Binding;", "binding$delegate", "Lcom/hi/dhl/jdatabinding/ActivityDataBindingDelegate;", "observer", "com/movieboxpro/android/view/activity/SettingActivity$observer$1", "Lcom/movieboxpro/android/view/activity/SettingActivity$observer$1;", "clearAppCache", "", "enableEventBus", "", "findExist", "fileName", "", "downloads", "", "Lcom/movieboxpro/android/db/entity/Download;", "initData", "initDownloadPath", "initListener", "initStatus", "initUserInfo", "initView", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onDestroy", "onSignOut", NotificationCompat.CATEGORY_EVENT, "Lcom/movieboxpro/android/event/LogoutEvent;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SettingActivity extends BaseBindingActivity {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(SettingActivity.class, "binding", "getBinding()Lcom/movieboxpro/android/databinding/ActivitySetting2Binding;", 0))};
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private final ActivityDataBindingDelegate binding$delegate = new ActivityDataBindingDelegate(ActivitySetting2Binding.class, this);
    private final SettingActivity$observer$1 observer = new Observer() { // from class: com.movieboxpro.android.view.activity.SettingActivity$observer$1
        @Override // java.util.Observer
        public void update(Observable o, Object obj) {
            Intrinsics.checkNotNullParameter(o, "o");
            SettingActivity.this.initUserInfo();
        }
    };

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    protected boolean enableEventBus() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ActivitySetting2Binding getBinding() {
        return (ActivitySetting2Binding) this.binding$delegate.getValue2((Activity) this, $$delegatedProperties[0]);
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initListener() {
        getBinding().llSmartDownload.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$SettingActivity$L9h8TuGX710sQH3YJly9cFQ_4qs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingActivity.m272initListener$lambda0(SettingActivity.this, view);
            }
        });
        getBinding().clInfo.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$SettingActivity$zgaGX743jMeQ199DKBOvXXA10AY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingActivity.m273initListener$lambda1(SettingActivity.this, view);
            }
        });
        getBinding().toolBar.llBack.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$SettingActivity$iqTaG_YGDfXvtjtfj1hVS0YgjD4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingActivity.m274initListener$lambda2(SettingActivity.this, view);
            }
        });
        getBinding().llSpeedTest.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$SettingActivity$qtBwnLi9vC1tFPpWIKgRw19wFe4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingActivity.m275initListener$lambda3(SettingActivity.this, view);
            }
        });
        getBinding().llEmptyCache.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$SettingActivity$8MwxNv27PrpuA7Z_g5WiKqrIvxU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingActivity.m276initListener$lambda4(SettingActivity.this, view);
            }
        });
        getBinding().llPlayer.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$SettingActivity$pwhIimHASVmYshWknASWJHFKUvY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingActivity.m277initListener$lambda6(SettingActivity.this, view);
            }
        });
        getBinding().switchAutoPlayNext.setCheckListener(new CustomSwitchView.OnCheckListener() { // from class: com.movieboxpro.android.view.activity.SettingActivity$initListener$7
            @Override // com.movieboxpro.android.view.widget.CustomSwitchView.OnCheckListener
            public void onCheckChangeListener(boolean z) {
                PrefsUtils.getInstance().putBoolean(Constant.Prefs.AUTO_PLAY_NEXT, z);
            }
        });
        getBinding().switchBlack.setCheckListener(new CustomSwitchView.OnCheckListener() { // from class: com.movieboxpro.android.view.activity.SettingActivity$initListener$8
            @Override // com.movieboxpro.android.view.widget.CustomSwitchView.OnCheckListener
            public void onCheckChangeListener(boolean z) {
                PrefsUtils.getInstance().putBoolean(Constant.Prefs.BLACK_MODE, z);
                if (z) {
                    AppCompatDelegate.setDefaultNightMode(2);
                } else {
                    AppCompatDelegate.setDefaultNightMode(1);
                }
            }
        });
        getBinding().switchAutoSelectSubtitle.setCheckListener(new CustomSwitchView.OnCheckListener() { // from class: com.movieboxpro.android.view.activity.SettingActivity$initListener$9
            @Override // com.movieboxpro.android.view.widget.CustomSwitchView.OnCheckListener
            public void onCheckChangeListener(boolean z) {
                PrefsUtils.getInstance().putBoolean(Constant.Prefs.AUTO_SELECT_SUBTITLE, z);
            }
        });
        getBinding().switchFullScreen.setCheckListener(new CustomSwitchView.OnCheckListener() { // from class: com.movieboxpro.android.view.activity.SettingActivity$initListener$10
            @Override // com.movieboxpro.android.view.widget.CustomSwitchView.OnCheckListener
            public void onCheckChangeListener(boolean z) {
                PrefsUtils.getInstance().putBoolean(Constant.Prefs.FULLSCREEN_PLAY, z);
            }
        });
        getBinding().switchHideMovieList.setCheckListener(new CustomSwitchView.OnCheckListener() { // from class: com.movieboxpro.android.view.activity.SettingActivity$initListener$11
            @Override // com.movieboxpro.android.view.widget.CustomSwitchView.OnCheckListener
            public void onCheckChangeListener(boolean z) {
                PrefsUtils.getInstance().putBoolean(Constant.Prefs.HIDE_MOVIELIST, z);
                HideMovieListLiveData.Companion.get().setValue(Boolean.valueOf(z));
            }
        });
        getBinding().switchDownloadCellular.setCheckListener(new CustomSwitchView.OnCheckListener() { // from class: com.movieboxpro.android.view.activity.SettingActivity$initListener$12
            @Override // com.movieboxpro.android.view.widget.CustomSwitchView.OnCheckListener
            public void onCheckChangeListener(boolean z) {
                PrefsUtils.getInstance().putBoolean(Constant.Prefs.USER_CELLUAR_DOWNLOAD, z);
            }
        });
        getBinding().switchRememberOrg.setCheckListener(new CustomSwitchView.OnCheckListener() { // from class: com.movieboxpro.android.view.activity.SettingActivity$initListener$13
            @Override // com.movieboxpro.android.view.widget.CustomSwitchView.OnCheckListener
            public void onCheckChangeListener(boolean z) {
                PrefsUtils.getInstance().putBoolean(Constant.Prefs.REMEMBER_ORG_QUALITY, z);
            }
        });
        getBinding().clDownloadLocation.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$SettingActivity$2BjmTjF_8pxdElZ7-9q3rx4diH4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingActivity.m279initListener$lambda7(SettingActivity.this, view);
            }
        });
        getBinding().llLanguage.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$SettingActivity$f8n7F4XPoy8LoQc8-N84Vg-DZ7Q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingActivity.m280initListener$lambda8(SettingActivity.this, view);
            }
        });
        getBinding().switchPush.setCheckListener(new CustomSwitchView.OnCheckListener() { // from class: com.movieboxpro.android.view.activity.SettingActivity$initListener$16
            @Override // com.movieboxpro.android.view.widget.CustomSwitchView.OnCheckListener
            public void onCheckChangeListener(boolean z) {
                RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.Companion.post("Movie_update_remind_v2").param(IjkMediaMeta.IJKM_KEY_TYPE, "push_notice").param(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(z ? 1 : 0)).asRequest(), SettingActivity.this), new SettingActivity$initListener$16$onCheckChangeListener$1(SettingActivity.this, z), null, new SettingActivity$initListener$16$onCheckChangeListener$2(SettingActivity.this), null, new SettingActivity$initListener$16$onCheckChangeListener$3(SettingActivity.this), 10, null);
            }
        });
        getBinding().switchEmail.setCheckListener(new CustomSwitchView.OnCheckListener() { // from class: com.movieboxpro.android.view.activity.SettingActivity$initListener$17
            @Override // com.movieboxpro.android.view.widget.CustomSwitchView.OnCheckListener
            public void onCheckChangeListener(boolean z) {
                RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.Companion.post("Movie_update_remind_v2").param(IjkMediaMeta.IJKM_KEY_TYPE, "email_notice").param(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(z ? 1 : 0)).asRequest(), SettingActivity.this), new SettingActivity$initListener$17$onCheckChangeListener$1(SettingActivity.this, z), null, new SettingActivity$initListener$17$onCheckChangeListener$2(SettingActivity.this), null, new SettingActivity$initListener$17$onCheckChangeListener$3(SettingActivity.this), 10, null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m272initListener$lambda0(SettingActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SettingActivity settingActivity = this$0;
        settingActivity.startActivity(new Intent(settingActivity, SmartDownloadSettingActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m273initListener$lambda1(SettingActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SettingActivity settingActivity = this$0;
        settingActivity.startActivity(new Intent(settingActivity, AccountActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m274initListener$lambda2(SettingActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-3  reason: not valid java name */
    public static final void m275initListener$lambda3(SettingActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TestSpeedActivity.Companion.start(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-4  reason: not valid java name */
    public static final void m276initListener$lambda4(SettingActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.clearAppCache();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-6  reason: not valid java name */
    public static final void m277initListener$lambda6(final SettingActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        new ListDialog.Builder(this$0).setItems(new String[]{"AUTO", "IJK", "VLC", "EXO"}).addAction(new ListDialog.OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$SettingActivity$Ztvn7jFz2B6DDz_D4vuTZmD-d1M
            @Override // com.movieboxpro.android.view.dialog.ListDialog.OnItemClickListener
            public final void onClick(View view2, int i) {
                SettingActivity.m278initListener$lambda6$lambda5(SettingActivity.this, view2, i);
            }
        }).setTitleVisible(true).create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-6$lambda-5  reason: not valid java name */
    public static final void m278initListener$lambda6$lambda5(SettingActivity this$0, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == 0) {
            this$0.getBinding().tvPlayer.setText("AUTO");
            PrefsUtils.getInstance().putInt(Constant.Prefs.PLAYER_ENGINE, 0);
            CommonExtKt.onMobEvent(this$0, "Setting_Auto_Engine");
            EventUtils.event("设置里切换Auto引擎");
        } else if (i == 1) {
            this$0.getBinding().tvPlayer.setText("IJK");
            PrefsUtils.getInstance().putInt(Constant.Prefs.PLAYER_ENGINE, 1);
            CommonExtKt.onMobEvent(this$0, "Setting_Ijk_Engine");
            EventUtils.event("设置里切换ijk引擎");
        } else if (i == 2) {
            this$0.getBinding().tvPlayer.setText("VLC");
            PrefsUtils.getInstance().putInt(Constant.Prefs.PLAYER_ENGINE, 2);
            CommonExtKt.onMobEvent(this$0, "Setting_vlc_Engine");
            EventUtils.event("设置里切换vlc引擎");
        } else if (i != 3) {
        } else {
            this$0.getBinding().tvPlayer.setText("EXO");
            PrefsUtils.getInstance().putInt(Constant.Prefs.PLAYER_ENGINE, 3);
            CommonExtKt.onMobEvent(this$0, "Setting_exo_Engine");
            EventUtils.event("设置里切换exo引擎");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-7  reason: not valid java name */
    public static final void m279initListener$lambda7(SettingActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ChooseDownloadPathDialog chooseDownloadPathDialog = new ChooseDownloadPathDialog();
        FragmentManager supportFragmentManager = this$0.getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        chooseDownloadPathDialog.show(supportFragmentManager, ChooseDownloadPathDialog.class.getSimpleName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-8  reason: not valid java name */
    public static final void m280initListener$lambda8(SettingActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SwitchLanguageActivity.Companion.start(this$0, 100);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 100) {
            if (StringsKt.equals("zh-cn", App.deviceLang, true)) {
                getBinding().tvLanguage.setText("中文简体");
            } else if (StringsKt.equals("zh-tw", App.deviceLang, true)) {
                getBinding().tvLanguage.setText("中文繁体");
            } else {
                getBinding().tvLanguage.setText(new Locale(App.deviceLang).getDisplayLanguage());
            }
        }
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initData() {
        getBinding().toolBar.tvTitle.setText("Setting");
        App.getInstance().addObserver(this.observer);
        DownloadPathLiveData.Companion.get().observe(this, new androidx.lifecycle.Observer() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$SettingActivity$ObdmJpjqYojiradSuJeW5W8T5d0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                SettingActivity.m271initData$lambda9(SettingActivity.this, (String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-9  reason: not valid java name */
    public static final void m271initData$lambda9(SettingActivity this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.initDownloadPath();
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initView() {
        initStatus();
    }

    private final void initStatus() {
        initUserInfo();
        initDownloadPath();
        int i = PrefsUtils.getInstance().getInt(Constant.Prefs.PLAYER_ENGINE, 0);
        if (i == 0) {
            getBinding().tvPlayer.setText("AUTO");
        } else if (i == 1) {
            getBinding().tvPlayer.setText("Ijk");
        } else if (i == 2) {
            getBinding().tvPlayer.setText("VLC");
        } else if (i == 3) {
            getBinding().tvPlayer.setText("EXO");
        }
        getBinding().switchAutoPlayNext.setChecked(PrefsUtils.getInstance().getBoolean(Constant.Prefs.AUTO_PLAY_NEXT, false));
        getBinding().switchAutoSelectSubtitle.setChecked(PrefsUtils.getInstance().getBoolean(Constant.Prefs.AUTO_SELECT_SUBTITLE, false));
        getBinding().switchRememberOrg.setChecked(PrefsUtils.getInstance().getBoolean(Constant.Prefs.REMEMBER_ORG_QUALITY, false));
        if (StringsKt.equals("zh-cn", App.deviceLang, true)) {
            getBinding().tvLanguage.setText("中文简体");
        } else if (StringsKt.equals("zh-tw", App.deviceLang, true)) {
            getBinding().tvLanguage.setText("中文繁体");
        } else {
            getBinding().tvLanguage.setText(new Locale(App.deviceLang).getDisplayLanguage());
        }
        getBinding().switchHideMovieList.setChecked(PrefsUtils.getInstance().getBoolean(Constant.Prefs.HIDE_MOVIELIST, false));
        getBinding().switchDownloadCellular.setChecked(PrefsUtils.getInstance().getBoolean(Constant.Prefs.USER_CELLUAR_DOWNLOAD, false));
        getBinding().switchFullScreen.setChecked(PrefsUtils.getInstance().getBoolean(Constant.Prefs.FULLSCREEN_PLAY, true));
        getBinding().switchBlack.setChecked(PrefsUtils.getInstance().getBoolean(Constant.Prefs.BLACK_MODE, false));
        getBinding().tvSmartDownload.setText(SettingManager.INSTANCE.getSmartDownload() ? "ON" : "OFF");
        SmartDownloadChangedLiveData.Companion.get().observe(this, new androidx.lifecycle.Observer() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$SettingActivity$8SWW7ve5dpajAVE0HtMqhHEaGxQ
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                SettingActivity.m281initStatus$lambda10(SettingActivity.this, (Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initStatus$lambda-10  reason: not valid java name */
    public static final void m281initStatus$lambda10(SettingActivity this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getBinding().tvSmartDownload.setText(SettingManager.INSTANCE.getSmartDownload() ? "ON" : "OFF");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseBindingActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        App.getInstance().delObserver(this.observer);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onSignOut(LogoutEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initUserInfo() {
        UserModel.UserData userData = App.getUserData();
        Intrinsics.checkNotNullExpressionValue(userData, "getUserData()");
        GlideUtils.load((Activity) this, userData.avatar, (ImageView) getBinding().ivAvatar);
        getBinding().tvNickname.setText(userData.username);
        if (userData.isvip == 1) {
            AppCompatImageView appCompatImageView = getBinding().ivVip;
            Intrinsics.checkNotNullExpressionValue(appCompatImageView, "binding.ivVip");
            CommonExtKt.visible(appCompatImageView);
        } else {
            AppCompatImageView appCompatImageView2 = getBinding().ivVip;
            Intrinsics.checkNotNullExpressionValue(appCompatImageView2, "binding.ivVip");
            CommonExtKt.gone(appCompatImageView2);
        }
        getBinding().switchPush.setChecked(userData.getPush_notice() == 1);
        getBinding().switchEmail.setChecked(userData.getEmail_notice() == 1);
    }

    private final void initDownloadPath() {
        String byte2FitMemorySize;
        String str;
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.INTERNAL_STORAGE, true)) {
            byte2FitMemorySize = FileUtils.byte2FitMemorySize(MemoryUtils.getAvailSpaceSize(this));
            Intrinsics.checkNotNullExpressionValue(byte2FitMemorySize, "byte2FitMemorySize(Memor….getAvailSpaceSize(this))");
            str = "Internal Storage";
        } else {
            byte2FitMemorySize = FileUtils.byte2FitMemorySize(FileUtils.getFsTotalSize(PrefsUtils.getInstance().getString(Constant.Prefs.DOWNLOAD_DIR)));
            Intrinsics.checkNotNullExpressionValue(byte2FitMemorySize, "byte2FitMemorySize(\n    …          )\n            )");
            str = "SD Card";
        }
        TextView textView = getBinding().tvDownloadDirName;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%s", Arrays.copyOf(new Object[]{str}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        textView.setText(format);
        TextView textView2 = getBinding().tvDownloadSpace;
        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
        String format2 = String.format("Free:%s", Arrays.copyOf(new Object[]{byte2FitMemorySize}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
        textView2.setText(format2);
    }

    private final void clearAppCache() {
        ((ObservableSubscribeProxy) io.reactivex.Observable.create(new ObservableOnSubscribe() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$SettingActivity$oK4fB8zQl6OMhQ34n-HVF8BkeFo
            @Override // io.reactivex.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                SettingActivity.m268clearAppCache$lambda13(SettingActivity.this, observableEmitter);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new io.reactivex.Observer<String>() { // from class: com.movieboxpro.android.view.activity.SettingActivity$clearAppCache$2
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable e) {
                Intrinsics.checkNotNullParameter(e, "e");
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
            }

            @Override // io.reactivex.Observer
            public void onNext(String t) {
                Intrinsics.checkNotNullParameter(t, "t");
                ToastUtils.showShort("Cache Empty", new Object[0]);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: clearAppCache$lambda-13  reason: not valid java name */
    public static final void m268clearAppCache$lambda13(SettingActivity this$0, ObservableEmitter emitter) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        GlideUtils.clearImageAllCache(this$0);
        FileUtils.deleteAllInDir(new File(Constant.DIR_CROP));
        FileUtils.deleteAllInDir(new File(Constant.DIR_LOG));
        FileUtils.deleteFilesInDirWithFilter(new File(Constant.DIR_DOWNLOAD), $$Lambda$SettingActivity$3g36Giv3ArrUyvZ27eommrYEhvg.INSTANCE);
        List<Download> all = App.getDB().downloadDao().getAll();
        List<File> listFilesInDirWithFilter = FileUtils.listFilesInDirWithFilter(new File(Constant.DIR_DOWNLOAD), $$Lambda$SettingActivity$XKOwai_VHDgXEsQW9pIBXXMUOI.INSTANCE, false);
        if (listFilesInDirWithFilter != null) {
            for (File file : listFilesInDirWithFilter) {
                String name = file.getName();
                Intrinsics.checkNotNullExpressionValue(name, "file.name");
                Intrinsics.checkNotNullExpressionValue(all, "all");
                if (!this$0.findExist(name, all)) {
                    file.delete();
                }
            }
        }
        emitter.onNext("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: clearAppCache$lambda-13$lambda-11  reason: not valid java name */
    public static final boolean m269clearAppCache$lambda13$lambda11(File pathname) {
        Intrinsics.checkNotNullParameter(pathname, "pathname");
        String extensionName = FileUtils.getExtensionName(pathname.getPath());
        return StringsKt.equals("srt", extensionName, true) || StringsKt.equals("ass", extensionName, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: clearAppCache$lambda-13$lambda-12  reason: not valid java name */
    public static final boolean m270clearAppCache$lambda13$lambda12(File pathname) {
        Intrinsics.checkNotNullParameter(pathname, "pathname");
        return StringsKt.equals("mp4", FileUtils.getExtensionName(pathname.getPath()), true);
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x000a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final boolean findExist(java.lang.String r4, java.util.List<? extends com.movieboxpro.android.db.entity.Download> r5) {
        /*
            r3 = this;
            java.util.Iterator r5 = r5.iterator()
        L4:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L30
            java.lang.Object r0 = r5.next()
            com.movieboxpro.android.db.entity.Download r0 = (com.movieboxpro.android.db.entity.Download) r0
            java.lang.String r1 = r0.getFileName()
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r1)
            if (r1 != 0) goto L2e
            java.lang.String r1 = r0.getPath()
            java.lang.String r0 = r0.getTitle()
            java.lang.String r2 = com.movieboxpro.android.app.Constant.DIR_DOWNLOAD
            java.lang.String r0 = com.ares.downloader.jarvis.core.RemoteFileUtil.getFileName(r1, r0, r2)
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r0)
            if (r0 == 0) goto L4
        L2e:
            r4 = 1
            return r4
        L30:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.view.activity.SettingActivity.findExist(java.lang.String, java.util.List):boolean");
    }
}
