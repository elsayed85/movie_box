package com.movieboxpro.android.utils.unrar.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
/* loaded from: classes3.dex */
public class ReadOnlyAccessFile extends RandomAccessFile implements IReadOnlyAccess {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public ReadOnlyAccessFile(File file) throws FileNotFoundException {
        super(file, "r");
    }

    @Override // com.movieboxpro.android.utils.unrar.io.IReadOnlyAccess
    public int readFully(byte[] bArr, int i) throws IOException {
        readFully(bArr, 0, i);
        return i;
    }

    @Override // com.movieboxpro.android.utils.unrar.io.IReadOnlyAccess
    public long getPosition() throws IOException {
        return getFilePointer();
    }

    @Override // com.movieboxpro.android.utils.unrar.io.IReadOnlyAccess
    public void setPosition(long j) throws IOException {
        seek(j);
    }
}
