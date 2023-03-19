package com.movieboxpro.android.model;

import com.chad.library.adapter.base.entity.node.BaseNode;
import java.util.List;
/* loaded from: classes3.dex */
public class FavoriteEpisodeItem extends BaseNode {
    private int episode;
    private String id;
    private boolean lastItem;
    private boolean lastSeason;
    private int over;
    private int runtime;
    private int season;
    private int seconds;
    private String thumbs;
    private String title;

    @Override // com.chad.library.adapter.base.entity.node.BaseNode
    public List<BaseNode> getChildNode() {
        return null;
    }

    public String getThumbs() {
        return this.thumbs;
    }

    public void setThumbs(String str) {
        this.thumbs = str;
    }

    public boolean isLastItem() {
        return this.lastItem;
    }

    public void setLastItem(boolean z) {
        this.lastItem = z;
    }

    public boolean isLastSeason() {
        return this.lastSeason;
    }

    public void setLastSeason(boolean z) {
        this.lastSeason = z;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
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

    public int getSeconds() {
        return this.seconds;
    }

    public void setSeconds(int i) {
        this.seconds = i;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
