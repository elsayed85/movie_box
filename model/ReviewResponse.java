package com.movieboxpro.android.model;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.beanutils.PropertyUtils;
/* compiled from: Bean.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u0011\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u001b\u0010\t\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/movieboxpro/android/model/ReviewResponse;", "", "list", "", "Lcom/movieboxpro/android/model/ReviewItem;", "(Ljava/util/List;)V", "getList", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ReviewResponse {
    private final List<ReviewItem> list;

    public ReviewResponse() {
        this(null, 1, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ReviewResponse copy$default(ReviewResponse reviewResponse, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = reviewResponse.list;
        }
        return reviewResponse.copy(list);
    }

    public final List<ReviewItem> component1() {
        return this.list;
    }

    public final ReviewResponse copy(List<ReviewItem> list) {
        return new ReviewResponse(list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ReviewResponse) && Intrinsics.areEqual(this.list, ((ReviewResponse) obj).list);
    }

    public int hashCode() {
        List<ReviewItem> list = this.list;
        if (list == null) {
            return 0;
        }
        return list.hashCode();
    }

    public String toString() {
        return "ReviewResponse(list=" + this.list + PropertyUtils.MAPPED_DELIM2;
    }

    public ReviewResponse(List<ReviewItem> list) {
        this.list = list;
    }

    public /* synthetic */ ReviewResponse(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list);
    }

    public final List<ReviewItem> getList() {
        return this.list;
    }
}
