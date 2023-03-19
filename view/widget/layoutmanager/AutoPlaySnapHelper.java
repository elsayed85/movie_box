package com.movieboxpro.android.view.widget.layoutmanager;

import android.os.Handler;
import android.os.Looper;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes3.dex */
class AutoPlaySnapHelper extends CenterSnapHelper {
    static final int LEFT = 1;
    static final int RIGHT = 2;
    static final int TIME_INTERVAL = 2000;
    private Runnable autoPlayRunnable;
    private int direction;
    private Handler handler;
    private boolean runnableAdded;
    private int timeInterval;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoPlaySnapHelper(int i, int i2) {
        checkTimeInterval(i);
        checkDirection(i2);
        this.handler = new Handler(Looper.getMainLooper());
        this.timeInterval = i;
        this.direction = i2;
    }

    @Override // com.movieboxpro.android.view.widget.layoutmanager.CenterSnapHelper
    public void attachToRecyclerView(RecyclerView recyclerView) throws IllegalStateException {
        if (this.mRecyclerView == recyclerView) {
            return;
        }
        if (this.mRecyclerView != null) {
            destroyCallbacks();
        }
        this.mRecyclerView = recyclerView;
        if (this.mRecyclerView != null) {
            final RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
            if (layoutManager instanceof ViewPagerLayoutManager) {
                setupCallbacks();
                this.mGravityScroller = new Scroller(this.mRecyclerView.getContext(), new DecelerateInterpolator());
                ViewPagerLayoutManager viewPagerLayoutManager = (ViewPagerLayoutManager) layoutManager;
                snapToCenterView(viewPagerLayoutManager, viewPagerLayoutManager.onPageChangeListener);
                viewPagerLayoutManager.setInfinite(true);
                Runnable runnable = new Runnable() { // from class: com.movieboxpro.android.view.widget.layoutmanager.AutoPlaySnapHelper.1
                    @Override // java.lang.Runnable
                    public void run() {
                        int currentPositionOffset = ((ViewPagerLayoutManager) layoutManager).getCurrentPositionOffset() * (((ViewPagerLayoutManager) layoutManager).getReverseLayout() ? -1 : 1);
                        ScrollHelper.smoothScrollToPosition(AutoPlaySnapHelper.this.mRecyclerView, (ViewPagerLayoutManager) layoutManager, AutoPlaySnapHelper.this.direction == 2 ? currentPositionOffset + 1 : currentPositionOffset - 1);
                        AutoPlaySnapHelper.this.handler.postDelayed(AutoPlaySnapHelper.this.autoPlayRunnable, AutoPlaySnapHelper.this.timeInterval);
                    }
                };
                this.autoPlayRunnable = runnable;
                this.handler.postDelayed(runnable, this.timeInterval);
                this.runnableAdded = true;
            }
        }
    }

    public void detach() {
        super.destroyCallbacks();
        if (this.runnableAdded) {
            this.handler.removeCallbacks(this.autoPlayRunnable);
            this.runnableAdded = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.movieboxpro.android.view.widget.layoutmanager.CenterSnapHelper
    public void destroyCallbacks() {
        super.destroyCallbacks();
        if (this.runnableAdded) {
            this.handler.removeCallbacks(this.autoPlayRunnable);
            this.runnableAdded = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void pause() {
        if (this.runnableAdded) {
            this.handler.removeCallbacks(this.autoPlayRunnable);
            this.runnableAdded = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void start() {
        if (this.runnableAdded) {
            return;
        }
        this.handler.postDelayed(this.autoPlayRunnable, this.timeInterval);
        this.runnableAdded = true;
    }

    void setTimeInterval(int i) {
        checkTimeInterval(i);
        this.timeInterval = i;
    }

    void setDirection(int i) {
        checkDirection(i);
        this.direction = i;
    }

    private void checkDirection(int i) {
        if (i != 1 && i != 2) {
            throw new IllegalArgumentException("direction should be one of left or right");
        }
    }

    private void checkTimeInterval(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("time interval should greater than 0");
        }
    }
}
