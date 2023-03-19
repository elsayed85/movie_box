package com.movieboxpro.android.view.activity.user;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.BaseActivity;
/* loaded from: classes3.dex */
public class QrCodeActivity extends BaseActivity implements DecoratedBarcodeView.TorchListener {
    private CaptureManager captureManager;
    private DecoratedBarcodeView decoratedBarcodeView;

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initData() {
    }

    @Override // com.movieboxpro.android.base.BaseActivity
    protected boolean isFullScreen() {
        return true;
    }

    @Override // com.journeyapps.barcodescanner.DecoratedBarcodeView.TorchListener
    public void onTorchOff() {
    }

    @Override // com.journeyapps.barcodescanner.DecoratedBarcodeView.TorchListener
    public void onTorchOn() {
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public View loadView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.activity_qr_code, viewGroup, false);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initView() {
        setTitleBar(false);
        DecoratedBarcodeView decoratedBarcodeView = (DecoratedBarcodeView) findViewById(R.id.decoratedBarcodeView);
        this.decoratedBarcodeView = decoratedBarcodeView;
        CaptureManager captureManager = new CaptureManager(this, decoratedBarcodeView);
        this.captureManager = captureManager;
        captureManager.initializeFromIntent(getIntent(), this.savedInstanceState);
        this.captureManager.decode();
        this.decoratedBarcodeView.setTorchListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        CaptureManager captureManager = this.captureManager;
        if (captureManager != null) {
            captureManager.onPause();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        CaptureManager captureManager = this.captureManager;
        if (captureManager != null) {
            captureManager.onResume();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        CaptureManager captureManager = this.captureManager;
        if (captureManager != null) {
            captureManager.onDestroy();
        }
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle, PersistableBundle persistableBundle) {
        super.onSaveInstanceState(bundle, persistableBundle);
        this.captureManager.onSaveInstanceState(bundle);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return this.decoratedBarcodeView.onKeyDown(i, keyEvent) || super.onKeyDown(i, keyEvent);
    }
}
