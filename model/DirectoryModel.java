package com.movieboxpro.android.model;

import com.movieboxpro.android.service.FileDownloadService;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.beanutils.PropertyUtils;
/* compiled from: DirectoryModel.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0019\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J;\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u00052\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\u000b\"\u0004\b\u000e\u0010\rR\u001a\u0010\b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\u000b\"\u0004\b\u000f\u0010\rR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0011\"\u0004\b\u0015\u0010\u0013¨\u0006!"}, d2 = {"Lcom/movieboxpro/android/model/DirectoryModel;", "", "name", "", "checked", "", FileDownloadService.PARAMS_KEY_PATH, "isDir", "isEdit", "(Ljava/lang/String;ZLjava/lang/String;ZZ)V", "getChecked", "()Z", "setChecked", "(Z)V", "setDir", "setEdit", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getPath", "setPath", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DirectoryModel {
    private boolean checked;
    private boolean isDir;
    private boolean isEdit;
    private String name;
    private String path;

    public static /* synthetic */ DirectoryModel copy$default(DirectoryModel directoryModel, String str, boolean z, String str2, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = directoryModel.name;
        }
        if ((i & 2) != 0) {
            z = directoryModel.checked;
        }
        boolean z4 = z;
        if ((i & 4) != 0) {
            str2 = directoryModel.path;
        }
        String str3 = str2;
        if ((i & 8) != 0) {
            z2 = directoryModel.isDir;
        }
        boolean z5 = z2;
        if ((i & 16) != 0) {
            z3 = directoryModel.isEdit;
        }
        return directoryModel.copy(str, z4, str3, z5, z3);
    }

    public final String component1() {
        return this.name;
    }

    public final boolean component2() {
        return this.checked;
    }

    public final String component3() {
        return this.path;
    }

    public final boolean component4() {
        return this.isDir;
    }

    public final boolean component5() {
        return this.isEdit;
    }

    public final DirectoryModel copy(String name, boolean z, String path, boolean z2, boolean z3) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(path, "path");
        return new DirectoryModel(name, z, path, z2, z3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DirectoryModel) {
            DirectoryModel directoryModel = (DirectoryModel) obj;
            return Intrinsics.areEqual(this.name, directoryModel.name) && this.checked == directoryModel.checked && Intrinsics.areEqual(this.path, directoryModel.path) && this.isDir == directoryModel.isDir && this.isEdit == directoryModel.isEdit;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = this.name.hashCode() * 31;
        boolean z = this.checked;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int hashCode2 = (((hashCode + i) * 31) + this.path.hashCode()) * 31;
        boolean z2 = this.isDir;
        int i2 = z2;
        if (z2 != 0) {
            i2 = 1;
        }
        int i3 = (hashCode2 + i2) * 31;
        boolean z3 = this.isEdit;
        return i3 + (z3 ? 1 : z3 ? 1 : 0);
    }

    public String toString() {
        return "DirectoryModel(name=" + this.name + ", checked=" + this.checked + ", path=" + this.path + ", isDir=" + this.isDir + ", isEdit=" + this.isEdit + PropertyUtils.MAPPED_DELIM2;
    }

    public DirectoryModel(String name, boolean z, String path, boolean z2, boolean z3) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(path, "path");
        this.name = name;
        this.checked = z;
        this.path = path;
        this.isDir = z2;
        this.isEdit = z3;
    }

    public /* synthetic */ DirectoryModel(String str, boolean z, String str2, boolean z2, boolean z3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? false : z, str2, (i & 8) != 0 ? true : z2, (i & 16) != 0 ? false : z3);
    }

    public final boolean getChecked() {
        return this.checked;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPath() {
        return this.path;
    }

    public final boolean isDir() {
        return this.isDir;
    }

    public final boolean isEdit() {
        return this.isEdit;
    }

    public final void setChecked(boolean z) {
        this.checked = z;
    }

    public final void setDir(boolean z) {
        this.isDir = z;
    }

    public final void setEdit(boolean z) {
        this.isEdit = z;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public final void setPath(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.path = str;
    }
}
