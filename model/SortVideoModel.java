package com.movieboxpro.android.model;
/* loaded from: classes3.dex */
public class SortVideoModel {
    private int display_order;
    private String id;

    public SortVideoModel(String str, int i) {
        this.id = str;
        this.display_order = i;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public int getDisplay_order() {
        return this.display_order;
    }

    public void setDisplay_order(int i) {
        this.display_order = i;
    }
}
