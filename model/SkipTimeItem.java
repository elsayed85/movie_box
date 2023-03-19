package com.movieboxpro.android.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.apache.commons.beanutils.PropertyUtils;
/* compiled from: Bean.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\u0007\"\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/movieboxpro/android/model/SkipTimeItem;", "", "time", "", "total", "(II)V", "getTime", "()I", "getTotal", "setTotal", "(I)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SkipTimeItem {
    private final int time;
    private int total;

    public SkipTimeItem() {
        this(0, 0, 3, null);
    }

    public static /* synthetic */ SkipTimeItem copy$default(SkipTimeItem skipTimeItem, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = skipTimeItem.time;
        }
        if ((i3 & 2) != 0) {
            i2 = skipTimeItem.total;
        }
        return skipTimeItem.copy(i, i2);
    }

    public final int component1() {
        return this.time;
    }

    public final int component2() {
        return this.total;
    }

    public final SkipTimeItem copy(int i, int i2) {
        return new SkipTimeItem(i, i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SkipTimeItem) {
            SkipTimeItem skipTimeItem = (SkipTimeItem) obj;
            return this.time == skipTimeItem.time && this.total == skipTimeItem.total;
        }
        return false;
    }

    public int hashCode() {
        return (this.time * 31) + this.total;
    }

    public String toString() {
        return "SkipTimeItem(time=" + this.time + ", total=" + this.total + PropertyUtils.MAPPED_DELIM2;
    }

    public SkipTimeItem(int i, int i2) {
        this.time = i;
        this.total = i2;
    }

    public /* synthetic */ SkipTimeItem(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? 0 : i2);
    }

    public final int getTime() {
        return this.time;
    }

    public final int getTotal() {
        return this.total;
    }

    public final void setTotal(int i) {
        this.total = i;
    }
}
