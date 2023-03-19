package com.movieboxpro.android.presenter.activity;

import android.app.Activity;
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
import com.movieboxpro.android.model.ActorModel;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.BaseResponse;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.common.Feedback;
import com.movieboxpro.android.model.detail.MovieBean;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.service.UploadErrorInfoService;
import com.movieboxpro.android.utils.ApiUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.detail.IMovieDetail;
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
import java.util.List;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* loaded from: classes3.dex */
public class PsMovieDetail extends PSVideoInfo {
    private static final String TAG = "PsMovieDetail";
    private Disposable disposable;

    public PsMovieDetail(IMovieDetail iMovieDetail) {
        super(iMovieDetail);
        this.abstractVideoBean = new MovieBean();
    }

    public void cancelWatch(LifecycleOwner lifecycleOwner, String str) {
        HttpRequest.post(lifecycleOwner, "User_watch_plan_del").param("mid", str).param("box_type", (Object) 1).asMsg().subscribe(new Observer<String>() { // from class: com.movieboxpro.android.presenter.activity.PsMovieDetail.1
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                PsMovieDetail.this.mViewController.showLoading();
            }

            @Override // io.reactivex.Observer
            public void onNext(String str2) {
                PsMovieDetail.this.mViewController.hideLoading();
                PsMovieDetail.this.mViewController.onWatchedChanged(-1);
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                PsMovieDetail.this.mViewController.hideLoading();
                ToastUtils.showShort("Load failed:" + th.getMessage());
            }
        });
    }

    public void markWatched(LifecycleOwner lifecycleOwner, String str, final int i) {
        ((ObservableSubscribeProxy) HttpRequest.post("User_watch_plan_add").param("box_type", (Object) 1).param("mid", str).param("watched", Integer.valueOf(i)).asRequest().compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(lifecycleOwner))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.presenter.activity.PsMovieDetail.2
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                PsMovieDetail.this.mViewController.showLoading();
            }

            @Override // io.reactivex.Observer
            public void onNext(String str2) {
                PsMovieDetail.this.mViewController.hideLoading();
                PsMovieDetail.this.mViewController.onWatchedChanged(i);
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                PsMovieDetail.this.mViewController.hideLoading();
                ToastUtils.showShort("Load failed:" + th.getMessage());
            }
        });
    }

    public void changeWatched(LifecycleOwner lifecycleOwner, String str, final int i) {
        ((ObservableSubscribeProxy) HttpRequest.post("User_watch_plan_mark").param("box_type", (Object) 1).param("mid", str).param("watched", Integer.valueOf(i)).asRequest().compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(lifecycleOwner))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.presenter.activity.PsMovieDetail.3
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                PsMovieDetail.this.mViewController.showLoading();
            }

            @Override // io.reactivex.Observer
            public void onNext(String str2) {
                PsMovieDetail.this.mViewController.hideLoading();
                PsMovieDetail.this.mViewController.onWatchedChanged(i);
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                PsMovieDetail.this.mViewController.hideLoading();
                ToastUtils.showShort("Load failed:" + th.getMessage());
            }
        });
    }

    @Override // com.movieboxpro.android.presenter.activity.PSVideoInfo
    public void getInfo(final String str, String str2) {
        if (this.abstractVideoBean == null || this.mViewController == null) {
            return;
        }
        this.mViewController.switchPlayButtonStage(0);
        this.abstractVideoBean.getDetailInfo(str, str2).map(new Function<String, MovieDetail>() { // from class: com.movieboxpro.android.presenter.activity.PsMovieDetail.5
            @Override // io.reactivex.functions.Function
            public MovieDetail apply(String str3) throws Exception {
                JSONObject jSONObject = (JSONObject) JSONObject.parse(str3);
                if (jSONObject.getInteger("code").intValue() == 1) {
                    return (MovieDetail) jSONObject.getJSONObject("data").toJavaObject((Class<Object>) MovieDetail.class);
                }
                return null;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpResponseObserver<MovieDetail>() { // from class: com.movieboxpro.android.presenter.activity.PsMovieDetail.4
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                RxManager.addDisposable(PsMovieDetail.TAG, disposable);
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(MovieDetail movieDetail) {
                if (PsMovieDetail.this.mViewController == null) {
                    return;
                }
                PsMovieDetail.this.mViewController.getVideoInfo(movieDetail);
                PsMovieDetail.this.mViewController.switchPlayButtonStage(1);
                PsMovieDetail.this.mViewController.switchPlayButtonStage(1);
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onException(Throwable th) {
                UploadErrorInfoService.Companion.startUploadService(App.getContext(), th, str, API.Movie.MOVE_DETAIL, 1, "获取电影详情");
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException apiException) {
                if (PsMovieDetail.this.mViewController == null) {
                    return;
                }
                ToastUtils.showShort("Load failed:" + apiException.getMessage());
                PsMovieDetail.this.mViewController.switchPlayButtonStage(2);
            }
        });
    }

    public void getShareLink(final Activity activity, String str) {
        HttpRequest.post("Share_url").param(IjkMediaMeta.IJKM_KEY_TYPE, "movie").param("id", str).asRequest().compose(RxUtils.rxTranslate2Bean(HashMap.class)).compose(RxUtils.rxSchedulerHelper()).subscribe(new HttpResponseObserver<HashMap>() { // from class: com.movieboxpro.android.presenter.activity.PsMovieDetail.6
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                if (PsMovieDetail.this.mViewController != null) {
                    PsMovieDetail.this.mViewController.showLoading();
                }
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(HashMap hashMap) {
                if (PsMovieDetail.this.mViewController != null) {
                    PsMovieDetail.this.mViewController.hideLoading();
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
                if (PsMovieDetail.this.mViewController != null) {
                    PsMovieDetail.this.mViewController.hideLoading();
                }
                ToastUtils.showShort("Share failed:" + apiException.getMessage());
            }
        });
    }

    public void getReviewNum(String str, boolean z) {
        HttpRequest.post(ApiUtils.INSTANCE.reviewNum(1)).param("box_type", (Object) 1).param("mid", str).asRequest().compose(RxUtils.rxTranslate2Bean(HashMap.class)).compose(RxUtils.rxSchedulerHelper()).subscribe(new Observer<HashMap>() { // from class: com.movieboxpro.android.presenter.activity.PsMovieDetail.7
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                RxManager.addDisposable(PsMovieDetail.TAG, disposable);
            }

            @Override // io.reactivex.Observer
            public void onNext(HashMap hashMap) {
                if (PsMovieDetail.this.mViewController == null) {
                    return;
                }
                PsMovieDetail.this.mViewController.showReviewCount(String.valueOf(((Integer) hashMap.get("count")).intValue()));
            }
        });
    }

    public void getVideoPoster(String str, String str2) {
        this.abstractVideoBean.getDetailInfo(str, str2).compose(RxUtils.rxTranslate2Bean(MovieDetail.class)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<MovieDetail>() { // from class: com.movieboxpro.android.presenter.activity.PsMovieDetail.8
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                RxManager.addDisposable(PsMovieDetail.TAG, disposable);
                if (PsMovieDetail.this.mViewController != null) {
                    PsMovieDetail.this.mViewController.showLoading();
                }
            }

            @Override // io.reactivex.Observer
            public void onNext(MovieDetail movieDetail) {
                if (PsMovieDetail.this.mViewController != null) {
                    PsMovieDetail.this.mViewController.showPoster(movieDetail.poster);
                    PsMovieDetail.this.mViewController.hideLoading();
                }
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                ToastUtils.showShort("Load failed:" + th.getMessage());
                if (PsMovieDetail.this.mViewController != null) {
                    PsMovieDetail.this.mViewController.hideLoading();
                }
            }
        });
    }

    public void requestActors(String str) {
        this.abstractVideoBean.actorList(str).compose(RxUtils.rxTranslate2List(ActorModel.class)).compose(RxUtils.rxSchedulerHelper()).subscribe(new BaseObserver<List<ActorModel>>() { // from class: com.movieboxpro.android.presenter.activity.PsMovieDetail.9
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                RxManager.addDisposable(PsMovieDetail.TAG, disposable);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(List<ActorModel> list) {
                if (PsMovieDetail.this.mViewController != null) {
                    PsMovieDetail.this.mViewController.showActors(list);
                }
            }
        });
    }

    public void SaveInDao(MovieDetail movieDetail) {
        PlayRecode findByTypeid = App.getDB().playRecodeDao().findByTypeid(movieDetail.box_type, movieDetail.id);
        if (findByTypeid == null) {
            PlayRecode playRecode = new PlayRecode();
            playRecode.setMovieId(movieDetail.id);
            playRecode.setBox_type(movieDetail.box_type);
            playRecode.setImdb_id(movieDetail.imdb_id);
            playRecode.setTitle(movieDetail.title);
            playRecode.setQuality(-1);
            playRecode.setStart_time(movieDetail.seconds * 1000);
            playRecode.setSeason(1);
            playRecode.setEpisode(1);
            App.getDB().playRecodeDao().insert(playRecode);
            return;
        }
        findByTypeid.setStart_time(movieDetail.seconds * 1000);
        App.getDB().playRecodeDao().update(findByTypeid);
    }

    public void addFavorite(String str, final boolean z) {
        if (this.mViewController == null) {
            return;
        }
        this.mViewController.showLoading();
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        JSONArray parseArray = JSONArray.parseArray(JSON.toJSONString(arrayList));
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("mid", (Object) parseArray);
        this.abstractVideoBean.addFavorite(str, jSONObject, z).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.presenter.activity.PsMovieDetail.10
            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                RxManager.addDisposable(PsMovieDetail.TAG, disposable);
            }

            @Override // io.reactivex.Observer
            public void onNext(String str2) {
                if (PsMovieDetail.this.mViewController == null) {
                    return;
                }
                if (((JSONObject) JSONObject.parse(str2)).getInteger("code").intValue() == 1) {
                    PsMovieDetail.this.mViewController.setFavorite(true ^ z);
                } else {
                    PsMovieDetail.this.mViewController.setFavorite(z);
                }
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                if (PsMovieDetail.this.mViewController == null) {
                    return;
                }
                PsMovieDetail.this.mViewController.hideLoading();
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                if (PsMovieDetail.this.mViewController == null) {
                    return;
                }
                PsMovieDetail.this.mViewController.hideLoading();
            }
        });
    }

    public void cancelRequest() {
        Disposable disposable = this.disposable;
        if (disposable != null) {
            disposable.dispose();
        }
    }

    public void getPath(final String str) {
        Http.getService().playingFeedback(API.BASE_URL, API.Common.PLAYING_FEEDBACK, App.getUserData().uid_v2, str, SystemUtils.getUniqueId(App.getContext()), 1, 0, 0, Build.BRAND, Build.MODEL).flatMap(new Function<String, ObservableSource<?>>() { // from class: com.movieboxpro.android.presenter.activity.PsMovieDetail.14
            @Override // io.reactivex.functions.Function
            public ObservableSource<?> apply(String str2) throws Exception {
                BaseResponse baseResponse = (BaseResponse) JSON.parseObject(str2, RxUtils.buildType(BaseResponse.class, DeviceModelResponse.class), new Feature[0]);
                if (baseResponse.getCode() == -88) {
                    if (PsMovieDetail.this.mViewController != null) {
                        PsMovieDetail.this.mViewController.hideLoading();
                        PsMovieDetail.this.mViewController.showScreenManage(str, new ArrayList<>(((DeviceModelResponse) baseResponse.getData()).getDevice_list()), baseResponse.getMsg());
                    }
                    return Observable.empty();
                }
                return Observable.just("");
            }
        }).onErrorResumeNext(new Function<Throwable, ObservableSource<?>>() { // from class: com.movieboxpro.android.presenter.activity.PsMovieDetail.13
            @Override // io.reactivex.functions.Function
            public ObservableSource<?> apply(Throwable th) throws Exception {
                return Observable.just("");
            }
        }).flatMap(new Function<Object, ObservableSource<MovieDetail>>() { // from class: com.movieboxpro.android.presenter.activity.PsMovieDetail.12
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.reactivex.functions.Function
            public ObservableSource<MovieDetail> apply(Object obj) throws Exception {
                return PsMovieDetail.this.abstractVideoBean.getPath(str, 0, 0).compose(RxUtils.rxTranslate2Bean(MovieDetail.class)).map(new Function<MovieDetail, MovieDetail>() { // from class: com.movieboxpro.android.presenter.activity.PsMovieDetail.12.1
                    @Override // io.reactivex.functions.Function
                    public MovieDetail apply(MovieDetail movieDetail) throws Exception {
                        Download findByIdAndType = App.getDB().downloadDao().findByIdAndType(str, 1, 2);
                        if (findByIdAndType != null && findByIdAndType.getStatue() == 2) {
                            findByIdAndType.setPath(Constant.DIR_DOWNLOAD + File.separator + RemoteFileUtil.getFileName(findByIdAndType.getPath(), findByIdAndType.getTitle(), Constant.DIR_DOWNLOAD));
                            if (new File(findByIdAndType.getPath()).exists()) {
                                BaseMediaModel.DownloadFile downloadFile = (BaseMediaModel.DownloadFile) JSONObject.parseObject(JSONObject.toJSONString(findByIdAndType), BaseMediaModel.DownloadFile.class);
                                downloadFile.real_quality = findByIdAndType.getQuality();
                                movieDetail.list.add(0, downloadFile);
                            }
                        }
                        return movieDetail;
                    }
                });
            }
        }).compose(RxUtils.rxSchedulerHelper()).subscribe(new HttpResponseObserver<MovieDetail>() { // from class: com.movieboxpro.android.presenter.activity.PsMovieDetail.11
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                RxManager.addDisposable(PsMovieDetail.TAG, disposable);
                PsMovieDetail.this.disposable = disposable;
                if (PsMovieDetail.this.mViewController != null) {
                    PsMovieDetail.this.mViewController.showLoading();
                }
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(MovieDetail movieDetail) {
                if (PsMovieDetail.this.mViewController == null) {
                    return;
                }
                if (movieDetail != null) {
                    PsMovieDetail.this.mViewController.ChoosePlayer(movieDetail);
                }
                if (PsMovieDetail.this.mViewController == null) {
                    return;
                }
                PsMovieDetail.this.mViewController.hideLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onException(Throwable th) {
                UploadErrorInfoService.Companion.startUploadService(App.getContext(), th, str, API.Movie.MOVE_DOWNLAOD, 1, "play");
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException apiException) {
                if (PsMovieDetail.this.mViewController == null) {
                    return;
                }
                ToastUtils.showShort("Load failed:code " + apiException.getCode() + apiException.getMessage());
                PsMovieDetail.this.mViewController.hideLoading();
            }
        });
    }

    public void getFeedBack() {
        this.abstractVideoBean.getFeedBackType(1).subscribeOn(Schedulers.io()).map(new Function<String, List<Feedback>>() { // from class: com.movieboxpro.android.presenter.activity.PsMovieDetail.16
            @Override // io.reactivex.functions.Function
            public List<Feedback> apply(String str) throws Exception {
                JSONObject jSONObject = (JSONObject) JSONObject.parse(str);
                if (jSONObject.getInteger("code").intValue() == 1) {
                    return jSONObject.getJSONArray("data").toJavaList(Feedback.class);
                }
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<Feedback>>() { // from class: com.movieboxpro.android.presenter.activity.PsMovieDetail.15
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                RxManager.addDisposable(PsMovieDetail.TAG, disposable);
            }

            @Override // io.reactivex.Observer
            public void onNext(List<Feedback> list) {
                if (PsMovieDetail.this.mViewController == null || list == null) {
                    return;
                }
                PsMovieDetail.this.mViewController.getFeedBack(list);
            }
        });
    }

    public void setFeedBack(int i, int i2, String str, String str2, int i3, String str3) {
        if (this.mViewController == null) {
            return;
        }
        this.mViewController.showLoading();
        this.abstractVideoBean.setFeedBack(i, i2, str, str2, i3, str3, 0, 0).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.presenter.activity.PsMovieDetail.17
            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                RxManager.addDisposable(PsMovieDetail.TAG, disposable);
            }

            @Override // io.reactivex.Observer
            public void onNext(String str4) {
                if (PsMovieDetail.this.mViewController == null) {
                    return;
                }
                PsMovieDetail.this.mViewController.showToast(((JSONObject) JSONObject.parse(str4)).getString(NotificationCompat.CATEGORY_MESSAGE));
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                if (PsMovieDetail.this.mViewController == null) {
                    return;
                }
                PsMovieDetail.this.mViewController.hideLoading();
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                if (PsMovieDetail.this.mViewController == null) {
                    return;
                }
                PsMovieDetail.this.mViewController.hideLoading();
            }
        });
    }

    public void getMovieList(String str) {
        this.abstractVideoBean.Playlists_list(str).compose(RxUtils.rxTranslate2List(MovieListModel.MovieListItem.class)).compose(RxUtils.rxSchedulerHelper()).subscribe(new BaseObserver<List<MovieListModel.MovieListItem>>() { // from class: com.movieboxpro.android.presenter.activity.PsMovieDetail.18
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                if (PsMovieDetail.this.mViewController != null) {
                    PsMovieDetail.this.mViewController.showLoading();
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(List<MovieListModel.MovieListItem> list) {
                super.onNext((AnonymousClass18) list);
                if (PsMovieDetail.this.mViewController != null) {
                    PsMovieDetail.this.mViewController.showBottomMovieListDialog(list);
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                if (PsMovieDetail.this.mViewController != null) {
                    PsMovieDetail.this.mViewController.hideLoading();
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                if (PsMovieDetail.this.mViewController != null) {
                    PsMovieDetail.this.mViewController.hideLoading();
                }
            }
        });
    }

    @Override // com.movieboxpro.android.presenter.activity.PSVideoInfo, com.movieboxpro.android.presenter.IPresenter
    public void detachView() {
        RxManager.remove(TAG);
        super.detachView();
    }
}
