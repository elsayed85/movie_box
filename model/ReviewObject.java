package com.movieboxpro.android.model;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.beanutils.PropertyUtils;
/* compiled from: Bean.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B1\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0006HÆ\u0003J5\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0006HÖ\u0001J\u0019\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r¨\u0006 "}, d2 = {"Lcom/movieboxpro/android/model/ReviewObject;", "Landroid/os/Parcelable;", "id", "", "box_type", "title", "", "name", "(IILjava/lang/String;Ljava/lang/String;)V", "getBox_type", "()I", "getId", "getName", "()Ljava/lang/String;", "getTitle", "component1", "component2", "component3", "component4", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ReviewObject implements Parcelable {
    public static final Parcelable.Creator<ReviewObject> CREATOR = new Creator();
    private final int box_type;
    private final int id;
    private final String name;
    private final String title;

    /* compiled from: Bean.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Creator implements Parcelable.Creator<ReviewObject> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final ReviewObject createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new ReviewObject(parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final ReviewObject[] newArray(int i) {
            return new ReviewObject[i];
        }
    }

    public ReviewObject() {
        this(0, 0, null, null, 15, null);
    }

    public static /* synthetic */ ReviewObject copy$default(ReviewObject reviewObject, int i, int i2, String str, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = reviewObject.id;
        }
        if ((i3 & 2) != 0) {
            i2 = reviewObject.box_type;
        }
        if ((i3 & 4) != 0) {
            str = reviewObject.title;
        }
        if ((i3 & 8) != 0) {
            str2 = reviewObject.name;
        }
        return reviewObject.copy(i, i2, str, str2);
    }

    public final int component1() {
        return this.id;
    }

    public final int component2() {
        return this.box_type;
    }

    public final String component3() {
        return this.title;
    }

    public final String component4() {
        return this.name;
    }

    public final ReviewObject copy(int i, int i2, String str, String str2) {
        return new ReviewObject(i, i2, str, str2);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ReviewObject) {
            ReviewObject reviewObject = (ReviewObject) obj;
            return this.id == reviewObject.id && this.box_type == reviewObject.box_type && Intrinsics.areEqual(this.title, reviewObject.title) && Intrinsics.areEqual(this.name, reviewObject.name);
        }
        return false;
    }

    public int hashCode() {
        int i = ((this.id * 31) + this.box_type) * 31;
        String str = this.title;
        int hashCode = (i + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.name;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "ReviewObject(id=" + this.id + ", box_type=" + this.box_type + ", title=" + ((Object) this.title) + ", name=" + ((Object) this.name) + PropertyUtils.MAPPED_DELIM2;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int i) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeInt(this.id);
        out.writeInt(this.box_type);
        out.writeString(this.title);
        out.writeString(this.name);
    }

    public ReviewObject(int i, int i2, String str, String str2) {
        this.id = i;
        this.box_type = i2;
        this.title = str;
        this.name = str2;
    }

    public /* synthetic */ ReviewObject(int i, int i2, String str, String str2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? 0 : i2, (i3 & 4) != 0 ? null : str, (i3 & 8) != 0 ? null : str2);
    }

    public final int getId() {
        return this.id;
    }

    public final int getBox_type() {
        return this.box_type;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getName() {
        return this.name;
    }
}
