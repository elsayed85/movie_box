package com.movieboxpro.android.model;

import java.io.File;
/* loaded from: classes3.dex */
public class DownloadLocation {
    private File file;
    private long freeSize;
    private String name;
    private boolean select;
    private long totalSize;

    public boolean isSelect() {
        return this.select;
    }

    public void setSelect(boolean z) {
        this.select = z;
    }

    public long getTotalSize() {
        return this.totalSize;
    }

    public void setTotalSize(long j) {
        this.totalSize = j;
    }

    public long getFreeSize() {
        return this.freeSize;
    }

    public void setFreeSize(long j) {
        this.freeSize = j;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public File getFile() {
        return this.file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
