package com.movieboxpro.android.view.dialog;

import com.movieboxpro.android.databinding.FragmentSkipTimeSettingBinding;
import com.movieboxpro.android.model.SkipEnd;
import com.movieboxpro.android.model.SkipStart;
import com.movieboxpro.android.model.SkipTimeResponse;
import com.movieboxpro.android.view.dialog.SkipTimeFragment;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SkipTimeFragment.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "response", "Lcom/movieboxpro/android/model/SkipTimeResponse;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SkipTimeFragment$SkipTimeSettingFragment$getSkipTime$3 extends Lambda implements Function1<SkipTimeResponse, Unit> {
    final /* synthetic */ SkipTimeFragment.SkipTimeSettingFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SkipTimeFragment$SkipTimeSettingFragment$getSkipTime$3(SkipTimeFragment.SkipTimeSettingFragment skipTimeSettingFragment) {
        super(1);
        this.this$0 = skipTimeSettingFragment;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(SkipTimeResponse skipTimeResponse) {
        invoke2(skipTimeResponse);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(SkipTimeResponse response) {
        FragmentSkipTimeSettingBinding binding;
        binding = this.this$0.getBinding();
        binding.loadingView.hideLoading();
        SkipTimeFragment.SkipTimeSettingFragment skipTimeSettingFragment = this.this$0;
        ArrayList<SkipStart> start_top_list = response.getStart_top_list();
        ArrayList<SkipEnd> end_top_list = response.getEnd_top_list();
        Intrinsics.checkNotNullExpressionValue(response, "response");
        skipTimeSettingFragment.getVideoImage(start_top_list, end_top_list, response);
    }
}
