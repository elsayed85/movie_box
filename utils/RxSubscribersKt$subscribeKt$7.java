package com.movieboxpro.android.utils;

import androidx.exifinterface.media.ExifInterface;
import com.alibaba.fastjson.asm.Opcodes;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.ServerException;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: RxSubscribers.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u000e\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "", "it", "", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 6, 0}, xi = Opcodes.ARETURN)
/* loaded from: classes3.dex */
public final class RxSubscribersKt$subscribeKt$7<T> implements Consumer {
    final /* synthetic */ Function1<ApiException, Unit> $onError;
    final /* synthetic */ Function1<ServerException, Unit> $onServerError;

    /* JADX WARN: Multi-variable type inference failed */
    public RxSubscribersKt$subscribeKt$7(Function1<? super ApiException, Unit> function1, Function1<? super ServerException, Unit> function12) {
        this.$onError = function1;
        this.$onServerError = function12;
    }

    @Override // io.reactivex.functions.Consumer
    public final void accept(Throwable th) {
        Function1<ApiException, Unit> function1 = this.$onError;
        ApiException handleException = ApiException.handleException(th);
        Intrinsics.checkNotNullExpressionValue(handleException, "handleException(it)");
        function1.invoke(handleException);
        if (th instanceof ServerException) {
            this.$onServerError.invoke(th);
        }
    }
}
