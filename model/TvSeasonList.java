package com.movieboxpro.android.model;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes3.dex */
public class TvSeasonList implements Parcelable {
    public static final Parcelable.Creator<TvSeasonList> CREATOR = new Parcelable.Creator<TvSeasonList>() { // from class: com.movieboxpro.android.model.TvSeasonList.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TvSeasonList createFromParcel(Parcel parcel) {
            return new TvSeasonList(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TvSeasonList[] newArray(int i) {
            return new TvSeasonList[i];
        }
    };
    private int episode;
    private String id;
    private long over;
    private int season;
    private long seconds;
    private String tid;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getTid() {
        return this.tid;
    }

    public void setTid(String str) {
        this.tid = str;
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

    public long getOver() {
        return this.over;
    }

    public void setOver(long j) {
        this.over = j;
    }

    public long getSeconds() {
        return this.seconds;
    }

    public void setSeconds(long j) {
        this.seconds = j;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.tid);
        parcel.writeInt(this.season);
        parcel.writeInt(this.episode);
        parcel.writeString(this.id);
        parcel.writeLong(this.over);
        parcel.writeLong(this.seconds);
    }

    public TvSeasonList() {
    }

    protected TvSeasonList(Parcel parcel) {
        this.tid = parcel.readString();
        this.season = parcel.readInt();
        this.episode = parcel.readInt();
        this.id = parcel.readString();
        this.over = parcel.readLong();
        this.seconds = parcel.readLong();
    }
}
