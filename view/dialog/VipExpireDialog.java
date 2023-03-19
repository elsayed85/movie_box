package com.movieboxpro.android.view.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialog;
import androidx.core.content.ContextCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.movieboxpro.android.R;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.VipExpireDialog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: VipExpireDialog.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\tB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/view/dialog/VipExpireDialog;", "Landroidx/appcompat/app/AppCompatDialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "Builder", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class VipExpireDialog extends AppCompatDialog {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VipExpireDialog(Context context) {
        super(context, R.style.BottomSheetFullScreenDialog);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), 17170445));
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = 80;
            attributes.dimAmount = 0.5f;
            attributes.width = -1;
            attributes.height = -2;
            window.setAttributes(attributes);
        }
    }

    /* compiled from: VipExpireDialog.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\tJ\u000e\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\fJ\u0006\u0010\u0018\u001a\u00020\u0000J\u000e\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\fJ\u000e\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\fJ\u000e\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\fR\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/movieboxpro/android/view/dialog/VipExpireDialog$Builder;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "actionListener", "Lcom/movieboxpro/android/view/dialog/DialogAction$ActionListener;", "cancelListener", "isCancelable", "", "isNegativeGone", "negativeText", "", "positiveText", "secondContent", "title", "create", "Lcom/movieboxpro/android/view/dialog/VipExpireDialog;", "setActionListener", "setCancelListener", "setCancelable", "cancelable", "setContent", FirebaseAnalytics.Param.CONTENT, "setNegativeGone", "setNegativeText", "text", "setPositiveText", "setTitle", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static class Builder {
        private DialogAction.ActionListener actionListener;
        private DialogAction.ActionListener cancelListener;
        private Context context;
        private boolean isCancelable;
        private boolean isNegativeGone;
        private String negativeText;
        private String positiveText;
        private String secondContent;
        private String title;

        public Builder(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            this.context = context;
            this.title = "Message";
            this.negativeText = "Cancel";
            this.positiveText = "OK";
            this.isCancelable = true;
        }

        public final Builder setTitle(String title) {
            Intrinsics.checkNotNullParameter(title, "title");
            this.title = title;
            return this;
        }

        public final Builder setContent(String content) {
            Intrinsics.checkNotNullParameter(content, "content");
            this.secondContent = content;
            return this;
        }

        public final Builder setNegativeGone() {
            this.isNegativeGone = true;
            return this;
        }

        public final Builder setNegativeText(String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            this.negativeText = text;
            return this;
        }

        public final Builder setPositiveText(String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            this.positiveText = text;
            return this;
        }

        public final Builder setActionListener(DialogAction.ActionListener actionListener) {
            Intrinsics.checkNotNullParameter(actionListener, "actionListener");
            this.actionListener = actionListener;
            return this;
        }

        public final Builder setCancelListener(DialogAction.ActionListener actionListener) {
            Intrinsics.checkNotNullParameter(actionListener, "actionListener");
            this.cancelListener = actionListener;
            return this;
        }

        public final Builder setCancelable(boolean z) {
            this.isCancelable = z;
            return this;
        }

        public final VipExpireDialog create() {
            final VipExpireDialog vipExpireDialog = new VipExpireDialog(this.context);
            View inflate = LayoutInflater.from(this.context).inflate(R.layout.vip_expire_date_layout, (ViewGroup) null);
            vipExpireDialog.setContentView(inflate);
            TextView cancel = (TextView) inflate.findViewById(R.id.tvCancel);
            cancel.setText(this.negativeText);
            if (this.isNegativeGone) {
                Intrinsics.checkNotNullExpressionValue(cancel, "cancel");
                CommonExtKt.gone(cancel);
            } else {
                Intrinsics.checkNotNullExpressionValue(cancel, "cancel");
                CommonExtKt.visible(cancel);
            }
            cancel.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$VipExpireDialog$Builder$928Kgwp-AD2vE5kH8HfBefgmE6M
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VipExpireDialog.Builder.m1133create$lambda0(VipExpireDialog.this, view);
                }
            });
            ((TextView) inflate.findViewById(R.id.tvTitle)).setText(this.title);
            ((TextView) inflate.findViewById(R.id.tvExpireDate)).setText(this.secondContent);
            ((TextView) inflate.findViewById(R.id.tvRenew)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$VipExpireDialog$Builder$T9HIoWusyAuveLdZrFn59hE19cE
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VipExpireDialog.Builder.m1134create$lambda1(VipExpireDialog.Builder.this, view);
                }
            });
            vipExpireDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$VipExpireDialog$Builder$I-LguNal2-90H75pCO-Em21Xgqk
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    VipExpireDialog.Builder.m1135create$lambda2(VipExpireDialog.Builder.this, dialogInterface);
                }
            });
            return vipExpireDialog;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: create$lambda-0  reason: not valid java name */
        public static final void m1133create$lambda0(VipExpireDialog dialog, View view) {
            Intrinsics.checkNotNullParameter(dialog, "$dialog");
            dialog.dismiss();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: create$lambda-1  reason: not valid java name */
        public static final void m1134create$lambda1(Builder this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            DialogAction.ActionListener actionListener = this$0.actionListener;
            if (actionListener == null) {
                return;
            }
            actionListener.onClick();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: create$lambda-2  reason: not valid java name */
        public static final void m1135create$lambda2(Builder this$0, DialogInterface dialogInterface) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            DialogAction.ActionListener actionListener = this$0.cancelListener;
            if (actionListener == null) {
                return;
            }
            actionListener.onClick();
        }
    }
}
