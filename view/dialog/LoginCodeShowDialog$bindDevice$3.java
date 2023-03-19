package com.movieboxpro.android.view.dialog;

import com.movieboxpro.android.app.App;
import com.movieboxpro.android.event.LoginEvent;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.view.dialog.DialogAction;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.greenrobot.eventbus.EventBus;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LoginCodeShowDialog.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class LoginCodeShowDialog$bindDevice$3 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ UserModel.UserData $userData;
    final /* synthetic */ LoginCodeShowDialog this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoginCodeShowDialog$bindDevice$3(LoginCodeShowDialog loginCodeShowDialog, UserModel.UserData userData) {
        super(1);
        this.this$0 = loginCodeShowDialog;
        this.$userData = userData;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(String str) {
        invoke2(str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(String str) {
        DialogAction.ActionListener actionListener;
        this.this$0.hideLoadingView();
        UserModel userModel = new UserModel();
        userModel.member = this.$userData;
        App.login(userModel);
        EventBus.getDefault().post(new LoginEvent());
        actionListener = this.this$0.loginListener;
        if (actionListener != null) {
            actionListener.onClick();
        }
        this.this$0.dismiss();
    }
}
