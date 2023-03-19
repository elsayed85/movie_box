package com.movieboxpro.android.view.activity.tv;

import com.movieboxpro.android.base.mvp.BaseContract;
import com.movieboxpro.android.model.DownloadInfo;
import com.movieboxpro.android.model.tv.TvDetail;
import java.util.List;
import kotlin.Metadata;
/* compiled from: TvDownloadContract.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004"}, d2 = {"Lcom/movieboxpro/android/view/activity/tv/TvDownloadContract;", "", "Presenter", "View", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface TvDownloadContract {

    /* compiled from: TvDownloadContract.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J$\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u0006H&J(\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006H&¨\u0006\r"}, d2 = {"Lcom/movieboxpro/android/view/activity/tv/TvDownloadContract$Presenter;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BasePresenter;", "Lcom/movieboxpro/android/view/activity/tv/TvDownloadContract$View;", "getEpisodes", "", "id", "", "season", "year", "markWatched", DownloadInfo.DOWNLOAD_OVER, "", "episode", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface Presenter extends BaseContract.BasePresenter<View> {
        void getEpisodes(String str, String str2, String str3);

        void markWatched(String str, int i, String str2, String str3);
    }

    /* compiled from: TvDownloadContract.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/view/activity/tv/TvDownloadContract$View;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BaseView;", "showEpisodes", "", "list", "", "Lcom/movieboxpro/android/model/tv/TvDetail$SeasonDetail;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface View extends BaseContract.BaseView {
        void showEpisodes(List<TvDetail.SeasonDetail> list);
    }
}
