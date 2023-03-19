package com.movieboxpro.android.view.widget;

import android.view.View;
import com.google.android.exoplayer2.ExoPlayer;
/* loaded from: classes3.dex */
public abstract class OnMultiClickListener implements View.OnClickListener {
    private static final int MIN_CLICK_DELAY_TIME = 2000;
    private static long lastClickTime;

    public abstract void onMultiClick(View view);

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - lastClickTime >= ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS) {
            lastClickTime = currentTimeMillis;
            onMultiClick(view);
        }
    }
}
