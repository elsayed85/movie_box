package com.movieboxpro.android.model;
/* loaded from: classes3.dex */
public class DownloadInfo {
    public static final String DOWNLOAD = "download";
    public static final String DOWNLOAD_CANCEL = "cancel";
    public static final String DOWNLOAD_ERROR = "error";
    public static final String DOWNLOAD_OVER = "over";
    public static final String DOWNLOAD_PAUSE = "pause";
    public static final long TOTAL_ERROR = -1;
    private String downloadStatus;
    private String fileName;
    private long progress;
    private long total;
    private String url;

    public DownloadInfo(String str) {
        this.url = str;
    }

    public String getUrl() {
        return this.url;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public long getTotal() {
        return this.total;
    }

    public void setTotal(long j) {
        this.total = j;
    }

    public long getProgress() {
        return this.progress;
    }

    public void setProgress(long j) {
        this.progress = j;
    }

    public String getDownloadStatus() {
        return this.downloadStatus;
    }

    public void setDownloadStatus(String str) {
        this.downloadStatus = str;
    }
}
