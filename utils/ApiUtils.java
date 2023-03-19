package com.movieboxpro.android.utils;

import kotlin.Metadata;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: ApiUtils.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u000e"}, d2 = {"Lcom/movieboxpro/android/utils/ApiUtils;", "", "()V", "deleteComment", "", IjkMediaMeta.IJKM_KEY_TYPE, "", "getCommentById", "getCommentDetail", "getReviews", "likeReview", "reviewNum", "sendComment", "uploadReviewImage", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ApiUtils {
    public static final ApiUtils INSTANCE = new ApiUtils();

    public final String deleteComment(int i) {
        return (i == 1 || i == 2) ? "Movie_comment_delete" : i != 3 ? i != 4 ? "Movie_comment_delete" : "Actor_comment_delete" : "Playlist_comment_delete";
    }

    public final String getCommentById(int i) {
        return (i == 1 || i == 2) ? "Movie_comment_list_by_id" : i != 3 ? i != 4 ? "Movie_comment_list_by_id" : "Actor_comment_list_by_id" : "Playlist_comment_list_by_id";
    }

    public final String getCommentDetail(int i) {
        return (i == 1 || i == 2) ? "Movie_comment_get" : i != 3 ? i != 4 ? "Movie_comment_get" : "Actor_comment_get" : "Playlist_comment_get";
    }

    public final String getReviews(int i) {
        return (i == 1 || i == 2) ? "Movie_comment_list" : i != 3 ? i != 4 ? "Movie_comment_list" : "Actor_comment_list" : "Playlist_comment_list";
    }

    public final String likeReview(int i) {
        return (i == 1 || i == 2) ? "Movie_comment_support" : i != 3 ? i != 4 ? "Movie_comment_support" : "Actor_comment_support" : "Playlist_comment_support";
    }

    public final String reviewNum(int i) {
        return (i == 1 || i == 2) ? "Movie_comment_count" : i != 3 ? i != 4 ? "Movie_comment_count" : "Actor_comment_count" : "Playlist_comment_count";
    }

    public final String sendComment(int i) {
        return (i == 1 || i == 2) ? "Movie_comment" : i != 3 ? i != 4 ? "Movie_comment" : "Actor_comment" : "Playlist_comment";
    }

    public final String uploadReviewImage(int i) {
        return (i == 1 || i == 2) ? "Movie_comment_upload" : i != 3 ? i != 4 ? "Movie_comment_upload" : "Actor_comment_upload" : "Playlist_comment_upload";
    }

    private ApiUtils() {
    }
}
