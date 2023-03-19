package com.movieboxpro.android.model;

import com.movieboxpro.android.service.FileDownloadService;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.beanutils.PropertyUtils;
/* compiled from: Bean.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J7\u0010\u0015\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00072\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\n¨\u0006\u001b"}, d2 = {"Lcom/movieboxpro/android/model/DownloadPath;", "", FileDownloadService.PARAMS_KEY_PATH, "", "title", "freeSpace", "select", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getFreeSpace", "()Ljava/lang/String;", "getPath", "getSelect", "()Z", "setSelect", "(Z)V", "getTitle", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DownloadPath {
    private final String freeSpace;
    private final String path;
    private boolean select;
    private final String title;

    public DownloadPath() {
        this(null, null, null, false, 15, null);
    }

    public static /* synthetic */ DownloadPath copy$default(DownloadPath downloadPath, String str, String str2, String str3, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = downloadPath.path;
        }
        if ((i & 2) != 0) {
            str2 = downloadPath.title;
        }
        if ((i & 4) != 0) {
            str3 = downloadPath.freeSpace;
        }
        if ((i & 8) != 0) {
            z = downloadPath.select;
        }
        return downloadPath.copy(str, str2, str3, z);
    }

    public final String component1() {
        return this.path;
    }

    public final String component2() {
        return this.title;
    }

    public final String component3() {
        return this.freeSpace;
    }

    public final boolean component4() {
        return this.select;
    }

    public final DownloadPath copy(String str, String str2, String str3, boolean z) {
        return new DownloadPath(str, str2, str3, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DownloadPath) {
            DownloadPath downloadPath = (DownloadPath) obj;
            return Intrinsics.areEqual(this.path, downloadPath.path) && Intrinsics.areEqual(this.title, downloadPath.title) && Intrinsics.areEqual(this.freeSpace, downloadPath.freeSpace) && this.select == downloadPath.select;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.path;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.title;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.freeSpace;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        boolean z = this.select;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode3 + i;
    }

    public String toString() {
        return "DownloadPath(path=" + ((Object) this.path) + ", title=" + ((Object) this.title) + ", freeSpace=" + ((Object) this.freeSpace) + ", select=" + this.select + PropertyUtils.MAPPED_DELIM2;
    }

    public DownloadPath(String str, String str2, String str3, boolean z) {
        this.path = str;
        this.title = str2;
        this.freeSpace = str3;
        this.select = z;
    }

    public /* synthetic */ DownloadPath(String str, String str2, String str3, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? false : z);
    }

    public final String getPath() {
        return this.path;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getFreeSpace() {
        return this.freeSpace;
    }

    public final boolean getSelect() {
        return this.select;
    }

    public final void setSelect(boolean z) {
        this.select = z;
    }
}
