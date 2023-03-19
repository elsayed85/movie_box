package com.movieboxpro.android.model;
/* loaded from: classes3.dex */
public class LanguageModel {
    private String fullLanguage;
    private String language;
    private boolean select;

    public String getFullLanguage() {
        return this.fullLanguage;
    }

    public void setFullLanguage(String str) {
        this.fullLanguage = str;
    }

    public LanguageModel(String str, String str2) {
        this.language = str;
        this.fullLanguage = str2;
    }

    public LanguageModel(String str, String str2, boolean z) {
        this.language = str;
        this.fullLanguage = str2;
        this.select = z;
    }

    public LanguageModel(String str) {
        this.language = str;
    }

    public LanguageModel(String str, boolean z) {
        this.language = str;
        this.select = z;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public boolean isSelect() {
        return this.select;
    }

    public void setSelect(boolean z) {
        this.select = z;
    }
}
