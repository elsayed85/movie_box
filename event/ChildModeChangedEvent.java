package com.movieboxpro.android.event;
/* loaded from: classes3.dex */
public class ChildModeChangedEvent {
    private boolean isOpen;

    public ChildModeChangedEvent(boolean z) {
        this.isOpen = z;
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    public void setOpen(boolean z) {
        this.isOpen = z;
    }
}
