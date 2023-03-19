package com.movieboxpro.android.utils.unrar.unpack.vm;
/* loaded from: classes3.dex */
public enum VMFlags {
    VM_FC(1),
    VM_FZ(2),
    VM_FS(Integer.MIN_VALUE);
    
    private int flag;

    VMFlags(int i) {
        this.flag = i;
    }

    public static VMFlags findFlag(int i) {
        if (VM_FC.equals(i)) {
            return VM_FC;
        }
        if (VM_FS.equals(i)) {
            return VM_FS;
        }
        if (VM_FZ.equals(i)) {
            return VM_FZ;
        }
        return null;
    }

    public boolean equals(int i) {
        return this.flag == i;
    }

    public int getFlag() {
        return this.flag;
    }
}
