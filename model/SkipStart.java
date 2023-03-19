package com.movieboxpro.android.model;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.apache.commons.beanutils.PropertyUtils;
/* compiled from: Bean.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/movieboxpro/android/model/SkipStart;", "", TtmlNode.START, "", "total", "(II)V", "getStart", "()I", "getTotal", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SkipStart {
    private final int start;
    private final int total;

    public SkipStart() {
        this(0, 0, 3, null);
    }

    public static /* synthetic */ SkipStart copy$default(SkipStart skipStart, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = skipStart.start;
        }
        if ((i3 & 2) != 0) {
            i2 = skipStart.total;
        }
        return skipStart.copy(i, i2);
    }

    public final int component1() {
        return this.start;
    }

    public final int component2() {
        return this.total;
    }

    public final SkipStart copy(int i, int i2) {
        return new SkipStart(i, i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SkipStart) {
            SkipStart skipStart = (SkipStart) obj;
            return this.start == skipStart.start && this.total == skipStart.total;
        }
        return false;
    }

    public int hashCode() {
        return (this.start * 31) + this.total;
    }

    public String toString() {
        return "SkipStart(start=" + this.start + ", total=" + this.total + PropertyUtils.MAPPED_DELIM2;
    }

    public SkipStart(int i, int i2) {
        this.start = i;
        this.total = i2;
    }

    public /* synthetic */ SkipStart(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? 0 : i2);
    }

    public final int getStart() {
        return this.start;
    }

    public final int getTotal() {
        return this.total;
    }
}
