package com.movieboxpro.android.view.listener;
/* loaded from: classes3.dex */
public interface PlayListener {
    void onBuffer(float f);

    void onComplete();

    void onError();

    void onPause();

    void onPlay();

    void onPositionChanged();

    void onStart();
}
