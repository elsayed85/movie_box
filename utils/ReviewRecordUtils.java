package com.movieboxpro.android.utils;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.movieboxpro.android.model.ReviewRecordModel;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: ReviewRecordUtils.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\f\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\u0005J\u0018\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0005J\u001a\u0010\u0011\u001a\u0004\u0018\u00010\u00062\b\u0010\f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0012\u001a\u00020\u0005J\u001a\u0010\u0013\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0005J$\u0010\u0014\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u0005JP\u0010\u0014\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u00052\u0014\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0014\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004J`\u0010\u0018\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00052\b\u0010\u0019\u001a\u0004\u0018\u00010\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0012\u001a\u00020\u00052\u0014\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0014\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004R*\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\b\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/movieboxpro/android/utils/ReviewRecordUtils;", "", "()V", "reviewRecord", "Ljava/util/HashMap;", "", "Lcom/movieboxpro/android/model/ReviewRecordModel;", "Lkotlin/collections/HashMap;", "videoReviewRecord", "clearReviewRecord", "", "deleteReviewRecord", "reviewId", "deleteVideoReviewRecord", "boxType", "", "videoId", "getReviewRecord", IjkMediaMeta.IJKM_KEY_TYPE, "getVideoReviewRecord", "saveReviewRecord", FirebaseAnalytics.Param.CONTENT, "map", "thumbMap", "saveVideoReviewRecord", "title", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ReviewRecordUtils {
    public static final Companion Companion = new Companion(null);
    private static ReviewRecordUtils instance;
    private HashMap<String, ReviewRecordModel> reviewRecord;
    private HashMap<String, ReviewRecordModel> videoReviewRecord;

    public /* synthetic */ ReviewRecordUtils(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private ReviewRecordUtils() {
        this.reviewRecord = new HashMap<>();
        this.videoReviewRecord = new HashMap<>();
    }

    /* compiled from: ReviewRecordUtils.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\u0004R\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00048BX\u0082\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/utils/ReviewRecordUtils$Companion;", "", "()V", "instance", "Lcom/movieboxpro/android/utils/ReviewRecordUtils;", "getInstance", "()Lcom/movieboxpro/android/utils/ReviewRecordUtils;", "get", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final ReviewRecordUtils getInstance() {
            if (ReviewRecordUtils.instance == null) {
                ReviewRecordUtils.instance = new ReviewRecordUtils(null);
            }
            return ReviewRecordUtils.instance;
        }

        public final ReviewRecordUtils get() {
            ReviewRecordUtils companion = getInstance();
            Intrinsics.checkNotNull(companion);
            return companion;
        }
    }

    public final void clearReviewRecord() {
        this.reviewRecord.clear();
        this.videoReviewRecord.clear();
    }

    public final ReviewRecordModel getVideoReviewRecord(int i, String str) {
        HashMap<String, ReviewRecordModel> hashMap = this.videoReviewRecord;
        StringBuilder sb = new StringBuilder();
        sb.append((Object) str);
        sb.append('_');
        sb.append(i);
        return hashMap.get(sb.toString());
    }

    public final void saveVideoReviewRecord(int i, String str, String str2, String str3, String type, HashMap<String, String> hashMap, HashMap<String, String> hashMap2) {
        Intrinsics.checkNotNullParameter(type, "type");
        ReviewRecordModel reviewRecordModel = new ReviewRecordModel(str3, type, str2, hashMap, hashMap2);
        StringBuilder sb = new StringBuilder();
        sb.append((Object) str);
        sb.append('_');
        sb.append(i);
        this.videoReviewRecord.put(sb.toString(), reviewRecordModel);
    }

    public final void deleteVideoReviewRecord(int i, String str) {
        HashMap<String, ReviewRecordModel> hashMap = this.videoReviewRecord;
        StringBuilder sb = new StringBuilder();
        sb.append((Object) str);
        sb.append('_');
        sb.append(i);
        hashMap.remove(sb.toString());
    }

    public final ReviewRecordModel getReviewRecord(String str, String type) {
        Intrinsics.checkNotNullParameter(type, "type");
        HashMap<String, ReviewRecordModel> hashMap = this.reviewRecord;
        return hashMap.get(((Object) str) + '_' + type);
    }

    public final void saveReviewRecord(String str, String str2, String str3, HashMap<String, String> hashMap, HashMap<String, String> hashMap2) {
        ReviewRecordModel reviewRecordModel = new ReviewRecordModel();
        reviewRecordModel.setContent(str3);
        reviewRecordModel.setType(str2);
        reviewRecordModel.setImgMap(hashMap);
        reviewRecordModel.setThumbImageMap(hashMap2);
        StringBuilder sb = new StringBuilder();
        sb.append((Object) str);
        sb.append('_');
        sb.append((Object) str2);
        this.reviewRecord.put(sb.toString(), reviewRecordModel);
    }

    public final void saveReviewRecord(String str, String str2, String str3) {
        ReviewRecordModel reviewRecordModel = new ReviewRecordModel();
        reviewRecordModel.setContent(str3);
        reviewRecordModel.setType(str2);
        StringBuilder sb = new StringBuilder();
        sb.append((Object) str);
        sb.append('_');
        sb.append((Object) str2);
        this.reviewRecord.put(sb.toString(), reviewRecordModel);
    }

    public final void deleteReviewRecord(String str) {
        this.reviewRecord.remove(Intrinsics.stringPlus(str, "_text"));
        this.reviewRecord.remove(Intrinsics.stringPlus(str, "_html"));
    }
}
