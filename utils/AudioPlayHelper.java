package com.movieboxpro.android.utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import com.dl7.player.media.MediaPlayerCompat;
import com.google.android.exoplayer2.ExoPlayer;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.model.DownloadInfo;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.interfaces.IMedia;
import tv.danmaku.ijk.media.player.IMediaPlayer;
/* compiled from: AudioPlayHelper.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0016\u001a\u00020\u00172\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019J\u0006\u0010\u001a\u001a\u00020\u0004J\u0010\u0010\u001b\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019J\u0010\u0010\u001c\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019J\u001a\u0010\u001d\u001a\u00020\u00172\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019J\u0010\u0010\u001d\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019J\u0006\u0010\u001e\u001a\u00020\u0017J\u0006\u0010\u001f\u001a\u00020\u0017J\u0018\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u00042\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019J\u0018\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u00042\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019J\u000e\u0010$\u001a\u00020\u00172\u0006\u0010%\u001a\u00020\u0004J\b\u0010&\u001a\u00020\u0017H\u0002J\u001a\u0010'\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u000f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0002J\b\u0010(\u001a\u00020\u0017H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/movieboxpro/android/utils/AudioPlayHelper;", "", "()V", "adjustDelayCount", "", "anotherDelayDisposable", "Lio/reactivex/disposables/Disposable;", "customDelay", "delayDisposable", "isNeedAddTime", "", "over400Count", "startDelay", "timeList", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "timerDisposable", "url", "", "vlcPlayer", "Lorg/videolan/libvlc/MediaPlayer;", "changeUrl", "", "player", "Ltv/danmaku/ijk/media/player/IMediaPlayer;", "getAudioDelay", DownloadInfo.DOWNLOAD_PAUSE, "pauseOrPlay", "play", "release", "resume", "seekTo", "position", "setAudioDelay", "delay", "setVolume", MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, "startAnotherDelay", "startTimer", "startTimerDelay", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AudioPlayHelper {
    private int adjustDelayCount;
    private Disposable anotherDelayDisposable;
    private int customDelay;
    private Disposable delayDisposable;
    private boolean isNeedAddTime;
    private int over400Count;
    private boolean startDelay;
    private ArrayList<Long> timeList;
    private Disposable timerDisposable;
    private String url = "";
    private final MediaPlayer vlcPlayer;

    public AudioPlayHelper() {
        VLCInstance vLCInstance = VLCInstance.INSTANCE;
        Context context = App.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext()");
        this.vlcPlayer = new MediaPlayer(vLCInstance.getInstance(context));
        this.timeList = new ArrayList<>();
        this.isNeedAddTime = true;
    }

    public final void play(final String str, final IMediaPlayer iMediaPlayer) {
        this.customDelay = 0;
        Disposable disposable = this.timerDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        Disposable disposable2 = this.delayDisposable;
        if (disposable2 != null) {
            disposable2.dispose();
        }
        this.url = str;
        Observable compose = Observable.just(str).map(new Function() { // from class: com.movieboxpro.android.utils.-$$Lambda$AudioPlayHelper$Ohn-SHb1f5gx3b8HzLLEeaCb1Qk
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                Unit m93play$lambda1;
                m93play$lambda1 = AudioPlayHelper.m93play$lambda1(IMediaPlayer.this, str, this, (String) obj);
                return m93play$lambda1;
            }
        }).compose(RxUtils.rxSchedulerHelper());
        Intrinsics.checkNotNullExpressionValue(compose, "just(url)\n              …tils.rxSchedulerHelper())");
        RxSubscribersKt.subscribeTo$default(compose, null, null, null, AudioPlayHelper$play$2.INSTANCE, 7, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: play$lambda-1  reason: not valid java name */
    public static final Unit m93play$lambda1(final IMediaPlayer iMediaPlayer, String str, final AudioPlayHelper this$0, String it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        MediaPlayerCompat.selectTrack(iMediaPlayer, -1);
        if (iMediaPlayer != null) {
            iMediaPlayer.pause();
        }
        Media media = new Media(this$0.vlcPlayer.getLibVLC(), Uri.parse(str));
        media.addOption(Intrinsics.stringPlus(":start-time=", Long.valueOf((iMediaPlayer == null ? 0L : iMediaPlayer.getCurrentPosition()) / 1000)));
        IMedia media2 = this$0.vlcPlayer.getMedia();
        if (media2 != null) {
            media2.release();
        }
        media.addOption(":no-video");
        this$0.vlcPlayer.setMedia(media);
        this$0.vlcPlayer.play();
        this$0.vlcPlayer.setEventListener(new MediaPlayer.EventListener() { // from class: com.movieboxpro.android.utils.-$$Lambda$AudioPlayHelper$F7ECr2D_ujeSWLFLubI7s-6QsK4
            @Override // org.videolan.libvlc.interfaces.AbstractVLCEvent.Listener
            public final void onEvent(MediaPlayer.Event event) {
                AudioPlayHelper.m94play$lambda1$lambda0(IMediaPlayer.this, this$0, event);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: play$lambda-1$lambda-0  reason: not valid java name */
    public static final void m94play$lambda1$lambda0(IMediaPlayer iMediaPlayer, AudioPlayHelper this$0, MediaPlayer.Event event) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (event.type != 259 || event.getBuffering() < 100.0f) {
            return;
        }
        if ((iMediaPlayer == null || iMediaPlayer.isPlaying()) ? false : true) {
            iMediaPlayer.start();
        }
        Disposable disposable = this$0.timerDisposable;
        if (disposable != null) {
            if (!(disposable != null && disposable.isDisposed())) {
                return;
            }
        }
        this$0.startTimer(1000L, iMediaPlayer);
    }

    public final void setAudioDelay(int i, IMediaPlayer iMediaPlayer) {
        this.customDelay += i;
        this.vlcPlayer.setTime((iMediaPlayer == null ? 0L : iMediaPlayer.getCurrentPosition()) - this.customDelay);
    }

    public final int getAudioDelay() {
        return this.customDelay;
    }

    private final void startTimer(long j, final IMediaPlayer iMediaPlayer) {
        Log.d("AudioPlayHelper", Intrinsics.stringPlus("startTimer:delay-", Long.valueOf(j)));
        this.timeList.clear();
        Observable<R> map = Observable.interval(j, 600L, TimeUnit.MILLISECONDS).map(new Function() { // from class: com.movieboxpro.android.utils.-$$Lambda$AudioPlayHelper$AtGGVACMSvECfG3LDcO391InSLk
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                String m95startTimer$lambda2;
                m95startTimer$lambda2 = AudioPlayHelper.m95startTimer$lambda2(AudioPlayHelper.this, iMediaPlayer, (Long) obj);
                return m95startTimer$lambda2;
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "interval(delay, 600, Tim…     \"\"\n                }");
        RxSubscribersKt.subscribeTo$default(map, null, null, new AudioPlayHelper$startTimer$2(this), null, 11, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startTimer$lambda-2  reason: not valid java name */
    public static final String m95startTimer$lambda2(AudioPlayHelper this$0, IMediaPlayer iMediaPlayer, Long it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        long time = ((this$0.vlcPlayer.getTime() - this$0.vlcPlayer.getAudioDelay()) + this$0.customDelay) - ((int) (iMediaPlayer == null ? 0L : iMediaPlayer.getCurrentPosition()));
        Log.d("AudioPlayHelper", time + "delay:" + this$0.vlcPlayer.getAudioDelay());
        if (this$0.adjustDelayCount < 5) {
            if (!(Math.abs(time) <= PrefsUtils.getInstance().getLong(Constant.Prefs.AUDIO_SYN_TIME, 500L) + ((long) Math.abs(this$0.customDelay)))) {
                this$0.vlcPlayer.setAudioDelay(0L);
                this$0.vlcPlayer.setTime((iMediaPlayer != null ? iMediaPlayer.getCurrentPosition() : 0L) - this$0.customDelay);
                this$0.adjustDelayCount++;
                this$0.timeList.clear();
                Disposable disposable = this$0.delayDisposable;
                if (disposable != null) {
                    disposable.dispose();
                }
                this$0.isNeedAddTime = true;
                this$0.startDelay = false;
                return "";
            }
            if (this$0.isNeedAddTime) {
                this$0.timeList.add(Long.valueOf(time));
            }
            if (!this$0.startDelay) {
                this$0.startDelay = true;
                this$0.startTimerDelay();
                return "";
            }
            Disposable disposable2 = this$0.delayDisposable;
            if (!(disposable2 != null && disposable2.isDisposed()) || Math.abs(time) <= 400) {
                return "";
            }
            int i = this$0.over400Count + 1;
            this$0.over400Count = i;
            if (i > 5) {
                this$0.over400Count = 0;
                this$0.startDelay = false;
                return "";
            }
            return "";
        }
        Disposable disposable3 = this$0.timerDisposable;
        if (disposable3 != null) {
            disposable3.dispose();
        }
        Disposable disposable4 = this$0.delayDisposable;
        if (disposable4 == null) {
            return "";
        }
        disposable4.dispose();
        return "";
    }

    private final void startTimerDelay() {
        Log.d("AudioPlayHelper", "Delay Timer");
        Disposable disposable = this.delayDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        Disposable disposable2 = this.anotherDelayDisposable;
        if (disposable2 != null) {
            disposable2.dispose();
        }
        Observable<Long> timer = Observable.timer(3L, TimeUnit.SECONDS);
        Intrinsics.checkNotNullExpressionValue(timer, "timer(3, TimeUnit.SECONDS)");
        RxSubscribersKt.subscribeTo$default(timer, null, null, new AudioPlayHelper$startTimerDelay$1(this), new AudioPlayHelper$startTimerDelay$2(this), 3, null);
    }

    private final void startAnotherDelay() {
        Observable<Long> timer = Observable.timer(5L, TimeUnit.SECONDS);
        Intrinsics.checkNotNullExpressionValue(timer, "timer(5, TimeUnit.SECONDS)");
        RxSubscribersKt.subscribeTo$default(timer, null, null, new AudioPlayHelper$startAnotherDelay$1(this), new AudioPlayHelper$startAnotherDelay$2(this), 3, null);
    }

    public final void changeUrl(String str, IMediaPlayer iMediaPlayer) {
        play(str, iMediaPlayer);
    }

    public final synchronized void seekTo(int i, IMediaPlayer iMediaPlayer) {
        if (this.vlcPlayer.hasMedia() && !this.vlcPlayer.isReleased()) {
            this.vlcPlayer.setTime(i);
            this.startDelay = false;
            startTimer(ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS, iMediaPlayer);
        }
    }

    public final void pause(IMediaPlayer iMediaPlayer) {
        if (!this.vlcPlayer.hasMedia() || this.vlcPlayer.isReleased()) {
            return;
        }
        Disposable disposable = this.timerDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        if (this.vlcPlayer.isPlaying()) {
            this.vlcPlayer.pause();
        }
    }

    public final void play(IMediaPlayer iMediaPlayer) {
        if (!this.vlcPlayer.hasMedia() || this.vlcPlayer.isReleased()) {
            return;
        }
        Disposable disposable = this.timerDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        if (this.vlcPlayer.isPlaying()) {
            return;
        }
        this.vlcPlayer.play();
        this.isNeedAddTime = true;
        this.startDelay = false;
        startTimer(ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS, iMediaPlayer);
    }

    public final void pauseOrPlay(IMediaPlayer iMediaPlayer) {
        if (!this.vlcPlayer.hasMedia() || this.vlcPlayer.isReleased()) {
            return;
        }
        Disposable disposable = this.timerDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        if (this.vlcPlayer.isPlaying()) {
            this.vlcPlayer.pause();
            return;
        }
        this.vlcPlayer.play();
        this.isNeedAddTime = true;
        this.startDelay = false;
        startTimer(ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS, iMediaPlayer);
    }

    public final void resume() {
        this.vlcPlayer.play();
    }

    public final void setVolume(int i) {
        this.vlcPlayer.setVolume(i);
    }

    public final void release() {
        try {
            Disposable disposable = this.timerDisposable;
            if (disposable != null) {
                disposable.dispose();
            }
            Disposable disposable2 = this.delayDisposable;
            if (disposable2 != null) {
                disposable2.dispose();
            }
            Disposable disposable3 = this.anotherDelayDisposable;
            if (disposable3 != null) {
                disposable3.dispose();
            }
            this.vlcPlayer.stop();
            this.vlcPlayer.setEventListener((MediaPlayer.EventListener) null);
            IMedia media = this.vlcPlayer.getMedia();
            if (media == null) {
                return;
            }
            media.release();
        } catch (Exception unused) {
        }
    }
}
