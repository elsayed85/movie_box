package com.movieboxpro.android.utils;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.utils.RandomPlayUtils;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: RandomPlayUtils.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lio/reactivex/disposables/Disposable;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RandomPlayUtils$getPlayPath$5 extends Lambda implements Function1<Disposable, Unit> {
    final /* synthetic */ RandomPlayUtils this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RandomPlayUtils$getPlayPath$5(RandomPlayUtils randomPlayUtils) {
        super(1);
        this.this$0 = randomPlayUtils;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Disposable disposable) {
        invoke2(disposable);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(Disposable it) {
        RandomPlayUtils.RandomPlayListener randomPlayListener;
        Intrinsics.checkNotNullParameter(it, "it");
        randomPlayListener = this.this$0.listener;
        if (randomPlayListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException(ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            randomPlayListener = null;
        }
        randomPlayListener.showLoading();
    }
}