package com.movieboxpro.android.event;

import com.movieboxpro.android.model.movie.MovelList;
/* loaded from: classes3.dex */
public class DownLoadEvent {
    public MovelList.MovieDownload download;
    public String id;
    public boolean isPaused;

    public DownLoadEvent(String str, boolean z, MovelList.MovieDownload movieDownload) {
        this.id = str;
        this.isPaused = z;
        this.download = movieDownload;
    }

    public String toString() {
        return this.id + this.isPaused;
    }
}
