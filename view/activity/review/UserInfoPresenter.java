package com.movieboxpro.android.view.activity.review;

import androidx.lifecycle.LifecycleOwner;
import com.alibaba.fastjson.JSON;
import com.google.android.gms.common.Scopes;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.base.mvp.BasePresenter;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.UserProfileResponse;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.review.UserInfoContract;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: UserInfoPresenter.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/UserInfoPresenter;", "Lcom/movieboxpro/android/base/mvp/BasePresenter;", "Lcom/movieboxpro/android/view/activity/review/UserInfoContract$View;", "Lcom/movieboxpro/android/view/activity/review/UserInfoContract$Presenter;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "(Landroidx/lifecycle/LifecycleOwner;)V", "getUserInfo", "", "uid", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UserInfoPresenter extends BasePresenter<UserInfoContract.View> implements UserInfoContract.Presenter {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserInfoPresenter(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
    }

    @Override // com.movieboxpro.android.view.activity.review.UserInfoContract.Presenter
    public void getUserInfo(String uid) {
        Intrinsics.checkNotNullParameter(uid, "uid");
        UserModel.BBsInfo bBsInfo = App.getBBsInfo();
        if (bBsInfo != null) {
            ((ObservableSubscribeProxy) Http.getService().getProfile(API.BBS_URL, Scopes.PROFILE, bBsInfo.getAuth(), bBsInfo.getAuthkey(), uid, "", bBsInfo.getBbs_uid()).map($$Lambda$UserInfoPresenter$REy4I5hvcphAliQTO_mUzEuDlY.INSTANCE).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<UserProfileResponse>() { // from class: com.movieboxpro.android.view.activity.review.UserInfoPresenter$getUserInfo$2
                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onStart(Disposable d) {
                    Intrinsics.checkNotNullParameter(d, "d");
                    UserInfoPresenter.this.getView().showLoading();
                }

                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onSuccess(UserProfileResponse model) {
                    Intrinsics.checkNotNullParameter(model, "model");
                    UserInfoPresenter.this.getView().showUserInfo(model);
                    UserInfoPresenter.this.getView().hideLoading();
                }

                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onError(ApiException e) {
                    Intrinsics.checkNotNullParameter(e, "e");
                    UserInfoPresenter.this.getView().hideLoading();
                    ToastUtils.showShort(Intrinsics.stringPlus("Load failed:", e.getMessage()), new Object[0]);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getUserInfo$lambda-0  reason: not valid java name */
    public static final UserProfileResponse m768getUserInfo$lambda0(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return (UserProfileResponse) JSON.parseObject(it, UserProfileResponse.class);
    }
}
