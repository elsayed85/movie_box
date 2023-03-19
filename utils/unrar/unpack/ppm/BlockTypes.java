package com.movieboxpro.android.utils.unrar.unpack.ppm;
/* loaded from: classes3.dex */
public enum BlockTypes {
    BLOCK_LZ(0),
    BLOCK_PPM(1);
    
    private int blockType;

    BlockTypes(int i) {
        this.blockType = i;
    }

    public int getBlockType() {
        return this.blockType;
    }

    public boolean equals(int i) {
        return this.blockType == i;
    }

    public static BlockTypes findBlockType(int i) {
        if (BLOCK_LZ.equals(i)) {
            return BLOCK_LZ;
        }
        if (BLOCK_PPM.equals(i)) {
            return BLOCK_PPM;
        }
        return null;
    }
}
