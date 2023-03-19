package com.movieboxpro.android.db.entity;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
/* loaded from: classes3.dex */
public class TestNetRecode {
    public String country;
    public String description;
    public int display_order;
    public String domain;
    public Long endTime;
    public int id;
    private int ids;
    public float ratio;
    public Long startTime = -1L;
    public String state = TtmlNode.START;
    public String url;

    public int getIds() {
        return this.ids;
    }

    public void setIds(int i) {
        this.ids = i;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getDomain() {
        return this.domain;
    }

    public void setDomain(String str) {
        this.domain = str;
    }

    public int getDisplay_order() {
        return this.display_order;
    }

    public void setDisplay_order(int i) {
        this.display_order = i;
    }

    public float getRatio() {
        return this.ratio;
    }

    public void setRatio(float f) {
        this.ratio = f;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public Long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Long l) {
        this.startTime = l;
    }

    public Long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Long l) {
        this.endTime = l;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String str) {
        this.state = str;
    }
}
