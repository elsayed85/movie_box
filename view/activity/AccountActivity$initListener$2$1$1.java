package com.movieboxpro.android.view.activity;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.event.LogoutEvent;
import com.movieboxpro.android.http.ApiException;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;
/* compiled from: AccountActivity.kt */
@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0002H\u0016¨\u0006\f"}, d2 = {"com/movieboxpro/android/view/activity/AccountActivity$initListener$2$1$1", "Lcom/movieboxpro/android/base/HttpResponseObserver;", "", "onError", "", "e", "Lcom/movieboxpro/android/http/ApiException;", "onStart", "d", "Lio/reactivex/disposables/Disposable;", "onSuccess", "model", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AccountActivity$initListener$2$1$1 extends HttpResponseObserver<String> {
    final /* synthetic */ AccountActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AccountActivity$initListener$2$1$1(AccountActivity accountActivity) {
        this.this$0 = accountActivity;
    }

    @Override // com.movieboxpro.android.base.HttpResponseObserver
    public void onStart(Disposable d) {
        Intrinsics.checkNotNullParameter(d, "d");
        this.this$0.showLoadingView();
    }

    @Override // com.movieboxpro.android.base.HttpResponseObserver
    public void onSuccess(String model) {
        GoogleSignInClient googleSignInClient;
        Task<Void> signOut;
        Intrinsics.checkNotNullParameter(model, "model");
        this.this$0.hideLoadingView();
        googleSignInClient = this.this$0.mGoogleSignInClient;
        if (googleSignInClient == null || (signOut = googleSignInClient.signOut()) == null) {
            return;
        }
        final AccountActivity accountActivity = this.this$0;
        signOut.addOnCompleteListener(accountActivity, new OnCompleteListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$AccountActivity$initListener$2$1$1$SG5JDtl1FfDvOToNPjPpKsdRxPI
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                AccountActivity$initListener$2$1$1.m135onSuccess$lambda0(AccountActivity.this, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onSuccess$lambda-0  reason: not valid java name */
    public static final void m135onSuccess$lambda0(AccountActivity this$0, Task it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        App.logout();
        EventBus.getDefault().post(new LogoutEvent());
        this$0.finish();
    }

    @Override // com.movieboxpro.android.base.HttpResponseObserver
    public void onError(ApiException e) {
        GoogleSignInClient googleSignInClient;
        Task<Void> signOut;
        Intrinsics.checkNotNullParameter(e, "e");
        this.this$0.hideLoadingView();
        googleSignInClient = this.this$0.mGoogleSignInClient;
        if (googleSignInClient == null || (signOut = googleSignInClient.signOut()) == null) {
            return;
        }
        final AccountActivity accountActivity = this.this$0;
        signOut.addOnCompleteListener(accountActivity, new OnCompleteListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$AccountActivity$initListener$2$1$1$uz8xyvkXTxxtaxojBk4cKyBvE74
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                AccountActivity$initListener$2$1$1.m134onError$lambda1(AccountActivity.this, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onError$lambda-1  reason: not valid java name */
    public static final void m134onError$lambda1(AccountActivity this$0, Task it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        App.logout();
        EventBus.getDefault().post(new LogoutEvent());
        this$0.finish();
    }
}
