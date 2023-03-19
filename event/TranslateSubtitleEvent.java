package com.movieboxpro.android.event;

import com.dueeeke.model.SrtPraseModel;
import java.util.List;
/* loaded from: classes3.dex */
public class TranslateSubtitleEvent {
    private List<SrtPraseModel> subtitles;

    public TranslateSubtitleEvent(List<SrtPraseModel> list) {
        this.subtitles = list;
    }

    public List<SrtPraseModel> getSubtitles() {
        return this.subtitles;
    }

    public void setSubtitles(List<SrtPraseModel> list) {
        this.subtitles = list;
    }
}
