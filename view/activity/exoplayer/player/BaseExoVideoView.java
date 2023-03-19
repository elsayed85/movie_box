package com.movieboxpro.android.view.activity.exoplayer.player;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.OrientationEventListener;
import android.widget.FrameLayout;
import com.dueeeke.videoplayer.player.PlayerConfig;
import com.dueeeke.videoplayer.util.PlayerUtils;
import com.google.android.exoplayer2.source.TrackGroup;
import com.movieboxpro.android.model.ExoAudioTrackInfo;
import com.movieboxpro.android.view.activity.exoplayer.controller.BaseVideoController;
import com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl;
import com.movieboxpro.android.view.activity.vlcvideoplayer.listener.OnVideoViewStateChangeListener;
import com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.interfaces.IMedia;
/* loaded from: classes3.dex */
public abstract class BaseExoVideoView extends FrameLayout implements MediaPlayerControl, PlayerEventListener {
    public static boolean IS_PLAY_ON_MOBILE_NETWORK = false;
    protected static final int LANDSCAPE = 2;
    public static final int PLAYER_FULL_SCREEN = 11;
    public static final int PLAYER_NORMAL = 10;
    protected static final int PORTRAIT = 1;
    protected static final int REVERSE_LANDSCAPE = 3;
    public static final int STATE_BUFFERED = 7;
    public static final int STATE_BUFFERING = 6;
    public static final int STATE_ERROR = -1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_OPENING = 9;
    public static final int STATE_PAUSED = 4;
    public static final int STATE_PLAYBACK_COMPLETED = 5;
    public static final int STATE_PLAYING = 3;
    public static final int STATE_PREPARED = 2;
    public static final int STATE_PREPARING = 1;
    public static final int STATE_START = 8;
    private static final String TAG = "BaseIjkVideoView";
    private int count;
    protected AssetFileDescriptor mAssetFileDescriptor;
    protected AudioFocusHelper mAudioFocusHelper;
    protected AudioManager mAudioManager;
    protected int mCurrentOrientation;
    protected int mCurrentPlayState;
    protected int mCurrentPlayerState;
    protected long mCurrentPosition;
    protected String mCurrentTitle;
    protected String mCurrentUrl;
    protected String mCurrentUrl2;
    protected Map<String, String> mHeaders;
    protected boolean mIsLockFullScreen;
    protected boolean mIsMute;
    protected ExoAbstractPlayer mMediaPlayer;
    protected List<OnVideoViewStateChangeListener> mOnVideoViewStateChangeListeners;
    protected OrientationEventListener mOrientationEventListener;
    protected PlayerConfig mPlayerConfig;
    protected long mSeekWhenPrepared;
    protected BaseVideoController mVideoController;
    private PlayListener playListener;

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public List<MediaPlayer.TrackDescription> getAudioTracks() {
        return null;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerEventListener
    public void onInfo(int i, int i2) {
    }

    protected abstract void setPlayState(int i);

    protected abstract void setPlayerState(int i);

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void updateVideoView() {
    }

    static /* synthetic */ int access$008(BaseExoVideoView baseExoVideoView) {
        int i = baseExoVideoView.count;
        baseExoVideoView.count = i + 1;
        return i;
    }

    public ExoAbstractPlayer getMediaPlayer() {
        return this.mMediaPlayer;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void setVolume(int i) {
        ExoAbstractPlayer exoAbstractPlayer = this.mMediaPlayer;
        if (exoAbstractPlayer != null) {
            exoAbstractPlayer.setVolume(i);
        }
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public int getVolume() {
        ExoAbstractPlayer exoAbstractPlayer = this.mMediaPlayer;
        if (exoAbstractPlayer != null) {
            return exoAbstractPlayer.getVolume();
        }
        return 0;
    }

    protected void onOrientationPortrait(Activity activity) {
        int i;
        if (this.mIsLockFullScreen || !this.mPlayerConfig.mAutoRotate || (i = this.mCurrentOrientation) == 1) {
            return;
        }
        if ((i == 2 || i == 3) && !isFullScreen()) {
            this.mCurrentOrientation = 1;
            return;
        }
        this.mCurrentOrientation = 1;
        activity.setRequestedOrientation(1);
        stopFullScreen();
    }

    protected void onOrientationLandscape(Activity activity) {
        int i = this.mCurrentOrientation;
        if (i == 2) {
            return;
        }
        if (i == 1 && activity.getRequestedOrientation() != 8 && isFullScreen()) {
            this.mCurrentOrientation = 2;
            return;
        }
        this.mCurrentOrientation = 2;
        if (!isFullScreen()) {
            startFullScreen();
        }
        activity.setRequestedOrientation(0);
    }

    protected void onOrientationReverseLandscape(Activity activity) {
        int i = this.mCurrentOrientation;
        if (i == 3) {
            return;
        }
        if (i == 1 && activity.getRequestedOrientation() != 0 && isFullScreen()) {
            this.mCurrentOrientation = 3;
            return;
        }
        this.mCurrentOrientation = 3;
        if (!isFullScreen()) {
            startFullScreen();
        }
        activity.setRequestedOrientation(8);
    }

    public BaseExoVideoView(Context context) {
        this(context, null);
    }

    public BaseExoVideoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BaseExoVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCurrentTitle = "";
        this.mCurrentPlayState = 0;
        this.mCurrentPlayerState = 10;
        this.mCurrentOrientation = 0;
        this.mOrientationEventListener = new OrientationEventListener(getContext()) { // from class: com.movieboxpro.android.view.activity.exoplayer.player.BaseExoVideoView.1
            private long mLastTime;

            @Override // android.view.OrientationEventListener
            public void onOrientationChanged(int i2) {
                Activity scanForActivity;
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - this.mLastTime < 300 || BaseExoVideoView.this.mVideoController == null || (scanForActivity = PlayerUtils.scanForActivity(BaseExoVideoView.this.mVideoController.getContext())) == null) {
                    return;
                }
                if (i2 >= 340) {
                    BaseExoVideoView.this.onOrientationPortrait(scanForActivity);
                } else if (i2 >= 260 && i2 <= 280) {
                    BaseExoVideoView.this.onOrientationLandscape(scanForActivity);
                } else if (i2 >= 70 && i2 <= 90) {
                    BaseExoVideoView.this.onOrientationReverseLandscape(scanForActivity);
                }
                this.mLastTime = currentTimeMillis;
            }
        };
        this.playListener = new PlayListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.player.BaseExoVideoView.2
            @Override // com.movieboxpro.android.view.activity.exoplayer.player.PlayListener
            public void onPause() {
            }

            @Override // com.movieboxpro.android.view.activity.exoplayer.player.PlayListener
            public void onStart() {
                BaseExoVideoView.this.setPlayState(9);
            }

            @Override // com.movieboxpro.android.view.activity.exoplayer.player.PlayListener
            public void onPlay() {
                BaseExoVideoView.this.getAudioTracks();
                BaseExoVideoView.this.setPlayState(8);
                if (BaseExoVideoView.this.mVideoController != null) {
                    BaseExoVideoView.this.mVideoController.setLoading(false);
                    BaseExoVideoView.this.mVideoController.setPlaying(true);
                }
            }

            @Override // com.movieboxpro.android.view.activity.exoplayer.player.PlayListener
            public void onBuffer(float f) {
                if (f >= 100.0f) {
                    BaseExoVideoView.this.setPlayState(3);
                    if (BaseExoVideoView.this.mVideoController != null) {
                        BaseExoVideoView.this.mVideoController.setPlaying(true);
                        BaseExoVideoView.this.mVideoController.setLoading(false);
                    }
                } else {
                    BaseExoVideoView.this.setPlayState(6);
                    if (BaseExoVideoView.this.mVideoController != null) {
                        BaseExoVideoView.this.mVideoController.setPlaying(false);
                        BaseExoVideoView.this.mVideoController.setLoading(true);
                    }
                }
                if (BaseExoVideoView.this.mVideoController != null) {
                    BaseExoVideoView.this.mVideoController.setBufferProgress((int) f);
                }
            }

            @Override // com.movieboxpro.android.view.activity.exoplayer.player.PlayListener
            public void onComplete() {
                BaseExoVideoView.this.onCompletion();
            }

            @Override // com.movieboxpro.android.view.activity.exoplayer.player.PlayListener
            public void onError(String str) {
                BaseExoVideoView.this.setPlayState(-1);
                if (BaseExoVideoView.this.mVideoController != null) {
                    BaseExoVideoView.this.mVideoController.setPlaying(false);
                    BaseExoVideoView.this.mVideoController.showErrorView(str);
                }
            }

            @Override // com.movieboxpro.android.view.activity.exoplayer.player.PlayListener
            public void onPositionChanged() {
                if (BaseExoVideoView.this.mMediaPlayer != null) {
                    BaseExoVideoView baseExoVideoView = BaseExoVideoView.this;
                    baseExoVideoView.mCurrentPosition = baseExoVideoView.mMediaPlayer.getCurrentPosition();
                }
                if (BaseExoVideoView.this.mVideoController != null && BaseExoVideoView.this.mMediaPlayer != null) {
                    BaseExoVideoView.this.mVideoController.setProgress((int) BaseExoVideoView.this.mCurrentPosition, (int) BaseExoVideoView.this.mMediaPlayer.getDuration());
                }
                BaseExoVideoView.access$008(BaseExoVideoView.this);
                if (BaseExoVideoView.this.count == 2) {
                    BaseExoVideoView.this.count = 0;
                }
            }

            @Override // com.movieboxpro.android.view.activity.exoplayer.player.PlayListener
            public void onVideoSizeChanged(int i2, int i3) {
                BaseExoVideoView.this.onVideoSizeChanged(i2, i3);
            }
        };
        this.mPlayerConfig = new PlayerConfig.Builder().build();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void initPlayer() {
        ExoAbstractPlayer exoAbstractPlayer = this.mMediaPlayer;
        if (exoAbstractPlayer == null) {
            this.mMediaPlayer = new CustomExoPlayer(getContext());
            Log.d(TAG, "SSSSSSend");
            this.mMediaPlayer.bindVideoView(this);
            this.mMediaPlayer.initPlayer();
            this.mMediaPlayer.setEnableMediaCodec(this.mPlayerConfig.enableMediaCodec);
            this.mMediaPlayer.setLooping(this.mPlayerConfig.isLooping);
        } else {
            exoAbstractPlayer.release();
            this.mMediaPlayer = null;
            this.mMediaPlayer = new CustomExoPlayer(getContext());
            Log.d(TAG, "SSSSSSend");
            this.mMediaPlayer.bindVideoView(this);
            this.mMediaPlayer.initPlayer();
            this.mMediaPlayer.setEnableMediaCodec(this.mPlayerConfig.enableMediaCodec);
            this.mMediaPlayer.setLooping(this.mPlayerConfig.isLooping);
        }
        if (this.mMediaPlayer != null) {
            if (this.playListener == null) {
                this.playListener = new PlayListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.player.BaseExoVideoView.3
                    @Override // com.movieboxpro.android.view.activity.exoplayer.player.PlayListener
                    public void onPause() {
                    }

                    @Override // com.movieboxpro.android.view.activity.exoplayer.player.PlayListener
                    public void onStart() {
                        BaseExoVideoView.this.setPlayState(9);
                    }

                    @Override // com.movieboxpro.android.view.activity.exoplayer.player.PlayListener
                    public void onPlay() {
                        BaseExoVideoView.this.getAudioTracks();
                        BaseExoVideoView.this.setPlayState(8);
                        if (BaseExoVideoView.this.mVideoController != null) {
                            BaseExoVideoView.this.mVideoController.setLoading(false);
                            BaseExoVideoView.this.mVideoController.setPlaying(true);
                        }
                    }

                    @Override // com.movieboxpro.android.view.activity.exoplayer.player.PlayListener
                    public void onBuffer(float f) {
                        if (f >= 100.0f) {
                            BaseExoVideoView.this.setPlayState(3);
                            if (BaseExoVideoView.this.mVideoController != null) {
                                BaseExoVideoView.this.mVideoController.setPlaying(true);
                                BaseExoVideoView.this.mVideoController.setLoading(false);
                            }
                        } else {
                            BaseExoVideoView.this.setPlayState(6);
                            if (BaseExoVideoView.this.mVideoController != null) {
                                BaseExoVideoView.this.mVideoController.setPlaying(false);
                                BaseExoVideoView.this.mVideoController.setLoading(true);
                            }
                        }
                        if (BaseExoVideoView.this.mVideoController != null) {
                            BaseExoVideoView.this.mVideoController.setBufferProgress((int) f);
                        }
                    }

                    @Override // com.movieboxpro.android.view.activity.exoplayer.player.PlayListener
                    public void onComplete() {
                        BaseExoVideoView.this.onCompletion();
                    }

                    @Override // com.movieboxpro.android.view.activity.exoplayer.player.PlayListener
                    public void onError(String str) {
                        BaseExoVideoView.this.setPlayState(-1);
                        if (BaseExoVideoView.this.mVideoController != null) {
                            BaseExoVideoView.this.mVideoController.setPlaying(false);
                            BaseExoVideoView.this.mVideoController.showErrorView(str);
                        }
                    }

                    @Override // com.movieboxpro.android.view.activity.exoplayer.player.PlayListener
                    public void onPositionChanged() {
                        if (BaseExoVideoView.this.mMediaPlayer != null) {
                            BaseExoVideoView baseExoVideoView = BaseExoVideoView.this;
                            baseExoVideoView.mCurrentPosition = baseExoVideoView.mMediaPlayer.getCurrentPosition();
                        }
                        if (BaseExoVideoView.this.mVideoController != null && BaseExoVideoView.this.mMediaPlayer != null) {
                            BaseExoVideoView.this.mVideoController.setProgress((int) BaseExoVideoView.this.mCurrentPosition, (int) BaseExoVideoView.this.mMediaPlayer.getDuration());
                        }
                        BaseExoVideoView.access$008(BaseExoVideoView.this);
                        if (BaseExoVideoView.this.count == 2) {
                            BaseExoVideoView.this.count = 0;
                        }
                    }

                    @Override // com.movieboxpro.android.view.activity.exoplayer.player.PlayListener
                    public void onVideoSizeChanged(int i, int i2) {
                        BaseExoVideoView.this.onVideoSizeChanged(i, i2);
                    }
                };
            }
            this.mMediaPlayer.registerListener(this.playListener);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void setAudioDelay(long j) {
        ExoAbstractPlayer exoAbstractPlayer = this.mMediaPlayer;
        if (exoAbstractPlayer != null) {
            exoAbstractPlayer.setAudioDelay(j);
        }
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public long getAudioDelay() {
        ExoAbstractPlayer exoAbstractPlayer = this.mMediaPlayer;
        if (exoAbstractPlayer != null) {
            return exoAbstractPlayer.getAudioDelay();
        }
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void startPrepare(boolean z) {
        if (TextUtils.isEmpty(this.mCurrentUrl) && this.mAssetFileDescriptor == null) {
            BaseVideoController baseVideoController = this.mVideoController;
            if (baseVideoController != null) {
                baseVideoController.showErrorView("Video url is empty,you can try switch quality or report to us");
                return;
            }
            return;
        }
        ExoAbstractPlayer exoAbstractPlayer = this.mMediaPlayer;
        if (exoAbstractPlayer != null) {
            if (z) {
                exoAbstractPlayer.reset();
            }
            this.mMediaPlayer.setEnableMediaCodec(this.mPlayerConfig.enableMediaCodec);
            AssetFileDescriptor assetFileDescriptor = this.mAssetFileDescriptor;
            if (assetFileDescriptor != null) {
                this.mMediaPlayer.setDataSource(assetFileDescriptor);
            } else {
                this.mMediaPlayer.setDataSource(this.mCurrentUrl, this.mCurrentPosition);
            }
            this.mMediaPlayer.prepareAsync();
            setPlayState(1);
            setPlayerState(isFullScreen() ? 11 : 10);
        }
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public int getCurrAudioIndex() {
        return this.mMediaPlayer.getCurrAudioIndex();
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public int addAudioTrack(String str) {
        return this.mMediaPlayer.addAudioTrack(str);
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void start() {
        Log.d(TAG, "SSSSSS" + this.mCurrentPlayState);
        if (this.mMediaPlayer == null) {
            startPlay();
        } else if (isInPlaybackState()) {
            startInPlaybackState();
        }
        setKeepScreenOn(true);
        AudioFocusHelper audioFocusHelper = this.mAudioFocusHelper;
        if (audioFocusHelper != null) {
            audioFocusHelper.requestFocus();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void startPlay() {
        if (!this.mPlayerConfig.disableAudioFocus) {
            this.mAudioManager = (AudioManager) getContext().getApplicationContext().getSystemService("audio");
            this.mAudioFocusHelper = new AudioFocusHelper();
        }
        long j = this.mSeekWhenPrepared;
        if (j != 0) {
            this.mCurrentPosition = j;
        }
        this.mOrientationEventListener.enable();
        initPlayer();
        startPrepare(false);
    }

    protected void startInPlaybackState() {
        this.mMediaPlayer.start();
        setPlayState(3);
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void pause() {
        if (isPlaying()) {
            this.mMediaPlayer.pause();
            setPlayState(4);
            setKeepScreenOn(false);
            AudioFocusHelper audioFocusHelper = this.mAudioFocusHelper;
            if (audioFocusHelper != null) {
                audioFocusHelper.abandonFocus();
            }
        }
    }

    public void resume() {
        if (!isInPlaybackState() || this.mMediaPlayer.isPlaying()) {
            return;
        }
        this.mMediaPlayer.start();
        setPlayState(3);
        AudioFocusHelper audioFocusHelper = this.mAudioFocusHelper;
        if (audioFocusHelper != null) {
            audioFocusHelper.requestFocus();
        }
        setKeepScreenOn(true);
    }

    public void stopPlayback() {
        ExoAbstractPlayer exoAbstractPlayer;
        if (this.mPlayerConfig.savingProgress && isInPlaybackState() && (exoAbstractPlayer = this.mMediaPlayer) != null) {
            exoAbstractPlayer.stop();
            setPlayState(0);
            AudioFocusHelper audioFocusHelper = this.mAudioFocusHelper;
            if (audioFocusHelper != null) {
                audioFocusHelper.abandonFocus();
            }
            setKeepScreenOn(false);
        }
        onPlayStopped();
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void release() {
        ExoAbstractPlayer exoAbstractPlayer = this.mMediaPlayer;
        if (exoAbstractPlayer != null) {
            try {
                exoAbstractPlayer.unRegisterListener(this.playListener);
                this.playListener = null;
                this.mMediaPlayer.reset();
                this.mMediaPlayer.release();
                this.mMediaPlayer = null;
                setPlayState(0);
                if (this.mAudioFocusHelper != null) {
                    this.mAudioFocusHelper.abandonFocus();
                }
                setKeepScreenOn(false);
            } catch (NullPointerException | Exception unused) {
            }
        }
        onPlayStopped();
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void releaseView() {
        try {
            if (this.mMediaPlayer != null) {
                this.mMediaPlayer.unRegisterListener(this.playListener);
                this.playListener = null;
                setKeepScreenOn(false);
                this.mMediaPlayer.releaseView();
                if (this.mAudioFocusHelper != null) {
                    this.mAudioFocusHelper.abandonFocus();
                }
                setKeepScreenOn(false);
            }
            onPlayStopped();
        } catch (Exception unused) {
        }
    }

    private void onPlayStopped() {
        BaseVideoController baseVideoController = this.mVideoController;
        if (baseVideoController != null) {
            baseVideoController.hideStatusView();
        }
        this.mOrientationEventListener.disable();
        this.mIsLockFullScreen = false;
        this.mCurrentPosition = 0L;
    }

    public void addOnVideoViewStateChangeListener(OnVideoViewStateChangeListener onVideoViewStateChangeListener) {
        if (this.mOnVideoViewStateChangeListeners == null) {
            this.mOnVideoViewStateChangeListeners = new ArrayList();
        }
        this.mOnVideoViewStateChangeListeners.add(onVideoViewStateChangeListener);
    }

    public void removeOnVideoViewStateChangeListener(OnVideoViewStateChangeListener onVideoViewStateChangeListener) {
        List<OnVideoViewStateChangeListener> list = this.mOnVideoViewStateChangeListeners;
        if (list != null) {
            list.remove(onVideoViewStateChangeListener);
        }
    }

    public void clearOnVideoViewStateChangeListeners() {
        List<OnVideoViewStateChangeListener> list = this.mOnVideoViewStateChangeListeners;
        if (list != null) {
            list.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isInPlaybackState() {
        int i;
        return (this.mMediaPlayer == null || (i = this.mCurrentPlayState) == -1 || i == 0 || i == 1 || i == 5) ? false : true;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public long getDuration() {
        if (isInPlaybackState()) {
            return this.mMediaPlayer.getDuration();
        }
        return 0L;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public long getCurrentPosition() {
        if (isInPlaybackState()) {
            long currentPosition = this.mMediaPlayer.getCurrentPosition();
            this.mCurrentPosition = currentPosition;
            return currentPosition;
        }
        return 0L;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void seekTo(long j) {
        if (isInPlaybackState()) {
            this.mMediaPlayer.seekTo(j);
            this.mSeekWhenPrepared = 0L;
            return;
        }
        this.mSeekWhenPrepared = j;
    }

    public long getStartPos() {
        return this.mSeekWhenPrepared;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public boolean isPlaying() {
        return isInPlaybackState() && this.mMediaPlayer.isPlaying();
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public boolean isPause() {
        return this.mCurrentPlayState == 4;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public int getBufferedPercentage() {
        ExoAbstractPlayer exoAbstractPlayer = this.mMediaPlayer;
        if (exoAbstractPlayer != null) {
            return exoAbstractPlayer.getBufferedPercentage();
        }
        return 0;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void setMute(boolean z) {
        if (this.mMediaPlayer != null) {
            this.mIsMute = z;
            float f = z ? 0.0f : 1.0f;
            this.mMediaPlayer.setVolume(f, f);
        }
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public boolean isMute() {
        return this.mIsMute;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void setLock(boolean z) {
        this.mIsLockFullScreen = z;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public String getTitle() {
        return this.mCurrentTitle;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerEventListener
    public void onError() {
        setPlayState(-1);
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerEventListener
    public void onCompletion() {
        setPlayState(5);
        setKeepScreenOn(false);
        this.mCurrentPosition = 0L;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerEventListener
    public void onPrepared() {
        setPlayState(2);
        long j = this.mCurrentPosition;
        if (j > 0) {
            seekTo(j);
        }
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void setPlayerConfig(PlayerConfig playerConfig) {
        this.mPlayerConfig = playerConfig;
    }

    public int getCurrentPlayerState() {
        return this.mCurrentPlayerState;
    }

    public int getCurrentPlayState() {
        return this.mCurrentPlayState;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public long getTcpSpeed() {
        return this.mMediaPlayer.getTcpSpeed();
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void setSpeed(float f) {
        ExoAbstractPlayer exoAbstractPlayer;
        if (!isInPlaybackState() || (exoAbstractPlayer = this.mMediaPlayer) == null) {
            return;
        }
        exoAbstractPlayer.setSpeed(f);
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public IMedia.AudioTrack[] getTrackInfo() {
        if (isInPlaybackState()) {
        }
        return null;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public List<ExoAudioTrackInfo> getExoAudioTracks() {
        ExoAbstractPlayer exoAbstractPlayer = this.mMediaPlayer;
        if (exoAbstractPlayer != null) {
            return exoAbstractPlayer.getAudioTracks();
        }
        return new ArrayList();
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void setTrackInfo(int i) {
        ExoAbstractPlayer exoAbstractPlayer;
        if (!isInPlaybackState() || (exoAbstractPlayer = this.mMediaPlayer) == null) {
            return;
        }
        exoAbstractPlayer.setTrackInfo(i);
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void refresh() {
        this.mCurrentPosition = 0L;
        ExoAbstractPlayer exoAbstractPlayer = this.mMediaPlayer;
        if (exoAbstractPlayer != null) {
            exoAbstractPlayer.seekTo(0L);
        }
    }

    public void setUrl(String str) {
        this.mCurrentUrl = str;
    }

    public void setUrl(String str, Map<String, String> map) {
        this.mCurrentUrl = str;
        this.mHeaders = map;
    }

    public void setAssetFileDescriptor(AssetFileDescriptor assetFileDescriptor) {
        this.mAssetFileDescriptor = assetFileDescriptor;
    }

    public void skipPositionWhenPlay(int i) {
        this.mCurrentPosition = i;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void setTitle(String str) {
        if (str != null) {
            this.mCurrentTitle = str;
        }
        BaseVideoController baseVideoController = this.mVideoController;
        if (baseVideoController != null) {
            baseVideoController.setTitle(this.mCurrentTitle);
        }
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void setExoAudioTrack(TrackGroup trackGroup) {
        ExoAbstractPlayer exoAbstractPlayer = this.mMediaPlayer;
        if (exoAbstractPlayer != null) {
            exoAbstractPlayer.setAudioTrack(trackGroup);
        }
    }

    public void setVolume(float f, float f2) {
        ExoAbstractPlayer exoAbstractPlayer = this.mMediaPlayer;
        if (exoAbstractPlayer != null) {
            exoAbstractPlayer.setVolume(f, f2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class AudioFocusHelper implements AudioManager.OnAudioFocusChangeListener {
        private int currentFocus;
        private boolean pausedForLoss;
        private boolean startRequested;

        private AudioFocusHelper() {
            this.startRequested = false;
            this.pausedForLoss = false;
            this.currentFocus = 0;
        }

        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i) {
            if (this.currentFocus == i) {
                return;
            }
            this.currentFocus = i;
            if (i == -3) {
                if (BaseExoVideoView.this.mMediaPlayer == null || !BaseExoVideoView.this.isPlaying() || BaseExoVideoView.this.mIsMute) {
                    return;
                }
                BaseExoVideoView.this.mMediaPlayer.setVolume(0.1f, 0.1f);
            } else if (i == -2 || i == -1) {
                if (BaseExoVideoView.this.isPlaying()) {
                    this.pausedForLoss = true;
                }
            } else if (i == 1 || i == 2) {
                if (this.startRequested || this.pausedForLoss) {
                    BaseExoVideoView.this.start();
                    this.startRequested = false;
                    this.pausedForLoss = false;
                }
                if (BaseExoVideoView.this.mMediaPlayer == null || BaseExoVideoView.this.mIsMute) {
                    return;
                }
                BaseExoVideoView.this.mMediaPlayer.setVolume(1.0f, 1.0f);
            }
        }

        boolean requestFocus() {
            if (this.currentFocus == 1) {
                return true;
            }
            if (BaseExoVideoView.this.mAudioManager == null) {
                return false;
            }
            if (1 == BaseExoVideoView.this.mAudioManager.requestAudioFocus(this, 3, 1)) {
                this.currentFocus = 1;
                return true;
            }
            this.startRequested = true;
            return false;
        }

        boolean abandonFocus() {
            if (BaseExoVideoView.this.mAudioManager == null) {
                return false;
            }
            this.startRequested = false;
            return 1 == BaseExoVideoView.this.mAudioManager.abandonAudioFocus(this);
        }
    }
}
