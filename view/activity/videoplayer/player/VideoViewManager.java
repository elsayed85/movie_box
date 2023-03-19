package com.movieboxpro.android.view.activity.videoplayer.player;
/* loaded from: classes3.dex */
public class VideoViewManager {
    private static VideoViewManager sInstance;
    private IjkVideoView mPlayer;

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

    public void setCurrentVideoPlayer(IjkVideoView ijkVideoView) {
        this.mPlayer = ijkVideoView;
    }

    public IjkVideoView getCurrentVideoPlayer() {
        return this.mPlayer;
    }

    public void releaseVideoPlayer() {
        IjkVideoView ijkVideoView = this.mPlayer;
        if (ijkVideoView != null) {
            ijkVideoView.release();
            this.mPlayer = null;
        }
    }

    public void stopPlayback() {
        IjkVideoView ijkVideoView = this.mPlayer;
        if (ijkVideoView != null) {
            ijkVideoView.stopPlayback();
        }
    }

    public boolean onBackPressed() {
        IjkVideoView ijkVideoView = this.mPlayer;
        return ijkVideoView != null && ijkVideoView.onBackPressed();
    }
}
