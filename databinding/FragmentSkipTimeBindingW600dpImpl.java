package com.movieboxpro.android.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.tabs.TabLayout;
import com.movieboxpro.android.R;
import com.movieboxpro.android.view.widget.NoScrollViewPager;
/* loaded from: classes3.dex */
public class FragmentSkipTimeBindingW600dpImpl extends FragmentSkipTimeBinding {
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
        sparseIntArray.put(R.id.tabLayout, 1);
        sViewsWithIds.put(R.id.ivClose, 2);
        sViewsWithIds.put(R.id.viewPager, 3);
        sViewsWithIds.put(R.id.tvDisableHint, 4);
        sViewsWithIds.put(R.id.llEnableSkip, 5);
        sViewsWithIds.put(R.id.ivCheck, 6);
        sViewsWithIds.put(R.id.tvOk, 7);
    }

    public FragmentSkipTimeBindingW600dpImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 8, sIncludes, sViewsWithIds));
    }

    private FragmentSkipTimeBindingW600dpImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (AppCompatImageView) objArr[6], (AppCompatImageView) objArr[2], (LinearLayout) objArr[5], (TabLayout) objArr[1], (TextView) objArr[4], (TextView) objArr[7], (NoScrollViewPager) objArr[3]);
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
