package com.movieboxpro.android.view.widget.TouchHelper;

import android.graphics.Canvas;
import android.util.Log;
import android.view.ViewGroup;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.movieboxpro.android.R;
import com.movieboxpro.android.view.widget.adapter.DeviceAdapter;
/* loaded from: classes3.dex */
public class DeviceTouchHelperCallback extends ItemTouchHelper.Callback {
    private double ICON_MAX_SIZE = 80.0d;
    private int fixedWidth = 220;
    private ItemTouchMoveListener moveListener;

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean isLongPressDragEnabled() {
        return true;
    }

    public DeviceTouchHelperCallback(ItemTouchMoveListener itemTouchMoveListener) {
        this.moveListener = itemTouchMoveListener;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(3, 4);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        if (viewHolder.getItemViewType() != viewHolder2.getItemViewType()) {
            return false;
        }
        return this.moveListener.onItemMove(viewHolder.getAdapterPosition(), viewHolder2.getAdapterPosition());
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
        this.moveListener.onItemRemove(viewHolder.getAdapterPosition());
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i) {
        if (i != 0) {
            viewHolder.itemView.setBackgroundColor(viewHolder.itemView.getContext().getResources().getColor(R.color.color_text_dark));
        }
        super.onSelectedChanged(viewHolder, i);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        viewHolder.itemView.setBackgroundColor(viewHolder.itemView.getContext().getResources().getColor(R.color.color_text_dark));
        viewHolder.itemView.setAlpha(1.0f);
        DeviceAdapter.Item1ViewHolder item1ViewHolder = (DeviceAdapter.Item1ViewHolder) viewHolder;
        viewHolder.itemView.setScrollX(0);
        super.clearView(recyclerView, viewHolder);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onChildDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f, float f2, int i, boolean z) {
        DeviceAdapter.Item1ViewHolder item1ViewHolder = (DeviceAdapter.Item1ViewHolder) viewHolder;
        if (i == 1) {
            Log.d("http", "4444");
            if (Math.abs(f) <= getSlideLimitation(viewHolder)) {
                viewHolder.itemView.scrollTo(-((int) f), 0);
                return;
            } else {
                int i2 = (Math.abs(f) > (recyclerView.getWidth() / 2) ? 1 : (Math.abs(f) == (recyclerView.getWidth() / 2) ? 0 : -1));
                return;
            }
        }
        super.onChildDraw(canvas, recyclerView, viewHolder, f, f2, i, z);
    }

    public int getSlideLimitation(RecyclerView.ViewHolder viewHolder) {
        ViewGroup viewGroup = (ViewGroup) viewHolder.itemView;
        return viewGroup.getChildAt(viewGroup.getChildCount() - 1).getLayoutParams().width;
    }
}
