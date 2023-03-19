package com.movieboxpro.android.view.widget.layoutmanager;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.Interpolator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
/* loaded from: classes3.dex */
public abstract class ViewPagerLayoutManager extends LinearLayoutManager {
    public static final int DETERMINE_BY_MAX_AND_MIN = -1;
    private static final int DIRECTION_BACKWARD = 1;
    private static final int DIRECTION_FORWARD = 0;
    private static final int DIRECTION_NO_WHERE = -1;
    public static final int HORIZONTAL = 0;
    protected static final int INVALID_SIZE = Integer.MAX_VALUE;
    public static final int VERTICAL = 1;
    private View currentFocusView;
    protected int mDecoratedMeasurement;
    protected int mDecoratedMeasurementInOther;
    private int mDistanceToBottom;
    private boolean mEnableBringCenterToFront;
    private boolean mInfinite;
    protected float mInterval;
    private int mLeftItems;
    private int mMaxVisibleItemCount;
    protected float mOffset;
    int mOrientation;
    protected OrientationHelper mOrientationHelper;
    private SavedState mPendingSavedState;
    private int mPendingScrollPosition;
    private boolean mRecycleChildrenOnDetach;
    private boolean mReverseLayout;
    private int mRightItems;
    private boolean mShouldReverseLayout;
    private Interpolator mSmoothScrollInterpolator;
    private boolean mSmoothScrollbarEnabled;
    protected int mSpaceInOther;
    protected int mSpaceMain;
    OnPageChangeListener onPageChangeListener;
    private SparseArray<View> positionCache;

    /* loaded from: classes3.dex */
    public interface OnPageChangeListener {
        void onPageScrollStateChanged(int i);

        void onPageSelected(int i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public float getDistanceRatio() {
        return 1.0f;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public View onFocusSearchFailed(View view, int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return null;
    }

    protected abstract float setInterval();

    protected abstract void setItemViewProperty(View view, float f);

    protected void setUp() {
    }

    protected float setViewElevation(View view, float f) {
        return 0.0f;
    }

    public ViewPagerLayoutManager(Context context) {
        this(context, 0, false);
    }

    public ViewPagerLayoutManager(Context context, int i, boolean z) {
        super(context);
        this.positionCache = new SparseArray<>();
        this.mReverseLayout = false;
        this.mShouldReverseLayout = false;
        this.mSmoothScrollbarEnabled = true;
        this.mPendingScrollPosition = -1;
        this.mPendingSavedState = null;
        this.mInfinite = false;
        this.mMaxVisibleItemCount = -1;
        this.mDistanceToBottom = Integer.MAX_VALUE;
        setOrientation(i);
        setReverseLayout(z);
        setAutoMeasureEnabled(true);
        setItemPrefetchEnabled(false);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public boolean getRecycleChildrenOnDetach() {
        return this.mRecycleChildrenOnDetach;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void setRecycleChildrenOnDetach(boolean z) {
        this.mRecycleChildrenOnDetach = z;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
        if (this.mRecycleChildrenOnDetach) {
            removeAndRecycleAllViews(recycler);
            recycler.clear();
        }
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public Parcelable onSaveInstanceState() {
        if (this.mPendingSavedState != null) {
            return new SavedState(this.mPendingSavedState);
        }
        SavedState savedState = new SavedState();
        savedState.position = this.mPendingScrollPosition;
        savedState.offset = this.mOffset;
        savedState.isReverseLayout = this.mShouldReverseLayout;
        return savedState;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.mPendingSavedState = new SavedState((SavedState) parcelable);
            requestLayout();
        }
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollHorizontally() {
        return this.mOrientation == 0;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return this.mOrientation == 1;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public int getOrientation() {
        return this.mOrientation;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void setOrientation(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("invalid orientation:" + i);
        }
        assertNotInLayoutOrScroll(null);
        if (i == this.mOrientation) {
            return;
        }
        this.mOrientation = i;
        this.mOrientationHelper = null;
        this.mDistanceToBottom = Integer.MAX_VALUE;
        removeAllViews();
    }

    public int getMaxVisibleItemCount() {
        return this.mMaxVisibleItemCount;
    }

    public void setMaxVisibleItemCount(int i) {
        assertNotInLayoutOrScroll(null);
        if (this.mMaxVisibleItemCount == i) {
            return;
        }
        this.mMaxVisibleItemCount = i;
        removeAllViews();
    }

    private void resolveShouldLayoutReverse() {
        if (this.mOrientation == 1 || !isLayoutRTL()) {
            this.mShouldReverseLayout = this.mReverseLayout;
        } else {
            this.mShouldReverseLayout = !this.mReverseLayout;
        }
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public boolean getReverseLayout() {
        return this.mReverseLayout;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void setReverseLayout(boolean z) {
        assertNotInLayoutOrScroll(null);
        if (z == this.mReverseLayout) {
            return;
        }
        this.mReverseLayout = z;
        removeAllViews();
    }

    public void setSmoothScrollInterpolator(Interpolator interpolator) {
        this.mSmoothScrollInterpolator = interpolator;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        int offsetToPosition;
        int i2;
        if (this.mInfinite) {
            int currentPosition = getCurrentPosition();
            int itemCount = getItemCount();
            if (i < currentPosition) {
                int i3 = currentPosition - i;
                int i4 = (itemCount - currentPosition) + i;
                i2 = i3 < i4 ? currentPosition - i3 : currentPosition + i4;
            } else {
                int i5 = i - currentPosition;
                int i6 = (itemCount + currentPosition) - i;
                i2 = i5 < i6 ? currentPosition + i5 : currentPosition - i6;
            }
            offsetToPosition = getOffsetToPosition(i2);
        } else {
            offsetToPosition = getOffsetToPosition(i);
        }
        if (this.mOrientation == 1) {
            recyclerView.smoothScrollBy(0, offsetToPosition, this.mSmoothScrollInterpolator);
        } else {
            recyclerView.smoothScrollBy(offsetToPosition, 0, this.mSmoothScrollInterpolator);
        }
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        float f;
        float f2;
        if (state.getItemCount() == 0) {
            removeAndRecycleAllViews(recycler);
            this.mOffset = 0.0f;
            return;
        }
        ensureLayoutState();
        resolveShouldLayoutReverse();
        View measureView = getMeasureView(recycler, state, 0);
        if (measureView == null) {
            removeAndRecycleAllViews(recycler);
            this.mOffset = 0.0f;
            return;
        }
        measureChildWithMargins(measureView, 0, 0);
        this.mDecoratedMeasurement = this.mOrientationHelper.getDecoratedMeasurement(measureView);
        this.mDecoratedMeasurementInOther = this.mOrientationHelper.getDecoratedMeasurementInOther(measureView);
        this.mSpaceMain = (this.mOrientationHelper.getTotalSpace() - this.mDecoratedMeasurement) / 2;
        if (this.mDistanceToBottom == Integer.MAX_VALUE) {
            this.mSpaceInOther = (this.mOrientationHelper.getTotalSpaceInOther() - this.mDecoratedMeasurementInOther) / 2;
        } else {
            this.mSpaceInOther = (this.mOrientationHelper.getTotalSpaceInOther() - this.mDecoratedMeasurementInOther) - this.mDistanceToBottom;
        }
        this.mInterval = setInterval();
        setUp();
        if (this.mInterval == 0.0f) {
            this.mLeftItems = 1;
            this.mRightItems = 1;
        } else {
            this.mLeftItems = ((int) Math.abs(minRemoveOffset() / this.mInterval)) + 1;
            this.mRightItems = ((int) Math.abs(maxRemoveOffset() / this.mInterval)) + 1;
        }
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null) {
            this.mShouldReverseLayout = savedState.isReverseLayout;
            this.mPendingScrollPosition = this.mPendingSavedState.position;
            this.mOffset = this.mPendingSavedState.offset;
        }
        int i = this.mPendingScrollPosition;
        if (i != -1) {
            if (this.mShouldReverseLayout) {
                f = i;
                f2 = -this.mInterval;
            } else {
                f = i;
                f2 = this.mInterval;
            }
            this.mOffset = f * f2;
        }
        layoutItems(recycler);
    }

    private View getMeasureView(RecyclerView.Recycler recycler, RecyclerView.State state, int i) {
        if (i >= state.getItemCount() || i < 0) {
            return null;
        }
        try {
            return recycler.getViewForPosition(i);
        } catch (Exception unused) {
            return getMeasureView(recycler, state, i + 1);
        }
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        this.mPendingSavedState = null;
        this.mPendingScrollPosition = -1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean onAddFocusables(RecyclerView recyclerView, ArrayList<View> arrayList, int i, int i2) {
        int currentPosition = getCurrentPosition();
        View findViewByPosition = findViewByPosition(currentPosition);
        if (findViewByPosition == null) {
            return true;
        }
        if (recyclerView.hasFocus()) {
            int movement = getMovement(i);
            if (movement != -1) {
                ScrollHelper.smoothScrollToPosition(recyclerView, this, movement == 1 ? currentPosition - 1 : currentPosition + 1);
            }
        } else {
            findViewByPosition.addFocusables(arrayList, i, i2);
        }
        return true;
    }

    private int getMovement(int i) {
        if (this.mOrientation == 1) {
            if (i == 33) {
                return !this.mShouldReverseLayout ? 1 : 0;
            }
            if (i == 130) {
                return this.mShouldReverseLayout ? 1 : 0;
            }
            return -1;
        } else if (i == 17) {
            return !this.mShouldReverseLayout ? 1 : 0;
        } else {
            if (i == 66) {
                return this.mShouldReverseLayout ? 1 : 0;
            }
            return -1;
        }
    }

    void ensureLayoutState() {
        if (this.mOrientationHelper == null) {
            this.mOrientationHelper = OrientationHelper.createOrientationHelper(this, this.mOrientation);
        }
    }

    private float getProperty(int i) {
        return i * (this.mShouldReverseLayout ? -this.mInterval : this.mInterval);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onAdapterChanged(RecyclerView.Adapter adapter, RecyclerView.Adapter adapter2) {
        removeAllViews();
        this.mOffset = 0.0f;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void scrollToPosition(int i) {
        if (this.mInfinite || (i >= 0 && i < getItemCount())) {
            this.mPendingScrollPosition = i;
            this.mOffset = i * (this.mShouldReverseLayout ? -this.mInterval : this.mInterval);
            requestLayout();
        }
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollRange(RecyclerView.State state) {
        return computeScrollRange();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollRange(RecyclerView.State state) {
        return computeScrollRange();
    }

    private int computeScrollOffset() {
        if (getChildCount() == 0) {
            return 0;
        }
        if (!this.mSmoothScrollbarEnabled) {
            return !this.mShouldReverseLayout ? getCurrentPosition() : (getItemCount() - getCurrentPosition()) - 1;
        }
        float offsetOfRightAdapterPosition = getOffsetOfRightAdapterPosition();
        return !this.mShouldReverseLayout ? (int) offsetOfRightAdapterPosition : (int) (((getItemCount() - 1) * this.mInterval) + offsetOfRightAdapterPosition);
    }

    private int computeScrollExtent() {
        if (getChildCount() == 0) {
            return 0;
        }
        if (this.mSmoothScrollbarEnabled) {
            return (int) this.mInterval;
        }
        return 1;
    }

    private int computeScrollRange() {
        if (getChildCount() == 0) {
            return 0;
        }
        if (!this.mSmoothScrollbarEnabled) {
            return getItemCount();
        }
        return (int) (getItemCount() * this.mInterval);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mOrientation == 1) {
            return 0;
        }
        return scrollBy(i, recycler, state);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mOrientation == 0) {
            return 0;
        }
        return scrollBy(i, recycler, state);
    }

    private int scrollBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        ensureLayoutState();
        float f = i;
        float distanceRatio = f / getDistanceRatio();
        if (Math.abs(distanceRatio) < 1.0E-8f) {
            return 0;
        }
        float f2 = this.mOffset + distanceRatio;
        if (!this.mInfinite && f2 < getMinOffset()) {
            i = (int) (f - ((f2 - getMinOffset()) * getDistanceRatio()));
        } else if (!this.mInfinite && f2 > getMaxOffset()) {
            i = (int) ((getMaxOffset() - this.mOffset) * getDistanceRatio());
        }
        this.mOffset += i / getDistanceRatio();
        layoutItems(recycler);
        return i;
    }

    private void layoutItems(RecyclerView.Recycler recycler) {
        int i;
        int i2;
        int i3;
        detachAndScrapAttachedViews(recycler);
        this.positionCache.clear();
        int itemCount = getItemCount();
        if (itemCount == 0) {
            return;
        }
        int currentPositionOffset = this.mShouldReverseLayout ? -getCurrentPositionOffset() : getCurrentPositionOffset();
        int i4 = currentPositionOffset - this.mLeftItems;
        int i5 = this.mRightItems + currentPositionOffset;
        if (useMaxVisibleCount()) {
            if (this.mMaxVisibleItemCount % 2 == 0) {
                i2 = this.mMaxVisibleItemCount / 2;
                i3 = (currentPositionOffset - i2) + 1;
            } else {
                i2 = (this.mMaxVisibleItemCount - 1) / 2;
                i3 = currentPositionOffset - i2;
            }
            i5 = 1 + i2 + currentPositionOffset;
            i4 = i3;
        }
        if (!this.mInfinite) {
            if (i4 < 0) {
                if (useMaxVisibleCount()) {
                    i5 = this.mMaxVisibleItemCount;
                }
                i4 = 0;
            }
            if (i5 > itemCount) {
                i5 = itemCount;
            }
        }
        float f = Float.MIN_VALUE;
        while (i4 < i5) {
            if (useMaxVisibleCount() || !removeCondition(getProperty(i4) - this.mOffset)) {
                if (i4 >= itemCount) {
                    i = i4 % itemCount;
                } else if (i4 < 0) {
                    int i6 = (-i4) % itemCount;
                    if (i6 == 0) {
                        i6 = itemCount;
                    }
                    i = itemCount - i6;
                } else {
                    i = i4;
                }
                View viewForPosition = recycler.getViewForPosition(i);
                measureChildWithMargins(viewForPosition, 0, 0);
                resetViewProperty(viewForPosition);
                float property = getProperty(i4) - this.mOffset;
                layoutScrap(viewForPosition, property);
                float viewElevation = this.mEnableBringCenterToFront ? setViewElevation(viewForPosition, property) : i;
                if (viewElevation > f) {
                    addView(viewForPosition);
                } else {
                    addView(viewForPosition, 0);
                }
                if (i4 == currentPositionOffset) {
                    this.currentFocusView = viewForPosition;
                }
                this.positionCache.put(i4, viewForPosition);
                f = viewElevation;
            }
            i4++;
        }
        this.currentFocusView.requestFocus();
    }

    private boolean useMaxVisibleCount() {
        return this.mMaxVisibleItemCount != -1;
    }

    private boolean removeCondition(float f) {
        return f > maxRemoveOffset() || f < minRemoveOffset();
    }

    private void resetViewProperty(View view) {
        view.setRotation(0.0f);
        view.setRotationY(0.0f);
        view.setRotationX(0.0f);
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        view.setAlpha(1.0f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getMaxOffset() {
        if (this.mShouldReverseLayout) {
            return 0.0f;
        }
        return (getItemCount() - 1) * this.mInterval;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getMinOffset() {
        if (this.mShouldReverseLayout) {
            return (-(getItemCount() - 1)) * this.mInterval;
        }
        return 0.0f;
    }

    private void layoutScrap(View view, float f) {
        int calItemLeft = calItemLeft(view, f);
        int calItemTop = calItemTop(view, f);
        if (this.mOrientation == 1) {
            int i = this.mSpaceInOther;
            int i2 = this.mSpaceMain;
            layoutDecorated(view, i + calItemLeft, i2 + calItemTop, i + calItemLeft + this.mDecoratedMeasurementInOther, i2 + calItemTop + this.mDecoratedMeasurement);
        } else {
            int i3 = this.mSpaceMain;
            int i4 = this.mSpaceInOther;
            layoutDecorated(view, i3 + calItemLeft, i4 + calItemTop, i3 + calItemLeft + this.mDecoratedMeasurement, i4 + calItemTop + this.mDecoratedMeasurementInOther);
        }
        setItemViewProperty(view, f);
    }

    protected int calItemLeft(View view, float f) {
        if (this.mOrientation == 1) {
            return 0;
        }
        return (int) f;
    }

    protected int calItemTop(View view, float f) {
        if (this.mOrientation == 1) {
            return (int) f;
        }
        return 0;
    }

    protected float maxRemoveOffset() {
        return this.mOrientationHelper.getTotalSpace() - this.mSpaceMain;
    }

    protected float minRemoveOffset() {
        return ((-this.mDecoratedMeasurement) - this.mOrientationHelper.getStartAfterPadding()) - this.mSpaceMain;
    }

    public int getCurrentPosition() {
        int itemCount;
        if (getItemCount() == 0) {
            return 0;
        }
        int currentPositionOffset = getCurrentPositionOffset();
        if (this.mInfinite) {
            if (this.mShouldReverseLayout) {
                if (currentPositionOffset > 0) {
                    itemCount = getItemCount() - (currentPositionOffset % getItemCount());
                } else {
                    itemCount = (-currentPositionOffset) % getItemCount();
                }
            } else if (currentPositionOffset >= 0) {
                itemCount = currentPositionOffset % getItemCount();
            } else {
                itemCount = (currentPositionOffset % getItemCount()) + getItemCount();
            }
            if (itemCount == getItemCount()) {
                return 0;
            }
            return itemCount;
        }
        return Math.abs(currentPositionOffset);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public View findViewByPosition(int i) {
        int itemCount = getItemCount();
        if (itemCount == 0) {
            return null;
        }
        for (int i2 = 0; i2 < this.positionCache.size(); i2++) {
            int keyAt = this.positionCache.keyAt(i2);
            if (keyAt >= 0) {
                if (i == keyAt % itemCount) {
                    return this.positionCache.valueAt(i2);
                }
            } else {
                int i3 = keyAt % itemCount;
                if (i3 == 0) {
                    i3 = -itemCount;
                }
                if (i3 + itemCount == i) {
                    return this.positionCache.valueAt(i2);
                }
            }
        }
        return null;
    }

    public int getLayoutPositionOfView(View view) {
        for (int i = 0; i < this.positionCache.size(); i++) {
            int keyAt = this.positionCache.keyAt(i);
            if (this.positionCache.get(keyAt) == view) {
                return keyAt;
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getCurrentPositionOffset() {
        float f = this.mInterval;
        if (f == 0.0f) {
            return 0;
        }
        return Math.round(this.mOffset / f);
    }

    private float getOffsetOfRightAdapterPosition() {
        if (this.mShouldReverseLayout) {
            if (this.mInfinite) {
                float f = this.mOffset;
                if (f <= 0.0f) {
                    return f % (this.mInterval * getItemCount());
                }
                float f2 = this.mInterval;
                return (getItemCount() * (-f2)) + (this.mOffset % (f2 * getItemCount()));
            }
            return this.mOffset;
        } else if (this.mInfinite) {
            float f3 = this.mOffset;
            if (f3 >= 0.0f) {
                return f3 % (this.mInterval * getItemCount());
            }
            float f4 = this.mInterval;
            return (getItemCount() * f4) + (this.mOffset % (f4 * getItemCount()));
        } else {
            return this.mOffset;
        }
    }

    public int getOffsetToCenter() {
        float currentPosition;
        float distanceRatio;
        if (this.mInfinite) {
            currentPosition = (getCurrentPositionOffset() * this.mInterval) - this.mOffset;
            distanceRatio = getDistanceRatio();
        } else {
            currentPosition = (getCurrentPosition() * (!this.mShouldReverseLayout ? this.mInterval : -this.mInterval)) - this.mOffset;
            distanceRatio = getDistanceRatio();
        }
        return (int) (currentPosition * distanceRatio);
    }

    public int getOffsetToPosition(int i) {
        float f;
        float distanceRatio;
        if (this.mInfinite) {
            f = ((getCurrentPositionOffset() + (!this.mShouldReverseLayout ? i - getCurrentPositionOffset() : (-getCurrentPositionOffset()) - i)) * this.mInterval) - this.mOffset;
            distanceRatio = getDistanceRatio();
        } else {
            f = (i * (!this.mShouldReverseLayout ? this.mInterval : -this.mInterval)) - this.mOffset;
            distanceRatio = getDistanceRatio();
        }
        return (int) (f * distanceRatio);
    }

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.onPageChangeListener = onPageChangeListener;
    }

    public void setInfinite(boolean z) {
        assertNotInLayoutOrScroll(null);
        if (z == this.mInfinite) {
            return;
        }
        this.mInfinite = z;
        requestLayout();
    }

    public boolean getInfinite() {
        return this.mInfinite;
    }

    public int getDistanceToBottom() {
        int i = this.mDistanceToBottom;
        return i == Integer.MAX_VALUE ? (this.mOrientationHelper.getTotalSpaceInOther() - this.mDecoratedMeasurementInOther) / 2 : i;
    }

    public void setDistanceToBottom(int i) {
        assertNotInLayoutOrScroll(null);
        if (this.mDistanceToBottom == i) {
            return;
        }
        this.mDistanceToBottom = i;
        removeAllViews();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void setSmoothScrollbarEnabled(boolean z) {
        this.mSmoothScrollbarEnabled = z;
    }

    public void setEnableBringCenterToFront(boolean z) {
        assertNotInLayoutOrScroll(null);
        if (this.mEnableBringCenterToFront == z) {
            return;
        }
        this.mEnableBringCenterToFront = z;
        requestLayout();
    }

    public boolean getEnableBringCenterToFront() {
        return this.mEnableBringCenterToFront;
    }

    public boolean getSmoothScrollbarEnabled() {
        return this.mSmoothScrollbarEnabled;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.movieboxpro.android.view.widget.layoutmanager.ViewPagerLayoutManager.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        boolean isReverseLayout;
        float offset;
        int position;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        SavedState() {
        }

        SavedState(Parcel parcel) {
            this.position = parcel.readInt();
            this.offset = parcel.readFloat();
            this.isReverseLayout = parcel.readInt() == 1;
        }

        public SavedState(SavedState savedState) {
            this.position = savedState.position;
            this.offset = savedState.offset;
            this.isReverseLayout = savedState.isReverseLayout;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.position);
            parcel.writeFloat(this.offset);
            parcel.writeInt(this.isReverseLayout ? 1 : 0);
        }
    }
}
