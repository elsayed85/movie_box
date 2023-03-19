package com.movieboxpro.android.model.movie;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes3.dex */
public class NormalFilmModel implements Parcelable {
    public static final Parcelable.Creator<NormalFilmModel> CREATOR = new Parcelable.Creator<NormalFilmModel>() { // from class: com.movieboxpro.android.model.movie.NormalFilmModel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NormalFilmModel createFromParcel(Parcel parcel) {
            return new NormalFilmModel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NormalFilmModel[] newArray(int i) {
            return new NormalFilmModel[i];
        }
    };
    @SerializedName("3d")
    private int _$3d;
    private String actors;
    private int box_type;
    private boolean checked;
    private String description;
    private int id;
    private String imdb_rating;
    private String mark;
    private String poster;
    private String quality_tag_new;
    private long runtime;
    private String title;
    private int updateCount;
    private int year;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof NormalFilmModel) {
            NormalFilmModel normalFilmModel = (NormalFilmModel) obj;
            return normalFilmModel.box_type == this.box_type && normalFilmModel.id == this.id;
        }
        return false;
    }

    public String getMark() {
        return this.mark;
    }

    public void setMark(String str) {
        this.mark = str;
    }

    public int hashCode() {
        int i = this.id;
        int i2 = this.box_type;
        return (i * i2) + i2 + 31;
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void setChecked(boolean z) {
        this.checked = z;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
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

    public int getYear() {
        return this.year;
    }

    public void setYear(int i) {
        this.year = i;
    }

    public long getRuntime() {
        return this.runtime;
    }

    public void setRuntime(long j) {
        this.runtime = j;
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

    public int getUpdateCount() {
        return this.updateCount;
    }

    public void setUpdateCount(int i) {
        this.updateCount = i;
    }

    public int get_$3d() {
        return this._$3d;
    }

    public void set_$3d(int i) {
        this._$3d = i;
    }

    public String getQuality_tag_new() {
        return this.quality_tag_new;
    }

    public void setQuality_tag_new(String str) {
        this.quality_tag_new = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.title);
        parcel.writeString(this.poster);
        parcel.writeInt(this.box_type);
        parcel.writeString(this.imdb_rating);
        parcel.writeInt(this.year);
        parcel.writeLong(this.runtime);
        parcel.writeString(this.actors);
        parcel.writeString(this.description);
        parcel.writeInt(this.updateCount);
        parcel.writeInt(this._$3d);
        parcel.writeString(this.quality_tag_new);
        parcel.writeByte(this.checked ? (byte) 1 : (byte) 0);
        parcel.writeString(this.mark);
    }

    public NormalFilmModel() {
        this.mark = "";
    }

    protected NormalFilmModel(Parcel parcel) {
        this.mark = "";
        this.id = parcel.readInt();
        this.title = parcel.readString();
        this.poster = parcel.readString();
        this.box_type = parcel.readInt();
        this.imdb_rating = parcel.readString();
        this.year = parcel.readInt();
        this.runtime = parcel.readLong();
        this.actors = parcel.readString();
        this.description = parcel.readString();
        this.updateCount = parcel.readInt();
        this._$3d = parcel.readInt();
        this.quality_tag_new = parcel.readString();
        this.checked = parcel.readByte() != 0;
        this.mark = parcel.readString();
    }
}
