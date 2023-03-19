package com.movieboxpro.android.utils;

import androidx.exifinterface.media.ExifInterface;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.ServerException;
import com.movieboxpro.android.model.BaseResponse;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import retrofit2.Call;
import retrofit2.Response;
/* compiled from: ResponseKt.kt */
@Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
@DebugMetadata(c = "com.movieboxpro.android.utils.ResponseKtKt$requestApi$1$1$deferred$1", f = "ResponseKt.kt", i = {}, l = {30, 36, 42}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class ResponseKtKt$requestApi$1$1$deferred$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
    final /* synthetic */ Call<String> $call;
    final /* synthetic */ Class<T> $clazz;
    final /* synthetic */ RetrofitCoroutineDSL<T> $coroutine;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ResponseKtKt$requestApi$1$1$deferred$1(Call<String> call, Class<T> cls, RetrofitCoroutineDSL<T> retrofitCoroutineDSL, Continuation<? super ResponseKtKt$requestApi$1$1$deferred$1> continuation) {
        super(2, continuation);
        this.$call = call;
        this.$clazz = cls;
        this.$coroutine = retrofitCoroutineDSL;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ResponseKtKt$requestApi$1$1$deferred$1(this.$call, this.$clazz, this.$coroutine, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super T> continuation) {
        return ((ResponseKtKt$requestApi$1$1$deferred$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        BaseResponse baseResponse;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i != 0) {
                if (i == 1 || i == 2) {
                    ResultKt.throwOnFailure(obj);
                    return null;
                } else if (i == 3) {
                    ResultKt.throwOnFailure(obj);
                    return null;
                } else {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
            ResultKt.throwOnFailure(obj);
            Response<String> execute = this.$call.execute();
            if (execute.isSuccessful()) {
                String valueOf = String.valueOf(execute.body());
                if (Intrinsics.areEqual(this.$clazz, Reflection.getOrCreateKotlinClass(List.class))) {
                    Object parseObject = JSONObject.parseObject(valueOf, RxUtils.buildType(BaseResponse.class, List.class, this.$clazz), new Feature[0]);
                    Intrinsics.checkNotNullExpressionValue(parseObject, "{\n                      …                        }");
                    baseResponse = (BaseResponse) parseObject;
                } else {
                    Object parseObject2 = JSON.parseObject(valueOf, RxUtils.buildType(BaseResponse.class, this.$clazz), new Feature[0]);
                    Intrinsics.checkNotNullExpressionValue(parseObject2, "{\n                      …                        }");
                    baseResponse = (BaseResponse) parseObject2;
                }
                if (baseResponse.getCode() == 1) {
                    return baseResponse.getData();
                }
                this.label = 1;
                if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass1(this.$coroutine, baseResponse, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return null;
            }
            this.label = 2;
            if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass2(this.$coroutine, execute, null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return null;
        } catch (Exception e) {
            this.label = 3;
            if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass3(this.$coroutine, e, null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ResponseKt.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.movieboxpro.android.utils.ResponseKtKt$requestApi$1$1$deferred$1$1", f = "ResponseKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.movieboxpro.android.utils.ResponseKtKt$requestApi$1$1$deferred$1$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ BaseResponse<T> $baseResponse;
        final /* synthetic */ RetrofitCoroutineDSL<T> $coroutine;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(RetrofitCoroutineDSL<T> retrofitCoroutineDSL, BaseResponse<T> baseResponse, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$coroutine = retrofitCoroutineDSL;
            this.$baseResponse = baseResponse;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$coroutine, this.$baseResponse, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                Function1<ApiException, Unit> onFail$app_webRelease = this.$coroutine.getOnFail$app_webRelease();
                if (onFail$app_webRelease == null) {
                    return null;
                }
                onFail$app_webRelease.invoke(new ApiException(new ServerException(this.$baseResponse.getCode(), this.$baseResponse.getMsg()), this.$baseResponse.getCode()));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ResponseKt.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.movieboxpro.android.utils.ResponseKtKt$requestApi$1$1$deferred$1$2", f = "ResponseKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.movieboxpro.android.utils.ResponseKtKt$requestApi$1$1$deferred$1$2  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ RetrofitCoroutineDSL<T> $coroutine;
        final /* synthetic */ Response<String> $response;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(RetrofitCoroutineDSL<T> retrofitCoroutineDSL, Response<String> response, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$coroutine = retrofitCoroutineDSL;
            this.$response = response;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$coroutine, this.$response, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                Function1<ApiException, Unit> onFail$app_webRelease = this.$coroutine.getOnFail$app_webRelease();
                if (onFail$app_webRelease == null) {
                    return null;
                }
                onFail$app_webRelease.invoke(new ApiException(new Throwable(String.valueOf(this.$response.errorBody())), this.$response.code()));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ResponseKt.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.movieboxpro.android.utils.ResponseKtKt$requestApi$1$1$deferred$1$3", f = "ResponseKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.movieboxpro.android.utils.ResponseKtKt$requestApi$1$1$deferred$1$3  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ RetrofitCoroutineDSL<T> $coroutine;
        final /* synthetic */ Exception $e;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(RetrofitCoroutineDSL<T> retrofitCoroutineDSL, Exception exc, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.$coroutine = retrofitCoroutineDSL;
            this.$e = exc;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass3(this.$coroutine, this.$e, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                Function1<ApiException, Unit> onFail$app_webRelease = this.$coroutine.getOnFail$app_webRelease();
                if (onFail$app_webRelease == null) {
                    return null;
                }
                ApiException handleException = ApiException.handleException(this.$e);
                Intrinsics.checkNotNullExpressionValue(handleException, "handleException(e)");
                onFail$app_webRelease.invoke(handleException);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
