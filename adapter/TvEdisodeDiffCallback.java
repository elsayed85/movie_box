package com.movieboxpro.android.adapter;

import androidx.recyclerview.widget.DiffUtil;
import com.movieboxpro.android.model.DownloadInfo;
import com.movieboxpro.android.model.tv.TvDetail;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: TvEdisodeDiffCallback.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/adapter/TvEdisodeDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/movieboxpro/android/model/tv/TvDetail$SeasonDetail;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TvEdisodeDiffCallback extends DiffUtil.ItemCallback<TvDetail.SeasonDetail> {
    @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
    public boolean areItemsTheSame(TvDetail.SeasonDetail oldItem, TvDetail.SeasonDetail newItem) {
        Intrinsics.checkNotNullParameter(oldItem, "oldItem");
        Intrinsics.checkNotNullParameter(newItem, "newItem");
        return Intrinsics.areEqual(oldItem.id, newItem.id) && Intrinsics.areEqual(oldItem.tid, newItem.tid);
    }

    @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
    public boolean areContentsTheSame(TvDetail.SeasonDetail oldItem, TvDetail.SeasonDetail newItem) {
        Intrinsics.checkNotNullParameter(oldItem, "oldItem");
        Intrinsics.checkNotNullParameter(newItem, "newItem");
        return (newItem.play_progress == null || oldItem.play_progress == null) ? oldItem.episode == newItem.episode && Intrinsics.areEqual(oldItem.title, newItem.title) && Intrinsics.areEqual(oldItem.released, newItem.released) && Intrinsics.areEqual(oldItem.imdb_rating, newItem.imdb_rating) && Intrinsics.areEqual(oldItem.runtime, newItem.runtime) && Intrinsics.areEqual(oldItem.synopsis, newItem.synopsis) && Intrinsics.areEqual(oldItem.thumbs, newItem.thumbs) && Intrinsics.areEqual(oldItem.quality_tag, newItem.quality_tag) && oldItem.released_timestamp == newItem.released_timestamp : Intrinsics.areEqual(oldItem.play_progress.get("seconds"), newItem.play_progress.get("seconds")) && Intrinsics.areEqual(oldItem.play_progress.get("last_time"), newItem.play_progress.get("last_time")) && Intrinsics.areEqual(oldItem.play_progress.get(DownloadInfo.DOWNLOAD_OVER), newItem.play_progress.get(DownloadInfo.DOWNLOAD_OVER)) && oldItem.episode == newItem.episode && Intrinsics.areEqual(oldItem.title, newItem.title) && Intrinsics.areEqual(oldItem.released, newItem.released) && Intrinsics.areEqual(oldItem.imdb_rating, newItem.imdb_rating) && Intrinsics.areEqual(oldItem.runtime, newItem.runtime) && Intrinsics.areEqual(oldItem.synopsis, newItem.synopsis) && Intrinsics.areEqual(oldItem.thumbs, newItem.thumbs) && Intrinsics.areEqual(oldItem.quality_tag, newItem.quality_tag) && oldItem.released_timestamp == newItem.released_timestamp;
    }
}
