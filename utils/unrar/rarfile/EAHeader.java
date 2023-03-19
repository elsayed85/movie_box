package com.movieboxpro.android.utils.unrar.rarfile;

import com.movieboxpro.android.utils.unrar.io.Raw;
import java.io.PrintStream;
/* loaded from: classes3.dex */
public class EAHeader extends SubBlockHeader {
    public static final short EAHeaderSize = 10;
    private int EACRC;
    private byte method;
    private int unpSize;
    private byte unpVer;

    public EAHeader(SubBlockHeader subBlockHeader, byte[] bArr) {
        super(subBlockHeader);
        this.unpSize = Raw.readIntLittleEndian(bArr, 0);
        this.unpVer = (byte) (this.unpVer | (bArr[4] & 255));
        this.method = (byte) (this.method | (bArr[5] & 255));
        this.EACRC = Raw.readIntLittleEndian(bArr, 6);
    }

    public int getEACRC() {
        return this.EACRC;
    }

    public byte getMethod() {
        return this.method;
    }

    public int getUnpSize() {
        return this.unpSize;
    }

    public byte getUnpVer() {
        return this.unpVer;
    }

    @Override // com.movieboxpro.android.utils.unrar.rarfile.SubBlockHeader, com.movieboxpro.android.utils.unrar.rarfile.BlockHeader, com.movieboxpro.android.utils.unrar.rarfile.BaseBlock
    public void print() {
        super.print();
        PrintStream printStream = System.out;
        printStream.print("unpSize: " + this.unpSize);
        PrintStream printStream2 = System.out;
        printStream2.print("unpVersion: " + ((int) this.unpVer));
        PrintStream printStream3 = System.out;
        printStream3.print("method: " + ((int) this.method));
        PrintStream printStream4 = System.out;
        printStream4.print("EACRC:" + this.EACRC);
    }
}
