package com.movieboxpro.android.view.fragment;

import com.chad.library.adapter.base.BaseNodeAdapter;
import com.movieboxpro.android.view.fragment.FavoriteFragment2;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FavoriteFragment2.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FavoriteFragment2$FavoriteListFragment$removeFavorite$3 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ int $position;
    final /* synthetic */ boolean $watching;
    final /* synthetic */ FavoriteFragment2.FavoriteListFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FavoriteFragment2$FavoriteListFragment$removeFavorite$3(FavoriteFragment2.FavoriteListFragment favoriteListFragment, boolean z, int i) {
        super(1);
        this.this$0 = favoriteListFragment;
        this.$watching = z;
        this.$position = i;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(String str) {
        invoke2(str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(String it) {
        BaseNodeAdapter baseNodeAdapter;
        BaseNodeAdapter baseNodeAdapter2;
        BaseNodeAdapter baseNodeAdapter3;
        BaseNodeAdapter baseNodeAdapter4;
        Intrinsics.checkNotNullParameter(it, "it");
        this.this$0.hideLoadingView();
        if (this.$watching) {
            return;
        }
        int i = this.$position;
        baseNodeAdapter = this.this$0.mAdapter;
        if (i == baseNodeAdapter.getData().size() - 1) {
            baseNodeAdapter3 = this.this$0.mAdapter;
            baseNodeAdapter3.getData().remove(this.$position);
            baseNodeAdapter4 = this.this$0.mAdapter;
            baseNodeAdapter4.notifyDataSetChanged();
            return;
        }
        baseNodeAdapter2 = this.this$0.mAdapter;
        baseNodeAdapter2.removeAt(this.$position);
    }
}
