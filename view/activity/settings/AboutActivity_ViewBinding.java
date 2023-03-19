package com.movieboxpro.android.view.activity.settings;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public class AboutActivity_ViewBinding implements Unbinder {
    private AboutActivity target;
    private View view7f09001a;
    private View view7f09001b;

    public AboutActivity_ViewBinding(AboutActivity aboutActivity) {
        this(aboutActivity, aboutActivity.getWindow().getDecorView());
    }

    public AboutActivity_ViewBinding(final AboutActivity aboutActivity, View view) {
        this.target = aboutActivity;
        aboutActivity.aboutVersion = (TextView) Utils.findRequiredViewAsType(view, R.id.about_version, "field 'aboutVersion'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.about_url, "field 'aboutUrl' and method 'Onclicked'");
        aboutActivity.aboutUrl = (TextView) Utils.castView(findRequiredView, R.id.about_url, "field 'aboutUrl'", TextView.class);
        this.view7f09001b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.AboutActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                aboutActivity.Onclicked(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.about_name, "field 'aboutName' and method 'Onclicked'");
        aboutActivity.aboutName = (TextView) Utils.castView(findRequiredView2, R.id.about_name, "field 'aboutName'", TextView.class);
        this.view7f09001a = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.AboutActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                aboutActivity.Onclicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        AboutActivity aboutActivity = this.target;
        if (aboutActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        aboutActivity.aboutVersion = null;
        aboutActivity.aboutUrl = null;
        aboutActivity.aboutName = null;
        this.view7f09001b.setOnClickListener(null);
        this.view7f09001b = null;
        this.view7f09001a.setOnClickListener(null);
        this.view7f09001a = null;
    }
}
