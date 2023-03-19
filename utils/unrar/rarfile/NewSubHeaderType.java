package com.movieboxpro.android.utils.unrar.rarfile;

import java.util.Arrays;
/* loaded from: classes3.dex */
public class NewSubHeaderType {
    private byte[] headerTypes;
    public static final NewSubHeaderType SUBHEAD_TYPE_CMT = new NewSubHeaderType(new byte[]{67, 77, 84});
    public static final NewSubHeaderType SUBHEAD_TYPE_ACL = new NewSubHeaderType(new byte[]{65, 67, 76});
    public static final NewSubHeaderType SUBHEAD_TYPE_STREAM = new NewSubHeaderType(new byte[]{83, 84, 77});
    public static final NewSubHeaderType SUBHEAD_TYPE_UOWNER = new NewSubHeaderType(new byte[]{85, 79, 87});
    public static final NewSubHeaderType SUBHEAD_TYPE_AV = new NewSubHeaderType(new byte[]{65, 86});
    public static final NewSubHeaderType SUBHEAD_TYPE_RR = new NewSubHeaderType(new byte[]{82, 82});
    public static final NewSubHeaderType SUBHEAD_TYPE_OS2EA = new NewSubHeaderType(new byte[]{69, 65, 50});
    public static final NewSubHeaderType SUBHEAD_TYPE_BEOSEA = new NewSubHeaderType(new byte[]{69, 65, 66, 69});

    private NewSubHeaderType(byte[] bArr) {
        this.headerTypes = bArr;
    }

    public boolean byteEquals(byte[] bArr) {
        return Arrays.equals(this.headerTypes, bArr);
    }

    public String toString() {
        return new String(this.headerTypes);
    }
}
