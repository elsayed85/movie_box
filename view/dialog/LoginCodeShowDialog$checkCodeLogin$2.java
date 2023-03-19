package com.movieboxpro.android.view.dialog;

import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.utils.ToastUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* compiled from: LoginCodeShowDialog.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/movieboxpro/android/http/ApiException;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class LoginCodeShowDialog$checkCodeLogin$2 extends Lambda implements Function1<ApiException, Unit> {
    public static final LoginCodeShowDialog$checkCodeLogin$2 INSTANCE = new LoginCodeShowDialog$checkCodeLogin$2();

    LoginCodeShowDialog$checkCodeLogin$2() {
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
        ToastUtils.showShort(Intrinsics.stringPlus("Load failed:", it.getMessage()), new Object[0]);
    }
}
