package com.movieboxpro.android.view.activity.videoplayer.controller;

import android.graphics.Bitmap;
import tv.danmaku.ijk.media.player.misc.IjkTrackInfo;
/* loaded from: classes3.dex */
public interface MediaPlayerControl {
    Bitmap doScreenShot();

    int getBufferedPercentage();

    int getCurrTrack();

    long getCurrentPosition();

    long getDuration();

    long getTcpSpeed();

    String getTitle();

    IjkTrackInfo[] getTrackInfo();

    int[] getVideoSize();

    boolean isFullScreen();

    boolean isMute();

    boolean isPlaying();

    void pause();

    void refresh();

    void retry();

    void seekTo(long j);

    void setLock(boolean z);

    void setMirrorRotation(boolean z);

    void setMute(boolean z);

    void setRotation(float f);

    void setScreenScale(int i);

    void setSpeed(float f);

    void setTrackInfo(int i);

    void start();

    void startFullScreen();

    void stopFullScreen();
}
