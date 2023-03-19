package com.movieboxpro.android.view.fragment;

import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.movieboxpro.android.model.FavoriteItem;
import com.movieboxpro.android.model.WatchingItem;
import com.movieboxpro.android.view.fragment.FavoriteFragment2;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FavoriteFragment2.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FavoriteFragment2$FavoriteListFragment$getWatchingList$4 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ FavoriteFragment2.FavoriteListFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FavoriteFragment2$FavoriteListFragment$getWatchingList$4(FavoriteFragment2.FavoriteListFragment favoriteListFragment) {
        super(1);
        this.this$0 = favoriteListFragment;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(String str) {
        invoke2(str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(String str) {
        BaseNodeAdapter baseNodeAdapter;
        WatchingItem watchingItem;
        BaseNodeAdapter baseNodeAdapter2;
        WatchingItem watchingItem2;
        BaseNodeAdapter baseNodeAdapter3;
        BaseNodeAdapter baseNodeAdapter4;
        baseNodeAdapter = this.this$0.mAdapter;
        BaseNode baseNode = (BaseNode) CollectionsKt.firstOrNull((List<? extends Object>) baseNodeAdapter.getData());
        if (baseNode instanceof FavoriteItem) {
            FavoriteItem favoriteItem = (FavoriteItem) baseNode;
            if (favoriteItem.getWatchingItem() != null) {
                baseNodeAdapter4 = this.this$0.mAdapter;
                baseNodeAdapter4.notifyItemChanged(0);
                return;
            }
            watchingItem2 = this.this$0.watchingItem;
            favoriteItem.setWatchingItem(watchingItem2);
            baseNodeAdapter3 = this.this$0.mAdapter;
            baseNodeAdapter3.notifyItemChanged(0);
            return;
        }
        FavoriteItem favoriteItem2 = new FavoriteItem();
        watchingItem = this.this$0.watchingItem;
        favoriteItem2.setWatchingItem(watchingItem);
        baseNodeAdapter2 = this.this$0.mAdapter;
        baseNodeAdapter2.addData(0, (BaseNode) favoriteItem2);
    }
}
