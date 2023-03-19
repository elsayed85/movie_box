package com.movieboxpro.android.view.activity;

import android.widget.TextView;
import com.movieboxpro.android.databinding.ActivityChildModeBinding;
import com.movieboxpro.android.model.common.Device;
import com.movieboxpro.android.utils.CommonExtKt;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ChildModeActivity.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u000120\u0010\u0002\u001a,\u0012\u0004\u0012\u00020\u0004 \u0006*\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u00050\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "it", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/common/Device;", "Lkotlin/collections/ArrayList;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ChildModeActivity$getDevices$2 extends Lambda implements Function1<ArrayList<Device>, Unit> {
    final /* synthetic */ ChildModeActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChildModeActivity$getDevices$2(ChildModeActivity childModeActivity) {
        super(1);
        this.this$0 = childModeActivity;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(ArrayList<Device> arrayList) {
        invoke2(arrayList);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(ArrayList<Device> arrayList) {
        ActivityChildModeBinding binding;
        ActivityChildModeBinding binding2;
        ActivityChildModeBinding binding3;
        if (arrayList.size() != 0) {
            binding2 = this.this$0.getBinding();
            TextView textView = binding2.tvTvNum;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.tvTvNum");
            CommonExtKt.visible(textView);
            binding3 = this.this$0.getBinding();
            binding3.tvTvNum.setText(String.valueOf(arrayList.size()));
            return;
        }
        binding = this.this$0.getBinding();
        TextView textView2 = binding.tvTvNum;
        Intrinsics.checkNotNullExpressionValue(textView2, "binding.tvTvNum");
        CommonExtKt.gone(textView2);
    }
}
