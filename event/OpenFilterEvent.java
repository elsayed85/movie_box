package com.movieboxpro.android.event;

import com.movieboxpro.android.model.common.Gener;
import java.util.List;
/* loaded from: classes3.dex */
public class OpenFilterEvent {
    private List<Gener> geners;
    private int type;

    public OpenFilterEvent() {
    }

    public OpenFilterEvent(int i, List<Gener> list) {
        this.type = i;
        this.geners = list;
    }

    public List<Gener> getGeners() {
        return this.geners;
    }

    public void setGeners(List<Gener> list) {
        this.geners = list;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }
}
