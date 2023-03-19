package com.movieboxpro.android.view.widget;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.movieboxpro.android.adapter.CollectAdapter;
import com.movieboxpro.android.model.FavoriteEpisodeItem;
import com.movieboxpro.android.model.FavoriteItem;
import com.movieboxpro.android.model.FavoriteSeasonItem;
import com.movieboxpro.android.utils.CommonExtKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* compiled from: AddTvWatchPlanPopView.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "item", "Lcom/movieboxpro/android/model/FavoriteItem;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class AddTvWatchPlanPopView$onCreate$4 extends Lambda implements Function1<FavoriteItem, Unit> {
    final /* synthetic */ RecyclerView $recyclerView;
    final /* synthetic */ AddTvWatchPlanPopView this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddTvWatchPlanPopView$onCreate$4(AddTvWatchPlanPopView addTvWatchPlanPopView, RecyclerView recyclerView) {
        super(1);
        this.this$0 = addTvWatchPlanPopView;
        this.$recyclerView = recyclerView;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(FavoriteItem favoriteItem) {
        invoke2(favoriteItem);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(FavoriteItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        ArrayList<Integer> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        List<FavoriteSeasonItem> episode_progress_list = item.getEpisode_progress_list();
        if (episode_progress_list != null) {
            int i = 0;
            for (Object obj : episode_progress_list) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                FavoriteSeasonItem favoriteSeasonItem = (FavoriteSeasonItem) obj;
                favoriteSeasonItem.setId(item.getId());
                favoriteSeasonItem.setExpanded(false);
                List<FavoriteEpisodeItem> episode_list = favoriteSeasonItem.getEpisode_list();
                if (episode_list == null) {
                    episode_list = new ArrayList<>();
                }
                favoriteSeasonItem.setEpisodes(new ArrayList(episode_list));
                favoriteSeasonItem.setWatchList(new ArrayList());
                List<FavoriteEpisodeItem> episode_list2 = favoriteSeasonItem.getEpisode_list();
                if (episode_list2 == null) {
                    episode_list2 = new ArrayList<>();
                }
                arrayList2.addAll(episode_list2);
                List<FavoriteEpisodeItem> episode_list3 = favoriteSeasonItem.getEpisode_list();
                if (episode_list3 != null) {
                    int i3 = 0;
                    for (Object obj2 : episode_list3) {
                        int i4 = i3 + 1;
                        if (i3 < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        FavoriteEpisodeItem favoriteEpisodeItem = (FavoriteEpisodeItem) obj2;
                        favoriteEpisodeItem.setSeason(favoriteSeasonItem.getSeason());
                        favoriteEpisodeItem.setId(item.getId());
                        if (favoriteEpisodeItem.getOver() == 1) {
                            favoriteSeasonItem.setWatchedCount(favoriteSeasonItem.getWatchedCount() + 1);
                            arrayList.add(1);
                            favoriteSeasonItem.getWatchList().add(1);
                        } else {
                            arrayList.add(0);
                            favoriteSeasonItem.getWatchList().add(0);
                        }
                        if (i == item.getEpisode_progress_list().size() - 1) {
                            favoriteEpisodeItem.setLastSeason(true);
                        }
                        if (i3 == favoriteSeasonItem.getEpisode_list().size() - 1) {
                            favoriteEpisodeItem.setLastItem(true);
                        }
                        i3 = i4;
                    }
                }
                item.setTotalEpisode(item.getTotalEpisode() + favoriteSeasonItem.getEpisode_list().size());
                item.setWatchedEpisodeCount(item.getWatchedEpisodeCount() + favoriteSeasonItem.getWatchedCount());
                i = i2;
            }
        }
        item.setEpisodes(arrayList);
        item.setAllEpisodes(arrayList2);
        List<FavoriteSeasonItem> episode_progress_list2 = item.getEpisode_progress_list();
        if (episode_progress_list2 == null) {
            episode_progress_list2 = new ArrayList<>();
        }
        item.setSeasons(new ArrayList(episode_progress_list2));
        this.this$0.getLoadView().hideLoadingView();
        CollectAdapter collectAdapter = new CollectAdapter(this.this$0.getLifecycleOwner(), this.this$0.getLoadView());
        RecyclerView recyclerView = this.$recyclerView;
        Intrinsics.checkNotNullExpressionValue(recyclerView, "recyclerView");
        CommonExtKt.visible(recyclerView);
        this.$recyclerView.setLayoutManager(new LinearLayoutManager(this.this$0.getContext()));
        this.$recyclerView.setAdapter(collectAdapter);
        collectAdapter.setList(item.getEpisode_progress_list());
    }
}
