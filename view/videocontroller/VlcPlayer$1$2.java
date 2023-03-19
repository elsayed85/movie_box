package com.movieboxpro.android.view.videocontroller;

import com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayListener;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: VlcPlayer.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "ex", "", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class VlcPlayer$1$2 extends Lambda implements Function1<Throwable, Unit> {
    final /* synthetic */ VlcPlayer this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VlcPlayer$1$2(VlcPlayer vlcPlayer) {
        super(1);
        this.this$0 = vlcPlayer;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(Throwable ex) {
        ArrayList<PlayListener> arrayList;
        Intrinsics.checkNotNullParameter(ex, "ex");
        arrayList = this.this$0.listeners;
        for (PlayListener playListener : arrayList) {
            if (playListener != null) {
                playListener.onError(ex.getMessage());
            }
        }
    }
}
