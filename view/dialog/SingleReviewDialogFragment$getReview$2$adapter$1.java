package com.movieboxpro.android.view.dialog;

import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.model.ReviewModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.TimeUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* compiled from: SingleReviewDialogFragment.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "reviewModel", "Lcom/movieboxpro/android/model/ReviewModel;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class SingleReviewDialogFragment$getReview$2$adapter$1 extends Lambda implements Function2<BaseViewHolder, ReviewModel, Unit> {
    final /* synthetic */ SingleReviewDialogFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SingleReviewDialogFragment$getReview$2$adapter$1(SingleReviewDialogFragment singleReviewDialogFragment) {
        super(2);
        this.this$0 = singleReviewDialogFragment;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(BaseViewHolder baseViewHolder, ReviewModel reviewModel) {
        invoke2(baseViewHolder, reviewModel);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(BaseViewHolder helper, ReviewModel reviewModel) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(reviewModel, "reviewModel");
        SingleReviewDialogFragment singleReviewDialogFragment = this.this$0;
        ImageView imageView = (ImageView) helper.getView(R.id.ivAvatar);
        TextView textView = (TextView) helper.getView(R.id.tvNickname);
        TextView textView2 = (TextView) helper.getView(R.id.tvTime);
        ImageView imageView2 = (ImageView) helper.getView(R.id.ivLike);
        TextView textView3 = (TextView) helper.getView(R.id.tvLikeNum);
        ImageView imageView3 = (ImageView) helper.getView(R.id.ivMore);
        CommonExtKt.gone((TextView) helper.getView(R.id.tvTitle));
        if (reviewModel.getSupport() == 0) {
            CommonExtKt.invisible(textView3);
        } else {
            CommonExtKt.visible(textView3);
            textView3.setText(String.valueOf(reviewModel.getSupport()));
        }
        GlideUtils.load(singleReviewDialogFragment.getContext(), reviewModel.getAvatar(), imageView, (int) R.drawable.ic_default_avatar);
        textView.setText(reviewModel.getAuthor());
        textView2.setText(TimeUtils.formatTime(reviewModel.getAdd_time() * 1000));
        textView3.setText(String.valueOf(reviewModel.getSupport()));
        if (reviewModel.getStatus() == 1) {
            imageView2.setImageResource(R.mipmap.ic_liked);
            CommonExtKt.textColor(textView3, R.color.color_main_blue);
        } else {
            imageView2.setImageResource(R.mipmap.ic_do_like);
            CommonExtKt.textColor(textView3, R.color.white_70alpha);
        }
        WebView webView = (WebView) helper.getView(R.id.webView);
        webView.setBackgroundColor(0);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);
        String message = reviewModel.getMessage();
        Intrinsics.checkNotNullExpressionValue(message, "it.message");
        singleReviewDialogFragment.setHtml(webView, message);
    }
}
