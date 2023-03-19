package com.movieboxpro.android.view.activity.exoplayer.player;
/* loaded from: classes3.dex */
public interface PlayListener {
    void onBuffer(float f);

    void onComplete();

    void onError(String str);

    void onPause();

    void onPlay();

    void onPositionChanged();

    void onStart();

    void onVideoSizeChanged(int i, int i2);
}
