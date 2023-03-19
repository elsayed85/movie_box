package com.movieboxpro.android.view.videocontroller;

import android.util.Log;
import com.movieboxpro.android.event.RefreshPlayUrlEvent;
import com.movieboxpro.android.view.activity.vlcvideoplayer.listener.PlayListener;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.greenrobot.eventbus.EventBus;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: VlcPlayer.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "code", "", "kotlin.jvm.PlatformType", "invoke", "(Ljava/lang/Integer;)V"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class VlcPlayer$1$3 extends Lambda implements Function1<Integer, Unit> {
    final /* synthetic */ VlcPlayer this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VlcPlayer$1$3(VlcPlayer vlcPlayer) {
        super(1);
        this.this$0 = vlcPlayer;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
        invoke2(num);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(Integer num) {
        String str;
        ArrayList<PlayListener> arrayList;
        str = this.this$0.TAG;
        Log.i(str, Intrinsics.stringPlus("MediaPlayer.Event.EncounteredError:", num));
        if (num == null || num.intValue() != 410) {
            arrayList = this.this$0.listeners;
            for (PlayListener playListener : arrayList) {
                if (playListener != null) {
                    playListener.onError(String.valueOf(num));
                }
            }
            return;
        }
        EventBus.getDefault().post(new RefreshPlayUrlEvent());
    }
}
