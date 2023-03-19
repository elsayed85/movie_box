package com.movieboxpro.android.base;

import android.os.Bundle;
import android.view.View;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.impl.LoadingPopupView;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: BaseBindingFragment.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0014J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\u000bH\u0016J\b\u0010\r\u001a\u00020\u000bH&J\b\u0010\u000e\u001a\u00020\u000bH&J\b\u0010\u000f\u001a\u00020\u000bH&J\u001a\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u000bH\u0016J\b\u0010\u0016\u001a\u00020\u000bH\u0016J\b\u0010\u0017\u001a\u00020\u000bH\u0016R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/movieboxpro/android/base/BaseBindingFragment;", "Lcom/movieboxpro/android/base/BaseLazyFragment;", "Lcom/movieboxpro/android/base/LoadView;", "layoutId", "", "(I)V", "loadingPopupView", "Lcom/lxj/xpopup/impl/LoadingPopupView;", "enableEventBus", "", "hideLoadingPage", "", "hideLoadingView", "initData", "initListener", "initView", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "showErrorPage", "showLoadingPage", "showLoadingView", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class BaseBindingFragment extends BaseLazyFragment implements LoadView {
    public Map<Integer, View> _$_findViewCache;
    private LoadingPopupView loadingPopupView;

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    protected boolean enableEventBus() {
        return false;
    }

    @Override // com.movieboxpro.android.base.LoadView
    public void hideLoadingPage() {
    }

    public abstract void initData();

    public abstract void initListener();

    public abstract void initView();

    @Override // com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.movieboxpro.android.base.LoadView
    public void showErrorPage() {
    }

    @Override // com.movieboxpro.android.base.LoadView
    public void showLoadingPage() {
    }

    public BaseBindingFragment(int i) {
        super(i);
        this._$_findViewCache = new LinkedHashMap();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        initData();
        initView();
        initListener();
    }

    @Override // com.movieboxpro.android.base.LoadView
    public void showLoadingView() {
        LoadingPopupView loadingPopupView = this.loadingPopupView;
        if (loadingPopupView != null) {
            Intrinsics.checkNotNull(loadingPopupView);
            if (loadingPopupView.isShow()) {
                return;
            }
        }
        LoadingPopupView loadingPopupView2 = this.loadingPopupView;
        if (loadingPopupView2 != null) {
            Intrinsics.checkNotNull(loadingPopupView2);
            if (loadingPopupView2.isShow()) {
                return;
            }
        }
        if (this.loadingPopupView == null) {
            this.loadingPopupView = new XPopup.Builder(getContext()).dismissOnBackPressed(true).dismissOnTouchOutside(false).hasShadowBg(true).asLoading("");
        }
        LoadingPopupView loadingPopupView3 = this.loadingPopupView;
        Intrinsics.checkNotNull(loadingPopupView3);
        loadingPopupView3.show();
    }

    @Override // com.movieboxpro.android.base.LoadView
    public void hideLoadingView() {
        LoadingPopupView loadingPopupView = this.loadingPopupView;
        if (loadingPopupView != null) {
            Intrinsics.checkNotNull(loadingPopupView);
            loadingPopupView.smartDismiss();
        }
    }
}
