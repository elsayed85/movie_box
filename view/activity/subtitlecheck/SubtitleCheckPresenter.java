package com.movieboxpro.android.view.activity.subtitlecheck;

import androidx.lifecycle.LifecycleOwner;
import com.dueeeke.model.SrtPraseModel;
import com.dueeeke.model.SubTitleFeedbackModel;
import com.movieboxpro.android.BuildConfig;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseObserver;
import com.movieboxpro.android.base.mvp.BasePresenter;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.ReaderUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SrtParser;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.subtitlecheck.SubtitleCheckContract;
import com.movieboxpro.android.view.activity.subtitlecheck.SubtitleCheckPresenter;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.ResponseBody;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: SubtitleCheckPresenter.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u0011B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J \u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J \u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\u0012"}, d2 = {"Lcom/movieboxpro/android/view/activity/subtitlecheck/SubtitleCheckPresenter;", "Lcom/movieboxpro/android/base/mvp/BasePresenter;", "Lcom/movieboxpro/android/view/activity/subtitlecheck/SubtitleCheckContract$View;", "Lcom/movieboxpro/android/view/activity/subtitlecheck/SubtitleCheckContract$Presenter;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "(Landroidx/lifecycle/LifecycleOwner;)V", "loadFeedbackData", "", IjkMediaMeta.IJKM_KEY_TYPE, "", "sid", "", "url", "voteFeedback", "videoType", "feedbackType", "FeedBackPageModel", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SubtitleCheckPresenter extends BasePresenter<SubtitleCheckContract.View> implements SubtitleCheckContract.Presenter {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SubtitleCheckPresenter(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
    }

    @Override // com.movieboxpro.android.view.activity.subtitlecheck.SubtitleCheckContract.Presenter
    public void voteFeedback(int i, final int i2, String sid) {
        Intrinsics.checkNotNullParameter(sid, "sid");
        ((ObservableSubscribeProxy) Http.getService().Srt_vote(API.BASE_URL, i == 1 ? "Movie_srt_vote" : "Tv_srt_vote", App.getUserData().uid_v2, sid, i2, BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new BaseObserver<String>() { // from class: com.movieboxpro.android.view.activity.subtitlecheck.SubtitleCheckPresenter$voteFeedback$1
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                super.onSubscribe(d);
                SubtitleCheckPresenter.this.getView().showLoadingView();
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(String t) {
                Intrinsics.checkNotNullParameter(t, "t");
                super.onNext((SubtitleCheckPresenter$voteFeedback$1) t);
                SubtitleCheckPresenter.this.getView().feedbackSuccess(i2);
                ToastUtils.showShort("vote success", new Object[0]);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                SubtitleCheckPresenter.this.getView().hideLoadingView();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                SubtitleCheckPresenter.this.getView().hideLoadingView();
                ToastUtils.showShort(Intrinsics.stringPlus("vote failed", apiException == null ? null : apiException.getMessage()), new Object[0]);
            }
        });
    }

    @Override // com.movieboxpro.android.view.activity.subtitlecheck.SubtitleCheckContract.Presenter
    public void loadFeedbackData(int i, String sid, final String url) {
        Intrinsics.checkNotNullParameter(sid, "sid");
        Intrinsics.checkNotNullParameter(url, "url");
        Observable zip = Observable.zip(Http.getService().Srt_vote_data(API.BASE_URL, i == 1 ? "Movie_srt_vote_data" : "Tv_srt_vote_data", App.getUserData().uid_v2, sid, BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslate2List(SubTitleFeedbackModel.class)), Http.getService().strlist(url).onErrorReturn($$Lambda$SubtitleCheckPresenter$zfQvUDz_VFqSbx0UdL9OJ9zVw0A.INSTANCE).map(new Function() { // from class: com.movieboxpro.android.view.activity.subtitlecheck.-$$Lambda$SubtitleCheckPresenter$Y6RosbSTZiVDgC9MjyP55cOPKi8
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                ArrayList m838loadFeedbackData$lambda1;
                m838loadFeedbackData$lambda1 = SubtitleCheckPresenter.m838loadFeedbackData$lambda1(url, (ResponseBody) obj);
                return m838loadFeedbackData$lambda1;
            }
        }), $$Lambda$SubtitleCheckPresenter$EkjzsnPawo4xx5M4gxykSpu6dAs.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(zip, "zip(listObservable, load…eModel(t1, t2)\n        })");
        ((ObservableSubscribeProxy) zip.compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new BaseObserver<FeedBackPageModel>() { // from class: com.movieboxpro.android.view.activity.subtitlecheck.SubtitleCheckPresenter$loadFeedbackData$2
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                super.onSubscribe(d);
                SubtitleCheckPresenter.this.getView().showLoading();
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(SubtitleCheckPresenter.FeedBackPageModel model) {
                Intrinsics.checkNotNullParameter(model, "model");
                super.onNext((SubtitleCheckPresenter$loadFeedbackData$2) model);
                SubtitleCheckPresenter.this.getView().showFeedbackData(model.getSubtitleFeedbackList(), model.getSubtitleList());
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                SubtitleCheckPresenter.this.getView().hideLoading();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException e) {
                Intrinsics.checkNotNullParameter(e, "e");
                SubtitleCheckPresenter.this.getView().hideLoading();
                ToastUtils.showShort(Intrinsics.stringPlus("load error", e.getMessage()), new Object[0]);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadFeedbackData$lambda-1  reason: not valid java name */
    public static final ArrayList m838loadFeedbackData$lambda1(String url, ResponseBody it) {
        Intrinsics.checkNotNullParameter(url, "$url");
        Intrinsics.checkNotNullParameter(it, "it");
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        sb.append(Constant.DIR_DOWNLOAD);
        sb.append('/');
        String substring = url.substring(StringsKt.lastIndexOf$default((CharSequence) url, "/", 0, false, 6, (Object) null) + 1);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
        sb.append(substring);
        String sb2 = sb.toString();
        FileUtils.writeFileFromBytesByStream(ReaderUtils.getChapterFile(sb2), it.bytes());
        SrtParser.parseSrt(sb2, arrayList);
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadFeedbackData$lambda-2  reason: not valid java name */
    public static final FeedBackPageModel m839loadFeedbackData$lambda2(List t1, ArrayList t2) {
        Intrinsics.checkNotNullParameter(t1, "t1");
        Intrinsics.checkNotNullParameter(t2, "t2");
        return new FeedBackPageModel(t1, t2);
    }

    /* compiled from: SubtitleCheckPresenter.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0002\u0010\u0007R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u000b"}, d2 = {"Lcom/movieboxpro/android/view/activity/subtitlecheck/SubtitleCheckPresenter$FeedBackPageModel;", "", "subtitleFeedbackList", "", "Lcom/dueeeke/model/SubTitleFeedbackModel;", "subtitleList", "Lcom/dueeeke/model/SrtPraseModel;", "(Ljava/util/List;Ljava/util/List;)V", "getSubtitleFeedbackList", "()Ljava/util/List;", "getSubtitleList", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class FeedBackPageModel {
        private final List<SubTitleFeedbackModel> subtitleFeedbackList;
        private final List<SrtPraseModel> subtitleList;

        /* JADX WARN: Multi-variable type inference failed */
        public FeedBackPageModel(List<? extends SubTitleFeedbackModel> subtitleFeedbackList, List<? extends SrtPraseModel> subtitleList) {
            Intrinsics.checkNotNullParameter(subtitleFeedbackList, "subtitleFeedbackList");
            Intrinsics.checkNotNullParameter(subtitleList, "subtitleList");
            this.subtitleFeedbackList = subtitleFeedbackList;
            this.subtitleList = subtitleList;
        }

        public final List<SubTitleFeedbackModel> getSubtitleFeedbackList() {
            return this.subtitleFeedbackList;
        }

        public final List<SrtPraseModel> getSubtitleList() {
            return this.subtitleList;
        }
    }
}
