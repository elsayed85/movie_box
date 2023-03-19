package com.movieboxpro.android.viewmodel;

import com.movieboxpro.android.BuildConfig;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.ResponseKtKt;
import com.movieboxpro.android.utils.RetrofitCoroutineDSL;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
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
import okhttp3.MultipartBody;
/* compiled from: UserInfoViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
@DebugMetadata(c = "com.movieboxpro.android.viewmodel.UserInfoViewModel$updateAvatar$1", f = "UserInfoViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class UserInfoViewModel$updateAvatar$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MultipartBody.Part $body;
    final /* synthetic */ String $data;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ UserInfoViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserInfoViewModel$updateAvatar$1(UserInfoViewModel userInfoViewModel, String str, MultipartBody.Part part, Continuation<? super UserInfoViewModel$updateAvatar$1> continuation) {
        super(2, continuation);
        this.this$0 = userInfoViewModel;
        this.$data = str;
        this.$body = part;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        UserInfoViewModel$updateAvatar$1 userInfoViewModel$updateAvatar$1 = new UserInfoViewModel$updateAvatar$1(this.this$0, this.$data, this.$body, continuation);
        userInfoViewModel$updateAvatar$1.L$0 = obj;
        return userInfoViewModel$updateAvatar$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UserInfoViewModel$updateAvatar$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: UserInfoViewModel.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "Lcom/movieboxpro/android/utils/RetrofitCoroutineDSL;", "Lcom/movieboxpro/android/model/user/UserModel$UserData;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
    /* renamed from: com.movieboxpro.android.viewmodel.UserInfoViewModel$updateAvatar$1$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass1 extends Lambda implements Function1<RetrofitCoroutineDSL<UserModel.UserData>, Unit> {
        final /* synthetic */ MultipartBody.Part $body;
        final /* synthetic */ String $data;
        final /* synthetic */ UserInfoViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(UserInfoViewModel userInfoViewModel, String str, MultipartBody.Part part) {
            super(1);
            this.this$0 = userInfoViewModel;
            this.$data = str;
            this.$body = part;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(RetrofitCoroutineDSL<UserModel.UserData> retrofitCoroutineDSL) {
            invoke2(retrofitCoroutineDSL);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(RetrofitCoroutineDSL<UserModel.UserData> requestApi) {
            Intrinsics.checkNotNullParameter(requestApi, "$this$requestApi");
            requestApi.setLoadingView(this.this$0.getLoadView());
            requestApi.setApi(Http.getService().callUploadAvatar(API.BASE_URL, this.$data, BuildConfig.APPLICATION_ID, App.versionCode, this.$body));
            requestApi.onSuccess(new C01101(this.this$0));
            requestApi.onFail(AnonymousClass2.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: UserInfoViewModel.kt */
        @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/movieboxpro/android/model/user/UserModel$UserData;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
        /* renamed from: com.movieboxpro.android.viewmodel.UserInfoViewModel$updateAvatar$1$1$1  reason: invalid class name and collision with other inner class name */
        /* loaded from: classes3.dex */
        public static final class C01101 extends Lambda implements Function1<UserModel.UserData, Unit> {
            final /* synthetic */ UserInfoViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01101(UserInfoViewModel userInfoViewModel) {
                super(1);
                this.this$0 = userInfoViewModel;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(UserModel.UserData userData) {
                invoke2(userData);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(UserModel.UserData it) {
                Intrinsics.checkNotNullParameter(it, "it");
                it.avatar += '?' + TimeUtils.getCurrentTime();
                UserModel.UserData userData = this.this$0.getUserInfo().get();
                if (userData != null) {
                    userData.avatar = it.avatar;
                }
                App.updateUser(it.avatar);
                this.this$0.getUserAvatar().set(it.avatar);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: UserInfoViewModel.kt */
        @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/movieboxpro/android/http/ApiException;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
        /* renamed from: com.movieboxpro.android.viewmodel.UserInfoViewModel$updateAvatar$1$1$2  reason: invalid class name */
        /* loaded from: classes3.dex */
        public static final class AnonymousClass2 extends Lambda implements Function1<ApiException, Unit> {
            public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

            AnonymousClass2() {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ApiException apiException) {
                invoke2(apiException);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(ApiException it) {
                Intrinsics.checkNotNullParameter(it, "it");
                ToastUtils.showShort(Intrinsics.stringPlus("Update avatar failed:", it.getMessage()), new Object[0]);
            }
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            ResponseKtKt.requestApi((CoroutineScope) this.L$0, UserModel.UserData.class, new AnonymousClass1(this.this$0, this.$data, this.$body));
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
