package com.movieboxpro.android.utils.unrar.crc;
/* loaded from: classes3.dex */
public class RarCRC {
    private static final int[] crcTab = new int[256];

    static {
        for (int i = 0; i < 256; i++) {
            int i2 = i;
            for (int i3 = 0; i3 < 8; i3++) {
                i2 = (i2 & 1) != 0 ? (i2 >>> 1) ^ (-306674912) : i2 >>> 1;
            }
            crcTab[i] = i2;
        }
    }

    private RarCRC() {
    }

    public static int checkCrc(int i, byte[] bArr, int i2, int i3) {
        int min = Math.min(bArr.length - i2, i3);
        for (int i4 = 0; i4 < min; i4++) {
            i = (i >>> 8) ^ crcTab[(bArr[i2 + i4] ^ i) & 255];
        }
        return i;
    }

    public static short checkOldCrc(short s, byte[] bArr, int i) {
        int min = Math.min(bArr.length, i);
        for (int i2 = 0; i2 < min; i2++) {
            short s2 = (short) (((short) (s + ((short) (bArr[i2] & 255)))) & (-1));
            s = (short) (((s2 >>> 15) | (s2 << 1)) & (-1));
        }
        return s;
    }
}
