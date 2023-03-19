package com.movieboxpro.android.view.activity.exoplayer;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import com.google.android.exoplayer2.ExoPlayer;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.model.DownloadInfo;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.VLCInstance;
import com.movieboxpro.android.view.activity.exoplayer.controller.ExoNormalController;
import com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.interfaces.IMedia;
import tv.danmaku.ijk.media.player.IMediaPlayer;
/* compiled from: AudioPlayHelper.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0016\u001a\u00020\u00172\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bJ\u0006\u0010\u001c\u001a\u00020\u0004J\u0006\u0010\u001d\u001a\u00020\u0017J\u000e\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001f\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J \u0010\u001f\u001a\u00020\u00172\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bJ\u0006\u0010 \u001a\u00020\u0017J\u0006\u0010!\u001a\u00020\u0017J\u0016\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0019J\u0018\u0010$\u001a\u00020\u00172\u0006\u0010%\u001a\u00020\u00042\b\u0010\u0018\u001a\u0004\u0018\u00010&J\u000e\u0010'\u001a\u00020\u00172\u0006\u0010(\u001a\u00020\u0004J\b\u0010)\u001a\u00020\u0017H\u0002J\u0018\u0010*\u001a\u00020\u00172\u0006\u0010%\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010+\u001a\u00020\u0017H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcom/movieboxpro/android/view/activity/exoplayer/AudioPlayHelper;", "", "()V", "adjustDelayCount", "", "anotherDelayDisposable", "Lio/reactivex/disposables/Disposable;", "customDelay", "delayDisposable", "isNeedAddTime", "", "over400Count", "startDelay", "timeList", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "timerDisposable", "url", "", "vlcPlayer", "Lorg/videolan/libvlc/MediaPlayer;", "changeUrl", "", "player", "Lcom/movieboxpro/android/view/activity/vlcvideoplayer/controller/MediaPlayerControl;", "normalController", "Lcom/movieboxpro/android/view/activity/exoplayer/controller/ExoNormalController;", "getAudioDelay", DownloadInfo.DOWNLOAD_PAUSE, "pauseOrPlay", "play", "release", "resume", "seekTo", "position", "setAudioDelay", "delay", "Ltv/danmaku/ijk/media/player/IMediaPlayer;", "setVolume", MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, "startAnotherDelay", "startTimer", "startTimerDelay", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
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

    public final void play(String str, final MediaPlayerControl player, final ExoNormalController normalController) {
        Intrinsics.checkNotNullParameter(player, "player");
        Intrinsics.checkNotNullParameter(normalController, "normalController");
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
        player.setVolume(0);
        player.pause();
        Media media = new Media(this.vlcPlayer.getLibVLC(), Uri.parse(str));
        media.addOption(Intrinsics.stringPlus(":start-time=", Long.valueOf(player.getCurrentPosition() / 1000)));
        IMedia media2 = this.vlcPlayer.getMedia();
        if (media2 != null) {
            media2.release();
        }
        media.addOption(":no-video");
        this.vlcPlayer.setMedia(media);
        this.vlcPlayer.play();
        this.vlcPlayer.setEventListener(new MediaPlayer.EventListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.-$$Lambda$AudioPlayHelper$idcD_oUkg1KeKskGOQw2E9lf-aY
            @Override // org.videolan.libvlc.interfaces.AbstractVLCEvent.Listener
            public final void onEvent(MediaPlayer.Event event) {
                AudioPlayHelper.m356play$lambda0(MediaPlayerControl.this, normalController, this, event);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: play$lambda-0  reason: not valid java name */
    public static final void m356play$lambda0(MediaPlayerControl player, ExoNormalController normalController, AudioPlayHelper this$0, MediaPlayer.Event event) {
        Intrinsics.checkNotNullParameter(player, "$player");
        Intrinsics.checkNotNullParameter(normalController, "$normalController");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (event.type != 259 || event.getBuffering() < 100.0f) {
            return;
        }
        if (!player.isPlaying()) {
            player.start();
            normalController.resumePlay();
        }
        Disposable disposable = this$0.timerDisposable;
        if (disposable != null) {
            if (!(disposable != null && disposable.isDisposed())) {
                return;
            }
        }
        this$0.startTimer(1000L, player);
    }

    public final void setAudioDelay(int i, IMediaPlayer iMediaPlayer) {
        this.customDelay += i;
        this.vlcPlayer.setTime((iMediaPlayer == null ? 0L : iMediaPlayer.getCurrentPosition()) - this.customDelay);
    }

    public final int getAudioDelay() {
        return this.customDelay;
    }

    private final void startTimer(long j, final MediaPlayerControl mediaPlayerControl) {
        Log.d("AudioPlayHelper", Intrinsics.stringPlus("startTimer:delay-", Long.valueOf(j)));
        this.timeList.clear();
        Observable.interval(j, 600L, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).map(new Function() { // from class: com.movieboxpro.android.view.activity.exoplayer.-$$Lambda$AudioPlayHelper$-0-aVez4K9EkOmheUFYt_DHiiEQ
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                Long m357startTimer$lambda1;
                m357startTimer$lambda1 = AudioPlayHelper.m357startTimer$lambda1(MediaPlayerControl.this, (Long) obj);
                return m357startTimer$lambda1;
            }
        }).observeOn(Schedulers.io()).map(new Function() { // from class: com.movieboxpro.android.view.activity.exoplayer.-$$Lambda$AudioPlayHelper$RqvGylby0yOCSajqx5wSZLIYC9Q
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                String m358startTimer$lambda2;
                m358startTimer$lambda2 = AudioPlayHelper.m358startTimer$lambda2(AudioPlayHelper.this, (Long) obj);
                return m358startTimer$lambda2;
            }
        }).subscribeOn(Schedulers.io()).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.exoplayer.AudioPlayHelper$startTimer$3
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable e) {
                Intrinsics.checkNotNullParameter(e, "e");
            }

            @Override // io.reactivex.Observer
            public void onNext(String t) {
                Intrinsics.checkNotNullParameter(t, "t");
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                AudioPlayHelper.this.timerDisposable = d;
                AudioPlayHelper.this.adjustDelayCount = 0;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startTimer$lambda-1  reason: not valid java name */
    public static final Long m357startTimer$lambda1(MediaPlayerControl player, Long it) {
        Intrinsics.checkNotNullParameter(player, "$player");
        Intrinsics.checkNotNullParameter(it, "it");
        return Long.valueOf(player.getCurrentPosition());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startTimer$lambda-2  reason: not valid java name */
    public static final String m358startTimer$lambda2(AudioPlayHelper this$0, Long it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        long time = ((this$0.vlcPlayer.getTime() - this$0.vlcPlayer.getAudioDelay()) + this$0.customDelay) - ((int) it.longValue());
        Log.d("AudioPlayHelper", time + "delay:" + this$0.vlcPlayer.getAudioDelay());
        if (this$0.adjustDelayCount < 5) {
            if (!(Math.abs(time) <= PrefsUtils.getInstance().getLong(Constant.Prefs.AUDIO_SYN_TIME, 500L) + ((long) Math.abs(this$0.customDelay)))) {
                this$0.vlcPlayer.setAudioDelay(0L);
                this$0.vlcPlayer.setTime(it.longValue() - this$0.customDelay);
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

    public final void changeUrl(String str, MediaPlayerControl player, ExoNormalController normalController) {
        Intrinsics.checkNotNullParameter(player, "player");
        Intrinsics.checkNotNullParameter(normalController, "normalController");
        play(str, player, normalController);
    }

    public final synchronized void seekTo(int i, MediaPlayerControl player) {
        Intrinsics.checkNotNullParameter(player, "player");
        if (this.vlcPlayer.hasMedia() && !this.vlcPlayer.isReleased()) {
            this.vlcPlayer.setTime(i);
            this.startDelay = false;
            startTimer(ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS, player);
        }
    }

    public final void pause() {
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

    public final void play(MediaPlayerControl player) {
        Intrinsics.checkNotNullParameter(player, "player");
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
        startTimer(ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS, player);
    }

    public final void pauseOrPlay(MediaPlayerControl player) {
        Intrinsics.checkNotNullParameter(player, "player");
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
        startTimer(ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS, player);
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
