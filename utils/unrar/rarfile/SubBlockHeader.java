package com.movieboxpro.android.utils.unrar.rarfile;

import com.movieboxpro.android.utils.unrar.io.Raw;
import java.io.PrintStream;
/* loaded from: classes3.dex */
public class SubBlockHeader extends BlockHeader {
    public static final short SubBlockHeaderSize = 3;
    private byte level;
    private short subType;

    public SubBlockHeader(SubBlockHeader subBlockHeader) {
        super(subBlockHeader);
        this.subType = subBlockHeader.getSubType().getSubblocktype();
        this.level = subBlockHeader.getLevel();
    }

    public SubBlockHeader(BlockHeader blockHeader, byte[] bArr) {
        super(blockHeader);
        this.subType = Raw.readShortLittleEndian(bArr, 0);
        this.level = (byte) (this.level | (bArr[2] & 255));
    }

    public byte getLevel() {
        return this.level;
    }

    public SubBlockHeaderType getSubType() {
        return SubBlockHeaderType.findSubblockHeaderType(this.subType);
    }

    @Override // com.movieboxpro.android.utils.unrar.rarfile.BlockHeader, com.movieboxpro.android.utils.unrar.rarfile.BaseBlock
    public void print() {
        super.print();
        PrintStream printStream = System.out;
        printStream.print("subtype: " + getSubType());
        PrintStream printStream2 = System.out;
        printStream2.print("level: " + ((int) this.level));
    }
}
