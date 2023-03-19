package com.movieboxpro.android.model;
/* loaded from: classes3.dex */
public class FilterCountry {
    private String country;
    private String icon;
    private int resId;
    private boolean select;

    public FilterCountry() {
    }

    public FilterCountry(String str, int i) {
        this.country = str;
        this.resId = i;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String str) {
        this.icon = str;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public int getResId() {
        return this.resId;
    }

    public void setResId(int i) {
        this.resId = i;
    }

    public boolean isSelect() {
        return this.select;
    }

    public void setSelect(boolean z) {
        this.select = z;
    }
}
