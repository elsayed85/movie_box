package com.movieboxpro.android.view.activity;

import java.io.File;
import java.io.FileFilter;
/* compiled from: lambda */
/* renamed from: com.movieboxpro.android.view.activity.-$$Lambda$SettingActivity$3g36Giv3ArrUyvZ27eommrYEhvg  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$SettingActivity$3g36Giv3ArrUyvZ27eommrYEhvg implements FileFilter {
    public static final /* synthetic */ $$Lambda$SettingActivity$3g36Giv3ArrUyvZ27eommrYEhvg INSTANCE = new $$Lambda$SettingActivity$3g36Giv3ArrUyvZ27eommrYEhvg();

    private /* synthetic */ $$Lambda$SettingActivity$3g36Giv3ArrUyvZ27eommrYEhvg() {
    }

    @Override // java.io.FileFilter
    public final boolean accept(File file) {
        boolean m269clearAppCache$lambda13$lambda11;
        m269clearAppCache$lambda13$lambda11 = SettingActivity.m269clearAppCache$lambda13$lambda11(file);
        return m269clearAppCache$lambda13$lambda11;
    }
}
