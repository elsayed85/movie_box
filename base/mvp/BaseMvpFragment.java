package com.movieboxpro.android.base.mvp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.impl.LoadingPopupView;
import com.lxj.xpopup.interfaces.XPopupCallback;
import com.movieboxpro.android.base.BaseLazyFragment;
import com.movieboxpro.android.base.mvp.BaseContract;
import com.movieboxpro.android.base.mvp.BaseContract.BasePresenter;
import org.greenrobot.eventbus.EventBus;
/* loaded from: classes.dex */
public abstract class BaseMvpFragment<T extends BaseContract.BasePresenter> extends BaseLazyFragment implements BaseContract.BaseView {
    private LoadingPopupView loadingPopupView;
    protected T mPresenter;
    Unbinder unbinder;

    protected abstract int bindLayout();

    protected abstract T bindPresenter();

    protected boolean enableEventBus() {
        return false;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void hideLoadingView() {
    }

    protected abstract void initData();

    protected abstract void initListener();

    protected abstract void initView();

    protected abstract void loadData();

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void showEmptyState() {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void showError(String str) {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void showErrorPage() {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void showFinish(String str) {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void showLoadingView() {
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        T bindPresenter = bindPresenter();
        this.mPresenter = bindPresenter;
        if (bindPresenter != null) {
            bindPresenter.attachView(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public LoadingPopupView getLoadingPopupView(XPopupCallback xPopupCallback) {
        if (this.loadingPopupView == null) {
            this.loadingPopupView = new XPopup.Builder(getContext()).dismissOnBackPressed(true).dismissOnTouchOutside(false).setPopupCallback(xPopupCallback).hasShadowBg(true).asLoading("");
        }
        return this.loadingPopupView;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(bindLayout(), viewGroup, false);
        this.unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initData();
        initView();
        initListener();
    }

    @Override // com.movieboxpro.android.base.BaseLazyFragment
    public void onFragmentFirstVisible() {
        loadData();
    }

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void showLoading() {
        LoadingPopupView loadingPopupView = this.loadingPopupView;
        if (loadingPopupView == null || !loadingPopupView.isShow()) {
            LoadingPopupView loadingPopupView2 = this.loadingPopupView;
            if (loadingPopupView2 == null || !loadingPopupView2.isShow()) {
                if (this.loadingPopupView == null) {
                    this.loadingPopupView = new XPopup.Builder(getContext()).dismissOnBackPressed(true).dismissOnTouchOutside(false).hasShadowBg(true).asLoading("");
                }
                this.loadingPopupView.show();
            }
        }
    }

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void hideLoading() {
        LoadingPopupView loadingPopupView = this.loadingPopupView;
        if (loadingPopupView != null) {
            loadingPopupView.smartDismiss();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (!enableEventBus() || EventBus.getDefault().isRegistered(this)) {
            return;
        }
        EventBus.getDefault().register(this);
    }

    @Override // com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        if (enableEventBus() && EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        T t = this.mPresenter;
        if (t != null) {
            t.detachView();
        }
        LoadingPopupView loadingPopupView = this.loadingPopupView;
        if (loadingPopupView != null && loadingPopupView.isShow()) {
            this.loadingPopupView.dismiss();
        }
        super.onDestroy();
    }

    @Override // com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.unbinder.unbind();
    }
}
