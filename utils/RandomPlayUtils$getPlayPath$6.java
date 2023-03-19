package com.movieboxpro.android.utils;

import com.movieboxpro.android.http.ServerException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* compiled from: RandomPlayUtils.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/movieboxpro/android/http/ServerException;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class RandomPlayUtils$getPlayPath$6 extends Lambda implements Function1<ServerException, Unit> {
    public static final RandomPlayUtils$getPlayPath$6 INSTANCE = new RandomPlayUtils$getPlayPath$6();

    RandomPlayUtils$getPlayPath$6() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(ServerException serverException) {
        invoke2(serverException);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(ServerException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (it.getErrCode() == -88) {
            ToastUtils.showShort(it.getMessage(), new Object[0]);
        }
    }
}
