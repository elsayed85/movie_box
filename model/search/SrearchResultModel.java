package com.movieboxpro.android.model.search;

import com.movieboxpro.android.model.ActorModel;
import com.movieboxpro.android.model.movie.MovieListModel;
import java.util.List;
/* loaded from: classes3.dex */
public class SrearchResultModel {
    public static final int ACTORS = 2;
    public static final int NORMAL = 1;
    public static final int PLAYLIST = 3;
    private List<ActorModel> actorModels;
    public String actors;
    public String banner_mini;
    public int box_type;
    public String cats;
    public String collect;
    public String description;
    public String id;
    public String imdb_rating;
    public String is_collect;
    public String lang;
    private List<MovieListModel.MovieListItem> playList;
    public String poster;
    public String quality_tag_new;
    public String runtime;
    public String title;
    public String trailer_url;
    public String view;
    private int viewType;
    public String year;

    public List<MovieListModel.MovieListItem> getPlayList() {
        return this.playList;
    }

    public void setPlayList(List<MovieListModel.MovieListItem> list) {
        this.playList = list;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public int getBox_type() {
        return this.box_type;
    }

    public void setBox_type(int i) {
        this.box_type = i;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getActors() {
        return this.actors;
    }

    public void setActors(String str) {
        this.actors = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getPoster() {
        return this.poster;
    }

    public void setPoster(String str) {
        this.poster = str;
    }

    public String getTrailer_url() {
        return this.trailer_url;
    }

    public void setTrailer_url(String str) {
        this.trailer_url = str;
    }

    public String getCats() {
        return this.cats;
    }

    public void setCats(String str) {
        this.cats = str;
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(String str) {
        this.year = str;
    }

    public String getLang() {
        return this.lang;
    }

    public void setLang(String str) {
        this.lang = str;
    }

    public String getBanner_mini() {
        return this.banner_mini;
    }

    public void setBanner_mini(String str) {
        this.banner_mini = str;
    }

    public String getCollect() {
        return this.collect;
    }

    public void setCollect(String str) {
        this.collect = str;
    }

    public String getView() {
        return this.view;
    }

    public void setView(String str) {
        this.view = str;
    }

    public String getIs_collect() {
        return this.is_collect;
    }

    public void setIs_collect(String str) {
        this.is_collect = str;
    }

    public String getQuality_tag_new() {
        return this.quality_tag_new;
    }

    public void setQuality_tag_new(String str) {
        this.quality_tag_new = str;
    }

    public String getRuntime() {
        return this.runtime;
    }

    public void setRuntime(String str) {
        this.runtime = str;
    }

    public String getImdb_rating() {
        return this.imdb_rating;
    }

    public void setImdb_rating(String str) {
        this.imdb_rating = str;
    }

    public int getViewType() {
        return this.viewType;
    }

    public void setViewType(int i) {
        this.viewType = i;
    }

    public List<ActorModel> getActorModels() {
        return this.actorModels;
    }

    public void setActorModels(List<ActorModel> list) {
        this.actorModels = list;
    }
}
