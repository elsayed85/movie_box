package com.movieboxpro.android.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.beanutils.PropertyUtils;
/* compiled from: Bean.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J)\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\t\"\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0018"}, d2 = {"Lcom/movieboxpro/android/model/VideoThumb;", "", "seconds", "", "url", "", "total", "(ILjava/lang/String;I)V", "getSeconds", "()I", "getTotal", "setTotal", "(I)V", "getUrl", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class VideoThumb {
    private final int seconds;
    private int total;
    private final String url;

    public VideoThumb() {
        this(0, null, 0, 7, null);
    }

    public static /* synthetic */ VideoThumb copy$default(VideoThumb videoThumb, int i, String str, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = videoThumb.seconds;
        }
        if ((i3 & 2) != 0) {
            str = videoThumb.url;
        }
        if ((i3 & 4) != 0) {
            i2 = videoThumb.total;
        }
        return videoThumb.copy(i, str, i2);
    }

    public final int component1() {
        return this.seconds;
    }

    public final String component2() {
        return this.url;
    }

    public final int component3() {
        return this.total;
    }

    public final VideoThumb copy(int i, String str, int i2) {
        return new VideoThumb(i, str, i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof VideoThumb) {
            VideoThumb videoThumb = (VideoThumb) obj;
            return this.seconds == videoThumb.seconds && Intrinsics.areEqual(this.url, videoThumb.url) && this.total == videoThumb.total;
        }
        return false;
    }

    public int hashCode() {
        int i = this.seconds * 31;
        String str = this.url;
        return ((i + (str == null ? 0 : str.hashCode())) * 31) + this.total;
    }

    public String toString() {
        return "VideoThumb(seconds=" + this.seconds + ", url=" + ((Object) this.url) + ", total=" + this.total + PropertyUtils.MAPPED_DELIM2;
    }

    public VideoThumb(int i, String str, int i2) {
        this.seconds = i;
        this.url = str;
        this.total = i2;
    }

    public /* synthetic */ VideoThumb(int i, String str, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? null : str, (i3 & 4) != 0 ? 0 : i2);
    }

    public final int getSeconds() {
        return this.seconds;
    }

    public final String getUrl() {
        return this.url;
    }

    public final int getTotal() {
        return this.total;
    }

    public final void setTotal(int i) {
        this.total = i;
    }
}
