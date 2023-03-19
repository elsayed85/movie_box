package com.movieboxpro.android.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.movieboxpro.android.R;
import de.hdodenhof.circleimageview.CircleImageView;
/* loaded from: classes3.dex */
public class ActivityAccount2BindingImpl extends ActivityAccount2Binding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, Object obj) {
        return true;
    }

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(20);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"common_title_bar_layout"}, new int[]{1}, new int[]{R.layout.common_title_bar_layout});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.llAvatar, 2);
        sViewsWithIds.put(R.id.ivAvatar, 3);
        sViewsWithIds.put(R.id.llName, 4);
        sViewsWithIds.put(R.id.tvUsername, 5);
        sViewsWithIds.put(R.id.llUid, 6);
        sViewsWithIds.put(R.id.tvUid, 7);
        sViewsWithIds.put(R.id.llEmail, 8);
        sViewsWithIds.put(R.id.tvEmail, 9);
        sViewsWithIds.put(R.id.llSubscriptions, 10);
        sViewsWithIds.put(R.id.tvVipExpire, 11);
        sViewsWithIds.put(R.id.ivVipArrow, 12);
        sViewsWithIds.put(R.id.llFamilyPlan, 13);
        sViewsWithIds.put(R.id.tvFamilyNum, 14);
        sViewsWithIds.put(R.id.llBlockList, 15);
        sViewsWithIds.put(R.id.llMyOrder, 16);
        sViewsWithIds.put(R.id.llAccountSecurity, 17);
        sViewsWithIds.put(R.id.llClearWatched, 18);
        sViewsWithIds.put(R.id.tvSignOut, 19);
    }

    public ActivityAccount2BindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 20, sIncludes, sViewsWithIds));
    }

    private ActivityAccount2BindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (CircleImageView) objArr[3], (AppCompatImageView) objArr[12], (LinearLayout) objArr[17], (LinearLayout) objArr[2], (LinearLayout) objArr[15], (LinearLayout) objArr[18], (LinearLayout) objArr[8], (LinearLayout) objArr[13], (LinearLayout) objArr[16], (LinearLayout) objArr[4], (LinearLayout) objArr[10], (LinearLayout) objArr[6], (CommonTitleBarLayoutBinding) objArr[1], (TextView) objArr[9], (TextView) objArr[14], (TextView) objArr[19], (TextView) objArr[7], (TextView) objArr[5], (TextView) objArr[11]);
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
