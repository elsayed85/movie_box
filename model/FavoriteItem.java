package com.movieboxpro.android.model;

import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes3.dex */
public class FavoriteItem extends BaseExpandNode {
    private List<FavoriteEpisodeItem> allEpisodes;
    private String banner_mini;
    private int box_type;
    private String cats;
    private int dislike_total;
    private int episode_count;
    private List<FavoriteSeasonItem> episode_progress_list;
    private ArrayList<Integer> episodes;
    private FavoriteTitle favoriteTitle;
    private String id;
    private String imdb_rating;
    private FavoriteEpisodeItem lastStartWatchedItem;
    private int lastWatchedIndex;
    private FavoriteEpisodeItem lastWatchedItem;
    private LastEpisode last_episode;
    private long last_play_time;
    private int like_status;
    private int like_total;
    private long mark_time;
    private int over_episode_count;
    private String poster;
    private String quality_tag_new;
    private String released;
    private int runtime;
    private int runtime_seconds;
    private String season_episode;
    private List<BaseNode> seasons;
    private int seconds;
    private boolean showProgress;
    private String title;
    private String tomato_meter;
    private int totalEpisode;
    private int updateCount;
    private String update_title;
    private int waiting;
    private boolean watched;
    private WatchingItem watchingItem;
    private String year;
    private int watchedEpisodeCount = 0;
    private int lastWatchedCount = 0;

    public WatchingItem getWatchingItem() {
        return this.watchingItem;
    }

    public void setWatchingItem(WatchingItem watchingItem) {
        this.watchingItem = watchingItem;
    }

    public int getLastWatchedIndex() {
        return this.lastWatchedIndex;
    }

    public void setLastWatchedIndex(int i) {
        this.lastWatchedIndex = i;
    }

    public FavoriteEpisodeItem getLastStartWatchedItem() {
        return this.lastStartWatchedItem;
    }

    public void setLastStartWatchedItem(FavoriteEpisodeItem favoriteEpisodeItem) {
        this.lastStartWatchedItem = favoriteEpisodeItem;
    }

    public FavoriteEpisodeItem getLastWatchedItem() {
        return this.lastWatchedItem;
    }

    public void setLastWatchedItem(FavoriteEpisodeItem favoriteEpisodeItem) {
        this.lastWatchedItem = favoriteEpisodeItem;
    }

    public int getLastWatchedCount() {
        return this.lastWatchedCount;
    }

    public void setLastWatchedCount(int i) {
        this.lastWatchedCount = i;
    }

    public List<FavoriteEpisodeItem> getAllEpisodes() {
        return this.allEpisodes;
    }

    public void setAllEpisodes(List<FavoriteEpisodeItem> list) {
        this.allEpisodes = list;
    }

    public boolean isShowProgress() {
        return this.showProgress;
    }

    public void setShowProgress(boolean z) {
        this.showProgress = z;
    }

    public FavoriteTitle getFavoriteTitle() {
        return this.favoriteTitle;
    }

    public void setFavoriteTitle(FavoriteTitle favoriteTitle) {
        this.favoriteTitle = favoriteTitle;
    }

    public ArrayList<Integer> getEpisodes() {
        return this.episodes;
    }

    public void setEpisodes(ArrayList<Integer> arrayList) {
        this.episodes = arrayList;
    }

    public int getTotalEpisode() {
        return this.totalEpisode;
    }

    public void setTotalEpisode(int i) {
        this.totalEpisode = i;
    }

    public boolean isWatched() {
        return this.watched;
    }

    public void setWatched(boolean z) {
        this.watched = z;
    }

    public long getMark_time() {
        return this.mark_time;
    }

    public void setMark_time(long j) {
        this.mark_time = j;
    }

    public int getWaiting() {
        return this.waiting;
    }

    public void setWaiting(int i) {
        this.waiting = i;
    }

    public String getUpdate_title() {
        return this.update_title;
    }

    public void setUpdate_title(String str) {
        this.update_title = str;
    }

    public int getWatchedEpisodeCount() {
        return this.watchedEpisodeCount;
    }

    public void setWatchedEpisodeCount(int i) {
        this.watchedEpisodeCount = i;
    }

    public List<FavoriteSeasonItem> getEpisode_progress_list() {
        return this.episode_progress_list;
    }

    public void setEpisode_progress_list(List<FavoriteSeasonItem> list) {
        this.episode_progress_list = list;
    }

    public String getBanner_mini() {
        return this.banner_mini;
    }

    public void setBanner_mini(String str) {
        this.banner_mini = str;
    }

    public int getEpisode_count() {
        return this.episode_count;
    }

    public void setEpisode_count(int i) {
        this.episode_count = i;
    }

    public int getOver_episode_count() {
        return this.over_episode_count;
    }

    public void setOver_episode_count(int i) {
        this.over_episode_count = i;
    }

    public String getSeason_episode() {
        return this.season_episode;
    }

    public void setSeason_episode(String str) {
        this.season_episode = str;
    }

    public LastEpisode getLast_episode() {
        return this.last_episode;
    }

    public void setLast_episode(LastEpisode lastEpisode) {
        this.last_episode = lastEpisode;
    }

    public String getTomato_meter() {
        return this.tomato_meter;
    }

    public void setTomato_meter(String str) {
        this.tomato_meter = str;
    }

    public String getReleased() {
        return this.released;
    }

    public void setReleased(String str) {
        this.released = str;
    }

    public String getCats() {
        return this.cats;
    }

    public void setCats(String str) {
        this.cats = str;
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

    public int getLike_status() {
        return this.like_status;
    }

    public void setLike_status(int i) {
        this.like_status = i;
    }

    public int getLike_total() {
        return this.like_total;
    }

    public void setLike_total(int i) {
        this.like_total = i;
    }

    public int getDislike_total() {
        return this.dislike_total;
    }

    public void setDislike_total(int i) {
        this.dislike_total = i;
    }

    public int getBox_type() {
        return this.box_type;
    }

    public void setBox_type(int i) {
        this.box_type = i;
    }

    public String getImdb_rating() {
        return this.imdb_rating;
    }

    public void setImdb_rating(String str) {
        this.imdb_rating = str;
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(String str) {
        this.year = str;
    }

    public String getQuality_tag_new() {
        return this.quality_tag_new;
    }

    public void setQuality_tag_new(String str) {
        this.quality_tag_new = str;
    }

    public int getRuntime() {
        return this.runtime;
    }

    public void setRuntime(int i) {
        this.runtime = i;
    }

    public int getUpdateCount() {
        return this.updateCount;
    }

    public void setUpdateCount(int i) {
        this.updateCount = i;
    }

    public int getRuntime_seconds() {
        return this.runtime_seconds;
    }

    public void setRuntime_seconds(int i) {
        this.runtime_seconds = i;
    }

    public int getSeconds() {
        return this.seconds;
    }

    public void setSeconds(int i) {
        this.seconds = i;
    }

    public long getLast_play_time() {
        return this.last_play_time;
    }

    public void setLast_play_time(long j) {
        this.last_play_time = j;
    }

    @Override // com.chad.library.adapter.base.entity.node.BaseNode
    public List<BaseNode> getChildNode() {
        return this.seasons;
    }

    public List<BaseNode> getSeasons() {
        return this.seasons;
    }

    public void setSeasons(List<BaseNode> list) {
        this.seasons = list;
    }

    /* loaded from: classes3.dex */
    public static class LastEpisode {
        private int dislike_total;
        private int episode;
        private String imdb_rating;
        private long last_play_time;
        private int like_status;
        private int like_total;
        private int runtime;
        private int season;
        private int seconds;
        private String thumbs;
        private String title;

        public int getLike_status() {
            return this.like_status;
        }

        public void setLike_status(int i) {
            this.like_status = i;
        }

        public int getLike_total() {
            return this.like_total;
        }

        public void setLike_total(int i) {
            this.like_total = i;
        }

        public int getDislike_total() {
            return this.dislike_total;
        }

        public void setDislike_total(int i) {
            this.dislike_total = i;
        }

        public long getLast_play_time() {
            return this.last_play_time;
        }

        public void setLast_play_time(long j) {
            this.last_play_time = j;
        }

        public int getSeconds() {
            return this.seconds;
        }

        public void setSeconds(int i) {
            this.seconds = i;
        }

        public int getRuntime() {
            return this.runtime;
        }

        public void setRuntime(int i) {
            this.runtime = i;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public String getImdb_rating() {
            return this.imdb_rating;
        }

        public void setImdb_rating(String str) {
            this.imdb_rating = str;
        }

        public int getSeason() {
            return this.season;
        }

        public void setSeason(int i) {
            this.season = i;
        }

        public int getEpisode() {
            return this.episode;
        }

        public void setEpisode(int i) {
            this.episode = i;
        }

        public String getThumbs() {
            return this.thumbs;
        }

        public void setThumbs(String str) {
            this.thumbs = str;
        }
    }
}
