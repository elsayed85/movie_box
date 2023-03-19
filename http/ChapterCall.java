package com.movieboxpro.android.http;

import retrofit2.Call;
/* loaded from: classes3.dex */
public class ChapterCall {
    public String bookid;
    public Call<String> call;
    public int chaper;
    public String sourceid;

    public ChapterCall(Call<String> call, String str, String str2, int i) {
        this.call = call;
        this.bookid = str;
        this.sourceid = str2;
        this.chaper = i;
    }
}
