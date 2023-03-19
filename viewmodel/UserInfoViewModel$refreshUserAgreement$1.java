package com.movieboxpro.android.viewmodel;

import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.model.UserAgreementModel;
import com.movieboxpro.android.utils.ResponseKtKt;
import com.movieboxpro.android.utils.RetrofitCoroutineDSL;
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
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: UserInfoViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
@DebugMetadata(c = "com.movieboxpro.android.viewmodel.UserInfoViewModel$refreshUserAgreement$1", f = "UserInfoViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class UserInfoViewModel$refreshUserAgreement$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ UserInfoViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserInfoViewModel$refreshUserAgreement$1(UserInfoViewModel userInfoViewModel, Continuation<? super UserInfoViewModel$refreshUserAgreement$1> continuation) {
        super(2, continuation);
        this.this$0 = userInfoViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        UserInfoViewModel$refreshUserAgreement$1 userInfoViewModel$refreshUserAgreement$1 = new UserInfoViewModel$refreshUserAgreement$1(this.this$0, continuation);
        userInfoViewModel$refreshUserAgreement$1.L$0 = obj;
        return userInfoViewModel$refreshUserAgreement$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UserInfoViewModel$refreshUserAgreement$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: UserInfoViewModel.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "Lcom/movieboxpro/android/utils/RetrofitCoroutineDSL;", "Lcom/movieboxpro/android/model/UserAgreementModel;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
    /* renamed from: com.movieboxpro.android.viewmodel.UserInfoViewModel$refreshUserAgreement$1$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass1 extends Lambda implements Function1<RetrofitCoroutineDSL<UserAgreementModel>, Unit> {
        final /* synthetic */ UserInfoViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(UserInfoViewModel userInfoViewModel) {
            super(1);
            this.this$0 = userInfoViewModel;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(RetrofitCoroutineDSL<UserAgreementModel> retrofitCoroutineDSL) {
            invoke2(retrofitCoroutineDSL);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: UserInfoViewModel.kt */
        @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/movieboxpro/android/model/UserAgreementModel;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
        /* renamed from: com.movieboxpro.android.viewmodel.UserInfoViewModel$refreshUserAgreement$1$1$1  reason: invalid class name and collision with other inner class name */
        /* loaded from: classes3.dex */
        public static final class C01091 extends Lambda implements Function1<UserAgreementModel, Unit> {
            final /* synthetic */ UserInfoViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01091(UserInfoViewModel userInfoViewModel) {
                super(1);
                this.this$0 = userInfoViewModel;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(UserAgreementModel userAgreementModel) {
                invoke2(userAgreementModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(UserAgreementModel it) {
                Intrinsics.checkNotNullParameter(it, "it");
                this.this$0.getUserAgreementModel().postValue(it);
            }
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(RetrofitCoroutineDSL<UserAgreementModel> requestApi) {
            Intrinsics.checkNotNullParameter(requestApi, "$this$requestApi");
            requestApi.setApi(HttpRequest.Companion.post("User_agreement").request());
            requestApi.onSuccess(new C01091(this.this$0));
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            ResponseKtKt.requestApi((CoroutineScope) this.L$0, UserAgreementModel.class, new AnonymousClass1(this.this$0));
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
