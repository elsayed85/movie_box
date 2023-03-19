package com.movieboxpro.android.view.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialog;
import com.movieboxpro.android.R;
import com.movieboxpro.android.view.dialog.DialogAction;
/* loaded from: classes3.dex */
public class MsgHintDialog extends AppCompatDialog {
    private Context mContext;

    public MsgHintDialog(Context context) {
        super(context, R.style.DialogStyle);
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
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        double d = this.mContext.getResources().getDisplayMetrics().widthPixels;
        Double.isNaN(d);
        attributes.width = (int) (d * 0.8d);
        attributes.height = -2;
        window.setAttributes(attributes);
    }

    /* loaded from: classes3.dex */
    public static class Builder {
        private DialogAction.ActionListener actionListener;
        private Context context;
        private DialogAction.ActionListener negativeActionListener;
        private String secondContent;
        private String title = "Note";
        private String negativeText = "Cancel";
        private String positiveText = "OK";
        private boolean isNegativeGone = false;
        private boolean canceled = true;

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

        public Builder setCancelable(boolean z) {
            this.canceled = z;
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

        public Builder setNegativeActionListener(DialogAction.ActionListener actionListener) {
            this.negativeActionListener = actionListener;
            return this;
        }

        public MsgHintDialog create() {
            final MsgHintDialog msgHintDialog = new MsgHintDialog(this.context);
            View inflate = LayoutInflater.from(this.context).inflate(R.layout.msg_hint_dialog_layout, (ViewGroup) null);
            msgHintDialog.addContentView(inflate, new ViewGroup.LayoutParams(-1, -2));
            ((TextView) inflate.findViewById(R.id.tv_title)).setText(this.title);
            ((TextView) inflate.findViewById(R.id.tv_content)).setText(this.secondContent);
            TextView textView = (TextView) inflate.findViewById(R.id.tv_negative);
            msgHintDialog.setCancelable(this.canceled);
            msgHintDialog.setCanceledOnTouchOutside(this.canceled);
            if (this.isNegativeGone) {
                textView.setVisibility(8);
            } else {
                textView.setVisibility(0);
            }
            textView.setText(this.negativeText);
            TextView textView2 = (TextView) inflate.findViewById(R.id.tv_positive);
            textView2.setText(this.positiveText);
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.MsgHintDialog.Builder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (Builder.this.actionListener != null) {
                        Builder.this.actionListener.onClick();
                    }
                    msgHintDialog.dismiss();
                }
            });
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.MsgHintDialog.Builder.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (Builder.this.negativeActionListener != null) {
                        Builder.this.negativeActionListener.onClick();
                    }
                    msgHintDialog.dismiss();
                }
            });
            return msgHintDialog;
        }
    }
}
