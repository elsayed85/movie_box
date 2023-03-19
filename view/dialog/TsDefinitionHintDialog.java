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
import androidx.core.content.ContextCompat;
import com.movieboxpro.android.R;
import com.movieboxpro.android.view.dialog.DialogAction;
/* loaded from: classes3.dex */
public class TsDefinitionHintDialog extends AppCompatDialog {
    private Context mContext;

    public TsDefinitionHintDialog(Context context) {
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

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setActionListener(DialogAction.ActionListener actionListener) {
            this.actionListener = actionListener;
            return this;
        }

        public TsDefinitionHintDialog create() {
            final TsDefinitionHintDialog tsDefinitionHintDialog = new TsDefinitionHintDialog(this.context);
            View inflate = LayoutInflater.from(this.context).inflate(R.layout.ts_definition_hint_dialog, (ViewGroup) null);
            tsDefinitionHintDialog.setContentView(inflate);
            tsDefinitionHintDialog.setCancelable(true);
            tsDefinitionHintDialog.setCanceledOnTouchOutside(true);
            ((TextView) inflate.findViewById(R.id.tvContinue)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.TsDefinitionHintDialog.Builder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (Builder.this.actionListener != null) {
                        Builder.this.actionListener.onClick();
                        tsDefinitionHintDialog.dismiss();
                    }
                }
            });
            ((TextView) inflate.findViewById(R.id.tvClose)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.TsDefinitionHintDialog.Builder.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    tsDefinitionHintDialog.dismiss();
                }
            });
            return tsDefinitionHintDialog;
        }
    }
}
