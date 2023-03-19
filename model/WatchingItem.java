package com.movieboxpro.android.model;

import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.movieboxpro.android.adapter.CollectAdapter;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes3.dex */
public class WatchingItem extends BaseExpandNode {
    private CollectAdapter.FavoriteWatchingProvider.WatchingAdapter adapter;
    private boolean haveWaiting;
    private ArrayList<FavoriteItem> list;
    private int waitingNum;
    private int watchingNum;

    @Override // com.chad.library.adapter.base.entity.node.BaseNode
    public List<BaseNode> getChildNode() {
        return null;
    }

    public boolean isHaveWaiting() {
        return this.haveWaiting;
    }

    public void setHaveWaiting(boolean z) {
        this.haveWaiting = z;
    }

    public CollectAdapter.FavoriteWatchingProvider.WatchingAdapter getAdapter() {
        return this.adapter;
    }

    public void setAdapter(CollectAdapter.FavoriteWatchingProvider.WatchingAdapter watchingAdapter) {
        this.adapter = watchingAdapter;
    }

    public ArrayList<FavoriteItem> getList() {
        return this.list;
    }

    public void setList(ArrayList<FavoriteItem> arrayList) {
        this.list = arrayList;
    }

    public int getWatchingNum() {
        return this.watchingNum;
    }

    public void setWatchingNum(int i) {
        this.watchingNum = i;
    }

    public int getWaitingNum() {
        return this.waitingNum;
    }

    public void setWaitingNum(int i) {
        this.waitingNum = i;
    }
}
