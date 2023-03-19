package com.movieboxpro.android.view.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.R;
import com.movieboxpro.android.databinding.DialogSmartDownloadHintBinding;
import com.movieboxpro.android.livedata.SmartDownloadChangedLiveData;
import com.movieboxpro.android.utils.SettingManager;
import com.movieboxpro.android.view.dialog.DialogAction;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: SmartDownloadHintDialog.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\bH\u0016J\b\u0010\n\u001a\u00020\bH\u0016J$\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u000e\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/movieboxpro/android/view/dialog/SmartDownloadHintDialog;", "Lcom/movieboxpro/android/view/dialog/BaseBindingCenterDialogFragment;", "()V", "binding", "Lcom/movieboxpro/android/databinding/DialogSmartDownloadHintBinding;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/DialogAction$ActionListener;", "initData", "", "initListener", "initView", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "setListener", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SmartDownloadHintDialog extends BaseBindingCenterDialogFragment {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private DialogSmartDownloadHintBinding binding;
    private DialogAction.ActionListener listener;

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
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

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void initData() {
    }

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void initView() {
    }

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final void setListener(DialogAction.ActionListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ViewDataBinding inflate = DataBindingUtil.inflate(inflater, R.layout.dialog_smart_download_hint, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, R.layo…d_hint, container, false)");
        DialogSmartDownloadHintBinding dialogSmartDownloadHintBinding = (DialogSmartDownloadHintBinding) inflate;
        this.binding = dialogSmartDownloadHintBinding;
        if (dialogSmartDownloadHintBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogSmartDownloadHintBinding = null;
        }
        View root = dialogSmartDownloadHintBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void initListener() {
        DialogSmartDownloadHintBinding dialogSmartDownloadHintBinding = this.binding;
        DialogSmartDownloadHintBinding dialogSmartDownloadHintBinding2 = null;
        if (dialogSmartDownloadHintBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogSmartDownloadHintBinding = null;
        }
        dialogSmartDownloadHintBinding.tvDismiss.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$SmartDownloadHintDialog$KvVAknUzchRbJ9d6vL75JubIVz4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SmartDownloadHintDialog.m1130initListener$lambda0(SmartDownloadHintDialog.this, view);
            }
        });
        DialogSmartDownloadHintBinding dialogSmartDownloadHintBinding3 = this.binding;
        if (dialogSmartDownloadHintBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogSmartDownloadHintBinding3 = null;
        }
        dialogSmartDownloadHintBinding3.tvOn.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$SmartDownloadHintDialog$5qQNuUrIDilU4YOGGd6nQOeDAMU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SmartDownloadHintDialog.m1131initListener$lambda1(SmartDownloadHintDialog.this, view);
            }
        });
        DialogSmartDownloadHintBinding dialogSmartDownloadHintBinding4 = this.binding;
        if (dialogSmartDownloadHintBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogSmartDownloadHintBinding2 = dialogSmartDownloadHintBinding4;
        }
        dialogSmartDownloadHintBinding2.tvNoShow.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$SmartDownloadHintDialog$t23hQfhlKb4J7Yl966xm2txkhAQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SmartDownloadHintDialog.m1132initListener$lambda2(SmartDownloadHintDialog.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m1130initListener$lambda0(SmartDownloadHintDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DialogAction.ActionListener actionListener = this$0.listener;
        if (actionListener != null) {
            actionListener.onClick();
        }
        this$0.dismissAllowingStateLoss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m1131initListener$lambda1(SmartDownloadHintDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SettingManager.INSTANCE.saveSmartDownload(true);
        SmartDownloadChangedLiveData.Companion.get().setValue(true);
        DialogAction.ActionListener actionListener = this$0.listener;
        if (actionListener != null) {
            actionListener.onClick();
        }
        this$0.dismissAllowingStateLoss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m1132initListener$lambda2(SmartDownloadHintDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SettingManager.INSTANCE.saveNeverShowSmartDownloadDialog();
        this$0.dismissAllowingStateLoss();
    }
}
