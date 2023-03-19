package com.movieboxpro.android.model;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.beanutils.PropertyUtils;
/* compiled from: Bean.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u000b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\rHÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001J\u0019\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\rHÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0019"}, d2 = {"Lcom/movieboxpro/android/model/AtItem;", "Landroid/os/Parcelable;", "uid", "", "username", "(Ljava/lang/String;Ljava/lang/String;)V", "getUid", "()Ljava/lang/String;", "getUsername", "component1", "component2", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AtItem implements Parcelable {
    public static final Parcelable.Creator<AtItem> CREATOR = new Creator();
    private final String uid;
    private final String username;

    /* compiled from: Bean.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Creator implements Parcelable.Creator<AtItem> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final AtItem createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new AtItem(parcel.readString(), parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final AtItem[] newArray(int i) {
            return new AtItem[i];
        }
    }

    public AtItem() {
        this(null, null, 3, null);
    }

    public static /* synthetic */ AtItem copy$default(AtItem atItem, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = atItem.uid;
        }
        if ((i & 2) != 0) {
            str2 = atItem.username;
        }
        return atItem.copy(str, str2);
    }

    public final String component1() {
        return this.uid;
    }

    public final String component2() {
        return this.username;
    }

    public final AtItem copy(String str, String str2) {
        return new AtItem(str, str2);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AtItem) {
            AtItem atItem = (AtItem) obj;
            return Intrinsics.areEqual(this.uid, atItem.uid) && Intrinsics.areEqual(this.username, atItem.username);
        }
        return false;
    }

    public int hashCode() {
        String str = this.uid;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.username;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "AtItem(uid=" + ((Object) this.uid) + ", username=" + ((Object) this.username) + PropertyUtils.MAPPED_DELIM2;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int i) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeString(this.uid);
        out.writeString(this.username);
    }

    public AtItem(String str, String str2) {
        this.uid = str;
        this.username = str2;
    }

    public /* synthetic */ AtItem(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2);
    }

    public final String getUid() {
        return this.uid;
    }

    public final String getUsername() {
        return this.username;
    }
}
