package com.movieboxpro.android.view.dialog;

import com.movieboxpro.android.model.ReviewItem;
import com.movieboxpro.android.model.ReviewResponse;
import com.movieboxpro.android.view.dialog.ReviewDialogContract;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* compiled from: ReviewDialogPresenter.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/movieboxpro/android/model/ReviewResponse;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class ReviewDialogPresenter$getMoreReviews$3 extends Lambda implements Function1<ReviewResponse, Unit> {
    final /* synthetic */ ReviewDialogPresenter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReviewDialogPresenter$getMoreReviews$3(ReviewDialogPresenter reviewDialogPresenter) {
        super(1);
        this.this$0 = reviewDialogPresenter;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(ReviewResponse reviewResponse) {
        invoke2(reviewResponse);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(ReviewResponse it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ReviewDialogContract.View view = this.this$0.getView();
        List<ReviewItem> list = it.getList();
        if (list == null) {
            list = new ArrayList<>();
        }
        view.showMoreReviews(new ArrayList(list));
    }
}
