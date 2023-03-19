package com.movieboxpro.android.view.activity.videoplayer.controller;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import com.dueeeke.videoplayer.StageState;
import com.dueeeke.videoplayer.util.PlayerUtils;
import com.dueeeke.videoplayer.widget.StatusView;
import com.movieboxpro.android.R;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.videoplayer.player.BaseIjkVideoView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;
import tv.danmaku.ijk.media.player.misc.IjkTrackInfo;
/* loaded from: classes3.dex */
public abstract class BaseVideoController extends FrameLayout {
    protected boolean haveReport;
    protected boolean localFile;
    protected View mControllerView;
    protected int mCurrentPlayState;
    protected int mDefaultTimeout;
    protected final Runnable mFadeOut;
    private StringBuilder mFormatBuilder;
    private Formatter mFormatter;
    protected boolean mIsLocked;
    protected com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl mMediaPlayer;
    protected Runnable mShowProgress;
    protected boolean mShowing;
    protected StatusView mStatusView;

    protected abstract int getLayoutId();

    public void getTrackInfo(IjkTrackInfo[] ijkTrackInfoArr) {
    }

    public void hide(StageState stageState) {
    }

    protected void onResume() {
    }

    protected void reportError() {
    }

    public void setPlayerState(int i) {
    }

    protected int setProgress() {
        return 0;
    }

    public void show(StageState stageState) {
    }

    public void showError(String str) {
    }

    public BaseVideoController(Context context) {
        this(context, null);
    }

    public BaseVideoController(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BaseVideoController(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mDefaultTimeout = 4000;
        this.mShowProgress = new Runnable() { // from class: com.movieboxpro.android.view.activity.videoplayer.controller.BaseVideoController.2
            @Override // java.lang.Runnable
            public void run() {
                int progress = BaseVideoController.this.setProgress();
                if (BaseVideoController.this.mMediaPlayer.isPlaying()) {
                    BaseVideoController baseVideoController = BaseVideoController.this;
                    baseVideoController.postDelayed(baseVideoController.mShowProgress, 1000 - (progress % 1000));
                }
            }
        };
        this.mFadeOut = new Runnable() { // from class: com.movieboxpro.android.view.activity.videoplayer.controller.BaseVideoController.3
            @Override // java.lang.Runnable
            public void run() {
                BaseVideoController.this.hide(StageState.Runnable);
            }
        };
        initView();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void initView() {
        this.mControllerView = LayoutInflater.from(getContext()).inflate(getLayoutId(), this);
        this.mFormatBuilder = new StringBuilder();
        this.mFormatter = new Formatter(this.mFormatBuilder, Locale.getDefault());
        setClickable(true);
        setFocusable(true);
    }

    public void setPlayState(int i) {
        this.mCurrentPlayState = i;
        hideStatusView();
    }

    public boolean isLocalFile() {
        return this.localFile;
    }

    public void setLocalFile(boolean z) {
        this.localFile = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void inflateStatusView() {
        if (this.mStatusView == null) {
            StatusView statusView = (StatusView) ((ViewStub) findViewById(R.id.errorViewStub)).inflate();
            this.mStatusView = statusView;
            statusView.setReportListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.controller.-$$Lambda$BaseVideoController$Mz7YMgoYSHPEsIwcLGbrd2XaSYc
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BaseVideoController.this.lambda$inflateStatusView$0$BaseVideoController(view);
                }
            });
        }
    }

    public /* synthetic */ void lambda$inflateStatusView$0$BaseVideoController(View view) {
        reportError();
        this.mStatusView.hideReport();
        this.haveReport = true;
        ToastUtils.showShort("Report successfully");
    }

    public void showStatusView(String str) {
        inflateStatusView();
        this.mStatusView.setVisibility(0);
        this.mStatusView.setMessage(str);
        this.mStatusView.hideReport();
        this.mStatusView.setButtonTextAndAction(getResources().getString(R.string.dkplayer_continue_play), new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.controller.BaseVideoController.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BaseVideoController.this.hideStatusView();
                BaseIjkVideoView.IS_PLAY_ON_MOBILE_NETWORK = true;
                BaseVideoController.this.mMediaPlayer.start();
            }
        });
    }

    public void hideStatusView() {
        StatusView statusView = this.mStatusView;
        if (statusView != null) {
            statusView.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void doPauseResume() {
        if (this.mCurrentPlayState == 6) {
            return;
        }
        if (this.mMediaPlayer.isPlaying()) {
            this.mMediaPlayer.pause();
            return;
        }
        this.mMediaPlayer.start();
        onResume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void doStartStopFullScreen() {
        Activity scanForActivity = PlayerUtils.scanForActivity(getContext());
        if (scanForActivity == null) {
            return;
        }
        if (this.mMediaPlayer.isFullScreen()) {
            this.mMediaPlayer.stopFullScreen();
            scanForActivity.setRequestedOrientation(1);
            return;
        }
        scanForActivity.setRequestedOrientation(0);
        this.mMediaPlayer.startFullScreen();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getCurrentSystemTime() {
        return new SimpleDateFormat(TimeUtils.PATTERN_MINUTE_SECOND, Locale.getDefault()).format(new Date());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String stringForTime(int i) {
        int i2 = i / 1000;
        int i3 = i2 % 60;
        int i4 = (i2 / 60) % 60;
        int i5 = i2 / 3600;
        this.mFormatBuilder.setLength(0);
        return i5 > 0 ? this.mFormatter.format("%d:%02d:%02d", Integer.valueOf(i5), Integer.valueOf(i4), Integer.valueOf(i3)).toString() : this.mFormatter.format("%d:%02d:%02d", Integer.valueOf(i5), Integer.valueOf(i4), Integer.valueOf(i3)).toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
    }

    public void setMediaPlayer(com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl mediaPlayerControl) {
        this.mMediaPlayer = mediaPlayerControl;
    }

    public void setTrackInfo(int i) {
        this.mMediaPlayer.setTrackInfo(i);
    }
}
