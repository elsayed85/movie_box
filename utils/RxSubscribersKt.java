package com.movieboxpro.android.utils;

import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.LifecycleOwner;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.ServerException;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: RxSubscribers.kt */
@Metadata(d1 = {"\u0000`\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\r\u001a(\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00100\u000f\"\b\b\u0000\u0010\u0010*\u00020\t*\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u00030\u0001H\u0002\u001a\u0012\u0010\u0011\u001a\u00020\u0012*\b\u0012\u0004\u0012\u00020\u00030\u0005H\u0002\u001a\u001e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u000f*\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00030\u0001H\u0002\u001a\u001e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\r0\u000f*\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00030\u0001H\u0002\u001a\u008a\u0001\u0010\u0015\u001a\u00020\u0003\"\n\b\u0000\u0010\u0010\u0018\u0001*\u00020\t*\b\u0012\u0004\u0012\u0002H\u00100\u00162\u0014\b\u0006\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u000e\b\u0006\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u0014\b\u0006\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u00030\u00012\u0014\b\u0006\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00030\u00012\u0014\b\u0006\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00030\u0001H\u0086\bø\u0001\u0000\u001a\u0082\u0001\u0010\u001c\u001a\u00020\r\"\b\b\u0000\u0010\u0010*\u00020\t*\b\u0012\u0004\u0012\u0002H\u00100\u00162\u0014\b\u0002\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u0014\b\u0002\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00030\u00012\u0014\b\u0002\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00030\u00012\u0014\b\u0002\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u00030\u0001\u001al\u0010\u001c\u001a\u00020\r\"\b\b\u0000\u0010\u0010*\u00020\t*\b\u0012\u0004\u0012\u0002H\u00100\u001d2\u0014\b\u0002\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00030\u00012\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u0014\b\u0002\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00030\u00012\u0014\b\u0002\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u00030\u0001\u001a\u0092\u0001\u0010\u001e\u001a\u00020\u0003\"\n\b\u0000\u0010\u0010\u0018\u0001*\u00020\t*\b\u0012\u0004\u0012\u00020\u001f0\u001d2\u0006\u0010 \u001a\u00020!2\u0014\b\u0006\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u000e\b\u0006\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u0014\b\u0006\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u00030\u00012\u0014\b\u0006\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00030\u00012\u0014\b\u0006\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00030\u0001H\u0086\bø\u0001\u0000\u001a\u0098\u0001\u0010\"\u001a\u00020\u0003\"\n\b\u0000\u0010\u0010\u0018\u0001*\u00020\t*\b\u0012\u0004\u0012\u00020\u001f0\u001d2\u0006\u0010 \u001a\u00020!2\u0014\b\u0006\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u000e\b\u0006\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u001a\b\u0006\u0010\u0019\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100#\u0012\u0004\u0012\u00020\u00030\u00012\u0014\b\u0006\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00030\u00012\u0014\b\u0006\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00030\u0001H\u0086\bø\u0001\u0000\u001a\u0086\u0001\u0010$\u001a\u00020\u0003*\b\u0012\u0004\u0012\u00020\u001f0\u001d2\u0006\u0010 \u001a\u00020!2\u0014\b\u0006\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u000e\b\u0006\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u0014\b\u0006\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00030\u00012\u0014\b\u0006\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00030\u00012\u0014\b\u0006\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00030\u0001H\u0086\bø\u0001\u0000\u001a!\u0010%\u001a\b\u0012\u0004\u0012\u0002H\u00100\u001d\"\u0006\b\u0000\u0010\u0010\u0018\u0001*\b\u0012\u0004\u0012\u00020\u001f0\u001dH\u0086\b\u001a'\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100#0\u001d\"\u0006\b\u0000\u0010\u0010\u0018\u0001*\b\u0012\u0004\u0012\u00020\u001f0\u001dH\u0086\b\u001a\u0016\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001d*\b\u0012\u0004\u0012\u00020\u001f0\u001d\u001a$\u0010(\u001a\b\u0012\u0004\u0012\u0002H\u00100\u0016\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\u001d2\u0006\u0010)\u001a\u00020!\u001a)\u0010*\u001a\b\u0012\u0004\u0012\u0002H\u00100\u0016\"\u0006\b\u0000\u0010\u0010\u0018\u0001*\b\u0012\u0004\u0012\u00020\u001f0\u001d2\u0006\u0010)\u001a\u00020!H\u0086\b\u001a$\u0010+\u001a\b\u0012\u0004\u0012\u0002H\u00100\u0016\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\u001d2\u0006\u0010)\u001a\u00020!\u001a/\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100#0\u0016\"\u0006\b\u0000\u0010\u0010\u0018\u0001*\b\u0012\u0004\u0012\u00020\u001f0\u001d2\u0006\u0010)\u001a\u00020!H\u0086\b\u001a\u001e\u0010-\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0016*\b\u0012\u0004\u0012\u00020\u001f0\u001d2\u0006\u0010)\u001a\u00020!\u001a\u0092\u0001\u0010.\u001a\u00020\u0003\"\n\b\u0000\u0010\u0010\u0018\u0001*\u00020\t*\b\u0012\u0004\u0012\u0002H\u00100\u001d2\u0006\u0010 \u001a\u00020!2\u0014\b\u0006\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u000e\b\u0006\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u0014\b\u0006\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u00030\u00012\u0014\b\u0006\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00030\u00012\u0014\b\u0006\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00030\u0001H\u0086\bø\u0001\u0000\u001a\u0092\u0001\u0010/\u001a\u00020\u0003\"\n\b\u0000\u0010\u0010\u0018\u0001*\u00020\t*\b\u0012\u0004\u0012\u0002H\u00100\u001d2\u0006\u0010 \u001a\u00020!2\u0014\b\u0006\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u000e\b\u0006\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u0014\b\u0006\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u00030\u00012\u0014\b\u0006\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00030\u00012\u0014\b\u0006\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00030\u0001H\u0086\bø\u0001\u0000\"\u001a\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u00060"}, d2 = {"onApiExceptionErrorStub", "Lkotlin/Function1;", "Lcom/movieboxpro/android/http/ApiException;", "", "onCompleteStub", "Lkotlin/Function0;", "onErrorStub", "", "onNextStub", "", "onServerErrorStub", "Lcom/movieboxpro/android/http/ServerException;", "onSubscribeStub", "Lio/reactivex/disposables/Disposable;", "asConsumer", "Lio/reactivex/functions/Consumer;", ExifInterface.GPS_DIRECTION_TRUE, "asOnCompleteAction", "Lio/reactivex/functions/Action;", "asOnErrorConsumer", "asOnSubscribe", "subscribeKt", "Lcom/uber/autodispose/ObservableSubscribeProxy;", "onError", "onComplete", "onSuccess", "onStart", "onServerError", "subscribeTo", "Lio/reactivex/Observable;", "subscribeToBean", "", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "subscribeToList", "", "subscribeToMsg", "toBean", "toLists", "toMsg", "transform", "owner", "transformBean", "transformCompute", "transformList", "transformMsg", "transformSubscribe", "transformSubscribeCompute", "app_webRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RxSubscribersKt {
    private static final Function1<Object, Unit> onNextStub = RxSubscribersKt$onNextStub$1.INSTANCE;
    private static final Function1<Throwable, Unit> onErrorStub = RxSubscribersKt$onErrorStub$1.INSTANCE;
    private static final Function1<ApiException, Unit> onApiExceptionErrorStub = RxSubscribersKt$onApiExceptionErrorStub$1.INSTANCE;
    private static final Function0<Unit> onCompleteStub = RxSubscribersKt$onCompleteStub$1.INSTANCE;
    private static final Function1<Disposable, Unit> onSubscribeStub = RxSubscribersKt$onSubscribeStub$1.INSTANCE;
    private static final Function1<ServerException, Unit> onServerErrorStub = RxSubscribersKt$onServerErrorStub$1.INSTANCE;

    private static final <T> Consumer<T> asConsumer(final Function1<? super T, Unit> function1) {
        if (function1 == onNextStub) {
            Consumer<T> emptyConsumer = Functions.emptyConsumer();
            Intrinsics.checkNotNullExpressionValue(emptyConsumer, "emptyConsumer()");
            return emptyConsumer;
        }
        return new Consumer() { // from class: com.movieboxpro.android.utils.-$$Lambda$RxSubscribersKt$87OcD6wwR9BOxj6u1KNjXq1K50Y
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                RxSubscribersKt.m110asConsumer$lambda0(Function1.this, obj);
            }
        };
    }

    /* renamed from: asConsumer$lambda-0 */
    public static final void m110asConsumer$lambda0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    private static final Consumer<Throwable> asOnErrorConsumer(final Function1<? super Throwable, Unit> function1) {
        return new Consumer() { // from class: com.movieboxpro.android.utils.-$$Lambda$RxSubscribersKt$wiSUlaEgsxsvr3r-iSJjQpGJ7Bk
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                RxSubscribersKt.m112asOnErrorConsumer$lambda1(Function1.this, (Throwable) obj);
            }
        };
    }

    /* renamed from: asOnErrorConsumer$lambda-1 */
    public static final void m112asOnErrorConsumer$lambda1(Function1 tmp0, Throwable th) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(th);
    }

    private static final Action asOnCompleteAction(final Function0<Unit> function0) {
        if (function0 == onCompleteStub) {
            Action EMPTY_ACTION = Functions.EMPTY_ACTION;
            Intrinsics.checkNotNullExpressionValue(EMPTY_ACTION, "EMPTY_ACTION");
            return EMPTY_ACTION;
        }
        return new Action() { // from class: com.movieboxpro.android.utils.-$$Lambda$RxSubscribersKt$PC4rAePk3tZDH0995qa1WVxJlDQ
            @Override // io.reactivex.functions.Action
            public final void run() {
                RxSubscribersKt.m111asOnCompleteAction$lambda2(Function0.this);
            }
        };
    }

    /* renamed from: asOnCompleteAction$lambda-2 */
    public static final void m111asOnCompleteAction$lambda2(Function0 tmp0) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke();
    }

    private static final Consumer<Disposable> asOnSubscribe(final Function1<? super Disposable, Unit> function1) {
        return new Consumer() { // from class: com.movieboxpro.android.utils.-$$Lambda$RxSubscribersKt$EGol_LCvLz7Tr4HIZQ2UNN3EA_w
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                RxSubscribersKt.m113asOnSubscribe$lambda3(Function1.this, (Disposable) obj);
            }
        };
    }

    /* renamed from: asOnSubscribe$lambda-3 */
    public static final void m113asOnSubscribe$lambda3(Function1 tmp0, Disposable disposable) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(disposable);
    }

    public static /* synthetic */ Disposable subscribeTo$default(Observable observable, Function1 function1, Function0 function0, Function1 function12, Function1 function13, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = onErrorStub;
        }
        if ((i & 2) != 0) {
            function0 = onCompleteStub;
        }
        if ((i & 4) != 0) {
            function12 = onSubscribeStub;
        }
        if ((i & 8) != 0) {
            function13 = onNextStub;
        }
        return subscribeTo(observable, function1, function0, function12, function13);
    }

    public static final <T> Disposable subscribeTo(Observable<T> observable, Function1<? super Throwable, Unit> onError, Function0<Unit> onComplete, Function1<? super Disposable, Unit> onStart, Function1<? super T, Unit> onSuccess) {
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.checkNotNullParameter(onError, "onError");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        Intrinsics.checkNotNullParameter(onStart, "onStart");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Disposable subscribe = observable.subscribe(asConsumer(onSuccess), asOnErrorConsumer(onError), asOnCompleteAction(onComplete), asOnSubscribe(onStart));
        Intrinsics.checkNotNullExpressionValue(subscribe, "subscribe(\n        onSuc… onStart.asOnSubscribe())");
        return subscribe;
    }

    public static /* synthetic */ Disposable subscribeTo$default(ObservableSubscribeProxy observableSubscribeProxy, Function1 function1, Function0 function0, Function1 function12, Function1 function13, Function1 function14, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = onApiExceptionErrorStub;
        }
        if ((i & 2) != 0) {
            function0 = onCompleteStub;
        }
        Function0 function02 = function0;
        if ((i & 4) != 0) {
            function12 = onSubscribeStub;
        }
        Function1 function15 = function12;
        if ((i & 8) != 0) {
            function13 = onServerErrorStub;
        }
        Function1 function16 = function13;
        if ((i & 16) != 0) {
            function14 = onNextStub;
        }
        return subscribeTo(observableSubscribeProxy, function1, function02, function15, function16, function14);
    }

    public static final <T> Disposable subscribeTo(ObservableSubscribeProxy<T> observableSubscribeProxy, final Function1<? super ApiException, Unit> onError, Function0<Unit> onComplete, Function1<? super Disposable, Unit> onStart, final Function1<? super ServerException, Unit> onServerError, Function1<? super T, Unit> onSuccess) {
        Intrinsics.checkNotNullParameter(observableSubscribeProxy, "<this>");
        Intrinsics.checkNotNullParameter(onError, "onError");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        Intrinsics.checkNotNullParameter(onStart, "onStart");
        Intrinsics.checkNotNullParameter(onServerError, "onServerError");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Disposable subscribe = observableSubscribeProxy.subscribe(asConsumer(onSuccess), new Consumer() { // from class: com.movieboxpro.android.utils.-$$Lambda$RxSubscribersKt$M6HcnsIM_CjZNHBUDRyBHuLzvrE
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                RxSubscribersKt.m115subscribeTo$lambda4(Function1.this, onServerError, (Throwable) obj);
            }
        }, asOnCompleteAction(onComplete), asOnSubscribe(onStart));
        Intrinsics.checkNotNullExpressionValue(subscribe, "subscribe(\n        onSuc…nStart.asOnSubscribe(),\n)");
        return subscribe;
    }

    /* renamed from: subscribeTo$lambda-4 */
    public static final void m115subscribeTo$lambda4(Function1 onError, Function1 onServerError, Throwable th) {
        Intrinsics.checkNotNullParameter(onError, "$onError");
        Intrinsics.checkNotNullParameter(onServerError, "$onServerError");
        ApiException handleException = ApiException.handleException(th);
        Intrinsics.checkNotNullExpressionValue(handleException, "handleException(it)");
        onError.invoke(handleException);
        if (th instanceof ServerException) {
            onServerError.invoke(th);
        }
    }

    public static final ObservableSubscribeProxy<String> transformMsg(Observable<String> observable, LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.checkNotNullParameter(owner, "owner");
        return transform(toMsg(observable), owner);
    }

    public static final <T> ObservableSubscribeProxy<T> transform(Observable<T> observable, LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.checkNotNullParameter(owner, "owner");
        Object as = observable.compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(owner));
        Intrinsics.checkNotNullExpressionValue(as, "this.compose(RxUtils.rxS…indLifecycleOwner(owner))");
        return (ObservableSubscribeProxy) as;
    }

    public static final <T> ObservableSubscribeProxy<T> transformCompute(Observable<T> observable, LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.checkNotNullParameter(owner, "owner");
        Object as = observable.compose(RxUtils.rxSchedulerComputeHelper()).as(RxUtils.bindLifecycleOwner(owner));
        Intrinsics.checkNotNullExpressionValue(as, "this.compose(RxUtils.rxS…indLifecycleOwner(owner))");
        return (ObservableSubscribeProxy) as;
    }

    public static final /* synthetic */ <T> Observable<T> toBean(Observable<String> observable) {
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        ObservableSource compose = observable.compose(RxUtils.rxTranslate2Bean(Object.class));
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
        return (Observable) compose;
    }

    public static final /* synthetic */ <T> Observable<List<T>> toLists(Observable<String> observable) {
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        ObservableSource compose = observable.compose(RxUtils.rxTranslate2List(Object.class));
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2List(T::class.java))");
        return (Observable) compose;
    }

    public static final Observable<String> toMsg(Observable<String> observable) {
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Observable compose = observable.compose(RxUtils.rxTranslateMsg());
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxTranslateMsg())");
        return compose;
    }

    public static /* synthetic */ void subscribeKt$default(ObservableSubscribeProxy observableSubscribeProxy, Function1 onError, Function0 onComplete, Function1 onSuccess, Function1 onStart, Function1 onServerError, int i, Object obj) {
        if ((i & 1) != 0) {
            onError = RxSubscribersKt$subscribeKt$1.INSTANCE;
        }
        if ((i & 2) != 0) {
            onComplete = RxSubscribersKt$subscribeKt$2.INSTANCE;
        }
        if ((i & 4) != 0) {
            Intrinsics.needClassReification();
            onSuccess = RxSubscribersKt$subscribeKt$3.INSTANCE;
        }
        if ((i & 8) != 0) {
            onStart = RxSubscribersKt$subscribeKt$4.INSTANCE;
        }
        if ((i & 16) != 0) {
            onServerError = RxSubscribersKt$subscribeKt$5.INSTANCE;
        }
        Intrinsics.checkNotNullParameter(observableSubscribeProxy, "<this>");
        Intrinsics.checkNotNullParameter(onError, "onError");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onStart, "onStart");
        Intrinsics.checkNotNullParameter(onServerError, "onServerError");
        observableSubscribeProxy.subscribe(new RxSubscribersKt$subscribeKt$6(onSuccess), new RxSubscribersKt$subscribeKt$7(onError, onServerError), new RxSubscribersKt$subscribeKt$8(onComplete), new RxSubscribersKt$subscribeKt$9(onStart));
    }

    public static final /* synthetic */ <T> void subscribeKt(ObservableSubscribeProxy<T> observableSubscribeProxy, Function1<? super ApiException, Unit> onError, Function0<Unit> onComplete, Function1<? super T, Unit> onSuccess, Function1<? super Disposable, Unit> onStart, Function1<? super ServerException, Unit> onServerError) {
        Intrinsics.checkNotNullParameter(observableSubscribeProxy, "<this>");
        Intrinsics.checkNotNullParameter(onError, "onError");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onStart, "onStart");
        Intrinsics.checkNotNullParameter(onServerError, "onServerError");
        observableSubscribeProxy.subscribe(new RxSubscribersKt$subscribeKt$6(onSuccess), new RxSubscribersKt$subscribeKt$7(onError, onServerError), new RxSubscribersKt$subscribeKt$8(onComplete), new RxSubscribersKt$subscribeKt$9(onStart));
    }

    public static /* synthetic */ void subscribeToBean$default(Observable observable, LifecycleOwner lifecycleOwner, Function1 onError, Function0 onComplete, Function1 onSuccess, Function1 onStart, Function1 onServerError, int i, Object obj) {
        if ((i & 2) != 0) {
            onError = RxSubscribersKt$subscribeToBean$1.INSTANCE;
        }
        if ((i & 4) != 0) {
            onComplete = RxSubscribersKt$subscribeToBean$2.INSTANCE;
        }
        if ((i & 8) != 0) {
            Intrinsics.needClassReification();
            onSuccess = RxSubscribersKt$subscribeToBean$3.INSTANCE;
        }
        if ((i & 16) != 0) {
            onStart = RxSubscribersKt$subscribeToBean$4.INSTANCE;
        }
        if ((i & 32) != 0) {
            onServerError = RxSubscribersKt$subscribeToBean$5.INSTANCE;
        }
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(onError, "onError");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onStart, "onStart");
        Intrinsics.checkNotNullParameter(onServerError, "onServerError");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Observable compose = observable.compose(RxUtils.rxTranslate2Bean(Object.class));
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
        transform(compose, lifecycleOwner).subscribe(new RxSubscribersKt$subscribeKt$6(onSuccess), new RxSubscribersKt$subscribeKt$7(onError, onServerError), new RxSubscribersKt$subscribeKt$8(onComplete), new RxSubscribersKt$subscribeKt$9(onStart));
    }

    public static /* synthetic */ void subscribeToList$default(Observable observable, LifecycleOwner lifecycleOwner, Function1 onError, Function0 onComplete, Function1 onSuccess, Function1 onStart, Function1 onServerError, int i, Object obj) {
        if ((i & 2) != 0) {
            onError = RxSubscribersKt$subscribeToList$1.INSTANCE;
        }
        if ((i & 4) != 0) {
            onComplete = RxSubscribersKt$subscribeToList$2.INSTANCE;
        }
        if ((i & 8) != 0) {
            Intrinsics.needClassReification();
            onSuccess = RxSubscribersKt$subscribeToList$3.INSTANCE;
        }
        if ((i & 16) != 0) {
            onStart = RxSubscribersKt$subscribeToList$4.INSTANCE;
        }
        if ((i & 32) != 0) {
            onServerError = RxSubscribersKt$subscribeToList$5.INSTANCE;
        }
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(onError, "onError");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onStart, "onStart");
        Intrinsics.checkNotNullParameter(onServerError, "onServerError");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Observable compose = observable.compose(RxUtils.rxTranslate2List(Object.class));
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2List(T::class.java))");
        transform(compose, lifecycleOwner).subscribe(new RxSubscribersKt$subscribeKt$6(onSuccess), new RxSubscribersKt$subscribeKt$7(onError, onServerError), new RxSubscribersKt$subscribeKt$8(onComplete), new RxSubscribersKt$subscribeKt$9(onStart));
    }

    public static /* synthetic */ void subscribeToMsg$default(Observable observable, LifecycleOwner lifecycleOwner, Function1 onError, Function0 onComplete, Function1 onSuccess, Function1 onStart, Function1 onServerError, int i, Object obj) {
        if ((i & 2) != 0) {
            onError = RxSubscribersKt$subscribeToMsg$1.INSTANCE;
        }
        if ((i & 4) != 0) {
            onComplete = RxSubscribersKt$subscribeToMsg$2.INSTANCE;
        }
        if ((i & 8) != 0) {
            onSuccess = RxSubscribersKt$subscribeToMsg$3.INSTANCE;
        }
        if ((i & 16) != 0) {
            onStart = RxSubscribersKt$subscribeToMsg$4.INSTANCE;
        }
        if ((i & 32) != 0) {
            onServerError = RxSubscribersKt$subscribeToMsg$5.INSTANCE;
        }
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(onError, "onError");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onStart, "onStart");
        Intrinsics.checkNotNullParameter(onServerError, "onServerError");
        transformMsg(observable, lifecycleOwner).subscribe(new RxSubscribersKt$subscribeKt$6(onSuccess), new RxSubscribersKt$subscribeKt$7(onError, onServerError), new RxSubscribersKt$subscribeKt$8(onComplete), new RxSubscribersKt$subscribeKt$9(onStart));
    }

    public static final void subscribeToMsg(Observable<String> observable, LifecycleOwner lifecycleOwner, Function1<? super ApiException, Unit> onError, Function0<Unit> onComplete, Function1<? super String, Unit> onSuccess, Function1<? super Disposable, Unit> onStart, Function1<? super ServerException, Unit> onServerError) {
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(onError, "onError");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onStart, "onStart");
        Intrinsics.checkNotNullParameter(onServerError, "onServerError");
        transformMsg(observable, lifecycleOwner).subscribe(new RxSubscribersKt$subscribeKt$6(onSuccess), new RxSubscribersKt$subscribeKt$7(onError, onServerError), new RxSubscribersKt$subscribeKt$8(onComplete), new RxSubscribersKt$subscribeKt$9(onStart));
    }

    public static /* synthetic */ void transformSubscribe$default(Observable observable, LifecycleOwner lifecycleOwner, Function1 onError, Function0 onComplete, Function1 onSuccess, Function1 onStart, Function1 onServerError, int i, Object obj) {
        if ((i & 2) != 0) {
            onError = RxSubscribersKt$transformSubscribe$1.INSTANCE;
        }
        if ((i & 4) != 0) {
            onComplete = RxSubscribersKt$transformSubscribe$2.INSTANCE;
        }
        if ((i & 8) != 0) {
            Intrinsics.needClassReification();
            onSuccess = RxSubscribersKt$transformSubscribe$3.INSTANCE;
        }
        if ((i & 16) != 0) {
            onStart = RxSubscribersKt$transformSubscribe$4.INSTANCE;
        }
        if ((i & 32) != 0) {
            onServerError = RxSubscribersKt$transformSubscribe$5.INSTANCE;
        }
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(onError, "onError");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onStart, "onStart");
        Intrinsics.checkNotNullParameter(onServerError, "onServerError");
        transform(observable, lifecycleOwner).subscribe(new RxSubscribersKt$subscribeKt$6(onSuccess), new RxSubscribersKt$subscribeKt$7(onError, onServerError), new RxSubscribersKt$subscribeKt$8(onComplete), new RxSubscribersKt$subscribeKt$9(onStart));
    }

    public static final /* synthetic */ <T> void transformSubscribe(Observable<T> observable, LifecycleOwner lifecycleOwner, Function1<? super ApiException, Unit> onError, Function0<Unit> onComplete, Function1<? super T, Unit> onSuccess, Function1<? super Disposable, Unit> onStart, Function1<? super ServerException, Unit> onServerError) {
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(onError, "onError");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onStart, "onStart");
        Intrinsics.checkNotNullParameter(onServerError, "onServerError");
        transform(observable, lifecycleOwner).subscribe(new RxSubscribersKt$subscribeKt$6(onSuccess), new RxSubscribersKt$subscribeKt$7(onError, onServerError), new RxSubscribersKt$subscribeKt$8(onComplete), new RxSubscribersKt$subscribeKt$9(onStart));
    }

    public static /* synthetic */ void transformSubscribeCompute$default(Observable observable, LifecycleOwner lifecycleOwner, Function1 onError, Function0 onComplete, Function1 onSuccess, Function1 onStart, Function1 onServerError, int i, Object obj) {
        if ((i & 2) != 0) {
            onError = RxSubscribersKt$transformSubscribeCompute$1.INSTANCE;
        }
        if ((i & 4) != 0) {
            onComplete = RxSubscribersKt$transformSubscribeCompute$2.INSTANCE;
        }
        if ((i & 8) != 0) {
            Intrinsics.needClassReification();
            onSuccess = RxSubscribersKt$transformSubscribeCompute$3.INSTANCE;
        }
        if ((i & 16) != 0) {
            onStart = RxSubscribersKt$transformSubscribeCompute$4.INSTANCE;
        }
        if ((i & 32) != 0) {
            onServerError = RxSubscribersKt$transformSubscribeCompute$5.INSTANCE;
        }
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(onError, "onError");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onStart, "onStart");
        Intrinsics.checkNotNullParameter(onServerError, "onServerError");
        transformCompute(observable, lifecycleOwner).subscribe(new RxSubscribersKt$subscribeKt$6(onSuccess), new RxSubscribersKt$subscribeKt$7(onError, onServerError), new RxSubscribersKt$subscribeKt$8(onComplete), new RxSubscribersKt$subscribeKt$9(onStart));
    }

    public static final /* synthetic */ <T> void transformSubscribeCompute(Observable<T> observable, LifecycleOwner lifecycleOwner, Function1<? super ApiException, Unit> onError, Function0<Unit> onComplete, Function1<? super T, Unit> onSuccess, Function1<? super Disposable, Unit> onStart, Function1<? super ServerException, Unit> onServerError) {
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(onError, "onError");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onStart, "onStart");
        Intrinsics.checkNotNullParameter(onServerError, "onServerError");
        transformCompute(observable, lifecycleOwner).subscribe(new RxSubscribersKt$subscribeKt$6(onSuccess), new RxSubscribersKt$subscribeKt$7(onError, onServerError), new RxSubscribersKt$subscribeKt$8(onComplete), new RxSubscribersKt$subscribeKt$9(onStart));
    }

    public static final /* synthetic */ <T> ObservableSubscribeProxy<T> transformBean(Observable<String> observable, LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.checkNotNullParameter(owner, "owner");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Observable compose = observable.compose(RxUtils.rxTranslate2Bean(Object.class));
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
        return transform(compose, owner);
    }

    public static final /* synthetic */ <T> ObservableSubscribeProxy<List<T>> transformList(Observable<String> observable, LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.checkNotNullParameter(owner, "owner");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Observable compose = observable.compose(RxUtils.rxTranslate2List(Object.class));
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2List(T::class.java))");
        return transform(compose, owner);
    }

    public static final /* synthetic */ <T> void subscribeToBean(Observable<String> observable, LifecycleOwner lifecycleOwner, Function1<? super ApiException, Unit> onError, Function0<Unit> onComplete, Function1<? super T, Unit> onSuccess, Function1<? super Disposable, Unit> onStart, Function1<? super ServerException, Unit> onServerError) {
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(onError, "onError");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onStart, "onStart");
        Intrinsics.checkNotNullParameter(onServerError, "onServerError");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Observable compose = observable.compose(RxUtils.rxTranslate2Bean(Object.class));
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
        transform(compose, lifecycleOwner).subscribe(new RxSubscribersKt$subscribeKt$6(onSuccess), new RxSubscribersKt$subscribeKt$7(onError, onServerError), new RxSubscribersKt$subscribeKt$8(onComplete), new RxSubscribersKt$subscribeKt$9(onStart));
    }

    public static final /* synthetic */ <T> void subscribeToList(Observable<String> observable, LifecycleOwner lifecycleOwner, Function1<? super ApiException, Unit> onError, Function0<Unit> onComplete, Function1<? super List<? extends T>, Unit> onSuccess, Function1<? super Disposable, Unit> onStart, Function1<? super ServerException, Unit> onServerError) {
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(onError, "onError");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onStart, "onStart");
        Intrinsics.checkNotNullParameter(onServerError, "onServerError");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Observable compose = observable.compose(RxUtils.rxTranslate2List(Object.class));
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2List(T::class.java))");
        transform(compose, lifecycleOwner).subscribe(new RxSubscribersKt$subscribeKt$6(onSuccess), new RxSubscribersKt$subscribeKt$7(onError, onServerError), new RxSubscribersKt$subscribeKt$8(onComplete), new RxSubscribersKt$subscribeKt$9(onStart));
    }
}
