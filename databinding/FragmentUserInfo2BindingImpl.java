package com.movieboxpro.android.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableField;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.movieboxpro.android.R;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.viewmodel.UserInfoViewModel;
import de.hdodenhof.circleimageview.CircleImageView;
/* loaded from: classes3.dex */
public class FragmentUserInfo2BindingImpl extends FragmentUserInfo2Binding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.flAvatar, 11);
        sViewsWithIds.put(R.id.flName, 12);
        sViewsWithIds.put(R.id.llVip, 13);
        sViewsWithIds.put(R.id.llTv, 14);
        sViewsWithIds.put(R.id.frameLayout, 15);
        sViewsWithIds.put(R.id.llNotification, 16);
        sViewsWithIds.put(R.id.tab_count, 17);
        sViewsWithIds.put(R.id.llHistory, 18);
        sViewsWithIds.put(R.id.rvFunction, 19);
    }

    public FragmentUserInfo2BindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 20, sIncludes, sViewsWithIds));
    }

    private FragmentUserInfo2BindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 5, (ConstraintLayout) objArr[1], (FrameLayout) objArr[11], (FrameLayout) objArr[12], (FrameLayout) objArr[15], (CircleImageView) objArr[2], (ImageView) objArr[7], (ImageView) objArr[6], (ImageView) objArr[3], (LinearLayout) objArr[18], (LinearLayout) objArr[16], (LinearLayout) objArr[14], (LinearLayout) objArr[13], (RecyclerView) objArr[19], (TextView) objArr[17], (TextView) objArr[4], (TextView) objArr[10], (TextView) objArr[8], (TextView) objArr[5], (TextView) objArr[9]);
        this.mDirtyFlags = -1L;
        this.clTopInfo.setTag(null);
        this.ivAvatar.setTag(null);
        this.ivScan.setTag(null);
        this.ivSignOut.setTag(null);
        this.ivVip.setTag(null);
        LinearLayout linearLayout = (LinearLayout) objArr[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        this.tvEmail.setTag(null);
        this.tvNextBill.setTag(null);
        this.tvUid.setTag(null);
        this.tvUsername.setTag(null);
        this.tvVipExpire.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 32L;
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
    public boolean setVariable(int i, Object obj) {
        if (2 == i) {
            setVm((UserInfoViewModel) obj);
            return true;
        }
        return false;
    }

    @Override // com.movieboxpro.android.databinding.FragmentUserInfo2Binding
    public void setVm(UserInfoViewModel userInfoViewModel) {
        updateRegistration(1, userInfoViewModel);
        this.mVm = userInfoViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(2);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            return false;
                        }
                        return onChangeVmUidString((ObservableField) obj, i2);
                    }
                    return onChangeVmUserInfo((ObservableField) obj, i2);
                }
                return onChangeVmNextBillData((ObservableField) obj, i2);
            }
            return onChangeVm((UserInfoViewModel) obj, i2);
        }
        return onChangeVmUserAvatar((ObservableField) obj, i2);
    }

    private boolean onChangeVmUserAvatar(ObservableField<String> observableField, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVm(UserInfoViewModel userInfoViewModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmNextBillData(ObservableField<String> observableField, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmUserInfo(ObservableField<UserModel.UserData> observableField, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmUidString(ObservableField<String> observableField, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:131:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0146  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 466
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.databinding.FragmentUserInfo2BindingImpl.executeBindings():void");
    }
}
