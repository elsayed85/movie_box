package com.movieboxpro.android.view.activity.videoplayer;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteFullException;
import android.os.Bundle;
import android.text.TextUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ares.downloader.jarvis.core.RemoteFileUtil;
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
import com.movieboxpro.android.base.BaseObserver;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.db.dao.DownloadDao;
import com.movieboxpro.android.db.entity.Download;
import com.movieboxpro.android.db.entity.PlayRecode;
import com.movieboxpro.android.event.CloseSkipEndEvent;
import com.movieboxpro.android.event.RefreshPlayUrlEvent;
import com.movieboxpro.android.event.RefreshSkipTimeEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.APIService;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.http.HttpUtils;
import com.movieboxpro.android.http.ServerException;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.DownloadModel;
import com.movieboxpro.android.model.SkipTimeResponse;
import com.movieboxpro.android.model.TvSeasonList;
import com.movieboxpro.android.model.common.Srt;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.timroes.axmlrpc.XMLRPCClient;
import com.movieboxpro.android.utils.Api;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.EventUtils;
import com.movieboxpro.android.utils.LogUtils;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SettingManager;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.Video.VideoPlayerActivity;
import com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity;
import com.movieboxpro.android.view.activity.exoplayer.player.NormalExoVideoView;
import com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl;
import com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.MsgHintDialog;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* loaded from: classes.dex */
public class TvPlayerActivity extends VideoActivityFactory {
    private Disposable disposable;
    private boolean showFlag = true;

    @Override // com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory
    protected int boxType() {
        return 2;
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory
    protected void startProgressTimer() {
        Disposable disposable = this.disposable;
        if (disposable != null) {
            disposable.dispose();
        }
        final MediaPlayerControl mediaPlayer = this.controller.getMediaPlayer();
        final long duration = mediaPlayer.getDuration();
        ((ObservableSubscribeProxy) Observable.interval(0L, 2L, TimeUnit.SECONDS).map(new Function() { // from class: com.movieboxpro.android.view.activity.videoplayer.-$$Lambda$TvPlayerActivity$RIJeCXg16ldRXvckcIAZKbd8HvA
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return TvPlayerActivity.this.lambda$startProgressTimer$0$TvPlayerActivity(mediaPlayer, duration, (Long) obj);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new AnonymousClass1(mediaPlayer, duration));
    }

    public /* synthetic */ Boolean lambda$startProgressTimer$0$TvPlayerActivity(MediaPlayerControl mediaPlayerControl, long j, Long l) throws Exception {
        if (!(mediaPlayerControl instanceof NormalExoVideoView) && this.currPlayingPosition != this.tvSeasonListList.size() - 1) {
            long currentPosition = mediaPlayerControl.getCurrentPosition();
            boolean z = PrefsUtils.getInstance().getBoolean(Constant.Prefs.SKIP_OPENING_ENDING, true);
            if (this.skipTime != null && z && ("other".equals(this.skipTime.getEnd_type()) || ("my".equals(this.skipTime.getEnd_type()) && this.skipTime.getEnd() > 0))) {
                if (j - currentPosition <= this.skipTime.getEnd() * 1000) {
                    if (this.showFlag) {
                        this.showFlag = false;
                        runOnUiThread(new Runnable() { // from class: com.movieboxpro.android.view.activity.videoplayer.TvPlayerActivity.2
                            @Override // java.lang.Runnable
                            public void run() {
                                PlayerController playerController = TvPlayerActivity.this.controller;
                                int end = TvPlayerActivity.this.skipTime.getEnd();
                                String str = TvPlayerActivity.this.movieDownload.id;
                                playerController.showRecommendSkipTime(end, false, str, TvPlayerActivity.this.fid + "", TvPlayerActivity.this.season, TvPlayerActivity.this.episode, TvPlayerActivity.this.getSupportFragmentManager(), new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.TvPlayerActivity.2.1
                                    @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                                    public void onClick() {
                                        TvPlayerActivity.this.onPlayNextEpisode(true);
                                        ((ObservableSubscribeProxy) HttpRequest.post(Api.INSTANCE.saveSkipTime(TvPlayerActivity.this.movieDownload.id, TvPlayerActivity.this.season, TvPlayerActivity.this.episode, "add", -1, TvPlayerActivity.this.skipTime.getEnd()), TvPlayerActivity.this).asRequest().compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(TvPlayerActivity.this))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.videoplayer.TvPlayerActivity.2.1.1
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
                                        ((ObservableSubscribeProxy) HttpRequest.post(Api.INSTANCE.voteSkipTime(TvPlayerActivity.this.movieDownload.id, TvPlayerActivity.this.season, TvPlayerActivity.this.episode, -1, TvPlayerActivity.this.skipTime.getEnd()), TvPlayerActivity.this).asRequest().compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(TvPlayerActivity.this))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.videoplayer.TvPlayerActivity.2.1.2
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
                                });
                            }
                        });
                    }
                } else {
                    this.showFlag = true;
                }
            }
            return false;
        }
        return false;
    }

    /* renamed from: com.movieboxpro.android.view.activity.videoplayer.TvPlayerActivity$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    class AnonymousClass1 implements Observer<Boolean> {
        final /* synthetic */ long val$duration;
        final /* synthetic */ MediaPlayerControl val$mediaPlayer;

        @Override // io.reactivex.Observer
        public void onComplete() {
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
        }

        AnonymousClass1(MediaPlayerControl mediaPlayerControl, long j) {
            this.val$mediaPlayer = mediaPlayerControl;
            this.val$duration = j;
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            TvPlayerActivity.this.disposable = disposable;
        }

        @Override // io.reactivex.Observer
        public void onNext(Boolean bool) {
            if (!(this.val$mediaPlayer instanceof NormalExoVideoView) || TvPlayerActivity.this.currPlayingPosition == TvPlayerActivity.this.tvSeasonListList.size() - 1) {
                return;
            }
            long currentPosition = this.val$mediaPlayer.getCurrentPosition();
            if (!PrefsUtils.getInstance().getBoolean(Constant.Prefs.SKIP_OPENING_ENDING, true) || TvPlayerActivity.this.skipTime == null) {
                return;
            }
            if (("other".equals(TvPlayerActivity.this.skipTime.getEnd_type()) || ("my".equals(TvPlayerActivity.this.skipTime.getEnd_type()) && TvPlayerActivity.this.skipTime.getEnd() > 0)) && this.val$duration - currentPosition <= TvPlayerActivity.this.skipTime.getEnd() * 1000) {
                if (TvPlayerActivity.this.showFlag) {
                    TvPlayerActivity.this.showFlag = false;
                    TvPlayerActivity.this.runOnUiThread(new Runnable() { // from class: com.movieboxpro.android.view.activity.videoplayer.TvPlayerActivity.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            PlayerController playerController = TvPlayerActivity.this.controller;
                            int end = TvPlayerActivity.this.skipTime.getEnd();
                            String str = TvPlayerActivity.this.movieDownload.id;
                            playerController.showRecommendSkipTime(end, false, str, TvPlayerActivity.this.fid + "", TvPlayerActivity.this.season, TvPlayerActivity.this.episode, TvPlayerActivity.this.getSupportFragmentManager(), new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.TvPlayerActivity.1.1.1
                                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                                public void onClick() {
                                    TvPlayerActivity.this.onPlayNextEpisode(true);
                                    ((ObservableSubscribeProxy) HttpRequest.post(Api.INSTANCE.saveSkipTime(TvPlayerActivity.this.movieDownload.id, TvPlayerActivity.this.season, TvPlayerActivity.this.episode, "add", TvPlayerActivity.this.skipTime.getEnd(), -1), TvPlayerActivity.this).asRequest().compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(TvPlayerActivity.this))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.videoplayer.TvPlayerActivity.1.1.1.1
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
                                    ((ObservableSubscribeProxy) HttpRequest.post(Api.INSTANCE.voteSkipTime(TvPlayerActivity.this.movieDownload.id, TvPlayerActivity.this.season, TvPlayerActivity.this.episode, TvPlayerActivity.this.skipTime.getEnd(), -1), TvPlayerActivity.this).asRequest().compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(TvPlayerActivity.this))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.videoplayer.TvPlayerActivity.1.1.1.2
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
                            });
                        }
                    });
                    return;
                }
                TvPlayerActivity.this.showFlag = true;
            }
        }
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory
    protected void videoPlayComplete() {
        Disposable disposable = this.disposable;
        if (disposable != null) {
            disposable.dispose();
        }
        this.isComplete = true;
        if (this.currPlayingPosition == this.tvSeasonListList.size() - 1) {
            this.controller.setNextViewGone();
        } else {
            this.controller.setNextViewVisible();
        }
        if (SettingManager.INSTANCE.getSmartDownload() && SettingManager.INSTANCE.getAutoDeleteWatched()) {
            if (this.tvSeasonListList == null || this.tvSeasonListList.size() == 0) {
                Download findByTidAndSeasonEpisode = App.getDB().downloadDao().findByTidAndSeasonEpisode(this.movieDownload.id, this.season, this.episode);
                if (findByTidAndSeasonEpisode != null) {
                    new DownloadModel(findByTidAndSeasonEpisode).startService(Constant.ACTION.MOVIE_DELETE, (Activity) this);
                }
            } else {
                TvSeasonList tvSeasonList = this.tvSeasonListList.get(this.currPlayingPosition);
                DownloadDao downloadDao = App.getDB().downloadDao();
                Download findByType = downloadDao.findByType(2, tvSeasonList.getTid() + "_" + tvSeasonList.getId());
                if (findByType != null) {
                    new DownloadModel(findByType).startService(Constant.ACTION.MOVIE_DELETE, (Activity) this);
                }
            }
        }
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.AUTO_PLAY_NEXT, false)) {
            onPlayNextEpisode(this.isComplete);
        }
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory, com.movieboxpro.android.view.activity.videoplayer.presenter.VideoPresenter
    public void _geiPrama() {
        CommonUtils.onEvent("WatchTV");
        EventUtils.event("观看电视");
        this.movieDownload = (BaseMediaModel) getSerializable("videoplayer_params", new BaseMediaModel());
        this.season = getIntParam(VideoPlayerActivity.VIDEO_ID, 1);
        this.episode = getIntParam("videoplayer_episode", 1);
        if (getBooleanParam("FeaturedFragment", false)) {
            Intent intent = new Intent();
            intent.putExtra("id", this.movieDownload.id);
            intent.putExtra("season", this.season);
            intent.putExtra("episode", this.season);
            setResult(-1, intent);
        }
        this.movieDownload.season = this.season;
        this.movieDownload.episode = this.episode;
        if (getBooleanParam(TvDetailActivity.IS_AUTO_NEXT, false)) {
            showToast("Play next Episode Automatically");
        }
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.tvSeasonListList = extras.getParcelableArrayList(TvDetailActivity.SEASON_TV_LIST);
            if (this.tvSeasonListList == null) {
                this.tvSeasonListList = new ArrayList();
                requestSeasonsInfo(this.season, this.movieDownload.id);
                return;
            }
            initPosition();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initPosition() {
        if (this.tvSeasonListList != null) {
            for (int i = 0; i < this.tvSeasonListList.size(); i++) {
                if (this.tvSeasonListList.get(i).getEpisode() == this.episode) {
                    this.currPlayingPosition = i;
                    return;
                }
            }
        }
    }

    private void requestSeasonsInfo(int i, String str) {
        APIService service = Http.getService();
        String str2 = API.BASE_URL;
        ((ObservableSubscribeProxy) service.TV_episode(str2, API.Tv.TV_EPISODE, str, i + "", "", App.getUserData().uid_v2, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE).compose(RxUtils.rxTranslate2List(TvDetail.SeasonDetail.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<List<TvDetail.SeasonDetail>>() { // from class: com.movieboxpro.android.view.activity.videoplayer.TvPlayerActivity.3
            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(List<TvDetail.SeasonDetail> list) {
                for (TvDetail.SeasonDetail seasonDetail : list) {
                    TvSeasonList tvSeasonList = new TvSeasonList();
                    tvSeasonList.setEpisode(seasonDetail.episode);
                    tvSeasonList.setSeason(seasonDetail.season);
                    tvSeasonList.setTid(TvPlayerActivity.this.movieDownload.id);
                    tvSeasonList.setId(seasonDetail.id);
                    TvPlayerActivity.this.tvSeasonListList.add(tvSeasonList);
                }
                TvPlayerActivity.this.initPosition();
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                TvPlayerActivity.this.hideLoading();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory
    public void onPlayNextEpisode(boolean z) {
        int definition;
        super.onPlayNextEpisode(z);
        if (SettingManager.INSTANCE.getSmartDownload() && SettingManager.INSTANCE.getAutoDeleteWatched()) {
            if (this.tvSeasonListList == null || this.tvSeasonListList.size() == 0) {
                Download findByTidAndSeasonEpisode = App.getDB().downloadDao().findByTidAndSeasonEpisode(this.movieDownload.id, this.season, this.episode);
                if (findByTidAndSeasonEpisode != null) {
                    new DownloadModel(findByTidAndSeasonEpisode).startService(Constant.ACTION.MOVIE_DELETE, (Activity) this);
                }
            } else {
                TvSeasonList tvSeasonList = this.tvSeasonListList.get(this.currPlayingPosition);
                Download findByType = App.getDB().downloadDao().findByType(2, tvSeasonList.getTid() + "_" + tvSeasonList.getId());
                if (findByType != null) {
                    new DownloadModel(findByType).startService(Constant.ACTION.MOVIE_DELETE, (Activity) this);
                }
            }
        }
        this.isComplete = z;
        String str = null;
        if (this.movieDownload != null && (definition = this.ijkPlayerPresenter.getDefinition()) >= 0 && definition < this.movieDownload.list.size()) {
            str = this.movieDownload.list.get(definition).tmfid;
        }
        if (str != null) {
            get_Movie_select((int) this.ijkPlayerPresenter.getDuration(), (int) this.ijkPlayerPresenter.getCurrentPosition(), this.ijkPlayerPresenter.getDefinition() < this.movieDownload.list.size() ? this.movieDownload.list.get(this.ijkPlayerPresenter.getDefinition()).tmfid + "" : "0");
        }
        this.isComplete = false;
        this.currPlayingPosition++;
        if (this.tvSeasonListList == null || this.tvSeasonListList.size() == 0) {
            Download findByTidAndSeasonEpisode2 = App.getDB().downloadDao().findByTidAndSeasonEpisode(this.movieDownload.id, this.season, this.episode + 1);
            if (findByTidAndSeasonEpisode2 != null) {
                if (2 == findByTidAndSeasonEpisode2.getStatue()) {
                    DownloadModel downloadModel = new DownloadModel(findByTidAndSeasonEpisode2);
                    downloadModel.path = Constant.DIR_DOWNLOAD + File.separator + RemoteFileUtil.getFileName(downloadModel.path, downloadModel.getTitle(), Constant.DIR_DOWNLOAD);
                    TvDetail tvDetail = new TvDetail(downloadModel);
                    this.list.clear();
                    this.movieDownload.list = tvDetail.list;
                    this.movieDownload.quality = tvDetail.quality;
                    this.episode++;
                    for (int i = 0; i < this.movieDownload.list.size(); i++) {
                        BaseMediaModel.DownloadFile downloadFile = this.movieDownload.list.get(i);
                        this.list.add(new MediaQualityInfo(i, downloadFile.h265, downloadFile.path, downloadFile.real_quality, downloadFile.size, TimeUtils.formatTime3(downloadFile.dateline * 1000), downloadFile.count, downloadFile.vip_only + "", downloadFile.filename, downloadFile.fid, false, downloadFile.path2, downloadFile.bitstream, downloadFile.original, downloadFile.hdr, downloadFile.colorbit, downloadFile.filename, downloadFile.mmfid, downloadFile.tmfid));
                    }
                    updateVideoTitle();
                    PlayRecode inDao = getInDao();
                    if (inDao != null) {
                        inDao.setStart_time(0);
                    }
                    this.ijkPlayerPresenter.resetDefinitionVideosAndPlay(this.list, inDao, 0);
                    this.controller.setNextViewGone();
                    this.controller.clearSubtitle();
                    this.controller.resetDefinition(this.list);
                    Disposable disposable = this.disposable;
                    if (disposable != null) {
                        disposable.dispose();
                    }
                    getSaveSkipTime();
                    Bundle extras = getIntent().getExtras();
                    extras.putParcelableArrayList(TvDetailActivity.SEASON_TV_LIST, new ArrayList<>(this.tvSeasonListList));
                    extras.putSerializable("videoplayer_params", this.movieDownload);
                    extras.putInt(VideoPlayerActivity.VIDEO_ID, this.season);
                    extras.putInt("videoplayer_episode", this.episode);
                    getIntent().putExtras(extras);
                    return;
                }
                ToastUtils.showShort("Episode " + (this.episode + 1) + " is not downloaded");
                return;
            }
            ToastUtils.showShort("Next episode is disabled.");
        } else if (this.currPlayingPosition < this.tvSeasonListList.size() && this.currPlayingPosition >= 0) {
            TvSeasonList tvSeasonList2 = this.tvSeasonListList.get(this.currPlayingPosition);
            if (getIntent().getBooleanExtra(VideoActivityFactory.IS_LOCAL_FILE, false)) {
                Download findByType2 = App.getDB().downloadDao().findByType(2, tvSeasonList2.getTid() + "_" + tvSeasonList2.getId());
                if (findByType2 != null) {
                    DownloadModel downloadModel2 = new DownloadModel(findByType2);
                    if (2 == findByType2.getStatue()) {
                        downloadModel2.path = Constant.DIR_DOWNLOAD + File.separator + RemoteFileUtil.getFileName(downloadModel2.path, downloadModel2.getTitle(), Constant.DIR_DOWNLOAD);
                        resetVideoUrl(new TvDetail(downloadModel2), true);
                        this.isLoadSubtitle = false;
                        this.previewVideoPrepared = false;
                        this.previewIjkVideoView.resetPlayUrl(findByType2.getPath());
                        return;
                    }
                    ToastUtils.showShort("Episode " + tvSeasonList2.getEpisode() + " is not downloaded");
                    return;
                }
                requestUrls(tvSeasonList2, true);
                return;
            }
            requestUrls(tvSeasonList2, true);
        } else {
            this.currPlayingPosition = this.tvSeasonListList.size() - 1;
            ToastUtils.showShort("No next episode");
        }
    }

    private void requestUrls(final TvSeasonList tvSeasonList, final boolean z) {
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
        ((ObservableSubscribeProxy) Http.getService().TV_downloadurl(API.BASE_URL, API.Tv.TV_DOWNLAODURL, App.isLogin() ? App.getUserData().uid_v2 : "", tvSeasonList.getTid(), String.valueOf(tvSeasonList.getSeason()), String.valueOf(tvSeasonList.getEpisode()), str2, str).compose(RxUtils.rxTranslate2Bean(TvDetail.class)).map(new Function<TvDetail, TvDetail>() { // from class: com.movieboxpro.android.view.activity.videoplayer.TvPlayerActivity.5
            @Override // io.reactivex.functions.Function
            public TvDetail apply(TvDetail tvDetail) throws Exception {
                Download findByTidAndSeasonEpisode = App.getDB().downloadDao().findByTidAndSeasonEpisode(tvSeasonList.getTid(), tvSeasonList.getSeason(), tvSeasonList.getEpisode());
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
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<TvDetail>() { // from class: com.movieboxpro.android.view.activity.videoplayer.TvPlayerActivity.4
            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                TvPlayerActivity.this.showLoading();
            }

            @Override // io.reactivex.Observer
            public void onNext(TvDetail tvDetail) {
                if (tvDetail != null && tvDetail.list.size() > 0) {
                    if (z) {
                        TvPlayerActivity.this.isLoadSubtitle = false;
                    }
                    TvPlayerActivity.this.resetVideoUrl(tvDetail, z);
                    if (tvDetail.list.size() > 0) {
                        TvPlayerActivity.this.previewVideoPrepared = false;
                        TvPlayerActivity.this.previewIjkVideoView.resetPlayUrl(tvDetail.list.get(tvDetail.list.size() - 1).path);
                    }
                } else if (TvPlayerActivity.this.currPlayingPosition == TvPlayerActivity.this.tvSeasonListList.size() - 1) {
                    TvPlayerActivity.this.showToast(String.format("Episode %s has no resources", Integer.valueOf(tvSeasonList.getEpisode())));
                } else {
                    TvPlayerActivity.this.showToast(String.format("Episode %s has no resources,press again play Episode %s", Integer.valueOf(tvSeasonList.getEpisode()), Integer.valueOf(tvSeasonList.getEpisode() + 1)));
                }
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                if (th instanceof ServerException) {
                    ToastUtils.showShort(((ServerException) th).getMessage());
                } else {
                    ToastUtils.showShort("load error");
                }
                TvPlayerActivity.this.hideLoading();
                TvPlayerActivity tvPlayerActivity = TvPlayerActivity.this;
                tvPlayerActivity.currPlayingPosition--;
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                TvPlayerActivity.this.hideLoading();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetVideoUrl(TvDetail tvDetail, boolean z) {
        int i;
        this.list.clear();
        this.movieDownload.list = tvDetail.list;
        this.movieDownload.quality = tvDetail.quality;
        TvSeasonList tvSeasonList = this.tvSeasonListList.get(this.currPlayingPosition);
        this.movieDownload.id = tvSeasonList.getTid();
        this.season = tvSeasonList.getSeason();
        this.episode = tvSeasonList.getEpisode();
        int i2 = 0;
        while (i2 < this.movieDownload.list.size()) {
            BaseMediaModel.DownloadFile downloadFile = this.movieDownload.list.get(i2);
            int i3 = downloadFile.h265;
            String str = downloadFile.path;
            String str2 = downloadFile.real_quality;
            String str3 = downloadFile.size;
            String formatTime3 = TimeUtils.formatTime3(downloadFile.dateline * 1000);
            String str4 = downloadFile.count;
            int i4 = i2;
            this.list.add(new MediaQualityInfo(i4, i3, str, str2, str3, formatTime3, str4, downloadFile.vip_only + "", downloadFile.filename, downloadFile.fid, false, downloadFile.path2, downloadFile.bitstream, downloadFile.original, downloadFile.hdr, downloadFile.colorbit, downloadFile.filename, downloadFile.mmfid, downloadFile.tmfid));
            i2 = i4 + 1;
        }
        updateVideoTitle();
        PlayRecode inDao = getInDao();
        if (!z || inDao == null) {
            i = 0;
        } else {
            i = 0;
            inDao.setStart_time(0);
        }
        if (z) {
            this.ijkPlayerPresenter.resetDefinitionVideosAndPlay(this.list, inDao, i);
        } else {
            this.ijkPlayerPresenter.resetDefinitionVideosAndPlay(this.list, inDao, tvDetail.seconds);
        }
        this.controller.setNextViewGone();
        this.controller.clearSubtitle();
        this.controller.resetDefinition(this.list);
        Disposable disposable = this.disposable;
        if (disposable != null) {
            disposable.dispose();
        }
        getSaveSkipTime();
        Bundle extras = getIntent().getExtras();
        extras.putParcelableArrayList(TvDetailActivity.SEASON_TV_LIST, new ArrayList<>(this.tvSeasonListList));
        extras.putSerializable("videoplayer_params", this.movieDownload);
        extras.putInt(VideoPlayerActivity.VIDEO_ID, this.season);
        extras.putInt("videoplayer_episode", this.episode);
        getIntent().putExtras(extras);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefreshUrl(RefreshPlayUrlEvent refreshPlayUrlEvent) {
        switchServer();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCloseSkipView(CloseSkipEndEvent closeSkipEndEvent) {
        this.showFlag = false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefreshSkipTime(final RefreshSkipTimeEvent refreshSkipTimeEvent) {
        ((ObservableSubscribeProxy) HttpRequest.post(Api.INSTANCE.getSkipTime(this.movieDownload.id, this.season, this.episode), this).asRequest().compose(RxUtils.rxTranslate2Bean(SkipTimeResponse.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<SkipTimeResponse>() { // from class: com.movieboxpro.android.view.activity.videoplayer.TvPlayerActivity.6
            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(SkipTimeResponse skipTimeResponse) {
                TvPlayerActivity.this.skipTime = skipTimeResponse;
                if (refreshSkipTimeEvent.getStartTime() != -1) {
                    new MsgHintDialog.Builder(TvPlayerActivity.this).setContent("Whether jump to the position just set?").setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.TvPlayerActivity.6.1
                        @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                        public void onClick() {
                            TvPlayerActivity.this.controller.getMediaPlayer().seekTo(refreshSkipTimeEvent.getStartTime() * 1000);
                        }
                    }).create().show();
                }
            }
        });
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory, com.movieboxpro.android.view.activity.videoplayer.presenter.VideoPresenter
    public void getSrt(MediaQualityInfo mediaQualityInfo) {
        if (Network.isConnected(this.context)) {
            get_Srt();
            APIService aPIService = this.service;
            String str = API.BASE_URL;
            String str2 = App.isLogin() ? App.getUserData().uid_v2 : "";
            ((ObservableSubscribeProxy) aPIService.TV_srt_list_v2(str, API.Tv.TV_SRTLIST_V2, str2, this.movieDownload.id, mediaQualityInfo.getFid() + "", this.season + "", this.episode + "", App.deviceLang, BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslate2Bean(ResponseSubtitleRecord.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<ResponseSubtitleRecord>() { // from class: com.movieboxpro.android.view.activity.videoplayer.TvPlayerActivity.7
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
                                next.subtitles.get(0).setSelect(true);
                                next.subtitles.get(0).setMyselect(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
                                responseSubtitleRecord.setSelect(next.subtitles.get(0));
                                break;
                            }
                        }
                        if (responseSubtitleRecord.getSelect() == null && responseSubtitleRecord.getList().size() > 0 && responseSubtitleRecord.getList().get(0).subtitles.size() > 0) {
                            responseSubtitleRecord.setSelect(responseSubtitleRecord.getList().get(0).subtitles.get(0));
                        }
                    }
                    TvPlayerActivity.this.srtHash.clear();
                    for (SRTModel sRTModel : responseSubtitleRecord.getList()) {
                        TvPlayerActivity.this.srtHash.put(sRTModel.language, sRTModel.subtitles);
                    }
                    if (TvPlayerActivity.this.controller != null) {
                        TvPlayerActivity.this.controller.setSrt(TvPlayerActivity.this.srtHash);
                    }
                    if (responseSubtitleRecord.getSelect() != null) {
                        TvPlayerActivity.this.controller.selectSubtitle(responseSubtitleRecord.getSelect());
                    }
                }
            });
        }
    }

    public void get_Srt() {
        ((ObservableSubscribeProxy) this.service.Tv_srt_auto(API.BASE_URL, API.Tv.TV_SRT_AUTO_V2, this.movieDownload.id, this.season, this.episode, App.deviceLang, App.isLogin() ? App.getUserData().uid_v2 : "").compose(RxUtils.rxTranslate2List(Srt.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<List<Srt>>() { // from class: com.movieboxpro.android.view.activity.videoplayer.TvPlayerActivity.8
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                RxManager.addDisposable(TvPlayerActivity.this.TAG, disposable);
            }

            @Override // io.reactivex.Observer
            public void onNext(List<Srt> list) {
                TvPlayerActivity.this.srtLists = list;
            }
        });
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory, com.movieboxpro.android.view.activity.videoplayer.presenter.VideoPresenter
    public void get_Movie_select(int i, int i2, String str) {
        int i3;
        Observable<String> empty;
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
        StringBuilder sb = new StringBuilder();
        int i6 = i4 / 1000;
        sb.append(i6);
        sb.append("");
        Observable<String> TV_play_progress = aPIService.TV_play_progress(str2, API.Tv.TV_PLAY_PROGRESS, str3, this.movieDownload.id, str, this.season + "", this.episode + "", sb.toString(), i5, BuildConfig.VERSION_NAME);
        if (!PrefsUtils.getInstance().getBoolean(Constant.Prefs.Incognito_Mode, false)) {
            APIService aPIService2 = this.service;
            String str4 = API.BASE_URL;
            String str5 = App.isLogin() ? App.getUserData().uid_v2 : "";
            empty = aPIService2.Tv_play(str4, API.Tv.TV_PLAY, str5, this.movieDownload.id, this.season + "", this.episode + "", i6 + "", str, BuildConfig.VERSION_NAME, SystemUtils.getUniqueId(this.context));
        } else {
            empty = Observable.empty();
        }
        Observable.zip(empty, TV_play_progress, $$Lambda$TvPlayerActivity$3SNAtJG_u8UqO8Q8HPKvdczcwcw.INSTANCE).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.videoplayer.TvPlayerActivity.9
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onNext(String str6) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }
        });
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory
    protected void desSelectSubtitle(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        APIService aPIService = this.service;
        String str2 = API.BASE_URL;
        String str3 = App.isLogin() ? App.getUserData().uid_v2 : "";
        ((ObservableSubscribeProxy) aPIService.Tv_srt_select(str2, API.Tv.TV_SRTSELECT, str3, this.movieDownload.id, this.season + "", this.episode + "", str, "del", BuildConfig.APPLICATION_ID).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.videoplayer.TvPlayerActivity.10
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(String str4) {
                LogUtils.INSTANCE.logD(str4);
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                LogUtils.INSTANCE.logD(th.getMessage());
            }
        });
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory, com.movieboxpro.android.view.activity.videoplayer.presenter.VideoPresenter
    public void get_srt_select(String str, String str2) {
        APIService aPIService = this.service;
        String str3 = API.BASE_URL;
        String str4 = App.isLogin() ? App.getUserData().uid_v2 : "";
        ((ObservableSubscribeProxy) aPIService.Tv_srt_select(str3, API.Tv.TV_SRTSELECT, str4, this.movieDownload.id, this.season + "", this.episode + "", str2, null, BuildConfig.APPLICATION_ID).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.videoplayer.TvPlayerActivity.11
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(String str5) {
                LogUtils.INSTANCE.logD(str5);
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                LogUtils.INSTANCE.logD(th.getMessage());
            }
        });
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory, com.movieboxpro.android.view.activity.videoplayer.presenter.VideoPresenter
    public void saveInDao(int i, int i2) {
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.Incognito_Mode)) {
            return;
        }
        String str = this.TAG;
        MLog.d(str, "SSSS" + i + this.movieDownload.id + "sss" + this.movieDownload.box_type);
        PlayRecode findByEpisode = App.getDB().playRecodeDao().findByEpisode(this.movieDownload.box_type, this.movieDownload.id, this.season, this.episode);
        if (findByEpisode == null) {
            PlayRecode playRecode = new PlayRecode();
            playRecode.setMovieId(this.movieDownload.id);
            playRecode.setBox_type(this.movieDownload.box_type);
            playRecode.setImdb_id(this.movieDownload.imdb_id);
            playRecode.setTitle(this.movieDownload.title);
            playRecode.setStart_time(i);
            playRecode.setQuality(i2);
            playRecode.setSeason(this.season);
            playRecode.setEpisode(this.episode);
            try {
                App.getDB().playRecodeDao().insert(playRecode);
                return;
            } catch (SQLiteFullException e) {
                e.printStackTrace();
                return;
            }
        }
        if (i > 0) {
            findByEpisode.setStart_time(i);
        }
        findByEpisode.setQuality(i2);
        try {
            App.getDB().playRecodeDao().update(findByEpisode);
        } catch (SQLiteFullException e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory
    protected void switchServer() {
        if (this.currPlayingPosition >= this.tvSeasonListList.size() || this.currPlayingPosition < 0) {
            return;
        }
        requestUrls(this.tvSeasonListList.get(this.currPlayingPosition), false);
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.presenter.VideoPresenter
    public PlayRecode getInDao() {
        return App.getDB().playRecodeDao().findByEpisode(2, this.movieDownload.id, this.season, this.episode);
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
        jSONObject.put("season", (Object) Integer.valueOf(this.season));
        jSONObject.put("episode", (Object) Integer.valueOf(this.episode));
        JSONArray jSONArray = new JSONArray();
        jSONArray.add(jSONObject);
        String str2 = this.TAG;
        MLog.d(str2, "1111onResponse  :" + jSONArray.toString());
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("limit", (Object) 500);
        xMLRPCClient.cancel(xMLRPCClient.callAsync(this.listener2, "SearchSubtitles", str, jSONArray, jSONObject2));
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
        jSONObject.put("module", (Object) API.Tv.UPLOAD_TV_SRT);
        jSONObject.put("tid", (Object) this.movieDownload.id);
        jSONObject.put("lang", (Object) extrModel.getISO639());
        jSONObject.put("language", (Object) extrModel.getLanguageName());
        jSONObject.put(IjkMediaMeta.IJKM_KEY_FORMAT, (Object) extrModel.getSubFormat());
        jSONObject.put("srt_id", (Object) extrModel.getIDSubtitle());
        jSONObject.put("season", (Object) Integer.valueOf(this.season));
        jSONObject.put("episode", (Object) Integer.valueOf(this.episode));
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
        jSONObject.put("module", (Object) API.Tv.UPLOAD_TV_SRTS);
        jSONObject.put("tid", (Object) this.movieDownload.id);
        jSONObject.put("lang", (Object) extrModel.getISO639());
        jSONObject.put("language", (Object) extrModel.getLanguageName());
        jSONObject.put(IjkMediaMeta.IJKM_KEY_FORMAT, (Object) extrModel.getSubFormat());
        jSONObject.put("srt_id", (Object) extrModel.getIDSubtitle());
        jSONObject.put("season", (Object) Integer.valueOf(this.season));
        jSONObject.put("episode", (Object) Integer.valueOf(this.episode));
        jSONObject.put("from", (Object) "opensubtitles");
        jSONObject.put("uid", (Object) App.getUserData().uid_v2);
        jSONObject.put("open_udid", (Object) SystemUtils.getUniqueId(App.getContext()));
        jSONObject.put("app_version", (Object) App.versionName);
        jSONObject.put("expired_date", (Object) Long.valueOf((TimeUtils.getCurrentTime() / 1000) + 43200));
        return HttpUtils.encodeBody(jSONObject.toJSONString());
    }
}
