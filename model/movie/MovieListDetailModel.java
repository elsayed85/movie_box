package com.movieboxpro.android.model.movie;

import com.google.gson.annotations.SerializedName;
import com.movieboxpro.android.model.user.UserModel;
import java.util.List;
/* loaded from: classes3.dex */
public class MovieListDetailModel {
    private String avatar;
    private UserModel.BBsInfo bbs_bind;
    private int collect_num;
    private int count;
    private long dateline;
    private String desc;
    private String email;
    private List<String> imgArr;
    private int is_collect;
    private int ismine;
    private String lid;
    private List<ListBean> list;
    private String name;
    private String rank;
    private int status;
    private String uid;
    private String username;

    public UserModel.BBsInfo getBbs_bind() {
        return this.bbs_bind;
    }

    public void setBbs_bind(UserModel.BBsInfo bBsInfo) {
        this.bbs_bind = bBsInfo;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public List<String> getImgArr() {
        return this.imgArr;
    }

    public void setImgArr(List<String> list) {
        this.imgArr = list;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public String getRank() {
        return this.rank;
    }

    public void setRank(String str) {
        this.rank = str;
    }

    public String getLid() {
        return this.lid;
    }

    public void setLid(String str) {
        this.lid = str;
    }

    public int getIsmine() {
        return this.ismine;
    }

    public void setIsmine(int i) {
        this.ismine = i;
    }

    public int getIs_collect() {
        return this.is_collect;
    }

    public void setIs_collect(int i) {
        this.is_collect = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int i) {
        this.count = i;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public int getCollect_num() {
        return this.collect_num;
    }

    public void setCollect_num(int i) {
        this.collect_num = i;
    }

    public long getDateline() {
        return this.dateline;
    }

    public void setDateline(long j) {
        this.dateline = j;
    }

    public List<ListBean> getList() {
        return this.list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    /* loaded from: classes3.dex */
    public static class ListBean {
        private int box_type;
        private String cats;
        private String description;
        private int id;
        private String imdb_rating;
        @SerializedName("3d")
        private int is3d;
        private String mark;
        private String poster;
        private String quality_tag_new;
        private int runtime;
        private String title;
        private String year;

        public String getCats() {
            return this.cats;
        }

        public void setCats(String str) {
            this.cats = str;
        }

        public int getBox_type() {
            return this.box_type;
        }

        public void setBox_type(int i) {
            this.box_type = i;
        }

        public int getId() {
            return this.id;
        }

        public void setId(int i) {
            this.id = i;
        }

        public String getMark() {
            return this.mark;
        }

        public void setMark(String str) {
            this.mark = str;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public String getYear() {
            return this.year;
        }

        public void setYear(String str) {
            this.year = str;
        }

        public int getRuntime() {
            return this.runtime;
        }

        public void setRuntime(int i) {
            this.runtime = i;
        }

        public String getImdb_rating() {
            return this.imdb_rating;
        }

        public void setImdb_rating(String str) {
            this.imdb_rating = str;
        }

        public String getPoster() {
            return this.poster;
        }

        public void setPoster(String str) {
            this.poster = str;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String str) {
            this.description = str;
        }

        public int getIs3d() {
            return this.is3d;
        }

        public void setIs3d(int i) {
            this.is3d = i;
        }

        public String getQuality_tag_new() {
            return this.quality_tag_new;
        }

        public void setQuality_tag_new(String str) {
            this.quality_tag_new = str;
        }
    }
}
