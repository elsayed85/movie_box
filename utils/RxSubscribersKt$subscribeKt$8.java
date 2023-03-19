package com.movieboxpro.android.utils;

import androidx.exifinterface.media.ExifInterface;
import com.alibaba.fastjson.asm.Opcodes;
import io.reactivex.functions.Action;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
/* compiled from: RxSubscribers.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "", "run"}, k = 3, mv = {1, 6, 0}, xi = Opcodes.ARETURN)
/* loaded from: classes3.dex */
public final class RxSubscribersKt$subscribeKt$8 implements Action {
    final /* synthetic */ Function0<Unit> $onComplete;

    public RxSubscribersKt$subscribeKt$8(Function0<Unit> function0) {
        this.$onComplete = function0;
    }

    @Override // io.reactivex.functions.Action
    public final void run() {
        this.$onComplete.invoke();
    }
}
