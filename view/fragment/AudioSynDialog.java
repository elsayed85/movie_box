package com.movieboxpro.android.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.databinding.DialogAudioSynBinding;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: AudioSynDialog.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\b\u0010\b\u001a\u00020\u0006H\u0016J$\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/movieboxpro/android/view/fragment/AudioSynDialog;", "Lcom/movieboxpro/android/view/dialog/BaseBindingCenterDialogFragment;", "()V", "binding", "Lcom/movieboxpro/android/databinding/DialogAudioSynBinding;", "initData", "", "initListener", "initView", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AudioSynDialog extends BaseBindingCenterDialogFragment {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private DialogAudioSynBinding binding;

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

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ViewDataBinding inflate = DataBindingUtil.inflate(inflater, R.layout.dialog_audio_syn, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, R.layo…io_syn, container, false)");
        DialogAudioSynBinding dialogAudioSynBinding = (DialogAudioSynBinding) inflate;
        this.binding = dialogAudioSynBinding;
        if (dialogAudioSynBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogAudioSynBinding = null;
        }
        View root = dialogAudioSynBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void initListener() {
        DialogAudioSynBinding dialogAudioSynBinding = this.binding;
        DialogAudioSynBinding dialogAudioSynBinding2 = null;
        if (dialogAudioSynBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogAudioSynBinding = null;
        }
        dialogAudioSynBinding.ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$AudioSynDialog$4U_nJx_ocLeSy3crE_6rWujkSiM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudioSynDialog.m1141initListener$lambda0(AudioSynDialog.this, view);
            }
        });
        DialogAudioSynBinding dialogAudioSynBinding3 = this.binding;
        if (dialogAudioSynBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogAudioSynBinding3 = null;
        }
        dialogAudioSynBinding3.ivAudioDelayAdd.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$AudioSynDialog$ikH96p8Z_iI9DiFu8f2vZsy-W-g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudioSynDialog.m1142initListener$lambda1(AudioSynDialog.this, view);
            }
        });
        DialogAudioSynBinding dialogAudioSynBinding4 = this.binding;
        if (dialogAudioSynBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogAudioSynBinding2 = dialogAudioSynBinding4;
        }
        dialogAudioSynBinding2.ivAudioDelaySub.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$AudioSynDialog$xHj7mhahOMTjEffnnJZvSCN6MUs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudioSynDialog.m1143initListener$lambda2(AudioSynDialog.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m1141initListener$lambda0(AudioSynDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m1142initListener$lambda1(AudioSynDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        long j = PrefsUtils.getInstance().getLong(Constant.Prefs.AUDIO_SYN_TIME, 500L) + 100;
        DialogAudioSynBinding dialogAudioSynBinding = this$0.binding;
        if (dialogAudioSynBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogAudioSynBinding = null;
        }
        TextView textView = dialogAudioSynBinding.tvAudioDelay;
        StringBuilder sb = new StringBuilder();
        sb.append(((float) j) / 1000.0f);
        sb.append('s');
        textView.setText(sb.toString());
        PrefsUtils.getInstance().putLong(Constant.Prefs.AUDIO_SYN_TIME, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m1143initListener$lambda2(AudioSynDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        long j = PrefsUtils.getInstance().getLong(Constant.Prefs.AUDIO_SYN_TIME, 500L) - 100;
        DialogAudioSynBinding dialogAudioSynBinding = this$0.binding;
        if (dialogAudioSynBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogAudioSynBinding = null;
        }
        TextView textView = dialogAudioSynBinding.tvAudioDelay;
        StringBuilder sb = new StringBuilder();
        sb.append(((float) j) / 1000.0f);
        sb.append('s');
        textView.setText(sb.toString());
        PrefsUtils.getInstance().putLong(Constant.Prefs.AUDIO_SYN_TIME, j);
    }

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void initView() {
        DialogAudioSynBinding dialogAudioSynBinding = this.binding;
        if (dialogAudioSynBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogAudioSynBinding = null;
        }
        TextView textView = dialogAudioSynBinding.tvAudioDelay;
        StringBuilder sb = new StringBuilder();
        sb.append(((float) PrefsUtils.getInstance().getLong(Constant.Prefs.AUDIO_SYN_TIME, 500L)) / 1000.0f);
        sb.append('s');
        textView.setText(sb.toString());
    }
}
