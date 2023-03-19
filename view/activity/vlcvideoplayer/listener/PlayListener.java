package com.movieboxpro.android.view.activity.vlcvideoplayer.listener;
/* loaded from: classes3.dex */
public interface PlayListener {
    void onBuffer(float f);

    void onComplete();

    void onError(String str);

    void onPause();

    void onPlay();

    void onPositionChanged();

    void onStart();
}
