package com.movieboxpro.android.view.videocontroller.fragment;

import com.dueeeke.model.SrtPraseModel;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.movieboxpro.android.base.mvp.BaseContract;
import java.util.List;
import kotlin.Metadata;
/* compiled from: TranslateSubtitleContract.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004"}, d2 = {"Lcom/movieboxpro/android/view/videocontroller/fragment/TranslateSubtitleContract;", "", "Presenter", "View", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface TranslateSubtitleContract {

    /* compiled from: TranslateSubtitleContract.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H&J \u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006H&J(\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH&¨\u0006\r"}, d2 = {"Lcom/movieboxpro/android/view/videocontroller/fragment/TranslateSubtitleContract$Presenter;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BasePresenter;", "Lcom/movieboxpro/android/view/videocontroller/fragment/TranslateSubtitleContract$View;", "parseContent", "", FirebaseAnalytics.Param.CONTENT, "", "translate", "fromLanguage", "toLanguage", "sid", "boxType", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface Presenter extends BaseContract.BasePresenter<View> {
        void parseContent(String str);

        void translate(String str, String str2, String str3);

        void translate(String str, String str2, String str3, int i);
    }

    /* compiled from: TranslateSubtitleContract.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0016\u0010\u0004\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H&J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH&J\u0016\u0010\f\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H&¨\u0006\r"}, d2 = {"Lcom/movieboxpro/android/view/videocontroller/fragment/TranslateSubtitleContract$View;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BaseView;", "dismissProgress", "", "showContent", "list", "", "Lcom/dueeeke/model/SrtPraseModel;", "showProgress", "progress", "", "max", "showSubtitles", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes.dex */
    public interface View extends BaseContract.BaseView {
        void dismissProgress();

        void showContent(List<SrtPraseModel> list);

        void showProgress(int i, int i2);

        void showSubtitles(List<SrtPraseModel> list);
    }
}
