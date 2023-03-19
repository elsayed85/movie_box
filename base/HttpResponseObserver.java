package com.movieboxpro.android.base;

import android.util.Pair;
import com.movieboxpro.android.http.ApiException;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;
/* loaded from: classes3.dex */
public abstract class HttpResponseObserver<T> implements Observer<T> {
    @Override // io.reactivex.Observer
    public void onComplete() {
    }

    public abstract void onError(ApiException apiException);

    public void onException(Throwable th) {
    }

    public abstract void onStart(Disposable disposable);

    public abstract void onSuccess(T t);

    @Override // io.reactivex.Observer
    public void onSubscribe(Disposable disposable) {
        onStart(disposable);
    }

    @Override // io.reactivex.Observer
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override // io.reactivex.Observer
    public final void onError(Throwable th) {
        onException(th);
        if (th instanceof ApiException) {
            onError((ApiException) th);
        } else {
            onError(ApiException.handleException(th));
        }
    }

    public static Pair<Integer, String> parseException(Throwable th) {
        HttpException httpException;
        Response<?> response;
        ResponseBody errorBody;
        if ((th instanceof HttpException) && (response = (httpException = (HttpException) th).response()) != null && (errorBody = response.errorBody()) != null) {
            try {
                return new Pair<>(Integer.valueOf(httpException.code()), errorBody.string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Pair<>(-1, "");
    }
}
