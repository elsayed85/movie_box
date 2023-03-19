package com.movieboxpro.android.adapter;

import com.movieboxpro.android.adapter.SeasonEpisodeAdapter;
import com.movieboxpro.android.model.FavoriteItem;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SeasonEpisodeAdapter.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SeasonEpisodeAdapter$TotalSeasonProvider$markWatched$3 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ Ref.ObjectRef<Integer> $endEpisode;
    final /* synthetic */ Ref.ObjectRef<Integer> $endSeason;
    final /* synthetic */ int $episode;
    final /* synthetic */ Ref.ObjectRef<Integer> $episodeProgress;
    final /* synthetic */ FavoriteItem $favoriteItem;
    final /* synthetic */ String $id;
    final /* synthetic */ int $over;
    final /* synthetic */ int $position;
    final /* synthetic */ int $season;
    final /* synthetic */ Ref.ObjectRef<Integer> $seasonProgress;
    final /* synthetic */ int $tvPos;
    final /* synthetic */ SeasonEpisodeAdapter.TotalSeasonProvider this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SeasonEpisodeAdapter$TotalSeasonProvider$markWatched$3(SeasonEpisodeAdapter.TotalSeasonProvider totalSeasonProvider, int i, FavoriteItem favoriteItem, int i2, Ref.ObjectRef<Integer> objectRef, Ref.ObjectRef<Integer> objectRef2, Ref.ObjectRef<Integer> objectRef3, int i3, Ref.ObjectRef<Integer> objectRef4, int i4, int i5, String str) {
        super(1);
        this.this$0 = totalSeasonProvider;
        this.$over = i;
        this.$favoriteItem = favoriteItem;
        this.$tvPos = i2;
        this.$seasonProgress = objectRef;
        this.$episodeProgress = objectRef2;
        this.$endSeason = objectRef3;
        this.$season = i3;
        this.$endEpisode = objectRef4;
        this.$episode = i4;
        this.$position = i5;
        this.$id = str;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(String str) {
        invoke2(str);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:84:0x01a6, code lost:
        if (r1.intValue() != r11) goto L101;
     */
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void invoke2(java.lang.String r18) {
        /*
            Method dump skipped, instructions count: 2218
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.adapter.SeasonEpisodeAdapter$TotalSeasonProvider$markWatched$3.invoke2(java.lang.String):void");
    }
}
