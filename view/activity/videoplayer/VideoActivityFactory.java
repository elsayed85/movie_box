package com.movieboxpro.android.view.activity.videoplayer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Color;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.util.Pair;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import butterknife.BindView;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.avery.subtitle.model.Subtitle;
import com.dueeeke.model.EncodeModel;
import com.dueeeke.model.ExtrModel;
import com.dueeeke.model.MediaQualityInfo;
import com.dueeeke.model.ResponseUploadExtraSubtitle;
import com.dueeeke.model.SRTModel;
import com.dueeeke.model.SrtPraseModel;
import com.dueeeke.model.SubTitleFeedbackModel;
import com.dueeeke.videoplayer.listener.PlayerEventListener;
import com.dueeeke.videoplayer.player.IjkPlayer;
import com.dueeeke.videoplayer.player.PlayerConfig;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.framework.CastButtonFactory;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.jaredrummler.android.colorpicker.ColorPickerDialog;
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener;
import com.movieboxpro.android.BuildConfig;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.AppManager;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.app.RxManager;
import com.movieboxpro.android.base.BaseActivity2;
import com.movieboxpro.android.base.BaseObserver;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.config.ConfigKey;
import com.movieboxpro.android.config.ConfigUtils;
import com.movieboxpro.android.db.dao.DownloadDao;
import com.movieboxpro.android.db.entity.PlayRecode;
import com.movieboxpro.android.event.CancelShowTestSpeedEvent;
import com.movieboxpro.android.event.FloatEvent;
import com.movieboxpro.android.event.LeCastDeviceFindEvent;
import com.movieboxpro.android.event.OnCastStartEvent;
import com.movieboxpro.android.event.OnSwitchServerEvent;
import com.movieboxpro.android.event.TranslateSubtitleEvent;
import com.movieboxpro.android.event.UpdateSkipEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.APIService;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.CallManager;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.http.HttpUploadRequest;
import com.movieboxpro.android.http.HttpUtils;
import com.movieboxpro.android.http.ServerException;
import com.movieboxpro.android.listener.VideoPreviewListener;
import com.movieboxpro.android.livedata.RefreshWaitingLiveData;
import com.movieboxpro.android.livedata.RefreshWatchedLiveData;
import com.movieboxpro.android.livedata.RendererLiveData;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.BaseResponse;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.FileMd5CheckModel;
import com.movieboxpro.android.model.SkipTimeResponse;
import com.movieboxpro.android.model.TranscodeResponse;
import com.movieboxpro.android.model.TvSeasonList;
import com.movieboxpro.android.model.common.Srt;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.service.SubtitleDownloadService;
import com.movieboxpro.android.timroes.axmlrpc.XMLRPCCallback;
import com.movieboxpro.android.timroes.axmlrpc.XMLRPCClient;
import com.movieboxpro.android.timroes.axmlrpc.XMLRPCException;
import com.movieboxpro.android.timroes.axmlrpc.XMLRPCServerException;
import com.movieboxpro.android.utils.Api;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.EventUtils;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.utils.LogUtils;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.ReaderUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SettingManager;
import com.movieboxpro.android.utils.SrtParser;
import com.movieboxpro.android.utils.StringUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.utils.tool.NetworkUtils;
import com.movieboxpro.android.view.activity.PreviewSubtitleDialog;
import com.movieboxpro.android.view.activity.Video.ExpandedControlsActivity;
import com.movieboxpro.android.view.activity.exoplayer.controller.ExoNormalController;
import com.movieboxpro.android.view.activity.exoplayer.player.NormalExoVideoView;
import com.movieboxpro.android.view.activity.settings.TestSpeedActivity;
import com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory;
import com.movieboxpro.android.view.activity.videoplayer.cast.DLNACastActivity;
import com.movieboxpro.android.view.activity.videoplayer.controller.NormalController;
import com.movieboxpro.android.view.activity.videoplayer.presenter.NormalPlayerPresenter;
import com.movieboxpro.android.view.activity.videoplayer.presenter.VideoPresenter;
import com.movieboxpro.android.view.activity.videoplayer.videoview.NormalIjkVideoView;
import com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl;
import com.movieboxpro.android.view.activity.vlcvideoplayer.listener.CallBack;
import com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback;
import com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController;
import com.movieboxpro.android.view.dialog.ChooseCastAppDialog;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.MsgHintDialog;
import com.movieboxpro.android.view.dialog.PreviewLocalSubtitleDialog;
import com.movieboxpro.android.view.dialog.RemindChangeVoiceDialog;
import com.movieboxpro.android.view.dialog.ScreenManageDialog;
import com.movieboxpro.android.view.fragment.TranscodeSubtitleFragment;
import com.movieboxpro.android.view.videocontroller.PopPlayerManager;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Triple;
import master.flame.danmaku.danmaku.parser.IDataSource;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.videolan.libvlc.RendererItem;
import org.videolan.libvlc.interfaces.IMedia;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.misc.IjkTrackInfo;
/* loaded from: classes.dex */
public abstract class VideoActivityFactory extends BaseActivity2 implements VideoPresenter, ColorPickerDialogListener {
    public static final String IS_LOCAL_FILE = "is_local_file";
    public static final int RE_CODE_TEST_SPEED = 1;
    public static final int START_DELAY = 1000;
    public static final int UPLOAD_DELAY = 300000;
    public static final int UPLOAD_SELECT_DELAY_INFO = 1000;
    private static HomeWatcherReceiver mHomeKeyReceiver;
    public static RendererLiveData rendererLiveData;
    protected PlayerController controller;
    private String currLocalFileSubtitleContent;
    private MediaQualityInfo currMediaQualityInfo;
    private String currOpenSubtitleContent;
    private String currOpenSubtitleLang;
    private String currSubtitleContent;
    private String currSubtitleLang;
    private ScreenManageDialog dialog;
    protected int episode;
    protected ExoNormalController exoNormalController;
    protected NormalExoVideoView exoVideoView;
    private ResponseUploadExtraSubtitle extraSubtitle;
    private String extraSubtitlePath;
    private Disposable feedbackDisposable;
    protected int fid;
    @BindView(R.id.flContainer)
    FrameLayout flContainer;
    @BindView(R.id.flPreview)
    FrameLayout flPreview;
    private IjkPlayer ijkPlayer;
    protected NormalController ijkPlayerController;
    protected NormalPlayerPresenter ijkPlayerPresenter;
    protected NormalIjkVideoView ijkVideoView;
    private boolean isAdLoad;
    protected boolean isComplete;
    protected boolean isLoadSubtitle;
    public String language;
    private int lastPos;
    private BaseResponse<DeviceModelResponse> lastResponse;
    private String lastSelectSid;
    private File localSubtitleFile;
    private String localSubtitleLang;
    private CastContext mCastContext;
    private CastSession mCastSession;
    private MediaInfo mSelectedMedia;
    private SessionManagerListener<CastSession> mSessionManagerListener;
    protected BaseMediaModel movieDownload;
    @BindView(R.id.player_container)
    FrameLayout playerContainer;
    private PopupMenu popupMenu;
    protected NormalIjkVideoView previewIjkVideoView;
    protected boolean previewVideoPrepared;
    public ExtrModel qualitys;
    private Disposable saveFeedbackDisposable;
    protected int season;
    private boolean showMuteHint;
    protected SkipTimeResponse skipTime;
    public List<Srt> srtLists;
    private File srtZipFile;
    private String subtitle_path;
    private Disposable timeDisposable;
    private Disposable timerLikeDisposable;
    private long timerLikeDuration;
    public String token;
    protected com.movieboxpro.android.view.activity.vlcvideoplayer.controller.NormalController vlcPlayerController;
    protected com.movieboxpro.android.view.activity.vlcvideoplayer.videoview.NormalIjkVideoView vlcVideoView;
    protected List<TvSeasonList> tvSeasonListList = new ArrayList();
    protected int currPlayingPosition = 0;
    List<MediaQualityInfo> list = new ArrayList();
    LinkedHashMap<String, List<SRTModel.SubTitles>> srtHash = new LinkedHashMap<>();
    private String lastServerDomain = "";
    private boolean firstPlay = true;
    private boolean havePop = false;
    private Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable seekRunnable = new Runnable() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.33
        @Override // java.lang.Runnable
        public void run() {
            if (VideoActivityFactory.this.previewVideoPrepared) {
                VideoActivityFactory.this.previewIjkVideoView.seekTo(VideoActivityFactory.this.lastPos * 1000);
            }
        }
    };
    private final Runnable showPreview = new Runnable() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.34
        @Override // java.lang.Runnable
        public void run() {
            VideoActivityFactory.this.flPreview.setVisibility(0);
        }
    };
    private Runnable uploadFileSubtitle = new Runnable() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.36
        @Override // java.lang.Runnable
        public void run() {
            if (VideoActivityFactory.this.localSubtitleFile != null) {
                VideoActivityFactory videoActivityFactory = VideoActivityFactory.this;
                videoActivityFactory.checkFileMd5(videoActivityFactory.localSubtitleFile.getPath(), 2);
            }
        }
    };
    public Runnable checkMd5 = new Runnable() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.43
        @Override // java.lang.Runnable
        public void run() {
            if (VideoActivityFactory.this.qualitys == null || VideoActivityFactory.this.subtitle_path == null) {
                return;
            }
            VideoActivityFactory videoActivityFactory = VideoActivityFactory.this;
            videoActivityFactory.checkFileMd5(videoActivityFactory.subtitle_path, 1);
        }
    };
    public Runnable uploadOpenSubtitleFile = new Runnable() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.44
        @Override // java.lang.Runnable
        public void run() {
            if (VideoActivityFactory.this.qualitys != null) {
                VideoActivityFactory videoActivityFactory = VideoActivityFactory.this;
                videoActivityFactory.updateSrt2(videoActivityFactory.qualitys);
            }
        }
    };
    private Runnable uploadSelectDelayInfoRunnable = new Runnable() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.45
        @Override // java.lang.Runnable
        public void run() {
            if (VideoActivityFactory.this.extraSubtitle == null || VideoActivityFactory.this.extraSubtitle.getSrt_info() == null) {
                return;
            }
            String sid = VideoActivityFactory.this.extraSubtitle.getSrt_info().getSid();
            if (VideoActivityFactory.this.controller != null) {
                VideoActivityFactory videoActivityFactory = VideoActivityFactory.this;
                videoActivityFactory.uploadDelayInfo(sid, videoActivityFactory.controller.getSrtspeed());
                VideoActivityFactory videoActivityFactory2 = VideoActivityFactory.this;
                videoActivityFactory2.extraSubtitlePath = videoActivityFactory2.extraSubtitle.getSrt_info().file_path;
                VideoActivityFactory videoActivityFactory3 = VideoActivityFactory.this;
                videoActivityFactory3.get_srt_select(videoActivityFactory3.extraSubtitlePath, sid);
                VideoActivityFactory.this.controller.saveDelayExtraSubtitleRecord(sid);
            }
        }
    };
    public boolean hasSubtitle = true;
    XMLRPCCallback listener = new XMLRPCCallback() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.50
        @Override // com.movieboxpro.android.timroes.axmlrpc.XMLRPCCallback
        public void onResponse(long j, Object obj) {
            JSONObject jSONObject = (JSONObject) JSONObject.parse(obj.toString());
            if (jSONObject.containsKey("token")) {
                VideoActivityFactory.this.token = jSONObject.getString("token");
            }
            VideoActivityFactory videoActivityFactory = VideoActivityFactory.this;
            videoActivityFactory.getSubtitle(videoActivityFactory.token);
            String str = VideoActivityFactory.this.TAG;
            MLog.d(str, "1111onResponse  :" + obj.toString() + VideoActivityFactory.this.token);
        }

        @Override // com.movieboxpro.android.timroes.axmlrpc.XMLRPCCallback
        public void onError(long j, XMLRPCException xMLRPCException) {
            VideoActivityFactory.this.runOnUiThread(new Runnable() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.50.1
                @Override // java.lang.Runnable
                public void run() {
                    VideoActivityFactory.this.videHideloading();
                }
            });
            VideoActivityFactory.this.showToast(xMLRPCException.getMessage());
            String str = VideoActivityFactory.this.TAG;
            MLog.d(str, "1111onError   : " + xMLRPCException.getMessage() + j);
        }

        @Override // com.movieboxpro.android.timroes.axmlrpc.XMLRPCCallback
        public void onServerError(long j, XMLRPCServerException xMLRPCServerException) {
            VideoActivityFactory.this.runOnUiThread(new Runnable() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.50.2
                @Override // java.lang.Runnable
                public void run() {
                    VideoActivityFactory.this.videHideloading();
                }
            });
            VideoActivityFactory.this.showToast(xMLRPCServerException.getMessage());
            String str = VideoActivityFactory.this.TAG;
            MLog.d(str, "1111onServerError   : " + xMLRPCServerException.getMessage() + j);
        }
    };
    XMLRPCCallback listener2 = new AnonymousClass51();
    private String afterTransCodeName = "";
    public Handler mHandler = new Handler();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ResponseBody lambda$requestFeedbackVoteInfo$10(Throwable th) throws Exception {
        return null;
    }

    private void startLeCastService() {
    }

    private void stopLeCastService() {
    }

    public abstract void _geiPrama();

    @Override // com.movieboxpro.android.view.activity.videoplayer.presenter.VideoPresenter
    public void _initAds() {
    }

    protected abstract int boxType();

    public String buildData(ExtrModel extrModel) {
        return null;
    }

    public String buildDatas(ExtrModel extrModel) {
        return null;
    }

    protected void desSelectSubtitle(String str) {
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void findCastDevice(LeCastDeviceFindEvent leCastDeviceFindEvent) {
    }

    public abstract void getSrt(MediaQualityInfo mediaQualityInfo);

    public abstract void get_Movie_select(int i, int i2, String str);

    public abstract void get_srt_select(String str, String str2);

    @Override // com.movieboxpro.android.base.BaseActivity2
    protected boolean isDialogStyle() {
        return true;
    }

    @Override // com.movieboxpro.android.base.BaseActivity2
    protected boolean isRequireNetWork() {
        return false;
    }

    @Override // com.movieboxpro.android.base.BaseActivity2
    protected boolean isShowWarning() {
        return false;
    }

    @Override // com.movieboxpro.android.base.BaseActivity2
    protected boolean isStatusBarTint() {
        return false;
    }

    @Override // com.jaredrummler.android.colorpicker.ColorPickerDialogListener
    public void onDialogDismissed(int i) {
    }

    public abstract void saveInDao(int i, int i2);

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showFloat(FloatEvent floatEvent) {
    }

    public void startFloatWindow(View view) {
    }

    protected void startProgressTimer() {
    }

    protected abstract void switchServer();

    protected void videoPlayComplete() {
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public View loadView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.activity_normal_player, (ViewGroup) null);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initView() {
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.FULLSCREEN_PLAY, true) && CommonUtils.isScreenLandscape(this)) {
            FrameLayout frameLayout = this.playerContainer;
            if (frameLayout != null) {
                frameLayout.post(new Runnable() { // from class: com.movieboxpro.android.view.activity.videoplayer.-$$Lambda$VideoActivityFactory$N_uYerqpXrzvj1uG7j9zGM42VDk
                    @Override // java.lang.Runnable
                    public final void run() {
                        VideoActivityFactory.this.lambda$initView$0$VideoActivityFactory();
                    }
                });
                return;
            }
            return;
        }
        switchFullscreen(false);
    }

    public /* synthetic */ void lambda$initView$0$VideoActivityFactory() {
        switchFullscreen(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openPopMenu() {
        PopupMenu popupMenu = new PopupMenu(this, this.controller.getCastButton());
        this.popupMenu = popupMenu;
        popupMenu.inflate(R.menu.cast_menu);
        this.popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.1
            @Override // androidx.appcompat.widget.PopupMenu.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem menuItem) {
                MediaQualityInfo currDefinitionItem;
                if (menuItem.getItemId() == R.id.originCast) {
                    DLNACastActivity.Companion.start(VideoActivityFactory.this.context, VideoActivityFactory.this.ijkPlayerPresenter.getUrl(), VideoActivityFactory.this.boxType() == 1 ? VideoActivityFactory.this.movieDownload.title : String.format("%s S%s E%s", VideoActivityFactory.this.movieDownload.title, Integer.valueOf(VideoActivityFactory.this.season), Integer.valueOf(VideoActivityFactory.this.episode)));
                } else if (menuItem.getItemId() == R.id.otherCast && (currDefinitionItem = VideoActivityFactory.this.ijkPlayerPresenter.getCurrDefinitionItem()) != null) {
                    ChooseCastAppDialog.Companion.newInstance(currDefinitionItem.getPath()).show(VideoActivityFactory.this.getSupportFragmentManager(), ChooseCastAppDialog.class.getSimpleName());
                }
                return false;
            }
        });
        this.popupMenu.show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setTranslateSubtitle(TranslateSubtitleEvent translateSubtitleEvent) {
        PlayerController playerController = this.controller;
        if (playerController != null) {
            playerController.showTranslateSubtitle(translateSubtitleEvent.getSubtitles());
        }
    }

    private void initPlayerEngine(int i, int i2) {
        if (i == 1) {
            NormalIjkVideoView normalIjkVideoView = new NormalIjkVideoView(this);
            this.ijkVideoView = normalIjkVideoView;
            this.ijkPlayerPresenter = normalIjkVideoView;
            this.playerContainer.addView(normalIjkVideoView);
            if (getIntent().getBooleanExtra(IS_LOCAL_FILE, false)) {
                this.controller.getMediaButton().setVisibility(8);
                this.controller.setCastViewGone();
                this.ijkPlayerController.setLocalFile(true);
            }
            this.ijkPlayer = new IjkPlayer(this.activity) { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.2
                @Override // com.dueeeke.videoplayer.player.IjkPlayer, com.dueeeke.videoplayer.player.AbstractPlayer
                public void setOptions() {
                    this.mMediaPlayer.setOption(1, "analyzemaxduration", 100L);
                    this.mMediaPlayer.setOption(1, "flush_packets", 1L);
                    this.mMediaPlayer.setOption(4, "packet-buffering", 0L);
                    this.mMediaPlayer.setOption(4, "reconnect", 3L);
                    this.mMediaPlayer.setOption(4, "framedrop", 5L);
                    this.mMediaPlayer.setOption(4, "soundtouch", 1L);
                    this.mMediaPlayer.setOption(2, "skip_loop_filter", 48L);
                    this.mMediaPlayer.setOption(4, "framedrop", 12L);
                    this.mMediaPlayer.setOption(4, "min-frames", 2L);
                    this.mMediaPlayer.setOption(1, "dns_cache_clear", 1L);
                    this.mMediaPlayer.setOption(1, "dns_cache_timeout", -1L);
                }
            };
            this.ijkPlayerPresenter.setPlayerConfig(new PlayerConfig.Builder().setCustomMediaPlayer(this.ijkPlayer).autoRotate().enableMediaCodec(true).savingProgress().build());
            this.ijkPlayerPresenter.setVideoController(this.ijkPlayerController);
            this.ijkPlayerPresenter.setCallBack(new CallBack() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.3
                @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.CallBack
                public void onSeekComplete(long j, long j2) {
                }

                @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.CallBack
                public void setQuality(MediaQualityInfo mediaQualityInfo) {
                    VideoActivityFactory.this.fid = mediaQualityInfo.getFid();
                    if (VideoActivityFactory.this.controller != null) {
                        VideoActivityFactory.this.controller.setQualityController();
                    }
                    if (VideoActivityFactory.this.isLoadSubtitle) {
                        return;
                    }
                    VideoActivityFactory.this.getSrt(mediaQualityInfo);
                    VideoActivityFactory.this.currMediaQualityInfo = mediaQualityInfo;
                    VideoActivityFactory.this.isLoadSubtitle = true;
                }
            });
        } else if (i == 3) {
            NormalExoVideoView normalExoVideoView = new NormalExoVideoView(this);
            this.exoVideoView = normalExoVideoView;
            this.ijkPlayerPresenter = normalExoVideoView;
            this.playerContainer.addView(normalExoVideoView);
            if (getIntent().getBooleanExtra(IS_LOCAL_FILE, false)) {
                this.controller.setCastViewGone();
                this.exoNormalController.setLocalFile(true);
            }
            this.ijkPlayerPresenter.setExoVideoController(this.exoNormalController);
            this.ijkPlayerPresenter.setCallBack(new CallBack() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.4
                @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.CallBack
                public void onSeekComplete(long j, long j2) {
                }

                @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.CallBack
                public void setQuality(MediaQualityInfo mediaQualityInfo) {
                    VideoActivityFactory.this.fid = mediaQualityInfo.getFid();
                    if (VideoActivityFactory.this.controller != null) {
                        VideoActivityFactory.this.controller.setQualityController();
                    }
                    if (VideoActivityFactory.this.isLoadSubtitle) {
                        return;
                    }
                    VideoActivityFactory.this.getSrt(mediaQualityInfo);
                    VideoActivityFactory.this.currMediaQualityInfo = mediaQualityInfo;
                    VideoActivityFactory.this.isLoadSubtitle = true;
                }
            });
        } else {
            com.movieboxpro.android.view.activity.vlcvideoplayer.videoview.NormalIjkVideoView normalIjkVideoView2 = new com.movieboxpro.android.view.activity.vlcvideoplayer.videoview.NormalIjkVideoView(this);
            this.vlcVideoView = normalIjkVideoView2;
            this.ijkPlayerPresenter = normalIjkVideoView2;
            this.playerContainer.addView(normalIjkVideoView2);
            if (rendererLiveData == null) {
                rendererLiveData = new RendererLiveData();
            }
            rendererLiveData.observe(this, new Observer() { // from class: com.movieboxpro.android.view.activity.videoplayer.-$$Lambda$VideoActivityFactory$VZnnm7K9ubjP1sfJ7NJHOIlZhp4
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    VideoActivityFactory.this.lambda$initPlayerEngine$1$VideoActivityFactory((RendererItem) obj);
                }
            });
            if (getIntent().getBooleanExtra(IS_LOCAL_FILE, false)) {
                this.controller.setCastViewGone();
                this.vlcPlayerController.setLocalFile(true);
            }
            this.ijkPlayerPresenter.setVlcVideoController(this.vlcPlayerController);
            this.ijkPlayerPresenter.setCallBack(new CallBack() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.5
                @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.CallBack
                public void onSeekComplete(long j, long j2) {
                }

                @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.CallBack
                public void setQuality(MediaQualityInfo mediaQualityInfo) {
                    VideoActivityFactory.this.fid = mediaQualityInfo.getFid();
                    if (VideoActivityFactory.this.controller != null) {
                        VideoActivityFactory.this.controller.setQualityController();
                    }
                    if (VideoActivityFactory.this.isLoadSubtitle) {
                        return;
                    }
                    VideoActivityFactory.this.getSrt(mediaQualityInfo);
                    VideoActivityFactory.this.currMediaQualityInfo = mediaQualityInfo;
                    VideoActivityFactory.this.isLoadSubtitle = true;
                }
            });
        }
        PlayRecode inDao = getInDao();
        if (i2 != -1 && inDao != null) {
            inDao.setQuality(i2);
        }
        if (inDao == null) {
            inDao = new PlayRecode();
            if (App.getUserData().getIsvip() == 1) {
                inDao.setQuality(1);
            } else {
                inDao.setQuality(-1);
            }
        }
        if (this.movieDownload.box_type == 1) {
            this.ijkPlayerPresenter.setDefinitionVideos(this.list, inDao, this.movieDownload.seconds);
        } else {
            int i3 = this.currPlayingPosition;
            if (i3 >= 0 && i3 < this.tvSeasonListList.size()) {
                this.ijkPlayerPresenter.setDefinitionVideos(this.list, inDao, this.tvSeasonListList.get(this.currPlayingPosition).getOver() == 1 ? 1 : (int) this.tvSeasonListList.get(this.currPlayingPosition).getSeconds());
            } else {
                this.ijkPlayerPresenter.setDefinitionVideos(this.list, inDao, 0);
            }
        }
        BaseMediaModel baseMediaModel = this.movieDownload;
        if (baseMediaModel != null) {
            if (baseMediaModel.box_type == 2) {
                this.ijkPlayerPresenter.setTitle(String.format("S%s E%s %s", Integer.valueOf(this.movieDownload.season), Integer.valueOf(this.movieDownload.episode), this.movieDownload.title));
            } else {
                this.ijkPlayerPresenter.setTitle(this.movieDownload.title);
            }
        }
        this.ijkPlayerPresenter.start();
    }

    public /* synthetic */ void lambda$initPlayerEngine$1$VideoActivityFactory(RendererItem rendererItem) {
        if (rendererItem != null) {
            PlayerController playerController = this.controller;
            if (playerController != null) {
                playerController.showCastingHolder(this.movieDownload.poster);
                return;
            }
            return;
        }
        PlayerController playerController2 = this.controller;
        if (playerController2 != null) {
            playerController2.hideCastingHolder();
        }
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initData() {
        _geiPrama();
        this.lastServerDomain = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_STATE, "");
        _initQuality();
        boolean booleanExtra = getIntent().getBooleanExtra(IS_LOCAL_FILE, false);
        int i = PrefsUtils.getInstance().getInt(Constant.Prefs.PLAYER_ENGINE, 0);
        PlayRecode inDao = getInDao();
        int quality = inDao != null ? inDao.getQuality() : -1;
        String string = PrefsUtils.getInstance().getString(Constant.Prefs.LAST_SELECT_QUALITY, "");
        if (TextUtils.isEmpty(string)) {
            string = App.getUserData().getIsvip() == 1 ? "1080p" : "360p";
        }
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.REMEMBER_ORG_QUALITY, false)) {
            int i2 = 0;
            while (true) {
                if (i2 >= this.list.size()) {
                    break;
                } else if (string.equals(this.list.get(i2).getReal_quality())) {
                    quality = i2;
                    break;
                } else {
                    i2++;
                }
            }
            if (PrefsUtils.getInstance().getInt(Constant.Prefs.IS_LAST_ORIGIN, 0) == 1) {
                if (i == 0) {
                    initController(2);
                    initPlayerEngine(2, quality);
                } else if (i == 1) {
                    initController(1);
                    initPlayerEngine(1, quality);
                } else {
                    initController(i);
                    initPlayerEngine(i, quality);
                }
            } else if (i == 0) {
                initController(3);
                initPlayerEngine(3, quality);
            } else if (i == 1) {
                initController(1);
                initPlayerEngine(1, quality);
            } else {
                initController(i);
                initPlayerEngine(i, quality);
            }
        } else if (PrefsUtils.getInstance().getInt(Constant.Prefs.IS_LAST_ORIGIN, 0) == 1) {
            if (i == 0) {
                initController(2);
                initPlayerEngine(2, quality);
            } else if (i == 1) {
                initController(1);
                initPlayerEngine(1, quality);
            } else {
                initController(i);
                initPlayerEngine(i, quality);
            }
        } else if (i == 0) {
            initController(3);
            initPlayerEngine(3, quality);
        } else if (i == 1) {
            initController(1);
            initPlayerEngine(1, quality);
        } else {
            initController(i);
            initPlayerEngine(i, quality);
        }
        if (!booleanExtra) {
            _initChromCast();
        }
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.AUTO_LANDSCAPE, false)) {
            setRequestedOrientation(0);
        }
        if (boxType() == 2) {
            getSaveSkipTime();
        }
        if (booleanExtra) {
            getVideoQuality();
        }
    }

    private void getVideoQuality() {
        String str;
        String str2;
        if (boxType() == 1) {
            String string = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, "");
            if (!TextUtils.isEmpty(string) && "0".equalsIgnoreCase(string)) {
                str2 = "";
                str = str2;
            } else {
                str = string;
                str2 = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
            }
            ((ObservableSubscribeProxy) Http.getService().Movie_detail(API.BASE_URL, API.Movie.MOVE_DOWNLAOD, App.isLogin() ? App.getUserData().uid_v2 : "", this.movieDownload.id, "", str2, str).compose(RxUtils.rxTranslate2Bean(MovieDetail.class)).map(new Function<MovieDetail, MovieDetail>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.7
                @Override // io.reactivex.functions.Function
                public MovieDetail apply(MovieDetail movieDetail) throws Exception {
                    VideoActivityFactory.this.movieDownload.list.addAll(movieDetail.list);
                    return movieDetail;
                }
            }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new io.reactivex.Observer<MovieDetail>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.6
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
                public void onNext(MovieDetail movieDetail) {
                    VideoActivityFactory.this._initQuality();
                    VideoActivityFactory.this.controller.resetDefinition(VideoActivityFactory.this.list);
                }
            });
            return;
        }
        ((ObservableSubscribeProxy) Http.getService().Tv_detail(API.BASE_URL, API.Tv.TV_DETAIL, App.isLogin() ? App.getUserData().uid_v2 : "", this.movieDownload.id, App.deviceLang, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE).compose(RxUtils.rxTranslate2Bean(TvDetail.class)).flatMap(new Function<TvDetail, Observable<TvDetail>>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.9
            @Override // io.reactivex.functions.Function
            public Observable<TvDetail> apply(final TvDetail tvDetail) throws Exception {
                String str3;
                String str4;
                String string2 = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, "");
                if (!TextUtils.isEmpty(string2) && "0".equalsIgnoreCase(string2)) {
                    str4 = "";
                    str3 = str4;
                } else {
                    str3 = string2;
                    str4 = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
                }
                return Http.getService().TV_downloadurl(API.BASE_URL, API.Tv.TV_DOWNLAODURL, App.isLogin() ? App.getUserData().uid_v2 : "", VideoActivityFactory.this.movieDownload.id, String.valueOf(VideoActivityFactory.this.season), String.valueOf(VideoActivityFactory.this.episode), str4, str3).compose(RxUtils.rxTranslate2Bean(BaseMediaModel.class)).map(new Function<BaseMediaModel, TvDetail>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.9.1
                    @Override // io.reactivex.functions.Function
                    public TvDetail apply(BaseMediaModel baseMediaModel) throws Exception {
                        VideoActivityFactory.this.movieDownload.list.addAll(baseMediaModel.list);
                        return tvDetail;
                    }
                });
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new io.reactivex.Observer<TvDetail>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.8
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
            public void onNext(TvDetail tvDetail) {
                VideoActivityFactory.this._initQuality();
                VideoActivityFactory.this.controller.resetDefinition(VideoActivityFactory.this.list);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void getSaveSkipTime() {
        ((ObservableSubscribeProxy) HttpRequest.post(Api.INSTANCE.getSkipTime(this.movieDownload.id, this.season, this.episode), this).asRequest().compose(RxUtils.rxTranslate2Bean(SkipTimeResponse.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<SkipTimeResponse>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.10
            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(SkipTimeResponse skipTimeResponse) {
                VideoActivityFactory.this.skipTime = skipTimeResponse;
                boolean z = PrefsUtils.getInstance().getBoolean(Constant.Prefs.SKIP_OPENING_ENDING, true);
                if (VideoActivityFactory.this.firstPlay || !z) {
                    return;
                }
                VideoActivityFactory.this.checkSkipTime();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkSkipTime() {
        if (this.skipTime == null || boxType() != 2) {
            return;
        }
        if ("other".equals(this.skipTime.getStart_type())) {
            long currentPosition = this.controller.getMediaPlayer().getCurrentPosition();
            if (this.skipTime.getStart() == 0 || currentPosition >= this.skipTime.getStart() * 1000) {
                return;
            }
            this.controller.getMediaPlayer().seekTo(this.skipTime.getStart() * 1000);
            PlayerController playerController = this.controller;
            int start = this.skipTime.getStart();
            String str = this.movieDownload.id;
            playerController.showRecommendSkipTime(start, true, str, this.fid + "", this.season, this.episode, getSupportFragmentManager(), new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.11
                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                public void onClick() {
                    ((ObservableSubscribeProxy) HttpRequest.post(Api.INSTANCE.saveSkipTime(VideoActivityFactory.this.movieDownload.id, VideoActivityFactory.this.season, VideoActivityFactory.this.episode, "add", VideoActivityFactory.this.skipTime.getStart(), -1), VideoActivityFactory.this).asRequest().compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(VideoActivityFactory.this))).subscribe(new io.reactivex.Observer<String>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.11.1
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
                    ((ObservableSubscribeProxy) HttpRequest.post(Api.INSTANCE.voteSkipTime(VideoActivityFactory.this.movieDownload.id, VideoActivityFactory.this.season, VideoActivityFactory.this.episode, VideoActivityFactory.this.skipTime.getStart(), -1), VideoActivityFactory.this).asRequest().compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(VideoActivityFactory.this))).subscribe(new io.reactivex.Observer<String>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.11.2
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
        } else if ("my".equals(this.skipTime.getStart_type())) {
            ((ObservableSubscribeProxy) Observable.timer(500L, TimeUnit.MILLISECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new io.reactivex.Observer<Long>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.12
                @Override // io.reactivex.Observer
                public void onError(Throwable th) {
                }

                @Override // io.reactivex.Observer
                public void onNext(Long l) {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable disposable) {
                }

                @Override // io.reactivex.Observer
                public void onComplete() {
                    long currentPosition2 = VideoActivityFactory.this.controller.getMediaPlayer().getCurrentPosition();
                    if (VideoActivityFactory.this.skipTime.getStart() == 0 || currentPosition2 >= VideoActivityFactory.this.skipTime.getStart() * 1000) {
                        return;
                    }
                    VideoActivityFactory.this.controller.getMediaPlayer().seekTo(VideoActivityFactory.this.skipTime.getStart() * 1000);
                    PlayerController playerController2 = VideoActivityFactory.this.controller;
                    int start2 = VideoActivityFactory.this.skipTime.getStart();
                    String str2 = VideoActivityFactory.this.movieDownload.id;
                    playerController2.showSkipTime(start2, true, str2, VideoActivityFactory.this.fid + "", VideoActivityFactory.this.season, VideoActivityFactory.this.episode, VideoActivityFactory.this.getSupportFragmentManager());
                }
            });
        }
    }

    public void changePlayerEngine(int i) {
        this.isLoadSubtitle = false;
        CastContext castContext = this.mCastContext;
        if (castContext != null) {
            castContext.getSessionManager().removeSessionManagerListener(this.mSessionManagerListener, CastSession.class);
        }
        saveRecord(true);
        NormalPlayerPresenter normalPlayerPresenter = this.ijkPlayerPresenter;
        int definition = normalPlayerPresenter != null ? normalPlayerPresenter.getDefinition() : -1;
        this.playerContainer.removeAllViews();
        NormalPlayerPresenter normalPlayerPresenter2 = this.ijkPlayerPresenter;
        if (normalPlayerPresenter2 != null) {
            normalPlayerPresenter2.release();
        }
        PlayerController playerController = this.controller;
        if (playerController != null) {
            playerController.destroy();
        }
        initController(i);
        initPlayerEngine(i, definition);
        CastContext castContext2 = this.mCastContext;
        if (castContext2 != null) {
            castContext2.getSessionManager().addSessionManagerListener(this.mSessionManagerListener, CastSession.class);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updateVideoTitle() {
        BaseMediaModel baseMediaModel;
        if (this.ijkPlayerPresenter == null || (baseMediaModel = this.movieDownload) == null) {
            return;
        }
        if (baseMediaModel.box_type == 2) {
            this.controller.updateTitle(String.format("S%s E%s %s", Integer.valueOf(this.movieDownload.season), Integer.valueOf(this.episode), this.movieDownload.title));
        } else {
            this.controller.updateTitle(this.movieDownload.title);
        }
    }

    @Override // com.movieboxpro.android.base.BaseActivity2, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        CastContext castContext = this.mCastContext;
        if (castContext != null) {
            castContext.getSessionManager().removeSessionManagerListener(this.mSessionManagerListener, CastSession.class);
        }
        deletePlayingStatus();
        saveRecord(false);
        this.ijkPlayerPresenter.pause();
        super.onPause();
    }

    private void startPlayingFeedbackTimer() {
        Disposable disposable = this.feedbackDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        savePlayingFeedback();
        ((ObservableSubscribeProxy) Observable.interval(60L, TimeUnit.SECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new io.reactivex.Observer<Long>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.13
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable2) {
                VideoActivityFactory.this.feedbackDisposable = disposable2;
            }

            @Override // io.reactivex.Observer
            public void onNext(Long l) {
                if (Network.isConnected(VideoActivityFactory.this)) {
                    VideoActivityFactory.this.savePlayingFeedback();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void savePlayingFeedback() {
        ((ObservableSubscribeProxy) Http.getService().playingFeedback(API.BASE_URL, API.Common.PLAYING_FEEDBACK, App.getUserData().uid_v2, this.movieDownload.id, SystemUtils.getUniqueId(this), this.movieDownload.box_type, this.movieDownload.season, this.movieDownload.episode, Build.BRAND, Build.MODEL).map(new Function<String, Boolean>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.15
            @Override // io.reactivex.functions.Function
            public Boolean apply(String str) throws Exception {
                BaseResponse baseResponse = (BaseResponse) JSON.parseObject(str, RxUtils.buildType(BaseResponse.class, DeviceModelResponse.class), new Feature[0]);
                if (baseResponse.getCode() == -88) {
                    if (VideoActivityFactory.this.dialog == null) {
                        VideoActivityFactory.this.showScreenManageDialog(baseResponse);
                    } else if (VideoActivityFactory.this.dialog.getDialog() == null) {
                        VideoActivityFactory.this.showScreenManageDialog(baseResponse);
                    } else if (!VideoActivityFactory.this.dialog.getDialog().isShowing()) {
                        VideoActivityFactory.this.showScreenManageDialog(baseResponse);
                    }
                    return true;
                }
                return false;
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<Boolean>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.14
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                VideoActivityFactory.this.saveFeedbackDisposable = disposable;
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(Boolean bool) {
                if (bool.booleanValue() && VideoActivityFactory.this.ijkPlayerPresenter != null) {
                    VideoActivityFactory.this.ijkPlayerPresenter.pause();
                }
                LogUtils.INSTANCE.logD("保存正在播放状态");
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException apiException) {
                LogUtils logUtils = LogUtils.INSTANCE;
                logUtils.logD("保存正在播放状态失败:" + apiException.getMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showScreenManageDialog(BaseResponse<DeviceModelResponse> baseResponse) {
        this.lastResponse = baseResponse;
        ScreenManageDialog newInstance = ScreenManageDialog.newInstance(new ArrayList(baseResponse.getData().getDevice_list()), baseResponse.getMsg(), false);
        this.dialog = newInstance;
        newInstance.setExitListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.16
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public void onClick() {
                VideoActivityFactory.this.finish();
            }
        });
        this.dialog.setListener(new ScreenManageDialog.OnStopDeviceListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.17
            @Override // com.movieboxpro.android.view.dialog.ScreenManageDialog.OnStopDeviceListener
            public void onStopDevice() {
                VideoActivityFactory.this.savePlayingFeedback();
                if (VideoActivityFactory.this.ijkPlayerPresenter != null) {
                    VideoActivityFactory.this.ijkPlayerPresenter.start();
                }
            }
        });
        this.dialog.show(getSupportFragmentManager(), ScreenManageDialog.class.getSimpleName());
    }

    private void deletePlayingStatus() {
        Disposable disposable = this.saveFeedbackDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        Disposable disposable2 = this.feedbackDisposable;
        if (disposable2 != null) {
            disposable2.dispose();
        }
        Http.getService().deletePlayingStatus(API.BASE_URL, API.Common.DELETE_PLAYING_STATUS, SystemUtils.getUniqueId(this), App.getUserData().uid_v2).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).subscribe(new io.reactivex.Observer<String>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.18
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable3) {
            }

            @Override // io.reactivex.Observer
            public void onNext(String str) {
                LogUtils.INSTANCE.logD("删除正在播放状态");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveRecord(boolean z) {
        int definition = this.ijkPlayerPresenter.getDefinition();
        if (definition < 0 || definition >= this.ijkPlayerPresenter.getDefinitionData().size()) {
            return;
        }
        if (this.movieDownload.getBoxType() == 1) {
            get_Movie_select((int) this.ijkPlayerPresenter.getDuration(), (int) this.ijkPlayerPresenter.getCurrentPosition(), this.ijkPlayerPresenter.getDefinition() < this.movieDownload.list.size() ? this.movieDownload.list.get(this.ijkPlayerPresenter.getDefinition()).mmfid + "" : "0");
        } else {
            get_Movie_select((int) this.ijkPlayerPresenter.getDuration(), (int) this.ijkPlayerPresenter.getCurrentPosition(), this.ijkPlayerPresenter.getDefinition() < this.movieDownload.list.size() ? this.movieDownload.list.get(this.ijkPlayerPresenter.getDefinition()).tmfid + "" : "0");
        }
        MediaQualityInfo mediaQualityInfo = this.ijkPlayerPresenter.getDefinitionData().get(definition);
        if (mediaQualityInfo != null) {
            if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.REMEMBER_ORG_QUALITY, false)) {
                saveInDao((int) this.ijkPlayerPresenter.getCurrentPosition(), definition);
            } else if (mediaQualityInfo.getOriginal() != 1) {
                saveInDao((int) this.ijkPlayerPresenter.getCurrentPosition(), this.ijkPlayerPresenter.getDefinition());
            } else if (z) {
                saveInDao((int) this.ijkPlayerPresenter.getCurrentPosition(), this.ijkPlayerPresenter.getDefinition());
            } else {
                for (int i = 0; i < this.ijkPlayerPresenter.getDefinitionData().size(); i++) {
                    if (this.ijkPlayerPresenter.getDefinitionData().get(i).getOriginal() != 1) {
                        saveInDao((int) this.ijkPlayerPresenter.getCurrentPosition(), i);
                        return;
                    }
                }
            }
        }
    }

    @Override // com.movieboxpro.android.base.BaseActivity2, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        startPlayingFeedbackTimer();
        CastContext castContext = this.mCastContext;
        if (castContext != null) {
            castContext.getSessionManager().addSessionManagerListener(this.mSessionManagerListener, CastSession.class);
        }
        super.onResume();
        if (this.ijkPlayerPresenter.isPause()) {
            this.ijkPlayerPresenter.start();
            NormalPlayerPresenter normalPlayerPresenter = this.ijkPlayerPresenter;
            if (normalPlayerPresenter instanceof NormalExoVideoView) {
                normalPlayerPresenter.seekTo(normalPlayerPresenter.getCurrentPosition());
            }
        }
        hideNavigationBar();
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.FULLSCREEN_PLAY, true) && CommonUtils.isScreenLandscape(this)) {
            switchFullscreen(true);
        }
    }

    private void hideNavigationBar() {
        getWindow().getDecorView().setSystemUiVisibility(6);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity2, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        RendererLiveData rendererLiveData2;
        NormalIjkVideoView normalIjkVideoView = this.previewIjkVideoView;
        if (normalIjkVideoView != null) {
            normalIjkVideoView.release();
        }
        this.handler.removeCallbacksAndMessages(null);
        this.handler = null;
        this.mSessionManagerListener = null;
        this.mHandler.removeCallbacks(this.checkMd5);
        this.mHandler.removeCallbacks(this.uploadOpenSubtitleFile);
        this.mHandler = null;
        this.controller.destroy();
        this.controller = null;
        if (this.ijkPlayerPresenter != null) {
            if (!this.havePop && (rendererLiveData2 = rendererLiveData) != null) {
                if (rendererLiveData2.getValue() == null) {
                    this.ijkPlayerPresenter.release();
                } else {
                    this.ijkPlayerPresenter.releaseView();
                }
            }
            this.ijkPlayerPresenter.release();
            this.ijkPlayerPresenter = null;
            this.ijkVideoView = null;
            this.vlcVideoView = null;
        }
        RefreshWaitingLiveData.Companion.get().setValue(true);
        RefreshWatchedLiveData.Companion.get().setValue(true);
        RxManager.remove(this.TAG);
        CallManager.cancelAll(getClass().getSimpleName());
        openSubtitleSignOut();
        super.onDestroy();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.controller.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkSubtitleCode(int i, final SRTModel.SubTitles subTitles) {
        ((ObservableSubscribeProxy) Http.getService2().download(subTitles.file_path).map(new Function<ResponseBody, File>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.21
            @Override // io.reactivex.functions.Function
            public File apply(ResponseBody responseBody) throws Exception {
                File file = new File(Constant.DIR_DOWNLOAD, subTitles.file_path.substring(subTitles.file_path.lastIndexOf("/") + 1));
                if (file.exists()) {
                    file.delete();
                }
                FileUtils.writeFileFromIS(file, responseBody.byteStream());
                return file;
            }
        }).flatMap(new Function<File, ObservableSource<TranscodeResponse>>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.20
            @Override // io.reactivex.functions.Function
            public ObservableSource<TranscodeResponse> apply(File file) throws Exception {
                return new HttpUploadRequest().addBaseParams("Srt_convert_encoding", "text/plain", file, "zip_file").addParam("encoding", SettingManager.INSTANCE.getTranscodeCode()).asRequest().compose(RxUtils.rxTranslate2Bean(TranscodeResponse.class));
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new AnonymousClass19(subTitles, i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory$19  reason: invalid class name */
    /* loaded from: classes3.dex */
    public class AnonymousClass19 implements io.reactivex.Observer<TranscodeResponse> {
        final /* synthetic */ int val$position;
        final /* synthetic */ SRTModel.SubTitles val$subTitles;

        @Override // io.reactivex.Observer
        public void onComplete() {
        }

        AnonymousClass19(SRTModel.SubTitles subTitles, int i) {
            this.val$subTitles = subTitles;
            this.val$position = i;
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            VideoActivityFactory.this.showLoading();
        }

        @Override // io.reactivex.Observer
        public void onNext(TranscodeResponse transcodeResponse) {
            VideoActivityFactory.this.hideLoading();
            if (TextUtils.isEmpty(transcodeResponse.getSrt_content())) {
                TranscodeSubtitleFragment newInstance = TranscodeSubtitleFragment.Companion.newInstance(this.val$subTitles.file_path, this.val$subTitles.sid, this.val$subTitles.lang, this.val$subTitles.language, VideoActivityFactory.this.boxType(), VideoActivityFactory.this.movieDownload.id, VideoActivityFactory.this.season, VideoActivityFactory.this.episode);
                newInstance.setListener(new TranscodeSubtitleFragment.OnSelectSubtitleListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.-$$Lambda$VideoActivityFactory$19$X0eBdl6TXt-HJs4q-aGDsxrqpEY
                    @Override // com.movieboxpro.android.view.fragment.TranscodeSubtitleFragment.OnSelectSubtitleListener
                    public final void onSelectSubtitle(List list) {
                        VideoActivityFactory.AnonymousClass19.this.lambda$onNext$0$VideoActivityFactory$19(list);
                    }
                });
                newInstance.show(VideoActivityFactory.this.getSupportFragmentManager(), TranscodeSubtitleFragment.class.getSimpleName());
                return;
            }
            VideoActivityFactory.this.controller.selectSubtitle(this.val$position, this.val$subTitles);
        }

        public /* synthetic */ void lambda$onNext$0$VideoActivityFactory$19(List list) {
            VideoActivityFactory.this.controller.setNewSubtitle(new ArrayList<>(list));
            if (VideoActivityFactory.this.currMediaQualityInfo != null) {
                VideoActivityFactory videoActivityFactory = VideoActivityFactory.this;
                videoActivityFactory.getSrt(videoActivityFactory.currMediaQualityInfo);
            }
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            VideoActivityFactory.this.hideLoading();
            if (th instanceof ServerException) {
                if (((ServerException) th).getErrCode() == -1) {
                    TranscodeSubtitleFragment newInstance = TranscodeSubtitleFragment.Companion.newInstance(this.val$subTitles.file_path, this.val$subTitles.sid, this.val$subTitles.lang, this.val$subTitles.language, VideoActivityFactory.this.boxType(), VideoActivityFactory.this.movieDownload.id, VideoActivityFactory.this.season, VideoActivityFactory.this.episode);
                    newInstance.setListener(new TranscodeSubtitleFragment.OnSelectSubtitleListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.-$$Lambda$VideoActivityFactory$19$pERVuXhMrFdnnXk3lRkRHPpM_ak
                        @Override // com.movieboxpro.android.view.fragment.TranscodeSubtitleFragment.OnSelectSubtitleListener
                        public final void onSelectSubtitle(List list) {
                            VideoActivityFactory.AnonymousClass19.this.lambda$onError$1$VideoActivityFactory$19(list);
                        }
                    });
                    newInstance.show(VideoActivityFactory.this.getSupportFragmentManager(), TranscodeSubtitleFragment.class.getSimpleName());
                    return;
                }
                return;
            }
            VideoActivityFactory.this.controller.selectSubtitle(this.val$position, this.val$subTitles);
        }

        public /* synthetic */ void lambda$onError$1$VideoActivityFactory$19(List list) {
            VideoActivityFactory.this.controller.setNewSubtitle(new ArrayList<>(list));
            if (VideoActivityFactory.this.currMediaQualityInfo != null) {
                VideoActivityFactory videoActivityFactory = VideoActivityFactory.this;
                videoActivityFactory.getSrt(videoActivityFactory.currMediaQualityInfo);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkAutoDownload(String str) {
        if (SettingManager.INSTANCE.getSmartDownload()) {
            if (!SettingManager.INSTANCE.getSmartDownloadWifiOnly() || NetworkUtils.isWifiConnected()) {
                APIService service = Http.getService();
                String str2 = API.BASE_URL;
                ((ObservableSubscribeProxy) service.TV_episode(str2, API.Tv.TV_EPISODE, str, this.season + "", "", App.getUserData().uid_v2, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE).compose(RxUtils.rxTranslate2List(TvDetail.SeasonDetail.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<List<TvDetail.SeasonDetail>>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.22
                    @Override // com.movieboxpro.android.base.HttpResponseObserver
                    public void onError(ApiException apiException) {
                    }

                    @Override // com.movieboxpro.android.base.HttpResponseObserver
                    public void onStart(Disposable disposable) {
                    }

                    @Override // com.movieboxpro.android.base.HttpResponseObserver
                    public void onSuccess(List<TvDetail.SeasonDetail> list) {
                        String str3;
                        String str4;
                        for (final TvDetail.SeasonDetail seasonDetail : list) {
                            if (seasonDetail.season == VideoActivityFactory.this.season && seasonDetail.episode == VideoActivityFactory.this.episode + 1) {
                                DownloadDao downloadDao = App.getDB().downloadDao();
                                if (downloadDao.findByType(2, seasonDetail.tid + "_" + seasonDetail.id) == null) {
                                    String string = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, "");
                                    if (!TextUtils.isEmpty(string) && "0".equalsIgnoreCase(string)) {
                                        str4 = "";
                                        str3 = str4;
                                    } else {
                                        str3 = string;
                                        str4 = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
                                    }
                                    ((ObservableSubscribeProxy) Http.getService().TV_downloadurl(API.BASE_URL, API.Tv.TV_DOWNLAODURL, App.isLogin() ? App.getUserData().uid_v2 : "", seasonDetail.tid, String.valueOf(seasonDetail.season), String.valueOf(seasonDetail.episode), str4, str3).compose(RxUtils.rxTranslate2Bean(TvDetail.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(VideoActivityFactory.this))).subscribe(new io.reactivex.Observer<TvDetail>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.22.1
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
                                        public void onNext(TvDetail tvDetail) {
                                            try {
                                                TvDetail tvDetail2 = (TvDetail) VideoActivityFactory.this.getSerializable("videoplayer_params", new TvDetail());
                                                tvDetail2.list = tvDetail.list;
                                                tvDetail2.seasonDetail = seasonDetail;
                                                Pair autoDownloadUrl = VideoActivityFactory.this.getAutoDownloadUrl(tvDetail.list);
                                                VideoActivityFactory.this.checkDownload((BaseMediaModel.DownloadFile) autoDownloadUrl.second, tvDetail2, ((Integer) autoDownloadUrl.first).intValue());
                                            } catch (Exception unused) {
                                            }
                                        }
                                    });
                                    return;
                                }
                                return;
                            }
                        }
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Pair<Integer, BaseMediaModel.DownloadFile> getAutoDownloadUrl(List<BaseMediaModel.DownloadFile> list) {
        String[] split = SettingManager.INSTANCE.getDownloadRule().split(",");
        String currDownloadRule = SettingManager.INSTANCE.getCurrDownloadRule();
        if (list.size() == 1) {
            return new Pair<>(0, list.get(0));
        }
        for (String str : split) {
            for (int i = 0; i < list.size(); i++) {
                BaseMediaModel.DownloadFile downloadFile = list.get(i);
                if ("ORG".equalsIgnoreCase(currDownloadRule)) {
                    if (!TextUtils.isEmpty(downloadFile.path) && downloadFile.original == 1) {
                        return new Pair<>(Integer.valueOf(i), downloadFile);
                    }
                } else if (downloadFile.original != 1 && !TextUtils.isEmpty(downloadFile.path) && str.equalsIgnoreCase(downloadFile.real_quality)) {
                    return new Pair<>(Integer.valueOf(i), downloadFile);
                }
            }
        }
        return new Pair<>(0, list.get(0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkDownload(BaseMediaModel.DownloadFile downloadFile, TvDetail tvDetail, int i) {
        if (!PrefsUtils.getInstance().getBoolean(Constant.Prefs.INTERNAL_STORAGE, true)) {
            if (!new File(PrefsUtils.getInstance().getString(Constant.Prefs.DOWNLOAD_DIR)).exists()) {
                ToastUtils.showShort("SD Card is invalid");
                return;
            } else {
                checkEnoughSpace(downloadFile, tvDetail, i);
                return;
            }
        }
        checkEnoughSpace(downloadFile, tvDetail, i);
    }

    private void checkEnoughSpace(BaseMediaModel.DownloadFile downloadFile, final TvDetail tvDetail, final int i) {
        ((ObservableSubscribeProxy) Observable.just(downloadFile).map(new Function<BaseMediaModel.DownloadFile, Boolean>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.24
            @Override // io.reactivex.functions.Function
            public Boolean apply(BaseMediaModel.DownloadFile downloadFile2) throws Exception {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(downloadFile2.path).openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setRequestProperty("Accept-Encoding", "identity");
                httpURLConnection.connect();
                long contentLength = httpURLConnection.getContentLength();
                if (contentLength < 0) {
                    contentLength = Long.parseLong(httpURLConnection.getHeaderField("Content-Length"));
                }
                httpURLConnection.disconnect();
                if (FileUtils.getFsAvailableSize(Constant.DIR_DOWNLOAD) > contentLength) {
                    return true;
                }
                ToastUtils.showShort("Not enough space to download next episode");
                return false;
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<Boolean>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.23
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException apiException) {
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(Boolean bool) {
                Intent intent = new Intent(VideoActivityFactory.this, SubtitleDownloadService.class);
                if (bool.booleanValue()) {
                    intent.putExtra("id", VideoActivityFactory.this.movieDownload.id);
                    intent.putExtra("name", VideoActivityFactory.this.movieDownload.title);
                    intent.putExtra("episode", VideoActivityFactory.this.episode);
                    intent.putExtra("season", VideoActivityFactory.this.season);
                    VideoActivityFactory.this.startService(intent);
                    tvDetail.saveInDao(i, VideoActivityFactory.this);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory$25  reason: invalid class name */
    /* loaded from: classes3.dex */
    public class AnonymousClass25 implements ControllerCallback {
        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public void Floatstart() {
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public void onIjkTrackComplete(List<IjkTrackInfo> list) {
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public void onTrackComplete(List<IMedia.AudioTrack> list) {
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public boolean showAlert() {
            return false;
        }

        AnonymousClass25() {
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public void checkSubtitleCode(int i, SRTModel.SubTitles subTitles) {
            VideoActivityFactory.this.checkSubtitleCode(i, subTitles);
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public void desSelectSubtitle(String str) {
            VideoActivityFactory.this.desSelectSubtitle(str);
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public void hideFragment(Fragment fragment) {
            if (fragment != null) {
                FragmentUtils.hide(fragment, R.anim.slide_out_right);
            }
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public void showFragment(Fragment fragment) {
            if (fragment != null) {
                FragmentUtils.show(fragment, R.anim.slide_in_right);
            }
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public void showFragmentDialog(DialogFragment dialogFragment) {
            if (dialogFragment != null) {
                dialogFragment.show(VideoActivityFactory.this.getSupportFragmentManager(), dialogFragment.getClass().getSimpleName());
            }
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public void addFragment(Fragment fragment, int i) {
            if (fragment != null) {
                FragmentUtils.add(VideoActivityFactory.this.getSupportFragmentManager(), fragment, i, (int) R.anim.slide_in_right, (int) R.anim.slide_out_right);
            }
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public void viewLocalSubtitleFile(final File file, final int i) {
            PreviewLocalSubtitleDialog newInstance = PreviewLocalSubtitleDialog.Companion.newInstance(file.getName(), file.getPath(), VideoActivityFactory.this.boxType(), VideoActivityFactory.this.movieDownload.id, VideoActivityFactory.this.season, VideoActivityFactory.this.episode);
            newInstance.setListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.25.1
                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                public void onClick() {
                    VideoActivityFactory.this.viewLocalSubtitle(file, i);
                }
            }, new PreviewLocalSubtitleDialog.OnSelectLocalSubtitleListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.25.2
                @Override // com.movieboxpro.android.view.dialog.PreviewLocalSubtitleDialog.OnSelectLocalSubtitleListener
                public void onSelectLocalSubtitle(ArrayList<SrtPraseModel> arrayList, List<Subtitle> list, String str, String str2) {
                    VideoActivityFactory.this.controller.showLocalSubtitle(arrayList, list, str2);
                    VideoActivityFactory.this.localSubtitleLang = str;
                }
            });
            newInstance.show(VideoActivityFactory.this.getSupportFragmentManager(), PreviewLocalSubtitleDialog.class.getSimpleName());
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public void subtitleFileSelected(File file) {
            VideoActivityFactory.this.loadSubtitleFromFile(file);
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public void setSrt(SRTModel.SubTitles subTitles) {
            VideoActivityFactory.this.setSrts(subTitles);
            VideoActivityFactory.this.get_srt_select(subTitles.getFile_path(), subTitles.sid);
            VideoActivityFactory.this.timerLikeSubtitle(subTitles.sid);
            VideoActivityFactory.this.mHandler.removeCallbacks(VideoActivityFactory.this.checkMd5);
            VideoActivityFactory.this.mHandler.removeCallbacks(VideoActivityFactory.this.uploadOpenSubtitleFile);
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public boolean getOpenSubtitle() {
            if (VideoActivityFactory.this.hasSubtitle) {
                VideoActivityFactory.this.getLogin();
                return true;
            }
            return false;
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public void downOpenSubtitle(final ExtrModel extrModel) {
            PreviewSubtitleDialog newInstance = PreviewSubtitleDialog.Companion.newInstance(extrModel.getSubFileName(), extrModel.getZipDownloadLink(), extrModel.getISO639(), extrModel.getLanguageName(), VideoActivityFactory.this.boxType(), VideoActivityFactory.this.movieDownload.id, VideoActivityFactory.this.season, VideoActivityFactory.this.episode);
            newInstance.setListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.25.3
                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                public void onClick() {
                    VideoActivityFactory.this.qualitys = extrModel;
                    VideoActivityFactory.this.currOpenSubtitleLang = extrModel.getISO639();
                    VideoActivityFactory.this.getSRTs(extrModel.getZipDownloadLink());
                }
            }, new TranscodeSubtitleFragment.OnSelectSubtitleListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.25.4
                @Override // com.movieboxpro.android.view.fragment.TranscodeSubtitleFragment.OnSelectSubtitleListener
                public void onSelectSubtitle(List<Subtitle> list) {
                    VideoActivityFactory.this.controller.setNewSubtitle(new ArrayList<>(list));
                    VideoActivityFactory.this.controller.hideOpenSubtitle();
                    VideoActivityFactory.this.ijkPlayerPresenter.start();
                }
            });
            newInstance.show(VideoActivityFactory.this.getSupportFragmentManager(), PreviewSubtitleDialog.class.getSimpleName());
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public void downloadAndTransCodeSubtitle(String str, String str2, String str3, String str4) {
            TranscodeSubtitleFragment newInstance = TranscodeSubtitleFragment.Companion.newInstance(str, str2, str3, str4, VideoActivityFactory.this.boxType(), VideoActivityFactory.this.movieDownload.id, VideoActivityFactory.this.season, VideoActivityFactory.this.episode);
            newInstance.setListener(new TranscodeSubtitleFragment.OnSelectSubtitleListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.-$$Lambda$VideoActivityFactory$25$7MVWhdwgzWEUHY4wmQbCkM09LdE
                @Override // com.movieboxpro.android.view.fragment.TranscodeSubtitleFragment.OnSelectSubtitleListener
                public final void onSelectSubtitle(List list) {
                    VideoActivityFactory.AnonymousClass25.this.lambda$downloadAndTransCodeSubtitle$0$VideoActivityFactory$25(list);
                }
            });
            newInstance.show(VideoActivityFactory.this.getSupportFragmentManager(), TranscodeSubtitleFragment.class.getSimpleName());
        }

        public /* synthetic */ void lambda$downloadAndTransCodeSubtitle$0$VideoActivityFactory$25(List list) {
            VideoActivityFactory.this.controller.setNewSubtitle(new ArrayList<>(list));
            if (VideoActivityFactory.this.currMediaQualityInfo != null) {
                VideoActivityFactory videoActivityFactory = VideoActivityFactory.this;
                videoActivityFactory.getSrt(videoActivityFactory.currMediaQualityInfo);
            }
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public void redownOpenSubtitle(ExtrModel extrModel) {
            VideoActivityFactory.this.mHandler.removeCallbacks(VideoActivityFactory.this.checkMd5);
            VideoActivityFactory.this.mHandler.removeCallbacks(VideoActivityFactory.this.uploadOpenSubtitleFile);
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public void playError() {
            VideoActivityFactory.this.ijkPlayerPresenter.setErrorVideos();
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public void getNetState() {
            TestSpeedActivity.Companion.startForResult(VideoActivityFactory.this, 1);
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public void uploadSrtDelayInfo(String str, int i) {
            VideoActivityFactory.this.uploadDelayInfo(str, i);
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public void requestSubtitleFeedbackVote(String str, SRTModel.SubTitles subTitles) {
            VideoActivityFactory.this.requestFeedbackVoteInfo(str, subTitles);
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public void voteSubtitleFeedback(int i, String str) {
            VideoActivityFactory.this.voteFeedback(i, str);
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public void transCode(String str, boolean z) {
            VideoActivityFactory.this.transCoding(str, z);
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public void onSelectedExtraSubtitle() {
            VideoActivityFactory.this.mHandler.removeCallbacks(VideoActivityFactory.this.checkMd5);
            VideoActivityFactory.this.mHandler.removeCallbacks(VideoActivityFactory.this.uploadOpenSubtitleFile);
            VideoActivityFactory.this.mHandler.removeCallbacks(VideoActivityFactory.this.checkMd5);
            VideoActivityFactory.this.mHandler.postDelayed(VideoActivityFactory.this.checkMd5, 1000L);
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public void onSelectedServiceSubtitle() {
            VideoActivityFactory.this.mHandler.removeCallbacks(VideoActivityFactory.this.checkMd5);
            VideoActivityFactory.this.mHandler.removeCallbacks(VideoActivityFactory.this.uploadOpenSubtitleFile);
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public void onVideoPlaying() {
            AudioManager audioManager;
            if (VideoActivityFactory.this.firstPlay) {
                if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.REMEMBER_PLAY_SPEED, false)) {
                    float f = PrefsUtils.getInstance().getFloat(Constant.Prefs.PLAY_SPEED, 1.0f);
                    VideoActivityFactory.this.controller.getMediaPlayer().setSpeed(f);
                    if (f != 1.0f) {
                        VideoActivityFactory.this.controller.showPlaySpeed(f);
                    } else {
                        VideoActivityFactory.this.controller.getMediaPlayer().setSpeed(1.0f);
                    }
                } else {
                    VideoActivityFactory.this.controller.getMediaPlayer().setSpeed(1.0f);
                }
                ((ObservableSubscribeProxy) Observable.interval(5L, TimeUnit.MINUTES).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(VideoActivityFactory.this))).subscribe(new io.reactivex.Observer<Long>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.25.5
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
                    public void onNext(Long l) {
                        if (VideoActivityFactory.this.controller.getMediaPlayer().isPlaying()) {
                            VideoActivityFactory.this.saveRecord(false);
                        }
                    }
                });
                VideoActivityFactory.this.firstPlay = false;
                boolean z = PrefsUtils.getInstance().getBoolean(Constant.Prefs.SKIP_OPENING_ENDING, true);
                if ((!z || VideoActivityFactory.this.skipTime == null || VideoActivityFactory.this.skipTime.getStart() * 1000 < VideoActivityFactory.this.controller.getMediaPlayer().getCurrentPosition()) && VideoActivityFactory.this.controller.getMediaPlayer().getCurrentPosition() > C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS) {
                    ((ObservableSubscribeProxy) Observable.timer(500L, TimeUnit.MILLISECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(VideoActivityFactory.this))).subscribe(new io.reactivex.Observer<Long>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.25.6
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
                        public void onNext(Long l) {
                            VideoActivityFactory.this.controller.setContinueText(StringUtils.stringForTime((int) VideoActivityFactory.this.controller.getMediaPlayer().getCurrentPosition()));
                        }
                    });
                }
                if (z && VideoActivityFactory.this.skipTime != null) {
                    VideoActivityFactory.this.checkSkipTime();
                }
                if (VideoActivityFactory.this.getIntent().getBooleanExtra(VideoActivityFactory.IS_LOCAL_FILE, false) && VideoActivityFactory.this.movieDownload.box_type == 2) {
                    VideoActivityFactory videoActivityFactory = VideoActivityFactory.this;
                    videoActivityFactory.checkAutoDownload(videoActivityFactory.movieDownload.id);
                }
            }
            VideoActivityFactory.this.flContainer.setKeepScreenOn(true);
            if (VideoActivityFactory.this.previewIjkVideoView == null) {
                VideoActivityFactory.this.initPreviewPlayer();
            }
            VideoActivityFactory.this.startProgressTimer();
            if (!VideoActivityFactory.this.isAdLoad) {
                if (App.getUserData().getIsvip() != 1) {
                    VideoActivityFactory.this._initAds();
                }
                VideoActivityFactory.this.isAdLoad = true;
            }
            if (!VideoActivityFactory.this.showMuteHint && (audioManager = (AudioManager) VideoActivityFactory.this.getSystemService("audio")) != null && audioManager.getStreamVolume(3) == 0 && VideoActivityFactory.this.controller != null) {
                VideoActivityFactory.this.controller.showMuteHint();
                VideoActivityFactory.this.showMuteHint = true;
            }
            VideoActivityFactory.this.isComplete = false;
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public void onNextEpisode() {
            VideoActivityFactory.this.onPlayNextEpisode(false);
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public void onVideoPlayComplete() {
            VideoActivityFactory.this.flContainer.setKeepScreenOn(false);
            VideoActivityFactory.this.videoPlayComplete();
            saveRecord();
            VideoActivityFactory.this.isComplete = true;
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public void onShowTrackHintDialog() {
            VideoActivityFactory.this.showRemindSwitchTrackDialog();
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public Pair<String, String> getSubtitleContentLang(int i) {
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        return new Pair<>(VideoActivityFactory.this.currLocalFileSubtitleContent, "");
                    }
                    return new Pair<>("", "");
                }
                return new Pair<>(VideoActivityFactory.this.currOpenSubtitleContent, VideoActivityFactory.this.currOpenSubtitleLang);
            }
            return new Pair<>(VideoActivityFactory.this.currSubtitleContent, VideoActivityFactory.this.currSubtitleLang);
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public String getServerSubtitleContent() {
            return VideoActivityFactory.this.currSubtitleContent;
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public int getBoxType() {
            return VideoActivityFactory.this.boxType();
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public IjkMediaPlayer getMediaPlayer() {
            return VideoActivityFactory.this.ijkPlayer.getMediaPlayer();
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public String getTitle() {
            return VideoActivityFactory.this.movieDownload != null ? VideoActivityFactory.this.movieDownload.title : "";
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public String getId() {
            return VideoActivityFactory.this.movieDownload != null ? VideoActivityFactory.this.movieDownload.id : "";
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public int getSeason() {
            return VideoActivityFactory.this.season;
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public int getEpisode() {
            return VideoActivityFactory.this.episode;
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public String getFid() {
            return String.valueOf(VideoActivityFactory.this.fid);
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public void castVideo() {
            VideoActivityFactory.this.openPopMenu();
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public void changeColor() {
            int i = PrefsUtils.getInstance().getInt(Constant.Prefs.SUBTITLE_COLOR, 0);
            if (i == 0) {
                i = -1;
            }
            VideoActivityFactory.this.getSupportFragmentManager().beginTransaction().add(ColorPickerDialog.newBuilder().setDialogType(1).setDialogTitle(R.string.select_subtitle_color).setColorShape(1).setPresets(ColorPickerDialog.DEFAULT_COLORS).setAllowPresets(true).setAllowPresets(true).setShowAlphaSlider(true).setShowColorShades(true).setColor(i).create(), "ColorPickerDialog").commitAllowingStateLoss();
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public void changeSubtitleBgColor() {
            ColorPickerDialog create = ColorPickerDialog.newBuilder().setDialogType(1).setDialogTitle(R.string.select_subtitle_bg_color).setColorShape(1).setPresets(ColorPickerDialog.DEFAULT_COLORS2).setAllowPresets(true).setAllowPresets(true).setShowAlphaSlider(true).setShowColorShades(true).setColor(PrefsUtils.getInstance().getInt(Constant.Prefs.SUBTITLE_BG_COLOR, Color.parseColor("#B3000000"))).create();
            create.setColorPickerDialogListener(new ColorPickerDialogListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.25.7
                @Override // com.jaredrummler.android.colorpicker.ColorPickerDialogListener
                public void onDialogDismissed(int i) {
                }

                @Override // com.jaredrummler.android.colorpicker.ColorPickerDialogListener
                public void onColorSelected(int i, int i2) {
                    if (VideoActivityFactory.this.controller != null) {
                        VideoActivityFactory.this.controller.changeSubtitleBgColor(i2);
                        PrefsUtils.getInstance().putInt(Constant.Prefs.SUBTITLE_BG_COLOR, i2);
                    }
                }
            });
            VideoActivityFactory.this.getSupportFragmentManager().beginTransaction().add(create, "ColorPickerDialog").commitAllowingStateLoss();
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public void openFullscreen() {
            VideoActivityFactory.this.switchFullscreen(true);
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public void closeFullscreen() {
            VideoActivityFactory.this.switchFullscreen(false);
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public void popPlayer() {
            if (Build.VERSION.SDK_INT < 23 || Settings.canDrawOverlays(VideoActivityFactory.this)) {
                VideoActivityFactory.this.enterPop();
            } else {
                new MsgHintDialog.Builder(VideoActivityFactory.this).setContent("MovieBoxPro needs you to grant this permission display your video in a popup over other applications.").setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.-$$Lambda$VideoActivityFactory$25$-3RJ-y_WgUlRqVYWL39z-4fwm3g
                    @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                    public final void onClick() {
                        VideoActivityFactory.AnonymousClass25.this.lambda$popPlayer$1$VideoActivityFactory$25();
                    }
                }).create().show();
            }
        }

        public /* synthetic */ void lambda$popPlayer$1$VideoActivityFactory$25() {
            Intent intent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION");
            intent.setData(Uri.parse("package:" + VideoActivityFactory.this.activity.getPackageName()));
            try {
                VideoActivityFactory.this.startActivity(intent);
            } catch (Exception unused) {
                ToastUtils.showShort("Please grant this permission on manually in Settings");
            }
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public boolean isLocalFile() {
            return VideoActivityFactory.this.getIntent().getBooleanExtra(VideoActivityFactory.IS_LOCAL_FILE, false);
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public void changePlayerEngine(int i) {
            if (VideoActivityFactory.this.ijkPlayerPresenter.getDefinition() == 0) {
                if (i == 0) {
                    VideoActivityFactory.this.changePlayerEngine(2);
                } else {
                    VideoActivityFactory.this.changePlayerEngine(i);
                }
            } else if (i == 0) {
                VideoActivityFactory.this.changePlayerEngine(1);
            } else {
                VideoActivityFactory.this.changePlayerEngine(i);
            }
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public void switchQuality(MediaQualityInfo mediaQualityInfo) {
            if (mediaQualityInfo.getOriginal() == 1 || mediaQualityInfo.getHdr() == 1) {
                if (PrefsUtils.getInstance().getInt(Constant.Prefs.PLAYER_ENGINE, 0) == 0) {
                    VideoActivityFactory.this.changePlayerEngine(2);
                }
            } else if (VideoActivityFactory.this.ijkPlayerPresenter instanceof com.movieboxpro.android.view.activity.vlcvideoplayer.videoview.NormalIjkVideoView) {
                VideoActivityFactory.this.changePlayerEngine(2);
            } else if (VideoActivityFactory.this.ijkPlayerPresenter instanceof NormalExoVideoView) {
                VideoActivityFactory.this.changePlayerEngine(3);
            } else if (VideoActivityFactory.this.ijkPlayerPresenter instanceof NormalIjkVideoView) {
                VideoActivityFactory.this.changePlayerEngine(1);
            }
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public String getPlayUrl() {
            return VideoActivityFactory.this.ijkPlayerPresenter != null ? VideoActivityFactory.this.ijkPlayerPresenter.getUrl() : "";
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public String getPreviewUrl() {
            return VideoActivityFactory.this.ijkPlayerPresenter != null ? VideoActivityFactory.this.ijkPlayerPresenter.getPreviewUrl() : "";
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public void finishPlay() {
            VideoActivityFactory.this.finish();
        }

        @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback
        public void saveRecord() {
            VideoActivityFactory.this.saveRecord(false);
        }
    }

    public void initController(int i) {
        AnonymousClass25 anonymousClass25 = new AnonymousClass25();
        final boolean booleanExtra = getIntent().getBooleanExtra(IS_LOCAL_FILE, false);
        VideoPreviewListener videoPreviewListener = new VideoPreviewListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.26
            @Override // com.movieboxpro.android.listener.VideoPreviewListener
            public void onProgressChanged(SeekBar seekBar, int i2, int i3) {
                if (Math.abs(i2 - VideoActivityFactory.this.lastPos) > 10) {
                    Log.d(VideoActivityFactory.this.TAG, "onProgressChanged: ");
                    if (booleanExtra) {
                        VideoActivityFactory.this.handler.removeCallbacks(VideoActivityFactory.this.seekRunnable);
                        VideoActivityFactory.this.handler.postDelayed(VideoActivityFactory.this.seekRunnable, 20L);
                    } else {
                        VideoActivityFactory.this.handler.removeCallbacks(VideoActivityFactory.this.seekRunnable);
                        VideoActivityFactory.this.handler.postDelayed(VideoActivityFactory.this.seekRunnable, 100L);
                    }
                    VideoActivityFactory.this.lastPos = i2;
                }
                int width = (int) ((i3 * seekBar.getWidth()) / seekBar.getMax());
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) VideoActivityFactory.this.flPreview.getLayoutParams();
                layoutParams.leftMargin = width;
                VideoActivityFactory.this.flPreview.setLayoutParams(layoutParams);
            }

            @Override // com.movieboxpro.android.listener.VideoPreviewListener
            public void startDrag() {
                VideoActivityFactory.this.handler.postDelayed(VideoActivityFactory.this.showPreview, 500L);
            }

            @Override // com.movieboxpro.android.listener.VideoPreviewListener
            public void stopDrag() {
                if (VideoActivityFactory.this.handler == null || VideoActivityFactory.this.flPreview == null) {
                    return;
                }
                VideoActivityFactory.this.handler.removeCallbacks(VideoActivityFactory.this.showPreview);
                VideoActivityFactory.this.flPreview.setVisibility(8);
            }
        };
        if (i == 1) {
            NormalController normalController = new NormalController(this.activity);
            this.ijkPlayerController = normalController;
            normalController.setVideoPreviewListener(videoPreviewListener);
            this.ijkPlayerController.setVideoModel(this.movieDownload);
            this.ijkPlayerController.setCallBack(anonymousClass25);
            this.controller = this.ijkPlayerController;
        } else if (i == 3) {
            ExoNormalController exoNormalController = new ExoNormalController(this.activity);
            this.exoNormalController = exoNormalController;
            exoNormalController.setVideoPreviewListener(videoPreviewListener);
            this.exoNormalController.setVideoModel(this.movieDownload);
            this.exoNormalController.setCallBack(anonymousClass25);
            this.controller = this.exoNormalController;
        } else {
            com.movieboxpro.android.view.activity.vlcvideoplayer.controller.NormalController normalController2 = new com.movieboxpro.android.view.activity.vlcvideoplayer.controller.NormalController(this.activity);
            this.vlcPlayerController = normalController2;
            normalController2.setVideoPreviewListener(videoPreviewListener);
            this.vlcPlayerController.setVideoModel(this.movieDownload);
            this.vlcPlayerController.setCallBack(anonymousClass25);
            this.controller = this.vlcPlayerController;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void timerLikeSubtitle(final String str) {
        Disposable disposable = this.timerLikeDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        long j = this.timerLikeDuration;
        if (j < 600 && j >= 6) {
            String str2 = boxType() == 1 ? "Movie_srt_opposition" : "TV_srt_opposition";
            Disposable disposable2 = this.timerLikeDisposable;
            if (disposable2 != null) {
                disposable2.dispose();
            }
            ((ObservableSubscribeProxy) HttpRequest.post(str2).param("sid", this.lastSelectSid).param("support", (Object) 2).asRequest().compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new io.reactivex.Observer<String>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.27
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
                public void onSubscribe(Disposable disposable3) {
                }
            });
        }
        this.timerLikeDuration = 0L;
        this.lastSelectSid = str;
        ((ObservableSubscribeProxy) Observable.interval(1L, TimeUnit.SECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new io.reactivex.Observer<Long>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.28
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable3) {
                VideoActivityFactory.this.timerLikeDisposable = disposable3;
            }

            @Override // io.reactivex.Observer
            public void onNext(Long l) {
                VideoActivityFactory.this.timerLikeDuration = l.longValue();
                if (l.longValue() >= 600) {
                    VideoActivityFactory.this.timerLikeDuration = 0L;
                    String str3 = VideoActivityFactory.this.boxType() == 1 ? "Movie_srt_opposition" : "TV_srt_opposition";
                    if (VideoActivityFactory.this.timerLikeDisposable != null) {
                        VideoActivityFactory.this.timerLikeDisposable.dispose();
                    }
                    ((ObservableSubscribeProxy) HttpRequest.post(str3).param("sid", str).param("support", (Object) 1).asRequest().compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(VideoActivityFactory.this))).subscribe(new io.reactivex.Observer<String>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.28.1
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
                        public void onSubscribe(Disposable disposable3) {
                        }
                    });
                }
            }
        });
    }

    private void startTimerToSkip(final int i) {
        Disposable disposable = this.timeDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        if (this.currPlayingPosition == this.tvSeasonListList.size() - 1) {
            return;
        }
        final MediaPlayerControl mediaPlayer = this.controller.getMediaPlayer();
        if (mediaPlayer instanceof NormalExoVideoView) {
            final long duration = mediaPlayer.getDuration();
            ((ObservableSubscribeProxy) Observable.interval(0L, 2L, TimeUnit.SECONDS).map($$Lambda$VideoActivityFactory$kZcXFBerT05PdBHmUmkVjXzw5_I.INSTANCE).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new io.reactivex.Observer<Boolean>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.29
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onError(Throwable th) {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable disposable2) {
                    VideoActivityFactory.this.timeDisposable = disposable2;
                }

                @Override // io.reactivex.Observer
                public void onNext(Boolean bool) {
                    if (duration - mediaPlayer.getCurrentPosition() <= i) {
                        if (VideoActivityFactory.this.timeDisposable != null) {
                            VideoActivityFactory.this.timeDisposable.dispose();
                        }
                        VideoActivityFactory.this.onPlayNextEpisode(true);
                    }
                }
            });
            return;
        }
        final long duration2 = mediaPlayer.getDuration();
        ((ObservableSubscribeProxy) Observable.interval(0L, 2L, TimeUnit.SECONDS).map(new Function() { // from class: com.movieboxpro.android.view.activity.videoplayer.-$$Lambda$VideoActivityFactory$YLdWQsJ8eJfg-IMWpiLNLxg4NLU
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return VideoActivityFactory.this.lambda$startTimerToSkip$3$VideoActivityFactory(mediaPlayer, duration2, i, (Long) obj);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new io.reactivex.Observer<Boolean>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.30
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable2) {
                VideoActivityFactory.this.timeDisposable = disposable2;
            }

            @Override // io.reactivex.Observer
            public void onNext(Boolean bool) {
                if (bool.booleanValue()) {
                    if (VideoActivityFactory.this.timeDisposable != null) {
                        VideoActivityFactory.this.timeDisposable.dispose();
                    }
                    VideoActivityFactory.this.onPlayNextEpisode(true);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Boolean lambda$startTimerToSkip$2(Long l) throws Exception {
        return false;
    }

    public /* synthetic */ Boolean lambda$startTimerToSkip$3$VideoActivityFactory(MediaPlayerControl mediaPlayerControl, long j, int i, Long l) throws Exception {
        if (this.controller != null) {
            return Boolean.valueOf(j - mediaPlayerControl.getCurrentPosition() <= ((long) i));
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initPreviewPlayer() {
        int[] videoSize = this.controller.getMediaPlayer().getVideoSize();
        if (videoSize != null && videoSize[0] != 0) {
            if (videoSize.length == 2) {
                ViewGroup.LayoutParams layoutParams = this.flPreview.getLayoutParams();
                int dp2px = DensityUtils.dp2px(200.0f);
                layoutParams.height = (videoSize[1] * dp2px) / videoSize[0];
                layoutParams.width = dp2px;
                this.flPreview.setLayoutParams(layoutParams);
            }
        } else {
            ViewGroup.LayoutParams layoutParams2 = this.flPreview.getLayoutParams();
            layoutParams2.height = DensityUtils.dp2px(150.0f);
            layoutParams2.width = DensityUtils.dp2px(200.0f);
            this.flPreview.setLayoutParams(layoutParams2);
        }
        this.previewIjkVideoView = new NormalIjkVideoView(this);
        IjkPlayer ijkPlayer = new IjkPlayer(this) { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.31
            @Override // com.dueeeke.videoplayer.player.IjkPlayer, com.dueeeke.videoplayer.player.AbstractPlayer
            public void setOptions() {
                this.mMediaPlayer.setOption(1, "analyzemaxduration", 100L);
                this.mMediaPlayer.setOption(1, "flush_packets", 1L);
                this.mMediaPlayer.setOption(4, "packet-buffering", 0L);
                this.mMediaPlayer.setOption(4, "reconnect", 3L);
                this.mMediaPlayer.setOption(1, "fflags", "fastseek");
                this.mMediaPlayer.setOption(4, "framedrop", 5L);
                this.mMediaPlayer.setOption(4, "soundtouch", 1L);
                this.mMediaPlayer.setOption(2, "skip_loop_filter", 48L);
                this.mMediaPlayer.setOption(4, "framedrop", 12L);
                this.mMediaPlayer.setOption(4, "min-frames", 1L);
                this.mMediaPlayer.setOption(1, "dns_cache_clear", 1L);
                this.mMediaPlayer.setOption(1, "dns_cache_timeout", -1L);
            }
        };
        this.previewIjkVideoView.setPlayerConfig(new PlayerConfig.Builder().setCustomMediaPlayer(ijkPlayer).enableMediaCodec(true).savingProgress().build());
        this.flPreview.addView(this.previewIjkVideoView);
        NormalPlayerPresenter normalPlayerPresenter = this.ijkPlayerPresenter;
        this.previewIjkVideoView.setPlayUrl(normalPlayerPresenter != null ? normalPlayerPresenter.getPreviewUrl() : "");
        this.previewIjkVideoView.start();
        this.previewIjkVideoView.setMute(true);
        ijkPlayer.bindVideoView(new PlayerEventListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.32
            @Override // com.dueeeke.videoplayer.listener.PlayerEventListener
            public void onCompletion() {
            }

            @Override // com.dueeeke.videoplayer.listener.PlayerEventListener
            public void onPrepared() {
            }

            @Override // com.dueeeke.videoplayer.listener.PlayerEventListener
            public void onSeekComplete(long j, long j2) {
            }

            @Override // com.dueeeke.videoplayer.listener.PlayerEventListener
            public void onVideoSizeChanged(int i, int i2) {
            }

            @Override // com.dueeeke.videoplayer.listener.PlayerEventListener
            public void onError() {
                Log.d(VideoActivityFactory.this.TAG, "onError: ");
            }

            @Override // com.dueeeke.videoplayer.listener.PlayerEventListener
            public void onInfo(int i, int i2) {
                if (i == 3) {
                    VideoActivityFactory.this.previewVideoPrepared = true;
                    VideoActivityFactory.this.previewIjkVideoView.pauseForce();
                    if (VideoActivityFactory.this.flPreview.getVisibility() == 0) {
                        VideoActivityFactory.this.previewIjkVideoView.seekTo(VideoActivityFactory.this.lastPos * 1000);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enterPop() {
        String str;
        CommonUtils.onEvent("enter_popup_window");
        EventUtils.event("进入悬浮窗");
        this.havePop = true;
        NormalPlayerPresenter normalPlayerPresenter = this.ijkPlayerPresenter;
        if (normalPlayerPresenter != null) {
            normalPlayerPresenter.releaseView();
        }
        if (boxType() == 1) {
            str = this.movieDownload.list.get(this.ijkPlayerPresenter.getDefinition()).mmfid;
        } else {
            str = this.movieDownload.list.get(this.ijkPlayerPresenter.getDefinition()).tmfid;
        }
        String str2 = str;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            extras.putString("playUrl", this.ijkPlayerPresenter.getUrl());
            extras.putLong("playPosition", this.ijkPlayerPresenter.getCurrentPosition());
        }
        PopPlayerManager.Companion.getInstance().showPopPlayer(App.getContext(), extras, boxType(), this.movieDownload, this.controller.getSubtitlesData(), this.controller.getSubtitleDelay(), str2, this.controller.getAudioTrackLanguage(), this.controller.getAudioTrackUrl());
        finish();
    }

    @Override // com.jaredrummler.android.colorpicker.ColorPickerDialogListener
    public void onColorSelected(int i, int i2) {
        PlayerController playerController = this.controller;
        if (playerController != null) {
            playerController.changeSubtitleColor(i2);
            PrefsUtils.getInstance().putInt(Constant.Prefs.SUBTITLE_COLOR, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showRemindSwitchTrackDialog() {
        if (PrefsUtils.getInstance().getInt("DialogCount", 0) > 5) {
            return;
        }
        final RemindChangeVoiceDialog remindChangeVoiceDialog = new RemindChangeVoiceDialog(this);
        remindChangeVoiceDialog.show();
        ((ObservableSubscribeProxy) Observable.timer(2L, TimeUnit.SECONDS).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<Long>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.35
            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(Long l) {
                super.onNext((AnonymousClass35) l);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                remindChangeVoiceDialog.dismiss();
            }
        });
        remindChangeVoiceDialog.setOnDismissListener($$Lambda$VideoActivityFactory$AbUjrg2bOO8L4Exn5XqJnvMT60.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startTimerUploadFileSubtitle() {
        this.mHandler.removeCallbacks(this.uploadFileSubtitle);
        this.mHandler.postDelayed(this.uploadFileSubtitle, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadSubtitleFromFile(final File file) {
        ((ObservableSubscribeProxy) Observable.just(file.getPath()).map(new Function() { // from class: com.movieboxpro.android.view.activity.videoplayer.-$$Lambda$VideoActivityFactory$g5Lx-kFDNiXNvoTaBDHVxdXnAis
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return VideoActivityFactory.this.lambda$loadSubtitleFromFile$5$VideoActivityFactory(file, (String) obj);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<List<SrtPraseModel>>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.37
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                VideoActivityFactory.this.showLoading();
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(List<SrtPraseModel> list) {
                super.onNext((AnonymousClass37) list);
                VideoActivityFactory.this.controller.setSubtitle(list);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                VideoActivityFactory.this.controller.hideSubtitleView();
                VideoActivityFactory.this.showToast("Subtitle load successfully");
                VideoActivityFactory.this.hideLoading();
                VideoActivityFactory.this.localSubtitleFile = file;
                VideoActivityFactory.this.startTimerUploadFileSubtitle();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                VideoActivityFactory.this.hideLoading();
                VideoActivityFactory videoActivityFactory = VideoActivityFactory.this;
                videoActivityFactory.showToast("Subtitle load fail" + apiException.getMessage());
            }
        });
    }

    public /* synthetic */ List lambda$loadSubtitleFromFile$5$VideoActivityFactory(File file, String str) throws Exception {
        ArrayList arrayList = new ArrayList();
        SrtParser.parseSrt(str, arrayList);
        this.currLocalFileSubtitleContent = FileUtils.readFile2String(file, FileUtils.getCharset(str));
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uploadLocalSubtitleFile(File file) {
        HttpUploadRequest addBaseParams;
        if (isMovie()) {
            addBaseParams = new HttpUploadRequest().addBaseParams("Upload_movie_srt_user", file);
            addBaseParams.addParam("mid", this.movieDownload.id);
        } else {
            addBaseParams = new HttpUploadRequest().addBaseParams("Upload_tv_srt_user", file);
            addBaseParams.addParam("tid", this.movieDownload.id);
            addBaseParams.addParam("season", Integer.valueOf(this.season));
            addBaseParams.addParam("episode", Integer.valueOf(this.episode));
        }
        String displayLanguage = new Locale(this.localSubtitleLang).getDisplayLanguage(Locale.ENGLISH);
        addBaseParams.addParam("lang", this.localSubtitleLang);
        addBaseParams.addParam("language", CommonUtils.toUpperCaseFirstOne(displayLanguage));
        addBaseParams.addParam(IjkMediaMeta.IJKM_KEY_FORMAT, "srt");
        addBaseParams.bindLifecycle(this).executeUpload();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void viewLocalSubtitle(File file, final int i) {
        this.srtZipFile = file;
        ((ObservableSubscribeProxy) Observable.zip(this.service.Encoding_list(API.BASE_URL, "Encoding_list").compose(RxUtils.rxTranslate2Bean(HashMap.class)).map($$Lambda$VideoActivityFactory$PAhZAMVhCXmWACToMBNApXX5bg.INSTANCE), getTransCodeObservable(file, false), new BiFunction() { // from class: com.movieboxpro.android.view.activity.videoplayer.-$$Lambda$VideoActivityFactory$1EtvEXKnJNlHZlksDwM_yUutg8g
            @Override // io.reactivex.functions.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return VideoActivityFactory.this.lambda$viewLocalSubtitle$7$VideoActivityFactory(i, (List) obj, (List) obj2);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<String>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.38
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                VideoActivityFactory.this.showLoading();
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(String str) {
                super.onNext((AnonymousClass38) str);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                VideoActivityFactory.this.hideLoading();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                VideoActivityFactory.this.hideLoading();
                VideoActivityFactory videoActivityFactory = VideoActivityFactory.this;
                videoActivityFactory.showToast("Subtitle load fail" + apiException.getMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ List lambda$viewLocalSubtitle$6(HashMap hashMap) throws Exception {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : ((HashMap) JSON.parseObject(JSON.toJSONString(hashMap.get("list")), HashMap.class)).entrySet()) {
            EncodeModel encodeModel = new EncodeModel();
            encodeModel.setLanguage((String) entry.getKey());
            encodeModel.setCode(JSON.parseArray(entry.getValue().toString(), String.class));
            arrayList.add(encodeModel);
        }
        return arrayList;
    }

    public /* synthetic */ String lambda$viewLocalSubtitle$7$VideoActivityFactory(int i, List list, List list2) throws Exception {
        this.controller.showTransCodingViewFromViewLocal(list2, list, i);
        return "";
    }

    private String buildData(String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("module", (Object) "Srt_convert_encoding");
        jSONObject.put("encoding", (Object) str);
        jSONObject.put("app_version", (Object) App.versionName);
        jSONObject.put("uid", (Object) App.getUserData().uid_v2);
        jSONObject.put("token", (Object) App.deviceToken);
        jSONObject.put("platform", (Object) "android");
        jSONObject.put("expired_date", (Object) Long.valueOf((TimeUtils.getCurrentTime() / 1000) + 43200));
        return HttpUtils.encodeBody(jSONObject.toJSONString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onPlayNextEpisode(boolean z) {
        this.firstPlay = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void transCoding(String str, final boolean z) {
        MultipartBody.Part part = null;
        try {
            part = MultipartBody.Part.createFormData("zip_file", URLEncoder.encode(this.srtZipFile.getName(), "utf-8"), RequestBody.create(MediaType.parse(z ? "text/plain" : "application/zip"), this.srtZipFile));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (part != null) {
            ((ObservableSubscribeProxy) Http.getService().Srt_convert_encoding(API.BASE_URL, buildData(str), BuildConfig.APPLICATION_ID, part).map($$Lambda$VideoActivityFactory$u8ZYB5lVNwFLWu8FbQDFML5HTt8.INSTANCE).map(new Function() { // from class: com.movieboxpro.android.view.activity.videoplayer.-$$Lambda$VideoActivityFactory$BgSpPUWl9aeVQKc3XL3kaM1oVjI
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    return VideoActivityFactory.this.lambda$transCoding$9$VideoActivityFactory(z, (HashMap) obj);
                }
            }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new io.reactivex.Observer<List<SrtPraseModel>>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.39
                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable disposable) {
                    VideoActivityFactory.this.showLoading();
                }

                @Override // io.reactivex.Observer
                public void onNext(List<SrtPraseModel> list) {
                    if (list.size() == 0) {
                        VideoActivityFactory.this.showToast("Convert fail");
                    }
                    VideoActivityFactory.this.controller.setTransCodeResult(list);
                }

                @Override // io.reactivex.Observer
                public void onError(Throwable th) {
                    VideoActivityFactory.this.hideLoading();
                    VideoActivityFactory.this.showToast("Convert fail");
                }

                @Override // io.reactivex.Observer
                public void onComplete() {
                    VideoActivityFactory.this.hideLoading();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ HashMap lambda$transCoding$8(String str) throws Exception {
        return (HashMap) ((BaseResponse) JSON.parseObject(str, RxUtils.buildType(BaseResponse.class, HashMap.class), new Feature[0])).getData();
    }

    public /* synthetic */ List lambda$transCoding$9$VideoActivityFactory(boolean z, HashMap hashMap) throws Exception {
        Object obj = hashMap.get("srt_content");
        Object obj2 = hashMap.get("srt_name");
        if (obj2 instanceof String) {
            this.afterTransCodeName = (String) obj2;
        }
        if (obj instanceof String) {
            if (z) {
                this.currLocalFileSubtitleContent = (String) obj;
            } else {
                this.currOpenSubtitleContent = (String) obj;
            }
            ArrayList arrayList = new ArrayList();
            String str = (String) obj;
            SrtParser.parseContentSrt(str, arrayList);
            File file = new File(Constant.DIR_TRANS_CODE_SUBTITLE + "/" + this.afterTransCodeName);
            FileUtils.writeFileFromString(file, str, false);
            this.subtitle_path = file.getPath();
            return arrayList;
        }
        return new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void voteFeedback(final int i, String str) {
        ((ObservableSubscribeProxy) this.service.Srt_vote(API.BASE_URL, this.movieDownload.getBoxType() == 1 ? "Movie_srt_vote" : "Tv_srt_vote", App.getUserData().uid_v2, str, i, BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<String>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.40
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                VideoActivityFactory.this.showLoading();
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(String str2) {
                super.onNext((AnonymousClass40) str2);
                VideoActivityFactory.this.controller.voteFeedbackSuccess(i);
                VideoActivityFactory.this.showToast("vote success");
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                VideoActivityFactory.this.hideLoading();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                VideoActivityFactory.this.hideLoading();
                VideoActivityFactory.this.showToast(apiException.getMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestFeedbackVoteInfo(String str, final SRTModel.SubTitles subTitles) {
        Observable map;
        Observable<R> compose = Http.getService().Srt_vote_data(API.BASE_URL, this.movieDownload.getBoxType() == 1 ? "Movie_srt_vote_data" : "Tv_srt_vote_data", App.getUserData().uid_v2, str, BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslate2List(SubTitleFeedbackModel.class));
        if (subTitles != null) {
            final String file_path = subTitles.getFile_path();
            map = Observable.zip(compose, this.service.strlist(subTitles.getFile_path()).onErrorReturn($$Lambda$VideoActivityFactory$9FmwFytEX3ZKV8ZLs5oeUqdiPro.INSTANCE), new BiFunction() { // from class: com.movieboxpro.android.view.activity.videoplayer.-$$Lambda$VideoActivityFactory$TS2HNXKvs-HM0KmIj7ewJqFws_E
                @Override // io.reactivex.functions.BiFunction
                public final Object apply(Object obj, Object obj2) {
                    return VideoActivityFactory.lambda$requestFeedbackVoteInfo$11(file_path, (List) obj, (ResponseBody) obj2);
                }
            });
        } else {
            map = compose.map($$Lambda$VideoActivityFactory$HcVwwpZsCQhVX8UUszdKBUxWQ.INSTANCE);
        }
        ((ObservableSubscribeProxy) map.compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<Triple<List<SubTitleFeedbackModel>, List<SrtPraseModel>, String>>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.41
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                VideoActivityFactory.this.showLoading();
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(Triple<List<SubTitleFeedbackModel>, List<SrtPraseModel>, String> triple) {
                super.onNext((AnonymousClass41) triple);
                VideoActivityFactory.this.controller.showSubtitleFeedback(triple.getThird(), triple.getFirst(), triple.getSecond(), subTitles);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                VideoActivityFactory.this.hideLoading();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                VideoActivityFactory.this.hideLoading();
                VideoActivityFactory videoActivityFactory = VideoActivityFactory.this;
                videoActivityFactory.showToast("load error " + apiException.getMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Triple lambda$requestFeedbackVoteInfo$11(String str, List list, ResponseBody responseBody) throws Exception {
        String str2;
        ArrayList arrayList = new ArrayList();
        if (responseBody != null) {
            String str3 = Constant.DIR_DOWNLOAD + "/" + str.substring(str.lastIndexOf("/") + 1);
            File chapterFile = ReaderUtils.getChapterFile(str3);
            FileUtils.writeFileFromBytesByStream(chapterFile, responseBody.bytes());
            str2 = FileUtils.readFile2String(chapterFile, FileUtils.getCharset(chapterFile.getPath()));
            SrtParser.parseSrt(str3, arrayList);
        } else {
            str2 = "";
        }
        return new Triple(list, arrayList, str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Triple lambda$requestFeedbackVoteInfo$12(List list) throws Exception {
        return new Triple(list, null, "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uploadDelayInfo(String str, int i) {
        ((ObservableSubscribeProxy) Http.getService().UploadSrt_DELAY_INFO(API.BASE_URL, this.movieDownload.getBoxType() == 1 ? "Movie_srt_delay" : "TV_srt_delay", App.getUserData().uid_v2, str, String.valueOf(this.fid), i, BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<String>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.42
            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(String str2) {
                super.onNext((AnonymousClass42) str2);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkFileMd5(final String str, final int i) {
        ((ObservableSubscribeProxy) Observable.just(str).map(new Function() { // from class: com.movieboxpro.android.view.activity.videoplayer.-$$Lambda$VideoActivityFactory$ooXirYELmXuX9hUU5K47otkrj4o
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return VideoActivityFactory.lambda$checkFileMd5$13(str, (String) obj);
            }
        }).flatMap(new Function() { // from class: com.movieboxpro.android.view.activity.videoplayer.-$$Lambda$VideoActivityFactory$2qV2cU1RLB3ZSTjFLyRJSt4aWd0
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return VideoActivityFactory.this.lambda$checkFileMd5$14$VideoActivityFactory((String) obj);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<FileMd5CheckModel>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.46
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(FileMd5CheckModel fileMd5CheckModel) {
                super.onNext((AnonymousClass46) fileMd5CheckModel);
                if (!fileMd5CheckModel.isExists()) {
                    if (i != 1) {
                        VideoActivityFactory.this.uploadLocalSubtitleFile(new File(str));
                        return;
                    }
                    VideoActivityFactory videoActivityFactory = VideoActivityFactory.this;
                    videoActivityFactory.updateSrt2(videoActivityFactory.qualitys);
                } else if (i != 1) {
                    if (VideoActivityFactory.this.extraSubtitle == null) {
                        VideoActivityFactory.this.extraSubtitle = new ResponseUploadExtraSubtitle();
                    }
                    VideoActivityFactory.this.extraSubtitle.setSrt_info(fileMd5CheckModel.getSrt_info());
                    if (VideoActivityFactory.this.mHandler != null) {
                        VideoActivityFactory.this.mHandler.postDelayed(VideoActivityFactory.this.uploadSelectDelayInfoRunnable, 1000L);
                    }
                } else if (VideoActivityFactory.this.mHandler != null) {
                    VideoActivityFactory.this.mHandler.postDelayed(VideoActivityFactory.this.uploadSelectDelayInfoRunnable, 1000L);
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                Log.d("dasda", "dasd");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$checkFileMd5$13(String str, String str2) throws Exception {
        File file = new File(str);
        return (file.isFile() && file.exists()) ? FileUtils.getFileMD5ToString(file) : "";
    }

    public /* synthetic */ ObservableSource lambda$checkFileMd5$14$VideoActivityFactory(String str) throws Exception {
        return Http.getService().Srt_md5_check(API.BASE_URL, API.Common.SUBTITLE_CHECK_MD5, this.movieDownload.box_type == 1 ? "movie" : "tv", str).compose(RxUtils.rxTranslate2Bean(FileMd5CheckModel.class));
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.presenter.VideoPresenter
    public void _initQuality() {
        this.list.clear();
        for (int i = 0; i < this.movieDownload.list.size(); i++) {
            BaseMediaModel.DownloadFile downloadFile = this.movieDownload.list.get(i);
            int i2 = downloadFile.h265;
            String str = downloadFile.path;
            String str2 = downloadFile.real_quality;
            String str3 = downloadFile.size;
            String formatTime3 = TimeUtils.formatTime3(downloadFile.dateline * 1000);
            String str4 = downloadFile.count;
            this.list.add(new MediaQualityInfo(i, i2, str, str2, str3, formatTime3, str4, downloadFile.vip_only + "", downloadFile.filename, downloadFile.fid, false, downloadFile.path2, downloadFile.bitstream, downloadFile.original, downloadFile.hdr, downloadFile.colorbit, downloadFile.filename, downloadFile.mmfid, downloadFile.tmfid));
        }
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.presenter.VideoPresenter
    public void setSrts(final SRTModel.SubTitles subTitles) {
        final String file_path = subTitles.getFile_path();
        ((ObservableSubscribeProxy) this.service.download2(file_path).map(new Function() { // from class: com.movieboxpro.android.view.activity.videoplayer.-$$Lambda$VideoActivityFactory$9Rrnnh_TbTEKhHiyz7unrqZ1xqw
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return VideoActivityFactory.this.lambda$setSrts$15$VideoActivityFactory(file_path, subTitles, (ResponseBody) obj);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new io.reactivex.Observer<Boolean>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.47
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onNext(Boolean bool) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }
        });
    }

    public /* synthetic */ Boolean lambda$setSrts$15$VideoActivityFactory(String str, SRTModel.SubTitles subTitles, ResponseBody responseBody) throws Exception {
        String str2 = Constant.DIR_DOWNLOAD + "/" + str.substring(str.lastIndexOf("/") + 1);
        File chapterFile = ReaderUtils.getChapterFile(str2);
        this.currSubtitleLang = subTitles.getLang();
        FileUtils.writeFileFromBytesByStream(chapterFile, responseBody.bytes());
        this.currSubtitleContent = FileUtils.readFile2String(chapterFile, FileUtils.getCharset(str2));
        if (this.controller != null) {
            ArrayList arrayList = new ArrayList();
            SrtParser.parseSrt(str2, arrayList);
            this.controller.startSRT(arrayList);
            showToast("Subtitle load successfully");
        }
        return true;
    }

    private void removePlayerFormParent() {
        NormalIjkVideoView normalIjkVideoView = this.ijkVideoView;
        if (normalIjkVideoView != null) {
            ViewParent parent = normalIjkVideoView.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.ijkVideoView);
                return;
            }
            return;
        }
        ViewParent parent2 = this.vlcVideoView.getParent();
        if (parent2 instanceof ViewGroup) {
            ((ViewGroup) parent2).removeView(this.vlcVideoView);
        }
    }

    private static void registerHomeKeyReceiver(Context context) {
        Log.d("ss", "registerHomeKeyReceiver");
        try {
            mHomeKeyReceiver = new HomeWatcherReceiver();
            context.registerReceiver(mHomeKeyReceiver, new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
        } catch (Exception unused) {
        }
    }

    private static void unregisterHomeKeyReceiver(Context context) {
        Log.i("ss", "unregisterHomeKeyReceiver");
        HomeWatcherReceiver homeWatcherReceiver = mHomeKeyReceiver;
        if (homeWatcherReceiver != null) {
            try {
                context.unregisterReceiver(homeWatcherReceiver);
            } catch (Exception unused) {
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateSkipStatus(UpdateSkipEvent updateSkipEvent) {
        this.controller.updateSkipSettingStatus();
    }

    public void setADS(boolean z) {
        if (!z || App.getUserData().isvip == 1) {
        }
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.presenter.VideoPresenter
    public void _initChromCast() {
        try {
            CastContext sharedInstance = CastContext.getSharedInstance(this.activity);
            this.mCastContext = sharedInstance;
            this.mCastSession = sharedInstance.getSessionManager().getCurrentCastSession();
            CastButtonFactory.setUpMediaRouteButton(this.activity, this.controller.getMediaButton());
            setupCastListener();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
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
        remoteMediaClient.addListener(new RemoteMediaClient.Listener() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.48
            @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
            public void onAdBreakStatusUpdated() {
            }

            @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
            public void onSendingRemoteMediaRequest() {
            }

            @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
            public void onStatusUpdated() {
                if (VideoActivityFactory.this.activity != null && !VideoActivityFactory.this.activity.isFinishing()) {
                    VideoActivityFactory.this.activity.startActivity(new Intent(VideoActivityFactory.this.activity, ExpandedControlsActivity.class));
                    EventBus.getDefault().post(new OnCastStartEvent());
                    AppManager.add(VideoActivityFactory.this.activity);
                    AppManager.finish((Class<? extends Activity>) VideoActivityFactory.this.activity.getClass());
                }
                remoteMediaClient.removeListener(this);
            }

            @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
            public void onMetadataUpdated() {
                Log.d(VideoActivityFactory.this.TAG, "!!!!!!!!+onMetadataUpdated");
            }

            @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
            public void onQueueStatusUpdated() {
                Log.d(VideoActivityFactory.this.TAG, "!!!!!!!!+onQueueStatusUpdated");
            }

            @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
            public void onPreloadStatusUpdated() {
                Log.d(VideoActivityFactory.this.TAG, "!!!!!!!!+onPreloadStatusUpdated");
            }
        });
        EventUtils.event("使用ChromeCast");
        NormalPlayerPresenter normalPlayerPresenter = this.ijkPlayerPresenter;
        remoteMediaClient.queueLoad(new MediaQueueItem[]{new MediaQueueItem.Builder(this.mSelectedMedia).setAutoplay(true).setPreloadTime(20.0d).setStartTime(normalPlayerPresenter != null ? normalPlayerPresenter.getCurrentPosition() / 1000 : 0L).build()}, 0, 0, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00da  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void initMediaDate() {
        /*
            Method dump skipped, instructions count: 254
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.initMediaDate():void");
    }

    private void setupCastListener() {
        this.mSessionManagerListener = new SessionManagerListener<CastSession>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.49
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
                VideoActivityFactory.this.mCastSession = castSession;
                VideoActivityFactory.this.loadRemoteMedia(0, true);
                VideoActivityFactory.this.activity.invalidateOptionsMenu();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory$51  reason: invalid class name */
    /* loaded from: classes3.dex */
    public class AnonymousClass51 implements XMLRPCCallback {
        AnonymousClass51() {
        }

        @Override // com.movieboxpro.android.timroes.axmlrpc.XMLRPCCallback
        public void onResponse(long j, Object obj) {
            JSONObject jSONObject = (JSONObject) JSONObject.parse(obj.toString());
            if (jSONObject.containsKey("data")) {
                final List javaList = jSONObject.getJSONArray("data").toJavaList(ExtrModel.class);
                VideoActivityFactory.this.runOnUiThread(new Runnable() { // from class: com.movieboxpro.android.view.activity.videoplayer.-$$Lambda$VideoActivityFactory$51$j29tVm4ivW9nQg3ijq_m01qwy7E
                    @Override // java.lang.Runnable
                    public final void run() {
                        VideoActivityFactory.AnonymousClass51.this.lambda$onResponse$0$VideoActivityFactory$51(javaList);
                    }
                });
            }
            String str = VideoActivityFactory.this.TAG;
            MLog.d(str, "1111onResponse2  :" + jSONObject);
        }

        public /* synthetic */ void lambda$onResponse$0$VideoActivityFactory$51(List list) {
            VideoActivityFactory.this.videHideloading();
            if (VideoActivityFactory.this.controller != null) {
                VideoActivityFactory.this.hasSubtitle = false;
                VideoActivityFactory.this.controller.setOpenSubtitle(list);
            }
        }

        public /* synthetic */ void lambda$onError$1$VideoActivityFactory$51() {
            VideoActivityFactory.this.videHideloading();
        }

        @Override // com.movieboxpro.android.timroes.axmlrpc.XMLRPCCallback
        public void onError(long j, XMLRPCException xMLRPCException) {
            VideoActivityFactory.this.runOnUiThread(new Runnable() { // from class: com.movieboxpro.android.view.activity.videoplayer.-$$Lambda$VideoActivityFactory$51$z7ZM5QbCuCOrjjdDbcp-2UPWtJ8
                @Override // java.lang.Runnable
                public final void run() {
                    VideoActivityFactory.AnonymousClass51.this.lambda$onError$1$VideoActivityFactory$51();
                }
            });
            VideoActivityFactory.this.showToast(xMLRPCException.getMessage());
            String str = VideoActivityFactory.this.TAG;
            MLog.d(str, "1111onError2   : " + xMLRPCException.getMessage() + j);
        }

        public /* synthetic */ void lambda$onServerError$2$VideoActivityFactory$51() {
            VideoActivityFactory.this.videHideloading();
        }

        @Override // com.movieboxpro.android.timroes.axmlrpc.XMLRPCCallback
        public void onServerError(long j, XMLRPCServerException xMLRPCServerException) {
            VideoActivityFactory.this.runOnUiThread(new Runnable() { // from class: com.movieboxpro.android.view.activity.videoplayer.-$$Lambda$VideoActivityFactory$51$eKIK1XUl3381M_AFSc54hXfRIMM
                @Override // java.lang.Runnable
                public final void run() {
                    VideoActivityFactory.AnonymousClass51.this.lambda$onServerError$2$VideoActivityFactory$51();
                }
            });
            VideoActivityFactory.this.showToast(xMLRPCServerException.getMessage());
            String str = VideoActivityFactory.this.TAG;
            MLog.d(str, "1111onServerError2   : " + xMLRPCServerException.getMessage() + j);
        }
    }

    public void getLogin() {
        URL url;
        showLoading();
        this.language = App.deviceLang;
        try {
            url = new URL("https://api.opensubtitles.org/xml-rpc");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            url = null;
        }
        XMLRPCClient xMLRPCClient = new XMLRPCClient(url, 256);
        String readStringConfig = ConfigUtils.readStringConfig(ConfigKey.OPENSUBTITLE_AGENT);
        if (TextUtils.isEmpty(readStringConfig)) {
            readStringConfig = "nPlayer v3";
        }
        xMLRPCClient.cancel(xMLRPCClient.callAsync(this.listener, "LogIn", "", "", "cn", readStringConfig));
    }

    public void openSubtitleSignOut() {
        URL url;
        try {
            url = new URL("https://api.opensubtitles.org/xml-rpc");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            url = null;
        }
        XMLRPCClient xMLRPCClient = new XMLRPCClient(url, 256);
        xMLRPCClient.cancel(xMLRPCClient.callAsync(new XMLRPCCallback() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.52
            @Override // com.movieboxpro.android.timroes.axmlrpc.XMLRPCCallback
            public void onError(long j, XMLRPCException xMLRPCException) {
            }

            @Override // com.movieboxpro.android.timroes.axmlrpc.XMLRPCCallback
            public void onResponse(long j, Object obj) {
            }

            @Override // com.movieboxpro.android.timroes.axmlrpc.XMLRPCCallback
            public void onServerError(long j, XMLRPCServerException xMLRPCServerException) {
            }
        }, "LogOut", this.token));
    }

    public void getSubtitle(String str) {
        BaseMediaModel baseMediaModel = this.movieDownload;
        if (baseMediaModel == null) {
            videHideloading();
        } else if (baseMediaModel.imdb_id == null) {
            videHideloading();
        }
    }

    public void getSRTs(final String str) {
        ((ObservableSubscribeProxy) Observable.zip(this.service.Encoding_list(API.BASE_URL, "Encoding_list").compose(RxUtils.rxTranslate2Bean(HashMap.class)).map($$Lambda$VideoActivityFactory$icRcs97jNvkLmA0uYIeseM6DlkA.INSTANCE), this.service.getOpenSubtitle(str).flatMap(new Function() { // from class: com.movieboxpro.android.view.activity.videoplayer.-$$Lambda$VideoActivityFactory$hMvqr619Gp3i7scIT2pa4sLbbIc
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return VideoActivityFactory.this.lambda$getSRTs$17$VideoActivityFactory(str, (ResponseBody) obj);
            }
        }), new BiFunction() { // from class: com.movieboxpro.android.view.activity.videoplayer.-$$Lambda$VideoActivityFactory$yCHUBbEGTBqqbdQaHleJvuHsAOY
            @Override // io.reactivex.functions.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return VideoActivityFactory.this.lambda$getSRTs$18$VideoActivityFactory((List) obj, (List) obj2);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<String>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.53
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                VideoActivityFactory.this.showLoading();
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(String str2) {
                super.onNext((AnonymousClass53) str2);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                VideoActivityFactory.this.hideLoading();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                VideoActivityFactory.this.hideLoading();
                VideoActivityFactory videoActivityFactory = VideoActivityFactory.this;
                videoActivityFactory.showToast("Subtitle load fail" + apiException.getMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ List lambda$getSRTs$16(HashMap hashMap) throws Exception {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : ((HashMap) JSON.parseObject(JSON.toJSONString(hashMap.get("list")), HashMap.class)).entrySet()) {
            EncodeModel encodeModel = new EncodeModel();
            encodeModel.setLanguage((String) entry.getKey());
            encodeModel.setCode(JSON.parseArray(entry.getValue().toString(), String.class));
            arrayList.add(encodeModel);
        }
        return arrayList;
    }

    public /* synthetic */ ObservableSource lambda$getSRTs$17$VideoActivityFactory(String str, ResponseBody responseBody) throws Exception {
        String str2 = Constant.DIR_DOWNLOAD;
        File saveFile = saveFile(responseBody, str2, str.substring(str.lastIndexOf("/") + 1) + Constant.SUFFIX_ZIP);
        this.srtZipFile = saveFile;
        return getTransCodeObservable(saveFile, true);
    }

    public /* synthetic */ String lambda$getSRTs$18$VideoActivityFactory(List list, List list2) throws Exception {
        this.controller.showTransCodingView(list2, list);
        return "";
    }

    private Observable<List<SrtPraseModel>> getTransCodeObservable(File file, final boolean z) {
        MultipartBody.Part part = null;
        try {
            part = MultipartBody.Part.createFormData("zip_file", URLEncoder.encode(file.getName(), "UTF-8"), RequestBody.create(MediaType.parse(z ? "application/zip" : "text/plain"), file));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return Http.getService().Srt_convert_encoding(API.BASE_URL, buildData("UTF-8"), BuildConfig.APPLICATION_ID, part).map($$Lambda$VideoActivityFactory$55NVUj049EtKdKCigpybKptzVoo.INSTANCE).map(new Function() { // from class: com.movieboxpro.android.view.activity.videoplayer.-$$Lambda$VideoActivityFactory$GvmfShSTotkJHQwJbybayDb-hiU
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return VideoActivityFactory.this.lambda$getTransCodeObservable$20$VideoActivityFactory(z, (HashMap) obj);
            }
        });
    }

    public /* synthetic */ List lambda$getTransCodeObservable$20$VideoActivityFactory(boolean z, HashMap hashMap) throws Exception {
        Object obj = hashMap.get("srt_content");
        Object obj2 = hashMap.get("srt_name");
        if (obj2 instanceof String) {
            this.afterTransCodeName = (String) obj2;
        }
        if (obj instanceof String) {
            if (z) {
                this.currOpenSubtitleContent = (String) obj;
            } else {
                this.currLocalFileSubtitleContent = (String) obj;
            }
            ArrayList arrayList = new ArrayList();
            String str = (String) obj;
            SrtParser.parseContentSrt(str, arrayList);
            File file = new File(Constant.DIR_TRANS_CODE_SUBTITLE + "/" + this.afterTransCodeName);
            FileUtils.writeFileFromString(file, str, false);
            this.subtitle_path = file.getPath();
            return arrayList;
        }
        return new ArrayList();
    }

    public void updateSrt(ExtrModel extrModel) {
        if (!App.isLogin() || this.service == null || this.subtitle_path == null) {
            return;
        }
        File file = new File(this.subtitle_path);
        if (file.exists()) {
            Call<String> uploadAvatar = this.service.uploadAvatar(API.BASE_URL, buildData(extrModel), BuildConfig.APPLICATION_ID, App.versionCode, MultipartBody.Part.createFormData(IDataSource.SCHEME_FILE_TAG, file.getName(), RequestBody.create(MediaType.parse("text/plain"), file)));
            CallManager.add(getClass().getSimpleName(), uploadAvatar);
            uploadAvatar.enqueue(new Callback<String>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.54
                @Override // retrofit2.Callback
                public void onFailure(Call<String> call, Throwable th) {
                }

                @Override // retrofit2.Callback
                public void onResponse(Call<String> call, Response<String> response) {
                    String str = VideoActivityFactory.this.TAG;
                    MLog.d(str, "!!!!@@ : " + response);
                }
            });
        }
    }

    public void updateSrt2(ExtrModel extrModel) {
        MultipartBody.Part part;
        if (App.isLogin() && this.service != null) {
            File file = new File(this.subtitle_path);
            if (file.exists()) {
                String buildDatas = buildDatas(extrModel);
                try {
                    part = MultipartBody.Part.createFormData(IDataSource.SCHEME_FILE_TAG, URLEncoder.encode(file.getName(), "utf-8"), RequestBody.create(MediaType.parse("text/plain"), file));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    part = null;
                }
                if (part != null) {
                    Call<String> uploadAvatar = this.service.uploadAvatar(API.BASE_URL, buildDatas, BuildConfig.APPLICATION_ID, App.versionCode, part);
                    CallManager.add(getClass().getSimpleName(), uploadAvatar);
                    uploadAvatar.enqueue(new Callback<String>() { // from class: com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory.55
                        @Override // retrofit2.Callback
                        public void onFailure(Call<String> call, Throwable th) {
                        }

                        @Override // retrofit2.Callback
                        public void onResponse(Call<String> call, Response<String> response) {
                            BaseResponse baseResponse = (BaseResponse) JSON.parseObject(response.body(), RxUtils.buildType(BaseResponse.class, ResponseUploadExtraSubtitle.class), new Feature[0]);
                            if (baseResponse != null) {
                                VideoActivityFactory.this.extraSubtitle = (ResponseUploadExtraSubtitle) baseResponse.getData();
                                VideoActivityFactory.this.mHandler.postDelayed(VideoActivityFactory.this.uploadSelectDelayInfoRunnable, 1000L);
                            }
                        }
                    });
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v12 */
    /* JADX WARN: Type inference failed for: r8v14 */
    /* JADX WARN: Type inference failed for: r8v16 */
    /* JADX WARN: Type inference failed for: r8v17, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r8v18 */
    /* JADX WARN: Type inference failed for: r8v19 */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r8v6, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r8v7 */
    /* JADX WARN: Type inference failed for: r8v8, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r8v9 */
    public File saveFile(ResponseBody responseBody, String str, String str2) {
        File file;
        byte[] bArr = new byte[2048];
        InputStream inputStream = null;
        try {
            try {
                InputStream byteStream = responseBody.byteStream();
                try {
                    try {
                        File file2 = new File(str);
                        if (!file2.exists()) {
                            file2.mkdirs();
                        }
                        File file3 = new File(file2, (String) str2);
                        try {
                            str2 = new FileOutputStream(file3);
                            while (true) {
                                try {
                                    int read = byteStream.read(bArr);
                                    if (read == -1) {
                                        break;
                                    }
                                    str2.write(bArr, 0, read);
                                } catch (FileNotFoundException e) {
                                    inputStream = byteStream;
                                    file = file3;
                                    e = e;
                                    str2 = str2;
                                    e.printStackTrace();
                                    if (inputStream != null) {
                                        try {
                                            inputStream.close();
                                        } catch (IOException e2) {
                                            e = e2;
                                            Log.e("saveFile", e.getMessage());
                                            return file;
                                        }
                                    }
                                    if (str2 != 0) {
                                        str2.close();
                                    }
                                    return file;
                                } catch (IOException e3) {
                                    inputStream = byteStream;
                                    file = file3;
                                    e = e3;
                                    str2 = str2;
                                    e.printStackTrace();
                                    if (inputStream != null) {
                                        try {
                                            inputStream.close();
                                        } catch (IOException e4) {
                                            e = e4;
                                            Log.e("saveFile", e.getMessage());
                                            return file;
                                        }
                                    }
                                    if (str2 != 0) {
                                        str2.close();
                                    }
                                    return file;
                                } catch (Throwable th) {
                                    th = th;
                                    inputStream = byteStream;
                                    if (inputStream != null) {
                                        try {
                                            inputStream.close();
                                        } catch (IOException e5) {
                                            Log.e("saveFile", e5.getMessage());
                                            throw th;
                                        }
                                    }
                                    if (str2 != 0) {
                                        str2.close();
                                    }
                                    throw th;
                                }
                            }
                            str2.flush();
                            if (byteStream != null) {
                                try {
                                    byteStream.close();
                                } catch (IOException e6) {
                                    Log.e("saveFile", e6.getMessage());
                                    return file3;
                                }
                            }
                            str2.close();
                            return file3;
                        } catch (FileNotFoundException e7) {
                            inputStream = byteStream;
                            file = file3;
                            e = e7;
                            str2 = 0;
                        } catch (IOException e8) {
                            inputStream = byteStream;
                            file = file3;
                            e = e8;
                            str2 = 0;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        str2 = 0;
                    }
                } catch (FileNotFoundException e9) {
                    e = e9;
                    str2 = 0;
                    inputStream = byteStream;
                    file = null;
                } catch (IOException e10) {
                    e = e10;
                    str2 = 0;
                    inputStream = byteStream;
                    file = null;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (FileNotFoundException e11) {
            e = e11;
            file = null;
            str2 = 0;
        } catch (IOException e12) {
            e = e12;
            file = null;
            str2 = 0;
        } catch (Throwable th4) {
            th = th4;
            str2 = 0;
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        this.activity.getMenuInflater().inflate(R.menu.browse, menu);
        CastButtonFactory.setUpMediaRouteButton(App.getContext(), menu, R.id.media_route_menu_item);
        return super.onCreateOptionsMenu(menu);
    }

    private boolean isMovie() {
        return this.movieDownload.getBoxType() == 1;
    }

    @Override // com.movieboxpro.android.base.BaseActivity2, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1 && i2 == -1) {
            String string = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_STATE, "");
            if (this.lastServerDomain.equals(string)) {
                return;
            }
            switchServer();
            this.lastServerDomain = string;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSwitchServer(OnSwitchServerEvent onSwitchServerEvent) {
        String string = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_STATE, "");
        if (this.lastServerDomain.equals(string)) {
            return;
        }
        switchServer();
        this.lastServerDomain = string;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void cancelShowTestSpeed(CancelShowTestSpeedEvent cancelShowTestSpeedEvent) {
        this.controller.cancelShowTestSpeed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchFullscreen(boolean z) {
        if (z) {
            if (Build.VERSION.SDK_INT >= 19) {
                this.playerContainer.setSystemUiVisibility(4096);
            }
            this.activity.getWindow().setFlags(1024, 1024);
            this.activity.getWindow().getDecorView().setSystemUiVisibility(1798);
            if (Build.VERSION.SDK_INT >= 28) {
                WindowManager.LayoutParams attributes = getWindow().getAttributes();
                attributes.layoutInDisplayCutoutMode = 1;
                getWindow().setAttributes(attributes);
                return;
            }
            return;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            this.playerContainer.setSystemUiVisibility(4096);
        }
        this.activity.getWindow().setFlags(1024, 1024);
        this.activity.getWindow().getDecorView().setSystemUiVisibility(1798);
        if (Build.VERSION.SDK_INT >= 28) {
            WindowManager.LayoutParams attributes2 = getWindow().getAttributes();
            attributes2.layoutInDisplayCutoutMode = 2;
            getWindow().setAttributes(attributes2);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (getResources().getConfiguration().orientation == 2) {
            ScreenManageDialog screenManageDialog = this.dialog;
            if (screenManageDialog != null && screenManageDialog.getDialog() != null && this.dialog.getDialog().isShowing() && this.lastResponse != null) {
                this.dialog.dismiss();
                showScreenManageDialog(this.lastResponse);
            }
            if (Build.VERSION.SDK_INT >= 28 && PrefsUtils.getInstance().getBoolean(Constant.Prefs.FULLSCREEN_PLAY, false)) {
                switchFullscreen(true);
                PlayerController playerController = this.controller;
                if (playerController != null) {
                    playerController.setControllerMargin(DensityUtils.getStatusBarHeight());
                }
            }
            PlayerController playerController2 = this.controller;
            if (playerController2 != null) {
                playerController2.switchScreenOrientation(true);
            }
        } else if (getResources().getConfiguration().orientation == 1) {
            ScreenManageDialog screenManageDialog2 = this.dialog;
            if (screenManageDialog2 != null && screenManageDialog2.getDialog() != null && this.dialog.getDialog().isShowing() && this.lastResponse != null) {
                this.dialog.dismiss();
                showScreenManageDialog(this.lastResponse);
            }
            if (Build.VERSION.SDK_INT >= 28 && PrefsUtils.getInstance().getBoolean(Constant.Prefs.FULLSCREEN_PLAY, false)) {
                switchFullscreen(false);
                PlayerController playerController3 = this.controller;
                if (playerController3 != null) {
                    playerController3.setControllerMargin(0);
                }
            }
            PlayerController playerController4 = this.controller;
            if (playerController4 != null) {
                playerController4.switchScreenOrientation(false);
            }
        }
    }
}
