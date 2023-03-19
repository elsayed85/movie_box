package com.movieboxpro.android.model;

import com.dueeeke.model.SRTModel;
/* loaded from: classes3.dex */
public class FileMd5CheckModel {
    private boolean exists;
    private SRTModel.SubTitles srt_info;

    public boolean isExists() {
        return this.exists;
    }

    public void setExists(boolean z) {
        this.exists = z;
    }

    public SRTModel.SubTitles getSrt_info() {
        return this.srt_info;
    }

    public void setSrt_info(SRTModel.SubTitles subTitles) {
        this.srt_info = subTitles;
    }
}
