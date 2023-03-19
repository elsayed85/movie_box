package com.movieboxpro.android.view.activity.videoplayer;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteFullException;
import android.text.TextUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dueeeke.model.ExtrModel;
import com.dueeeke.model.MediaQualityInfo;
import com.dueeeke.model.ResponseSubtitleRecord;
import com.dueeeke.model.SRTModel;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.BuildConfig;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.app.RxManager;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.db.entity.PlayRecode;
import com.movieboxpro.android.event.OnPlayFinishEvent;
import com.movieboxpro.android.event.RefreshPlayUrlEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.APIService;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.http.HttpUtils;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.MySubtitleResponse;
import com.movieboxpro.android.model.common.Srt;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.timroes.axmlrpc.XMLRPCClient;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.EventUtils;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.Video.VideoPlayerActivity;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* loaded from: classes.dex */
public class MoviePlayerActivity extends VideoActivityFactory {
    private boolean isComplete = false;

    @Override // com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory
    protected int boxType() {
        return 1;
    }

    public static void start(Context context, MovieDetail movieDetail, String str) {
        Intent intent = new Intent(context, MoviePlayerActivity.class);
        intent.putExtra("videoplayer_params", movieDetail);
        intent.putExtra(VideoPlayerActivity.VIDEO_ID, str);
        context.startActivity(intent);
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory, com.movieboxpro.android.view.activity.videoplayer.presenter.VideoPresenter
    public void _geiPrama() {
        CommonUtils.onEvent("WatchMovie");
        EventUtils.event("观看电影");
        this.movieDownload = (BaseMediaModel) getSerializable("videoplayer_params", new BaseMediaModel());
        if (getBooleanParam("FeaturedFragment", false)) {
            Intent intent = new Intent();
            intent.putExtra("id", this.movieDownload.id);
            setResult(-1, intent);
        }
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory, com.movieboxpro.android.view.activity.videoplayer.presenter.VideoPresenter
    public void getSrt(MediaQualityInfo mediaQualityInfo) {
        if (Network.isConnected(this.context)) {
            get_Srt();
            APIService aPIService = this.service;
            String str = API.BASE_URL;
            String str2 = App.isLogin() ? App.getUserData().uid_v2 : "";
            String str3 = this.movieDownload.id;
            ((ObservableSubscribeProxy) Observable.zip(aPIService.Movie_srt_list_v2(str, API.Movie.MOVIE_SRTLIST_V2, str2, str3, mediaQualityInfo.getFid() + "", Locale.getDefault().getLanguage(), BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslate2Bean(ResponseSubtitleRecord.class)).subscribeOn(Schedulers.io()), HttpRequest.post("My_srt").param("mid", this.movieDownload.id).param("box_type", Integer.valueOf(boxType())).param("season", Integer.valueOf(this.season)).param("episode", Integer.valueOf(this.episode)).asRequest().compose(RxUtils.rxTranslate2Bean(MySubtitleResponse.class)).subscribeOn(Schedulers.io()), new BiFunction<ResponseSubtitleRecord, MySubtitleResponse, ResponseSubtitleRecord>() { // from class: com.movieboxpro.android.view.activity.videoplayer.MoviePlayerActivity.2
                @Override // io.reactivex.functions.BiFunction
                public ResponseSubtitleRecord apply(ResponseSubtitleRecord responseSubtitleRecord, MySubtitleResponse mySubtitleResponse) throws Exception {
                    if (mySubtitleResponse.getSrt_list() != null && !mySubtitleResponse.getSrt_list().isEmpty()) {
                        SRTModel sRTModel = new SRTModel();
                        sRTModel.language = "My Subtitles";
                        sRTModel.subtitles = mySubtitleResponse.getSrt_list();
                        responseSubtitleRecord.getList().add(0, sRTModel);
                    }
                    return responseSubtitleRecord;
                }
            }).observeOn(AndroidSchedulers.mainThread()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<ResponseSubtitleRecord>() { // from class: com.movieboxpro.android.view.activity.videoplayer.MoviePlayerActivity.1
                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onError(ApiException apiException) {
                }

                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onStart(Disposable disposable) {
                }

                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onSuccess(ResponseSubtitleRecord responseSubtitleRecord) {
                    if (responseSubtitleRecord.getSelect() == null && PrefsUtils.getInstance().getBoolean(Constant.Prefs.AUTO_SELECT_SUBTITLE, false)) {
                        String lowerCase = App.deviceLang.toLowerCase(Locale.getDefault());
                        Iterator<SRTModel> it = responseSubtitleRecord.getList().iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            SRTModel next = it.next();
                            if (next.subtitles != null && next.subtitles.size() > 0 && next.subtitles.get(0).lang.contains(lowerCase)) {
                                responseSubtitleRecord.setSelect(next.subtitles.get(0));
                                next.subtitles.get(0).setMyselect(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
                                next.subtitles.get(0).setSelect(true);
                                break;
                            }
                        }
                        if (responseSubtitleRecord.getSelect() == null && responseSubtitleRecord.getList().size() > 0 && responseSubtitleRecord.getList().get(0).subtitles.size() > 0) {
                            responseSubtitleRecord.setSelect(responseSubtitleRecord.getList().get(0).subtitles.get(0));
                        }
                    }
                    for (SRTModel sRTModel : responseSubtitleRecord.getList()) {
                        MoviePlayerActivity.this.srtHash.put(sRTModel.language, sRTModel.subtitles);
                    }
                    if (MoviePlayerActivity.this.controller != null) {
                        MoviePlayerActivity.this.controller.setSrt(MoviePlayerActivity.this.srtHash);
                    }
                    if (responseSubtitleRecord.getSelect() != null) {
                        MoviePlayerActivity.this.controller.selectSubtitle(responseSubtitleRecord.getSelect());
                    }
                }
            });
        }
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory, com.movieboxpro.android.view.activity.videoplayer.presenter.VideoPresenter
    public void get_Movie_select(int i, int i2, String str) {
        int i3;
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.Incognito_Mode)) {
            return;
        }
        if (this.isComplete) {
            i3 = 1;
        } else if (i2 <= 0) {
            return;
        } else {
            i3 = 0;
        }
        int i4 = i2 < 0 ? 0 : i2;
        int i5 = i - i4 <= 180000 ? 1 : i3;
        APIService aPIService = this.service;
        String str2 = API.BASE_URL;
        String str3 = App.isLogin() ? App.getUserData().uid_v2 : "";
        String str4 = this.movieDownload.id;
        StringBuilder sb = new StringBuilder();
        int i6 = i4 / 1000;
        sb.append(i6);
        sb.append("");
        aPIService.Movie_play_progress2(str2, API.Movie.MOVIE_PLAY_PROGRESS, str3, str4, str, sb.toString(), i5, BuildConfig.VERSION_NAME).compose(RxUtils.rxSchedulerHelper()).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.videoplayer.MoviePlayerActivity.3
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onNext(String str5) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }
        });
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.Incognito_Mode, false)) {
            return;
        }
        APIService aPIService2 = this.service;
        String str5 = API.BASE_URL;
        String str6 = App.isLogin() ? App.getUserData().uid_v2 : "";
        String str7 = this.movieDownload != null ? this.movieDownload.id : "";
        aPIService2.Movie_play(str5, API.Movie.MOVIE_PLAY, str6, str7, i6 + "", str, SystemUtils.getUniqueId(this.context)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.videoplayer.MoviePlayerActivity.4
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(String str8) {
                EventBus.getDefault().post(new OnPlayFinishEvent());
            }
        });
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory
    protected void desSelectSubtitle(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ((ObservableSubscribeProxy) this.service.Movie_srt_select(API.BASE_URL, API.Movie.MOVIE_SRTSELECT, App.isLogin() ? App.getUserData().uid_v2 : "", str, this.movieDownload != null ? this.movieDownload.id : "", "del", BuildConfig.APPLICATION_ID).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.videoplayer.MoviePlayerActivity.5
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onNext(String str2) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }
        });
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory, com.movieboxpro.android.view.activity.videoplayer.presenter.VideoPresenter
    public void get_srt_select(String str, String str2) {
        ((ObservableSubscribeProxy) this.service.Movie_srt_select(API.BASE_URL, API.Movie.MOVIE_SRTSELECT, App.isLogin() ? App.getUserData().uid_v2 : "", str2, this.movieDownload != null ? this.movieDownload.id : "", null, BuildConfig.APPLICATION_ID).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.videoplayer.MoviePlayerActivity.6
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onNext(String str3) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }
        });
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory, com.movieboxpro.android.view.activity.videoplayer.presenter.VideoPresenter
    public void saveInDao(int i, int i2) {
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.Incognito_Mode)) {
            return;
        }
        PlayRecode findByTypeid = App.getDB().playRecodeDao().findByTypeid(this.movieDownload.box_type, this.movieDownload.id);
        if (findByTypeid == null) {
            PlayRecode playRecode = new PlayRecode();
            playRecode.setMovieId(this.movieDownload.id);
            playRecode.setBox_type(this.movieDownload.box_type);
            playRecode.setImdb_id(this.movieDownload.imdb_id);
            playRecode.setTitle(this.movieDownload.title);
            playRecode.setStart_time(i);
            playRecode.setQuality(i2);
            playRecode.setSeason(1);
            playRecode.setEpisode(1);
            try {
                App.getDB().playRecodeDao().insert(playRecode);
                return;
            } catch (SQLiteFullException e) {
                e.printStackTrace();
                return;
            }
        }
        if (i > 0) {
            findByTypeid.setStart_time(i);
        }
        findByTypeid.setQuality(i2);
        try {
            App.getDB().playRecodeDao().update(findByTypeid);
        } catch (SQLiteFullException e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory
    protected void switchServer() {
        String str;
        String str2;
        String string = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, "");
        if (!TextUtils.isEmpty(string) && "0".equalsIgnoreCase(string)) {
            str2 = "";
            str = str2;
        } else {
            str = string;
            str2 = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
        }
        ((ObservableSubscribeProxy) Http.getService().Movie_detail(API.BASE_URL, API.Movie.MOVE_DOWNLAOD, App.getUserData().uid_v2, this.movieDownload.id, App.deviceLang, str2, str).compose(RxUtils.rxTranslate2Bean(MovieDetail.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<MovieDetail>() { // from class: com.movieboxpro.android.view.activity.videoplayer.MoviePlayerActivity.7
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                MoviePlayerActivity.this.showLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(MovieDetail movieDetail) {
                MoviePlayerActivity.this.hideLoading();
                MoviePlayerActivity.this.movieDownload.list = movieDetail.list;
                MoviePlayerActivity.this.movieDownload.quality = movieDetail.quality;
                MoviePlayerActivity.this.movieDownload.seconds = movieDetail.seconds;
                MoviePlayerActivity.this.list.clear();
                MoviePlayerActivity.this._initQuality();
                PlayRecode inDao = MoviePlayerActivity.this.getInDao();
                MoviePlayerActivity.this.controller.resetDefinition(MoviePlayerActivity.this.list);
                MoviePlayerActivity.this.ijkPlayerPresenter.resetDefinitionVideosAndPlay(MoviePlayerActivity.this.list, inDao, MoviePlayerActivity.this.movieDownload.seconds);
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException apiException) {
                MoviePlayerActivity.this.hideLoading();
                ToastUtils.showShort("Load failed");
            }
        });
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.presenter.VideoPresenter
    public PlayRecode getInDao() {
        return App.getDB().playRecodeDao().findByTypeid(1, this.movieDownload.id);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefreshUrl(RefreshPlayUrlEvent refreshPlayUrlEvent) {
        switchServer();
    }

    public void get_Srt() {
        ((ObservableSubscribeProxy) this.service.Movie_srt_auto(API.BASE_URL, API.Movie.MOVIE_SRT_AUTO_V2, this.movieDownload.id, App.deviceLang, App.isLogin() ? App.getUserData().uid_v2 : "").compose(RxUtils.rxTranslate2List(Srt.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<List<Srt>>() { // from class: com.movieboxpro.android.view.activity.videoplayer.MoviePlayerActivity.8
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                RxManager.addDisposable(MoviePlayerActivity.this.TAG, disposable);
            }

            @Override // io.reactivex.Observer
            public void onNext(List<Srt> list) {
                MoviePlayerActivity.this.srtLists = list;
            }
        });
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory
    public void getSubtitle(String str) {
        URL url;
        super.getSubtitle(str);
        try {
            url = new URL("https://api.opensubtitles.org/xml-rpc");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            url = null;
        }
        XMLRPCClient xMLRPCClient = new XMLRPCClient(url, 256);
        JSONObject jSONObject = new JSONObject();
        if (this.movieDownload.imdb_id == null) {
            this.movieDownload.imdb_id = TtmlNode.TAG_TT;
        }
        jSONObject.put("imdbid", (Object) this.movieDownload.imdb_id.replaceAll(TtmlNode.TAG_TT, ""));
        jSONObject.put("sublanguageid", (Object) this.language);
        JSONArray jSONArray = new JSONArray();
        jSONArray.add(jSONObject);
        String str2 = this.TAG;
        MLog.d(str2, "1111onResponse  :" + jSONArray.toString());
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("limit", (Object) 500);
        xMLRPCClient.cancel(xMLRPCClient.callAsync(this.listener2, "SearchSubtitles", str, jSONArray, jSONObject2));
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory
    protected void videoPlayComplete() {
        this.isComplete = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory, com.movieboxpro.android.base.BaseActivity2, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.listener2 = null;
        this.listener = null;
        super.onDestroy();
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory
    public String buildData(ExtrModel extrModel) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("module", (Object) API.Movie.UPLOAD_MOVIE_SRT);
        jSONObject.put("mid", (Object) this.movieDownload.id);
        jSONObject.put("lang", (Object) extrModel.getISO639());
        jSONObject.put("language", (Object) extrModel.getLanguageName());
        jSONObject.put(IjkMediaMeta.IJKM_KEY_FORMAT, (Object) extrModel.getSubFormat());
        jSONObject.put("srt_id", (Object) extrModel.getIDSubtitle());
        jSONObject.put("from", (Object) "opensubtitles");
        jSONObject.put("uid", (Object) App.getUserData().uid_v2);
        jSONObject.put("open_udid", (Object) SystemUtils.getUniqueId(App.getContext()));
        jSONObject.put("app_version", (Object) App.versionName);
        jSONObject.put("expired_date", (Object) Long.valueOf((TimeUtils.getCurrentTime() / 1000) + 43200));
        return HttpUtils.encodeBody(jSONObject.toJSONString());
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory
    public String buildDatas(ExtrModel extrModel) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("module", (Object) API.Movie.UPLOAD_MOVIE_SRTS);
        jSONObject.put("mid", (Object) this.movieDownload.id);
        jSONObject.put("lang", (Object) extrModel.getISO639());
        jSONObject.put("language", (Object) extrModel.getLanguageName());
        jSONObject.put(IjkMediaMeta.IJKM_KEY_FORMAT, (Object) extrModel.getSubFormat());
        jSONObject.put("srt_id", (Object) extrModel.getIDSubtitle());
        jSONObject.put("from", (Object) "opensubtitles");
        jSONObject.put("uid", (Object) App.getUserData().uid_v2);
        jSONObject.put("open_udid", (Object) SystemUtils.getUniqueId(App.getContext()));
        jSONObject.put("app_version", (Object) App.versionName);
        jSONObject.put("expired_date", (Object) Long.valueOf((TimeUtils.getCurrentTime() / 1000) + 43200));
        return HttpUtils.encodeBody(jSONObject.toJSONString());
    }
}
