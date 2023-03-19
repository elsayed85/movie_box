package com.movieboxpro.android.base.mvp;

import com.uber.autodispose.AutoDisposeConverter;
/* loaded from: classes3.dex */
public interface BaseView {
    <T> AutoDisposeConverter<T> bindAutoDispose();

    void hideLoading();

    void onError(Throwable th);

    void showLoading();
}
