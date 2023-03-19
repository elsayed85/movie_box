package com.movieboxpro.android.view.fragment.userinfo;

import androidx.lifecycle.LifecycleOwner;
import com.movieboxpro.android.BuildConfig;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.base.mvp.BasePresenter;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.http.ServerException;
import com.movieboxpro.android.model.NoticeCountModel;
import com.movieboxpro.android.model.UserAgreementModel;
import com.movieboxpro.android.model.common.NetTestModel;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.fragment.userinfo.UserInfoContract;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: UserInfoPresenter.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\b\u0010\f\u001a\u00020\bH\u0016J\b\u0010\r\u001a\u00020\bH\u0016J\u0010\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\nH\u0016J\b\u0010\u0010\u001a\u00020\bH\u0016¨\u0006\u0011"}, d2 = {"Lcom/movieboxpro/android/view/fragment/userinfo/UserInfoPresenter;", "Lcom/movieboxpro/android/base/mvp/BasePresenter;", "Lcom/movieboxpro/android/view/fragment/userinfo/UserInfoContract$View;", "Lcom/movieboxpro/android/view/fragment/userinfo/UserInfoContract$Presenter;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "(Landroidx/lifecycle/LifecycleOwner;)V", "doTvLogin", "", "id", "", "doWebLogin", "getNoticeCount", "getTestSpeedInfo", "getUserAgreement", "uid", "signOut", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UserInfoPresenter extends BasePresenter<UserInfoContract.View> implements UserInfoContract.Presenter {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserInfoPresenter(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
    }

    @Override // com.movieboxpro.android.view.fragment.userinfo.UserInfoContract.Presenter
    public void getNoticeCount() {
        Observable<String> asRequest = HttpRequest.Companion.post("User_activity_unread_count").asRequest();
        LifecycleOwner mLifecycleOwner = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner, "mLifecycleOwner");
        Observable<R> compose = asRequest.compose(RxUtils.rxTranslate2Bean(NoticeCountModel.class));
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(compose, mLifecycleOwner), null, null, null, null, new UserInfoPresenter$getNoticeCount$1(this), 15, null);
    }

    @Override // com.movieboxpro.android.view.fragment.userinfo.UserInfoContract.Presenter
    public void getUserAgreement(String uid) {
        Intrinsics.checkNotNullParameter(uid, "uid");
        Observable<String> User_agreement = Http.getService().User_agreement(API.BASE_URL, "User_agreement", App.getUserData().uid_v2);
        LifecycleOwner mLifecycleOwner = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner, "mLifecycleOwner");
        Observable<R> compose = User_agreement.compose(RxUtils.rxTranslate2Bean(UserAgreementModel.class));
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
        RxSubscribersKt.transform(compose, mLifecycleOwner).subscribe(new Consumer() { // from class: com.movieboxpro.android.view.fragment.userinfo.UserInfoPresenter$getUserAgreement$$inlined$subscribeToBean$default$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(T it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                UserInfoPresenter.this.getView().showAgreement((UserAgreementModel) it);
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.fragment.userinfo.UserInfoPresenter$getUserAgreement$$inlined$subscribeToBean$default$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Intrinsics.checkNotNullExpressionValue(ApiException.handleException(th), "handleException(it)");
                if (th instanceof ServerException) {
                    ServerException serverException = (ServerException) th;
                }
            }
        }, new Action() { // from class: com.movieboxpro.android.view.fragment.userinfo.UserInfoPresenter$getUserAgreement$$inlined$subscribeToBean$default$3
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.fragment.userinfo.UserInfoPresenter$getUserAgreement$$inlined$subscribeToBean$default$4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
            }
        });
    }

    @Override // com.movieboxpro.android.view.fragment.userinfo.UserInfoContract.Presenter
    public void doTvLogin(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        ((ObservableSubscribeProxy) Http.getService().Scan_qrcode(API.BASE_URL, API.Common.SCANQRCODE, App.getUserData().uid_v2, id, BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<String>() { // from class: com.movieboxpro.android.view.fragment.userinfo.UserInfoPresenter$doTvLogin$1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                UserInfoPresenter.this.getView().showLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(String model) {
                Intrinsics.checkNotNullParameter(model, "model");
                UserInfoPresenter.this.getView().hideLoading();
                ToastUtils.showShort("Login Successfully", new Object[0]);
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                Intrinsics.checkNotNullParameter(e, "e");
                UserInfoPresenter.this.getView().hideLoading();
                if (e.getCode() == -101) {
                    UserInfoPresenter.this.getView().deviceFull();
                }
                ToastUtils.showShort(Intrinsics.stringPlus("Login failed:", e.getMessage()), new Object[0]);
            }
        });
    }

    @Override // com.movieboxpro.android.view.fragment.userinfo.UserInfoContract.Presenter
    public void doWebLogin(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        Observable<String> asRequest = HttpRequest.Companion.post("Scan_login_qrcode_v2").param("code", id).asRequest();
        LifecycleOwner mLifecycleOwner = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner, "mLifecycleOwner");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(asRequest, mLifecycleOwner), new UserInfoPresenter$doWebLogin$1(this), null, new UserInfoPresenter$doWebLogin$2(this), null, new UserInfoPresenter$doWebLogin$3(this), 10, null);
    }

    @Override // com.movieboxpro.android.view.fragment.userinfo.UserInfoContract.Presenter
    public void signOut() {
        ((ObservableSubscribeProxy) Http.getService().login_out(API.BASE_URL, API.USER.LOGIN_OUT, SystemUtils.getUniqueId(App.getContext()), BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<String>() { // from class: com.movieboxpro.android.view.fragment.userinfo.UserInfoPresenter$signOut$1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                UserInfoPresenter.this.getView().showLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(String model) {
                Intrinsics.checkNotNullParameter(model, "model");
                UserInfoPresenter.this.getView().signOutComplete();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                Intrinsics.checkNotNullParameter(e, "e");
                UserInfoPresenter.this.getView().hideLoading();
            }
        });
    }

    @Override // com.movieboxpro.android.view.fragment.userinfo.UserInfoContract.Presenter
    public void getTestSpeedInfo() {
        ((ObservableSubscribeProxy) Http.getService().Register_NeedCode(API.BASE_URL, API.Common.TEST_NETWORK).compose(RxUtils.rxTranslate2List(NetTestModel.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<List<? extends NetTestModel>>() { // from class: com.movieboxpro.android.view.fragment.userinfo.UserInfoPresenter$getTestSpeedInfo$1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                UserInfoPresenter.this.getView().showLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(List<? extends NetTestModel> list) {
                Intrinsics.checkNotNullParameter(list, "list");
                UserInfoPresenter.this.getView().hideLoading();
                UserInfoPresenter.this.getView().showTestSpeedDialog(list);
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                Intrinsics.checkNotNullParameter(e, "e");
                UserInfoPresenter.this.getView().hideLoading();
                ToastUtils.showShort("Get Test data failed", new Object[0]);
            }
        });
    }
}
