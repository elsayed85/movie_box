package com.movieboxpro.android.utils;

import android.os.Handler;
/* loaded from: classes3.dex */
public class ClickCounter {
    private int allTimes;
    private int currentTime = 0;
    private long deltaTime;
    private OnCountListener listener;
    private Handler mHandler;
    private long[] timesArray;

    /* loaded from: classes3.dex */
    public interface OnCountListener {
        void onCount(int i);

        void onFinish();
    }

    public ClickCounter(int i, long j) {
        if (i <= 1) {
            this.allTimes = 1;
        } else {
            this.allTimes = i;
        }
        if (j <= 500) {
            this.deltaTime = 500L;
        } else {
            this.deltaTime = j;
        }
        this.timesArray = new long[i];
        this.mHandler = new Handler();
    }

    public void setListener(OnCountListener onCountListener) {
        this.listener = onCountListener;
    }

    public void countClick() {
        int i;
        if (this.allTimes == 1) {
            OnCountListener onCountListener = this.listener;
            if (onCountListener != null) {
                onCountListener.onFinish();
                return;
            }
            return;
        }
        this.mHandler.removeCallbacksAndMessages(null);
        long currentTimeMillis = System.currentTimeMillis();
        long[] jArr = this.timesArray;
        int i2 = this.currentTime;
        jArr[i2] = currentTimeMillis;
        if (i2 > 0 && i2 < (i = this.allTimes)) {
            if (jArr[i2] - jArr[i2 - 1] > this.deltaTime) {
                this.currentTime = 0;
                return;
            } else if (i2 == i - 1) {
                OnCountListener onCountListener2 = this.listener;
                if (onCountListener2 != null) {
                    onCountListener2.onFinish();
                }
                this.currentTime = 0;
                return;
            }
        }
        int i3 = this.currentTime + 1;
        this.currentTime = i3;
        OnCountListener onCountListener3 = this.listener;
        if (onCountListener3 != null) {
            onCountListener3.onCount(i3);
        }
        this.mHandler.postDelayed(new Runnable() { // from class: com.movieboxpro.android.utils.ClickCounter.1
            @Override // java.lang.Runnable
            public void run() {
                ClickCounter.this.currentTime = 0;
            }
        }, this.deltaTime);
    }
}
