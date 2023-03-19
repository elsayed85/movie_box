package com.movieboxpro.android.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.movieboxpro.android.R;
import com.movieboxpro.android.view.widget.LoadingStatusView;
/* loaded from: classes3.dex */
public class AddTvWatchPlanPopLayoutBindingImpl extends AddTvWatchPlanPopLayoutBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, Object obj) {
        return true;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.llWaiting, 1);
        sViewsWithIds.put(R.id.tvWaitingTime, 2);
        sViewsWithIds.put(R.id.tvWaitingAdded, 3);
        sViewsWithIds.put(R.id.llWatched, 4);
        sViewsWithIds.put(R.id.tvWatchedTime, 5);
        sViewsWithIds.put(R.id.tvWatchedAdded, 6);
        sViewsWithIds.put(R.id.loadingView, 7);
        sViewsWithIds.put(R.id.recyclerView, 8);
    }

    public AddTvWatchPlanPopLayoutBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 9, sIncludes, sViewsWithIds));
    }

    private AddTvWatchPlanPopLayoutBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (LinearLayout) objArr[1], (LinearLayout) objArr[4], (LoadingStatusView) objArr[7], (RecyclerView) objArr[8], (TextView) objArr[3], (TextView) objArr[2], (TextView) objArr[6], (TextView) objArr[5]);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) objArr[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.mDirtyFlags != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0L;
        }
    }
}
