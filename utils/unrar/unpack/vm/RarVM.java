package com.movieboxpro.android.utils.unrar.unpack.vm;

import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import com.alibaba.fastjson.asm.Opcodes;
import com.google.common.base.Ascii;
import com.movieboxpro.android.utils.unrar.crc.RarCRC;
import com.movieboxpro.android.utils.unrar.io.Raw;
import java.util.List;
import java.util.Vector;
/* loaded from: classes3.dex */
public class RarVM extends BitInput {
    private static final long UINT_MASK = -1;
    public static final int VM_FIXEDGLOBALSIZE = 64;
    public static final int VM_GLOBALMEMADDR = 245760;
    public static final int VM_GLOBALMEMSIZE = 8192;
    public static final int VM_MEMMASK = 262143;
    public static final int VM_MEMSIZE = 262144;
    private static final int regCount = 8;
    private int IP;
    private int codeSize;
    private int flags;
    private int[] R = new int[8];
    private int maxOpCount = 25000000;
    private byte[] mem = null;

    public void init() {
        if (this.mem == null) {
            this.mem = new byte[262148];
        }
    }

    private boolean isVMMem(byte[] bArr) {
        return this.mem == bArr;
    }

    private int getValue(boolean z, byte[] bArr, int i) {
        if (z) {
            if (isVMMem(bArr)) {
                return bArr[i];
            }
            return bArr[i] & 255;
        } else if (isVMMem(bArr)) {
            return Raw.readIntLittleEndian(bArr, i);
        } else {
            return Raw.readIntBigEndian(bArr, i);
        }
    }

    private void setValue(boolean z, byte[] bArr, int i, int i2) {
        if (z) {
            if (isVMMem(bArr)) {
                bArr[i] = (byte) i2;
            } else {
                bArr[i] = (byte) ((bArr[i] & 0) | ((byte) (i2 & 255)));
            }
        } else if (isVMMem(bArr)) {
            Raw.writeIntLittleEndian(bArr, i, i2);
        } else {
            Raw.writeIntBigEndian(bArr, i, i2);
        }
    }

    public void setLowEndianValue(byte[] bArr, int i, int i2) {
        Raw.writeIntLittleEndian(bArr, i, i2);
    }

    public void setLowEndianValue(Vector<Byte> vector, int i, int i2) {
        vector.set(i + 0, Byte.valueOf((byte) (i2 & 255)));
        vector.set(i + 1, Byte.valueOf((byte) ((i2 >>> 8) & 255)));
        vector.set(i + 2, Byte.valueOf((byte) ((i2 >>> 16) & 255)));
        vector.set(i + 3, Byte.valueOf((byte) ((i2 >>> 24) & 255)));
    }

    private int getOperand(VMPreparedOperand vMPreparedOperand) {
        if (vMPreparedOperand.getType() == VMOpType.VM_OPREGMEM) {
            return Raw.readIntLittleEndian(this.mem, 262143 & (vMPreparedOperand.getOffset() + vMPreparedOperand.getBase()));
        }
        return Raw.readIntLittleEndian(this.mem, vMPreparedOperand.getOffset());
    }

    public void execute(VMPreparedProgram vMPreparedProgram) {
        List<VMPreparedCommand> cmd;
        for (int i = 0; i < vMPreparedProgram.getInitR().length; i++) {
            this.R[i] = vMPreparedProgram.getInitR()[i];
        }
        long min = Math.min(vMPreparedProgram.getGlobalData().size(), 8192) & (-1);
        if (min != 0) {
            for (int i2 = 0; i2 < min; i2++) {
                this.mem[i2 + VM_GLOBALMEMADDR] = vMPreparedProgram.getGlobalData().get(i2).byteValue();
            }
        }
        long min2 = Math.min(vMPreparedProgram.getStaticData().size(), 8192 - min) & (-1);
        if (min2 != 0) {
            for (int i3 = 0; i3 < min2; i3++) {
                this.mem[((int) min) + VM_GLOBALMEMADDR + i3] = vMPreparedProgram.getStaticData().get(i3).byteValue();
            }
        }
        this.R[7] = 262144;
        this.flags = 0;
        if (vMPreparedProgram.getAltCmd().size() != 0) {
            cmd = vMPreparedProgram.getAltCmd();
        } else {
            cmd = vMPreparedProgram.getCmd();
        }
        if (!ExecuteCode(cmd, vMPreparedProgram.getCmdCount())) {
            cmd.get(0).setOpCode(VMCommands.VM_RET);
        }
        int value = getValue(false, this.mem, 245792) & VM_MEMMASK;
        int value2 = 262143 & getValue(false, this.mem, 245788);
        if (value + value2 >= 262144) {
            value = 0;
            value2 = 0;
        }
        vMPreparedProgram.setFilteredDataOffset(value);
        vMPreparedProgram.setFilteredDataSize(value2);
        vMPreparedProgram.getGlobalData().clear();
        int min3 = Math.min(getValue(false, this.mem, 245808), 8128);
        if (min3 != 0) {
            int i4 = min3 + 64;
            vMPreparedProgram.getGlobalData().setSize(i4);
            for (int i5 = 0; i5 < i4; i5++) {
                vMPreparedProgram.getGlobalData().set(i5, Byte.valueOf(this.mem[i5 + VM_GLOBALMEMADDR]));
            }
        }
    }

    public byte[] getMem() {
        return this.mem;
    }

    private boolean setIP(int i) {
        if (i >= this.codeSize) {
            return true;
        }
        int i2 = this.maxOpCount - 1;
        this.maxOpCount = i2;
        if (i2 <= 0) {
            return false;
        }
        this.IP = i;
        return true;
    }

    private boolean ExecuteCode(List<VMPreparedCommand> list, int i) {
        int flag;
        int i2;
        int flag2;
        int i3;
        byte[] bArr;
        byte[] bArr2;
        int flag3;
        byte[] bArr3;
        byte[] bArr4;
        int flag4;
        byte[] bArr5;
        byte[] bArr6;
        int flag5;
        byte[] bArr7;
        byte[] bArr8;
        int flag6;
        int flag7;
        int flag8;
        int flag9;
        int flag10;
        int flag11;
        int flag12;
        int flag13;
        int[] iArr;
        int i4;
        int flag14;
        int i5;
        int flag15;
        this.maxOpCount = 25000000;
        this.codeSize = i;
        this.IP = 0;
        while (true) {
            VMPreparedCommand vMPreparedCommand = list.get(this.IP);
            int operand = getOperand(vMPreparedCommand.getOp1());
            int operand2 = getOperand(vMPreparedCommand.getOp2());
            switch (AnonymousClass1.$SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[vMPreparedCommand.getOpCode().ordinal()]) {
                case 1:
                    setValue(vMPreparedCommand.isByteMode(), this.mem, operand, getValue(vMPreparedCommand.isByteMode(), this.mem, operand2));
                    break;
                case 2:
                    byte[] bArr9 = this.mem;
                    setValue(true, bArr9, operand, getValue(true, bArr9, operand2));
                    break;
                case 3:
                    byte[] bArr10 = this.mem;
                    setValue(false, bArr10, operand, getValue(false, bArr10, operand2));
                    break;
                case 4:
                    int value = getValue(vMPreparedCommand.isByteMode(), this.mem, operand);
                    int value2 = value - getValue(vMPreparedCommand.isByteMode(), this.mem, operand2);
                    if (value2 == 0) {
                        this.flags = VMFlags.VM_FZ.getFlag();
                        break;
                    } else {
                        this.flags = value2 > value ? 1 : (value2 & VMFlags.VM_FS.getFlag()) | 0;
                        break;
                    }
                case 5:
                    int value3 = getValue(true, this.mem, operand);
                    int value4 = value3 - getValue(true, this.mem, operand2);
                    if (value4 == 0) {
                        this.flags = VMFlags.VM_FZ.getFlag();
                        break;
                    } else {
                        this.flags = value4 > value3 ? 1 : (VMFlags.VM_FS.getFlag() & value4) | 0;
                        break;
                    }
                case 6:
                    int value5 = getValue(false, this.mem, operand);
                    int value6 = value5 - getValue(false, this.mem, operand2);
                    if (value6 == 0) {
                        this.flags = VMFlags.VM_FZ.getFlag();
                        break;
                    } else {
                        this.flags = value6 > value5 ? 1 : (VMFlags.VM_FS.getFlag() & value6) | 0;
                        break;
                    }
                case 7:
                    int value7 = getValue(vMPreparedCommand.isByteMode(), this.mem, operand);
                    int value8 = (int) ((value7 + getValue(vMPreparedCommand.isByteMode(), this.mem, operand2)) & (-1));
                    if (vMPreparedCommand.isByteMode()) {
                        value8 &= 255;
                        if (value8 < value7) {
                            i3 = 1;
                        } else {
                            if (value8 == 0) {
                                flag2 = VMFlags.VM_FZ.getFlag();
                            } else {
                                flag2 = (value8 & 128) != 0 ? VMFlags.VM_FS.getFlag() : 0;
                            }
                            i3 = flag2 | 0;
                        }
                        this.flags = i3;
                    } else {
                        if (value8 < value7) {
                            i2 = 1;
                        } else {
                            if (value8 == 0) {
                                flag = VMFlags.VM_FZ.getFlag();
                            } else {
                                flag = VMFlags.VM_FS.getFlag() & value8;
                            }
                            i2 = flag | 0;
                        }
                        this.flags = i2;
                    }
                    setValue(vMPreparedCommand.isByteMode(), this.mem, operand, value8);
                    break;
                case 8:
                    setValue(true, this.mem, operand, (int) (getValue(true, bArr, operand) & (getValue(true, this.mem, operand2) - 1) & (-1)));
                    break;
                case 9:
                    setValue(false, this.mem, operand, (int) (getValue(false, bArr2, operand) & (getValue(false, this.mem, operand2) - 1) & (-1)));
                    break;
                case 10:
                    int value9 = getValue(vMPreparedCommand.isByteMode(), this.mem, operand);
                    int value10 = (int) (value9 & ((-1) - getValue(vMPreparedCommand.isByteMode(), this.mem, operand2)) & (-1));
                    if (value10 == 0) {
                        flag3 = VMFlags.VM_FZ.getFlag();
                    } else {
                        flag3 = value10 > value9 ? 1 : (VMFlags.VM_FS.getFlag() & value10) | 0;
                    }
                    this.flags = flag3;
                    setValue(vMPreparedCommand.isByteMode(), this.mem, operand, value10);
                    break;
                case 11:
                    setValue(true, this.mem, operand, (int) (getValue(true, bArr3, operand) & ((-1) - getValue(true, this.mem, operand2)) & (-1)));
                    break;
                case 12:
                    setValue(false, this.mem, operand, (int) (getValue(false, bArr4, operand) & ((-1) - getValue(false, this.mem, operand2)) & (-1)));
                    break;
                case 13:
                    if ((this.flags & VMFlags.VM_FZ.getFlag()) == 0) {
                        break;
                    } else {
                        setIP(getValue(false, this.mem, operand));
                        continue;
                    }
                case 14:
                    if ((this.flags & VMFlags.VM_FZ.getFlag()) != 0) {
                        break;
                    } else {
                        setIP(getValue(false, this.mem, operand));
                        continue;
                    }
                case 15:
                    int value11 = (int) (getValue(vMPreparedCommand.isByteMode(), this.mem, operand) & 0);
                    if (vMPreparedCommand.isByteMode()) {
                        value11 &= 255;
                    }
                    setValue(vMPreparedCommand.isByteMode(), this.mem, operand, value11);
                    if (value11 == 0) {
                        flag4 = VMFlags.VM_FZ.getFlag();
                    } else {
                        flag4 = VMFlags.VM_FS.getFlag() & value11;
                    }
                    this.flags = flag4;
                    break;
                case 16:
                    setValue(true, this.mem, operand, (int) (getValue(true, bArr5, operand) & 0));
                    break;
                case 17:
                    setValue(false, this.mem, operand, (int) (getValue(false, bArr6, operand) & 0));
                    break;
                case 18:
                    int value12 = (int) (getValue(vMPreparedCommand.isByteMode(), this.mem, operand) & (-2));
                    setValue(vMPreparedCommand.isByteMode(), this.mem, operand, value12);
                    if (value12 == 0) {
                        flag5 = VMFlags.VM_FZ.getFlag();
                    } else {
                        flag5 = VMFlags.VM_FS.getFlag() & value12;
                    }
                    this.flags = flag5;
                    break;
                case 19:
                    setValue(true, this.mem, operand, (int) (getValue(true, bArr7, operand) & (-2)));
                    break;
                case 20:
                    setValue(false, this.mem, operand, (int) (getValue(false, bArr8, operand) & (-2)));
                    break;
                case 21:
                    setIP(getValue(false, this.mem, operand));
                    continue;
                case 22:
                    int value13 = getValue(vMPreparedCommand.isByteMode(), this.mem, operand2) ^ getValue(vMPreparedCommand.isByteMode(), this.mem, operand);
                    if (value13 == 0) {
                        flag6 = VMFlags.VM_FZ.getFlag();
                    } else {
                        flag6 = VMFlags.VM_FS.getFlag() & value13;
                    }
                    this.flags = flag6;
                    setValue(vMPreparedCommand.isByteMode(), this.mem, operand, value13);
                    break;
                case 23:
                    int value14 = getValue(vMPreparedCommand.isByteMode(), this.mem, operand2) & getValue(vMPreparedCommand.isByteMode(), this.mem, operand);
                    if (value14 == 0) {
                        flag7 = VMFlags.VM_FZ.getFlag();
                    } else {
                        flag7 = VMFlags.VM_FS.getFlag() & value14;
                    }
                    this.flags = flag7;
                    setValue(vMPreparedCommand.isByteMode(), this.mem, operand, value14);
                    break;
                case 24:
                    int value15 = getValue(vMPreparedCommand.isByteMode(), this.mem, operand2) | getValue(vMPreparedCommand.isByteMode(), this.mem, operand);
                    if (value15 == 0) {
                        flag8 = VMFlags.VM_FZ.getFlag();
                    } else {
                        flag8 = VMFlags.VM_FS.getFlag() & value15;
                    }
                    this.flags = flag8;
                    setValue(vMPreparedCommand.isByteMode(), this.mem, operand, value15);
                    break;
                case 25:
                    int value16 = getValue(vMPreparedCommand.isByteMode(), this.mem, operand2) & getValue(vMPreparedCommand.isByteMode(), this.mem, operand);
                    if (value16 == 0) {
                        flag9 = VMFlags.VM_FZ.getFlag();
                    } else {
                        flag9 = value16 & VMFlags.VM_FS.getFlag();
                    }
                    this.flags = flag9;
                    break;
                case 26:
                    if ((this.flags & VMFlags.VM_FS.getFlag()) == 0) {
                        break;
                    } else {
                        setIP(getValue(false, this.mem, operand));
                        continue;
                    }
                case 27:
                    if ((this.flags & VMFlags.VM_FS.getFlag()) != 0) {
                        break;
                    } else {
                        setIP(getValue(false, this.mem, operand));
                        continue;
                    }
                case 28:
                    if ((this.flags & VMFlags.VM_FC.getFlag()) == 0) {
                        break;
                    } else {
                        setIP(getValue(false, this.mem, operand));
                        continue;
                    }
                case 29:
                    if ((this.flags & (VMFlags.VM_FC.getFlag() | VMFlags.VM_FZ.getFlag())) == 0) {
                        break;
                    } else {
                        setIP(getValue(false, this.mem, operand));
                        continue;
                    }
                case 30:
                    if ((this.flags & (VMFlags.VM_FC.getFlag() | VMFlags.VM_FZ.getFlag())) != 0) {
                        break;
                    } else {
                        setIP(getValue(false, this.mem, operand));
                        continue;
                    }
                case 31:
                    if ((this.flags & VMFlags.VM_FC.getFlag()) != 0) {
                        break;
                    } else {
                        setIP(getValue(false, this.mem, operand));
                        continue;
                    }
                case 32:
                    int[] iArr2 = this.R;
                    iArr2[7] = iArr2[7] - 4;
                    byte[] bArr11 = this.mem;
                    setValue(false, bArr11, iArr2[7] & VM_MEMMASK, getValue(false, bArr11, operand));
                    break;
                case 33:
                    byte[] bArr12 = this.mem;
                    setValue(false, bArr12, operand, getValue(false, bArr12, this.R[7] & VM_MEMMASK));
                    int[] iArr3 = this.R;
                    iArr3[7] = iArr3[7] + 4;
                    break;
                case 34:
                    int[] iArr4 = this.R;
                    iArr4[7] = iArr4[7] - 4;
                    setValue(false, this.mem, iArr4[7] & VM_MEMMASK, this.IP + 1);
                    setIP(getValue(false, this.mem, operand));
                    continue;
                case 35:
                    setValue(vMPreparedCommand.isByteMode(), this.mem, operand, getValue(vMPreparedCommand.isByteMode(), this.mem, operand) ^ (-1));
                    break;
                case 36:
                    int value17 = getValue(vMPreparedCommand.isByteMode(), this.mem, operand);
                    int value18 = getValue(vMPreparedCommand.isByteMode(), this.mem, operand2);
                    int i6 = value17 << value18;
                    if (i6 == 0) {
                        flag10 = VMFlags.VM_FZ.getFlag();
                    } else {
                        flag10 = VMFlags.VM_FS.getFlag() & i6;
                    }
                    this.flags = (((value17 << (value18 + (-1))) & Integer.MIN_VALUE) != 0 ? VMFlags.VM_FC.getFlag() : 0) | flag10;
                    setValue(vMPreparedCommand.isByteMode(), this.mem, operand, i6);
                    break;
                case 37:
                    int value19 = getValue(vMPreparedCommand.isByteMode(), this.mem, operand);
                    int value20 = getValue(vMPreparedCommand.isByteMode(), this.mem, operand2);
                    int i7 = value19 >>> value20;
                    if (i7 == 0) {
                        flag11 = VMFlags.VM_FZ.getFlag();
                    } else {
                        flag11 = VMFlags.VM_FS.getFlag() & i7;
                    }
                    this.flags = ((value19 >>> (value20 - 1)) & VMFlags.VM_FC.getFlag()) | flag11;
                    setValue(vMPreparedCommand.isByteMode(), this.mem, operand, i7);
                    break;
                case 38:
                    int value21 = getValue(vMPreparedCommand.isByteMode(), this.mem, operand);
                    int value22 = getValue(vMPreparedCommand.isByteMode(), this.mem, operand2);
                    int i8 = value21 >> value22;
                    if (i8 == 0) {
                        flag12 = VMFlags.VM_FZ.getFlag();
                    } else {
                        flag12 = VMFlags.VM_FS.getFlag() & i8;
                    }
                    this.flags = ((value21 >> (value22 - 1)) & VMFlags.VM_FC.getFlag()) | flag12;
                    setValue(vMPreparedCommand.isByteMode(), this.mem, operand, i8);
                    break;
                case 39:
                    int i9 = -getValue(vMPreparedCommand.isByteMode(), this.mem, operand);
                    if (i9 == 0) {
                        flag13 = VMFlags.VM_FZ.getFlag();
                    } else {
                        flag13 = VMFlags.VM_FC.getFlag() | (VMFlags.VM_FS.getFlag() & i9);
                    }
                    this.flags = flag13;
                    setValue(vMPreparedCommand.isByteMode(), this.mem, operand, i9);
                    break;
                case 40:
                    byte[] bArr13 = this.mem;
                    setValue(true, bArr13, operand, -getValue(true, bArr13, operand));
                    break;
                case 41:
                    byte[] bArr14 = this.mem;
                    setValue(false, bArr14, operand, -getValue(false, bArr14, operand));
                    break;
                case 42:
                    int i10 = this.R[7] - 4;
                    int i11 = 0;
                    while (i11 < 8) {
                        setValue(false, this.mem, i10 & VM_MEMMASK, this.R[i11]);
                        i11++;
                        i10 -= 4;
                    }
                    this.R[7] = iArr[7] - 32;
                    break;
                case 43:
                    int i12 = this.R[7];
                    int i13 = 0;
                    while (i13 < 8) {
                        this.R[7 - i13] = getValue(false, this.mem, i12 & VM_MEMMASK);
                        i13++;
                        i12 += 4;
                    }
                    break;
                case 44:
                    int[] iArr5 = this.R;
                    iArr5[7] = iArr5[7] - 4;
                    setValue(false, this.mem, iArr5[7] & VM_MEMMASK, this.flags);
                    break;
                case 45:
                    this.flags = getValue(false, this.mem, this.R[7] & VM_MEMMASK);
                    int[] iArr6 = this.R;
                    iArr6[7] = iArr6[7] + 4;
                    break;
                case 46:
                    byte[] bArr15 = this.mem;
                    setValue(false, bArr15, operand, getValue(true, bArr15, operand2));
                    break;
                case 47:
                    byte[] bArr16 = this.mem;
                    setValue(false, bArr16, operand, (byte) getValue(true, bArr16, operand2));
                    break;
                case 48:
                    int value23 = getValue(vMPreparedCommand.isByteMode(), this.mem, operand);
                    setValue(vMPreparedCommand.isByteMode(), this.mem, operand, getValue(vMPreparedCommand.isByteMode(), this.mem, operand2));
                    setValue(vMPreparedCommand.isByteMode(), this.mem, operand2, value23);
                    break;
                case 49:
                    setValue(vMPreparedCommand.isByteMode(), this.mem, operand, (int) (getValue(vMPreparedCommand.isByteMode(), this.mem, operand) & (getValue(vMPreparedCommand.isByteMode(), this.mem, operand2) * (-1)) & (-1) & (-1)));
                    break;
                case 50:
                    int value24 = getValue(vMPreparedCommand.isByteMode(), this.mem, operand2);
                    if (value24 != 0) {
                        setValue(vMPreparedCommand.isByteMode(), this.mem, operand, getValue(vMPreparedCommand.isByteMode(), this.mem, operand) / value24);
                        break;
                    }
                    break;
                case 51:
                    int value25 = getValue(vMPreparedCommand.isByteMode(), this.mem, operand);
                    int flag16 = this.flags & VMFlags.VM_FC.getFlag();
                    int value26 = (int) (value25 & (getValue(vMPreparedCommand.isByteMode(), this.mem, operand2) - 1) & (flag16 - 1) & (-1));
                    if (vMPreparedCommand.isByteMode()) {
                        value26 &= 255;
                    }
                    if (value26 < value25 || (value26 == value25 && flag16 != 0)) {
                        i4 = 1;
                    } else {
                        if (value26 == 0) {
                            flag14 = VMFlags.VM_FZ.getFlag();
                        } else {
                            flag14 = VMFlags.VM_FS.getFlag() & value26;
                        }
                        i4 = flag14 | 0;
                    }
                    this.flags = i4;
                    setValue(vMPreparedCommand.isByteMode(), this.mem, operand, value26);
                    break;
                case 52:
                    int value27 = getValue(vMPreparedCommand.isByteMode(), this.mem, operand);
                    int flag17 = this.flags & VMFlags.VM_FC.getFlag();
                    int value28 = (int) (value27 & ((-1) - getValue(vMPreparedCommand.isByteMode(), this.mem, operand2)) & ((-1) - flag17) & (-1));
                    if (vMPreparedCommand.isByteMode()) {
                        value28 &= 255;
                    }
                    if (value28 > value27 || (value28 == value27 && flag17 != 0)) {
                        i5 = 1;
                    } else {
                        if (value28 == 0) {
                            flag15 = VMFlags.VM_FZ.getFlag();
                        } else {
                            flag15 = VMFlags.VM_FS.getFlag() & value28;
                        }
                        i5 = flag15 | 0;
                    }
                    this.flags = i5;
                    setValue(vMPreparedCommand.isByteMode(), this.mem, operand, value28);
                    break;
                case 53:
                    int[] iArr7 = this.R;
                    if (iArr7[7] >= 262144) {
                        return true;
                    }
                    setIP(getValue(false, this.mem, iArr7[7] & VM_MEMMASK));
                    int[] iArr8 = this.R;
                    iArr8[7] = iArr8[7] + 4;
                    continue;
                case 54:
                    ExecuteStandardFilter(VMStandardFilters.findFilter(vMPreparedCommand.getOp1().getData()));
                    break;
            }
            this.IP++;
            this.maxOpCount--;
        }
    }

    public void prepare(byte[] bArr, int i, VMPreparedProgram vMPreparedProgram) {
        int cmdCount;
        InitBitInput();
        int min = Math.min(32768, i);
        for (int i2 = 0; i2 < min; i2++) {
            byte[] bArr2 = this.inBuf;
            bArr2[i2] = (byte) (bArr2[i2] | bArr[i2]);
        }
        byte b = 0;
        for (int i3 = 1; i3 < i; i3++) {
            b = (byte) (b ^ bArr[i3]);
        }
        faddbits(8);
        vMPreparedProgram.setCmdCount(0);
        if (b == bArr[0]) {
            VMStandardFilters IsStandardFilter = IsStandardFilter(bArr, i);
            if (IsStandardFilter != VMStandardFilters.VMSF_NONE) {
                VMPreparedCommand vMPreparedCommand = new VMPreparedCommand();
                vMPreparedCommand.setOpCode(VMCommands.VM_STANDARD);
                vMPreparedCommand.getOp1().setData(IsStandardFilter.getFilter());
                vMPreparedCommand.getOp1().setType(VMOpType.VM_OPNONE);
                vMPreparedCommand.getOp2().setType(VMOpType.VM_OPNONE);
                vMPreparedProgram.getCmd().add(vMPreparedCommand);
                vMPreparedProgram.setCmdCount(vMPreparedProgram.getCmdCount() + 1);
                i = 0;
            }
            int fgetbits = fgetbits();
            faddbits(1);
            if ((fgetbits & 32768) != 0) {
                long ReadData = ReadData(this) & 0;
                for (int i4 = 0; this.inAddr < i && i4 < ReadData; i4++) {
                    vMPreparedProgram.getStaticData().add(Byte.valueOf((byte) (fgetbits() >> 8)));
                    faddbits(8);
                }
            }
            while (this.inAddr < i) {
                VMPreparedCommand vMPreparedCommand2 = new VMPreparedCommand();
                int fgetbits2 = fgetbits();
                if ((fgetbits2 & 32768) == 0) {
                    vMPreparedCommand2.setOpCode(VMCommands.findVMCommand(fgetbits2 >> 12));
                    faddbits(4);
                } else {
                    vMPreparedCommand2.setOpCode(VMCommands.findVMCommand((fgetbits2 >> 10) - 24));
                    faddbits(6);
                }
                if ((VMCmdFlags.VM_CmdFlags[vMPreparedCommand2.getOpCode().getVMCommand()] & 4) != 0) {
                    vMPreparedCommand2.setByteMode((fgetbits() >> 15) == 1);
                    faddbits(1);
                } else {
                    vMPreparedCommand2.setByteMode(false);
                }
                vMPreparedCommand2.getOp1().setType(VMOpType.VM_OPNONE);
                vMPreparedCommand2.getOp2().setType(VMOpType.VM_OPNONE);
                int i5 = VMCmdFlags.VM_CmdFlags[vMPreparedCommand2.getOpCode().getVMCommand()] & 3;
                if (i5 > 0) {
                    decodeArg(vMPreparedCommand2.getOp1(), vMPreparedCommand2.isByteMode());
                    if (i5 == 2) {
                        decodeArg(vMPreparedCommand2.getOp2(), vMPreparedCommand2.isByteMode());
                    } else if (vMPreparedCommand2.getOp1().getType() == VMOpType.VM_OPINT && (VMCmdFlags.VM_CmdFlags[vMPreparedCommand2.getOpCode().getVMCommand()] & Ascii.CAN) != 0) {
                        int data = vMPreparedCommand2.getOp1().getData();
                        if (data >= 256) {
                            cmdCount = data + InputDeviceCompat.SOURCE_ANY;
                        } else {
                            if (data >= 136) {
                                data -= 264;
                            } else if (data >= 16) {
                                data -= 8;
                            } else if (data >= 8) {
                                data -= 16;
                            }
                            cmdCount = data + vMPreparedProgram.getCmdCount();
                        }
                        vMPreparedCommand2.getOp1().setData(cmdCount);
                    }
                }
                vMPreparedProgram.setCmdCount(vMPreparedProgram.getCmdCount() + 1);
                vMPreparedProgram.getCmd().add(vMPreparedCommand2);
            }
        }
        VMPreparedCommand vMPreparedCommand3 = new VMPreparedCommand();
        vMPreparedCommand3.setOpCode(VMCommands.VM_RET);
        vMPreparedCommand3.getOp1().setType(VMOpType.VM_OPNONE);
        vMPreparedCommand3.getOp2().setType(VMOpType.VM_OPNONE);
        vMPreparedProgram.getCmd().add(vMPreparedCommand3);
        vMPreparedProgram.setCmdCount(vMPreparedProgram.getCmdCount() + 1);
        if (i != 0) {
            optimize(vMPreparedProgram);
        }
    }

    private void decodeArg(VMPreparedOperand vMPreparedOperand, boolean z) {
        int fgetbits = fgetbits();
        if ((32768 & fgetbits) != 0) {
            vMPreparedOperand.setType(VMOpType.VM_OPREG);
            vMPreparedOperand.setData((fgetbits >> 12) & 7);
            vMPreparedOperand.setOffset(vMPreparedOperand.getData());
            faddbits(4);
        } else if ((49152 & fgetbits) == 0) {
            vMPreparedOperand.setType(VMOpType.VM_OPINT);
            if (z) {
                vMPreparedOperand.setData((fgetbits >> 6) & 255);
                faddbits(10);
                return;
            }
            faddbits(2);
            vMPreparedOperand.setData(ReadData(this));
        } else {
            vMPreparedOperand.setType(VMOpType.VM_OPREGMEM);
            if ((fgetbits & 8192) == 0) {
                vMPreparedOperand.setData((fgetbits >> 10) & 7);
                vMPreparedOperand.setOffset(vMPreparedOperand.getData());
                vMPreparedOperand.setBase(0);
                faddbits(6);
                return;
            }
            if ((fgetbits & 4096) == 0) {
                vMPreparedOperand.setData((fgetbits >> 9) & 7);
                vMPreparedOperand.setOffset(vMPreparedOperand.getData());
                faddbits(7);
            } else {
                vMPreparedOperand.setData(0);
                faddbits(4);
            }
            vMPreparedOperand.setBase(ReadData(this));
        }
    }

    private void optimize(VMPreparedProgram vMPreparedProgram) {
        List<VMPreparedCommand> cmd = vMPreparedProgram.getCmd();
        for (VMPreparedCommand vMPreparedCommand : cmd) {
            int i = AnonymousClass1.$SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[vMPreparedCommand.getOpCode().ordinal()];
            boolean z = true;
            if (i == 1) {
                vMPreparedCommand.setOpCode(vMPreparedCommand.isByteMode() ? VMCommands.VM_MOVB : VMCommands.VM_MOVD);
            } else if (i == 4) {
                vMPreparedCommand.setOpCode(vMPreparedCommand.isByteMode() ? VMCommands.VM_CMPB : VMCommands.VM_CMPD);
            } else if ((VMCmdFlags.VM_CmdFlags[vMPreparedCommand.getOpCode().getVMCommand()] & 64) != 0) {
                for (int indexOf = cmd.indexOf(vMPreparedCommand) + 1; indexOf < cmd.size(); indexOf++) {
                    byte b = VMCmdFlags.VM_CmdFlags[cmd.get(indexOf).getOpCode().getVMCommand()];
                    if ((b & 56) != 0) {
                        break;
                    } else if ((b & 64) != 0) {
                        break;
                    }
                }
                z = false;
                if (!z) {
                    int i2 = AnonymousClass1.$SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[vMPreparedCommand.getOpCode().ordinal()];
                    if (i2 == 7) {
                        vMPreparedCommand.setOpCode(vMPreparedCommand.isByteMode() ? VMCommands.VM_ADDB : VMCommands.VM_ADDD);
                    } else if (i2 == 10) {
                        vMPreparedCommand.setOpCode(vMPreparedCommand.isByteMode() ? VMCommands.VM_SUBB : VMCommands.VM_SUBD);
                    } else if (i2 == 15) {
                        vMPreparedCommand.setOpCode(vMPreparedCommand.isByteMode() ? VMCommands.VM_INCB : VMCommands.VM_INCD);
                    } else if (i2 == 18) {
                        vMPreparedCommand.setOpCode(vMPreparedCommand.isByteMode() ? VMCommands.VM_DECB : VMCommands.VM_DECD);
                    } else if (i2 == 39) {
                        vMPreparedCommand.setOpCode(vMPreparedCommand.isByteMode() ? VMCommands.VM_NEGB : VMCommands.VM_NEGD);
                    }
                }
            }
        }
    }

    public static int ReadData(BitInput bitInput) {
        int fgetbits = bitInput.fgetbits();
        int i = 49152 & fgetbits;
        if (i == 0) {
            bitInput.faddbits(6);
            return (fgetbits >> 10) & 15;
        } else if (i == 16384) {
            if ((fgetbits & 15360) == 0) {
                int i2 = ((fgetbits >> 2) & 255) | InputDeviceCompat.SOURCE_ANY;
                bitInput.faddbits(14);
                return i2;
            }
            int i3 = (fgetbits >> 6) & 255;
            bitInput.faddbits(10);
            return i3;
        } else if (i == 32768) {
            bitInput.faddbits(2);
            int fgetbits2 = bitInput.fgetbits();
            bitInput.faddbits(16);
            return fgetbits2;
        } else {
            bitInput.faddbits(2);
            bitInput.faddbits(16);
            int fgetbits3 = (bitInput.fgetbits() << 16) | bitInput.fgetbits();
            bitInput.faddbits(16);
            return fgetbits3;
        }
    }

    private VMStandardFilters IsStandardFilter(byte[] bArr, int i) {
        VMStandardFilterSignature[] vMStandardFilterSignatureArr = {new VMStandardFilterSignature(53, -1386780537, VMStandardFilters.VMSF_E8), new VMStandardFilterSignature(57, 1020781950, VMStandardFilters.VMSF_E8E9), new VMStandardFilterSignature(120, 929663295, VMStandardFilters.VMSF_ITANIUM), new VMStandardFilterSignature(29, 235276157, VMStandardFilters.VMSF_DELTA), new VMStandardFilterSignature(Opcodes.FCMPL, 472669640, VMStandardFilters.VMSF_RGB), new VMStandardFilterSignature(216, -1132075263, VMStandardFilters.VMSF_AUDIO), new VMStandardFilterSignature(40, 1186579808, VMStandardFilters.VMSF_UPCASE)};
        int checkCrc = RarCRC.checkCrc(-1, bArr, 0, bArr.length) ^ (-1);
        for (int i2 = 0; i2 < 7; i2++) {
            if (vMStandardFilterSignatureArr[i2].getCRC() == checkCrc && vMStandardFilterSignatureArr[i2].getLength() == bArr.length) {
                return vMStandardFilterSignatureArr[i2].getType();
            }
        }
        return VMStandardFilters.VMSF_NONE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.movieboxpro.android.utils.unrar.unpack.vm.RarVM$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands;
        static final /* synthetic */ int[] $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMStandardFilters;

        static {
            int[] iArr = new int[VMStandardFilters.values().length];
            $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMStandardFilters = iArr;
            try {
                iArr[VMStandardFilters.VMSF_E8.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMStandardFilters[VMStandardFilters.VMSF_E8E9.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMStandardFilters[VMStandardFilters.VMSF_ITANIUM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMStandardFilters[VMStandardFilters.VMSF_DELTA.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMStandardFilters[VMStandardFilters.VMSF_RGB.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMStandardFilters[VMStandardFilters.VMSF_AUDIO.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMStandardFilters[VMStandardFilters.VMSF_UPCASE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr2 = new int[VMCommands.values().length];
            $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands = iArr2;
            try {
                iArr2[VMCommands.VM_MOV.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_MOVB.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_MOVD.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_CMP.ordinal()] = 4;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_CMPB.ordinal()] = 5;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_CMPD.ordinal()] = 6;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_ADD.ordinal()] = 7;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_ADDB.ordinal()] = 8;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_ADDD.ordinal()] = 9;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_SUB.ordinal()] = 10;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_SUBB.ordinal()] = 11;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_SUBD.ordinal()] = 12;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_JZ.ordinal()] = 13;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_JNZ.ordinal()] = 14;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_INC.ordinal()] = 15;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_INCB.ordinal()] = 16;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_INCD.ordinal()] = 17;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_DEC.ordinal()] = 18;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_DECB.ordinal()] = 19;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_DECD.ordinal()] = 20;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_JMP.ordinal()] = 21;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_XOR.ordinal()] = 22;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_AND.ordinal()] = 23;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_OR.ordinal()] = 24;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_TEST.ordinal()] = 25;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_JS.ordinal()] = 26;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_JNS.ordinal()] = 27;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_JB.ordinal()] = 28;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_JBE.ordinal()] = 29;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_JA.ordinal()] = 30;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_JAE.ordinal()] = 31;
            } catch (NoSuchFieldError unused38) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_PUSH.ordinal()] = 32;
            } catch (NoSuchFieldError unused39) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_POP.ordinal()] = 33;
            } catch (NoSuchFieldError unused40) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_CALL.ordinal()] = 34;
            } catch (NoSuchFieldError unused41) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_NOT.ordinal()] = 35;
            } catch (NoSuchFieldError unused42) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_SHL.ordinal()] = 36;
            } catch (NoSuchFieldError unused43) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_SHR.ordinal()] = 37;
            } catch (NoSuchFieldError unused44) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_SAR.ordinal()] = 38;
            } catch (NoSuchFieldError unused45) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_NEG.ordinal()] = 39;
            } catch (NoSuchFieldError unused46) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_NEGB.ordinal()] = 40;
            } catch (NoSuchFieldError unused47) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_NEGD.ordinal()] = 41;
            } catch (NoSuchFieldError unused48) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_PUSHA.ordinal()] = 42;
            } catch (NoSuchFieldError unused49) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_POPA.ordinal()] = 43;
            } catch (NoSuchFieldError unused50) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_PUSHF.ordinal()] = 44;
            } catch (NoSuchFieldError unused51) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_POPF.ordinal()] = 45;
            } catch (NoSuchFieldError unused52) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_MOVZX.ordinal()] = 46;
            } catch (NoSuchFieldError unused53) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_MOVSX.ordinal()] = 47;
            } catch (NoSuchFieldError unused54) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_XCHG.ordinal()] = 48;
            } catch (NoSuchFieldError unused55) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_MUL.ordinal()] = 49;
            } catch (NoSuchFieldError unused56) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_DIV.ordinal()] = 50;
            } catch (NoSuchFieldError unused57) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_ADC.ordinal()] = 51;
            } catch (NoSuchFieldError unused58) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_SBB.ordinal()] = 52;
            } catch (NoSuchFieldError unused59) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_RET.ordinal()] = 53;
            } catch (NoSuchFieldError unused60) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_STANDARD.ordinal()] = 54;
            } catch (NoSuchFieldError unused61) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMCommands[VMCommands.VM_PRINT.ordinal()] = 55;
            } catch (NoSuchFieldError unused62) {
            }
        }
    }

    private void ExecuteStandardFilter(VMStandardFilters vMStandardFilters) {
        byte b;
        int i;
        int i2;
        int i3;
        byte[] bArr;
        char c = 3;
        switch (AnonymousClass1.$SwitchMap$com$movieboxpro$android$utils$unrar$unpack$vm$VMStandardFilters[vMStandardFilters.ordinal()]) {
            case 1:
            case 2:
                int[] iArr = this.R;
                int i4 = iArr[4];
                long j = iArr[6] & (-1);
                if (i4 >= 245760) {
                    return;
                }
                byte b2 = (byte) (vMStandardFilters == VMStandardFilters.VMSF_E8E9 ? 233 : 232);
                int i5 = 0;
                while (i5 < i4 - 4) {
                    int i6 = i5 + 1;
                    byte b3 = this.mem[i5];
                    if (b3 == 232 || b3 == b2) {
                        long j2 = i6 + j;
                        long value = getValue(false, this.mem, i6);
                        if ((value & (-2147483648L)) != 0) {
                            if (((j2 + value) & (-2147483648L)) == 0) {
                                setValue(false, this.mem, i6, ((int) value) + 16777216);
                            }
                        } else if (((value - 16777216) & (-2147483648L)) != 0) {
                            setValue(false, this.mem, i6, (int) (value - j2));
                        }
                        i6 += 4;
                    }
                    i5 = i6;
                }
                return;
            case 3:
                int[] iArr2 = this.R;
                int i7 = iArr2[4];
                long j3 = iArr2[6] & (-1);
                if (i7 >= 245760) {
                    return;
                }
                byte[] bArr2 = {4, 4, 6, 6, 0, 0, 7, 7, 4, 4, 0, 0, 4, 4, 0, 0};
                long j4 = j3 >>> 4;
                int i8 = 0;
                while (i8 < i7 - 21) {
                    int i9 = (this.mem[i8] & Ascii.US) - 16;
                    if (i9 >= 0 && (b = bArr2[i9]) != 0) {
                        for (int i10 = 0; i10 <= 2; i10++) {
                            if (((1 << i10) & b) != 0) {
                                int i11 = (i10 * 41) + 5;
                                if (filterItanium_GetBits(i8, i11 + 37, 4) == 5) {
                                    filterItanium_SetBits(i8, 1048575 & ((int) (filterItanium_GetBits(i8, i, 20) - j4)), i11 + 13, 20);
                                }
                            }
                        }
                    }
                    i8 += 16;
                    j4++;
                }
                return;
            case 4:
                int[] iArr3 = this.R;
                int i12 = iArr3[4] & (-1);
                int i13 = iArr3[0] & (-1);
                int i14 = (i12 * 2) & (-1);
                setValue(false, this.mem, 245792, i12);
                if (i12 >= 122880) {
                    return;
                }
                int i15 = 0;
                for (int i16 = 0; i16 < i13; i16++) {
                    int i17 = i12 + i16;
                    byte b4 = 0;
                    while (i17 < i14) {
                        byte[] bArr3 = this.mem;
                        b4 = (byte) (b4 - bArr3[i15]);
                        bArr3[i17] = b4;
                        i17 += i13;
                        i15++;
                    }
                }
                return;
            case 5:
                int[] iArr4 = this.R;
                int i18 = iArr4[4];
                int i19 = 3;
                int i20 = iArr4[0] - 3;
                int i21 = iArr4[1];
                setValue(false, this.mem, 245792, i18);
                if (i18 >= 122880 || i21 < 0) {
                    return;
                }
                int i22 = 0;
                int i23 = 0;
                while (i22 < i19) {
                    int i24 = i22;
                    long j5 = 0;
                    while (i24 < i18) {
                        int i25 = i24 - i20;
                        if (i25 >= i19) {
                            int i26 = i25 + i18;
                            byte[] bArr4 = this.mem;
                            long j6 = bArr4[i26] & 255;
                            long j7 = bArr4[i26 - i19] & 255;
                            long j8 = (j5 + j6) - j7;
                            i2 = i22;
                            i3 = i20;
                            int abs = Math.abs((int) (j8 - j5));
                            long j9 = j5;
                            int abs2 = Math.abs((int) (j8 - j6));
                            int abs3 = Math.abs((int) (j8 - j7));
                            j5 = (abs > abs2 || abs > abs3) ? abs2 <= abs3 ? j6 : j7 : j9;
                        } else {
                            i2 = i22;
                            i3 = i20;
                        }
                        j5 = (j5 - bArr[i23]) & 255 & 255;
                        this.mem[i18 + i24] = (byte) (j5 & 255);
                        i24 += 3;
                        i23++;
                        i22 = i2;
                        i19 = 3;
                        i20 = i3;
                    }
                    i22++;
                    i19 = 3;
                }
                int i27 = i18 - 2;
                while (i21 < i27) {
                    byte[] bArr5 = this.mem;
                    int i28 = i18 + i21;
                    byte b5 = bArr5[i28 + 1];
                    bArr5[i28] = (byte) (bArr5[i28] + b5);
                    int i29 = i28 + 2;
                    bArr5[i29] = (byte) (bArr5[i29] + b5);
                    i21 += 3;
                }
                return;
            case 6:
                int[] iArr5 = this.R;
                int i30 = iArr5[4];
                int i31 = iArr5[0];
                setValue(false, this.mem, 245792, i30);
                if (i30 >= 122880) {
                    return;
                }
                int i32 = 0;
                int i33 = 0;
                while (i32 < i31) {
                    long[] jArr = new long[7];
                    int i34 = i32;
                    long j10 = 0;
                    int i35 = 0;
                    int i36 = 0;
                    int i37 = 0;
                    int i38 = 0;
                    long j11 = 0;
                    int i39 = 0;
                    int i40 = 0;
                    while (i34 < i30) {
                        int i41 = (int) j10;
                        int i42 = i41 - i39;
                        byte[] bArr6 = this.mem;
                        int i43 = i33 + 1;
                        int i44 = i34;
                        long j12 = bArr6[i33] & 255;
                        long j13 = (((((((8 * j11) + (i36 * i41)) + (i35 * i42)) + (i37 * i38)) >>> c) & 255) - j12) & (-1);
                        int i45 = i30;
                        bArr6[i30 + i44] = (byte) j13;
                        long j14 = (byte) (j13 - j11);
                        int i46 = ((byte) j12) << 3;
                        jArr[0] = jArr[0] + Math.abs(i46);
                        jArr[1] = jArr[1] + Math.abs(i46 - i41);
                        jArr[2] = jArr[2] + Math.abs(i46 + i41);
                        jArr[3] = jArr[3] + Math.abs(i46 - i42);
                        jArr[4] = jArr[4] + Math.abs(i46 + i42);
                        jArr[5] = jArr[5] + Math.abs(i46 - i38);
                        jArr[6] = jArr[6] + Math.abs(i46 + i38);
                        if ((i40 & 31) == 0) {
                            long j15 = jArr[0];
                            long j16 = 0;
                            jArr[0] = 0;
                            long j17 = 0;
                            int i47 = 1;
                            while (i47 < 7) {
                                if (jArr[i47] < j15) {
                                    j15 = jArr[i47];
                                    j17 = i47;
                                }
                                jArr[i47] = j16;
                                i47++;
                                j16 = 0;
                            }
                            switch ((int) j17) {
                                case 1:
                                    if (i36 >= -16) {
                                        i36--;
                                        break;
                                    } else {
                                        break;
                                    }
                                case 2:
                                    if (i36 < 16) {
                                        i36++;
                                        break;
                                    } else {
                                        break;
                                    }
                                case 3:
                                    if (i35 >= -16) {
                                        i35--;
                                        break;
                                    } else {
                                        break;
                                    }
                                case 4:
                                    if (i35 < 16) {
                                        i35++;
                                        break;
                                    } else {
                                        break;
                                    }
                                case 5:
                                    if (i37 >= -16) {
                                        i37--;
                                        break;
                                    } else {
                                        break;
                                    }
                                case 6:
                                    if (i37 < 16) {
                                        i37++;
                                        break;
                                    } else {
                                        break;
                                    }
                            }
                        }
                        i34 = i44 + i31;
                        i40++;
                        i38 = i42;
                        i39 = i41;
                        j10 = j14;
                        i33 = i43;
                        i30 = i45;
                        j11 = j13;
                        c = 3;
                    }
                    i32++;
                    c = 3;
                }
                return;
            case 7:
                int i48 = this.R[4];
                if (i48 >= 122880) {
                    return;
                }
                int i49 = i48;
                int i50 = 0;
                while (i50 < i48) {
                    byte[] bArr7 = this.mem;
                    int i51 = i50 + 1;
                    byte b6 = bArr7[i50];
                    if (b6 == 2) {
                        int i52 = i51 + 1;
                        byte b7 = bArr7[i51];
                        if (b7 != 2) {
                            b7 = (byte) (b7 - 32);
                        }
                        i51 = i52;
                        b6 = b7;
                    }
                    this.mem[i49] = b6;
                    i50 = i51;
                    i49++;
                }
                setValue(false, this.mem, 245788, i49 - i48);
                setValue(false, this.mem, 245792, i48);
                return;
            default:
                return;
        }
    }

    private void filterItanium_SetBits(int i, int i2, int i3, int i4) {
        int i5 = i3 / 8;
        int i6 = i3 & 7;
        int i7 = (((-1) >>> (32 - i4)) << i6) ^ (-1);
        int i8 = i2 << i6;
        for (int i9 = 0; i9 < 4; i9++) {
            byte[] bArr = this.mem;
            int i10 = i + i5 + i9;
            bArr[i10] = (byte) (bArr[i10] & i7);
            bArr[i10] = (byte) (bArr[i10] | i8);
            i7 = (i7 >>> 8) | ViewCompat.MEASURED_STATE_MASK;
            i8 >>>= 8;
        }
    }

    private int filterItanium_GetBits(int i, int i2, int i3) {
        int i4 = i2 / 8;
        byte[] bArr = this.mem;
        int i5 = i4 + 1;
        int i6 = i5 + 1;
        int i7 = (bArr[i4 + i] & 255) | ((bArr[i5 + i] & 255) << 8);
        return ((((bArr[i + (i6 + 1)] & 255) << 24) | (i7 | ((bArr[i6 + i] & 255) << 16))) >>> (i2 & 7)) & ((-1) >>> (32 - i3));
    }

    public void setMemory(int i, byte[] bArr, int i2, int i3) {
        if (i < 262144) {
            for (int i4 = 0; i4 < Math.min(bArr.length - i2, i3) && 262144 - i >= i4; i4++) {
                this.mem[i + i4] = bArr[i2 + i4];
            }
        }
    }
}
