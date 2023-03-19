package com.movieboxpro.android.view.activity.settings;

import android.widget.CompoundButton;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.utils.PrefsUtils;
/* compiled from: lambda */
/* renamed from: com.movieboxpro.android.view.activity.settings.-$$Lambda$SettingActivity$oFMShRiXn8T1rBHUW_sKjDvIL5k  reason: invalid class name */
/* loaded from: classes3.dex */
public final /* synthetic */ class $$Lambda$SettingActivity$oFMShRiXn8T1rBHUW_sKjDvIL5k implements CompoundButton.OnCheckedChangeListener {
    public static final /* synthetic */ $$Lambda$SettingActivity$oFMShRiXn8T1rBHUW_sKjDvIL5k INSTANCE = new $$Lambda$SettingActivity$oFMShRiXn8T1rBHUW_sKjDvIL5k();

    private /* synthetic */ $$Lambda$SettingActivity$oFMShRiXn8T1rBHUW_sKjDvIL5k() {
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        PrefsUtils.getInstance().putBoolean(Constant.Prefs.AUTO_SELECT_SUBTITLE, z);
    }
}
