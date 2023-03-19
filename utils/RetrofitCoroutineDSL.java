package com.movieboxpro.android.utils;

import androidx.exifinterface.media.ExifInterface;
import com.movieboxpro.android.base.LoadView;
import com.movieboxpro.android.http.ApiException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import retrofit2.Call;
/* compiled from: RetrofitCoroutineDSL.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\r\u0010 \u001a\u00020\u0013H\u0000¢\u0006\u0002\b!J\u0014\u0010\u0014\u001a\u00020\u00132\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012J)\u0010\u0019\u001a\u00020\u00132!\u0010\"\u001a\u001d\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\u00130\u0017J\u0014\u0010\u001c\u001a\u00020\u00132\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012J\u001a\u0010\u001e\u001a\u00020\u00132\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00130\u0017R\"\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R.\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00122\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R:\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00172\u0014\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0017@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR.\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00122\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0016R:\u0010\u001e\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00172\u0014\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0017@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001b¨\u0006%"}, d2 = {"Lcom/movieboxpro/android/utils/RetrofitCoroutineDSL;", ExifInterface.GPS_DIRECTION_TRUE, "", "()V", "api", "Lretrofit2/Call;", "", "getApi", "()Lretrofit2/Call;", "setApi", "(Lretrofit2/Call;)V", "loadingView", "Lcom/movieboxpro/android/base/LoadView;", "getLoadingView", "()Lcom/movieboxpro/android/base/LoadView;", "setLoadingView", "(Lcom/movieboxpro/android/base/LoadView;)V", "<set-?>", "Lkotlin/Function0;", "", "onComplete", "getOnComplete$app_webRelease", "()Lkotlin/jvm/functions/Function0;", "Lkotlin/Function1;", "Lcom/movieboxpro/android/http/ApiException;", "onFail", "getOnFail$app_webRelease", "()Lkotlin/jvm/functions/Function1;", "onStart", "getOnStart$app_webRelease", "onSuccess", "getOnSuccess$app_webRelease", "clean", "clean$app_webRelease", "block", "Lkotlin/ParameterName;", "name", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RetrofitCoroutineDSL<T> {
    private Call<String> api;
    private LoadView loadingView;
    private Function0<Unit> onComplete;
    private Function1<? super ApiException, Unit> onFail;
    private Function0<Unit> onStart;
    private Function1<? super T, Unit> onSuccess;

    public final Call<String> getApi() {
        return this.api;
    }

    public final void setApi(Call<String> call) {
        this.api = call;
    }

    public final LoadView getLoadingView() {
        return this.loadingView;
    }

    public final void setLoadingView(LoadView loadView) {
        this.loadingView = loadView;
    }

    public final Function1<T, Unit> getOnSuccess$app_webRelease() {
        return (Function1<? super T, Unit>) this.onSuccess;
    }

    public final Function1<ApiException, Unit> getOnFail$app_webRelease() {
        return this.onFail;
    }

    public final Function0<Unit> getOnComplete$app_webRelease() {
        return this.onComplete;
    }

    public final Function0<Unit> getOnStart$app_webRelease() {
        return this.onStart;
    }

    public final void onSuccess(Function1<? super T, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        this.onSuccess = block;
    }

    public final void onFail(Function1<? super ApiException, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        this.onFail = block;
    }

    public final void onStart(Function0<Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        this.onStart = block;
    }

    public final void onComplete(Function0<Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        this.onComplete = block;
    }

    public final void clean$app_webRelease() {
        this.onSuccess = null;
        this.onComplete = null;
        this.onFail = null;
        this.onStart = null;
    }
}
