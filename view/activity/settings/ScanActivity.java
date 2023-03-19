package com.movieboxpro.android.view.activity.settings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.core.app.NotificationCompat;
import butterknife.BindView;
import com.alibaba.fastjson.JSONObject;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.movieboxpro.android.BuildConfig;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.app.RxManager;
import com.movieboxpro.android.base.BaseActivity;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.utils.EventUtils;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.view.activity.user.QrCodeActivity;
import com.movieboxpro.android.view.dialog.ChildModeHintDialog;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
/* loaded from: classes3.dex */
public class ScanActivity extends BaseActivity {
    @BindView(R.id.activity_scan)
    WebView activityScan;

    @Override // com.movieboxpro.android.base.BaseActivity
    protected int getStatusTintColor() {
        return R.color.color_main;
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, ScanActivity.class));
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public View loadView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.activity_scan, (ViewGroup) null);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initView() {
        setTitle("How to Use Tv ver");
        setTitleBack(true);
        setTitleRightIcon(R.drawable.ic_scan, new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$ScanActivity$6UrZrLA06yYCHN5kfAdiKvU2mJQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ScanActivity.this.lambda$initView$0$ScanActivity(view);
            }
        });
    }

    public /* synthetic */ void lambda$initView$0$ScanActivity(View view) {
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.CHILD_MODE, false)) {
            new ChildModeHintDialog(this).show();
            return;
        }
        EventUtils.event("点击扫描按钮");
        new IntentIntegrator(this.activity).setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES).setPrompt("Please aim at QR code").setCameraId(0).setBeepEnabled(true).setCaptureActivity(QrCodeActivity.class).initiateScan();
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initData() {
        this.activityScan.loadUrl("https://www.movieboxpro.app/index/index/android_tv");
        this.activityScan.getSettings().setJavaScriptEnabled(true);
        this.activityScan.getSettings().setSupportZoom(true);
        this.activityScan.getSettings().setBuiltInZoomControls(true);
        this.activityScan.getSettings().setUseWideViewPort(true);
        this.activityScan.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        this.activityScan.getSettings().setLoadWithOverviewMode(true);
        this.activityScan.setWebViewClient(new WebViewClient() { // from class: com.movieboxpro.android.view.activity.settings.ScanActivity.1
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                SystemUtils.startBrowser((Activity) ScanActivity.this, "https://www.movieboxpro.app/index/index/android_tv");
                return true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        String str = this.TAG;
        MLog.d(str, "收到" + i + i2);
        if (i2 == -1 && i == 49374 && App.isLogin()) {
            IntentResult parseActivityResult = IntentIntegrator.parseActivityResult(i, i2, intent);
            String str2 = this.TAG;
            MLog.d(str2, "扫码结果：" + parseActivityResult.getContents());
            if (parseActivityResult.getContents() == null) {
                showToast("Fail");
                return;
            }
            String contents = parseActivityResult.getContents();
            String str3 = this.TAG;
            MLog.d(str3, "扫码结果：" + contents);
            scanedResult(contents);
        }
    }

    public void scanedResult(String str) {
        if (Network.isConnected(this.context)) {
            ((ObservableSubscribeProxy) this.service.Scan_qrcode(API.BASE_URL, API.Common.SCANQRCODE, App.getUserData().uid_v2, str, BuildConfig.VERSION_NAME).subscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.settings.ScanActivity.2
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onError(Throwable th) {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable disposable) {
                    RxManager.addDisposable(ScanActivity.this.TAG, disposable);
                }

                @Override // io.reactivex.Observer
                public void onNext(String str2) {
                    ScanActivity.this.showToast(((JSONObject) JSONObject.parse(str2)).getString(NotificationCompat.CATEGORY_MESSAGE));
                }
            });
        }
    }
}
