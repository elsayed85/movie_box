package com.movieboxpro.android.utils.unrar.rarfile;

import com.movieboxpro.android.utils.unrar.io.Raw;
/* loaded from: classes3.dex */
public class ProtectHeader extends BlockHeader {
    public static final int protectHeaderSize = 8;
    private byte mark;
    private short recSectors;
    private int totalBlocks;
    private byte version;

    public ProtectHeader(BlockHeader blockHeader, byte[] bArr) {
        super(blockHeader);
        this.version = (byte) (this.version | (bArr[0] & 255));
        this.recSectors = Raw.readShortLittleEndian(bArr, 0);
        this.totalBlocks = Raw.readIntLittleEndian(bArr, 2);
        this.mark = (byte) (this.mark | (bArr[6] & 255));
    }

    public byte getMark() {
        return this.mark;
    }

    public short getRecSectors() {
        return this.recSectors;
    }

    public int getTotalBlocks() {
        return this.totalBlocks;
    }

    public byte getVersion() {
        return this.version;
    }
}
