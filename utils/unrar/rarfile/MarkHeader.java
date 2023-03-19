package com.movieboxpro.android.utils.unrar.rarfile;

import com.movieboxpro.android.utils.unrar.io.Raw;
import java.io.PrintStream;
/* loaded from: classes3.dex */
public class MarkHeader extends BaseBlock {
    private boolean oldFormat;

    public MarkHeader(BaseBlock baseBlock) {
        super(baseBlock);
        this.oldFormat = false;
    }

    public boolean isValid() {
        return getHeadCRC() == 24914 && getHeaderType() == UnrarHeadertype.MarkHeader && getFlags() == 6689 && getHeaderSize() == 7;
    }

    public boolean isSignature() {
        byte[] bArr = new byte[7];
        Raw.writeShortLittleEndian(bArr, 0, this.headCRC);
        bArr[2] = this.headerType;
        Raw.writeShortLittleEndian(bArr, 3, this.flags);
        Raw.writeShortLittleEndian(bArr, 5, this.headerSize);
        if (bArr[0] == 82) {
            if (bArr[1] == 69 && bArr[2] == 126 && bArr[3] == 94) {
                this.oldFormat = true;
            } else if (bArr[1] != 97 || bArr[2] != 114 || bArr[3] != 33 || bArr[4] != 26 || bArr[5] != 7 || bArr[6] != 0) {
                return false;
            } else {
                this.oldFormat = false;
            }
            return true;
        }
        return false;
    }

    public boolean isOldFormat() {
        return this.oldFormat;
    }

    @Override // com.movieboxpro.android.utils.unrar.rarfile.BaseBlock
    public void print() {
        super.print();
        PrintStream printStream = System.out;
        printStream.print("valid: " + isValid());
    }
}
