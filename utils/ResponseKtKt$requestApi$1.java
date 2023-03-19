package com.movieboxpro.android.utils;

import androidx.exifinterface.media.ExifInterface;
import com.movieboxpro.android.base.LoadView;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import retrofit2.Call;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ResponseKt.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
@DebugMetadata(c = "com.movieboxpro.android.utils.ResponseKtKt$requestApi$1", f = "ResponseKt.kt", i = {0}, l = {56}, m = "invokeSuspend", n = {"coroutine"}, s = {"L$0"})
/* loaded from: classes3.dex */
public final class ResponseKtKt$requestApi$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Class<T> $clazz;
    final /* synthetic */ Function1<RetrofitCoroutineDSL<T>, Unit> $dsl;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ResponseKtKt$requestApi$1(Function1<? super RetrofitCoroutineDSL<T>, Unit> function1, Class<T> cls, Continuation<? super ResponseKtKt$requestApi$1> continuation) {
        super(2, continuation);
        this.$dsl = function1;
        this.$clazz = cls;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ResponseKtKt$requestApi$1 responseKtKt$requestApi$1 = new ResponseKtKt$requestApi$1(this.$dsl, this.$clazz, continuation);
        responseKtKt$requestApi$1.L$0 = obj;
        return responseKtKt$requestApi$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ResponseKtKt$requestApi$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Deferred async$default;
        RetrofitCoroutineDSL retrofitCoroutineDSL;
        Function1 onSuccess$app_webRelease;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            RetrofitCoroutineDSL retrofitCoroutineDSL2 = new RetrofitCoroutineDSL();
            this.$dsl.invoke(retrofitCoroutineDSL2);
            Function0<Unit> onStart$app_webRelease = retrofitCoroutineDSL2.getOnStart$app_webRelease();
            if (onStart$app_webRelease != null) {
                onStart$app_webRelease.invoke();
            }
            LoadView loadingView = retrofitCoroutineDSL2.getLoadingView();
            if (loadingView != null) {
                loadingView.showLoadingView();
            }
            Call<String> api = retrofitCoroutineDSL2.getApi();
            if (api != null) {
                async$default = BuildersKt__Builders_commonKt.async$default(coroutineScope, Dispatchers.getIO(), null, new ResponseKtKt$requestApi$1$1$deferred$1(api, this.$clazz, retrofitCoroutineDSL2, null), 2, null);
                async$default.invokeOnCompletion(new ResponseKtKt$requestApi$1$1$1(async$default, api, retrofitCoroutineDSL2));
                this.L$0 = retrofitCoroutineDSL2;
                this.label = 1;
                Object await = async$default.await(this);
                if (await == coroutine_suspended) {
                    return coroutine_suspended;
                }
                retrofitCoroutineDSL = retrofitCoroutineDSL2;
                obj = await;
            }
            return Unit.INSTANCE;
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            retrofitCoroutineDSL = (RetrofitCoroutineDSL) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        if (obj != null && (onSuccess$app_webRelease = retrofitCoroutineDSL.getOnSuccess$app_webRelease()) != null) {
            onSuccess$app_webRelease.invoke(obj);
        }
        LoadView loadingView2 = retrofitCoroutineDSL.getLoadingView();
        if (loadingView2 != null) {
            loadingView2.hideLoadingView();
        }
        Function0<Unit> onComplete$app_webRelease = retrofitCoroutineDSL.getOnComplete$app_webRelease();
        if (onComplete$app_webRelease != null) {
            onComplete$app_webRelease.invoke();
        }
        return Unit.INSTANCE;
    }
}
