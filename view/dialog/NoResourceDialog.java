package com.movieboxpro.android.view.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialog;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.R;
import com.movieboxpro.android.view.dialog.DialogAction;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: NoResourceDialog.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0002J\b\u0010\u000b\u001a\u00020\nH\u0002J\u0012\u0010\f\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/movieboxpro/android/view/dialog/NoResourceDialog;", "Landroidx/appcompat/app/AppCompatDialog;", "context", "Landroid/content/Context;", "unReleased", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/DialogAction$ActionListener;", "(Landroid/content/Context;ZLcom/movieboxpro/android/view/dialog/DialogAction$ActionListener;)V", "initDialog", "", "initView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class NoResourceDialog extends AppCompatDialog {
    private final DialogAction.ActionListener listener;
    private final boolean unReleased;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NoResourceDialog(Context context, boolean z, DialogAction.ActionListener listener) {
        super(context, R.style.BottomSheetFullScreenDialog);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.unReleased = z;
        this.listener = listener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_no_resource_hint);
        initDialog();
        initView();
    }

    private final void initView() {
        ((TextView) findViewById(R.id.tvClose)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$NoResourceDialog$Gj0eN6dEYKg9Hsa1MDjzHEAoL2M
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NoResourceDialog.m1046initView$lambda0(NoResourceDialog.this, view);
            }
        });
        if (this.unReleased) {
            ((TextView) findViewById(R.id.textView)).setText("Unreleased");
            ((TextView) findViewById(R.id.tvHint)).setText("The movie unreleased at the moment, you can watch the trailer first");
        } else {
            ((TextView) findViewById(R.id.textView)).setText("Not Available");
            ((TextView) findViewById(R.id.tvHint)).setText("The movie has no resources at the moment, you can watch the trailer first");
        }
        ((TextView) findViewById(R.id.tvPositive)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$NoResourceDialog$-Kd2aHxdWXyuAspZMq5wrcUxf1s
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NoResourceDialog.m1047initView$lambda1(NoResourceDialog.this, view);
            }
        });
        ((LinearLayout) findViewById(R.id.llPlayTrailer)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$NoResourceDialog$QUXNqW7xYI8Kz7nK_HUQQZatw4I
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NoResourceDialog.m1048initView$lambda2(NoResourceDialog.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-0  reason: not valid java name */
    public static final void m1046initView$lambda0(NoResourceDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-1  reason: not valid java name */
    public static final void m1047initView$lambda1(NoResourceDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-2  reason: not valid java name */
    public static final void m1048initView$lambda2(NoResourceDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.listener.onClick();
        this$0.dismiss();
    }

    private final void initDialog() {
        Window window = getWindow();
        if (window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            window.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), 17170445));
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = 80;
            attributes.dimAmount = 0.5f;
            attributes.width = -1;
            attributes.height = -2;
            window.setAttributes(attributes);
        }
    }
}
