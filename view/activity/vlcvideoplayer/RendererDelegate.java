package com.movieboxpro.android.view.activity.vlcvideoplayer;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.livedata.LiveDataSet;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.VLCInstance;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.RendererDiscoverer;
import org.videolan.libvlc.RendererItem;
import org.videolan.libvlc.interfaces.ILibVLC;
/* compiled from: RendererDelegate.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\u0012\u0010\u0012\u001a\u00020\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u0006\u0010\u0015\u001a\u00020\u0011J\u0006\u0010\u0016\u001a\u00020\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/movieboxpro/android/view/activity/vlcvideoplayer/RendererDelegate;", "Lorg/videolan/libvlc/RendererDiscoverer$EventListener;", "()V", "TAG", "", "discoverers", "Ljava/util/ArrayList;", "Lorg/videolan/libvlc/RendererDiscoverer;", "Lkotlin/collections/ArrayList;", "renderers", "Lcom/movieboxpro/android/livedata/LiveDataSet;", "Lorg/videolan/libvlc/RendererItem;", "getRenderers", "()Lcom/movieboxpro/android/livedata/LiveDataSet;", "started", "", "clear", "", "onEvent", NotificationCompat.CATEGORY_EVENT, "Lorg/videolan/libvlc/RendererDiscoverer$Event;", TtmlNode.START, "stop", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RendererDelegate implements RendererDiscoverer.EventListener {
    public static final RendererDelegate INSTANCE = new RendererDelegate();
    private static final String TAG = "VLC/RendererDelegate";
    private static final ArrayList<RendererDiscoverer> discoverers = new ArrayList<>();
    private static final LiveDataSet<RendererItem> renderers = new LiveDataSet<>();
    private static volatile boolean started;

    private RendererDelegate() {
    }

    static {
        INSTANCE.start();
    }

    public final LiveDataSet<RendererItem> getRenderers() {
        return renderers;
    }

    public final void start() {
        if (started) {
            return;
        }
        VLCInstance vLCInstance = VLCInstance.INSTANCE;
        Context context = App.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext()");
        final ILibVLC vLCInstance2 = vLCInstance.getInstance(context);
        Observable compose = Observable.just("").flatMap(new Function() { // from class: com.movieboxpro.android.view.activity.vlcvideoplayer.-$$Lambda$RendererDelegate$So8CaIqonIJwBlZ3ZTKGM5F3TXQ
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                ObservableSource m893start$lambda0;
                m893start$lambda0 = RendererDelegate.m893start$lambda0(ILibVLC.this, (String) obj);
                return m893start$lambda0;
            }
        }).map(new Function() { // from class: com.movieboxpro.android.view.activity.vlcvideoplayer.-$$Lambda$RendererDelegate$JdHdAkgcYiHPNFpVJgpKFr7k9ns
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                RendererDiscoverer m894start$lambda1;
                m894start$lambda1 = RendererDelegate.m894start$lambda1(ILibVLC.this, this, (RendererDiscoverer.Description) obj);
                return m894start$lambda1;
            }
        }).map($$Lambda$RendererDelegate$byea0BcrCk_96aWj8Cv0CsJs4JI.INSTANCE).compose(RxUtils.rxSchedulerHelper());
        Intrinsics.checkNotNullExpressionValue(compose, "just(\"\")\n               …tils.rxSchedulerHelper())");
        RxSubscribersKt.subscribeTo$default(compose, null, null, null, RendererDelegate$start$4.INSTANCE, 7, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: start$lambda-0  reason: not valid java name */
    public static final ObservableSource m893start$lambda0(ILibVLC libVlc, String it) {
        Intrinsics.checkNotNullParameter(libVlc, "$libVlc");
        Intrinsics.checkNotNullParameter(it, "it");
        started = true;
        RendererDiscoverer.Description[] list = RendererDiscoverer.list(libVlc);
        Intrinsics.checkNotNullExpressionValue(list, "list(libVlc)");
        return Observable.fromIterable(ArraysKt.toList(list));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: start$lambda-1  reason: not valid java name */
    public static final RendererDiscoverer m894start$lambda1(ILibVLC libVlc, RendererDelegate this$0, RendererDiscoverer.Description it) {
        Intrinsics.checkNotNullParameter(libVlc, "$libVlc");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        RendererDiscoverer rendererDiscoverer = new RendererDiscoverer(libVlc, it.name);
        discoverers.add(rendererDiscoverer);
        rendererDiscoverer.setEventListener((RendererDiscoverer.EventListener) this$0);
        return rendererDiscoverer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: start$lambda-2  reason: not valid java name */
    public static final Boolean m895start$lambda2(RendererDiscoverer it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Boolean.valueOf(!it.isReleased() ? it.start() : false);
    }

    public final void stop() {
        if (started) {
            started = false;
            Iterator<RendererDiscoverer> it = discoverers.iterator();
            while (it.hasNext()) {
                it.next().stop();
            }
            clear();
        }
    }

    private final void clear() {
        discoverers.clear();
        renderers.clear();
    }

    @Override // org.videolan.libvlc.interfaces.AbstractVLCEvent.Listener
    public void onEvent(RendererDiscoverer.Event event) {
        Integer valueOf = event == null ? null : Integer.valueOf(event.type);
        if (valueOf != null && valueOf.intValue() == 1282) {
            LiveDataSet<RendererItem> liveDataSet = renderers;
            RendererItem item = event.getItem();
            Intrinsics.checkNotNullExpressionValue(item, "event.item");
            liveDataSet.add((LiveDataSet<RendererItem>) item);
        } else if (valueOf != null && valueOf.intValue() == 1283) {
            LiveDataSet<RendererItem> liveDataSet2 = renderers;
            RendererItem item2 = event.getItem();
            Intrinsics.checkNotNullExpressionValue(item2, "event.item");
            liveDataSet2.remove((LiveDataSet<RendererItem>) item2);
        }
    }
}
