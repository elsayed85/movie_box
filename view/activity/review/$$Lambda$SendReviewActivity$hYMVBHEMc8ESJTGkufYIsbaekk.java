package com.movieboxpro.android.view.activity.review;

import com.movieboxpro.android.model.movie.NormalFilmModel;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
/* compiled from: lambda */
/* renamed from: com.movieboxpro.android.view.activity.review.-$$Lambda$SendReviewActivity$hYMVBHEMc8ESJTGkufYIs-baekk  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$SendReviewActivity$hYMVBHEMc8ESJTGkufYIsbaekk implements Function {
    public static final /* synthetic */ $$Lambda$SendReviewActivity$hYMVBHEMc8ESJTGkufYIsbaekk INSTANCE = new $$Lambda$SendReviewActivity$hYMVBHEMc8ESJTGkufYIsbaekk();

    private /* synthetic */ $$Lambda$SendReviewActivity$hYMVBHEMc8ESJTGkufYIsbaekk() {
    }

    @Override // io.reactivex.functions.Function
    public final Object apply(Object obj) {
        ObservableSource m708getAltVideos$lambda41;
        m708getAltVideos$lambda41 = SendReviewActivity.m708getAltVideos$lambda41((NormalFilmModel) obj);
        return m708getAltVideos$lambda41;
    }
}
