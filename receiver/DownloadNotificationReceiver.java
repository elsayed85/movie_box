package com.movieboxpro.android.receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.movieboxpro.android.utils.Utils;
import com.movieboxpro.android.view.activity.MainActivity;
import com.movieboxpro.android.view.activity.download.DownloadingActivity;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: DownloadNotificationReceiver.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0006H\u0002J\u001c\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016¨\u0006\u000f"}, d2 = {"Lcom/movieboxpro/android/receiver/DownloadNotificationReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "isActivityExistsInStack", "", "clz", "Ljava/lang/Class;", "Landroid/app/Activity;", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DownloadNotificationReceiver extends BroadcastReceiver {
    public static final String CHECK_DOWNLOAD = "check_download";
    public static final String CHECK_DOWNLOADED = "check_downloaded";
    public static final Companion Companion = new Companion(null);

    /* compiled from: DownloadNotificationReceiver.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/movieboxpro/android/receiver/DownloadNotificationReceiver$Companion;", "", "()V", "CHECK_DOWNLOAD", "", "CHECK_DOWNLOADED", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent == null ? null : intent.getAction();
        if (Intrinsics.areEqual(CHECK_DOWNLOAD, action)) {
            if (isActivityExistsInStack(MainActivity.class)) {
                DownloadingActivity.startWithFlags(context);
                return;
            }
            Intent intent2 = new Intent(context, MainActivity.class);
            intent2.setFlags(268435456);
            Intent[] intentArr = {intent2, new Intent(context, DownloadingActivity.class)};
            if (context == null) {
                return;
            }
            context.startActivities(intentArr);
        } else if (Intrinsics.areEqual(CHECK_DOWNLOADED, action)) {
            Intent intent3 = new Intent(context, MainActivity.class);
            intent3.setFlags(268435456);
            intent3.putExtra("checkDownloaded", true);
            if (context == null) {
                return;
            }
            context.startActivity(intent3);
        }
    }

    private final boolean isActivityExistsInStack(Class<? extends Activity> cls) {
        Iterator<Activity> it = Utils.getActivityList().iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(it.next().getClass(), cls)) {
                return true;
            }
        }
        return false;
    }
}
