package com.movieboxpro.android.view.activity.exoplayer.player;
/* loaded from: classes3.dex */
public class VideoViewManager {
    private static VideoViewManager sInstance;
    private ExoVideoView mPlayer;

    private VideoViewManager() {
    }

    public static VideoViewManager instance() {
        if (sInstance == null) {
            synchronized (VideoViewManager.class) {
                if (sInstance == null) {
                    sInstance = new VideoViewManager();
                }
            }
        }
        return sInstance;
    }

    public void setCurrentVideoPlayer(ExoVideoView exoVideoView) {
        this.mPlayer = exoVideoView;
    }

    public ExoVideoView getCurrentVideoPlayer() {
        return this.mPlayer;
    }

    public void releaseVideoPlayer() {
        ExoVideoView exoVideoView = this.mPlayer;
        if (exoVideoView != null) {
            exoVideoView.release();
            this.mPlayer = null;
        }
    }

    public void stopPlayback() {
        ExoVideoView exoVideoView = this.mPlayer;
        if (exoVideoView != null) {
            exoVideoView.stopPlayback();
        }
    }

    public boolean onBackPressed() {
        ExoVideoView exoVideoView = this.mPlayer;
        return exoVideoView != null && exoVideoView.onBackPressed();
    }
}
