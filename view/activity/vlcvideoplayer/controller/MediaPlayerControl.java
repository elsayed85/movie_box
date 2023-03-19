package com.movieboxpro.android.view.activity.vlcvideoplayer.controller;

import android.graphics.Bitmap;
import com.dueeeke.videoplayer.player.PlayerConfig;
import com.google.android.exoplayer2.source.TrackGroup;
import com.movieboxpro.android.model.ExoAudioTrackInfo;
import java.util.List;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.RendererItem;
import org.videolan.libvlc.interfaces.IMedia;
import tv.danmaku.ijk.media.player.misc.IjkTrackInfo;
/* loaded from: classes3.dex */
public interface MediaPlayerControl {
    int addAudioTrack(String str);

    void addSubtitleTrack(String str, boolean z);

    Bitmap doScreenShot();

    long getAudioDelay();

    List<MediaPlayer.TrackDescription> getAudioTracks();

    int getBufferedPercentage();

    int getCurrAudioIndex();

    int getCurrTrack();

    long getCurrentPosition();

    long getDuration();

    List<ExoAudioTrackInfo> getExoAudioTracks();

    IjkTrackInfo[] getIjkTrackInfo();

    long getTcpSpeed();

    String getTitle();

    IMedia.AudioTrack[] getTrackInfo();

    int[] getVideoSize();

    int getVolume();

    boolean isFullScreen();

    boolean isMute();

    boolean isPause();

    boolean isPlaying();

    void pause();

    void refresh();

    void release();

    void releaseView();

    void retry();

    void retryPlay();

    void seekTo(long j);

    void setAudioDelay(long j);

    void setEnableHardCodec(boolean z);

    void setExoAudioTrack(TrackGroup trackGroup);

    void setExoVideoController(com.movieboxpro.android.view.activity.exoplayer.controller.BaseVideoController baseVideoController);

    void setLock(boolean z);

    void setMirrorRotation(boolean z);

    void setMute(boolean z);

    void setPlayerConfig(PlayerConfig playerConfig);

    void setRenderer(RendererItem rendererItem);

    void setRotation(float f);

    void setScreenScale(int i);

    void setSpeed(float f);

    void setTitle(String str);

    void setTrackInfo(int i);

    void setVideoController(com.movieboxpro.android.view.activity.videoplayer.controller.BaseVideoController baseVideoController);

    void setVlcVideoController(BaseVideoController baseVideoController);

    void setVolume(int i);

    void start();

    void startFullScreen();

    void stopFullScreen();

    void updateVideoView();
}
