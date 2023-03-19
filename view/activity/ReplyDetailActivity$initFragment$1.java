package com.movieboxpro.android.view.activity;

import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.widget.EditText;
import android.widget.TextView;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.mvp.BaseContract;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.KeyboardUtils;
import com.movieboxpro.android.utils.SpanUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.ReplyDetailActivity;
import com.snowtop.diskpanda.view.widget.at.MethodContext;
import com.snowtop.diskpanda.view.widget.at.User;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ReplyDetailActivity.kt */
@Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016J\b\u0010\u0007\u001a\u00020\u0003H\u0016J\u001a\u0010\b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\nH\u0016J$\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\r\u001a\u00020\nH\u0016J\b\u0010\u000e\u001a\u00020\u0003H\u0016¨\u0006\u000f"}, d2 = {"com/movieboxpro/android/view/activity/ReplyDetailActivity$initFragment$1", "Lcom/movieboxpro/android/view/activity/ReplyDetailActivity$ReplyDetailListFragment$LoadingListener;", "at", "", "commentId", "", "username", "hideLoading", "likeReview", "support", "", "onReply", "id", "position", "showLoading", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ReplyDetailActivity$initFragment$1 implements ReplyDetailActivity.ReplyDetailListFragment.LoadingListener {
    final /* synthetic */ ReplyDetailActivity this$0;

    @Override // com.movieboxpro.android.view.activity.ReplyDetailActivity.ReplyDetailListFragment.LoadingListener
    public void hideLoading() {
    }

    @Override // com.movieboxpro.android.view.activity.ReplyDetailActivity.ReplyDetailListFragment.LoadingListener
    public void showLoading() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReplyDetailActivity$initFragment$1(ReplyDetailActivity replyDetailActivity) {
        this.this$0 = replyDetailActivity;
    }

    @Override // com.movieboxpro.android.view.activity.ReplyDetailActivity.ReplyDetailListFragment.LoadingListener
    public void onReply(String str, String str2, int i) {
        String str3;
        SpanUtils with = SpanUtils.with((TextView) this.this$0._$_findCachedViewById(R.id.tvReplyAt));
        Intrinsics.checkNotNullExpressionValue(with, "with(tvReplyAt)");
        SpanUtils addText = CommonExtKt.addText(with, "Reply ", 16, R.color.white30_transparent);
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
        EditText etContent = (EditText) this.this$0._$_findCachedViewById(R.id.etContent);
        Intrinsics.checkNotNullExpressionValue(etContent, "etContent");
        CommonExtKt.visible(etContent);
        str3 = this.this$0.commentId;
        if (Intrinsics.areEqual(str3, str)) {
            this.this$0.replyId = "";
            this.this$0.currReplyPos = i;
        } else {
            this.this$0.replyId = str;
            this.this$0.currReplyPos = i;
        }
        final ReplyDetailActivity replyDetailActivity = this.this$0;
        ((EditText) this.this$0._$_findCachedViewById(R.id.etContent)).postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyDetailActivity$initFragment$1$6NatvqIyfK7XVz5asWAMmJU42dU
            @Override // java.lang.Runnable
            public final void run() {
                ReplyDetailActivity$initFragment$1.m267onReply$lambda0(ReplyDetailActivity.this);
            }
        }, 200L);
        KeyboardUtils.showSoftInput((EditText) this.this$0._$_findCachedViewById(R.id.etContent));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onReply$lambda-0  reason: not valid java name */
    public static final void m267onReply$lambda0(ReplyDetailActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        EditText editText = (EditText) this$0._$_findCachedViewById(R.id.etContent);
        if (editText == null) {
            return;
        }
        editText.requestFocus();
    }

    @Override // com.movieboxpro.android.view.activity.ReplyDetailActivity.ReplyDetailListFragment.LoadingListener
    public void likeReview(String str, int i) {
        BaseContract.BasePresenter basePresenter;
        int i2;
        basePresenter = this.this$0.mPresenter;
        i2 = this.this$0.boxType;
        ((ReplyDetailPresenter) basePresenter).likeReview(str, i, i2);
    }

    @Override // com.movieboxpro.android.view.activity.ReplyDetailActivity.ReplyDetailListFragment.LoadingListener
    public void at(String str, String str2) {
        MethodContext methodContext;
        if (((User[]) ((EditText) this.this$0._$_findCachedViewById(R.id.etContent)).getText().getSpans(0, ((EditText) this.this$0._$_findCachedViewById(R.id.etContent)).length(), User.class)).length >= 10) {
            ToastUtils.showShort("Choose up to 10 at a time", new Object[0]);
        } else if (((EditText) this.this$0._$_findCachedViewById(R.id.etContent)).isFocused()) {
            if (str2 == null) {
                str2 = "";
            }
            User user = new User("", str2);
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
