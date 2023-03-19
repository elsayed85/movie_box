package com.movieboxpro.android.view.videocontroller;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.TextureView;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.movieboxpro.android.model.DownloadInfo;
import com.movieboxpro.android.service.FileDownloadService;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.VLCInstance;
import com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayListener;
import com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.RendererItem;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.libvlc.util.DisplayManager;
import org.videolan.libvlc.util.VLCVideoLayout;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
/* compiled from: VlcPlayer.kt */
@Metadata(d1 = {"\u0000¾\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0019\u001a\u00020\n2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0006H\u0016J\u001a\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001a\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0018\u0010\u001f\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010!\u001a\u00020\u001eH\u0002J\b\u0010\"\u001a\u00020#H\u0016J\u000e\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%H\u0016J\u0006\u0010'\u001a\u00020\nJ\b\u0010(\u001a\u00020\nH\u0016J\b\u0010)\u001a\u00020\nH\u0016J\b\u0010*\u001a\u00020#H\u0016J\b\u0010+\u001a\u00020#H\u0016J\b\u0010,\u001a\u0004\u0018\u00010\u0006J\b\u0010-\u001a\u00020\u0016H\u0016J\b\u0010.\u001a\u00020#H\u0016J\u0013\u0010/\u001a\b\u0012\u0004\u0012\u00020100H\u0016¢\u0006\u0002\u00102J\b\u00103\u001a\u0004\u0018\u00010\u0006J\b\u00104\u001a\u0004\u0018\u00010\u0006J\b\u00105\u001a\u000206H\u0016J\b\u00107\u001a\u00020\nH\u0016J\b\u00108\u001a\u00020\u001cH\u0016J\b\u00109\u001a\u00020\u001eH\u0016J\b\u0010:\u001a\u00020\u001cH\u0016J\b\u0010;\u001a\u00020\u001cH\u0016J\u0012\u0010<\u001a\u00020\u001c2\b\u0010=\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010>\u001a\u00020\u001cH\u0016J\b\u0010?\u001a\u00020\u001cH\u0016J\b\u0010@\u001a\u00020\u001cH\u0016J\u0010\u0010A\u001a\u00020\u001c2\u0006\u0010B\u001a\u00020#H\u0016J\u0010\u0010C\u001a\u00020\u001c2\u0006\u0010D\u001a\u00020#H\u0016J\u0012\u0010E\u001a\u00020\u001c2\b\u0010F\u001a\u0004\u0018\u00010GH\u0016J\u001a\u0010E\u001a\u00020\u001c2\b\u0010H\u001a\u0004\u0018\u00010\u00062\u0006\u0010I\u001a\u00020#H\u0016J(\u0010E\u001a\u00020\u001c2\b\u0010H\u001a\u0004\u0018\u00010\u00062\u0014\u0010J\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010KH\u0016J\u0010\u0010L\u001a\u00020\u001c2\u0006\u0010M\u001a\u00020NH\u0016J\u0010\u0010O\u001a\u00020\u001c2\u0006\u0010P\u001a\u00020\u001eH\u0016J\u0010\u0010Q\u001a\u00020\u001c2\u0006\u0010R\u001a\u00020\u001eH\u0016J\u0010\u0010S\u001a\u00020\u001c2\u0006\u0010T\u001a\u00020\u001eH\u0016J\b\u0010U\u001a\u00020\u001cH\u0016J\u0012\u0010V\u001a\u00020\u001c2\b\u0010W\u001a\u0004\u0018\u00010XH\u0016J\u0012\u0010Y\u001a\u00020\u001c2\b\u0010Z\u001a\u0004\u0018\u00010[H\u0016J\u0010\u0010\\\u001a\u00020\u001c2\u0006\u0010]\u001a\u00020^H\u0016J\u0010\u0010_\u001a\u00020\u001c2\u0006\u0010`\u001a\u00020aH\u0016J\u0010\u0010b\u001a\u00020\u001c2\u0006\u0010c\u001a\u00020dH\u0016J\u0010\u0010e\u001a\u00020\u001c2\u0006\u0010f\u001a\u00020\nH\u0016J.\u0010g\u001a\u00020\u001c2\b\u0010h\u001a\u0004\u0018\u00010\u00062\b\u0010i\u001a\u0004\u0018\u00010\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010j\u001a\u00020\u001c2\u0006\u0010k\u001a\u00020\nH\u0016J\u0018\u0010l\u001a\u00020\u001c2\u0006\u0010m\u001a\u00020^2\u0006\u0010n\u001a\u00020^H\u0016J\u0010\u0010l\u001a\u00020\u001c2\u0006\u0010o\u001a\u00020\nH\u0016J\b\u0010p\u001a\u00020\u001cH\u0016J\b\u0010q\u001a\u00020\u001cH\u0016J\u0012\u0010r\u001a\u00020\u001c2\b\u0010=\u001a\u0004\u0018\u00010\u0013H\u0016J\u0018\u0010s\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0011\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0012j\n\u0012\u0006\u0012\u0004\u0018\u00010\u0013`\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006t"}, d2 = {"Lcom/movieboxpro/android/view/videocontroller/VlcPlayer;", "Lcom/movieboxpro/android/view/activity/vlcvideoplayer/player/AbstractPlayer;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "activity", "Landroid/app/Activity;", "boxType", "", "currPath", "currVLCVideoLayout", "Lorg/videolan/libvlc/util/VLCVideoLayout;", "displayManager", "Lorg/videolan/libvlc/util/DisplayManager;", "fid", "listeners", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/view/activity/vlcvideoplayer/listener/PlayListener;", "Lkotlin/collections/ArrayList;", "mediaPlayer", "Lorg/videolan/libvlc/MediaPlayer;", "videoPoster", "videoTitle", "addAudioTrack", "url", "addSubtitle", "", "select", "", "attachView", "vlcVideoLayout", "checkAvailable", "getAudioDelay", "", "getAudioTracks", "", "Lorg/videolan/libvlc/MediaPlayer$TrackDescription;", "getBoxType", "getBufferedPercentage", "getCurrAudioIndex", "getCurrentPosition", "getDuration", "getFid", "getMediaPlayer", "getTcpSpeed", "getTrackInfo", "", "Lorg/videolan/libvlc/interfaces/IMedia$AudioTrack;", "()[Lorg/videolan/libvlc/interfaces/IMedia$AudioTrack;", "getVideoPoster", "getVideoTitle", "getVideoTrack", "Lorg/videolan/libvlc/interfaces/IMedia$VideoTrack;", "getVolume", "initPlayer", "isPlaying", DownloadInfo.DOWNLOAD_PAUSE, "prepareAsync", "registerListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "release", "releaseView", "reset", "seekTo", "time", "setAudioDelay", "audioDelay", "setDataSource", IjkMediaPlayer.OnNativeInvokeListener.ARG_FD, "Landroid/content/res/AssetFileDescriptor;", FileDownloadService.PARAMS_KEY_PATH, "startTime", "headers", "", "setDisplay", "holder", "Landroid/view/SurfaceHolder;", "setEnableHardCodec", "enableHardCodec", "setEnableMediaCodec", "isEnable", "setLooping", "isLooping", "setOptions", "setPlayListener", "eventListener", "Lorg/videolan/libvlc/MediaPlayer$EventListener;", "setRenderer", "renderer", "Lorg/videolan/libvlc/RendererItem;", "setSpeed", "speed", "", "setSurface", "surface", "Landroid/view/Surface;", "setTextureView", "textureView", "Landroid/view/TextureView;", "setTrackInfo", FirebaseAnalytics.Param.INDEX, "setVideoInfo", "title", "poster", "setVideoScale", "scale", "setVolume", "v1", "v2", MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, TtmlNode.START, "stop", "unRegisterListener", "updateVideoView", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public class VlcPlayer extends AbstractPlayer {
    private final String TAG;
    private Activity activity;
    private int boxType;
    private String currPath;
    private VLCVideoLayout currVLCVideoLayout;
    private DisplayManager displayManager;
    private String fid;
    private final ArrayList<PlayListener> listeners;
    private final MediaPlayer mediaPlayer;
    private String videoPoster;
    private String videoTitle;

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public void addSubtitle(String str, boolean z) {
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public int getBufferedPercentage() {
        return 0;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public long getTcpSpeed() {
        return 0L;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public IMedia.AudioTrack[] getTrackInfo() {
        return new IMedia.AudioTrack[0];
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public void initPlayer() {
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public void prepareAsync() {
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public void setDataSource(AssetFileDescriptor assetFileDescriptor) {
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public void setDataSource(String str, Map<String, String> map) {
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public void setDisplay(SurfaceHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public void setEnableMediaCodec(boolean z) {
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public void setLooping(boolean z) {
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public void setOptions() {
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public void setSurface(Surface surface) {
        Intrinsics.checkNotNullParameter(surface, "surface");
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public void setVolume(float f, float f2) {
    }

    public VlcPlayer(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.mediaPlayer = new MediaPlayer(VLCInstance.INSTANCE.getInstance(context));
        this.videoTitle = "";
        this.videoPoster = "";
        this.fid = "";
        this.boxType = 1;
        this.listeners = new ArrayList<>();
        this.TAG = "VlcPlayer";
        this.currPath = "";
        this.mediaPlayer.setEventListener(new MediaPlayer.EventListener() { // from class: com.movieboxpro.android.view.videocontroller.-$$Lambda$VlcPlayer$MHAMg3w5u63mrSjVn57mAAonGCY
            @Override // org.videolan.libvlc.interfaces.AbstractVLCEvent.Listener
            public final void onEvent(MediaPlayer.Event event) {
                VlcPlayer.m1348_init_$lambda7(VlcPlayer.this, event);
            }
        });
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public MediaPlayer getMediaPlayer() {
        return this.mediaPlayer;
    }

    public final String getVideoTitle() {
        return this.videoTitle;
    }

    public final String getVideoPoster() {
        return this.videoPoster;
    }

    public final String getFid() {
        return this.fid;
    }

    public final int getBoxType() {
        return this.boxType;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-7  reason: not valid java name */
    public static final void m1348_init_$lambda7(final VlcPlayer this$0, MediaPlayer.Event event) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = event.type;
        if (i == 265) {
            for (PlayListener playListener : this$0.listeners) {
                if (playListener != null) {
                    playListener.onComplete();
                }
            }
        } else if (i == 266) {
            Log.i(this$0.TAG, "MediaPlayer.Event.EncounteredError");
            Observable compose = Observable.just(this$0.currPath).map(new Function() { // from class: com.movieboxpro.android.view.videocontroller.-$$Lambda$VlcPlayer$_o58HJeE7aBI4NY3pUVes5ybFzI
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    Integer m1349lambda7$lambda0;
                    m1349lambda7$lambda0 = VlcPlayer.m1349lambda7$lambda0(VlcPlayer.this, (String) obj);
                    return m1349lambda7$lambda0;
                }
            }).compose(RxUtils.rxSchedulerHelper());
            Intrinsics.checkNotNullExpressionValue(compose, "just(currPath)\n         …tils.rxSchedulerHelper())");
            RxSubscribersKt.subscribeTo$default(compose, new VlcPlayer$1$2(this$0), null, null, new VlcPlayer$1$3(this$0), 6, null);
        } else if (i != 268) {
            switch (i) {
                case 258:
                    for (PlayListener playListener2 : this$0.listeners) {
                        if (playListener2 != null) {
                            playListener2.onStart();
                        }
                    }
                    Log.i(this$0.TAG, "MediaPlayer.Event.Opening");
                    return;
                case 259:
                    for (PlayListener playListener3 : this$0.listeners) {
                        if (!(event.getBuffering() == 16.666668f) && playListener3 != null) {
                            playListener3.onBuffer(event.getBuffering());
                        }
                    }
                    return;
                case 260:
                    for (PlayListener playListener4 : this$0.listeners) {
                        if (playListener4 != null) {
                            playListener4.onPlay();
                        }
                    }
                    return;
                case 261:
                    for (PlayListener playListener5 : this$0.listeners) {
                        if (playListener5 != null) {
                            playListener5.onPause();
                        }
                    }
                    return;
                default:
                    return;
            }
        } else {
            for (PlayListener playListener6 : this$0.listeners) {
                if (playListener6 != null) {
                    playListener6.onPositionChanged();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-7$lambda-0  reason: not valid java name */
    public static final Integer m1349lambda7$lambda0(VlcPlayer this$0, String it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        URLConnection openConnection = new URL(this$0.currPath).openConnection();
        if (openConnection == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.net.HttpURLConnection");
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setConnectTimeout(10000);
        httpURLConnection.connect();
        return Integer.valueOf(httpURLConnection.getResponseCode());
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public void setPlayListener(MediaPlayer.EventListener eventListener) {
        this.mediaPlayer.setEventListener(eventListener);
    }

    private final boolean checkAvailable() {
        return !this.mediaPlayer.isReleased() && this.mediaPlayer.hasMedia();
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public long getDuration() {
        if (this.mediaPlayer.hasMedia()) {
            return this.mediaPlayer.getLength();
        }
        return 0L;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public void setTrackInfo(int i) {
        if (checkAvailable()) {
            this.mediaPlayer.setAudioTrack(i);
        }
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public void updateVideoView(VLCVideoLayout vlcVideoLayout, Activity activity) {
        Intrinsics.checkNotNullParameter(vlcVideoLayout, "vlcVideoLayout");
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.mediaPlayer.updateVideoSurfaces();
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public void seekTo(long j) {
        if (checkAvailable()) {
            this.mediaPlayer.setTime(j);
        }
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public void unRegisterListener(PlayListener playListener) {
        this.listeners.remove(playListener);
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public long getCurrentPosition() {
        if (checkAvailable()) {
            return this.mediaPlayer.getTime();
        }
        return 0L;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public void setTextureView(TextureView textureView) {
        Intrinsics.checkNotNullParameter(textureView, "textureView");
        this.mediaPlayer.getVLCVout().detachViews();
        this.mediaPlayer.getVLCVout().setVideoView(textureView);
        this.mediaPlayer.getVLCVout().attachViews();
        this.mediaPlayer.setVideoScale(MediaPlayer.ScaleType.values()[MediaPlayer.ScaleType.SURFACE_BEST_FIT.ordinal()]);
        if (checkAvailable()) {
            this.mediaPlayer.play();
        }
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public void attachView(VLCVideoLayout vlcVideoLayout, Activity activity) {
        Intrinsics.checkNotNullParameter(vlcVideoLayout, "vlcVideoLayout");
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.currVLCVideoLayout = vlcVideoLayout;
        this.activity = activity;
        if (this.mediaPlayer.getVLCVout().areViewsAttached()) {
            this.mediaPlayer.detachViews();
        }
        if (this.displayManager == null) {
            this.displayManager = new DisplayManager(activity, null, false, false, false);
        }
        this.mediaPlayer.attachViews(vlcVideoLayout, this.displayManager, true, false);
        this.mediaPlayer.setVideoScale(MediaPlayer.ScaleType.values()[MediaPlayer.ScaleType.SURFACE_BEST_FIT.ordinal()]);
        if (checkAvailable()) {
            this.mediaPlayer.play();
        }
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public List<MediaPlayer.TrackDescription> getAudioTracks() {
        if (checkAvailable()) {
            MediaPlayer.TrackDescription[] audioTracks = this.mediaPlayer.getAudioTracks();
            List<MediaPlayer.TrackDescription> list = audioTracks == null ? null : ArraysKt.toList(audioTracks);
            return list == null ? new ArrayList() : list;
        }
        return new ArrayList();
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public void start() {
        Activity activity;
        if (checkAvailable()) {
            boolean z = true;
            if (this.mediaPlayer.getVLCVout().areViewsAttached() || (activity = this.activity) == null || this.currVLCVideoLayout == null) {
                z = false;
            } else {
                if (this.displayManager == null) {
                    Intrinsics.checkNotNull(activity);
                    this.displayManager = new DisplayManager(activity, null, false, false, false);
                }
                MediaPlayer mediaPlayer = this.mediaPlayer;
                VLCVideoLayout vLCVideoLayout = this.currVLCVideoLayout;
                Intrinsics.checkNotNull(vLCVideoLayout);
                mediaPlayer.attachViews(vLCVideoLayout, this.displayManager, true, false);
                this.mediaPlayer.setVideoScale(MediaPlayer.ScaleType.values()[MediaPlayer.ScaleType.SURFACE_BEST_FIT.ordinal()]);
            }
            this.mediaPlayer.play();
            if (z) {
                this.mediaPlayer.setTime(getCurrentPosition());
            }
        }
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public void setVolume(int i) {
        this.mediaPlayer.setVolume(i);
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public void setDataSource(String str, long j) {
        Media media;
        Log.i(this.TAG, Intrinsics.stringPlus("path:", str));
        this.currPath = str;
        Uri parse = Uri.parse(str);
        if (str != null && StringsKt.startsWith$default(str, "http", false, 2, (Object) null)) {
            media = new Media(this.mediaPlayer.getLibVLC(), parse);
        } else {
            media = new Media(this.mediaPlayer.getLibVLC(), str);
        }
        media.addOption(":input-fast-seek");
        media.addOption(Intrinsics.stringPlus(":start-time=", Long.valueOf(j / 1000)));
        IMedia media2 = this.mediaPlayer.getMedia();
        if (media2 != null) {
            media2.release();
        }
        this.mediaPlayer.setMedia(media);
        if (checkAvailable()) {
            this.mediaPlayer.play();
        }
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public long getAudioDelay() {
        return this.mediaPlayer.getAudioDelay() / 1000;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public void reset() {
        if (checkAvailable()) {
            this.mediaPlayer.stop();
            IMedia media = this.mediaPlayer.getMedia();
            if (media == null) {
                return;
            }
            media.release();
        }
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public int addAudioTrack(String str) {
        this.mediaPlayer.addSlave(1, Uri.parse(new URL(str).toURI().toString()), true);
        return 1;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public boolean isPlaying() {
        if (checkAvailable()) {
            return this.mediaPlayer.isPlaying();
        }
        return false;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public void setVideoScale(int i) {
        if (i == 0) {
            this.mediaPlayer.setVideoScale(MediaPlayer.ScaleType.SURFACE_BEST_FIT);
        } else if (i == 1) {
            this.mediaPlayer.setVideoScale(MediaPlayer.ScaleType.SURFACE_16_9);
        } else if (i == 2) {
            this.mediaPlayer.setVideoScale(MediaPlayer.ScaleType.SURFACE_4_3);
        } else if (i == 3) {
            this.mediaPlayer.setVideoScale(MediaPlayer.ScaleType.SURFACE_FILL);
        } else if (i == 5) {
            this.mediaPlayer.setVideoScale(MediaPlayer.ScaleType.SURFACE_ORIGINAL);
        } else if (i != 6) {
        } else {
            this.mediaPlayer.setVideoScale(MediaPlayer.ScaleType.SURFACE_FIT_SCREEN);
        }
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public void registerListener(PlayListener playListener) {
        this.listeners.add(playListener);
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public void pause() {
        if (checkAvailable()) {
            this.mediaPlayer.pause();
        }
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public void setEnableHardCodec(boolean z) {
        if (this.mediaPlayer.hasMedia()) {
            if (z) {
                IMedia media = this.mediaPlayer.getMedia();
                if (media == null) {
                    return;
                }
                media.setDefaultMediaPlayerOptions();
                return;
            }
            IMedia media2 = this.mediaPlayer.getMedia();
            if (media2 == null) {
                return;
            }
            media2.setHWDecoderEnabled(false, false);
        }
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public void setRenderer(RendererItem rendererItem) {
        this.mediaPlayer.setRenderer(rendererItem);
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public int getVolume() {
        return this.mediaPlayer.getVolume();
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public void stop() {
        if (checkAvailable()) {
            this.mediaPlayer.stop();
        }
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public int getCurrAudioIndex() {
        return this.mediaPlayer.getAudioTrack();
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public void setSpeed(float f) {
        this.mediaPlayer.setRate(f);
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public void releaseView() {
        if (this.mediaPlayer.isReleased() || !this.mediaPlayer.getVLCVout().areViewsAttached()) {
            return;
        }
        this.mediaPlayer.getVLCVout().detachViews();
        this.mediaPlayer.detachViews();
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public IMedia.VideoTrack getVideoTrack() {
        IMedia.VideoTrack currentVideoTrack = this.mediaPlayer.getCurrentVideoTrack();
        Intrinsics.checkNotNullExpressionValue(currentVideoTrack, "mediaPlayer.currentVideoTrack");
        return currentVideoTrack;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public void setVideoInfo(String str, String str2, String str3, int i) {
        this.videoTitle = str;
        this.videoPoster = str2;
        this.fid = str3;
        this.boxType = i;
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public void release() {
        if (!this.mediaPlayer.isReleased() && this.mediaPlayer.getVLCVout().areViewsAttached()) {
            this.mediaPlayer.getVLCVout().detachViews();
            this.mediaPlayer.detachViews();
        }
        IMedia media = this.mediaPlayer.getMedia();
        if (media == null) {
            return;
        }
        media.release();
    }

    @Override // com.movieboxpro.android.view.activity.vlcvideoplayer.player.AbstractPlayer
    public void setAudioDelay(long j) {
        long audioDelay = this.mediaPlayer.getAudioDelay() + (1000 * j);
        if (this.mediaPlayer.hasMedia()) {
            this.mediaPlayer.setAudioDelay(audioDelay);
            CommonExtKt.logD(this, String.valueOf(j));
        }
    }
}
