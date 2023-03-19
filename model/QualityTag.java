package com.movieboxpro.android.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.beanutils.PropertyUtils;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: Bean.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0005HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lcom/movieboxpro/android/model/QualityTag;", "", IjkMediaMeta.IJKM_KEY_TYPE, "", "tag", "", "(ILjava/lang/String;)V", "getTag", "()Ljava/lang/String;", "getType", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class QualityTag {
    private final String tag;
    private final int type;

    public QualityTag() {
        this(0, null, 3, null);
    }

    public static /* synthetic */ QualityTag copy$default(QualityTag qualityTag, int i, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = qualityTag.type;
        }
        if ((i2 & 2) != 0) {
            str = qualityTag.tag;
        }
        return qualityTag.copy(i, str);
    }

    public final int component1() {
        return this.type;
    }

    public final String component2() {
        return this.tag;
    }

    public final QualityTag copy(int i, String str) {
        return new QualityTag(i, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof QualityTag) {
            QualityTag qualityTag = (QualityTag) obj;
            return this.type == qualityTag.type && Intrinsics.areEqual(this.tag, qualityTag.tag);
        }
        return false;
    }

    public int hashCode() {
        int i = this.type * 31;
        String str = this.tag;
        return i + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "QualityTag(type=" + this.type + ", tag=" + ((Object) this.tag) + PropertyUtils.MAPPED_DELIM2;
    }

    public QualityTag(int i, String str) {
        this.type = i;
        this.tag = str;
    }

    public /* synthetic */ QualityTag(int i, String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 1 : i, (i2 & 2) != 0 ? null : str);
    }

    public final int getType() {
        return this.type;
    }

    public final String getTag() {
        return this.tag;
    }
}
