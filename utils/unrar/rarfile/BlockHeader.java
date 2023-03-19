package com.movieboxpro.android.utils.unrar.rarfile;

import com.movieboxpro.android.utils.unrar.io.Raw;
/* loaded from: classes3.dex */
public class BlockHeader extends BaseBlock {
    public static final short blockHeaderSize = 4;
    private int dataSize;
    private int packSize;

    public BlockHeader() {
    }

    public BlockHeader(BlockHeader blockHeader) {
        super(blockHeader);
        int dataSize = blockHeader.getDataSize();
        this.packSize = dataSize;
        this.dataSize = dataSize;
        this.positionInFile = blockHeader.getPositionInFile();
    }

    public BlockHeader(BaseBlock baseBlock, byte[] bArr) {
        super(baseBlock);
        int readIntLittleEndian = Raw.readIntLittleEndian(bArr, 0);
        this.packSize = readIntLittleEndian;
        this.dataSize = readIntLittleEndian;
    }

    public int getDataSize() {
        return this.dataSize;
    }

    public int getPackSize() {
        return this.packSize;
    }

    @Override // com.movieboxpro.android.utils.unrar.rarfile.BaseBlock
    public void print() {
        super.print();
        System.out.print("DataSize: " + getDataSize() + " packSize: " + getPackSize());
    }
}
