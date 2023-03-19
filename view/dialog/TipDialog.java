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
/* loaded from: classes3.dex */
public class TipDialog extends AppCompatDialog {
    private Context mContext;

    /* loaded from: classes3.dex */
    public interface OnPositiveListener {
        void onClick();
    }

    public TipDialog(Context context, int i) {
        super(context, i);
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
        private Context context;
        private OnPositiveListener mOnPositiveListener;
        private String msg;
        private String title = "提示";
        private String negativeMsg = "我知道了";
        private boolean cancelable = true;
        private boolean canceledOnTouchOutside = true;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setCancelable(boolean z) {
            this.cancelable = z;
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean z) {
            this.canceledOnTouchOutside = z;
            return this;
        }

        public TipDialog create() {
            TipDialog tipDialog = new TipDialog(this.context, R.style.DialogStyle);
            View inflate = LayoutInflater.from(this.context).inflate(R.layout.tip_dialog_layout, (ViewGroup) null);
            tipDialog.addContentView(inflate, new ViewGroup.LayoutParams(-1, -2));
            ((TextView) inflate.findViewById(R.id.tv_title)).setText(this.title);
            ((TextView) inflate.findViewById(R.id.tv_content)).setText(this.msg);
            tipDialog.setCancelable(this.cancelable);
            tipDialog.setCanceledOnTouchOutside(this.canceledOnTouchOutside);
            return tipDialog;
        }
    }
}
