package com.movieboxpro.android.model.movie;

import java.io.Serializable;
import java.util.List;
/* loaded from: classes3.dex */
public class MovieListModel {
    private int code;
    private boolean init;
    private int ismore;
    private List<MovieListItem> list;
    private String name;
    private String type;
    private int viewType;

    public boolean isInit() {
        return this.init;
    }

    public void setInit(boolean z) {
        this.init = z;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getIsmore() {
        return this.ismore;
    }

    public void setIsmore(int i) {
        this.ismore = i;
    }

    public List<MovieListItem> getList() {
        return this.list;
    }

    public void setList(List<MovieListItem> list) {
        this.list = list;
    }

    public int getViewType() {
        return this.viewType;
    }

    public void setViewType(int i) {
        this.viewType = i;
    }

    /* loaded from: classes3.dex */
    public static class DoubleMovie {
        private MovieListItem first;
        private MovieListItem second;

        public MovieListItem getFirst() {
            return this.first;
        }

        public void setFirst(MovieListItem movieListItem) {
            this.first = movieListItem;
        }

        public MovieListItem getSecond() {
            return this.second;
        }

        public void setSecond(MovieListItem movieListItem) {
            this.second = movieListItem;
        }
    }

    /* loaded from: classes3.dex */
    public static class VideoItem implements Serializable {
        private String title;

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }
    }

    /* loaded from: classes3.dex */
    public static class MovieListItem implements Serializable {
        public static final int ACTOR_LIST = 3;
        public static final int COLLECTION = 6;
        public static final int COMPILATIONS = 2;
        public static final int DAY_HOT_LIST = 5;
        public static final int LATEST_LIST = 8;
        public static final int PLAY_LIST = 1;
        public static final int SPECIAL = 4;
        public static final int WEEK_HOT_LIST = 7;
        private String actor_id;
        private String avatar;
        private String collect_num;
        private int count;
        private String cover;
        private DoubleMovie doubleMovie;
        private String id;
        private List<String> imgArr;
        private int itemType;
        private String job;
        private String lid;
        private boolean more;
        private List<String> movieArr;
        private String name;
        private String rank;
        private int status;
        private String username;
        private List<VideoItem> videoArr;

        public boolean isMore() {
            return this.more;
        }

        public void setMore(boolean z) {
            this.more = z;
        }

        public DoubleMovie getDoubleMovie() {
            return this.doubleMovie;
        }

        public void setDoubleMovie(DoubleMovie doubleMovie) {
            this.doubleMovie = doubleMovie;
        }

        public List<VideoItem> getVideoArr() {
            return this.videoArr;
        }

        public void setVideoArr(List<VideoItem> list) {
            this.videoArr = list;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int i) {
            this.status = i;
        }

        public String getRank() {
            return this.rank;
        }

        public void setRank(String str) {
            this.rank = str;
        }

        public String getCollect_num() {
            return this.collect_num;
        }

        public void setCollect_num(String str) {
            this.collect_num = str;
        }

        public List<String> getMovieArr() {
            return this.movieArr;
        }

        public void setMovieArr(List<String> list) {
            this.movieArr = list;
        }

        public String getId() {
            return this.id;
        }

        public void setId(String str) {
            this.id = str;
        }

        public int getItemType() {
            return this.itemType;
        }

        public void setItemType(int i) {
            this.itemType = i;
        }

        public String getJob() {
            return this.job;
        }

        public void setJob(String str) {
            this.job = str;
        }

        public String getAvatar() {
            return this.avatar;
        }

        public void setAvatar(String str) {
            this.avatar = str;
        }

        public String getActor_id() {
            return this.actor_id;
        }

        public void setActor_id(String str) {
            this.actor_id = str;
        }

        public String getCover() {
            return this.cover;
        }

        public void setCover(String str) {
            this.cover = str;
        }

        public String getLid() {
            return this.lid;
        }

        public void setLid(String str) {
            this.lid = str;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getUsername() {
            return this.username;
        }

        public void setUsername(String str) {
            this.username = str;
        }

        public int getCount() {
            return this.count;
        }

        public void setCount(int i) {
            this.count = i;
        }

        public List<String> getImgArr() {
            return this.imgArr;
        }

        public void setImgArr(List<String> list) {
            this.imgArr = list;
        }
    }
}
