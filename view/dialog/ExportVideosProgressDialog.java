package com.movieboxpro.android.view.dialog;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.R;
import com.movieboxpro.android.view.dialog.DialogAction;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ExportVideosProgressDialog.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\nH\u0016J\b\u0010\u000e\u001a\u00020\nH\u0016J\u001e\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\fR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ExportVideosProgressDialog;", "Lcom/movieboxpro/android/view/dialog/BaseCenterDialog;", "context", "Landroid/content/Context;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/DialogAction$ActionListener;", "(Landroid/content/Context;Lcom/movieboxpro/android/view/dialog/DialogAction$ActionListener;)V", "handle", "Landroid/os/Handler;", "initData", "", "initLayoutId", "", "initListener", "initView", "updateProgress", "progressText", "", "progress", "max", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ExportVideosProgressDialog extends BaseCenterDialog {
    private final Handler handle;
    private final DialogAction.ActionListener listener;

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialog
    public void initData() {
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialog
    public int initLayoutId() {
        return R.layout.dialog_export_videos_progress;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialog
    public void initView() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExportVideosProgressDialog(Context context, DialogAction.ActionListener listener) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
        this.handle = new Handler(Looper.getMainLooper());
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialog
    public void initListener() {
        ((TextView) findViewById(R.id.tvStop)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ExportVideosProgressDialog$3XyNWoapFEX0WqxpdTgBMI7tlSs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ExportVideosProgressDialog.m970initListener$lambda0(ExportVideosProgressDialog.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m970initListener$lambda0(ExportVideosProgressDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.listener.onClick();
        this$0.dismiss();
    }

    public final void updateProgress(final String progressText, final int i, final int i2) {
        Intrinsics.checkNotNullParameter(progressText, "progressText");
        this.handle.post(new Runnable() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ExportVideosProgressDialog$-uo5deQgDF_VPhcp8LAJ34VyzXE
            @Override // java.lang.Runnable
            public final void run() {
                ExportVideosProgressDialog.m972updateProgress$lambda1(ExportVideosProgressDialog.this, progressText, i, i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: updateProgress$lambda-1  reason: not valid java name */
    public static final void m972updateProgress$lambda1(ExportVideosProgressDialog this$0, String progressText, int i, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(progressText, "$progressText");
        ((TextView) this$0.findViewById(R.id.tvSizeProgress)).setText(progressText);
        ((ProgressBar) this$0.findViewById(R.id.pbExport)).setProgress(i);
        ((ProgressBar) this$0.findViewById(R.id.pbExport)).setMax(i2);
    }
}
