package com.movieboxpro.android.view.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatDialog;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public class RemindChangeVoiceDialog extends AppCompatDialog {
    private final Context mContext;

    public RemindChangeVoiceDialog(Context context) {
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
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.remind_change_voice_layout, (ViewGroup) null);
        setContentView(inflate);
        ((LinearLayout) inflate.findViewById(R.id.linearLayout)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.RemindChangeVoiceDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RemindChangeVoiceDialog.this.dismiss();
            }
        });
    }
}
