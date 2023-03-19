package com.movieboxpro.android.view.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import android.view.WindowManager;
import androidx.recyclerview.widget.RecyclerView;
import com.movieboxpro.android.utils.DensityUtils;
/* loaded from: classes3.dex */
public class GalleryItemDecoration extends RecyclerView.ItemDecoration {
    private int mLeftPageVisibleWidth;
    private int mPageMargin = 10;

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int dpToPx;
        int dpToPx2;
        if (this.mLeftPageVisibleWidth == 0) {
            this.mLeftPageVisibleWidth = DensityUtils.px2dp(view.getContext(), getScreenWidth(view.getContext()) - DensityUtils.dp2px(view.getContext(), 110.0f)) / 2;
        }
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        int itemCount = recyclerView.getAdapter().getItemCount();
        if (childAdapterPosition == 0) {
            dpToPx = dpToPx(this.mLeftPageVisibleWidth);
        } else {
            dpToPx = dpToPx(this.mPageMargin);
        }
        if (childAdapterPosition == itemCount - 1) {
            dpToPx2 = dpToPx(this.mLeftPageVisibleWidth);
        } else {
            dpToPx2 = dpToPx(this.mPageMargin);
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        layoutParams.setMargins(dpToPx, 10, dpToPx2, 10);
        view.setLayoutParams(layoutParams);
        super.getItemOffsets(rect, view, recyclerView, state);
    }

    private int dpToPx(int i) {
        return (int) ((i * Resources.getSystem().getDisplayMetrics().density) + 0.5f);
    }

    public static int getScreenWidth(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getWidth();
    }
}
