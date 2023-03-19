package com.movieboxpro.android.view.fragment;

import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.fragment.FavoriteFragment2;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FavoriteFragment2.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012&\u0010\u0002\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004 \u0005*\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "Lkotlin/Pair;", "Lcom/movieboxpro/android/model/BaseMediaModel;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FavoriteFragment2$FavoriteListFragment$getPlayPath$7 extends Lambda implements Function1<Pair<? extends BaseMediaModel, ? extends BaseMediaModel>, Unit> {
    final /* synthetic */ Ref.IntRef $position;
    final /* synthetic */ FavoriteFragment2.FavoriteListFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FavoriteFragment2$FavoriteListFragment$getPlayPath$7(FavoriteFragment2.FavoriteListFragment favoriteListFragment, Ref.IntRef intRef) {
        super(1);
        this.this$0 = favoriteListFragment;
        this.$position = intRef;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Pair<? extends BaseMediaModel, ? extends BaseMediaModel> pair) {
        invoke2(pair);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(Pair<? extends BaseMediaModel, ? extends BaseMediaModel> pair) {
        boolean z = true;
        if (pair.getFirst() instanceof TvDetail) {
            TvDetail tvDetail = (TvDetail) pair.getFirst();
            tvDetail.addDonwload(pair.getSecond());
            List<BaseMediaModel.DownloadFile> list = tvDetail.list;
            if (list != null && !list.isEmpty()) {
                z = false;
            }
            if (!z) {
                TvDetail.SeasonDetail seasonDetail = null;
                Ref.IntRef intRef = this.$position;
                List<TvDetail.SeasonDetail> list2 = tvDetail.episode;
                Intrinsics.checkNotNullExpressionValue(list2, "tvDetail.episode");
                Iterator<T> it = list2.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    TvDetail.SeasonDetail seasonDetail2 = (TvDetail.SeasonDetail) it.next();
                    if (seasonDetail2.episode == intRef.element) {
                        seasonDetail = seasonDetail2;
                        break;
                    }
                }
                if (seasonDetail != null) {
                    tvDetail.seasonDetail = seasonDetail;
                    this.this$0.goTvPlayer(tvDetail);
                } else {
                    ToastUtils.showShort("Episode " + this.$position.element + ":no resource", new Object[0]);
                }
            } else {
                ToastUtils.showShort("NO RESOURCE", new Object[0]);
            }
        } else if (pair.getFirst() instanceof MovieDetail) {
            MovieDetail movieDetail = (MovieDetail) pair.getFirst();
            movieDetail.addDonwload(pair.getSecond());
            List<BaseMediaModel.DownloadFile> list3 = movieDetail.list;
            if (list3 != null && !list3.isEmpty()) {
                z = false;
            }
            if (!z) {
                this.this$0.goMoviePlayer(movieDetail);
            } else {
                ToastUtils.showShort("NO RESOURCE", new Object[0]);
            }
        }
        this.this$0.hideLoadingView();
    }
}
