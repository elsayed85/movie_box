package com.movieboxpro.android.view.dialog;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialog;
import androidx.core.content.ContextCompat;
import com.movieboxpro.android.R;
import com.movieboxpro.android.view.dialog.DialogAction;
/* loaded from: classes3.dex */
public class UpdateSheetDialog extends AppCompatDialog {
    private final Context mContext;

    public UpdateSheetDialog(Context context) {
        super(context, R.style.BottomSheetFullScreenDialog);
        this.mContext = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initDialog();
    }

    private void initDialog() {
        Window window = getWindow();
        if (window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            window.setBackgroundDrawable(ContextCompat.getDrawable(this.mContext, 17170445));
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = 80;
            attributes.dimAmount = 0.0f;
            attributes.width = -1;
            attributes.height = -2;
            window.setAttributes(attributes);
        }
    }

    /* loaded from: classes3.dex */
    public static class Builder {
        private DialogAction.ActionListener actionListener;
        private Context context;
        private String secondContent;
        private String title = "Message";
        private String negativeText = "Cancel";
        private String positiveText = "OK";
        private boolean isNegativeGone = false;
        private boolean isCancelable = true;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitle(String str) {
            this.title = str;
            return this;
        }

        public Builder setContent(String str) {
            this.secondContent = str;
            return this;
        }

        public Builder setNegativeGone() {
            this.isNegativeGone = true;
            return this;
        }

        public Builder setNegativeText(String str) {
            this.negativeText = str;
            return this;
        }

        public Builder setPositiveText(String str) {
            this.positiveText = str;
            return this;
        }

        public Builder setActionListener(DialogAction.ActionListener actionListener) {
            this.actionListener = actionListener;
            return this;
        }

        public Builder setCancelable(boolean z) {
            this.isCancelable = z;
            return this;
        }

        public UpdateSheetDialog create() {
            final UpdateSheetDialog updateSheetDialog = new UpdateSheetDialog(this.context);
            View inflate = LayoutInflater.from(this.context).inflate(R.layout.update_dialog_layout, (ViewGroup) null);
            updateSheetDialog.setContentView(inflate);
            updateSheetDialog.setCancelable(this.isCancelable);
            updateSheetDialog.setCanceledOnTouchOutside(this.isCancelable);
            ((TextView) inflate.findViewById(R.id.tv_title)).setText(this.title);
            TextView textView = (TextView) inflate.findViewById(R.id.tv_content);
            if (Build.VERSION.SDK_INT >= 24) {
                textView.setText(Html.fromHtml(this.secondContent, 63));
            } else {
                textView.setText(Html.fromHtml(this.secondContent));
            }
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            TextView textView2 = (TextView) inflate.findViewById(R.id.tv_negative);
            if (this.isNegativeGone) {
                textView2.setVisibility(8);
            } else {
                textView2.setVisibility(0);
            }
            textView2.setText(this.negativeText);
            TextView textView3 = (TextView) inflate.findViewById(R.id.tv_positive);
            textView3.setText(this.positiveText);
            textView3.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.UpdateSheetDialog.Builder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (Builder.this.actionListener != null) {
                        Builder.this.actionListener.onClick();
                    }
                    if (Builder.this.isCancelable) {
                        updateSheetDialog.dismiss();
                    }
                }
            });
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.UpdateSheetDialog.Builder.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    updateSheetDialog.dismiss();
                }
            });
            return updateSheetDialog;
        }

        private void setDialogSize(final View view, final Context context) {
            view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.movieboxpro.android.view.dialog.UpdateSheetDialog.Builder.3
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View view2, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                    int height = view2.getHeight();
                    int width = view2.getWidth();
                    double d = context.getResources().getDisplayMetrics().heightPixels;
                    Double.isNaN(d);
                    int i9 = (int) (d * 0.6d);
                    if (height > i9) {
                        i9 = -2;
                    }
                    view.setLayoutParams(new FrameLayout.LayoutParams(width, i9));
                }
            });
        }
    }
}
