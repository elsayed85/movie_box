package com.movieboxpro.android.view.dialog;

import androidx.core.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.movieboxpro.android.base.mvp.BaseContract;
import com.movieboxpro.android.model.ReportReason;
import com.movieboxpro.android.model.ReviewItem;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
/* compiled from: ReviewDialogContract.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ReviewDialogContract;", "", "Presenter", "View", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface ReviewDialogContract {

    /* compiled from: ReviewDialogContract.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J2\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0006H&J\u0012\u0010\f\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u0006H&J\u001a\u0010\u000e\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH&J2\u0010\u000f\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0006H&J\"\u0010\u0010\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\bH&JF\u0010\u0012\u001a\u00020\u00042\b\u0010\u0013\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0016\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0016j\b\u0012\u0004\u0012\u00020\u0006`\u0017H&¨\u0006\u0018"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ReviewDialogContract$Presenter;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BasePresenter;", "Lcom/movieboxpro/android/view/dialog/ReviewDialogContract$View;", "getMoreReviews", "", "id", "", "boxType", "", "currPage", "pageSize", "sort", "getReportReason", "commentId", "getReviewNum", "getReviews", "likeReview", NotificationCompat.CATEGORY_STATUS, "sendReview", "atId", FirebaseAnalytics.Param.CONTENT, "images", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface Presenter extends BaseContract.BasePresenter<View> {
        void getMoreReviews(String str, int i, int i2, int i3, String str2);

        void getReportReason(String str);

        void getReviewNum(String str, int i);

        void getReviews(String str, int i, int i2, int i3, String str2);

        void likeReview(String str, int i, int i2);

        void sendReview(String str, String str2, String str3, int i, ArrayList<String> arrayList);
    }

    /* compiled from: ReviewDialogContract.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\u0016\u0010\u0005\u001a\u00020\u00032\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H&J \u0010\t\u001a\u00020\u00032\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\n0\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\fH&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\fH&J\u0016\u0010\u000f\u001a\u00020\u00032\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H&¨\u0006\u0010"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ReviewDialogContract$View;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BaseView;", "loadMoreFailed", "", "reviewSuccess", "showMoreReviews", "list", "", "Lcom/movieboxpro/android/model/ReviewItem;", "showReportReason", "Lcom/movieboxpro/android/model/ReportReason;", "commentId", "", "showReviewNum", "num", "showReviews", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface View extends BaseContract.BaseView {
        void loadMoreFailed();

        void reviewSuccess();

        void showMoreReviews(List<ReviewItem> list);

        void showReportReason(List<ReportReason> list, String str);

        void showReviewNum(String str);

        void showReviews(List<ReviewItem> list);
    }
}
