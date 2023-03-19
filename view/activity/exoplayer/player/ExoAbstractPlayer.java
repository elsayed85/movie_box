package com.movieboxpro.android.view.activity.exoplayer.player;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.TextureView;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.TracksInfo;
import com.google.android.exoplayer2.source.TrackGroup;
import com.movieboxpro.android.model.ExoAudioTrackInfo;
import com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayerEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.RendererItem;
import org.videolan.libvlc.util.VLCVideoLayout;
/* loaded from: classes3.dex */
public abstract class ExoAbstractPlayer {
    protected PlayerEventListener mPlayerEventListener;

    public abstract int addAudioTrack(String str);

    public abstract void addSubtitle(String str, boolean z);

    public void attachView(VLCVideoLayout vLCVideoLayout, Activity activity) {
    }

    public void bindVideoView(PlayerEventListener playerEventListener) {
    }

    public abstract long getAudioDelay();

    public abstract int getBufferedPercentage();

    public abstract int getCurrAudioIndex();

    public abstract long getCurrentPosition();

    public abstract long getDuration();

    public abstract ExoPlayer getMediaPlayer();

    public abstract long getTcpSpeed();

    public abstract TracksInfo getTrackInfo();

    public abstract int[] getVideoSize();

    public abstract TrackGroup getVideoTrack();

    public abstract int getVolume();

    public abstract void initPlayer();

    public abstract boolean isPlaying();

    public abstract void mute(boolean z);

    public abstract void pause();

    public abstract void prepareAsync();

    public abstract void registerListener(PlayListener playListener);

    public abstract void release();

    public abstract void releaseView();

    public abstract void reset();

    public abstract void seekTo(long j);

    public abstract void setAudioDelay(long j);

    public abstract void setAudioTrack(TrackGroup trackGroup);

    public abstract void setDataSource(AssetFileDescriptor assetFileDescriptor);

    public abstract void setDataSource(String str, long j);

    public abstract void setDataSource(String str, Map<String, String> map);

    public abstract void setDisplay(SurfaceHolder surfaceHolder);

    public abstract void setEnableHardCodec(boolean z);

    public abstract void setEnableMediaCodec(boolean z);

    public abstract void setLooping(boolean z);

    public abstract void setOptions();

    public void setPlayListener(MediaPlayer.EventListener eventListener) {
    }

    public abstract void setRenderer(RendererItem rendererItem);

    public abstract void setSpeed(float f);

    public abstract void setSurface(Surface surface);

    public void setTextureView(TextureView textureView) {
    }

    public abstract void setTrackInfo(int i);

    public abstract void setVideoInfo(String str, String str2, String str3, int i);

    public abstract void setVideoScale(int i);

    public abstract void setVolume(float f, float f2);

    public abstract void setVolume(int i);

    public abstract void start();

    public abstract void stop();

    public abstract void unRegisterListener(PlayListener playListener);

    public abstract void updateVideoView(VLCVideoLayout vLCVideoLayout, Activity activity);

    public List<ExoAudioTrackInfo> getAudioTracks() {
        return new ArrayList();
    }
}
