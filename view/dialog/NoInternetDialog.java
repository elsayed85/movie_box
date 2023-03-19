package com.movieboxpro.android.view.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import androidx.appcompat.app.AppCompatDialog;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public class NoInternetDialog extends AppCompatDialog {
    private OnButtonClickListener mOnButtonClickListener;

    /* loaded from: classes3.dex */
    public interface OnButtonClickListener {
        void onClick();
    }

    public NoInternetDialog(Context context, OnButtonClickListener onButtonClickListener) {
        super(context, R.style.DialogStyle);
        this.mOnButtonClickListener = onButtonClickListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        Window window = getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_no_internet);
            ((Button) window.findViewById(R.id.btn_try_again)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.NoInternetDialog.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (NoInternetDialog.this.mOnButtonClickListener != null) {
                        NoInternetDialog.this.mOnButtonClickListener.onClick();
                        NoInternetDialog.this.dismiss();
                    }
                }
            });
            WindowManager.LayoutParams attributes = window.getAttributes();
            double d = getContext().getResources().getDisplayMetrics().widthPixels;
            Double.isNaN(d);
            attributes.width = (int) (d * 0.7d);
            attributes.height = -2;
            window.setAttributes(attributes);
        }
    }
}
