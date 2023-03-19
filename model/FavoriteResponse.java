package com.movieboxpro.android.model;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.beanutils.PropertyUtils;
/* compiled from: Bean.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u001c\b\u0002\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005¢\u0006\u0002\u0010\u0006J\u001d\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005HÆ\u0003J'\u0010\n\u001a\u00020\u00002\u001c\b\u0002\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R%\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/movieboxpro/android/model/FavoriteResponse;", "", "list", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/FavoriteItem;", "Lkotlin/collections/ArrayList;", "(Ljava/util/ArrayList;)V", "getList", "()Ljava/util/ArrayList;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FavoriteResponse {
    private final ArrayList<FavoriteItem> list;

    public FavoriteResponse() {
        this(null, 1, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FavoriteResponse copy$default(FavoriteResponse favoriteResponse, ArrayList arrayList, int i, Object obj) {
        if ((i & 1) != 0) {
            arrayList = favoriteResponse.list;
        }
        return favoriteResponse.copy(arrayList);
    }

    public final ArrayList<FavoriteItem> component1() {
        return this.list;
    }

    public final FavoriteResponse copy(ArrayList<FavoriteItem> arrayList) {
        return new FavoriteResponse(arrayList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof FavoriteResponse) && Intrinsics.areEqual(this.list, ((FavoriteResponse) obj).list);
    }

    public int hashCode() {
        ArrayList<FavoriteItem> arrayList = this.list;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.hashCode();
    }

    public String toString() {
        return "FavoriteResponse(list=" + this.list + PropertyUtils.MAPPED_DELIM2;
    }

    public FavoriteResponse(ArrayList<FavoriteItem> arrayList) {
        this.list = arrayList;
    }

    public /* synthetic */ FavoriteResponse(ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : arrayList);
    }

    public final ArrayList<FavoriteItem> getList() {
        return this.list;
    }
}
