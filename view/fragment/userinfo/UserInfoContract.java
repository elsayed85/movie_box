package com.movieboxpro.android.view.fragment.userinfo;

import com.movieboxpro.android.base.mvp.BaseContract;
import com.movieboxpro.android.model.UserAgreementModel;
import com.movieboxpro.android.model.common.NetTestModel;
import java.util.List;
import kotlin.Metadata;
/* compiled from: UserInfoContract.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004"}, d2 = {"Lcom/movieboxpro/android/view/fragment/userinfo/UserInfoContract;", "", "Presenter", "View", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface UserInfoContract {

    /* compiled from: UserInfoContract.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\b\u001a\u00020\u0004H&J\b\u0010\t\u001a\u00020\u0004H&J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0006H&J\b\u0010\f\u001a\u00020\u0004H&¨\u0006\r"}, d2 = {"Lcom/movieboxpro/android/view/fragment/userinfo/UserInfoContract$Presenter;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BasePresenter;", "Lcom/movieboxpro/android/view/fragment/userinfo/UserInfoContract$View;", "doTvLogin", "", "id", "", "doWebLogin", "getNoticeCount", "getTestSpeedInfo", "getUserAgreement", "uid", "signOut", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface Presenter extends BaseContract.BasePresenter<View> {
        void doTvLogin(String str);

        void doWebLogin(String str);

        void getNoticeCount();

        void getTestSpeedInfo();

        void getUserAgreement(String str);

        void signOut();
    }

    /* compiled from: UserInfoContract.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&J\u0016\u0010\n\u001a\u00020\u00032\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH&J\b\u0010\u000e\u001a\u00020\u0003H&¨\u0006\u000f"}, d2 = {"Lcom/movieboxpro/android/view/fragment/userinfo/UserInfoContract$View;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BaseView;", "deviceFull", "", "setNoticeCount", "count", "", "showAgreement", "model", "Lcom/movieboxpro/android/model/UserAgreementModel;", "showTestSpeedDialog", "list", "", "Lcom/movieboxpro/android/model/common/NetTestModel;", "signOutComplete", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes.dex */
    public interface View extends BaseContract.BaseView {
        void deviceFull();

        void setNoticeCount(int i);

        void showAgreement(UserAgreementModel userAgreementModel);

        void showTestSpeedDialog(List<? extends NetTestModel> list);

        void signOutComplete();
    }
}
