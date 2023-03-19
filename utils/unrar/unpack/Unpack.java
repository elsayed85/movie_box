package com.movieboxpro.android.utils.unrar.unpack;

import com.google.common.base.Ascii;
import com.movieboxpro.android.utils.unrar.exception.RarException;
import com.movieboxpro.android.utils.unrar.unpack.decode.Compress;
import com.movieboxpro.android.utils.unrar.unpack.ppm.BlockTypes;
import com.movieboxpro.android.utils.unrar.unpack.ppm.ModelPPM;
import com.movieboxpro.android.utils.unrar.unpack.ppm.SubAllocator;
import com.movieboxpro.android.utils.unrar.unpack.vm.BitInput;
import com.movieboxpro.android.utils.unrar.unpack.vm.RarVM;
import com.movieboxpro.android.utils.unrar.unpack.vm.VMPreparedProgram;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
/* loaded from: classes3.dex */
public final class Unpack extends Unpack20 {
    public static int[] DBitLengthCounts = {4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 14, 0, 12};
    private boolean externalWindow;
    private boolean fileExtracted;
    private int lastFilter;
    private int lowDistRepCount;
    private boolean ppmError;
    private int ppmEscChar;
    private int prevLowDist;
    private boolean tablesRead;
    private BlockTypes unpBlockType;
    private long writtenFileSize;
    private final ModelPPM ppm = new ModelPPM();
    private RarVM rarVM = new RarVM();
    private List<UnpackFilter> filters = new ArrayList();
    private List<UnpackFilter> prgStack = new ArrayList();
    private List<Integer> oldFilterLengths = new ArrayList();
    private byte[] unpOldTable = new byte[404];

    public Unpack(ComprDataIO comprDataIO) {
        this.unpIO = comprDataIO;
        this.window = null;
        this.externalWindow = false;
        this.suspended = false;
        this.unpAllBuf = false;
        this.unpSomeRead = false;
    }

    public void init(byte[] bArr) {
        if (bArr == null) {
            this.window = new byte[4194304];
        } else {
            this.window = bArr;
            this.externalWindow = true;
        }
        this.inAddr = 0;
        unpInitData(false);
    }

    public void doUnpack(int i, boolean z) throws IOException, RarException {
        if (this.unpIO.getSubHeader().getUnpMethod() == 48) {
            unstoreFile();
        }
        if (i == 15) {
            unpack15(z);
        } else if (i == 20 || i == 26) {
            unpack20(z);
        } else if (i == 29 || i == 36) {
            unpack29(z);
        }
    }

    private void unstoreFile() throws IOException, RarException {
        byte[] bArr = new byte[65536];
        while (true) {
            int unpRead = this.unpIO.unpRead(bArr, 0, (int) Math.min(65536, this.destUnpSize));
            if (unpRead == 0 || unpRead == -1) {
                return;
            }
            if (unpRead >= this.destUnpSize) {
                unpRead = (int) this.destUnpSize;
            }
            this.unpIO.unpWrite(bArr, 0, unpRead);
            if (this.destUnpSize >= 0) {
                this.destUnpSize -= unpRead;
            }
        }
    }

    private void unpack29(boolean z) throws IOException, RarException {
        int i;
        int[] iArr = new int[60];
        byte[] bArr = new byte[60];
        if (iArr[1] == 0) {
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            while (true) {
                int[] iArr2 = DBitLengthCounts;
                if (i2 >= iArr2.length) {
                    break;
                }
                int i6 = iArr2[i2];
                int i7 = 0;
                while (i7 < i6) {
                    iArr[i5] = i3;
                    bArr[i5] = (byte) i4;
                    i7++;
                    i5++;
                    i3 += 1 << i4;
                }
                i2++;
                i4++;
            }
        }
        this.fileExtracted = true;
        if (!this.suspended) {
            unpInitData(z);
            if (!unpReadBuf()) {
                return;
            }
            if ((!z || !this.tablesRead) && !readTables()) {
                return;
            }
        }
        if (this.ppmError) {
            return;
        }
        while (true) {
            this.unpPtr &= Compress.MAXWINMASK;
            if (this.inAddr > this.readBorder && !unpReadBuf()) {
                break;
            }
            if (((this.wrPtr - this.unpPtr) & Compress.MAXWINMASK) < 260 && this.wrPtr != this.unpPtr) {
                UnpWriteBuf();
                if (this.writtenFileSize > this.destUnpSize) {
                    return;
                }
                if (this.suspended) {
                    this.fileExtracted = false;
                    return;
                }
            }
            if (this.unpBlockType == BlockTypes.BLOCK_PPM) {
                int decodeChar = this.ppm.decodeChar();
                if (decodeChar == -1) {
                    this.ppmError = true;
                    break;
                }
                if (decodeChar == this.ppmEscChar) {
                    int decodeChar2 = this.ppm.decodeChar();
                    if (decodeChar2 != 0) {
                        if (decodeChar2 == 2 || decodeChar2 == -1) {
                            break;
                        } else if (decodeChar2 == 3) {
                            if (!readVMCodePPM()) {
                                break;
                            }
                        } else if (decodeChar2 == 4) {
                            boolean z2 = false;
                            int i8 = 0;
                            int i9 = 0;
                            for (int i10 = 0; i10 < 4 && !z2; i10++) {
                                int decodeChar3 = this.ppm.decodeChar();
                                if (decodeChar3 == -1) {
                                    z2 = true;
                                } else if (i10 == 3) {
                                    i9 = decodeChar3 & 255;
                                } else {
                                    i8 = (i8 << 8) + (decodeChar3 & 255);
                                }
                            }
                            if (z2) {
                                break;
                            }
                            copyString(i9 + 32, i8 + 2);
                        } else if (decodeChar2 == 5) {
                            int decodeChar4 = this.ppm.decodeChar();
                            if (decodeChar4 == -1) {
                                break;
                            }
                            copyString(decodeChar4 + 4, 1);
                        }
                    } else if (!readTables()) {
                        break;
                    }
                }
                byte[] bArr2 = this.window;
                int i11 = this.unpPtr;
                this.unpPtr = i11 + 1;
                bArr2[i11] = (byte) decodeChar;
            } else {
                int decodeNumber = decodeNumber(this.LD);
                if (decodeNumber < 256) {
                    byte[] bArr3 = this.window;
                    int i12 = this.unpPtr;
                    this.unpPtr = i12 + 1;
                    bArr3[i12] = (byte) decodeNumber;
                } else if (decodeNumber >= 271) {
                    int i13 = decodeNumber - 271;
                    int i14 = LDecode[i13] + 3;
                    byte b = LBits[i13];
                    if (b > 0) {
                        i14 += getbits() >>> (16 - b);
                        addbits(b);
                    }
                    int decodeNumber2 = decodeNumber(this.DD);
                    int i15 = iArr[decodeNumber2] + 1;
                    byte b2 = bArr[decodeNumber2];
                    if (b2 > 0) {
                        if (decodeNumber2 > 9) {
                            if (b2 > 4) {
                                i15 += (getbits() >>> (20 - b2)) << 4;
                                addbits(b2 - 4);
                            }
                            int i16 = this.lowDistRepCount;
                            if (i16 > 0) {
                                this.lowDistRepCount = i16 - 1;
                                i = this.prevLowDist;
                            } else {
                                int decodeNumber3 = decodeNumber(this.LDD);
                                if (decodeNumber3 == 16) {
                                    this.lowDistRepCount = 15;
                                    i = this.prevLowDist;
                                } else {
                                    i15 += decodeNumber3;
                                    this.prevLowDist = decodeNumber3;
                                }
                            }
                            i15 += i;
                        } else {
                            i15 += getbits() >>> (16 - b2);
                            addbits(b2);
                        }
                    }
                    if (i15 >= 8192) {
                        i14++;
                        if (i15 >= 262144) {
                            i14++;
                        }
                    }
                    insertOldDist(i15);
                    insertLastMatch(i14, i15);
                    copyString(i14, i15);
                } else if (decodeNumber == 256) {
                    if (!readEndOfBlock()) {
                        break;
                    }
                } else if (decodeNumber == 257) {
                    if (!readVMCode()) {
                        break;
                    }
                } else if (decodeNumber == 258) {
                    if (this.lastLength != 0) {
                        copyString(this.lastLength, this.lastDist);
                    }
                } else if (decodeNumber < 263) {
                    int i17 = decodeNumber - 259;
                    int i18 = this.oldDist[i17];
                    while (i17 > 0) {
                        this.oldDist[i17] = this.oldDist[i17 - 1];
                        i17--;
                    }
                    this.oldDist[0] = i18;
                    int decodeNumber4 = decodeNumber(this.RD);
                    int i19 = LDecode[decodeNumber4] + 2;
                    byte b3 = LBits[decodeNumber4];
                    if (b3 > 0) {
                        i19 += getbits() >>> (16 - b3);
                        addbits(b3);
                    }
                    insertLastMatch(i19, i18);
                    copyString(i19, i18);
                } else if (decodeNumber < 272) {
                    int i20 = decodeNumber - 263;
                    int i21 = SDDecode[i20] + 1;
                    int i22 = SDBits[i20];
                    if (i22 > 0) {
                        i21 += getbits() >>> (16 - i22);
                        addbits(i22);
                    }
                    insertOldDist(i21);
                    insertLastMatch(2, i21);
                    copyString(2, i21);
                }
            }
        }
        UnpWriteBuf();
    }

    private void UnpWriteBuf() throws IOException {
        UnpackFilter unpackFilter;
        int i = this.wrPtr;
        int i2 = (this.unpPtr - i) & Compress.MAXWINMASK;
        int i3 = 0;
        while (i3 < this.prgStack.size()) {
            UnpackFilter unpackFilter2 = this.prgStack.get(i3);
            if (unpackFilter2 != null) {
                if (unpackFilter2.isNextWindow()) {
                    unpackFilter2.setNextWindow(false);
                } else {
                    int blockStart = unpackFilter2.getBlockStart();
                    int blockLength = unpackFilter2.getBlockLength();
                    if (((blockStart - i) & Compress.MAXWINMASK) >= i2) {
                        continue;
                    } else {
                        if (i != blockStart) {
                            UnpWriteArea(i, blockStart);
                            i2 = (this.unpPtr - blockStart) & Compress.MAXWINMASK;
                            i = blockStart;
                        }
                        if (blockLength <= i2) {
                            i = (blockStart + blockLength) & Compress.MAXWINMASK;
                            if (blockStart < i || i == 0) {
                                this.rarVM.setMemory(0, this.window, blockStart, blockLength);
                            } else {
                                int i4 = 4194304 - blockStart;
                                this.rarVM.setMemory(0, this.window, blockStart, i4);
                                this.rarVM.setMemory(i4, this.window, 0, i);
                            }
                            VMPreparedProgram prg = this.filters.get(unpackFilter2.getParentFilter()).getPrg();
                            VMPreparedProgram prg2 = unpackFilter2.getPrg();
                            if (prg.getGlobalData().size() > 64) {
                                prg2.getGlobalData().setSize(prg.getGlobalData().size());
                                for (int i5 = 0; i5 < prg.getGlobalData().size() - 64; i5++) {
                                    int i6 = i5 + 64;
                                    prg2.getGlobalData().set(i6, prg.getGlobalData().get(i6));
                                }
                            }
                            ExecuteCode(prg2);
                            if (prg2.getGlobalData().size() > 64) {
                                if (prg.getGlobalData().size() < prg2.getGlobalData().size()) {
                                    prg.getGlobalData().setSize(prg2.getGlobalData().size());
                                }
                                for (int i7 = 0; i7 < prg2.getGlobalData().size() - 64; i7++) {
                                    int i8 = i7 + 64;
                                    prg.getGlobalData().set(i8, prg2.getGlobalData().get(i8));
                                }
                            } else {
                                prg.getGlobalData().clear();
                            }
                            int filteredDataOffset = prg2.getFilteredDataOffset();
                            int filteredDataSize = prg2.getFilteredDataSize();
                            byte[] bArr = new byte[filteredDataSize];
                            for (int i9 = 0; i9 < filteredDataSize; i9++) {
                                bArr[i9] = this.rarVM.getMem()[filteredDataOffset + i9];
                            }
                            this.prgStack.set(i3, null);
                            while (true) {
                                int i10 = i3 + 1;
                                if (i10 >= this.prgStack.size() || (unpackFilter = this.prgStack.get(i10)) == null || unpackFilter.getBlockStart() != blockStart || unpackFilter.getBlockLength() != filteredDataSize || unpackFilter.isNextWindow()) {
                                    break;
                                }
                                this.rarVM.setMemory(0, bArr, 0, filteredDataSize);
                                VMPreparedProgram prg3 = this.filters.get(unpackFilter.getParentFilter()).getPrg();
                                VMPreparedProgram prg4 = unpackFilter.getPrg();
                                if (prg3.getGlobalData().size() > 64) {
                                    prg4.getGlobalData().setSize(prg3.getGlobalData().size());
                                    for (int i11 = 0; i11 < prg3.getGlobalData().size() - 64; i11++) {
                                        int i12 = i11 + 64;
                                        prg4.getGlobalData().set(i12, prg3.getGlobalData().get(i12));
                                    }
                                }
                                ExecuteCode(prg4);
                                if (prg4.getGlobalData().size() > 64) {
                                    if (prg3.getGlobalData().size() < prg4.getGlobalData().size()) {
                                        prg3.getGlobalData().setSize(prg4.getGlobalData().size());
                                    }
                                    for (int i13 = 0; i13 < prg4.getGlobalData().size() - 64; i13++) {
                                        int i14 = i13 + 64;
                                        prg3.getGlobalData().set(i14, prg4.getGlobalData().get(i14));
                                    }
                                } else {
                                    prg3.getGlobalData().clear();
                                }
                                int filteredDataOffset2 = prg4.getFilteredDataOffset();
                                int filteredDataSize2 = prg4.getFilteredDataSize();
                                byte[] bArr2 = new byte[filteredDataSize2];
                                for (int i15 = 0; i15 < filteredDataSize2; i15++) {
                                    bArr2[i15] = prg4.getGlobalData().get(filteredDataOffset2 + i15).byteValue();
                                }
                                this.prgStack.set(i10, null);
                                i3 = i10;
                                filteredDataSize = filteredDataSize2;
                                bArr = bArr2;
                            }
                            this.unpIO.unpWrite(bArr, 0, filteredDataSize);
                            this.unpSomeRead = true;
                            this.writtenFileSize += filteredDataSize;
                            i2 = (this.unpPtr - i) & Compress.MAXWINMASK;
                        } else {
                            while (i3 < this.prgStack.size()) {
                                UnpackFilter unpackFilter3 = this.prgStack.get(i3);
                                if (unpackFilter3 != null && unpackFilter3.isNextWindow()) {
                                    unpackFilter3.setNextWindow(false);
                                }
                                i3++;
                            }
                            this.wrPtr = i;
                            return;
                        }
                    }
                }
            }
            i3++;
        }
        UnpWriteArea(i, this.unpPtr);
        this.wrPtr = this.unpPtr;
    }

    private void UnpWriteArea(int i, int i2) throws IOException {
        if (i2 != i) {
            this.unpSomeRead = true;
        }
        if (i2 < i) {
            UnpWriteData(this.window, i, (-i) & Compress.MAXWINMASK);
            UnpWriteData(this.window, 0, i2);
            this.unpAllBuf = true;
            return;
        }
        UnpWriteData(this.window, i, i2 - i);
    }

    private void UnpWriteData(byte[] bArr, int i, int i2) throws IOException {
        if (this.writtenFileSize >= this.destUnpSize) {
            return;
        }
        long j = this.destUnpSize - this.writtenFileSize;
        long j2 = i2;
        if (j2 > j) {
            i2 = (int) j;
        }
        this.unpIO.unpWrite(bArr, i, i2);
        this.writtenFileSize += j2;
    }

    private void insertOldDist(int i) {
        this.oldDist[3] = this.oldDist[2];
        this.oldDist[2] = this.oldDist[1];
        this.oldDist[1] = this.oldDist[0];
        this.oldDist[0] = i;
    }

    private void insertLastMatch(int i, int i2) {
        this.lastDist = i2;
        this.lastLength = i;
    }

    private void copyString(int i, int i2) {
        int i3 = this.unpPtr - i2;
        if (i3 < 0 || i3 >= 4194044 || this.unpPtr >= 4194044) {
            while (true) {
                int i4 = i - 1;
                if (i == 0) {
                    return;
                }
                this.window[this.unpPtr] = this.window[i3 & Compress.MAXWINMASK];
                this.unpPtr = (this.unpPtr + 1) & Compress.MAXWINMASK;
                i = i4;
                i3++;
            }
        } else {
            byte[] bArr = this.window;
            int i5 = this.unpPtr;
            this.unpPtr = i5 + 1;
            int i6 = i3 + 1;
            bArr[i5] = this.window[i3];
            while (true) {
                i--;
                if (i <= 0) {
                    return;
                }
                byte[] bArr2 = this.window;
                int i7 = this.unpPtr;
                this.unpPtr = i7 + 1;
                bArr2[i7] = this.window[i6];
                i6++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.utils.unrar.unpack.Unpack15
    public void unpInitData(boolean z) {
        if (!z) {
            this.tablesRead = false;
            Arrays.fill(this.oldDist, 0);
            this.oldDistPtr = 0;
            this.lastDist = 0;
            this.lastLength = 0;
            Arrays.fill(this.unpOldTable, (byte) 0);
            this.unpPtr = 0;
            this.wrPtr = 0;
            this.ppmEscChar = 2;
            initFilters();
        }
        InitBitInput();
        this.ppmError = false;
        this.writtenFileSize = 0L;
        this.readTop = 0;
        this.readBorder = 0;
        unpInitData20(z);
    }

    private void initFilters() {
        this.oldFilterLengths.clear();
        this.lastFilter = 0;
        this.filters.clear();
        this.prgStack.clear();
    }

    private boolean readEndOfBlock() throws IOException, RarException {
        boolean z;
        boolean z2;
        int i = getbits();
        if ((32768 & i) != 0) {
            addbits(1);
            z = true;
            z2 = false;
        } else {
            z = (i & 16384) != 0;
            addbits(2);
            z2 = true;
        }
        this.tablesRead = !z;
        if (z2) {
            return false;
        }
        return !z || readTables();
    }

    private boolean readTables() throws IOException, RarException {
        int fgetbits;
        int fgetbits2;
        byte[] bArr = new byte[20];
        byte[] bArr2 = new byte[404];
        int i = 0;
        if (this.inAddr > this.readTop - 25 && !unpReadBuf()) {
            return false;
        }
        faddbits((8 - this.inBit) & 7);
        long fgetbits3 = fgetbits() & (-1);
        if ((32768 & fgetbits3) != 0) {
            this.unpBlockType = BlockTypes.BLOCK_PPM;
            return this.ppm.decodeInit(this, this.ppmEscChar);
        }
        this.unpBlockType = BlockTypes.BLOCK_LZ;
        this.prevLowDist = 0;
        this.lowDistRepCount = 0;
        if ((fgetbits3 & 16384) == 0) {
            Arrays.fill(this.unpOldTable, (byte) 0);
        }
        faddbits(2);
        int i2 = 0;
        while (i2 < 20) {
            int fgetbits4 = (fgetbits() >>> 12) & 255;
            faddbits(4);
            if (fgetbits4 == 15) {
                int fgetbits5 = (fgetbits() >>> 12) & 255;
                faddbits(4);
                if (fgetbits5 == 0) {
                    bArr[i2] = Ascii.SI;
                } else {
                    int i3 = fgetbits5 + 2;
                    while (true) {
                        int i4 = i3 - 1;
                        if (i3 <= 0 || i2 >= 20) {
                            break;
                        }
                        bArr[i2] = 0;
                        i2++;
                        i3 = i4;
                    }
                    i2--;
                }
            } else {
                bArr[i2] = (byte) fgetbits4;
            }
            i2++;
        }
        makeDecodeTables(bArr, 0, this.BD, 20);
        int i5 = 0;
        while (i5 < 404) {
            if (this.inAddr > this.readTop - 5 && !unpReadBuf()) {
                return false;
            }
            int decodeNumber = decodeNumber(this.BD);
            if (decodeNumber < 16) {
                bArr2[i5] = (byte) ((decodeNumber + this.unpOldTable[i5]) & 15);
                i5++;
            } else if (decodeNumber < 18) {
                if (decodeNumber == 16) {
                    fgetbits = (fgetbits() >>> 13) + 3;
                    faddbits(3);
                } else {
                    fgetbits = (fgetbits() >>> 9) + 11;
                    faddbits(7);
                }
                while (true) {
                    int i6 = fgetbits - 1;
                    if (fgetbits > 0 && i5 < 404) {
                        bArr2[i5] = bArr2[i5 - 1];
                        i5++;
                        fgetbits = i6;
                    }
                }
            } else {
                if (decodeNumber == 18) {
                    fgetbits2 = (fgetbits() >>> 13) + 3;
                    faddbits(3);
                } else {
                    fgetbits2 = (fgetbits() >>> 9) + 11;
                    faddbits(7);
                }
                while (true) {
                    int i7 = fgetbits2 - 1;
                    if (fgetbits2 > 0 && i5 < 404) {
                        bArr2[i5] = 0;
                        i5++;
                        fgetbits2 = i7;
                    }
                }
            }
        }
        this.tablesRead = true;
        if (this.inAddr > this.readTop) {
            return false;
        }
        makeDecodeTables(bArr2, 0, this.LD, Compress.NC);
        makeDecodeTables(bArr2, Compress.NC, this.DD, 60);
        makeDecodeTables(bArr2, 359, this.LDD, 17);
        makeDecodeTables(bArr2, 376, this.RD, 28);
        while (true) {
            byte[] bArr3 = this.unpOldTable;
            if (i >= bArr3.length) {
                return true;
            }
            bArr3[i] = bArr2[i];
            i++;
        }
    }

    private boolean readVMCode() throws IOException, RarException {
        int i = getbits() >> 8;
        addbits(8);
        int i2 = (i & 7) + 1;
        if (i2 == 7) {
            i2 = (getbits() >> 8) + 7;
            addbits(8);
        } else if (i2 == 8) {
            i2 = getbits();
            addbits(16);
        }
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < i2; i3++) {
            if (this.inAddr >= this.readTop - 1 && !unpReadBuf() && i3 < i2 - 1) {
                return false;
            }
            arrayList.add(Byte.valueOf((byte) (getbits() >> 8)));
            addbits(8);
        }
        return addVMCode(i, arrayList, i2);
    }

    private boolean readVMCodePPM() throws IOException, RarException {
        int decodeChar;
        int decodeChar2 = this.ppm.decodeChar();
        if (decodeChar2 == -1) {
            return false;
        }
        int i = (decodeChar2 & 7) + 1;
        if (i == 7) {
            int decodeChar3 = this.ppm.decodeChar();
            if (decodeChar3 == -1) {
                return false;
            }
            i = decodeChar3 + 7;
        } else if (i == 8) {
            int decodeChar4 = this.ppm.decodeChar();
            if (decodeChar4 == -1 || (decodeChar = this.ppm.decodeChar()) == -1) {
                return false;
            }
            i = (decodeChar4 * 256) + decodeChar;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < i; i2++) {
            int decodeChar5 = this.ppm.decodeChar();
            if (decodeChar5 == -1) {
                return false;
            }
            arrayList.add(Byte.valueOf((byte) decodeChar5));
        }
        return addVMCode(decodeChar2, arrayList, i);
    }

    private boolean addVMCode(int i, List<Byte> list, int i2) {
        int i3;
        UnpackFilter unpackFilter;
        int ReadData;
        BitInput bitInput = new BitInput();
        bitInput.InitBitInput();
        for (int i4 = 0; i4 < Math.min(32768, list.size()); i4++) {
            bitInput.getInBuf()[i4] = list.get(i4).byteValue();
        }
        this.rarVM.init();
        if ((i & 128) != 0) {
            i3 = RarVM.ReadData(bitInput);
            if (i3 == 0) {
                initFilters();
            } else {
                i3--;
            }
        } else {
            i3 = this.lastFilter;
        }
        if (i3 > this.filters.size() || i3 > this.oldFilterLengths.size()) {
            return false;
        }
        this.lastFilter = i3;
        boolean z = i3 == this.filters.size();
        UnpackFilter unpackFilter2 = new UnpackFilter();
        if (!z) {
            unpackFilter = this.filters.get(i3);
            unpackFilter2.setParentFilter(i3);
            unpackFilter.setExecCount(unpackFilter.getExecCount() + 1);
        } else if (i3 > 1024) {
            return false;
        } else {
            unpackFilter = new UnpackFilter();
            this.filters.add(unpackFilter);
            unpackFilter2.setParentFilter(this.filters.size() - 1);
            this.oldFilterLengths.add(0);
            unpackFilter.setExecCount(0);
        }
        this.prgStack.add(unpackFilter2);
        unpackFilter2.setExecCount(unpackFilter.getExecCount());
        int ReadData2 = RarVM.ReadData(bitInput);
        if ((i & 64) != 0) {
            ReadData2 += 258;
        }
        unpackFilter2.setBlockStart((this.unpPtr + ReadData2) & Compress.MAXWINMASK);
        if ((i & 32) != 0) {
            unpackFilter2.setBlockLength(RarVM.ReadData(bitInput));
        } else {
            unpackFilter2.setBlockLength(i3 < this.oldFilterLengths.size() ? this.oldFilterLengths.get(i3).intValue() : 0);
        }
        unpackFilter2.setNextWindow(this.wrPtr != this.unpPtr && ((this.wrPtr - this.unpPtr) & Compress.MAXWINMASK) <= ReadData2);
        this.oldFilterLengths.set(i3, Integer.valueOf(unpackFilter2.getBlockLength()));
        Arrays.fill(unpackFilter2.getPrg().getInitR(), 0);
        unpackFilter2.getPrg().getInitR()[3] = 245760;
        unpackFilter2.getPrg().getInitR()[4] = unpackFilter2.getBlockLength();
        unpackFilter2.getPrg().getInitR()[5] = unpackFilter2.getExecCount();
        if ((i & 16) != 0) {
            int fgetbits = bitInput.fgetbits() >>> 9;
            bitInput.faddbits(7);
            for (int i5 = 0; i5 < 7; i5++) {
                if (((1 << i5) & fgetbits) != 0) {
                    unpackFilter2.getPrg().getInitR()[i5] = RarVM.ReadData(bitInput);
                }
            }
        }
        if (z) {
            int ReadData3 = RarVM.ReadData(bitInput);
            if (ReadData3 >= 65536 || ReadData3 == 0) {
                return false;
            }
            byte[] bArr = new byte[ReadData3];
            for (int i6 = 0; i6 < ReadData3; i6++) {
                if (bitInput.Overflow(3)) {
                    return false;
                }
                bArr[i6] = (byte) (bitInput.fgetbits() >> 8);
                bitInput.faddbits(8);
            }
            this.rarVM.prepare(bArr, ReadData3, unpackFilter.getPrg());
        }
        unpackFilter2.getPrg().setAltCmd(unpackFilter.getPrg().getCmd());
        unpackFilter2.getPrg().setCmdCount(unpackFilter.getPrg().getCmdCount());
        int size = unpackFilter.getPrg().getStaticData().size();
        if (size > 0 && size < 8192) {
            unpackFilter2.getPrg().setStaticData(unpackFilter.getPrg().getStaticData());
        }
        if (unpackFilter2.getPrg().getGlobalData().size() < 64) {
            unpackFilter2.getPrg().getGlobalData().clear();
            unpackFilter2.getPrg().getGlobalData().setSize(64);
        }
        Vector<Byte> globalData = unpackFilter2.getPrg().getGlobalData();
        for (int i7 = 0; i7 < 7; i7++) {
            this.rarVM.setLowEndianValue(globalData, i7 * 4, unpackFilter2.getPrg().getInitR()[i7]);
        }
        this.rarVM.setLowEndianValue(globalData, 28, unpackFilter2.getBlockLength());
        this.rarVM.setLowEndianValue(globalData, 32, 0);
        this.rarVM.setLowEndianValue(globalData, 36, 0);
        this.rarVM.setLowEndianValue(globalData, 40, 0);
        this.rarVM.setLowEndianValue(globalData, 44, unpackFilter2.getExecCount());
        for (int i8 = 0; i8 < 16; i8++) {
            globalData.set(i8 + 48, (byte) 0);
        }
        if ((i & 8) != 0) {
            if (bitInput.Overflow(3) || (ReadData = RarVM.ReadData(bitInput)) > 8128) {
                return false;
            }
            int size2 = unpackFilter2.getPrg().getGlobalData().size();
            int i9 = ReadData + 64;
            if (size2 < i9) {
                unpackFilter2.getPrg().getGlobalData().setSize(i9 - size2);
            }
            Vector<Byte> globalData2 = unpackFilter2.getPrg().getGlobalData();
            for (int i10 = 0; i10 < ReadData; i10++) {
                if (bitInput.Overflow(3)) {
                    return false;
                }
                globalData2.set(64 + i10, Byte.valueOf((byte) (bitInput.fgetbits() >>> 8)));
                bitInput.faddbits(8);
            }
        }
        return true;
    }

    private void ExecuteCode(VMPreparedProgram vMPreparedProgram) {
        if (vMPreparedProgram.getGlobalData().size() > 0) {
            vMPreparedProgram.getInitR()[6] = (int) this.writtenFileSize;
            this.rarVM.setLowEndianValue(vMPreparedProgram.getGlobalData(), 36, (int) this.writtenFileSize);
            this.rarVM.setLowEndianValue(vMPreparedProgram.getGlobalData(), 40, (int) (this.writtenFileSize >>> 32));
            this.rarVM.execute(vMPreparedProgram);
        }
    }

    public boolean isFileExtracted() {
        return this.fileExtracted;
    }

    public void setDestSize(long j) {
        this.destUnpSize = j;
        this.fileExtracted = false;
    }

    public void setSuspended(boolean z) {
        this.suspended = z;
    }

    public int getChar() throws IOException, RarException {
        if (this.inAddr > 32738) {
            unpReadBuf();
        }
        byte[] bArr = this.inBuf;
        int i = this.inAddr;
        this.inAddr = i + 1;
        return bArr[i] & 255;
    }

    public int getPpmEscChar() {
        return this.ppmEscChar;
    }

    public void setPpmEscChar(int i) {
        this.ppmEscChar = i;
    }

    public void cleanUp() {
        SubAllocator subAlloc;
        ModelPPM modelPPM = this.ppm;
        if (modelPPM == null || (subAlloc = modelPPM.getSubAlloc()) == null) {
            return;
        }
        subAlloc.stopSubAllocator();
    }
}
