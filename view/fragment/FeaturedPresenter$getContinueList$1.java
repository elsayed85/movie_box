package com.movieboxpro.android.view.fragment;

import com.movieboxpro.android.model.common.Homelist;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FeaturedPresenter.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "Lcom/movieboxpro/android/model/common/Homelist$Typelist;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FeaturedPresenter$getContinueList$1 extends Lambda implements Function1<List<? extends Homelist.Typelist>, Unit> {
    final /* synthetic */ FeaturedPresenter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FeaturedPresenter$getContinueList$1(FeaturedPresenter featuredPresenter) {
        super(1);
        this.this$0 = featuredPresenter;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(List<? extends Homelist.Typelist> list) {
        invoke2(list);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(List<? extends Homelist.Typelist> it) {
        Intrinsics.checkNotNullParameter(it, "it");
        this.this$0.getView().showContinueList(it);
    }
}
