package com.movieboxpro.android.model;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.beanutils.PropertyUtils;
/* compiled from: Bean.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J)\u0010\u0010\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\t\u0010\u0011\u001a\u00020\u0005HÖ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\u0019\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u001d"}, d2 = {"Lcom/movieboxpro/android/model/ImageItem;", "Landroid/os/Parcelable;", "url", "", "height", "", "width", "(Ljava/lang/String;II)V", "getHeight", "()I", "getUrl", "()Ljava/lang/String;", "getWidth", "component1", "component2", "component3", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ImageItem implements Parcelable {
    public static final Parcelable.Creator<ImageItem> CREATOR = new Creator();
    private final int height;
    private final String url;
    private final int width;

    /* compiled from: Bean.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Creator implements Parcelable.Creator<ImageItem> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final ImageItem createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new ImageItem(parcel.readString(), parcel.readInt(), parcel.readInt());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final ImageItem[] newArray(int i) {
            return new ImageItem[i];
        }
    }

    public ImageItem() {
        this(null, 0, 0, 7, null);
    }

    public static /* synthetic */ ImageItem copy$default(ImageItem imageItem, String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = imageItem.url;
        }
        if ((i3 & 2) != 0) {
            i = imageItem.height;
        }
        if ((i3 & 4) != 0) {
            i2 = imageItem.width;
        }
        return imageItem.copy(str, i, i2);
    }

    public final String component1() {
        return this.url;
    }

    public final int component2() {
        return this.height;
    }

    public final int component3() {
        return this.width;
    }

    public final ImageItem copy(String str, int i, int i2) {
        return new ImageItem(str, i, i2);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ImageItem) {
            ImageItem imageItem = (ImageItem) obj;
            return Intrinsics.areEqual(this.url, imageItem.url) && this.height == imageItem.height && this.width == imageItem.width;
        }
        return false;
    }

    public int hashCode() {
        String str = this.url;
        return ((((str == null ? 0 : str.hashCode()) * 31) + this.height) * 31) + this.width;
    }

    public String toString() {
        return "ImageItem(url=" + ((Object) this.url) + ", height=" + this.height + ", width=" + this.width + PropertyUtils.MAPPED_DELIM2;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int i) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeString(this.url);
        out.writeInt(this.height);
        out.writeInt(this.width);
    }

    public ImageItem(String str, int i, int i2) {
        this.url = str;
        this.height = i;
        this.width = i2;
    }

    public /* synthetic */ ImageItem(String str, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? null : str, (i3 & 2) != 0 ? 0 : i, (i3 & 4) != 0 ? 0 : i2);
    }

    public final String getUrl() {
        return this.url;
    }

    public final int getHeight() {
        return this.height;
    }

    public final int getWidth() {
        return this.width;
    }
}
