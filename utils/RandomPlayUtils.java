package com.movieboxpro.android.utils;

import android.os.Build;
import android.text.TextUtils;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.ares.downloader.jarvis.core.RemoteFileUtil;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.db.entity.Download;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.BaseResponse;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.DownloadInfo;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.model.movie.MovieListDetailModel;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.view.dialog.ScreenManageDialog;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import org.apache.commons.validator.Field;
/* compiled from: RandomPlayUtils.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u0000 \u001a2\u00020\u0001:\u0002\u001a\u001bB\u0005¢\u0006\u0002\u0010\u0002J(\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0004H\u0002J\u001e\u0010\u0017\u001a\u00020\u00112\u0016\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\fj\b\u0012\u0004\u0012\u00020\u000f`\rJ\u001e\u0010\u0019\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\u00040\fj\b\u0012\u0004\u0012\u00020\u0004`\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\fj\b\u0012\u0004\u0012\u00020\u000f`\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/movieboxpro/android/utils/RandomPlayUtils;", "", "()V", "currRandomIndex", "", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/utils/RandomPlayUtils$RandomPlayListener;", "randomIndex", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "videoList", "Lcom/movieboxpro/android/model/movie/MovieListDetailModel$ListBean;", "getPlayPath", "", "id", "", "boxType", "currSeason", "currEpisode", "setVideoList", "list", "startRandomPlay", "Companion", "RandomPlayListener", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RandomPlayUtils {
    public static final Companion Companion = new Companion(null);
    private static final Lazy<RandomPlayUtils> instance$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) RandomPlayUtils$Companion$instance$2.INSTANCE);
    private int currRandomIndex;
    private FragmentManager fragmentManager;
    private LifecycleOwner lifecycleOwner;
    private RandomPlayListener listener;
    private final ArrayList<MovieListDetailModel.ListBean> videoList = new ArrayList<>();
    private final ArrayList<Integer> randomIndex = new ArrayList<>();

    /* compiled from: RandomPlayUtils.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\u0003H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, d2 = {"Lcom/movieboxpro/android/utils/RandomPlayUtils$RandomPlayListener;", "", "hideLoading", "", "moviePlay", "movieDetail", "Lcom/movieboxpro/android/model/movie/MovieDetail;", "showLoading", "tvPlay", "tvDetail", "Lcom/movieboxpro/android/model/tv/TvDetail;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface RandomPlayListener {
        void hideLoading();

        void moviePlay(MovieDetail movieDetail);

        void showLoading();

        void tvPlay(TvDetail tvDetail);
    }

    /* compiled from: RandomPlayUtils.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/utils/RandomPlayUtils$Companion;", "", "()V", "instance", "Lcom/movieboxpro/android/utils/RandomPlayUtils;", "getInstance", "()Lcom/movieboxpro/android/utils/RandomPlayUtils;", "instance$delegate", "Lkotlin/Lazy;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final RandomPlayUtils getInstance() {
            return (RandomPlayUtils) RandomPlayUtils.instance$delegate.getValue();
        }
    }

    public final void setVideoList(ArrayList<MovieListDetailModel.ListBean> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.videoList.clear();
        this.videoList.addAll(list);
    }

    public final void startRandomPlay(RandomPlayListener listener, FragmentManager fragmentManager, LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(fragmentManager, "fragmentManager");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        this.listener = listener;
        this.fragmentManager = fragmentManager;
        this.lifecycleOwner = lifecycleOwner;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getPlayPath(final String str, final int i, final int i2, final int i3) {
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = 1;
        Observable flatMap = Http.getService().playingFeedback(API.BASE_URL, API.Common.PLAYING_FEEDBACK, App.getUserData().uid_v2, str, SystemUtils.getUniqueId(App.getContext()), i, i2, i3, Build.BRAND, Build.MODEL).flatMap(new Function() { // from class: com.movieboxpro.android.utils.-$$Lambda$RandomPlayUtils$Ngkggn3MYnRPq9c-AzrgsgOjY5Q
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                ObservableSource m101getPlayPath$lambda0;
                m101getPlayPath$lambda0 = RandomPlayUtils.m101getPlayPath$lambda0(RandomPlayUtils.this, str, i, i2, i3, (String) obj);
                return m101getPlayPath$lambda0;
            }
        }).onErrorResumeNext($$Lambda$RandomPlayUtils$_CeTbJBXff4MUDF4wYhNkFS7hk.INSTANCE).flatMap(new Function() { // from class: com.movieboxpro.android.utils.-$$Lambda$RandomPlayUtils$lIX4myggtacYImHedGYa-by0EoY
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                ObservableSource m103getPlayPath$lambda5;
                m103getPlayPath$lambda5 = RandomPlayUtils.m103getPlayPath$lambda5(i, str, intRef, (String) obj);
                return m103getPlayPath$lambda5;
            }
        });
        Intrinsics.checkNotNullExpressionValue(flatMap, "getService().playingFeed…          }\n            }");
        LifecycleOwner lifecycleOwner = this.lifecycleOwner;
        if (lifecycleOwner == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lifecycleOwner");
            lifecycleOwner = null;
        }
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(flatMap, lifecycleOwner), new RandomPlayUtils$getPlayPath$4(this), null, new RandomPlayUtils$getPlayPath$5(this), RandomPlayUtils$getPlayPath$6.INSTANCE, new RandomPlayUtils$getPlayPath$7(this, intRef), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getPlayPath$lambda-0  reason: not valid java name */
    public static final ObservableSource m101getPlayPath$lambda0(final RandomPlayUtils this$0, final String id, final int i, final int i2, final int i3, String it) {
        Observable just;
        List<DeviceModelResponse.DeviceModel> list;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(id, "$id");
        Intrinsics.checkNotNullParameter(it, "it");
        Object parseObject = JSON.parseObject(it, RxUtils.buildType(BaseResponse.class, DeviceModelResponse.class), new Feature[0]);
        Intrinsics.checkNotNullExpressionValue(parseObject, "parseObject(it, RxUtils.…delResponse::class.java))");
        BaseResponse baseResponse = (BaseResponse) parseObject;
        if (baseResponse.getCode() == -88) {
            RandomPlayListener randomPlayListener = this$0.listener;
            FragmentManager fragmentManager = null;
            if (randomPlayListener == null) {
                Intrinsics.throwUninitializedPropertyAccessException(ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
                randomPlayListener = null;
            }
            randomPlayListener.hideLoading();
            ScreenManageDialog.Companion companion = ScreenManageDialog.Companion;
            List<DeviceModelResponse.DeviceModel> device_list = ((DeviceModelResponse) baseResponse.getData()).getDevice_list();
            if (device_list != null) {
                list = device_list;
            } else {
                list = new ArrayList();
            }
            ScreenManageDialog newInstance$default = ScreenManageDialog.Companion.newInstance$default(companion, new ArrayList(list), baseResponse.getMsg(), false, 4, null);
            newInstance$default.setListener(new ScreenManageDialog.OnStopDeviceListener() { // from class: com.movieboxpro.android.utils.RandomPlayUtils$getPlayPath$1$1
                @Override // com.movieboxpro.android.view.dialog.ScreenManageDialog.OnStopDeviceListener
                public void onStopDevice() {
                    RandomPlayUtils.this.getPlayPath(id, i, i2, i3);
                }
            });
            FragmentManager fragmentManager2 = this$0.fragmentManager;
            if (fragmentManager2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fragmentManager");
            } else {
                fragmentManager = fragmentManager2;
            }
            newInstance$default.show(fragmentManager, ScreenManageDialog.class.getSimpleName());
            just = Observable.empty();
        } else {
            just = Observable.just("");
        }
        return just;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getPlayPath$lambda-1  reason: not valid java name */
    public static final Observable m102getPlayPath$lambda1(Throwable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Observable.just("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r2v1, types: [T, java.lang.String] */
    /* renamed from: getPlayPath$lambda-5  reason: not valid java name */
    public static final ObservableSource m103getPlayPath$lambda5(final int i, final String id, final Ref.IntRef position, String it) {
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
        return compose.flatMap(new Function() { // from class: com.movieboxpro.android.utils.-$$Lambda$RandomPlayUtils$shIMJiJCws7Vv4eHD9dfNpPeNlg
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                ObservableSource m104getPlayPath$lambda5$lambda4;
                m104getPlayPath$lambda5$lambda4 = RandomPlayUtils.m104getPlayPath$lambda5$lambda4(Ref.ObjectRef.this, objectRef2, position, id, i, (BaseMediaModel) obj);
                return m104getPlayPath$lambda5$lambda4;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getPlayPath$lambda-5$lambda-4  reason: not valid java name */
    public static final ObservableSource m104getPlayPath$lambda5$lambda4(Ref.ObjectRef oss, Ref.ObjectRef groupID, Ref.IntRef position, final String id, final int i, final BaseMediaModel model) {
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
            map = Http.getService().TV_downloadurl(API.BASE_URL, API.Tv.TV_DOWNLAODURL, App.isLogin() ? App.getUserData().uid_v2 : "", model.id, String.valueOf(intRef.element), String.valueOf(intRef2.element), (String) oss.element, (String) groupID.element).compose(RxUtils.rxTranslate2Bean(BaseMediaModel.class)).map(new Function() { // from class: com.movieboxpro.android.utils.-$$Lambda$RandomPlayUtils$YLYRQVJmkO3EZQwUcO6UjlLE0IQ
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    Pair m106getPlayPath$lambda5$lambda4$lambda3;
                    m106getPlayPath$lambda5$lambda4$lambda3 = RandomPlayUtils.m106getPlayPath$lambda5$lambda4$lambda3(id, intRef, intRef2, model, (BaseMediaModel) obj);
                    return m106getPlayPath$lambda5$lambda4$lambda3;
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "getService().TV_download…                        }");
        } else {
            Observable<R> compose = Http.getService().Movie_detail(API.BASE_URL, API.Movie.MOVE_DOWNLAOD, App.isLogin() ? App.getUserData().uid_v2 : "", model.id, App.deviceLang, (String) oss.element, (String) groupID.element).compose(RxUtils.rxTranslate2Bean(BaseMediaModel.class));
            Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
            map = compose.map(new Function() { // from class: com.movieboxpro.android.utils.-$$Lambda$RandomPlayUtils$_Hh5vZFvu6ZNlBysoPuaha6DtwI
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    Pair m105getPlayPath$lambda5$lambda4$lambda2;
                    m105getPlayPath$lambda5$lambda4$lambda2 = RandomPlayUtils.m105getPlayPath$lambda5$lambda4$lambda2(id, i, model, (BaseMediaModel) obj);
                    return m105getPlayPath$lambda5$lambda4$lambda2;
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "getService().Movie_detai…                        }");
        }
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getPlayPath$lambda-5$lambda-4$lambda-2  reason: not valid java name */
    public static final Pair m105getPlayPath$lambda5$lambda4$lambda2(String id, int i, BaseMediaModel model, BaseMediaModel it) {
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
    public static final Pair m106getPlayPath$lambda5$lambda4$lambda3(String id, Ref.IntRef season, Ref.IntRef episode, BaseMediaModel model, BaseMediaModel it) {
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
}
