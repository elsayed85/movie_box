package com.movieboxpro.android.view.fragment;

import android.os.Build;
import android.text.TextUtils;
import androidx.lifecycle.LifecycleOwner;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.ares.downloader.jarvis.core.RemoteFileUtil;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.BuildConfig;
import com.movieboxpro.android.adapter.FeaturedAdapter;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.mvp.BasePresenter;
import com.movieboxpro.android.db.entity.Download;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.http.ServerException;
import com.movieboxpro.android.model.AdvertisementModel;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.BaseResponse;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.DownloadInfo;
import com.movieboxpro.android.model.FeaturedDataModel;
import com.movieboxpro.android.model.common.Homelist;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.utils.CacheDiskUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.fragment.FeaturedContract;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import org.apache.commons.validator.Field;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: FeaturedPresenter.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\b\u0010\r\u001a\u00020\nH\u0016J\u001a\u0010\u000e\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\"\u0010\u0011\u001a\u00020\n2\b\u0010\u0012\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\nH\u0016J(\u0010\u0017\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u0010H\u0016J\u0012\u0010\u001a\u001a\u00020\n2\b\u0010\u001b\u001a\u0004\u0018\u00010\fH\u0016R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/movieboxpro/android/view/fragment/FeaturedPresenter;", "Lcom/movieboxpro/android/base/mvp/BasePresenter;", "Lcom/movieboxpro/android/view/fragment/FeaturedContract$View;", "Lcom/movieboxpro/android/view/fragment/FeaturedContract$Presenter;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "(Landroidx/lifecycle/LifecycleOwner;)V", "disposable", "Lio/reactivex/disposables/Disposable;", "advNotify", "", "id", "", "cancelRequest", "deleteHistory", "boxType", "", "dislikeMovie", "mid", "position", "adapter", "Lcom/movieboxpro/android/adapter/FeaturedAdapter$FeaturedListAdapter;", "getContinueList", "getPlayPath", "currSeason", "currEpisode", "requestFeaturedData", "uid", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FeaturedPresenter extends BasePresenter<FeaturedContract.View> implements FeaturedContract.Presenter {
    private Disposable disposable;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FeaturedPresenter(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
    }

    @Override // com.movieboxpro.android.view.fragment.FeaturedContract.Presenter
    public void getPlayPath(final String id, final int i, final int i2, final int i3) {
        Intrinsics.checkNotNullParameter(id, "id");
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = 1;
        Observable flatMap = Http.getService().playingFeedback(API.BASE_URL, API.Common.PLAYING_FEEDBACK, App.getUserData().uid_v2, id, SystemUtils.getUniqueId(App.getContext()), i, i2, i3, Build.BRAND, Build.MODEL).flatMap(new Function() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$FeaturedPresenter$uJNgxKu34fU1I6qeaHb-wjEEOvQ
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                ObservableSource m1193getPlayPath$lambda0;
                m1193getPlayPath$lambda0 = FeaturedPresenter.m1193getPlayPath$lambda0(FeaturedPresenter.this, id, i, i2, i3, (String) obj);
                return m1193getPlayPath$lambda0;
            }
        }).onErrorResumeNext($$Lambda$FeaturedPresenter$VXz84_xyVlSaUlxBBIcY4Or88JA.INSTANCE).flatMap(new Function() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$FeaturedPresenter$rvj-utrXAQHwv_zZJ46c1uZIyEE
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                ObservableSource m1195getPlayPath$lambda5;
                m1195getPlayPath$lambda5 = FeaturedPresenter.m1195getPlayPath$lambda5(i, id, intRef, (String) obj);
                return m1195getPlayPath$lambda5;
            }
        });
        Intrinsics.checkNotNullExpressionValue(flatMap, "getService().playingFeed…          }\n            }");
        LifecycleOwner mLifecycleOwner = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner, "mLifecycleOwner");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(flatMap, mLifecycleOwner), new FeaturedPresenter$getPlayPath$4(this), null, new FeaturedPresenter$getPlayPath$5(this), FeaturedPresenter$getPlayPath$6.INSTANCE, new FeaturedPresenter$getPlayPath$7(this, intRef), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getPlayPath$lambda-0  reason: not valid java name */
    public static final ObservableSource m1193getPlayPath$lambda0(FeaturedPresenter this$0, String id, int i, int i2, int i3, String it) {
        Observable just;
        List<DeviceModelResponse.DeviceModel> list;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(id, "$id");
        Intrinsics.checkNotNullParameter(it, "it");
        Object parseObject = JSON.parseObject(it, RxUtils.buildType(BaseResponse.class, DeviceModelResponse.class), new Feature[0]);
        Intrinsics.checkNotNullExpressionValue(parseObject, "parseObject(\n           …s.java)\n                )");
        BaseResponse baseResponse = (BaseResponse) parseObject;
        if (baseResponse.getCode() == -88) {
            this$0.getView().hideLoadView();
            FeaturedContract.View view = this$0.getView();
            List<DeviceModelResponse.DeviceModel> device_list = ((DeviceModelResponse) baseResponse.getData()).getDevice_list();
            if (device_list != null) {
                list = device_list;
            } else {
                list = new ArrayList();
            }
            view.showScreenManage(new ArrayList<>(list), baseResponse.getMsg(), id, i, i2, i3);
            just = Observable.empty();
        } else {
            just = Observable.just("");
        }
        return just;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getPlayPath$lambda-1  reason: not valid java name */
    public static final Observable m1194getPlayPath$lambda1(Throwable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Observable.just("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r2v1, types: [T, java.lang.String] */
    /* renamed from: getPlayPath$lambda-5  reason: not valid java name */
    public static final ObservableSource m1195getPlayPath$lambda5(final int i, final String id, final Ref.IntRef position, String it) {
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
        return compose.flatMap(new Function() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$FeaturedPresenter$8eV4AZWi1R7Jpo5WXdqlmOpPYdU
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                ObservableSource m1196getPlayPath$lambda5$lambda4;
                m1196getPlayPath$lambda5$lambda4 = FeaturedPresenter.m1196getPlayPath$lambda5$lambda4(Ref.ObjectRef.this, objectRef2, position, id, i, (BaseMediaModel) obj);
                return m1196getPlayPath$lambda5$lambda4;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getPlayPath$lambda-5$lambda-4  reason: not valid java name */
    public static final ObservableSource m1196getPlayPath$lambda5$lambda4(Ref.ObjectRef oss, Ref.ObjectRef groupID, Ref.IntRef position, final String id, final int i, final BaseMediaModel model) {
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
            map = Http.getService().TV_downloadurl(API.BASE_URL, API.Tv.TV_DOWNLAODURL, App.isLogin() ? App.getUserData().uid_v2 : "", model.id, String.valueOf(intRef.element), String.valueOf(intRef2.element), (String) oss.element, (String) groupID.element).compose(RxUtils.rxTranslate2Bean(BaseMediaModel.class)).map(new Function() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$FeaturedPresenter$FdHUKnxFsoF2rvIY2sOnIyCbVvQ
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    Pair m1198getPlayPath$lambda5$lambda4$lambda3;
                    m1198getPlayPath$lambda5$lambda4$lambda3 = FeaturedPresenter.m1198getPlayPath$lambda5$lambda4$lambda3(id, intRef, intRef2, model, (BaseMediaModel) obj);
                    return m1198getPlayPath$lambda5$lambda4$lambda3;
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "getService().TV_download…                        }");
        } else {
            Observable<R> compose = Http.getService().Movie_detail(API.BASE_URL, API.Movie.MOVE_DOWNLAOD, App.isLogin() ? App.getUserData().uid_v2 : "", model.id, App.deviceLang, (String) oss.element, (String) groupID.element).compose(RxUtils.rxTranslate2Bean(BaseMediaModel.class));
            Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
            map = compose.map(new Function() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$FeaturedPresenter$AtfpJVj70Opx_p8l7nylE4U2MXU
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    Pair m1197getPlayPath$lambda5$lambda4$lambda2;
                    m1197getPlayPath$lambda5$lambda4$lambda2 = FeaturedPresenter.m1197getPlayPath$lambda5$lambda4$lambda2(id, i, model, (BaseMediaModel) obj);
                    return m1197getPlayPath$lambda5$lambda4$lambda2;
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "getService().Movie_detai…                        }");
        }
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getPlayPath$lambda-5$lambda-4$lambda-2  reason: not valid java name */
    public static final Pair m1197getPlayPath$lambda5$lambda4$lambda2(String id, int i, BaseMediaModel model, BaseMediaModel it) {
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
    public static final Pair m1198getPlayPath$lambda5$lambda4$lambda3(String id, Ref.IntRef season, Ref.IntRef episode, BaseMediaModel model, BaseMediaModel it) {
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

    @Override // com.movieboxpro.android.view.fragment.FeaturedContract.Presenter
    public void requestFeaturedData(String str) {
        Observable<R> compose = Http.getService().Advertisement(API.BASE_URL, "Advertisement_v2", App.getUserData().uid_v2, "6").compose(RxUtils.rxTranslate2List(AdvertisementModel.class));
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2List(T::class.java))");
        Observable subscribeOn = compose.onErrorReturn($$Lambda$FeaturedPresenter$YddUe9j3u3avJ3_ME6EO98ynHOo.INSTANCE).subscribeOn(Schedulers.io());
        Observable<R> compose2 = Http.getService().Home_list(API.BASE_URL, API.Common.HOME_LIST_V2, str, TtmlNode.COMBINE_ALL, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, "10", PrefsUtils.getInstance().getBoolean(Constant.Prefs.Incognito_Mode, false) ? 1 : 0, BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslate2List(Homelist.class));
        Intrinsics.checkNotNullExpressionValue(compose2, "this.compose(RxUtils.rxT…late2List(T::class.java))");
        Object as = Observable.zip(subscribeOn, compose2.subscribeOn(Schedulers.io()), $$Lambda$FeaturedPresenter$YvxFdAKHLbCf2m4L2zWEAiYIJG8.INSTANCE).onErrorReturn($$Lambda$FeaturedPresenter$o_Vm0KMX6ySn9aBPHtySoOJ_oM4.INSTANCE).observeOn(AndroidSchedulers.mainThread()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner));
        Intrinsics.checkNotNullExpressionValue(as, "zip(\n            adverti…leOwner(mLifecycleOwner))");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, new FeaturedPresenter$requestFeaturedData$3(this), null, new FeaturedPresenter$requestFeaturedData$4(this), null, new FeaturedPresenter$requestFeaturedData$5(this), 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: requestFeaturedData$lambda-6  reason: not valid java name */
    public static final List m1201requestFeaturedData$lambda6(Throwable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: requestFeaturedData$lambda-7  reason: not valid java name */
    public static final FeaturedDataModel m1202requestFeaturedData$lambda7(List t1, List t2) {
        Intrinsics.checkNotNullParameter(t1, "t1");
        Intrinsics.checkNotNullParameter(t2, "t2");
        return new FeaturedDataModel(t1, t2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: requestFeaturedData$lambda-8  reason: not valid java name */
    public static final FeaturedDataModel m1203requestFeaturedData$lambda8(Throwable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ToastUtils.showShort(Intrinsics.stringPlus("Load failed:", it.getMessage()), new Object[0]);
        String cacheData = CacheDiskUtils.getInstance().getString(FeaturedFragment.FEATURED_CACHE, "");
        Intrinsics.checkNotNullExpressionValue(cacheData, "cacheData");
        if (cacheData.length() > 0) {
            return (FeaturedDataModel) JSON.parseObject(cacheData, FeaturedDataModel.class);
        }
        throw new ApiException(it, -1);
    }

    @Override // com.movieboxpro.android.view.fragment.FeaturedContract.Presenter
    public void advNotify(String str) {
        String str2 = str;
        if (str2 == null || StringsKt.isBlank(str2)) {
            return;
        }
        Observable<String> Advertisement_notify = Http.getService().Advertisement_notify(API.BASE_URL, "Advertisement_notify", str);
        LifecycleOwner mLifecycleOwner = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner, "mLifecycleOwner");
        RxSubscribersKt.transformMsg(Advertisement_notify, mLifecycleOwner).subscribe(new Consumer() { // from class: com.movieboxpro.android.view.fragment.FeaturedPresenter$advNotify$$inlined$subscribeToMsg$default$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(T it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                String str3 = (String) it;
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.fragment.FeaturedPresenter$advNotify$$inlined$subscribeToMsg$default$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Intrinsics.checkNotNullExpressionValue(ApiException.handleException(th), "handleException(it)");
                if (th instanceof ServerException) {
                    ServerException serverException = (ServerException) th;
                }
            }
        }, new Action() { // from class: com.movieboxpro.android.view.fragment.FeaturedPresenter$advNotify$$inlined$subscribeToMsg$default$3
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.fragment.FeaturedPresenter$advNotify$$inlined$subscribeToMsg$default$4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
            }
        });
    }

    @Override // com.movieboxpro.android.view.fragment.FeaturedContract.Presenter
    public void getContinueList() {
        HttpRequest.Companion companion = HttpRequest.Companion;
        LifecycleOwner mLifecycleOwner = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner, "mLifecycleOwner");
        RxSubscribersKt.subscribeTo$default(companion.post(mLifecycleOwner, API.Common.HOME_LIST_V2).param(IjkMediaMeta.IJKM_KEY_TYPE, "continue").param("page", (Object) 1).param("pagelimit", (Object) 10).asList(Homelist.Typelist.class), null, null, null, null, new FeaturedPresenter$getContinueList$1(this), 15, null);
    }

    @Override // com.movieboxpro.android.view.fragment.FeaturedContract.Presenter
    public void cancelRequest() {
        Disposable disposable = this.disposable;
        if (disposable == null) {
            return;
        }
        disposable.dispose();
    }

    @Override // com.movieboxpro.android.view.fragment.FeaturedContract.Presenter
    public void dislikeMovie(String str, int i, FeaturedAdapter.FeaturedListAdapter adapter) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        HttpRequest.Companion companion = HttpRequest.Companion;
        LifecycleOwner mLifecycleOwner = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner, "mLifecycleOwner");
        RxSubscribersKt.subscribeTo$default(companion.post(mLifecycleOwner, "Movie_dislike").param("mid", str).param("box_type", (Object) 1).asMsg(), new FeaturedPresenter$dislikeMovie$1(this), null, new FeaturedPresenter$dislikeMovie$2(this), null, new FeaturedPresenter$dislikeMovie$3(this, adapter, i), 10, null);
    }

    @Override // com.movieboxpro.android.view.fragment.FeaturedContract.Presenter
    public void deleteHistory(String str, int i) {
        HttpRequest.Companion companion = HttpRequest.Companion;
        LifecycleOwner mLifecycleOwner = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner, "mLifecycleOwner");
        Observable<String> asRequest = companion.post(mLifecycleOwner, "User_watch_plan_del").param("mid", str).param("box_type", Integer.valueOf(i)).asRequest();
        LifecycleOwner mLifecycleOwner2 = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner2, "mLifecycleOwner");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(asRequest, mLifecycleOwner2), new FeaturedPresenter$deleteHistory$1(this), null, new FeaturedPresenter$deleteHistory$2(this), null, new FeaturedPresenter$deleteHistory$3(this), 10, null);
    }
}
