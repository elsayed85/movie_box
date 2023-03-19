package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatDialogFragment;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.R;
import com.movieboxpro.android.utils.ScreenUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: BaseCenterDialogFragment.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0004H\u0014J\b\u0010\u0006\u001a\u00020\u0007H\u0014J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u0004H&J\b\u0010\u000b\u001a\u00020\u0004H&J\b\u0010\f\u001a\u00020\u0007H\u0014J\u0012\u0010\r\u001a\u00020\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0012\u0010\u0010\u001a\u00020\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J&\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u001a\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00122\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0010\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0012H\u0002¨\u0006\u001b"}, d2 = {"Lcom/movieboxpro/android/view/dialog/BaseCenterDialogFragment;", "Landroidx/appcompat/app/AppCompatDialogFragment;", "()V", "initData", "", "initDialog", "initDialogWidth", "", "initLayoutId", "", "initListener", "initView", "maxHeight", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreate", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "onViewCreated", "view", "setDialogSize", "mView", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class BaseCenterDialogFragment extends AppCompatDialogFragment {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

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

    public abstract void initData();

    protected float initDialogWidth() {
        return 0.0f;
    }

    public abstract int initLayoutId();

    public abstract void initListener();

    public abstract void initView();

    protected float maxHeight() {
        return 0.6f;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(initLayoutId(), viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        setDialogSize(view);
        initView();
        initData();
        initListener();
    }

    private final void setDialogSize(final View view) {
        if (maxHeight() == 0.0f) {
            return;
        }
        view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$BaseCenterDialogFragment$-XwoQnUJcTVooIQ4uF_UIrKxEvA
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view2, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                BaseCenterDialogFragment.m934setDialogSize$lambda0(BaseCenterDialogFragment.this, view, view2, i, i2, i3, i4, i5, i6, i7, i8);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setDialogSize$lambda-0  reason: not valid java name */
    public static final void m934setDialogSize$lambda0(BaseCenterDialogFragment this$0, View mView, View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mView, "$mView");
        int height = view.getHeight();
        view.getWidth();
        double screenHeight = ScreenUtils.getScreenHeight();
        Double.isNaN(screenHeight);
        int i9 = (int) (screenHeight * 0.6d);
        if (height > i9) {
            if (height < i9) {
                i9 = -2;
            }
            float initDialogWidth = this$0.initDialogWidth();
            if (!(initDialogWidth == 0.0f)) {
                mView.setLayoutParams(new FrameLayout.LayoutParams((int) initDialogWidth, i9));
            } else {
                mView.setLayoutParams(new FrameLayout.LayoutParams(-1, i9));
            }
        }
    }

    protected void initDialog() {
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
}
