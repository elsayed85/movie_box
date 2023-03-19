package com.movieboxpro.android.view.activity.exoplayer.player;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.view.Surface;
import android.view.SurfaceHolder;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import com.google.android.exoplayer2.DeviceInfo;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.PlaybackException;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.TracksInfo;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.decoder.DecoderReuseEvaluation;
import com.google.android.exoplayer2.source.LoadEventInfo;
import com.google.android.exoplayer2.source.MediaLoadData;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionOverrides;
import com.google.android.exoplayer2.trackselection.TrackSelectionParameters;
import com.google.android.exoplayer2.video.VideoSize;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.collect.ImmutableList;
import com.movieboxpro.android.model.DownloadInfo;
import com.movieboxpro.android.model.ExoAudioTrackInfo;
import com.movieboxpro.android.service.FileDownloadService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.RendererItem;
import org.videolan.libvlc.util.VLCVideoLayout;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
/* compiled from: CustomExoPlayer.kt */
@Metadata(d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0018\u001a\u00020\n2\b\u0010\u0019\u001a\u0004\u0018\u00010\fH\u0016J\u001a\u0010\u001a\u001a\u00020\u001b2\b\u0010\u0019\u001a\u0004\u0018\u00010\f2\u0006\u0010\u001c\u001a\u00020\u000fH\u0016J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\u000e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 H\u0016J\b\u0010\"\u001a\u00020\nH\u0016J\b\u0010#\u001a\u00020\nH\u0016J\b\u0010$\u001a\u00020\u001eH\u0016J\b\u0010%\u001a\u00020\u001eH\u0016J\b\u0010&\u001a\u00020\u0016H\u0016J\b\u0010'\u001a\u00020\u001eH\u0016J\b\u0010(\u001a\u00020)H\u0016J\b\u0010*\u001a\u00020+H\u0016J\n\u0010,\u001a\u0004\u0018\u00010-H\u0016J\b\u0010.\u001a\u00020\nH\u0016J\b\u0010/\u001a\u00020\u001bH\u0016J\b\u00100\u001a\u00020\u000fH\u0016J\u0010\u00101\u001a\u00020\u001b2\u0006\u00101\u001a\u00020\u000fH\u0016J\b\u00102\u001a\u00020\u001bH\u0016J\b\u00103\u001a\u00020\u001bH\u0016J\u0010\u00104\u001a\u00020\u001b2\u0006\u00105\u001a\u00020\u0014H\u0016J\b\u00106\u001a\u00020\u001bH\u0016J\b\u00107\u001a\u00020\u001bH\u0016J\b\u00108\u001a\u00020\u001bH\u0016J\u0010\u00109\u001a\u00020\u001b2\u0006\u0010:\u001a\u00020\u001eH\u0016J\u0010\u0010;\u001a\u00020\u001b2\u0006\u0010<\u001a\u00020\u001eH\u0016J\u0012\u0010=\u001a\u00020\u001b2\b\u0010>\u001a\u0004\u0018\u00010-H\u0016J\u0012\u0010?\u001a\u00020\u001b2\b\u0010@\u001a\u0004\u0018\u00010AH\u0016J\u001a\u0010?\u001a\u00020\u001b2\b\u0010B\u001a\u0004\u0018\u00010\f2\u0006\u0010C\u001a\u00020\u001eH\u0016J(\u0010?\u001a\u00020\u001b2\b\u0010B\u001a\u0004\u0018\u00010\f2\u0014\u0010D\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f\u0018\u00010EH\u0016J\u0010\u0010F\u001a\u00020\u001b2\u0006\u0010G\u001a\u00020HH\u0016J\u0010\u0010I\u001a\u00020\u001b2\u0006\u0010J\u001a\u00020\u000fH\u0016J\u0010\u0010K\u001a\u00020\u001b2\u0006\u0010L\u001a\u00020\u000fH\u0016J\u0010\u0010M\u001a\u00020\u001b2\u0006\u0010N\u001a\u00020\u000fH\u0016J\b\u0010O\u001a\u00020\u001bH\u0016J\u0012\u0010P\u001a\u00020\u001b2\b\u0010Q\u001a\u0004\u0018\u00010RH\u0016J\u0010\u0010S\u001a\u00020\u001b2\u0006\u0010T\u001a\u00020UH\u0016J\u0010\u0010V\u001a\u00020\u001b2\u0006\u0010W\u001a\u00020XH\u0016J\u0010\u0010Y\u001a\u00020\u001b2\u0006\u0010Z\u001a\u00020\nH\u0016J.\u0010[\u001a\u00020\u001b2\b\u0010\\\u001a\u0004\u0018\u00010\f2\b\u0010]\u001a\u0004\u0018\u00010\f2\b\u0010^\u001a\u0004\u0018\u00010\f2\u0006\u0010_\u001a\u00020\nH\u0016J\u0010\u0010`\u001a\u00020\u001b2\u0006\u0010a\u001a\u00020\nH\u0016J\u0018\u0010b\u001a\u00020\u001b2\u0006\u0010c\u001a\u00020U2\u0006\u0010d\u001a\u00020UH\u0016J\u0010\u0010b\u001a\u00020\u001b2\u0006\u0010e\u001a\u00020\nH\u0016J\b\u0010f\u001a\u00020\u001bH\u0016J\b\u0010g\u001a\u00020\u001bH\u0016J\u0012\u0010h\u001a\u00020\u001b2\b\u00105\u001a\u0004\u0018\u00010\u0014H\u0016J\u001c\u0010i\u001a\u00020\u001b2\b\u0010j\u001a\u0004\u0018\u00010k2\b\u0010l\u001a\u0004\u0018\u00010mH\u0016R\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\u00140\u0006j\b\u0012\u0004\u0012\u00020\u0014`\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006n"}, d2 = {"Lcom/movieboxpro/android/view/activity/exoplayer/player/CustomExoPlayer;", "Lcom/movieboxpro/android/view/activity/exoplayer/player/ExoAbstractPlayer;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "audioTracks", "Ljava/util/ArrayList;", "Lcom/google/android/exoplayer2/TracksInfo$TrackGroupInfo;", "Lkotlin/collections/ArrayList;", "currSelectAudioIndex", "", "currSelectId", "", "height", "isBuffering", "", "isLastReportedPlayWhenReady", "isPreparing", "lastReportedPlaybackState", "listeners", "Lcom/movieboxpro/android/view/activity/exoplayer/player/PlayListener;", "player", "Lcom/google/android/exoplayer2/ExoPlayer;", "width", "addAudioTrack", "url", "addSubtitle", "", "select", "getAudioDelay", "", "getAudioTracks", "", "Lcom/movieboxpro/android/model/ExoAudioTrackInfo;", "getBufferedPercentage", "getCurrAudioIndex", "getCurrentPosition", "getDuration", "getMediaPlayer", "getTcpSpeed", "getTrackInfo", "Lcom/google/android/exoplayer2/TracksInfo;", "getVideoSize", "", "getVideoTrack", "Lcom/google/android/exoplayer2/source/TrackGroup;", "getVolume", "initPlayer", "isPlaying", "mute", DownloadInfo.DOWNLOAD_PAUSE, "prepareAsync", "registerListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "release", "releaseView", "reset", "seekTo", "time", "setAudioDelay", "audioDelay", "setAudioTrack", "trackGroup", "setDataSource", IjkMediaPlayer.OnNativeInvokeListener.ARG_FD, "Landroid/content/res/AssetFileDescriptor;", FileDownloadService.PARAMS_KEY_PATH, "startTime", "headers", "", "setDisplay", "holder", "Landroid/view/SurfaceHolder;", "setEnableHardCodec", "enableHardCodec", "setEnableMediaCodec", "isEnable", "setLooping", "isLooping", "setOptions", "setRenderer", "renderer", "Lorg/videolan/libvlc/RendererItem;", "setSpeed", "speed", "", "setSurface", "surface", "Landroid/view/Surface;", "setTrackInfo", "SelecttrackInfo", "setVideoInfo", "title", "poster", "fid", "boxType", "setVideoScale", "scale", "setVolume", "v1", "v2", MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, TtmlNode.START, "stop", "unRegisterListener", "updateVideoView", "vlcVideoLayout", "Lorg/videolan/libvlc/util/VLCVideoLayout;", "activity", "Landroid/app/Activity;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CustomExoPlayer extends ExoAbstractPlayer {
    private final ArrayList<TracksInfo.TrackGroupInfo> audioTracks;
    private int currSelectAudioIndex;
    private String currSelectId;
    private int height;
    private boolean isBuffering;
    private boolean isLastReportedPlayWhenReady;
    private boolean isPreparing;
    private int lastReportedPlaybackState;
    private final ArrayList<PlayListener> listeners;
    private final ExoPlayer player;
    private int width;

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public int addAudioTrack(String str) {
        return -1;
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public void addSubtitle(String str, boolean z) {
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public long getAudioDelay() {
        return 0L;
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public long getTcpSpeed() {
        return 0L;
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public TrackGroup getVideoTrack() {
        return null;
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public void prepareAsync() {
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public void releaseView() {
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public void setAudioDelay(long j) {
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public void setDataSource(AssetFileDescriptor assetFileDescriptor) {
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public void setDataSource(String str, Map<String, String> map) {
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public void setEnableHardCodec(boolean z) {
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public void setEnableMediaCodec(boolean z) {
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public void setLooping(boolean z) {
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public void setOptions() {
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public void setRenderer(RendererItem rendererItem) {
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public void setSurface(Surface surface) {
        Intrinsics.checkNotNullParameter(surface, "surface");
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public void setTrackInfo(int i) {
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public void setVideoInfo(String str, String str2, String str3, int i) {
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public void setVideoScale(int i) {
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public void updateVideoView(VLCVideoLayout vLCVideoLayout, Activity activity) {
    }

    public CustomExoPlayer(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ExoPlayer build = new ExoPlayer.Builder(context).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder(context).build()");
        this.player = build;
        this.listeners = new ArrayList<>();
        this.isPreparing = true;
        this.currSelectId = "0";
        this.audioTracks = new ArrayList<>();
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public void initPlayer() {
        this.player.addAnalyticsListener(new AnalyticsListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer$initPlayer$1
            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onAudioAttributesChanged(AnalyticsListener.EventTime eventTime, AudioAttributes audioAttributes) {
                AnalyticsListener.CC.$default$onAudioAttributesChanged(this, eventTime, audioAttributes);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onAudioCodecError(AnalyticsListener.EventTime eventTime, Exception exc) {
                AnalyticsListener.CC.$default$onAudioCodecError(this, eventTime, exc);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            @Deprecated
            public /* synthetic */ void onAudioDecoderInitialized(AnalyticsListener.EventTime eventTime, String str, long j) {
                AnalyticsListener.CC.$default$onAudioDecoderInitialized(this, eventTime, str, j);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onAudioDecoderInitialized(AnalyticsListener.EventTime eventTime, String str, long j, long j2) {
                AnalyticsListener.CC.$default$onAudioDecoderInitialized(this, eventTime, str, j, j2);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onAudioDecoderReleased(AnalyticsListener.EventTime eventTime, String str) {
                AnalyticsListener.CC.$default$onAudioDecoderReleased(this, eventTime, str);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onAudioDisabled(AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters) {
                AnalyticsListener.CC.$default$onAudioDisabled(this, eventTime, decoderCounters);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onAudioEnabled(AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters) {
                AnalyticsListener.CC.$default$onAudioEnabled(this, eventTime, decoderCounters);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            @Deprecated
            public /* synthetic */ void onAudioInputFormatChanged(AnalyticsListener.EventTime eventTime, Format format) {
                AnalyticsListener.CC.$default$onAudioInputFormatChanged(this, eventTime, format);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onAudioInputFormatChanged(AnalyticsListener.EventTime eventTime, Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
                AnalyticsListener.CC.$default$onAudioInputFormatChanged(this, eventTime, format, decoderReuseEvaluation);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onAudioPositionAdvancing(AnalyticsListener.EventTime eventTime, long j) {
                AnalyticsListener.CC.$default$onAudioPositionAdvancing(this, eventTime, j);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onAudioSessionIdChanged(AnalyticsListener.EventTime eventTime, int i) {
                AnalyticsListener.CC.$default$onAudioSessionIdChanged(this, eventTime, i);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onAudioSinkError(AnalyticsListener.EventTime eventTime, Exception exc) {
                AnalyticsListener.CC.$default$onAudioSinkError(this, eventTime, exc);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onAudioUnderrun(AnalyticsListener.EventTime eventTime, int i, long j, long j2) {
                AnalyticsListener.CC.$default$onAudioUnderrun(this, eventTime, i, j, j2);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onAvailableCommandsChanged(AnalyticsListener.EventTime eventTime, Player.Commands commands) {
                AnalyticsListener.CC.$default$onAvailableCommandsChanged(this, eventTime, commands);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onBandwidthEstimate(AnalyticsListener.EventTime eventTime, int i, long j, long j2) {
                AnalyticsListener.CC.$default$onBandwidthEstimate(this, eventTime, i, j, j2);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onCues(AnalyticsListener.EventTime eventTime, List<Cue> list) {
                AnalyticsListener.CC.$default$onCues(this, eventTime, list);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            @Deprecated
            public /* synthetic */ void onDecoderDisabled(AnalyticsListener.EventTime eventTime, int i, DecoderCounters decoderCounters) {
                AnalyticsListener.CC.$default$onDecoderDisabled(this, eventTime, i, decoderCounters);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            @Deprecated
            public /* synthetic */ void onDecoderEnabled(AnalyticsListener.EventTime eventTime, int i, DecoderCounters decoderCounters) {
                AnalyticsListener.CC.$default$onDecoderEnabled(this, eventTime, i, decoderCounters);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            @Deprecated
            public /* synthetic */ void onDecoderInitialized(AnalyticsListener.EventTime eventTime, int i, String str, long j) {
                AnalyticsListener.CC.$default$onDecoderInitialized(this, eventTime, i, str, j);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            @Deprecated
            public /* synthetic */ void onDecoderInputFormatChanged(AnalyticsListener.EventTime eventTime, int i, Format format) {
                AnalyticsListener.CC.$default$onDecoderInputFormatChanged(this, eventTime, i, format);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onDeviceInfoChanged(AnalyticsListener.EventTime eventTime, DeviceInfo deviceInfo) {
                AnalyticsListener.CC.$default$onDeviceInfoChanged(this, eventTime, deviceInfo);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onDeviceVolumeChanged(AnalyticsListener.EventTime eventTime, int i, boolean z) {
                AnalyticsListener.CC.$default$onDeviceVolumeChanged(this, eventTime, i, z);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onDownstreamFormatChanged(AnalyticsListener.EventTime eventTime, MediaLoadData mediaLoadData) {
                AnalyticsListener.CC.$default$onDownstreamFormatChanged(this, eventTime, mediaLoadData);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onDrmKeysLoaded(AnalyticsListener.EventTime eventTime) {
                AnalyticsListener.CC.$default$onDrmKeysLoaded(this, eventTime);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onDrmKeysRemoved(AnalyticsListener.EventTime eventTime) {
                AnalyticsListener.CC.$default$onDrmKeysRemoved(this, eventTime);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onDrmKeysRestored(AnalyticsListener.EventTime eventTime) {
                AnalyticsListener.CC.$default$onDrmKeysRestored(this, eventTime);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            @Deprecated
            public /* synthetic */ void onDrmSessionAcquired(AnalyticsListener.EventTime eventTime) {
                AnalyticsListener.CC.$default$onDrmSessionAcquired(this, eventTime);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onDrmSessionAcquired(AnalyticsListener.EventTime eventTime, int i) {
                AnalyticsListener.CC.$default$onDrmSessionAcquired(this, eventTime, i);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onDrmSessionManagerError(AnalyticsListener.EventTime eventTime, Exception exc) {
                AnalyticsListener.CC.$default$onDrmSessionManagerError(this, eventTime, exc);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onDrmSessionReleased(AnalyticsListener.EventTime eventTime) {
                AnalyticsListener.CC.$default$onDrmSessionReleased(this, eventTime);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onDroppedVideoFrames(AnalyticsListener.EventTime eventTime, int i, long j) {
                AnalyticsListener.CC.$default$onDroppedVideoFrames(this, eventTime, i, j);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onEvents(Player player, AnalyticsListener.Events events) {
                AnalyticsListener.CC.$default$onEvents(this, player, events);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onIsLoadingChanged(AnalyticsListener.EventTime eventTime, boolean z) {
                AnalyticsListener.CC.$default$onIsLoadingChanged(this, eventTime, z);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onIsPlayingChanged(AnalyticsListener.EventTime eventTime, boolean z) {
                AnalyticsListener.CC.$default$onIsPlayingChanged(this, eventTime, z);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onLoadCanceled(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
                AnalyticsListener.CC.$default$onLoadCanceled(this, eventTime, loadEventInfo, mediaLoadData);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onLoadCompleted(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
                AnalyticsListener.CC.$default$onLoadCompleted(this, eventTime, loadEventInfo, mediaLoadData);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onLoadError(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
                AnalyticsListener.CC.$default$onLoadError(this, eventTime, loadEventInfo, mediaLoadData, iOException, z);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onLoadStarted(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
                AnalyticsListener.CC.$default$onLoadStarted(this, eventTime, loadEventInfo, mediaLoadData);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            @Deprecated
            public /* synthetic */ void onLoadingChanged(AnalyticsListener.EventTime eventTime, boolean z) {
                AnalyticsListener.CC.$default$onLoadingChanged(this, eventTime, z);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onMaxSeekToPreviousPositionChanged(AnalyticsListener.EventTime eventTime, long j) {
                AnalyticsListener.CC.$default$onMaxSeekToPreviousPositionChanged(this, eventTime, j);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onMediaItemTransition(AnalyticsListener.EventTime eventTime, MediaItem mediaItem, int i) {
                AnalyticsListener.CC.$default$onMediaItemTransition(this, eventTime, mediaItem, i);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onMediaMetadataChanged(AnalyticsListener.EventTime eventTime, MediaMetadata mediaMetadata) {
                AnalyticsListener.CC.$default$onMediaMetadataChanged(this, eventTime, mediaMetadata);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onMetadata(AnalyticsListener.EventTime eventTime, com.google.android.exoplayer2.metadata.Metadata metadata) {
                AnalyticsListener.CC.$default$onMetadata(this, eventTime, metadata);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onPlayWhenReadyChanged(AnalyticsListener.EventTime eventTime, boolean z, int i) {
                AnalyticsListener.CC.$default$onPlayWhenReadyChanged(this, eventTime, z, i);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onPlaybackParametersChanged(AnalyticsListener.EventTime eventTime, PlaybackParameters playbackParameters) {
                AnalyticsListener.CC.$default$onPlaybackParametersChanged(this, eventTime, playbackParameters);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onPlaybackStateChanged(AnalyticsListener.EventTime eventTime, int i) {
                AnalyticsListener.CC.$default$onPlaybackStateChanged(this, eventTime, i);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onPlaybackSuppressionReasonChanged(AnalyticsListener.EventTime eventTime, int i) {
                AnalyticsListener.CC.$default$onPlaybackSuppressionReasonChanged(this, eventTime, i);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onPlayerError(AnalyticsListener.EventTime eventTime, PlaybackException playbackException) {
                AnalyticsListener.CC.$default$onPlayerError(this, eventTime, playbackException);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onPlayerErrorChanged(AnalyticsListener.EventTime eventTime, PlaybackException playbackException) {
                AnalyticsListener.CC.$default$onPlayerErrorChanged(this, eventTime, playbackException);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onPlayerReleased(AnalyticsListener.EventTime eventTime) {
                AnalyticsListener.CC.$default$onPlayerReleased(this, eventTime);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onPlaylistMetadataChanged(AnalyticsListener.EventTime eventTime, MediaMetadata mediaMetadata) {
                AnalyticsListener.CC.$default$onPlaylistMetadataChanged(this, eventTime, mediaMetadata);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            @Deprecated
            public /* synthetic */ void onPositionDiscontinuity(AnalyticsListener.EventTime eventTime, int i) {
                AnalyticsListener.CC.$default$onPositionDiscontinuity(this, eventTime, i);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onPositionDiscontinuity(AnalyticsListener.EventTime eventTime, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i) {
                AnalyticsListener.CC.$default$onPositionDiscontinuity(this, eventTime, positionInfo, positionInfo2, i);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onRenderedFirstFrame(AnalyticsListener.EventTime eventTime, Object obj, long j) {
                AnalyticsListener.CC.$default$onRenderedFirstFrame(this, eventTime, obj, j);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onRepeatModeChanged(AnalyticsListener.EventTime eventTime, int i) {
                AnalyticsListener.CC.$default$onRepeatModeChanged(this, eventTime, i);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onSeekBackIncrementChanged(AnalyticsListener.EventTime eventTime, long j) {
                AnalyticsListener.CC.$default$onSeekBackIncrementChanged(this, eventTime, j);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onSeekForwardIncrementChanged(AnalyticsListener.EventTime eventTime, long j) {
                AnalyticsListener.CC.$default$onSeekForwardIncrementChanged(this, eventTime, j);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            @Deprecated
            public /* synthetic */ void onSeekProcessed(AnalyticsListener.EventTime eventTime) {
                AnalyticsListener.CC.$default$onSeekProcessed(this, eventTime);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            @Deprecated
            public /* synthetic */ void onSeekStarted(AnalyticsListener.EventTime eventTime) {
                AnalyticsListener.CC.$default$onSeekStarted(this, eventTime);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onShuffleModeChanged(AnalyticsListener.EventTime eventTime, boolean z) {
                AnalyticsListener.CC.$default$onShuffleModeChanged(this, eventTime, z);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onSkipSilenceEnabledChanged(AnalyticsListener.EventTime eventTime, boolean z) {
                AnalyticsListener.CC.$default$onSkipSilenceEnabledChanged(this, eventTime, z);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onSurfaceSizeChanged(AnalyticsListener.EventTime eventTime, int i, int i2) {
                AnalyticsListener.CC.$default$onSurfaceSizeChanged(this, eventTime, i, i2);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onTrackSelectionParametersChanged(AnalyticsListener.EventTime eventTime, TrackSelectionParameters trackSelectionParameters) {
                AnalyticsListener.CC.$default$onTrackSelectionParametersChanged(this, eventTime, trackSelectionParameters);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            @Deprecated
            public /* synthetic */ void onTracksChanged(AnalyticsListener.EventTime eventTime, TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
                AnalyticsListener.CC.$default$onTracksChanged(this, eventTime, trackGroupArray, trackSelectionArray);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onUpstreamDiscarded(AnalyticsListener.EventTime eventTime, MediaLoadData mediaLoadData) {
                AnalyticsListener.CC.$default$onUpstreamDiscarded(this, eventTime, mediaLoadData);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onVideoCodecError(AnalyticsListener.EventTime eventTime, Exception exc) {
                AnalyticsListener.CC.$default$onVideoCodecError(this, eventTime, exc);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            @Deprecated
            public /* synthetic */ void onVideoDecoderInitialized(AnalyticsListener.EventTime eventTime, String str, long j) {
                AnalyticsListener.CC.$default$onVideoDecoderInitialized(this, eventTime, str, j);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onVideoDecoderInitialized(AnalyticsListener.EventTime eventTime, String str, long j, long j2) {
                AnalyticsListener.CC.$default$onVideoDecoderInitialized(this, eventTime, str, j, j2);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onVideoDecoderReleased(AnalyticsListener.EventTime eventTime, String str) {
                AnalyticsListener.CC.$default$onVideoDecoderReleased(this, eventTime, str);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onVideoDisabled(AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters) {
                AnalyticsListener.CC.$default$onVideoDisabled(this, eventTime, decoderCounters);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onVideoEnabled(AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters) {
                AnalyticsListener.CC.$default$onVideoEnabled(this, eventTime, decoderCounters);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onVideoFrameProcessingOffset(AnalyticsListener.EventTime eventTime, long j, int i) {
                AnalyticsListener.CC.$default$onVideoFrameProcessingOffset(this, eventTime, j, i);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            @Deprecated
            public /* synthetic */ void onVideoInputFormatChanged(AnalyticsListener.EventTime eventTime, Format format) {
                AnalyticsListener.CC.$default$onVideoInputFormatChanged(this, eventTime, format);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onVideoInputFormatChanged(AnalyticsListener.EventTime eventTime, Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
                AnalyticsListener.CC.$default$onVideoInputFormatChanged(this, eventTime, format, decoderReuseEvaluation);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            @Deprecated
            public /* synthetic */ void onVideoSizeChanged(AnalyticsListener.EventTime eventTime, int i, int i2, int i3, float f) {
                AnalyticsListener.CC.$default$onVideoSizeChanged(this, eventTime, i, i2, i3, f);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public /* synthetic */ void onVolumeChanged(AnalyticsListener.EventTime eventTime, float f) {
                AnalyticsListener.CC.$default$onVolumeChanged(this, eventTime, f);
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public void onTracksInfoChanged(AnalyticsListener.EventTime eventTime, TracksInfo tracksInfo) {
                ArrayList arrayList;
                ArrayList arrayList2;
                ArrayList arrayList3;
                Intrinsics.checkNotNullParameter(eventTime, "eventTime");
                Intrinsics.checkNotNullParameter(tracksInfo, "tracksInfo");
                arrayList = CustomExoPlayer.this.audioTracks;
                arrayList.clear();
                ImmutableList<TracksInfo.TrackGroupInfo> trackGroupInfos = tracksInfo.getTrackGroupInfos();
                Intrinsics.checkNotNullExpressionValue(trackGroupInfos, "tracksInfo.trackGroupInfos");
                CustomExoPlayer customExoPlayer = CustomExoPlayer.this;
                for (TracksInfo.TrackGroupInfo trackGroupInfo : trackGroupInfos) {
                    if (trackGroupInfo.getTrackType() == 1) {
                        arrayList3 = customExoPlayer.audioTracks;
                        arrayList3.add(trackGroupInfo);
                    }
                }
                arrayList2 = CustomExoPlayer.this.audioTracks;
                CustomExoPlayer customExoPlayer2 = CustomExoPlayer.this;
                int i = 0;
                for (Object obj : arrayList2) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    if (((TracksInfo.TrackGroupInfo) obj).isSelected()) {
                        customExoPlayer2.currSelectAudioIndex = i;
                    }
                    i = i2;
                }
            }

            /* JADX WARN: Code restructure failed: missing block: B:5:0x0024, code lost:
                if (r6 != r8) goto L8;
             */
            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onPlayerStateChanged(com.google.android.exoplayer2.analytics.AnalyticsListener.EventTime r6, boolean r7, int r8) {
                /*
                    r5 = this;
                    java.lang.String r0 = "eventTime"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
                    com.movieboxpro.android.utils.LogUtils r6 = com.movieboxpro.android.utils.LogUtils.INSTANCE
                    java.lang.Integer r0 = java.lang.Integer.valueOf(r8)
                    java.lang.String r1 = "state:"
                    java.lang.String r0 = kotlin.jvm.internal.Intrinsics.stringPlus(r1, r0)
                    java.lang.String r1 = "CustomExoPlayer"
                    r6.logD(r1, r0)
                    com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer r6 = com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer.this
                    boolean r6 = com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer.access$isLastReportedPlayWhenReady$p(r6)
                    if (r6 != r7) goto L26
                    com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer r6 = com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer.this
                    int r6 = com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer.access$getLastReportedPlaybackState$p(r6)
                    if (r6 == r8) goto Ld8
                L26:
                    com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer r6 = com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer.this
                    com.google.android.exoplayer2.ExoPlayer r6 = com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer.access$getPlayer$p(r6)
                    r6.getBufferedPercentage()
                    com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer r6 = com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer.this
                    boolean r6 = com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer.access$isBuffering$p(r6)
                    r0 = 0
                    r1 = 4
                    r2 = 3
                    if (r6 == 0) goto L62
                    if (r8 == r2) goto L3f
                    if (r8 == r1) goto L3f
                    goto L62
                L3f:
                    com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer r6 = com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer.this
                    java.util.ArrayList r6 = com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer.access$getListeners$p(r6)
                    java.lang.Iterable r6 = (java.lang.Iterable) r6
                    java.util.Iterator r6 = r6.iterator()
                L4b:
                    boolean r3 = r6.hasNext()
                    if (r3 == 0) goto L5d
                    java.lang.Object r3 = r6.next()
                    com.movieboxpro.android.view.activity.exoplayer.player.PlayListener r3 = (com.movieboxpro.android.view.activity.exoplayer.player.PlayListener) r3
                    r4 = 1120403456(0x42c80000, float:100.0)
                    r3.onBuffer(r4)
                    goto L4b
                L5d:
                    com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer r6 = com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer.this
                    com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer.access$setBuffering$p(r6, r0)
                L62:
                    r6 = 1
                    if (r8 != r6) goto L6a
                    com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer r3 = com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer.this
                    com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer.access$setPreparing$p(r3, r6)
                L6a:
                    com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer r3 = com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer.this
                    boolean r3 = com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer.access$isPreparing$p(r3)
                    if (r3 == 0) goto L95
                    if (r8 != r2) goto L95
                    com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer r2 = com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer.this
                    java.util.ArrayList r2 = com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer.access$getListeners$p(r2)
                    java.lang.Iterable r2 = (java.lang.Iterable) r2
                    java.util.Iterator r2 = r2.iterator()
                L80:
                    boolean r3 = r2.hasNext()
                    if (r3 == 0) goto L90
                    java.lang.Object r3 = r2.next()
                    com.movieboxpro.android.view.activity.exoplayer.player.PlayListener r3 = (com.movieboxpro.android.view.activity.exoplayer.player.PlayListener) r3
                    r3.onPlay()
                    goto L80
                L90:
                    com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer r2 = com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer.this
                    com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer.access$setPreparing$p(r2, r0)
                L95:
                    r0 = 2
                    if (r8 == r0) goto Lb7
                    if (r8 == r1) goto L9b
                    goto Ld8
                L9b:
                    com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer r6 = com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer.this
                    java.util.ArrayList r6 = com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer.access$getListeners$p(r6)
                    java.lang.Iterable r6 = (java.lang.Iterable) r6
                    java.util.Iterator r6 = r6.iterator()
                La7:
                    boolean r0 = r6.hasNext()
                    if (r0 == 0) goto Ld8
                    java.lang.Object r0 = r6.next()
                    com.movieboxpro.android.view.activity.exoplayer.player.PlayListener r0 = (com.movieboxpro.android.view.activity.exoplayer.player.PlayListener) r0
                    r0.onComplete()
                    goto La7
                Lb7:
                    com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer r0 = com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer.this
                    java.util.ArrayList r0 = com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer.access$getListeners$p(r0)
                    java.lang.Iterable r0 = (java.lang.Iterable) r0
                    java.util.Iterator r0 = r0.iterator()
                Lc3:
                    boolean r1 = r0.hasNext()
                    if (r1 == 0) goto Ld3
                    java.lang.Object r1 = r0.next()
                    com.movieboxpro.android.view.activity.exoplayer.player.PlayListener r1 = (com.movieboxpro.android.view.activity.exoplayer.player.PlayListener) r1
                    r1.onStart()
                    goto Lc3
                Ld3:
                    com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer r0 = com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer.this
                    com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer.access$setBuffering$p(r0, r6)
                Ld8:
                    com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer r6 = com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer.this
                    com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer.access$setLastReportedPlayWhenReady$p(r6, r7)
                    com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer r6 = com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer.this
                    com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer.access$setLastReportedPlaybackState$p(r6, r8)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.view.activity.exoplayer.player.CustomExoPlayer$initPlayer$1.onPlayerStateChanged(com.google.android.exoplayer2.analytics.AnalyticsListener$EventTime, boolean, int):void");
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public void onVideoSizeChanged(AnalyticsListener.EventTime eventTime, VideoSize videoSize) {
                ArrayList<PlayListener> arrayList;
                int i;
                int i2;
                Intrinsics.checkNotNullParameter(eventTime, "eventTime");
                Intrinsics.checkNotNullParameter(videoSize, "videoSize");
                CustomExoPlayer.this.height = videoSize.height;
                CustomExoPlayer.this.width = videoSize.width;
                arrayList = CustomExoPlayer.this.listeners;
                CustomExoPlayer customExoPlayer = CustomExoPlayer.this;
                for (PlayListener playListener : arrayList) {
                    i = customExoPlayer.width;
                    i2 = customExoPlayer.height;
                    playListener.onVideoSizeChanged(i, i2);
                }
            }

            @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
            public void onTimelineChanged(AnalyticsListener.EventTime eventTime, int i) {
                Intrinsics.checkNotNullParameter(eventTime, "eventTime");
                AnalyticsListener.CC.$default$onTimelineChanged(this, eventTime, i);
            }
        });
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public void setDataSource(String str, long j) {
        if (str == null) {
            str = "";
        }
        MediaItem fromUri = MediaItem.fromUri(str);
        Intrinsics.checkNotNullExpressionValue(fromUri, "fromUri(path?:\"\")");
        this.player.setMediaItem(fromUri);
        this.player.setPlayWhenReady(true);
        this.player.prepare();
        this.player.play();
        this.player.seekTo(j);
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public void start() {
        this.player.play();
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public void pause() {
        this.player.pause();
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public void stop() {
        this.player.stop();
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public void reset() {
        this.player.stop();
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public void mute(boolean z) {
        this.player.setDeviceMuted(z);
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public boolean isPlaying() {
        return this.player.isPlaying();
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public void seekTo(long j) {
        this.player.seekTo(j);
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public void release() {
        this.player.release();
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public long getCurrentPosition() {
        return this.player.getCurrentPosition();
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public long getDuration() {
        return this.player.getDuration();
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public int getBufferedPercentage() {
        return this.player.getBufferedPercentage();
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public void setDisplay(SurfaceHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        this.player.setVideoSurfaceHolder(holder);
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public void setVolume(float f, float f2) {
        this.player.setVolume((f + f2) / 2);
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public void setVolume(int i) {
        this.player.setVolume(i);
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public void setSpeed(float f) {
        this.player.setPlaybackSpeed(f);
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public TracksInfo getTrackInfo() {
        TracksInfo currentTracksInfo = this.player.getCurrentTracksInfo();
        Intrinsics.checkNotNullExpressionValue(currentTracksInfo, "player.currentTracksInfo");
        return currentTracksInfo;
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public int[] getVideoSize() {
        return new int[]{this.width, this.height};
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public int getCurrAudioIndex() {
        return this.currSelectAudioIndex;
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public void setAudioTrack(TrackGroup trackGroup) {
        if (trackGroup != null) {
            TrackSelectionOverrides build = new TrackSelectionOverrides.Builder().setOverrideForType(new TrackSelectionOverrides.TrackSelectionOverride(trackGroup)).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder()\n              …\n                .build()");
            ExoPlayer exoPlayer = this.player;
            exoPlayer.setTrackSelectionParameters(exoPlayer.getTrackSelectionParameters().buildUpon().setTrackSelectionOverrides(build).build());
        }
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public int getVolume() {
        return (int) this.player.getVolume();
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public List<ExoAudioTrackInfo> getAudioTracks() {
        ArrayList arrayList = new ArrayList();
        for (TracksInfo.TrackGroupInfo trackGroupInfo : this.audioTracks) {
            if (trackGroupInfo.getTrackGroup().length > 0) {
                arrayList.add(new ExoAudioTrackInfo(1, trackGroupInfo.getTrackGroup().toBundle(), 1, 1, trackGroupInfo.getTrackGroup().getFormat(0).toBundle()));
            }
        }
        return arrayList;
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public void registerListener(PlayListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listeners.add(listener);
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public void unRegisterListener(PlayListener playListener) {
        this.listeners.remove(playListener);
    }

    @Override // com.movieboxpro.android.view.activity.exoplayer.player.ExoAbstractPlayer
    public ExoPlayer getMediaPlayer() {
        return this.player;
    }
}
