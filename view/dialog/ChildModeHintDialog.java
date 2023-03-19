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
public class ChildModeHintDialog extends AppCompatDialog {
    private DialogAction.ActionListener actionListener;
    private Context mContext;

    public void setActionListener(DialogAction.ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public ChildModeHintDialog(Context context) {
        super(context, R.style.DialogStyle);
        this.mContext = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initView();
        initDialog();
    }

    private void initView() {
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.dialog_child_mode_hint, (ViewGroup) null);
        setContentView(inflate, new ViewGroup.LayoutParams(-1, -2));
        ((TextView) inflate.findViewById(R.id.tv_positive)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.ChildModeHintDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ChildModeHintDialog.this.actionListener != null) {
                    ChildModeHintDialog.this.actionListener.onClick();
                }
                ChildModeHintDialog.this.dismiss();
            }
        });
        ((TextView) inflate.findViewById(R.id.tv_negative)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.ChildModeHintDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ChildModeHintDialog.this.dismiss();
            }
        });
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
}
