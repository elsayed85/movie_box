package com.movieboxpro.android.view.activity.vlcvideoplayer.controller;

import android.content.Context;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.core.content.ContextCompat;
import com.dueeeke.videoplayer.StageState;
import com.dueeeke.videoplayer.util.PlayerUtils;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.vlcvideoplayer.widget.CenterView;
/* loaded from: classes3.dex */
public abstract class GestureVideoController extends BaseVideoController {
    private float audioMax;
    private boolean downUp;
    private boolean isVolumeMax;
    private float lastIndex;
    protected AudioManager mAudioManager;
    protected float mBrightness;
    protected CenterView mCenterView;
    protected GestureDetector mGestureDetector;
    private float mIndex;
    protected boolean mIsGestureEnabled;
    protected boolean mNeedSeek;
    protected int mPosition;
    protected int mStreamVolume;
    private float overVolume;
    private float touchY;

    protected int getVolume() {
        return 100;
    }

    protected void seekProgress(long j) {
    }

    protected void seekProgressEnd() {
    }

    protected void setVolume(int i) {
    }

    protected void showForwardBackView(int i) {
    }

    protected void slideSeekTo() {
    }

    protected void toggleLongSpeedPlay(boolean z) {
    }

    public GestureVideoController(Context context) {
        super(context);
        this.isVolumeMax = false;
        this.downUp = false;
        this.lastIndex = 0.0f;
        this.mIndex = 0.0f;
        this.overVolume = 0.0f;
    }

    public GestureVideoController(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isVolumeMax = false;
        this.downUp = false;
        this.lastIndex = 0.0f;
        this.mIndex = 0.0f;
        this.overVolume = 0.0f;
    }

    public GestureVideoController(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isVolumeMax = false;
        this.downUp = false;
        this.lastIndex = 0.0f;
        this.mIndex = 0.0f;
        this.overVolume = 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.controller.BaseVideoController
    public void initView() {
        super.initView();
        CenterView centerView = new CenterView(getContext());
        this.mCenterView = centerView;
        centerView.setVisibility(8);
        addView(this.mCenterView);
        AudioManager audioManager = (AudioManager) getContext().getSystemService("audio");
        this.mAudioManager = audioManager;
        this.audioMax = audioManager.getStreamMaxVolume(3);
        this.mGestureDetector = new GestureDetector(getContext(), new MyGestureListener());
        setOnTouchListener(new View.OnTouchListener() { // from class: com.movieboxpro.android.view.activity.vlcvideoplayer.controller.GestureVideoController.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return GestureVideoController.this.mGestureDetector.onTouchEvent(motionEvent);
            }
        });
    }

    /* loaded from: classes3.dex */
    protected class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private boolean mChangeBrightness;
        private boolean mChangePosition;
        private boolean mChangeVolume;
        private boolean mFirstTouch;

        protected MyGestureListener() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            if (!GestureVideoController.this.mIsGestureEnabled || PlayerUtils.isEdge(GestureVideoController.this.getContext(), motionEvent)) {
                return super.onDown(motionEvent);
            }
            GestureVideoController gestureVideoController = GestureVideoController.this;
            gestureVideoController.mStreamVolume = gestureVideoController.mAudioManager.getStreamVolume(3);
            GestureVideoController gestureVideoController2 = GestureVideoController.this;
            gestureVideoController2.mBrightness = PlayerUtils.scanForActivity(gestureVideoController2.getContext()).getWindow().getAttributes().screenBrightness;
            this.mFirstTouch = true;
            this.mChangePosition = false;
            this.mChangeBrightness = false;
            this.mChangeVolume = false;
            GestureVideoController.this.isVolumeMax = false;
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            super.onLongPress(motionEvent);
            if (GestureVideoController.this.mIsLocked) {
                return;
            }
            if (GestureVideoController.this.mShowing) {
                GestureVideoController.this.hide(StageState.SingleTab);
            }
            GestureVideoController.this.toggleLongSpeedPlay(true);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            if (GestureVideoController.this.mShowing) {
                GestureVideoController.this.hide(StageState.SingleTab);
                return true;
            }
            GestureVideoController.this.show(StageState.SingleTab);
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (motionEvent == null || motionEvent2 == null) {
                return false;
            }
            if (!GestureVideoController.this.mIsGestureEnabled || PlayerUtils.isEdge(GestureVideoController.this.getContext(), motionEvent)) {
                return super.onScroll(motionEvent, motionEvent2, f, f2);
            }
            float x = motionEvent.getX() - motionEvent2.getX();
            float y = motionEvent.getY() - motionEvent2.getY();
            if (this.mFirstTouch) {
                boolean z = Math.abs(f) >= Math.abs(f2);
                this.mChangePosition = z;
                if (!z) {
                    if (motionEvent2.getX() > PlayerUtils.getScreenWidth(GestureVideoController.this.getContext(), true) / 2) {
                        this.mChangeVolume = true;
                    } else {
                        this.mChangeBrightness = true;
                    }
                }
                this.mFirstTouch = false;
            }
            if (this.mChangePosition) {
                if (!GestureVideoController.this.isLoading) {
                    GestureVideoController.this.slideToChangePosition(x);
                }
            } else if (this.mChangeBrightness) {
                GestureVideoController.this.slideToChangeBrightness(y);
            } else if (this.mChangeVolume) {
                GestureVideoController.this.slideToChangeVolume(y);
                GestureVideoController.this.downUp = true;
            }
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            if (!GestureVideoController.this.mIsLocked) {
                float x = motionEvent.getX();
                if (x > (PlayerUtils.getScreenWidth(GestureVideoController.this.getContext(), true) / 4) * 3) {
                    GestureVideoController.this.showForwardBackView(15);
                } else if (x < PlayerUtils.getScreenWidth(GestureVideoController.this.getContext(), true) / 4) {
                    GestureVideoController.this.showForwardBackView(-15);
                } else {
                    GestureVideoController.this.doPauseResume();
                }
            }
            return true;
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = motionEvent.getAction() == 1;
        if (z) {
            this.downUp = false;
            this.lastIndex = this.mIndex;
            toggleLongSpeedPlay(false);
        }
        if (!this.mGestureDetector.onTouchEvent(motionEvent) && z) {
            if (this.mCenterView.getVisibility() == 0) {
                this.mCenterView.setVisibility(8);
            }
            if (this.mNeedSeek) {
                this.mMediaPlayer.seekTo(this.mPosition);
                this.mNeedSeek = false;
                seekProgressEnd();
                slideSeekTo();
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void slideToChangePosition(float f) {
        this.mCenterView.setVisibility(0);
        this.mCenterView.setProVisibility(8);
        int measuredWidth = getMeasuredWidth();
        int duration = (int) this.mMediaPlayer.getDuration();
        int currentPosition = (int) this.mMediaPlayer.getCurrentPosition();
        int i = (int) ((((-f) / measuredWidth) * 120000.0f) + currentPosition);
        if (i > currentPosition) {
            this.mCenterView.setIcon(R.drawable.dkplayer_ic_action_fast_forward);
        } else {
            this.mCenterView.setIcon(R.drawable.dkplayer_ic_action_fast_rewind);
        }
        if (i > duration) {
            i = duration;
        }
        int i2 = i >= 0 ? i : 0;
        this.mPosition = i2;
        seekProgress(i2);
        CenterView centerView = this.mCenterView;
        centerView.setTextView(stringForTime(i2) + "/" + stringForTime(duration));
        this.mNeedSeek = true;
    }

    protected void slideToChangeBrightness(float f) {
        this.mCenterView.setVisibility(0);
        this.mCenterView.setProVisibility(0);
        Window window = PlayerUtils.scanForActivity(getContext()).getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        this.mCenterView.setIcon(R.drawable.dkplayer_ic_action_brightness);
        int measuredHeight = getMeasuredHeight();
        if (this.mBrightness == -1.0f) {
            this.mBrightness = 0.5f;
        }
        float f2 = (((f * 2.0f) / measuredHeight) * 1.0f) + this.mBrightness;
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        float f3 = f2 <= 1.0f ? f2 : 1.0f;
        int i = (int) (100.0f * f3);
        CenterView centerView = this.mCenterView;
        centerView.setTextView(i + "%");
        this.mCenterView.setProPercent(i);
        attributes.screenBrightness = f3;
        window.setAttributes(attributes);
    }

    protected void slideToChangeVolume(float f) {
        float f2;
        if (this.isVolumeMax) {
            return;
        }
        this.mCenterView.setVisibility(0);
        this.mCenterView.setProVisibility(0);
        if (this.lastIndex == 0.0f) {
            this.lastIndex = this.mStreamVolume;
        }
        float f3 = this.audioMax;
        float measuredHeight = ((f * 2.0f) / getMeasuredHeight()) * f3;
        if (this.overVolume == 0.0f) {
            f2 = this.mStreamVolume;
        } else {
            f2 = this.lastIndex;
        }
        float f4 = f2 + measuredHeight;
        if (!this.downUp) {
            this.lastIndex = f4;
        }
        this.mIndex = f4;
        if (f4 < 0.0f) {
            this.mCenterView.setIcon(R.drawable.dkplayer_ic_action_volume_off);
            f4 = 0.0f;
        } else {
            this.mCenterView.setIcon(R.drawable.dkplayer_ic_action_volume_up);
        }
        int i = this.overVolume == 0.0f ? (int) ((f4 / f3) * 100.0f) : ((int) (((f4 - this.audioMax) / f3) * 100.0f)) + 100;
        if (i >= 100) {
            if (i < 200) {
                if (this.overVolume == 0.0f) {
                    this.isVolumeMax = true;
                    ToastUtils.showShort("Slide up again to go beyond 100%");
                    if (getVolume() != 100) {
                        setVolume(100);
                    }
                    i = 100;
                } else {
                    setVolume(i);
                }
            } else {
                if (getVolume() != 200) {
                    setVolume(200);
                }
                i = 200;
            }
            this.overVolume = f3;
        } else {
            this.overVolume = 0.0f;
            if (getVolume() != 100) {
                setVolume(100);
            }
            f3 = f4;
        }
        if (i > 100) {
            this.mCenterView.setTextColor(ContextCompat.getColor(App.getContext(), R.color.red));
        } else {
            this.mCenterView.setTextColor(ContextCompat.getColor(App.getContext(), R.color.white));
        }
        this.mCenterView.setTextView(i + "%");
        this.mCenterView.setProPercent(i);
        try {
            this.mAudioManager.setStreamVolume(3, (int) f3, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
