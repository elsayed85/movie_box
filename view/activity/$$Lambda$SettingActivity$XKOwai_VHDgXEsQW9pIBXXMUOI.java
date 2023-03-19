package com.movieboxpro.android.view.activity;

import java.io.File;
import java.io.FileFilter;
/* compiled from: lambda */
/* renamed from: com.movieboxpro.android.view.activity.-$$Lambda$SettingActivity$XKOwai_V-HDgXEsQW9pIBXXMUOI  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$SettingActivity$XKOwai_VHDgXEsQW9pIBXXMUOI implements FileFilter {
    public static final /* synthetic */ $$Lambda$SettingActivity$XKOwai_VHDgXEsQW9pIBXXMUOI INSTANCE = new $$Lambda$SettingActivity$XKOwai_VHDgXEsQW9pIBXXMUOI();

    private /* synthetic */ $$Lambda$SettingActivity$XKOwai_VHDgXEsQW9pIBXXMUOI() {
    }

    @Override // java.io.FileFilter
    public final boolean accept(File file) {
        boolean m270clearAppCache$lambda13$lambda12;
        m270clearAppCache$lambda13$lambda12 = SettingActivity.m270clearAppCache$lambda13$lambda12(file);
        return m270clearAppCache$lambda13$lambda12;
    }
}
