package com.movieboxpro.android.utils.unrar.unpack.ppm;

import com.movieboxpro.android.utils.unrar.io.Raw;
/* loaded from: classes3.dex */
public class RarNode extends Pointer {
    public static final int size = 4;
    private int next;

    public RarNode(byte[] bArr) {
        super(bArr);
    }

    public int getNext() {
        if (this.mem != null) {
            this.next = Raw.readIntLittleEndian(this.mem, this.pos);
        }
        return this.next;
    }

    public void setNext(RarNode rarNode) {
        setNext(rarNode.getAddress());
    }

    public void setNext(int i) {
        this.next = i;
        if (this.mem != null) {
            Raw.writeIntLittleEndian(this.mem, this.pos, i);
        }
    }

    public String toString() {
        return "State[\n  pos=" + this.pos + "\n  size=4\n  next=" + getNext() + "\n]";
    }
}
