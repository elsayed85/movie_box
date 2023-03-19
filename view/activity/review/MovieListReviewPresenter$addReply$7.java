package com.movieboxpro.android.view.activity.review;

import com.movieboxpro.android.model.BBsResponseModel;
import com.movieboxpro.android.utils.ToastUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* compiled from: MovieListReviewPresenter.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/movieboxpro/android/model/BBsResponseModel;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class MovieListReviewPresenter$addReply$7 extends Lambda implements Function1<BBsResponseModel, Unit> {
    final /* synthetic */ MovieListReviewPresenter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MovieListReviewPresenter$addReply$7(MovieListReviewPresenter movieListReviewPresenter) {
        super(1);
        this.this$0 = movieListReviewPresenter;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(BBsResponseModel bBsResponseModel) {
        invoke2(bBsResponseModel);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(BBsResponseModel bBsResponseModel) {
        if (Intrinsics.areEqual("post_reply_succeed", bBsResponseModel.getMessage().getMessageval())) {
            this.this$0.getView().reviewSuccess();
        } else {
            ToastUtils.showShort(bBsResponseModel.getMessage().getMessageval(), new Object[0]);
        }
        this.this$0.getView().hideLoadingView();
    }
}
