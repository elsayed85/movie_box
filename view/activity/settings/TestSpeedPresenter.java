package com.movieboxpro.android.view.activity.settings;

import androidx.lifecycle.LifecycleOwner;
import com.alibaba.fastjson.JSON;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.base.mvp.BasePresenter;
import com.movieboxpro.android.db.entity.TestNetRecode;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.IpInfoModel;
import com.movieboxpro.android.model.SpeedTestFeedbackModel;
import com.movieboxpro.android.model.TestSpeedModel;
import com.movieboxpro.android.model.common.NetTestModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.settings.TestSpeedContract;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
/* compiled from: TestSpeedPresenter.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0007J\u0018\u0010\u0011\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0017J4\u0010\u0012\u001a\u00020\b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0017\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u000fH\u0016¨\u0006\u0019"}, d2 = {"Lcom/movieboxpro/android/view/activity/settings/TestSpeedPresenter;", "Lcom/movieboxpro/android/base/mvp/BasePresenter;", "Lcom/movieboxpro/android/view/activity/settings/TestSpeedContract$View;", "Lcom/movieboxpro/android/view/activity/settings/TestSpeedContract$Presenter;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "(Landroidx/lifecycle/LifecycleOwner;)V", "getIp", "", "getServerInfo", "insertDao", "testNetRecode", "Lcom/movieboxpro/android/model/common/NetTestModel;", "sendCustomTestInfo", "info", "", "title", "sendTestInfo", "speedFeedBack", "list", "", "Lcom/movieboxpro/android/model/SpeedTestFeedbackModel;", "gpsLocation", "ipLocation", IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TestSpeedPresenter extends BasePresenter<TestSpeedContract.View> implements TestSpeedContract.Presenter {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TestSpeedPresenter(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
    }

    @Override // com.movieboxpro.android.view.activity.settings.TestSpeedContract.Presenter
    public void getServerInfo() {
        ((ObservableSubscribeProxy) Http.getService().Register_NeedCode(API.BASE_URL, API.Common.TEST_NETWORK).compose(RxUtils.rxTranslate2Bean(TestSpeedModel.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<TestSpeedModel>() { // from class: com.movieboxpro.android.view.activity.settings.TestSpeedPresenter$getServerInfo$1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                TestSpeedPresenter.this.getView().showLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(TestSpeedModel list) {
                Intrinsics.checkNotNullParameter(list, "list");
                TestSpeedPresenter.this.getView().hideLoading();
                TestSpeedPresenter.this.getView().showServerInfo(list);
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                Intrinsics.checkNotNullParameter(e, "e");
                TestSpeedPresenter.this.getView().hideLoading();
                ToastUtils.showShort("Get Test data failed", new Object[0]);
                TestSpeedPresenter.this.getView().showErrorPage();
            }
        });
    }

    @Override // com.movieboxpro.android.view.activity.settings.TestSpeedContract.Presenter
    public void getIp() {
        ((ObservableSubscribeProxy) Http.getService().simple(API.BASE_URL, "Ip_info").compose(RxUtils.rxTranslate2Bean(IpInfoModel.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<IpInfoModel>() { // from class: com.movieboxpro.android.view.activity.settings.TestSpeedPresenter$getIp$1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(IpInfoModel model) {
                Intrinsics.checkNotNullParameter(model, "model");
                TestSpeedPresenter.this.getView().showIpLocation(model);
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                Intrinsics.checkNotNullParameter(e, "e");
                CommonExtKt.logD(this, e.getMessage());
                TestSpeedPresenter.this.getView().getIpFailed();
            }
        });
    }

    @Override // com.movieboxpro.android.view.activity.settings.TestSpeedContract.Presenter
    public void insertDao(NetTestModel testNetRecode) {
        Intrinsics.checkNotNullParameter(testNetRecode, "testNetRecode");
        TestNetRecode findAll = App.getDB().testnetRecodeDao().findAll(1);
        if (findAll == null) {
            TestNetRecode testNetRecode2 = new TestNetRecode();
            testNetRecode2.setIds(1);
            testNetRecode2.setId(testNetRecode.id);
            testNetRecode2.setCountry(testNetRecode.country);
            testNetRecode2.setDescription(testNetRecode.description);
            testNetRecode2.setDomain(testNetRecode.domain);
            testNetRecode2.setDisplay_order(testNetRecode.display_order);
            testNetRecode2.setRatio(testNetRecode.ratio);
            testNetRecode2.setUrl(testNetRecode.url);
            testNetRecode2.setStartTime(testNetRecode.startTime);
            testNetRecode2.setEndTime(testNetRecode.endTime);
            testNetRecode2.setState(testNetRecode.state);
            App.getDB().testnetRecodeDao().insert(testNetRecode2);
            return;
        }
        findAll.setId(testNetRecode.id);
        findAll.setCountry(testNetRecode.country);
        findAll.setDescription(testNetRecode.description);
        findAll.setDomain(testNetRecode.domain);
        findAll.setDisplay_order(testNetRecode.display_order);
        findAll.setRatio(testNetRecode.ratio);
        findAll.setUrl(testNetRecode.url);
        findAll.setStartTime(testNetRecode.startTime);
        findAll.setEndTime(testNetRecode.endTime);
        findAll.setState(testNetRecode.state);
        App.getDB().testnetRecodeDao().update(findAll);
    }

    @Override // com.movieboxpro.android.view.activity.settings.TestSpeedContract.Presenter
    public void sendTestInfo(String info, String title) {
        Intrinsics.checkNotNullParameter(info, "info");
        Intrinsics.checkNotNullParameter(title, "title");
        Http.getService().Movie_feedback2(API.BASE_URL, API.Common.MOVIE_FEEDBACK, App.isLogin() ? App.getUserData().uid_v2 : "", -1, "", -1, "", "14", info, 0, 0, title).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).subscribe(new HttpResponseObserver<String>() { // from class: com.movieboxpro.android.view.activity.settings.TestSpeedPresenter$sendTestInfo$1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                Intrinsics.checkNotNullParameter(e, "e");
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(String model) {
                Intrinsics.checkNotNullParameter(model, "model");
            }
        });
    }

    public final void sendCustomTestInfo(String info, String title) {
        Intrinsics.checkNotNullParameter(info, "info");
        Intrinsics.checkNotNullParameter(title, "title");
        Observable<String> Movie_feedback2 = Http.getService().Movie_feedback2(API.BASE_URL, API.Common.MOVIE_FEEDBACK, App.isLogin() ? App.getUserData().uid_v2 : "", -1, "", -1, "", "", info, 0, 0, title);
        LifecycleOwner mLifecycleOwner = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner, "mLifecycleOwner");
        RxSubscribersKt.transformMsg(Movie_feedback2, mLifecycleOwner).subscribe(new HttpResponseObserver<String>() { // from class: com.movieboxpro.android.view.activity.settings.TestSpeedPresenter$sendCustomTestInfo$1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                TestSpeedPresenter.this.getView().showLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(String model) {
                Intrinsics.checkNotNullParameter(model, "model");
                TestSpeedPresenter.this.getView().hideLoadingView();
                ToastUtils.showShort("Report successfully", new Object[0]);
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                Intrinsics.checkNotNullParameter(e, "e");
                TestSpeedPresenter.this.getView().hideLoadingView();
            }
        });
    }

    @Override // com.movieboxpro.android.view.activity.settings.TestSpeedContract.Presenter
    public void speedFeedBack(List<? extends SpeedTestFeedbackModel> list, String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(list, "list");
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = hashMap;
        hashMap2.put("module", "Test_network_feedback");
        String str4 = App.getUserData().uid_v2;
        if (str4 == null) {
            str4 = "";
        }
        hashMap2.put("uid", str4);
        if (str3 == null) {
            str3 = "";
        }
        hashMap2.put(IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, str3);
        if (str == null) {
            str = "";
        }
        hashMap2.put("gps_location", str);
        if (str2 == null) {
            str2 = "";
        }
        hashMap2.put("ip_location", str2);
        hashMap2.put("speed", list);
        Http.getService().sortFavorite(API.BASE_URL, RequestBody.create(MediaType.parse("text/plain; charset=utf-8"), JSON.toJSONString(hashMap))).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).subscribe(new HttpResponseObserver<String>() { // from class: com.movieboxpro.android.view.activity.settings.TestSpeedPresenter$speedFeedBack$1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                Intrinsics.checkNotNullParameter(e, "e");
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(String model) {
                Intrinsics.checkNotNullParameter(model, "model");
                CommonExtKt.logD(TestSpeedPresenter.this, "speedFeedBack success");
            }
        });
    }
}
