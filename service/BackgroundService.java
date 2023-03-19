package com.movieboxpro.android.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public class BackgroundService extends Service {
    private static final String NOTIFICATION_CHANNEL_NAME = "BackgroundLocation";
    private NotificationManager notificationManager = null;
    boolean isCreateChannel = false;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void startBackgroundService(Context context) {
        context.startService(new Intent(context, BackgroundService.class));
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    private Notification buildNotification() {
        Notification.Builder builder;
        if (Build.VERSION.SDK_INT >= 26) {
            if (this.notificationManager == null) {
                this.notificationManager = (NotificationManager) getSystemService("notification");
            }
            String packageName = getPackageName();
            if (!this.isCreateChannel) {
                NotificationChannel notificationChannel = new NotificationChannel(packageName, NOTIFICATION_CHANNEL_NAME, 3);
                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(-16776961);
                notificationChannel.setShowBadge(true);
                this.notificationManager.createNotificationChannel(notificationChannel);
                this.isCreateChannel = true;
            }
            builder = new Notification.Builder(getApplicationContext(), packageName);
        } else {
            builder = new Notification.Builder(getApplicationContext());
        }
        builder.setSmallIcon(R.drawable.ic_launcher).setContentTitle("MovieBoxPro").setContentText("正在后台运行").setWhen(System.currentTimeMillis());
        if (Build.VERSION.SDK_INT >= 16) {
            return builder.build();
        }
        return builder.getNotification();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        startForeground(1000, buildNotification());
        return 1;
    }
}
