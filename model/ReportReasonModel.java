package com.movieboxpro.android.model;
/* loaded from: classes3.dex */
public class ReportReasonModel {
    private boolean choose;
    private String content;
    private String reason;

    public ReportReasonModel(String str, boolean z) {
        this.reason = str;
        this.choose = z;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public boolean isChoose() {
        return this.choose;
    }

    public void setChoose(boolean z) {
        this.choose = z;
    }
}
