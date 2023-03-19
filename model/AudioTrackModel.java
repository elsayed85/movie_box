package com.movieboxpro.android.model;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes3.dex */
public class AudioTrackModel implements Parcelable {
    public static final Parcelable.Creator<AudioTrackModel> CREATOR = new Parcelable.Creator<AudioTrackModel>() { // from class: com.movieboxpro.android.model.AudioTrackModel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AudioTrackModel createFromParcel(Parcel parcel) {
            return new AudioTrackModel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AudioTrackModel[] newArray(int i) {
            return new AudioTrackModel[i];
        }
    };
    private long add_time;
    private String bitstream;
    private String channel;
    private String download_url;
    private ExoAudioTrackInfo exoAudioTrackInfo;
    private String fid;
    private String file;
    private String format;
    private String frequency;
    private int id;
    private String lang;
    private String name;
    private String oss_fid;
    private boolean select;
    private long size_bytes;
    private String time;
    private int trackId;
    private String video_file_name;
    private int vip_only;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ExoAudioTrackInfo getExoAudioTrackInfo() {
        return this.exoAudioTrackInfo;
    }

    public void setExoAudioTrackInfo(ExoAudioTrackInfo exoAudioTrackInfo) {
        this.exoAudioTrackInfo = exoAudioTrackInfo;
    }

    public int getTrackId() {
        return this.trackId;
    }

    public void setTrackId(int i) {
        this.trackId = i;
    }

    public int getVip_only() {
        return this.vip_only;
    }

    public void setVip_only(int i) {
        this.vip_only = i;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public String getFid() {
        return this.fid;
    }

    public void setFid(String str) {
        this.fid = str;
    }

    public String getOss_fid() {
        return this.oss_fid;
    }

    public void setOss_fid(String str) {
        this.oss_fid = str;
    }

    public String getVideo_file_name() {
        return this.video_file_name;
    }

    public void setVideo_file_name(String str) {
        this.video_file_name = str;
    }

    public String getChannel() {
        return this.channel;
    }

    public void setChannel(String str) {
        this.channel = str;
    }

    public long getAdd_time() {
        return this.add_time;
    }

    public void setAdd_time(long j) {
        this.add_time = j;
    }

    public String getDownload_url() {
        return this.download_url;
    }

    public void setDownload_url(String str) {
        this.download_url = str;
    }

    public String getBitstream() {
        return this.bitstream;
    }

    public void setBitstream(String str) {
        this.bitstream = str;
    }

    public String getFrequency() {
        return this.frequency;
    }

    public void setFrequency(String str) {
        this.frequency = str;
    }

    public long getSize_bytes() {
        return this.size_bytes;
    }

    public void setSize_bytes(long j) {
        this.size_bytes = j;
    }

    public String getFormat() {
        return this.format;
    }

    public void setFormat(String str) {
        this.format = str;
    }

    public String getFile() {
        return this.file;
    }

    public void setFile(String str) {
        this.file = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public boolean isSelect() {
        return this.select;
    }

    public void setSelect(boolean z) {
        this.select = z;
    }

    public String getLang() {
        return this.lang;
    }

    public void setLang(String str) {
        this.lang = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeInt(this.id);
        parcel.writeString(this.download_url);
        parcel.writeByte(this.select ? (byte) 1 : (byte) 0);
        parcel.writeString(this.bitstream);
        parcel.writeString(this.frequency);
        parcel.writeLong(this.size_bytes);
        parcel.writeString(this.format);
        parcel.writeString(this.file);
        parcel.writeString(this.lang);
        parcel.writeLong(this.add_time);
        parcel.writeString(this.channel);
        parcel.writeString(this.video_file_name);
        parcel.writeString(this.oss_fid);
        parcel.writeString(this.fid);
        parcel.writeString(this.time);
        parcel.writeInt(this.trackId);
        parcel.writeParcelable(this.exoAudioTrackInfo, i);
    }

    public AudioTrackModel() {
    }

    protected AudioTrackModel(Parcel parcel) {
        this.name = parcel.readString();
        this.id = parcel.readInt();
        this.download_url = parcel.readString();
        this.select = parcel.readByte() != 0;
        this.bitstream = parcel.readString();
        this.frequency = parcel.readString();
        this.size_bytes = parcel.readLong();
        this.format = parcel.readString();
        this.file = parcel.readString();
        this.lang = parcel.readString();
        this.add_time = parcel.readLong();
        this.channel = parcel.readString();
        this.video_file_name = parcel.readString();
        this.oss_fid = parcel.readString();
        this.fid = parcel.readString();
        this.time = parcel.readString();
        this.trackId = parcel.readInt();
        this.exoAudioTrackInfo = (ExoAudioTrackInfo) parcel.readParcelable(ExoAudioTrackInfo.class.getClassLoader());
    }
}
