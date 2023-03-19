package com.movieboxpro.android.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public class ActivityChildModeBindingImpl extends ActivityChildModeBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, Object obj) {
        return true;
    }

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(13);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"common_title_bar_layout"}, new int[]{1}, new int[]{R.layout.common_title_bar_layout});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.llChildMode, 2);
        sViewsWithIds.put(R.id.imageView, 3);
        sViewsWithIds.put(R.id.textView, 4);
        sViewsWithIds.put(R.id.ivChildMode, 5);
        sViewsWithIds.put(R.id.llSuperChildMode, 6);
        sViewsWithIds.put(R.id.imageView2, 7);
        sViewsWithIds.put(R.id.textView2, 8);
        sViewsWithIds.put(R.id.ivSuperChildMode, 9);
        sViewsWithIds.put(R.id.flTvDevice, 10);
        sViewsWithIds.put(R.id.tvTvNum, 11);
        sViewsWithIds.put(R.id.flManage, 12);
    }

    public ActivityChildModeBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 13, sIncludes, sViewsWithIds));
    }

    private ActivityChildModeBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (FrameLayout) objArr[12], (LinearLayout) objArr[10], (AppCompatImageView) objArr[3], (AppCompatImageView) objArr[7], (AppCompatImageView) objArr[5], (AppCompatImageView) objArr[9], (LinearLayout) objArr[2], (LinearLayout) objArr[6], (TextView) objArr[4], (TextView) objArr[8], (CommonTitleBarLayoutBinding) objArr[1], (TextView) objArr[11]);
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
            this.mDirtyFlags = 2L;
        }
        this.toolBar.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.toolBar.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.toolBar.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeToolBar((CommonTitleBarLayoutBinding) obj, i2);
    }

    private boolean onChangeToolBar(CommonTitleBarLayoutBinding commonTitleBarLayoutBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0L;
        }
        executeBindingsOn(this.toolBar);
    }
}
