package com.movieboxpro.android.model;

import java.io.Serializable;
/* loaded from: classes3.dex */
public class TrailerItem implements Serializable {
    private String img;
    private String url;
    private String videoId;

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String str) {
        this.img = str;
    }

    public String getVideoId() {
        return this.videoId;
    }

    public void setVideoId(String str) {
        this.videoId = str;
    }
}
