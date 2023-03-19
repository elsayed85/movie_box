package com.movieboxpro.android.view.activity.downloadsubtitle;

import androidx.lifecycle.LifecycleOwner;
import com.dueeeke.model.ResponseSubtitleRecord;
import com.movieboxpro.android.BuildConfig;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseObserver;
import com.movieboxpro.android.base.mvp.BasePresenter;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.ReaderUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.downloadsubtitle.DownloadSubtitleContract;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.ResponseBody;
/* compiled from: DownloadSubtitlePresenter.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J&\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\tH\u0002J,\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\u00102\u0006\u0010\u0012\u001a\u00020\tH\u0016J(\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017H\u0016¨\u0006\u0019"}, d2 = {"Lcom/movieboxpro/android/view/activity/downloadsubtitle/DownloadSubtitlePresenter;", "Lcom/movieboxpro/android/base/mvp/BasePresenter;", "Lcom/movieboxpro/android/view/activity/downloadsubtitle/DownloadSubtitleContract$View;", "Lcom/movieboxpro/android/view/activity/downloadsubtitle/DownloadSubtitleContract$Presenter;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "(Landroidx/lifecycle/LifecycleOwner;)V", "downloadSubtitleObservable", "Lio/reactivex/Observable;", "", "sid", "url", "dir", "downloadSubtitles", "", "urls", "", "sidList", "downloadDir", "loadSubtitle", "id", "fid", "season", "", "episode", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DownloadSubtitlePresenter extends BasePresenter<DownloadSubtitleContract.View> implements DownloadSubtitleContract.Presenter {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DownloadSubtitlePresenter(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
    }

    @Override // com.movieboxpro.android.view.activity.downloadsubtitle.DownloadSubtitleContract.Presenter
    public void downloadSubtitles(List<String> urls, List<String> sidList, final String downloadDir) {
        Intrinsics.checkNotNullParameter(urls, "urls");
        Intrinsics.checkNotNullParameter(sidList, "sidList");
        Intrinsics.checkNotNullParameter(downloadDir, "downloadDir");
        ((ObservableSubscribeProxy) Observable.zip(Observable.fromIterable(urls), Observable.fromIterable(sidList), $$Lambda$DownloadSubtitlePresenter$Fy6de9rxlJkKnyhBfCyin8Qq_M.INSTANCE).flatMap(new Function() { // from class: com.movieboxpro.android.view.activity.downloadsubtitle.-$$Lambda$DownloadSubtitlePresenter$opCsaUIPlEzaD5baN2hudyl0gmY
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                ObservableSource m352downloadSubtitles$lambda1;
                m352downloadSubtitles$lambda1 = DownloadSubtitlePresenter.m352downloadSubtitles$lambda1(DownloadSubtitlePresenter.this, downloadDir, (Pair) obj);
                return m352downloadSubtitles$lambda1;
            }
        }).toList().toObservable().compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new BaseObserver<List<? extends String>>() { // from class: com.movieboxpro.android.view.activity.downloadsubtitle.DownloadSubtitlePresenter$downloadSubtitles$3
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                super.onSubscribe(d);
                DownloadSubtitlePresenter.this.getView().showLoadingView();
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(List<String> t) {
                Intrinsics.checkNotNullParameter(t, "t");
                super.onNext((DownloadSubtitlePresenter$downloadSubtitles$3) t);
                DownloadSubtitlePresenter.this.getView().downloadSubtitlesComplete(t);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                DownloadSubtitlePresenter.this.getView().hideLoadingView();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                DownloadSubtitlePresenter.this.getView().hideLoadingView();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: downloadSubtitles$lambda-0  reason: not valid java name */
    public static final Pair m351downloadSubtitles$lambda0(String url, String sid) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(sid, "sid");
        return new Pair(url, sid);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: downloadSubtitles$lambda-1  reason: not valid java name */
    public static final ObservableSource m352downloadSubtitles$lambda1(DownloadSubtitlePresenter this$0, String downloadDir, Pair it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(downloadDir, "$downloadDir");
        Intrinsics.checkNotNullParameter(it, "it");
        return this$0.downloadSubtitleObservable((String) it.getSecond(), (String) it.getFirst(), downloadDir);
    }

    private final Observable<String> downloadSubtitleObservable(final String str, final String str2, final String str3) {
        Observable map = Http.getService().strlist(str2).map(new Function() { // from class: com.movieboxpro.android.view.activity.downloadsubtitle.-$$Lambda$DownloadSubtitlePresenter$eEKgwDsURwKT_M6wEc7mCnigXkg
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                String m350downloadSubtitleObservable$lambda2;
                m350downloadSubtitleObservable$lambda2 = DownloadSubtitlePresenter.m350downloadSubtitleObservable$lambda2(str3, str, str2, (ResponseBody) obj);
                return m350downloadSubtitleObservable$lambda2;
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "getService().strlist(url…    sid\n                }");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: downloadSubtitleObservable$lambda-2  reason: not valid java name */
    public static final String m350downloadSubtitleObservable$lambda2(String dir, String sid, String url, ResponseBody it) {
        Intrinsics.checkNotNullParameter(dir, "$dir");
        Intrinsics.checkNotNullParameter(sid, "$sid");
        Intrinsics.checkNotNullParameter(url, "$url");
        Intrinsics.checkNotNullParameter(it, "it");
        StringBuilder sb = new StringBuilder();
        sb.append(dir);
        sb.append('/');
        sb.append(sid);
        sb.append('_');
        String substring = url.substring(StringsKt.lastIndexOf$default((CharSequence) url, "/", 0, false, 6, (Object) null) + 1);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
        sb.append(substring);
        FileUtils.writeFileFromBytesByStream(ReaderUtils.getChapterFile(sb.toString()), it.bytes());
        return sid;
    }

    @Override // com.movieboxpro.android.view.activity.downloadsubtitle.DownloadSubtitleContract.Presenter
    public void loadSubtitle(String id, String fid, int i, int i2) {
        Observable<String> TV_srt_list_v2;
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(fid, "fid");
        if (i == 0 && i2 == 0) {
            TV_srt_list_v2 = Http.getService().Movie_srt_list_v2(API.BASE_URL, API.Movie.MOVIE_SRTLIST_V2, App.isLogin() ? App.getUserData().uid_v2 : "", id, fid, Locale.getDefault().getLanguage(), BuildConfig.VERSION_NAME);
        } else {
            TV_srt_list_v2 = Http.getService().TV_srt_list_v2(API.BASE_URL, API.Tv.TV_SRTLIST_V2, App.getUserData().uid_v2, id, fid, String.valueOf(i), String.valueOf(i2), Locale.getDefault().getLanguage(), BuildConfig.VERSION_NAME);
        }
        ((ObservableSubscribeProxy) TV_srt_list_v2.compose(RxUtils.rxTranslate2Bean(ResponseSubtitleRecord.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new BaseObserver<ResponseSubtitleRecord>() { // from class: com.movieboxpro.android.view.activity.downloadsubtitle.DownloadSubtitlePresenter$loadSubtitle$1
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                super.onSubscribe(d);
                DownloadSubtitlePresenter.this.getView().showLoading();
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(ResponseSubtitleRecord t) {
                Intrinsics.checkNotNullParameter(t, "t");
                super.onNext((DownloadSubtitlePresenter$loadSubtitle$1) t);
                DownloadSubtitlePresenter.this.getView().showSubtitle(t);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                DownloadSubtitlePresenter.this.getView().hideLoading();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                DownloadSubtitlePresenter.this.getView().hideLoading();
                ToastUtils.showShort(apiException == null ? null : apiException.getMessage(), new Object[0]);
            }
        });
    }
}
