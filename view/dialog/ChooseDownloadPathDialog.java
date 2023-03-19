package com.movieboxpro.android.view.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseBindingBottomDialogFragment;
import com.movieboxpro.android.databinding.DialogChooseDownloadPathBinding;
import com.movieboxpro.android.livedata.DownloadPathLiveData;
import com.movieboxpro.android.service.FileDownloadService;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.view.dialog.DownloadPathDialog;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ChooseDownloadPathDialog.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\f\u001a\u00020\nH\u0016J$\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChooseDownloadPathDialog;", "Lcom/movieboxpro/android/base/BaseBindingBottomDialogFragment;", "()V", "binding", "Lcom/movieboxpro/android/databinding/DialogChooseDownloadPathBinding;", "internal", "", FileDownloadService.PARAMS_KEY_PATH, "", "initData", "", "initListener", "initView", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ChooseDownloadPathDialog extends BaseBindingBottomDialogFragment {
    private DialogChooseDownloadPathBinding binding;
    private String path;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private boolean internal = true;

    @Override // com.movieboxpro.android.base.BaseBindingBottomDialogFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.movieboxpro.android.base.BaseBindingBottomDialogFragment
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

    @Override // com.movieboxpro.android.base.BaseBindingBottomDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ViewDataBinding inflate = DataBindingUtil.inflate(inflater, R.layout.dialog_choose_download_path, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, R.layo…d_path, container, false)");
        DialogChooseDownloadPathBinding dialogChooseDownloadPathBinding = (DialogChooseDownloadPathBinding) inflate;
        this.binding = dialogChooseDownloadPathBinding;
        if (dialogChooseDownloadPathBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogChooseDownloadPathBinding = null;
        }
        View root = dialogChooseDownloadPathBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // com.movieboxpro.android.base.BaseBindingBottomDialogFragment
    public void initListener() {
        DialogChooseDownloadPathBinding dialogChooseDownloadPathBinding = this.binding;
        DialogChooseDownloadPathBinding dialogChooseDownloadPathBinding2 = null;
        if (dialogChooseDownloadPathBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogChooseDownloadPathBinding = null;
        }
        dialogChooseDownloadPathBinding.tvCancel.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ChooseDownloadPathDialog$phe-uIcdGqUbKJ1zuHKxyKZ6aRs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChooseDownloadPathDialog.m951initListener$lambda0(ChooseDownloadPathDialog.this, view);
            }
        });
        DialogChooseDownloadPathBinding dialogChooseDownloadPathBinding3 = this.binding;
        if (dialogChooseDownloadPathBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogChooseDownloadPathBinding3 = null;
        }
        dialogChooseDownloadPathBinding3.llPath.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ChooseDownloadPathDialog$5ckB1iz9hOlY--KoqpWn49AIECY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChooseDownloadPathDialog.m952initListener$lambda1(ChooseDownloadPathDialog.this, view);
            }
        });
        DialogChooseDownloadPathBinding dialogChooseDownloadPathBinding4 = this.binding;
        if (dialogChooseDownloadPathBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogChooseDownloadPathBinding2 = dialogChooseDownloadPathBinding4;
        }
        dialogChooseDownloadPathBinding2.tvSave.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ChooseDownloadPathDialog$vruUbtXek8J8uviKR2qsSDwJT-4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChooseDownloadPathDialog.m953initListener$lambda2(ChooseDownloadPathDialog.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m951initListener$lambda0(ChooseDownloadPathDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m952initListener$lambda1(final ChooseDownloadPathDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DownloadPathDialog.Companion companion = DownloadPathDialog.Companion;
        String str = this$0.path;
        if (str == null) {
            str = Constant.DIR_DOWNLOAD;
        }
        DownloadPathDialog newInstance = companion.newInstance(str);
        newInstance.setListener(new DownloadPathDialog.ChooseDownloadPathListener() { // from class: com.movieboxpro.android.view.dialog.ChooseDownloadPathDialog$initListener$2$1
            @Override // com.movieboxpro.android.view.dialog.DownloadPathDialog.ChooseDownloadPathListener
            public void onChoose(boolean z, String path) {
                DialogChooseDownloadPathBinding dialogChooseDownloadPathBinding;
                DialogChooseDownloadPathBinding dialogChooseDownloadPathBinding2;
                Intrinsics.checkNotNullParameter(path, "path");
                ChooseDownloadPathDialog.this.path = path;
                ChooseDownloadPathDialog.this.internal = z;
                DialogChooseDownloadPathBinding dialogChooseDownloadPathBinding3 = null;
                if (z) {
                    dialogChooseDownloadPathBinding2 = ChooseDownloadPathDialog.this.binding;
                    if (dialogChooseDownloadPathBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        dialogChooseDownloadPathBinding3 = dialogChooseDownloadPathBinding2;
                    }
                    dialogChooseDownloadPathBinding3.tvPath.setText("Internal Storage");
                    return;
                }
                dialogChooseDownloadPathBinding = ChooseDownloadPathDialog.this.binding;
                if (dialogChooseDownloadPathBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    dialogChooseDownloadPathBinding3 = dialogChooseDownloadPathBinding;
                }
                dialogChooseDownloadPathBinding3.tvPath.setText("SD Card");
            }
        });
        FragmentManager childFragmentManager = this$0.getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
        newInstance.show(childFragmentManager, DownloadPathDialog.class.getSimpleName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m953initListener$lambda2(ChooseDownloadPathDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.path != null) {
            if (this$0.internal) {
                PrefsUtils.getInstance().putBoolean(Constant.Prefs.INTERNAL_STORAGE, true);
            } else {
                PrefsUtils.getInstance().putBoolean(Constant.Prefs.INTERNAL_STORAGE, false);
            }
            PrefsUtils.getInstance().putString(Constant.Prefs.DOWNLOAD_DIR, this$0.path);
            Constant.DIR_DOWNLOAD = this$0.path;
            DownloadPathLiveData.Companion.get().setValue(Constant.DIR_DOWNLOAD);
        }
        this$0.dismiss();
    }

    @Override // com.movieboxpro.android.base.BaseBindingBottomDialogFragment
    public void initData() {
        DownloadPathLiveData.Companion.get().observe(this, new Observer() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ChooseDownloadPathDialog$EMDGvr5b_JQFkC4P45U6jictoUQ
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ChooseDownloadPathDialog.m950initData$lambda3(ChooseDownloadPathDialog.this, (String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-3  reason: not valid java name */
    public static final void m950initData$lambda3(ChooseDownloadPathDialog this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DialogChooseDownloadPathBinding dialogChooseDownloadPathBinding = null;
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.INTERNAL_STORAGE, true)) {
            DialogChooseDownloadPathBinding dialogChooseDownloadPathBinding2 = this$0.binding;
            if (dialogChooseDownloadPathBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                dialogChooseDownloadPathBinding = dialogChooseDownloadPathBinding2;
            }
            dialogChooseDownloadPathBinding.tvPath.setText("Internal Storage");
            return;
        }
        DialogChooseDownloadPathBinding dialogChooseDownloadPathBinding3 = this$0.binding;
        if (dialogChooseDownloadPathBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogChooseDownloadPathBinding = dialogChooseDownloadPathBinding3;
        }
        dialogChooseDownloadPathBinding.tvPath.setText("SD Card");
    }

    @Override // com.movieboxpro.android.base.BaseBindingBottomDialogFragment
    public void initView() {
        DialogChooseDownloadPathBinding dialogChooseDownloadPathBinding = null;
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.INTERNAL_STORAGE, true)) {
            DialogChooseDownloadPathBinding dialogChooseDownloadPathBinding2 = this.binding;
            if (dialogChooseDownloadPathBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                dialogChooseDownloadPathBinding = dialogChooseDownloadPathBinding2;
            }
            dialogChooseDownloadPathBinding.tvPath.setText("Internal Storage");
            return;
        }
        DialogChooseDownloadPathBinding dialogChooseDownloadPathBinding3 = this.binding;
        if (dialogChooseDownloadPathBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogChooseDownloadPathBinding = dialogChooseDownloadPathBinding3;
        }
        dialogChooseDownloadPathBinding.tvPath.setText("SD Card");
    }
}
