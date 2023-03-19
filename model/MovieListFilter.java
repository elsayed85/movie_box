package com.movieboxpro.android.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.beanutils.PropertyUtils;
/* compiled from: Bean.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J+\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r¨\u0006\u001a"}, d2 = {"Lcom/movieboxpro/android/model/MovieListFilter;", "", "title", "", "select", "", "serviceText", "(Ljava/lang/String;ZLjava/lang/String;)V", "getSelect", "()Z", "setSelect", "(Z)V", "getServiceText", "()Ljava/lang/String;", "setServiceText", "(Ljava/lang/String;)V", "getTitle", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MovieListFilter {
    private boolean select;
    private String serviceText;
    private final String title;

    public MovieListFilter() {
        this(null, false, null, 7, null);
    }

    public static /* synthetic */ MovieListFilter copy$default(MovieListFilter movieListFilter, String str, boolean z, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = movieListFilter.title;
        }
        if ((i & 2) != 0) {
            z = movieListFilter.select;
        }
        if ((i & 4) != 0) {
            str2 = movieListFilter.serviceText;
        }
        return movieListFilter.copy(str, z, str2);
    }

    public final String component1() {
        return this.title;
    }

    public final boolean component2() {
        return this.select;
    }

    public final String component3() {
        return this.serviceText;
    }

    public final MovieListFilter copy(String str, boolean z, String str2) {
        return new MovieListFilter(str, z, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MovieListFilter) {
            MovieListFilter movieListFilter = (MovieListFilter) obj;
            return Intrinsics.areEqual(this.title, movieListFilter.title) && this.select == movieListFilter.select && Intrinsics.areEqual(this.serviceText, movieListFilter.serviceText);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.title;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        boolean z = this.select;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode + i) * 31;
        String str2 = this.serviceText;
        return i2 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "MovieListFilter(title=" + ((Object) this.title) + ", select=" + this.select + ", serviceText=" + ((Object) this.serviceText) + PropertyUtils.MAPPED_DELIM2;
    }

    public MovieListFilter(String str, boolean z, String str2) {
        this.title = str;
        this.select = z;
        this.serviceText = str2;
    }

    public /* synthetic */ MovieListFilter(String str, boolean z, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? false : z, (i & 4) != 0 ? null : str2);
    }

    public final String getTitle() {
        return this.title;
    }

    public final boolean getSelect() {
        return this.select;
    }

    public final void setSelect(boolean z) {
        this.select = z;
    }

    public final String getServiceText() {
        return this.serviceText;
    }

    public final void setServiceText(String str) {
        this.serviceText = str;
    }
}
