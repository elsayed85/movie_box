package com.movieboxpro.android.model;

import java.util.List;
/* loaded from: classes3.dex */
public class ReviewPageModel {
    private String count;
    private List<ReviewModel> list;
    private int totalPage;

    public int getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(int i) {
        this.totalPage = i;
    }

    public List<ReviewModel> getList() {
        return this.list;
    }

    public void setList(List<ReviewModel> list) {
        this.list = list;
    }

    public String getCount() {
        return this.count;
    }

    public void setCount(String str) {
        this.count = str;
    }
}
