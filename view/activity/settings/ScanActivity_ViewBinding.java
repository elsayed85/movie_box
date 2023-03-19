package com.movieboxpro.android.view.activity.settings;

import android.view.View;
import android.webkit.WebView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public class ScanActivity_ViewBinding implements Unbinder {
    private ScanActivity target;

    public ScanActivity_ViewBinding(ScanActivity scanActivity) {
        this(scanActivity, scanActivity.getWindow().getDecorView());
    }

    public ScanActivity_ViewBinding(ScanActivity scanActivity, View view) {
        this.target = scanActivity;
        scanActivity.activityScan = (WebView) Utils.findRequiredViewAsType(view, R.id.activity_scan, "field 'activityScan'", WebView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        ScanActivity scanActivity = this.target;
        if (scanActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        scanActivity.activityScan = null;
    }
}
