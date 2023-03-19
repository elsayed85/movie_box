package com.movieboxpro.android.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableField;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.movieboxpro.android.R;
import com.movieboxpro.android.utils.BindingAdapters;
import com.movieboxpro.android.view.dialog.LoginCodeShowDialog;
/* loaded from: classes3.dex */
public class DialogLoginCodeShowBindingImpl extends DialogLoginCodeShowBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView1;
    private final ImageView mboundView2;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.progressBar, 3);
        sViewsWithIds.put(R.id.tvTry, 4);
    }

    public DialogLoginCodeShowBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 5, sIncludes, sViewsWithIds));
    }

    private DialogLoginCodeShowBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (ProgressBar) objArr[3], (TextView) objArr[4]);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) objArr[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) objArr[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        ImageView imageView = (ImageView) objArr[2];
        this.mboundView2 = imageView;
        imageView.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.mDirtyFlags != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, Object obj) {
        if (1 == i) {
            setDialog((LoginCodeShowDialog) obj);
            return true;
        }
        return false;
    }

    @Override // com.movieboxpro.android.databinding.DialogLoginCodeShowBinding
    public void setDialog(LoginCodeShowDialog loginCodeShowDialog) {
        this.mDialog = loginCodeShowDialog;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(1);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeDialogCode((ObservableField) obj, i2);
    }

    private boolean onChangeDialogCode(ObservableField<String> observableField, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        LoginCodeShowDialog loginCodeShowDialog = this.mDialog;
        long j2 = 7 & j;
        String str = null;
        if (j2 != 0) {
            ObservableField<String> code = loginCodeShowDialog != null ? loginCodeShowDialog.getCode() : null;
            updateRegistration(0, code);
            if (code != null) {
                str = code.get();
            }
        }
        if (j2 != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, str);
        }
        if ((j & 4) != 0) {
            BindingAdapters.loadImage(this.mboundView2, (int) R.drawable.code_login_hint);
        }
    }
}
