package com.movieboxpro.android.view.videocontroller.fragment;

import com.movieboxpro.android.model.WifiUploadSubtitleModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
/* compiled from: SearchUploadSubtitlePresenter.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "", "Lcom/movieboxpro/android/model/WifiUploadSubtitleModel;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class SearchUploadSubtitlePresenter$loadSubtitle$2 extends Lambda implements Function1<List<WifiUploadSubtitleModel>, Unit> {
    final /* synthetic */ Ref.IntRef $lastSelectedPosition;
    final /* synthetic */ SearchUploadSubtitlePresenter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchUploadSubtitlePresenter$loadSubtitle$2(SearchUploadSubtitlePresenter searchUploadSubtitlePresenter, Ref.IntRef intRef) {
        super(1);
        this.this$0 = searchUploadSubtitlePresenter;
        this.$lastSelectedPosition = intRef;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(List<WifiUploadSubtitleModel> list) {
        invoke2(list);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(List<WifiUploadSubtitleModel> it) {
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this.this$0.getView().showSubtitles(it, this.$lastSelectedPosition.element);
    }
}
