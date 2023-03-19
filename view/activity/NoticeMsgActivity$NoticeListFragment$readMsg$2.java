package com.movieboxpro.android.view.activity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.movieboxpro.android.model.NoticeMsg;
import com.movieboxpro.android.view.activity.NoticeMsgActivity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: NoticeMsgActivity.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class NoticeMsgActivity$NoticeListFragment$readMsg$2 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ NoticeMsg $item;
    final /* synthetic */ int $position;
    final /* synthetic */ NoticeMsgActivity.NoticeListFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NoticeMsgActivity$NoticeListFragment$readMsg$2(NoticeMsg noticeMsg, NoticeMsgActivity.NoticeListFragment noticeListFragment, int i) {
        super(1);
        this.$item = noticeMsg;
        this.this$0 = noticeListFragment;
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
        Intrinsics.checkNotNullParameter(it, "it");
        this.$item.setIs_read(1);
        baseQuickAdapter = this.this$0.mAdapter;
        baseQuickAdapter.notifyItemChanged(this.$position);
    }
}
