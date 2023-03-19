package com.movieboxpro.android.model;

import java.io.Serializable;
/* loaded from: classes3.dex */
public class LoginBrowser implements Serializable {
    private long add_time;
    private String browser;
    private String id;
    private long last_time;
    private String os;
    private String uniqid;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getOs() {
        return this.os;
    }

    public void setOs(String str) {
        this.os = str;
    }

    public String getBrowser() {
        return this.browser;
    }

    public void setBrowser(String str) {
        this.browser = str;
    }

    public String getUniqid() {
        return this.uniqid;
    }

    public void setUniqid(String str) {
        this.uniqid = str;
    }

    public long getAdd_time() {
        return this.add_time;
    }

    public void setAdd_time(long j) {
        this.add_time = j;
    }

    public long getLast_time() {
        return this.last_time;
    }

    public void setLast_time(long j) {
        this.last_time = j;
    }
}
