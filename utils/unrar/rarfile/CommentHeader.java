package com.movieboxpro.android.utils.unrar.rarfile;

import com.movieboxpro.android.utils.unrar.io.Raw;
/* loaded from: classes3.dex */
public class CommentHeader extends BaseBlock {
    public static final short commentHeaderSize = 6;
    private short commCRC;
    private byte unpMethod;
    private short unpSize;
    private byte unpVersion;

    public CommentHeader(BaseBlock baseBlock, byte[] bArr) {
        super(baseBlock);
        this.unpSize = Raw.readShortLittleEndian(bArr, 0);
        this.unpVersion = (byte) (this.unpVersion | (bArr[2] & 255));
        this.unpMethod = (byte) (this.unpMethod | (bArr[3] & 255));
        this.commCRC = Raw.readShortLittleEndian(bArr, 4);
    }

    public short getCommCRC() {
        return this.commCRC;
    }

    public byte getUnpMethod() {
        return this.unpMethod;
    }

    public short getUnpSize() {
        return this.unpSize;
    }

    public byte getUnpVersion() {
        return this.unpVersion;
    }
}
