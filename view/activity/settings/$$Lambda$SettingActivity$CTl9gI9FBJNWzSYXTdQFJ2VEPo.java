package com.movieboxpro.android.view.activity.settings;

import java.io.File;
import java.io.FileFilter;
/* compiled from: lambda */
/* renamed from: com.movieboxpro.android.view.activity.settings.-$$Lambda$SettingActivity$CTl9-gI9FBJNWzSYXTdQFJ2VEPo  reason: invalid class name */
/* loaded from: classes3.dex */
public final /* synthetic */ class $$Lambda$SettingActivity$CTl9gI9FBJNWzSYXTdQFJ2VEPo implements FileFilter {
    public static final /* synthetic */ $$Lambda$SettingActivity$CTl9gI9FBJNWzSYXTdQFJ2VEPo INSTANCE = new $$Lambda$SettingActivity$CTl9gI9FBJNWzSYXTdQFJ2VEPo();

    private /* synthetic */ $$Lambda$SettingActivity$CTl9gI9FBJNWzSYXTdQFJ2VEPo() {
    }

    @Override // java.io.FileFilter
    public final boolean accept(File file) {
        return SettingActivity.lambda$clearAppCache$1(file);
    }
}
