package com.movieboxpro.android.view.activity.vlcvideoplayer;

import android.content.Context;
import com.movieboxpro.android.utils.SingletonHolder;
import com.movieboxpro.android.view.videocontroller.VlcPlayer;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* compiled from: VlcPlayerInstance.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0003R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0002X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/view/activity/vlcvideoplayer/VlcPlayerInstance;", "Lcom/movieboxpro/android/utils/SingletonHolder;", "Lcom/movieboxpro/android/view/videocontroller/VlcPlayer;", "Landroid/content/Context;", "()V", "vlcPlayer", "initPlayer", "context", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class VlcPlayerInstance extends SingletonHolder<VlcPlayer, Context> {
    public static final VlcPlayerInstance INSTANCE = new VlcPlayerInstance();
    private static VlcPlayer vlcPlayer;

    /* compiled from: VlcPlayerInstance.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lcom/movieboxpro/android/view/videocontroller/VlcPlayer;", "context", "Landroid/content/Context;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
    /* renamed from: com.movieboxpro.android.view.activity.vlcvideoplayer.VlcPlayerInstance$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    static final class AnonymousClass1 extends Lambda implements Function1<Context, VlcPlayer> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final VlcPlayer invoke(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return VlcPlayerInstance.INSTANCE.initPlayer(context);
        }
    }

    private VlcPlayerInstance() {
        super(AnonymousClass1.INSTANCE);
    }

    public final VlcPlayer initPlayer(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        VlcPlayer vlcPlayer2 = new VlcPlayer(context);
        vlcPlayer = vlcPlayer2;
        Intrinsics.checkNotNull(vlcPlayer2);
        return vlcPlayer2;
    }
}
