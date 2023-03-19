package com.movieboxpro.android.receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.movieboxpro.android.utils.Utils;
import com.movieboxpro.android.view.activity.MainActivity;
import com.movieboxpro.android.view.activity.videoplayer.MoviePlayerActivity;
import com.movieboxpro.android.view.activity.videoplayer.TvPlayerActivity;
import java.util.Iterator;
import kotlin.Metadata;
/* compiled from: PopPlayerOpenReceiver.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/receiver/PopPlayerOpenReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PopPlayerOpenReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Intent intent2;
        boolean z;
        Intent[] intentArr;
        if (context == null) {
            return;
        }
        Bundle extras = intent == null ? null : intent.getExtras();
        Integer valueOf = intent != null ? Integer.valueOf(intent.getIntExtra("boxType", 1)) : null;
        if (valueOf != null && valueOf.intValue() == 1) {
            intent2 = new Intent(context, MoviePlayerActivity.class);
        } else {
            intent2 = new Intent(context, TvPlayerActivity.class);
        }
        if (extras != null) {
            intent2.putExtras(extras);
        }
        intent2.addFlags(268435456);
        Iterator<Activity> it = Utils.getActivityList().iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            } else if (it.next() instanceof MainActivity) {
                z = true;
                break;
            }
        }
        if (z) {
            intentArr = new Intent[]{intent2};
        } else {
            Intent intent3 = new Intent(context, MainActivity.class);
            intent3.addFlags(268435456);
            intent3.addFlags(67108864);
            intentArr = new Intent[]{intent3, intent2};
        }
        try {
            context.startActivities(intentArr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
