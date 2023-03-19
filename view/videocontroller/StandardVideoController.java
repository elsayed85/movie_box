package com.movieboxpro.android.view.videocontroller;

import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.FragmentManager;
import com.avery.subtitle.widget.SpanUtils;
import com.dueeeke.videoplayer.StageState;
import com.dueeeke.videoplayer.util.L;
import com.dueeeke.videoplayer.util.PlayerUtils;
import com.google.android.exoplayer2.C;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.event.CloseSkipEndEvent;
import com.movieboxpro.android.listener.VideoPreviewListener;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ScreenUtils;
import com.movieboxpro.android.view.activity.videoplayer.controller.GestureVideoController;
import com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl;
import com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.SkipTimeFragment;
import com.movieboxpro.android.view.widget.CustomClickableSpan;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.EventBus;
/* loaded from: classes3.dex */
public abstract class StandardVideoController extends GestureVideoController implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, PlayerController {
    private Handler handler;
    private Runnable hideBackwardRunnable;
    private final Runnable hideContinueText;
    private Runnable hideForwardRunnable;
    private ImageView ivCloseContinue;
    protected LinearLayout llClose;
    private LinearLayout llContinue;
    protected LinearLayout llNextEpisode;
    private TextView llPlayWithOther;
    protected LinearLayout llReplay;
    private LinearLayout llSpeedPlay;
    protected ImageView mBackButton;
    private ImageView mBatteryLevel;
    private BatteryReceiver mBatteryReceiver;
    protected LinearLayout mBottomContainer;
    private ProgressBar mBottomProgress;
    private LinearLayout mCompleteContainer;
    protected TextView mCurrTime;
    protected ImageView mFullScreenButton;
    private Animation mHideAnim;
    private boolean mIsDragging;
    private boolean mIsLive;
    private ProgressBar mLoadingProgress;
    protected ImageView mLockButton;
    private ImageView mPlayButton;
    protected ImageView mRefreshButton;
    protected LinearLayout mRlNext;
    private Animation mShowAnim;
    private ImageView mStartPlayButton;
    private TextView mSysTime;
    private ImageView mThumb;
    protected MarqueeTextView mTitle;
    protected LinearLayout mTopContainer;
    protected TextView mTotalTime;
    protected SeekBar mVideoProgress;
    private RadioGroup rgSkip;
    private final Runnable showBaseViewRunnable;
    private List<Long> speeds;
    private boolean startSeek;
    private Disposable tcpSpeedDisposable;
    private TextView tvBackward;
    private TextView tvContinue;
    private TextView tvForward;
    private TextView tvTcpSpeed;
    private VideoPreviewListener videoPreviewListener;

    protected void finishPlay() {
    }

    protected long getBitStream() {
        return 0L;
    }

    protected int getBoxType() {
        return 0;
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.controller.BaseVideoController
    protected int getLayoutId() {
        return R.layout.dkplayer_layout_standard_controller;
    }

    public String getPlayUrl() {
        return "";
    }

    public String getPreviewUrl() {
        return "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void playNextEpisode() {
    }

    protected void playWithOtherPlayer() {
    }

    protected void showTestSpeedHint() {
    }

    public StandardVideoController(Context context) {
        this(context, null);
    }

    public StandardVideoController(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public StandardVideoController(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mShowAnim = AnimationUtils.loadAnimation(getContext(), R.anim.dkplayer_anim_alpha_in);
        this.mHideAnim = AnimationUtils.loadAnimation(getContext(), R.anim.dkplayer_anim_alpha_out);
        this.speeds = new ArrayList();
        this.hideContinueText = new Runnable() { // from class: com.movieboxpro.android.view.videocontroller.StandardVideoController.4
            @Override // java.lang.Runnable
            public void run() {
                StandardVideoController.this.llContinue.setVisibility(8);
            }
        };
        this.showBaseViewRunnable = new Runnable() { // from class: com.movieboxpro.android.view.videocontroller.StandardVideoController.15
            @Override // java.lang.Runnable
            public void run() {
                StandardVideoController.this.showBaseViews();
            }
        };
        this.hideBackwardRunnable = new Runnable() { // from class: com.movieboxpro.android.view.videocontroller.StandardVideoController.16
            @Override // java.lang.Runnable
            public void run() {
                StandardVideoController.this.tvBackward.setVisibility(8);
            }
        };
        this.hideForwardRunnable = new Runnable() { // from class: com.movieboxpro.android.view.videocontroller.StandardVideoController.17
            @Override // java.lang.Runnable
            public void run() {
                StandardVideoController.this.tvForward.setVisibility(8);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.view.activity.videoplayer.controller.GestureVideoController, com.movieboxpro.android.view.activity.videoplayer.controller.BaseVideoController
    public void initView() {
        super.initView();
        this.handler = new Handler();
        ImageView imageView = (ImageView) this.mControllerView.findViewById(R.id.fullscreen);
        this.mFullScreenButton = imageView;
        imageView.setOnClickListener(this);
        this.mBottomContainer = (LinearLayout) this.mControllerView.findViewById(R.id.bottom_container);
        this.mTopContainer = (LinearLayout) this.mControllerView.findViewById(R.id.top_container);
        SeekBar seekBar = (SeekBar) this.mControllerView.findViewById(R.id.seekBar);
        this.mVideoProgress = seekBar;
        seekBar.setOnSeekBarChangeListener(this);
        this.mTotalTime = (TextView) this.mControllerView.findViewById(R.id.total_time);
        this.mCurrTime = (TextView) this.mControllerView.findViewById(R.id.curr_time);
        ImageView imageView2 = (ImageView) this.mControllerView.findViewById(R.id.back);
        this.mBackButton = imageView2;
        imageView2.setOnClickListener(this);
        ImageView imageView3 = (ImageView) this.mControllerView.findViewById(R.id.lock);
        this.mLockButton = imageView3;
        imageView3.setOnClickListener(this);
        ImageView imageView4 = (ImageView) this.mControllerView.findViewById(R.id.thumb);
        this.mThumb = imageView4;
        imageView4.setOnClickListener(this);
        ImageView imageView5 = (ImageView) this.mControllerView.findViewById(R.id.iv_play);
        this.mPlayButton = imageView5;
        imageView5.setOnClickListener(this);
        ImageView imageView6 = (ImageView) this.mControllerView.findViewById(R.id.start_play);
        this.mStartPlayButton = imageView6;
        imageView6.setOnClickListener(this);
        this.mLoadingProgress = (ProgressBar) this.mControllerView.findViewById(R.id.loading);
        this.tvTcpSpeed = (TextView) this.mControllerView.findViewById(R.id.tvTcpSpeed);
        this.mBottomProgress = (ProgressBar) this.mControllerView.findViewById(R.id.bottom_progress);
        LinearLayout linearLayout = (LinearLayout) this.mControllerView.findViewById(R.id.complete_container);
        this.mCompleteContainer = linearLayout;
        linearLayout.setOnClickListener(this);
        this.mTitle = (MarqueeTextView) this.mControllerView.findViewById(R.id.title);
        this.mSysTime = (TextView) this.mControllerView.findViewById(R.id.sys_time);
        this.mBatteryLevel = (ImageView) this.mControllerView.findViewById(R.id.iv_battery);
        this.mBatteryReceiver = new BatteryReceiver(this.mBatteryLevel);
        ImageView imageView7 = (ImageView) this.mControllerView.findViewById(R.id.iv_refresh);
        this.mRefreshButton = imageView7;
        imageView7.setOnClickListener(this);
        this.tvForward = (TextView) this.mControllerView.findViewById(R.id.tvForward);
        this.tvBackward = (TextView) this.mControllerView.findViewById(R.id.tvBackward);
        this.llPlayWithOther = (TextView) this.mControllerView.findViewById(R.id.tvPlayWithOther);
        this.rgSkip = (RadioGroup) this.mControllerView.findViewById(R.id.rgSkip);
        initListener();
        Paint paint = new Paint();
        paint.setTextSize(DensityUtils.sp2px(14.0f));
        int measureText = (int) (paint.measureText("4:44:44") + 15.0f);
        this.mCurrTime.setWidth(measureText);
        this.mTotalTime.setWidth(measureText);
        this.mRlNext = (LinearLayout) this.mControllerView.findViewById(R.id.rl_next);
        this.llNextEpisode = (LinearLayout) this.mControllerView.findViewById(R.id.llNextEpisode);
        this.llReplay = (LinearLayout) this.mControllerView.findViewById(R.id.llReplay);
        this.llClose = (LinearLayout) this.mControllerView.findViewById(R.id.llClose);
        this.llNextEpisode.setOnClickListener(this);
        this.llReplay.setOnClickListener(this);
        this.llClose.setOnClickListener(this);
        this.llPlayWithOther.setOnClickListener(this);
        this.tvContinue = (TextView) this.mControllerView.findViewById(R.id.tvContinue);
        this.llContinue = (LinearLayout) this.mControllerView.findViewById(R.id.llContinue);
        ImageView imageView8 = (ImageView) this.mControllerView.findViewById(R.id.ivCloseContinue);
        this.ivCloseContinue = imageView8;
        imageView8.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.videocontroller.StandardVideoController.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                StandardVideoController.this.llContinue.setVisibility(8);
                EventBus.getDefault().post(new CloseSkipEndEvent());
            }
        });
        this.tvContinue.setOnClickListener(this);
        this.llSpeedPlay = (LinearLayout) this.mControllerView.findViewById(R.id.llSpeedPlay);
        if (!ScreenUtils.isPortrait(getContext())) {
            DensityUtils.setMarginPx(this.llContinue, 5, 0, 5, DensityUtils.dp2px(15.0f));
        } else {
            DensityUtils.setMarginPx(this.llContinue, 5, 0, 5, DensityUtils.dp2px(55.0f));
        }
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.SKIP_OPENING_ENDING, true)) {
            this.rgSkip.check(R.id.rbSkipOn);
        } else {
            this.rgSkip.check(R.id.rbSkipOff);
        }
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void updateSkipSettingStatus() {
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.SKIP_OPENING_ENDING, true)) {
            this.rgSkip.check(R.id.rbSkipOn);
        } else {
            this.rgSkip.check(R.id.rbSkipOff);
        }
    }

    private void initListener() {
        this.rgSkip.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.movieboxpro.android.view.videocontroller.StandardVideoController.2
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rbSkipOn) {
                    PrefsUtils.getInstance().putBoolean(Constant.Prefs.SKIP_OPENING_ENDING, true);
                } else if (i == R.id.rbSkipOff) {
                    PrefsUtils.getInstance().putBoolean(Constant.Prefs.SKIP_OPENING_ENDING, false);
                }
            }
        });
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.controller.GestureVideoController
    protected void toggleLongSpeedPlay(boolean z) {
        if (z) {
            if (this.mMediaPlayer == null || !this.mMediaPlayer.isPlaying()) {
                return;
            }
            this.llSpeedPlay.setVisibility(0);
            this.mMediaPlayer.setSpeed(3.0f);
        } else if (this.llSpeedPlay.getVisibility() == 0) {
            this.llSpeedPlay.setVisibility(8);
            this.mMediaPlayer.setSpeed(1.0f);
        }
    }

    public void setVideoPreviewListener(VideoPreviewListener videoPreviewListener) {
        this.videoPreviewListener = videoPreviewListener;
    }

    private void setCurrTimeText(String str) {
        this.mCurrTime.setText(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.view.activity.videoplayer.controller.BaseVideoController, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        Disposable disposable = this.tcpSpeedDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        this.handler.removeCallbacksAndMessages(null);
        super.onDetachedFromWindow();
        getContext().unregisterReceiver(this.mBatteryReceiver);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.view.activity.videoplayer.controller.BaseVideoController, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getContext().registerReceiver(this.mBatteryReceiver, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
    }

    private void startShowTcpSpeed() {
        this.mStartPlayButton.setVisibility(8);
        if (this.tcpSpeedDisposable != null) {
            this.speeds.clear();
            this.tcpSpeedDisposable.dispose();
        }
        TextView textView = this.tvTcpSpeed;
        if (textView != null) {
            textView.setText("");
        }
        Observable.interval(1L, TimeUnit.SECONDS).compose(RxUtils.rxSchedulerHelper()).subscribe(new Observer<Long>() { // from class: com.movieboxpro.android.view.videocontroller.StandardVideoController.3
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                StandardVideoController.this.tcpSpeedDisposable = disposable;
            }

            @Override // io.reactivex.Observer
            public void onNext(Long l) {
                int size;
                if (StandardVideoController.this.tvTcpSpeed == null || StandardVideoController.this.mMediaPlayer == null) {
                    return;
                }
                if (StandardVideoController.this.tvTcpSpeed.getVisibility() != 0) {
                    StandardVideoController.this.tvTcpSpeed.setVisibility(0);
                }
                long tcpSpeed = StandardVideoController.this.mMediaPlayer.getTcpSpeed();
                StandardVideoController.this.tvTcpSpeed.setText(String.format("%sKB/S", Long.valueOf(tcpSpeed / 1024)));
                if (l.longValue() <= 6) {
                    StandardVideoController.this.speeds.add(Long.valueOf(tcpSpeed));
                }
                if (l.longValue() == 7) {
                    long j = 0;
                    for (Long l2 : StandardVideoController.this.speeds) {
                        j += l2.longValue();
                    }
                    if ((j / (StandardVideoController.this.speeds.size() != 0 ? size : 1)) / 1024 < (StandardVideoController.this.getBitStream() > 0 ? StandardVideoController.this.getBitStream() : 128L)) {
                        StandardVideoController.this.showTestSpeedHint();
                    }
                } else if (l.longValue() == 25) {
                    StandardVideoController.this.showTestSpeedHint();
                }
            }
        });
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void cancelShowTestSpeed() {
        stopShowTcpSpeed();
    }

    private void stopShowTcpSpeed() {
        Disposable disposable = this.tcpSpeedDisposable;
        if (disposable != null) {
            disposable.dispose();
            this.speeds.clear();
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.fullscreen) {
            doStartStopFullScreen();
        } else if (id == R.id.back) {
            setPlayState(0);
            PlayerUtils.scanForActivity(getContext()).finish();
        } else if (id == R.id.lock) {
            doLockUnlock();
        } else if (id == R.id.iv_play || id == R.id.thumb) {
            doPauseResume();
        } else if (id == R.id.llReplay) {
            this.mMediaPlayer.retry();
        } else if (id == R.id.llClose) {
            finishPlay();
        } else if (id == R.id.llNextEpisode) {
            playNextEpisode();
        } else if (id == R.id.iv_refresh) {
            this.mMediaPlayer.refresh();
        } else if (id == R.id.start_play) {
            doPauseResume();
        } else if (id == R.id.tvPlayWithOther) {
            playWithOtherPlayer();
        }
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void setContinueText(String str) {
        this.handler.removeCallbacks(this.hideBackwardRunnable);
        this.llContinue.setVisibility(0);
        this.ivCloseContinue.setVisibility(8);
        SpanUtils.with(this.tvContinue).append("Continue ").setForegroundColor(Color.parseColor("#A1FFFFFF")).setFontSize(15, true).append(str).setForegroundColor(Color.parseColor("#A1FFFFFF")).setFontSize(15, true).append(",").setForegroundColor(Color.parseColor("#A1FFFFFF")).setFontSize(15, true).append(" Jump ").setForegroundColor(Color.parseColor("#0579FF")).setFontSize(15, true).setClickSpan(new CustomClickableSpan(Color.parseColor("#0579FF"), false) { // from class: com.movieboxpro.android.view.videocontroller.StandardVideoController.5
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                StandardVideoController.this.mMediaPlayer.seekTo(0L);
                StandardVideoController.this.llContinue.setVisibility(8);
            }
        }).append("to Start").setForegroundColor(Color.parseColor("#A1FFFFFF")).setFontSize(15, true).create();
        this.handler.postDelayed(this.hideContinueText, C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void showRecommendSkipTime(final int i, final boolean z, final String str, final String str2, final int i2, final int i3, final FragmentManager fragmentManager, final DialogAction.ActionListener actionListener) {
        this.handler.removeCallbacks(this.hideBackwardRunnable);
        this.llContinue.setVisibility(0);
        if (z) {
            this.ivCloseContinue.setVisibility(8);
            final Runnable runnable = new Runnable() { // from class: com.movieboxpro.android.view.videocontroller.StandardVideoController.6
                @Override // java.lang.Runnable
                public void run() {
                    StandardVideoController.this.llContinue.setVisibility(8);
                    DialogAction.ActionListener actionListener2 = actionListener;
                    if (actionListener2 != null) {
                        actionListener2.onClick();
                    }
                }
            };
            SpanUtils fontSize = SpanUtils.with(this.tvContinue).append("Skipped Opening ").setForegroundColor(Color.parseColor("#A1FFFFFF")).setFontSize(17, true);
            fontSize.append(" " + i + "S ").setForegroundColor(Color.parseColor("#0579FF")).setFontSize(17, true).setClickSpan(new CustomClickableSpan(Color.parseColor("#0579FF"), true) { // from class: com.movieboxpro.android.view.videocontroller.StandardVideoController.8
                @Override // android.text.style.ClickableSpan
                public void onClick(View view) {
                    StandardVideoController.this.llContinue.setVisibility(8);
                    SkipTimeFragment.Companion.newInstance(str, str2, i2, i3).show(fragmentManager, SkipTimeFragment.class.getSimpleName());
                    StandardVideoController.this.handler.removeCallbacks(runnable);
                }
            }).append(", ").setForegroundColor(Color.parseColor("#A1FFFFFF")).setFontSize(17, true).append(" RESTART").setForegroundColor(Color.parseColor("#0579FF")).setFontSize(17, true).setClickSpan(new CustomClickableSpan(Color.parseColor("#0579FF"), false) { // from class: com.movieboxpro.android.view.videocontroller.StandardVideoController.7
                @Override // android.text.style.ClickableSpan
                public void onClick(View view) {
                    StandardVideoController.this.mMediaPlayer.seekTo(0L);
                    StandardVideoController.this.llContinue.setVisibility(8);
                    StandardVideoController.this.handler.removeCallbacks(runnable);
                }
            }).create();
            this.handler.postDelayed(runnable, 5000L);
            return;
        }
        this.ivCloseContinue.setVisibility(0);
        SpanUtils fontSize2 = SpanUtils.with(this.tvContinue).append("Ignore Ending ").setForegroundColor(Color.parseColor("#A1FFFFFF")).setFontSize(17, true);
        fontSize2.append(" " + i + "S ").setForegroundColor(Color.parseColor("#0579FF")).setFontSize(17, true).setClickSpan(new CustomClickableSpan(Color.parseColor("#0579FF"), true) { // from class: com.movieboxpro.android.view.videocontroller.StandardVideoController.10
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                SkipTimeFragment.Companion.newInstance(str, str2, i2, i3, true).show(fragmentManager, SkipTimeFragment.class.getSimpleName());
            }
        }).append(",   ").setForegroundColor(Color.parseColor("#A1FFFFFF")).setFontSize(17, true).append("NEXT").setForegroundColor(Color.parseColor("#0579FF")).setFontSize(17, true).setClickSpan(new CustomClickableSpan(Color.parseColor("#0579FF"), true) { // from class: com.movieboxpro.android.view.videocontroller.StandardVideoController.9
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                if (z) {
                    StandardVideoController.this.mMediaPlayer.seekTo(i * 1000);
                    StandardVideoController.this.llContinue.setVisibility(8);
                }
                DialogAction.ActionListener actionListener2 = actionListener;
                if (actionListener2 != null) {
                    actionListener2.onClick();
                }
                StandardVideoController.this.llContinue.setVisibility(8);
            }
        }).create();
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void showWillSkipTime(int i, boolean z, final String str, final String str2, final int i2, final int i3, final FragmentManager fragmentManager) {
        String str3;
        this.handler.removeCallbacks(this.hideBackwardRunnable);
        this.tvContinue.setVisibility(0);
        if (z) {
            str3 = "Will Skip Opening " + i + "s,";
        } else {
            str3 = "Will Skip Ending " + i + "s,";
        }
        SpanUtils.with(this.tvContinue).append(str3).setForegroundColor(Color.parseColor("#A1FFFFFF")).setFontSize(15, true).append("SETTING").setForegroundColor(Color.parseColor("#0579FF")).setFontSize(15, true).setClickSpan(new CustomClickableSpan(Color.parseColor("#0579FF"), false) { // from class: com.movieboxpro.android.view.videocontroller.StandardVideoController.11
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                SkipTimeFragment.Companion.newInstance(str, str2, i2, i3).show(fragmentManager, SkipTimeFragment.class.getSimpleName());
            }
        }).create();
        this.handler.postDelayed(this.hideContinueText, 5000L);
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void showSkipTime(int i, boolean z, final String str, final String str2, final int i2, final int i3, final FragmentManager fragmentManager) {
        this.handler.removeCallbacks(this.hideBackwardRunnable);
        this.llContinue.setVisibility(0);
        this.ivCloseContinue.setVisibility(8);
        SpanUtils fontSize = SpanUtils.with(this.tvContinue).append(z ? "Skipped Opening " : "Skipped Ending ").setForegroundColor(Color.parseColor("#A1FFFFFF")).setFontSize(17, true);
        fontSize.append(" " + i + "S ").setForegroundColor(Color.parseColor("#0579FF")).setFontSize(17, true).setClickSpan(new CustomClickableSpan(Color.parseColor("#0579FF"), true) { // from class: com.movieboxpro.android.view.videocontroller.StandardVideoController.13
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                SkipTimeFragment.Companion.newInstance(str, str2, i2, i3).show(fragmentManager, SkipTimeFragment.class.getSimpleName());
            }
        }).append(", ").setForegroundColor(Color.parseColor("#A1FFFFFF")).setFontSize(17, true).append(" RESTART").setForegroundColor(Color.parseColor("#0579FF")).setFontSize(17, true).setClickSpan(new CustomClickableSpan(Color.parseColor("#0579FF"), false) { // from class: com.movieboxpro.android.view.videocontroller.StandardVideoController.12
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                StandardVideoController.this.mMediaPlayer.seekTo(0L);
                StandardVideoController.this.llContinue.setVisibility(8);
            }
        }).create();
        this.handler.postDelayed(this.hideContinueText, 5000L);
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void updateTitle(String str) {
        MarqueeTextView marqueeTextView = this.mTitle;
        if (marqueeTextView != null) {
            marqueeTextView.setText(str);
        }
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.controller.BaseVideoController
    public void setPlayerState(int i) {
        if (i == 10) {
            L.e("PLAYER_NORMAL");
            if (this.mIsLocked) {
                return;
            }
            setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            this.mIsGestureEnabled = true;
            this.mFullScreenButton.setSelected(false);
            this.mLockButton.setVisibility(8);
            this.mSysTime.setVisibility(8);
            this.mBatteryLevel.setVisibility(8);
        } else if (i != 11) {
        } else {
            L.e("PLAYER_FULL_SCREEN");
            if (this.mIsLocked) {
                return;
            }
            this.mIsGestureEnabled = true;
            this.mFullScreenButton.setSelected(true);
            this.mBackButton.setVisibility(0);
            this.mTitle.setVisibility(0);
            this.mSysTime.setVisibility(0);
            this.mBatteryLevel.setVisibility(0);
            if (this.mShowing) {
                this.mLockButton.setVisibility(0);
                this.mTopContainer.setVisibility(0);
                return;
            }
            this.mLockButton.setVisibility(8);
        }
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.controller.BaseVideoController
    public void setPlayState(int i) {
        super.setPlayState(i);
        switch (i) {
            case 0:
                L.e("STATE_IDLE");
                hide(StageState.PlayState);
                this.mIsLocked = false;
                this.mLockButton.setSelected(false);
                this.mMediaPlayer.setLock(false);
                this.mBottomProgress.setProgress(0);
                this.mBottomProgress.setSecondaryProgress(0);
                this.mVideoProgress.setProgress(0);
                this.mVideoProgress.setSecondaryProgress(0);
                this.mCompleteContainer.setVisibility(8);
                this.mBottomProgress.setVisibility(8);
                this.mLoadingProgress.setVisibility(8);
                this.tvTcpSpeed.setVisibility(8);
                stopShowTcpSpeed();
                this.mThumb.setVisibility(0);
                return;
            case 1:
                L.e("STATE_PREPARING");
                this.mCompleteContainer.setVisibility(8);
                this.mLoadingProgress.setVisibility(0);
                startShowTcpSpeed();
                return;
            case 2:
                L.e("STATE_PREPARED");
                if (this.mIsLive) {
                    return;
                }
                this.mBottomProgress.setVisibility(8);
                return;
            case 3:
                L.e("STATE_PLAYING");
                post(this.mShowProgress);
                this.mPlayButton.setSelected(true);
                this.mLoadingProgress.setVisibility(8);
                this.tvTcpSpeed.setVisibility(8);
                stopShowTcpSpeed();
                this.mCompleteContainer.setVisibility(8);
                this.mThumb.setVisibility(8);
                this.mStartPlayButton.setImageResource(R.drawable.dkplayer_ic_action_pause);
                if (this.mTopContainer.getVisibility() == 0) {
                    this.mStartPlayButton.setVisibility(0);
                    return;
                }
                return;
            case 4:
                L.e("STATE_PAUSED");
                this.mPlayButton.setSelected(false);
                this.mStartPlayButton.setImageResource(R.drawable.dkplayer_ic_action_play_arrow);
                return;
            case 5:
                L.e("STATE_PLAYBACK_COMPLETED");
                hide(StageState.PlayState);
                removeCallbacks(this.mShowProgress);
                this.mThumb.setVisibility(8);
                this.mCompleteContainer.setVisibility(0);
                this.mBottomProgress.setProgress(0);
                this.mBottomProgress.setSecondaryProgress(0);
                this.mVideoProgress.setProgress(0);
                this.mVideoProgress.setSecondaryProgress(0);
                this.mIsLocked = false;
                this.mMediaPlayer.setLock(false);
                return;
            case 6:
                L.e("STATE_BUFFERING");
                this.mPlayButton.setSelected(true);
                this.mLoadingProgress.setVisibility(0);
                this.mThumb.setVisibility(8);
                return;
            case 7:
                this.mLoadingProgress.setVisibility(8);
                this.tvTcpSpeed.setVisibility(8);
                stopShowTcpSpeed();
                this.mThumb.setVisibility(8);
                L.e("STATE_BUFFERED");
                return;
            default:
                return;
        }
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.controller.BaseVideoController
    public void showError(String str) {
        inflateStatusView();
        if (TextUtils.isEmpty(str)) {
            str = getResources().getString(R.string.dkplayer_error_message);
        }
        this.mStatusView.setVisibility(0);
        this.mStatusView.setMessage(str);
        if (!this.haveReport) {
            this.mStatusView.showReport();
        }
        this.mStatusView.setButtonTextAndAction(getResources().getString(R.string.dkplayer_retry), new View.OnClickListener() { // from class: com.movieboxpro.android.view.videocontroller.StandardVideoController.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                StandardVideoController.this.hideStatusView();
                StandardVideoController.this.mMediaPlayer.retry();
            }
        });
        L.e("STATE_ERROR");
        this.mStartPlayButton.setVisibility(8);
        this.mLoadingProgress.setVisibility(8);
        this.tvTcpSpeed.setVisibility(8);
        stopShowTcpSpeed();
        this.mThumb.setVisibility(8);
        this.mBottomProgress.setVisibility(8);
        this.mTopContainer.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void retryPlay() {
        hideStatusView();
        this.mMediaPlayer.retryPlay();
    }

    protected void doLockUnlock() {
        if (this.mIsLocked) {
            this.mIsLocked = false;
            this.mShowing = false;
            this.mIsGestureEnabled = true;
            show(StageState.Lock);
            this.mLockButton.setSelected(false);
            try {
                Toast.makeText(getContext(), (int) R.string.dkplayer_unlocked, 0).show();
            } catch (WindowManager.BadTokenException unused) {
                Log.d("StandardVideoController", "doLockUnlock: ");
            }
        } else {
            hide(StageState.Lock);
            this.mIsLocked = true;
            this.mIsGestureEnabled = false;
            this.mLockButton.setSelected(true);
            try {
                Toast.makeText(getContext(), (int) R.string.dkplayer_locked, 0).show();
            } catch (WindowManager.BadTokenException unused2) {
                Log.d("StandardVideoController", "doLockUnlock: ");
            }
        }
        this.mMediaPlayer.setLock(this.mIsLocked);
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.controller.GestureVideoController
    protected void seekProgress(long j) {
        if (!this.startSeek) {
            this.startSeek = true;
            VideoPreviewListener videoPreviewListener = this.videoPreviewListener;
            if (videoPreviewListener != null) {
                videoPreviewListener.startDrag();
            }
            if (this.mBottomContainer.getVisibility() == 8) {
                this.handler.postDelayed(this.showBaseViewRunnable, 450L);
            }
        }
        long duration = this.mMediaPlayer.getDuration();
        this.mVideoProgress.setMax(((int) duration) / 1000);
        int i = (int) (j / 1000);
        this.mVideoProgress.setProgress(i);
        if (this.videoPreviewListener == null || this.mVideoProgress.getMax() == 0) {
            return;
        }
        this.videoPreviewListener.onProgressChanged(this.mVideoProgress, (int) ((((duration * j) / 1000) / this.mVideoProgress.getMax()) / 1000), i);
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.controller.GestureVideoController
    protected void seekProgressEnd() {
        this.handler.removeCallbacks(this.showBaseViewRunnable);
        if (this.mBottomContainer.getVisibility() == 0) {
            hideBaseView();
        }
        this.startSeek = false;
        VideoPreviewListener videoPreviewListener = this.videoPreviewListener;
        if (videoPreviewListener != null) {
            videoPreviewListener.stopDrag();
        }
    }

    public void setLive() {
        this.mIsLive = true;
        this.mBottomProgress.setVisibility(8);
        this.mVideoProgress.setVisibility(4);
        this.mTotalTime.setVisibility(4);
        this.mCurrTime.setVisibility(4);
        this.mRefreshButton.setVisibility(0);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
        this.mIsDragging = true;
        removeCallbacks(this.mShowProgress);
        removeCallbacks(this.mFadeOut);
        VideoPreviewListener videoPreviewListener = this.videoPreviewListener;
        if (videoPreviewListener != null) {
            videoPreviewListener.startDrag();
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
        long duration = this.mMediaPlayer.getDuration();
        if (this.mVideoProgress.getMax() != 0) {
            int progress = (int) ((duration * seekBar.getProgress()) / this.mVideoProgress.getMax());
            this.mMediaPlayer.seekTo(progress);
            this.mIsDragging = false;
            post(this.mShowProgress);
            show(StageState.Gesture);
            VideoPreviewListener videoPreviewListener = this.videoPreviewListener;
            if (videoPreviewListener != null) {
                videoPreviewListener.stopDrag();
            }
            playerSeekTo(progress);
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z) {
            long duration = (this.mMediaPlayer.getDuration() * i) / this.mVideoProgress.getMax();
            VideoPreviewListener videoPreviewListener = this.videoPreviewListener;
            if (videoPreviewListener != null) {
                videoPreviewListener.onProgressChanged(seekBar, (int) (duration / 1000), i);
            }
            if (this.mCurrTime != null) {
                setCurrTimeText(stringForTime((int) duration));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.view.activity.videoplayer.controller.GestureVideoController
    public void playerSeekTo(int i) {
        super.playerSeekTo(i);
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.controller.BaseVideoController
    public void hide(StageState stageState) {
        if (this.mShowing) {
            this.mLockButton.setVisibility(8);
            if (!this.mIsLocked) {
                hideBaseView();
            }
            this.mShowing = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void hideBaseView() {
        this.mTopContainer.setVisibility(8);
        this.mTopContainer.startAnimation(this.mHideAnim);
        this.mBottomContainer.setVisibility(8);
        this.mBottomContainer.startAnimation(this.mHideAnim);
        this.mRlNext.setVisibility(8);
        this.mRlNext.startAnimation(this.mHideAnim);
        if (this.mLoadingProgress.getVisibility() != 0) {
            this.mStartPlayButton.setVisibility(8);
            this.mStartPlayButton.startAnimation(this.mHideAnim);
            return;
        }
        this.mStartPlayButton.setVisibility(8);
    }

    private void show(int i) {
        TextView textView = this.mSysTime;
        if (textView != null) {
            textView.setText(getCurrentSystemTime());
        }
        if (!this.mShowing) {
            this.mLockButton.setVisibility(0);
            if (!this.mIsLocked) {
                showBaseViews();
            }
            this.mShowing = true;
        }
        removeCallbacks(this.mFadeOut);
        if (i != 0) {
            postDelayed(this.mFadeOut, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showBaseViews() {
        this.mBottomContainer.setVisibility(0);
        this.mBottomContainer.startAnimation(this.mShowAnim);
        this.mTopContainer.setVisibility(0);
        this.mTopContainer.startAnimation(this.mShowAnim);
        if (getBoxType() != 1) {
            this.mRlNext.setVisibility(0);
            this.mRlNext.startAnimation(this.mShowAnim);
        }
        if (this.mLoadingProgress.getVisibility() != 0) {
            this.mStartPlayButton.setVisibility(0);
            this.mStartPlayButton.startAnimation(this.mShowAnim);
        }
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.controller.GestureVideoController
    protected void showForwardBackView(int i) {
        if (this.mLoadingProgress.getVisibility() == 0) {
            return;
        }
        if (i > 0) {
            showForward();
            this.tvForward.setText(String.format("+ %ss", Integer.valueOf(i)));
            long currentPosition = this.mMediaPlayer.getCurrentPosition() + C.DEFAULT_SEEK_FORWARD_INCREMENT_MS;
            this.mMediaPlayer.seekTo(currentPosition);
            playerSeekTo((int) currentPosition);
            return;
        }
        showBackward();
        this.tvBackward.setText(String.format("- %ss", Integer.valueOf(Math.abs(i))));
        long currentPosition2 = this.mMediaPlayer.getCurrentPosition() - C.DEFAULT_SEEK_FORWARD_INCREMENT_MS;
        this.mMediaPlayer.seekTo(currentPosition2);
        playerSeekTo((int) currentPosition2);
    }

    private void showBackward() {
        this.tvBackward.setVisibility(0);
        this.handler.removeCallbacks(this.hideBackwardRunnable);
        this.handler.postDelayed(this.hideBackwardRunnable, 1000L);
    }

    private void showForward() {
        this.tvForward.setVisibility(0);
        this.handler.removeCallbacks(this.hideForwardRunnable);
        this.handler.postDelayed(this.hideForwardRunnable, 1000L);
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.controller.BaseVideoController
    public void show(StageState stageState) {
        show(this.mDefaultTimeout);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.view.activity.videoplayer.controller.BaseVideoController
    public int setProgress() {
        if (this.mMediaPlayer == null || this.mIsDragging) {
            return 0;
        }
        MarqueeTextView marqueeTextView = this.mTitle;
        if (marqueeTextView != null && TextUtils.isEmpty(marqueeTextView.getText())) {
            this.mTitle.setText(this.mMediaPlayer.getTitle());
        }
        int currentPosition = (int) this.mMediaPlayer.getCurrentPosition();
        int duration = (int) this.mMediaPlayer.getDuration();
        SeekBar seekBar = this.mVideoProgress;
        if (seekBar != null) {
            if (duration > 0) {
                seekBar.setEnabled(true);
                double d = currentPosition;
                Double.isNaN(d);
                double d2 = duration;
                Double.isNaN(d2);
                double d3 = (d * 1.0d) / d2;
                double max = this.mVideoProgress.getMax();
                Double.isNaN(max);
                int i = (int) (d3 * max);
                this.mVideoProgress.setProgress(i);
                this.mBottomProgress.setProgress(i);
            } else {
                seekBar.setEnabled(false);
            }
            int bufferedPercentage = this.mMediaPlayer.getBufferedPercentage();
            if (bufferedPercentage >= 95) {
                SeekBar seekBar2 = this.mVideoProgress;
                seekBar2.setSecondaryProgress(seekBar2.getMax());
                ProgressBar progressBar = this.mBottomProgress;
                progressBar.setSecondaryProgress(progressBar.getMax());
            } else {
                int i2 = bufferedPercentage * 10;
                this.mVideoProgress.setSecondaryProgress(i2);
                this.mBottomProgress.setSecondaryProgress(i2);
            }
        }
        TextView textView = this.mTotalTime;
        if (textView != null) {
            textView.setText(stringForTime(duration - currentPosition));
        }
        if (this.mCurrTime != null) {
            setCurrTimeText(stringForTime(currentPosition));
        }
        return currentPosition;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.view.activity.videoplayer.controller.GestureVideoController
    public void slideToChangePosition(float f) {
        if (this.mIsLive) {
            this.mNeedSeek = false;
        } else {
            super.slideToChangePosition(f);
        }
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public MediaPlayerControl getMediaPlayer() {
        return this.mMediaPlayer;
    }

    public ImageView getThumb() {
        return this.mThumb;
    }

    public void switchScreenOrientation(boolean z) {
        if (z) {
            DensityUtils.setMarginPx(this.llContinue, 5, 0, 5, DensityUtils.dp2px(15.0f));
        } else {
            DensityUtils.setMarginPx(this.llContinue, 5, 0, 5, DensityUtils.dp2px(55.0f));
        }
    }

    public boolean onBackPressed() {
        if (this.mIsLocked) {
            show(StageState.Lock);
            try {
                Toast.makeText(getContext(), (int) R.string.dkplayer_lock_tip, 0).show();
                return true;
            } catch (WindowManager.BadTokenException unused) {
                Log.d("StandardVideoController", "onBackPressed: ");
                return true;
            }
        }
        return false;
    }
}
