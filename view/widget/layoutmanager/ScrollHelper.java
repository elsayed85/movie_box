package com.movieboxpro.android.view.widget.layoutmanager;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes3.dex */
public class ScrollHelper {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void smoothScrollToPosition(RecyclerView recyclerView, ViewPagerLayoutManager viewPagerLayoutManager, int i) {
        int offsetToPosition = viewPagerLayoutManager.getOffsetToPosition(i);
        if (viewPagerLayoutManager.getOrientation() == 1) {
            recyclerView.smoothScrollBy(0, offsetToPosition);
        } else {
            recyclerView.smoothScrollBy(offsetToPosition, 0);
        }
    }

    public static void smoothScrollToTargetView(RecyclerView recyclerView, View view) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof ViewPagerLayoutManager) {
            ViewPagerLayoutManager viewPagerLayoutManager = (ViewPagerLayoutManager) layoutManager;
            smoothScrollToPosition(recyclerView, viewPagerLayoutManager, viewPagerLayoutManager.getLayoutPositionOfView(view));
        }
    }
}
