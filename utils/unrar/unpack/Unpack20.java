package com.movieboxpro.android.utils.unrar.unpack;

import androidx.core.view.InputDeviceCompat;
import com.alibaba.fastjson.asm.Opcodes;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.audio.WavUtil;
import com.google.common.base.Ascii;
import com.movieboxpro.android.utils.unrar.exception.RarException;
import com.movieboxpro.android.utils.unrar.unpack.decode.AudioVariables;
import com.movieboxpro.android.utils.unrar.unpack.decode.BitDecode;
import com.movieboxpro.android.utils.unrar.unpack.decode.Compress;
import com.movieboxpro.android.utils.unrar.unpack.decode.Decode;
import com.movieboxpro.android.utils.unrar.unpack.decode.DistDecode;
import com.movieboxpro.android.utils.unrar.unpack.decode.LitDecode;
import com.movieboxpro.android.utils.unrar.unpack.decode.LowDistDecode;
import com.movieboxpro.android.utils.unrar.unpack.decode.MultDecode;
import com.movieboxpro.android.utils.unrar.unpack.decode.RepDecode;
import java.io.IOException;
import java.util.Arrays;
/* loaded from: classes3.dex */
public abstract class Unpack20 extends Unpack15 {
    protected int UnpAudioBlock;
    protected int UnpChannelDelta;
    protected int UnpChannels;
    protected int UnpCurChannel;
    public static final int[] LDecode = {0, 1, 2, 3, 4, 5, 6, 7, 8, 10, 12, 14, 16, 20, 24, 28, 32, 40, 48, 56, 64, 80, 96, 112, 128, Opcodes.IF_ICMPNE, 192, 224};
    public static final byte[] LBits = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5};
    public static final int[] DDecode = {0, 1, 2, 3, 4, 6, 8, 12, 16, 24, 32, 48, 64, 96, 128, 192, 256, 384, 512, 768, 1024, 1536, 2048, 3072, 4096, 6144, 8192, 12288, 16384, 24576, 32768, 49152, 65536, 98304, 131072, 196608, 262144, 327680, 393216, 458752, 524288, 589824, 655360, 720896, 786432, 851968, 917504, 983040};
    public static final int[] DBits = {0, 0, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12, 12, 13, 13, 14, 14, 15, 15, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16};
    public static final int[] SDDecode = {0, 4, 8, 16, 32, 64, 128, 192};
    public static final int[] SDBits = {2, 2, 3, 4, 5, 6, 6, 6};
    protected MultDecode[] MD = new MultDecode[4];
    protected byte[] UnpOldTable20 = new byte[AnalyticsListener.EVENT_PLAYER_RELEASED];
    protected AudioVariables[] AudV = new AudioVariables[4];
    protected LitDecode LD = new LitDecode();
    protected DistDecode DD = new DistDecode();
    protected LowDistDecode LDD = new LowDistDecode();
    protected RepDecode RD = new RepDecode();
    protected BitDecode BD = new BitDecode();

    /* JADX INFO: Access modifiers changed from: protected */
    public void unpack20(boolean z) throws IOException, RarException {
        if (this.suspended) {
            this.unpPtr = this.wrPtr;
        } else {
            unpInitData(z);
            if (!unpReadBuf()) {
                return;
            }
            if (!z && !ReadTables20()) {
                return;
            }
            this.destUnpSize--;
        }
        while (this.destUnpSize >= 0) {
            this.unpPtr &= Compress.MAXWINMASK;
            if (this.inAddr > this.readTop - 30 && !unpReadBuf()) {
                break;
            }
            if (((this.wrPtr - this.unpPtr) & Compress.MAXWINMASK) < 270 && this.wrPtr != this.unpPtr) {
                oldUnpWriteBuf();
                if (this.suspended) {
                    return;
                }
            }
            if (this.UnpAudioBlock != 0) {
                int decodeNumber = decodeNumber(this.MD[this.UnpCurChannel]);
                if (decodeNumber == 256) {
                    if (!ReadTables20()) {
                        break;
                    }
                } else {
                    byte[] bArr = this.window;
                    int i = this.unpPtr;
                    this.unpPtr = i + 1;
                    bArr[i] = DecodeAudio(decodeNumber);
                    int i2 = this.UnpCurChannel + 1;
                    this.UnpCurChannel = i2;
                    if (i2 == this.UnpChannels) {
                        this.UnpCurChannel = 0;
                    }
                    this.destUnpSize--;
                }
            } else {
                int decodeNumber2 = decodeNumber(this.LD);
                if (decodeNumber2 < 256) {
                    byte[] bArr2 = this.window;
                    int i3 = this.unpPtr;
                    this.unpPtr = i3 + 1;
                    bArr2[i3] = (byte) decodeNumber2;
                    this.destUnpSize--;
                } else if (decodeNumber2 > 269) {
                    int i4 = decodeNumber2 - 270;
                    int i5 = LDecode[i4] + 3;
                    byte b = LBits[i4];
                    if (b > 0) {
                        i5 += getbits() >>> (16 - b);
                        addbits(b);
                    }
                    int decodeNumber3 = decodeNumber(this.DD);
                    int i6 = DDecode[decodeNumber3] + 1;
                    int i7 = DBits[decodeNumber3];
                    if (i7 > 0) {
                        i6 += getbits() >>> (16 - i7);
                        addbits(i7);
                    }
                    if (i6 >= 8192) {
                        i5++;
                        if (i6 >= 262144) {
                            i5++;
                        }
                    }
                    CopyString20(i5, i6);
                } else if (decodeNumber2 == 269) {
                    if (!ReadTables20()) {
                        break;
                    }
                } else if (decodeNumber2 == 256) {
                    CopyString20(this.lastLength, this.lastDist);
                } else if (decodeNumber2 < 261) {
                    int i8 = this.oldDist[(this.oldDistPtr - (decodeNumber2 + InputDeviceCompat.SOURCE_ANY)) & 3];
                    int decodeNumber4 = decodeNumber(this.RD);
                    int i9 = LDecode[decodeNumber4] + 2;
                    byte b2 = LBits[decodeNumber4];
                    if (b2 > 0) {
                        i9 += getbits() >>> (16 - b2);
                        addbits(b2);
                    }
                    if (i8 >= 257) {
                        i9++;
                        if (i8 >= 8192) {
                            i9++;
                            if (i8 >= 262144) {
                                i9++;
                            }
                        }
                    }
                    CopyString20(i9, i8);
                } else if (decodeNumber2 < 270) {
                    int i10 = decodeNumber2 - 261;
                    int i11 = SDDecode[i10] + 1;
                    int i12 = SDBits[i10];
                    if (i12 > 0) {
                        i11 += getbits() >>> (16 - i12);
                        addbits(i12);
                    }
                    CopyString20(2, i11);
                }
            }
        }
        ReadLastTables();
        oldUnpWriteBuf();
    }

    protected void CopyString20(int i, int i2) {
        int[] iArr = this.oldDist;
        int i3 = this.oldDistPtr;
        this.oldDistPtr = i3 + 1;
        iArr[i3 & 3] = i2;
        this.lastDist = i2;
        this.lastLength = i;
        this.destUnpSize -= i;
        int i4 = this.unpPtr - i2;
        if (i4 < 4194004 && this.unpPtr < 4194004) {
            byte[] bArr = this.window;
            int i5 = this.unpPtr;
            this.unpPtr = i5 + 1;
            int i6 = i4 + 1;
            bArr[i5] = this.window[i4];
            byte[] bArr2 = this.window;
            int i7 = this.unpPtr;
            this.unpPtr = i7 + 1;
            int i8 = i6 + 1;
            bArr2[i7] = this.window[i6];
            while (i > 2) {
                i--;
                byte[] bArr3 = this.window;
                int i9 = this.unpPtr;
                this.unpPtr = i9 + 1;
                bArr3[i9] = this.window[i8];
                i8++;
            }
            return;
        }
        while (true) {
            int i10 = i - 1;
            if (i == 0) {
                return;
            }
            this.window[this.unpPtr] = this.window[i4 & Compress.MAXWINMASK];
            this.unpPtr = (this.unpPtr + 1) & Compress.MAXWINMASK;
            i = i10;
            i4++;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void makeDecodeTables(byte[] bArr, int i, Decode decode, int i2) {
        int i3;
        int[] iArr = new int[16];
        int[] iArr2 = new int[16];
        Arrays.fill(iArr, 0);
        Arrays.fill(decode.getDecodeNum(), 0);
        int i4 = 0;
        while (true) {
            if (i4 >= i2) {
                break;
            }
            int i5 = bArr[i + i4] & Ascii.SI;
            iArr[i5] = iArr[i5] + 1;
            i4++;
        }
        iArr[0] = 0;
        iArr2[0] = 0;
        decode.getDecodePos()[0] = 0;
        decode.getDecodeLen()[0] = 0;
        long j = 0;
        for (i3 = 1; i3 < 16; i3++) {
            j = (j + iArr[i3]) * 2;
            long j2 = j << (15 - i3);
            if (j2 > 65535) {
                j2 = 65535;
            }
            decode.getDecodeLen()[i3] = (int) j2;
            int[] decodePos = decode.getDecodePos();
            int i6 = i3 - 1;
            int i7 = decode.getDecodePos()[i6] + iArr[i6];
            decodePos[i3] = i7;
            iArr2[i3] = i7;
        }
        for (int i8 = 0; i8 < i2; i8++) {
            int i9 = i + i8;
            if (bArr[i9] != 0) {
                int[] decodeNum = decode.getDecodeNum();
                int i10 = bArr[i9] & Ascii.SI;
                int i11 = iArr2[i10];
                iArr2[i10] = i11 + 1;
                decodeNum[i11] = i8;
            }
        }
        decode.setMaxNum(i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int decodeNumber(Decode decode) {
        long j = getbits() & WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE;
        int[] decodeLen = decode.getDecodeLen();
        int i = 8;
        if (j < decodeLen[8]) {
            if (j < decodeLen[4]) {
                if (j < decodeLen[2]) {
                    i = j < ((long) decodeLen[1]) ? 1 : 2;
                } else {
                    i = j < ((long) decodeLen[3]) ? 3 : 4;
                }
            } else if (j < decodeLen[6]) {
                i = j < ((long) decodeLen[5]) ? 5 : 6;
            } else if (j < decodeLen[7]) {
                i = 7;
            }
        } else if (j < decodeLen[12]) {
            if (j < decodeLen[10]) {
                i = j < ((long) decodeLen[9]) ? 9 : 10;
            } else {
                i = j < ((long) decodeLen[11]) ? 11 : 12;
            }
        } else if (j < decodeLen[14]) {
            i = j < ((long) decodeLen[13]) ? 13 : 14;
        } else {
            i = 15;
        }
        addbits(i);
        int i2 = decode.getDecodePos()[i] + ((((int) j) - decodeLen[i - 1]) >>> (16 - i));
        if (i2 >= decode.getMaxNum()) {
            i2 = 0;
        }
        return decode.getDecodeNum()[i2];
    }

    protected boolean ReadTables20() throws IOException, RarException {
        int i;
        int i2;
        byte[] bArr = new byte[19];
        byte[] bArr2 = new byte[AnalyticsListener.EVENT_PLAYER_RELEASED];
        int i3 = 0;
        if (this.inAddr > this.readTop - 25 && !unpReadBuf()) {
            return false;
        }
        int i4 = getbits();
        this.UnpAudioBlock = 32768 & i4;
        if ((i4 & 16384) == 0) {
            Arrays.fill(this.UnpOldTable20, (byte) 0);
        }
        addbits(2);
        if (this.UnpAudioBlock != 0) {
            int i5 = ((i4 >>> 12) & 3) + 1;
            this.UnpChannels = i5;
            if (this.UnpCurChannel >= i5) {
                this.UnpCurChannel = 0;
            }
            addbits(2);
            i = this.UnpChannels * 257;
        } else {
            i = 374;
        }
        for (int i6 = 0; i6 < 19; i6++) {
            bArr[i6] = (byte) (getbits() >>> 12);
            addbits(4);
        }
        makeDecodeTables(bArr, 0, this.BD, 19);
        int i7 = 0;
        while (i7 < i) {
            if (this.inAddr > this.readTop - 5 && !unpReadBuf()) {
                return false;
            }
            int decodeNumber = decodeNumber(this.BD);
            if (decodeNumber < 16) {
                bArr2[i7] = (byte) ((decodeNumber + this.UnpOldTable20[i7]) & 15);
                i7++;
            } else if (decodeNumber == 16) {
                int i8 = (getbits() >>> 14) + 3;
                addbits(2);
                while (true) {
                    int i9 = i8 - 1;
                    if (i8 > 0 && i7 < i) {
                        bArr2[i7] = bArr2[i7 - 1];
                        i7++;
                        i8 = i9;
                    }
                }
            } else {
                if (decodeNumber == 17) {
                    i2 = (getbits() >>> 13) + 3;
                    addbits(3);
                } else {
                    i2 = (getbits() >>> 9) + 11;
                    addbits(7);
                }
                while (true) {
                    int i10 = i2 - 1;
                    if (i2 > 0 && i7 < i) {
                        bArr2[i7] = 0;
                        i7++;
                        i2 = i10;
                    }
                }
            }
        }
        if (this.inAddr > this.readTop) {
            return true;
        }
        if (this.UnpAudioBlock != 0) {
            for (int i11 = 0; i11 < this.UnpChannels; i11++) {
                makeDecodeTables(bArr2, i11 * 257, this.MD[i11], 257);
            }
        } else {
            makeDecodeTables(bArr2, 0, this.LD, Compress.NC20);
            makeDecodeTables(bArr2, Compress.NC20, this.DD, 48);
            makeDecodeTables(bArr2, 346, this.RD, 28);
        }
        while (true) {
            byte[] bArr3 = this.UnpOldTable20;
            if (i3 >= bArr3.length) {
                return true;
            }
            bArr3[i3] = bArr2[i3];
            i3++;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void unpInitData20(boolean z) {
        if (z) {
            return;
        }
        this.UnpCurChannel = 0;
        this.UnpChannelDelta = 0;
        this.UnpChannels = 1;
        Arrays.fill(this.AudV, new AudioVariables());
        Arrays.fill(this.UnpOldTable20, (byte) 0);
    }

    protected void ReadLastTables() throws IOException, RarException {
        if (this.readTop >= this.inAddr + 5) {
            if (this.UnpAudioBlock != 0) {
                if (decodeNumber(this.MD[this.UnpCurChannel]) == 256) {
                    ReadTables20();
                }
            } else if (decodeNumber(this.LD) == 269) {
                ReadTables20();
            }
        }
    }

    protected byte DecodeAudio(int i) {
        AudioVariables audioVariables = this.AudV[this.UnpCurChannel];
        audioVariables.setByteCount(audioVariables.getByteCount() + 1);
        audioVariables.setD4(audioVariables.getD3());
        audioVariables.setD3(audioVariables.getD2());
        audioVariables.setD2(audioVariables.getLastDelta() - audioVariables.getD1());
        audioVariables.setD1(audioVariables.getLastDelta());
        int lastChar = ((((((audioVariables.getLastChar() * 8) + (audioVariables.getK1() * audioVariables.getD1())) + ((audioVariables.getK2() * audioVariables.getD2()) + (audioVariables.getK3() * audioVariables.getD3()))) + ((audioVariables.getK4() * audioVariables.getD4()) + (audioVariables.getK5() * this.UnpChannelDelta))) >>> 3) & 255) - i;
        int i2 = ((byte) i) << 3;
        int[] dif = audioVariables.getDif();
        dif[0] = dif[0] + Math.abs(i2);
        int[] dif2 = audioVariables.getDif();
        dif2[1] = dif2[1] + Math.abs(i2 - audioVariables.getD1());
        int[] dif3 = audioVariables.getDif();
        dif3[2] = dif3[2] + Math.abs(audioVariables.getD1() + i2);
        int[] dif4 = audioVariables.getDif();
        dif4[3] = dif4[3] + Math.abs(i2 - audioVariables.getD2());
        int[] dif5 = audioVariables.getDif();
        dif5[4] = dif5[4] + Math.abs(audioVariables.getD2() + i2);
        int[] dif6 = audioVariables.getDif();
        dif6[5] = dif6[5] + Math.abs(i2 - audioVariables.getD3());
        int[] dif7 = audioVariables.getDif();
        dif7[6] = dif7[6] + Math.abs(audioVariables.getD3() + i2);
        int[] dif8 = audioVariables.getDif();
        dif8[7] = dif8[7] + Math.abs(i2 - audioVariables.getD4());
        int[] dif9 = audioVariables.getDif();
        dif9[8] = dif9[8] + Math.abs(audioVariables.getD4() + i2);
        int[] dif10 = audioVariables.getDif();
        dif10[9] = dif10[9] + Math.abs(i2 - this.UnpChannelDelta);
        int[] dif11 = audioVariables.getDif();
        dif11[10] = dif11[10] + Math.abs(i2 + this.UnpChannelDelta);
        audioVariables.setLastDelta((byte) (lastChar - audioVariables.getLastChar()));
        this.UnpChannelDelta = audioVariables.getLastDelta();
        audioVariables.setLastChar(lastChar);
        if ((audioVariables.getByteCount() & 31) == 0) {
            int i3 = audioVariables.getDif()[0];
            audioVariables.getDif()[0] = 0;
            int i4 = 0;
            for (int i5 = 1; i5 < audioVariables.getDif().length; i5++) {
                if (audioVariables.getDif()[i5] < i3) {
                    i3 = audioVariables.getDif()[i5];
                    i4 = i5;
                }
                audioVariables.getDif()[i5] = 0;
            }
            switch (i4) {
                case 1:
                    if (audioVariables.getK1() >= -16) {
                        audioVariables.setK1(audioVariables.getK1() - 1);
                        break;
                    }
                    break;
                case 2:
                    if (audioVariables.getK1() < 16) {
                        audioVariables.setK1(audioVariables.getK1() + 1);
                        break;
                    }
                    break;
                case 3:
                    if (audioVariables.getK2() >= -16) {
                        audioVariables.setK2(audioVariables.getK2() - 1);
                        break;
                    }
                    break;
                case 4:
                    if (audioVariables.getK2() < 16) {
                        audioVariables.setK2(audioVariables.getK2() + 1);
                        break;
                    }
                    break;
                case 5:
                    if (audioVariables.getK3() >= -16) {
                        audioVariables.setK3(audioVariables.getK3() - 1);
                        break;
                    }
                    break;
                case 6:
                    if (audioVariables.getK3() < 16) {
                        audioVariables.setK3(audioVariables.getK3() + 1);
                        break;
                    }
                    break;
                case 7:
                    if (audioVariables.getK4() >= -16) {
                        audioVariables.setK4(audioVariables.getK4() - 1);
                        break;
                    }
                    break;
                case 8:
                    if (audioVariables.getK4() < 16) {
                        audioVariables.setK4(audioVariables.getK4() + 1);
                        break;
                    }
                    break;
                case 9:
                    if (audioVariables.getK5() >= -16) {
                        audioVariables.setK5(audioVariables.getK5() - 1);
                        break;
                    }
                    break;
                case 10:
                    if (audioVariables.getK5() < 16) {
                        audioVariables.setK5(audioVariables.getK5() + 1);
                        break;
                    }
                    break;
            }
        }
        return (byte) lastChar;
    }
}
