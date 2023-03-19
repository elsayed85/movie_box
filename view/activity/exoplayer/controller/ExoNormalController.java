package com.movieboxpro.android.view.activity.exoplayer.controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.activity.ComponentActivity;
import androidx.core.content.FileProvider;
import androidx.core.util.Pair;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.mediarouter.app.MediaRouteButton;
import com.avery.subtitle.OnMediaStatusListener;
import com.avery.subtitle.SubtitleLoader;
import com.avery.subtitle.model.Subtitle;
import com.avery.subtitle.model.Time;
import com.avery.subtitle.widget.SimpleSubtitleView2;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.dueeeke.model.EncodeModel;
import com.dueeeke.model.ExtrModel;
import com.dueeeke.model.MediaQualityInfo;
import com.dueeeke.model.SRTModel;
import com.dueeeke.model.SrtPraseModel;
import com.dueeeke.model.SubTitleFeedbackModel;
import com.dueeeke.videoplayer.StageState;
import com.dueeeke.videoplayer.util.L;
import com.dueeeke.videoplayer.util.PlayerUtils;
import com.dueeeke.widget.CustomMediaRouteButton;
import com.dueeeke.widget.SubtitleSettingView;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.source.TrackGroup;
import com.huantansheng.easyphotos.utils.file.FileUtils;
import com.jaygoo.widget.OnRangeChangedListener;
import com.jaygoo.widget.RangeSeekBar;
import com.jaygoo.widget.VerticalRangeSeekBar;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseActivity;
import com.movieboxpro.android.config.ConfigKey;
import com.movieboxpro.android.config.ConfigUtils;
import com.movieboxpro.android.db.entity.SubtitleDelayRecord;
import com.movieboxpro.android.event.TransformSubtitleDataEvent;
import com.movieboxpro.android.listener.TrafficSpeedListener;
import com.movieboxpro.android.model.AudioTrackModel;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.EventUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.LogUtils;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.SettingManager;
import com.movieboxpro.android.utils.SrtParser;
import com.movieboxpro.android.utils.TestNetSpeedUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.exoplayer.AudioPlayHelper;
import com.movieboxpro.android.view.activity.exoplayer.ExoAudioTracksFragment;
import com.movieboxpro.android.view.activity.exoplayer.controller.ExoNormalController;
import com.movieboxpro.android.view.activity.user.Login2Activity;
import com.movieboxpro.android.view.activity.user.VipActivity;
import com.movieboxpro.android.view.activity.videoplayer.LastSaidFragment;
import com.movieboxpro.android.view.activity.videoplayer.presenter.NormalPlayerPresenter;
import com.movieboxpro.android.view.activity.vlcvideoplayer.listener.ControllerCallback;
import com.movieboxpro.android.view.activity.vlcvideoplayer.widget.EngineHelpDialog;
import com.movieboxpro.android.view.dialog.ListDialog;
import com.movieboxpro.android.view.dialog.SkipTimeFragment;
import com.movieboxpro.android.view.videocontroller.CcController;
import com.movieboxpro.android.view.videocontroller.LrcCheckController;
import com.movieboxpro.android.view.videocontroller.LrcController;
import com.movieboxpro.android.view.videocontroller.OpenSubtitleController;
import com.movieboxpro.android.view.videocontroller.TransCodingSubtitleController;
import com.movieboxpro.android.view.videocontroller.fragment.SearchUploadSubtitleFragment;
import com.movieboxpro.android.view.videocontroller.fragment.SubtitleListFragment;
import com.movieboxpro.android.view.videocontroller.fragment.TranslateSubtitleActivity;
import com.movieboxpro.android.view.videocontroller.fragment.VideoDefinitionFragment;
import com.movieboxpro.android.view.widget.AntiShakeUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.interfaces.IMedia;
/* loaded from: classes3.dex */
public class ExoNormalController extends StandardVideoController {
    private static String TAG = "NormalController";
    private List<MediaPlayer.TrackDescription> ListTracks;
    protected OpenSubtitleController SubController;
    private String audioOssFid;
    private AudioPlayHelper audioPlayHelper;
    private ArrayList<AudioTrackModel> audioTrackModels;
    private ExoAudioTracksFragment audioTracksFragment;
    private long bitStream;
    private String currAudioTrackLanguage;
    private String currAudioTrackUrl;
    private int currSelectedSubtitlePosition;
    private String currSelectedSubtitleSid;
    private String currSid;
    private SRTModel.SubTitles currSubtitle;
    private SRTModel.SubTitles currViewSelectedSubtitle;
    private int currViewSubtitlePosition;
    private String currViewSubtitleSid;
    private int currentIndex;
    private FrameLayout flCasting;
    private FrameLayout flContainer;
    private ListDialog fontListDialog;
    private RadioGroup fullscreenRadio;
    private boolean isLoadExtraSubtitle;
    private boolean isVideoPlayComplete;
    private ImageView ivCloseChangePos;
    private LastSaidFragment lastSaidFragment;
    private LinearLayout llBottomController;
    private LinearLayout llChangeSubtitlePos;
    private LinearLayout llFullscreen;
    private LinearLayout llPlaySpeed;
    private LinearLayout llSubtitle;
    private RadioGroup mAspectRatioOptions;
    protected ImageView mBottomCCclose;
    protected ImageView mBottomCCmore;
    protected ImageView mBottomCCsize;
    protected ImageView mCC;
    ControllerCallback mCallBack;
    private Context mContext;
    private RadioGroup mDecode;
    private Handler mHandler;
    protected ImageView mLeCast;
    protected LrcCheckController mLrcCheckController;
    protected LrcController mLrcController;
    protected LinearLayout mNetStateController;
    protected ImageView mSetting;
    protected NestedScrollView mSettingController;
    private RadioGroup mSpeedRatioOptions;
    protected ImageView mSrtAdd;
    protected TextView mSrtAverage;
    protected ImageView mSrtClose;
    protected RelativeLayout mSrtController;
    protected LinearLayout mSrtControllers;
    protected ImageView mSrtMore;
    protected TextView mSrtSpeed;
    protected ImageView mSrtsubstate;
    protected ImageView mTrackInfo;
    private ImageView mTvSettings;
    private FrameLayout mainViewContiner;
    private CustomMediaRouteButton mediaRouteButton;
    protected BaseMediaModel movieDownload;
    protected ImageView multiRate;
    RelativeLayout rlAudioDelay;
    Runnable runnable;
    Runnable runnable2;
    private SearchUploadSubtitleFragment searchUploadSubtitleFragment;
    private int showTestNetDelayMillis;
    private SimpleSubtitleView2 simpleSubtitleView;
    private List<SrtPraseModel> srtList;
    private int srtSize;
    private int srtspeed;
    private boolean startTestSpeed;
    private LinkedHashMap<String, List<SRTModel.SubTitles>> subtitleData;
    private View subtitleLine;
    private SubtitleListFragment subtitleListFragment;
    private SubtitleSettingView subtitleSettingView;
    private int subtitleType;
    protected TransCodingSubtitleController transcodingSubtitleController;
    protected ImageView translateSubtitle;
    private TextView tvAudioDelay;
    private TextView tvPlaySpeed;
    private TextView tvTestNetSpeed;
    private int uploadDelayTime;
    private Runnable uploadSrcDelayInfo;
    private VerticalRangeSeekBar verticalSeekBar;
    private VideoDefinitionFragment videoDefinitionFragment;
    private List<SrtPraseModel> viewSrtList;

    /* loaded from: classes3.dex */
    public interface CallBack {
        void Floatstart();

        void addFragment(Fragment fragment, int i);

        void castVideo();

        void changeColor();

        void closeFullscreen();

        void downOpenSubtitle(ExtrModel extrModel);

        int getBoxType();

        int getCurrPosition();

        int getEpisode();

        String getFid();

        String getId();

        void getNetState();

        boolean getOpenSubtitle();

        int getSeason();

        String getServerSubtitleContent();

        Pair<String, String> getSubtitleContentLang(int i);

        String getTitle();

        void hideFragment(Fragment fragment);

        boolean isLocalFile();

        void onNextEpisode();

        void onSelectedExtraSubtitle();

        void onSelectedServiceSubtitle();

        void onShowTrackHintDialog();

        void onTrackComplete(List<IMedia.AudioTrack> list);

        void onVideoPlayComplete();

        void onVideoPlaying();

        void openFullscreen();

        void playError();

        void popPlayer(boolean z);

        void redownOpenSubtitle(ExtrModel extrModel);

        void requestSubtitleFeedbackVote(String str, SRTModel.SubTitles subTitles);

        void setSrt(SRTModel.SubTitles subTitles);

        boolean showAlert();

        void showFragment(Fragment fragment);

        void showFragmentDialog(DialogFragment dialogFragment);

        void subtitleFileSelected(File file);

        void transCode(String str, boolean z);

        void uploadSrtDelayInfo(String str, int i);

        void viewLocalSubtitleFile(File file, int i);

        void voteSubtitleFeedback(int i, String str);
    }

    private void initSearchUploadFragment() {
    }

    public ExoNormalController(Context context) {
        this(context, null);
    }

    public ExoNormalController(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ExoNormalController(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.fontListDialog = null;
        this.srtspeed = 0;
        this.srtSize = PrefsUtils.getInstance().getInt(Constant.Prefs.SUBTITLE_SIZE, 16);
        this.ListTracks = new ArrayList();
        this.bitStream = 0L;
        this.isLoadExtraSubtitle = false;
        this.mHandler = new Handler() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.ExoNormalController.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
            }
        };
        this.srtList = new ArrayList();
        this.currViewSubtitlePosition = -1;
        this.currViewSubtitleSid = "";
        this.currSelectedSubtitlePosition = -1;
        this.currSelectedSubtitleSid = "";
        this.showTestNetDelayMillis = 10000;
        this.uploadDelayTime = ConfigUtils.readIntegerConfig(ConfigKey.UPLOAD_DELAY_TIME, 0);
        this.currentIndex = 0;
        this.startTestSpeed = false;
        this.runnable = new Runnable() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.ExoNormalController.16
            @Override // java.lang.Runnable
            public void run() {
                TestNetSpeedUtils.stopTestSpeed(new TrafficSpeedListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.ExoNormalController.16.1
                    @Override // com.movieboxpro.android.listener.TrafficSpeedListener
                    public void onSpeed(long j) {
                        String tmid;
                        synchronized (this) {
                            if (SettingManager.INSTANCE.isAutoSelectPlayQuality()) {
                                MediaQualityInfo findAutoQualityItem = ExoNormalController.this.findAutoQualityItem(((NormalPlayerPresenter) ExoNormalController.this.mMediaPlayer).getCurrDefinitionItem().getReal_quality(), j);
                                if (findAutoQualityItem != null) {
                                    if (ExoNormalController.this.videoDefinitionFragment == null) {
                                        ExoNormalController.this.changeQuality(findAutoQualityItem, false);
                                    } else {
                                        if (ExoNormalController.this.movieDownload.box_type == 1) {
                                            tmid = findAutoQualityItem.getMmid();
                                        } else {
                                            tmid = findAutoQualityItem.getTmid();
                                        }
                                        ExoNormalController.this.videoDefinitionFragment.setSelectQuality(tmid);
                                    }
                                    ToastUtils.showShort("Switched to " + findAutoQualityItem.getReal_quality() + " automatically");
                                }
                            } else {
                                ExoNormalController.this.mNetStateController.setVisibility(0);
                            }
                        }
                    }
                });
            }
        };
        this.runnable2 = new Runnable() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.ExoNormalController.17
            @Override // java.lang.Runnable
            public void run() {
                synchronized (this) {
                }
            }
        };
        this.audioOssFid = "";
        this.uploadSrcDelayInfo = new Runnable() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.ExoNormalController.25
            @Override // java.lang.Runnable
            public void run() {
                if (ExoNormalController.this.mCallBack != null) {
                    ExoNormalController.this.mCallBack.uploadSrtDelayInfo(ExoNormalController.this.currSid, ExoNormalController.this.srtspeed);
                }
            }
        };
        this.mContext = context;
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.controller.StandardVideoController, com.movieboxpro.android.view.activity.exoplayer.controller.GestureVideoController, com.movieboxpro.android.view.activity.exoplayer.controller.BaseVideoController
    protected void initView() {
        super.initView();
        L.setDebug(false);
        this.simpleSubtitleView = (SimpleSubtitleView2) findViewById(R.id.simpleSubtitleView);
        int i = PrefsUtils.getInstance().getInt(Constant.Prefs.SUBTITLE_SIZE, 16);
        this.srtSize = i;
        this.simpleSubtitleView.changeSize(i);
        this.simpleSubtitleView.bindOnMediaStatusListener(new OnMediaStatusListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.ExoNormalController.2
            @Override // com.avery.subtitle.OnMediaStatusListener
            public boolean isPrepared() {
                return ExoNormalController.this.mMediaPlayer != null;
            }

            @Override // com.avery.subtitle.OnMediaStatusListener
            public boolean isPlaying() {
                if (ExoNormalController.this.mMediaPlayer != null) {
                    return ExoNormalController.this.mMediaPlayer.isPlaying();
                }
                return false;
            }

            @Override // com.avery.subtitle.OnMediaStatusListener
            public long getCurrentPosition() {
                if (ExoNormalController.this.mMediaPlayer != null) {
                    return ExoNormalController.this.mMediaPlayer.getCurrentPosition();
                }
                return 0L;
            }
        });
        TextView textView = (TextView) findViewById(R.id.tvPop);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.-$$Lambda$ExoNormalController$EFSQM7uEHbkhLbsQ6Tiu3B3Wi70
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ExoNormalController.this.lambda$initView$0$ExoNormalController(view);
            }
        });
        if (Build.VERSION.SDK_INT < 21) {
            textView.setVisibility(8);
            ((TextView) findViewById(R.id.float_ratio_name)).setVisibility(8);
        }
        this.simpleSubtitleView.changeColor(PrefsUtils.getInstance().getInt(Constant.Prefs.SUBTITLE_COLOR, -1));
        String string = PrefsUtils.getInstance().getString(Constant.Prefs.SUBTITLE_FONT, "Default");
        if ("Default".equals(string)) {
            this.simpleSubtitleView.changeFont(Typeface.DEFAULT_BOLD);
        } else {
            try {
                this.simpleSubtitleView.changeFont(Typeface.createFromFile(string));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.mainViewContiner = (FrameLayout) this.mControllerView.findViewById(R.id.frameLayout);
        this.llFullscreen = (LinearLayout) this.mControllerView.findViewById(R.id.llFullscreen);
        this.multiRate = (ImageView) this.mControllerView.findViewById(R.id.tv_multi_rate);
        this.mLeCast = (ImageView) this.mControllerView.findViewById(R.id.iv_cast);
        this.mCC = (ImageView) this.mControllerView.findViewById(R.id.iv_cc);
        this.mSetting = (ImageView) this.mControllerView.findViewById(R.id.iv_setting);
        ImageView imageView = (ImageView) this.mControllerView.findViewById(R.id.iv_track);
        this.mTrackInfo = imageView;
        imageView.setEnabled(false);
        this.mLrcController = (LrcController) this.mControllerView.findViewById(R.id.cc_lrccontroller);
        this.mLrcCheckController = (LrcCheckController) this.mControllerView.findViewById(R.id.lrc_check_controller);
        this.transcodingSubtitleController = (TransCodingSubtitleController) this.mControllerView.findViewById(R.id.transCodeController);
        this.llBottomController = (LinearLayout) this.mControllerView.findViewById(R.id.llBottomController);
        this.llSubtitle = (LinearLayout) this.mControllerView.findViewById(R.id.llSubtitle);
        this.subtitleLine = this.mControllerView.findViewById(R.id.subtitleLine);
        this.mTvSettings = (ImageView) findViewById(R.id.tv_settings);
        this.mAspectRatioOptions = (RadioGroup) findViewById(R.id.aspect_ratio_group);
        this.llPlaySpeed = (LinearLayout) findViewById(R.id.llPlaySpeed);
        this.tvPlaySpeed = (TextView) findViewById(R.id.tvPlaySpeed);
        initPositionSeekBar();
        RelativeLayout relativeLayout = (RelativeLayout) this.mControllerView.findViewById(R.id.ll_bottom_srt);
        this.mSrtController = relativeLayout;
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.ExoNormalController.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
        this.mSrtControllers = (LinearLayout) this.mControllerView.findViewById(R.id.ll_bottom_srt_controller);
        this.mSettingController = (NestedScrollView) this.mControllerView.findViewById(R.id.fl_setting_controller);
        this.mControllerView.findViewById(R.id.ivEngineHelp).setOnClickListener(this);
        initSubtitleSettingView();
        this.mBottomCCsize = (ImageView) this.mControllerView.findViewById(R.id.ll_bottom_controller_size);
        this.mBottomCCmore = (ImageView) this.mControllerView.findViewById(R.id.ll_bottom_controller_more);
        this.mBottomCCclose = (ImageView) this.mControllerView.findViewById(R.id.ll_bottom_controller_close);
        this.mBottomCCsize.setOnClickListener(this);
        this.mBottomCCmore.setOnClickListener(this);
        this.mBottomCCclose.setOnClickListener(this);
        this.mNetStateController = (LinearLayout) this.mControllerView.findViewById(R.id.llTestSpeedHint);
        TextView textView2 = (TextView) this.mControllerView.findViewById(R.id.tvSpeedTest);
        this.tvTestNetSpeed = textView2;
        textView2.getPaint().setFlags(8);
        this.tvTestNetSpeed.getPaint().setAntiAlias(true);
        this.SubController = (OpenSubtitleController) this.mControllerView.findViewById(R.id.cc_opensubtitle);
        this.mSrtAverage = (TextView) this.mControllerView.findViewById(R.id.ll_srt_average);
        this.mSrtSpeed = (TextView) this.mControllerView.findViewById(R.id.ll_srt_speed);
        this.mSrtAdd = (ImageView) this.mControllerView.findViewById(R.id.ll_srt_add);
        this.mSrtsubstate = (ImageView) this.mControllerView.findViewById(R.id.ll_srt_subtract);
        this.mSrtClose = (ImageView) this.mControllerView.findViewById(R.id.ll_srt_close);
        this.mSrtMore = (ImageView) this.mControllerView.findViewById(R.id.ll_srt_list);
        this.mSpeedRatioOptions = (RadioGroup) findViewById(R.id.speed_ratio_group);
        this.mSrtMore.setClickable(true);
        this.mSrtAdd.setLongClickable(true);
        final TextView textView3 = (TextView) this.mControllerView.findViewById(R.id.tvSendLog);
        textView3.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.ExoNormalController.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ExoNormalController.this.mCallBack != null) {
                    ExoNormalController.this.reportError();
                    ToastUtils.showShort("Send successfully");
                    textView3.setEnabled(false);
                }
            }
        });
        CustomMediaRouteButton customMediaRouteButton = (CustomMediaRouteButton) this.mControllerView.findViewById(R.id.media_route_button);
        this.mediaRouteButton = customMediaRouteButton;
        customMediaRouteButton.setRemoteIndicatorDrawable(getResources().getDrawable(R.drawable.mr_button_light));
        this.translateSubtitle = (ImageView) this.mControllerView.findViewById(R.id.ll_bottom_controller_translate);
        initRememberSpeed();
        _initSetting();
        initAudioDelayView();
        this.mControllerView.findViewById(R.id.ivSkipSetting).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.ExoNormalController.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ExoNormalController.this.mCallBack != null) {
                    ExoNormalController.this.mCallBack.showFragmentDialog(SkipTimeFragment.Companion.newInstance(ExoNormalController.this.mCallBack.getId(), ExoNormalController.this.mCallBack.getFid(), ExoNormalController.this.mCallBack.getSeason(), ExoNormalController.this.mCallBack.getEpisode()));
                }
            }
        });
        this.translateSubtitle.setOnClickListener(this);
        this.multiRate.setOnClickListener(this);
        this.mSetting.setOnClickListener(this);
        this.mLeCast.setOnClickListener(this);
        this.mCC.setOnClickListener(this);
        this.mTrackInfo.setOnClickListener(this);
        this.mSrtAdd.setOnClickListener(this);
        this.mSrtsubstate.setOnClickListener(this);
        this.mSrtClose.setOnClickListener(this);
        this.mSrtMore.setOnClickListener(this);
        this.SubController.setCallBack(new OpenSubtitleController.mOpenSubtitle() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.ExoNormalController.6
            @Override // com.movieboxpro.android.view.videocontroller.OpenSubtitleController.mOpenSubtitle
            public void disDownlaod(ExtrModel extrModel) {
            }

            @Override // com.movieboxpro.android.view.videocontroller.OpenSubtitleController.mOpenSubtitle
            public void download(ExtrModel extrModel) {
                ExoNormalController.this.mCallBack.downOpenSubtitle(extrModel);
                ExoNormalController.this.mMediaPlayer.pause();
            }
        });
        initCallback();
        initListener();
    }

    public /* synthetic */ void lambda$initView$0$ExoNormalController(View view) {
        ControllerCallback controllerCallback = this.mCallBack;
        if (controllerCallback != null) {
            controllerCallback.popPlayer();
            hideSettingView();
        }
    }

    private void initRememberSpeed() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.llRemember);
        final ImageView imageView = (ImageView) findViewById(R.id.ivCheckable);
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.REMEMBER_PLAY_SPEED, false)) {
            imageView.setImageResource(R.mipmap.ic_checkbox_checked);
            float f = PrefsUtils.getInstance().getFloat(Constant.Prefs.PLAY_SPEED, 1.0f);
            if (f == 1.0f) {
                this.mSpeedRatioOptions.check(R.id.speed_1);
            } else if (f == 1.25f) {
                this.mSpeedRatioOptions.check(R.id.speed_1_25);
            } else if (f == 1.5f) {
                this.mSpeedRatioOptions.check(R.id.speed_1_5);
            } else if (f == 2.0f) {
                this.mSpeedRatioOptions.check(R.id.speed_2);
            } else if (f == 0.5f) {
                this.mSpeedRatioOptions.check(R.id.speed_0_5);
            }
        } else {
            imageView.setImageResource(R.mipmap.ic_checkbox);
        }
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.ExoNormalController.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                boolean z = !PrefsUtils.getInstance().getBoolean(Constant.Prefs.REMEMBER_PLAY_SPEED, false);
                if (z) {
                    imageView.setImageResource(R.mipmap.ic_checkbox_checked);
                } else {
                    imageView.setImageResource(R.mipmap.ic_checkbox);
                }
                PrefsUtils.getInstance().putBoolean(Constant.Prefs.REMEMBER_PLAY_SPEED, z);
            }
        });
    }

    private void initAudioDelayView() {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rlAudioDelay);
        this.rlAudioDelay = relativeLayout;
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.ExoNormalController.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
        ((ImageView) findViewById(R.id.ivAudioDelayClose)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.-$$Lambda$ExoNormalController$Qawb9fzaQU-Pi2gP3TzR84HsX0g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ExoNormalController.this.lambda$initAudioDelayView$1$ExoNormalController(view);
            }
        });
        this.tvAudioDelay = (TextView) findViewById(R.id.tvAudioDelay);
        ((ImageView) findViewById(R.id.ivAudioDelaySub)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.-$$Lambda$ExoNormalController$_Gc2uSh3bIQqdFMa5KvOvhCS8LQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ExoNormalController.this.lambda$initAudioDelayView$2$ExoNormalController(view);
            }
        });
        ((ImageView) findViewById(R.id.ivAudioDelayAdd)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.-$$Lambda$ExoNormalController$c4Z8_J5AqNisw_FHwJmCRFUyvEE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ExoNormalController.this.lambda$initAudioDelayView$3$ExoNormalController(view);
            }
        });
    }

    public /* synthetic */ void lambda$initAudioDelayView$1$ExoNormalController(View view) {
        scaleViewHide(this.rlAudioDelay, 0.5f, false);
        SimpleSubtitleView2 simpleSubtitleView2 = this.simpleSubtitleView;
        if (simpleSubtitleView2 == null || !simpleSubtitleView2.isPlay()) {
            return;
        }
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(100L);
        this.mSrtControllers.setVisibility(0);
        this.mSrtControllers.startAnimation(scaleAnimation);
    }

    public /* synthetic */ void lambda$initAudioDelayView$2$ExoNormalController(View view) {
        removeCallbacks(this.mFadeOut);
        this.mMediaPlayer.setAudioDelay(100L);
        TextView textView = this.tvAudioDelay;
        textView.setText(String.format("%.1f", Float.valueOf(((float) (-this.mMediaPlayer.getAudioDelay())) / 1000.0f)) + "s");
    }

    public /* synthetic */ void lambda$initAudioDelayView$3$ExoNormalController(View view) {
        removeCallbacks(this.mFadeOut);
        this.mMediaPlayer.setAudioDelay(-100L);
        TextView textView = this.tvAudioDelay;
        textView.setText(String.format("%.1f", Float.valueOf(((float) (-this.mMediaPlayer.getAudioDelay())) / 1000.0f)) + "s");
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void showPlaySpeed(float f) {
        this.llPlaySpeed.setVisibility(0);
        this.tvPlaySpeed.setText(String.valueOf(f));
        this.mHandler.postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.ExoNormalController.9
            @Override // java.lang.Runnable
            public void run() {
                ExoNormalController.this.llPlaySpeed.setVisibility(8);
            }
        }, ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void showCastingHolder(String str) {
        if (this.flCasting == null) {
            this.flCasting = (FrameLayout) findViewById(R.id.flCasting);
        }
        this.flCasting.setVisibility(0);
        GlideUtils.load(this.mContext, str, (ImageView) findViewById(R.id.ivCastingHolder));
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void hideCastingHolder() {
        if (this.flCasting == null) {
            this.flCasting = (FrameLayout) findViewById(R.id.flCasting);
        }
        this.flCasting.setVisibility(8);
    }

    public void hiFullscreenSetting() {
        this.llFullscreen.setVisibility(8);
    }

    private void initPositionSeekBar() {
        final FrameLayout frameLayout = (FrameLayout) this.mControllerView.findViewById(R.id.flSubtitle);
        frameLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.-$$Lambda$ExoNormalController$_0f0hEwIJpmoEwJyNC_cCcABnaU
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                ExoNormalController.this.lambda$initPositionSeekBar$4$ExoNormalController(view, i, i2, i3, i4, i5, i6, i7, i8);
            }
        });
        this.llChangeSubtitlePos = (LinearLayout) this.mControllerView.findViewById(R.id.llChangeSubtitlePos);
        ImageView imageView = (ImageView) this.mControllerView.findViewById(R.id.ivCloseChangePos);
        this.ivCloseChangePos = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.-$$Lambda$ExoNormalController$crHNVva6Dj0QmYxokcUGR7TTgiw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ExoNormalController.this.lambda$initPositionSeekBar$5$ExoNormalController(view);
            }
        });
        this.verticalSeekBar = (VerticalRangeSeekBar) this.mControllerView.findViewById(R.id.positionSeekBar);
        float f = PrefsUtils.getInstance().getFloat(Constant.Prefs.SUBTITLE_POS, 0.0f);
        int screenHeight = DensityUtils.getScreenHeight(App.getContext());
        if (f == 0.0f) {
            int dp2px = DensityUtils.dp2px(10.0f);
            DensityUtils.setMarginPx(this.llSubtitle, 0, 0, 0, dp2px);
            if (screenHeight > 0) {
                this.verticalSeekBar.setRange(0.0f, screenHeight, 0.0f);
                this.verticalSeekBar.setProgress(dp2px);
            }
        } else if (f == -1.0f) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.llSubtitle.getLayoutParams();
            layoutParams.gravity = 48;
            this.llSubtitle.setLayoutParams(layoutParams);
            if (screenHeight > 0) {
                this.verticalSeekBar.setRange(0.0f, screenHeight, 0.0f);
                VerticalRangeSeekBar verticalRangeSeekBar = this.verticalSeekBar;
                verticalRangeSeekBar.setProgress(verticalRangeSeekBar.getMaxProgress());
            }
        } else if (screenHeight > 0) {
            float f2 = screenHeight;
            this.verticalSeekBar.setRange(0.0f, f2, 0.0f);
            this.verticalSeekBar.setProgress(f2 * f);
            DensityUtils.setMarginPx(this.llSubtitle, 0, 0, 0, (int) this.verticalSeekBar.getLeftSeekBar().getProgress());
        }
        this.verticalSeekBar.setOnRangeChangedListener(new OnRangeChangedListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.ExoNormalController.10
            @Override // com.jaygoo.widget.OnRangeChangedListener
            public void onStartTrackingTouch(RangeSeekBar rangeSeekBar, boolean z) {
            }

            @Override // com.jaygoo.widget.OnRangeChangedListener
            public void onStopTrackingTouch(RangeSeekBar rangeSeekBar, boolean z) {
            }

            @Override // com.jaygoo.widget.OnRangeChangedListener
            public void onRangeChanged(RangeSeekBar rangeSeekBar, float f3, float f4, boolean z) {
                if (z) {
                    if (ExoNormalController.this.mBottomContainer.getVisibility() == 0) {
                        ExoNormalController.this.hideBaseView();
                        ExoNormalController.this.mShowing = false;
                    }
                    if (f3 <= frameLayout.getHeight() - ExoNormalController.this.llSubtitle.getHeight()) {
                        Log.d(ExoNormalController.TAG, "BOTTOM");
                        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) ExoNormalController.this.llSubtitle.getLayoutParams();
                        layoutParams2.gravity = 80;
                        ExoNormalController.this.llSubtitle.setLayoutParams(layoutParams2);
                        DensityUtils.setMarginPx(ExoNormalController.this.llSubtitle, 0, 0, 0, (int) f3);
                        PrefsUtils.getInstance().putFloat(Constant.Prefs.SUBTITLE_POS, f3 / rangeSeekBar.getMaxProgress());
                        return;
                    }
                    Log.d(ExoNormalController.TAG, "TOP");
                    FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) ExoNormalController.this.llSubtitle.getLayoutParams();
                    layoutParams3.gravity = 48;
                    layoutParams3.bottomMargin = 0;
                    ExoNormalController.this.llSubtitle.setLayoutParams(layoutParams3);
                    PrefsUtils.getInstance().putFloat(Constant.Prefs.SUBTITLE_POS, -1.0f);
                }
            }
        });
    }

    public /* synthetic */ void lambda$initPositionSeekBar$4$ExoNormalController(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (i8 == i4 && i6 == i2) {
            return;
        }
        if (i8 == 0 && i6 == 0) {
            return;
        }
        int i9 = i4 - i2;
        float f = i9;
        float progress = (this.verticalSeekBar.getLeftSeekBar().getProgress() * f) / (i8 - i6);
        if (((FrameLayout.LayoutParams) this.llSubtitle.getLayoutParams()).gravity == 48) {
            if (i9 > 0) {
                this.verticalSeekBar.setRange(0.0f, f, 0.0f);
                this.verticalSeekBar.setProgress(progress);
                return;
            }
            return;
        }
        DensityUtils.setMarginPx(this.llSubtitle, 0, 0, 0, (int) progress);
        if (i9 > 0) {
            this.verticalSeekBar.setRange(0.0f, f, 0.0f);
            this.verticalSeekBar.setProgress(progress);
        }
    }

    public /* synthetic */ void lambda$initPositionSeekBar$5$ExoNormalController(View view) {
        this.llChangeSubtitlePos.setVisibility(8);
        this.subtitleLine.setVisibility(8);
        if (this.mMediaPlayer.isPlaying()) {
            return;
        }
        doPauseResume();
    }

    private void initSubtitleSettingView() {
        int i = PrefsUtils.getInstance().getInt(Constant.Prefs.SUBTITLE_COLOR, -1);
        String string = PrefsUtils.getInstance().getString(Constant.Prefs.SUBTITLE_FONT, "Default");
        SubtitleSettingView subtitleSettingView = (SubtitleSettingView) this.mControllerView.findViewById(R.id.subtitleSettingView);
        this.subtitleSettingView = subtitleSettingView;
        subtitleSettingView.setColor(i);
        this.subtitleSettingView.setBgColor(PrefsUtils.getInstance().getInt(Constant.Prefs.SUBTITLE_BG_COLOR, Color.parseColor("#B3000000")));
        boolean z = PrefsUtils.getInstance().getBoolean(Constant.Prefs.OPEN_SUBTITLE_BG, false);
        this.subtitleSettingView.setBackground(z);
        if (z) {
            this.simpleSubtitleView.setBackgroundColor(PrefsUtils.getInstance().getInt(Constant.Prefs.SUBTITLE_BG_COLOR, Color.parseColor("#B3000000")));
        } else {
            this.simpleSubtitleView.setBackgroundColor(0);
        }
        if ("Default".equals(string)) {
            this.subtitleSettingView.setFont("Default", Typeface.DEFAULT_BOLD);
        } else {
            File file = new File(string);
            String name = file.getName();
            try {
                this.subtitleSettingView.setFont(name.substring(0, name.lastIndexOf(FileUtils.HIDDEN_PREFIX)), Typeface.createFromFile(file));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.subtitleSettingView.setDefaultSize(this.srtSize);
        this.subtitleSettingView.setListener(new AnonymousClass11());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.movieboxpro.android.view.activity.exoplayer.controller.ExoNormalController$11  reason: invalid class name */
    /* loaded from: classes3.dex */
    public class AnonymousClass11 implements SubtitleSettingView.SubtitleSettingListener {
        AnonymousClass11() {
        }

        @Override // com.dueeeke.widget.SubtitleSettingView.SubtitleSettingListener
        public void changeSubtitleBgColor() {
            if (ExoNormalController.this.mCallBack != null) {
                ExoNormalController.this.mCallBack.changeSubtitleBgColor();
            }
        }

        @Override // com.dueeeke.widget.SubtitleSettingView.SubtitleSettingListener
        public void openCloseBg(boolean z) {
            if (z) {
                ExoNormalController.this.simpleSubtitleView.setBackgroundColor(Color.parseColor("#B3000000"));
            } else {
                ExoNormalController.this.simpleSubtitleView.setBackgroundColor(0);
            }
            PrefsUtils.getInstance().putBoolean(Constant.Prefs.OPEN_SUBTITLE_BG, z);
        }

        @Override // com.dueeeke.widget.SubtitleSettingView.SubtitleSettingListener
        public void showChangePos() {
            ExoNormalController.this.llChangeSubtitlePos.setVisibility(0);
            ExoNormalController.this.subtitleLine.setVisibility(0);
            ExoNormalController exoNormalController = ExoNormalController.this;
            exoNormalController.scaleViewHide(exoNormalController.subtitleSettingView, 0.2f, true);
            ExoNormalController.this.hideBaseView();
            ExoNormalController.this.mShowing = false;
            ExoNormalController.this.mMediaPlayer.pause();
        }

        @Override // com.dueeeke.widget.SubtitleSettingView.SubtitleSettingListener
        public void onViewHide() {
            ExoNormalController exoNormalController = ExoNormalController.this;
            exoNormalController.scaleViewHide(exoNormalController.subtitleSettingView, 0.2f, true);
        }

        @Override // com.dueeeke.widget.SubtitleSettingView.SubtitleSettingListener
        public void onSubtitleSizeChanged(int i) {
            ExoNormalController.this.srtSize = i;
            PrefsUtils.getInstance().putInt(Constant.Prefs.SUBTITLE_SIZE, ExoNormalController.this.srtSize);
            ExoNormalController.this.simpleSubtitleView.changeSize(ExoNormalController.this.srtSize);
            ExoNormalController exoNormalController = ExoNormalController.this;
            exoNormalController.removeCallbacks(exoNormalController.mFadeOut);
            ExoNormalController exoNormalController2 = ExoNormalController.this;
            exoNormalController2.postDelayed(exoNormalController2.mFadeOut, ExoNormalController.this.mDefaultTimeout);
            ExoNormalController exoNormalController3 = ExoNormalController.this;
            exoNormalController3.post(exoNormalController3.mShowProgress);
        }

        @Override // com.dueeeke.widget.SubtitleSettingView.SubtitleSettingListener
        public void onChangeColor() {
            ExoNormalController.this.mCallBack.changeColor();
        }

        @Override // com.dueeeke.widget.SubtitleSettingView.SubtitleSettingListener
        public void onChangeFont() {
            if (ExoNormalController.this.fontListDialog != null) {
                ExoNormalController.this.fontListDialog.show();
                return;
            }
            try {
                final File[] listFiles = new File("/system/fonts").listFiles();
                final ArrayList arrayList = new ArrayList(listFiles.length);
                arrayList.add("Default");
                for (File file : listFiles) {
                    String name = file.getName();
                    arrayList.add(name.substring(0, name.lastIndexOf(FileUtils.HIDDEN_PREFIX)));
                }
                BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.adapter_text_item, arrayList) { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.ExoNormalController.11.1
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // com.chad.library.adapter.base.BaseQuickAdapter
                    public void convert(BaseViewHolder baseViewHolder, String str) {
                        TextView textView = (TextView) baseViewHolder.getView(R.id.textView);
                        if (baseViewHolder.getAdapterPosition() == 0) {
                            textView.setTypeface(Typeface.DEFAULT_BOLD);
                        } else {
                            try {
                                textView.setTypeface(Typeface.createFromFile(listFiles[baseViewHolder.getAdapterPosition() - 1]));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        textView.setText(str);
                    }
                };
                baseQuickAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.-$$Lambda$ExoNormalController$11$0nXj4DMcPrb9hFNMB3eleeZUoBA
                    @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                    public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                        ExoNormalController.AnonymousClass11.this.lambda$onChangeFont$0$ExoNormalController$11(arrayList, listFiles, baseQuickAdapter2, view, i);
                    }
                });
                ExoNormalController.this.fontListDialog = new ListDialog.Builder(ExoNormalController.this.mContext).setItems(arrayList).setTitleVisible(true).setAdapter(baseQuickAdapter).create();
                ExoNormalController.this.fontListDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
                ToastUtils.showShort("Can't change font:" + e.getMessage());
            }
        }

        public /* synthetic */ void lambda$onChangeFont$0$ExoNormalController$11(List list, File[] fileArr, BaseQuickAdapter baseQuickAdapter, View view, int i) {
            if (ExoNormalController.this.fontListDialog != null) {
                ExoNormalController.this.fontListDialog.dismiss();
            }
            if (i == 0) {
                ExoNormalController.this.simpleSubtitleView.changeFont(Typeface.DEFAULT_BOLD);
                PrefsUtils.getInstance().putString(Constant.Prefs.SUBTITLE_FONT, "Default");
                ExoNormalController.this.subtitleSettingView.setFont((String) list.get(i), Typeface.DEFAULT_BOLD);
                return;
            }
            int i2 = i - 1;
            Typeface createFromFile = Typeface.createFromFile(fileArr[i2]);
            ExoNormalController.this.simpleSubtitleView.changeFont(createFromFile);
            ExoNormalController.this.subtitleSettingView.setFont((String) list.get(i2 + 1), createFromFile);
            PrefsUtils.getInstance().putString(Constant.Prefs.SUBTITLE_FONT, fileArr[i2].getPath());
        }
    }

    public void showLeCastButton() {
        this.mLeCast.setVisibility(0);
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.controller.StandardVideoController
    protected int getBoxType() {
        ControllerCallback controllerCallback = this.mCallBack;
        if (controllerCallback != null) {
            return controllerCallback.getBoxType();
        }
        return 1;
    }

    private void initListener() {
        this.mRlNext.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.ExoNormalController.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AntiShakeUtils.isInvalidClick(ExoNormalController.this.mRlNext) || ExoNormalController.this.mCallBack == null) {
                    return;
                }
                ExoNormalController.this.mCallBack.onNextEpisode();
            }
        });
        this.tvTestNetSpeed.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.-$$Lambda$ExoNormalController$-3m2-crqFO6vHcxD4dKkl96npvo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ExoNormalController.this.lambda$initListener$6$ExoNormalController(view);
            }
        });
    }

    public /* synthetic */ void lambda$initListener$6$ExoNormalController(View view) {
        showLogin("Slow connection, do you want to switch to the standby line?");
        this.mNetStateController.setVisibility(8);
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void showLocalSubtitle(List<SrtPraseModel> list, List<Subtitle> list2, String str) {
        ControllerCallback controllerCallback = this.mCallBack;
        if (controllerCallback != null) {
            controllerCallback.subtitleFileSelected(new File(str));
        }
        this.translateSubtitle.setImageResource(R.mipmap.ic_translate_normal);
        this.transcodingSubtitleController.setVisibility(8);
        this.SubController.setVisibility(8);
        startSRT(list);
        showSimpleSubtitles(list2);
        this.mMediaPlayer.start();
        this.isLoadExtraSubtitle = true;
        setSrtController(0);
        this.subtitleType = 3;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void hideOpenSubtitle() {
        this.SubController.setVisibility(8);
    }

    private void initCallback() {
        this.transcodingSubtitleController.setTransCodingSubtitleCallback(new TransCodingSubtitleController.TransCodingSubtitleCallback() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.ExoNormalController.13
            @Override // com.movieboxpro.android.view.videocontroller.TransCodingSubtitleController.TransCodingSubtitleCallback
            public void onEncodeSelected(String str, boolean z) {
                ExoNormalController.this.mCallBack.transCode(str, z);
            }

            @Override // com.movieboxpro.android.view.videocontroller.TransCodingSubtitleController.TransCodingSubtitleCallback
            public void onDone(List<SrtPraseModel> list, int i, boolean z, List<Subtitle> list2) {
                if (ExoNormalController.this.mCallBack != null) {
                    ExoNormalController.this.mCallBack.onSelectedExtraSubtitle();
                }
                ExoNormalController.this.translateSubtitle.setImageResource(R.mipmap.ic_translate_normal);
                ExoNormalController.this.transcodingSubtitleController.setVisibility(8);
                ExoNormalController.this.SubController.setVisibility(8);
                ExoNormalController.this.startSRT(list);
                ExoNormalController.this.showSimpleSubtitles(list2);
                ExoNormalController.this.mMediaPlayer.start();
                ExoNormalController.this.isLoadExtraSubtitle = true;
                ExoNormalController.this.setSrtController(0);
                if (z) {
                    ExoNormalController.this.subtitleType = 3;
                } else {
                    ExoNormalController.this.subtitleType = 2;
                }
            }

            @Override // com.movieboxpro.android.view.videocontroller.TransCodingSubtitleController.TransCodingSubtitleCallback
            public void onClose() {
                ExoNormalController.this.transcodingSubtitleController.setVisibility(8);
            }
        });
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void showSubtitleFeedback(String str, List<SubTitleFeedbackModel> list, List<SrtPraseModel> list2, final SRTModel.SubTitles subTitles) {
        this.viewSrtList = list2;
        this.mLrcCheckController.setVisibility(0);
        ArrayList arrayList = new ArrayList();
        if (list2 == null) {
            arrayList.addAll(this.srtList);
        } else {
            arrayList.addAll(list2);
        }
        hideSubtitleListFragment();
        this.mLrcCheckController.setDataList(str, SrtParser.selectPos, this.currViewSelectedSubtitle.count, list, arrayList, subTitles, new LrcCheckController.SubtitleFeedbackCallback() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.ExoNormalController.14
            @Override // com.movieboxpro.android.view.videocontroller.LrcCheckController.SubtitleFeedbackCallback
            public void onSelected(SrtPraseModel srtPraseModel, boolean z, List<SrtPraseModel> list3) {
                if (z) {
                    if (ExoNormalController.this.viewSrtList != null) {
                        ExoNormalController.this.srtList.clear();
                        ExoNormalController.this.srtList.addAll(list3);
                    }
                    ExoNormalController.this.translateSubtitle.setImageResource(R.mipmap.ic_translate_selected);
                } else {
                    ExoNormalController.this.translateSubtitle.setImageResource(R.mipmap.ic_translate_normal);
                    if (ExoNormalController.this.viewSrtList != null) {
                        ExoNormalController.this.srtList.clear();
                        ExoNormalController.this.srtList.addAll(ExoNormalController.this.viewSrtList);
                    }
                }
                ExoNormalController.this.showSimpleSubtitles2(list3);
                ExoNormalController.this.setSubtitleDelay(Integer.parseInt(subTitles.getDelay()));
                ExoNormalController.this.isLoadExtraSubtitle = false;
                ExoNormalController exoNormalController = ExoNormalController.this;
                exoNormalController.currSelectedSubtitleSid = exoNormalController.currViewSubtitleSid;
                if (ExoNormalController.this.currSelectedSubtitlePosition == -1) {
                    ExoNormalController.this.startSRT(new ArrayList(ExoNormalController.this.srtList));
                }
                ExoNormalController exoNormalController2 = ExoNormalController.this;
                exoNormalController2.currSid = exoNormalController2.currSelectedSubtitleSid;
                ExoNormalController.this.mLrcCheckController.setVisibility(8);
                ExoNormalController.this.setDelaySecond(Integer.parseInt(subTitles.getDelay()));
                ExoNormalController exoNormalController3 = ExoNormalController.this;
                exoNormalController3.setSrtController(exoNormalController3.srtspeed);
                TextView textView = ExoNormalController.this.mSrtSpeed;
                StringBuilder sb = new StringBuilder();
                sb.append(ExoNormalController.this.srtspeed);
                textView.setText(sb);
                ExoNormalController.this.mMediaPlayer.start();
                if (ExoNormalController.this.subtitleListFragment != null) {
                    ExoNormalController.this.subtitleListFragment.setSelectPosition(ExoNormalController.this.currViewSubtitlePosition);
                }
                ExoNormalController.this.mCallBack.onSelectedServiceSubtitle();
                ExoNormalController.this.startTimerUploadSrtDelayInfo();
                ExoNormalController.this.mMediaPlayer.start();
            }

            @Override // com.movieboxpro.android.view.videocontroller.LrcCheckController.SubtitleFeedbackCallback
            public void onVoteClick(int i) {
                ExoNormalController.this.mCallBack.voteSubtitleFeedback(i, ExoNormalController.this.currViewSelectedSubtitle.sid);
            }

            @Override // com.movieboxpro.android.view.videocontroller.LrcCheckController.SubtitleFeedbackCallback
            public void onCancel() {
                ExoNormalController.this.mMediaPlayer.start();
            }
        });
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void setNewSubtitle(ArrayList<Subtitle> arrayList) {
        this.simpleSubtitleView.setSubtitles(arrayList);
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public ImageView getCastButton() {
        return this.mLeCast;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void voteFeedbackSuccess(int i) {
        this.mLrcCheckController.voteSuccess(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDelaySecond(int i) {
        SubtitleDelayRecord findDelayRecordBySid = App.getDB().subtitleDelayRecordDao().findDelayRecordBySid(this.currSid);
        if (findDelayRecordBySid != null) {
            this.srtspeed = findDelayRecordBySid.getDelaySecond();
            this.simpleSubtitleView.setDelay(i);
            return;
        }
        this.srtspeed = i;
    }

    private String getLrcText(String str) {
        try {
            InputStream open = getContext().getAssets().open(str);
            byte[] bArr = new byte[open.available()];
            open.read(bArr);
            open.close();
            return new String(bArr);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public int getSubtitleDelay() {
        SimpleSubtitleView2 simpleSubtitleView2 = this.simpleSubtitleView;
        if (simpleSubtitleView2 != null) {
            return simpleSubtitleView2.getDelay();
        }
        return 0;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public List<Subtitle> getSubtitlesData() {
        SimpleSubtitleView2 simpleSubtitleView2 = this.simpleSubtitleView;
        if (simpleSubtitleView2 != null) {
            return simpleSubtitleView2.getSubtitles();
        }
        return new ArrayList();
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public String getAudioTrackLanguage() {
        return this.currAudioTrackLanguage;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public String getAudioTrackUrl() {
        return this.currAudioTrackUrl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSimpleSubtitle(final String str, final String str2, final String str3, final String str4) {
        SimpleSubtitleView2 simpleSubtitleView2 = this.simpleSubtitleView;
        if (simpleSubtitleView2 != null) {
            simpleSubtitleView2.setSubtitlePath(str, new SubtitleLoader.MessCodeListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.-$$Lambda$ExoNormalController$fnluKQJECh-PitIIEmlmIgadnA4
                @Override // com.avery.subtitle.SubtitleLoader.MessCodeListener
                public final void messCode() {
                    ExoNormalController.this.lambda$showSimpleSubtitle$7$ExoNormalController(str, str2, str3, str4);
                }
            });
        }
    }

    public /* synthetic */ void lambda$showSimpleSubtitle$7$ExoNormalController(String str, String str2, String str3, String str4) {
        ControllerCallback controllerCallback = this.mCallBack;
        if (controllerCallback != null) {
            controllerCallback.downloadAndTransCodeSubtitle(str, str2, str3, str4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSimpleSubtitles(List<Subtitle> list) {
        SimpleSubtitleView2 simpleSubtitleView2 = this.simpleSubtitleView;
        if (simpleSubtitleView2 == null || list == null) {
            return;
        }
        simpleSubtitleView2.setSubtitles(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSubtitleDelay(int i) {
        SimpleSubtitleView2 simpleSubtitleView2 = this.simpleSubtitleView;
        if (simpleSubtitleView2 != null) {
            simpleSubtitleView2.setDelay(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSimpleSubtitles2(List<SrtPraseModel> list) {
        if (this.simpleSubtitleView == null || list == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (SrtPraseModel srtPraseModel : list) {
            Subtitle subtitle = new Subtitle();
            subtitle.content = srtPraseModel.getSrtBody();
            subtitle.start = new Time(srtPraseModel.getBeginTime());
            subtitle.end = new Time(srtPraseModel.getEndTime());
            arrayList.add(subtitle);
        }
        this.simpleSubtitleView.setSubtitles(arrayList);
    }

    private void setQuality(String str) {
        if (str == null) {
            this.multiRate.setImageResource(R.mipmap.ic_media_quality_origin);
            return;
        }
        char c = 65535;
        switch (str.hashCode()) {
            case 1687:
                if (str.equals("4K")) {
                    c = 3;
                    break;
                }
                break;
            case 110308:
                if (str.equals("org")) {
                    c = 4;
                    break;
                }
                break;
            case 1572835:
                if (str.equals("360p")) {
                    c = 0;
                    break;
                }
                break;
            case 1688155:
                if (str.equals("720p")) {
                    c = 1;
                    break;
                }
                break;
            case 46737913:
                if (str.equals("1080p")) {
                    c = 2;
                    break;
                }
                break;
        }
        if (c == 0) {
            this.multiRate.setImageResource(R.mipmap.ic_media_quality_smooth);
        } else if (c == 1) {
            this.multiRate.setImageResource(R.mipmap.ic_media_quality_medium);
        } else if (c == 2) {
            this.multiRate.setImageResource(R.mipmap.ic_media_quality_high);
        } else if (c == 3) {
            this.multiRate.setImageResource(R.mipmap.ic_media_quality_super);
        } else if (c != 4) {
        } else {
            this.multiRate.setImageResource(R.mipmap.ic_media_quality_origin);
        }
    }

    private void _initSetting() {
        this.mAspectRatioOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.-$$Lambda$ExoNormalController$dGoxZQyW1BtEK-eWZwGjNgTlH78
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                ExoNormalController.this.lambda$_initSetting$8$ExoNormalController(radioGroup, i);
            }
        });
        this.mSpeedRatioOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.-$$Lambda$ExoNormalController$mG3OZITiJiSJGNK7h0glC-AJVrY
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                ExoNormalController.this.lambda$_initSetting$9$ExoNormalController(radioGroup, i);
            }
        });
        this.mDecode = (RadioGroup) findViewById(R.id.medcode_ratio_group);
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.OPEN_HARD_CODEC, true)) {
            this.mDecode.check(R.id.medcode_open);
        } else {
            this.mDecode.check(R.id.medcode_close);
        }
        this.mDecode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.-$$Lambda$ExoNormalController$ZdDjwteFkCYeAFd-9EUSVs6djQY
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                ExoNormalController.this.lambda$_initSetting$10$ExoNormalController(radioGroup, i);
            }
        });
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.engine_ratio_group);
        int i = PrefsUtils.getInstance().getInt(Constant.Prefs.PLAYER_ENGINE, 0);
        if (i == 0) {
            radioGroup.check(R.id.AutoEngine);
        } else if (i == 1) {
            radioGroup.check(R.id.ijkPlayer);
        } else if (i == 2) {
            radioGroup.check(R.id.vlcPlayer);
        } else if (i == 3) {
            radioGroup.check(R.id.exoPlayer);
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.-$$Lambda$ExoNormalController$uwJFBQnFfRxkXuj3jUjrXLJj1y4
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup2, int i2) {
                ExoNormalController.this.lambda$_initSetting$11$ExoNormalController(radioGroup2, i2);
            }
        });
        RadioGroup radioGroup2 = (RadioGroup) findViewById(R.id.rgLandscape);
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.AUTO_LANDSCAPE, false)) {
            radioGroup2.check(R.id.rbOnLandscape);
        } else {
            radioGroup2.check(R.id.rbOffLandscape);
        }
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.ExoNormalController.15
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup3, int i2) {
                if (i2 == R.id.rbOnLandscape) {
                    PrefsUtils.getInstance().putBoolean(Constant.Prefs.AUTO_LANDSCAPE, true);
                } else if (i2 == R.id.rbOffLandscape) {
                    PrefsUtils.getInstance().putBoolean(Constant.Prefs.AUTO_LANDSCAPE, false);
                }
            }
        });
        if (Build.VERSION.SDK_INT >= 28) {
            this.flContainer = (FrameLayout) findViewById(R.id.flContainer);
            this.fullscreenRadio = (RadioGroup) findViewById(R.id.fullscreen_ratio_group);
            final int statusBarHeight = DensityUtils.getStatusBarHeight();
            if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.FULLSCREEN_PLAY, false)) {
                this.fullscreenRadio.check(R.id.fullscreen_open);
                this.flContainer.post(new Runnable() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.-$$Lambda$ExoNormalController$gXA12ub9LN5_SmraiDmtnIuypHE
                    @Override // java.lang.Runnable
                    public final void run() {
                        ExoNormalController.this.lambda$_initSetting$12$ExoNormalController(statusBarHeight);
                    }
                });
            } else {
                this.fullscreenRadio.check(R.id.fullscreen_close);
                setControllerMargin(0);
            }
            this.fullscreenRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.-$$Lambda$ExoNormalController$nD5jAfmEQVrcvydK6tTMvW6JAU8
                @Override // android.widget.RadioGroup.OnCheckedChangeListener
                public final void onCheckedChanged(RadioGroup radioGroup3, int i2) {
                    ExoNormalController.this.lambda$_initSetting$13$ExoNormalController(statusBarHeight, radioGroup3, i2);
                }
            });
        }
    }

    public /* synthetic */ void lambda$_initSetting$8$ExoNormalController(RadioGroup radioGroup, int i) {
        if (i == R.id.aspect_fit_parent) {
            this.mMediaPlayer.setScreenScale(0);
            PrefsUtils.getInstance().putInt(Constant.Prefs.PLAY_SCALE_VALUE, 0);
        } else if (i == R.id.aspect_fit_screen) {
            this.mMediaPlayer.setScreenScale(3);
            PrefsUtils.getInstance().putInt(Constant.Prefs.PLAY_SCALE_VALUE, 1);
        } else if (i == R.id.aspect_filling) {
            this.mMediaPlayer.setScreenScale(6);
            PrefsUtils.getInstance().putInt(Constant.Prefs.PLAY_SCALE_VALUE, 2);
        } else if (i == R.id.aspect_16_and_9) {
            this.mMediaPlayer.setScreenScale(1);
            PrefsUtils.getInstance().putInt(Constant.Prefs.PLAY_SCALE_VALUE, 3);
        } else if (i == R.id.aspect_4_and_3) {
            this.mMediaPlayer.setScreenScale(2);
            PrefsUtils.getInstance().putInt(Constant.Prefs.PLAY_SCALE_VALUE, 4);
        } else if (i == R.id.aspect_center) {
            this.mMediaPlayer.setScreenScale(5);
            PrefsUtils.getInstance().putInt(Constant.Prefs.PLAY_SCALE_VALUE, 5);
        }
    }

    public /* synthetic */ void lambda$_initSetting$9$ExoNormalController(RadioGroup radioGroup, int i) {
        float f = 0.5f;
        if (i == R.id.speed_1) {
            this.mMediaPlayer.setSpeed(1.0f);
        } else {
            if (i == R.id.speed_1_25) {
                this.mMediaPlayer.setSpeed(1.25f);
                f = 1.25f;
            } else if (i == R.id.speed_1_5) {
                this.mMediaPlayer.setSpeed(1.5f);
                f = 1.5f;
            } else if (i == R.id.speed_2) {
                this.mMediaPlayer.setSpeed(2.0f);
                f = 2.0f;
            } else if (i == R.id.speed_0_5) {
                this.mMediaPlayer.setSpeed(0.5f);
            }
            PrefsUtils.getInstance().putFloat(Constant.Prefs.PLAY_SPEED, f);
        }
        f = 1.0f;
        PrefsUtils.getInstance().putFloat(Constant.Prefs.PLAY_SPEED, f);
    }

    public /* synthetic */ void lambda$_initSetting$10$ExoNormalController(RadioGroup radioGroup, int i) {
        hideSettingView();
        if (i == R.id.medcode_open) {
            PrefsUtils.getInstance().putBoolean(Constant.Prefs.OPEN_HARD_CODEC, true);
            ((NormalPlayerPresenter) this.mMediaPlayer).switchDefaultDefinition(true);
        } else if (i == R.id.medcode_close) {
            PrefsUtils.getInstance().putBoolean(Constant.Prefs.OPEN_HARD_CODEC, false);
            ((NormalPlayerPresenter) this.mMediaPlayer).switchDefaultDefinition(false);
        }
    }

    public /* synthetic */ void lambda$_initSetting$11$ExoNormalController(RadioGroup radioGroup, int i) {
        if (i == R.id.AutoEngine) {
            hideSettingView();
            PrefsUtils.getInstance().putInt(Constant.Prefs.PLAYER_ENGINE, 0);
            ControllerCallback controllerCallback = this.mCallBack;
            if (controllerCallback != null) {
                controllerCallback.changePlayerEngine(0);
            }
            CommonUtils.onEvent("Player_auto_engine");
            EventUtils.event("播放页面切换Auto引擎");
        } else if (i == R.id.ijkPlayer) {
            hideSettingView();
            PrefsUtils.getInstance().putInt(Constant.Prefs.PLAYER_ENGINE, 1);
            ControllerCallback controllerCallback2 = this.mCallBack;
            if (controllerCallback2 != null) {
                controllerCallback2.changePlayerEngine(1);
            }
            CommonUtils.onEvent("Player_ijk_engine");
            EventUtils.event("播放页面切换ijk引擎");
        } else if (i == R.id.vlcPlayer) {
            hideSettingView();
            PrefsUtils.getInstance().putInt(Constant.Prefs.PLAYER_ENGINE, 2);
            ControllerCallback controllerCallback3 = this.mCallBack;
            if (controllerCallback3 != null) {
                controllerCallback3.changePlayerEngine(2);
            }
            CommonUtils.onEvent("Player_vlc_engine");
            EventUtils.event("播放页面切换vlc引擎");
        } else if (i == R.id.exoPlayer) {
            hideSettingView();
            PrefsUtils.getInstance().putInt(Constant.Prefs.PLAYER_ENGINE, 3);
            ControllerCallback controllerCallback4 = this.mCallBack;
            if (controllerCallback4 != null) {
                controllerCallback4.changePlayerEngine(3);
            }
            CommonUtils.onEvent("Player_exo_engine");
            EventUtils.event("播放页面切换exo引擎");
        }
    }

    public /* synthetic */ void lambda$_initSetting$12$ExoNormalController(int i) {
        if (CommonUtils.isScreenLandscape(App.getContext())) {
            setControllerMargin(i);
        }
    }

    public /* synthetic */ void lambda$_initSetting$13$ExoNormalController(int i, RadioGroup radioGroup, int i2) {
        if (i2 == R.id.fullscreen_open) {
            if (!CommonUtils.isScreenLandscape(this.mContext)) {
                PrefsUtils.getInstance().putBoolean(Constant.Prefs.FULLSCREEN_PLAY, true);
                return;
            }
            ControllerCallback controllerCallback = this.mCallBack;
            if (controllerCallback != null) {
                controllerCallback.openFullscreen();
                setControllerMargin(i);
            }
            PrefsUtils.getInstance().putBoolean(Constant.Prefs.FULLSCREEN_PLAY, true);
        } else if (i2 == R.id.fullscreen_close) {
            if (!CommonUtils.isScreenLandscape(this.mContext)) {
                PrefsUtils.getInstance().putBoolean(Constant.Prefs.FULLSCREEN_PLAY, false);
                return;
            }
            ControllerCallback controllerCallback2 = this.mCallBack;
            if (controllerCallback2 != null) {
                controllerCallback2.closeFullscreen();
                setControllerMargin(0);
            }
            PrefsUtils.getInstance().putBoolean(Constant.Prefs.FULLSCREEN_PLAY, false);
        }
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void setControllerMargin(int i) {
        FrameLayout frameLayout = this.flContainer;
        if (frameLayout != null) {
            DensityUtils.setMarginPx(frameLayout, i, 0, 0, 0);
        }
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.controller.StandardVideoController, com.movieboxpro.android.view.activity.exoplayer.controller.BaseVideoController
    public void setPlayerState(int i) {
        super.setPlayerState(i);
        if (i == 10) {
            this.multiRate.setVisibility(0);
        } else if (i != 11) {
        } else {
            this.multiRate.setVisibility(0);
        }
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.controller.BaseVideoController
    protected void reportError() {
        if (this.mCallBack != null) {
            LogUtils.INSTANCE.uploadError2(true, this.mCallBack.getBoxType(), this.mCallBack.getSeason(), this.mCallBack.getEpisode(), this.mCallBack.getId());
        }
    }

    public void resumePlay() {
        onResume();
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.controller.BaseVideoController
    protected void onResume() {
        this.simpleSubtitleView.resume();
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.controller.StandardVideoController, com.movieboxpro.android.view.activity.exoplayer.controller.BaseVideoController
    public void setPlayState(int i) {
        super.setPlayState(i);
        if (i == 2) {
            Log.d(TAG, "");
        } else if (i == 3) {
            this.isVideoPlayComplete = false;
            this.mMediaPlayer.updateVideoView();
            if (!this.simpleSubtitleView.isPlay()) {
                this.simpleSubtitleView.resume();
            }
            AudioPlayHelper audioPlayHelper = this.audioPlayHelper;
            if (audioPlayHelper != null) {
                audioPlayHelper.play(this.mMediaPlayer);
            }
        } else if (i == 4) {
            this.simpleSubtitleView.pause();
            AudioPlayHelper audioPlayHelper2 = this.audioPlayHelper;
            if (audioPlayHelper2 != null) {
                audioPlayHelper2.pause();
            }
        } else if (i == 5) {
            this.isVideoPlayComplete = true;
            ControllerCallback controllerCallback = this.mCallBack;
            if (controllerCallback != null) {
                controllerCallback.onVideoPlayComplete();
                this.mTopContainer.setVisibility(8);
                this.mBottomContainer.setVisibility(8);
            }
        } else if (i != 8) {
            if (i != 9) {
                return;
            }
            this.mHandler.postDelayed(this.runnable, this.showTestNetDelayMillis);
            int i2 = this.uploadDelayTime;
            if (i2 != 0) {
                this.mHandler.postDelayed(this.runnable2, i2);
            }
            if (this.startTestSpeed) {
                return;
            }
            TestNetSpeedUtils.INSTANCE.startTestSpeed((ComponentActivity) this.mContext);
            this.startTestSpeed = true;
        } else {
            TestNetSpeedUtils testNetSpeedUtils = TestNetSpeedUtils.INSTANCE;
            TestNetSpeedUtils.stopTestSpeed();
            this.simpleSubtitleView.start();
            this.isVideoPlayComplete = false;
            if (this.mCallBack != null) {
                this.mHandler.removeCallbacks(this.runnable);
                this.mHandler.removeCallbacks(this.runnable2);
                this.mNetStateController.setVisibility(8);
                this.mCallBack.onVideoPlaying();
            }
            int i3 = PrefsUtils.getInstance().getInt(Constant.Prefs.PLAY_SCALE_VALUE, -1);
            if (i3 == 1) {
                this.mAspectRatioOptions.check(R.id.aspect_fit_screen);
            } else if (i3 == 2) {
                this.mAspectRatioOptions.check(R.id.aspect_filling);
            } else if (i3 == 3) {
                this.mAspectRatioOptions.check(R.id.aspect_16_and_9);
            } else if (i3 == 4) {
                this.mAspectRatioOptions.check(R.id.aspect_4_and_3);
            } else if (i3 == 5) {
                this.mAspectRatioOptions.check(R.id.aspect_center);
            }
            this.mTrackInfo.setEnabled(true);
            this.audioTracksFragment = null;
        }
    }

    private MediaQualityInfo findQualityItem(String str, List<MediaQualityInfo> list) {
        for (MediaQualityInfo mediaQualityInfo : list) {
            if (str.equalsIgnoreCase(mediaQualityInfo.getReal_quality()) && !TextUtils.isEmpty(mediaQualityInfo.getPath())) {
                return mediaQualityInfo;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MediaQualityInfo findAutoQualityItem(String str, long j) {
        List<MediaQualityInfo> definitionData = ((NormalPlayerPresenter) this.mMediaPlayer).getDefinitionData();
        if ("4K".equalsIgnoreCase(str)) {
            if (j >= 2048) {
                return findQualityItem("1080p", definitionData);
            }
            if (j > 500) {
                return findQualityItem("720p", definitionData);
            }
            return findQualityItem("360p", definitionData);
        } else if ("1080p".equalsIgnoreCase(str)) {
            if (j > 500) {
                return findQualityItem("720p", definitionData);
            }
            return findQualityItem("360p", definitionData);
        } else if ("720p".equalsIgnoreCase(str)) {
            return findQualityItem("360p", definitionData);
        } else {
            if ("360p".equalsIgnoreCase(str)) {
                return null;
            }
            if (j >= 2048) {
                return findQualityItem("1080p", definitionData);
            }
            if (j > 500) {
                return findQualityItem("720p", definitionData);
            }
            return findQualityItem("360p", definitionData);
        }
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void cancelShowTestSpeed() {
        this.mHandler.removeCallbacks(this.runnable);
    }

    public void showLogin(String str) {
        ControllerCallback controllerCallback;
        if (getContext() == null || (controllerCallback = this.mCallBack) == null) {
            return;
        }
        controllerCallback.getNetState();
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.controller.StandardVideoController
    protected void playNextEpisode() {
        ControllerCallback controllerCallback;
        super.playNextEpisode();
        if (AntiShakeUtils.isInvalidClick(this.llNextEpisode) || (controllerCallback = this.mCallBack) == null) {
            return;
        }
        controllerCallback.onNextEpisode();
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.controller.StandardVideoController
    protected void finishPlay() {
        ControllerCallback controllerCallback = this.mCallBack;
        if (controllerCallback != null) {
            controllerCallback.finishPlay();
        }
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void setNextViewVisible() {
        if (this.mRlNext.getVisibility() == 8) {
            this.mRlNext.setVisibility(0);
        }
        this.llNextEpisode.setVisibility(0);
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void setNextViewGone() {
        if (this.mRlNext.getVisibility() == 0) {
            this.mRlNext.setVisibility(8);
        }
        this.llNextEpisode.setVisibility(8);
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void resetDefinition(List<MediaQualityInfo> list) {
        VideoDefinitionFragment videoDefinitionFragment = this.videoDefinitionFragment;
        if (videoDefinitionFragment != null) {
            videoDefinitionFragment.resetDefinition(list);
        }
    }

    public void hideSubtitleListFragment() {
        SubtitleListFragment subtitleListFragment = this.subtitleListFragment;
        if (subtitleListFragment == null || !subtitleListFragment.isVisible()) {
            return;
        }
        this.mCallBack.hideFragment(this.subtitleListFragment);
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void hideSubtitleView() {
        hideSubtitleListFragment();
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void clearSubtitle() {
        this.srtList.clear();
        this.simpleSubtitleView.stop();
        this.mSrtControllers.setVisibility(8);
        this.simpleSubtitleView.clearSubtitles();
        SubtitleListFragment subtitleListFragment = this.subtitleListFragment;
        if (subtitleListFragment != null) {
            subtitleListFragment.clearData();
        }
    }

    private void showSettingView() {
        this.mSettingController.setVisibility(0);
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 1.0f, 1, 0.0f, 1, 0.0f, 1, 0.0f);
        translateAnimation.setInterpolator(new DecelerateInterpolator());
        translateAnimation.setDuration(300L);
        this.mSettingController.startAnimation(translateAnimation);
    }

    private void hideSettingView() {
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 1.0f, 1, 0.0f, 1, 0.0f);
        translateAnimation.setDuration(300L);
        translateAnimation.setInterpolator(new DecelerateInterpolator());
        translateAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.ExoNormalController.18
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                if (ExoNormalController.this.mSettingController != null) {
                    ExoNormalController.this.mSettingController.setVisibility(8);
                }
            }
        });
        this.mSettingController.startAnimation(translateAnimation);
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.controller.StandardVideoController, android.view.View.OnClickListener
    public void onClick(View view) {
        ControllerCallback controllerCallback;
        super.onClick(view);
        removeCallbacks(this.mFadeOut);
        postDelayed(this.mFadeOut, this.mDefaultTimeout);
        int id = view.getId();
        if (id == R.id.tv_multi_rate) {
            if (this.mSettingController.getVisibility() == 0) {
                hideSettingView();
            }
            VideoDefinitionFragment videoDefinitionFragment = this.videoDefinitionFragment;
            if (videoDefinitionFragment == null) {
                initVideoDefinitionFragment();
                this.mCallBack.addFragment(this.videoDefinitionFragment, R.id.frameLayout);
                return;
            }
            this.mCallBack.showFragment(videoDefinitionFragment);
        } else if (id == R.id.iv_cast) {
            this.mCallBack.castVideo();
        } else if (id == R.id.iv_cc) {
            if (this.mSettingController.getVisibility() == 0) {
                hideSettingView();
            }
            SubtitleListFragment subtitleListFragment = this.subtitleListFragment;
            if (subtitleListFragment == null) {
                initSubtitleListFragment();
                this.mCallBack.addFragment(this.subtitleListFragment, R.id.frameLayout);
                return;
            }
            this.mCallBack.showFragment(subtitleListFragment);
        } else if (id == R.id.ll_srt_add) {
            this.srtspeed++;
            saveDelaySubtitleRecord();
            this.mSrtSpeed.setText(this.srtspeed + "");
            post(this.mShowProgress);
            startTimerUploadSrtDelayInfo();
            this.simpleSubtitleView.setDelay(this.srtspeed);
        } else if (id == R.id.ll_srt_subtract) {
            this.srtspeed--;
            saveDelaySubtitleRecord();
            this.mSrtSpeed.setText(this.srtspeed + "");
            post(this.mShowProgress);
            startTimerUploadSrtDelayInfo();
            this.simpleSubtitleView.setDelay(this.srtspeed);
        } else if (id == R.id.ll_srt_close) {
            scaleViewHide(this.mSrtController, 0.1f, true);
        } else if (id == R.id.ll_srt_list) {
            this.mMediaPlayer.pause();
            showLastSaidFragment();
        } else if (id == R.id.iv_setting) {
            showSettingView();
        } else if (id == R.id.iv_track) {
            ExoAudioTracksFragment exoAudioTracksFragment = this.audioTracksFragment;
            if (exoAudioTracksFragment == null) {
                initAudioTracksFragment();
                this.mCallBack.addFragment(this.audioTracksFragment, R.id.frameLayout);
                return;
            }
            this.mCallBack.showFragment(exoAudioTracksFragment);
        } else if (id == R.id.ll_bottom_controller_more) {
            removeCallbacks(this.mFadeOut);
            scaleViewShow(this.subtitleSettingView, 0.2f);
            this.subtitleSettingView.setVisibility(0);
            this.simpleSubtitleView.setVisibility(0);
            this.mSrtControllers.setVisibility(8);
        } else if (id == R.id.ll_bottom_controller_size) {
            scaleViewShow(this.mSrtController, 0.1f);
            this.mSrtController.setVisibility(0);
            this.mSrtControllers.setVisibility(8);
        } else if (id == R.id.ll_bottom_controller_close) {
            this.mSrtControllers.setVisibility(8);
            SubtitleListFragment subtitleListFragment2 = this.subtitleListFragment;
            if (subtitleListFragment2 != null) {
                subtitleListFragment2.clearData();
            }
            this.simpleSubtitleView.stop();
            this.simpleSubtitleView.reset();
            ControllerCallback controllerCallback2 = this.mCallBack;
            if (controllerCallback2 != null) {
                controllerCallback2.desSelectSubtitle(this.currSid);
            }
        } else if (id != R.id.ll_bottom_controller_translate) {
            if (id != R.id.ivEngineHelp || (controllerCallback = this.mCallBack) == null) {
                return;
            }
            controllerCallback.showFragmentDialog(new EngineHelpDialog());
        } else {
            ControllerCallback controllerCallback3 = this.mCallBack;
            if (controllerCallback3 != null) {
                Pair<String, String> subtitleContentLang = controllerCallback3.getSubtitleContentLang(this.subtitleType);
                if (subtitleContentLang != null) {
                    EventBus.getDefault().postSticky(new TransformSubtitleDataEvent(subtitleContentLang.first, subtitleContentLang.second, this.simpleSubtitleView.getSubtitles()));
                    TranslateSubtitleActivity.Companion.start(this.mContext, "", subtitleContentLang.second);
                    return;
                }
                ToastUtils.showShort("not support translate");
            }
        }
    }

    public void setSubtitleSize(int i) {
        SimpleSubtitleView2 simpleSubtitleView2 = this.simpleSubtitleView;
        if (simpleSubtitleView2 != null) {
            simpleSubtitleView2.changeSize(i);
        }
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.controller.GestureVideoController
    protected void setVolume(int i) {
        if (this.mMediaPlayer == null || this.audioPlayHelper != null) {
            return;
        }
        this.mMediaPlayer.setVolume(i);
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.controller.GestureVideoController
    protected int getVolume() {
        if (this.mMediaPlayer != null) {
            return this.mMediaPlayer.getVolume();
        }
        return 100;
    }

    private void initAudioTracksFragment() {
        ExoAudioTracksFragment newInstance = ExoAudioTracksFragment.Companion.newInstance(((NormalPlayerPresenter) this.mMediaPlayer).getCurrDefinitionItem(), new ArrayList<>(this.mMediaPlayer.getExoAudioTracks()), this.audioTrackModels, this.mCallBack.getId(), this.mCallBack.getBoxType(), this.mMediaPlayer.getCurrAudioIndex(), this.audioOssFid, this.mCallBack.getSeason(), this.mCallBack.getEpisode());
        this.audioTracksFragment = newInstance;
        newInstance.setListener(new ExoAudioTracksFragment.OnAudioTrackListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.ExoNormalController.19
            @Override // com.movieboxpro.android.view.activity.exoplayer.ExoAudioTracksFragment.OnAudioTrackListener
            public void onOnlineAudios(ArrayList<AudioTrackModel> arrayList) {
                ExoNormalController.this.audioTrackModels = arrayList;
            }

            @Override // com.movieboxpro.android.view.activity.exoplayer.ExoAudioTracksFragment.OnAudioTrackListener
            public void onAdjustAudioDelay(String str) {
                if (ExoNormalController.this.mCallBack != null) {
                    ExoNormalController.this.mCallBack.hideFragment(ExoNormalController.this.audioTracksFragment);
                }
                ExoNormalController.this.show(StageState.SingleTab);
                ExoNormalController exoNormalController = ExoNormalController.this;
                exoNormalController.removeCallbacks(exoNormalController.mFadeOut);
                ExoNormalController.this.rlAudioDelay.setVisibility(0);
                ExoNormalController.this.mSrtControllers.setVisibility(8);
            }

            @Override // com.movieboxpro.android.view.activity.exoplayer.ExoAudioTracksFragment.OnAudioTrackListener
            public void onAudioClick(TrackGroup trackGroup, String str, boolean z, boolean z2, String str2, String str3) {
                if (TextUtils.isEmpty(str)) {
                    ExoNormalController.this.currAudioTrackLanguage = str3;
                    ExoNormalController.this.currAudioTrackUrl = str;
                } else {
                    ExoNormalController.this.currAudioTrackUrl = str;
                    ExoNormalController.this.currAudioTrackLanguage = "";
                }
                if (TextUtils.isEmpty(str)) {
                    ExoNormalController.this.audioOssFid = "";
                    ExoNormalController.this.rlAudioDelay.setVisibility(8);
                    ExoNormalController.this.mMediaPlayer.setExoAudioTrack(trackGroup);
                    ExoNormalController.this.mMediaPlayer.setMute(false);
                    if (ExoNormalController.this.audioPlayHelper != null) {
                        ExoNormalController.this.audioPlayHelper.release();
                        ExoNormalController.this.audioPlayHelper = null;
                    }
                } else {
                    ExoNormalController.this.tvAudioDelay.setText("0s");
                    ExoNormalController.this.audioOssFid = str2;
                    if (ExoNormalController.this.audioPlayHelper != null) {
                        ExoNormalController.this.audioPlayHelper.changeUrl(str, ExoNormalController.this.mMediaPlayer, ExoNormalController.this);
                    } else {
                        ExoNormalController.this.audioPlayHelper = new AudioPlayHelper();
                        ExoNormalController.this.audioPlayHelper.play(str, ExoNormalController.this.mMediaPlayer, ExoNormalController.this);
                    }
                    ExoNormalController.this.mMediaPlayer.setMute(true);
                }
                if (ExoNormalController.this.mCallBack != null) {
                    ExoNormalController.this.mCallBack.hideFragment(ExoNormalController.this.audioTracksFragment);
                }
            }
        });
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void selectSubtitle(int i, SRTModel.SubTitles subTitles) {
        this.currSid = subTitles.getSid();
        ControllerCallback controllerCallback = this.mCallBack;
        if (controllerCallback != null) {
            controllerCallback.setSrt(subTitles);
        }
        setDelaySecond(Integer.parseInt(subTitles.getDelay()));
        setSrtController(this.srtspeed);
        this.currSelectedSubtitlePosition = i;
        this.currSelectedSubtitleSid = subTitles.sid;
        this.isLoadExtraSubtitle = false;
        this.translateSubtitle.setImageResource(R.mipmap.ic_translate_normal);
        showSimpleSubtitle(subTitles.getFile_path(), subTitles.sid, subTitles.lang, subTitles.language);
    }

    private void initSubtitleListFragment() {
        SubtitleListFragment newInstance = SubtitleListFragment.Companion.newInstance(this.mCallBack.getTitle(), this.mCallBack.getId(), this.mCallBack.getFid(), Integer.valueOf(this.mCallBack.getSeason()), Integer.valueOf(this.mCallBack.getEpisode()), Integer.valueOf(this.mCallBack.getBoxType()));
        this.subtitleListFragment = newInstance;
        newInstance.setSubtitleData(this.subtitleData);
        this.subtitleListFragment.setSearchUploadCallback(new SearchUploadSubtitleFragment.SearchUploadSubtitleCallback() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.ExoNormalController.20
            @Override // com.movieboxpro.android.view.videocontroller.fragment.SearchUploadSubtitleFragment.SearchUploadSubtitleCallback
            public void selectedSubtitle(File file, String str) {
                if (file != null) {
                    ExoNormalController.this.showSimpleSubtitle(file.getPath(), str, "", "");
                    ExoNormalController.this.mCallBack.subtitleFileSelected(file);
                }
            }

            @Override // com.movieboxpro.android.view.videocontroller.fragment.SearchUploadSubtitleFragment.SearchUploadSubtitleCallback
            public void viewSearchUploadSubtitle(File file, int i) {
                if (file == null || ExoNormalController.this.mCallBack == null) {
                    return;
                }
                ExoNormalController.this.mCallBack.viewLocalSubtitleFile(file, i);
            }

            @Override // com.movieboxpro.android.view.videocontroller.fragment.SearchUploadSubtitleFragment.SearchUploadSubtitleCallback
            public void startOpenSubtitleSite() {
                ExoNormalController.this.hideSubtitleListFragment();
                if (ExoNormalController.this.mCallBack == null || ExoNormalController.this.mCallBack.getOpenSubtitle()) {
                    return;
                }
                ExoNormalController.this.SubController.setVisibility(0);
            }
        });
        this.subtitleListFragment.setSubtitleCallback(new CcController.CcCallBack() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.ExoNormalController.21
            @Override // com.movieboxpro.android.view.videocontroller.CcController.CcCallBack
            public void onSelectedCc(int i, SRTModel.SubTitles subTitles) {
                if (subTitles.language != null && (subTitles.language.contains("Chinese") || subTitles.language.contains("Japanese"))) {
                    if (ExoNormalController.this.mCallBack != null) {
                        ExoNormalController.this.mCallBack.checkSubtitleCode(i, subTitles);
                        return;
                    }
                    return;
                }
                ExoNormalController.this.currSid = subTitles.getSid();
                if (ExoNormalController.this.mCallBack != null) {
                    ExoNormalController.this.mCallBack.setSrt(subTitles);
                }
                ExoNormalController.this.setDelaySecond(Integer.parseInt(subTitles.getDelay()));
                ExoNormalController exoNormalController = ExoNormalController.this;
                exoNormalController.setSrtController(exoNormalController.srtspeed);
                ExoNormalController.this.currSelectedSubtitlePosition = i;
                ExoNormalController.this.currSelectedSubtitleSid = subTitles.sid;
                ExoNormalController.this.isLoadExtraSubtitle = false;
                ExoNormalController.this.translateSubtitle.setImageResource(R.mipmap.ic_translate_normal);
                ExoNormalController.this.showSimpleSubtitle(subTitles.getFile_path(), subTitles.sid, subTitles.lang, subTitles.language);
            }

            @Override // com.movieboxpro.android.view.videocontroller.CcController.CcCallBack
            public void onDisSelectedCc(String str) {
                ExoNormalController.this.mSrtControllers.setVisibility(8);
                if (ExoNormalController.this.subtitleListFragment != null) {
                    ExoNormalController.this.subtitleListFragment.disSelect();
                }
                ExoNormalController.this.simpleSubtitleView.stop();
                ExoNormalController.this.simpleSubtitleView.reset();
                if (ExoNormalController.this.mCallBack != null) {
                    ExoNormalController.this.mCallBack.desSelectSubtitle(ExoNormalController.this.currSid);
                }
            }

            @Override // com.movieboxpro.android.view.videocontroller.CcController.CcCallBack
            public void onViewSubtitleClick(int i, SRTModel.SubTitles subTitles) {
                ExoNormalController.this.mMediaPlayer.pause();
                if (ExoNormalController.this.mCallBack != null) {
                    if (ExoNormalController.this.currSelectedSubtitleSid.equals(subTitles.sid)) {
                        ExoNormalController.this.mCallBack.requestSubtitleFeedbackVote(subTitles.sid, subTitles);
                    } else {
                        ExoNormalController.this.mCallBack.requestSubtitleFeedbackVote(subTitles.sid, subTitles);
                    }
                    ExoNormalController.this.currViewSelectedSubtitle = subTitles;
                    ExoNormalController.this.currViewSubtitlePosition = i;
                    ExoNormalController.this.currViewSubtitleSid = subTitles.sid;
                }
            }
        });
    }

    private void showLastSaidFragment() {
        this.lastSaidFragment = LastSaidFragment.Companion.newInstance(this.simpleSubtitleView.getCurrSubtitlePos(), CommonUtils.isScreenLandscape(this.mContext));
        if (this.simpleSubtitleView.getSubtitles() != null) {
            this.lastSaidFragment.setSubtitleData(this.simpleSubtitleView.getSubtitles());
        } else {
            this.lastSaidFragment.setSubtitleData(new ArrayList());
        }
        this.lastSaidFragment.setConfirmLastSaidListener(new LastSaidFragment.OnConfirmListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.-$$Lambda$ExoNormalController$LyMDjLpYcofLfN_Rs4KAFdfJ07A
            @Override // com.movieboxpro.android.view.activity.videoplayer.LastSaidFragment.OnConfirmListener
            public final void onConfirmLastSaid(int i, Subtitle subtitle) {
                ExoNormalController.this.lambda$showLastSaidFragment$14$ExoNormalController(i, subtitle);
            }
        });
        this.mCallBack.showFragmentDialog(this.lastSaidFragment);
    }

    public /* synthetic */ void lambda$showLastSaidFragment$14$ExoNormalController(int i, Subtitle subtitle) {
        int currSubtitle = (int) (this.simpleSubtitleView.setCurrSubtitle(subtitle) / 1000);
        this.srtspeed = currSubtitle;
        this.mSrtSpeed.setText(String.valueOf(currSubtitle));
        saveDelaySubtitleRecord();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scaleViewHide(final View view, float f, final boolean z) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, 1, f, 1, 1.0f);
        scaleAnimation.setDuration(150L);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.ExoNormalController.22
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                view.setVisibility(0);
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(8);
                ScaleAnimation scaleAnimation2 = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, 1, 0.5f, 1, 0.5f);
                scaleAnimation2.setDuration(100L);
                if (z) {
                    ExoNormalController.this.mSrtControllers.setVisibility(0);
                    ExoNormalController.this.mSrtControllers.startAnimation(scaleAnimation2);
                }
            }
        });
        view.startAnimation(scaleAnimation);
    }

    private void scaleViewShow(final View view, float f) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, 1, f, 1, 1.0f);
        scaleAnimation.setDuration(150L);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.ExoNormalController.23
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                view.setVisibility(0);
            }
        });
        view.startAnimation(scaleAnimation);
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.controller.StandardVideoController
    public long getBitStream() {
        return this.bitStream;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void setVideoModel(BaseMediaModel baseMediaModel) {
        this.movieDownload = baseMediaModel;
    }

    private void initVideoDefinitionFragment() {
        String tmid;
        if (this.movieDownload.box_type == 1) {
            tmid = ((NormalPlayerPresenter) this.mMediaPlayer).getCurrDefinitionItem().getMmid();
        } else {
            tmid = ((NormalPlayerPresenter) this.mMediaPlayer).getCurrDefinitionItem().getTmid();
        }
        VideoDefinitionFragment newInstance = VideoDefinitionFragment.Companion.newInstance(((NormalPlayerPresenter) this.mMediaPlayer).getDefinitionData(), this.movieDownload.box_type, this.movieDownload.id, this.movieDownload.season, this.movieDownload.episode, tmid);
        this.videoDefinitionFragment = newInstance;
        newInstance.setCallback(new VideoDefinitionFragment.SwitchDefinitionCallback() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.-$$Lambda$ExoNormalController$-M1WHaIEUt09MDXbHJwV4pxitDs
            @Override // com.movieboxpro.android.view.videocontroller.fragment.VideoDefinitionFragment.SwitchDefinitionCallback
            public final void onSwitchDefinition(MediaQualityInfo mediaQualityInfo, int i, boolean z) {
                ExoNormalController.this.lambda$initVideoDefinitionFragment$15$ExoNormalController(mediaQualityInfo, i, z);
            }
        });
    }

    public /* synthetic */ void lambda$initVideoDefinitionFragment$15$ExoNormalController(MediaQualityInfo mediaQualityInfo, int i, boolean z) {
        changeQuality(mediaQualityInfo, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeQuality(MediaQualityInfo mediaQualityInfo, boolean z) {
        int indexOf = ((NormalPlayerPresenter) this.mMediaPlayer).getDefinitionData().indexOf(mediaQualityInfo);
        this.mTrackInfo.setEnabled(false);
        this.ListTracks.clear();
        AudioPlayHelper audioPlayHelper = this.audioPlayHelper;
        if (audioPlayHelper != null) {
            audioPlayHelper.release();
            this.audioPlayHelper = null;
        }
        this.mNetStateController.setVisibility(8);
        PrefsUtils.getInstance().putString(Constant.Prefs.LAST_SELECT_QUALITY, mediaQualityInfo.getReal_quality());
        PrefsUtils.getInstance().putInt(Constant.Prefs.IS_LAST_ORIGIN, mediaQualityInfo.getOriginal());
        ControllerCallback controllerCallback = this.mCallBack;
        if (controllerCallback != null && z) {
            controllerCallback.hideFragment(this.videoDefinitionFragment);
        }
        if (App.isLogin()) {
            if (mediaQualityInfo.getIsVip().equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                if (App.getUserData().isvip == 1) {
                    ((NormalPlayerPresenter) this.mMediaPlayer).switchDefinition(indexOf);
                    setQuality(((NormalPlayerPresenter) this.mMediaPlayer).getDefinitionData().get(indexOf).getReal_quality());
                    return;
                }
                ((BaseActivity) PlayerUtils.scanForActivity(getContext())).route(VipActivity.class);
            }
            ((NormalPlayerPresenter) this.mMediaPlayer).switchDefinition(indexOf);
            setQuality(((NormalPlayerPresenter) this.mMediaPlayer).getDefinitionData().get(indexOf).getReal_quality());
        } else if (mediaQualityInfo.getIsVip().equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
            ((BaseActivity) PlayerUtils.scanForActivity(getContext())).route(Login2Activity.class);
        } else {
            ((NormalPlayerPresenter) this.mMediaPlayer).switchDefinition(indexOf);
            setQuality(((NormalPlayerPresenter) this.mMediaPlayer).getDefinitionData().get(indexOf).getReal_quality());
        }
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.controller.StandardVideoController
    protected void playWithOtherPlayer() {
        Uri fromFile;
        MediaQualityInfo currDefinitionItem = ((NormalPlayerPresenter) this.mMediaPlayer).getCurrDefinitionItem();
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.setAction("android.intent.action.VIEW");
        try {
            if (this.mCallBack.isLocalFile()) {
                if (Build.VERSION.SDK_INT >= 24) {
                    Context context = this.mContext;
                    fromFile = FileProvider.getUriForFile(context, App.getInstance().getPackageName() + ".fileProvider", new File(currDefinitionItem.getPath()));
                    intent.addFlags(3);
                } else {
                    fromFile = Uri.fromFile(new File(currDefinitionItem.getPath()));
                }
                intent.setDataAndType(fromFile, "video/mp4");
            } else {
                intent.setDataAndType(Uri.parse(currDefinitionItem.getPath()), "video/mp4");
            }
            this.mContext.startActivity(intent);
            Intent.createChooser(intent, "Choose a player to play");
        } catch (Exception unused) {
            ToastUtils.showShort("No player available");
        }
    }

    private void calBitStream(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (str.contains("MB/s")) {
                try {
                    this.bitStream = (long) ((Double.parseDouble(str.replace("MB/s", "")) / 8.0d) * 1024.0d * 0.6d);
                    return;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    return;
                }
            }
            return;
        }
        this.bitStream = 0L;
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.controller.StandardVideoController
    protected void showTestSpeedHint() {
        this.mNetStateController.setVisibility(0);
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void setSubtitle(List<SrtPraseModel> list) {
        this.subtitleType = 3;
        this.isLoadExtraSubtitle = false;
        this.srtList.clear();
        this.srtList.addAll(list);
        this.srtspeed = 0;
        if (this.mSrtControllers.getVisibility() != 0) {
            setSrtController(this.srtspeed);
        }
        this.mLrcController.resetData(this.srtList);
        this.translateSubtitle.setImageResource(R.mipmap.ic_translate_normal);
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void selectSubtitle(SRTModel.SubTitles subTitles) {
        this.currSid = subTitles.getSid();
        ControllerCallback controllerCallback = this.mCallBack;
        if (controllerCallback != null) {
            controllerCallback.setSrt(subTitles);
        }
        setDelaySecond(Integer.parseInt(subTitles.getDelay()));
        setSrtController(this.srtspeed);
        this.currSelectedSubtitleSid = subTitles.sid;
        this.isLoadExtraSubtitle = false;
        this.translateSubtitle.setImageResource(R.mipmap.ic_translate_normal);
        showSimpleSubtitle(subTitles.getFile_path(), subTitles.sid, subTitles.lang, subTitles.language);
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void showMuteHint() {
        this.mHandler.postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.ExoNormalController.24
            @Override // java.lang.Runnable
            public void run() {
                final TextView textView = (TextView) ExoNormalController.this.findViewById(R.id.tvMuteHint);
                if (textView != null) {
                    textView.setVisibility(0);
                    TranslateAnimation translateAnimation = new TranslateAnimation(1, -1.0f, 1, 0.0f, 1, 0.0f, 1, 0.0f);
                    translateAnimation.setDuration(300L);
                    textView.startAnimation(translateAnimation);
                    ExoNormalController.this.mHandler.postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.ExoNormalController.24.1
                        @Override // java.lang.Runnable
                        public void run() {
                            TranslateAnimation translateAnimation2 = new TranslateAnimation(1, 0.0f, 1, -1.0f, 1, 0.0f, 1, 0.0f);
                            translateAnimation2.setDuration(300L);
                            textView.startAnimation(translateAnimation2);
                            translateAnimation2.setAnimationListener(new Animation.AnimationListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.ExoNormalController.24.1.1
                                @Override // android.view.animation.Animation.AnimationListener
                                public void onAnimationRepeat(Animation animation) {
                                }

                                @Override // android.view.animation.Animation.AnimationListener
                                public void onAnimationStart(Animation animation) {
                                }

                                @Override // android.view.animation.Animation.AnimationListener
                                public void onAnimationEnd(Animation animation) {
                                    if (textView != null) {
                                        textView.setVisibility(8);
                                    }
                                }
                            });
                        }
                    }, ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
                }
            }
        }, ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void showTranslateSubtitle(List<SrtPraseModel> list) {
        if (list != null) {
            ArrayList arrayList = new ArrayList();
            for (SrtPraseModel srtPraseModel : list) {
                Subtitle subtitle = new Subtitle();
                subtitle.content = srtPraseModel.getSrtBody();
                subtitle.start = new Time(srtPraseModel.getBeginTime());
                subtitle.end = new Time(srtPraseModel.getEndTime());
                arrayList.add(subtitle);
            }
            this.simpleSubtitleView.setSubtitles(arrayList);
            if (this.mLrcCheckController.getVisibility() == 0) {
                this.mLrcCheckController.setTranslateSubtitle(list);
            } else {
                this.translateSubtitle.setImageResource(R.mipmap.ic_translate_selected);
                this.srtList.clear();
                this.srtList.addAll(list);
                this.mLrcController.resetData(this.srtList);
            }
            this.simpleSubtitleView.refreshSubtitle();
        }
    }

    private void saveDelaySubtitleRecord() {
        if (this.isLoadExtraSubtitle || this.currSid == null) {
            return;
        }
        SubtitleDelayRecord subtitleDelayRecord = new SubtitleDelayRecord();
        subtitleDelayRecord.setDelaySecond(this.srtspeed);
        subtitleDelayRecord.setSid(this.currSid);
        App.getDB().subtitleDelayRecordDao().insert(subtitleDelayRecord);
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void saveDelayExtraSubtitleRecord(String str) {
        this.isLoadExtraSubtitle = false;
        this.currSid = str;
        saveDelaySubtitleRecord();
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public int getSrtspeed() {
        return this.srtspeed;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startTimerUploadSrtDelayInfo() {
        this.mHandler.removeCallbacks(this.uploadSrcDelayInfo);
        this.mHandler.postDelayed(this.uploadSrcDelayInfo, 300000L);
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.controller.StandardVideoController, com.movieboxpro.android.view.activity.exoplayer.controller.BaseVideoController
    public void hide(StageState stageState) {
        ControllerCallback controllerCallback;
        ControllerCallback controllerCallback2;
        ControllerCallback controllerCallback3;
        VideoDefinitionFragment videoDefinitionFragment;
        SubtitleListFragment subtitleListFragment;
        ExoAudioTracksFragment exoAudioTracksFragment;
        MLog.d("SSS", "hTIANNA : " + this.mShowing + ":" + this.mIsLocked);
        if (!stageState.equals(StageState.Runnable) && (this.mSettingController.getVisibility() == 0 || this.SubController.getVisibility() == 0 || this.mLrcController.getVisibility() == 0 || this.mLrcCheckController.getVisibility() == 0 || this.transcodingSubtitleController.getVisibility() == 0 || (((videoDefinitionFragment = this.videoDefinitionFragment) != null && videoDefinitionFragment.isFragmentVisible()) || (((subtitleListFragment = this.subtitleListFragment) != null && subtitleListFragment.isFragmentVisible()) || ((exoAudioTracksFragment = this.audioTracksFragment) != null && exoAudioTracksFragment.isFragmentVisible()))))) {
            hideSettingView();
            this.SubController.setVisibility(8);
            this.mLrcController.setVisibility(8);
            this.mLrcCheckController.setVisibility(8);
            this.transcodingSubtitleController.setVisibility(8);
            VideoDefinitionFragment videoDefinitionFragment2 = this.videoDefinitionFragment;
            if (videoDefinitionFragment2 != null && videoDefinitionFragment2.isFragmentVisible() && (controllerCallback3 = this.mCallBack) != null) {
                controllerCallback3.hideFragment(this.videoDefinitionFragment);
            }
            SubtitleListFragment subtitleListFragment2 = this.subtitleListFragment;
            if (subtitleListFragment2 != null && subtitleListFragment2.isFragmentVisible() && (controllerCallback2 = this.mCallBack) != null) {
                controllerCallback2.hideFragment(this.subtitleListFragment);
            }
            ExoAudioTracksFragment exoAudioTracksFragment2 = this.audioTracksFragment;
            if (exoAudioTracksFragment2 != null && exoAudioTracksFragment2.isFragmentVisible() && (controllerCallback = this.mCallBack) != null) {
                controllerCallback.hideFragment(this.audioTracksFragment);
            }
        }
        super.hide(stageState);
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.controller.StandardVideoController, com.movieboxpro.android.view.activity.exoplayer.controller.BaseVideoController
    public void show(StageState stageState) {
        ControllerCallback controllerCallback;
        ControllerCallback controllerCallback2;
        ControllerCallback controllerCallback3;
        VideoDefinitionFragment videoDefinitionFragment;
        SubtitleListFragment subtitleListFragment;
        ExoAudioTracksFragment exoAudioTracksFragment;
        MLog.d("SSS", "sTIANNA : " + this.mShowing + ":" + this.mIsLocked);
        if (this.isVideoPlayComplete) {
            return;
        }
        if (stageState.equals(StageState.SingleTab) && (this.mSettingController.getVisibility() == 0 || this.SubController.getVisibility() == 0 || this.mLrcController.getVisibility() == 0 || this.mLrcCheckController.getVisibility() == 0 || this.transcodingSubtitleController.getVisibility() == 0 || (((videoDefinitionFragment = this.videoDefinitionFragment) != null && videoDefinitionFragment.isFragmentVisible()) || (((subtitleListFragment = this.subtitleListFragment) != null && subtitleListFragment.isFragmentVisible()) || ((exoAudioTracksFragment = this.audioTracksFragment) != null && exoAudioTracksFragment.isFragmentVisible()))))) {
            hideSettingView();
            this.SubController.setVisibility(8);
            this.mLrcController.setVisibility(8);
            this.mLrcCheckController.setVisibility(8);
            this.transcodingSubtitleController.setVisibility(8);
            VideoDefinitionFragment videoDefinitionFragment2 = this.videoDefinitionFragment;
            if (videoDefinitionFragment2 != null && (controllerCallback3 = this.mCallBack) != null) {
                controllerCallback3.hideFragment(videoDefinitionFragment2);
            }
            SubtitleListFragment subtitleListFragment2 = this.subtitleListFragment;
            if (subtitleListFragment2 != null && (controllerCallback2 = this.mCallBack) != null) {
                controllerCallback2.hideFragment(subtitleListFragment2);
            }
            ExoAudioTracksFragment exoAudioTracksFragment2 = this.audioTracksFragment;
            if (exoAudioTracksFragment2 == null || !exoAudioTracksFragment2.isFragmentVisible() || (controllerCallback = this.mCallBack) == null) {
                return;
            }
            controllerCallback.hideFragment(this.audioTracksFragment);
            return;
        }
        super.show(stageState);
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.controller.StandardVideoController, com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public boolean onBackPressed() {
        ControllerCallback controllerCallback;
        ControllerCallback controllerCallback2;
        ControllerCallback controllerCallback3;
        VideoDefinitionFragment videoDefinitionFragment;
        SubtitleListFragment subtitleListFragment;
        ExoAudioTracksFragment exoAudioTracksFragment;
        if (this.mSettingController.getVisibility() == 0 || this.mLrcController.getVisibility() == 0 || this.mLrcCheckController.getVisibility() == 0 || this.transcodingSubtitleController.getVisibility() == 0 || this.SubController.getVisibility() == 0 || (((videoDefinitionFragment = this.videoDefinitionFragment) != null && videoDefinitionFragment.isFragmentVisible()) || (((subtitleListFragment = this.subtitleListFragment) != null && subtitleListFragment.isFragmentVisible()) || ((exoAudioTracksFragment = this.audioTracksFragment) != null && exoAudioTracksFragment.isFragmentVisible())))) {
            hideSettingView();
            this.mLrcController.setVisibility(8);
            this.mLrcCheckController.setVisibility(8);
            this.transcodingSubtitleController.setVisibility(8);
            this.SubController.setVisibility(8);
            VideoDefinitionFragment videoDefinitionFragment2 = this.videoDefinitionFragment;
            if (videoDefinitionFragment2 != null && (controllerCallback3 = this.mCallBack) != null) {
                controllerCallback3.hideFragment(videoDefinitionFragment2);
            }
            SubtitleListFragment subtitleListFragment2 = this.subtitleListFragment;
            if (subtitleListFragment2 != null && (controllerCallback2 = this.mCallBack) != null) {
                controllerCallback2.hideFragment(subtitleListFragment2);
            }
            ExoAudioTracksFragment exoAudioTracksFragment2 = this.audioTracksFragment;
            if (exoAudioTracksFragment2 == null || !exoAudioTracksFragment2.isFragmentVisible() || (controllerCallback = this.mCallBack) == null) {
                return true;
            }
            controllerCallback.hideFragment(this.audioTracksFragment);
            return true;
        }
        return super.onBackPressed();
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.controller.StandardVideoController, com.movieboxpro.android.view.activity.exoplayer.controller.BaseVideoController
    public int setProgress(int i, int i2) {
        return super.setProgress(i, i2);
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void showTransCodingView(final List<SrtPraseModel> list, final List<EncodeModel> list2) {
        this.mHandler.post(new Runnable() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.-$$Lambda$ExoNormalController$4yqztuIVkpqXD6JyhQp2qjfR_Kg
            @Override // java.lang.Runnable
            public final void run() {
                ExoNormalController.this.lambda$showTransCodingView$16$ExoNormalController(list, list2);
            }
        });
    }

    public /* synthetic */ void lambda$showTransCodingView$16$ExoNormalController(List list, List list2) {
        this.transcodingSubtitleController.setVisibility(0);
        this.transcodingSubtitleController.setSubtitleData(list, list2);
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void showTransCodingViewFromViewLocal(final List<SrtPraseModel> list, final List<EncodeModel> list2, final int i) {
        this.mHandler.post(new Runnable() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.-$$Lambda$ExoNormalController$IgKCIUW-9lS8t4YBpJaH4IgpXlA
            @Override // java.lang.Runnable
            public final void run() {
                ExoNormalController.this.lambda$showTransCodingViewFromViewLocal$17$ExoNormalController(list, list2, i);
            }
        });
    }

    public /* synthetic */ void lambda$showTransCodingViewFromViewLocal$17$ExoNormalController(List list, List list2, int i) {
        this.transcodingSubtitleController.setVisibility(0);
        this.transcodingSubtitleController.setSubtitleDataFromLocal(list, list2, i);
        SubtitleListFragment subtitleListFragment = this.subtitleListFragment;
        if (subtitleListFragment == null || !subtitleListFragment.isFragmentVisible()) {
            return;
        }
        this.mCallBack.hideFragment(this.subtitleListFragment);
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void setTransCodeResult(List<SrtPraseModel> list) {
        this.transcodingSubtitleController.setSubtitle(list);
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void startSRT(List<SrtPraseModel> list) {
        this.subtitleType = 1;
        this.srtList.clear();
        this.srtList.addAll(list);
        this.mHandler.post(new Runnable() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.-$$Lambda$ExoNormalController$ipw5JVf9N2WNkrvkdwr6APvYejQ
            @Override // java.lang.Runnable
            public final void run() {
                ExoNormalController.this.lambda$startSRT$19$ExoNormalController();
            }
        });
        startTimerUploadSrtDelayInfo();
    }

    public /* synthetic */ void lambda$startSRT$19$ExoNormalController() {
        this.mLrcController.setDataList(this.srtList, new LrcController.LrcCallBack() { // from class: com.movieboxpro.android.view.activity.exoplayer.controller.-$$Lambda$ExoNormalController$n49m5FP8jReE58inzFr0sD8ry2E
            @Override // com.movieboxpro.android.view.videocontroller.LrcController.LrcCallBack
            public final void mLrcSelected(SrtPraseModel srtPraseModel) {
                ExoNormalController.this.lambda$startSRT$18$ExoNormalController(srtPraseModel);
            }
        });
    }

    public /* synthetic */ void lambda$startSRT$18$ExoNormalController(SrtPraseModel srtPraseModel) {
        int beginTime = ((int) (srtPraseModel.getBeginTime() - this.mMediaPlayer.getCurrentPosition())) / 1000;
        this.srtspeed = beginTime;
        this.simpleSubtitleView.setDelay(beginTime);
        TextView textView = this.mSrtSpeed;
        StringBuilder sb = new StringBuilder();
        sb.append(this.srtspeed);
        textView.setText(sb);
        this.mMediaPlayer.start();
        this.mLrcController.setVisibility(8);
    }

    public void setSrtController(int i) {
        this.mSrtControllers.setVisibility(0);
        this.mSrtController.setVisibility(8);
        this.simpleSubtitleView.setVisibility(0);
        this.srtspeed = i;
        this.simpleSubtitleView.setDelay(i);
        TextView textView = this.mSrtAverage;
        StringBuilder sb = new StringBuilder();
        sb.append("Most users choose ");
        sb.append(this.srtspeed);
        sb.append("s");
        sb.append(" , do you want to speed up or slow down?");
        textView.setText(sb);
        TextView textView2 = this.mSrtSpeed;
        textView2.setText(this.srtspeed + "");
        hideSubtitleListFragment();
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void setSrt(LinkedHashMap<String, List<SRTModel.SubTitles>> linkedHashMap) {
        SubtitleListFragment subtitleListFragment;
        if (this.subtitleData != null && (subtitleListFragment = this.subtitleListFragment) != null) {
            subtitleListFragment.resetSubtitleData(linkedHashMap);
        }
        this.subtitleData = linkedHashMap;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void setQualityController() {
        setQuality(((NormalPlayerPresenter) this.mMediaPlayer).getDefinitionData().size() > ((NormalPlayerPresenter) this.mMediaPlayer).getDefinition() ? ((NormalPlayerPresenter) this.mMediaPlayer).getDefinitionData().get(((NormalPlayerPresenter) this.mMediaPlayer).getDefinition()).getReal_quality() : "NULL");
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void setOpenSubtitle(List<ExtrModel> list) {
        OpenSubtitleController openSubtitleController = this.SubController;
        if (openSubtitleController != null) {
            openSubtitleController.setVideoSubtitle(list);
            this.SubController.setVisibility(0);
        }
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void setCallBack(ControllerCallback controllerCallback) {
        this.mCallBack = controllerCallback;
        if (controllerCallback != null) {
            if (controllerCallback.getBoxType() == 1) {
                this.llNextEpisode.setVisibility(8);
            } else {
                this.llNextEpisode.setVisibility(0);
            }
        }
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void setCastViewGone() {
        this.mLeCast.setVisibility(8);
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void changeSubtitleColor(int i) {
        this.subtitleSettingView.setColor(i);
        SimpleSubtitleView2 simpleSubtitleView2 = this.simpleSubtitleView;
        if (simpleSubtitleView2 != null) {
            simpleSubtitleView2.changeColor(i);
        }
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void changeSubtitleBgColor(int i) {
        this.subtitleSettingView.setBgColor(i);
        SimpleSubtitleView2 simpleSubtitleView2 = this.simpleSubtitleView;
        if (simpleSubtitleView2 != null) {
            simpleSubtitleView2.setBackgroundColor(i);
        }
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.controller.GestureVideoController
    protected void slideSeekTo() {
        AudioPlayHelper audioPlayHelper = this.audioPlayHelper;
        if (audioPlayHelper != null) {
            audioPlayHelper.seekTo((int) this.mMediaPlayer.getCurrentPosition(), this.mMediaPlayer);
        }
        SimpleSubtitleView2 simpleSubtitleView2 = this.simpleSubtitleView;
        if (simpleSubtitleView2 != null) {
            simpleSubtitleView2.refreshSubtitle();
        }
    }

    public static String toHexEncoding(int i) {
        StringBuilder sb = new StringBuilder();
        String hexString = Integer.toHexString(Color.alpha(i));
        String hexString2 = Integer.toHexString(Color.red(i));
        String hexString3 = Integer.toHexString(Color.green(i));
        String hexString4 = Integer.toHexString(Color.blue(i));
        if (hexString.length() == 1) {
            hexString = "0" + hexString;
        }
        if (hexString2.length() == 1) {
            hexString2 = "0" + hexString2;
        }
        if (hexString3.length() == 1) {
            hexString3 = "0" + hexString3;
        }
        if (hexString4.length() == 1) {
            hexString4 = "0" + hexString4;
        }
        sb.append("#");
        sb.append(hexString);
        sb.append(hexString2);
        sb.append(hexString3);
        sb.append(hexString4);
        return sb.toString().toUpperCase();
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.controller.BaseVideoController
    public void getTrackInfo(IMedia.AudioTrack[] audioTrackArr) {
        super.getTrackInfo(audioTrackArr);
        ControllerCallback controllerCallback = this.mCallBack;
        if (controllerCallback != null) {
            controllerCallback.onTrackComplete(Arrays.asList(audioTrackArr));
        }
        if (audioTrackArr == null) {
            return;
        }
        this.ListTracks.clear();
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.controller.BaseVideoController
    public void setTrackInfo(int i) {
        super.setTrackInfo(i);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private String translate(String str) {
        char c;
        switch (str.hashCode()) {
            case 100574:
                if (str.equals("eng")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 104598:
                if (str.equals("ita")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 114084:
                if (str.equals("spa")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 115947:
                if (str.equals(C.LANGUAGE_UNDETERMINED)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        return c != 0 ? c != 1 ? c != 2 ? c != 3 ? str : "unknown" : "Spain" : "English" : "Italy";
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public MediaRouteButton getMediaButton() {
        return this.mediaRouteButton;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void destroy() {
        AudioPlayHelper audioPlayHelper = this.audioPlayHelper;
        if (audioPlayHelper != null) {
            audioPlayHelper.release();
        }
        this.mSrtControllers.clearAnimation();
        this.mSrtController.clearAnimation();
        this.simpleSubtitleView.destroy();
        this.mCallBack = null;
        this.mHandler.removeCallbacksAndMessages(null);
        this.mHandler = null;
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.controller.StandardVideoController, com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerController
    public void switchScreenOrientation(boolean z) {
        super.switchScreenOrientation(z);
        LastSaidFragment lastSaidFragment = this.lastSaidFragment;
        if (lastSaidFragment != null && lastSaidFragment.isVisible()) {
            this.lastSaidFragment.dismiss();
            showLastSaidFragment();
        }
        this.transcodingSubtitleController.switchScreenLayout(z);
        this.subtitleSettingView.switchScreenLayout(z);
    }
}
