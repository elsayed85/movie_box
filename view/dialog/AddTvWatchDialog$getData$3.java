package com.movieboxpro.android.view.dialog;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.SeasonEpisodeAdapter;
import com.movieboxpro.android.databinding.AddTvWatchPlanPopLayoutBinding;
import com.movieboxpro.android.model.FavoriteEpisodeItem;
import com.movieboxpro.android.model.FavoriteItem;
import com.movieboxpro.android.model.FavoriteSeasonItem;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AddTvWatchDialog.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "item", "Lcom/movieboxpro/android/model/FavoriteItem;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AddTvWatchDialog$getData$3 extends Lambda implements Function1<FavoriteItem, Unit> {
    final /* synthetic */ AddTvWatchDialog this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddTvWatchDialog$getData$3(AddTvWatchDialog addTvWatchDialog) {
        super(1);
        this.this$0 = addTvWatchDialog;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(FavoriteItem favoriteItem) {
        invoke2(favoriteItem);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(FavoriteItem item) {
        String tid;
        AddTvWatchPlanPopLayoutBinding addTvWatchPlanPopLayoutBinding;
        int i;
        AddTvWatchPlanPopLayoutBinding addTvWatchPlanPopLayoutBinding2;
        AddTvWatchPlanPopLayoutBinding addTvWatchPlanPopLayoutBinding3;
        SeasonEpisodeAdapter seasonEpisodeAdapter;
        SeasonEpisodeAdapter seasonEpisodeAdapter2;
        SeasonEpisodeAdapter seasonEpisodeAdapter3;
        SeasonEpisodeAdapter seasonEpisodeAdapter4;
        SeasonEpisodeAdapter seasonEpisodeAdapter5;
        SeasonEpisodeAdapter seasonEpisodeAdapter6;
        boolean z;
        int i2;
        boolean z2;
        Intrinsics.checkNotNullParameter(item, "item");
        tid = this.this$0.getTid();
        item.setId(tid);
        addTvWatchPlanPopLayoutBinding = this.this$0.binding;
        if (addTvWatchPlanPopLayoutBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            addTvWatchPlanPopLayoutBinding = null;
        }
        addTvWatchPlanPopLayoutBinding.loadingView.hideLoading();
        ArrayList<Integer> arrayList = new ArrayList<>();
        ArrayList<FavoriteEpisodeItem> arrayList2 = new ArrayList();
        List<FavoriteSeasonItem> episode_progress_list = item.getEpisode_progress_list();
        int i3 = -1;
        if (episode_progress_list != null) {
            int i4 = 0;
            for (Object obj : episode_progress_list) {
                int i5 = i4 + 1;
                if (i4 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                FavoriteSeasonItem favoriteSeasonItem = (FavoriteSeasonItem) obj;
                List<FavoriteEpisodeItem> episode_list = favoriteSeasonItem.getEpisode_list();
                Intrinsics.checkNotNullExpressionValue(episode_list, "season.episode_list");
                ListIterator<FavoriteEpisodeItem> listIterator = episode_list.listIterator(episode_list.size());
                while (true) {
                    if (!listIterator.hasPrevious()) {
                        i2 = -1;
                        break;
                    }
                    if (listIterator.previous().getOver() == 1) {
                        z2 = true;
                        continue;
                    } else {
                        z2 = false;
                        continue;
                    }
                    if (z2) {
                        i2 = listIterator.nextIndex();
                        break;
                    }
                }
                if (i2 != i3) {
                    FavoriteEpisodeItem favoriteEpisodeItem = favoriteSeasonItem.getEpisode_list().get(i2);
                    favoriteSeasonItem.setLastStartWatchedItem(favoriteEpisodeItem);
                    favoriteSeasonItem.setLastWatchedItem(favoriteEpisodeItem);
                    favoriteSeasonItem.setLastWatchedIndex(i2);
                    if (i2 >= 0) {
                        while (true) {
                            int i6 = i2 - 1;
                            if (favoriteSeasonItem.getEpisode_list().get(i2).getOver() != 1) {
                                break;
                            }
                            favoriteSeasonItem.setLastStartWatchedItem(favoriteSeasonItem.getEpisode_list().get(i2));
                            if (i6 < 0) {
                                break;
                            }
                            i2 = i6;
                        }
                    }
                }
                favoriteSeasonItem.setId(item.getId());
                favoriteSeasonItem.setExpanded(false);
                List<FavoriteEpisodeItem> episode_list2 = favoriteSeasonItem.getEpisode_list();
                if (episode_list2 == null) {
                    episode_list2 = new ArrayList<>();
                }
                favoriteSeasonItem.setEpisodes(new ArrayList(episode_list2));
                favoriteSeasonItem.setWatchList(new ArrayList());
                List<FavoriteEpisodeItem> episode_list3 = favoriteSeasonItem.getEpisode_list();
                if (episode_list3 == null) {
                    episode_list3 = new ArrayList<>();
                }
                arrayList2.addAll(episode_list3);
                List<FavoriteEpisodeItem> episode_list4 = favoriteSeasonItem.getEpisode_list();
                if (episode_list4 != null) {
                    int i7 = 0;
                    for (Object obj2 : episode_list4) {
                        int i8 = i7 + 1;
                        if (i7 < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        FavoriteEpisodeItem favoriteEpisodeItem2 = (FavoriteEpisodeItem) obj2;
                        favoriteEpisodeItem2.setSeason(favoriteSeasonItem.getSeason());
                        favoriteEpisodeItem2.setId(item.getId());
                        if (favoriteEpisodeItem2.getOver() == 1) {
                            favoriteSeasonItem.setWatchedCount(favoriteSeasonItem.getWatchedCount() + 1);
                            arrayList.add(1);
                            favoriteSeasonItem.getWatchList().add(1);
                        } else {
                            arrayList.add(0);
                            favoriteSeasonItem.getWatchList().add(0);
                        }
                        if (i4 == item.getEpisode_progress_list().size() - 1) {
                            favoriteEpisodeItem2.setLastSeason(true);
                        }
                        if (i7 == favoriteSeasonItem.getEpisode_list().size() - 1) {
                            favoriteEpisodeItem2.setLastItem(true);
                        }
                        i7 = i8;
                    }
                }
                item.setTotalEpisode(item.getTotalEpisode() + favoriteSeasonItem.getEpisode_list().size());
                item.setWatchedEpisodeCount(item.getWatchedEpisodeCount() + favoriteSeasonItem.getWatchedCount());
                i4 = i5;
                i3 = -1;
            }
        }
        ArrayList arrayList3 = arrayList2;
        ListIterator<FavoriteEpisodeItem> listIterator2 = arrayList3.listIterator(arrayList3.size());
        while (true) {
            if (!listIterator2.hasPrevious()) {
                i = -1;
                break;
            }
            if (listIterator2.previous().getOver() == 1) {
                z = true;
                continue;
            } else {
                z = false;
                continue;
            }
            if (z) {
                i = listIterator2.nextIndex();
                break;
            }
        }
        if (i != -1) {
            Object obj3 = arrayList2.get(i);
            Intrinsics.checkNotNullExpressionValue(obj3, "allEpisodes[lastIndex]");
            FavoriteEpisodeItem favoriteEpisodeItem3 = (FavoriteEpisodeItem) obj3;
            item.setLastStartWatchedItem(favoriteEpisodeItem3);
            item.setLastWatchedItem(favoriteEpisodeItem3);
            item.setLastWatchedIndex(i);
            for (FavoriteEpisodeItem favoriteEpisodeItem4 : arrayList2) {
                if (favoriteEpisodeItem4.getSeason() < favoriteEpisodeItem3.getSeason()) {
                    item.setLastWatchedCount(item.getLastWatchedCount() + 1);
                } else if (favoriteEpisodeItem4.getSeason() == favoriteEpisodeItem3.getSeason() && favoriteEpisodeItem4.getEpisode() <= favoriteEpisodeItem3.getEpisode()) {
                    item.setLastWatchedCount(item.getLastWatchedCount() + 1);
                }
            }
            if (i >= 0) {
                while (true) {
                    int i9 = i - 1;
                    if (((FavoriteEpisodeItem) arrayList2.get(i)).getOver() != 1) {
                        break;
                    }
                    item.setLastStartWatchedItem((FavoriteEpisodeItem) arrayList2.get(i));
                    if (i9 < 0) {
                        break;
                    }
                    i = i9;
                }
            }
        }
        item.setEpisodes(arrayList);
        item.setAllEpisodes(arrayList3);
        List<FavoriteSeasonItem> episode_progress_list2 = item.getEpisode_progress_list();
        if (episode_progress_list2 == null) {
            episode_progress_list2 = new ArrayList<>();
        }
        item.setSeasons(new ArrayList(episode_progress_list2));
        this.this$0.hideLoadingView();
        AddTvWatchDialog addTvWatchDialog = this.this$0;
        addTvWatchDialog.adapter = new SeasonEpisodeAdapter(addTvWatchDialog, addTvWatchDialog, null, 4, null);
        addTvWatchPlanPopLayoutBinding2 = this.this$0.binding;
        if (addTvWatchPlanPopLayoutBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            addTvWatchPlanPopLayoutBinding2 = null;
        }
        addTvWatchPlanPopLayoutBinding2.recyclerView.setLayoutManager(new LinearLayoutManager(this.this$0.getContext()));
        addTvWatchPlanPopLayoutBinding3 = this.this$0.binding;
        if (addTvWatchPlanPopLayoutBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            addTvWatchPlanPopLayoutBinding3 = null;
        }
        RecyclerView recyclerView = addTvWatchPlanPopLayoutBinding3.recyclerView;
        seasonEpisodeAdapter = this.this$0.adapter;
        if (seasonEpisodeAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            seasonEpisodeAdapter = null;
        }
        recyclerView.setAdapter(seasonEpisodeAdapter);
        item.setExpanded(false);
        seasonEpisodeAdapter2 = this.this$0.adapter;
        if (seasonEpisodeAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            seasonEpisodeAdapter2 = null;
        }
        seasonEpisodeAdapter2.setList(CollectionsKt.arrayListOf(item));
        seasonEpisodeAdapter3 = this.this$0.adapter;
        if (seasonEpisodeAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            seasonEpisodeAdapter3 = null;
        }
        seasonEpisodeAdapter3.addChildClickViewIds(R.id.ivExpand, R.id.ivMark);
        seasonEpisodeAdapter4 = this.this$0.adapter;
        if (seasonEpisodeAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            seasonEpisodeAdapter4 = null;
        }
        final AddTvWatchDialog addTvWatchDialog2 = this.this$0;
        seasonEpisodeAdapter4.setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$AddTvWatchDialog$getData$3$svUfw3U206acFIOaOt3zdVjFVec
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i10) {
                AddTvWatchDialog$getData$3.m930invoke$lambda5(AddTvWatchDialog.this, baseQuickAdapter, view, i10);
            }
        });
        seasonEpisodeAdapter5 = this.this$0.adapter;
        if (seasonEpisodeAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            seasonEpisodeAdapter6 = null;
        } else {
            seasonEpisodeAdapter6 = seasonEpisodeAdapter5;
        }
        final AddTvWatchDialog addTvWatchDialog3 = this.this$0;
        seasonEpisodeAdapter6.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$AddTvWatchDialog$getData$3$Ux5rldKJ0B_x2aywQ_hrw02lc8c
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i10) {
                AddTvWatchDialog$getData$3.m931invoke$lambda6(AddTvWatchDialog.this, baseQuickAdapter, view, i10);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: invoke$lambda-5  reason: not valid java name */
    public static final void m930invoke$lambda5(AddTvWatchDialog this$0, BaseQuickAdapter noName_0, View view, int i) {
        SeasonEpisodeAdapter seasonEpisodeAdapter;
        SeasonEpisodeAdapter seasonEpisodeAdapter2;
        SeasonEpisodeAdapter seasonEpisodeAdapter3;
        String tid;
        String tid2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(view, "view");
        seasonEpisodeAdapter = this$0.adapter;
        SeasonEpisodeAdapter seasonEpisodeAdapter4 = null;
        if (seasonEpisodeAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            seasonEpisodeAdapter = null;
        }
        BaseNode item = seasonEpisodeAdapter.getItem(i);
        int id = view.getId();
        if (id == R.id.ivExpand) {
            seasonEpisodeAdapter2 = this$0.adapter;
            if (seasonEpisodeAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                seasonEpisodeAdapter2 = null;
            }
            seasonEpisodeAdapter2.expandOrCollapse(i, true, true, 110);
            seasonEpisodeAdapter3 = this$0.adapter;
            if (seasonEpisodeAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                seasonEpisodeAdapter4 = seasonEpisodeAdapter3;
            }
            seasonEpisodeAdapter4.notifyItemChanged(i);
        } else if (id != R.id.ivMark) {
        } else {
            if (item instanceof FavoriteSeasonItem) {
                tid2 = this$0.getTid();
                FavoriteSeasonItem favoriteSeasonItem = (FavoriteSeasonItem) item;
                this$0.markTv(tid2, String.valueOf(favoriteSeasonItem.getSeason()), "", favoriteSeasonItem.getWatchedCount() == favoriteSeasonItem.getEpisode_list().size() ? 0 : 1, i, (r18 & 32) != 0 ? null : favoriteSeasonItem, (r18 & 64) != 0 ? null : null);
            } else if (item instanceof FavoriteEpisodeItem) {
                tid = this$0.getTid();
                FavoriteEpisodeItem favoriteEpisodeItem = (FavoriteEpisodeItem) item;
                this$0.markTv(tid, String.valueOf(favoriteEpisodeItem.getSeason()), String.valueOf(favoriteEpisodeItem.getEpisode()), favoriteEpisodeItem.getOver() == 1 ? 0 : 1, i, (r18 & 32) != 0 ? null : null, (r18 & 64) != 0 ? null : favoriteEpisodeItem);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: invoke$lambda-6  reason: not valid java name */
    public static final void m931invoke$lambda6(AddTvWatchDialog this$0, BaseQuickAdapter noName_0, View view, int i) {
        SeasonEpisodeAdapter seasonEpisodeAdapter;
        String tid;
        String tid2;
        SeasonEpisodeAdapter seasonEpisodeAdapter2;
        SeasonEpisodeAdapter seasonEpisodeAdapter3;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(view, "view");
        seasonEpisodeAdapter = this$0.adapter;
        SeasonEpisodeAdapter seasonEpisodeAdapter4 = null;
        if (seasonEpisodeAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            seasonEpisodeAdapter = null;
        }
        BaseNode item = seasonEpisodeAdapter.getItem(i);
        if (item instanceof FavoriteItem) {
            seasonEpisodeAdapter2 = this$0.adapter;
            if (seasonEpisodeAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                seasonEpisodeAdapter2 = null;
            }
            seasonEpisodeAdapter2.expandOrCollapse(i, true, true, 110);
            seasonEpisodeAdapter3 = this$0.adapter;
            if (seasonEpisodeAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                seasonEpisodeAdapter4 = seasonEpisodeAdapter3;
            }
            seasonEpisodeAdapter4.notifyItemChanged(i);
        } else if (item instanceof FavoriteSeasonItem) {
            tid2 = this$0.getTid();
            FavoriteSeasonItem favoriteSeasonItem = (FavoriteSeasonItem) item;
            this$0.markTv(tid2, String.valueOf(favoriteSeasonItem.getSeason()), "", favoriteSeasonItem.getWatchedCount() == favoriteSeasonItem.getEpisode_list().size() ? 0 : 1, i, (r18 & 32) != 0 ? null : favoriteSeasonItem, (r18 & 64) != 0 ? null : null);
        } else if (item instanceof FavoriteEpisodeItem) {
            tid = this$0.getTid();
            FavoriteEpisodeItem favoriteEpisodeItem = (FavoriteEpisodeItem) item;
            this$0.markTv(tid, String.valueOf(favoriteEpisodeItem.getSeason()), String.valueOf(favoriteEpisodeItem.getEpisode()), favoriteEpisodeItem.getOver() == 1 ? 0 : 1, i, (r18 & 32) != 0 ? null : null, (r18 & 64) != 0 ? null : favoriteEpisodeItem);
        }
    }
}
