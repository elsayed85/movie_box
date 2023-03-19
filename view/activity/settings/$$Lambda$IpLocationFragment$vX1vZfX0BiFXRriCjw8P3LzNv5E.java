package com.movieboxpro.android.view.activity.settings;

import com.movieboxpro.android.utils.LocationUtils;
import com.movieboxpro.android.view.dialog.DialogAction;
/* compiled from: lambda */
/* renamed from: com.movieboxpro.android.view.activity.settings.-$$Lambda$IpLocationFragment$vX1vZfX0BiFXRriCjw8P3LzNv5E  reason: invalid class name */
/* loaded from: classes3.dex */
public final /* synthetic */ class $$Lambda$IpLocationFragment$vX1vZfX0BiFXRriCjw8P3LzNv5E implements DialogAction.ActionListener {
    public static final /* synthetic */ $$Lambda$IpLocationFragment$vX1vZfX0BiFXRriCjw8P3LzNv5E INSTANCE = new $$Lambda$IpLocationFragment$vX1vZfX0BiFXRriCjw8P3LzNv5E();

    private /* synthetic */ $$Lambda$IpLocationFragment$vX1vZfX0BiFXRriCjw8P3LzNv5E() {
    }

    @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
    public final void onClick() {
        LocationUtils.openGpsSettings();
    }
}
