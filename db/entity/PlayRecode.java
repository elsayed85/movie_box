package com.movieboxpro.android.db.entity;
/* loaded from: classes3.dex */
public class PlayRecode {
    private int box_type;
    private int episode;
    private int id;
    private String imdb_id;
    private String movieId;
    private String privateid;
    private int quality;
    private int season;
    private int start_time;
    private String title;

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getPrivateid() {
        return this.privateid;
    }

    public void setPrivateid(String str) {
        this.privateid = str;
    }

    public String getMovieId() {
        return this.movieId;
    }

    public void setMovieId(String str) {
        this.movieId = str;
    }

    public int getBox_type() {
        return this.box_type;
    }

    public void setBox_type(int i) {
        this.box_type = i;
    }

    public String getImdb_id() {
        return this.imdb_id;
    }

    public void setImdb_id(String str) {
        this.imdb_id = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public int getStart_time() {
        return this.start_time;
    }

    public void setStart_time(int i) {
        this.start_time = i;
    }

    public int getQuality() {
        return this.quality;
    }

    public void setQuality(int i) {
        this.quality = i;
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
}
