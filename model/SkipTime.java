package com.movieboxpro.android.model;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.apache.commons.beanutils.PropertyUtils;
/* compiled from: Bean.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/movieboxpro/android/model/SkipTime;", "", TtmlNode.START, "", TtmlNode.END, "(II)V", "getEnd", "()I", "getStart", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SkipTime {
    private final int end;
    private final int start;

    public SkipTime() {
        this(0, 0, 3, null);
    }

    public static /* synthetic */ SkipTime copy$default(SkipTime skipTime, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = skipTime.start;
        }
        if ((i3 & 2) != 0) {
            i2 = skipTime.end;
        }
        return skipTime.copy(i, i2);
    }

    public final int component1() {
        return this.start;
    }

    public final int component2() {
        return this.end;
    }

    public final SkipTime copy(int i, int i2) {
        return new SkipTime(i, i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SkipTime) {
            SkipTime skipTime = (SkipTime) obj;
            return this.start == skipTime.start && this.end == skipTime.end;
        }
        return false;
    }

    public int hashCode() {
        return (this.start * 31) + this.end;
    }

    public String toString() {
        return "SkipTime(start=" + this.start + ", end=" + this.end + PropertyUtils.MAPPED_DELIM2;
    }

    public SkipTime(int i, int i2) {
        this.start = i;
        this.end = i2;
    }

    public /* synthetic */ SkipTime(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? 0 : i2);
    }

    public final int getStart() {
        return this.start;
    }

    public final int getEnd() {
        return this.end;
    }
}
