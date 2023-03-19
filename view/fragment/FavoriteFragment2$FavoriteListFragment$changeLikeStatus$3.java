package com.movieboxpro.android.view.fragment;

import com.chad.library.adapter.base.BaseNodeAdapter;
import com.movieboxpro.android.model.FavoriteItem;
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
public final class FavoriteFragment2$FavoriteListFragment$changeLikeStatus$3 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ int $boxType;
    final /* synthetic */ int $finalStatus;
    final /* synthetic */ boolean $like;
    final /* synthetic */ int $position;
    final /* synthetic */ int $season;
    final /* synthetic */ FavoriteFragment2.FavoriteListFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FavoriteFragment2$FavoriteListFragment$changeLikeStatus$3(FavoriteFragment2.FavoriteListFragment favoriteListFragment, int i, int i2, int i3, int i4, boolean z) {
        super(1);
        this.this$0 = favoriteListFragment;
        this.$position = i;
        this.$boxType = i2;
        this.$season = i3;
        this.$finalStatus = i4;
        this.$like = z;
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
        Intrinsics.checkNotNullParameter(it, "it");
        this.this$0.hideLoadingView();
        baseNodeAdapter = this.this$0.mAdapter;
        FavoriteItem favoriteItem = (FavoriteItem) baseNodeAdapter.getItem(this.$position);
        if (this.$boxType == 2 && this.$season != 0) {
            favoriteItem.getLast_episode().setLike_status(this.$finalStatus);
            if (this.$like) {
                int i = this.$finalStatus;
                if (i == 1) {
                    favoriteItem.getLast_episode().setLike_total(favoriteItem.getLast_episode().getLike_total() + 1);
                } else if (i == 0) {
                    favoriteItem.getLast_episode().setLike_total(favoriteItem.getLast_episode().getLike_total() - 1);
                }
            } else {
                int i2 = this.$finalStatus;
                if (i2 == 2) {
                    favoriteItem.getLast_episode().setDislike_total(favoriteItem.getLast_episode().getDislike_total() + 1);
                } else if (i2 == 0) {
                    favoriteItem.getLast_episode().setDislike_total(favoriteItem.getLast_episode().getDislike_total() - 1);
                }
            }
            baseNodeAdapter3 = this.this$0.mAdapter;
            baseNodeAdapter3.notifyItemChanged(this.$position);
            return;
        }
        favoriteItem.setLike_status(this.$finalStatus);
        if (this.$like) {
            int i3 = this.$finalStatus;
            if (i3 == 1) {
                favoriteItem.setLike_total(favoriteItem.getLike_total() + 1);
            } else if (i3 == 0) {
                favoriteItem.setLike_total(favoriteItem.getLike_total() - 1);
            }
        } else {
            int i4 = this.$finalStatus;
            if (i4 == 2) {
                favoriteItem.setDislike_total(favoriteItem.getDislike_total() + 1);
            } else if (i4 == 0) {
                favoriteItem.setDislike_total(favoriteItem.getDislike_total() - 1);
            }
        }
        baseNodeAdapter2 = this.this$0.mAdapter;
        baseNodeAdapter2.notifyItemChanged(this.$position);
    }
}
