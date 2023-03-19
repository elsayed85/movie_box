package com.movieboxpro.android.model;
/* loaded from: classes3.dex */
public class ChatMsgModel {
    private String author;
    private String authorid;
    private String avatar;
    private long dateline;
    private int isYou;
    private String members;
    private String message;
    private String msgfrom;
    private String msgfromid;
    private String msgtoid;
    private String plid;
    private String pmid;
    private String pmtype;
    private String subject;
    private String touid;

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public String getPlid() {
        return this.plid;
    }

    public void setPlid(String str) {
        this.plid = str;
    }

    public String getAuthorid() {
        return this.authorid;
    }

    public void setAuthorid(String str) {
        this.authorid = str;
    }

    public String getPmtype() {
        return this.pmtype;
    }

    public void setPmtype(String str) {
        this.pmtype = str;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String str) {
        this.subject = str;
    }

    public String getMembers() {
        return this.members;
    }

    public void setMembers(String str) {
        this.members = str;
    }

    public long getDateline() {
        return this.dateline;
    }

    public void setDateline(long j) {
        this.dateline = j;
    }

    public String getPmid() {
        return this.pmid;
    }

    public void setPmid(String str) {
        this.pmid = str;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getTouid() {
        return this.touid;
    }

    public void setTouid(String str) {
        this.touid = str;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String str) {
        this.author = str;
    }

    public String getMsgfromid() {
        return this.msgfromid;
    }

    public void setMsgfromid(String str) {
        this.msgfromid = str;
    }

    public String getMsgfrom() {
        return this.msgfrom;
    }

    public void setMsgfrom(String str) {
        this.msgfrom = str;
    }

    public String getMsgtoid() {
        return this.msgtoid;
    }

    public void setMsgtoid(String str) {
        this.msgtoid = str;
    }

    public int getIsYou() {
        return this.isYou;
    }

    public void setIsYou(int i) {
        this.isYou = i;
    }
}
