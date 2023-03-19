package com.movieboxpro.android.view.activity.settings;

import android.widget.CompoundButton;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.utils.PrefsUtils;
/* compiled from: lambda */
/* renamed from: com.movieboxpro.android.view.activity.settings.-$$Lambda$SettingActivity$t49jdaSsjcSR_RWrfshfhaU4hfI  reason: invalid class name */
/* loaded from: classes3.dex */
public final /* synthetic */ class $$Lambda$SettingActivity$t49jdaSsjcSR_RWrfshfhaU4hfI implements CompoundButton.OnCheckedChangeListener {
    public static final /* synthetic */ $$Lambda$SettingActivity$t49jdaSsjcSR_RWrfshfhaU4hfI INSTANCE = new $$Lambda$SettingActivity$t49jdaSsjcSR_RWrfshfhaU4hfI();

    private /* synthetic */ $$Lambda$SettingActivity$t49jdaSsjcSR_RWrfshfhaU4hfI() {
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        PrefsUtils.getInstance().putBoolean(Constant.Prefs.AUTO_PLAY_NEXT, z);
    }
}
