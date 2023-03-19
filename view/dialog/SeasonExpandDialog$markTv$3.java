package com.movieboxpro.android.view.dialog;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.movieboxpro.android.adapter.SeasonEpisodeAdapter;
import com.movieboxpro.android.model.FavoriteEpisodeItem;
import com.movieboxpro.android.model.FavoriteItem;
import com.movieboxpro.android.model.FavoriteSeasonItem;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.dialog.SeasonExpandDialog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SeasonExpandDialog.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SeasonExpandDialog$markTv$3 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ String $episode;
    final /* synthetic */ FavoriteEpisodeItem $favoriteEpisodeItem;
    final /* synthetic */ FavoriteSeasonItem $favoriteSeasonItem;
    final /* synthetic */ String $id;
    final /* synthetic */ int $over;
    final /* synthetic */ int $position;
    final /* synthetic */ String $season;
    final /* synthetic */ SeasonExpandDialog this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SeasonExpandDialog$markTv$3(SeasonExpandDialog seasonExpandDialog, String str, String str2, String str3, int i, int i2, FavoriteSeasonItem favoriteSeasonItem, FavoriteEpisodeItem favoriteEpisodeItem) {
        super(1);
        this.this$0 = seasonExpandDialog;
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
        SeasonExpandDialog.OnMarkListener onMarkListener;
        FavoriteSeasonItem favoriteSeasonItem;
        int i;
        SeasonEpisodeAdapter seasonEpisodeAdapter;
        SeasonEpisodeAdapter seasonEpisodeAdapter2;
        SeasonEpisodeAdapter seasonEpisodeAdapter3;
        int i2;
        int i3;
        SeasonEpisodeAdapter seasonEpisodeAdapter4;
        boolean checkShowProgress;
        SeasonEpisodeAdapter seasonEpisodeAdapter5;
        SeasonEpisodeAdapter seasonEpisodeAdapter6;
        SeasonEpisodeAdapter seasonEpisodeAdapter7;
        boolean z;
        SeasonEpisodeAdapter seasonEpisodeAdapter8;
        boolean z2;
        FavoriteEpisodeItem favoriteEpisodeItem;
        SeasonEpisodeAdapter seasonEpisodeAdapter9;
        SeasonEpisodeAdapter seasonEpisodeAdapter10;
        SeasonEpisodeAdapter seasonEpisodeAdapter11;
        SeasonEpisodeAdapter seasonEpisodeAdapter12;
        int i4;
        boolean checkShowProgress2;
        SeasonEpisodeAdapter seasonEpisodeAdapter13;
        boolean z3;
        SeasonEpisodeAdapter seasonEpisodeAdapter14;
        int i5;
        SeasonEpisodeAdapter seasonEpisodeAdapter15;
        boolean z4;
        SeasonEpisodeAdapter seasonEpisodeAdapter16;
        Intrinsics.checkNotNullParameter(it, "it");
        this.this$0.hideLoadingView();
        ToastUtils.showShort("Marked", new Object[0]);
        onMarkListener = this.this$0.listener;
        if (onMarkListener != null) {
            onMarkListener.onMark(this.$id, this.$season, this.$episode, this.$over, this.$position, this.$favoriteSeasonItem, this.$favoriteEpisodeItem);
            Unit unit = Unit.INSTANCE;
        }
        FavoriteSeasonItem favoriteSeasonItem2 = this.$favoriteSeasonItem;
        if (favoriteSeasonItem2 != null) {
            SeasonExpandDialog seasonExpandDialog = this.this$0;
            int i6 = this.$position;
            int i7 = this.$over;
            favoriteSeasonItem2.getWatchList().clear();
            List<FavoriteEpisodeItem> episode_list = favoriteSeasonItem2.getEpisode_list();
            Intrinsics.checkNotNullExpressionValue(episode_list, "it.episode_list");
            for (FavoriteEpisodeItem favoriteEpisodeItem2 : episode_list) {
                favoriteSeasonItem2.getWatchList().add(Integer.valueOf(i7));
            }
            seasonEpisodeAdapter16 = seasonExpandDialog.adapter;
            if (seasonEpisodeAdapter16 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                seasonEpisodeAdapter16 = null;
            }
            seasonEpisodeAdapter16.notifyItemChanged(i6);
            Unit unit2 = Unit.INSTANCE;
            Unit unit3 = Unit.INSTANCE;
        }
        if ((!StringsKt.isBlank(this.$episode)) && (favoriteEpisodeItem = this.$favoriteEpisodeItem) != null) {
            int i8 = this.$over;
            SeasonExpandDialog seasonExpandDialog2 = this.this$0;
            int i9 = this.$position;
            String str = this.$episode;
            favoriteEpisodeItem.setOver(i8);
            seasonEpisodeAdapter9 = seasonExpandDialog2.adapter;
            if (seasonEpisodeAdapter9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                seasonEpisodeAdapter9 = null;
            }
            seasonEpisodeAdapter9.notifyItemChanged(i9);
            seasonEpisodeAdapter10 = seasonExpandDialog2.adapter;
            if (seasonEpisodeAdapter10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                seasonEpisodeAdapter10 = null;
            }
            Iterator<BaseNode> it2 = seasonEpisodeAdapter10.getData().iterator();
            int i10 = 0;
            while (true) {
                if (!it2.hasNext()) {
                    i10 = -1;
                    break;
                }
                BaseNode next = it2.next();
                if ((next instanceof FavoriteSeasonItem) && ((FavoriteSeasonItem) next).getSeason() == favoriteEpisodeItem.getSeason()) {
                    break;
                }
                i10++;
            }
            if (i10 != -1) {
                seasonEpisodeAdapter14 = seasonExpandDialog2.adapter;
                if (seasonEpisodeAdapter14 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    seasonEpisodeAdapter14 = null;
                }
                FavoriteSeasonItem favoriteSeasonItem3 = (FavoriteSeasonItem) seasonEpisodeAdapter14.getItem(i10);
                if (i8 == 1) {
                    favoriteSeasonItem3.setWatchedCount(favoriteSeasonItem3.getWatchedCount() + 1);
                } else {
                    favoriteSeasonItem3.setWatchedCount(favoriteSeasonItem3.getWatchedCount() - 1);
                }
                favoriteSeasonItem3.getWatchList().set(Integer.parseInt(str) - 1, Integer.valueOf(i8));
                List<FavoriteEpisodeItem> episode_list2 = favoriteSeasonItem3.getEpisode_list();
                Intrinsics.checkNotNullExpressionValue(episode_list2, "seasonItem.episode_list");
                ListIterator<FavoriteEpisodeItem> listIterator = episode_list2.listIterator(episode_list2.size());
                while (true) {
                    if (!listIterator.hasPrevious()) {
                        i5 = -1;
                        break;
                    }
                    if (listIterator.previous().getOver() == 1) {
                        z4 = true;
                        continue;
                    } else {
                        z4 = false;
                        continue;
                    }
                    if (z4) {
                        i5 = listIterator.nextIndex();
                        break;
                    }
                }
                if (i5 != -1) {
                    FavoriteEpisodeItem favoriteEpisodeItem3 = favoriteSeasonItem3.getEpisode_list().get(i5);
                    favoriteSeasonItem3.setLastStartWatchedItem(favoriteEpisodeItem3);
                    favoriteSeasonItem3.setLastWatchedItem(favoriteEpisodeItem3);
                    favoriteSeasonItem3.setLastWatchedIndex(i5);
                    if (i5 >= 0) {
                        while (true) {
                            int i11 = i5 - 1;
                            if (favoriteSeasonItem3.getEpisode_list().get(i5).getOver() != 1) {
                                break;
                            }
                            favoriteSeasonItem3.setLastStartWatchedItem(favoriteSeasonItem3.getEpisode_list().get(i5));
                            if (i11 < 0) {
                                break;
                            }
                            i5 = i11;
                        }
                    }
                } else {
                    favoriteSeasonItem3.setLastWatchedItem(null);
                    favoriteSeasonItem3.setLastStartWatchedItem(null);
                    favoriteSeasonItem3.setLastWatchedIndex(0);
                }
                seasonEpisodeAdapter15 = seasonExpandDialog2.adapter;
                if (seasonEpisodeAdapter15 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    seasonEpisodeAdapter15 = null;
                }
                seasonEpisodeAdapter15.notifyItemChanged(i10);
            }
            seasonEpisodeAdapter11 = seasonExpandDialog2.adapter;
            if (seasonEpisodeAdapter11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                seasonEpisodeAdapter11 = null;
            }
            Iterator<BaseNode> it3 = seasonEpisodeAdapter11.getData().iterator();
            int i12 = 0;
            while (true) {
                if (!it3.hasNext()) {
                    i12 = -1;
                    break;
                }
                BaseNode next2 = it3.next();
                if (next2 instanceof FavoriteItem ? Intrinsics.areEqual(((FavoriteItem) next2).getId(), favoriteEpisodeItem.getId()) : false) {
                    break;
                }
                i12++;
            }
            if (i12 != -1) {
                seasonEpisodeAdapter12 = seasonExpandDialog2.adapter;
                if (seasonEpisodeAdapter12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    seasonEpisodeAdapter12 = null;
                }
                FavoriteItem favoriteItem = (FavoriteItem) seasonEpisodeAdapter12.getItem(i12);
                ArrayList<Integer> arrayList = new ArrayList<>();
                favoriteItem.setWatchedEpisodeCount(0);
                List<FavoriteSeasonItem> episode_progress_list = favoriteItem.getEpisode_progress_list();
                Intrinsics.checkNotNullExpressionValue(episode_progress_list, "item.episode_progress_list");
                for (FavoriteSeasonItem favoriteSeasonItem4 : episode_progress_list) {
                    List<FavoriteEpisodeItem> episode_list3 = favoriteSeasonItem4.getEpisode_list();
                    Intrinsics.checkNotNullExpressionValue(episode_list3, "it.episode_list");
                    for (FavoriteEpisodeItem favoriteEpisodeItem4 : episode_list3) {
                        if (favoriteEpisodeItem4.getOver() == 1) {
                            favoriteItem.setWatchedEpisodeCount(favoriteItem.getWatchedEpisodeCount() + 1);
                            arrayList.add(1);
                        } else {
                            arrayList.add(0);
                        }
                    }
                }
                List<FavoriteEpisodeItem> allEpisodes = favoriteItem.getAllEpisodes();
                Intrinsics.checkNotNullExpressionValue(allEpisodes, "item.allEpisodes");
                ListIterator<FavoriteEpisodeItem> listIterator2 = allEpisodes.listIterator(allEpisodes.size());
                while (true) {
                    if (!listIterator2.hasPrevious()) {
                        i4 = -1;
                        break;
                    }
                    if (listIterator2.previous().getOver() == 1) {
                        z3 = true;
                        continue;
                    } else {
                        z3 = false;
                        continue;
                    }
                    if (z3) {
                        i4 = listIterator2.nextIndex();
                        break;
                    }
                }
                if (i4 != -1) {
                    favoriteItem.setLastWatchedCount(0);
                    FavoriteEpisodeItem favoriteEpisodeItem5 = favoriteItem.getAllEpisodes().get(i4);
                    favoriteItem.setLastStartWatchedItem(favoriteEpisodeItem5);
                    favoriteItem.setLastWatchedItem(favoriteEpisodeItem5);
                    favoriteItem.setLastWatchedIndex(i4);
                    List<FavoriteEpisodeItem> allEpisodes2 = favoriteItem.getAllEpisodes();
                    Intrinsics.checkNotNullExpressionValue(allEpisodes2, "item.allEpisodes");
                    for (FavoriteEpisodeItem favoriteEpisodeItem6 : allEpisodes2) {
                        if (favoriteEpisodeItem6.getSeason() < favoriteEpisodeItem5.getSeason()) {
                            favoriteItem.setLastWatchedCount(favoriteItem.getLastWatchedCount() + 1);
                        } else if (favoriteEpisodeItem6.getSeason() == favoriteEpisodeItem5.getSeason() && favoriteEpisodeItem6.getEpisode() <= favoriteEpisodeItem5.getEpisode()) {
                            favoriteItem.setLastWatchedCount(favoriteItem.getLastWatchedCount() + 1);
                        }
                    }
                    if (i4 >= 0) {
                        while (true) {
                            int i13 = i4 - 1;
                            if (favoriteItem.getAllEpisodes().get(i4).getOver() != 1) {
                                break;
                            }
                            favoriteItem.setLastStartWatchedItem(favoriteItem.getAllEpisodes().get(i4));
                            if (i13 < 0) {
                                break;
                            }
                            i4 = i13;
                        }
                    }
                } else {
                    favoriteItem.setWatchedEpisodeCount(0);
                    favoriteItem.setLastWatchedItem(null);
                    favoriteItem.setLastStartWatchedItem(null);
                    favoriteItem.setLastWatchedCount(0);
                }
                favoriteItem.setEpisodes(arrayList);
                checkShowProgress2 = seasonExpandDialog2.checkShowProgress(arrayList);
                favoriteItem.setShowProgress(checkShowProgress2);
                seasonEpisodeAdapter13 = seasonExpandDialog2.adapter;
                if (seasonEpisodeAdapter13 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    seasonEpisodeAdapter13 = null;
                }
                seasonEpisodeAdapter13.notifyItemChanged(i12);
            }
            Unit unit4 = Unit.INSTANCE;
            Unit unit5 = Unit.INSTANCE;
        }
        if (!(!StringsKt.isBlank(this.$season)) || (favoriteSeasonItem = this.$favoriteSeasonItem) == null) {
            return;
        }
        int i14 = this.$over;
        SeasonExpandDialog seasonExpandDialog3 = this.this$0;
        int i15 = this.$position;
        if (i14 == 1) {
            favoriteSeasonItem.setWatchedCount(favoriteSeasonItem.getEpisode_list().size());
        } else {
            favoriteSeasonItem.setWatchedCount(0);
        }
        List<FavoriteEpisodeItem> episode_list4 = favoriteSeasonItem.getEpisode_list();
        if (episode_list4 != null) {
            for (FavoriteEpisodeItem favoriteEpisodeItem7 : episode_list4) {
                favoriteEpisodeItem7.setOver(i14);
            }
            Unit unit6 = Unit.INSTANCE;
        }
        List<FavoriteEpisodeItem> episode_list5 = favoriteSeasonItem.getEpisode_list();
        Intrinsics.checkNotNullExpressionValue(episode_list5, "it.episode_list");
        ListIterator<FavoriteEpisodeItem> listIterator3 = episode_list5.listIterator(episode_list5.size());
        while (true) {
            if (!listIterator3.hasPrevious()) {
                i = -1;
                break;
            }
            if (listIterator3.previous().getOver() == 1) {
                z2 = true;
                continue;
            } else {
                z2 = false;
                continue;
            }
            if (z2) {
                i = listIterator3.nextIndex();
                break;
            }
        }
        if (i != -1) {
            FavoriteEpisodeItem favoriteEpisodeItem8 = favoriteSeasonItem.getEpisode_list().get(i);
            favoriteSeasonItem.setLastStartWatchedItem(favoriteEpisodeItem8);
            favoriteSeasonItem.setLastWatchedItem(favoriteEpisodeItem8);
            favoriteSeasonItem.setLastWatchedIndex(i);
            if (i >= 0) {
                while (true) {
                    int i16 = i - 1;
                    if (favoriteSeasonItem.getEpisode_list().get(i).getOver() != 1) {
                        break;
                    }
                    favoriteSeasonItem.setLastStartWatchedItem(favoriteSeasonItem.getEpisode_list().get(i));
                    if (i16 < 0) {
                        break;
                    }
                    i = i16;
                }
            }
        } else {
            favoriteSeasonItem.setLastWatchedItem(null);
            favoriteSeasonItem.setLastStartWatchedItem(null);
            favoriteSeasonItem.setLastWatchedIndex(0);
        }
        seasonEpisodeAdapter = seasonExpandDialog3.adapter;
        if (seasonEpisodeAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            seasonEpisodeAdapter = null;
        }
        int i17 = 0;
        for (Object obj : seasonEpisodeAdapter.getData()) {
            int i18 = i17 + 1;
            if (i17 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            BaseNode baseNode = (BaseNode) obj;
            if (baseNode instanceof FavoriteEpisodeItem) {
                FavoriteEpisodeItem favoriteEpisodeItem9 = (FavoriteEpisodeItem) baseNode;
                if (Intrinsics.areEqual(favoriteEpisodeItem9.getId(), favoriteSeasonItem.getId()) && favoriteEpisodeItem9.getSeason() == favoriteSeasonItem.getSeason()) {
                    favoriteEpisodeItem9.setOver(i14);
                    seasonEpisodeAdapter8 = seasonExpandDialog3.adapter;
                    if (seasonEpisodeAdapter8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        seasonEpisodeAdapter8 = null;
                    }
                    seasonEpisodeAdapter8.notifyItemChanged(i17);
                }
            }
            i17 = i18;
        }
        seasonEpisodeAdapter2 = seasonExpandDialog3.adapter;
        if (seasonEpisodeAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            seasonEpisodeAdapter2 = null;
        }
        int findParentNode = seasonEpisodeAdapter2.findParentNode(i15);
        seasonEpisodeAdapter3 = seasonExpandDialog3.adapter;
        if (seasonEpisodeAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            seasonEpisodeAdapter3 = null;
        }
        FavoriteItem favoriteItem2 = (FavoriteItem) seasonEpisodeAdapter3.getItem(findParentNode);
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        favoriteItem2.setWatchedEpisodeCount(0);
        List<FavoriteSeasonItem> episode_progress_list2 = favoriteItem2.getEpisode_progress_list();
        Intrinsics.checkNotNullExpressionValue(episode_progress_list2, "item.episode_progress_list");
        for (FavoriteSeasonItem favoriteSeasonItem5 : episode_progress_list2) {
            List<FavoriteEpisodeItem> episode_list6 = favoriteSeasonItem5.getEpisode_list();
            Intrinsics.checkNotNullExpressionValue(episode_list6, "it.episode_list");
            for (FavoriteEpisodeItem favoriteEpisodeItem10 : episode_list6) {
                if (favoriteEpisodeItem10.getOver() == 1) {
                    favoriteItem2.setWatchedEpisodeCount(favoriteItem2.getWatchedEpisodeCount() + 1);
                    arrayList2.add(1);
                } else {
                    arrayList2.add(0);
                }
            }
        }
        List<FavoriteEpisodeItem> allEpisodes3 = favoriteItem2.getAllEpisodes();
        Intrinsics.checkNotNullExpressionValue(allEpisodes3, "item.allEpisodes");
        ListIterator<FavoriteEpisodeItem> listIterator4 = allEpisodes3.listIterator(allEpisodes3.size());
        while (true) {
            if (!listIterator4.hasPrevious()) {
                i2 = -1;
                i3 = -1;
                break;
            }
            if (listIterator4.previous().getOver() == 1) {
                z = true;
                continue;
            } else {
                z = false;
                continue;
            }
            if (z) {
                i3 = listIterator4.nextIndex();
                i2 = -1;
                break;
            }
        }
        if (i3 != i2) {
            favoriteItem2.setLastWatchedCount(0);
            FavoriteEpisodeItem favoriteEpisodeItem11 = favoriteItem2.getAllEpisodes().get(i3);
            favoriteItem2.setLastStartWatchedItem(favoriteEpisodeItem11);
            favoriteItem2.setLastWatchedItem(favoriteEpisodeItem11);
            favoriteItem2.setLastWatchedIndex(i3);
            List<FavoriteEpisodeItem> allEpisodes4 = favoriteItem2.getAllEpisodes();
            Intrinsics.checkNotNullExpressionValue(allEpisodes4, "item.allEpisodes");
            for (FavoriteEpisodeItem favoriteEpisodeItem12 : allEpisodes4) {
                if (favoriteEpisodeItem12.getSeason() < favoriteEpisodeItem11.getSeason()) {
                    favoriteItem2.setLastWatchedCount(favoriteItem2.getLastWatchedCount() + 1);
                } else if (favoriteEpisodeItem12.getSeason() == favoriteEpisodeItem11.getSeason() && favoriteEpisodeItem12.getEpisode() <= favoriteEpisodeItem11.getEpisode()) {
                    favoriteItem2.setLastWatchedCount(favoriteItem2.getLastWatchedCount() + 1);
                }
            }
            if (i3 >= 0) {
                while (true) {
                    int i19 = i3 - 1;
                    if (favoriteItem2.getAllEpisodes().get(i3).getOver() != 1) {
                        break;
                    }
                    favoriteItem2.setLastStartWatchedItem(favoriteItem2.getAllEpisodes().get(i3));
                    if (i19 < 0) {
                        break;
                    }
                    i3 = i19;
                }
            }
            seasonEpisodeAdapter4 = null;
        } else {
            favoriteItem2.setWatchedEpisodeCount(0);
            seasonEpisodeAdapter4 = null;
            favoriteItem2.setLastWatchedItem(null);
            favoriteItem2.setLastStartWatchedItem(null);
            favoriteItem2.setLastWatchedCount(0);
        }
        favoriteItem2.setEpisodes(arrayList2);
        checkShowProgress = seasonExpandDialog3.checkShowProgress(arrayList2);
        favoriteItem2.setShowProgress(checkShowProgress);
        seasonEpisodeAdapter5 = seasonExpandDialog3.adapter;
        if (seasonEpisodeAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            seasonEpisodeAdapter5 = seasonEpisodeAdapter4;
        }
        seasonEpisodeAdapter5.notifyItemChanged(findParentNode);
        seasonEpisodeAdapter6 = seasonExpandDialog3.adapter;
        if (seasonEpisodeAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            seasonEpisodeAdapter7 = seasonEpisodeAdapter4;
        } else {
            seasonEpisodeAdapter7 = seasonEpisodeAdapter6;
        }
        seasonEpisodeAdapter7.notifyItemChanged(i15);
        Unit unit7 = Unit.INSTANCE;
        Unit unit8 = Unit.INSTANCE;
    }
}
