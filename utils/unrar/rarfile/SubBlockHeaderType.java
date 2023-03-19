package com.movieboxpro.android.utils.unrar.rarfile;
/* loaded from: classes3.dex */
public enum SubBlockHeaderType {
    EA_HEAD(256),
    UO_HEAD(257),
    MAC_HEAD(258),
    BEEA_HEAD(259),
    NTACL_HEAD(260),
    STREAM_HEAD(261);
    
    private short subblocktype;

    SubBlockHeaderType(short s) {
        this.subblocktype = s;
    }

    public boolean equals(short s) {
        return this.subblocktype == s;
    }

    public static SubBlockHeaderType findSubblockHeaderType(short s) {
        if (EA_HEAD.equals(s)) {
            return EA_HEAD;
        }
        if (UO_HEAD.equals(s)) {
            return UO_HEAD;
        }
        if (MAC_HEAD.equals(s)) {
            return MAC_HEAD;
        }
        if (BEEA_HEAD.equals(s)) {
            return BEEA_HEAD;
        }
        if (NTACL_HEAD.equals(s)) {
            return NTACL_HEAD;
        }
        if (STREAM_HEAD.equals(s)) {
            return STREAM_HEAD;
        }
        return null;
    }

    public short getSubblocktype() {
        return this.subblocktype;
    }
}
