package com.movieboxpro.android.view.activity.videoplayer.floatmanager;

import android.os.Bundle;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.view.activity.videoplayer.videoview.NormalIjkVideoView;
/* loaded from: classes3.dex */
public class PIPManager {
    private static PIPManager instance;
    private Bundle bundle;
    private boolean isShowing;
    private Class mActClass;
    private String mAction;
    private int mPlayingPosition = -1;
    private NormalIjkVideoView ijkVideoView = new NormalIjkVideoView(App.getContext());
    private FloatController mFloatController = new FloatController(App.getContext());
    private FloatView floatView = new FloatView(App.getInstance(), 0, 0);

    private PIPManager() {
    }

    public static PIPManager getInstance() {
        if (instance == null) {
            synchronized (PIPManager.class) {
                if (instance == null) {
                    instance = new PIPManager();
                }
            }
        }
        return instance;
    }

    public NormalIjkVideoView getIjkVideoViews() {
        if (this.ijkVideoView == null) {
            this.ijkVideoView = new NormalIjkVideoView(App.getContext());
        }
        return this.ijkVideoView;
    }

    public void startFloatWindow() {
        if (this.isShowing) {
            return;
        }
        removePlayerFormParent();
        this.mFloatController.setPlayState(this.ijkVideoView.getCurrentPlayState());
        this.mFloatController.setPlayerState(this.ijkVideoView.getCurrentPlayerState());
        this.ijkVideoView.setVideoController(this.mFloatController);
        this.floatView.addView(this.ijkVideoView);
        this.floatView.addToWindow();
        this.isShowing = true;
    }

    public void stopFloatWindow() {
        if (this.isShowing) {
            this.floatView.removeFromWindow();
            removePlayerFormParent();
            this.isShowing = false;
        }
    }

    private void removePlayerFormParent() {
        ViewParent parent = this.ijkVideoView.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(this.ijkVideoView);
        }
    }

    public void setPlayingPosition(int i) {
        this.mPlayingPosition = i;
    }

    public int getPlayingPosition() {
        return this.mPlayingPosition;
    }

    public void pause() {
        if (this.isShowing) {
            return;
        }
        this.ijkVideoView.pause();
    }

    public void resume() {
        if (this.isShowing) {
            return;
        }
        this.ijkVideoView.resume();
    }

    public void reset() {
        if (this.isShowing) {
            return;
        }
        removePlayerFormParent();
        this.ijkVideoView.setVideoController(null);
        this.ijkVideoView.release();
        this.ijkVideoView = null;
        this.mPlayingPosition = -1;
        this.mActClass = null;
    }

    public boolean onBackPress() {
        return !this.isShowing && this.ijkVideoView.onBackPressed();
    }

    public boolean isStartFloatWindow() {
        return this.isShowing;
    }

    public void setFloatViewVisible() {
        if (this.isShowing) {
            this.ijkVideoView.resume();
            this.floatView.setVisibility(0);
        }
    }

    public void setAction(String str) {
        this.mAction = str;
    }

    public String getAction() {
        return this.mAction;
    }

    public void setInfo(Bundle bundle) {
        this.bundle = bundle;
    }

    public Bundle getInfo() {
        return this.bundle;
    }
}
