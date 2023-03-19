package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatDialogFragment;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.impl.LoadingPopupView;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.LoadView;
import com.movieboxpro.android.utils.DensityUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: BaseBindingCenterDialogFragment.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\u0007H\u0016J\b\u0010\t\u001a\u00020\u0007H&J\b\u0010\n\u001a\u00020\u0007H\u0002J\b\u0010\u000b\u001a\u00020\u0007H&J\b\u0010\f\u001a\u00020\u0007H&J\u0012\u0010\r\u001a\u00020\u00072\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0012\u0010\u0010\u001a\u00020\u00072\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u001a\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0010\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u0013H\u0002J\b\u0010\u0016\u001a\u00020\u0007H\u0016J\b\u0010\u0017\u001a\u00020\u0007H\u0016J\b\u0010\u0018\u001a\u00020\u0007H\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/movieboxpro/android/view/dialog/BaseBindingCenterDialogFragment;", "Landroidx/appcompat/app/AppCompatDialogFragment;", "Lcom/movieboxpro/android/base/LoadView;", "()V", "loadingPopupView", "Lcom/lxj/xpopup/impl/LoadingPopupView;", "hideLoadingPage", "", "hideLoadingView", "initData", "initDialog", "initListener", "initView", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreate", "onViewCreated", "view", "Landroid/view/View;", "setDialogSize", "mView", "showErrorPage", "showLoadingPage", "showLoadingView", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class BaseBindingCenterDialogFragment extends AppCompatDialogFragment implements LoadView {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
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

    @Override // com.movieboxpro.android.base.LoadView
    public void hideLoadingPage() {
    }

    public abstract void initData();

    public abstract void initListener();

    public abstract void initView();

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
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

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(0, R.style.DialogStyle);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        initDialog();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        initView();
        initData();
        initListener();
    }

    private final void setDialogSize(final View view) {
        view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$BaseBindingCenterDialogFragment$LmTk7DXKbbUE3MVA0fvUC5KVavM
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view2, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                BaseBindingCenterDialogFragment.m932setDialogSize$lambda0(BaseBindingCenterDialogFragment.this, view, view2, i, i2, i3, i4, i5, i6, i7, i8);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setDialogSize$lambda-0  reason: not valid java name */
    public static final void m932setDialogSize$lambda0(BaseBindingCenterDialogFragment this$0, View mView, View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mView, "$mView");
        int height = view.getHeight();
        view.getWidth();
        double screenHeight = DensityUtils.getScreenHeight(this$0.getContext());
        Double.isNaN(screenHeight);
        int i9 = (int) (screenHeight * 0.6d);
        if (height > i9) {
            if (height < i9) {
                i9 = -2;
            }
            mView.setLayoutParams(new FrameLayout.LayoutParams(-1, i9));
        }
    }

    private final void initDialog() {
        Dialog dialog = getDialog();
        Window window = dialog == null ? null : dialog.getWindow();
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        Context context = getContext();
        if (context == null) {
            return;
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        Intrinsics.checkNotNullExpressionValue(displayMetrics, "it.resources.displayMetrics");
        double d = displayMetrics.widthPixels;
        Double.isNaN(d);
        attributes.width = (int) (d * 0.8d);
        attributes.height = -2;
        window.setAttributes(attributes);
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
}
