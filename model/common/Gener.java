package com.movieboxpro.android.model.common;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes3.dex */
public class Gener implements Parcelable {
    public static final Parcelable.Creator<Gener> CREATOR = new Parcelable.Creator<Gener>() { // from class: com.movieboxpro.android.model.common.Gener.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Gener createFromParcel(Parcel parcel) {
            return new Gener(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Gener[] newArray(int i) {
            return new Gener[i];
        }
    };
    private int hot;
    private int id;
    private String img;
    private boolean isSelect;
    private String mark;
    private String name;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean isSelect() {
        return this.isSelect;
    }

    public void setSelect(boolean z) {
        this.isSelect = z;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getMark() {
        return this.mark;
    }

    public void setMark(String str) {
        this.mark = str;
    }

    public int getHot() {
        return this.hot;
    }

    public void setHot(int i) {
        this.hot = i;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String str) {
        this.img = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.mark);
        parcel.writeInt(this.hot);
        parcel.writeString(this.img);
    }

    public Gener() {
    }

    protected Gener(Parcel parcel) {
        this.id = parcel.readInt();
        this.name = parcel.readString();
        this.mark = parcel.readString();
        this.hot = parcel.readInt();
        this.img = parcel.readString();
    }
}
