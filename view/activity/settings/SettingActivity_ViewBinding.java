package com.movieboxpro.android.view.activity.settings;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public class SettingActivity_ViewBinding implements Unbinder {
    private SettingActivity target;
    private View view7f090113;
    private View view7f0905e4;
    private View view7f0905e7;
    private View view7f0905ec;
    private View view7f0905f1;
    private View view7f0905f4;
    private View view7f0905f9;
    private View view7f0905fb;
    private View view7f090600;
    private View view7f0906e7;
    private View view7f0906fb;
    private View view7f0906fc;
    private View view7f09070d;
    private View view7f090736;
    private View view7f09074b;
    private View view7f09076c;
    private View view7f0907c0;
    private View view7f0907c1;

    public SettingActivity_ViewBinding(SettingActivity settingActivity) {
        this(settingActivity, settingActivity.getWindow().getDecorView());
    }

    public SettingActivity_ViewBinding(final SettingActivity settingActivity, View view) {
        this.target = settingActivity;
        settingActivity.tvDownloadDirName = (TextView) Utils.findRequiredViewAsType(view, R.id.tvDownloadDirName, "field 'tvDownloadDirName'", TextView.class);
        settingActivity.tvDownloadSpace = (TextView) Utils.findRequiredViewAsType(view, R.id.tvDownloadSpace, "field 'tvDownloadSpace'", TextView.class);
        settingActivity.emailRemind = (SwitchCompat) Utils.findRequiredViewAsType(view, R.id.switch_email_remind, "field 'emailRemind'", SwitchCompat.class);
        settingActivity.clEmailRemind = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.clEmailRemind, "field 'clEmailRemind'", ConstraintLayout.class);
        settingActivity.childModeSwitch = (SwitchCompat) Utils.findRequiredViewAsType(view, R.id.switch_child_mode, "field 'childModeSwitch'", SwitchCompat.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.tvChangeChildModePsw, "field 'tvChangeChildModePsw' and method 'onViewClicked'");
        settingActivity.tvChangeChildModePsw = (TextView) Utils.castView(findRequiredView, R.id.tvChangeChildModePsw, "field 'tvChangeChildModePsw'", TextView.class);
        this.view7f0906fb = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.SettingActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                settingActivity.onViewClicked(view2);
            }
        });
        settingActivity.userCelluarDownload = (SwitchCompat) Utils.findRequiredViewAsType(view, R.id.setting_download_more, "field 'userCelluarDownload'", SwitchCompat.class);
        settingActivity.tvCheckUpdate = (TextView) Utils.findRequiredViewAsType(view, R.id.tvCheckUpdate, "field 'tvCheckUpdate'", TextView.class);
        settingActivity.ivNewVersion = (ImageView) Utils.findRequiredViewAsType(view, R.id.ivNewVersion, "field 'ivNewVersion'", ImageView.class);
        settingActivity.tvPlayerEngine = (TextView) Utils.findRequiredViewAsType(view, R.id.tvPlayerEngine, "field 'tvPlayerEngine'", TextView.class);
        settingActivity.switchRememberOrg = (SwitchCompat) Utils.findRequiredViewAsType(view, R.id.switch_remember_org, "field 'switchRememberOrg'", SwitchCompat.class);
        settingActivity.switchAutoNext = (SwitchCompat) Utils.findRequiredViewAsType(view, R.id.switchAutoNext, "field 'switchAutoNext'", SwitchCompat.class);
        settingActivity.switchAutoSelectSubtitle = (SwitchCompat) Utils.findRequiredViewAsType(view, R.id.switchAutoSelectSubtitle, "field 'switchAutoSelectSubtitle'", SwitchCompat.class);
        settingActivity.switchPlayFullscreen = (SwitchCompat) Utils.findRequiredViewAsType(view, R.id.switch_play_fullscreen, "field 'switchPlayFullscreen'", SwitchCompat.class);
        settingActivity.setting_play_fullscreen = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.setting_play_fullscreen, "field 'setting_play_fullscreen'", ConstraintLayout.class);
        settingActivity.switchBlackMode = (SwitchCompat) Utils.findRequiredViewAsType(view, R.id.switchBlackMode, "field 'switchBlackMode'", SwitchCompat.class);
        settingActivity.switchHideMovielist = (SwitchCompat) Utils.findRequiredViewAsType(view, R.id.switch_hide_movielist, "field 'switchHideMovielist'", SwitchCompat.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.setting_language, "method 'onViewClicked'");
        this.view7f0905f9 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.SettingActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                settingActivity.onViewClicked(view2);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.setting_download, "method 'onViewClicked'");
        this.view7f0905ec = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.SettingActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                settingActivity.onViewClicked(view2);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.setting_cache, "method 'onViewClicked'");
        this.view7f0905e7 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.SettingActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                settingActivity.onViewClicked(view2);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.setting_faq, "method 'onViewClicked'");
        this.view7f0905f1 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.SettingActivity_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                settingActivity.onViewClicked(view2);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.setting_about, "method 'onViewClicked'");
        this.view7f0905e4 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.SettingActivity_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                settingActivity.onViewClicked(view2);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, R.id.setting_feedback, "method 'onViewClicked'");
        this.view7f0905f4 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.SettingActivity_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                settingActivity.onViewClicked(view2);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, R.id.setting_mytickets, "method 'onViewClicked'");
        this.view7f0905fb = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.SettingActivity_ViewBinding.8
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                settingActivity.onViewClicked(view2);
            }
        });
        View findRequiredView9 = Utils.findRequiredView(view, R.id.tvAbout, "method 'onViewClicked'");
        this.view7f0906e7 = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.SettingActivity_ViewBinding.9
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                settingActivity.onViewClicked(view2);
            }
        });
        View findRequiredView10 = Utils.findRequiredView(view, R.id.tvPrivacyPolicy, "method 'onViewClicked'");
        this.view7f09076c = findRequiredView10;
        findRequiredView10.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.SettingActivity_ViewBinding.10
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                settingActivity.onViewClicked(view2);
            }
        });
        View findRequiredView11 = Utils.findRequiredView(view, R.id.tvInviteCode, "method 'onViewClicked'");
        this.view7f090736 = findRequiredView11;
        findRequiredView11.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.SettingActivity_ViewBinding.11
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                settingActivity.onViewClicked(view2);
            }
        });
        View findRequiredView12 = Utils.findRequiredView(view, R.id.tvChangeEmail, "method 'onViewClicked'");
        this.view7f0906fc = findRequiredView12;
        findRequiredView12.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.SettingActivity_ViewBinding.12
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                settingActivity.onViewClicked(view2);
            }
        });
        View findRequiredView13 = Utils.findRequiredView(view, R.id.tvMyOrder, "method 'onViewClicked'");
        this.view7f09074b = findRequiredView13;
        findRequiredView13.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.SettingActivity_ViewBinding.13
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                settingActivity.onViewClicked(view2);
            }
        });
        View findRequiredView14 = Utils.findRequiredView(view, R.id.setting_player_engine, "method 'onViewClicked'");
        this.view7f090600 = findRequiredView14;
        findRequiredView14.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.SettingActivity_ViewBinding.14
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                settingActivity.onViewClicked(view2);
            }
        });
        View findRequiredView15 = Utils.findRequiredView(view, R.id.clDownloadLocation, "method 'onViewClicked'");
        this.view7f090113 = findRequiredView15;
        findRequiredView15.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.SettingActivity_ViewBinding.15
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                settingActivity.onViewClicked(view2);
            }
        });
        View findRequiredView16 = Utils.findRequiredView(view, R.id.tvDeleteAccount, "method 'onViewClicked'");
        this.view7f09070d = findRequiredView16;
        findRequiredView16.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.SettingActivity_ViewBinding.16
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                settingActivity.onViewClicked(view2);
            }
        });
        View findRequiredView17 = Utils.findRequiredView(view, R.id.tvUploadVlcLog, "method 'onViewClicked'");
        this.view7f0907c1 = findRequiredView17;
        findRequiredView17.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.SettingActivity_ViewBinding.17
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                settingActivity.onViewClicked(view2);
            }
        });
        View findRequiredView18 = Utils.findRequiredView(view, R.id.tvUploadIJKLog, "method 'onViewClicked'");
        this.view7f0907c0 = findRequiredView18;
        findRequiredView18.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.SettingActivity_ViewBinding.18
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                settingActivity.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SettingActivity settingActivity = this.target;
        if (settingActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        settingActivity.tvDownloadDirName = null;
        settingActivity.tvDownloadSpace = null;
        settingActivity.emailRemind = null;
        settingActivity.clEmailRemind = null;
        settingActivity.childModeSwitch = null;
        settingActivity.tvChangeChildModePsw = null;
        settingActivity.userCelluarDownload = null;
        settingActivity.tvCheckUpdate = null;
        settingActivity.ivNewVersion = null;
        settingActivity.tvPlayerEngine = null;
        settingActivity.switchRememberOrg = null;
        settingActivity.switchAutoNext = null;
        settingActivity.switchAutoSelectSubtitle = null;
        settingActivity.switchPlayFullscreen = null;
        settingActivity.setting_play_fullscreen = null;
        settingActivity.switchBlackMode = null;
        settingActivity.switchHideMovielist = null;
        this.view7f0906fb.setOnClickListener(null);
        this.view7f0906fb = null;
        this.view7f0905f9.setOnClickListener(null);
        this.view7f0905f9 = null;
        this.view7f0905ec.setOnClickListener(null);
        this.view7f0905ec = null;
        this.view7f0905e7.setOnClickListener(null);
        this.view7f0905e7 = null;
        this.view7f0905f1.setOnClickListener(null);
        this.view7f0905f1 = null;
        this.view7f0905e4.setOnClickListener(null);
        this.view7f0905e4 = null;
        this.view7f0905f4.setOnClickListener(null);
        this.view7f0905f4 = null;
        this.view7f0905fb.setOnClickListener(null);
        this.view7f0905fb = null;
        this.view7f0906e7.setOnClickListener(null);
        this.view7f0906e7 = null;
        this.view7f09076c.setOnClickListener(null);
        this.view7f09076c = null;
        this.view7f090736.setOnClickListener(null);
        this.view7f090736 = null;
        this.view7f0906fc.setOnClickListener(null);
        this.view7f0906fc = null;
        this.view7f09074b.setOnClickListener(null);
        this.view7f09074b = null;
        this.view7f090600.setOnClickListener(null);
        this.view7f090600 = null;
        this.view7f090113.setOnClickListener(null);
        this.view7f090113 = null;
        this.view7f09070d.setOnClickListener(null);
        this.view7f09070d = null;
        this.view7f0907c1.setOnClickListener(null);
        this.view7f0907c1 = null;
        this.view7f0907c0.setOnClickListener(null);
        this.view7f0907c0 = null;
    }
}
