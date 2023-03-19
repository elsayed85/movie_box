package com.movieboxpro.android.view.videocontroller;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteFullException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.core.view.GestureDetectorCompat;
import com.avery.subtitle.OnMediaStatusListener;
import com.avery.subtitle.model.Subtitle;
import com.avery.subtitle.widget.SimpleSubtitleView;
import com.dueeeke.model.ResponseSubtitleRecord;
import com.google.android.exoplayer2.C;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.just.agentweb.DefaultWebClient;
import com.movieboxpro.android.BuildConfig;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.db.entity.PlayRecode;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.APIService;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.TvSeasonList;
import com.movieboxpro.android.receiver.PopPlayerOpenReceiver;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ScreenUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.Video.VideoPlayerActivity;
import com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity;
import com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayListener;
import com.movieboxpro.android.view.widget.PopupLayout;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.libvlc.interfaces.IVLCVout;
/* compiled from: PopupManager.kt */
@Metadata(d1 = {"\u0000Õ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0018*\u0001\u001f\u0018\u0000 i2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005:\u0001iB_\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f\u0012\u0006\u0010\u0011\u001a\u00020\u000b\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0013¢\u0006\u0002\u0010\u0016J\u0016\u00107\u001a\u00020\u000b2\f\u00108\u001a\b\u0012\u0004\u0012\u0002090\u000fH\u0002J\b\u0010:\u001a\u00020;H\u0002J\u0010\u0010<\u001a\u00020\u00132\u0006\u0010=\u001a\u00020\u0013H\u0002J\u0012\u0010>\u001a\u00020;2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0002J\b\u0010?\u001a\u00020;H\u0002J\u0010\u0010@\u001a\u00020;2\u0006\u0010A\u001a\u00020BH\u0016J\u0010\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020FH\u0016J\u0010\u0010G\u001a\u00020D2\u0006\u0010E\u001a\u00020FH\u0016J\u0010\u0010H\u001a\u00020D2\u0006\u0010E\u001a\u00020FH\u0016J(\u0010I\u001a\u00020D2\u0006\u0010J\u001a\u00020F2\u0006\u0010K\u001a\u00020F2\u0006\u0010L\u001a\u00020M2\u0006\u0010N\u001a\u00020MH\u0016J\u0010\u0010O\u001a\u00020;2\u0006\u0010E\u001a\u00020FH\u0016J@\u0010P\u001a\u00020;2\u0006\u0010Q\u001a\u00020R2\u0006\u0010S\u001a\u00020\u000b2\u0006\u0010T\u001a\u00020\u000b2\u0006\u0010U\u001a\u00020\u000b2\u0006\u0010V\u001a\u00020\u000b2\u0006\u0010W\u001a\u00020\u000b2\u0006\u0010X\u001a\u00020\u000bH\u0016J(\u0010Y\u001a\u00020D2\u0006\u0010J\u001a\u00020F2\u0006\u0010K\u001a\u00020F2\u0006\u0010Z\u001a\u00020M2\u0006\u0010[\u001a\u00020MH\u0016J\u0010\u0010\\\u001a\u00020;2\u0006\u0010E\u001a\u00020FH\u0016J\u0010\u0010]\u001a\u00020D2\u0006\u0010E\u001a\u00020FH\u0016J\u0010\u0010^\u001a\u00020D2\u0006\u0010E\u001a\u00020FH\u0016J\u0010\u0010_\u001a\u00020;2\u0006\u0010Q\u001a\u00020RH\u0016J\u0010\u0010`\u001a\u00020;2\u0006\u0010Q\u001a\u00020RH\u0016J\u0006\u0010a\u001a\u00020;J\b\u0010b\u001a\u00020;H\u0002J \u0010c\u001a\u00020;2\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\b\u0010d\u001a\u00020;H\u0002J\b\u0010e\u001a\u00020;H\u0002J\b\u0010f\u001a\u00020;H\u0002J\u0006\u0010g\u001a\u00020;J\b\u0010h\u001a\u00020;H\u0002R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\u001b0\u001aj\b\u0012\u0004\u0012\u00020\u001b`\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010 R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020/X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u000201X\u0082.¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u000203X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u000203X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u000206X\u0082.¢\u0006\u0002\n\u0000¨\u0006j"}, d2 = {"Lcom/movieboxpro/android/view/videocontroller/PopupManager;", "Landroid/view/GestureDetector$OnDoubleTapListener;", "Landroid/view/View$OnClickListener;", "Landroid/view/GestureDetector$OnGestureListener;", "Lorg/videolan/libvlc/interfaces/IVLCVout$OnNewVideoLayoutListener;", "Lorg/videolan/libvlc/interfaces/IVLCVout$Callback;", "context", "Landroid/content/Context;", "bundle", "Landroid/os/Bundle;", "boxType", "", "videoModel", "Lcom/movieboxpro/android/model/BaseMediaModel;", "subtitles", "", "Lcom/avery/subtitle/model/Subtitle;", "subtitleDelay", "fid", "", "audioTrackLanguage", "audioTrackUrl", "(Landroid/content/Context;Landroid/os/Bundle;ILcom/movieboxpro/android/model/BaseMediaModel;Ljava/util/List;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "closeButton", "Landroid/widget/ImageView;", "disposableList", "Ljava/util/ArrayList;", "Lio/reactivex/disposables/Disposable;", "Lkotlin/collections/ArrayList;", "expandButton", "handler", "com/movieboxpro/android/view/videocontroller/PopupManager$handler$1", "Lcom/movieboxpro/android/view/videocontroller/PopupManager$handler$1;", "hideBackwardRunnable", "Ljava/lang/Runnable;", "hideForwardRunnable", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lorg/videolan/libvlc/MediaPlayer$EventListener;", "playListener", "Lcom/movieboxpro/android/view/activity/vlcvideoplayer/listener/PlayListener;", "playPauseButton", "popupLayout", "Lcom/movieboxpro/android/view/widget/PopupLayout;", "progressBar", "Landroid/widget/ProgressBar;", "rootView", "seekBar", "Landroidx/appcompat/widget/AppCompatSeekBar;", "subtitleView", "Lcom/avery/subtitle/widget/SimpleSubtitleView;", "tvBackward", "Landroid/widget/TextView;", "tvForward", "vlcPlayer", "Lcom/movieboxpro/android/view/videocontroller/VlcPlayer;", "chooseQuality", "list", "Lcom/movieboxpro/android/model/BaseMediaModel$DownloadFile;", "expandToVideoPlayer", "", "getRealUrl", "oriUrl", "getSubtitles", "hideNotification", "onClick", "v", "Landroid/view/View;", "onDoubleTap", "", "e", "Landroid/view/MotionEvent;", "onDoubleTapEvent", "onDown", "onFling", "e1", "e2", "velocityX", "", "velocityY", "onLongPress", "onNewVideoLayout", "vlcVout", "Lorg/videolan/libvlc/interfaces/IVLCVout;", "width", "height", "visibleWidth", "visibleHeight", "sarNum", "sarDen", "onScroll", "distanceX", "distanceY", "onShowPress", "onSingleTapConfirmed", "onSingleTapUp", "onSurfacesCreated", "onSurfacesDestroyed", "removePopup", "savePlayRecord", "setNewPlay", "showBackward", "showForward", "showNotification", "showPopup", "stopPlayback", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PopupManager implements GestureDetector.OnDoubleTapListener, View.OnClickListener, GestureDetector.OnGestureListener, IVLCVout.OnNewVideoLayoutListener, IVLCVout.Callback {
    private static final int FLING_STOP_VELOCITY = 3000;
    private static final int HIDE_BUTTONS = 1;
    private static final int MSG_DELAY = 3000;
    private static final int SHOW_BUTTONS = 0;
    private final String audioTrackLanguage;
    private final String audioTrackUrl;
    private int boxType;
    private Bundle bundle;
    private ImageView closeButton;
    private final Context context;
    private final ArrayList<Disposable> disposableList;
    private ImageView expandButton;
    private String fid;
    private final PopupManager$handler$1 handler;
    private final Runnable hideBackwardRunnable;
    private final Runnable hideForwardRunnable;
    private MediaPlayer.EventListener listener;
    private PlayListener playListener;
    private ImageView playPauseButton;
    private PopupLayout popupLayout;
    private ProgressBar progressBar;
    private PopupLayout rootView;
    private AppCompatSeekBar seekBar;
    private int subtitleDelay;
    private SimpleSubtitleView subtitleView;
    private List<? extends Subtitle> subtitles;
    private TextView tvBackward;
    private TextView tvForward;
    private BaseMediaModel videoModel;
    private VlcPlayer vlcPlayer;
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "VLC/PopupManager";

    private final void hideNotification() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: savePlayRecord$lambda-3  reason: not valid java name */
    public static final String m1346savePlayRecord$lambda3(String noName_0, String noName_1) {
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        return "";
    }

    private final void showNotification() {
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTapEvent(MotionEvent e) {
        Intrinsics.checkNotNullParameter(e, "e");
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onDown(MotionEvent e) {
        Intrinsics.checkNotNullParameter(e, "e");
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onFling(MotionEvent e1, MotionEvent e2, float f, float f2) {
        Intrinsics.checkNotNullParameter(e1, "e1");
        Intrinsics.checkNotNullParameter(e2, "e2");
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent e) {
        Intrinsics.checkNotNullParameter(e, "e");
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float f, float f2) {
        Intrinsics.checkNotNullParameter(e1, "e1");
        Intrinsics.checkNotNullParameter(e2, "e2");
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onShowPress(MotionEvent e) {
        Intrinsics.checkNotNullParameter(e, "e");
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(MotionEvent e) {
        Intrinsics.checkNotNullParameter(e, "e");
        return false;
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [com.movieboxpro.android.view.videocontroller.PopupManager$handler$1] */
    public PopupManager(Context context, Bundle bundle, int i, BaseMediaModel videoModel, List<? extends Subtitle> list, int i2, String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(videoModel, "videoModel");
        this.context = context;
        this.bundle = bundle;
        this.boxType = i;
        this.videoModel = videoModel;
        this.subtitles = list;
        this.subtitleDelay = i2;
        this.fid = str;
        this.audioTrackLanguage = str2;
        this.audioTrackUrl = str3;
        this.disposableList = new ArrayList<>();
        final Looper mainLooper = Looper.getMainLooper();
        this.handler = new Handler(mainLooper) { // from class: com.movieboxpro.android.view.videocontroller.PopupManager$handler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                ImageView imageView;
                ImageView imageView2;
                ImageView imageView3;
                AppCompatSeekBar appCompatSeekBar;
                AppCompatSeekBar appCompatSeekBar2;
                Intrinsics.checkNotNullParameter(msg, "msg");
                int i3 = msg.what == 0 ? 0 : 8;
                imageView = PopupManager.this.playPauseButton;
                AppCompatSeekBar appCompatSeekBar3 = null;
                if (imageView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("playPauseButton");
                    imageView = null;
                }
                imageView.setVisibility(i3);
                imageView2 = PopupManager.this.closeButton;
                if (imageView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("closeButton");
                    imageView2 = null;
                }
                imageView2.setVisibility(i3);
                imageView3 = PopupManager.this.expandButton;
                if (imageView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("expandButton");
                    imageView3 = null;
                }
                imageView3.setVisibility(i3);
                if (i3 == 8) {
                    appCompatSeekBar2 = PopupManager.this.seekBar;
                    if (appCompatSeekBar2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("seekBar");
                    } else {
                        appCompatSeekBar3 = appCompatSeekBar2;
                    }
                    appCompatSeekBar3.setVisibility(4);
                    return;
                }
                appCompatSeekBar = PopupManager.this.seekBar;
                if (appCompatSeekBar == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("seekBar");
                } else {
                    appCompatSeekBar3 = appCompatSeekBar;
                }
                appCompatSeekBar3.setVisibility(i3);
            }
        };
        this.hideBackwardRunnable = new Runnable() { // from class: com.movieboxpro.android.view.videocontroller.-$$Lambda$PopupManager$oCEoZn5zzADRT_Z3nU9jHArCw3o
            @Override // java.lang.Runnable
            public final void run() {
                PopupManager.m1343hideBackwardRunnable$lambda4(PopupManager.this);
            }
        };
        this.hideForwardRunnable = new Runnable() { // from class: com.movieboxpro.android.view.videocontroller.-$$Lambda$PopupManager$OY5DFKM81o-fNcO5BVwHMM9-Uko
            @Override // java.lang.Runnable
            public final void run() {
                PopupManager.m1344hideForwardRunnable$lambda5(PopupManager.this);
            }
        };
    }

    public /* synthetic */ PopupManager(Context context, Bundle bundle, int i, BaseMediaModel baseMediaModel, List list, int i2, String str, String str2, String str3, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, bundle, i, baseMediaModel, (i3 & 16) != 0 ? new ArrayList() : list, i2, str, str2, str3);
    }

    public final void removePopup() {
        this.listener = null;
        VlcPlayer vlcPlayer = this.vlcPlayer;
        if (vlcPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vlcPlayer");
            vlcPlayer = null;
        }
        vlcPlayer.unRegisterListener(this.playListener);
        savePlayRecord();
        SimpleSubtitleView simpleSubtitleView = this.subtitleView;
        if (simpleSubtitleView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleView");
            simpleSubtitleView = null;
        }
        simpleSubtitleView.destroy();
        VlcPlayer vlcPlayer2 = this.vlcPlayer;
        if (vlcPlayer2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vlcPlayer");
            vlcPlayer2 = null;
        }
        vlcPlayer2.stop();
        VlcPlayer vlcPlayer3 = this.vlcPlayer;
        if (vlcPlayer3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vlcPlayer");
            vlcPlayer3 = null;
        }
        vlcPlayer3.release();
        if (this.rootView == null) {
            return;
        }
        VlcPlayer vlcPlayer4 = this.vlcPlayer;
        if (vlcPlayer4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vlcPlayer");
            vlcPlayer4 = null;
        }
        IVLCVout vLCVout = vlcPlayer4.getMediaPlayer().getVLCVout();
        Intrinsics.checkNotNullExpressionValue(vLCVout, "vlcPlayer.getMediaPlayer().vlcVout");
        vLCVout.detachViews();
        PopupLayout popupLayout = this.rootView;
        Intrinsics.checkNotNull(popupLayout);
        popupLayout.close();
        this.rootView = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void showPopup() {
        String string;
        VlcPlayerInstance vlcPlayerInstance = VlcPlayerInstance.INSTANCE;
        Context context = App.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext()");
        VlcPlayer vlcPlayerInstance2 = vlcPlayerInstance.getInstance(context);
        this.vlcPlayer = vlcPlayerInstance2;
        VlcPlayer vlcPlayer = null;
        if (vlcPlayerInstance2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vlcPlayer");
            vlcPlayerInstance2 = null;
        }
        vlcPlayerInstance2.releaseView();
        Object systemService = this.context.getApplicationContext().getSystemService("layout_inflater");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.LayoutInflater");
        }
        View inflate = ((LayoutInflater) systemService).inflate(R.layout.video_popup, (ViewGroup) null);
        if (inflate == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.movieboxpro.android.view.widget.PopupLayout");
        }
        PopupLayout popupLayout = (PopupLayout) inflate;
        this.rootView = popupLayout;
        Intrinsics.checkNotNull(popupLayout);
        View findViewById = popupLayout.findViewById(R.id.video_play_pause);
        Intrinsics.checkNotNullExpressionValue(findViewById, "rootView!!.findViewById(R.id.video_play_pause)");
        this.playPauseButton = (ImageView) findViewById;
        PopupLayout popupLayout2 = this.rootView;
        Intrinsics.checkNotNull(popupLayout2);
        View findViewById2 = popupLayout2.findViewById(R.id.popup_close);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "rootView!!.findViewById(R.id.popup_close)");
        this.closeButton = (ImageView) findViewById2;
        PopupLayout popupLayout3 = this.rootView;
        Intrinsics.checkNotNull(popupLayout3);
        View findViewById3 = popupLayout3.findViewById(R.id.popup_expand);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "rootView!!.findViewById(R.id.popup_expand)");
        this.expandButton = (ImageView) findViewById3;
        PopupLayout popupLayout4 = this.rootView;
        Intrinsics.checkNotNull(popupLayout4);
        View findViewById4 = popupLayout4.findViewById(R.id.simpleSubtitleView);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "rootView!!.findViewById(R.id.simpleSubtitleView)");
        this.subtitleView = (SimpleSubtitleView) findViewById4;
        PopupLayout popupLayout5 = this.rootView;
        Intrinsics.checkNotNull(popupLayout5);
        View findViewById5 = popupLayout5.findViewById(R.id.progressBar);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "rootView!!.findViewById(R.id.progressBar)");
        this.progressBar = (ProgressBar) findViewById5;
        PopupLayout popupLayout6 = this.rootView;
        Intrinsics.checkNotNull(popupLayout6);
        View findViewById6 = popupLayout6.findViewById(R.id.seekBar);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "rootView!!.findViewById(R.id.seekBar)");
        this.seekBar = (AppCompatSeekBar) findViewById6;
        PopupLayout popupLayout7 = this.rootView;
        Intrinsics.checkNotNull(popupLayout7);
        View findViewById7 = popupLayout7.findViewById(R.id.popupLayout);
        Intrinsics.checkNotNullExpressionValue(findViewById7, "rootView!!.findViewById(R.id.popupLayout)");
        this.popupLayout = (PopupLayout) findViewById7;
        PopupLayout popupLayout8 = this.rootView;
        Intrinsics.checkNotNull(popupLayout8);
        View findViewById8 = popupLayout8.findViewById(R.id.tvForward);
        Intrinsics.checkNotNullExpressionValue(findViewById8, "rootView!!.findViewById(R.id.tvForward)");
        this.tvForward = (TextView) findViewById8;
        PopupLayout popupLayout9 = this.rootView;
        Intrinsics.checkNotNull(popupLayout9);
        View findViewById9 = popupLayout9.findViewById(R.id.tvBackward);
        Intrinsics.checkNotNullExpressionValue(findViewById9, "rootView!!.findViewById(R.id.tvBackward)");
        this.tvBackward = (TextView) findViewById9;
        ImageView imageView = this.playPauseButton;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playPauseButton");
            imageView = null;
        }
        PopupManager popupManager = this;
        imageView.setOnClickListener(popupManager);
        ImageView imageView2 = this.closeButton;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("closeButton");
            imageView2 = null;
        }
        imageView2.setOnClickListener(popupManager);
        ImageView imageView3 = this.expandButton;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("expandButton");
            imageView3 = null;
        }
        imageView3.setOnClickListener(popupManager);
        VlcPlayer vlcPlayer2 = this.vlcPlayer;
        if (vlcPlayer2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vlcPlayer");
            vlcPlayer2 = null;
        }
        Bundle bundle = this.bundle;
        String str = "";
        if (bundle != null && (string = bundle.getString("playUrl")) != null) {
            str = string;
        }
        Bundle bundle2 = this.bundle;
        vlcPlayer2.setDataSource(str, bundle2 == null ? 0L : bundle2.getLong("playPosition"));
        AppCompatSeekBar appCompatSeekBar = this.seekBar;
        if (appCompatSeekBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("seekBar");
            appCompatSeekBar = null;
        }
        appCompatSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.movieboxpro.android.view.videocontroller.PopupManager$showPopup$1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                VlcPlayer vlcPlayerInstance3;
                if (!z || (vlcPlayerInstance3 = VlcPlayerInstance.INSTANCE.getInstance()) == null) {
                    return;
                }
                vlcPlayerInstance3.seekTo(i);
            }
        });
        GestureDetectorCompat gestureDetectorCompat = new GestureDetectorCompat(this.context, this);
        gestureDetectorCompat.setOnDoubleTapListener(this);
        PopupLayout popupLayout10 = this.rootView;
        Intrinsics.checkNotNull(popupLayout10);
        popupLayout10.setGestureDetector(gestureDetectorCompat);
        VlcPlayer vlcPlayer3 = this.vlcPlayer;
        if (vlcPlayer3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vlcPlayer");
            vlcPlayer3 = null;
        }
        IVLCVout vLCVout = vlcPlayer3.getMediaPlayer().getVLCVout();
        Intrinsics.checkNotNullExpressionValue(vLCVout, "vlcPlayer.getMediaPlayer().vlcVout");
        PopupLayout popupLayout11 = this.rootView;
        Intrinsics.checkNotNull(popupLayout11);
        vLCVout.addCallback(this);
        vLCVout.setVideoView((TextureView) popupLayout11.findViewById(R.id.player_surface));
        vLCVout.attachViews(this);
        PopupLayout popupLayout12 = this.rootView;
        Intrinsics.checkNotNull(popupLayout12);
        popupLayout12.setVLCVOut(vLCVout);
        SimpleSubtitleView simpleSubtitleView = this.subtitleView;
        if (simpleSubtitleView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleView");
            simpleSubtitleView = null;
        }
        simpleSubtitleView.bindOnMediaStatusListener(new OnMediaStatusListener() { // from class: com.movieboxpro.android.view.videocontroller.PopupManager$showPopup$2
            @Override // com.avery.subtitle.OnMediaStatusListener
            public boolean isPlaying() {
                VlcPlayer vlcPlayer4;
                vlcPlayer4 = PopupManager.this.vlcPlayer;
                if (vlcPlayer4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("vlcPlayer");
                    vlcPlayer4 = null;
                }
                return vlcPlayer4.getMediaPlayer().isPlaying();
            }

            @Override // com.avery.subtitle.OnMediaStatusListener
            public boolean isPrepared() {
                VlcPlayer vlcPlayer4;
                vlcPlayer4 = PopupManager.this.vlcPlayer;
                if (vlcPlayer4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("vlcPlayer");
                    vlcPlayer4 = null;
                }
                return vlcPlayer4.getMediaPlayer().hasMedia();
            }

            @Override // com.avery.subtitle.OnMediaStatusListener
            public long getCurrentPosition() {
                VlcPlayer vlcPlayer4;
                vlcPlayer4 = PopupManager.this.vlcPlayer;
                if (vlcPlayer4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("vlcPlayer");
                    vlcPlayer4 = null;
                }
                return vlcPlayer4.getCurrentPosition();
            }
        });
        SimpleSubtitleView simpleSubtitleView2 = this.subtitleView;
        if (simpleSubtitleView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleView");
            simpleSubtitleView2 = null;
        }
        simpleSubtitleView2.setDelay(this.subtitleDelay);
        SimpleSubtitleView simpleSubtitleView3 = this.subtitleView;
        if (simpleSubtitleView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleView");
            simpleSubtitleView3 = null;
        }
        simpleSubtitleView3.changeSize(10);
        SimpleSubtitleView simpleSubtitleView4 = this.subtitleView;
        SimpleSubtitleView simpleSubtitleView5 = simpleSubtitleView4;
        if (simpleSubtitleView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleView");
            simpleSubtitleView5 = null;
        }
        simpleSubtitleView5.setSubtitles(this.subtitles);
        SimpleSubtitleView simpleSubtitleView6 = this.subtitleView;
        if (simpleSubtitleView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleView");
            simpleSubtitleView6 = null;
        }
        simpleSubtitleView6.start();
        final Ref.IntRef intRef = new Ref.IntRef();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        booleanRef.element = true;
        this.playListener = new PlayListener() { // from class: com.movieboxpro.android.view.videocontroller.PopupManager$showPopup$3
            @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayListener
            public void onComplete() {
            }

            @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayListener
            public void onError(String msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
            }

            @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayListener
            public void onPause() {
                PopupLayout popupLayout13;
                SimpleSubtitleView simpleSubtitleView7;
                ImageView imageView4;
                popupLayout13 = PopupManager.this.rootView;
                SimpleSubtitleView simpleSubtitleView8 = null;
                if (popupLayout13 != null) {
                    imageView4 = PopupManager.this.playPauseButton;
                    if (imageView4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("playPauseButton");
                        imageView4 = null;
                    }
                    imageView4.setImageResource(R.mipmap.ic_cast_play);
                }
                simpleSubtitleView7 = PopupManager.this.subtitleView;
                if (simpleSubtitleView7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("subtitleView");
                } else {
                    simpleSubtitleView8 = simpleSubtitleView7;
                }
                simpleSubtitleView8.pause();
            }

            @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayListener
            public void onPositionChanged() {
                AppCompatSeekBar appCompatSeekBar2;
                VlcPlayer vlcPlayer4;
                AppCompatSeekBar appCompatSeekBar3;
                VlcPlayer vlcPlayer5;
                intRef.element++;
                if (intRef.element == 2) {
                    appCompatSeekBar2 = PopupManager.this.seekBar;
                    VlcPlayer vlcPlayer6 = null;
                    if (appCompatSeekBar2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("seekBar");
                        appCompatSeekBar2 = null;
                    }
                    vlcPlayer4 = PopupManager.this.vlcPlayer;
                    if (vlcPlayer4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("vlcPlayer");
                        vlcPlayer4 = null;
                    }
                    appCompatSeekBar2.setProgress((int) vlcPlayer4.getCurrentPosition());
                    appCompatSeekBar3 = PopupManager.this.seekBar;
                    if (appCompatSeekBar3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("seekBar");
                        appCompatSeekBar3 = null;
                    }
                    vlcPlayer5 = PopupManager.this.vlcPlayer;
                    if (vlcPlayer5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("vlcPlayer");
                    } else {
                        vlcPlayer6 = vlcPlayer5;
                    }
                    appCompatSeekBar3.setMax((int) vlcPlayer6.getDuration());
                    intRef.element = 0;
                }
            }

            @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayListener
            public void onBuffer(float f) {
                ProgressBar progressBar;
                ProgressBar progressBar2;
                ProgressBar progressBar3 = null;
                if (f < 100.0f) {
                    progressBar2 = PopupManager.this.progressBar;
                    if (progressBar2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("progressBar");
                    } else {
                        progressBar3 = progressBar2;
                    }
                    CommonExtKt.visible(progressBar3);
                    return;
                }
                progressBar = PopupManager.this.progressBar;
                if (progressBar == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("progressBar");
                } else {
                    progressBar3 = progressBar;
                }
                CommonExtKt.gone(progressBar3);
            }

            @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayListener
            public void onStart() {
                booleanRef.element = true;
            }

            @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayListener
            public void onPlay() {
                PopupLayout popupLayout13;
                SimpleSubtitleView simpleSubtitleView7;
                VlcPlayer vlcPlayer4;
                String str2;
                String str3;
                VlcPlayer vlcPlayer5;
                String str4;
                String str5;
                VlcPlayer vlcPlayer6;
                PopupLayout popupLayout14;
                ImageView imageView4;
                popupLayout13 = PopupManager.this.rootView;
                VlcPlayer vlcPlayer7 = null;
                if (popupLayout13 != null) {
                    imageView4 = PopupManager.this.playPauseButton;
                    if (imageView4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("playPauseButton");
                        imageView4 = null;
                    }
                    imageView4.setImageResource(R.mipmap.ic_cast_pause);
                }
                simpleSubtitleView7 = PopupManager.this.subtitleView;
                if (simpleSubtitleView7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("subtitleView");
                    simpleSubtitleView7 = null;
                }
                simpleSubtitleView7.start();
                if (booleanRef.element) {
                    int i = 0;
                    booleanRef.element = false;
                    vlcPlayer4 = PopupManager.this.vlcPlayer;
                    if (vlcPlayer4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("vlcPlayer");
                        vlcPlayer4 = null;
                    }
                    IMedia media = vlcPlayer4.getMediaPlayer().getMedia();
                    if (media == null) {
                        return;
                    }
                    PopupManager popupManager2 = PopupManager.this;
                    IMedia.Track track = media.getTrack(0);
                    if (track instanceof IMedia.VideoTrack) {
                        popupLayout14 = popupManager2.popupLayout;
                        if (popupLayout14 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("popupLayout");
                            popupLayout14 = null;
                        }
                        IMedia.VideoTrack videoTrack = (IMedia.VideoTrack) track;
                        popupLayout14.setInitViewSize(videoTrack.width, videoTrack.height);
                    }
                    str2 = popupManager2.audioTrackLanguage;
                    String str6 = str2;
                    if (str6 == null || StringsKt.isBlank(str6)) {
                        str3 = popupManager2.audioTrackUrl;
                        String str7 = str3;
                        if (((str7 == null || StringsKt.isBlank(str7)) ? 1 : 1) == 0) {
                            vlcPlayer5 = popupManager2.vlcPlayer;
                            if (vlcPlayer5 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("vlcPlayer");
                            } else {
                                vlcPlayer7 = vlcPlayer5;
                            }
                            str4 = popupManager2.audioTrackUrl;
                            vlcPlayer7.addAudioTrack(str4);
                            return;
                        }
                        return;
                    }
                    int trackCount = media.getTrackCount();
                    while (i < trackCount) {
                        int i2 = i + 1;
                        IMedia.Track track2 = media.getTrack(i);
                        if (track2 instanceof IMedia.AudioTrack) {
                            String str8 = track2.language;
                            str5 = popupManager2.audioTrackLanguage;
                            if (Intrinsics.areEqual(str8, str5)) {
                                vlcPlayer6 = popupManager2.vlcPlayer;
                                if (vlcPlayer6 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("vlcPlayer");
                                } else {
                                    vlcPlayer7 = vlcPlayer6;
                                }
                                vlcPlayer7.setTrackInfo(i);
                                return;
                            }
                        }
                        i = i2;
                    }
                }
            }
        };
        VlcPlayer vlcPlayer4 = this.vlcPlayer;
        if (vlcPlayer4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vlcPlayer");
        } else {
            vlcPlayer = vlcPlayer4;
        }
        vlcPlayer.registerListener(this.playListener);
    }

    public final void setNewPlay(Bundle bundle, int i, BaseMediaModel videoModel) {
        PlayRecode findByEpisode;
        BaseMediaModel.DownloadFile downloadFile;
        long start_time;
        Intrinsics.checkNotNullParameter(videoModel, "videoModel");
        videoModel.season = bundle == null ? 1 : bundle.getInt(VideoPlayerActivity.VIDEO_ID);
        videoModel.episode = bundle == null ? 1 : bundle.getInt("videoplayer_episode");
        SimpleSubtitleView simpleSubtitleView = this.subtitleView;
        VlcPlayer vlcPlayer = null;
        if (simpleSubtitleView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleView");
            simpleSubtitleView = null;
        }
        simpleSubtitleView.stop();
        this.bundle = bundle;
        this.boxType = i;
        this.videoModel = videoModel;
        VlcPlayer vlcPlayer2 = this.vlcPlayer;
        if (vlcPlayer2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vlcPlayer");
            vlcPlayer2 = null;
        }
        vlcPlayer2.getMediaPlayer().stop();
        VlcPlayer vlcPlayer3 = this.vlcPlayer;
        if (vlcPlayer3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vlcPlayer");
            vlcPlayer3 = null;
        }
        IMedia media = vlcPlayer3.getMediaPlayer().getMedia();
        if (media != null) {
            media.release();
        }
        List<BaseMediaModel.DownloadFile> list = videoModel.list;
        if (!(list == null || list.isEmpty())) {
            if (i == 1) {
                findByEpisode = App.getDB().playRecodeDao().findByTypeid(1, videoModel.id);
            } else {
                findByEpisode = App.getDB().playRecodeDao().findByEpisode(2, videoModel.id, videoModel.season, videoModel.episode);
            }
            if (findByEpisode == null) {
                downloadFile = null;
                start_time = 0;
            } else {
                downloadFile = CommonExtKt.checkIndexLegal(findByEpisode.getQuality(), videoModel.list) ? videoModel.list.get(findByEpisode.getQuality()) : null;
                start_time = findByEpisode.getStart_time();
            }
            if (start_time == 0) {
                start_time = videoModel.seconds;
            }
            if (downloadFile == null) {
                List<BaseMediaModel.DownloadFile> list2 = videoModel.list;
                List<BaseMediaModel.DownloadFile> list3 = videoModel.list;
                Intrinsics.checkNotNullExpressionValue(list3, "videoModel.list");
                downloadFile = list2.get(chooseQuality(list3));
            }
            Intrinsics.checkNotNull(downloadFile);
            BaseMediaModel.DownloadFile downloadFile2 = downloadFile;
            String str = downloadFile2.path;
            Intrinsics.checkNotNullExpressionValue(str, "currQuality!!.path");
            String realUrl = getRealUrl(str);
            VlcPlayer vlcPlayer4 = this.vlcPlayer;
            if (vlcPlayer4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("vlcPlayer");
            } else {
                vlcPlayer = vlcPlayer4;
            }
            vlcPlayer.setDataSource(realUrl, start_time);
            getSubtitles(String.valueOf(downloadFile2.fid));
            this.fid = i == 1 ? downloadFile2.mmfid : downloadFile2.tmfid;
            return;
        }
        ToastUtils.showShort("no resource", new Object[0]);
    }

    private final void getSubtitles(String str) {
        Observable compose;
        if (this.boxType == 1) {
            APIService service = Http.getService();
            String str2 = API.BASE_URL;
            String str3 = App.getUserData().uid_v2;
            compose = service.Movie_srt_list_v2(str2, API.Movie.MOVIE_SRTLIST_V2, str3 == null ? "" : str3, this.videoModel.id, str, App.deviceLang, BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslate2Bean(ResponseSubtitleRecord.class));
        } else {
            APIService service2 = Http.getService();
            String str4 = API.BASE_URL;
            String str5 = App.getUserData().uid_v2;
            if (str5 == null) {
                str5 = "";
            }
            compose = service2.TV_srt_list_v2(str4, API.Tv.TV_SRTLIST_V2, str5, this.videoModel.id, str, String.valueOf(this.videoModel.season), String.valueOf(this.videoModel.episode), App.deviceLang, BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslate2Bean(ResponseSubtitleRecord.class));
        }
        Observable compose2 = compose.compose(RxUtils.rxSchedulerHelper());
        Intrinsics.checkNotNullExpressionValue(compose2, "requestSubtitle.compose(…tils.rxSchedulerHelper())");
        RxSubscribersKt.subscribeTo$default(compose2, null, null, new PopupManager$getSubtitles$1(this), new PopupManager$getSubtitles$2(this), 3, null);
    }

    private final String getRealUrl(String str) {
        if (StringsKt.equals("0", PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, ""), true)) {
            int i = 2;
            if (StringsKt.startsWith$default(str, DefaultWebClient.HTTP_SCHEME, false, 2, (Object) null) || StringsKt.startsWith$default(str, DefaultWebClient.HTTPS_SCHEME, false, 2, (Object) null)) {
                String path = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_STATE, "Error");
                if (!Intrinsics.areEqual(path, "Error")) {
                    Log.d("tag", Intrinsics.stringPlus("截取链接1 : ", str));
                    Object[] array = new Regex("/").split(str, 0).toArray(new String[0]);
                    if (array != null) {
                        String[] strArr = (String[]) array;
                        StringBuilder sb = new StringBuilder();
                        if (strArr.length > 2) {
                            Intrinsics.checkNotNullExpressionValue(path, "path");
                            strArr[2] = path;
                            int length = strArr.length;
                            while (i < length) {
                                int i2 = i + 1;
                                if (i != strArr.length - 1) {
                                    sb.append(strArr[i]);
                                    sb.append("/");
                                } else {
                                    sb.append(strArr[i]);
                                }
                                i = i2;
                            }
                            String sb2 = sb.toString();
                            Intrinsics.checkNotNullExpressionValue(sb2, "path2.toString()");
                            return sb2;
                        }
                    } else {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                    }
                } else {
                    PrefsUtils.getInstance().remove(Constant.Prefs.NETWORK_STATE);
                }
            }
        }
        return str;
    }

    private final int chooseQuality(List<? extends BaseMediaModel.DownloadFile> list) {
        int i = 0;
        for (Object obj : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            BaseMediaModel.DownloadFile downloadFile = (BaseMediaModel.DownloadFile) obj;
            String str = downloadFile.path;
            if (!(str == null || StringsKt.isBlank(str)) && !Intrinsics.areEqual(downloadFile.quality, "org")) {
                return i;
            }
            i = i2;
        }
        return 0;
    }

    private final void savePlayRecord() {
        PlayRecode findByEpisode;
        VlcPlayer vlcPlayer = this.vlcPlayer;
        VlcPlayer vlcPlayer2 = null;
        if (vlcPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vlcPlayer");
            vlcPlayer = null;
        }
        int currentPosition = (int) vlcPlayer.getCurrentPosition();
        if (this.boxType == 1) {
            findByEpisode = App.getDB().playRecodeDao().findByTypeid(this.videoModel.box_type, this.videoModel.id);
        } else {
            findByEpisode = App.getDB().playRecodeDao().findByEpisode(this.videoModel.box_type, this.videoModel.id, this.videoModel.season, this.videoModel.episode);
        }
        if (findByEpisode == null) {
            PlayRecode playRecode = new PlayRecode();
            playRecode.setMovieId(this.videoModel.id);
            playRecode.setBox_type(this.videoModel.box_type);
            playRecode.setImdb_id(this.videoModel.imdb_id);
            playRecode.setTitle(this.videoModel.title);
            playRecode.setStart_time(currentPosition);
            playRecode.setSeason(1);
            playRecode.setEpisode(1);
            try {
                App.getDB().playRecodeDao().insert(playRecode);
            } catch (SQLiteFullException e) {
                e.printStackTrace();
            }
        } else {
            VlcPlayer vlcPlayer3 = this.vlcPlayer;
            if (vlcPlayer3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("vlcPlayer");
                vlcPlayer3 = null;
            }
            if (((int) vlcPlayer3.getCurrentPosition()) > 0) {
                findByEpisode.setStart_time(currentPosition);
            }
            try {
                App.getDB().playRecodeDao().update(findByEpisode);
            } catch (SQLiteFullException e2) {
                e2.printStackTrace();
            }
        }
        if (this.boxType == 1) {
            Observable compose = Http.getService().Movie_play(API.BASE_URL, API.Movie.MOVIE_PLAY, App.isLogin() ? App.getUserData().uid_v2 : "", this.videoModel.id, String.valueOf(currentPosition / 1000), this.fid, SystemUtils.getUniqueId(App.getContext())).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper());
            Intrinsics.checkNotNullExpressionValue(compose, "getService().Movie_play(…tils.rxSchedulerHelper())");
            RxSubscribersKt.subscribeTo$default(compose, null, null, null, new PopupManager$savePlayRecord$1(this), 7, null);
            return;
        }
        VlcPlayer vlcPlayer4 = this.vlcPlayer;
        if (vlcPlayer4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vlcPlayer");
        } else {
            vlcPlayer2 = vlcPlayer4;
        }
        int i = vlcPlayer2.getDuration() - ((long) currentPosition) < 180000 ? 1 : 0;
        int i2 = currentPosition / 1000;
        Observable compose2 = Observable.zip(Http.getService().TV_play_progress(API.BASE_URL, API.Tv.TV_PLAY_PROGRESS, App.isLogin() ? App.getUserData().uid_v2 : "", this.videoModel.id, this.fid, String.valueOf(this.videoModel.season), String.valueOf(this.videoModel.episode), String.valueOf(i2), i, BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslateMsg()), Http.getService().Tv_play(API.BASE_URL, API.Tv.TV_PLAY, App.isLogin() ? App.getUserData().uid_v2 : "", this.videoModel.id, String.valueOf(this.videoModel.season), String.valueOf(this.videoModel.episode), String.valueOf(i2), this.fid, BuildConfig.VERSION_NAME, SystemUtils.getUniqueId(App.getContext())).compose(RxUtils.rxTranslateMsg()), $$Lambda$PopupManager$YmaqB6clh61VPi5g2rR3__3wGLk.INSTANCE).compose(RxUtils.rxSchedulerHelper());
        Intrinsics.checkNotNullExpressionValue(compose2, "zip(observable1, observa…tils.rxSchedulerHelper())");
        RxSubscribersKt.subscribeTo$default(compose2, null, null, null, new PopupManager$savePlayRecord$3(this), 7, null);
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Intrinsics.checkNotNullParameter(e, "e");
        ImageView imageView = this.playPauseButton;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playPauseButton");
            imageView = null;
        }
        if (imageView.getVisibility() == 0) {
            removeMessages(1);
            sendEmptyMessage(1);
        } else {
            sendEmptyMessage(0);
            sendEmptyMessageDelayed(1, C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
        }
        return true;
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTap(MotionEvent e) {
        Intrinsics.checkNotNullParameter(e, "e");
        VlcPlayer vlcPlayer = this.vlcPlayer;
        VlcPlayer vlcPlayer2 = null;
        if (vlcPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vlcPlayer");
            vlcPlayer = null;
        }
        if (vlcPlayer.getMediaPlayer().isSeekable()) {
            if (e.getX() > ScreenUtils.getScreenWidth() / 2) {
                VlcPlayer vlcPlayer3 = this.vlcPlayer;
                if (vlcPlayer3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("vlcPlayer");
                    vlcPlayer3 = null;
                }
                VlcPlayer vlcPlayer4 = this.vlcPlayer;
                if (vlcPlayer4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("vlcPlayer");
                } else {
                    vlcPlayer2 = vlcPlayer4;
                }
                vlcPlayer3.seekTo(vlcPlayer2.getCurrentPosition() + 15000);
                showForward();
                return true;
            }
            VlcPlayer vlcPlayer5 = this.vlcPlayer;
            if (vlcPlayer5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("vlcPlayer");
                vlcPlayer5 = null;
            }
            VlcPlayer vlcPlayer6 = this.vlcPlayer;
            if (vlcPlayer6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("vlcPlayer");
            } else {
                vlcPlayer2 = vlcPlayer6;
            }
            vlcPlayer5.seekTo(vlcPlayer2.getCurrentPosition() - 15000);
            showBackward();
            return true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: hideBackwardRunnable$lambda-4  reason: not valid java name */
    public static final void m1343hideBackwardRunnable$lambda4(PopupManager this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TextView textView = this$0.tvBackward;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvBackward");
            textView = null;
        }
        CommonExtKt.gone(textView);
    }

    private final void showBackward() {
        TextView textView = this.tvBackward;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvBackward");
            textView = null;
        }
        CommonExtKt.visible(textView);
        TextView textView3 = this.tvBackward;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvBackward");
        } else {
            textView2 = textView3;
        }
        textView2.setText("- 15s");
        removeCallbacks(this.hideBackwardRunnable);
        postDelayed(this.hideBackwardRunnable, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: hideForwardRunnable$lambda-5  reason: not valid java name */
    public static final void m1344hideForwardRunnable$lambda5(PopupManager this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TextView textView = this$0.tvForward;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvForward");
            textView = null;
        }
        CommonExtKt.gone(textView);
    }

    private final void showForward() {
        TextView textView = this.tvForward;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvForward");
            textView = null;
        }
        CommonExtKt.visible(textView);
        TextView textView3 = this.tvForward;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvForward");
        } else {
            textView2 = textView3;
        }
        textView2.setText("+ 15s");
        removeCallbacks(this.hideForwardRunnable);
        postDelayed(this.hideForwardRunnable, 1000L);
    }

    @Override // org.videolan.libvlc.interfaces.IVLCVout.OnNewVideoLayoutListener
    public void onNewVideoLayout(IVLCVout vlcVout, int i, int i2, int i3, int i4, int i5, int i6) {
        double d;
        double d2;
        Intrinsics.checkNotNullParameter(vlcVout, "vlcVout");
        PopupLayout popupLayout = this.rootView;
        if (popupLayout == null) {
            return;
        }
        Intrinsics.checkNotNull(popupLayout);
        int width = popupLayout.getWidth();
        PopupLayout popupLayout2 = this.rootView;
        Intrinsics.checkNotNull(popupLayout2);
        int height = popupLayout2.getHeight();
        if (width * height == 0) {
            Log.e(TAG, "Invalid surface size");
        } else if (i == 0 || i2 == 0) {
            PopupLayout popupLayout3 = this.rootView;
            Intrinsics.checkNotNull(popupLayout3);
            popupLayout3.setViewSize(width, height);
        } else {
            double d3 = width;
            double d4 = height;
            if (i6 == i5) {
                d = i3;
                d2 = i4;
                Double.isNaN(d);
                Double.isNaN(d2);
            } else {
                double d5 = i3;
                double d6 = i5;
                Double.isNaN(d5);
                Double.isNaN(d6);
                double d7 = i6;
                Double.isNaN(d7);
                d = (d5 * d6) / d7;
                d2 = i4;
                Double.isNaN(d2);
            }
            double d8 = d / d2;
            Double.isNaN(d3);
            Double.isNaN(d4);
            if (d3 / d4 < d8) {
                Double.isNaN(d3);
                d4 = d3 / d8;
            } else {
                Double.isNaN(d4);
                d3 = d4 * d8;
            }
            PopupLayout popupLayout4 = this.rootView;
            Intrinsics.checkNotNull(popupLayout4);
            popupLayout4.setViewSize((int) Math.floor(d3), (int) Math.floor(d4));
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        switch (v.getId()) {
            case R.id.popup_close /* 2131297592 */:
                stopPlayback();
                return;
            case R.id.popup_expand /* 2131297593 */:
                expandToVideoPlayer();
                return;
            case R.id.video_play_pause /* 2131298390 */:
                VlcPlayer vlcPlayer = this.vlcPlayer;
                VlcPlayer vlcPlayer2 = null;
                if (vlcPlayer == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("vlcPlayer");
                    vlcPlayer = null;
                }
                if (vlcPlayer.isPlaying()) {
                    VlcPlayer vlcPlayer3 = this.vlcPlayer;
                    if (vlcPlayer3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("vlcPlayer");
                    } else {
                        vlcPlayer2 = vlcPlayer3;
                    }
                    vlcPlayer2.pause();
                    return;
                }
                VlcPlayer vlcPlayer4 = this.vlcPlayer;
                if (vlcPlayer4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("vlcPlayer");
                } else {
                    vlcPlayer2 = vlcPlayer4;
                }
                vlcPlayer2.start();
                return;
            default:
                return;
        }
    }

    private final void expandToVideoPlayer() {
        Bundle bundle = this.bundle;
        VlcPlayer vlcPlayer = null;
        ArrayList parcelableArrayList = bundle == null ? null : bundle.getParcelableArrayList(TvDetailActivity.SEASON_TV_LIST);
        Bundle bundle2 = this.bundle;
        Serializable serializable = bundle2 == null ? null : bundle2.getSerializable("videoplayer_params");
        BaseMediaModel baseMediaModel = serializable instanceof BaseMediaModel ? (BaseMediaModel) serializable : null;
        if (baseMediaModel != null) {
            VlcPlayer vlcPlayer2 = this.vlcPlayer;
            if (vlcPlayer2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("vlcPlayer");
                vlcPlayer2 = null;
            }
            baseMediaModel.seconds = (int) (vlcPlayer2.getCurrentPosition() / 1000);
        }
        if (parcelableArrayList != null && baseMediaModel != null) {
            Iterator it = CollectionsKt.withIndex(parcelableArrayList).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                IndexedValue indexedValue = (IndexedValue) it.next();
                if (((TvSeasonList) indexedValue.getValue()).getEpisode() == baseMediaModel.getEpisode()) {
                    TvSeasonList tvSeasonList = (TvSeasonList) indexedValue.getValue();
                    VlcPlayer vlcPlayer3 = this.vlcPlayer;
                    if (vlcPlayer3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("vlcPlayer");
                    } else {
                        vlcPlayer = vlcPlayer3;
                    }
                    tvSeasonList.setSeconds(vlcPlayer.getCurrentPosition() / 1000);
                }
            }
        }
        PopPlayerManager.Companion.getInstance().removePopPlayer();
        Intent intent = new Intent(App.getContext(), PopPlayerOpenReceiver.class);
        Bundle bundle3 = this.bundle;
        if (bundle3 != null) {
            bundle3.putInt("boxType", this.boxType);
            intent.putExtras(bundle3);
        }
        App.getContext().sendBroadcast(intent);
    }

    private final void stopPlayback() {
        PopPlayerManager.Companion.getInstance().removePopPlayer();
    }

    @Override // org.videolan.libvlc.interfaces.IVLCVout.Callback
    public void onSurfacesCreated(IVLCVout vlcVout) {
        Intrinsics.checkNotNullParameter(vlcVout, "vlcVout");
        CommonExtKt.logD(this, "onSurfacesCreated");
    }

    @Override // org.videolan.libvlc.interfaces.IVLCVout.Callback
    public void onSurfacesDestroyed(IVLCVout vlcVout) {
        Intrinsics.checkNotNullParameter(vlcVout, "vlcVout");
        vlcVout.removeCallback(this);
    }

    /* compiled from: PopupManager.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/view/videocontroller/PopupManager$Companion;", "", "()V", "FLING_STOP_VELOCITY", "", "HIDE_BUTTONS", "MSG_DELAY", "SHOW_BUTTONS", "TAG", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
