package com.movieboxpro.android.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.os.Parcelable;
import org.greenrobot.eventbus.EventBus;
/* loaded from: classes3.dex */
public class WifiConnectChangedReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Parcelable parcelableExtra;
        if (!"android.net.wifi.STATE_CHANGE".equals(intent.getAction()) || (parcelableExtra = intent.getParcelableExtra("networkInfo")) == null) {
            return;
        }
        NetworkInfo networkInfo = (NetworkInfo) parcelableExtra;
        EventBus.getDefault().post(networkInfo);
        networkInfo.getState();
    }
}
