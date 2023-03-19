package com.movieboxpro.android.view.activity.videoplayer.floatmanager;

import android.content.Context;
import android.os.Build;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.dueeeke.videoplayer.util.PlayerUtils;
import com.google.android.material.badge.BadgeDrawable;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public class FloatView extends FrameLayout {
    private boolean firstTouch;
    private int floatX;
    private int floatY;
    private WindowManager.LayoutParams mParams;
    private WindowManager mWindowManager;

    public FloatView(Context context, int i, int i2) {
        super(context);
        this.firstTouch = true;
        this.floatX = i;
        this.floatY = i2;
        init();
    }

    private void init() {
        setBackgroundResource(R.drawable.shape_float_window_background);
        int dp2px = PlayerUtils.dp2px(getContext(), 1.0f);
        setPadding(dp2px, dp2px, dp2px, dp2px);
        initWindow();
    }

    private void initWindow() {
        this.mWindowManager = PlayerUtils.getWindowManager(getContext().getApplicationContext());
        this.mParams = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= 26) {
            this.mParams.type = 2038;
        } else {
            this.mParams.type = 2003;
        }
        this.mParams.format = -3;
        this.mParams.flags = 8;
        this.mParams.windowAnimations = R.style.FloatWindowAnimation;
        this.mParams.gravity = BadgeDrawable.TOP_START;
        int dp2px = PlayerUtils.dp2px(getContext(), 250.0f);
        this.mParams.width = dp2px;
        this.mParams.height = (dp2px * 9) / 16;
        this.mParams.x = this.floatX;
        this.mParams.y = this.floatY;
    }

    public boolean addToWindow() {
        if (this.mWindowManager != null) {
            if (Build.VERSION.SDK_INT >= 19) {
                if (isAttachedToWindow()) {
                    return false;
                }
                this.mWindowManager.addView(this, this.mParams);
                return true;
            }
            try {
                if (getParent() == null) {
                    this.mWindowManager.addView(this, this.mParams);
                }
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public boolean removeFromWindow() {
        if (this.mWindowManager != null) {
            if (Build.VERSION.SDK_INT >= 19) {
                if (isAttachedToWindow()) {
                    this.mWindowManager.removeViewImmediate(this);
                    return true;
                }
                return false;
            }
            try {
                if (getParent() != null) {
                    this.mWindowManager.removeViewImmediate(this);
                }
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return motionEvent.getAction() != 0;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int rawX = (int) motionEvent.getRawX();
        int rawY = (int) motionEvent.getRawY();
        int action = motionEvent.getAction();
        if (action == 1) {
            this.firstTouch = true;
        } else if (action == 2) {
            if (this.firstTouch) {
                this.floatX = (int) motionEvent.getX();
                double y = motionEvent.getY();
                double statusBarHeight = PlayerUtils.getStatusBarHeight(getContext());
                Double.isNaN(y);
                this.floatY = (int) (y + statusBarHeight);
                this.firstTouch = false;
            }
            this.mParams.x = rawX - this.floatX;
            this.mParams.y = rawY - this.floatY;
            this.mWindowManager.updateViewLayout(this, this.mParams);
        }
        return super.onTouchEvent(motionEvent);
    }
}
