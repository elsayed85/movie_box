package com.movieboxpro.android.view.activity;

import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.widget.EditText;
import android.widget.TextView;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.mvp.BaseContract;
import com.movieboxpro.android.listener.ReviewListener;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.KeyboardUtils;
import com.movieboxpro.android.utils.SpanUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.snowtop.diskpanda.view.widget.at.MethodContext;
import com.snowtop.diskpanda.view.widget.at.User;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ReplyActivity.kt */
@Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016J\u001a\u0010\u0007\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\nH\u0016J\u001c\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\r"}, d2 = {"com/movieboxpro/android/view/activity/ReplyActivity$initData$1", "Lcom/movieboxpro/android/listener/ReviewListener;", "at", "", "uid", "", "username", "likeReview", "commentId", "support", "", "onReply", "id", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ReplyActivity$initData$1 implements ReviewListener {
    final /* synthetic */ ReplyActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReplyActivity$initData$1(ReplyActivity replyActivity) {
        this.this$0 = replyActivity;
    }

    @Override // com.movieboxpro.android.listener.ReviewListener
    public void onReply(String str, String str2) {
        SpanUtils with = SpanUtils.with((TextView) this.this$0._$_findCachedViewById(R.id.tvReplyAt));
        Intrinsics.checkNotNullExpressionValue(with, "with(tvReplyAt)");
        SpanUtils addText = CommonExtKt.addText(with, "Reply ", 16, R.color.white_70alpha);
        if (str2 == null) {
            str2 = "";
        }
        CommonExtKt.addText(addText, str2, 16, R.color.color_main_blue).create();
        TextView tvReplyAt = (TextView) this.this$0._$_findCachedViewById(R.id.tvReplyAt);
        Intrinsics.checkNotNullExpressionValue(tvReplyAt, "tvReplyAt");
        CommonExtKt.visible(tvReplyAt);
        TextView tvReply = (TextView) this.this$0._$_findCachedViewById(R.id.tvReply);
        Intrinsics.checkNotNullExpressionValue(tvReply, "tvReply");
        CommonExtKt.gone(tvReply);
        this.this$0.replyId = str;
        final ReplyActivity replyActivity = this.this$0;
        ((EditText) this.this$0._$_findCachedViewById(R.id.etContent)).postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyActivity$initData$1$F70sci6w5ap2GJQb6Id8104IQxo
            @Override // java.lang.Runnable
            public final void run() {
                ReplyActivity$initData$1.m227onReply$lambda0(ReplyActivity.this);
            }
        }, 200L);
        KeyboardUtils.showSoftInput((EditText) this.this$0._$_findCachedViewById(R.id.etContent));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onReply$lambda-0  reason: not valid java name */
    public static final void m227onReply$lambda0(ReplyActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((EditText) this$0._$_findCachedViewById(R.id.etContent)).requestFocus();
    }

    @Override // com.movieboxpro.android.listener.ReviewListener
    public void likeReview(String str, int i) {
        BaseContract.BasePresenter basePresenter;
        int i2;
        basePresenter = this.this$0.mPresenter;
        i2 = this.this$0.boxType;
        ((ReplyPresenter) basePresenter).likeReview(str, i, i2);
    }

    @Override // com.movieboxpro.android.listener.ReviewListener
    public void at(String str, String str2) {
        MethodContext methodContext;
        if (((User[]) ((EditText) this.this$0._$_findCachedViewById(R.id.etContent)).getText().getSpans(0, ((EditText) this.this$0._$_findCachedViewById(R.id.etContent)).length(), User.class)).length >= 10) {
            ToastUtils.showShort("Choose up to 10 at a time", new Object[0]);
        } else if (((EditText) this.this$0._$_findCachedViewById(R.id.etContent)).isFocused()) {
            if (str == null) {
                str = "";
            }
            if (str2 == null) {
                str2 = "";
            }
            User user = new User(str, str2);
            Editable text = ((EditText) this.this$0._$_findCachedViewById(R.id.etContent)).getText();
            if (text != null) {
                methodContext = this.this$0.methodContext;
                ((SpannableStringBuilder) text).append((CharSequence) methodContext.newSpannable(user)).append((CharSequence) " ");
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type android.text.SpannableStringBuilder");
        }
    }
}
