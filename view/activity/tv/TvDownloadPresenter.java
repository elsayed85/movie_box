package com.movieboxpro.android.view.activity.tv;

import androidx.lifecycle.LifecycleOwner;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.base.mvp.BasePresenter;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.DownloadInfo;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.tv.TvDownloadContract;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.disposables.Disposable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: TvDownloadPresenter.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J$\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\nH\u0016J(\u0010\r\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\nH\u0016¨\u0006\u0011"}, d2 = {"Lcom/movieboxpro/android/view/activity/tv/TvDownloadPresenter;", "Lcom/movieboxpro/android/base/mvp/BasePresenter;", "Lcom/movieboxpro/android/view/activity/tv/TvDownloadContract$View;", "Lcom/movieboxpro/android/view/activity/tv/TvDownloadContract$Presenter;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "(Landroidx/lifecycle/LifecycleOwner;)V", "getEpisodes", "", "id", "", "season", "year", "markWatched", DownloadInfo.DOWNLOAD_OVER, "", "episode", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TvDownloadPresenter extends BasePresenter<TvDownloadContract.View> implements TvDownloadContract.Presenter {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TvDownloadPresenter(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
    }

    @Override // com.movieboxpro.android.view.activity.tv.TvDownloadContract.Presenter
    public void markWatched(String id, int i, String season, String episode) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(season, "season");
        Intrinsics.checkNotNullParameter(episode, "episode");
        ((ObservableSubscribeProxy) Http.getService().AddWatchedFlag(API.BASE_URL, API.Tv.TV_over_v2, App.getUserData().uid_v2, i, id, season, episode).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<String>() { // from class: com.movieboxpro.android.view.activity.tv.TvDownloadPresenter$markWatched$1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                TvDownloadPresenter.this.getView().showLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(String model) {
                Intrinsics.checkNotNullParameter(model, "model");
                TvDownloadPresenter.this.getView().hideLoadingView();
                ToastUtils.showShort("Mark successfully", new Object[0]);
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                Intrinsics.checkNotNullParameter(e, "e");
                TvDownloadPresenter.this.getView().hideLoadingView();
                ToastUtils.showShort(Intrinsics.stringPlus("Mark failed:", e.getMessage()), new Object[0]);
            }
        });
    }

    @Override // com.movieboxpro.android.view.activity.tv.TvDownloadContract.Presenter
    public void getEpisodes(String id, String str, String str2) {
        Intrinsics.checkNotNullParameter(id, "id");
        ((ObservableSubscribeProxy) Http.getService().TV_episode(API.BASE_URL, API.Tv.TV_EPISODE, id, Intrinsics.stringPlus(str, ""), str2, App.getUserData().uid_v2, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE).compose(RxUtils.rxTranslate2List(TvDetail.SeasonDetail.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<List<TvDetail.SeasonDetail>>() { // from class: com.movieboxpro.android.view.activity.tv.TvDownloadPresenter$getEpisodes$1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                TvDownloadPresenter.this.getView().showLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(List<TvDetail.SeasonDetail> model) {
                Intrinsics.checkNotNullParameter(model, "model");
                TvDownloadPresenter.this.getView().showEpisodes(model);
                TvDownloadPresenter.this.getView().hideLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                Intrinsics.checkNotNullParameter(e, "e");
                TvDownloadPresenter.this.getView().hideLoadingView();
            }
        });
    }
}
