package com.movieboxpro.android.model;

import java.util.ArrayList;
/* loaded from: classes3.dex */
public class ListResponse<T> {
    private ArrayList<T> list;

    public void setList(ArrayList<T> arrayList) {
        this.list = arrayList;
    }

    public ArrayList<T> getList() {
        return this.list;
    }
}
