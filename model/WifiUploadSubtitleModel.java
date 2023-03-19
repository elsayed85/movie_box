package com.movieboxpro.android.model;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import master.flame.danmaku.danmaku.parser.IDataSource;
import org.apache.commons.beanutils.PropertyUtils;
/* compiled from: WifiUploadSubtitleModel.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0018\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J3\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010 \u001a\u00020\n2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020#HÖ\u0001J\t\u0010$\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0014\"\u0004\b\u0018\u0010\u0016R\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0014\"\u0004\b\u001a\u0010\u0016¨\u0006%"}, d2 = {"Lcom/movieboxpro/android/model/WifiUploadSubtitleModel;", "", "name", "", IDataSource.SCHEME_FILE_TAG, "Ljava/io/File;", "size", "time", "(Ljava/lang/String;Ljava/io/File;Ljava/lang/String;Ljava/lang/String;)V", "checked", "", "getChecked", "()Z", "setChecked", "(Z)V", "getFile", "()Ljava/io/File;", "setFile", "(Ljava/io/File;)V", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getSize", "setSize", "getTime", "setTime", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WifiUploadSubtitleModel {
    private boolean checked;
    private File file;
    private String name;
    private String size;
    private String time;

    public WifiUploadSubtitleModel() {
        this(null, null, null, null, 15, null);
    }

    public static /* synthetic */ WifiUploadSubtitleModel copy$default(WifiUploadSubtitleModel wifiUploadSubtitleModel, String str, File file, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = wifiUploadSubtitleModel.name;
        }
        if ((i & 2) != 0) {
            file = wifiUploadSubtitleModel.file;
        }
        if ((i & 4) != 0) {
            str2 = wifiUploadSubtitleModel.size;
        }
        if ((i & 8) != 0) {
            str3 = wifiUploadSubtitleModel.time;
        }
        return wifiUploadSubtitleModel.copy(str, file, str2, str3);
    }

    public final String component1() {
        return this.name;
    }

    public final File component2() {
        return this.file;
    }

    public final String component3() {
        return this.size;
    }

    public final String component4() {
        return this.time;
    }

    public final WifiUploadSubtitleModel copy(String name, File file, String size, String time) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(size, "size");
        Intrinsics.checkNotNullParameter(time, "time");
        return new WifiUploadSubtitleModel(name, file, size, time);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof WifiUploadSubtitleModel) {
            WifiUploadSubtitleModel wifiUploadSubtitleModel = (WifiUploadSubtitleModel) obj;
            return Intrinsics.areEqual(this.name, wifiUploadSubtitleModel.name) && Intrinsics.areEqual(this.file, wifiUploadSubtitleModel.file) && Intrinsics.areEqual(this.size, wifiUploadSubtitleModel.size) && Intrinsics.areEqual(this.time, wifiUploadSubtitleModel.time);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.name.hashCode() * 31;
        File file = this.file;
        return ((((hashCode + (file == null ? 0 : file.hashCode())) * 31) + this.size.hashCode()) * 31) + this.time.hashCode();
    }

    public String toString() {
        return "WifiUploadSubtitleModel(name=" + this.name + ", file=" + this.file + ", size=" + this.size + ", time=" + this.time + PropertyUtils.MAPPED_DELIM2;
    }

    public WifiUploadSubtitleModel(String name, File file, String size, String time) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(size, "size");
        Intrinsics.checkNotNullParameter(time, "time");
        this.name = name;
        this.file = file;
        this.size = size;
        this.time = time;
    }

    public /* synthetic */ WifiUploadSubtitleModel(String str, File file, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? null : file, (i & 4) != 0 ? "" : str2, (i & 8) != 0 ? "" : str3);
    }

    public final File getFile() {
        return this.file;
    }

    public final String getName() {
        return this.name;
    }

    public final String getSize() {
        return this.size;
    }

    public final String getTime() {
        return this.time;
    }

    public final void setFile(File file) {
        this.file = file;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public final void setSize(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.size = str;
    }

    public final void setTime(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.time = str;
    }

    public final boolean getChecked() {
        return this.checked;
    }

    public final void setChecked(boolean z) {
        this.checked = z;
    }
}
