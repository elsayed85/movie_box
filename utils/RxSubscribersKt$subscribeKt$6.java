package com.movieboxpro.android.utils;

import androidx.exifinterface.media.ExifInterface;
import com.alibaba.fastjson.asm.Opcodes;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: RxSubscribers.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u000e\u0010\u0004\u001a\n \u0005*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "", "it", "kotlin.jvm.PlatformType", "accept", "(Ljava/lang/Object;)V"}, k = 3, mv = {1, 6, 0}, xi = Opcodes.ARETURN)
/* loaded from: classes3.dex */
public final class RxSubscribersKt$subscribeKt$6<T> implements Consumer {
    final /* synthetic */ Function1<T, Unit> $onSuccess;

    /* JADX WARN: Multi-variable type inference failed */
    public RxSubscribersKt$subscribeKt$6(Function1<? super T, Unit> function1) {
        this.$onSuccess = function1;
    }

    @Override // io.reactivex.functions.Consumer
    public final void accept(T it) {
        Function1<T, Unit> function1 = this.$onSuccess;
        Intrinsics.checkNotNullExpressionValue(it, "it");
        function1.invoke(it);
    }
}
