package com.movieboxpro.android.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.movieboxpro.android.R;
import com.movieboxpro.android.db.entity.Download;
import com.movieboxpro.android.receiver.DownloadNotificationReceiver;
/* loaded from: classes3.dex */
public class DownloadNotificationService extends Service {
    private NotificationCompat.Builder builder;
    private NotificationManager manager;
    private int mainId = 100;
    private int firstId = -1;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        startForeground(this.mainId, initNotification("MovieBoxPro Downloading"));
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return new DownloadBinder();
    }

    /* loaded from: classes3.dex */
    public class DownloadBinder extends Binder {
        public DownloadBinder() {
        }

        DownloadNotificationService getService() {
            return DownloadNotificationService.this;
        }
    }

    public Notification initNotification(String str) {
        this.manager = (NotificationManager) getSystemService("notification");
        if (Build.VERSION.SDK_INT >= 26) {
            this.manager.createNotificationChannel(new NotificationChannel("MovieBoxProDownload", str, 1));
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "MovieBoxProDownload");
        this.builder = builder;
        builder.setDefaults(4).setOngoing(true).setOnlyAlertOnce(true).setPriority(-2).setContentTitle("Start Download").setContentText("").setSmallIcon(R.drawable.logo);
        Intent intent = new Intent(this, DownloadNotificationReceiver.class);
        intent.setAction(DownloadNotificationReceiver.CHECK_DOWNLOAD);
        this.builder.setContentIntent(PendingIntent.getBroadcast(this, 1, intent, 134217728));
        return this.builder.build();
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
        if (this.firstId == -1) {
            int id = download.getId();
            this.firstId = id;
            this.manager.cancel(id);
            this.manager.notify(this.mainId, this.builder.build());
        } else if (download.getId() == this.firstId) {
            this.manager.notify(this.mainId, this.builder.build());
        } else {
            this.manager.notify(download.getId(), this.builder.build());
        }
    }

    public void onDownloadProgress(Download download, String str, int i) {
        Log.d("DownloadNotification", i + "");
        NotificationCompat.Builder builder = this.builder;
        builder.setContentText(str + "/S");
        this.builder.setAutoCancel(false);
        this.builder.setProgress(100, i, false);
        if (download.getBox_type() == 1) {
            this.builder.setContentTitle(download.getTitle());
        } else {
            this.builder.setContentTitle(String.format("%s S%s E%s", download.getTitle(), Integer.valueOf(download.getSeason()), Integer.valueOf(download.getEpisode())));
        }
        if (this.firstId == -1) {
            int id = download.getId();
            this.firstId = id;
            this.manager.cancel(id);
            this.manager.notify(this.mainId, this.builder.build());
        } else if (download.getId() == this.firstId) {
            this.manager.notify(this.mainId, this.builder.build());
        } else {
            this.manager.notify(download.getId(), this.builder.build());
        }
    }

    public void onDownloadPause(Download download) {
        int i = this.firstId;
        if (i != -1) {
            if (i == download.getId()) {
                this.firstId = -1;
            } else {
                this.manager.cancel(download.getId());
            }
        }
    }

    public void onDownloadSuccess(Download download) {
        int i = this.firstId;
        if (i != -1) {
            if (i == download.getId()) {
                this.firstId = -1;
            } else {
                this.manager.cancel(download.getId());
            }
        }
    }

    public void onDownloadFailed(Download download) {
        int i = this.firstId;
        if (i != -1) {
            if (i == download.getId()) {
                this.firstId = -1;
            } else {
                this.manager.cancel(download.getId());
            }
        }
    }

    public void onDownloadDelete(Download download) {
        int i = this.firstId;
        if (i != -1) {
            if (i == download.getId()) {
                this.firstId = -1;
            } else {
                this.manager.cancel(download.getId());
            }
        }
    }
}
