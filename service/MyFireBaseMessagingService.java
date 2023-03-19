package com.movieboxpro.android.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.os.Build;
import android.util.Log;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.movieboxpro.android.R;
import com.movieboxpro.android.receiver.PushMessageClickReceiver;
import com.movieboxpro.android.utils.RomUtils;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: MyFireBaseMessagingService.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0002\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eH\u0016J&\u0010\u000f\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000e2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0\u0012H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/movieboxpro/android/service/MyFireBaseMessagingService;", "Lcom/google/firebase/messaging/FirebaseMessagingService;", "()V", "mNotifyManager", "Landroid/app/NotificationManager;", "msgId", "", "onCreate", "", "onMessageReceived", "remoteMessage", "Lcom/google/firebase/messaging/RemoteMessage;", "onNewToken", "p0", "", "sendNotification", TtmlNode.TAG_BODY, "data", "", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MyFireBaseMessagingService extends FirebaseMessagingService {
    public static final Companion Companion = new Companion(null);
    private NotificationManager mNotifyManager;
    private int msgId;

    /* compiled from: MyFireBaseMessagingService.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/service/MyFireBaseMessagingService$Companion;", "", "()V", TtmlNode.START, "", "context", "Landroid/content/Context;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(context, MyFireBaseMessagingService.class);
            intent.setAction("com.google.firebase.MESSAGING_EVENT");
            context.startService(intent);
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Object systemService = getSystemService("notification");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.app.NotificationManager");
        }
        this.mNotifyManager = (NotificationManager) systemService;
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onNewToken(String p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        super.onNewToken(p0);
        Log.d("MyFireBaseMessaging", Intrinsics.stringPlus("new Token:", p0));
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Intrinsics.checkNotNullParameter(remoteMessage, "remoteMessage");
        super.onMessageReceived(remoteMessage);
        RemoteMessage.Notification notification = remoteMessage.getNotification();
        String body = notification == null ? null : notification.getBody();
        Map<String, String> data = remoteMessage.getData();
        Intrinsics.checkNotNullExpressionValue(data, "remoteMessage.data");
        sendNotification(body, data);
    }

    private final void sendNotification(String str, Map<String, String> map) {
        PendingIntent broadcast;
        MyFireBaseMessagingService myFireBaseMessagingService = this;
        Intent intent = new Intent(myFireBaseMessagingService, PushMessageClickReceiver.class);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            intent.putExtra(entry.getKey(), entry.getValue());
        }
        intent.setAction("Handle_Push_Msg");
        if (Build.VERSION.SDK_INT >= 23) {
            broadcast = PendingIntent.getBroadcast(myFireBaseMessagingService, (int) System.currentTimeMillis(), intent, 1140850688);
        } else {
            broadcast = PendingIntent.getBroadcast(myFireBaseMessagingService, (int) System.currentTimeMillis(), intent, 1073741824);
        }
        this.msgId++;
        Notification.Builder builder = new Notification.Builder(myFireBaseMessagingService);
        NotificationManager notificationManager = null;
        if (Build.VERSION.SDK_INT >= 26) {
            AudioAttributes build = new AudioAttributes.Builder().setUsage(5).setContentType(3).build();
            NotificationChannel notificationChannel = new NotificationChannel("push_msg", "push_msg_name", 3);
            notificationChannel.setSound(RingtoneManager.getDefaultUri(2), build);
            notificationChannel.enableVibration(true);
            builder.setChannelId("push_msg");
            NotificationManager notificationManager2 = this.mNotifyManager;
            if (notificationManager2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mNotifyManager");
                notificationManager2 = null;
            }
            notificationManager2.createNotificationChannel(notificationChannel);
        }
        builder.setContentTitle("MovieBoxPro").setContentText(str).setAutoCancel(true).setProgress(0, 0, false);
        if (RomUtils.isGoogle() || RomUtils.isOneplus()) {
            builder.setSmallIcon(R.mipmap.ic_white_icon);
            int i = Build.VERSION.SDK_INT;
            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_logo));
        } else {
            builder.setSmallIcon(R.mipmap.ic_logo);
        }
        builder.setSound(RingtoneManager.getDefaultUri(2));
        builder.setContentIntent(broadcast);
        NotificationManager notificationManager3 = this.mNotifyManager;
        if (notificationManager3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mNotifyManager");
        } else {
            notificationManager = notificationManager3;
        }
        notificationManager.notify(this.msgId, builder.build());
    }
}
