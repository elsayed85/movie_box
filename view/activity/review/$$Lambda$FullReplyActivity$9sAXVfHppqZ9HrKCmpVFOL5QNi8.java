package com.movieboxpro.android.view.activity.review;

import com.movieboxpro.android.model.movie.NormalFilmModel;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
/* compiled from: lambda */
/* renamed from: com.movieboxpro.android.view.activity.review.-$$Lambda$FullReplyActivity$9sAXVfHppqZ9HrKCmpVFOL5QNi8  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$FullReplyActivity$9sAXVfHppqZ9HrKCmpVFOL5QNi8 implements Function {
    public static final /* synthetic */ $$Lambda$FullReplyActivity$9sAXVfHppqZ9HrKCmpVFOL5QNi8 INSTANCE = new $$Lambda$FullReplyActivity$9sAXVfHppqZ9HrKCmpVFOL5QNi8();

    private /* synthetic */ $$Lambda$FullReplyActivity$9sAXVfHppqZ9HrKCmpVFOL5QNi8() {
    }

    @Override // io.reactivex.functions.Function
    public final Object apply(Object obj) {
        ObservableSource m511getAltVideos$lambda34;
        m511getAltVideos$lambda34 = FullReplyActivity.m511getAltVideos$lambda34((NormalFilmModel) obj);
        return m511getAltVideos$lambda34;
    }
}
