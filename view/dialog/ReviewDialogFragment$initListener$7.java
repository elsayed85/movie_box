package com.movieboxpro.android.view.dialog;

import android.content.Context;
import com.movieboxpro.android.model.ReviewItem;
import com.movieboxpro.android.view.activity.ReplyDetailActivity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* compiled from: ReviewDialogFragment.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "item", "Lcom/movieboxpro/android/model/ReviewItem;", "position", "", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class ReviewDialogFragment$initListener$7 extends Lambda implements Function2<ReviewItem, Integer, Unit> {
    final /* synthetic */ ReviewDialogFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReviewDialogFragment$initListener$7(ReviewDialogFragment reviewDialogFragment) {
        super(2);
        this.this$0 = reviewDialogFragment;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(ReviewItem reviewItem, Integer num) {
        invoke(reviewItem, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(ReviewItem item, int i) {
        String str;
        int i2;
        Intrinsics.checkNotNullParameter(item, "item");
        ReplyDetailActivity.Companion companion = ReplyDetailActivity.Companion;
        Context context = this.this$0.getContext();
        str = this.this$0.id;
        i2 = this.this$0.boxType;
        companion.start(context, str, i2, item.getComment_id(), null);
    }
}
