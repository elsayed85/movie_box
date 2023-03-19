package com.movieboxpro.android.model;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes3.dex */
public class ReplyMessageModel {
    private String author;
    private String authorid;
    private String avatar;
    private String category;
    private long dateline;
    private String fid;
    private String from_id;
    private String from_idtype;
    private String from_num;
    private String id;
    @SerializedName("new")
    private String newX;
    private String note;
    private String pid;
    private String position;
    private String rowid;
    private String style;
    private String tid;
    private String type;
    private String uid;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getNewX() {
        return this.newX;
    }

    public void setNewX(String str) {
        this.newX = str;
    }

    public String getAuthorid() {
        return this.authorid;
    }

    public void setAuthorid(String str) {
        this.authorid = str;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String str) {
        this.author = str;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String str) {
        this.note = str;
    }

    public long getDateline() {
        return this.dateline;
    }

    public void setDateline(long j) {
        this.dateline = j;
    }

    public String getFrom_id() {
        return this.from_id;
    }

    public void setFrom_id(String str) {
        this.from_id = str;
    }

    public String getFrom_idtype() {
        return this.from_idtype;
    }

    public void setFrom_idtype(String str) {
        this.from_idtype = str;
    }

    public String getFrom_num() {
        return this.from_num;
    }

    public void setFrom_num(String str) {
        this.from_num = str;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String str) {
        this.category = str;
    }

    public String getStyle() {
        return this.style;
    }

    public void setStyle(String str) {
        this.style = str;
    }

    public String getRowid() {
        return this.rowid;
    }

    public void setRowid(String str) {
        this.rowid = str;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public String getTid() {
        return this.tid;
    }

    public void setTid(String str) {
        this.tid = str;
    }

    public String getPid() {
        return this.pid;
    }

    public void setPid(String str) {
        this.pid = str;
    }

    public String getFid() {
        return this.fid;
    }

    public void setFid(String str) {
        this.fid = str;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String str) {
        this.position = str;
    }
}
