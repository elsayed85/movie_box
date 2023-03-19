package com.movieboxpro.android.view.videocontroller.fragment;

import com.avery.subtitle.model.Subtitle;
import com.dueeeke.model.SrtPraseModel;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* compiled from: TranslateSubtitlePresenter.kt */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class TranslateSubtitlePresenter$startTranslate$4 extends Lambda implements Function0<Unit> {
    final /* synthetic */ ArrayList<ArrayList<Subtitle>> $subtitleList;
    final /* synthetic */ ArrayList<SrtPraseModel> $subtitleMode;
    final /* synthetic */ TranslateSubtitlePresenter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TranslateSubtitlePresenter$startTranslate$4(ArrayList<ArrayList<Subtitle>> arrayList, TranslateSubtitlePresenter translateSubtitlePresenter, ArrayList<SrtPraseModel> arrayList2) {
        super(0);
        this.$subtitleList = arrayList;
        this.this$0 = translateSubtitlePresenter;
        this.$subtitleMode = arrayList2;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2() {
        ArrayList<SrtPraseModel> arrayList = this.$subtitleMode;
        Iterator<T> it = this.$subtitleList.iterator();
        while (it.hasNext()) {
            for (Subtitle subtitle : (ArrayList) it.next()) {
                SrtPraseModel srtPraseModel = new SrtPraseModel();
                srtPraseModel.setBeginTime(subtitle.start.mseconds);
                srtPraseModel.setEndTime(subtitle.end.mseconds);
                srtPraseModel.setSrtBody(subtitle.content);
                arrayList.add(srtPraseModel);
            }
        }
        this.this$0.getView().showSubtitles(this.$subtitleMode);
        this.this$0.getView().dismissProgress();
    }
}
