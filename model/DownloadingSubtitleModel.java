package com.movieboxpro.android.model;

import com.dueeeke.model.SRTModel;
/* loaded from: classes3.dex */
public class DownloadingSubtitleModel {
    private boolean complete;
    private int progress;
    private boolean selected;
    private SRTModel.SubTitles subTitles;

    public DownloadingSubtitleModel(SRTModel.SubTitles subTitles) {
        this.subTitles = subTitles;
    }

    public SRTModel.SubTitles getSubTitles() {
        return this.subTitles;
    }

    public void setSubTitles(SRTModel.SubTitles subTitles) {
        this.subTitles = subTitles;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(boolean z) {
        this.selected = z;
    }

    public boolean isComplete() {
        return this.complete;
    }

    public void setComplete(boolean z) {
        this.complete = z;
    }

    public int getProgress() {
        return this.progress;
    }

    public void setProgress(int i) {
        this.progress = i;
    }
}
