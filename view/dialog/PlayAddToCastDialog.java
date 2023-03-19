package com.movieboxpro.android.view.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatDialog;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public class PlayAddToCastDialog extends AppCompatDialog {
    private OnPlayAddListener listener;
    private Context mContext;

    /* loaded from: classes3.dex */
    public interface OnPlayAddListener {
        void onAdd();

        void onPlay();
    }

    public PlayAddToCastDialog(Context context, OnPlayAddListener onPlayAddListener) {
        super(context, R.style.DialogStyle);
        this.mContext = context;
        this.listener = onPlayAddListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initDialog();
        initView();
    }

    private void initView() {
        View inflate = View.inflate(this.mContext, R.layout.play_add_cast_dialog, null);
        setContentView(inflate);
        ((LinearLayout) inflate.findViewById(R.id.ll_play)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.PlayAddToCastDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PlayAddToCastDialog.this.listener.onPlay();
                PlayAddToCastDialog.this.dismiss();
            }
        });
        ((LinearLayout) inflate.findViewById(R.id.ll_add)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.PlayAddToCastDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PlayAddToCastDialog.this.listener.onAdd();
                PlayAddToCastDialog.this.dismiss();
            }
        });
        ((LinearLayout) inflate.findViewById(R.id.ll_cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.PlayAddToCastDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PlayAddToCastDialog.this.dismiss();
            }
        });
    }

    private void initDialog() {
        Window window = getWindow();
        if (window == null) {
            return;
        }
        window.setGravity(17);
        WindowManager.LayoutParams attributes = window.getAttributes();
        double d = this.mContext.getResources().getDisplayMetrics().widthPixels;
        Double.isNaN(d);
        attributes.width = (int) (d * 0.7d);
        window.setAttributes(attributes);
    }
}
