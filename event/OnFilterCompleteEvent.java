package com.movieboxpro.android.event;
/* loaded from: classes3.dex */
public class OnFilterCompleteEvent {
    private boolean isComplete;
    private boolean isMovie;

    public OnFilterCompleteEvent(boolean z, boolean z2) {
        this.isComplete = z;
        this.isMovie = z2;
    }

    public boolean isMovie() {
        return this.isMovie;
    }

    public void setMovie(boolean z) {
        this.isMovie = z;
    }

    public boolean isComplete() {
        return this.isComplete;
    }

    public void setComplete(boolean z) {
        this.isComplete = z;
    }
}
