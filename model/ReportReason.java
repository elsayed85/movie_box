package com.movieboxpro.android.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.beanutils.PropertyUtils;
/* compiled from: Bean.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u000b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/movieboxpro/android/model/ReportReason;", "", "id", "", "reason", "(Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getReason", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ReportReason {
    private final String id;
    private final String reason;

    public ReportReason() {
        this(null, null, 3, null);
    }

    public static /* synthetic */ ReportReason copy$default(ReportReason reportReason, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = reportReason.id;
        }
        if ((i & 2) != 0) {
            str2 = reportReason.reason;
        }
        return reportReason.copy(str, str2);
    }

    public final String component1() {
        return this.id;
    }

    public final String component2() {
        return this.reason;
    }

    public final ReportReason copy(String str, String str2) {
        return new ReportReason(str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ReportReason) {
            ReportReason reportReason = (ReportReason) obj;
            return Intrinsics.areEqual(this.id, reportReason.id) && Intrinsics.areEqual(this.reason, reportReason.reason);
        }
        return false;
    }

    public int hashCode() {
        String str = this.id;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.reason;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "ReportReason(id=" + ((Object) this.id) + ", reason=" + ((Object) this.reason) + PropertyUtils.MAPPED_DELIM2;
    }

    public ReportReason(String str, String str2) {
        this.id = str;
        this.reason = str2;
    }

    public /* synthetic */ ReportReason(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2);
    }

    public final String getId() {
        return this.id;
    }

    public final String getReason() {
        return this.reason;
    }
}
