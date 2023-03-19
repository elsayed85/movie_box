package com.movieboxpro.android.view.activity.review;

import com.movieboxpro.android.model.movie.NormalFilmModel;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
/* compiled from: lambda */
/* renamed from: com.movieboxpro.android.view.activity.review.-$$Lambda$SendActorReviewActivity$VQo-AZIiLq3CorBunHBmA2QQsg8  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$SendActorReviewActivity$VQoAZIiLq3CorBunHBmA2QQsg8 implements Function {
    public static final /* synthetic */ $$Lambda$SendActorReviewActivity$VQoAZIiLq3CorBunHBmA2QQsg8 INSTANCE = new $$Lambda$SendActorReviewActivity$VQoAZIiLq3CorBunHBmA2QQsg8();

    private /* synthetic */ $$Lambda$SendActorReviewActivity$VQoAZIiLq3CorBunHBmA2QQsg8() {
    }

    @Override // io.reactivex.functions.Function
    public final Object apply(Object obj) {
        ObservableSource m658getAltVideos$lambda40;
        m658getAltVideos$lambda40 = SendActorReviewActivity.m658getAltVideos$lambda40((NormalFilmModel) obj);
        return m658getAltVideos$lambda40;
    }
}
