package com.movieboxpro.android.view.activity.review;

import com.movieboxpro.android.model.movie.NormalFilmModel;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
/* compiled from: lambda */
/* renamed from: com.movieboxpro.android.view.activity.review.-$$Lambda$EditReviewActivity$Kpe0ruxY0Q39DaBfIWg2hGu4cnY  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$EditReviewActivity$Kpe0ruxY0Q39DaBfIWg2hGu4cnY implements Function {
    public static final /* synthetic */ $$Lambda$EditReviewActivity$Kpe0ruxY0Q39DaBfIWg2hGu4cnY INSTANCE = new $$Lambda$EditReviewActivity$Kpe0ruxY0Q39DaBfIWg2hGu4cnY();

    private /* synthetic */ $$Lambda$EditReviewActivity$Kpe0ruxY0Q39DaBfIWg2hGu4cnY() {
    }

    @Override // io.reactivex.functions.Function
    public final Object apply(Object obj) {
        ObservableSource m432getAltVideos$lambda39;
        m432getAltVideos$lambda39 = EditReviewActivity.m432getAltVideos$lambda39((NormalFilmModel) obj);
        return m432getAltVideos$lambda39;
    }
}
