package com.movieboxpro.android.view.dialog;

import android.content.Context;
import android.os.Build;
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
public class SwitchOriginRateDialog extends AppCompatDialog {
    private Context mContext;

    public SwitchOriginRateDialog(Context context) {
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

    @Override // android.app.Dialog
    public void show() {
        if (getWindow() != null) {
            getWindow().setFlags(8, 8);
        }
        super.show();
        fullScreenImmersive(getWindow().getDecorView());
        getWindow().clearFlags(8);
    }

    private void fullScreenImmersive(View view) {
        if (Build.VERSION.SDK_INT >= 19) {
            view.setSystemUiVisibility(5894);
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

        public SwitchOriginRateDialog create() {
            final SwitchOriginRateDialog switchOriginRateDialog = new SwitchOriginRateDialog(this.context);
            View inflate = LayoutInflater.from(this.context).inflate(R.layout.switch_origin_rate_layout, (ViewGroup) null);
            switchOriginRateDialog.addContentView(inflate, new ViewGroup.LayoutParams(-1, -2));
            ((TextView) inflate.findViewById(R.id.tv_keep)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.SwitchOriginRateDialog.Builder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (Builder.this.actionListener != null) {
                        Builder.this.actionListener.onClick();
                    }
                    switchOriginRateDialog.dismiss();
                }
            });
            ((TextView) inflate.findViewById(R.id.tv_cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.SwitchOriginRateDialog.Builder.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    switchOriginRateDialog.dismiss();
                }
            });
            return switchOriginRateDialog;
        }
    }
}
