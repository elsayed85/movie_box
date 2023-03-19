package com.movieboxpro.android.adapter;

import com.movieboxpro.android.adapter.CollectAdapter;
import com.movieboxpro.android.model.FavoriteSeasonItem;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CollectAdapter.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CollectAdapter$FavoriteSeasonProvider$markSeason$3 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ Ref.ObjectRef<Integer> $endEpisode;
    final /* synthetic */ Ref.ObjectRef<Integer> $endSeason;
    final /* synthetic */ int $episode;
    final /* synthetic */ Ref.ObjectRef<Integer> $episodeProgress;
    final /* synthetic */ int $over;
    final /* synthetic */ int $position;
    final /* synthetic */ int $season;
    final /* synthetic */ FavoriteSeasonItem $seasonItem;
    final /* synthetic */ int $seasonPos;
    final /* synthetic */ Ref.ObjectRef<Integer> $seasonProgress;
    final /* synthetic */ CollectAdapter.FavoriteSeasonProvider this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CollectAdapter$FavoriteSeasonProvider$markSeason$3(CollectAdapter.FavoriteSeasonProvider favoriteSeasonProvider, int i, FavoriteSeasonItem favoriteSeasonItem, int i2, Ref.ObjectRef<Integer> objectRef, Ref.ObjectRef<Integer> objectRef2, Ref.ObjectRef<Integer> objectRef3, int i3, Ref.ObjectRef<Integer> objectRef4, int i4, int i5) {
        super(1);
        this.this$0 = favoriteSeasonProvider;
        this.$over = i;
        this.$seasonItem = favoriteSeasonItem;
        this.$seasonPos = i2;
        this.$seasonProgress = objectRef;
        this.$episodeProgress = objectRef2;
        this.$endSeason = objectRef3;
        this.$season = i3;
        this.$endEpisode = objectRef4;
        this.$episode = i4;
        this.$position = i5;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(String str) {
        invoke2(str);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:131:0x02f7, code lost:
        if (r1.intValue() != r7) goto L160;
     */
    /* JADX WARN: Type inference failed for: r1v109, types: [com.chad.library.adapter.base.BaseNodeAdapter] */
    /* JADX WARN: Type inference failed for: r1v19, types: [com.chad.library.adapter.base.BaseNodeAdapter] */
    /* JADX WARN: Type inference failed for: r1v57, types: [com.chad.library.adapter.base.BaseNodeAdapter] */
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void invoke2(java.lang.String r18) {
        /*
            Method dump skipped, instructions count: 2108
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.adapter.CollectAdapter$FavoriteSeasonProvider$markSeason$3.invoke2(java.lang.String):void");
    }
}
