package com.movieboxpro.android.view.activity.videoplayer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.movieboxpro.android.event.FloatEvent;
import org.greenrobot.eventbus.EventBus;
/* loaded from: classes3.dex */
public class HomeWatcherReceiver extends BroadcastReceiver {
    private static final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";
    private static final String SYSTEM_DIALOG_REASON_KEY = "reason";
    private static final String SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps";
    private static final String TAG = "HomeReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.CLOSE_SYSTEM_DIALOGS")) {
            String stringExtra = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
            Log.d(TAG, "reason: " + stringExtra);
            if (SYSTEM_DIALOG_REASON_HOME_KEY.equals(stringExtra)) {
                Log.d(TAG, SYSTEM_DIALOG_REASON_HOME_KEY);
                EventBus.getDefault().post(new FloatEvent());
            }
        }
    }
}
