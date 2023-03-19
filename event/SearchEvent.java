package com.movieboxpro.android.event;

import android.graphics.Bitmap;
/* loaded from: classes3.dex */
public class SearchEvent {
    public Bitmap bitmap;
    public String keyWord;
    public String tab;

    public SearchEvent(String str, String str2) {
        this.keyWord = str;
        this.tab = str2;
    }

    public SearchEvent(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
