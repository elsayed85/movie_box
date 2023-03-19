package com.movieboxpro.android.base.mvp;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import butterknife.ButterKnife;
import com.gyf.immersionbar.ImmersionBar;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.mvp.BaseContract;
import com.movieboxpro.android.base.mvp.BaseContract.BasePresenter;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.view.widget.loadcallback.EmptyCallbackView;
import com.movieboxpro.android.view.widget.loadcallback.ErrorCallbackView;
import com.movieboxpro.android.view.widget.loadcallback.LoadingCallbackView;
import org.greenrobot.eventbus.EventBus;
/* loaded from: classes.dex */
public abstract class BaseMvpActivity<T extends BaseContract.BasePresenter> extends AppCompatActivity implements BaseContract.BaseView {
    private KProgressHUD kProgressHUD;
    private LoadService loadService;
    protected T mPresenter;

    protected abstract T bindPresenter();

    protected boolean enableEventBus() {
        return false;
    }

    protected abstract int getLayoutResId();

    protected abstract void initData();

    protected abstract void initListener();

    protected abstract void initView();

    protected boolean isErrorPageEnable() {
        return false;
    }

    protected boolean isFullScreen() {
        return false;
    }

    protected boolean isNeedLoadData() {
        return true;
    }

    protected abstract void requestData();

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void showError(String str) {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void showFinish(String str) {
    }

    protected boolean useSkin() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.BLACK_MODE)) {
            setTheme((int) R.style.MainBlackAppTheme);
        }
        initStatusBar();
        View inflate = View.inflate(this, getLayoutResId(), null);
        setContentView(inflate);
        ButterKnife.bind(this);
        this.mPresenter = bindPresenter();
        attachView();
        initLoadSir(inflate);
        initData();
        initView();
        initListener();
        if (isNeedLoadData()) {
            if (Network.isConnected(this)) {
                requestData();
                return;
            } else {
                this.loadService.showCallback(ErrorCallbackView.class);
                return;
            }
        }
        this.loadService.showSuccess();
    }

    private void initLoadSir(View view) {
        this.loadService = LoadSir.getDefault().register(view, new Callback.OnReloadListener() { // from class: com.movieboxpro.android.base.mvp.BaseMvpActivity.1
            @Override // com.kingja.loadsir.callback.Callback.OnReloadListener
            public void onReload(View view2) {
                BaseMvpActivity.this.requestData();
            }
        });
    }

    protected int getStatusColor() {
        return PrefsUtils.getInstance().getBoolean(Constant.Prefs.BLACK_MODE) ? R.color.color_main_back_black : R.color.color_main_back;
    }

    private void initStatusBar() {
        if (isFullScreen()) {
            ImmersionBar.with(this).autoDarkModeEnable(true).transparentStatusBar().transparentNavigationBar().init();
        } else {
            ImmersionBar.with(this).fitsSystemWindows(true).statusBarColor(getStatusColor()).navigationBarColor(getStatusColor()).init();
        }
    }

    private void attachView() {
        T t = this.mPresenter;
        if (t != null) {
            t.attachView(this);
        }
    }

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void showLoading() {
        this.loadService.showCallback(LoadingCallbackView.class);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void showLoadingView() {
        if (isFinishing()) {
            return;
        }
        if (this.kProgressHUD == null) {
            this.kProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(1).setDimAmount(0.0f).show();
        }
        this.kProgressHUD.show();
    }

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void hideLoading() {
        this.loadService.showSuccess();
    }

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void hideLoadingView() {
        if (isFinishing()) {
            return;
        }
        if (this.kProgressHUD == null) {
            this.kProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(1).setDimAmount(0.0f).show();
        }
        this.kProgressHUD.dismiss();
    }

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void showErrorPage() {
        this.loadService.showCallback(ErrorCallbackView.class);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void showEmptyState() {
        this.loadService.showCallback(EmptyCallbackView.class);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        if (!enableEventBus() || EventBus.getDefault().isRegistered(this)) {
            return;
        }
        EventBus.getDefault().register(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        if (enableEventBus() && EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        detachView();
        super.onDestroy();
    }

    private void detachView() {
        T t = this.mPresenter;
        if (t != null) {
            t.detachView();
        }
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    protected void route(Class<? extends Activity> cls, Bundle bundle, View view, String str) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (Build.VERSION.SDK_INT > 21) {
            ActivityCompat.startActivity(this, intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this, Pair.create(view, str)).toBundle());
        } else {
            startActivity(intent);
        }
    }
}
