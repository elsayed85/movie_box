package com.movieboxpro.android.view.videocontroller.fragment;

import com.dueeeke.model.ResponseSubtitleRecord;
import com.movieboxpro.android.base.mvp.BaseContract;
import kotlin.Metadata;
/* compiled from: SubtitleListContract.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004"}, d2 = {"Lcom/movieboxpro/android/view/videocontroller/fragment/SubtitleListContract;", "", "Presenter", "View", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface SubtitleListContract {

    /* compiled from: SubtitleListContract.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J?\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\tH&¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lcom/movieboxpro/android/view/videocontroller/fragment/SubtitleListContract$Presenter;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BasePresenter;", "Lcom/movieboxpro/android/view/videocontroller/fragment/SubtitleListContract$View;", "getSubtitleData", "", "fid", "", "id", "boxType", "", "season", "episode", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface Presenter extends BaseContract.BasePresenter<View> {
        void getSubtitleData(String str, String str2, Integer num, Integer num2, Integer num3);
    }

    /* compiled from: SubtitleListContract.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/view/videocontroller/fragment/SubtitleListContract$View;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BaseView;", "loadFailed", "", "showSubtitleData", "subtitleData", "Lcom/dueeeke/model/ResponseSubtitleRecord;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface View extends BaseContract.BaseView {
        void loadFailed();

        void showSubtitleData(ResponseSubtitleRecord responseSubtitleRecord);
    }
}
