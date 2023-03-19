package com.movieboxpro.android.view.dialog;

import com.movieboxpro.android.model.ReportReason;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* compiled from: ReviewDialogPresenter.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012*\u0010\u0002\u001a&\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004 \u0005*\u0012\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/ReportReason;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class ReviewDialogPresenter$getReportReason$4 extends Lambda implements Function1<ArrayList<ReportReason>, Unit> {
    final /* synthetic */ String $commentId;
    final /* synthetic */ ReviewDialogPresenter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReviewDialogPresenter$getReportReason$4(ReviewDialogPresenter reviewDialogPresenter, String str) {
        super(1);
        this.this$0 = reviewDialogPresenter;
        this.$commentId = str;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(ArrayList<ReportReason> arrayList) {
        invoke2(arrayList);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(ArrayList<ReportReason> it) {
        this.this$0.getView().hideLoadingView();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this.this$0.getView().showReportReason(it, this.$commentId);
    }
}
