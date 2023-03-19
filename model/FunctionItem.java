package com.movieboxpro.android.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.beanutils.PropertyUtils;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: Bean.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lcom/movieboxpro/android/model/FunctionItem;", "", "title", "", IjkMediaMeta.IJKM_KEY_TYPE, "", "(Ljava/lang/String;I)V", "getTitle", "()Ljava/lang/String;", "getType", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FunctionItem {
    private final String title;
    private final int type;

    public FunctionItem() {
        this(null, 0, 3, null);
    }

    public static /* synthetic */ FunctionItem copy$default(FunctionItem functionItem, String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = functionItem.title;
        }
        if ((i2 & 2) != 0) {
            i = functionItem.type;
        }
        return functionItem.copy(str, i);
    }

    public final String component1() {
        return this.title;
    }

    public final int component2() {
        return this.type;
    }

    public final FunctionItem copy(String str, int i) {
        return new FunctionItem(str, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FunctionItem) {
            FunctionItem functionItem = (FunctionItem) obj;
            return Intrinsics.areEqual(this.title, functionItem.title) && this.type == functionItem.type;
        }
        return false;
    }

    public int hashCode() {
        String str = this.title;
        return ((str == null ? 0 : str.hashCode()) * 31) + this.type;
    }

    public String toString() {
        return "FunctionItem(title=" + ((Object) this.title) + ", type=" + this.type + PropertyUtils.MAPPED_DELIM2;
    }

    public FunctionItem(String str, int i) {
        this.title = str;
        this.type = i;
    }

    public /* synthetic */ FunctionItem(String str, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? 0 : i);
    }

    public final String getTitle() {
        return this.title;
    }

    public final int getType() {
        return this.type;
    }
}
