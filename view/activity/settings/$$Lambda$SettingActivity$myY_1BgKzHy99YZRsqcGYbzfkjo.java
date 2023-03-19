package com.movieboxpro.android.view.activity.settings;

import com.movieboxpro.android.utils.FileUtils;
import java.io.File;
import java.io.FileFilter;
/* compiled from: lambda */
/* renamed from: com.movieboxpro.android.view.activity.settings.-$$Lambda$SettingActivity$myY_1BgKzHy99YZRsqcGYbzfkjo  reason: invalid class name */
/* loaded from: classes3.dex */
public final /* synthetic */ class $$Lambda$SettingActivity$myY_1BgKzHy99YZRsqcGYbzfkjo implements FileFilter {
    public static final /* synthetic */ $$Lambda$SettingActivity$myY_1BgKzHy99YZRsqcGYbzfkjo INSTANCE = new $$Lambda$SettingActivity$myY_1BgKzHy99YZRsqcGYbzfkjo();

    private /* synthetic */ $$Lambda$SettingActivity$myY_1BgKzHy99YZRsqcGYbzfkjo() {
    }

    @Override // java.io.FileFilter
    public final boolean accept(File file) {
        boolean equalsIgnoreCase;
        equalsIgnoreCase = "mp4".equalsIgnoreCase(FileUtils.getExtensionName(file.getPath()));
        return equalsIgnoreCase;
    }
}
