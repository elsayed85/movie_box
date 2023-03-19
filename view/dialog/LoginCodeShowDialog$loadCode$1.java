package com.movieboxpro.android.view.dialog;

import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.databinding.ObservableField;
import com.movieboxpro.android.databinding.DialogLoginCodeShowBinding;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.RetrofitCoroutineDSL;
import com.movieboxpro.android.utils.ToastUtils;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LoginCodeShowDialog.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00030\u0002H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "Lcom/movieboxpro/android/utils/RetrofitCoroutineDSL;", "Ljava/util/HashMap;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class LoginCodeShowDialog$loadCode$1 extends Lambda implements Function1<RetrofitCoroutineDSL<HashMap<?, ?>>, Unit> {
    final /* synthetic */ LoginCodeShowDialog this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoginCodeShowDialog$loadCode$1(LoginCodeShowDialog loginCodeShowDialog) {
        super(1);
        this.this$0 = loginCodeShowDialog;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(RetrofitCoroutineDSL<HashMap<?, ?>> retrofitCoroutineDSL) {
        invoke2(retrofitCoroutineDSL);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: LoginCodeShowDialog.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
    /* renamed from: com.movieboxpro.android.view.dialog.LoginCodeShowDialog$loadCode$1$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass1 extends Lambda implements Function0<Unit> {
        final /* synthetic */ LoginCodeShowDialog this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(LoginCodeShowDialog loginCodeShowDialog) {
            super(0);
            this.this$0 = loginCodeShowDialog;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2() {
            DialogLoginCodeShowBinding dialogLoginCodeShowBinding;
            DialogLoginCodeShowBinding dialogLoginCodeShowBinding2;
            dialogLoginCodeShowBinding = this.this$0.binding;
            DialogLoginCodeShowBinding dialogLoginCodeShowBinding3 = null;
            if (dialogLoginCodeShowBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                dialogLoginCodeShowBinding = null;
            }
            ProgressBar progressBar = dialogLoginCodeShowBinding.progressBar;
            Intrinsics.checkNotNullExpressionValue(progressBar, "binding.progressBar");
            CommonExtKt.visible(progressBar);
            dialogLoginCodeShowBinding2 = this.this$0.binding;
            if (dialogLoginCodeShowBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                dialogLoginCodeShowBinding3 = dialogLoginCodeShowBinding2;
            }
            TextView textView = dialogLoginCodeShowBinding3.tvTry;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.tvTry");
            CommonExtKt.gone(textView);
        }
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(RetrofitCoroutineDSL<HashMap<?, ?>> requestApi) {
        Intrinsics.checkNotNullParameter(requestApi, "$this$requestApi");
        requestApi.setApi(HttpRequest.Companion.post("Add_captcha").request());
        requestApi.onStart(new AnonymousClass1(this.this$0));
        requestApi.onSuccess(new AnonymousClass2(this.this$0));
        requestApi.onFail(new AnonymousClass3(this.this$0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: LoginCodeShowDialog.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Ljava/util/HashMap;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
    /* renamed from: com.movieboxpro.android.view.dialog.LoginCodeShowDialog$loadCode$1$2  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass2 extends Lambda implements Function1<HashMap<?, ?>, Unit> {
        final /* synthetic */ LoginCodeShowDialog this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(LoginCodeShowDialog loginCodeShowDialog) {
            super(1);
            this.this$0 = loginCodeShowDialog;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(HashMap<?, ?> hashMap) {
            invoke2(hashMap);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(HashMap<?, ?> it) {
            DialogLoginCodeShowBinding dialogLoginCodeShowBinding;
            Intrinsics.checkNotNullParameter(it, "it");
            dialogLoginCodeShowBinding = this.this$0.binding;
            if (dialogLoginCodeShowBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                dialogLoginCodeShowBinding = null;
            }
            ProgressBar progressBar = dialogLoginCodeShowBinding.progressBar;
            Intrinsics.checkNotNullExpressionValue(progressBar, "binding.progressBar");
            CommonExtKt.gone(progressBar);
            ObservableField<String> code = this.this$0.getCode();
            Object obj = it.get("code");
            if (obj != null) {
                code.set((String) obj);
                this.this$0.startTimer();
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: LoginCodeShowDialog.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/movieboxpro/android/http/ApiException;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
    /* renamed from: com.movieboxpro.android.view.dialog.LoginCodeShowDialog$loadCode$1$3  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass3 extends Lambda implements Function1<ApiException, Unit> {
        final /* synthetic */ LoginCodeShowDialog this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(LoginCodeShowDialog loginCodeShowDialog) {
            super(1);
            this.this$0 = loginCodeShowDialog;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ApiException apiException) {
            invoke2(apiException);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(ApiException it) {
            DialogLoginCodeShowBinding dialogLoginCodeShowBinding;
            DialogLoginCodeShowBinding dialogLoginCodeShowBinding2;
            Intrinsics.checkNotNullParameter(it, "it");
            dialogLoginCodeShowBinding = this.this$0.binding;
            DialogLoginCodeShowBinding dialogLoginCodeShowBinding3 = null;
            if (dialogLoginCodeShowBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                dialogLoginCodeShowBinding = null;
            }
            ProgressBar progressBar = dialogLoginCodeShowBinding.progressBar;
            Intrinsics.checkNotNullExpressionValue(progressBar, "binding.progressBar");
            CommonExtKt.gone(progressBar);
            dialogLoginCodeShowBinding2 = this.this$0.binding;
            if (dialogLoginCodeShowBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                dialogLoginCodeShowBinding3 = dialogLoginCodeShowBinding2;
            }
            TextView textView = dialogLoginCodeShowBinding3.tvTry;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.tvTry");
            CommonExtKt.visible(textView);
            ToastUtils.showShort(Intrinsics.stringPlus("Load failed:", it.getMessage()), new Object[0]);
        }
    }
}
