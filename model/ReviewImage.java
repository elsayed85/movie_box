package com.movieboxpro.android.model;

import android.net.Uri;
/* loaded from: classes3.dex */
public class ReviewImage {
    private String path;
    private int type;
    private Uri uri;
    private String url;

    public Uri getUri() {
        return this.uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }
}
