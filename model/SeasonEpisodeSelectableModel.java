package com.movieboxpro.android.model;
/* loaded from: classes3.dex */
public class SeasonEpisodeSelectableModel {
    private boolean isSelect;
    private String text;

    public SeasonEpisodeSelectableModel(String str) {
        this.text = str;
    }

    public SeasonEpisodeSelectableModel(String str, boolean z) {
        this.text = str;
        this.isSelect = z;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public boolean isSelect() {
        return this.isSelect;
    }

    public void setSelect(boolean z) {
        this.isSelect = z;
    }
}
