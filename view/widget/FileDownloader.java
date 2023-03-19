package com.movieboxpro.android.view.widget;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.model.DownloadInfo;
import com.movieboxpro.android.service.FileDownloadService;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.utils.ParamsUtils;
import com.movieboxpro.android.utils.ToastUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: FileDownloader.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001:\u000212B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\b\b\u0002\u0010'\u001a\u00020\u0017J\b\u0010(\u001a\u00020$H\u0002J\u0018\u0010)\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010*\u001a\u00020\u0017H\u0002J\u0018\u0010+\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010\u001c\u001a\u00020\u0017H\u0002J\u0010\u0010,\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0002J\u0018\u0010-\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u0017H\u0002J\u0010\u0010.\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0002J\u0010\u0010/\u001a\u00020$2\u0006\u00100\u001a\u00020\u0006H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000b\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0018\u00010\u0015R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0019\"\u0004\b\u001e\u0010\u001bR\u000e\u0010\u001f\u001a\u00020\u0017X\u0082D¢\u0006\u0002\n\u0000R\u001c\u0010 \u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0019\"\u0004\b\"\u0010\u001b¨\u00063"}, d2 = {"Lcom/movieboxpro/android/view/widget/FileDownloader;", "", "()V", "activity", "Landroid/app/Activity;", "id", "", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/widget/FileDownloader$DownloadListener;", "getListener", "()Lcom/movieboxpro/android/view/widget/FileDownloader$DownloadListener;", "setListener", "(Lcom/movieboxpro/android/view/widget/FileDownloader$DownloadListener;)V", "mDialog", "Landroid/app/ProgressDialog;", "mReceiver", "Lcom/movieboxpro/android/view/widget/FileDownloader$DownloadReceiver;", FileDownloadService.PARAMS_KEY_MD5, "", "getMd5", "()Ljava/lang/String;", "setMd5", "(Ljava/lang/String;)V", FileDownloadService.PARAMS_KEY_PATH, "getPath", "setPath", "tag", "url", "getUrl", "setUrl", DownloadInfo.DOWNLOAD, "", "context", "Landroid/content/Context;", "title", "hideProgress", "onDownloadFailure", "error", "onDownloadSuccess", "registerReceiver", "showProgress", "unregisterReceiver", "updateProgress", "progress", "DownloadListener", "DownloadReceiver", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FileDownloader {
    private Activity activity;
    private Integer id;
    private DownloadListener listener;
    private ProgressDialog mDialog;
    private DownloadReceiver mReceiver;
    private String md5;
    private String path;
    private final String tag = "FileDownloader";
    private String url;

    /* compiled from: FileDownloader.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0007H\u0016¨\u0006\r"}, d2 = {"Lcom/movieboxpro/android/view/widget/FileDownloader$DownloadListener;", "", "onFailure", "", "id", "", "error", "", "onProgress", "progress", "onStarted", "onSuccess", FileDownloadService.PARAMS_KEY_PATH, "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface DownloadListener {

        /* compiled from: FileDownloader.kt */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        /* loaded from: classes3.dex */
        public static final class DefaultImpls {
            public static void onFailure(DownloadListener downloadListener, int i, String error) {
                Intrinsics.checkNotNullParameter(downloadListener, "this");
                Intrinsics.checkNotNullParameter(error, "error");
            }

            public static void onProgress(DownloadListener downloadListener, int i, int i2) {
                Intrinsics.checkNotNullParameter(downloadListener, "this");
            }

            public static void onStarted(DownloadListener downloadListener, int i) {
                Intrinsics.checkNotNullParameter(downloadListener, "this");
            }

            public static void onSuccess(DownloadListener downloadListener, int i, String path) {
                Intrinsics.checkNotNullParameter(downloadListener, "this");
                Intrinsics.checkNotNullParameter(path, "path");
            }
        }

        void onFailure(int i, String str);

        void onProgress(int i, int i2);

        void onStarted(int i);

        void onSuccess(int i, String str);
    }

    public final Integer getId() {
        return this.id;
    }

    public final void setId(Integer num) {
        this.id = num;
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setUrl(String str) {
        this.url = str;
    }

    public final String getPath() {
        return this.path;
    }

    public final void setPath(String str) {
        this.path = str;
    }

    public final String getMd5() {
        return this.md5;
    }

    public final void setMd5(String str) {
        this.md5 = str;
    }

    public final DownloadListener getListener() {
        return this.listener;
    }

    public final void setListener(DownloadListener downloadListener) {
        this.listener = downloadListener;
    }

    public static /* synthetic */ void download$default(FileDownloader fileDownloader, Context context, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = "DOWNLOADING";
        }
        fileDownloader.download(context, str);
    }

    public final void download(Context context, String title) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(title, "title");
        if (this.url == null) {
            throw new RuntimeException("url not specified");
        }
        if (this.path == null) {
            throw new RuntimeException("path not specified");
        }
        this.activity = (Activity) context;
        Intent intent = new Intent(context, FileDownloadService.class);
        intent.setAction(Constant.ACTION.DOWNLOAD_FILE);
        intent.putExtras(ParamsUtils.newBuilder().addParam(FileDownloadService.PARAMS_KEY_PATH, this.path).addParam("url", this.url).addParam(FileDownloadService.PARAMS_KEY_MD5, this.md5).build());
        context.startService(intent);
        showProgress(context, title);
        registerReceiver(context);
        DownloadListener downloadListener = this.listener;
        if (downloadListener == null) {
            return;
        }
        Integer num = this.id;
        Intrinsics.checkNotNull(num);
        downloadListener.onStarted(num.intValue());
    }

    private final void showProgress(final Context context, String str) {
        ProgressDialog progressDialog = this.mDialog;
        if (progressDialog != null) {
            Intrinsics.checkNotNull(progressDialog);
            if (progressDialog.isShowing()) {
                return;
            }
        }
        ProgressDialog progressDialog2 = new ProgressDialog(context, R.style.AppTheme_DialogStyle);
        this.mDialog = progressDialog2;
        Intrinsics.checkNotNull(progressDialog2);
        progressDialog2.setMax(100);
        ProgressDialog progressDialog3 = this.mDialog;
        Intrinsics.checkNotNull(progressDialog3);
        progressDialog3.setIndeterminate(false);
        ProgressDialog progressDialog4 = this.mDialog;
        Intrinsics.checkNotNull(progressDialog4);
        progressDialog4.setTitle(str);
        ProgressDialog progressDialog5 = this.mDialog;
        Intrinsics.checkNotNull(progressDialog5);
        progressDialog5.setProgressStyle(1);
        ProgressDialog progressDialog6 = this.mDialog;
        Intrinsics.checkNotNull(progressDialog6);
        progressDialog6.setCancelable(false);
        ProgressDialog progressDialog7 = this.mDialog;
        Intrinsics.checkNotNull(progressDialog7);
        progressDialog7.setCanceledOnTouchOutside(false);
        ProgressDialog progressDialog8 = this.mDialog;
        Intrinsics.checkNotNull(progressDialog8);
        progressDialog8.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.movieboxpro.android.view.widget.-$$Lambda$FileDownloader$fMazKp3sTXQG3lzPAcvECx_H8jM
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                FileDownloader.m1429showProgress$lambda0(FileDownloader.this, context, dialogInterface);
            }
        });
        ProgressDialog progressDialog9 = this.mDialog;
        Intrinsics.checkNotNull(progressDialog9);
        progressDialog9.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showProgress$lambda-0  reason: not valid java name */
    public static final void m1429showProgress$lambda0(FileDownloader this$0, Context context, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        this$0.unregisterReceiver(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateProgress(int i) {
        MLog.d(this.tag, Intrinsics.stringPlus("updateProgress: ", Integer.valueOf(i)));
        ProgressDialog progressDialog = this.mDialog;
        if (progressDialog == null) {
            return;
        }
        progressDialog.setProgress(i);
    }

    private final void hideProgress() {
        Activity activity = this.activity;
        boolean z = false;
        if (activity != null && activity.isFinishing()) {
            z = true;
        }
        if (z) {
            return;
        }
        try {
            ProgressDialog progressDialog = this.mDialog;
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        } catch (IllegalArgumentException unused) {
        }
        this.mDialog = null;
    }

    private final void registerReceiver(Context context) {
        if (this.mReceiver != null) {
            return;
        }
        this.mReceiver = new DownloadReceiver(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.ACTION.DOWNLOAD_FILE_COMPLETE);
        intentFilter.addAction(Constant.ACTION.DOWNLOAD_FILE_STARTED);
        intentFilter.addAction(Constant.ACTION.DOWNLOAD_FILE_PROGRESS);
        intentFilter.addAction(Constant.ACTION.DOWNLOAD_FILE_FAILURE);
        DownloadReceiver downloadReceiver = this.mReceiver;
        if (downloadReceiver == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.movieboxpro.android.view.widget.FileDownloader.DownloadReceiver");
        }
        LocalBroadcastManager.getInstance(context).registerReceiver(downloadReceiver, intentFilter);
    }

    private final void unregisterReceiver(Context context) {
        DownloadReceiver downloadReceiver = this.mReceiver;
        if (downloadReceiver != null) {
            if (downloadReceiver == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.movieboxpro.android.view.widget.FileDownloader.DownloadReceiver");
            }
            LocalBroadcastManager.getInstance(context).unregisterReceiver(downloadReceiver);
        }
        this.mReceiver = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onDownloadSuccess(Context context, String str) {
        hideProgress();
        unregisterReceiver(context);
        DownloadListener downloadListener = this.listener;
        if (downloadListener != null) {
            Integer num = this.id;
            Intrinsics.checkNotNull(num);
            downloadListener.onSuccess(num.intValue(), str);
        }
        Context context2 = App.getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext()");
        CommonExtKt.onMobEvent(context2, "file_download_success");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onDownloadFailure(Context context, String str) {
        hideProgress();
        unregisterReceiver(context);
        ToastUtils.showShort(str, new Object[0]);
        DownloadListener downloadListener = this.listener;
        if (downloadListener != null) {
            Integer num = this.id;
            Intrinsics.checkNotNull(num);
            downloadListener.onFailure(num.intValue(), str);
        }
        Context context2 = App.getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext()");
        CommonExtKt.onMobEvent(context2, "file_download_error");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: FileDownloader.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/movieboxpro/android/view/widget/FileDownloader$DownloadReceiver;", "Landroid/content/BroadcastReceiver;", "(Lcom/movieboxpro/android/view/widget/FileDownloader;)V", "tag", "", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public final class DownloadReceiver extends BroadcastReceiver {
        private final String tag;
        final /* synthetic */ FileDownloader this$0;

        public DownloadReceiver(FileDownloader this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
            this.tag = "DownloadReceiver";
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String string;
            String string2;
            Intrinsics.checkNotNullParameter(context, "context");
            String action = intent == null ? null : intent.getAction();
            if (action != null) {
                String str = "";
                switch (action.hashCode()) {
                    case -716980159:
                        if (action.equals(Constant.ACTION.DOWNLOAD_FILE_PROGRESS)) {
                            Bundle extras = intent.getExtras();
                            this.this$0.updateProgress(extras == null ? 0 : extras.getInt("progress"));
                            return;
                        }
                        return;
                    case -641094707:
                        if (action.equals(Constant.ACTION.DOWNLOAD_FILE_STARTED)) {
                            MLog.d(this.tag, "START");
                            return;
                        }
                        return;
                    case -315347123:
                        if (action.equals(Constant.ACTION.DOWNLOAD_FILE_COMPLETE)) {
                            Bundle extras2 = intent.getExtras();
                            if (extras2 != null && (string = extras2.getString(FileDownloadService.PARAMS_KEY_PATH)) != null) {
                                str = string;
                            }
                            this.this$0.onDownloadSuccess(context, str);
                            return;
                        }
                        return;
                    case 169516246:
                        if (action.equals(Constant.ACTION.DOWNLOAD_FILE_FAILURE)) {
                            Bundle extras3 = intent.getExtras();
                            if (extras3 != null && (string2 = extras3.getString("error")) != null) {
                                str = string2;
                            }
                            this.this$0.onDownloadFailure(context, str);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }
}
