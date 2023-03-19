package com.movieboxpro.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.android.R;
import com.movieboxpro.android.view.dialog.LoginCodeShowDialog;
/* loaded from: classes3.dex */
public abstract class DialogLoginCodeShowBinding extends ViewDataBinding {
    @Bindable
    protected LoginCodeShowDialog mDialog;
    public final ProgressBar progressBar;
    public final TextView tvTry;

    public abstract void setDialog(LoginCodeShowDialog loginCodeShowDialog);

    /* JADX INFO: Access modifiers changed from: protected */
    public DialogLoginCodeShowBinding(Object obj, View view, int i, ProgressBar progressBar, TextView textView) {
        super(obj, view, i);
        this.progressBar = progressBar;
        this.tvTry = textView;
    }

    public LoginCodeShowDialog getDialog() {
        return this.mDialog;
    }

    public static DialogLoginCodeShowBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogLoginCodeShowBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogLoginCodeShowBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_login_code_show, viewGroup, z, obj);
    }

    public static DialogLoginCodeShowBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogLoginCodeShowBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogLoginCodeShowBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_login_code_show, null, false, obj);
    }

    public static DialogLoginCodeShowBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogLoginCodeShowBinding bind(View view, Object obj) {
        return (DialogLoginCodeShowBinding) bind(obj, view, R.layout.dialog_login_code_show);
    }
}
