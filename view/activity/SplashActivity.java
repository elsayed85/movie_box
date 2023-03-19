package com.movieboxpro.android.view.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import butterknife.BindView;
import com.gyf.immersionbar.ImmersionBar;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseActivity;
import com.movieboxpro.android.config.ConfigKey;
import com.movieboxpro.android.config.ConfigUtils;
import com.movieboxpro.android.event.ConfigReceiveEvent;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.PrefsUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* loaded from: classes.dex */
public class SplashActivity extends BaseActivity {
    public static boolean AD_LOAD = false;
    @BindView(R.id.adContainer)
    FrameLayout adContainer;
    private Animation operatingAnim;
    @BindView(R.id.splash_bottom)
    ImageView splashBottom;
    @BindView(R.id.splash_top)
    ImageView splashTop;

    private void printSystemInfo() {
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
    protected boolean isTitleBarShow() {
        return false;
    }

    @Override // com.movieboxpro.android.base.BaseActivity
    protected boolean shouldLoadSplashAd() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ImmersionBar.with(this).fullScreen(true).transparentNavigationBar().init();
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public View loadView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.activity_splash, viewGroup, false);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initView() {
        setTitleBar(false);
        this.operatingAnim = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initData() {
        if (!isTaskRoot()) {
            Intent intent = getIntent();
            String action = intent.getAction();
            handlePushParam();
            if (intent.hasCategory("android.intent.category.LAUNCHER") && "android.intent.action.MAIN".equals(action)) {
                finish();
                return;
            }
        }
        checkOnlineParams();
    }

    private void handlePushParam() {
        Bundle extras = getIntent().getExtras();
        if (extras == null || TextUtils.isEmpty(extras.getString(IjkMediaMeta.IJKM_KEY_TYPE))) {
            return;
        }
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtras(extras);
        startActivity(intent);
    }

    private void checkOnlineParams() {
        if (!Network.isConnected(this.context)) {
            MLog.d(this.TAG, "onResume: no network");
            if (!ConfigUtils.containsConfig(ConfigKey.SHOW_NEWS_MODULE)) {
                String format = String.format("%s_%s_%s", ConfigKey.SHOW_NEWS_MODULE, App.channel, App.versionName);
                if (!App.channel.equals("reader")) {
                    ConfigUtils.writeIntgerConfig(format, 0);
                } else {
                    ConfigUtils.writeIntgerConfig(format, 1);
                }
            }
            onNext();
        } else if (ConfigUtils.containsConfig("url")) {
            MLog.d(this.TAG, "onResume: next");
            onNext();
        } else {
            MLog.d(this.TAG, "onResume: update online");
            this.app.updateOnline();
        }
    }

    public void onNext() {
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.IS_FIRST_OPEN, true)) {
            this.mRoot.postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.activity.SplashActivity.1
                @Override // java.lang.Runnable
                public void run() {
                    SplashActivity.this.showAnimal();
                }
            }, 200L);
            return;
        }
        int readIntBoolConfig = ConfigUtils.readIntBoolConfig(ConfigKey.SHOW_SPLASH_ADVERT);
        int i = App.isLogin() ? App.getUserData().isvip : 0;
        if (App.isLogin() && i == 0 && readIntBoolConfig == 1) {
            loadsplash();
        } else {
            this.mRoot.postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.activity.SplashActivity.2
                @Override // java.lang.Runnable
                public void run() {
                    SplashActivity.this.showAnimal();
                }
            }, 200L);
        }
    }

    private void loadsplash() {
        this.mRoot.postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.activity.SplashActivity.3
            @Override // java.lang.Runnable
            public void run() {
                SplashActivity.this.onAccepted();
            }
        }, 200L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAccepted() {
        this.app.readOnline();
        printSystemInfo();
        toMainOrLogin();
    }

    private void toMainOrLogin() {
        EventBus.getDefault().unregister(this);
        Bundle extras = getIntent().getExtras();
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.SUPER_CHILD_MODE, false)) {
            startActivity(new Intent(this, SuperChildModeMainActivity.class));
            finish();
            return;
        }
        Intent intent = new Intent(this, MainActivity.class);
        if (extras != null) {
            intent.putExtras(extras);
        }
        intent.setAction(getIntent().getAction());
        startActivity(intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAnimal() {
        this.operatingAnim.setInterpolator(new LinearInterpolator());
        Animation animation = this.operatingAnim;
        if (animation != null) {
            this.splashTop.startAnimation(animation);
            this.operatingAnim.setAnimationListener(new Animation.AnimationListener() { // from class: com.movieboxpro.android.view.activity.SplashActivity.4
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation2) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation2) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation2) {
                    SplashActivity.this.mRoot.postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.activity.SplashActivity.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            SplashActivity.this.onAccepted();
                        }
                    }, 200L);
                }
            });
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.operatingAnim == null) {
            this.mRoot.postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.activity.SplashActivity.5
                @Override // java.lang.Runnable
                public void run() {
                    SplashActivity.this.onAccepted();
                }
            }, 200L);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        this.splashTop.clearAnimation();
        super.onPause();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return i == 4 || super.onKeyDown(i, keyEvent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onConfigReceive(ConfigReceiveEvent configReceiveEvent) {
        MLog.d(this.TAG, "onConfigReceive");
        onNext();
    }
}
