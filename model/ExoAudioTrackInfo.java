package com.movieboxpro.android.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.beanutils.PropertyUtils;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: Bean.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B;\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003J?\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\u0019\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r¨\u0006$"}, d2 = {"Lcom/movieboxpro/android/model/ExoAudioTrackInfo;", "Landroid/os/Parcelable;", "renderIndex", "", "trackGroup", "Landroid/os/Bundle;", "groupIndex", "trackIndex", IjkMediaMeta.IJKM_KEY_FORMAT, "(ILandroid/os/Bundle;IILandroid/os/Bundle;)V", "getFormat", "()Landroid/os/Bundle;", "getGroupIndex", "()I", "getRenderIndex", "getTrackGroup", "getTrackIndex", "component1", "component2", "component3", "component4", "component5", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ExoAudioTrackInfo implements Parcelable {
    public static final Parcelable.Creator<ExoAudioTrackInfo> CREATOR = new Creator();
    private final Bundle format;
    private final int groupIndex;
    private final int renderIndex;
    private final Bundle trackGroup;
    private final int trackIndex;

    /* compiled from: Bean.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Creator implements Parcelable.Creator<ExoAudioTrackInfo> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final ExoAudioTrackInfo createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new ExoAudioTrackInfo(parcel.readInt(), parcel.readBundle(), parcel.readInt(), parcel.readInt(), parcel.readBundle());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final ExoAudioTrackInfo[] newArray(int i) {
            return new ExoAudioTrackInfo[i];
        }
    }

    public ExoAudioTrackInfo() {
        this(0, null, 0, 0, null, 31, null);
    }

    public static /* synthetic */ ExoAudioTrackInfo copy$default(ExoAudioTrackInfo exoAudioTrackInfo, int i, Bundle bundle, int i2, int i3, Bundle bundle2, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = exoAudioTrackInfo.renderIndex;
        }
        if ((i4 & 2) != 0) {
            bundle = exoAudioTrackInfo.trackGroup;
        }
        Bundle bundle3 = bundle;
        if ((i4 & 4) != 0) {
            i2 = exoAudioTrackInfo.groupIndex;
        }
        int i5 = i2;
        if ((i4 & 8) != 0) {
            i3 = exoAudioTrackInfo.trackIndex;
        }
        int i6 = i3;
        if ((i4 & 16) != 0) {
            bundle2 = exoAudioTrackInfo.format;
        }
        return exoAudioTrackInfo.copy(i, bundle3, i5, i6, bundle2);
    }

    public final int component1() {
        return this.renderIndex;
    }

    public final Bundle component2() {
        return this.trackGroup;
    }

    public final int component3() {
        return this.groupIndex;
    }

    public final int component4() {
        return this.trackIndex;
    }

    public final Bundle component5() {
        return this.format;
    }

    public final ExoAudioTrackInfo copy(int i, Bundle bundle, int i2, int i3, Bundle bundle2) {
        return new ExoAudioTrackInfo(i, bundle, i2, i3, bundle2);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ExoAudioTrackInfo) {
            ExoAudioTrackInfo exoAudioTrackInfo = (ExoAudioTrackInfo) obj;
            return this.renderIndex == exoAudioTrackInfo.renderIndex && Intrinsics.areEqual(this.trackGroup, exoAudioTrackInfo.trackGroup) && this.groupIndex == exoAudioTrackInfo.groupIndex && this.trackIndex == exoAudioTrackInfo.trackIndex && Intrinsics.areEqual(this.format, exoAudioTrackInfo.format);
        }
        return false;
    }

    public int hashCode() {
        int i = this.renderIndex * 31;
        Bundle bundle = this.trackGroup;
        int hashCode = (((((i + (bundle == null ? 0 : bundle.hashCode())) * 31) + this.groupIndex) * 31) + this.trackIndex) * 31;
        Bundle bundle2 = this.format;
        return hashCode + (bundle2 != null ? bundle2.hashCode() : 0);
    }

    public String toString() {
        return "ExoAudioTrackInfo(renderIndex=" + this.renderIndex + ", trackGroup=" + this.trackGroup + ", groupIndex=" + this.groupIndex + ", trackIndex=" + this.trackIndex + ", format=" + this.format + PropertyUtils.MAPPED_DELIM2;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int i) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeInt(this.renderIndex);
        out.writeBundle(this.trackGroup);
        out.writeInt(this.groupIndex);
        out.writeInt(this.trackIndex);
        out.writeBundle(this.format);
    }

    public ExoAudioTrackInfo(int i, Bundle bundle, int i2, int i3, Bundle bundle2) {
        this.renderIndex = i;
        this.trackGroup = bundle;
        this.groupIndex = i2;
        this.trackIndex = i3;
        this.format = bundle2;
    }

    public /* synthetic */ ExoAudioTrackInfo(int i, Bundle bundle, int i2, int i3, Bundle bundle2, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? -1 : i, (i4 & 2) != 0 ? null : bundle, (i4 & 4) != 0 ? -1 : i2, (i4 & 8) == 0 ? i3 : -1, (i4 & 16) != 0 ? null : bundle2);
    }

    public final int getRenderIndex() {
        return this.renderIndex;
    }

    public final Bundle getTrackGroup() {
        return this.trackGroup;
    }

    public final int getGroupIndex() {
        return this.groupIndex;
    }

    public final int getTrackIndex() {
        return this.trackIndex;
    }

    public final Bundle getFormat() {
        return this.format;
    }
}
