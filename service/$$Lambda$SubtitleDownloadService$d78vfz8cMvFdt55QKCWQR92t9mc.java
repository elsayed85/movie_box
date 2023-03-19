package com.movieboxpro.android.service;

import io.reactivex.functions.BiFunction;
import kotlin.Pair;
/* compiled from: lambda */
/* renamed from: com.movieboxpro.android.service.-$$Lambda$SubtitleDownloadService$d78vfz8cMvFdt55QKCWQR92t9mc  reason: invalid class name */
/* loaded from: classes3.dex */
public final /* synthetic */ class $$Lambda$SubtitleDownloadService$d78vfz8cMvFdt55QKCWQR92t9mc implements BiFunction {
    public static final /* synthetic */ $$Lambda$SubtitleDownloadService$d78vfz8cMvFdt55QKCWQR92t9mc INSTANCE = new $$Lambda$SubtitleDownloadService$d78vfz8cMvFdt55QKCWQR92t9mc();

    private /* synthetic */ $$Lambda$SubtitleDownloadService$d78vfz8cMvFdt55QKCWQR92t9mc() {
    }

    @Override // io.reactivex.functions.BiFunction
    public final Object apply(Object obj, Object obj2) {
        Pair m66downloadSubtitles$lambda5;
        m66downloadSubtitles$lambda5 = SubtitleDownloadService.m66downloadSubtitles$lambda5((String) obj, (String) obj2);
        return m66downloadSubtitles$lambda5;
    }
}
