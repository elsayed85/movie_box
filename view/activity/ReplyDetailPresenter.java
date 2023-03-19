package com.movieboxpro.android.view.activity;

import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LifecycleOwner;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.movieboxpro.android.base.mvp.BasePresenter;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.model.ReviewResponse;
import com.movieboxpro.android.utils.ApiUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.view.activity.ReplyDetailContract;
import io.reactivex.Observable;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ReplyDetailPresenter.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001a\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\"\u0010\r\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\fH\u0016JF\u0010\u000f\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\n2\b\u0010\u0011\u001a\u0004\u0018\u00010\n2\b\u0010\u0012\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0016\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\n0\u0014j\b\u0012\u0004\u0012\u00020\n`\u0015H\u0016¨\u0006\u0016"}, d2 = {"Lcom/movieboxpro/android/view/activity/ReplyDetailPresenter;", "Lcom/movieboxpro/android/base/mvp/BasePresenter;", "Lcom/movieboxpro/android/view/activity/ReplyDetailContract$View;", "Lcom/movieboxpro/android/view/activity/ReplyDetailContract$Presenter;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "(Landroidx/lifecycle/LifecycleOwner;)V", "getCommentDetail", "", "commentId", "", "boxType", "", "likeReview", NotificationCompat.CATEGORY_STATUS, "sendReview", "atId", "id", FirebaseAnalytics.Param.CONTENT, "images", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ReplyDetailPresenter extends BasePresenter<ReplyDetailContract.View> implements ReplyDetailContract.Presenter {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReplyDetailPresenter(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
    }

    @Override // com.movieboxpro.android.view.activity.ReplyDetailContract.Presenter
    public void sendReview(String str, String str2, String str3, int i, ArrayList<String> images) {
        Intrinsics.checkNotNullParameter(images, "images");
        HttpRequest.Companion companion = HttpRequest.Companion;
        LifecycleOwner mLifecycleOwner = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner, "mLifecycleOwner");
        Observable<String> asRequest = companion.post(mLifecycleOwner, ApiUtils.INSTANCE.sendComment(i)).param("mid", str2).param("actor_id", str2).param("pid", str2).param("box_type", Integer.valueOf(i)).param("at", str).param("img_list", images).param("comment", str3).asRequest();
        LifecycleOwner mLifecycleOwner2 = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner2, "mLifecycleOwner");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(asRequest, mLifecycleOwner2), new ReplyDetailPresenter$sendReview$1(this), null, new ReplyDetailPresenter$sendReview$2(this), null, new ReplyDetailPresenter$sendReview$3(this), 10, null);
    }

    @Override // com.movieboxpro.android.view.activity.ReplyDetailContract.Presenter
    public void likeReview(String str, int i, int i2) {
        HttpRequest.Companion companion = HttpRequest.Companion;
        LifecycleOwner mLifecycleOwner = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner, "mLifecycleOwner");
        Observable<String> asRequest = companion.post(mLifecycleOwner, ApiUtils.INSTANCE.likeReview(i2)).param("comment_id", str).param("support", Integer.valueOf(i)).asRequest();
        LifecycleOwner mLifecycleOwner2 = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner2, "mLifecycleOwner");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(asRequest, mLifecycleOwner2), ReplyDetailPresenter$likeReview$1.INSTANCE, null, null, null, ReplyDetailPresenter$likeReview$2.INSTANCE, 14, null);
    }

    @Override // com.movieboxpro.android.view.activity.ReplyDetailContract.Presenter
    public void getCommentDetail(String str, int i) {
        HttpRequest.Companion companion = HttpRequest.Companion;
        LifecycleOwner mLifecycleOwner = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner, "mLifecycleOwner");
        Observable<String> asRequest = companion.post(mLifecycleOwner, ApiUtils.INSTANCE.getCommentDetail(i)).param("comment_ids", CollectionsKt.arrayListOf(str)).asRequest();
        LifecycleOwner mLifecycleOwner2 = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner2, "mLifecycleOwner");
        Observable<R> compose = asRequest.compose(RxUtils.rxTranslate2Bean(ReviewResponse.class));
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(compose, mLifecycleOwner2), new ReplyDetailPresenter$getCommentDetail$1(this), null, new ReplyDetailPresenter$getCommentDetail$2(this), null, new ReplyDetailPresenter$getCommentDetail$3(this), 10, null);
    }
}
