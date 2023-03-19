package com.movieboxpro.android.view.activity.Video;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.fragment.app.FragmentActivity;
import butterknife.BindView;
import butterknife.OnClick;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.dl7.player.media.IjkPlayerView;
import com.dl7.player.model.ExtrModel;
import com.dl7.player.model.MediaQualityInfo;
import com.dl7.player.model.SRTModel;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.cast.framework.CastButtonFactory;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.images.WebImage;
import com.movieboxpro.android.BuildConfig;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.AppManager;
import com.movieboxpro.android.app.RxManager;
import com.movieboxpro.android.base.BaseActivity;
import com.movieboxpro.android.config.ConfigKey;
import com.movieboxpro.android.config.ConfigUtils;
import com.movieboxpro.android.db.entity.PlayRecode;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.APIService;
import com.movieboxpro.android.http.CallManager;
import com.movieboxpro.android.http.HttpUtils;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.common.Srt;
import com.movieboxpro.android.presenter.ChromeCastPresenter;
import com.movieboxpro.android.presenter.setting.SRTPresenter;
import com.movieboxpro.android.timroes.axmlrpc.XMLRPCCallback;
import com.movieboxpro.android.timroes.axmlrpc.XMLRPCClient;
import com.movieboxpro.android.timroes.axmlrpc.XMLRPCException;
import com.movieboxpro.android.timroes.axmlrpc.XMLRPCServerException;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.view.activity.user.Login2Activity;
import com.movieboxpro.android.view.activity.user.VipActivity;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
/* loaded from: classes3.dex */
public class VideoPlayerActivity extends BaseActivity implements SRTPresenter {
    public static final String VIDEO_EPISODE = "videoplayer_episode";
    public static final String VIDEO_ID = "videoplayer_id";
    public static final String VIDEO_PARAMS = "videoplayer_params";
    public String language;
    @BindView(R.id.adView_close)
    ImageView mAdsClose;
    private CastContext mCastContext;
    private CastSession mCastSession;
    private Disposable mDisposable;
    @BindView(R.id.player_view)
    IjkPlayerView mPlayerView;
    private MediaInfo mSelectedMedia;
    private SessionManagerListener<CastSession> mSessionManagerListener;
    private BaseMediaModel movieDownload;
    private String movieid;
    public Srt srtLists;
    public String token;
    LinkedHashMap<String, List<SRTModel.subTitles>> srtHash = new LinkedHashMap<>();
    List<MediaQualityInfo> list = new ArrayList();
    IjkPlayerView.OnVipClickListener onVipClickListener = new IjkPlayerView.OnVipClickListener() { // from class: com.movieboxpro.android.view.activity.Video.VideoPlayerActivity.1
        @Override // com.dl7.player.media.IjkPlayerView.OnVipClickListener
        public boolean onClick(String str) {
            if (App.isLogin()) {
                if (!str.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE) || App.getUserData().isvip == 1) {
                    return false;
                }
                VideoPlayerActivity.this.route(VipActivity.class);
                return true;
            } else if (str.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                VideoPlayerActivity.this.route(Login2Activity.class);
                return true;
            } else {
                return false;
            }
        }
    };
    IjkPlayerView.OnDownloadClickListener onDownloadClickListener = new IjkPlayerView.OnDownloadClickListener() { // from class: com.movieboxpro.android.view.activity.Video.VideoPlayerActivity.2
        @Override // com.dl7.player.media.IjkPlayerView.OnDownloadClickListener
        public void onClick(String str) {
            VideoPlayerActivity.this.Movie_srt_select(str);
            VideoPlayerActivity.this.downLoad(str);
        }
    };
    XMLRPCCallback listener = new XMLRPCCallback() { // from class: com.movieboxpro.android.view.activity.Video.VideoPlayerActivity.10
        @Override // com.movieboxpro.android.timroes.axmlrpc.XMLRPCCallback
        public void onResponse(long j, Object obj) {
            JSONObject jSONObject = (JSONObject) JSONObject.parse(obj.toString());
            if (jSONObject.containsKey("token")) {
                VideoPlayerActivity.this.token = jSONObject.getString("token");
            }
            VideoPlayerActivity videoPlayerActivity = VideoPlayerActivity.this;
            videoPlayerActivity.getSubtitle(videoPlayerActivity.token);
            String str = VideoPlayerActivity.this.TAG;
            MLog.d(str, "1111onResponse  :" + obj.toString() + VideoPlayerActivity.this.token);
        }

        @Override // com.movieboxpro.android.timroes.axmlrpc.XMLRPCCallback
        public void onError(long j, XMLRPCException xMLRPCException) {
            String str = VideoPlayerActivity.this.TAG;
            MLog.d(str, "1111onError   : " + xMLRPCException.getMessage() + j);
            VideoPlayerActivity.this.hideLoading();
        }

        @Override // com.movieboxpro.android.timroes.axmlrpc.XMLRPCCallback
        public void onServerError(long j, XMLRPCServerException xMLRPCServerException) {
            String str = VideoPlayerActivity.this.TAG;
            MLog.d(str, "1111onServerError   : " + xMLRPCServerException.getMessage() + j);
            VideoPlayerActivity.this.hideLoading();
        }
    };
    XMLRPCCallback listener2 = new XMLRPCCallback() { // from class: com.movieboxpro.android.view.activity.Video.VideoPlayerActivity.11
        @Override // com.movieboxpro.android.timroes.axmlrpc.XMLRPCCallback
        public void onResponse(long j, Object obj) {
            JSONObject jSONObject = (JSONObject) JSONObject.parse(obj.toString());
            if (jSONObject.containsKey("data")) {
                final List javaList = jSONObject.getJSONArray("data").toJavaList(ExtrModel.class);
                VideoPlayerActivity.this.runOnUiThread(new Runnable() { // from class: com.movieboxpro.android.view.activity.Video.VideoPlayerActivity.11.1
                    @Override // java.lang.Runnable
                    public void run() {
                        VideoPlayerActivity.this.mPlayerView.shwoExtraSrt(javaList);
                    }
                });
            }
            VideoPlayerActivity.this.hideLoading();
            String str = VideoPlayerActivity.this.TAG;
            MLog.d(str, "1111onResponse2  :" + jSONObject);
        }

        @Override // com.movieboxpro.android.timroes.axmlrpc.XMLRPCCallback
        public void onError(long j, XMLRPCException xMLRPCException) {
            String str = VideoPlayerActivity.this.TAG;
            MLog.d(str, "1111onError2   : " + xMLRPCException.getMessage() + j);
            VideoPlayerActivity.this.hideLoading();
        }

        @Override // com.movieboxpro.android.timroes.axmlrpc.XMLRPCCallback
        public void onServerError(long j, XMLRPCServerException xMLRPCServerException) {
            String str = VideoPlayerActivity.this.TAG;
            MLog.d(str, "1111onServerError2   : " + xMLRPCServerException.getMessage() + j);
            VideoPlayerActivity.this.hideLoading();
        }
    };
    IjkPlayerView.OnOpenExtraDownload onOpenExtraDownload = new IjkPlayerView.OnOpenExtraDownload() { // from class: com.movieboxpro.android.view.activity.Video.VideoPlayerActivity.12
        @Override // com.dl7.player.media.IjkPlayerView.OnOpenExtraDownload
        public void onExtra(String str) {
            VideoPlayerActivity.this.getLogin(str);
        }

        @Override // com.dl7.player.media.IjkPlayerView.OnOpenExtraDownload
        public void download(String str) {
            VideoPlayerActivity.this.getSRTs(str);
        }
    };

    @Override // com.movieboxpro.android.base.BaseActivity
    protected boolean isRequireNetWork() {
        return false;
    }

    @Override // com.movieboxpro.android.base.BaseActivity
    protected boolean isShowWarning() {
        return false;
    }

    public void setBanner(boolean z) {
    }

    @OnClick({R.id.adView_close})
    public void onclick(View view) {
        this.mAdsClose.setVisibility(8);
    }

    private void _setUiLayoutFullscreen() {
        if (Build.VERSION.SDK_INT >= 14) {
            this.mRoot.setSystemUiVisibility(5894);
        }
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public View loadView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.activity_videoplayer, viewGroup, false);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initView() {
        this.mPlayerView.init();
        _setUiLayoutFullscreen();
        Glide.with((FragmentActivity) this).load(Integer.valueOf((int) R.drawable.ic_black_video)).into(this.mPlayerView.mPlayerThumb);
        setTitleBar(false);
        this.movieDownload = (BaseMediaModel) getSerializable("videoplayer_params", new BaseMediaModel());
        this.movieid = getStringParam(VIDEO_ID, "");
        CastContext sharedInstance = CastContext.getSharedInstance(this.activity);
        this.mCastContext = sharedInstance;
        this.mCastSession = sharedInstance.getSessionManager().getCurrentCastSession();
        CastButtonFactory.setUpMediaRouteButton(this.activity, this.mPlayerView.getMediaButton());
        setupCastListener();
        setBanner(App.isLogin());
    }

    public void excuetSer() {
        for (BaseMediaModel.DownloadFile downloadFile : this.movieDownload.list) {
            String str = downloadFile.path;
            String str2 = downloadFile.quality;
            String str3 = downloadFile.size;
            String formatTime3 = TimeUtils.formatTime3(downloadFile.dateline * 1000);
            String str4 = downloadFile.count;
            this.list.add(new MediaQualityInfo(0, str, str2, str3, formatTime3, str4, downloadFile.vip_only + "", downloadFile.filename, false));
        }
        PlayRecode findByTypeid = App.getDB().playRecodeDao().findByTypeid(1, this.movieDownload.id);
        this.mPlayerView.setVideoSource(this.list, this.onVipClickListener).setExtraSrt(this.onOpenExtraDownload);
        if (findByTypeid != null) {
            this.mPlayerView.setMediaQuality((findByTypeid.getQuality() >= this.list.size() || findByTypeid.getQuality() <= 0) ? choose() : findByTypeid.getQuality()).start();
        } else {
            this.mPlayerView.setMediaQuality(choose()).start();
        }
        if (findByTypeid != null) {
            String str5 = this.TAG;
            MLog.d(str5, "SSSS" + findByTypeid.getStart_time());
            this.mPlayerView.seekTo(findByTypeid.getStart_time());
        }
        setData();
        get_Srt();
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initData() {
        excuetSer();
    }

    public void setData() {
        if (Network.isConnected(this.context)) {
            this.service.Movie_srt_list(API.BASE_URL, API.Movie.MOVIE_SRTLIST, App.isLogin() ? App.getUserData().uid_v2 : "", this.movieid, Locale.getDefault().getLanguage(), BuildConfig.VERSION_NAME).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.Video.VideoPlayerActivity.3
                @Override // io.reactivex.Observer
                public void onError(Throwable th) {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable disposable) {
                    RxManager.addDisposable(VideoPlayerActivity.this.TAG, disposable);
                }

                @Override // io.reactivex.Observer
                public void onNext(String str) {
                    JSONObject jSONObject = (JSONObject) JSONObject.parse(str);
                    int intValue = jSONObject.getInteger("code").intValue();
                    VideoPlayerActivity.this.srtHash.clear();
                    if (intValue == 1) {
                        for (SRTModel sRTModel : jSONObject.getJSONObject("data").getJSONArray("list").toJavaList(SRTModel.class)) {
                            VideoPlayerActivity.this.srtHash.put(sRTModel.language, sRTModel.subtitles);
                        }
                    }
                }

                @Override // io.reactivex.Observer
                public void onComplete() {
                    if (VideoPlayerActivity.this.mPlayerView != null) {
                        VideoPlayerActivity.this.mPlayerView.setSRTList(VideoPlayerActivity.this.srtHash, VideoPlayerActivity.this.onDownloadClickListener);
                    }
                }
            });
        }
    }

    public void downLoad(String str) {
        getSRT(str);
    }

    public void getSRT(String str) {
        showLoading();
    }

    public void Movie_srt_select(String str) {
        String str2;
        if (str != null) {
            String substring = str.substring(0, str.lastIndexOf("/") - 1);
            str2 = substring.substring(substring.lastIndexOf("/") + 1);
        } else {
            str2 = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
        }
        String str3 = str2;
        APIService aPIService = this.service;
        String str4 = API.BASE_URL;
        String str5 = App.isLogin() ? App.getUserData().uid_v2 : "";
        BaseMediaModel baseMediaModel = this.movieDownload;
        aPIService.Movie_srt_select(str4, API.Movie.MOVIE_SRTSELECT, str5, str3, baseMediaModel != null ? baseMediaModel.id : "", null, BuildConfig.APPLICATION_ID).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.Video.VideoPlayerActivity.4
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

    public void Movie_play(int i) {
        if (this.movieDownload == null) {
            return;
        }
        APIService aPIService = this.service;
        String str = API.BASE_URL;
        String str2 = App.isLogin() ? App.getUserData().uid_v2 : "";
        BaseMediaModel baseMediaModel = this.movieDownload;
        String str3 = baseMediaModel != null ? baseMediaModel.id : "";
        aPIService.Movie_play(str, API.Movie.MOVIE_PLAY, str2, str3, (i / 1000) + "", this.mPlayerView.mCurSelectQuality < this.movieDownload.list.size() ? this.movieDownload.list.get(this.mPlayerView.mCurSelectQuality).mmfid : "", SystemUtils.getUniqueId(App.getContext())).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.Video.VideoPlayerActivity.5
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onNext(String str4) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                RxManager.addDisposable(VideoPlayerActivity.this.TAG, disposable);
            }
        });
    }

    public int choose() {
        for (int i = 0; i < this.movieDownload.list.size(); i++) {
            if (this.movieDownload.list.get(i).path != null && !TextUtils.isEmpty(this.movieDownload.list.get(i).path)) {
                return i;
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        this.mCastContext.getSessionManager().addSessionManagerListener(this.mSessionManagerListener, CastSession.class);
        _setUiLayoutFullscreen();
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.onResume();
        }
        super.onResume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.onPause();
        }
        this.mCastContext.getSessionManager().removeSessionManagerListener(this.mSessionManagerListener, CastSession.class);
        super.onPause();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            Movie_play(ijkPlayerView.onStop());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            saveInDao(this.mPlayerView.onDestroy(), ijkPlayerView.mCurSelectQuality);
        }
        RxManager.remove(this.TAG);
        CallManager.cancelAll(getClass().getSimpleName());
        cancel();
    }

    public void saveInDao(int i, int i2) {
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
            App.getDB().playRecodeDao().insert(playRecode);
            return;
        }
        findByTypeid.setStart_time(i);
        findByTypeid.setQuality(i2);
        App.getDB().playRecodeDao().update(findByTypeid);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.mPlayerView.handleVolumeKey(i)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.mPlayerView.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

    @Override // com.movieboxpro.android.presenter.setting.SRTPresenter
    public LinkedHashMap<String, List<SRTModel.subTitles>> getSRTList() {
        return this.srtHash;
    }

    public void setADS(boolean z) {
        if (z && App.getUserData().isvip == 1) {
            return;
        }
        Observable.interval(ConfigUtils.readIntegerConfig(ConfigKey.SPLASH_AD_INTERVAL, 30), TimeUnit.MINUTES).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Long>() { // from class: com.movieboxpro.android.view.activity.Video.VideoPlayerActivity.6
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                RxManager.addDisposable(VideoPlayerActivity.this.TAG, disposable);
            }

            @Override // io.reactivex.Observer
            public void onNext(Long l) {
                MLog.d("TAG", "执行");
            }
        });
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mPlayerView.configurationChanged(configuration);
    }

    public void cancel() {
        Disposable disposable = this.mDisposable;
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.mDisposable.dispose();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        this.activity.getMenuInflater().inflate(R.menu.browse, menu);
        CastButtonFactory.setUpMediaRouteButton(App.getContext(), menu, R.id.media_route_menu_item);
        return super.onCreateOptionsMenu(menu);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadRemoteMedia(int i, boolean z) {
        if (this.mCastSession == null) {
            return;
        }
        initMediaDate();
        final RemoteMediaClient remoteMediaClient = this.mCastSession.getRemoteMediaClient();
        if (remoteMediaClient == null) {
            return;
        }
        remoteMediaClient.addListener(new RemoteMediaClient.Listener() { // from class: com.movieboxpro.android.view.activity.Video.VideoPlayerActivity.7
            @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
            public void onAdBreakStatusUpdated() {
            }

            @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
            public void onSendingRemoteMediaRequest() {
            }

            @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
            public void onStatusUpdated() {
                if (VideoPlayerActivity.this.activity != null && !VideoPlayerActivity.this.activity.isFinishing()) {
                    VideoPlayerActivity.this.activity.startActivity(new Intent(VideoPlayerActivity.this.activity, ExpandedControlsActivity.class));
                    AppManager.add(VideoPlayerActivity.this.activity);
                    AppManager.finish((Class<? extends Activity>) VideoPlayerActivity.this.activity.getClass());
                }
                remoteMediaClient.removeListener(this);
            }

            @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
            public void onMetadataUpdated() {
                Log.d(VideoPlayerActivity.this.TAG, "!!!!!!!!+onMetadataUpdated");
            }

            @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
            public void onQueueStatusUpdated() {
                Log.d(VideoPlayerActivity.this.TAG, "!!!!!!!!+onQueueStatusUpdated");
            }

            @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
            public void onPreloadStatusUpdated() {
                Log.d(VideoPlayerActivity.this.TAG, "!!!!!!!!+onPreloadStatusUpdated");
            }
        });
        remoteMediaClient.load(this.mSelectedMedia, z, i);
    }

    private void initMediaDate() {
        org.json.JSONObject jSONObject;
        JSONException e;
        MediaMetadata mediaMetadata = new MediaMetadata(100);
        mediaMetadata.putString(MediaMetadata.KEY_TITLE, this.movieDownload.title);
        mediaMetadata.putString(MediaMetadata.KEY_SUBTITLE, this.movieDownload.description);
        mediaMetadata.addImage(new WebImage(Uri.parse(this.movieDownload.poster)));
        mediaMetadata.addImage(new WebImage(Uri.parse(this.movieDownload.poster)));
        ArrayList arrayList = new ArrayList();
        Log.d("dasdasdas", "https://www.movieboxpro.app/api/srttovtt/index?srt_uarl=" + HttpUtils.toURLEncoded(this.srtLists.file_path));
        if (this.srtLists != null) {
            arrayList.add(ChromeCastPresenter.buildTrack(1L, "text", MediaTrack.ROLE_SUBTITLE, "https://www.movieboxpro.app/api/srttovtt/index?srt_uarl=" + HttpUtils.toURLEncoded(this.srtLists.file_path), this.srtLists.language, this.srtLists.lang));
        }
        try {
            jSONObject = new org.json.JSONObject();
            try {
                jSONObject.put("KEY_DESCRIPTION", this.movieDownload.description);
            } catch (JSONException e2) {
                e = e2;
                Log.e(this.TAG, "Failed to add description to the json object", e);
                this.mSelectedMedia = new MediaInfo.Builder(this.movieDownload.list.get(choose()).path).setStreamType(1).setContentType("videos/mp4").setMetadata(mediaMetadata).setMediaTracks(arrayList).setCustomData(jSONObject).build();
            }
        } catch (JSONException e3) {
            jSONObject = null;
            e = e3;
        }
        this.mSelectedMedia = new MediaInfo.Builder(this.movieDownload.list.get(choose()).path).setStreamType(1).setContentType("videos/mp4").setMetadata(mediaMetadata).setMediaTracks(arrayList).setCustomData(jSONObject).build();
    }

    private void setupCastListener() {
        this.mSessionManagerListener = new SessionManagerListener<CastSession>() { // from class: com.movieboxpro.android.view.activity.Video.VideoPlayerActivity.8
            private void onApplicationDisconnected() {
            }

            @Override // com.google.android.gms.cast.framework.SessionManagerListener
            public void onSessionEnding(CastSession castSession) {
            }

            @Override // com.google.android.gms.cast.framework.SessionManagerListener
            public void onSessionResuming(CastSession castSession, String str) {
            }

            @Override // com.google.android.gms.cast.framework.SessionManagerListener
            public void onSessionStarting(CastSession castSession) {
            }

            @Override // com.google.android.gms.cast.framework.SessionManagerListener
            public void onSessionSuspended(CastSession castSession, int i) {
            }

            @Override // com.google.android.gms.cast.framework.SessionManagerListener
            public void onSessionEnded(CastSession castSession, int i) {
                onApplicationDisconnected();
            }

            @Override // com.google.android.gms.cast.framework.SessionManagerListener
            public void onSessionResumed(CastSession castSession, boolean z) {
                onApplicationConnected(castSession);
            }

            @Override // com.google.android.gms.cast.framework.SessionManagerListener
            public void onSessionResumeFailed(CastSession castSession, int i) {
                onApplicationDisconnected();
            }

            @Override // com.google.android.gms.cast.framework.SessionManagerListener
            public void onSessionStarted(CastSession castSession, String str) {
                onApplicationConnected(castSession);
            }

            @Override // com.google.android.gms.cast.framework.SessionManagerListener
            public void onSessionStartFailed(CastSession castSession, int i) {
                onApplicationDisconnected();
            }

            private void onApplicationConnected(CastSession castSession) {
                VideoPlayerActivity.this.mCastSession = castSession;
                VideoPlayerActivity.this.loadRemoteMedia(0, true);
                VideoPlayerActivity.this.activity.invalidateOptionsMenu();
            }
        };
    }

    public void get_Srt() {
        this.service.Movie_srt_auto(API.BASE_URL, API.Movie.MOVIE_SRT_AUTO, this.movieid, Locale.getDefault().getLanguage(), App.isLogin() ? App.getUserData().uid_v2 : "").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.Video.VideoPlayerActivity.9
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                RxManager.addDisposable(VideoPlayerActivity.this.TAG, disposable);
            }

            @Override // io.reactivex.Observer
            public void onNext(String str) {
                JSONObject jSONObject = (JSONObject) JSONObject.parse(str);
                if (jSONObject.getInteger("code").intValue() == 1) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                    VideoPlayerActivity.this.srtLists = (Srt) jSONObject2.toJavaObject((Class<Object>) Srt.class);
                }
            }
        });
    }

    public void getLogin(String str) {
        URL url;
        showLoading();
        this.language = str;
        try {
            url = new URL("https://api.opensubtitles.org/xml-rpc");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            url = null;
        }
        XMLRPCClient xMLRPCClient = new XMLRPCClient(url, 8448);
        xMLRPCClient.cancel(xMLRPCClient.callAsync(this.listener, "LogIn", "", "", "cn", "TemporaryUserAgent"));
    }

    public void getSubtitle(String str) {
        URL url;
        showLoading();
        try {
            url = new URL("https://api.opensubtitles.org/xml-rpc");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            url = null;
        }
        XMLRPCClient xMLRPCClient = new XMLRPCClient(url, 8192);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("imdbid", (Object) this.movieDownload.imdb_id.replaceAll(TtmlNode.TAG_TT, ""));
        jSONObject.put("sublanguageid", (Object) this.language);
        JSONArray jSONArray = new JSONArray();
        jSONArray.add(jSONObject);
        String str2 = this.TAG;
        MLog.d(str2, "1111onResponse  :" + jSONArray.toString());
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("limit", (Object) 50);
        xMLRPCClient.cancel(xMLRPCClient.callAsync(this.listener2, "SearchSubtitles", str, jSONArray, jSONObject2));
    }

    public void getSRTs(String str) {
        String str2 = this.TAG;
        MLog.d(str2, "1111onResponse  : " + str);
        showLoading();
    }
}
