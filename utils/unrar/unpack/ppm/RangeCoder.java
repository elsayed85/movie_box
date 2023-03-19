package com.movieboxpro.android.utils.unrar.unpack.ppm;

import com.movieboxpro.android.utils.unrar.exception.RarException;
import com.movieboxpro.android.utils.unrar.unpack.Unpack;
import java.io.IOException;
/* loaded from: classes3.dex */
public class RangeCoder {
    public static final int BOT = 32768;
    public static final int TOP = 16777216;
    private static final long uintMask = 4294967295L;
    private long code;
    private long low;
    private long range;
    private final SubRange subRange = new SubRange();
    private Unpack unpackRead;

    public SubRange getSubRange() {
        return this.subRange;
    }

    public void initDecoder(Unpack unpack) throws IOException, RarException {
        this.unpackRead = unpack;
        this.code = 0L;
        this.low = 0L;
        this.range = uintMask;
        for (int i = 0; i < 4; i++) {
            this.code = ((this.code << 8) | getChar()) & uintMask;
        }
    }

    public int getCurrentCount() {
        long scale = (this.range / this.subRange.getScale()) & uintMask;
        this.range = scale;
        return (int) ((this.code - this.low) / scale);
    }

    public long getCurrentShiftCount(int i) {
        long j = this.range >>> i;
        this.range = j;
        return uintMask & ((this.code - this.low) / j);
    }

    public void decode() {
        this.low = (this.low + (this.range * this.subRange.getLowCount())) & uintMask;
        this.range = (this.range * (this.subRange.getHighCount() - this.subRange.getLowCount())) & uintMask;
    }

    private int getChar() throws IOException, RarException {
        return this.unpackRead.getChar();
    }

    public void ariDecNormalize() throws IOException, RarException {
        boolean z = false;
        while (true) {
            long j = this.low;
            long j2 = this.range;
            if ((j ^ (j + j2)) >= 16777216) {
                z = j2 < 32768;
                if (!z) {
                    return;
                }
            }
            if (z) {
                this.range = (-this.low) & 32767 & uintMask;
                z = false;
            }
            this.code = ((this.code << 8) | getChar()) & uintMask;
            this.range = (this.range << 8) & uintMask;
            this.low = uintMask & (this.low << 8);
        }
    }

    public String toString() {
        return "RangeCoder[\n  low=" + this.low + "\n  code=" + this.code + "\n  range=" + this.range + "\n  subrange=" + this.subRange + "]";
    }

    /* loaded from: classes3.dex */
    public static class SubRange {
        private long highCount;
        private long lowCount;
        private long scale;

        public long getHighCount() {
            return this.highCount;
        }

        public void setHighCount(long j) {
            this.highCount = j & RangeCoder.uintMask;
        }

        public long getLowCount() {
            return this.lowCount & RangeCoder.uintMask;
        }

        public void setLowCount(long j) {
            this.lowCount = j & RangeCoder.uintMask;
        }

        public long getScale() {
            return this.scale;
        }

        public void setScale(long j) {
            this.scale = j & RangeCoder.uintMask;
        }

        public void incScale(int i) {
            setScale(getScale() + i);
        }

        public String toString() {
            return "SubRange[\n  lowCount=" + this.lowCount + "\n  highCount=" + this.highCount + "\n  scale=" + this.scale + "]";
        }
    }
}
