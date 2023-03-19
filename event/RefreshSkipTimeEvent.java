package com.movieboxpro.android.event;
/* loaded from: classes3.dex */
public class RefreshSkipTimeEvent {
    private int startTime;

    public RefreshSkipTimeEvent() {
        this.startTime = -1;
    }

    public RefreshSkipTimeEvent(int i) {
        this.startTime = -1;
        this.startTime = i;
    }

    public int getStartTime() {
        return this.startTime;
    }

    public void setStartTime(int i) {
        this.startTime = i;
    }
}
