package com.movieboxpro.android.view.activity.tv;

import com.movieboxpro.android.model.BaseMediaModel;
import io.reactivex.functions.Function;
/* compiled from: lambda */
/* renamed from: com.movieboxpro.android.view.activity.tv.-$$Lambda$TvDownloadActivity$2opd90Z6e70bNj5M3jWEHLIUyBU  reason: invalid class name */
/* loaded from: classes3.dex */
public final /* synthetic */ class $$Lambda$TvDownloadActivity$2opd90Z6e70bNj5M3jWEHLIUyBU implements Function {
    public static final /* synthetic */ $$Lambda$TvDownloadActivity$2opd90Z6e70bNj5M3jWEHLIUyBU INSTANCE = new $$Lambda$TvDownloadActivity$2opd90Z6e70bNj5M3jWEHLIUyBU();

    private /* synthetic */ $$Lambda$TvDownloadActivity$2opd90Z6e70bNj5M3jWEHLIUyBU() {
    }

    @Override // io.reactivex.functions.Function
    public final Object apply(Object obj) {
        Boolean m840checkEnoughSpace$lambda5;
        m840checkEnoughSpace$lambda5 = TvDownloadActivity.m840checkEnoughSpace$lambda5((BaseMediaModel.DownloadFile) obj);
        return m840checkEnoughSpace$lambda5;
    }
}
