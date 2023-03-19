package com.movieboxpro.android.event;
/* loaded from: classes3.dex */
public class ChangeToPauseEvent {
    private int boxType;
    private String privateId;

    public ChangeToPauseEvent(int i, String str) {
        this.boxType = i;
        this.privateId = str;
    }

    public int getBoxType() {
        return this.boxType;
    }

    public void setBoxType(int i) {
        this.boxType = i;
    }

    public String getPrivateId() {
        return this.privateId;
    }

    public void setPrivateId(String str) {
        this.privateId = str;
    }
}
