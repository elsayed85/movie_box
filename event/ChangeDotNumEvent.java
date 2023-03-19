package com.movieboxpro.android.event;
/* loaded from: classes3.dex */
public class ChangeDotNumEvent {
    private int num;
    private int position;

    public ChangeDotNumEvent(int i, int i2) {
        this.position = i;
        this.num = i2;
    }

    public int getNum() {
        return this.num;
    }

    public void setNum(int i) {
        this.num = i;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        this.position = i;
    }
}
