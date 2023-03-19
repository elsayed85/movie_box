package com.movieboxpro.android.model;

import java.util.HashMap;
/* loaded from: classes3.dex */
public class ReviewRecordModel {
    private String content;
    private HashMap<String, String> imgMap;
    private HashMap<String, String> thumbImageMap;
    private String title;
    private String type;

    public ReviewRecordModel() {
    }

    public ReviewRecordModel(String str, String str2, String str3, HashMap<String, String> hashMap, HashMap<String, String> hashMap2) {
        this.content = str;
        this.type = str2;
        this.title = str3;
        this.imgMap = hashMap;
        this.thumbImageMap = hashMap2;
    }

    public HashMap<String, String> getThumbImageMap() {
        return this.thumbImageMap;
    }

    public void setThumbImageMap(HashMap<String, String> hashMap) {
        this.thumbImageMap = hashMap;
    }

    public HashMap<String, String> getImgMap() {
        return this.imgMap;
    }

    public void setImgMap(HashMap<String, String> hashMap) {
        this.imgMap = hashMap;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
