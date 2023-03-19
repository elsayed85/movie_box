package com.movieboxpro.android.view.fragment;

import com.movieboxpro.android.model.FavoriteEpisodeItem;
import com.movieboxpro.android.model.FavoriteSeasonItem;
import com.movieboxpro.android.utils.ToastUtils;
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
public final class FavoriteFragment2$FavoriteListFragment$markTv$3 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ String $episode;
    final /* synthetic */ FavoriteEpisodeItem $favoriteEpisodeItem;
    final /* synthetic */ FavoriteSeasonItem $favoriteSeasonItem;
    final /* synthetic */ String $id;
    final /* synthetic */ int $over;
    final /* synthetic */ int $position;
    final /* synthetic */ String $season;
    final /* synthetic */ boolean $watching;
    final /* synthetic */ FavoriteFragment2.FavoriteListFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FavoriteFragment2$FavoriteListFragment$markTv$3(FavoriteFragment2.FavoriteListFragment favoriteListFragment, boolean z, String str, String str2, String str3, int i, int i2, FavoriteSeasonItem favoriteSeasonItem, FavoriteEpisodeItem favoriteEpisodeItem) {
        super(1);
        this.this$0 = favoriteListFragment;
        this.$watching = z;
        this.$id = str;
        this.$season = str2;
        this.$episode = str3;
        this.$over = i;
        this.$position = i2;
        this.$favoriteSeasonItem = favoriteSeasonItem;
        this.$favoriteEpisodeItem = favoriteEpisodeItem;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(String str) {
        invoke2(str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        this.this$0.hideLoadingView();
        ToastUtils.showShort("Marked", new Object[0]);
        if (this.$watching) {
            this.this$0.getWatchingList();
        } else {
            this.this$0.updateMarkStatus(this.$id, this.$season, this.$episode, this.$over, this.$position, this.$favoriteSeasonItem, this.$favoriteEpisodeItem);
        }
    }
}
