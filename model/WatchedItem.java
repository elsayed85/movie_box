package com.movieboxpro.android.model;

import com.movieboxpro.android.view.dialog.FilterVideoDialog;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.beanutils.PropertyUtils;
/* compiled from: Bean.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b5\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001Bï\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a¢\u0006\u0002\u0010\u001bJ\t\u00109\u001a\u00020\u0003HÆ\u0003J\t\u0010:\u001a\u00020\u0003HÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010B\u001a\u00020\u0003HÆ\u0003J\t\u0010C\u001a\u00020\u0003HÆ\u0003J\u000b\u0010D\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010E\u001a\u00020\u0003HÆ\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u001aHÆ\u0003J\u000b\u0010G\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010H\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010I\u001a\u00020\tHÆ\u0003J\u000b\u0010J\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010K\u001a\u00020\u0003HÆ\u0003J\t\u0010L\u001a\u00020\u0003HÆ\u0003J\t\u0010M\u001a\u00020\u0003HÆ\u0003Jó\u0001\u0010N\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0016\u001a\u00020\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u00032\b\b\u0002\u0010\u0018\u001a\u00020\u00032\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÆ\u0001J\u0013\u0010O\u001a\u00020P2\b\u0010Q\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010R\u001a\u00020\u0003HÖ\u0001J\t\u0010S\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u001a\u0010\u000e\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u001d\"\u0004\b!\u0010\"R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u001a¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001fR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u001a\u0010\f\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u001d\"\u0004\b*\u0010\"R\u001a\u0010\r\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u001d\"\u0004\b,\u0010\"R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001fR\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001fR\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u001fR\u0011\u0010\u0016\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u001dR\u0011\u0010\u0018\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u001dR\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u001fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u001fR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u001dR\u0011\u0010\u0017\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\u001dR\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u001fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b7\u0010\u001fR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\u001f¨\u0006T"}, d2 = {"Lcom/movieboxpro/android/model/WatchedItem;", "", "box_type", "", "id", "", "watched_id", "title", "last_play_time", "", "imdb_rating", "tomato_meter", "like_status", "like_total", "dislike_total", "year", "cats", FilterVideoDialog.DEFAULT_SORT, "poster", "quality_tag_new", "season_episode", "update_title", "runtime", "updateCount", "runtime_seconds", "episode_info", "Lcom/movieboxpro/android/model/WatchedEpisode;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;IIIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IIILcom/movieboxpro/android/model/WatchedEpisode;)V", "getBox_type", "()I", "getCats", "()Ljava/lang/String;", "getDislike_total", "setDislike_total", "(I)V", "getEpisode_info", "()Lcom/movieboxpro/android/model/WatchedEpisode;", "getId", "getImdb_rating", "getLast_play_time", "()J", "getLike_status", "setLike_status", "getLike_total", "setLike_total", "getPoster", "getQuality_tag_new", "getReleased", "getRuntime", "getRuntime_seconds", "getSeason_episode", "getTitle", "getTomato_meter", "getUpdateCount", "getUpdate_title", "getWatched_id", "getYear", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WatchedItem {
    private final int box_type;
    private final String cats;
    private int dislike_total;
    private final WatchedEpisode episode_info;
    private final String id;
    private final String imdb_rating;
    private final long last_play_time;
    private int like_status;
    private int like_total;
    private final String poster;
    private final String quality_tag_new;
    private final String released;
    private final int runtime;
    private final int runtime_seconds;
    private final String season_episode;
    private final String title;
    private final int tomato_meter;
    private final int updateCount;
    private final String update_title;
    private final String watched_id;
    private final String year;

    public WatchedItem() {
        this(0, null, null, null, 0L, null, 0, 0, 0, 0, null, null, null, null, null, null, null, 0, 0, 0, null, 2097151, null);
    }

    public final int component1() {
        return this.box_type;
    }

    public final int component10() {
        return this.dislike_total;
    }

    public final String component11() {
        return this.year;
    }

    public final String component12() {
        return this.cats;
    }

    public final String component13() {
        return this.released;
    }

    public final String component14() {
        return this.poster;
    }

    public final String component15() {
        return this.quality_tag_new;
    }

    public final String component16() {
        return this.season_episode;
    }

    public final String component17() {
        return this.update_title;
    }

    public final int component18() {
        return this.runtime;
    }

    public final int component19() {
        return this.updateCount;
    }

    public final String component2() {
        return this.id;
    }

    public final int component20() {
        return this.runtime_seconds;
    }

    public final WatchedEpisode component21() {
        return this.episode_info;
    }

    public final String component3() {
        return this.watched_id;
    }

    public final String component4() {
        return this.title;
    }

    public final long component5() {
        return this.last_play_time;
    }

    public final String component6() {
        return this.imdb_rating;
    }

    public final int component7() {
        return this.tomato_meter;
    }

    public final int component8() {
        return this.like_status;
    }

    public final int component9() {
        return this.like_total;
    }

    public final WatchedItem copy(int i, String str, String str2, String str3, long j, String str4, int i2, int i3, int i4, int i5, String str5, String str6, String str7, String str8, String str9, String str10, String str11, int i6, int i7, int i8, WatchedEpisode watchedEpisode) {
        return new WatchedItem(i, str, str2, str3, j, str4, i2, i3, i4, i5, str5, str6, str7, str8, str9, str10, str11, i6, i7, i8, watchedEpisode);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof WatchedItem) {
            WatchedItem watchedItem = (WatchedItem) obj;
            return this.box_type == watchedItem.box_type && Intrinsics.areEqual(this.id, watchedItem.id) && Intrinsics.areEqual(this.watched_id, watchedItem.watched_id) && Intrinsics.areEqual(this.title, watchedItem.title) && this.last_play_time == watchedItem.last_play_time && Intrinsics.areEqual(this.imdb_rating, watchedItem.imdb_rating) && this.tomato_meter == watchedItem.tomato_meter && this.like_status == watchedItem.like_status && this.like_total == watchedItem.like_total && this.dislike_total == watchedItem.dislike_total && Intrinsics.areEqual(this.year, watchedItem.year) && Intrinsics.areEqual(this.cats, watchedItem.cats) && Intrinsics.areEqual(this.released, watchedItem.released) && Intrinsics.areEqual(this.poster, watchedItem.poster) && Intrinsics.areEqual(this.quality_tag_new, watchedItem.quality_tag_new) && Intrinsics.areEqual(this.season_episode, watchedItem.season_episode) && Intrinsics.areEqual(this.update_title, watchedItem.update_title) && this.runtime == watchedItem.runtime && this.updateCount == watchedItem.updateCount && this.runtime_seconds == watchedItem.runtime_seconds && Intrinsics.areEqual(this.episode_info, watchedItem.episode_info);
        }
        return false;
    }

    public int hashCode() {
        int i = this.box_type * 31;
        String str = this.id;
        int hashCode = (i + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.watched_id;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.title;
        int hashCode3 = (((hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31) + C$r8$backportedMethods$utility$Long$1$hashCode.hashCode(this.last_play_time)) * 31;
        String str4 = this.imdb_rating;
        int hashCode4 = (((((((((hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31) + this.tomato_meter) * 31) + this.like_status) * 31) + this.like_total) * 31) + this.dislike_total) * 31;
        String str5 = this.year;
        int hashCode5 = (hashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.cats;
        int hashCode6 = (hashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.released;
        int hashCode7 = (hashCode6 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.poster;
        int hashCode8 = (hashCode7 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.quality_tag_new;
        int hashCode9 = (hashCode8 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.season_episode;
        int hashCode10 = (hashCode9 + (str10 == null ? 0 : str10.hashCode())) * 31;
        String str11 = this.update_title;
        int hashCode11 = (((((((hashCode10 + (str11 == null ? 0 : str11.hashCode())) * 31) + this.runtime) * 31) + this.updateCount) * 31) + this.runtime_seconds) * 31;
        WatchedEpisode watchedEpisode = this.episode_info;
        return hashCode11 + (watchedEpisode != null ? watchedEpisode.hashCode() : 0);
    }

    public String toString() {
        return "WatchedItem(box_type=" + this.box_type + ", id=" + ((Object) this.id) + ", watched_id=" + ((Object) this.watched_id) + ", title=" + ((Object) this.title) + ", last_play_time=" + this.last_play_time + ", imdb_rating=" + ((Object) this.imdb_rating) + ", tomato_meter=" + this.tomato_meter + ", like_status=" + this.like_status + ", like_total=" + this.like_total + ", dislike_total=" + this.dislike_total + ", year=" + ((Object) this.year) + ", cats=" + ((Object) this.cats) + ", released=" + ((Object) this.released) + ", poster=" + ((Object) this.poster) + ", quality_tag_new=" + ((Object) this.quality_tag_new) + ", season_episode=" + ((Object) this.season_episode) + ", update_title=" + ((Object) this.update_title) + ", runtime=" + this.runtime + ", updateCount=" + this.updateCount + ", runtime_seconds=" + this.runtime_seconds + ", episode_info=" + this.episode_info + PropertyUtils.MAPPED_DELIM2;
    }

    public WatchedItem(int i, String str, String str2, String str3, long j, String str4, int i2, int i3, int i4, int i5, String str5, String str6, String str7, String str8, String str9, String str10, String str11, int i6, int i7, int i8, WatchedEpisode watchedEpisode) {
        this.box_type = i;
        this.id = str;
        this.watched_id = str2;
        this.title = str3;
        this.last_play_time = j;
        this.imdb_rating = str4;
        this.tomato_meter = i2;
        this.like_status = i3;
        this.like_total = i4;
        this.dislike_total = i5;
        this.year = str5;
        this.cats = str6;
        this.released = str7;
        this.poster = str8;
        this.quality_tag_new = str9;
        this.season_episode = str10;
        this.update_title = str11;
        this.runtime = i6;
        this.updateCount = i7;
        this.runtime_seconds = i8;
        this.episode_info = watchedEpisode;
    }

    public /* synthetic */ WatchedItem(int i, String str, String str2, String str3, long j, String str4, int i2, int i3, int i4, int i5, String str5, String str6, String str7, String str8, String str9, String str10, String str11, int i6, int i7, int i8, WatchedEpisode watchedEpisode, int i9, DefaultConstructorMarker defaultConstructorMarker) {
        this((i9 & 1) != 0 ? 0 : i, (i9 & 2) != 0 ? null : str, (i9 & 4) != 0 ? null : str2, (i9 & 8) != 0 ? null : str3, (i9 & 16) != 0 ? 0L : j, (i9 & 32) != 0 ? null : str4, (i9 & 64) != 0 ? 0 : i2, (i9 & 128) != 0 ? 0 : i3, (i9 & 256) != 0 ? 0 : i4, (i9 & 512) != 0 ? 0 : i5, (i9 & 1024) != 0 ? null : str5, (i9 & 2048) != 0 ? null : str6, (i9 & 4096) != 0 ? null : str7, (i9 & 8192) != 0 ? null : str8, (i9 & 16384) != 0 ? null : str9, (i9 & 32768) != 0 ? null : str10, (i9 & 65536) != 0 ? null : str11, (i9 & 131072) != 0 ? 0 : i6, (i9 & 262144) != 0 ? 0 : i7, (i9 & 524288) != 0 ? 0 : i8, (i9 & 1048576) != 0 ? null : watchedEpisode);
    }

    public final int getBox_type() {
        return this.box_type;
    }

    public final String getId() {
        return this.id;
    }

    public final String getWatched_id() {
        return this.watched_id;
    }

    public final String getTitle() {
        return this.title;
    }

    public final long getLast_play_time() {
        return this.last_play_time;
    }

    public final String getImdb_rating() {
        return this.imdb_rating;
    }

    public final int getTomato_meter() {
        return this.tomato_meter;
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

    public final String getYear() {
        return this.year;
    }

    public final String getCats() {
        return this.cats;
    }

    public final String getReleased() {
        return this.released;
    }

    public final String getPoster() {
        return this.poster;
    }

    public final String getQuality_tag_new() {
        return this.quality_tag_new;
    }

    public final String getSeason_episode() {
        return this.season_episode;
    }

    public final String getUpdate_title() {
        return this.update_title;
    }

    public final int getRuntime() {
        return this.runtime;
    }

    public final int getUpdateCount() {
        return this.updateCount;
    }

    public final int getRuntime_seconds() {
        return this.runtime_seconds;
    }

    public final WatchedEpisode getEpisode_info() {
        return this.episode_info;
    }
}
