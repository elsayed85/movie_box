package com.movieboxpro.android.event;

import com.avery.subtitle.model.Subtitle;
import java.util.List;
/* loaded from: classes3.dex */
public class TransformSubtitleDataEvent {
    private String content;
    private String language;
    private List<Subtitle> subtitles;

    public TransformSubtitleDataEvent(String str, String str2, List<Subtitle> list) {
        this.content = str;
        this.language = str2;
        this.subtitles = list;
    }

    public List<Subtitle> getSubtitles() {
        return this.subtitles;
    }

    public void setSubtitles(List<Subtitle> list) {
        this.subtitles = list;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }
}
