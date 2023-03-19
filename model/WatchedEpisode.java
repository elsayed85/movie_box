package com.movieboxpro.android.model;

import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.movieboxpro.android.view.dialog.FilterVideoDialog;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.beanutils.PropertyUtils;
/* compiled from: Bean.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b \n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001Bq\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000eJ\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\nHÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\nHÆ\u0003Ju\u0010)\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\nHÆ\u0001J\u0013\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010-\u001a\u00020\u0003HÖ\u0001J\t\u0010.\u001a\u00020\nHÖ\u0001R\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0013\u0010\r\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0010\"\u0004\b\u0017\u0010\u0012R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0010\"\u0004\b\u0019\u0010\u0012R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0015R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0010R\u0013\u0010\f\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0015R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0015¨\u0006/"}, d2 = {"Lcom/movieboxpro/android/model/WatchedEpisode;", "", "season", "", "episode", "like_status", "like_total", "dislike_total", "runtime", "title", "", FilterVideoDialog.DEFAULT_SORT, "thumbs", "imdb_rating", "(IIIIIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDislike_total", "()I", "setDislike_total", "(I)V", "getEpisode", "getImdb_rating", "()Ljava/lang/String;", "getLike_status", "setLike_status", "getLike_total", "setLike_total", "getReleased", "getRuntime", "getSeason", "getThumbs", "getTitle", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WatchedEpisode {
    private int dislike_total;
    private final int episode;
    private final String imdb_rating;
    private int like_status;
    private int like_total;
    private final String released;
    private final int runtime;
    private final int season;
    private final String thumbs;
    private final String title;

    public WatchedEpisode() {
        this(0, 0, 0, 0, 0, 0, null, null, null, null, AnalyticsListener.EVENT_DRM_KEYS_LOADED, null);
    }

    public final int component1() {
        return this.season;
    }

    public final String component10() {
        return this.imdb_rating;
    }

    public final int component2() {
        return this.episode;
    }

    public final int component3() {
        return this.like_status;
    }

    public final int component4() {
        return this.like_total;
    }

    public final int component5() {
        return this.dislike_total;
    }

    public final int component6() {
        return this.runtime;
    }

    public final String component7() {
        return this.title;
    }

    public final String component8() {
        return this.released;
    }

    public final String component9() {
        return this.thumbs;
    }

    public final WatchedEpisode copy(int i, int i2, int i3, int i4, int i5, int i6, String str, String str2, String str3, String str4) {
        return new WatchedEpisode(i, i2, i3, i4, i5, i6, str, str2, str3, str4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof WatchedEpisode) {
            WatchedEpisode watchedEpisode = (WatchedEpisode) obj;
            return this.season == watchedEpisode.season && this.episode == watchedEpisode.episode && this.like_status == watchedEpisode.like_status && this.like_total == watchedEpisode.like_total && this.dislike_total == watchedEpisode.dislike_total && this.runtime == watchedEpisode.runtime && Intrinsics.areEqual(this.title, watchedEpisode.title) && Intrinsics.areEqual(this.released, watchedEpisode.released) && Intrinsics.areEqual(this.thumbs, watchedEpisode.thumbs) && Intrinsics.areEqual(this.imdb_rating, watchedEpisode.imdb_rating);
        }
        return false;
    }

    public int hashCode() {
        int i = ((((((((((this.season * 31) + this.episode) * 31) + this.like_status) * 31) + this.like_total) * 31) + this.dislike_total) * 31) + this.runtime) * 31;
        String str = this.title;
        int hashCode = (i + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.released;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.thumbs;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.imdb_rating;
        return hashCode3 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        return "WatchedEpisode(season=" + this.season + ", episode=" + this.episode + ", like_status=" + this.like_status + ", like_total=" + this.like_total + ", dislike_total=" + this.dislike_total + ", runtime=" + this.runtime + ", title=" + ((Object) this.title) + ", released=" + ((Object) this.released) + ", thumbs=" + ((Object) this.thumbs) + ", imdb_rating=" + ((Object) this.imdb_rating) + PropertyUtils.MAPPED_DELIM2;
    }

    public WatchedEpisode(int i, int i2, int i3, int i4, int i5, int i6, String str, String str2, String str3, String str4) {
        this.season = i;
        this.episode = i2;
        this.like_status = i3;
        this.like_total = i4;
        this.dislike_total = i5;
        this.runtime = i6;
        this.title = str;
        this.released = str2;
        this.thumbs = str3;
        this.imdb_rating = str4;
    }

    public /* synthetic */ WatchedEpisode(int i, int i2, int i3, int i4, int i5, int i6, String str, String str2, String str3, String str4, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? 0 : i, (i7 & 2) != 0 ? 0 : i2, (i7 & 4) != 0 ? 0 : i3, (i7 & 8) != 0 ? 0 : i4, (i7 & 16) != 0 ? 0 : i5, (i7 & 32) == 0 ? i6 : 0, (i7 & 64) != 0 ? null : str, (i7 & 128) != 0 ? null : str2, (i7 & 256) != 0 ? null : str3, (i7 & 512) == 0 ? str4 : null);
    }

    public final int getSeason() {
        return this.season;
    }

    public final int getEpisode() {
        return this.episode;
    }

    public final int getLike_status() {
        return this.like_status;
    }

    public final void setLike_status(int i) {
        this.like_status = i;
    }

    public final int getLike_total() {
        return this.like_total;
    }

    public final void setLike_total(int i) {
        this.like_total = i;
    }

    public final int getDislike_total() {
        return this.dislike_total;
    }

    public final void setDislike_total(int i) {
        this.dislike_total = i;
    }

    public final int getRuntime() {
        return this.runtime;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getReleased() {
        return this.released;
    }

    public final String getThumbs() {
        return this.thumbs;
    }

    public final String getImdb_rating() {
        return this.imdb_rating;
    }
}
