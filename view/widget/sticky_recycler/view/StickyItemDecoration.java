package com.movieboxpro.android.view.widget.sticky_recycler.view;

import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes3.dex */
public class StickyItemDecoration extends RecyclerView.ItemDecoration {
    private RecyclerView.Adapter<RecyclerView.ViewHolder> mAdapter;
    private boolean mCurrentUIFindStickView;
    private int mItemViewHeight;
    private LinearLayoutManager mLayoutManager;
    private View mStickyItemView;
    private int mStickyItemViewMarginTop;
    private RecyclerView.ViewHolder mViewHolder;
    private int mBindDataPosition = -1;
    private List<Integer> mStickyPositionList = new ArrayList();

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        super.onDrawOver(canvas, recyclerView, state);
        if (recyclerView.getAdapter().getItemCount() <= 0) {
            return;
        }
        this.mCurrentUIFindStickView = false;
        this.mLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int childCount = recyclerView.getChildCount();
        int i = 0;
        while (true) {
            if (i >= childCount) {
                break;
            }
            View childAt = recyclerView.getChildAt(i);
            if (((Boolean) childAt.getTag()).booleanValue()) {
                this.mCurrentUIFindStickView = true;
                getStickyViewHolder(recyclerView);
                cacheStickyViewPosition(i);
                if (childAt.getTop() <= 0) {
                    bindDataForStickyView(this.mLayoutManager.findFirstVisibleItemPosition(), recyclerView.getMeasuredWidth());
                } else if (this.mStickyPositionList.size() > 0) {
                    if (this.mStickyPositionList.size() == 1) {
                        bindDataForStickyView(this.mStickyPositionList.get(0).intValue(), recyclerView.getMeasuredWidth());
                    } else {
                        bindDataForStickyView(this.mStickyPositionList.get(this.mStickyPositionList.lastIndexOf(Integer.valueOf(this.mLayoutManager.findFirstVisibleItemPosition() + i)) - 1).intValue(), recyclerView.getMeasuredWidth());
                    }
                }
                if (childAt.getTop() > 0) {
                    int top2 = childAt.getTop();
                    int i2 = this.mItemViewHeight;
                    if (top2 <= i2) {
                        this.mStickyItemViewMarginTop = i2 - childAt.getTop();
                        drawStickyItemView(canvas);
                    }
                }
                this.mStickyItemViewMarginTop = 0;
                View nextStickyView = getNextStickyView(recyclerView);
                if (nextStickyView != null) {
                    int top3 = nextStickyView.getTop();
                    int i3 = this.mItemViewHeight;
                    if (top3 <= i3) {
                        this.mStickyItemViewMarginTop = i3 - nextStickyView.getTop();
                    }
                }
                drawStickyItemView(canvas);
            } else {
                i++;
            }
        }
        if (this.mCurrentUIFindStickView) {
            return;
        }
        this.mStickyItemViewMarginTop = 0;
        if (this.mLayoutManager.findFirstVisibleItemPosition() + recyclerView.getChildCount() == recyclerView.getAdapter().getItemCount() && this.mStickyPositionList.size() > 0) {
            List<Integer> list = this.mStickyPositionList;
            bindDataForStickyView(list.get(list.size() - 1).intValue(), recyclerView.getMeasuredWidth());
        }
        drawStickyItemView(canvas);
    }

    private View getNextStickyView(RecyclerView recyclerView) {
        int childCount = recyclerView.getChildCount();
        View view = null;
        int i = 0;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = recyclerView.getChildAt(i2);
            if (((Boolean) childAt.getTag()).booleanValue()) {
                i++;
                view = childAt;
            }
            if (i == 2) {
                break;
            }
        }
        return view;
    }

    private void getStickyViewHolder(RecyclerView recyclerView) {
        if (this.mAdapter != null) {
            return;
        }
        RecyclerView.Adapter<RecyclerView.ViewHolder> adapter = recyclerView.getAdapter();
        this.mAdapter = adapter;
        RecyclerView.ViewHolder onCreateViewHolder = adapter.onCreateViewHolder(recyclerView, 1);
        this.mViewHolder = onCreateViewHolder;
        this.mStickyItemView = onCreateViewHolder.itemView;
    }

    private void cacheStickyViewPosition(int i) {
        int findFirstVisibleItemPosition = this.mLayoutManager.findFirstVisibleItemPosition() + i;
        if (this.mStickyPositionList.contains(Integer.valueOf(findFirstVisibleItemPosition))) {
            return;
        }
        this.mStickyPositionList.add(Integer.valueOf(findFirstVisibleItemPosition));
    }

    private void bindDataForStickyView(int i, int i2) {
        RecyclerView.ViewHolder viewHolder;
        if (this.mBindDataPosition == i || (viewHolder = this.mViewHolder) == null) {
            return;
        }
        this.mBindDataPosition = i;
        this.mAdapter.onBindViewHolder(viewHolder, i);
        measureLayoutStickyItemView(i2);
        this.mItemViewHeight = this.mViewHolder.itemView.getBottom() - this.mViewHolder.itemView.getTop();
    }

    private void measureLayoutStickyItemView(int i) {
        int makeMeasureSpec;
        View view = this.mStickyItemView;
        if (view == null || !view.isLayoutRequested()) {
            return;
        }
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i, 1073741824);
        ViewGroup.LayoutParams layoutParams = this.mStickyItemView.getLayoutParams();
        if (layoutParams != null && layoutParams.height > 0) {
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824);
        } else {
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        }
        this.mStickyItemView.measure(makeMeasureSpec2, makeMeasureSpec);
        View view2 = this.mStickyItemView;
        view2.layout(0, 0, view2.getMeasuredWidth(), this.mStickyItemView.getMeasuredHeight());
    }

    private void drawStickyItemView(Canvas canvas) {
        if (this.mStickyItemView == null) {
            return;
        }
        int save = canvas.save();
        canvas.translate(0.0f, -this.mStickyItemViewMarginTop);
        this.mStickyItemView.draw(canvas);
        canvas.restoreToCount(save);
    }
}
