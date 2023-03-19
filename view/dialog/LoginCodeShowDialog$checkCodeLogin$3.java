package com.movieboxpro.android.view.dialog;

import android.content.Context;
import com.alibaba.fastjson.JSONObject;
import com.movieboxpro.android.model.BaseResponse;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.view.activity.settings.DeviceManagerActivity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LoginCodeShowDialog.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "Lcom/movieboxpro/android/model/BaseResponse;", "", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class LoginCodeShowDialog$checkCodeLogin$3 extends Lambda implements Function1<BaseResponse<String>, Unit> {
    final /* synthetic */ LoginCodeShowDialog this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoginCodeShowDialog$checkCodeLogin$3(LoginCodeShowDialog loginCodeShowDialog) {
        super(1);
        this.this$0 = loginCodeShowDialog;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(BaseResponse<String> baseResponse) {
        invoke2(baseResponse);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(BaseResponse<String> baseResponse) {
        if (baseResponse.getCode() == -101) {
            Context context = this.this$0.getContext();
            if (context == null) {
                return;
            }
            DeviceManagerActivity.Companion companion = DeviceManagerActivity.Companion;
            String data = baseResponse.getData();
            Intrinsics.checkNotNullExpressionValue(data, "it.data");
            companion.start(context, data);
        } else if (baseResponse.getCode() == 1) {
            UserModel.UserData userModel = (UserModel.UserData) JSONObject.parseObject(baseResponse.getData(), UserModel.UserData.class);
            LoginCodeShowDialog loginCodeShowDialog = this.this$0;
            Intrinsics.checkNotNullExpressionValue(userModel, "userModel");
            loginCodeShowDialog.bindDevice(userModel);
        }
    }
}
