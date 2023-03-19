package com.movieboxpro.android.view.dialog;

import com.movieboxpro.android.base.CommBaseLoadMoreAdapter;
import com.movieboxpro.android.model.ReviewItem;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ReviewDialogFragment.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ReviewDialogFragment$deleteComment$3 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ int $position;
    final /* synthetic */ ReviewDialogFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReviewDialogFragment$deleteComment$3(ReviewDialogFragment reviewDialogFragment, int i) {
        super(1);
        this.this$0 = reviewDialogFragment;
        this.$position = i;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(String str) {
        invoke2(str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(String it) {
        CommBaseLoadMoreAdapter commBaseLoadMoreAdapter;
        CommBaseLoadMoreAdapter commBaseLoadMoreAdapter2;
        Intrinsics.checkNotNullParameter(it, "it");
        this.this$0.hideLoadingView();
        commBaseLoadMoreAdapter = this.this$0.adapter;
        CommBaseLoadMoreAdapter commBaseLoadMoreAdapter3 = null;
        if (commBaseLoadMoreAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseLoadMoreAdapter = null;
        }
        ((ReviewItem) commBaseLoadMoreAdapter.getItem(this.$position)).set_delete(1);
        commBaseLoadMoreAdapter2 = this.this$0.adapter;
        if (commBaseLoadMoreAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            commBaseLoadMoreAdapter3 = commBaseLoadMoreAdapter2;
        }
        commBaseLoadMoreAdapter3.notifyItemChanged(this.$position);
    }
}
