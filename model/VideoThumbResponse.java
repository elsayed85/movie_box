package com.movieboxpro.android.model;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.beanutils.PropertyUtils;
/* compiled from: Bean.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u001c\b\u0002\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005¢\u0006\u0002\u0010\u0006J\u001d\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005HÆ\u0003J'\u0010\n\u001a\u00020\u00002\u001c\b\u0002\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R%\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/movieboxpro/android/model/VideoThumbResponse;", "", "thumbs", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/VideoThumb;", "Lkotlin/collections/ArrayList;", "(Ljava/util/ArrayList;)V", "getThumbs", "()Ljava/util/ArrayList;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class VideoThumbResponse {
    private final ArrayList<VideoThumb> thumbs;

    public VideoThumbResponse() {
        this(null, 1, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ VideoThumbResponse copy$default(VideoThumbResponse videoThumbResponse, ArrayList arrayList, int i, Object obj) {
        if ((i & 1) != 0) {
            arrayList = videoThumbResponse.thumbs;
        }
        return videoThumbResponse.copy(arrayList);
    }

    public final ArrayList<VideoThumb> component1() {
        return this.thumbs;
    }

    public final VideoThumbResponse copy(ArrayList<VideoThumb> arrayList) {
        return new VideoThumbResponse(arrayList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof VideoThumbResponse) && Intrinsics.areEqual(this.thumbs, ((VideoThumbResponse) obj).thumbs);
    }

    public int hashCode() {
        ArrayList<VideoThumb> arrayList = this.thumbs;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.hashCode();
    }

    public String toString() {
        return "VideoThumbResponse(thumbs=" + this.thumbs + PropertyUtils.MAPPED_DELIM2;
    }

    public VideoThumbResponse(ArrayList<VideoThumb> arrayList) {
        this.thumbs = arrayList;
    }

    public /* synthetic */ VideoThumbResponse(ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : arrayList);
    }

    public final ArrayList<VideoThumb> getThumbs() {
        return this.thumbs;
    }
}
