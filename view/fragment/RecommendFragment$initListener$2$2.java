package com.movieboxpro.android.view.fragment;

import com.movieboxpro.android.view.fragment.RecommendFragment;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: RecommendFragment.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RecommendFragment$initListener$2$2 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ RecommendFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecommendFragment$initListener$2$2(RecommendFragment recommendFragment) {
        super(1);
        this.this$0 = recommendFragment;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(String str) {
        invoke2(str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(String it) {
        RecommendFragment.RecommendListFragment recommendListFragment;
        Intrinsics.checkNotNullParameter(it, "it");
        recommendListFragment = this.this$0.fragment;
        if (recommendListFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
            recommendListFragment = null;
        }
        recommendListFragment.startRefresh();
    }
}
