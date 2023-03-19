package com.movieboxpro.android.view.dialog;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatDialog;
import com.movieboxpro.android.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: BaseCenterDialog.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\u0006H\u0004J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\u0006H&J\b\u0010\r\u001a\u00020\u0006H&J\u0012\u0010\u000e\u001a\u00020\u00062\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014¨\u0006\u0011"}, d2 = {"Lcom/movieboxpro/android/view/dialog/BaseCenterDialog;", "Landroidx/appcompat/app/AppCompatDialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "initData", "", "initDialog", "initDialogWidth", "", "initLayoutId", "", "initListener", "initView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class BaseCenterDialog extends AppCompatDialog {
    public abstract void initData();

    public float initDialogWidth() {
        return 0.8f;
    }

    public abstract int initLayoutId();

    public abstract void initListener();

    public abstract void initView();

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseCenterDialog(Context context) {
        super(context, R.style.DialogStyle);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(initLayoutId());
        initDialog();
        initView();
        initData();
        initListener();
    }

    protected final void initDialog() {
        Window window = getWindow();
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        Intrinsics.checkNotNullExpressionValue(displayMetrics, "context.resources.displayMetrics");
        attributes.width = (int) (displayMetrics.widthPixels * initDialogWidth());
        attributes.height = -2;
        window.setAttributes(attributes);
    }
}
