package com.movieboxpro.android.utils;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: Api.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u001c\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J:\u0010\u0003\u001a\"\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004j\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u0005J6\u0010\t\u001a\"\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004j\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bJ\u0082\u0001\u0010\r\u001a\"\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004j\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\u00062\u0006\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u00052\b\u0010\u0014\u001a\u0004\u0018\u00010\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u000bJB\u0010\u0016\u001a\"\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004j\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\u00062\b\u0010\u0017\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0018\u001a\u00020\u000b2\b\u0010\b\u001a\u0004\u0018\u00010\u0005J>\u0010\u0019\u001a\"\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004j\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\u00062\u0006\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bJF\u0010\u0019\u001a\"\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004j\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\u00062\u0006\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bJ6\u0010\u001c\u001a\"\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004j\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bJ@\u0010\u001d\u001a\"\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004j\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\u00062\b\u0010\u001e\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001f\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u000bJ6\u0010!\u001a\"\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004j\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bJ\\\u0010\"\u001a\"\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004j\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\u00062\b\u0010#\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001f\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010$\u001a\u00020\u000b2\b\b\u0002\u0010%\u001a\u00020\u000bJT\u0010&\u001a\"\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004j\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\u00062\b\u0010#\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001f\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u000b2\b\b\u0002\u0010$\u001a\u00020\u000b2\b\b\u0002\u0010%\u001a\u00020\u000b¨\u0006'"}, d2 = {"Lcom/movieboxpro/android/utils/Api;", "", "()V", "blockUser", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "uid", IjkMediaMeta.IJKM_KEY_TYPE, "getBlockUsers", "page", "", "pageLimit", "getCollectList", "boxType", "quality", "sort", "country", "year", "content_rating", "cat_id", "waiting", "getFileThumbs", "fid", "box_type", "getLikeList", "like_status", "likeStatus", "getRecommendList", "getSkipTime", "id", "season", "episode", "getWatchedList", "saveSkipTime", "tid", TtmlNode.START, TtmlNode.END, "voteSkipTime", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Api {
    public static final Api INSTANCE = new Api();

    private Api() {
    }

    public final HashMap<String, Object> blockUser(String str, String str2) {
        return MapsKt.hashMapOf(new Pair("module", "User_comment_block"), new Pair("block_uid", str), new Pair(IjkMediaMeta.IJKM_KEY_TYPE, str2));
    }

    public final HashMap<String, Object> getBlockUsers(int i, int i2) {
        return MapsKt.hashMapOf(new Pair("module", "User_comment_block_user_list"), new Pair("page", Integer.valueOf(i)), new Pair("pagelimit", Integer.valueOf(i2)));
    }

    public final HashMap<String, Object> getCollectList(String boxType, String str, String str2, String str3, String str4, String str5, String str6, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(boxType, "boxType");
        return MapsKt.hashMapOf(new Pair("module", "User_watched_plan_list_v2"), new Pair("page", Integer.valueOf(i)), new Pair("pagelimit", Integer.valueOf(i2)), new Pair("box_type", boxType), new Pair("quality", str), new Pair("sort", str2), new Pair("country", str3), new Pair("year", str4), new Pair("content_rating", str5), new Pair("cat_id", str6), new Pair("waiting", Integer.valueOf(i3)));
    }

    public final HashMap<String, Object> getLikeList(int i, int i2, int i3) {
        return MapsKt.hashMapOf(new Pair("module", "User_movie_like_list"), new Pair("like_status", Integer.valueOf(i)), new Pair("page", Integer.valueOf(i2)), new Pair("pagelimit", Integer.valueOf(i3)));
    }

    public final HashMap<String, Object> saveSkipTime(String str, int i, int i2, String type, int i3, int i4) {
        Intrinsics.checkNotNullParameter(type, "type");
        return MapsKt.hashMapOf(new Pair("module", "TV_episode_title_mark"), new Pair("tid", str), new Pair("season", Integer.valueOf(i)), new Pair("episode", Integer.valueOf(i2)), new Pair(IjkMediaMeta.IJKM_KEY_TYPE, type), new Pair(TtmlNode.START, Integer.valueOf(i3)), new Pair(TtmlNode.END, Integer.valueOf(i4)));
    }

    public final HashMap<String, Object> voteSkipTime(String str, int i, int i2, int i3, int i4) {
        return MapsKt.hashMapOf(new Pair("module", "TV_episode_title_vote"), new Pair("tid", str), new Pair("season", Integer.valueOf(i)), new Pair("episode", Integer.valueOf(i2)), new Pair(IjkMediaMeta.IJKM_KEY_TYPE, "add"), new Pair(TtmlNode.START, Integer.valueOf(i3)), new Pair(TtmlNode.END, Integer.valueOf(i4)));
    }

    public final HashMap<String, Object> getFileThumbs(String str, int i, String str2) {
        return MapsKt.hashMapOf(new Pair("module", "Video_thumbs"), new Pair("fid", str), new Pair("box_type", Integer.valueOf(i)), new Pair(IjkMediaMeta.IJKM_KEY_TYPE, str2));
    }

    public final HashMap<String, Object> getSkipTime(String str, int i, int i2) {
        return MapsKt.hashMapOf(new Pair("module", "TV_episode_title_get"), new Pair("tid", str), new Pair("season", Integer.valueOf(i)), new Pair("episode", Integer.valueOf(i2)));
    }

    public final HashMap<String, Object> getWatchedList(int i, int i2) {
        return MapsKt.hashMapOf(new Pair("module", "User_watched_list"), new Pair("page", Integer.valueOf(i)), new Pair("pagelimit", Integer.valueOf(i2)));
    }

    public final HashMap<String, Object> getRecommendList(int i, int i2) {
        return MapsKt.hashMapOf(new Pair("module", "User_recommend_list"), new Pair("page", Integer.valueOf(i)), new Pair("pagelimit", Integer.valueOf(i2)));
    }

    public final HashMap<String, Object> getLikeList(int i, int i2, int i3, int i4) {
        return MapsKt.hashMapOf(new Pair("module", "User_movie_like_list"), new Pair("page", Integer.valueOf(i3)), new Pair("pagelimit", Integer.valueOf(i4)), new Pair("like_status", Integer.valueOf(i)), new Pair("box_type", Integer.valueOf(i2)));
    }
}
