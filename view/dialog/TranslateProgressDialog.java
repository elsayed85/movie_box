package com.movieboxpro.android.view.dialog;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.ProgressBar;
import com.movieboxpro.android.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: TranslateProgressDialog.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\u0005H\u0016J\b\u0010\f\u001a\u00020\nH\u0016J\b\u0010\r\u001a\u00020\nH\u0016J\u0016\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/movieboxpro/android/view/dialog/TranslateProgressDialog;", "Lcom/movieboxpro/android/view/dialog/BaseCenterDialog;", "context", "Landroid/content/Context;", "max", "", "(Landroid/content/Context;I)V", "progressBar", "Landroid/widget/ProgressBar;", "initData", "", "initLayoutId", "initListener", "initView", "updateProgress", "progress", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TranslateProgressDialog extends BaseCenterDialog {
    private final int max;
    private ProgressBar progressBar;

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialog
    public void initData() {
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialog
    public int initLayoutId() {
        return R.layout.dialog_translate_progress;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialog
    public void initListener() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TranslateProgressDialog(Context context, int i) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.max = i;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialog
    public void initView() {
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        View findViewById = findViewById(R.id.progressBar);
        Intrinsics.checkNotNull(findViewById);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(R.id.progressBar)!!");
        ProgressBar progressBar = (ProgressBar) findViewById;
        this.progressBar = progressBar;
        if (progressBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressBar");
            progressBar = null;
        }
        progressBar.setMax(this.max);
    }

    public final void updateProgress(int i, int i2) {
        ProgressBar progressBar = this.progressBar;
        ProgressBar progressBar2 = null;
        if (progressBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressBar");
            progressBar = null;
        }
        progressBar.setMax(i2);
        if (Build.VERSION.SDK_INT >= 24) {
            ProgressBar progressBar3 = this.progressBar;
            if (progressBar3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressBar");
            } else {
                progressBar2 = progressBar3;
            }
            progressBar2.setProgress(i, true);
            return;
        }
        ProgressBar progressBar4 = this.progressBar;
        if (progressBar4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressBar");
        } else {
            progressBar2 = progressBar4;
        }
        progressBar2.setProgress(i);
    }
}
