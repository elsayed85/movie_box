package com.movieboxpro.android.view.activity.vlcvideoplayer.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.movieboxpro.android.R;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.view.dialog.BaseCenterDialogFragment;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: EngineHelpDialog.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0014J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\u0004H\u0016J\b\u0010\t\u001a\u00020\u0004H\u0016J\b\u0010\n\u001a\u00020\u000bH\u0014J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0016¨\u0006\u000f"}, d2 = {"Lcom/movieboxpro/android/view/activity/vlcvideoplayer/widget/EngineHelpDialog;", "Lcom/movieboxpro/android/view/dialog/BaseCenterDialogFragment;", "()V", "initData", "", "initDialog", "initLayoutId", "", "initListener", "initView", "maxHeight", "", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class EngineHelpDialog extends BaseCenterDialogFragment {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment
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

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment
    public void initData() {
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment
    public int initLayoutId() {
        return R.layout.dialog_engine_help;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment
    public void initListener() {
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment
    public void initView() {
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment
    protected float maxHeight() {
        return 0.0f;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment
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
        if (CommonUtils.isTablet()) {
            double d = displayMetrics.widthPixels;
            Double.isNaN(d);
            attributes.width = (int) (d * 0.6d);
        } else {
            double d2 = displayMetrics.widthPixels;
            Double.isNaN(d2);
            attributes.width = (int) (d2 * 0.9d);
        }
        window.setAttributes(attributes);
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
    }
}
