package com.movieboxpro.android.model.search;
/* loaded from: classes3.dex */
public class SearchWordList {
    public static final int TYPE_AD = 6;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_ITEM1 = 1;
    public static final int TYPE_ITEM2 = 2;
    public static final int TYPE_ITEM3 = 3;
    public static final int TYPE_ITEM4 = 4;
    public static final int TYPE_ITEM5 = 5;
    public String KeyWord;
    public String Power;
    public String dateline;
    public String id;
    public String keyword;
    public String uid;
    public int viewType;

    public SearchWordList() {
        this.viewType = 0;
    }

    public SearchWordList(int i, String str) {
        this.viewType = 0;
        this.viewType = i;
        this.KeyWord = str;
    }
}
