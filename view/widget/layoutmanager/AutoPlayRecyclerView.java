package com.movieboxpro.android.view.widget.layoutmanager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes3.dex */
public class AutoPlayRecyclerView extends RecyclerView {
    private AutoPlaySnapHelper autoPlaySnapHelper;

    public AutoPlayRecyclerView(Context context) {
        this(context, null);
    }

    public AutoPlayRecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AutoPlayRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.autoPlaySnapHelper = new AutoPlaySnapHelper(2000, 2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        AutoPlaySnapHelper autoPlaySnapHelper;
        boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        int action = motionEvent.getAction();
        if (action == 0) {
            AutoPlaySnapHelper autoPlaySnapHelper2 = this.autoPlaySnapHelper;
            if (autoPlaySnapHelper2 != null) {
                autoPlaySnapHelper2.pause();
            }
        } else if (action == 1 && (autoPlaySnapHelper = this.autoPlaySnapHelper) != null) {
            autoPlaySnapHelper.start();
        }
        return dispatchTouchEvent;
    }

    public void start() {
        this.autoPlaySnapHelper.start();
    }

    public void pause() {
        this.autoPlaySnapHelper.pause();
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        super.setLayoutManager(layoutManager);
        this.autoPlaySnapHelper.attachToRecyclerView(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        AutoPlaySnapHelper autoPlaySnapHelper = this.autoPlaySnapHelper;
        if (autoPlaySnapHelper != null) {
            autoPlaySnapHelper.detach();
        }
        super.onDetachedFromWindow();
    }
}
