package com.movieboxpro.android.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.beanutils.PropertyUtils;
/* compiled from: Bean.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/movieboxpro/android/model/SkipTimeInfo;", "", "info", "Lcom/movieboxpro/android/model/SkipTime;", "(Lcom/movieboxpro/android/model/SkipTime;)V", "getInfo", "()Lcom/movieboxpro/android/model/SkipTime;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SkipTimeInfo {
    private final SkipTime info;

    public SkipTimeInfo() {
        this(null, 1, null);
    }

    public static /* synthetic */ SkipTimeInfo copy$default(SkipTimeInfo skipTimeInfo, SkipTime skipTime, int i, Object obj) {
        if ((i & 1) != 0) {
            skipTime = skipTimeInfo.info;
        }
        return skipTimeInfo.copy(skipTime);
    }

    public final SkipTime component1() {
        return this.info;
    }

    public final SkipTimeInfo copy(SkipTime skipTime) {
        return new SkipTimeInfo(skipTime);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof SkipTimeInfo) && Intrinsics.areEqual(this.info, ((SkipTimeInfo) obj).info);
    }

    public int hashCode() {
        SkipTime skipTime = this.info;
        if (skipTime == null) {
            return 0;
        }
        return skipTime.hashCode();
    }

    public String toString() {
        return "SkipTimeInfo(info=" + this.info + PropertyUtils.MAPPED_DELIM2;
    }

    public SkipTimeInfo(SkipTime skipTime) {
        this.info = skipTime;
    }

    public /* synthetic */ SkipTimeInfo(SkipTime skipTime, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : skipTime);
    }

    public final SkipTime getInfo() {
        return this.info;
    }
}
