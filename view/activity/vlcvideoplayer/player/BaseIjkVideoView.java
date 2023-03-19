package com.movieboxpro.android.view.activity.vlcvideoplayer.player;

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
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.view.activity.vlcvideoplayer.VlcPlayerInstance;
import com.movieboxpro.android.view.activity.vlcvideoplayer.controller.BaseVideoController;
import com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl;
import com.movieboxpro.android.view.activity.vlcvideoplayer.listener.OnVideoViewStateChangeListener;
import com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayListener;
import com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.interfaces.IMedia;
/* loaded from: classes3.dex */
public abstract class BaseIjkVideoView extends FrameLayout implements MediaPlayerControl, PlayerEventListener {
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
    protected AbstractPlayer mMediaPlayer;
    protected List<OnVideoViewStateChangeListener> mOnVideoViewStateChangeListeners;
    protected OrientationEventListener mOrientationEventListener;
    protected PlayerConfig mPlayerConfig;
    protected long mSeekWhenPrepared;
    protected BaseVideoController mVideoController;
    private PlayListener playListener;

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerEventListener
    public void onInfo(int i, int i2) {
    }

    protected abstract void setPlayState(int i);

    protected abstract void setPlayerState(int i);

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void updateVideoView() {
    }

    static /* synthetic */ int access$008(BaseIjkVideoView baseIjkVideoView) {
        int i = baseIjkVideoView.count;
        baseIjkVideoView.count = i + 1;
        return i;
    }

    public AbstractPlayer getMediaPlayer() {
        return this.mMediaPlayer;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void setVolume(int i) {
        AbstractPlayer abstractPlayer = this.mMediaPlayer;
        if (abstractPlayer != null) {
            abstractPlayer.setVolume(i);
        }
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public int getVolume() {
        AbstractPlayer abstractPlayer = this.mMediaPlayer;
        if (abstractPlayer != null) {
            return abstractPlayer.getVolume();
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

    public BaseIjkVideoView(Context context) {
        this(context, null);
    }

    public BaseIjkVideoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BaseIjkVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCurrentTitle = "";
        this.mCurrentPlayState = 0;
        this.mCurrentPlayerState = 10;
        this.mCurrentOrientation = 0;
        this.mOrientationEventListener = new OrientationEventListener(getContext()) { // from class: com.movieboxpro.android.view.activity.vlcvideoplayer.player.BaseIjkVideoView.1
            private long mLastTime;

            @Override // android.view.OrientationEventListener
            public void onOrientationChanged(int i2) {
                Activity scanForActivity;
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - this.mLastTime < 300 || BaseIjkVideoView.this.mVideoController == null || (scanForActivity = PlayerUtils.scanForActivity(BaseIjkVideoView.this.mVideoController.getContext())) == null) {
                    return;
                }
                if (i2 >= 340) {
                    BaseIjkVideoView.this.onOrientationPortrait(scanForActivity);
                } else if (i2 >= 260 && i2 <= 280) {
                    BaseIjkVideoView.this.onOrientationLandscape(scanForActivity);
                } else if (i2 >= 70 && i2 <= 90) {
                    BaseIjkVideoView.this.onOrientationReverseLandscape(scanForActivity);
                }
                this.mLastTime = currentTimeMillis;
            }
        };
        this.playListener = new PlayListener() { // from class: com.movieboxpro.android.view.activity.vlcvideoplayer.player.BaseIjkVideoView.2
            @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayListener
            public void onPause() {
            }

            @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayListener
            public void onStart() {
                BaseIjkVideoView.this.setPlayState(9);
            }

            @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayListener
            public void onPlay() {
                BaseIjkVideoView.this.getAudioTracks();
                BaseIjkVideoView.this.setPlayState(3);
                if (BaseIjkVideoView.this.mVideoController != null) {
                    BaseIjkVideoView.this.mVideoController.setLoading(false);
                    BaseIjkVideoView.this.mVideoController.setPlaying(true);
                }
            }

            @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayListener
            public void onBuffer(float f) {
                if (f >= 100.0f) {
                    BaseIjkVideoView.this.setPlayState(8);
                    if (BaseIjkVideoView.this.mVideoController != null) {
                        BaseIjkVideoView.this.mVideoController.setPlaying(true);
                        BaseIjkVideoView.this.mVideoController.setLoading(false);
                    }
                } else {
                    BaseIjkVideoView.this.setPlayState(6);
                    if (BaseIjkVideoView.this.mVideoController != null) {
                        BaseIjkVideoView.this.mVideoController.setPlaying(false);
                        BaseIjkVideoView.this.mVideoController.setLoading(true);
                    }
                }
                if (BaseIjkVideoView.this.mVideoController != null) {
                    BaseIjkVideoView.this.mVideoController.setBufferProgress((int) f);
                }
            }

            @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayListener
            public void onComplete() {
                BaseIjkVideoView.this.onCompletion();
            }

            @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayListener
            public void onError(String str) {
                BaseIjkVideoView.this.setPlayState(-1);
                if (BaseIjkVideoView.this.mVideoController != null) {
                    BaseIjkVideoView.this.mVideoController.setPlaying(false);
                    BaseIjkVideoView.this.mVideoController.showErrorView(str);
                }
            }

            @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayListener
            public void onPositionChanged() {
                if (BaseIjkVideoView.this.mMediaPlayer != null) {
                    BaseIjkVideoView baseIjkVideoView = BaseIjkVideoView.this;
                    baseIjkVideoView.mCurrentPosition = baseIjkVideoView.mMediaPlayer.getCurrentPosition();
                }
                if (BaseIjkVideoView.this.mVideoController != null && BaseIjkVideoView.this.mMediaPlayer != null) {
                    BaseIjkVideoView.this.mVideoController.setProgress((int) BaseIjkVideoView.this.mCurrentPosition, (int) BaseIjkVideoView.this.mMediaPlayer.getDuration());
                }
                BaseIjkVideoView.access$008(BaseIjkVideoView.this);
                if (BaseIjkVideoView.this.count == 2) {
                    BaseIjkVideoView.this.count = 0;
                }
            }
        };
        this.mPlayerConfig = new PlayerConfig.Builder().build();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void initPlayer() {
        if (this.mMediaPlayer == null) {
            this.mMediaPlayer = VlcPlayerInstance.INSTANCE.getInstance(App.getContext());
            Log.d(TAG, "SSSSSSend");
            this.mMediaPlayer.bindVideoView(this);
            this.mMediaPlayer.initPlayer();
            this.mMediaPlayer.setEnableMediaCodec(this.mPlayerConfig.enableMediaCodec);
            this.mMediaPlayer.setLooping(this.mPlayerConfig.isLooping);
        } else {
            this.mMediaPlayer = null;
            this.mMediaPlayer = VlcPlayerInstance.INSTANCE.getInstance(App.getContext());
            Log.d(TAG, "SSSSSSend");
            this.mMediaPlayer.bindVideoView(this);
            this.mMediaPlayer.initPlayer();
            this.mMediaPlayer.setEnableMediaCodec(this.mPlayerConfig.enableMediaCodec);
            this.mMediaPlayer.setLooping(this.mPlayerConfig.isLooping);
        }
        this.mMediaPlayer.registerListener(this.playListener);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void setAudioDelay(long j) {
        AbstractPlayer abstractPlayer = this.mMediaPlayer;
        if (abstractPlayer != null) {
            abstractPlayer.setAudioDelay(j);
        }
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public long getAudioDelay() {
        AbstractPlayer abstractPlayer = this.mMediaPlayer;
        if (abstractPlayer != null) {
            return abstractPlayer.getAudioDelay();
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
        AbstractPlayer abstractPlayer = this.mMediaPlayer;
        if (abstractPlayer != null) {
            if (z) {
                abstractPlayer.reset();
            }
            this.mMediaPlayer.setEnableMediaCodec(this.mPlayerConfig.enableMediaCodec);
            AssetFileDescriptor assetFileDescriptor = this.mAssetFileDescriptor;
            if (assetFileDescriptor != null) {
                this.mMediaPlayer.setDataSource(assetFileDescriptor);
            } else {
                this.mMediaPlayer.setDataSource(this.mCurrentUrl, this.mCurrentPosition);
            }
            this.mMediaPlayer.prepareAsync();
            int i = 1;
            setPlayState(1);
            setPlayerState(isFullScreen() ? 11 : 10);
            int i2 = PrefsUtils.getInstance().getInt(Constant.Prefs.PLAY_SCALE_VALUE, 0);
            if (i2 != 0) {
                if (i2 == 1) {
                    i = 3;
                } else if (i2 == 2) {
                    i = 6;
                } else if (i2 != 3) {
                    if (i2 == 4) {
                        i = 2;
                    } else if (i2 == 5) {
                        i = 5;
                    }
                }
                this.mMediaPlayer.setVideoScale(i);
            }
            i = 0;
            this.mMediaPlayer.setVideoScale(i);
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
        AbstractPlayer abstractPlayer;
        if (this.mPlayerConfig.savingProgress && isInPlaybackState() && (abstractPlayer = this.mMediaPlayer) != null) {
            abstractPlayer.stop();
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
        AbstractPlayer abstractPlayer = this.mMediaPlayer;
        if (abstractPlayer != null) {
            try {
                abstractPlayer.unRegisterListener(this.playListener);
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
        AbstractPlayer abstractPlayer = this.mMediaPlayer;
        if (abstractPlayer != null) {
            return abstractPlayer.getBufferedPercentage();
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
        AbstractPlayer abstractPlayer;
        if (!isInPlaybackState() || (abstractPlayer = this.mMediaPlayer) == null) {
            return;
        }
        abstractPlayer.setSpeed(f);
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public IMedia.AudioTrack[] getTrackInfo() {
        if (isInPlaybackState()) {
            return this.mMediaPlayer.getTrackInfo();
        }
        return null;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public List<MediaPlayer.TrackDescription> getAudioTracks() {
        AbstractPlayer abstractPlayer = this.mMediaPlayer;
        if (abstractPlayer != null) {
            return abstractPlayer.getAudioTracks();
        }
        return new ArrayList();
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void setTrackInfo(int i) {
        AbstractPlayer abstractPlayer;
        if (!isInPlaybackState() || (abstractPlayer = this.mMediaPlayer) == null) {
            return;
        }
        abstractPlayer.setTrackInfo(i);
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void refresh() {
        this.mCurrentPosition = 0L;
        AbstractPlayer abstractPlayer = this.mMediaPlayer;
        if (abstractPlayer != null) {
            abstractPlayer.seekTo(0L);
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

    public void setVolume(float f, float f2) {
        AbstractPlayer abstractPlayer = this.mMediaPlayer;
        if (abstractPlayer != null) {
            abstractPlayer.setVolume(f, f2);
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
                if (BaseIjkVideoView.this.mMediaPlayer == null || !BaseIjkVideoView.this.isPlaying() || BaseIjkVideoView.this.mIsMute) {
                    return;
                }
                BaseIjkVideoView.this.mMediaPlayer.setVolume(0.1f, 0.1f);
            } else if (i == -2 || i == -1) {
                if (BaseIjkVideoView.this.isPlaying()) {
                    this.pausedForLoss = true;
                }
            } else if (i == 1 || i == 2) {
                if (this.startRequested || this.pausedForLoss) {
                    BaseIjkVideoView.this.start();
                    this.startRequested = false;
                    this.pausedForLoss = false;
                }
                if (BaseIjkVideoView.this.mMediaPlayer == null || BaseIjkVideoView.this.mIsMute) {
                    return;
                }
                BaseIjkVideoView.this.mMediaPlayer.setVolume(1.0f, 1.0f);
            }
        }

        boolean requestFocus() {
            if (this.currentFocus == 1) {
                return true;
            }
            if (BaseIjkVideoView.this.mAudioManager == null) {
                return false;
            }
            if (1 == BaseIjkVideoView.this.mAudioManager.requestAudioFocus(this, 3, 1)) {
                this.currentFocus = 1;
                return true;
            }
            this.startRequested = true;
            return false;
        }

        boolean abandonFocus() {
            if (BaseIjkVideoView.this.mAudioManager == null) {
                return false;
            }
            this.startRequested = false;
            return 1 == BaseIjkVideoView.this.mAudioManager.abandonAudioFocus(this);
        }
    }
}
