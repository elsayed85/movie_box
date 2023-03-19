package com.movieboxpro.android.view.videocontroller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import com.google.firebase.analytics.FirebaseAnalytics;
/* loaded from: classes3.dex */
public class BatteryReceiver extends BroadcastReceiver {
    private ImageView pow;

    public BatteryReceiver(ImageView imageView) {
        this.pow = imageView;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return;
        }
        this.pow.getDrawable().setLevel((extras.getInt(FirebaseAnalytics.Param.LEVEL) * 100) / extras.getInt("scale"));
    }
}
