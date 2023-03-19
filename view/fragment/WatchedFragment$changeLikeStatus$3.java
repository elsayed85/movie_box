package com.movieboxpro.android.view.fragment;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.movieboxpro.android.model.WatchedEpisode;
import com.movieboxpro.android.model.WatchedItem;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: WatchedFragment.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WatchedFragment$changeLikeStatus$3 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ int $boxType;
    final /* synthetic */ int $finalStatus;
    final /* synthetic */ boolean $like;
    final /* synthetic */ int $position;
    final /* synthetic */ Integer $season;
    final /* synthetic */ WatchedFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WatchedFragment$changeLikeStatus$3(WatchedFragment watchedFragment, int i, int i2, Integer num, int i3, boolean z) {
        super(1);
        this.this$0 = watchedFragment;
        this.$position = i;
        this.$boxType = i2;
        this.$season = num;
        this.$finalStatus = i3;
        this.$like = z;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(String str) {
        invoke2(str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(String it) {
        BaseQuickAdapter baseQuickAdapter;
        BaseQuickAdapter baseQuickAdapter2;
        Integer num;
        WatchedEpisode episode_info;
        BaseQuickAdapter baseQuickAdapter3;
        WatchedEpisode episode_info2;
        Intrinsics.checkNotNullParameter(it, "it");
        this.this$0.hideLoadingView();
        baseQuickAdapter = this.this$0.mAdapter;
        WatchedItem watchedItem = (WatchedItem) baseQuickAdapter.getItem(this.$position);
        if (this.$boxType == 2 && ((num = this.$season) == null || num.intValue() != 0)) {
            WatchedEpisode episode_info3 = watchedItem.getEpisode_info();
            if (episode_info3 != null) {
                episode_info3.setLike_status(this.$finalStatus);
            }
            if (this.$like) {
                int i = this.$finalStatus;
                if (i == 1) {
                    WatchedEpisode episode_info4 = watchedItem.getEpisode_info();
                    if (episode_info4 != null) {
                        WatchedEpisode episode_info5 = watchedItem.getEpisode_info();
                        episode_info4.setLike_total((episode_info5 != null ? episode_info5.getLike_total() : 0) + 1);
                    }
                } else if (i == 0 && (episode_info2 = watchedItem.getEpisode_info()) != null) {
                    WatchedEpisode episode_info6 = watchedItem.getEpisode_info();
                    episode_info2.setLike_total((episode_info6 != null ? episode_info6.getLike_total() : 0) - 1);
                }
            } else {
                int i2 = this.$finalStatus;
                if (i2 == 2) {
                    WatchedEpisode episode_info7 = watchedItem.getEpisode_info();
                    if (episode_info7 != null) {
                        WatchedEpisode episode_info8 = watchedItem.getEpisode_info();
                        episode_info7.setDislike_total((episode_info8 != null ? episode_info8.getDislike_total() : 0) + 1);
                    }
                } else if (i2 == 0 && (episode_info = watchedItem.getEpisode_info()) != null) {
                    WatchedEpisode episode_info9 = watchedItem.getEpisode_info();
                    episode_info.setDislike_total((episode_info9 != null ? episode_info9.getDislike_total() : 0) - 1);
                }
            }
            baseQuickAdapter3 = this.this$0.mAdapter;
            baseQuickAdapter3.notifyItemChanged(this.$position);
            return;
        }
        watchedItem.setLike_status(this.$finalStatus);
        if (this.$like) {
            int i3 = this.$finalStatus;
            if (i3 == 1) {
                watchedItem.setLike_total(watchedItem.getLike_total() + 1);
            } else if (i3 == 0) {
                watchedItem.setLike_total(watchedItem.getLike_total() - 1);
            }
        } else {
            int i4 = this.$finalStatus;
            if (i4 == 2) {
                watchedItem.setDislike_total(watchedItem.getDislike_total() + 1);
            } else if (i4 == 0) {
                watchedItem.setDislike_total(watchedItem.getDislike_total() - 1);
            }
        }
        baseQuickAdapter2 = this.this$0.mAdapter;
        baseQuickAdapter2.notifyItemChanged(this.$position);
    }
}
