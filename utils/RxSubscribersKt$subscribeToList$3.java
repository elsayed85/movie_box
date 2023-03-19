package com.movieboxpro.android.utils;

import androidx.exifinterface.media.ExifInterface;
import com.alibaba.fastjson.asm.Opcodes;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* compiled from: RxSubscribers.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0000\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "", "it", "", "invoke"}, k = 3, mv = {1, 6, 0}, xi = Opcodes.ARETURN)
/* loaded from: classes3.dex */
public final class RxSubscribersKt$subscribeToList$3 extends Lambda implements Function1<List<? extends T>, Unit> {
    public static final RxSubscribersKt$subscribeToList$3 INSTANCE = new RxSubscribersKt$subscribeToList$3();

    public RxSubscribersKt$subscribeToList$3() {
        super(1);
    }

    public final void invoke(List<? extends T> it) {
        Intrinsics.checkNotNullParameter(it, "it");
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
        invoke((List) obj);
        return Unit.INSTANCE;
    }
}
