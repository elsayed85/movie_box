package com.movieboxpro.android.model;
/* loaded from: classes3.dex */
public class AdParamModel {
    private int end;
    private String key;
    private int num;
    private int start;

    public AdParamModel(String str, int i) {
        this.key = str;
        this.num = i;
    }

    public int getStart() {
        return this.start;
    }

    public void setStart(int i) {
        this.start = i;
    }

    public int getEnd() {
        return this.end;
    }

    public void setEnd(int i) {
        this.end = i;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public int getNum() {
        return this.num;
    }

    public void setNum(int i) {
        this.num = i;
    }
}
