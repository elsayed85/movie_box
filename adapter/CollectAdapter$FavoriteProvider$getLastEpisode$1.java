package com.movieboxpro.android.adapter;

import com.chad.library.adapter.base.BaseProviderMultiAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.movieboxpro.android.adapter.CollectAdapter;
import com.movieboxpro.android.model.FavoriteEpisodeItem;
import com.movieboxpro.android.model.FavoriteItem;
import com.movieboxpro.android.model.FavoriteSeasonItem;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* compiled from: CollectAdapter.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/movieboxpro/android/model/FavoriteItem;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class CollectAdapter$FavoriteProvider$getLastEpisode$1 extends Lambda implements Function1<FavoriteItem, Unit> {
    final /* synthetic */ FavoriteItem $favoriteItem;
    final /* synthetic */ int $position;
    final /* synthetic */ CollectAdapter.FavoriteProvider this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CollectAdapter$FavoriteProvider$getLastEpisode$1(FavoriteItem favoriteItem, CollectAdapter.FavoriteProvider favoriteProvider, int i) {
        super(1);
        this.$favoriteItem = favoriteItem;
        this.this$0 = favoriteProvider;
        this.$position = i;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(FavoriteItem favoriteItem) {
        invoke2(favoriteItem);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(FavoriteItem it) {
        FavoriteSeasonItem favoriteSeasonItem;
        List<FavoriteEpisodeItem> episode_list;
        Intrinsics.checkNotNullParameter(it, "it");
        List<FavoriteSeasonItem> episode_progress_list = this.$favoriteItem.getEpisode_progress_list();
        FavoriteEpisodeItem favoriteEpisodeItem = null;
        if (episode_progress_list != null && (favoriteSeasonItem = (FavoriteSeasonItem) CollectionsKt.lastOrNull((List<? extends Object>) episode_progress_list)) != null && (episode_list = favoriteSeasonItem.getEpisode_list()) != null) {
            favoriteEpisodeItem = (FavoriteEpisodeItem) CollectionsKt.lastOrNull((List<? extends Object>) episode_list);
        }
        boolean z = false;
        if (favoriteEpisodeItem != null && favoriteEpisodeItem.getOver() == 1) {
            z = true;
        }
        if (z) {
            if (this.$favoriteItem.getLast_episode() == null) {
                this.$favoriteItem.setLast_episode(new FavoriteItem.LastEpisode());
            }
            this.$favoriteItem.getLast_episode().setEpisode(favoriteEpisodeItem.getEpisode());
            this.$favoriteItem.getLast_episode().setSeason(favoriteEpisodeItem.getSeason());
            this.$favoriteItem.getLast_episode().setTitle(favoriteEpisodeItem.getTitle());
            this.$favoriteItem.getLast_episode().setThumbs(favoriteEpisodeItem.getThumbs());
            this.$favoriteItem.getLast_episode().setRuntime(favoriteEpisodeItem.getRuntime());
            this.$favoriteItem.getLast_episode().setSeconds(favoriteEpisodeItem.getSeconds());
            BaseProviderMultiAdapter<BaseNode> adapter = this.this$0.getAdapter2();
            if (adapter == null) {
                return;
            }
            adapter.notifyItemChanged(this.$position);
            return;
        }
        this.$favoriteItem.setLast_episode(it.getLast_episode());
        BaseProviderMultiAdapter<BaseNode> adapter2 = this.this$0.getAdapter2();
        if (adapter2 == null) {
            return;
        }
        adapter2.notifyItemChanged(this.$position);
    }
}
