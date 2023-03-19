package com.movieboxpro.android.service;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.utils.IntentUtils;
import com.movieboxpro.android.utils.MLog;
import java.io.File;
import java.io.IOException;
import kotlin.io.FilesKt;
import okhttp3.ResponseBody;
import retrofit2.Response;
/* loaded from: classes3.dex */
public class FileDownloadService extends IntentService {
    public static final String PARAMS_KEY_ERROR = "error";
    public static final String PARAMS_KEY_MD5 = "md5";
    public static final String PARAMS_KEY_PATH = "path";
    public static final String PARAMS_KEY_PROGRESS = "progress";
    public static final String PARAMS_KEY_URL = "url";
    private static final String TAG = "FileDownloadService";
    private String channelId;
    private NotificationCompat.Builder mBuilder;
    private NotificationManager mNotifyManager;
    private CharSequence name;

    public FileDownloadService() {
        super(TAG);
        this.channelId = "foreground_id";
        this.name = "foreground_name";
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mNotifyManager = (NotificationManager) getSystemService("notification");
        this.mBuilder = new NotificationCompat.Builder(this, this.channelId).setOnlyAlertOnce(true);
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(this.channelId, this.name, 3);
            notificationChannel.enableVibration(false);
            this.mBuilder.setChannelId(this.channelId);
            NotificationManager notificationManager = this.mNotifyManager;
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
        this.mBuilder.setContentTitle("MovieBoxPro").setSmallIcon(R.mipmap.ic_logo).setAutoCancel(false);
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        String str;
        String str2;
        String str3;
        if (intent != null) {
            if (Constant.ACTION.DOWNLOAD_FILE.equals(intent.getAction())) {
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    str2 = extras.getString("url", null);
                    str3 = extras.getString(PARAMS_KEY_PATH, null);
                    str = extras.getString(PARAMS_KEY_MD5, null);
                } else {
                    str = null;
                    str2 = null;
                    str3 = null;
                }
                if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
                    try {
                        doDownload(str3, str2, str);
                        return;
                    } catch (IOException e) {
                        onDownloadFailure(null, e.getLocalizedMessage());
                        return;
                    }
                }
                onDownloadFailure(null, "Success");
                return;
            }
            onDownloadFailure(null, "Fail");
            return;
        }
        onDownloadFailure(null, "Fail");
    }

    private void doDownload(String str, String str2, String str3) throws IOException {
        onDownloadStarted(str);
        Response<ResponseBody> execute = Http.getService().download(str2).execute();
        if (execute.isSuccessful()) {
            SaveResult saveResponseBody = saveResponseBody(str, execute.body(), str3);
            if (saveResponseBody.isSuccess()) {
                onDownloadSuccess(str);
                return;
            } else {
                onDownloadFailure(str, saveResponseBody.getError());
                return;
            }
        }
        MLog.d(TAG, "doDownload: error code: " + execute.code());
        onDownloadFailure(str, execute.code() + "");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0131 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0126 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v16 */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v18 */
    /* JADX WARN: Type inference failed for: r7v19 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v6, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x00a2 -> B:89:0x0123). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.movieboxpro.android.service.FileDownloadService.SaveResult saveResponseBody(java.lang.String r19, okhttp3.ResponseBody r20, java.lang.String r21) {
        /*
            Method dump skipped, instructions count: 317
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.service.FileDownloadService.saveResponseBody(java.lang.String, okhttp3.ResponseBody, java.lang.String):com.movieboxpro.android.service.FileDownloadService$SaveResult");
    }

    private void onDownloadStarted(String str) {
        Intent intent = new Intent(Constant.ACTION.DOWNLOAD_FILE_STARTED);
        intent.putExtra("progress", 0);
        intent.putExtra(PARAMS_KEY_PATH, str);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private void onDownloadProgress(String str, int i) {
        MLog.d(TAG, "onDownloadProgress: " + i);
        NotificationCompat.Builder builder = this.mBuilder;
        builder.setContentText("Downloading " + i + "%");
        this.mBuilder.setProgress(100, i, false);
        this.mNotifyManager.notify(2000, this.mBuilder.build());
        Intent intent = new Intent(Constant.ACTION.DOWNLOAD_FILE_PROGRESS);
        intent.putExtra("progress", i);
        intent.putExtra(PARAMS_KEY_PATH, str);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private void onDownloadSuccess(String str) {
        this.mBuilder.setContentText("Download Success");
        File file = new File(str);
        if ("apk".equalsIgnoreCase(FilesKt.getExtension(file))) {
            startActivity(IntentUtils.getInstallAppIntent(file, true));
        }
        this.mNotifyManager.notify(2000, this.mBuilder.build());
        Intent intent = new Intent(Constant.ACTION.DOWNLOAD_FILE_COMPLETE);
        intent.putExtra("progress", 100);
        intent.putExtra(PARAMS_KEY_PATH, str);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private void onDownloadFailure(String str, String str2) {
        NotificationCompat.Builder builder = this.mBuilder;
        builder.setContentText("Download Error:" + str2);
        this.mNotifyManager.notify(2000, this.mBuilder.build());
        Intent intent = new Intent(Constant.ACTION.DOWNLOAD_FILE_FAILURE);
        intent.putExtra("progress", 0);
        intent.putExtra(PARAMS_KEY_PATH, str);
        intent.putExtra("error", str2);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class SaveResult {
        private String error;
        private boolean success;

        SaveResult(boolean z, String str) {
            this.success = z;
            this.error = str;
        }

        public boolean isSuccess() {
            return this.success;
        }

        public void setSuccess(boolean z) {
            this.success = z;
        }

        public String getError() {
            return this.error;
        }

        public void setError(String str) {
            this.error = str;
        }
    }
}
