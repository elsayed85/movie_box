package com.movieboxpro.android.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.mediarouter.app.MediaRouteButton;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public class ActivitySuperChildModeMainBindingImpl extends ActivitySuperChildModeMainBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

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
        sparseIntArray.put(R.id.homelist_view_title, 1);
        sViewsWithIds.put(R.id.homelist_view_logo, 2);
        sViewsWithIds.put(R.id.flCast, 3);
        sViewsWithIds.put(R.id.media_route_button, 4);
        sViewsWithIds.put(R.id.flDrawer, 5);
        sViewsWithIds.put(R.id.homelist_view_more, 6);
        sViewsWithIds.put(R.id.dot, 7);
        sViewsWithIds.put(R.id.flMode, 8);
        sViewsWithIds.put(R.id.textView, 9);
        sViewsWithIds.put(R.id.flContainer, 10);
        sViewsWithIds.put(R.id.drawerFrameLayout, 11);
    }

    public ActivitySuperChildModeMainBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 12, sIncludes, sViewsWithIds));
    }

    private ActivitySuperChildModeMainBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (View) objArr[7], (FrameLayout) objArr[11], (DrawerLayout) objArr[0], (FrameLayout) objArr[3], (FrameLayout) objArr[10], (FrameLayout) objArr[5], (FrameLayout) objArr[8], (ImageView) objArr[2], (ImageView) objArr[6], (LinearLayout) objArr[1], (MediaRouteButton) objArr[4], (TextView) objArr[9]);
        this.mDirtyFlags = -1L;
        this.drawerLayout.setTag(null);
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
