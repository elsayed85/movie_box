package com.movieboxpro.android.base.mvp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.impl.LoadingPopupView;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.mvp.BaseContract;
import com.movieboxpro.android.base.mvp.BaseContract.BasePresenter;
import org.greenrobot.eventbus.EventBus;
/* loaded from: classes3.dex */
public abstract class BaseDialogMvpFragment<T extends BaseContract.BasePresenter> extends DialogFragment implements BaseContract.BaseView {
    private KProgressHUD hud;
    private LoadingPopupView loadingPopupView;
    protected T mPresenter;

    protected abstract int bindLayout();

    protected abstract T bindPresenter();

    protected boolean enableEventBus() {
        return false;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void hideLoading() {
    }

    protected abstract void initData();

    protected int initDialogHeight() {
        return -1;
    }

    protected abstract void initListener();

    protected abstract void initView();

    protected abstract void loadData();

    protected boolean needExpand() {
        return false;
    }

    protected boolean needFullScreen() {
        return false;
    }

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
    public void showLoading() {
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        T bindPresenter = bindPresenter();
        this.mPresenter = bindPresenter;
        if (bindPresenter != null) {
            bindPresenter.attachView(this);
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(0, R.style.BottomSheetFullScreenDialog);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        Window window;
        if (getDialog() != null && getContext() != null && (window = getDialog().getWindow()) != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams attributes = window.getAttributes();
            window.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), 17170445));
            if (initDialogHeight() != -1) {
                window.setLayout(-1, initDialogHeight());
            } else {
                window.setLayout(-1, -2);
            }
            attributes.dimAmount = 0.5f;
            attributes.gravity = 80;
            window.setAttributes(attributes);
        }
        super.onActivityCreated(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(bindLayout(), viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initData();
        initView();
        initListener();
        loadData();
    }

    @Override // androidx.fragment.app.DialogFragment
    public void show(FragmentManager fragmentManager, String str) {
        try {
            super.show(fragmentManager, str);
        } catch (IllegalStateException unused) {
        }
    }

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void showLoadingView() {
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
    public void hideLoadingView() {
        LoadingPopupView loadingPopupView = this.loadingPopupView;
        if (loadingPopupView != null) {
            loadingPopupView.smartDismiss();
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (!enableEventBus() || EventBus.getDefault().isRegistered(this)) {
            return;
        }
        EventBus.getDefault().register(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // androidx.fragment.app.Fragment
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
        KProgressHUD kProgressHUD = this.hud;
        if (kProgressHUD != null && kProgressHUD.isShowing()) {
            this.hud.dismiss();
        }
        super.onDestroy();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
    }
}
