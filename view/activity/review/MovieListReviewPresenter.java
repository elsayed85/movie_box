package com.movieboxpro.android.view.activity.review;

import androidx.lifecycle.LifecycleOwner;
import com.alibaba.fastjson.JSON;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.movieboxpro.android.BuildConfig;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.base.mvp.BasePresenter;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpUtils;
import com.movieboxpro.android.http.ServerException;
import com.movieboxpro.android.model.BBsResponseModel;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ShellUtil;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.review.MovieListReviewContract;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
/* compiled from: MovieListReviewPresenter.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0086\u0002\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\n2\b\u0010\u0011\u001a\u0004\u0018\u00010\n2\b\u0010\u0012\u001a\u0004\u0018\u00010\n2\b\u0010\u0013\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0014\u001a\u00020\n2\b\u0010\u0015\u001a\u0004\u0018\u00010\n2\b\u0010\u0016\u001a\u0004\u0018\u00010\n2\b\u0010\u0017\u001a\u0004\u0018\u00010\n2\b\u0010\u0018\u001a\u0004\u0018\u00010\n2\b\u0010\u0019\u001a\u0004\u0018\u00010\n2\b\u0010\u001a\u001a\u0004\u0018\u00010\n2\u0006\u0010\u001b\u001a\u00020\u000e2\b\u0010\u001c\u001a\u0004\u0018\u00010\n2\b\u0010\u001d\u001a\u0004\u0018\u00010\n2\b\u0010\u001e\u001a\u0004\u0018\u00010\n2\b\u0010\u001f\u001a\u0004\u0018\u00010\n2\b\u0010 \u001a\u0004\u0018\u00010\n2\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010#\u001a\u0004\u0018\u00010\n2\b\u0010$\u001a\u0004\u0018\u00010\n2\b\u0010%\u001a\u0004\u0018\u00010\nH\u0016J:\u0010&\u001a\u00020\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\n2\b\u0010\u0011\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\b\u0010\u0016\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010'\u001a\u00020\nH\u0002¨\u0006("}, d2 = {"Lcom/movieboxpro/android/view/activity/review/MovieListReviewPresenter;", "Lcom/movieboxpro/android/base/mvp/BasePresenter;", "Lcom/movieboxpro/android/view/activity/review/MovieListReviewContract$View;", "Lcom/movieboxpro/android/view/activity/review/MovieListReviewContract$Presenter;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "(Landroidx/lifecycle/LifecycleOwner;)V", "addReply", "", "uid", "", "username", "tid", "htmlOn", "", "auth", "authkey", "formash", FirebaseAnalytics.Param.CONTENT, "userFile", "repquote", "authorId", "pid", "authorUsername", "authorAvatar", "authorTime", "authorContent", "reply", "listName", "listImg", "listDesc", "listId", "email", "bBsInfo", "Lcom/movieboxpro/android/model/user/UserModel$BBsInfo;", "userId", "listUsername", "listAvatar", "doLike", "getBBSApiAPPID", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MovieListReviewPresenter extends BasePresenter<MovieListReviewContract.View> implements MovieListReviewContract.Presenter {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MovieListReviewPresenter(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
    }

    @Override // com.movieboxpro.android.view.activity.review.MovieListReviewContract.Presenter
    public void doLike(String str, String str2, String str3, String str4, String str5) {
        Observable<R> map = Http.getService().doLike(API.BBS_URL, "postreview", str4, str5, "support", str, str2, str3).map($$Lambda$MovieListReviewPresenter$GDWvieIMVAsfitBNPxEtUMm6yDw.INSTANCE);
        LifecycleOwner mLifecycleOwner = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(map, "map {\n                  …s.java)\n                }");
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner, "mLifecycleOwner");
        RxSubscribersKt.transform(map, mLifecycleOwner).subscribe(new Consumer() { // from class: com.movieboxpro.android.view.activity.review.MovieListReviewPresenter$doLike$$inlined$transformSubscribe$default$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(T it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                BBsResponseModel bBsResponseModel = (BBsResponseModel) it;
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.MovieListReviewPresenter$doLike$$inlined$transformSubscribe$default$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Intrinsics.checkNotNullExpressionValue(ApiException.handleException(th), "handleException(it)");
                MovieListReviewPresenter.this.getView().hideLoadingView();
                if (th instanceof ServerException) {
                    ServerException serverException = (ServerException) th;
                }
            }
        }, new Action() { // from class: com.movieboxpro.android.view.activity.review.MovieListReviewPresenter$doLike$$inlined$transformSubscribe$default$3
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.MovieListReviewPresenter$doLike$$inlined$transformSubscribe$default$4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                MovieListReviewPresenter.this.getView().showLoadingView();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: doLike$lambda-0  reason: not valid java name */
    public static final BBsResponseModel m586doLike$lambda0(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return (BBsResponseModel) JSON.parseObject(it, BBsResponseModel.class);
    }

    @Override // com.movieboxpro.android.view.activity.review.MovieListReviewContract.Presenter
    public void addReply(final String str, final String str2, final String str3, final int i, final String str4, final String str5, final String str6, final String str7, final String str8, final String repquote, final String str9, final String str10, final String str11, final String str12, final String str13, final String str14, final int i2, final String str15, final String str16, final String str17, final String str18, final String str19, UserModel.BBsInfo bBsInfo, final String str20, final String str21, final String str22) {
        Intrinsics.checkNotNullParameter(repquote, "repquote");
        if (bBsInfo != null) {
            Observable<R> map = Http.getService().movieListReply(API.BBS_URL, "movielist_newthread", str2, str, str3, i, str4, str5, str6, "", "yes", str15, URLEncoder.encode(Pattern.compile(ShellUtil.COMMAND_LINE_END).matcher(str7).replaceAll("<br>"), "UTF-8"), "", str8, repquote, str9, getBBSApiAPPID(), str10, str11, str12, str13, str14 == null ? "" : str14, i2, str21, str18, str17 == null ? "" : str17, str19, "", str20, str15, str16, str21, str22).map($$Lambda$MovieListReviewPresenter$tJbPcsRI_NLcsRhCOJrXXk3_2xU.INSTANCE);
            Intrinsics.checkNotNullExpressionValue(map, "getService().movieListRe…va)\n                    }");
            LifecycleOwner mLifecycleOwner = this.mLifecycleOwner;
            Intrinsics.checkNotNullExpressionValue(mLifecycleOwner, "mLifecycleOwner");
            RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(map, mLifecycleOwner), new MovieListReviewPresenter$addReply$5(this), null, new MovieListReviewPresenter$addReply$6(this), null, new MovieListReviewPresenter$addReply$7(this), 10, null);
            return;
        }
        Observable<R> compose = Http.getService().getUserInfo(API.BASE_URL, API.USER.USERINFO, str20, "", BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslate2Bean(UserModel.UserData.class));
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
        compose.flatMap(new Function() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$MovieListReviewPresenter$-ItogBgdtE7YBmUHx0cKQtHC08g
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                ObservableSource m583addReply$lambda4;
                m583addReply$lambda4 = MovieListReviewPresenter.m583addReply$lambda4(str7, str2, str, str3, i, str4, str5, str6, str15, str8, repquote, str9, this, str10, str11, str12, str13, str14, i2, str21, str18, str17, str19, str20, str16, str22, (UserModel.UserData) obj);
                return m583addReply$lambda4;
            }
        }).map($$Lambda$MovieListReviewPresenter$t4TAA_5wDtxsXKhI4fuj0ffHcs.INSTANCE).compose(RxUtils.rxSchedulerHelper()).subscribe(new HttpResponseObserver<BBsResponseModel>() { // from class: com.movieboxpro.android.view.activity.review.MovieListReviewPresenter$addReply$3
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                MovieListReviewPresenter.this.getView().showLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(BBsResponseModel model) {
                Intrinsics.checkNotNullParameter(model, "model");
                if (model.getMessage() != null) {
                    if (Intrinsics.areEqual("post_reply_succeed", model.getMessage().getMessageval())) {
                        MovieListReviewPresenter.this.getView().reviewSuccess();
                    } else {
                        ToastUtils.showShort(model.getMessage().getMessageval(), new Object[0]);
                    }
                } else {
                    ToastUtils.showShort("Reply failed", new Object[0]);
                }
                MovieListReviewPresenter.this.getView().hideLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                Intrinsics.checkNotNullParameter(e, "e");
                MovieListReviewPresenter.this.getView().hideLoadingView();
                ToastUtils.showShort(Intrinsics.stringPlus("Send Failed:", e.getMessage()), new Object[0]);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: addReply$lambda-4  reason: not valid java name */
    public static final ObservableSource m583addReply$lambda4(String str, String str2, String str3, String str4, int i, String str5, String str6, String str7, String str8, String str9, String repquote, String str10, MovieListReviewPresenter this$0, String str11, String str12, String str13, String str14, String str15, int i2, String str16, String str17, String str18, String str19, String str20, String str21, String str22, UserModel.UserData it) {
        Observable<String> movieListReply;
        Intrinsics.checkNotNullParameter(repquote, "$repquote");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        List<UserModel.Bind> list = it.bind;
        if (!(list == null || list.isEmpty())) {
            movieListReply = Http.getService().movieListReply(API.BBS_URL, "movielist_newthread", str2, str3, str4, i, str5, str6, str7, "", "yes", str8, URLEncoder.encode(Pattern.compile(ShellUtil.COMMAND_LINE_END).matcher(str).replaceAll("<br>"), "UTF-8"), "", str9, repquote, str10, this$0.getBBSApiAPPID(), str11, str12, str13, str14, str15 == null ? "" : str15, i2, str16, str17, str18 == null ? "" : str18, str19, it.bind.get(0).openid, str20, str8, str21, str16, str22);
        } else {
            movieListReply = Http.getService().movieListReply(API.BBS_URL, "movielist_newthread", str2, str3, str4, i, str5, str6, str7, "", "yes", str8, URLEncoder.encode(Pattern.compile(ShellUtil.COMMAND_LINE_END).matcher(str).replaceAll("<br>"), "UTF-8"), "", str9, repquote, str10, this$0.getBBSApiAPPID(), str11, str12, str13, str14, str15 == null ? "" : str15, i2, str16, str17, str18 == null ? "" : str18, str19, "", str20, str8, str21, str16, str22);
        }
        return movieListReply;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: addReply$lambda-5  reason: not valid java name */
    public static final BBsResponseModel m584addReply$lambda5(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return (BBsResponseModel) JSON.parseObject(it, BBsResponseModel.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: addReply$lambda-6  reason: not valid java name */
    public static final BBsResponseModel m585addReply$lambda6(String it) {
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
