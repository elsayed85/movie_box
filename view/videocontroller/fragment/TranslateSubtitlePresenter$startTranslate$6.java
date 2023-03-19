package com.movieboxpro.android.view.videocontroller.fragment;

import com.avery.subtitle.model.Subtitle;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
/* compiled from: TranslateSubtitlePresenter.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0003*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "kotlin.jvm.PlatformType", "invoke", "(Lkotlin/Unit;)V"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class TranslateSubtitlePresenter$startTranslate$6 extends Lambda implements Function1<Unit, Unit> {
    final /* synthetic */ Ref.IntRef $progress;
    final /* synthetic */ ArrayList<ArrayList<Subtitle>> $subtitleList;
    final /* synthetic */ TranslateSubtitlePresenter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TranslateSubtitlePresenter$startTranslate$6(Ref.IntRef intRef, TranslateSubtitlePresenter translateSubtitlePresenter, ArrayList<ArrayList<Subtitle>> arrayList) {
        super(1);
        this.$progress = intRef;
        this.this$0 = translateSubtitlePresenter;
        this.$subtitleList = arrayList;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Unit unit) {
        invoke2(unit);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(Unit unit) {
        this.$progress.element++;
        this.this$0.getView().showProgress(this.$progress.element, this.$subtitleList.size());
    }
}
