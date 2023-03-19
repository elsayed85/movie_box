package com.movieboxpro.android.view.activity.exoplayer.player;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.TextureView;
import android.view.View;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;
import com.dueeeke.videoplayer.util.PlayerUtils;
import com.movieboxpro.android.R;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.view.activity.videoplayer.controller.BaseVideoController;
import com.movieboxpro.android.view.activity.videoplayer.widget.ResizeSurfaceView;
import com.movieboxpro.android.view.activity.videoplayer.widget.ResizeTextureView;
import com.movieboxpro.android.view.activity.vlcvideoplayer.listener.OnVideoViewStateChangeListener;
import org.videolan.libvlc.RendererItem;
import tv.danmaku.ijk.media.player.misc.IjkTrackInfo;
/* loaded from: classes3.dex */
public class ExoVideoView extends BaseExoVideoView {
    private static final int FULLSCREEN_FLAGS = 4098;
    public static final int SCREEN_SCALE_16_9 = 1;
    public static final int SCREEN_SCALE_4_3 = 2;
    public static final int SCREEN_SCALE_CENTER_CROP = 5;
    public static final int SCREEN_SCALE_DEFAULT = 0;
    public static final int SCREEN_SCALE_MATCH_PARENT = 3;
    public static final int SCREEN_SCALE_MATCH_WIDTH_PARENT = 6;
    public static final int SCREEN_SCALE_ORIGINAL = 4;
    protected int mCurrentScreenScale;
    protected View mHideNavBarView;
    protected boolean mIsFullScreen;
    protected FrameLayout mPlayerContainer;
    protected SurfaceTexture mSurfaceTexture;
    protected ResizeSurfaceView mSurfaceView;
    protected ResizeTextureView mTextureView;
    protected int[] mVideoSize;

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void addSubtitleTrack(String str, boolean z) {
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public int getCurrTrack() {
        return 0;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public IjkTrackInfo[] getIjkTrackInfo() {
        return new IjkTrackInfo[0];
    }

    public boolean onBackPressed() {
        return false;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerEventListener
    public void onSeekComplete(long j, long j2) {
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void setEnableHardCodec(boolean z) {
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void setRenderer(RendererItem rendererItem) {
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void setVideoController(BaseVideoController baseVideoController) {
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void setVlcVideoController(com.movieboxpro.android.view.activity.vlcvideoplayer.controller.BaseVideoController baseVideoController) {
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.BaseExoVideoView, com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void updateVideoView() {
    }

    public ExoVideoView(Context context) {
        this(context, null);
    }

    public ExoVideoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ExoVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCurrentScreenScale = 0;
        this.mVideoSize = new int[]{0, 0};
        initView();
    }

    protected void initView() {
        FrameLayout frameLayout = new FrameLayout(getContext());
        this.mPlayerContainer = frameLayout;
        frameLayout.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        addView(this.mPlayerContainer, new FrameLayout.LayoutParams(-1, -1));
        View view = new View(getContext());
        this.mHideNavBarView = view;
        view.setSystemUiVisibility(4098);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.view.activity.exoplayer.player.BaseExoVideoView
    public void initPlayer() {
        super.initPlayer();
        addDisplay();
    }

    public void addDisplay() {
        addSurfaceView();
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.BaseExoVideoView
    protected void setPlayState(int i) {
        this.mCurrentPlayState = i;
        if (this.mVideoController != null) {
            this.mVideoController.setPlayState(i);
        }
        if (this.mOnVideoViewStateChangeListeners != null) {
            for (OnVideoViewStateChangeListener onVideoViewStateChangeListener : this.mOnVideoViewStateChangeListeners) {
                onVideoViewStateChangeListener.onPlayStateChanged(i);
            }
        }
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.BaseExoVideoView
    protected void setPlayerState(int i) {
        this.mCurrentPlayerState = i;
        if (this.mVideoController != null) {
            this.mVideoController.setPlayerState(i);
        }
        if (this.mOnVideoViewStateChangeListeners != null) {
            for (OnVideoViewStateChangeListener onVideoViewStateChangeListener : this.mOnVideoViewStateChangeListeners) {
                onVideoViewStateChangeListener.onPlayerStateChanged(i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.view.activity.exoplayer.player.BaseExoVideoView
    public void startPlay() {
        if (this.mPlayerConfig.addToPlayerManager) {
            VideoViewManager.instance().releaseVideoPlayer();
            VideoViewManager.instance().setCurrentVideoPlayer(this);
        }
        if (checkNetwork()) {
            return;
        }
        super.startPlay();
    }

    private void addSurfaceView() {
        this.mPlayerContainer.removeView(this.mSurfaceView);
        ResizeSurfaceView resizeSurfaceView = new ResizeSurfaceView(getContext());
        this.mSurfaceView = resizeSurfaceView;
        SurfaceHolder holder = resizeSurfaceView.getHolder();
        holder.addCallback(new SurfaceHolder.Callback() { // from class: com.movieboxpro.android.view.activity.exoplayer.player.ExoVideoView.1
            @Override // android.view.SurfaceHolder.Callback
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                if (ExoVideoView.this.mMediaPlayer != null) {
                    ExoVideoView.this.mMediaPlayer.setDisplay(surfaceHolder);
                }
            }
        });
        holder.setFormat(1);
        this.mPlayerContainer.addView(this.mSurfaceView, 0, new FrameLayout.LayoutParams(-1, -1, 17));
    }

    private void addTextureView() {
        this.mSurfaceTexture = null;
        ResizeTextureView resizeTextureView = new ResizeTextureView(getContext());
        this.mTextureView = resizeTextureView;
        resizeTextureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.player.ExoVideoView.2
            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
                if (ExoVideoView.this.mSurfaceTexture != null) {
                    ExoVideoView.this.mTextureView.setSurfaceTexture(ExoVideoView.this.mSurfaceTexture);
                } else {
                    ExoVideoView.this.mSurfaceTexture = surfaceTexture;
                    ExoVideoView.this.mMediaPlayer.setSurface(new Surface(surfaceTexture));
                }
                ExoVideoView.this.mMediaPlayer.setTextureView(ExoVideoView.this.mTextureView);
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                return ExoVideoView.this.mSurfaceTexture == null;
            }
        });
        new FrameLayout.LayoutParams(-1, -1, 17);
    }

    protected boolean checkNetwork() {
        if (PlayerUtils.getNetworkType(getContext()) == 4 && !IS_PLAY_ON_MOBILE_NETWORK) {
            if (this.mVideoController != null) {
                this.mVideoController.showStatusView(getResources().getString(R.string.dkplayer_wifi_tip));
            }
            return true;
        } else if (Network.isConnected(getContext()) || this.mVideoController == null || this.mVideoController.isLocalFile()) {
            return false;
        } else {
            this.mVideoController.showStatusView("No internet");
            return true;
        }
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.BaseExoVideoView, com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void release() {
        super.release();
        this.mPlayerContainer.removeView(this.mTextureView);
        this.mPlayerContainer.removeView(this.mSurfaceView);
        SurfaceTexture surfaceTexture = this.mSurfaceTexture;
        if (surfaceTexture != null) {
            surfaceTexture.release();
            this.mSurfaceTexture = null;
        }
        this.mCurrentScreenScale = 0;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void startFullScreen() {
        Activity scanForActivity;
        if (this.mVideoController == null || (scanForActivity = PlayerUtils.scanForActivity(this.mVideoController.getContext())) == null || this.mIsFullScreen) {
            return;
        }
        PlayerUtils.hideActionBar(this.mVideoController.getContext());
        removeView(this.mHideNavBarView);
        addView(this.mHideNavBarView);
        scanForActivity.getWindow().setFlags(1024, 1024);
        this.mOrientationEventListener.enable();
        this.mIsFullScreen = true;
        setPlayerState(11);
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void stopFullScreen() {
        Activity scanForActivity;
        if (this.mVideoController == null || (scanForActivity = PlayerUtils.scanForActivity(this.mVideoController.getContext())) == null || !this.mIsFullScreen) {
            return;
        }
        if (!this.mPlayerConfig.mAutoRotate) {
            this.mOrientationEventListener.disable();
        }
        PlayerUtils.hideActionBar(this.mVideoController.getContext());
        removeView(this.mHideNavBarView);
        addView(this.mHideNavBarView);
        setSystemUiVisibility(5894);
        scanForActivity.getWindow().setFlags(1024, 1024);
        requestFocus();
        this.mIsFullScreen = false;
        setPlayerState(10);
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public boolean isFullScreen() {
        return this.mIsFullScreen;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void retry() {
        addDisplay();
        this.mCurrentPosition = 0L;
        startPrepare(true);
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void retryPlay() {
        if (this.mMediaPlayer != null) {
            this.mCurrentPosition = this.mMediaPlayer.getCurrentPosition();
        }
        addDisplay();
        startPrepare(true);
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.BaseExoVideoView, com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerEventListener
    public void onInfo(int i, int i2) {
        super.onInfo(i, i2);
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerEventListener
    public void onVideoSizeChanged(int i, int i2) {
        int[] iArr = this.mVideoSize;
        iArr[0] = i;
        iArr[1] = i2;
        this.mSurfaceView.setScreenScale(this.mCurrentScreenScale);
        this.mSurfaceView.setVideoSize(i, i2);
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            this.mHideNavBarView.setSystemUiVisibility(4098);
        }
        if (isInPlaybackState()) {
            if (this.mPlayerConfig.mAutoRotate || this.mIsFullScreen) {
                if (z) {
                    postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.activity.exoplayer.player.ExoVideoView.3
                        @Override // java.lang.Runnable
                        public void run() {
                            ExoVideoView.this.mOrientationEventListener.enable();
                        }
                    }, 800L);
                } else {
                    this.mOrientationEventListener.disable();
                }
            }
        }
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void setExoVideoController(com.movieboxpro.android.view.activity.exoplayer.controller.BaseVideoController baseVideoController) {
        this.mPlayerContainer.removeAllViews();
        this.mVideoController = baseVideoController;
        if (baseVideoController != null) {
            baseVideoController.setMediaPlayer(this);
            this.mPlayerContainer.addView(this.mVideoController, new FrameLayout.LayoutParams(-1, -1));
        }
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void setScreenScale(int i) {
        this.mCurrentScreenScale = i;
        ResizeSurfaceView resizeSurfaceView = this.mSurfaceView;
        if (resizeSurfaceView != null) {
            resizeSurfaceView.setScreenScale(i);
            return;
        }
        ResizeTextureView resizeTextureView = this.mTextureView;
        if (resizeTextureView != null) {
            resizeTextureView.setScreenScale(i);
        }
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void setMirrorRotation(boolean z) {
        ResizeTextureView resizeTextureView = this.mTextureView;
        if (resizeTextureView != null) {
            resizeTextureView.setScaleX(z ? -1.0f : 1.0f);
        }
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public Bitmap doScreenShot() {
        ResizeTextureView resizeTextureView = this.mTextureView;
        if (resizeTextureView != null) {
            return resizeTextureView.getBitmap();
        }
        return null;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public int[] getVideoSize() {
        return this.mMediaPlayer.getVideoSize();
    }

    @Override // android.view.View, com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void setRotation(float f) {
        ResizeTextureView resizeTextureView = this.mTextureView;
        if (resizeTextureView != null) {
            resizeTextureView.setRotation(f);
            this.mTextureView.requestLayout();
        }
        ResizeSurfaceView resizeSurfaceView = this.mSurfaceView;
        if (resizeSurfaceView != null) {
            resizeSurfaceView.setRotation(f);
            this.mSurfaceView.requestLayout();
        }
    }
}
