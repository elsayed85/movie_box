package com.movieboxpro.android.view.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.movieboxpro.android.model.ReviewItem;
import com.movieboxpro.android.model.ReviewResponse;
import com.movieboxpro.android.view.activity.ReplyDetailActivity;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ReplyDetailActivity.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/movieboxpro/android/model/ReviewResponse;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ReplyDetailActivity$ReplyDetailListFragment$refreshSort$3 extends Lambda implements Function1<ReviewResponse, Unit> {
    final /* synthetic */ ReplyDetailActivity.ReplyDetailListFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReplyDetailActivity$ReplyDetailListFragment$refreshSort$3(ReplyDetailActivity.ReplyDetailListFragment replyDetailListFragment) {
        super(1);
        this.this$0 = replyDetailListFragment;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(ReviewResponse reviewResponse) {
        invoke2(reviewResponse);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(ReviewResponse it) {
        BaseQuickAdapter baseQuickAdapter;
        BaseQuickAdapter baseQuickAdapter2;
        BaseQuickAdapter baseQuickAdapter3;
        RecyclerView recyclerView;
        Intrinsics.checkNotNullParameter(it, "it");
        this.this$0.endRefreshView();
        baseQuickAdapter = this.this$0.mAdapter;
        for (IndexedValue indexedValue : CollectionsKt.withIndex(baseQuickAdapter.getData())) {
            if (((ReviewItem) indexedValue.getValue()).getItemType() == 2) {
                baseQuickAdapter2 = this.this$0.mAdapter;
                ArrayList arrayList = new ArrayList(baseQuickAdapter2.getData().subList(0, indexedValue.getIndex() + 1));
                List<ReviewItem> list = it.getList();
                if (list != null) {
                    for (ReviewItem reviewItem : list) {
                        List<ReviewItem> son_reply = reviewItem.getSon_reply();
                        if (son_reply == null || son_reply.isEmpty()) {
                            reviewItem.setItemType(1);
                        } else {
                            reviewItem.setItemType(3);
                        }
                    }
                }
                List<ReviewItem> list2 = it.getList();
                if (list2 == null) {
                    list2 = new ArrayList<>();
                }
                arrayList.addAll(list2);
                baseQuickAdapter3 = this.this$0.mAdapter;
                baseQuickAdapter3.setList(arrayList);
                recyclerView = this.this$0.mRecyclerView;
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager == null) {
                    throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
                }
                ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(indexedValue.getIndex(), 0);
                return;
            }
        }
    }
}
