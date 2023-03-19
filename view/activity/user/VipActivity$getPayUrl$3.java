package com.movieboxpro.android.view.activity.user;

import com.movieboxpro.android.config.ConfigKey;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: VipActivity.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Ljava/util/HashMap;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class VipActivity$getPayUrl$3 extends Lambda implements Function1<HashMap<?, ?>, Unit> {
    final /* synthetic */ VipActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VipActivity$getPayUrl$3(VipActivity vipActivity) {
        super(1);
        this.this$0 = vipActivity;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(HashMap<?, ?> hashMap) {
        invoke2(hashMap);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(HashMap<?, ?> it) {
        Intrinsics.checkNotNullParameter(it, "it");
        this.this$0.hideLoadingView();
        Object obj = it.get(ConfigKey.PAY_URL);
        if (obj == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
        String str = (String) obj;
        if (StringsKt.isBlank(str)) {
            return;
        }
        this.this$0.payUrl = str;
    }
}
