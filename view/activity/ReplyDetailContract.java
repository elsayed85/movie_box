package com.movieboxpro.android.view.activity;

import androidx.core.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.movieboxpro.android.base.mvp.BaseContract;
import com.movieboxpro.android.model.ReviewItem;
import java.util.ArrayList;
import kotlin.Metadata;
/* compiled from: ReplyDetailContract.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004"}, d2 = {"Lcom/movieboxpro/android/view/activity/ReplyDetailContract;", "", "Presenter", "View", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface ReplyDetailContract {

    /* compiled from: ReplyDetailContract.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\"\u0010\t\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\bH&JF\u0010\u000b\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0016\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0010j\b\u0012\u0004\u0012\u00020\u0006`\u0011H&¨\u0006\u0012"}, d2 = {"Lcom/movieboxpro/android/view/activity/ReplyDetailContract$Presenter;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BasePresenter;", "Lcom/movieboxpro/android/view/activity/ReplyDetailContract$View;", "getCommentDetail", "", "commentId", "", "boxType", "", "likeReview", NotificationCompat.CATEGORY_STATUS, "sendReview", "atId", "id", FirebaseAnalytics.Param.CONTENT, "images", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface Presenter extends BaseContract.BasePresenter<View> {
        void getCommentDetail(String str, int i);

        void likeReview(String str, int i, int i2);

        void sendReview(String str, String str2, String str3, int i, ArrayList<String> arrayList);
    }

    /* compiled from: ReplyDetailContract.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/view/activity/ReplyDetailContract$View;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BaseView;", "reviewSuccess", "", "showReview", "item", "Lcom/movieboxpro/android/model/ReviewItem;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface View extends BaseContract.BaseView {
        void reviewSuccess();

        void showReview(ReviewItem reviewItem);
    }
}
