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
/* loaded from: classes3.dex */
public class TimeCheckDialog extends AppCompatDialog {
    private Context mContext;

    public TimeCheckDialog(Context context) {
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
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.time_check_layout, (ViewGroup) null);
        setContentView(inflate);
        ((TextView) inflate.findViewById(R.id.tvPositive)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$TimeCheckDialog$hHZADFAABUwTLgzpiJmnuYyi_eI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TimeCheckDialog.this.lambda$initDialog$0$TimeCheckDialog(view);
            }
        });
    }

    public /* synthetic */ void lambda$initDialog$0$TimeCheckDialog(View view) {
        dismiss();
    }
}
