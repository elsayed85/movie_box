package com.movieboxpro.android.view.dialog;

import android.widget.TextView;
import com.movieboxpro.android.databinding.AdapterMoreImageBinding;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.view.dialog.SkipTimeFragment;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SkipTimeFragment.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lio/reactivex/disposables/Disposable;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SkipTimeFragment$SkipTimeSettingFragment$getMoreImage$2 extends Lambda implements Function1<Disposable, Unit> {
    final /* synthetic */ SkipTimeFragment.SkipTimeSettingFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SkipTimeFragment$SkipTimeSettingFragment$getMoreImage$2(SkipTimeFragment.SkipTimeSettingFragment skipTimeSettingFragment) {
        super(1);
        this.this$0 = skipTimeSettingFragment;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Disposable disposable) {
        invoke2(disposable);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(Disposable it) {
        AdapterMoreImageBinding adapterMoreImageBinding;
        AdapterMoreImageBinding adapterMoreImageBinding2;
        Intrinsics.checkNotNullParameter(it, "it");
        adapterMoreImageBinding = this.this$0.moreBinding;
        AdapterMoreImageBinding adapterMoreImageBinding3 = null;
        if (adapterMoreImageBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moreBinding");
            adapterMoreImageBinding = null;
        }
        adapterMoreImageBinding.loadingView.showLoading();
        adapterMoreImageBinding2 = this.this$0.moreBinding;
        if (adapterMoreImageBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moreBinding");
        } else {
            adapterMoreImageBinding3 = adapterMoreImageBinding2;
        }
        TextView textView = adapterMoreImageBinding3.tvMore;
        Intrinsics.checkNotNullExpressionValue(textView, "moreBinding.tvMore");
        CommonExtKt.gone(textView);
    }
}
