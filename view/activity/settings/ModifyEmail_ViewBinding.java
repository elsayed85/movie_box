package com.movieboxpro.android.view.activity.settings;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public class ModifyEmail_ViewBinding implements Unbinder {
    private ModifyEmail target;
    private View view7f090462;
    private TextWatcher view7f090462TextWatcher;

    public ModifyEmail_ViewBinding(ModifyEmail modifyEmail) {
        this(modifyEmail, modifyEmail.getWindow().getDecorView());
    }

    public ModifyEmail_ViewBinding(final ModifyEmail modifyEmail, View view) {
        this.target = modifyEmail;
        View findRequiredView = Utils.findRequiredView(view, R.id.modify_email_text, "field 'modifyEmailText' and method 'onTextChanged'");
        modifyEmail.modifyEmailText = (EditText) Utils.castView(findRequiredView, R.id.modify_email_text, "field 'modifyEmailText'", EditText.class);
        this.view7f090462 = findRequiredView;
        TextWatcher textWatcher = new TextWatcher() { // from class: com.movieboxpro.android.view.activity.settings.ModifyEmail_ViewBinding.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                modifyEmail.onTextChanged(charSequence);
            }
        };
        this.view7f090462TextWatcher = textWatcher;
        ((TextView) findRequiredView).addTextChangedListener(textWatcher);
        modifyEmail.hintName = (TextView) Utils.findRequiredViewAsType(view, R.id.modify_email_name, "field 'hintName'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        ModifyEmail modifyEmail = this.target;
        if (modifyEmail == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        modifyEmail.modifyEmailText = null;
        modifyEmail.hintName = null;
        ((TextView) this.view7f090462).removeTextChangedListener(this.view7f090462TextWatcher);
        this.view7f090462TextWatcher = null;
        this.view7f090462 = null;
    }
}
