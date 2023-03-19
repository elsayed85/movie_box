package com.movieboxpro.android.view.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialog;
import com.movieboxpro.android.R;
import com.movieboxpro.android.view.dialog.DialogAction;
/* loaded from: classes3.dex */
public class EditTextDialog extends AppCompatDialog {
    private Context mContext;

    public EditTextDialog(Context context) {
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
        private DialogAction.EditActionListener actionListener;
        private Context context;
        private EditText editText;
        private String hint;
        private String negativeText = "Cancel";
        private String positiveText = "OK";
        private String secondContent;
        private String title;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitle(String str) {
            this.title = str;
            return this;
        }

        public Builder setHint(String str) {
            this.hint = str;
            return this;
        }

        public Builder setContent(String str) {
            this.secondContent = str;
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

        public Builder setActionListener(DialogAction.EditActionListener editActionListener) {
            this.actionListener = editActionListener;
            return this;
        }

        public EditTextDialog create() {
            final EditTextDialog editTextDialog = new EditTextDialog(this.context);
            View inflate = LayoutInflater.from(this.context).inflate(R.layout.edit_text_dialog_layout, (ViewGroup) null);
            editTextDialog.addContentView(inflate, new ViewGroup.LayoutParams(-1, -2));
            ((TextView) inflate.findViewById(R.id.tv_title)).setText(this.title);
            ((TextView) inflate.findViewById(R.id.tv_content)).setText(this.secondContent);
            EditText editText = (EditText) inflate.findViewById(R.id.editText);
            this.editText = editText;
            editText.setHint(this.hint);
            TextView textView = (TextView) inflate.findViewById(R.id.tv_negative);
            textView.setText(this.negativeText);
            final TextView textView2 = (TextView) inflate.findViewById(R.id.tv_positive);
            textView2.setText(this.positiveText);
            textView2.setEnabled(false);
            this.editText.addTextChangedListener(new TextWatcher() { // from class: com.movieboxpro.android.view.dialog.EditTextDialog.Builder.1
                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable editable) {
                    if (!TextUtils.isEmpty(editable.toString())) {
                        textView2.setEnabled(true);
                    } else {
                        textView2.setEnabled(false);
                    }
                }
            });
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.EditTextDialog.Builder.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (Builder.this.actionListener != null) {
                        Builder.this.actionListener.onClick(Builder.this.editText.getText().toString());
                    }
                    editTextDialog.dismiss();
                }
            });
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.EditTextDialog.Builder.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    editTextDialog.dismiss();
                }
            });
            final InputMethodManager inputMethodManager = (InputMethodManager) editTextDialog.getContext().getSystemService("input_method");
            editTextDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.movieboxpro.android.view.dialog.EditTextDialog.Builder.4
                @Override // android.content.DialogInterface.OnDismissListener
                public void onDismiss(DialogInterface dialogInterface) {
                    InputMethodManager inputMethodManager2 = inputMethodManager;
                    if (inputMethodManager2 != null) {
                        inputMethodManager2.hideSoftInputFromWindow(Builder.this.editText.getWindowToken(), 0);
                    }
                }
            });
            this.editText.postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.dialog.EditTextDialog.Builder.5
                @Override // java.lang.Runnable
                public void run() {
                    Builder.this.editText.requestFocus();
                    InputMethodManager inputMethodManager2 = inputMethodManager;
                    if (inputMethodManager2 != null) {
                        inputMethodManager2.showSoftInput(Builder.this.editText, 0);
                    }
                }
            }, 300L);
            return editTextDialog;
        }
    }
}
