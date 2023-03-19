package com.movieboxpro.android.adapter;

import androidx.recyclerview.widget.DiffUtil;
import com.movieboxpro.android.model.common.Homelist;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: FeaturedDataDiffCallback.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u001a\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¨\u0006\u000b"}, d2 = {"Lcom/movieboxpro/android/adapter/FeaturedDataDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/movieboxpro/android/model/common/Homelist$Typelist;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "getChangePayload", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FeaturedDataDiffCallback extends DiffUtil.ItemCallback<Homelist.Typelist> {
    @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
    public Object getChangePayload(Homelist.Typelist oldItem, Homelist.Typelist newItem) {
        Intrinsics.checkNotNullParameter(oldItem, "oldItem");
        Intrinsics.checkNotNullParameter(newItem, "newItem");
        return null;
    }

    @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
    public boolean areItemsTheSame(Homelist.Typelist oldItem, Homelist.Typelist newItem) {
        Intrinsics.checkNotNullParameter(oldItem, "oldItem");
        Intrinsics.checkNotNullParameter(newItem, "newItem");
        int i = oldItem.viewType;
        if (i != 5) {
            if (i != 7) {
                if (i != 8) {
                    return Intrinsics.areEqual(oldItem.id, newItem.id) && oldItem.box_type == newItem.box_type;
                }
                return Intrinsics.areEqual(oldItem.id, newItem.id);
            }
            return Intrinsics.areEqual(oldItem.getLid(), newItem.getLid());
        }
        return Intrinsics.areEqual(oldItem.getActor_id(), newItem.getActor_id());
    }

    @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
    public boolean areContentsTheSame(Homelist.Typelist oldItem, Homelist.Typelist newItem) {
        Intrinsics.checkNotNullParameter(oldItem, "oldItem");
        Intrinsics.checkNotNullParameter(newItem, "newItem");
        int i = oldItem.viewType;
        if (i != 2) {
            if (i != 5) {
                if (i != 6) {
                    if (i == 7) {
                        List<String> imgArr = oldItem.getImgArr();
                        if (!(imgArr == null || imgArr.isEmpty())) {
                            List<String> imgArr2 = newItem.getImgArr();
                            if (!(imgArr2 == null || imgArr2.isEmpty())) {
                                if (!Intrinsics.areEqual(oldItem.getName(), newItem.getName()) || !Intrinsics.areEqual(oldItem.getCount(), newItem.getCount()) || !Intrinsics.areEqual(oldItem.getImgArr().get(0), newItem.getImgArr().get(0))) {
                                    return false;
                                }
                            }
                        }
                        if (!Intrinsics.areEqual(oldItem.getName(), newItem.getName()) || !Intrinsics.areEqual(oldItem.getCount(), newItem.getCount())) {
                            return false;
                        }
                    } else if (i == 8) {
                        if (!Intrinsics.areEqual(oldItem.poster, newItem.poster) || !Intrinsics.areEqual(oldItem.getUrl(), newItem.getUrl())) {
                            return false;
                        }
                    } else if (oldItem.box_type == 1) {
                        if (!Intrinsics.areEqual(oldItem.poster, newItem.poster) || !Intrinsics.areEqual(oldItem.quality_tag_new, newItem.quality_tag_new) || !Intrinsics.areEqual(oldItem.getImdb_rating(), newItem.getImdb_rating()) || !Intrinsics.areEqual(oldItem.title, newItem.title)) {
                            return false;
                        }
                    } else if (!Intrinsics.areEqual(oldItem.poster, newItem.poster) || !Intrinsics.areEqual(oldItem.update_title, newItem.update_title) || !Intrinsics.areEqual(oldItem.getImdb_rating(), newItem.getImdb_rating()) || !Intrinsics.areEqual(oldItem.season_episode, newItem.season_episode) || !Intrinsics.areEqual(oldItem.title, newItem.title)) {
                        return false;
                    }
                } else if (!Intrinsics.areEqual(oldItem.getName(), newItem.getName()) || !Intrinsics.areEqual(oldItem.getImg(), newItem.getImg())) {
                    return false;
                }
            } else if (!Intrinsics.areEqual(oldItem.getAvatar(), newItem.getAvatar()) || !Intrinsics.areEqual(oldItem.getName(), newItem.getName()) || !Intrinsics.areEqual(oldItem.getJob(), newItem.getJob())) {
                return false;
            }
        } else if (oldItem.box_type == 1) {
            if (!Intrinsics.areEqual(oldItem.poster, newItem.poster) || oldItem.getSeconds() != newItem.getSeconds() || oldItem.getRuntime() != newItem.getRuntime()) {
                return false;
            }
        } else if (oldItem.getHistory() != null) {
            if (!Intrinsics.areEqual(oldItem.poster, newItem.poster) || oldItem.getHistory().getSeconds() != newItem.getHistory().getSeconds() || oldItem.getHistory().getRuntime() != newItem.getHistory().getRuntime() || oldItem.getHistory().getSeason() != newItem.getHistory().getSeason() || oldItem.getHistory().getEpisode() != newItem.getHistory().getEpisode()) {
                return false;
            }
        } else {
            return Intrinsics.areEqual(oldItem.poster, newItem.poster);
        }
        return true;
    }
}
