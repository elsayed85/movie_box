package com.movieboxpro.android.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.model.tv.TvDetail;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: TvEpisodeAdapter.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0002H\u0015¨\u0006\u000b"}, d2 = {"Lcom/movieboxpro/android/adapter/TvEpisodeAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/movieboxpro/android/model/tv/TvDetail$SeasonDetail;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "list", "", "(Ljava/util/List;)V", "convert", "", "helper", "item", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TvEpisodeAdapter extends BaseQuickAdapter<TvDetail.SeasonDetail, BaseViewHolder> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TvEpisodeAdapter(List<TvDetail.SeasonDetail> list) {
        super(R.layout.layout_seasson_item, list);
        Intrinsics.checkNotNullParameter(list, "list");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0309  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x034c  */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void convert(com.chad.library.adapter.base.viewholder.BaseViewHolder r22, com.movieboxpro.android.model.tv.TvDetail.SeasonDetail r23) {
        /*
            Method dump skipped, instructions count: 969
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.adapter.TvEpisodeAdapter.convert(com.chad.library.adapter.base.viewholder.BaseViewHolder, com.movieboxpro.android.model.tv.TvDetail$SeasonDetail):void");
    }
}
