package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.movieboxpro.android.R;
import com.movieboxpro.android.utils.ToastUtils;
/* loaded from: classes3.dex */
public class InvateDialog extends Dialog {
    public static InvateDialog payDialog;
    private EditText InvateEdit;
    private TextView LoginIn;
    private ConstraintLayout lLayout_pay;
    private onNoOnclickListener noOnclickListener;

    /* loaded from: classes3.dex */
    public interface onNoOnclickListener {
        void onNoClick(String str);
    }

    private void initData() {
    }

    public InvateDialog(Context context) {
        super(context);
        payDialog = this;
    }

    public InvateDialog setNoOnclickListener(onNoOnclickListener onnoonclicklistener) {
        this.noOnclickListener = onnoonclicklistener;
        return this;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_invate_dialog);
        setCanceledOnTouchOutside(false);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        this.InvateEdit = (EditText) findViewById(R.id.invate_edit);
        this.LoginIn = (TextView) findViewById(R.id.invate_login);
        this.lLayout_pay = (ConstraintLayout) findViewById(R.id.lLayout_pay);
        Display defaultDisplay = ((WindowManager) payDialog.getContext().getSystemService("window")).getDefaultDisplay();
        ConstraintLayout constraintLayout = this.lLayout_pay;
        double width = defaultDisplay.getWidth();
        Double.isNaN(width);
        constraintLayout.setLayoutParams(new FrameLayout.LayoutParams((int) (width * 0.6d), -2));
    }

    public static int dip2px(Context context, float f) {
        return (int) ((f * getScreenDendity(context)) + 0.5f);
    }

    public static float getScreenDendity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    private void initEvent() {
        this.LoginIn.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.InvateDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (InvateDialog.this.noOnclickListener != null) {
                    if (!TextUtils.isEmpty(InvateDialog.this.InvateEdit.getText().toString())) {
                        InvateDialog.this.noOnclickListener.onNoClick(InvateDialog.this.InvateEdit.getText().toString());
                    } else {
                        ToastUtils.showShort("empty invite code");
                    }
                }
            }
        });
    }
}
