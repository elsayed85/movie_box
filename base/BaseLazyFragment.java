package com.movieboxpro.android.base;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import java.util.List;
/* loaded from: classes.dex */
public abstract class BaseLazyFragment extends Fragment {
    private boolean currentVisibleState;
    private boolean isViewCreated;
    protected boolean isVisibleToUser;
    private boolean mIsFirstVisible;

    public BaseLazyFragment(int i) {
        super(i);
        this.mIsFirstVisible = true;
        this.isViewCreated = false;
        this.currentVisibleState = false;
    }

    public BaseLazyFragment() {
        this.mIsFirstVisible = true;
        this.isViewCreated = false;
        this.currentVisibleState = false;
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (this.isViewCreated) {
            if (z && !this.currentVisibleState) {
                dispatchUserVisibleHint(true);
            } else if (z || !this.currentVisibleState) {
            } else {
                dispatchUserVisibleHint(false);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.isViewCreated = true;
        if (isHidden() || !getUserVisibleHint()) {
            return;
        }
        dispatchUserVisibleHint(true);
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            dispatchUserVisibleHint(false);
        } else {
            dispatchUserVisibleHint(true);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.mIsFirstVisible || isHidden() || this.currentVisibleState || !getUserVisibleHint()) {
            return;
        }
        dispatchUserVisibleHint(true);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        if (this.currentVisibleState && getUserVisibleHint()) {
            dispatchUserVisibleHint(false);
        }
    }

    private void dispatchUserVisibleHint(boolean z) {
        if ((z && isParentInvisible()) || this.currentVisibleState == z) {
            return;
        }
        this.currentVisibleState = z;
        if (z) {
            if (this.mIsFirstVisible) {
                this.mIsFirstVisible = false;
                onFragmentFirstVisible();
            } else {
                onFragmentResumeNoFirst();
            }
            onFragmentResume();
            dispatchChildVisibleState(true);
            return;
        }
        dispatchChildVisibleState(false);
        onFragmentPause();
    }

    private boolean isParentInvisible() {
        Fragment parentFragment = getParentFragment();
        if (parentFragment instanceof BaseLazyFragment) {
            return !((BaseLazyFragment) parentFragment).isSupportVisible();
        }
        return false;
    }

    private boolean isSupportVisible() {
        return this.currentVisibleState;
    }

    private void dispatchChildVisibleState(boolean z) {
        List<Fragment> fragments = getChildFragmentManager().getFragments();
        if (fragments.isEmpty()) {
            return;
        }
        for (Fragment fragment : fragments) {
            if ((fragment instanceof BaseLazyFragment) && !fragment.isHidden() && fragment.getUserVisibleHint()) {
                BaseLazyFragment baseLazyFragment = (BaseLazyFragment) fragment;
                if (baseLazyFragment.isViewCreated) {
                    baseLazyFragment.dispatchUserVisibleHint(z);
                }
            }
        }
    }

    public void onFragmentFirstVisible() {
        this.isVisibleToUser = true;
    }

    public void onFragmentResume() {
        this.isVisibleToUser = true;
    }

    public void onFragmentResumeNoFirst() {
        this.isVisibleToUser = true;
    }

    public void onFragmentPause() {
        this.isVisibleToUser = false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.isViewCreated = false;
        this.mIsFirstVisible = true;
        this.isVisibleToUser = false;
    }
}
