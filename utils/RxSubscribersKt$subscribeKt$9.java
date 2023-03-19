package com.movieboxpro.android.utils;

import androidx.exifinterface.media.ExifInterface;
import com.alibaba.fastjson.asm.Opcodes;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: RxSubscribers.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u000e\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "", "it", "Lio/reactivex/disposables/Disposable;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 6, 0}, xi = Opcodes.ARETURN)
/* loaded from: classes3.dex */
public final class RxSubscribersKt$subscribeKt$9<T> implements Consumer {
    final /* synthetic */ Function1<Disposable, Unit> $onStart;

    /* JADX WARN: Multi-variable type inference failed */
    public RxSubscribersKt$subscribeKt$9(Function1<? super Disposable, Unit> function1) {
        this.$onStart = function1;
    }

    @Override // io.reactivex.functions.Consumer
    public final void accept(Disposable it) {
        Function1<Disposable, Unit> function1 = this.$onStart;
        Intrinsics.checkNotNullExpressionValue(it, "it");
        function1.invoke(it);
    }
}
