package com.movieboxpro.android.model;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.beanutils.PropertyUtils;
/* compiled from: Bean.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B;\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u001c\b\u0002\u0010\u0005\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006j\n\u0012\u0004\u0012\u00020\u0007\u0018\u0001`\b¢\u0006\u0002\u0010\tJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u001d\u0010\u0011\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006j\n\u0012\u0004\u0012\u00020\u0007\u0018\u0001`\bHÆ\u0003J?\u0010\u0012\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\u001c\b\u0002\u0010\u0005\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006j\n\u0012\u0004\u0012\u00020\u0007\u0018\u0001`\bHÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR%\u0010\u0005\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006j\n\u0012\u0004\u0012\u00020\u0007\u0018\u0001`\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/movieboxpro/android/model/AudioTrackItem;", "", "title", "", "time", "tracks", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/AudioTrackModel;", "Lkotlin/collections/ArrayList;", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/ArrayList;)V", "getTime", "()Ljava/lang/String;", "getTitle", "getTracks", "()Ljava/util/ArrayList;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AudioTrackItem {
    private final String time;
    private final String title;
    private final ArrayList<AudioTrackModel> tracks;

    public AudioTrackItem() {
        this(null, null, null, 7, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AudioTrackItem copy$default(AudioTrackItem audioTrackItem, String str, String str2, ArrayList arrayList, int i, Object obj) {
        if ((i & 1) != 0) {
            str = audioTrackItem.title;
        }
        if ((i & 2) != 0) {
            str2 = audioTrackItem.time;
        }
        if ((i & 4) != 0) {
            arrayList = audioTrackItem.tracks;
        }
        return audioTrackItem.copy(str, str2, arrayList);
    }

    public final String component1() {
        return this.title;
    }

    public final String component2() {
        return this.time;
    }

    public final ArrayList<AudioTrackModel> component3() {
        return this.tracks;
    }

    public final AudioTrackItem copy(String str, String str2, ArrayList<AudioTrackModel> arrayList) {
        return new AudioTrackItem(str, str2, arrayList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AudioTrackItem) {
            AudioTrackItem audioTrackItem = (AudioTrackItem) obj;
            return Intrinsics.areEqual(this.title, audioTrackItem.title) && Intrinsics.areEqual(this.time, audioTrackItem.time) && Intrinsics.areEqual(this.tracks, audioTrackItem.tracks);
        }
        return false;
    }

    public int hashCode() {
        String str = this.title;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.time;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        ArrayList<AudioTrackModel> arrayList = this.tracks;
        return hashCode2 + (arrayList != null ? arrayList.hashCode() : 0);
    }

    public String toString() {
        return "AudioTrackItem(title=" + ((Object) this.title) + ", time=" + ((Object) this.time) + ", tracks=" + this.tracks + PropertyUtils.MAPPED_DELIM2;
    }

    public AudioTrackItem(String str, String str2, ArrayList<AudioTrackModel> arrayList) {
        this.title = str;
        this.time = str2;
        this.tracks = arrayList;
    }

    public /* synthetic */ AudioTrackItem(String str, String str2, ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : arrayList);
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getTime() {
        return this.time;
    }

    public final ArrayList<AudioTrackModel> getTracks() {
        return this.tracks;
    }
}
