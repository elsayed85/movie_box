package com.movieboxpro.android.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.impl.LoadingPopupView;
import com.movieboxpro.android.R;
import com.movieboxpro.android.view.widget.loadcallback.ErrorCallbackView;
import com.movieboxpro.android.view.widget.loadcallback.LoadingCallbackView;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: BaseBindingBottomDialogFragment.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0014J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\u000bH\u0016J\b\u0010\r\u001a\u00020\u000bH&J\b\u0010\u000e\u001a\u00020\u000bH&J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u000bH\u0002J\b\u0010\u0012\u001a\u00020\u000bH&J\b\u0010\u0013\u001a\u00020\u000bH\u0014J\b\u0010\u0014\u001a\u00020\u0015H\u0014J\u0012\u0010\u0016\u001a\u00020\u000b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0012\u0010\u0019\u001a\u00020\u001a2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010\u001b\u001a\u00020\u000bH\u0016J\b\u0010\u001c\u001a\u00020\u000bH\u0016J\u001a\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u00102\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u001a\u0010\u001f\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\b\u0010$\u001a\u00020\u000bH\u0016J\b\u0010%\u001a\u00020\u000bH\u0016J\b\u0010&\u001a\u00020\u000bH\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/movieboxpro/android/base/BaseBindingBottomDialogFragment;", "Lcom/google/android/material/bottomsheet/BottomSheetDialogFragment;", "Lcom/movieboxpro/android/base/LoadView;", "()V", "loadService", "Lcom/kingja/loadsir/core/LoadService;", "loadingPopupView", "Lcom/lxj/xpopup/impl/LoadingPopupView;", "dialogHeight", "", "hideLoadingPage", "", "hideLoadingView", "initData", "initListener", "initStatusContainerView", "Landroid/view/View;", "initStatusPageView", "initView", "loadData", "needFullscreen", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateDialog", "Landroid/app/Dialog;", "onDestroy", "onStart", "onViewCreated", "view", "show", "manager", "Landroidx/fragment/app/FragmentManager;", "tag", "", "showErrorPage", "showLoadingPage", "showLoadingView", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class BaseBindingBottomDialogFragment extends BottomSheetDialogFragment implements LoadView {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private LoadService<?> loadService;
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

    protected int dialogHeight() {
        return 0;
    }

    public abstract void initData();

    public abstract void initListener();

    public abstract void initView();

    /* JADX INFO: Access modifiers changed from: protected */
    public void loadData() {
    }

    protected boolean needFullscreen() {
        return false;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(0, R.style.TransparentDialog);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        initView();
        initData();
        initListener();
    }

    @Override // com.google.android.material.bottomsheet.BottomSheetDialogFragment, androidx.appcompat.app.AppCompatDialogFragment, androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        if (dialogHeight() == 0) {
            return new BottomSheetDialog(requireContext(), getTheme());
        }
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        return new FixedHeightBottomSheetDialog(requireContext, getTheme(), dialogHeight());
    }

    @Override // androidx.fragment.app.DialogFragment
    public void show(FragmentManager manager, String str) {
        Intrinsics.checkNotNullParameter(manager, "manager");
        try {
            super.show(manager, str);
        } catch (IllegalStateException unused) {
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.google.android.material.bottomsheet.BottomSheetDialog");
        }
        BottomSheetBehavior<FrameLayout> behavior = ((BottomSheetDialog) dialog).getBehavior();
        Intrinsics.checkNotNullExpressionValue(behavior, "dialog as BottomSheetDialog).behavior");
        behavior.setState(3);
        if (needFullscreen()) {
            Dialog dialog2 = getDialog();
            FrameLayout frameLayout = dialog2 == null ? null : (FrameLayout) dialog2.findViewById(R.id.design_bottom_sheet);
            Intrinsics.checkNotNull(frameLayout);
            frameLayout.getLayoutParams().height = -1;
            BottomSheetBehavior from = BottomSheetBehavior.from(frameLayout);
            Intrinsics.checkNotNullExpressionValue(from, "from(view)");
            from.setPeekHeight(3000);
            from.setState(3);
        }
    }

    @Override // com.movieboxpro.android.base.LoadView
    public void showLoadingView() {
        if (getContext() == null) {
            return;
        }
        if (this.loadingPopupView == null) {
            this.loadingPopupView = new XPopup.Builder(getContext()).hasShadowBg(false).dismissOnBackPressed(true).dismissOnTouchOutside(false).asLoading();
        }
        LoadingPopupView loadingPopupView = this.loadingPopupView;
        Intrinsics.checkNotNull(loadingPopupView);
        loadingPopupView.show();
    }

    @Override // com.movieboxpro.android.base.LoadView
    public void hideLoadingView() {
        if (this.loadingPopupView == null && getContext() != null) {
            this.loadingPopupView = new XPopup.Builder(getContext()).hasShadowBg(false).dismissOnBackPressed(true).dismissOnTouchOutside(false).asLoading();
        }
        LoadingPopupView loadingPopupView = this.loadingPopupView;
        if (loadingPopupView == null) {
            return;
        }
        loadingPopupView.delayDismiss(500L);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        hideLoadingView();
        super.onDestroy();
    }

    @Override // com.movieboxpro.android.base.LoadView
    public void showLoadingPage() {
        if (this.loadService == null) {
            initStatusPageView();
        }
        LoadService<?> loadService = this.loadService;
        Intrinsics.checkNotNull(loadService);
        loadService.showCallback(LoadingCallbackView.class);
    }

    @Override // com.movieboxpro.android.base.LoadView
    public void hideLoadingPage() {
        if (this.loadService == null) {
            initStatusPageView();
        }
        LoadService<?> loadService = this.loadService;
        Intrinsics.checkNotNull(loadService);
        loadService.showSuccess();
    }

    @Override // com.movieboxpro.android.base.LoadView
    public void showErrorPage() {
        if (this.loadService == null) {
            initStatusPageView();
        }
        LoadService<?> loadService = this.loadService;
        Intrinsics.checkNotNull(loadService);
        loadService.showCallback(ErrorCallbackView.class);
    }

    private final void initStatusPageView() {
        this.loadService = LoadSir.getDefault().register(initStatusContainerView(), new Callback.OnReloadListener() { // from class: com.movieboxpro.android.base.BaseBindingBottomDialogFragment$initStatusPageView$1
            @Override // com.kingja.loadsir.callback.Callback.OnReloadListener
            public final void onReload(View view) {
                BaseBindingBottomDialogFragment.this.loadData();
            }
        });
    }

    private final View initStatusContainerView() {
        View requireView = requireView();
        Intrinsics.checkNotNullExpressionValue(requireView, "requireView()");
        return requireView;
    }
}
