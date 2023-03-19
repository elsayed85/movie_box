package com.movieboxpro.android.view.dialog;

import com.movieboxpro.android.utils.ToastUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
/* compiled from: ReviewDialogPresenter.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class ReviewDialogPresenter$sendReview$3 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ String $atId;
    final /* synthetic */ ReviewDialogPresenter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReviewDialogPresenter$sendReview$3(ReviewDialogPresenter reviewDialogPresenter, String str) {
        super(1);
        this.this$0 = reviewDialogPresenter;
        this.$atId = str;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(String str) {
        invoke2(str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        this.this$0.getView().hideLoadingView();
        this.this$0.getView().reviewSuccess();
        String str = this.$atId;
        if (str == null || StringsKt.isBlank(str)) {
            ToastUtils.showShort("Review sent", new Object[0]);
        } else {
            ToastUtils.showShort("Reply sent", new Object[0]);
        }
    }
}
