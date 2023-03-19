package com.movieboxpro.android.view.videocontroller.fragment;

import com.dueeeke.model.MediaQualityInfo;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* compiled from: VideoDefinitionFragment.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "t", "Lcom/dueeeke/model/MediaQualityInfo;", "invoke", "(Lcom/dueeeke/model/MediaQualityInfo;)Ljava/lang/Integer;"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class VideoDefinitionFragment$initData$5 extends Lambda implements Function1<MediaQualityInfo, Integer> {
    public static final VideoDefinitionFragment$initData$5 INSTANCE = new VideoDefinitionFragment$initData$5();

    VideoDefinitionFragment$initData$5() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Integer invoke(MediaQualityInfo t) {
        Intrinsics.checkNotNullParameter(t, "t");
        return Integer.valueOf(t.getViewType());
    }
}
