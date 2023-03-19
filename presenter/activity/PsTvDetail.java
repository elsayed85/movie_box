package com.movieboxpro.android.presenter.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LifecycleOwner;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.ares.downloader.jarvis.core.RemoteFileUtil;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.app.RxManager;
import com.movieboxpro.android.base.BaseObserver;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.db.entity.Download;
import com.movieboxpro.android.db.entity.PlayRecode;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.livedata.RefreshWaitingLiveData;
import com.movieboxpro.android.livedata.RefreshWatchedLiveData;
import com.movieboxpro.android.model.ActorModel;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.BaseResponse;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.common.Feedback;
import com.movieboxpro.android.model.detail.AbstractVideoBean;
import com.movieboxpro.android.model.detail.TvBean;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.presenter.IPresenter;
import com.movieboxpro.android.service.UploadErrorInfoService;
import com.movieboxpro.android.utils.ApiUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.detail.ITvDetail;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* loaded from: classes3.dex */
public class PsTvDetail implements IPresenter {
    private static final String TAG = "PsTvDetail";
    private AbstractVideoBean abstractVideoBean = new TvBean();
    private Disposable disposable;
    private Context mContext;
    private ITvDetail mViewController;

    @Override // com.movieboxpro.android.presenter.IPresenter
    public void loadData() {
    }

    public void markWatch() {
    }

    public PsTvDetail(Context context, ITvDetail iTvDetail) {
        this.mContext = context;
        this.mViewController = iTvDetail;
    }

    @Override // com.movieboxpro.android.presenter.IPresenter
    public void detachView() {
        this.mContext = null;
        this.mViewController = null;
        RxManager.remove(TAG);
    }

    public void cancelRequest() {
        Disposable disposable = this.disposable;
        if (disposable != null) {
            disposable.dispose();
        }
    }

    public void cancelWatch(LifecycleOwner lifecycleOwner, String str) {
        HttpRequest.post(lifecycleOwner, "User_watch_plan_del").param("mid", str).param("box_type", (Object) 2).asMsg().subscribe(new Observer<String>() { // from class: com.movieboxpro.android.presenter.activity.PsTvDetail.1
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                PsTvDetail.this.mViewController.showLoading();
            }

            @Override // io.reactivex.Observer
            public void onNext(String str2) {
                PsTvDetail.this.mViewController.hideLoading();
                PsTvDetail.this.mViewController.onWatchedChanged(-1);
                RefreshWatchedLiveData.Companion.get().setValue(true);
                RefreshWaitingLiveData.Companion.get().setValue(true);
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                PsTvDetail.this.mViewController.hideLoading();
                ToastUtils.showShort("Load failed:" + th.getMessage());
            }
        });
    }

    public void markWatched(LifecycleOwner lifecycleOwner, String str, final int i) {
        ((ObservableSubscribeProxy) HttpRequest.post("User_watch_plan_add").param("box_type", (Object) 2).param("mid", str).param("watched", Integer.valueOf(i)).asRequest().compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(lifecycleOwner))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.presenter.activity.PsTvDetail.2
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                PsTvDetail.this.mViewController.showLoading();
            }

            @Override // io.reactivex.Observer
            public void onNext(String str2) {
                PsTvDetail.this.mViewController.hideLoading();
                PsTvDetail.this.mViewController.onWatchedChanged(i);
                RefreshWatchedLiveData.Companion.get().setValue(true);
                RefreshWaitingLiveData.Companion.get().setValue(true);
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                PsTvDetail.this.mViewController.hideLoading();
                ToastUtils.showShort("Load failed:" + th.getMessage());
            }
        });
    }

    public void changeWatched(LifecycleOwner lifecycleOwner, String str, final int i) {
        ((ObservableSubscribeProxy) HttpRequest.post("User_watch_plan_mark").param("box_type", (Object) 2).param("mid", str).param("watched", Integer.valueOf(i)).asRequest().compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(lifecycleOwner))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.presenter.activity.PsTvDetail.3
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                PsTvDetail.this.mViewController.showLoading();
            }

            @Override // io.reactivex.Observer
            public void onNext(String str2) {
                PsTvDetail.this.mViewController.hideLoading();
                PsTvDetail.this.mViewController.onWatchedChanged(i);
                RefreshWatchedLiveData.Companion.get().setValue(true);
                RefreshWaitingLiveData.Companion.get().setValue(true);
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                PsTvDetail.this.mViewController.hideLoading();
                ToastUtils.showShort("Load failed:" + th.getMessage());
            }
        });
    }

    public void getInfo(final String str, String str2) {
        ITvDetail iTvDetail = this.mViewController;
        if (iTvDetail == null || this.abstractVideoBean == null) {
            return;
        }
        iTvDetail.switchPlayButtonStage(0);
        this.abstractVideoBean.getDetailInfo(str, str2).subscribeOn(Schedulers.io()).compose(RxUtils.rxTranslate2Bean(TvDetail.class)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpResponseObserver<TvDetail>() { // from class: com.movieboxpro.android.presenter.activity.PsTvDetail.4
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                RxManager.addDisposable(PsTvDetail.TAG, disposable);
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(TvDetail tvDetail) {
                if (PsTvDetail.this.mViewController == null) {
                    return;
                }
                PsTvDetail.this.mViewController.getVideoInfo(tvDetail);
                PsTvDetail.this.mViewController.switchPlayButtonStage(1);
                PsTvDetail.this.mViewController.switchPlayButtonStage(1);
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onException(Throwable th) {
                UploadErrorInfoService.Companion.startUploadService(App.getContext(), th, str, "TV_detail_1", 2, "获取电视详情");
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException apiException) {
                if (PsTvDetail.this.mViewController == null) {
                    return;
                }
                PsTvDetail.this.mViewController.switchPlayButtonStage(2);
            }
        });
        getReviewNum(str);
    }

    public void getShareLink(final Activity activity, String str) {
        HttpRequest.post("Share_url").param(IjkMediaMeta.IJKM_KEY_TYPE, "tv").param("id", str).asRequest().compose(RxUtils.rxTranslate2Bean(HashMap.class)).compose(RxUtils.rxSchedulerHelper()).subscribe(new HttpResponseObserver<HashMap>() { // from class: com.movieboxpro.android.presenter.activity.PsTvDetail.5
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                if (PsTvDetail.this.mViewController != null) {
                    PsTvDetail.this.mViewController.showLoading();
                }
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(HashMap hashMap) {
                if (PsTvDetail.this.mViewController != null) {
                    PsTvDetail.this.mViewController.hideLoading();
                }
                Intent intent = new Intent();
                intent.setAction("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.TEXT", (String) hashMap.get("url"));
                try {
                    activity.startActivity(Intent.createChooser(intent, "Share the detail link"));
                } catch (Exception unused) {
                    ToastUtils.showShort("Share failed,the link is copied");
                }
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException apiException) {
                if (PsTvDetail.this.mViewController != null) {
                    PsTvDetail.this.mViewController.hideLoading();
                }
                ToastUtils.showShort("Share failed:" + apiException.getMessage());
            }
        });
    }

    public void getReviewNum(String str) {
        HttpRequest.post(ApiUtils.INSTANCE.reviewNum(2)).param("box_type", (Object) 2).param("mid", str).asRequest().compose(RxUtils.rxTranslate2Bean(HashMap.class)).compose(RxUtils.rxSchedulerHelper()).subscribe(new Observer<HashMap>() { // from class: com.movieboxpro.android.presenter.activity.PsTvDetail.6
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                RxManager.addDisposable(PsTvDetail.TAG, disposable);
            }

            @Override // io.reactivex.Observer
            public void onNext(HashMap hashMap) {
                if (PsTvDetail.this.mViewController == null) {
                    return;
                }
                PsTvDetail.this.mViewController.showReviewCount(String.valueOf(((Integer) hashMap.get("count")).intValue()));
            }
        });
    }

    public void getVideoPoster(String str, String str2) {
        this.abstractVideoBean.getDetailInfo(str, str2).subscribeOn(Schedulers.io()).compose(RxUtils.rxTranslate2Bean(TvDetail.class)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<TvDetail>() { // from class: com.movieboxpro.android.presenter.activity.PsTvDetail.7
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                RxManager.addDisposable(PsTvDetail.TAG, disposable);
                if (PsTvDetail.this.mViewController != null) {
                    PsTvDetail.this.mViewController.showLoading();
                }
            }

            @Override // io.reactivex.Observer
            public void onNext(TvDetail tvDetail) {
                if (PsTvDetail.this.mViewController != null) {
                    PsTvDetail.this.mViewController.showPoster(tvDetail.poster);
                    PsTvDetail.this.mViewController.hideLoading();
                }
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                ToastUtils.showShort("Load failed:" + th.getMessage());
                if (PsTvDetail.this.mViewController != null) {
                    PsTvDetail.this.mViewController.hideLoading();
                }
            }
        });
    }

    public void requestActors(String str) {
        this.abstractVideoBean.actorList(str).compose(RxUtils.rxTranslate2List(ActorModel.class)).compose(RxUtils.rxSchedulerHelper()).subscribe(new BaseObserver<List<ActorModel>>() { // from class: com.movieboxpro.android.presenter.activity.PsTvDetail.8
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                RxManager.addDisposable(PsTvDetail.TAG, disposable);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(List<ActorModel> list) {
                if (PsTvDetail.this.mViewController != null) {
                    PsTvDetail.this.mViewController.showActors(list);
                }
            }
        });
    }

    public void SaveInDao(TvDetail tvDetail, int i, int i2, int i3) {
        PlayRecode findByEpisode = App.getDB().playRecodeDao().findByEpisode(tvDetail.box_type, tvDetail.id, i2, i3);
        if (findByEpisode == null) {
            PlayRecode playRecode = new PlayRecode();
            playRecode.setMovieId(tvDetail.id);
            playRecode.setBox_type(tvDetail.box_type);
            playRecode.setImdb_id(tvDetail.imdb_id);
            playRecode.setTitle(tvDetail.title);
            playRecode.setQuality(-1);
            playRecode.setStart_time(i * 1000);
            playRecode.setSeason(i2);
            playRecode.setEpisode(i3);
            App.getDB().playRecodeDao().insert(playRecode);
            return;
        }
        findByEpisode.setStart_time(i * 1000);
        App.getDB().playRecodeDao().update(findByEpisode);
    }

    public void addFavorite(String str, final boolean z) {
        ITvDetail iTvDetail;
        if (App.isLogin() && (iTvDetail = this.mViewController) != null) {
            iTvDetail.showLoading();
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            JSONArray parseArray = JSONArray.parseArray(JSON.toJSONString(arrayList));
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("tid", (Object) parseArray);
            this.abstractVideoBean.addFavorite(str, jSONObject, z).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.presenter.activity.PsTvDetail.9
                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable disposable) {
                    RxManager.addDisposable(PsTvDetail.TAG, disposable);
                }

                @Override // io.reactivex.Observer
                public void onNext(String str2) {
                    if (PsTvDetail.this.mViewController == null) {
                        return;
                    }
                    if (((JSONObject) JSONObject.parse(str2)).getInteger("code").intValue() == 1) {
                        PsTvDetail.this.mViewController.setFavorite(true ^ z);
                    } else {
                        PsTvDetail.this.mViewController.setFavorite(z);
                    }
                }

                @Override // io.reactivex.Observer
                public void onError(Throwable th) {
                    if (PsTvDetail.this.mViewController == null) {
                        return;
                    }
                    PsTvDetail.this.mViewController.hideLoading();
                }

                @Override // io.reactivex.Observer
                public void onComplete() {
                    if (PsTvDetail.this.mViewController == null) {
                        return;
                    }
                    PsTvDetail.this.mViewController.hideLoading();
                }
            });
        }
    }

    public void getFeedBack() {
        this.abstractVideoBean.getFeedBackType(1).subscribeOn(Schedulers.io()).map(new Function<String, List<Feedback>>() { // from class: com.movieboxpro.android.presenter.activity.PsTvDetail.11
            @Override // io.reactivex.functions.Function
            public List<Feedback> apply(String str) throws Exception {
                JSONObject jSONObject = (JSONObject) JSONObject.parse(str);
                if (jSONObject.getInteger("code").intValue() == 1) {
                    return jSONObject.getJSONArray("data").toJavaList(Feedback.class);
                }
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<Feedback>>() { // from class: com.movieboxpro.android.presenter.activity.PsTvDetail.10
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                RxManager.addDisposable(PsTvDetail.TAG, disposable);
            }

            @Override // io.reactivex.Observer
            public void onNext(List<Feedback> list) {
                if (PsTvDetail.this.mViewController == null || list == null) {
                    return;
                }
                PsTvDetail.this.mViewController.getFeedBack(list);
            }
        });
    }

    public void setFeedBack(int i, int i2, String str, String str2, int i3, String str3, int i4, int i5) {
        this.mViewController.showLoading();
        this.abstractVideoBean.setFeedBack(i, i2, str, str2, i3, str3, i4, i5).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.presenter.activity.PsTvDetail.12
            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                RxManager.addDisposable(PsTvDetail.TAG, disposable);
            }

            @Override // io.reactivex.Observer
            public void onNext(String str4) {
                if (PsTvDetail.this.mViewController == null) {
                    return;
                }
                PsTvDetail.this.mViewController.showToast(((JSONObject) JSONObject.parse(str4)).getString(NotificationCompat.CATEGORY_MESSAGE));
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                if (PsTvDetail.this.mViewController == null) {
                    return;
                }
                PsTvDetail.this.mViewController.hideLoading();
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                if (PsTvDetail.this.mViewController == null) {
                    return;
                }
                PsTvDetail.this.mViewController.hideLoading();
            }
        });
    }

    public void getPath(final String str, final int i, final int i2, final int i3, final boolean z) {
        Http.getService().playingFeedback(API.BASE_URL, API.Common.PLAYING_FEEDBACK, App.getUserData().uid_v2, str, SystemUtils.getUniqueId(App.getContext()), 1, i2, i, Build.BRAND, Build.MODEL).flatMap(new Function<String, ObservableSource<?>>() { // from class: com.movieboxpro.android.presenter.activity.PsTvDetail.16
            @Override // io.reactivex.functions.Function
            public ObservableSource<?> apply(String str2) throws Exception {
                BaseResponse baseResponse = (BaseResponse) JSON.parseObject(str2, RxUtils.buildType(BaseResponse.class, DeviceModelResponse.class), new Feature[0]);
                if (baseResponse.getCode() == -88) {
                    if (PsTvDetail.this.mViewController != null) {
                        PsTvDetail.this.mViewController.hideLoading();
                        PsTvDetail.this.mViewController.showScreenManage(str, new ArrayList<>(((DeviceModelResponse) baseResponse.getData()).getDevice_list()), baseResponse.getMsg(), i2, i, i3, z);
                    }
                    return Observable.empty();
                }
                return Observable.just("");
            }
        }).onErrorResumeNext(new Function<Throwable, ObservableSource<?>>() { // from class: com.movieboxpro.android.presenter.activity.PsTvDetail.15
            @Override // io.reactivex.functions.Function
            public ObservableSource<?> apply(Throwable th) throws Exception {
                return Observable.just("");
            }
        }).flatMap(new Function<Object, ObservableSource<TvDetail>>() { // from class: com.movieboxpro.android.presenter.activity.PsTvDetail.14
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.reactivex.functions.Function
            public ObservableSource<TvDetail> apply(Object obj) throws Exception {
                return PsTvDetail.this.abstractVideoBean.getPath(str, i2, i).compose(RxUtils.rxTranslate2Bean(TvDetail.class)).map(new Function<TvDetail, TvDetail>() { // from class: com.movieboxpro.android.presenter.activity.PsTvDetail.14.1
                    @Override // io.reactivex.functions.Function
                    public TvDetail apply(TvDetail tvDetail) throws Exception {
                        Download findByTidAndSeasonEpisode = App.getDB().downloadDao().findByTidAndSeasonEpisode(str, i2, i);
                        if (findByTidAndSeasonEpisode != null && findByTidAndSeasonEpisode.getStatue() == 2) {
                            findByTidAndSeasonEpisode.setPath(Constant.DIR_DOWNLOAD + File.separator + RemoteFileUtil.getFileName(findByTidAndSeasonEpisode.getPath(), findByTidAndSeasonEpisode.getTitle(), Constant.DIR_DOWNLOAD));
                            if (new File(findByTidAndSeasonEpisode.getPath()).exists()) {
                                BaseMediaModel.DownloadFile downloadFile = (BaseMediaModel.DownloadFile) JSONObject.parseObject(JSONObject.toJSONString(findByTidAndSeasonEpisode), BaseMediaModel.DownloadFile.class);
                                downloadFile.real_quality = findByTidAndSeasonEpisode.getQuality();
                                tvDetail.list.add(0, downloadFile);
                            }
                        }
                        return tvDetail;
                    }
                });
            }
        }).compose(RxUtils.rxSchedulerHelper()).subscribe(new HttpResponseObserver<TvDetail>() { // from class: com.movieboxpro.android.presenter.activity.PsTvDetail.13
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                RxManager.addDisposable(PsTvDetail.TAG, disposable);
                PsTvDetail.this.disposable = disposable;
                if (PsTvDetail.this.mViewController != null) {
                    PsTvDetail.this.mViewController.showLoading();
                }
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(TvDetail tvDetail) {
                if (PsTvDetail.this.mViewController == null) {
                    return;
                }
                PsTvDetail.this.mViewController.ChoosePlayer(tvDetail, i3, i, z);
                PsTvDetail.this.mViewController.hideLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onException(Throwable th) {
                UploadErrorInfoService.Companion.startUploadService(App.getContext(), th, str, API.Tv.TV_DOWNLAODURL, 2, "play", i2, i);
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException apiException) {
                if (PsTvDetail.this.mViewController == null) {
                    return;
                }
                ToastUtils.showShort("Load failed:" + apiException.getMessage());
                PsTvDetail.this.mViewController.hideLoading();
            }
        });
    }

    public void getPlayPath(final String str, final int i, final int i2, final boolean z) {
        this.abstractVideoBean.TV_episode(str, i2, "", App.getUserData().uid_v2).compose(RxUtils.rxTranslate2List(TvDetail.SeasonDetail.class)).flatMap(new Function() { // from class: com.movieboxpro.android.presenter.activity.-$$Lambda$PsTvDetail$su4p5DFMnyCpPv088ziermVe1_4
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return PsTvDetail.this.lambda$getPlayPath$1$PsTvDetail(str, i2, i, (List) obj);
            }
        }).compose(RxUtils.rxSchedulerHelper()).subscribe(new HttpResponseObserver<TvDetail>() { // from class: com.movieboxpro.android.presenter.activity.PsTvDetail.17
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                RxManager.addDisposable(PsTvDetail.TAG, disposable);
                if (PsTvDetail.this.mViewController != null) {
                    PsTvDetail.this.mViewController.showLoading();
                }
                PsTvDetail.this.disposable = disposable;
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(TvDetail tvDetail) {
                if (PsTvDetail.this.mViewController == null) {
                    return;
                }
                PsTvDetail.this.mViewController.goTvPlayer(tvDetail, i, z);
                PsTvDetail.this.mViewController.hideLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onException(Throwable th) {
                UploadErrorInfoService.Companion.startUploadService(App.getContext(), th, str, API.Tv.TV_DOWNLAODURL, 2, "play", i2, i);
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException apiException) {
                if (PsTvDetail.this.mViewController == null) {
                    return;
                }
                ToastUtils.showShort("Load failed:" + apiException.getMessage());
                PsTvDetail.this.mViewController.hideLoading();
            }
        });
    }

    public /* synthetic */ ObservableSource lambda$getPlayPath$1$PsTvDetail(final String str, final int i, final int i2, final List list) throws Exception {
        return this.abstractVideoBean.getPath(str, i, i2).compose(RxUtils.rxTranslate2Bean(TvDetail.class)).map(new Function() { // from class: com.movieboxpro.android.presenter.activity.-$$Lambda$PsTvDetail$jjfOASOJnfRnGll36FbXixEiSg0
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return PsTvDetail.lambda$getPlayPath$0(str, i, i2, list, (TvDetail) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ TvDetail lambda$getPlayPath$0(String str, int i, int i2, List list, TvDetail tvDetail) throws Exception {
        Download findByTidAndSeasonEpisode = App.getDB().downloadDao().findByTidAndSeasonEpisode(str, i, i2);
        if (findByTidAndSeasonEpisode != null && findByTidAndSeasonEpisode.getStatue() == 2) {
            findByTidAndSeasonEpisode.setPath(Constant.DIR_DOWNLOAD + File.separator + RemoteFileUtil.getFileName(findByTidAndSeasonEpisode.getPath(), findByTidAndSeasonEpisode.getTitle(), Constant.DIR_DOWNLOAD));
            if (new File(findByTidAndSeasonEpisode.getPath()).exists()) {
                BaseMediaModel.DownloadFile downloadFile = (BaseMediaModel.DownloadFile) JSONObject.parseObject(JSONObject.toJSONString(findByTidAndSeasonEpisode), BaseMediaModel.DownloadFile.class);
                downloadFile.real_quality = findByTidAndSeasonEpisode.getQuality();
                tvDetail.list.add(0, downloadFile);
            }
        }
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            TvDetail.SeasonDetail seasonDetail = (TvDetail.SeasonDetail) it.next();
            if (seasonDetail.episode == i2) {
                tvDetail.seasonDetail = seasonDetail;
                break;
            }
        }
        tvDetail.episode = list;
        return tvDetail;
    }

    public void getSeason(String str, int i, String str2, final int i2, String str3) {
        this.abstractVideoBean.TV_episode(str, i, str2, str3).subscribeOn(Schedulers.io()).map(new Function<String, List<TvDetail.SeasonDetail>>() { // from class: com.movieboxpro.android.presenter.activity.PsTvDetail.19
            @Override // io.reactivex.functions.Function
            public List<TvDetail.SeasonDetail> apply(String str4) throws Exception {
                JSONObject jSONObject = (JSONObject) JSONObject.parse(str4);
                if (jSONObject.getInteger("code").intValue() == 1) {
                    return jSONObject.getJSONArray("data").toJavaList(TvDetail.SeasonDetail.class);
                }
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<TvDetail.SeasonDetail>>() { // from class: com.movieboxpro.android.presenter.activity.PsTvDetail.18
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                RxManager.addDisposable(PsTvDetail.TAG, disposable);
            }

            @Override // io.reactivex.Observer
            public void onNext(List<TvDetail.SeasonDetail> list) {
                if (list == null || PsTvDetail.this.mViewController == null) {
                    return;
                }
                int i3 = i2;
                if (i3 == 0) {
                    PsTvDetail.this.mViewController.setDateList(list);
                } else if (i3 != 1) {
                } else {
                    PsTvDetail.this.mViewController.getProblem(list);
                }
            }
        });
    }

    public void addWatchedFlag(String str, int i, String str2, String str3) {
        Http.getService().AddWatchedFlag(API.BASE_URL, API.Tv.TV_over_v2, App.getUserData().uid_v2, i, str, str2, str3).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).subscribe(new BaseObserver<String>() { // from class: com.movieboxpro.android.presenter.activity.PsTvDetail.20
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                RxManager.addDisposable(PsTvDetail.TAG, disposable);
                if (PsTvDetail.this.mViewController != null) {
                    PsTvDetail.this.mViewController.showLoading();
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(String str4) {
                ToastUtils.showShort("Mark successfully");
                if (PsTvDetail.this.mViewController != null) {
                    PsTvDetail.this.mViewController.markWatchedComplete();
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                if (PsTvDetail.this.mViewController != null) {
                    PsTvDetail.this.mViewController.hideLoading();
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                if (PsTvDetail.this.mViewController != null) {
                    PsTvDetail.this.mViewController.hideLoading();
                }
                ToastUtils.showShort("Mark failed:" + apiException.getMessage());
            }
        });
    }

    public void getMovieList(String str) {
        this.abstractVideoBean.Playlists_list(str).compose(RxUtils.rxTranslate2List(MovieListModel.MovieListItem.class)).compose(RxUtils.rxSchedulerHelper()).subscribe(new BaseObserver<List<MovieListModel.MovieListItem>>() { // from class: com.movieboxpro.android.presenter.activity.PsTvDetail.21
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                RxManager.addDisposable(PsTvDetail.TAG, disposable);
                if (PsTvDetail.this.mViewController != null) {
                    PsTvDetail.this.mViewController.showLoading();
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(List<MovieListModel.MovieListItem> list) {
                super.onNext((AnonymousClass21) list);
                if (PsTvDetail.this.mViewController != null) {
                    PsTvDetail.this.mViewController.showBottomMovieListDialog(list);
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                if (PsTvDetail.this.mViewController != null) {
                    PsTvDetail.this.mViewController.hideLoading();
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                if (PsTvDetail.this.mViewController != null) {
                    PsTvDetail.this.mViewController.hideLoading();
                }
            }
        });
    }
}
