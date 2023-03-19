package com.movieboxpro.android.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.impl.LoadingPopupView;
import com.movieboxpro.android.R;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: BaseFullScreenDialogFragment.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H$J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0002J\u0006\u0010\n\u001a\u00020\u000bJ\b\u0010\f\u001a\u00020\u000bH&J\b\u0010\r\u001a\u00020\u000bH&J\b\u0010\u000e\u001a\u00020\u000bH&J\u0012\u0010\u000f\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u0012\u0010\u0012\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J&\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u001a\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u00142\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u0006\u0010\u001b\u001a\u00020\u000bR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/movieboxpro/android/base/BaseFullScreenDialogFragment;", "Landroidx/appcompat/app/AppCompatDialogFragment;", "()V", "loadingPopupView", "Lcom/lxj/xpopup/impl/LoadingPopupView;", "bindLayout", "", "getContextRect", "activity", "Landroid/app/Activity;", "hideLoadingView", "", "initData", "initListener", "initView", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreate", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "onViewCreated", "view", "showLoadingView", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class BaseFullScreenDialogFragment extends AppCompatDialogFragment {
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

    protected abstract int bindLayout();

    public abstract void initData();

    public abstract void initListener();

    public abstract void initView();

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(0, R.style.BottomSheetFullScreenDialog);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        Dialog dialog = getDialog();
        Window window = dialog == null ? null : dialog.getWindow();
        if (window != null) {
            int i = 0;
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams attributes = window.getAttributes();
            Context context = getContext();
            Intrinsics.checkNotNull(context);
            window.setBackgroundDrawable(ContextCompat.getDrawable(context, 17170445));
            if (getActivity() != null) {
                FragmentActivity activity = getActivity();
                Intrinsics.checkNotNull(activity);
                Intrinsics.checkNotNullExpressionValue(activity, "activity!!");
                i = getContextRect(activity);
            }
            if (i == 0) {
                i = -1;
            }
            window.setLayout(-1, i);
            attributes.dimAmount = 0.0f;
            attributes.gravity = 80;
            window.setAttributes(attributes);
        }
        super.onActivityCreated(bundle);
    }

    private final int getContextRect(Activity activity) {
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.height();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(bindLayout(), viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        initData();
        initView();
        initListener();
    }

    public final void showLoadingView() {
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

    public final void hideLoadingView() {
        LoadingPopupView loadingPopupView = this.loadingPopupView;
        if (loadingPopupView != null) {
            Intrinsics.checkNotNull(loadingPopupView);
            loadingPopupView.smartDismiss();
        }
    }
}
