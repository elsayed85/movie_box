package com.movieboxpro.android.timroes.base64;

import com.google.common.base.Ascii;
import com.movieboxpro.android.utils.ShellUtil;
import java.util.HashMap;
/* loaded from: classes3.dex */
public class Base64 {
    private static final char[] code = "=ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    private static final HashMap<Character, Byte> map = new HashMap<>();

    static {
        int i = 0;
        while (true) {
            char[] cArr = code;
            if (i >= cArr.length) {
                return;
            }
            map.put(Character.valueOf(cArr[i]), Byte.valueOf((byte) i));
            i++;
        }
    }

    private Base64() {
    }

    public static byte[] decode(String str) {
        String replaceAll = str.replaceAll("\\r|\\n", "");
        if (replaceAll.length() % 4 != 0) {
            throw new IllegalArgumentException("The length of the input string must be a multiple of four.");
        }
        if (!replaceAll.matches("^[A-Za-z0-9+/]*[=]{0,3}$")) {
            throw new IllegalArgumentException("The argument contains illegal characters.");
        }
        int length = (replaceAll.length() * 3) / 4;
        byte[] bArr = new byte[length];
        char[] charArray = replaceAll.toCharArray();
        int i = 0;
        int i2 = 0;
        while (i < charArray.length) {
            int byteValue = map.get(Character.valueOf(charArray[i + 1])).byteValue() - 1;
            int byteValue2 = map.get(Character.valueOf(charArray[i + 2])).byteValue() - 1;
            int i3 = i2 + 1;
            bArr[i2] = (byte) (((map.get(Character.valueOf(charArray[i])).byteValue() - 1) << 2) | (byteValue >>> 4));
            int i4 = i3 + 1;
            bArr[i3] = (byte) (((byteValue & 15) << 4) | (byteValue2 >>> 2));
            bArr[i4] = (byte) (((byteValue2 & 3) << 6) | ((map.get(Character.valueOf(charArray[i + 3])).byteValue() - 1) & 63));
            i += 4;
            i2 = i4 + 1;
        }
        if (replaceAll.endsWith("=")) {
            int length2 = length - (replaceAll.length() - replaceAll.indexOf("="));
            byte[] bArr2 = new byte[length2];
            System.arraycopy(bArr, 0, bArr2, 0, length2);
            return bArr2;
        }
        return bArr;
    }

    public static String decodeAsString(String str) {
        return new String(decode(str));
    }

    public static String encode(String str) {
        return encode(str.getBytes());
    }

    public static String encode(Byte[] bArr) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr2[i] = bArr[i].byteValue();
        }
        return encode(bArr2);
    }

    public static String encode(byte[] bArr) {
        StringBuilder sb = new StringBuilder(((bArr.length + 2) / 3) * 4);
        byte[] encodeAsBytes = encodeAsBytes(bArr);
        for (int i = 0; i < encodeAsBytes.length; i++) {
            sb.append(code[encodeAsBytes[i] + 1]);
            if (i % 72 == 71) {
                sb.append(ShellUtil.COMMAND_LINE_END);
            }
        }
        return sb.toString();
    }

    public static byte[] encodeAsBytes(String str) {
        return encodeAsBytes(str.getBytes());
    }

    public static byte[] encodeAsBytes(byte[] bArr) {
        int length = ((bArr.length + 2) / 3) * 4;
        byte[] bArr2 = new byte[length];
        int length2 = ((bArr.length + 2) / 3) * 3;
        byte[] bArr3 = new byte[length2];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        int i = 0;
        for (int i2 = 0; i2 < length2; i2 += 3) {
            int i3 = i + 1;
            bArr2[i] = (byte) ((bArr3[i2] & 255) >>> 2);
            int i4 = i3 + 1;
            int i5 = i2 + 1;
            bArr2[i3] = (byte) (((bArr3[i2] & 3) << 4) | ((bArr3[i5] & 255) >>> 4));
            int i6 = i4 + 1;
            int i7 = i2 + 2;
            bArr2[i4] = (byte) (((bArr3[i5] & Ascii.SI) << 2) | ((bArr3[i7] & 255) >>> 6));
            i = i6 + 1;
            bArr2[i6] = (byte) (bArr3[i7] & 63);
        }
        for (int length3 = length2 - bArr.length; length3 > 0; length3--) {
            bArr2[length - length3] = -1;
        }
        return bArr2;
    }
}
