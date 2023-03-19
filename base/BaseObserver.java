package com.movieboxpro.android.base;

import com.movieboxpro.android.http.ApiException;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
/* loaded from: classes3.dex */
public abstract class BaseObserver<T> implements Observer<T> {
    @Override // io.reactivex.Observer
    public void onComplete() {
    }

    public abstract void onError(ApiException apiException);

    @Override // io.reactivex.Observer
    public void onNext(T t) {
    }

    @Override // io.reactivex.Observer
    public void onSubscribe(Disposable disposable) {
    }

    @Override // io.reactivex.Observer
    public final void onError(Throwable th) {
        if (th instanceof ApiException) {
            onError((ApiException) th);
        } else {
            onError(ApiException.handleException(th));
        }
    }
}
