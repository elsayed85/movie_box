package com.movieboxpro.android.model.movie;

import com.ares.downloader.jarvis.Jarvis;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes3.dex */
public class MovelList implements Serializable {
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_ITEM1 = 1;
    public static final int TYPE_ITEM2 = 2;
    public static final int TYPE_ITEM3 = 3;
    public static final int TYPE_ITEM4 = 4;
    public static final int TYPE_ITEM5 = 5;
    public static final int TYPE_ITEM6 = 6;
    public static final int TYPE_ITEM7 = 7;
    public List<MovieFrist> dataList;
    public String state;
    public String title;
    public int viewType;

    public MovelList() {
        this.viewType = 0;
    }

    public MovelList(int i) {
        this.viewType = 0;
        this.viewType = i;
    }

    /* loaded from: classes3.dex */
    public static class MovieFrist extends MovieDetail {
        public MovieFrist() {
        }

        public MovieFrist(int i) {
            this.box_type = i;
        }
    }

    /* loaded from: classes3.dex */
    public static class MovieDownload extends MovieDetail {
        public int DownloadProcess = 0;
        public Jarvis.Downloader downloader;
        public String fileLength;
        public String fileName;
        public String paths;
        public long size;
        public long speed;
        public int statue;

        public MovieDownload() {
        }

        public MovieDownload(int i) {
            this.box_type = i;
        }
    }
}
