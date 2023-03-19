package com.movieboxpro.android.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.gyf.immersionbar.ImmersionBar;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.PrefsUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;
/* compiled from: BaseSimpleActivity.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0014J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\nH\u0014J\b\u0010\f\u001a\u00020\bH\u0014J\b\u0010\r\u001a\u00020\bH&J\b\u0010\u000e\u001a\u00020\bH&J\b\u0010\u000f\u001a\u00020\bH&J\b\u0010\u0010\u001a\u00020\u0006H\u0014J\b\u0010\u0011\u001a\u00020\u0006H\u0014J\b\u0010\u0012\u001a\u00020\u0006H\u0014J\u0012\u0010\u0013\u001a\u00020\b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\b\u0010\u0016\u001a\u00020\bH\u0014J\b\u0010\u0017\u001a\u00020\bH\u0014J\b\u0010\u0018\u001a\u00020\bH\u0014J\b\u0010\u0019\u001a\u00020\bH\u0014J\b\u0010\u001a\u001a\u00020\bH\u0014J\u0010\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u0006H\u0014R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/movieboxpro/android/base/BaseSimpleActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "kProgressHUD", "Lcom/kaopiz/kprogresshud/KProgressHUD;", "enableEventBus", "", "finish", "", "getLayoutResId", "", "getStatusColor", "hideLoadingView", "initData", "initListener", "initView", "needFullscreen", "needImmersionBar", "needLeftInTransition", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "onResume", "onStart", "showLoadingView", "startActivity", "intent", "Landroid/content/Intent;", "useSkin", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class BaseSimpleActivity extends AppCompatActivity {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private KProgressHUD kProgressHUD;

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

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

    protected boolean enableEventBus() {
        return false;
    }

    public abstract int getLayoutResId();

    public abstract void initData();

    public abstract void initListener();

    public abstract void initView();

    protected boolean needFullscreen() {
        return false;
    }

    protected boolean needImmersionBar() {
        return true;
    }

    protected boolean needLeftInTransition() {
        return true;
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
        setContentView(View.inflate(this, getLayoutResId(), null));
        if (needImmersionBar()) {
            if (needFullscreen()) {
                ImmersionBar.with(this).autoDarkModeEnable(true).transparentNavigationBar().transparentStatusBar().init();
            } else {
                CommonExtKt.initImmersionBar(this, getStatusColor());
            }
        }
        initView();
        initData();
        initListener();
    }

    protected int getStatusColor() {
        return PrefsUtils.getInstance().getBoolean(Constant.Prefs.BLACK_MODE) ? R.color.color_main_back_black : R.color.color_main_back;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void hideLoadingView() {
        if (isFinishing()) {
            return;
        }
        if (this.kProgressHUD == null) {
            this.kProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(1).setDimAmount(0.0f);
        }
        KProgressHUD kProgressHUD = this.kProgressHUD;
        if (kProgressHUD == null) {
            return;
        }
        kProgressHUD.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showLoadingView() {
        if (isFinishing()) {
            return;
        }
        if (this.kProgressHUD == null) {
            this.kProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(1).setDimAmount(0.0f);
        }
        KProgressHUD kProgressHUD = this.kProgressHUD;
        if (kProgressHUD == null) {
            return;
        }
        kProgressHUD.show();
    }

    /* JADX INFO: Access modifiers changed from: protected */
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

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.startActivity(intent);
        if (needLeftInTransition()) {
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        if (needLeftInTransition()) {
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (enableEventBus() && EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
