package com.movieboxpro.android.utils.unrar.rarfile;

import com.movieboxpro.android.utils.unrar.io.Raw;
import java.io.PrintStream;
import kotlin.UShort;
/* loaded from: classes3.dex */
public class UnixOwnersHeader extends SubBlockHeader {
    private String group;
    private int groupNameSize;
    private String owner;
    private int ownerNameSize;

    public UnixOwnersHeader(SubBlockHeader subBlockHeader, byte[] bArr) {
        super(subBlockHeader);
        this.ownerNameSize = Raw.readShortLittleEndian(bArr, 0) & UShort.MAX_VALUE;
        this.groupNameSize = Raw.readShortLittleEndian(bArr, 2) & UShort.MAX_VALUE;
        int i = this.ownerNameSize;
        if (4 + i < bArr.length) {
            byte[] bArr2 = new byte[i];
            System.arraycopy(bArr, 4, bArr2, 0, i);
            this.owner = new String(bArr2);
        }
        int i2 = 4 + this.ownerNameSize;
        int i3 = this.groupNameSize;
        if (i2 + i3 < bArr.length) {
            byte[] bArr3 = new byte[i3];
            System.arraycopy(bArr, i2, bArr3, 0, i3);
            this.group = new String(bArr3);
        }
    }

    public String getGroup() {
        return this.group;
    }

    public void setGroup(String str) {
        this.group = str;
    }

    public int getGroupNameSize() {
        return this.groupNameSize;
    }

    public void setGroupNameSize(int i) {
        this.groupNameSize = i;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String str) {
        this.owner = str;
    }

    public int getOwnerNameSize() {
        return this.ownerNameSize;
    }

    public void setOwnerNameSize(int i) {
        this.ownerNameSize = i;
    }

    @Override // com.movieboxpro.android.utils.unrar.rarfile.SubBlockHeader, com.movieboxpro.android.utils.unrar.rarfile.BlockHeader, com.movieboxpro.android.utils.unrar.rarfile.BaseBlock
    public void print() {
        super.print();
        PrintStream printStream = System.out;
        printStream.print("ownerNameSize: " + this.ownerNameSize);
        PrintStream printStream2 = System.out;
        printStream2.print("owner: " + this.owner);
        PrintStream printStream3 = System.out;
        printStream3.print("groupNameSize: " + this.groupNameSize);
        PrintStream printStream4 = System.out;
        printStream4.print("group: " + this.group);
    }
}
