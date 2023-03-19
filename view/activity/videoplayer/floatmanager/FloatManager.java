package com.movieboxpro.android.view.activity.videoplayer.floatmanager;

import android.view.ViewGroup;
import android.view.ViewParent;
import com.dl7.player.media.IjkVideoView;
import com.movieboxpro.android.app.App;
/* loaded from: classes3.dex */
public class FloatManager {
    private static FloatManager instance;
    private boolean isShowing;
    private Class mActClass;
    private int mPlayingPosition = -1;
    private IjkVideoView ijkVideoView = new IjkVideoView(App.getInstance());
    private FloatController mFloatController = new FloatController(App.getInstance());
    private FloatView floatView = new FloatView(App.getInstance(), 0, 0);

    private FloatManager() {
    }

    public static FloatManager getInstance() {
        if (instance == null) {
            synchronized (FloatManager.class) {
                if (instance == null) {
                    instance = new FloatManager();
                }
            }
        }
        return instance;
    }

    public IjkVideoView getIjkVideoViews() {
        return this.ijkVideoView;
    }

    public void startFloatWindow() {
        if (this.isShowing) {
            return;
        }
        removePlayerFormParent();
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
    }

    public boolean onBackPress() {
        return !this.isShowing;
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

    public void setActClass(Class cls) {
        this.mActClass = cls;
    }

    public Class getActClass() {
        return this.mActClass;
    }
}
