package com.movieboxpro.android.utils.unrar.rarfile;

import com.movieboxpro.android.utils.unrar.io.Raw;
import java.io.PrintStream;
/* loaded from: classes3.dex */
public class MacInfoHeader extends SubBlockHeader {
    public static final short MacInfoHeaderSize = 8;
    private int fileCreator;
    private int fileType;

    public MacInfoHeader(SubBlockHeader subBlockHeader, byte[] bArr) {
        super(subBlockHeader);
        this.fileType = Raw.readIntLittleEndian(bArr, 0);
        this.fileCreator = Raw.readIntLittleEndian(bArr, 4);
    }

    public int getFileCreator() {
        return this.fileCreator;
    }

    public void setFileCreator(int i) {
        this.fileCreator = i;
    }

    public int getFileType() {
        return this.fileType;
    }

    public void setFileType(int i) {
        this.fileType = i;
    }

    @Override // com.movieboxpro.android.utils.unrar.rarfile.SubBlockHeader, com.movieboxpro.android.utils.unrar.rarfile.BlockHeader, com.movieboxpro.android.utils.unrar.rarfile.BaseBlock
    public void print() {
        super.print();
        PrintStream printStream = System.out;
        printStream.print("filetype: " + this.fileType);
        PrintStream printStream2 = System.out;
        printStream2.print("creator :" + this.fileCreator);
    }
}
