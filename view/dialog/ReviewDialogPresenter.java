package com.movieboxpro.android.view.dialog;

import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LifecycleOwner;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.movieboxpro.android.base.mvp.BasePresenter;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.model.ListResponse;
import com.movieboxpro.android.model.ReportReason;
import com.movieboxpro.android.model.ReviewResponse;
import com.movieboxpro.android.utils.ApiUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.view.dialog.ReviewDialogContract;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ReviewDialogPresenter.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J2\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\nH\u0016J\u0012\u0010\u0010\u001a\u00020\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\nH\u0016J\u001a\u0010\u0012\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J2\u0010\u0013\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\nH\u0016J\"\u0010\u0014\u001a\u00020\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0015\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\fH\u0016JF\u0010\u0016\u001a\u00020\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u0018\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0016\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\n0\u001aj\b\u0012\u0004\u0012\u00020\n`\u001bH\u0016¨\u0006\u001c"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ReviewDialogPresenter;", "Lcom/movieboxpro/android/base/mvp/BasePresenter;", "Lcom/movieboxpro/android/view/dialog/ReviewDialogContract$View;", "Lcom/movieboxpro/android/view/dialog/ReviewDialogContract$Presenter;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "(Landroidx/lifecycle/LifecycleOwner;)V", "getMoreReviews", "", "id", "", "boxType", "", "currPage", "pageSize", "sort", "getReportReason", "commentId", "getReviewNum", "getReviews", "likeReview", NotificationCompat.CATEGORY_STATUS, "sendReview", "atId", FirebaseAnalytics.Param.CONTENT, "images", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ReviewDialogPresenter extends BasePresenter<ReviewDialogContract.View> implements ReviewDialogContract.Presenter {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReviewDialogPresenter(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
    }

    @Override // com.movieboxpro.android.view.dialog.ReviewDialogContract.Presenter
    public void sendReview(String str, String str2, String str3, int i, ArrayList<String> images) {
        Intrinsics.checkNotNullParameter(images, "images");
        HttpRequest.Companion companion = HttpRequest.Companion;
        LifecycleOwner mLifecycleOwner = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner, "mLifecycleOwner");
        Observable<String> asRequest = companion.post(mLifecycleOwner, ApiUtils.INSTANCE.sendComment(i)).param("mid", str2).param("pid", str2).param("actor_id", str2).param("box_type", Integer.valueOf(i)).param("at", str).param("img_list", images).param("comment", str3).asRequest();
        LifecycleOwner mLifecycleOwner2 = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner2, "mLifecycleOwner");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(asRequest, mLifecycleOwner2), new ReviewDialogPresenter$sendReview$1(this), null, new ReviewDialogPresenter$sendReview$2(this), null, new ReviewDialogPresenter$sendReview$3(this, str), 10, null);
    }

    @Override // com.movieboxpro.android.view.dialog.ReviewDialogContract.Presenter
    public void getReviewNum(String str, int i) {
        Observable<String> asRequest = HttpRequest.Companion.post(ApiUtils.INSTANCE.reviewNum(i)).param("box_type", Integer.valueOf(i)).param("mid", str).param("pid", str).param("actor_id", str).asRequest();
        LifecycleOwner mLifecycleOwner = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner, "mLifecycleOwner");
        Observable<R> compose = asRequest.compose(RxUtils.rxTranslate2Bean(HashMap.class));
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(compose, mLifecycleOwner), null, null, null, null, new ReviewDialogPresenter$getReviewNum$1(this), 15, null);
    }

    @Override // com.movieboxpro.android.view.dialog.ReviewDialogContract.Presenter
    public void likeReview(String str, int i, int i2) {
        HttpRequest.Companion companion = HttpRequest.Companion;
        LifecycleOwner mLifecycleOwner = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner, "mLifecycleOwner");
        Observable<String> asRequest = companion.post(mLifecycleOwner, ApiUtils.INSTANCE.likeReview(i2)).param("comment_id", str).param("support", Integer.valueOf(i)).asRequest();
        LifecycleOwner mLifecycleOwner2 = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner2, "mLifecycleOwner");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(asRequest, mLifecycleOwner2), new ReviewDialogPresenter$likeReview$1(this), null, null, null, new ReviewDialogPresenter$likeReview$2(this), 14, null);
    }

    @Override // com.movieboxpro.android.view.dialog.ReviewDialogContract.Presenter
    public void getReportReason(String str) {
        Observable<R> compose = HttpRequest.Companion.post("Comment_reason_list").asRequest().compose(RxUtils.rxTranslate2Bean(String.class));
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
        Object as = compose.map($$Lambda$ReviewDialogPresenter$vE_JSMNNPzxVwqVGMgC8q0tfOEw.INSTANCE).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner));
        Intrinsics.checkNotNullExpressionValue(as, "HttpRequest.post(\"Commen…leOwner(mLifecycleOwner))");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, new ReviewDialogPresenter$getReportReason$2(this), null, new ReviewDialogPresenter$getReportReason$3(this), null, new ReviewDialogPresenter$getReportReason$4(this, str), 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getReportReason$lambda-0  reason: not valid java name */
    public static final ArrayList m1101getReportReason$lambda0(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Object parseObject = JSON.parseObject(it, RxUtils.buildType(ListResponse.class, ReportReason.class), new Feature[0]);
        Intrinsics.checkNotNullExpressionValue(parseObject, "parseObject(\n           …      )\n                )");
        return ((ListResponse) parseObject).getList();
    }

    @Override // com.movieboxpro.android.view.dialog.ReviewDialogContract.Presenter
    public void getReviews(String str, int i, int i2, int i3, String sort) {
        Intrinsics.checkNotNullParameter(sort, "sort");
        HttpRequest.Companion companion = HttpRequest.Companion;
        LifecycleOwner mLifecycleOwner = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner, "mLifecycleOwner");
        Observable<String> asRequest = companion.post(mLifecycleOwner, ApiUtils.INSTANCE.getReviews(i)).param("box_type", Integer.valueOf(i)).param("mid", str).param("pid", str).param("actor_id", str).param("page", Integer.valueOf(i2)).param("pagelimit", Integer.valueOf(i3)).param("sort", sort).asRequest();
        LifecycleOwner mLifecycleOwner2 = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner2, "mLifecycleOwner");
        Observable<R> compose = asRequest.compose(RxUtils.rxTranslate2Bean(ReviewResponse.class));
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(compose, mLifecycleOwner2), new ReviewDialogPresenter$getReviews$1(this), null, new ReviewDialogPresenter$getReviews$2(this), null, new ReviewDialogPresenter$getReviews$3(this), 10, null);
    }

    @Override // com.movieboxpro.android.view.dialog.ReviewDialogContract.Presenter
    public void getMoreReviews(String str, int i, int i2, int i3, String sort) {
        Intrinsics.checkNotNullParameter(sort, "sort");
        HttpRequest.Companion companion = HttpRequest.Companion;
        LifecycleOwner mLifecycleOwner = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner, "mLifecycleOwner");
        Observable<String> asRequest = companion.post(mLifecycleOwner, ApiUtils.INSTANCE.getReviews(i)).param("box_type", Integer.valueOf(i)).param("mid", str).param("pid", str).param("actor_id", str).param("page", Integer.valueOf(i2)).param("pagelimit", Integer.valueOf(i3)).param("sort", sort).asRequest();
        LifecycleOwner mLifecycleOwner2 = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner2, "mLifecycleOwner");
        Observable<R> compose = asRequest.compose(RxUtils.rxTranslate2Bean(ReviewResponse.class));
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(compose, mLifecycleOwner2), new ReviewDialogPresenter$getMoreReviews$1(this), null, ReviewDialogPresenter$getMoreReviews$2.INSTANCE, null, new ReviewDialogPresenter$getMoreReviews$3(this), 10, null);
    }
}
