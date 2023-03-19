package com.movieboxpro.android.view.activity.actor;

import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* compiled from: ActorDetailPresenter.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\"\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "it", "Ljava/util/HashMap;", "", "", "Lkotlin/collections/HashMap;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class ActorDetailPresenter$getReviewNum$1 extends Lambda implements Function1<HashMap<String, Integer>, Unit> {
    final /* synthetic */ ActorDetailPresenter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActorDetailPresenter$getReviewNum$1(ActorDetailPresenter actorDetailPresenter) {
        super(1);
        this.this$0 = actorDetailPresenter;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(HashMap<String, Integer> hashMap) {
        invoke2(hashMap);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(HashMap<String, Integer> it) {
        Intrinsics.checkNotNullParameter(it, "it");
        this.this$0.getView().showReviewCount(String.valueOf(it.get("count")));
    }
}
