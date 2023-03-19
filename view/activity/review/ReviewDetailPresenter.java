package com.movieboxpro.android.view.activity.review;

import androidx.lifecycle.LifecycleOwner;
import com.alibaba.fastjson.JSON;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.movieboxpro.android.base.mvp.BasePresenter;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpUtils;
import com.movieboxpro.android.http.ServerException;
import com.movieboxpro.android.model.BBsResponseModel;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.ShellUtil;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.review.ReviewDetailContract;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
/* compiled from: ReviewDetailPresenter.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0010\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J¬\u0001\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\n2\b\u0010\u0011\u001a\u0004\u0018\u00010\n2\b\u0010\u0012\u001a\u0004\u0018\u00010\n2\b\u0010\u0013\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0014\u001a\u00020\n2\b\u0010\u0015\u001a\u0004\u0018\u00010\n2\b\u0010\u0016\u001a\u0004\u0018\u00010\n2\b\u0010\u0017\u001a\u0004\u0018\u00010\n2\b\u0010\u0018\u001a\u0004\u0018\u00010\n2\b\u0010\u0019\u001a\u0004\u0018\u00010\n2\b\u0010\u001a\u001a\u0004\u0018\u00010\n2\u0006\u0010\u001b\u001a\u00020\u000eH\u0016J:\u0010\u001c\u001a\u00020\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\n2\b\u0010\u0011\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\b\u0010\u0016\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010\u001d\u001a\u00020\nH\u0002¨\u0006\u001e"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/ReviewDetailPresenter;", "Lcom/movieboxpro/android/base/mvp/BasePresenter;", "Lcom/movieboxpro/android/view/activity/review/ReviewDetailContract$View;", "Lcom/movieboxpro/android/view/activity/review/ReviewDetailContract$Presenter;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "(Landroidx/lifecycle/LifecycleOwner;)V", "addReply", "", "uid", "", "username", "tid", "htmlOn", "", "auth", "authkey", "formash", FirebaseAnalytics.Param.CONTENT, "userFile", "repquote", "authorId", "pid", "authorUsername", "authorAvatar", "authorTime", "authorContent", "reply", "doLike", "getBBSApiAPPID", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ReviewDetailPresenter extends BasePresenter<ReviewDetailContract.View> implements ReviewDetailContract.Presenter {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReviewDetailPresenter(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
    }

    @Override // com.movieboxpro.android.view.activity.review.ReviewDetailContract.Presenter
    public void doLike(String str, String str2, String str3, String str4, String str5) {
        Observable<R> map = Http.getService().doLike(API.BBS_URL, "postreview", str4, str5, "support", str, str2, str3).map($$Lambda$ReviewDetailPresenter$hQSI1x64XOaWTHMY9GNPOYdn10.INSTANCE);
        LifecycleOwner mLifecycleOwner = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(map, "map {\n                  …s.java)\n                }");
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner, "mLifecycleOwner");
        RxSubscribersKt.transform(map, mLifecycleOwner).subscribe(new Consumer() { // from class: com.movieboxpro.android.view.activity.review.ReviewDetailPresenter$doLike$$inlined$transformSubscribe$default$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(T it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                BBsResponseModel bBsResponseModel = (BBsResponseModel) it;
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.ReviewDetailPresenter$doLike$$inlined$transformSubscribe$default$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Intrinsics.checkNotNullExpressionValue(ApiException.handleException(th), "handleException(it)");
                ReviewDetailPresenter.this.getView().hideLoadingView();
                if (th instanceof ServerException) {
                    ServerException serverException = (ServerException) th;
                }
            }
        }, new Action() { // from class: com.movieboxpro.android.view.activity.review.ReviewDetailPresenter$doLike$$inlined$transformSubscribe$default$3
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.ReviewDetailPresenter$doLike$$inlined$transformSubscribe$default$4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                ReviewDetailPresenter.this.getView().showLoadingView();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: doLike$lambda-0  reason: not valid java name */
    public static final BBsResponseModel m652doLike$lambda0(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return (BBsResponseModel) JSON.parseObject(it, BBsResponseModel.class);
    }

    @Override // com.movieboxpro.android.view.activity.review.ReviewDetailContract.Presenter
    public void addReply(String str, String str2, String str3, int i, String str4, String str5, String str6, String str7, String str8, String repquote, String str9, String str10, String str11, String str12, String str13, String str14, int i2) {
        Intrinsics.checkNotNullParameter(repquote, "repquote");
        Observable<R> map = Http.getService().reply(API.BBS_URL, "sendreply", str2, str, str3, i, str4, str5, str6, "", "yes", "", URLEncoder.encode(Pattern.compile(ShellUtil.COMMAND_LINE_END).matcher(str7).replaceAll("<br>"), "UTF-8"), "", str8, repquote, str9, getBBSApiAPPID(), str10, str11, str12, str13, str14, i2, 1).map($$Lambda$ReviewDetailPresenter$cZ9GUneIWXurqJEz24yuRKzktA.INSTANCE);
        LifecycleOwner mLifecycleOwner = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(map, "map {\n                  …s.java)\n                }");
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner, "mLifecycleOwner");
        RxSubscribersKt.transform(map, mLifecycleOwner).subscribe(new Consumer() { // from class: com.movieboxpro.android.view.activity.review.ReviewDetailPresenter$addReply$$inlined$transformSubscribe$default$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(T it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                BBsResponseModel bBsResponseModel = (BBsResponseModel) it;
                if (Intrinsics.areEqual("post_reply_succeed", bBsResponseModel.getMessage().getMessageval())) {
                    ReviewDetailPresenter.this.getView().reviewSuccess();
                } else {
                    ToastUtils.showShort(bBsResponseModel.getMessage().getMessageval(), new Object[0]);
                }
                ReviewDetailPresenter.this.getView().hideLoadingView();
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.ReviewDetailPresenter$addReply$$inlined$transformSubscribe$default$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                ApiException handleException = ApiException.handleException(th);
                Intrinsics.checkNotNullExpressionValue(handleException, "handleException(it)");
                ReviewDetailPresenter.this.getView().hideLoadingView();
                ToastUtils.showShort(Intrinsics.stringPlus("Send Failed:", handleException.getMessage()), new Object[0]);
                if (th instanceof ServerException) {
                    ServerException serverException = (ServerException) th;
                }
            }
        }, new Action() { // from class: com.movieboxpro.android.view.activity.review.ReviewDetailPresenter$addReply$$inlined$transformSubscribe$default$3
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.ReviewDetailPresenter$addReply$$inlined$transformSubscribe$default$4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                ReviewDetailPresenter.this.getView().showLoadingView();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: addReply$lambda-4  reason: not valid java name */
    public static final BBsResponseModel m651addReply$lambda4(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return (BBsResponseModel) JSON.parseObject(it, BBsResponseModel.class);
    }

    private final String getBBSApiAPPID() {
        long currentTime = TimeUtils.getCurrentTime() / 1000;
        String md5 = HttpUtils.md5("27");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.CHINA, "%d%s", Arrays.copyOf(new Object[]{Long.valueOf(currentTime), md5}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        return format;
    }
}
