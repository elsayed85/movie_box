package com.movieboxpro.android.view.activity.settings;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.BaseActivity;
import com.movieboxpro.android.utils.SystemUtils;
/* loaded from: classes3.dex */
public class AboutActivity extends BaseActivity {
    @BindView(R.id.about_name)
    TextView aboutName;
    @BindView(R.id.about_url)
    TextView aboutUrl;
    @BindView(R.id.about_version)
    TextView aboutVersion;

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initData() {
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, AboutActivity.class));
    }

    @OnClick({R.id.about_name, R.id.about_url})
    public void Onclicked(View view) {
        switch (view.getId()) {
            case R.id.about_name /* 2131296282 */:
                if (this.activity == null || this.activity.isFinishing()) {
                    return;
                }
                SystemUtils.sendEmail(this.activity);
                return;
            case R.id.about_url /* 2131296283 */:
                if (this.activity == null || this.activity.isFinishing()) {
                    return;
                }
                SystemUtils.startBrowser(this.activity, "https://www.movieboxpro.app");
                return;
            default:
                return;
        }
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public View loadView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.activity_about, (ViewGroup) null);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initView() {
        setTitle("About");
        this.aboutVersion.setText("v15.5");
    }
}
