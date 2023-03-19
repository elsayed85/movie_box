package com.movieboxpro.android.model;
/* loaded from: classes3.dex */
public class FilterModel {
    private boolean isSelect;
    private String serviceText;
    private String text;

    public FilterModel(String str, String str2) {
        this.text = str;
        this.serviceText = str2;
    }

    public FilterModel(String str, String str2, boolean z) {
        this.text = str;
        this.serviceText = str2;
        this.isSelect = z;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public String getServiceText() {
        return this.serviceText;
    }

    public void setServiceText(String str) {
        this.serviceText = str;
    }

    public boolean isSelect() {
        return this.isSelect;
    }

    public void setSelect(boolean z) {
        this.isSelect = z;
    }
}
