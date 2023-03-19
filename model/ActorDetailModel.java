package com.movieboxpro.android.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
/* loaded from: classes3.dex */
public class ActorDetailModel {
    private ActorModel actor;
    private List<ActorMovie> movie;

    /* renamed from: tv  reason: collision with root package name */
    private List<ActorTvShow> f53tv;

    public ActorModel getActor() {
        return this.actor;
    }

    public void setActor(ActorModel actorModel) {
        this.actor = actorModel;
    }

    public List<ActorTvShow> getTv() {
        return this.f53tv;
    }

    public void setTv(List<ActorTvShow> list) {
        this.f53tv = list;
    }

    public List<ActorMovie> getMovie() {
        return this.movie;
    }

    public void setMovie(List<ActorMovie> list) {
        this.movie = list;
    }

    /* loaded from: classes3.dex */
    public static class ActorMovie {
        private String id;
        private String imdb_rating;
        private String poster;
        private String quality_tag_new;

        public String getImdb_rating() {
            return this.imdb_rating;
        }

        public void setImdb_rating(String str) {
            this.imdb_rating = str;
        }

        public String getId() {
            return this.id;
        }

        public void setId(String str) {
            this.id = str;
        }

        public String getPoster() {
            return this.poster;
        }

        public void setPoster(String str) {
            this.poster = str;
        }

        public String getQuality_tag_new() {
            return this.quality_tag_new;
        }

        public void setQuality_tag_new(String str) {
            this.quality_tag_new = str;
        }
    }

    /* loaded from: classes3.dex */
    public static class ActorTvShow implements Parcelable {
        public static final Parcelable.Creator<ActorTvShow> CREATOR = new Parcelable.Creator<ActorTvShow>() { // from class: com.movieboxpro.android.model.ActorDetailModel.ActorTvShow.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ActorTvShow createFromParcel(Parcel parcel) {
                return new ActorTvShow(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ActorTvShow[] newArray(int i) {
                return new ActorTvShow[i];
            }
        };
        private String banner_mini;
        private String id;
        private String imdb_rating;
        private String poster;
        private String season_episode;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String getImdb_rating() {
            return this.imdb_rating;
        }

        public void setImdb_rating(String str) {
            this.imdb_rating = str;
        }

        public String getBanner_mini() {
            return this.banner_mini;
        }

        public void setBanner_mini(String str) {
            this.banner_mini = str;
        }

        public String getId() {
            return this.id;
        }

        public void setId(String str) {
            this.id = str;
        }

        public String getPoster() {
            return this.poster;
        }

        public void setPoster(String str) {
            this.poster = str;
        }

        public String getSeason_episode() {
            return this.season_episode;
        }

        public void setSeason_episode(String str) {
            this.season_episode = str;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.id);
            parcel.writeString(this.poster);
            parcel.writeString(this.season_episode);
            parcel.writeString(this.banner_mini);
            parcel.writeString(this.imdb_rating);
        }

        public ActorTvShow() {
        }

        protected ActorTvShow(Parcel parcel) {
            this.id = parcel.readString();
            this.poster = parcel.readString();
            this.season_episode = parcel.readString();
            this.banner_mini = parcel.readString();
            this.imdb_rating = parcel.readString();
        }
    }
}
