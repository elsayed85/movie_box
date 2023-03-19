package com.movieboxpro.android.model.common;

import com.movieboxpro.android.adapter.FeaturedAdapter;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes3.dex */
public class Homelist {
    public static final int FEATURED_ITEM_ACTOR = 5;
    public static final int FEATURED_ITEM_AD = 8;
    public static final int FEATURED_ITEM_CONTINUE = 2;
    public static final int FEATURED_ITEM_GENRE = 6;
    public static final int FEATURED_ITEM_LAND_TV = 1;
    public static final int FEATURED_ITEM_LIKE = 9;
    public static final int FEATURED_ITEM_MOVIE = 3;
    public static final int FEATURED_ITEM_PLAY_LIST = 7;
    public static final int FEATURED_ITEM_TV = 4;
    public String adImgUrl;
    public int box_type;
    public int code;
    private FeaturedAdapter.FeaturedListAdapter featuredAdapter;
    public int ismore;
    public String name;
    public String type;
    public int viewType = 0;
    public List<Typelist> list = new ArrayList();

    public FeaturedAdapter.FeaturedListAdapter getFeaturedAdapter() {
        return this.featuredAdapter;
    }

    public void setFeaturedAdapter(FeaturedAdapter.FeaturedListAdapter featuredListAdapter) {
        this.featuredAdapter = featuredListAdapter;
    }

    public String getAdImgUrl() {
        return this.adImgUrl;
    }

    public void setAdImgUrl(String str) {
        this.adImgUrl = str;
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

    public int getBox_type() {
        return this.box_type;
    }

    public void setBox_type(int i) {
        this.box_type = i;
    }

    public int getViewType() {
        return this.viewType;
    }

    public void setViewType(int i) {
        this.viewType = i;
    }

    public List<Typelist> getList() {
        return this.list;
    }

    public void setList(List<Typelist> list) {
        this.list = list;
    }

    /* loaded from: classes3.dex */
    public static class History {
        private int episode;
        private int over;
        private int runtime;
        private int season;
        private int seconds;

        public int getOver() {
            return this.over;
        }

        public void setOver(int i) {
            this.over = i;
        }

        public int getRuntime() {
            return this.runtime;
        }

        public void setRuntime(int i) {
            this.runtime = i;
        }

        public int getEpisode() {
            return this.episode;
        }

        public void setEpisode(int i) {
            this.episode = i;
        }

        public int getSeason() {
            return this.season;
        }

        public void setSeason(int i) {
            this.season = i;
        }

        public int getSeconds() {
            return this.seconds;
        }

        public void setSeconds(int i) {
            this.seconds = i;
        }
    }

    /* loaded from: classes3.dex */
    public static class Typelist {
        private String actor_id;
        private String avatar;
        public String banner_mini;
        public int box_type;
        private String content_rating;
        private String count;
        private String featuredType;
        private History history;
        public String id;
        private String imdb_rating;
        private String img;
        private List<String> imgArr;
        public int itemType;
        private String job;
        private String lid;
        private String name;
        public String poster;
        public String poster_2;
        public String poster_min;
        public String quality_tag_new;
        private int runtime;
        public String season_episode;
        private int seconds;
        private String tid;
        public String title;
        private int tomato_meter;
        private int type;
        public String update_title;
        private String url;
        private String username;
        public int viewType = 0;

        public int getTomato_meter() {
            return this.tomato_meter;
        }

        public void setTomato_meter(int i) {
            this.tomato_meter = i;
        }

        public String getPoster_min() {
            return this.poster_min;
        }

        public void setPoster_min(String str) {
            this.poster_min = str;
        }

        public String getTid() {
            return this.tid;
        }

        public void setTid(String str) {
            this.tid = str;
        }

        public String getContent_rating() {
            return this.content_rating;
        }

        public void setContent_rating(String str) {
            this.content_rating = str;
        }

        public String getFeaturedType() {
            return this.featuredType;
        }

        public void setFeaturedType(String str) {
            this.featuredType = str;
        }

        public void setItemType(int i) {
            this.itemType = i;
        }

        public String getImdb_rating() {
            return this.imdb_rating;
        }

        public void setImdb_rating(String str) {
            this.imdb_rating = str;
        }

        public int getRuntime() {
            return this.runtime;
        }

        public void setRuntime(int i) {
            this.runtime = i;
        }

        public int getSeconds() {
            return this.seconds;
        }

        public void setSeconds(int i) {
            this.seconds = i;
        }

        public int getType() {
            return this.type;
        }

        public void setType(int i) {
            this.type = i;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        public History getHistory() {
            return this.history;
        }

        public void setHistory(History history) {
            this.history = history;
        }

        public String getImg() {
            return this.img;
        }

        public void setImg(String str) {
            this.img = str;
        }

        public String getUsername() {
            return this.username;
        }

        public void setUsername(String str) {
            this.username = str;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
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

        public String getLid() {
            return this.lid;
        }

        public void setLid(String str) {
            this.lid = str;
        }

        public String getCount() {
            return this.count;
        }

        public void setCount(String str) {
            this.count = str;
        }

        public List<String> getImgArr() {
            return this.imgArr;
        }

        public void setImgArr(List<String> list) {
            this.imgArr = list;
        }

        public String getId() {
            return this.id;
        }

        public void setId(String str) {
            this.id = str;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public String getPoster() {
            return this.poster;
        }

        public void setPoster(String str) {
            this.poster = str;
        }

        public String getBanner_mini() {
            return this.banner_mini;
        }

        public void setBanner_mini(String str) {
            this.banner_mini = str;
        }

        public String getSeason_episode() {
            return this.season_episode;
        }

        public void setSeason_episode(String str) {
            this.season_episode = str;
        }

        public String getUpdate_title() {
            return this.update_title;
        }

        public void setUpdate_title(String str) {
            this.update_title = str;
        }

        public String getQuality_tag_new() {
            return this.quality_tag_new;
        }

        public void setQuality_tag_new(String str) {
            this.quality_tag_new = str;
        }

        public int getBox_type() {
            return this.box_type;
        }

        public void setBox_type(int i) {
            this.box_type = i;
        }

        public String getPoster_2() {
            return this.poster_2;
        }

        public void setPoster_2(String str) {
            this.poster_2 = str;
        }

        public int getViewType() {
            return this.viewType;
        }

        public void setViewType(int i) {
            this.viewType = i;
        }

        public int getItemType() {
            return this.itemType;
        }
    }
}
