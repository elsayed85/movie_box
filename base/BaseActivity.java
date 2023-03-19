package com.movieboxpro.android.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import butterknife.ButterKnife;
import com.google.android.exoplayer2.PlaybackException;
import com.gyf.immersionbar.ImmersionBar;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.impl.LoadingPopupView;
import com.lxj.xpopup.interfaces.XPopupCallback;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.config.ConfigKey;
import com.movieboxpro.android.config.ConfigUtils;
import com.movieboxpro.android.event.UrlChangeEvent;
import com.movieboxpro.android.http.APIService;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.presenter.IPresenter;
import com.movieboxpro.android.utils.InputMethodUtils;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.utils.Toastor;
import com.movieboxpro.android.view.dialog.NoInternetDialog;
import com.movieboxpro.android.view.listener.IFragmentController;
import com.movieboxpro.android.view.listener.ITitleController;
import com.movieboxpro.android.view.listener.IViewController;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Stack;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
/* loaded from: classes.dex */
public abstract class BaseActivity extends AppCompatActivity implements IViewController, ITitleController, IFragmentController {
    public String TAG;
    protected Activity activity;
    protected App app;
    protected Context appContext;
    protected Context context;
    protected AlertDialog dialog;
    protected LoadingPopupView loadingPopupView;
    protected FrameLayout mAdContainer;
    protected FrameLayout mContent;
    protected FrameLayout mFragment;
    protected View mRoot;
    protected View mTitleBar;
    protected ImageView mTitleLeftIconImg;
    protected TextView mTitleLeftText;
    protected RelativeLayout mTitleRightIcon;
    protected ImageView mTitleRightIconDot;
    protected ImageView mTitleRightIconImg;
    protected TextView mTitleRightText;
    protected TextView mTitleText;
    private Toastor mToastor;
    protected IPresenter presenter;
    protected Bundle savedInstanceState;
    protected APIService service;
    protected Stack<String> tags = new Stack<>();
    protected Boolean isStatusBarTint = true;
    private boolean hasLoadingHide = false;

    protected boolean isDialogStyle() {
        return false;
    }

    protected boolean isFullScreen() {
        return false;
    }

    protected boolean isRequireNetWork() {
        return true;
    }

    protected boolean isShowWarning() {
        return true;
    }

    protected boolean isStatusBarTint() {
        return true;
    }

    protected boolean isSupportBack() {
        return true;
    }

    protected boolean isTitleBarShow() {
        return true;
    }

    protected void onTryAgainClick() {
    }

    protected boolean useSkin() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.BLACK_MODE)) {
            setTheme(R.style.MainBlackAppTheme);
        }
        this.savedInstanceState = bundle;
        String simpleName = getClass().getSimpleName();
        this.TAG = simpleName;
        MLog.d(simpleName, "onCreate");
        initContext();
        if (!Network.isConnected(this.context)) {
            if (isShowWarning()) {
                MLog.d(this.TAG, "onCreate: showWarning");
                showToast("NetWork Fail");
            }
            if (isRequireNetWork()) {
                finish();
                return;
            }
        }
        if (!isDialogStyle()) {
            initBaseLayout();
            initTitleBar();
            initStatusTint();
            View loadView = loadView(getLayoutInflater(), this.mContent);
            if (loadView != null) {
                this.mContent.addView(loadView);
            }
        } else {
            MLog.d(this.TAG, "wuqi!!!");
            View loadView2 = loadView(getLayoutInflater(), null);
            if (loadView2 != null) {
                setContentView(loadView2);
            }
        }
        ButterKnife.bind(this);
        initPresenter();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        this.service = Http.getService();
        initView();
        initData();
    }

    private void initContext() {
        this.app = App.getInstance();
        this.appContext = App.getContext();
        this.context = this;
        this.activity = this;
    }

    private void initBaseLayout() {
        setContentView(R.layout.activity_base);
        this.mRoot = findViewById(R.id.base_root);
        this.mTitleBar = findViewById(R.id.activity_base_title);
        this.mTitleLeftText = (TextView) findViewById(R.id.titlebar_left_text);
        this.mTitleLeftIconImg = (ImageView) findViewById(R.id.titlebar_left_icon_img);
        this.mTitleText = (TextView) findViewById(R.id.titlebar_title_text);
        this.mTitleRightText = (TextView) findViewById(R.id.titlebar_right_text);
        this.mTitleRightIcon = (RelativeLayout) findViewById(R.id.titlebar_right_icon);
        this.mTitleRightIconImg = (ImageView) findViewById(R.id.titlebar_right_icon_img);
        this.mTitleRightIconDot = (ImageView) findViewById(R.id.titlebar_right_icon_dot);
        this.mContent = (FrameLayout) findViewById(R.id.activity_base_content);
        this.mFragment = (FrameLayout) findViewById(R.id.activity_base_fragment);
        this.mAdContainer = (FrameLayout) findViewById(R.id.splash_ad_container);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public LoadingPopupView getLoadingPopupView(XPopupCallback xPopupCallback) {
        if (this.loadingPopupView == null) {
            this.loadingPopupView = new XPopup.Builder(this).dismissOnBackPressed(true).dismissOnTouchOutside(false).setPopupCallback(xPopupCallback).hasShadowBg(true).asLoading("");
        }
        return this.loadingPopupView;
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void setTitleBar(boolean z) {
        if (z) {
            this.mTitleBar.setVisibility(0);
            getWindow().setFeatureInt(7, R.layout.layout_title_bar);
            return;
        }
        this.mTitleBar.setVisibility(8);
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void setTitle(String str, View.OnClickListener onClickListener) {
        this.mTitleText.setVisibility(0);
        this.mTitleText.setText(str);
        if (onClickListener != null) {
            this.mTitleText.setClickable(true);
            this.mTitleText.setOnClickListener(onClickListener);
            return;
        }
        this.mTitleText.setClickable(false);
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void setTitle(String str) {
        setTitle(str, null);
    }

    public void setTitleBack(View.OnClickListener onClickListener, boolean z) {
        if (z) {
            this.mTitleLeftText.setVisibility(8);
            this.mTitleLeftIconImg.setVisibility(0);
            this.mTitleLeftIconImg.setImageResource(R.drawable.selector_titlebar_back);
            if (onClickListener != null) {
                this.mTitleLeftIconImg.setOnClickListener(onClickListener);
                return;
            } else {
                this.mTitleLeftIconImg.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.base.BaseActivity.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        BaseActivity.this.finish();
                    }
                });
                return;
            }
        }
        this.mTitleLeftIconImg.setVisibility(8);
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void setTitleBack(boolean z) {
        setTitleBack(null, z);
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void setTitleBackground(int i) {
        this.mTitleBar.setBackgroundColor(i);
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void setTitleLeftText(String str, View.OnClickListener onClickListener) {
        if (isTitleBarShow()) {
            if (str != null) {
                this.mTitleLeftIconImg.setVisibility(8);
                this.mTitleLeftText.setVisibility(0);
                this.mTitleLeftText.setText(str);
                if (onClickListener != null) {
                    this.mTitleLeftText.setClickable(true);
                    this.mTitleLeftText.setOnClickListener(onClickListener);
                    return;
                }
                return;
            }
            this.mTitleLeftText.setVisibility(8);
        }
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void setTitleLeftIcon(int i, View.OnClickListener onClickListener) {
        this.mTitleLeftIconImg.setVisibility(8);
        this.mTitleLeftIconImg.setVisibility(0);
        this.mTitleLeftIconImg.setImageResource(i);
        if (onClickListener != null) {
            this.mTitleLeftIconImg.setClickable(true);
            this.mTitleLeftIconImg.setOnClickListener(onClickListener);
            return;
        }
        this.mTitleLeftIconImg.setClickable(false);
    }

    public void setTitleRightText(String str) {
        setTitleRightText(str, false, null);
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void setTitleRightText(String str, boolean z, View.OnClickListener onClickListener) {
        this.mTitleRightIcon.setVisibility(8);
        if (TextUtils.isEmpty(str)) {
            this.mTitleRightText.setVisibility(8);
            return;
        }
        this.mTitleRightText.setVisibility(0);
        this.mTitleRightText.setText(str);
        if (z) {
            this.mTitleRightText.setBackgroundResource(R.drawable.bg_border_btn_white);
        } else {
            this.mTitleRightText.setBackgroundResource(R.drawable.bg_null);
        }
        if (onClickListener != null) {
            this.mTitleRightText.setClickable(true);
            this.mTitleRightText.setOnClickListener(onClickListener);
            return;
        }
        this.mTitleRightText.setClickable(false);
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void setTitleRightText(String str, View.OnClickListener onClickListener) {
        setTitleRightText(str, false, onClickListener);
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void setTitleRightIcon(int i, boolean z, View.OnClickListener onClickListener) {
        this.mTitleRightText.setVisibility(8);
        this.mTitleRightIcon.setVisibility(0);
        this.mTitleRightIconImg.setVisibility(0);
        this.mTitleRightIconImg.setImageResource(i);
        if (z) {
            this.mTitleRightIconDot.setVisibility(0);
        } else {
            this.mTitleRightIconDot.setVisibility(8);
        }
        if (onClickListener != null) {
            this.mTitleRightIcon.setClickable(true);
            this.mTitleRightIcon.setOnClickListener(onClickListener);
            return;
        }
        this.mTitleRightIcon.setClickable(false);
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void setTitleRightIcon(int i, View.OnClickListener onClickListener) {
        setTitleRightIcon(i, false, onClickListener);
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void clearTitleRightNew() {
        this.mTitleRightIconDot.setVisibility(8);
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void clearTitleRight() {
        this.mTitleRightText.setVisibility(8);
        this.mTitleRightIcon.setVisibility(8);
    }

    private void initTitleBar() {
        if (isTitleBarShow()) {
            setTitleBar(true);
            if (isSupportBack()) {
                setTitleBack(true);
                return;
            } else {
                setTitleBack(false);
                return;
            }
        }
        setTitleBar(false);
    }

    private void initStatusTint() {
        if (isStatusBarTint()) {
            if (isFullScreen()) {
                ImmersionBar.with(this).autoDarkModeEnable(true).transparentStatusBar().transparentNavigationBar().init();
            } else {
                ImmersionBar.with(this).statusBarColor(getStatusTintColor()).navigationBarColor(R.color.color_main).fitsSystemWindows(true).init();
            }
        }
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initPresenter() {
        MLog.d(this.TAG, "initPresenter");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        MLog.d(this.TAG, "onStart");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        MLog.d(this.TAG, "onNewIntent");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        MLog.d(this.TAG, "onResume");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        MLog.d(this.TAG, "onDownloadPause");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        MLog.d(this.TAG, "onStop");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        MLog.d(this.TAG, "onDestroy");
        LoadingPopupView loadingPopupView = this.loadingPopupView;
        if (loadingPopupView != null && loadingPopupView.isShow()) {
            this.loadingPopupView.dismiss();
            this.loadingPopupView = null;
        }
        AlertDialog alertDialog = this.dialog;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.dialog.dismiss();
        }
        hideLoading(false);
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        IPresenter iPresenter = this.presenter;
        if (iPresenter != null) {
            iPresenter.detachView();
        }
        super.onDestroy();
    }

    @Override // com.movieboxpro.android.view.listener.IFragmentController
    public void onFragmentStart(String str) {
        String str2 = this.TAG;
        MLog.d(str2, "onFragmentStart: " + str);
    }

    @Override // com.movieboxpro.android.view.listener.IFragmentController
    public void onFragmentFinish(String str) {
        String str2 = this.TAG;
        MLog.d(str2, "onFragmentFinish: " + str);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public <T> void updateView(T t) {
        MLog.d(this.TAG, "updateView");
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void showLoading() {
        LoadingPopupView loadingPopupView = this.loadingPopupView;
        if (loadingPopupView == null || !loadingPopupView.isShow()) {
            LoadingPopupView loadingPopupView2 = this.loadingPopupView;
            if (loadingPopupView2 == null || !loadingPopupView2.isShow()) {
                if (this.loadingPopupView == null) {
                    this.loadingPopupView = new XPopup.Builder(this).dismissOnBackPressed(true).dismissOnTouchOutside(false).hasShadowBg(true).asLoading("");
                }
                this.loadingPopupView.show();
            }
        }
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void hideLoading() {
        hideLoading(true);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void hideLoading(boolean z) {
        LoadingPopupView loadingPopupView = this.loadingPopupView;
        if (loadingPopupView != null) {
            loadingPopupView.smartDismiss();
        }
    }

    public void videHideloading() {
        LoadingPopupView loadingPopupView = this.loadingPopupView;
        if (loadingPopupView != null) {
            loadingPopupView.smartDismiss();
        }
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void showNoInternetDialog() {
        new NoInternetDialog(this, new NoInternetDialog.OnButtonClickListener() { // from class: com.movieboxpro.android.base.BaseActivity.2
            @Override // com.movieboxpro.android.view.dialog.NoInternetDialog.OnButtonClickListener
            public void onClick() {
                BaseActivity.this.onTryAgainClick();
            }
        }).show();
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void showToast(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ToastUtils.showShort(str);
    }

    public void showToast(int i) {
        showToast(getString(i));
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void showAlert(String str) {
        new com.adorkable.iosdialog.AlertDialog(this.context).builder().setTitle("Note").setMsg(str).setNegativeButton("OK", new View.OnClickListener() { // from class: com.movieboxpro.android.base.BaseActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        }).setCancelable(false).show();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        MLog.d(this.TAG, "onActivityResult");
    }

    protected boolean shouldLoadSplashAd() {
        if (!ConfigUtils.readBooleanConfig(ConfigKey.SHOW_SPLASH_AD, true)) {
            MLog.d(this.TAG, "shouldLoadSplashAd: not show ad");
            return false;
        }
        int minutesSinceLastBack = TimeUtils.minutesSinceLastBack(PrefsUtils.getInstance().getLong(Constant.Prefs.ENTER_BACK_TIME, 0L));
        int readIntegerConfig = ConfigUtils.readIntegerConfig(ConfigKey.SPLASH_AD_INTERVAL, 10) * PlaybackException.ERROR_CODE_DRM_UNSPECIFIED;
        if (App.isDebug) {
            readIntegerConfig = 1;
        }
        MLog.d(this.TAG, String.format(Locale.CHINA, "shouldLoadSplashAd: minutes: %d,  interva: %d", Integer.valueOf(minutesSinceLastBack), Integer.valueOf(readIntegerConfig)));
        return minutesSinceLastBack >= readIntegerConfig;
    }

    protected int getStatusTintColor() {
        return PrefsUtils.getInstance().getBoolean(Constant.Prefs.BLACK_MODE) ? R.color.color_main_back_black : R.color.color_main_back;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isScreenPortrait() {
        return getResources().getConfiguration().orientation == 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isScreenLandscape() {
        return getResources().getConfiguration().orientation == 2;
    }

    @Override // android.app.Activity
    public void finish() {
        if (this.tags.isEmpty()) {
            super.finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        } else {
            popFragment();
        }
        if (InputMethodUtils.isActive(this.context)) {
            InputMethodUtils.hideSoftInput(this);
        }
    }

    protected void popFragment() {
        if (this.tags.isEmpty()) {
            return;
        }
        String pop = this.tags.pop();
        getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentByTag(pop)).commit();
        onFragmentFinish(pop);
    }

    protected void setResult(int i, String str, int i2) {
        if (str == null) {
            return;
        }
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt(str, i2);
        intent.putExtras(bundle);
        setResult(i, intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setResult(int i, String str, String str2) {
        if (str == null || str2 == null) {
            return;
        }
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString(str, str2);
        intent.putExtras(bundle);
        setResult(i, intent);
        finish();
    }

    protected void setResult(int i, String str, Serializable serializable) {
        if (str == null || serializable == null) {
            return;
        }
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable(str, serializable);
        intent.putExtras(bundle);
        setResult(i, intent);
        finish();
    }

    protected int getIntResult(Intent intent, String str) {
        Bundle extras;
        if (intent == null || str == null || (extras = intent.getExtras()) == null) {
            return 0;
        }
        return extras.getInt(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getStringResult(Intent intent, String str) {
        Bundle extras;
        if (intent == null || str == null || (extras = intent.getExtras()) == null) {
            return null;
        }
        return extras.getString(str);
    }

    protected Serializable getResult(Intent intent, String str) {
        Bundle extras;
        if (intent == null || str == null || (extras = intent.getExtras()) == null) {
            return null;
        }
        return extras.getSerializable(str);
    }

    public void route(Class<? extends Activity> cls) {
        route(cls, (Bundle) null);
    }

    public void route(Class<? extends Activity> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void route(Class<? extends Activity> cls, int i) {
        route(cls, null, i);
    }

    public void route(Class<? extends Activity> cls, Bundle bundle, int i) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, i);
    }

    public void route(BaseFragment baseFragment) {
        if (baseFragment != null) {
            String name = baseFragment.getName();
            this.tags.push(name);
            getSupportFragmentManager().beginTransaction().add(R.id.activity_base_fragment, baseFragment, name).addToBackStack(name).commit();
            onFragmentStart(name);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void route(Class<? extends Activity> cls, Bundle bundle, View view, String str) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (Build.VERSION.SDK_INT > 21) {
            ActivityCompat.startActivity(this, intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this.activity, Pair.create(view, str)).toBundle());
            return;
        }
        startActivity(intent);
    }

    protected boolean getBooleanParam(String str, boolean z) {
        Bundle extras = getIntent().getExtras();
        return extras != null ? extras.getBoolean(str, z) : z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getStringParam(String str, String str2) {
        Bundle extras = getIntent().getExtras();
        return extras != null ? extras.getString(str, str2) : str2;
    }

    protected int getIntParam(String str, int i) {
        Bundle extras = getIntent().getExtras();
        return extras != null ? extras.getInt(str, i) : i;
    }

    protected double getDoubleParam(String str, double d) {
        Bundle extras = getIntent().getExtras();
        return extras != null ? extras.getDouble(str, d) : d;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public <T extends Serializable> T getSerializable(String str, T t) {
        T t2;
        Bundle extras = getIntent().getExtras();
        return (extras == null || (t2 = (T) extras.getSerializable(str)) == null) ? t : t2;
    }

    protected <T extends Parcelable> T getParcelable(String str, T t) {
        T t2;
        Bundle extras = getIntent().getExtras();
        return (extras == null || (t2 = (T) extras.getParcelable(str)) == null) ? t : t2;
    }

    protected List<? extends Serializable> getListParam(String str) {
        Serializable serializable;
        Bundle extras = getIntent().getExtras();
        if (extras == null || (serializable = extras.getSerializable(str)) == null) {
            return null;
        }
        return (List) serializable;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UrlChangeEvent urlChangeEvent) {
        this.service = Http.getService();
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
