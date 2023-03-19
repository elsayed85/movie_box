package com.movieboxpro.android.view.activity.user;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public class Login2Activity_ViewBinding implements Unbinder {
    private Login2Activity target;
    private View view7f0903a8;
    private View view7f0903c3;

    public Login2Activity_ViewBinding(Login2Activity login2Activity) {
        this(login2Activity, login2Activity.getWindow().getDecorView());
    }

    public Login2Activity_ViewBinding(final Login2Activity login2Activity, View view) {
        this.target = login2Activity;
        View findRequiredView = Utils.findRequiredView(view, R.id.llSignIn, "method 'onViewClicked'");
        this.view7f0903c3 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.user.Login2Activity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                login2Activity.onViewClicked();
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.llLoginCode, "method 'onLoginCode'");
        this.view7f0903a8 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.user.Login2Activity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                login2Activity.onLoginCode();
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        if (this.target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        this.view7f0903c3.setOnClickListener(null);
        this.view7f0903c3 = null;
        this.view7f0903a8.setOnClickListener(null);
        this.view7f0903a8 = null;
    }
}
