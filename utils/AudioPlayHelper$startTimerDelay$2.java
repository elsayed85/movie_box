package com.movieboxpro.android.utils;

import android.util.Log;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.libvlc.MediaPlayer;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AudioPlayHelper.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "invoke", "(Ljava/lang/Long;)V"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AudioPlayHelper$startTimerDelay$2 extends Lambda implements Function1<Long, Unit> {
    final /* synthetic */ AudioPlayHelper this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AudioPlayHelper$startTimerDelay$2(AudioPlayHelper audioPlayHelper) {
        super(1);
        this.this$0 = audioPlayHelper;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Long l) {
        invoke2(l);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(Long l) {
        ArrayList arrayList;
        MediaPlayer mediaPlayer;
        ArrayList arrayList2;
        this.this$0.over400Count = 0;
        arrayList = this.this$0.timeList;
        long averageOfLong = (long) CollectionsKt.averageOfLong(arrayList);
        Log.d("AudioPlayHelper", Intrinsics.stringPlus("setDelay:", Long.valueOf(averageOfLong)));
        mediaPlayer = this.this$0.vlcPlayer;
        mediaPlayer.setAudioDelay(averageOfLong);
        arrayList2 = this.this$0.timeList;
        arrayList2.clear();
    }
}
