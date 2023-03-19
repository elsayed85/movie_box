package com.movieboxpro.android.view.activity.review;

import com.movieboxpro.android.model.movie.NormalFilmModel;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
/* compiled from: lambda */
/* renamed from: com.movieboxpro.android.view.activity.review.-$$Lambda$FullMovieListReplyActivity$fSdHE3sO7UVdH3ejfz6_AVDSJyw  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$FullMovieListReplyActivity$fSdHE3sO7UVdH3ejfz6_AVDSJyw implements Function {
    public static final /* synthetic */ $$Lambda$FullMovieListReplyActivity$fSdHE3sO7UVdH3ejfz6_AVDSJyw INSTANCE = new $$Lambda$FullMovieListReplyActivity$fSdHE3sO7UVdH3ejfz6_AVDSJyw();

    private /* synthetic */ $$Lambda$FullMovieListReplyActivity$fSdHE3sO7UVdH3ejfz6_AVDSJyw() {
    }

    @Override // io.reactivex.functions.Function
    public final Object apply(Object obj) {
        ObservableSource m470getAltVideos$lambda34;
        m470getAltVideos$lambda34 = FullMovieListReplyActivity.m470getAltVideos$lambda34((NormalFilmModel) obj);
        return m470getAltVideos$lambda34;
    }
}
