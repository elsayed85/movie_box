package com.movieboxpro.android.model;

import java.util.List;
/* loaded from: classes3.dex */
public class MessageResponse {
    private List<MessagesModel> list;
    private int newCount;

    public int getNewCount() {
        return this.newCount;
    }

    public void setNewCount(int i) {
        this.newCount = i;
    }

    public List<MessagesModel> getList() {
        return this.list;
    }

    public void setList(List<MessagesModel> list) {
        this.list = list;
    }
}
