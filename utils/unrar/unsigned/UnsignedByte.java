package com.movieboxpro.android.utils.unrar.unsigned;
/* loaded from: classes3.dex */
public class UnsignedByte {
    public static short add(byte b, byte b2) {
        return (short) (b + b2);
    }

    public static byte intToByte(int i) {
        return (byte) (i & 255);
    }

    public static byte longToByte(long j) {
        return (byte) (j & 255);
    }

    public static byte shortToByte(short s) {
        return (byte) (s & 255);
    }

    public static short sub(byte b, byte b2) {
        return (short) (b - b2);
    }

    public static void main(String[] strArr) {
        System.out.println((int) add((byte) -2, (byte) 1));
        System.out.println((int) add((byte) -1, (byte) 1));
        System.out.println((int) add(Byte.MAX_VALUE, (byte) 1));
        System.out.println((int) add((byte) -1, (byte) -1));
        System.out.println((int) sub((byte) -2, (byte) 1));
        System.out.println((int) sub((byte) 0, (byte) 1));
        System.out.println((int) sub(Byte.MIN_VALUE, (byte) 1));
        System.out.println(1);
    }
}
