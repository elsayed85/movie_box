package com.movieboxpro.android.view.activity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.movieboxpro.android.model.ReviewItem;
import com.movieboxpro.android.view.activity.MyCommentActivity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MyCommentActivity.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MyCommentActivity$MyCommentListFragment$deleteComment$2 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ int $position;
    final /* synthetic */ MyCommentActivity.MyCommentListFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MyCommentActivity$MyCommentListFragment$deleteComment$2(MyCommentActivity.MyCommentListFragment myCommentListFragment, int i) {
        super(1);
        this.this$0 = myCommentListFragment;
        this.$position = i;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(String str) {
        invoke2(str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(String it) {
        BaseQuickAdapter baseQuickAdapter;
        BaseQuickAdapter baseQuickAdapter2;
        Intrinsics.checkNotNullParameter(it, "it");
        baseQuickAdapter = this.this$0.mAdapter;
        ((ReviewItem) baseQuickAdapter.getItem(this.$position)).set_delete(1);
        baseQuickAdapter2 = this.this$0.mAdapter;
        baseQuickAdapter2.notifyItemChanged(this.$position);
    }
}
