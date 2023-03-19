package com.movieboxpro.android.model;

import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;
import java.util.List;
/* loaded from: classes3.dex */
public class FavoriteSeasonItem extends BaseExpandNode {
    private List<FavoriteEpisodeItem> episode_list;
    private List<BaseNode> episodes;
    private String id;
    private FavoriteEpisodeItem lastStartWatchedItem;
    private int lastWatchedIndex;
    private FavoriteEpisodeItem lastWatchedItem;
    private int season;
    private List<Integer> watchList;
    private int watchedCount;

    public FavoriteEpisodeItem getLastWatchedItem() {
        return this.lastWatchedItem;
    }

    public void setLastWatchedItem(FavoriteEpisodeItem favoriteEpisodeItem) {
        this.lastWatchedItem = favoriteEpisodeItem;
    }

    public FavoriteEpisodeItem getLastStartWatchedItem() {
        return this.lastStartWatchedItem;
    }

    public void setLastStartWatchedItem(FavoriteEpisodeItem favoriteEpisodeItem) {
        this.lastStartWatchedItem = favoriteEpisodeItem;
    }

    public int getLastWatchedIndex() {
        return this.lastWatchedIndex;
    }

    public void setLastWatchedIndex(int i) {
        this.lastWatchedIndex = i;
    }

    public List<Integer> getWatchList() {
        return this.watchList;
    }

    public void setWatchList(List<Integer> list) {
        this.watchList = list;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public int getWatchedCount() {
        return this.watchedCount;
    }

    public void setWatchedCount(int i) {
        this.watchedCount = i;
    }

    public int getSeason() {
        return this.season;
    }

    public void setSeason(int i) {
        this.season = i;
    }

    public List<FavoriteEpisodeItem> getEpisode_list() {
        return this.episode_list;
    }

    public void setEpisode_list(List<FavoriteEpisodeItem> list) {
        this.episode_list = list;
    }

    @Override // com.chad.library.adapter.base.entity.node.BaseNode
    public List<BaseNode> getChildNode() {
        return this.episodes;
    }

    public List<BaseNode> getEpisodes() {
        return this.episodes;
    }

    public void setEpisodes(List<BaseNode> list) {
        this.episodes = list;
    }
}
