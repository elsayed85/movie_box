package com.movieboxpro.android.view.dialog;

import com.movieboxpro.android.base.mvp.BaseContract;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ReviewDialogFragment.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u000120\u0010\u0002\u001a,\u0012\u0004\u0012\u00020\u0004 \u0006*\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u00050\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "it", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ReviewDialogFragment$sendReview$9 extends Lambda implements Function1<ArrayList<String>, Unit> {
    final /* synthetic */ String $content;
    final /* synthetic */ ReviewDialogFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReviewDialogFragment$sendReview$9(ReviewDialogFragment reviewDialogFragment, String str) {
        super(1);
        this.this$0 = reviewDialogFragment;
        this.$content = str;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(ArrayList<String> arrayList) {
        invoke2(arrayList);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(ArrayList<String> it) {
        BaseContract.BasePresenter basePresenter;
        String str;
        String str2;
        int i;
        basePresenter = this.this$0.mPresenter;
        str = this.this$0.replyId;
        str2 = this.this$0.id;
        String str3 = this.$content;
        i = this.this$0.boxType;
        Intrinsics.checkNotNullExpressionValue(it, "it");
        ((ReviewDialogPresenter) basePresenter).sendReview(str, str2, str3, i, it);
    }
}
