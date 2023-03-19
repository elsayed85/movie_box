package com.movieboxpro.android.view.activity;

import com.movieboxpro.android.utils.FileUtils;
import java.io.File;
import java.io.FileFilter;
/* compiled from: lambda */
/* renamed from: com.movieboxpro.android.view.activity.-$$Lambda$MainActivity$ciyU20-oH4zgh2OJpEkDzXCgoyI  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$MainActivity$ciyU20oH4zgh2OJpEkDzXCgoyI implements FileFilter {
    public static final /* synthetic */ $$Lambda$MainActivity$ciyU20oH4zgh2OJpEkDzXCgoyI INSTANCE = new $$Lambda$MainActivity$ciyU20oH4zgh2OJpEkDzXCgoyI();

    private /* synthetic */ $$Lambda$MainActivity$ciyU20oH4zgh2OJpEkDzXCgoyI() {
    }

    @Override // java.io.FileFilter
    public final boolean accept(File file) {
        boolean equalsIgnoreCase;
        equalsIgnoreCase = "mp4".equalsIgnoreCase(FileUtils.getExtensionName(file.getPath()));
        return equalsIgnoreCase;
    }
}
