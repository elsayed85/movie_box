package com.movieboxpro.android.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.beanutils.PropertyUtils;
/* compiled from: Bean.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J9\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0018"}, d2 = {"Lcom/movieboxpro/android/model/BlockedUser;", "", "avatar", "", "uid", "username", "email", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAvatar", "()Ljava/lang/String;", "getEmail", "getUid", "getUsername", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BlockedUser {
    private final String avatar;
    private final String email;
    private final String uid;
    private final String username;

    public BlockedUser() {
        this(null, null, null, null, 15, null);
    }

    public static /* synthetic */ BlockedUser copy$default(BlockedUser blockedUser, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = blockedUser.avatar;
        }
        if ((i & 2) != 0) {
            str2 = blockedUser.uid;
        }
        if ((i & 4) != 0) {
            str3 = blockedUser.username;
        }
        if ((i & 8) != 0) {
            str4 = blockedUser.email;
        }
        return blockedUser.copy(str, str2, str3, str4);
    }

    public final String component1() {
        return this.avatar;
    }

    public final String component2() {
        return this.uid;
    }

    public final String component3() {
        return this.username;
    }

    public final String component4() {
        return this.email;
    }

    public final BlockedUser copy(String str, String str2, String str3, String str4) {
        return new BlockedUser(str, str2, str3, str4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BlockedUser) {
            BlockedUser blockedUser = (BlockedUser) obj;
            return Intrinsics.areEqual(this.avatar, blockedUser.avatar) && Intrinsics.areEqual(this.uid, blockedUser.uid) && Intrinsics.areEqual(this.username, blockedUser.username) && Intrinsics.areEqual(this.email, blockedUser.email);
        }
        return false;
    }

    public int hashCode() {
        String str = this.avatar;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.uid;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.username;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.email;
        return hashCode3 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        return "BlockedUser(avatar=" + ((Object) this.avatar) + ", uid=" + ((Object) this.uid) + ", username=" + ((Object) this.username) + ", email=" + ((Object) this.email) + PropertyUtils.MAPPED_DELIM2;
    }

    public BlockedUser(String str, String str2, String str3, String str4) {
        this.avatar = str;
        this.uid = str2;
        this.username = str3;
        this.email = str4;
    }

    public /* synthetic */ BlockedUser(String str, String str2, String str3, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4);
    }

    public final String getAvatar() {
        return this.avatar;
    }

    public final String getUid() {
        return this.uid;
    }

    public final String getUsername() {
        return this.username;
    }

    public final String getEmail() {
        return this.email;
    }
}
