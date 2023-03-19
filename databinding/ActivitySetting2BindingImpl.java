package com.movieboxpro.android.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.movieboxpro.android.R;
import com.movieboxpro.android.view.widget.CustomSwitchView;
import de.hdodenhof.circleimageview.CircleImageView;
/* loaded from: classes3.dex */
public class ActivitySetting2BindingImpl extends ActivitySetting2Binding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, Object obj) {
        return true;
    }

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(30);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"common_title_bar_layout"}, new int[]{1}, new int[]{R.layout.common_title_bar_layout});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.clInfo, 2);
        sViewsWithIds.put(R.id.ivAvatar, 3);
        sViewsWithIds.put(R.id.ivArrow, 4);
        sViewsWithIds.put(R.id.tvNickname, 5);
        sViewsWithIds.put(R.id.ivVip, 6);
        sViewsWithIds.put(R.id.tvDesc, 7);
        sViewsWithIds.put(R.id.llPlayer, 8);
        sViewsWithIds.put(R.id.tvPlayer, 9);
        sViewsWithIds.put(R.id.switchAutoPlayNext, 10);
        sViewsWithIds.put(R.id.switchAutoSelectSubtitle, 11);
        sViewsWithIds.put(R.id.switchRememberOrg, 12);
        sViewsWithIds.put(R.id.switchFullScreen, 13);
        sViewsWithIds.put(R.id.llLanguage, 14);
        sViewsWithIds.put(R.id.tvLanguage, 15);
        sViewsWithIds.put(R.id.switchBlack, 16);
        sViewsWithIds.put(R.id.switchHideMovieList, 17);
        sViewsWithIds.put(R.id.clDownloadLocation, 18);
        sViewsWithIds.put(R.id.tvDownloadLocation, 19);
        sViewsWithIds.put(R.id.tvDownloadDirName, 20);
        sViewsWithIds.put(R.id.tvDownloadSpace, 21);
        sViewsWithIds.put(R.id.ivArrow2, 22);
        sViewsWithIds.put(R.id.llSmartDownload, 23);
        sViewsWithIds.put(R.id.tvSmartDownload, 24);
        sViewsWithIds.put(R.id.llEmptyCache, 25);
        sViewsWithIds.put(R.id.switchPush, 26);
        sViewsWithIds.put(R.id.switchEmail, 27);
        sViewsWithIds.put(R.id.switchDownloadCellular, 28);
        sViewsWithIds.put(R.id.llSpeedTest, 29);
    }

    public ActivitySetting2BindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 30, sIncludes, sViewsWithIds));
    }

    private ActivitySetting2BindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (ConstraintLayout) objArr[18], (ConstraintLayout) objArr[2], (AppCompatImageView) objArr[4], (AppCompatImageView) objArr[22], (CircleImageView) objArr[3], (AppCompatImageView) objArr[6], (LinearLayout) objArr[25], (LinearLayout) objArr[14], (LinearLayout) objArr[8], (LinearLayout) objArr[23], (LinearLayout) objArr[29], (CustomSwitchView) objArr[10], (CustomSwitchView) objArr[11], (CustomSwitchView) objArr[16], (CustomSwitchView) objArr[28], (CustomSwitchView) objArr[27], (CustomSwitchView) objArr[13], (CustomSwitchView) objArr[17], (CustomSwitchView) objArr[26], (CustomSwitchView) objArr[12], (CommonTitleBarLayoutBinding) objArr[1], (TextView) objArr[7], (TextView) objArr[20], (TextView) objArr[19], (TextView) objArr[21], (TextView) objArr[15], (TextView) objArr[5], (TextView) objArr[9], (TextView) objArr[24]);
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
