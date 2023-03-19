package com.movieboxpro.android.model;

import com.chad.library.adapter.base.entity.node.BaseNode;
import java.util.List;
/* loaded from: classes3.dex */
public class FavoriteTitle extends BaseNode {
    private boolean waiting;

    @Override // com.chad.library.adapter.base.entity.node.BaseNode
    public List<BaseNode> getChildNode() {
        return null;
    }

    public FavoriteTitle(boolean z) {
        this.waiting = z;
    }

    public boolean isWaiting() {
        return this.waiting;
    }

    public void setWaiting(boolean z) {
        this.waiting = z;
    }
}
