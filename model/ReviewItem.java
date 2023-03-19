package com.movieboxpro.android.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.movieboxpro.android.utils.unrar.unpack.decode.Compress;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.beanutils.PropertyUtils;
/* compiled from: Bean.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\bB\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u009d\u0002\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\b\u0012\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\b\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0011\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0011\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0011\u0012\u0010\b\u0002\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\b\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0011\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001d\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001d¢\u0006\u0002\u0010 J\u000b\u0010F\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010G\u001a\u0004\u0018\u00010\u0011HÆ\u0003¢\u0006\u0002\u00100J\u0010\u0010H\u001a\u0004\u0018\u00010\u0011HÆ\u0003¢\u0006\u0002\u00100J\t\u0010I\u001a\u00020\u0011HÆ\u0003J\u0010\u0010J\u001a\u0004\u0018\u00010\u0011HÆ\u0003¢\u0006\u0002\u00100J\u0011\u0010K\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\bHÆ\u0003J\t\u0010L\u001a\u00020\u0011HÆ\u0003J\t\u0010M\u001a\u00020\u0011HÆ\u0003J\u000b\u0010N\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010O\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010P\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010Q\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010R\u001a\u0004\u0018\u00010\u001dHÆ\u0003J\u000b\u0010S\u001a\u0004\u0018\u00010\u001dHÆ\u0003J\u000b\u0010T\u001a\u0004\u0018\u00010\u001dHÆ\u0003J\t\u0010U\u001a\u00020\u0006HÆ\u0003J\u0011\u0010V\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bHÆ\u0003J\u000b\u0010W\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010X\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010Y\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\bHÆ\u0003J\u0011\u0010Z\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\bHÆ\u0003J\u0010\u0010[\u001a\u0004\u0018\u00010\u0011HÆ\u0003¢\u0006\u0002\u00100J¦\u0002\u0010\\\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\b2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u00112\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00112\u0010\b\u0002\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\b2\b\b\u0002\u0010\u0017\u001a\u00020\u00112\b\b\u0002\u0010\u0018\u001a\u00020\u00112\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001dHÆ\u0001¢\u0006\u0002\u0010]J\t\u0010^\u001a\u00020\u0011HÖ\u0001J\u0013\u0010_\u001a\u00020`2\b\u0010a\u001a\u0004\u0018\u00010bHÖ\u0003J\t\u0010c\u001a\u00020\u0011HÖ\u0001J\t\u0010d\u001a\u00020\u0003HÖ\u0001J\u0019\u0010e\u001a\u00020f2\u0006\u0010g\u001a\u00020h2\u0006\u0010i\u001a\u00020\u0011HÖ\u0001R\u0013\u0010\u001f\u001a\u0004\u0018\u00010\u001d¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0019\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010$R\u0011\u0010\u0018\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\u0017\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b*\u0010)R\u0019\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b+\u0010&R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b,\u0010$R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0019\u0010\f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b/\u0010&R\u001e\u0010\u0015\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u0010\n\u0002\u00103\u001a\u0004\b\u0015\u00100\"\u0004\b1\u00102R\u001a\u0010\u0014\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010)\"\u0004\b5\u00106R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b7\u0010$R\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u001d¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\"R\u0013\u0010\u001a\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b9\u0010$R\u0013\u0010\u001e\u001a\u0004\u0018\u00010\u001d¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\"R\u001e\u0010\u0012\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u0010\n\u0002\u00103\u001a\u0004\b;\u00100\"\u0004\b<\u00102R\"\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010&\"\u0004\b>\u0010?R\u001e\u0010\u0013\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u0010\n\u0002\u00103\u001a\u0004\b@\u00100\"\u0004\bA\u00102R\u001e\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u0010\n\u0002\u00103\u001a\u0004\bB\u00100\"\u0004\bC\u00102R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bD\u0010$R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bE\u0010$¨\u0006j"}, d2 = {"Lcom/movieboxpro/android/model/ReviewItem;", "Landroid/os/Parcelable;", "username", "", "uid", "dateline", "", "comment", "", "Lcom/movieboxpro/android/model/Comment;", "avatar", "comment_id", "img_list", "Lcom/movieboxpro/android/model/ImageItem;", "at", "Lcom/movieboxpro/android/model/AtItem;", "support_status", "", "reply", "support", "itemType", "is_delete", "son_reply", "box_type", "blocked", "mid", "pid", "actor_id", "movie", "Lcom/movieboxpro/android/model/ReviewObject;", "playlist", "actor", "(Ljava/lang/String;Ljava/lang/String;JLjava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;ILjava/lang/Integer;Ljava/util/List;IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/movieboxpro/android/model/ReviewObject;Lcom/movieboxpro/android/model/ReviewObject;Lcom/movieboxpro/android/model/ReviewObject;)V", "getActor", "()Lcom/movieboxpro/android/model/ReviewObject;", "getActor_id", "()Ljava/lang/String;", "getAt", "()Ljava/util/List;", "getAvatar", "getBlocked", "()I", "getBox_type", "getComment", "getComment_id", "getDateline", "()J", "getImg_list", "()Ljava/lang/Integer;", "set_delete", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getItemType", "setItemType", "(I)V", "getMid", "getMovie", "getPid", "getPlaylist", "getReply", "setReply", "getSon_reply", "setSon_reply", "(Ljava/util/List;)V", "getSupport", "setSupport", "getSupport_status", "setSupport_status", "getUid", "getUsername", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;JLjava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;ILjava/lang/Integer;Ljava/util/List;IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/movieboxpro/android/model/ReviewObject;Lcom/movieboxpro/android/model/ReviewObject;Lcom/movieboxpro/android/model/ReviewObject;)Lcom/movieboxpro/android/model/ReviewItem;", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ReviewItem implements Parcelable {
    public static final Parcelable.Creator<ReviewItem> CREATOR = new Creator();
    private final ReviewObject actor;
    private final String actor_id;
    private final List<AtItem> at;
    private final String avatar;
    private final int blocked;
    private final int box_type;
    private final List<Comment> comment;
    private final String comment_id;
    private final long dateline;
    private final List<ImageItem> img_list;
    private Integer is_delete;
    private int itemType;
    private final String mid;
    private final ReviewObject movie;
    private final String pid;
    private final ReviewObject playlist;
    private Integer reply;
    private List<ReviewItem> son_reply;
    private Integer support;
    private Integer support_status;
    private final String uid;
    private final String username;

    /* compiled from: Bean.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Creator implements Parcelable.Creator<ReviewItem> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final ReviewItem createFromParcel(Parcel parcel) {
            ArrayList arrayList;
            ArrayList arrayList2;
            ArrayList arrayList3;
            ArrayList arrayList4;
            int i;
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            long readLong = parcel.readLong();
            if (parcel.readInt() == 0) {
                arrayList = null;
            } else {
                int readInt = parcel.readInt();
                arrayList = new ArrayList(readInt);
                for (int i2 = 0; i2 != readInt; i2++) {
                    arrayList.add(Comment.CREATOR.createFromParcel(parcel));
                }
            }
            ArrayList arrayList5 = arrayList;
            String readString3 = parcel.readString();
            String readString4 = parcel.readString();
            if (parcel.readInt() == 0) {
                arrayList2 = null;
            } else {
                int readInt2 = parcel.readInt();
                arrayList2 = new ArrayList(readInt2);
                for (int i3 = 0; i3 != readInt2; i3++) {
                    arrayList2.add(ImageItem.CREATOR.createFromParcel(parcel));
                }
            }
            ArrayList arrayList6 = arrayList2;
            if (parcel.readInt() == 0) {
                arrayList3 = null;
            } else {
                int readInt3 = parcel.readInt();
                arrayList3 = new ArrayList(readInt3);
                for (int i4 = 0; i4 != readInt3; i4++) {
                    arrayList3.add(AtItem.CREATOR.createFromParcel(parcel));
                }
            }
            ArrayList arrayList7 = arrayList3;
            Integer valueOf = parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt());
            Integer valueOf2 = parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt());
            Integer valueOf3 = parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt());
            int readInt4 = parcel.readInt();
            Integer valueOf4 = parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt());
            if (parcel.readInt() == 0) {
                i = readInt4;
                arrayList4 = null;
            } else {
                int readInt5 = parcel.readInt();
                arrayList4 = new ArrayList(readInt5);
                i = readInt4;
                int i5 = 0;
                while (i5 != readInt5) {
                    arrayList4.add(ReviewItem.CREATOR.createFromParcel(parcel));
                    i5++;
                    readInt5 = readInt5;
                }
            }
            return new ReviewItem(readString, readString2, readLong, arrayList5, readString3, readString4, arrayList6, arrayList7, valueOf, valueOf2, valueOf3, i, valueOf4, arrayList4, parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() == 0 ? null : ReviewObject.CREATOR.createFromParcel(parcel), parcel.readInt() == 0 ? null : ReviewObject.CREATOR.createFromParcel(parcel), parcel.readInt() == 0 ? null : ReviewObject.CREATOR.createFromParcel(parcel));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final ReviewItem[] newArray(int i) {
            return new ReviewItem[i];
        }
    }

    public ReviewItem() {
        this(null, null, 0L, null, null, null, null, null, null, null, null, 0, null, null, 0, 0, null, null, null, null, null, null, Compress.MAXWINMASK, null);
    }

    public final String component1() {
        return this.username;
    }

    public final Integer component10() {
        return this.reply;
    }

    public final Integer component11() {
        return this.support;
    }

    public final int component12() {
        return this.itemType;
    }

    public final Integer component13() {
        return this.is_delete;
    }

    public final List<ReviewItem> component14() {
        return this.son_reply;
    }

    public final int component15() {
        return this.box_type;
    }

    public final int component16() {
        return this.blocked;
    }

    public final String component17() {
        return this.mid;
    }

    public final String component18() {
        return this.pid;
    }

    public final String component19() {
        return this.actor_id;
    }

    public final String component2() {
        return this.uid;
    }

    public final ReviewObject component20() {
        return this.movie;
    }

    public final ReviewObject component21() {
        return this.playlist;
    }

    public final ReviewObject component22() {
        return this.actor;
    }

    public final long component3() {
        return this.dateline;
    }

    public final List<Comment> component4() {
        return this.comment;
    }

    public final String component5() {
        return this.avatar;
    }

    public final String component6() {
        return this.comment_id;
    }

    public final List<ImageItem> component7() {
        return this.img_list;
    }

    public final List<AtItem> component8() {
        return this.at;
    }

    public final Integer component9() {
        return this.support_status;
    }

    public final ReviewItem copy(String str, String str2, long j, List<Comment> list, String str3, String str4, List<ImageItem> list2, List<AtItem> list3, Integer num, Integer num2, Integer num3, int i, Integer num4, List<ReviewItem> list4, int i2, int i3, String str5, String str6, String str7, ReviewObject reviewObject, ReviewObject reviewObject2, ReviewObject reviewObject3) {
        return new ReviewItem(str, str2, j, list, str3, str4, list2, list3, num, num2, num3, i, num4, list4, i2, i3, str5, str6, str7, reviewObject, reviewObject2, reviewObject3);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ReviewItem) {
            ReviewItem reviewItem = (ReviewItem) obj;
            return Intrinsics.areEqual(this.username, reviewItem.username) && Intrinsics.areEqual(this.uid, reviewItem.uid) && this.dateline == reviewItem.dateline && Intrinsics.areEqual(this.comment, reviewItem.comment) && Intrinsics.areEqual(this.avatar, reviewItem.avatar) && Intrinsics.areEqual(this.comment_id, reviewItem.comment_id) && Intrinsics.areEqual(this.img_list, reviewItem.img_list) && Intrinsics.areEqual(this.at, reviewItem.at) && Intrinsics.areEqual(this.support_status, reviewItem.support_status) && Intrinsics.areEqual(this.reply, reviewItem.reply) && Intrinsics.areEqual(this.support, reviewItem.support) && this.itemType == reviewItem.itemType && Intrinsics.areEqual(this.is_delete, reviewItem.is_delete) && Intrinsics.areEqual(this.son_reply, reviewItem.son_reply) && this.box_type == reviewItem.box_type && this.blocked == reviewItem.blocked && Intrinsics.areEqual(this.mid, reviewItem.mid) && Intrinsics.areEqual(this.pid, reviewItem.pid) && Intrinsics.areEqual(this.actor_id, reviewItem.actor_id) && Intrinsics.areEqual(this.movie, reviewItem.movie) && Intrinsics.areEqual(this.playlist, reviewItem.playlist) && Intrinsics.areEqual(this.actor, reviewItem.actor);
        }
        return false;
    }

    public int hashCode() {
        String str = this.username;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.uid;
        int hashCode2 = (((hashCode + (str2 == null ? 0 : str2.hashCode())) * 31) + C$r8$backportedMethods$utility$Long$1$hashCode.hashCode(this.dateline)) * 31;
        List<Comment> list = this.comment;
        int hashCode3 = (hashCode2 + (list == null ? 0 : list.hashCode())) * 31;
        String str3 = this.avatar;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.comment_id;
        int hashCode5 = (hashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        List<ImageItem> list2 = this.img_list;
        int hashCode6 = (hashCode5 + (list2 == null ? 0 : list2.hashCode())) * 31;
        List<AtItem> list3 = this.at;
        int hashCode7 = (hashCode6 + (list3 == null ? 0 : list3.hashCode())) * 31;
        Integer num = this.support_status;
        int hashCode8 = (hashCode7 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.reply;
        int hashCode9 = (hashCode8 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.support;
        int hashCode10 = (((hashCode9 + (num3 == null ? 0 : num3.hashCode())) * 31) + this.itemType) * 31;
        Integer num4 = this.is_delete;
        int hashCode11 = (hashCode10 + (num4 == null ? 0 : num4.hashCode())) * 31;
        List<ReviewItem> list4 = this.son_reply;
        int hashCode12 = (((((hashCode11 + (list4 == null ? 0 : list4.hashCode())) * 31) + this.box_type) * 31) + this.blocked) * 31;
        String str5 = this.mid;
        int hashCode13 = (hashCode12 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.pid;
        int hashCode14 = (hashCode13 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.actor_id;
        int hashCode15 = (hashCode14 + (str7 == null ? 0 : str7.hashCode())) * 31;
        ReviewObject reviewObject = this.movie;
        int hashCode16 = (hashCode15 + (reviewObject == null ? 0 : reviewObject.hashCode())) * 31;
        ReviewObject reviewObject2 = this.playlist;
        int hashCode17 = (hashCode16 + (reviewObject2 == null ? 0 : reviewObject2.hashCode())) * 31;
        ReviewObject reviewObject3 = this.actor;
        return hashCode17 + (reviewObject3 != null ? reviewObject3.hashCode() : 0);
    }

    public String toString() {
        return "ReviewItem(username=" + ((Object) this.username) + ", uid=" + ((Object) this.uid) + ", dateline=" + this.dateline + ", comment=" + this.comment + ", avatar=" + ((Object) this.avatar) + ", comment_id=" + ((Object) this.comment_id) + ", img_list=" + this.img_list + ", at=" + this.at + ", support_status=" + this.support_status + ", reply=" + this.reply + ", support=" + this.support + ", itemType=" + this.itemType + ", is_delete=" + this.is_delete + ", son_reply=" + this.son_reply + ", box_type=" + this.box_type + ", blocked=" + this.blocked + ", mid=" + ((Object) this.mid) + ", pid=" + ((Object) this.pid) + ", actor_id=" + ((Object) this.actor_id) + ", movie=" + this.movie + ", playlist=" + this.playlist + ", actor=" + this.actor + PropertyUtils.MAPPED_DELIM2;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int i) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeString(this.username);
        out.writeString(this.uid);
        out.writeLong(this.dateline);
        List<Comment> list = this.comment;
        if (list == null) {
            out.writeInt(0);
        } else {
            out.writeInt(1);
            out.writeInt(list.size());
            for (Comment comment : list) {
                comment.writeToParcel(out, i);
            }
        }
        out.writeString(this.avatar);
        out.writeString(this.comment_id);
        List<ImageItem> list2 = this.img_list;
        if (list2 == null) {
            out.writeInt(0);
        } else {
            out.writeInt(1);
            out.writeInt(list2.size());
            for (ImageItem imageItem : list2) {
                imageItem.writeToParcel(out, i);
            }
        }
        List<AtItem> list3 = this.at;
        if (list3 == null) {
            out.writeInt(0);
        } else {
            out.writeInt(1);
            out.writeInt(list3.size());
            for (AtItem atItem : list3) {
                atItem.writeToParcel(out, i);
            }
        }
        Integer num = this.support_status;
        if (num == null) {
            out.writeInt(0);
        } else {
            out.writeInt(1);
            out.writeInt(num.intValue());
        }
        Integer num2 = this.reply;
        if (num2 == null) {
            out.writeInt(0);
        } else {
            out.writeInt(1);
            out.writeInt(num2.intValue());
        }
        Integer num3 = this.support;
        if (num3 == null) {
            out.writeInt(0);
        } else {
            out.writeInt(1);
            out.writeInt(num3.intValue());
        }
        out.writeInt(this.itemType);
        Integer num4 = this.is_delete;
        if (num4 == null) {
            out.writeInt(0);
        } else {
            out.writeInt(1);
            out.writeInt(num4.intValue());
        }
        List<ReviewItem> list4 = this.son_reply;
        if (list4 == null) {
            out.writeInt(0);
        } else {
            out.writeInt(1);
            out.writeInt(list4.size());
            for (ReviewItem reviewItem : list4) {
                reviewItem.writeToParcel(out, i);
            }
        }
        out.writeInt(this.box_type);
        out.writeInt(this.blocked);
        out.writeString(this.mid);
        out.writeString(this.pid);
        out.writeString(this.actor_id);
        ReviewObject reviewObject = this.movie;
        if (reviewObject == null) {
            out.writeInt(0);
        } else {
            out.writeInt(1);
            reviewObject.writeToParcel(out, i);
        }
        ReviewObject reviewObject2 = this.playlist;
        if (reviewObject2 == null) {
            out.writeInt(0);
        } else {
            out.writeInt(1);
            reviewObject2.writeToParcel(out, i);
        }
        ReviewObject reviewObject3 = this.actor;
        if (reviewObject3 == null) {
            out.writeInt(0);
            return;
        }
        out.writeInt(1);
        reviewObject3.writeToParcel(out, i);
    }

    public ReviewItem(String str, String str2, long j, List<Comment> list, String str3, String str4, List<ImageItem> list2, List<AtItem> list3, Integer num, Integer num2, Integer num3, int i, Integer num4, List<ReviewItem> list4, int i2, int i3, String str5, String str6, String str7, ReviewObject reviewObject, ReviewObject reviewObject2, ReviewObject reviewObject3) {
        this.username = str;
        this.uid = str2;
        this.dateline = j;
        this.comment = list;
        this.avatar = str3;
        this.comment_id = str4;
        this.img_list = list2;
        this.at = list3;
        this.support_status = num;
        this.reply = num2;
        this.support = num3;
        this.itemType = i;
        this.is_delete = num4;
        this.son_reply = list4;
        this.box_type = i2;
        this.blocked = i3;
        this.mid = str5;
        this.pid = str6;
        this.actor_id = str7;
        this.movie = reviewObject;
        this.playlist = reviewObject2;
        this.actor = reviewObject3;
    }

    public final String getUsername() {
        return this.username;
    }

    public final String getUid() {
        return this.uid;
    }

    public final long getDateline() {
        return this.dateline;
    }

    public final List<Comment> getComment() {
        return this.comment;
    }

    public final String getAvatar() {
        return this.avatar;
    }

    public final String getComment_id() {
        return this.comment_id;
    }

    public final List<ImageItem> getImg_list() {
        return this.img_list;
    }

    public final List<AtItem> getAt() {
        return this.at;
    }

    public /* synthetic */ ReviewItem(String str, String str2, long j, List list, String str3, String str4, List list2, List list3, Integer num, Integer num2, Integer num3, int i, Integer num4, List list4, int i2, int i3, String str5, String str6, String str7, ReviewObject reviewObject, ReviewObject reviewObject2, ReviewObject reviewObject3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? null : str, (i4 & 2) != 0 ? null : str2, (i4 & 4) != 0 ? 0L : j, (i4 & 8) != 0 ? null : list, (i4 & 16) != 0 ? null : str3, (i4 & 32) != 0 ? null : str4, (i4 & 64) != 0 ? null : list2, (i4 & 128) != 0 ? null : list3, (i4 & 256) != 0 ? 0 : num, (i4 & 512) != 0 ? 0 : num2, (i4 & 1024) != 0 ? 0 : num3, (i4 & 2048) != 0 ? 0 : i, (i4 & 4096) != 0 ? 0 : num4, (i4 & 8192) != 0 ? null : list4, (i4 & 16384) != 0 ? 1 : i2, (i4 & 32768) == 0 ? i3 : 1, (i4 & 65536) != 0 ? null : str5, (i4 & 131072) != 0 ? null : str6, (i4 & 262144) != 0 ? null : str7, (i4 & 524288) != 0 ? null : reviewObject, (i4 & 1048576) != 0 ? null : reviewObject2, (i4 & 2097152) != 0 ? null : reviewObject3);
    }

    public final Integer getSupport_status() {
        return this.support_status;
    }

    public final void setSupport_status(Integer num) {
        this.support_status = num;
    }

    public final Integer getReply() {
        return this.reply;
    }

    public final void setReply(Integer num) {
        this.reply = num;
    }

    public final Integer getSupport() {
        return this.support;
    }

    public final void setSupport(Integer num) {
        this.support = num;
    }

    public final int getItemType() {
        return this.itemType;
    }

    public final void setItemType(int i) {
        this.itemType = i;
    }

    public final Integer is_delete() {
        return this.is_delete;
    }

    public final void set_delete(Integer num) {
        this.is_delete = num;
    }

    public final List<ReviewItem> getSon_reply() {
        return this.son_reply;
    }

    public final void setSon_reply(List<ReviewItem> list) {
        this.son_reply = list;
    }

    public final int getBox_type() {
        return this.box_type;
    }

    public final int getBlocked() {
        return this.blocked;
    }

    public final String getMid() {
        return this.mid;
    }

    public final String getPid() {
        return this.pid;
    }

    public final String getActor_id() {
        return this.actor_id;
    }

    public final ReviewObject getMovie() {
        return this.movie;
    }

    public final ReviewObject getPlaylist() {
        return this.playlist;
    }

    public final ReviewObject getActor() {
        return this.actor;
    }
}
