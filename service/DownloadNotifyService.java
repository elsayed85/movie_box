package com.movieboxpro.android.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.db.entity.Download;
import com.movieboxpro.android.event.DownloadChangedEvent;
import com.movieboxpro.android.receiver.DownloadNotificationReceiver;
import com.movieboxpro.android.utils.RomUtils;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
/* loaded from: classes3.dex */
public class DownloadNotifyService extends Service {
    private NotificationCompat.Builder builder;
    private NotificationManager manager;
    private int mainId = 100;
    private int firstId = -1;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        startForeground(this.mainId, initNotification("MovieBoxPro Downloading"));
        DownloadBroadcastReceiver downloadBroadcastReceiver = new DownloadBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.ACTION.DOWNLOAD_NOTIFY_SUCCESS);
        intentFilter.addAction(Constant.ACTION.DOWNLOAD_NOTIFY_START);
        intentFilter.addAction(Constant.ACTION.DOWNLOAD_NOTIFY_PAUSE);
        intentFilter.addAction(Constant.ACTION.DOWNLOAD_NOTIFY_DELETE);
        intentFilter.addAction(Constant.ACTION.DOWNLOAD_NOTIFY_FAIL);
        intentFilter.addAction(Constant.ACTION.DOWNLOAD_NOTIFY_PROGRESS);
        LocalBroadcastManager.getInstance(this).registerReceiver(downloadBroadcastReceiver, intentFilter);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return new DownloadBinder();
    }

    /* loaded from: classes3.dex */
    public class DownloadBinder extends Binder {
        public DownloadBinder() {
        }

        DownloadNotifyService getService() {
            return DownloadNotifyService.this;
        }
    }

    public Notification initNotification(String str) {
        this.manager = (NotificationManager) getSystemService("notification");
        if (Build.VERSION.SDK_INT >= 26) {
            this.manager.createNotificationChannel(new NotificationChannel("MovieBoxProDownload", str, 1));
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "MovieBoxProDownload");
        this.builder = builder;
        builder.setDefaults(4).setOngoing(true).setOnlyAlertOnce(true).setPriority(-2).setContentTitle("Start Download").setContentText("");
        if (RomUtils.isGoogle() || RomUtils.isOneplus()) {
            this.builder.setSmallIcon(R.mipmap.ic_white_logo);
            this.builder.setColor(Color.parseColor("#2D2D30"));
            this.builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_logo));
        } else {
            this.builder.setSmallIcon(R.mipmap.ic_logo);
        }
        Intent intent = new Intent(this, DownloadNotificationReceiver.class);
        intent.setAction(DownloadNotificationReceiver.CHECK_DOWNLOAD);
        this.builder.setContentIntent(PendingIntent.getBroadcast(this, 1, intent, 134217728));
        return this.builder.build();
    }

    public void onDownloadStart(int i, int i2, int i3, int i4, String str) {
        this.builder.setOngoing(true);
        this.builder.setAutoCancel(false);
        if (i2 == 1) {
            this.builder.setContentTitle(str);
        } else {
            this.builder.setContentTitle(String.format("%s S%s E%s", str, Integer.valueOf(i3), Integer.valueOf(i4)));
        }
        this.builder.setContentText("The task is started");
        this.builder.setProgress(0, 0, true);
        int i5 = this.firstId;
        if (i5 == -1) {
            this.firstId = i;
            this.manager.cancel(i);
            this.manager.notify(this.mainId, this.builder.build());
        } else if (i == i5) {
            this.manager.notify(this.mainId, this.builder.build());
        } else {
            this.manager.notify(i, this.builder.build());
        }
    }

    public void onDownloadProgress(int i, int i2, int i3, String str, int i4, String str2, int i5) {
        Log.d("DownloadNotification", i5 + "");
        NotificationCompat.Builder builder = this.builder;
        builder.setContentText(str2 + "/S");
        this.builder.setAutoCancel(false);
        this.builder.setProgress(100, i5, false);
        if (i == 1) {
            this.builder.setContentTitle(str);
        } else {
            this.builder.setContentTitle(String.format("%s S%s E%s", str, Integer.valueOf(i2), Integer.valueOf(i3)));
        }
        int i6 = this.firstId;
        if (i6 == -1) {
            this.firstId = i4;
            this.manager.cancel(i4);
            this.manager.notify(this.mainId, this.builder.build());
        } else if (i4 == i6) {
            this.manager.notify(this.mainId, this.builder.build());
        } else {
            this.manager.notify(i4, this.builder.build());
        }
    }

    public void onDownloadCancel(int i) {
        int i2 = this.firstId;
        if (i2 == -1) {
            this.manager.cancelAll();
        } else if (i2 == i) {
            this.firstId = -1;
        } else {
            this.manager.cancel(i);
        }
    }

    public void onDownloadSuccess(int i, String str, int i2, int i3, int i4) {
        onDownloadCancel(i);
        if (i4 == 1) {
            this.builder.setContentTitle(str);
        } else {
            this.builder.setContentTitle(String.format("%s S%s E%s", str, Integer.valueOf(i2), Integer.valueOf(i3)));
        }
        this.builder.setOngoing(false);
        this.builder.setContentText("Download completed");
        this.builder.setProgress(0, 0, false);
        this.builder.setAutoCancel(true);
        Intent intent = new Intent(this, DownloadNotificationReceiver.class);
        intent.setAction(DownloadNotificationReceiver.CHECK_DOWNLOADED);
        this.builder.setContentIntent(PendingIntent.getBroadcast(this, 1, intent, 134217728));
        this.manager.notify(i, this.builder.build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void checkAllDownload() {
        List<Download> findByStatue = App.getDB().downloadDao().findByStatue(1);
        if (findByStatue == null || findByStatue.size() == 0) {
            stopSelf();
        }
    }

    /* loaded from: classes3.dex */
    class DownloadBroadcastReceiver extends BroadcastReceiver {
        DownloadBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String stringExtra = intent.getStringExtra("speedText");
            int intExtra = intent.getIntExtra("id", 0);
            String stringExtra2 = intent.getStringExtra("title");
            int intExtra2 = intent.getIntExtra("season", 1);
            int intExtra3 = intent.getIntExtra("episode", 1);
            int intExtra4 = intent.getIntExtra("boxType", 1);
            int intExtra5 = intent.getIntExtra("progress", 0);
            String action = intent.getAction();
            if (action != null) {
                char c = 65535;
                switch (action.hashCode()) {
                    case -551317599:
                        if (action.equals(Constant.ACTION.DOWNLOAD_NOTIFY_PAUSE)) {
                            c = 2;
                            break;
                        }
                        break;
                    case -548000243:
                        if (action.equals(Constant.ACTION.DOWNLOAD_NOTIFY_START)) {
                            c = 0;
                            break;
                        }
                        break;
                    case -251113152:
                        if (action.equals(Constant.ACTION.DOWNLOAD_NOTIFY_DELETE)) {
                            c = 4;
                            break;
                        }
                        break;
                    case 951748595:
                        if (action.equals(Constant.ACTION.DOWNLOAD_NOTIFY_FAIL)) {
                            c = 3;
                            break;
                        }
                        break;
                    case 1672607874:
                        if (action.equals(Constant.ACTION.DOWNLOAD_NOTIFY_PROGRESS)) {
                            c = 1;
                            break;
                        }
                        break;
                    case 1682761454:
                        if (action.equals(Constant.ACTION.DOWNLOAD_NOTIFY_SUCCESS)) {
                            c = 5;
                            break;
                        }
                        break;
                }
                if (c == 0) {
                    DownloadNotifyService.this.onDownloadStart(intExtra, intExtra4, intExtra2, intExtra3, stringExtra2);
                } else if (c == 1) {
                    DownloadNotifyService.this.onDownloadProgress(intExtra4, intExtra2, intExtra3, stringExtra2, intExtra, stringExtra, intExtra5);
                } else if (c == 2 || c == 3 || c == 4) {
                    EventBus.getDefault().post(new DownloadChangedEvent());
                    DownloadNotifyService.this.onDownloadCancel(intExtra);
                    DownloadNotifyService.this.checkAllDownload();
                } else if (c != 5) {
                } else {
                    DownloadNotifyService.this.onDownloadSuccess(intExtra, stringExtra2, intExtra2, intExtra3, intExtra4);
                    DownloadNotifyService.this.checkAllDownload();
                }
            }
        }
    }
}
