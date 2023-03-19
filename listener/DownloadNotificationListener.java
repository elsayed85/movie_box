package com.movieboxpro.android.listener;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.movieboxpro.android.R;
import com.movieboxpro.android.db.entity.Download;
import com.movieboxpro.android.receiver.DownloadNotificationReceiver;
/* loaded from: classes3.dex */
public class DownloadNotificationListener {
    private NotificationCompat.Builder builder;
    private Context context;
    private String downloadId = "";
    private NotificationManager manager;

    public DownloadNotificationListener(Context context) {
        this.context = context.getApplicationContext();
    }

    public void setTitle(String str) {
        NotificationCompat.Builder builder = this.builder;
        if (builder != null) {
            builder.setContentTitle(str);
        }
    }

    public void initNotification(String str) {
        this.manager = (NotificationManager) this.context.getSystemService("notification");
        if (Build.VERSION.SDK_INT >= 26) {
            this.manager.createNotificationChannel(new NotificationChannel("MovieBoxProDownload", str, 1));
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this.context, "MovieBoxProDownload");
        this.builder = builder;
        builder.setDefaults(4).setOngoing(true).setOnlyAlertOnce(true).setPriority(-2).setContentTitle("Start Download").setContentText("").setSmallIcon(R.drawable.logo);
        Intent intent = new Intent(this.context, DownloadNotificationReceiver.class);
        intent.setAction(DownloadNotificationReceiver.CHECK_DOWNLOAD);
        this.builder.setContentIntent(PendingIntent.getBroadcast(this.context, 1, intent, 134217728));
    }

    public void onDownloadStart(Download download) {
        this.builder.setOngoing(true);
        this.builder.setAutoCancel(false);
        if (download.getBox_type() == 1) {
            this.builder.setContentTitle(download.getTitle());
        } else {
            this.builder.setContentTitle(String.format("%s S%s E%s", download.getTitle(), Integer.valueOf(download.getSeason()), Integer.valueOf(download.getEpisode())));
        }
        this.builder.setContentText("The task is started");
        this.builder.setProgress(0, 0, true);
        this.manager.notify(download.getId(), this.builder.build());
    }

    public void onDownloadProgress(Download download, String str, int i) {
        Log.d("DownloadNotification", i + "");
        NotificationCompat.Builder builder = this.builder;
        builder.setContentText(str + "/S");
        this.builder.setAutoCancel(false);
        this.builder.setProgress(100, i, false);
        this.manager.notify(download.getId(), this.builder.build());
    }

    public void onDownloadPause(Download download) {
        this.manager.cancel(download.getId());
    }

    public void onDownloadSuccess(Download download) {
        this.manager.cancel(download.getId());
    }

    public void onDownloadFailed(Download download) {
        this.manager.cancel(download.getId());
    }

    public void onDownloadDelete(Download download) {
        this.manager.cancel(download.getId());
    }
}
