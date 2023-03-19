package com.movieboxpro.android.adapter;

import androidx.recyclerview.widget.DiffUtil;
import com.movieboxpro.android.model.common.Collectlist;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: VideoDiffCallback.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u001a\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¨\u0006\u000b"}, d2 = {"Lcom/movieboxpro/android/adapter/VideoDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/movieboxpro/android/model/common/Collectlist;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "getChangePayload", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class VideoDiffCallback extends DiffUtil.ItemCallback<Collectlist> {
    @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
    public Object getChangePayload(Collectlist oldItem, Collectlist newItem) {
        Intrinsics.checkNotNullParameter(oldItem, "oldItem");
        Intrinsics.checkNotNullParameter(newItem, "newItem");
        return null;
    }

    @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
    public boolean areItemsTheSame(Collectlist oldItem, Collectlist newItem) {
        Intrinsics.checkNotNullParameter(oldItem, "oldItem");
        Intrinsics.checkNotNullParameter(newItem, "newItem");
        return Intrinsics.areEqual(oldItem.getCollect_id(), newItem.getCollect_id());
    }

    @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
    public boolean areContentsTheSame(Collectlist oldItem, Collectlist newItem) {
        Intrinsics.checkNotNullParameter(oldItem, "oldItem");
        Intrinsics.checkNotNullParameter(newItem, "newItem");
        return oldItem.box_type == 1 ? Intrinsics.areEqual(oldItem.id, newItem.id) && oldItem.box_type == newItem.box_type && Intrinsics.areEqual(oldItem.getImdb_rating(), newItem.getImdb_rating()) && Intrinsics.areEqual(oldItem.poster, newItem.poster) && Intrinsics.areEqual(oldItem.quality_tag_new, newItem.quality_tag_new) : Intrinsics.areEqual(oldItem.id, newItem.id) && oldItem.box_type == newItem.box_type && Intrinsics.areEqual(oldItem.getImdb_rating(), newItem.getImdb_rating()) && Intrinsics.areEqual(oldItem.poster, newItem.poster) && Intrinsics.areEqual(oldItem.quality_tag_new, newItem.quality_tag_new) && oldItem.updateCount == newItem.updateCount && Intrinsics.areEqual(oldItem.season_episode, newItem.season_episode) && Intrinsics.areEqual(oldItem.title, newItem.title);
    }
}
