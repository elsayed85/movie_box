package com.movieboxpro.android.utils.unrar.io;

import java.io.EOFException;
import java.io.IOException;
/* loaded from: classes3.dex */
public class ReadOnlyAccessByteArray implements IReadOnlyAccess {
    private byte[] file;
    private int positionInFile;

    @Override // com.movieboxpro.android.utils.unrar.io.IReadOnlyAccess
    public void close() throws IOException {
    }

    public ReadOnlyAccessByteArray(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException("file must not be null!!");
        }
        this.file = bArr;
        this.positionInFile = 0;
    }

    @Override // com.movieboxpro.android.utils.unrar.io.IReadOnlyAccess
    public long getPosition() throws IOException {
        return this.positionInFile;
    }

    @Override // com.movieboxpro.android.utils.unrar.io.IReadOnlyAccess
    public void setPosition(long j) throws IOException {
        if (j < this.file.length && j >= 0) {
            this.positionInFile = (int) j;
            return;
        }
        throw new EOFException();
    }

    @Override // com.movieboxpro.android.utils.unrar.io.IReadOnlyAccess
    public int read() throws IOException {
        byte[] bArr = this.file;
        int i = this.positionInFile;
        this.positionInFile = i + 1;
        return bArr[i];
    }

    @Override // com.movieboxpro.android.utils.unrar.io.IReadOnlyAccess
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int min = Math.min(i2, this.file.length - this.positionInFile);
        System.arraycopy(this.file, this.positionInFile, bArr, i, min);
        this.positionInFile += min;
        return min;
    }

    @Override // com.movieboxpro.android.utils.unrar.io.IReadOnlyAccess
    public int readFully(byte[] bArr, int i) throws IOException {
        if (bArr != null) {
            if (i == 0) {
                throw new IllegalArgumentException("cannot read 0 bytes ;-)");
            }
            int min = Math.min(i, (this.file.length - this.positionInFile) - 1);
            System.arraycopy(this.file, this.positionInFile, bArr, 0, min);
            this.positionInFile += min;
            return min;
        }
        throw new NullPointerException("buffer must not be null");
    }
}
