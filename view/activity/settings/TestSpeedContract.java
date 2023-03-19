package com.movieboxpro.android.view.activity.settings;

import com.movieboxpro.android.base.mvp.BaseContract;
import com.movieboxpro.android.model.IpInfoModel;
import com.movieboxpro.android.model.SpeedTestFeedbackModel;
import com.movieboxpro.android.model.TestSpeedModel;
import com.movieboxpro.android.model.common.NetTestModel;
import java.util.List;
import kotlin.Metadata;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
/* compiled from: TestSpeedContract.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004"}, d2 = {"Lcom/movieboxpro/android/view/activity/settings/TestSpeedContract;", "", "Presenter", "View", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface TestSpeedContract {

    /* compiled from: TestSpeedContract.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0004H&J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH&J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH&J4\u0010\r\u001a\u00020\u00042\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u000bH&¨\u0006\u0014"}, d2 = {"Lcom/movieboxpro/android/view/activity/settings/TestSpeedContract$Presenter;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BasePresenter;", "Lcom/movieboxpro/android/view/activity/settings/TestSpeedContract$View;", "getIp", "", "getServerInfo", "insertDao", "testNetRecode", "Lcom/movieboxpro/android/model/common/NetTestModel;", "sendTestInfo", "info", "", "title", "speedFeedBack", "list", "", "Lcom/movieboxpro/android/model/SpeedTestFeedbackModel;", "gpsLocation", "ipLocation", IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface Presenter extends BaseContract.BasePresenter<View> {
        void getIp();

        void getServerInfo();

        void insertDao(NetTestModel netTestModel);

        void sendTestInfo(String str, String str2);

        void speedFeedBack(List<? extends SpeedTestFeedbackModel> list, String str, String str2, String str3);
    }

    /* compiled from: TestSpeedContract.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/view/activity/settings/TestSpeedContract$View;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BaseView;", "getIpFailed", "", "showIpLocation", "info", "Lcom/movieboxpro/android/model/IpInfoModel;", "showServerInfo", "Lcom/movieboxpro/android/model/TestSpeedModel;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes.dex */
    public interface View extends BaseContract.BaseView {
        void getIpFailed();

        void showIpLocation(IpInfoModel ipInfoModel);

        void showServerInfo(TestSpeedModel testSpeedModel);
    }
}
