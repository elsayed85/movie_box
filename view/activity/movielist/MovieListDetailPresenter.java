package com.movieboxpro.android.view.activity.movielist;

import android.os.Build;
import android.text.TextUtils;
import androidx.lifecycle.LifecycleOwner;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.ares.downloader.jarvis.core.RemoteFileUtil;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.movieboxpro.android.BuildConfig;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseObserver;
import com.movieboxpro.android.base.mvp.BasePresenter;
import com.movieboxpro.android.db.entity.Download;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.BaseResponse;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.DownloadInfo;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.model.movie.MovieListDetailModel;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.utils.ApiUtils;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.movielist.MovieListDetailContract;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import org.apache.commons.validator.Field;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: MovieListDetailPresenter.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\n\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\u00020\u0003B\u0011\b\u0000\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J0\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\nH\u0016J$\u0010\u000f\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\nJ&\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0013J0\u0010\u0016\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u0013H\u0016J(\u0010\u001b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\nH\u0016J \u0010\u001c\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\nH\u0016¨\u0006\u001d"}, d2 = {"Lcom/movieboxpro/android/view/activity/movielist/MovieListDetailPresenter;", "Lcom/movieboxpro/android/base/mvp/BasePresenter;", "Lcom/movieboxpro/android/view/activity/movielist/MovieListDetailContract$View;", "Lcom/movieboxpro/android/view/activity/movielist/MovieListDetailContract$Presenter;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "(Landroidx/lifecycle/LifecycleOwner;)V", "collect", "", "uid", "", "lid", IjkMediaMeta.IJKM_KEY_TYPE, "appVersion", "userUid", "deleteCollect", "getPlayPath", "id", "boxType", "", "currSeason", "currEpisode", "requestFilterList", "sort_by", "sort", "page", "pageSize", "requestList", "requestListToPlay", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MovieListDetailPresenter extends BasePresenter<MovieListDetailContract.View> implements MovieListDetailContract.Presenter {
    public MovieListDetailPresenter(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    @Override // com.movieboxpro.android.view.activity.movielist.MovieListDetailContract.Presenter
    public void requestList(String uid, String lid, final int i, String appVersion) {
        Intrinsics.checkNotNullParameter(uid, "uid");
        Intrinsics.checkNotNullParameter(lid, "lid");
        Intrinsics.checkNotNullParameter(appVersion, "appVersion");
        ((ObservableSubscribeProxy) Http.getService().Playlists_get(API.BASE_URL, "Playlists_get", uid, lid, i, 15, appVersion).compose(RxUtils.rxTranslate2Bean(MovieListDetailModel.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new BaseObserver<MovieListDetailModel>() { // from class: com.movieboxpro.android.view.activity.movielist.MovieListDetailPresenter$requestList$1
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                super.onSubscribe(d);
                if (i == 1) {
                    MovieListDetailContract.View view = this.getView();
                    Intrinsics.checkNotNull(view);
                    view.showLoading();
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(MovieListDetailModel movieListDetailModel) {
                Intrinsics.checkNotNullParameter(movieListDetailModel, "movieListDetailModel");
                super.onNext((MovieListDetailPresenter$requestList$1) movieListDetailModel);
                MovieListDetailContract.View view = this.getView();
                Intrinsics.checkNotNull(view);
                view.showData(movieListDetailModel);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                if (i == 1) {
                    MovieListDetailContract.View view = this.getView();
                    Intrinsics.checkNotNull(view);
                    view.hideLoading();
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException e) {
                Intrinsics.checkNotNullParameter(e, "e");
                if (e.getCode() == -2 || e.getCode() == -1) {
                    ToastUtils.showShort(e.getMessage(), new Object[0]);
                    if (i == 1) {
                        MovieListDetailContract.View view = this.getView();
                        Intrinsics.checkNotNull(view);
                        view.movieListDeleted(e.getMessage());
                    }
                }
                MovieListDetailContract.View view2 = this.getView();
                Intrinsics.checkNotNull(view2);
                view2.showErrorPage();
            }
        });
        ((ObservableSubscribeProxy) HttpRequest.Companion.post(ApiUtils.INSTANCE.reviewNum(3)).param("pid", lid).asRequest().compose(RxUtils.rxTranslate2Bean(HashMap.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new Observer<HashMap<?, ?>>() { // from class: com.movieboxpro.android.view.activity.movielist.MovieListDetailPresenter$requestList$2
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
            }

            @Override // io.reactivex.Observer
            public void onNext(HashMap<?, ?> map) {
                Intrinsics.checkNotNullParameter(map, "map");
                MovieListDetailContract.View view = MovieListDetailPresenter.this.getView();
                Intrinsics.checkNotNull(view);
                view.showReviewCount(String.valueOf(map.get("count")));
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable e) {
                Intrinsics.checkNotNullParameter(e, "e");
                CommonExtKt.logD(this, "");
            }
        });
    }

    @Override // com.movieboxpro.android.view.activity.movielist.MovieListDetailContract.Presenter
    public void requestFilterList(String lid, String sort_by, String sort, final int i, int i2) {
        Intrinsics.checkNotNullParameter(lid, "lid");
        Intrinsics.checkNotNullParameter(sort_by, "sort_by");
        Intrinsics.checkNotNullParameter(sort, "sort");
        ((ObservableSubscribeProxy) HttpRequest.Companion.post("Playlists_get_v3").param("sort_by", sort_by).param("sort", sort).param("lid", lid).param("page", Integer.valueOf(i)).param("pagelimit", Integer.valueOf(i2)).asRequest().compose(RxUtils.rxTranslate2Bean(MovieListDetailModel.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new BaseObserver<MovieListDetailModel>() { // from class: com.movieboxpro.android.view.activity.movielist.MovieListDetailPresenter$requestFilterList$1
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                super.onSubscribe(d);
                if (i == 1) {
                    MovieListDetailContract.View view = this.getView();
                    Intrinsics.checkNotNull(view);
                    view.showLoading();
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(MovieListDetailModel movieListDetailModel) {
                Intrinsics.checkNotNullParameter(movieListDetailModel, "movieListDetailModel");
                super.onNext((MovieListDetailPresenter$requestFilterList$1) movieListDetailModel);
                MovieListDetailContract.View view = this.getView();
                Intrinsics.checkNotNull(view);
                view.showData(movieListDetailModel);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                if (i == 1) {
                    MovieListDetailContract.View view = this.getView();
                    Intrinsics.checkNotNull(view);
                    view.hideLoading();
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException e) {
                Intrinsics.checkNotNullParameter(e, "e");
                if (e.getCode() == -2 || e.getCode() == -1) {
                    ToastUtils.showShort(e.getMessage(), new Object[0]);
                    if (i == 1) {
                        MovieListDetailContract.View view = this.getView();
                        Intrinsics.checkNotNull(view);
                        view.movieListDeleted(e.getMessage());
                    }
                }
                MovieListDetailContract.View view2 = this.getView();
                Intrinsics.checkNotNull(view2);
                view2.showErrorPage();
            }
        });
    }

    public final void getPlayPath(final String id, final int i, final int i2, final int i3) {
        Intrinsics.checkNotNullParameter(id, "id");
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = 1;
        Observable flatMap = Http.getService().playingFeedback(API.BASE_URL, API.Common.PLAYING_FEEDBACK, App.getUserData().uid_v2, id, SystemUtils.getUniqueId(App.getContext()), i, i2, i3, Build.BRAND, Build.MODEL).flatMap(new Function() { // from class: com.movieboxpro.android.view.activity.movielist.-$$Lambda$MovieListDetailPresenter$YiuQ_zv-w5xEHapRLMIz-aBj0Mk
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                ObservableSource m397getPlayPath$lambda0;
                m397getPlayPath$lambda0 = MovieListDetailPresenter.m397getPlayPath$lambda0(MovieListDetailPresenter.this, id, i, i2, i3, (String) obj);
                return m397getPlayPath$lambda0;
            }
        }).onErrorResumeNext($$Lambda$MovieListDetailPresenter$1Cx02vEyR8nALbvwyMLb0U35lU.INSTANCE).flatMap(new Function() { // from class: com.movieboxpro.android.view.activity.movielist.-$$Lambda$MovieListDetailPresenter$KqurWeUpb0SWV5bSpxUzRcdohYE
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                ObservableSource m399getPlayPath$lambda5;
                m399getPlayPath$lambda5 = MovieListDetailPresenter.m399getPlayPath$lambda5(i, id, intRef, (String) obj);
                return m399getPlayPath$lambda5;
            }
        });
        Intrinsics.checkNotNullExpressionValue(flatMap, "getService().playingFeed…          }\n            }");
        LifecycleOwner mLifecycleOwner = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner, "mLifecycleOwner");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(flatMap, mLifecycleOwner), new MovieListDetailPresenter$getPlayPath$4(this), null, new MovieListDetailPresenter$getPlayPath$5(this), MovieListDetailPresenter$getPlayPath$6.INSTANCE, new MovieListDetailPresenter$getPlayPath$7(this, intRef), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getPlayPath$lambda-0  reason: not valid java name */
    public static final ObservableSource m397getPlayPath$lambda0(MovieListDetailPresenter this$0, String id, int i, int i2, int i3, String it) {
        Observable just;
        List<DeviceModelResponse.DeviceModel> list;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(id, "$id");
        Intrinsics.checkNotNullParameter(it, "it");
        Object parseObject = JSON.parseObject(it, RxUtils.buildType(BaseResponse.class, DeviceModelResponse.class), new Feature[0]);
        Intrinsics.checkNotNullExpressionValue(parseObject, "parseObject(it, RxUtils.…delResponse::class.java))");
        BaseResponse baseResponse = (BaseResponse) parseObject;
        if (baseResponse.getCode() == -88) {
            MovieListDetailContract.View view = this$0.getView();
            Intrinsics.checkNotNull(view);
            view.hideLoadingView();
            MovieListDetailContract.View view2 = this$0.getView();
            if (view2 != null) {
                List<DeviceModelResponse.DeviceModel> device_list = ((DeviceModelResponse) baseResponse.getData()).getDevice_list();
                if (device_list != null) {
                    list = device_list;
                } else {
                    list = new ArrayList();
                }
                view2.showScreenManage(new ArrayList<>(list), baseResponse.getMsg(), id, i, i2, i3);
            }
            just = Observable.empty();
        } else {
            just = Observable.just("");
        }
        return just;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getPlayPath$lambda-1  reason: not valid java name */
    public static final Observable m398getPlayPath$lambda1(Throwable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Observable.just("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r2v1, types: [T, java.lang.String] */
    /* renamed from: getPlayPath$lambda-5  reason: not valid java name */
    public static final ObservableSource m399getPlayPath$lambda5(final int i, final String id, final Ref.IntRef position, String it) {
        Observable compose;
        Intrinsics.checkNotNullParameter(id, "$id");
        Intrinsics.checkNotNullParameter(position, "$position");
        Intrinsics.checkNotNullParameter(it, "it");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, "");
        if (TextUtils.isEmpty((CharSequence) objectRef2.element)) {
            objectRef.element = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
        } else if (StringsKt.equals("0", (String) objectRef2.element, true)) {
            objectRef.element = "";
            objectRef2.element = "";
        }
        if (i == 1) {
            compose = Http.getService().Movie_detail(API.BASE_URL, API.Movie.MOVE_DETAIL, App.isLogin() ? App.getUserData().uid_v2 : "", id, App.deviceLang, (String) objectRef.element, (String) objectRef2.element).compose(RxUtils.rxTranslate2Bean(MovieDetail.class));
            Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
        } else {
            compose = Http.getService().Tv_detail(API.BASE_URL, API.Tv.TV_DETAIL, App.isLogin() ? App.getUserData().uid_v2 : "", id, App.deviceLang, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE).compose(RxUtils.rxTranslate2Bean(TvDetail.class));
            Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
        }
        return compose.flatMap(new Function() { // from class: com.movieboxpro.android.view.activity.movielist.-$$Lambda$MovieListDetailPresenter$KvehMZD5ITA3ULfX_vEkWiXmN9g
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                ObservableSource m400getPlayPath$lambda5$lambda4;
                m400getPlayPath$lambda5$lambda4 = MovieListDetailPresenter.m400getPlayPath$lambda5$lambda4(Ref.ObjectRef.this, objectRef2, position, id, i, (BaseMediaModel) obj);
                return m400getPlayPath$lambda5$lambda4;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getPlayPath$lambda-5$lambda-4  reason: not valid java name */
    public static final ObservableSource m400getPlayPath$lambda5$lambda4(Ref.ObjectRef oss, Ref.ObjectRef groupID, Ref.IntRef position, final String id, final int i, final BaseMediaModel model) {
        Observable map;
        Intrinsics.checkNotNullParameter(oss, "$oss");
        Intrinsics.checkNotNullParameter(groupID, "$groupID");
        Intrinsics.checkNotNullParameter(position, "$position");
        Intrinsics.checkNotNullParameter(id, "$id");
        Intrinsics.checkNotNullParameter(model, "model");
        if (!(model instanceof MovieDetail)) {
            TvDetail tvDetail = (TvDetail) model;
            final Ref.IntRef intRef = new Ref.IntRef();
            intRef.element = 1;
            final Ref.IntRef intRef2 = new Ref.IntRef();
            intRef2.element = 1;
            String str = tvDetail.history;
            if (!(str == null || str.length() == 0) && !Intrinsics.areEqual(Field.TOKEN_INDEXED, tvDetail.history)) {
                Object parse = JSONObject.parse(tvDetail.history);
                if (parse == null) {
                    throw new NullPointerException("null cannot be cast to non-null type com.alibaba.fastjson.JSONObject");
                }
                JSONObject jSONObject = (JSONObject) parse;
                Integer integer = jSONObject.getInteger("season");
                Intrinsics.checkNotNullExpressionValue(integer, "jsonObject.getInteger(\"season\")");
                intRef.element = integer.intValue();
                Integer integer2 = jSONObject.getInteger("episode");
                Intrinsics.checkNotNullExpressionValue(integer2, "jsonObject.getInteger(\"episode\")");
                intRef2.element = integer2.intValue();
                Integer integer3 = jSONObject.getInteger(DownloadInfo.DOWNLOAD_OVER);
                if ((integer3 != null ? integer3.intValue() : 0) == 1) {
                    intRef2.element++;
                }
            }
            position.element = intRef2.element;
            map = Http.getService().TV_downloadurl(API.BASE_URL, API.Tv.TV_DOWNLAODURL, App.isLogin() ? App.getUserData().uid_v2 : "", model.id, String.valueOf(intRef.element), String.valueOf(intRef2.element), (String) oss.element, (String) groupID.element).compose(RxUtils.rxTranslate2Bean(BaseMediaModel.class)).map(new Function() { // from class: com.movieboxpro.android.view.activity.movielist.-$$Lambda$MovieListDetailPresenter$QhAoVlg7x1L3vCJEL0Grwyg97Bg
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    Pair m402getPlayPath$lambda5$lambda4$lambda3;
                    m402getPlayPath$lambda5$lambda4$lambda3 = MovieListDetailPresenter.m402getPlayPath$lambda5$lambda4$lambda3(id, intRef, intRef2, model, (BaseMediaModel) obj);
                    return m402getPlayPath$lambda5$lambda4$lambda3;
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "getService().TV_download…                        }");
        } else {
            Observable<R> compose = Http.getService().Movie_detail(API.BASE_URL, API.Movie.MOVE_DOWNLAOD, App.isLogin() ? App.getUserData().uid_v2 : "", model.id, App.deviceLang, (String) oss.element, (String) groupID.element).compose(RxUtils.rxTranslate2Bean(BaseMediaModel.class));
            Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
            map = compose.map(new Function() { // from class: com.movieboxpro.android.view.activity.movielist.-$$Lambda$MovieListDetailPresenter$a4HzBHEYLh80y_WrU_CSHmaizm0
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    Pair m401getPlayPath$lambda5$lambda4$lambda2;
                    m401getPlayPath$lambda5$lambda4$lambda2 = MovieListDetailPresenter.m401getPlayPath$lambda5$lambda4$lambda2(id, i, model, (BaseMediaModel) obj);
                    return m401getPlayPath$lambda5$lambda4$lambda2;
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "getService().Movie_detai…                        }");
        }
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getPlayPath$lambda-5$lambda-4$lambda-2  reason: not valid java name */
    public static final Pair m401getPlayPath$lambda5$lambda4$lambda2(String id, int i, BaseMediaModel model, BaseMediaModel it) {
        Intrinsics.checkNotNullParameter(id, "$id");
        Intrinsics.checkNotNullParameter(model, "$model");
        Intrinsics.checkNotNullParameter(it, "it");
        Download findByIdAndType = App.getDB().downloadDao().findByIdAndType(id, i, 2);
        if (findByIdAndType != null) {
            findByIdAndType.setPath(Constant.DIR_DOWNLOAD + ((Object) File.separator) + ((Object) RemoteFileUtil.getFileName(findByIdAndType.getPath(), findByIdAndType.getTitle(), Constant.DIR_DOWNLOAD)));
            if (new File(findByIdAndType.getPath()).exists()) {
                BaseMediaModel.DownloadFile downloadFile = (BaseMediaModel.DownloadFile) JSONObject.parseObject(JSONObject.toJSONString(findByIdAndType), BaseMediaModel.DownloadFile.class);
                downloadFile.real_quality = findByIdAndType.getQuality();
                it.list.add(0, downloadFile);
            }
        }
        return new Pair(model, it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getPlayPath$lambda-5$lambda-4$lambda-3  reason: not valid java name */
    public static final Pair m402getPlayPath$lambda5$lambda4$lambda3(String id, Ref.IntRef season, Ref.IntRef episode, BaseMediaModel model, BaseMediaModel it) {
        Intrinsics.checkNotNullParameter(id, "$id");
        Intrinsics.checkNotNullParameter(season, "$season");
        Intrinsics.checkNotNullParameter(episode, "$episode");
        Intrinsics.checkNotNullParameter(model, "$model");
        Intrinsics.checkNotNullParameter(it, "it");
        Download findByTidAndSeasonEpisode = App.getDB().downloadDao().findByTidAndSeasonEpisode(id, season.element, episode.element);
        if (findByTidAndSeasonEpisode != null && findByTidAndSeasonEpisode.getStatue() == 2) {
            findByTidAndSeasonEpisode.setPath(Constant.DIR_DOWNLOAD + ((Object) File.separator) + ((Object) RemoteFileUtil.getFileName(findByTidAndSeasonEpisode.getPath(), findByTidAndSeasonEpisode.getTitle(), Constant.DIR_DOWNLOAD)));
            if (new File(findByTidAndSeasonEpisode.getPath()).exists()) {
                BaseMediaModel.DownloadFile downloadFile = (BaseMediaModel.DownloadFile) JSONObject.parseObject(JSONObject.toJSONString(findByTidAndSeasonEpisode), BaseMediaModel.DownloadFile.class);
                downloadFile.real_quality = findByTidAndSeasonEpisode.getQuality();
                it.list.add(0, downloadFile);
            }
        }
        return new Pair(model, it);
    }

    @Override // com.movieboxpro.android.view.activity.movielist.MovieListDetailContract.Presenter
    public void requestListToPlay(String lid, String sort_by, String sort) {
        Intrinsics.checkNotNullParameter(lid, "lid");
        Intrinsics.checkNotNullParameter(sort_by, "sort_by");
        Intrinsics.checkNotNullParameter(sort, "sort");
        ((ObservableSubscribeProxy) HttpRequest.Companion.post("Playlists_get_v2").param("sort_by", sort_by).param("sort", sort).param("lid", lid).asRequest().compose(RxUtils.rxTranslate2Bean(MovieListDetailModel.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new Observer<MovieListDetailModel>() { // from class: com.movieboxpro.android.view.activity.movielist.MovieListDetailPresenter$requestListToPlay$1
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                MovieListDetailContract.View view = MovieListDetailPresenter.this.getView();
                Intrinsics.checkNotNull(view);
                view.showLoadingView();
            }

            @Override // io.reactivex.Observer
            public void onNext(MovieListDetailModel model) {
                Intrinsics.checkNotNullParameter(model, "model");
                MovieListDetailContract.View view = MovieListDetailPresenter.this.getView();
                Intrinsics.checkNotNull(view);
                view.hideLoadingView();
                MovieListDetailContract.View view2 = MovieListDetailPresenter.this.getView();
                Intrinsics.checkNotNull(view2);
                view2.randomPlay(model);
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable e) {
                Intrinsics.checkNotNullParameter(e, "e");
                MovieListDetailContract.View view = MovieListDetailPresenter.this.getView();
                Intrinsics.checkNotNull(view);
                view.hideLoadingView();
                ToastUtils.showShort(Intrinsics.stringPlus("Load failed:", e.getMessage()), new Object[0]);
            }
        });
    }

    public final void deleteCollect(String str, String str2, String str3) {
        ((ObservableSubscribeProxy) Http.getService().Playlists_collect(API.BASE_URL, "Playlists_collect", str, str2, str3, BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new BaseObserver<String>() { // from class: com.movieboxpro.android.view.activity.movielist.MovieListDetailPresenter$deleteCollect$1
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                super.onSubscribe(d);
                MovieListDetailContract.View view = MovieListDetailPresenter.this.getView();
                Intrinsics.checkNotNull(view);
                view.showLoadingView();
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(String b) {
                Intrinsics.checkNotNullParameter(b, "b");
                super.onNext((MovieListDetailPresenter$deleteCollect$1) b);
                MovieListDetailContract.View view = MovieListDetailPresenter.this.getView();
                Intrinsics.checkNotNull(view);
                view.removeCollectComplete();
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                MovieListDetailContract.View view = MovieListDetailPresenter.this.getView();
                Intrinsics.checkNotNull(view);
                view.hideLoadingView();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException e) {
                Intrinsics.checkNotNullParameter(e, "e");
                MovieListDetailContract.View view = MovieListDetailPresenter.this.getView();
                Intrinsics.checkNotNull(view);
                view.hideLoadingView();
            }
        });
    }

    @Override // com.movieboxpro.android.view.activity.movielist.MovieListDetailContract.Presenter
    public void collect(String uid, String lid, String type, String appVersion, String userUid) {
        Intrinsics.checkNotNullParameter(uid, "uid");
        Intrinsics.checkNotNullParameter(lid, "lid");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(appVersion, "appVersion");
        Intrinsics.checkNotNullParameter(userUid, "userUid");
        if (Intrinsics.areEqual("add", type) && !TextUtils.isEmpty(userUid)) {
            ((ObservableSubscribeProxy) Http.getService().addMovieListNotice(API.BBS_URL, "add_mylist_notice", uid, App.getUserData().username, userUid, lid).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.movielist.MovieListDetailPresenter$collect$1
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onError(Throwable e) {
                    Intrinsics.checkNotNullParameter(e, "e");
                }

                @Override // io.reactivex.Observer
                public void onNext(String t) {
                    Intrinsics.checkNotNullParameter(t, "t");
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable d) {
                    Intrinsics.checkNotNullParameter(d, "d");
                }
            });
        }
        ((ObservableSubscribeProxy) Http.getService().Playlists_collect(API.BASE_URL, "Playlists_collect", uid, lid, type, BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new BaseObserver<String>() { // from class: com.movieboxpro.android.view.activity.movielist.MovieListDetailPresenter$collect$2
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                super.onSubscribe(d);
                MovieListDetailContract.View view = MovieListDetailPresenter.this.getView();
                Intrinsics.checkNotNull(view);
                view.showLoadingView();
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(String b) {
                Intrinsics.checkNotNullParameter(b, "b");
                super.onNext((MovieListDetailPresenter$collect$2) b);
                MovieListDetailContract.View view = MovieListDetailPresenter.this.getView();
                Intrinsics.checkNotNull(view);
                view.doCollectComplete(true);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                MovieListDetailContract.View view = MovieListDetailPresenter.this.getView();
                Intrinsics.checkNotNull(view);
                view.hideLoadingView();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException e) {
                Intrinsics.checkNotNullParameter(e, "e");
                MovieListDetailContract.View view = MovieListDetailPresenter.this.getView();
                Intrinsics.checkNotNull(view);
                view.hideLoadingView();
                MovieListDetailContract.View view2 = MovieListDetailPresenter.this.getView();
                Intrinsics.checkNotNull(view2);
                view2.doCollectComplete(false);
            }
        });
    }
}
