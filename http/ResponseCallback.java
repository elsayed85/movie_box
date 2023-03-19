package com.movieboxpro.android.http;
/* loaded from: classes3.dex */
public interface ResponseCallback<T> {
    void onFailure(int i, String str);

    void onStart();

    void onSuccess(T t);
}
