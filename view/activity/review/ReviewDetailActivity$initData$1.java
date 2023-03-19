package com.movieboxpro.android.view.activity.review;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.movieboxpro.android.R;
import com.movieboxpro.android.listener.OnReplyClickListener;
import com.movieboxpro.android.model.ReviewModel;
import com.movieboxpro.android.model.ReviewRecordModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.InputMethodUtils;
import com.movieboxpro.android.utils.ReviewRecordUtils;
import com.movieboxpro.android.view.activity.review.ReviewDetailActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
/* compiled from: ReviewDetailActivity.kt */
@Metadata(d1 = {"\u0000-\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\"\u0010\u0006\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001a\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\f2\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\u000f"}, d2 = {"com/movieboxpro/android/view/activity/review/ReviewDetailActivity$initData$1", "Lcom/movieboxpro/android/listener/OnReplyClickListener;", "goSingleReview", "", "pid", "", "onMoreActionClicked", "model", "Lcom/movieboxpro/android/model/ReviewModel;", "view", "Landroid/view/View;", "position", "", "onReplyClicked", "y", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ReviewDetailActivity$initData$1 implements OnReplyClickListener {
    final /* synthetic */ ReviewDetailActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReviewDetailActivity$initData$1(ReviewDetailActivity reviewDetailActivity) {
        this.this$0 = reviewDetailActivity;
    }

    @Override // com.movieboxpro.android.listener.OnReplyClickListener
    public void goSingleReview(String pid) {
        Intrinsics.checkNotNullParameter(pid, "pid");
        SingleReviewActivity.Companion.start(this.this$0, pid, 2);
    }

    @Override // com.movieboxpro.android.listener.OnReplyClickListener
    public void onReplyClicked(final int i, ReviewModel reviewModel) {
        ReviewModel reviewModel2;
        String str;
        String str2;
        String str3;
        String str4;
        boolean z = true;
        if (!(reviewModel != null && reviewModel.getItemType() == 4)) {
            this.this$0.isReplyTo = true;
            this.this$0.currPid = reviewModel != null ? reviewModel.getPid() : null;
            this.this$0.currReviewModel = reviewModel;
            ReviewRecordUtils reviewRecordUtils = ReviewRecordUtils.Companion.get();
            str3 = this.this$0.currPid;
            ReviewRecordModel reviewRecord = reviewRecordUtils.getReviewRecord(str3, "html");
            if (reviewRecord != null) {
                String content = reviewRecord.getContent();
                if (content != null && !StringsKt.isBlank(content)) {
                    z = false;
                }
                if (!z) {
                    this.this$0.goFullReview();
                    return;
                }
            }
            ReviewRecordUtils reviewRecordUtils2 = ReviewRecordUtils.Companion.get();
            str4 = this.this$0.currPid;
            ReviewRecordModel reviewRecord2 = reviewRecordUtils2.getReviewRecord(str4, "text");
            if (reviewRecord2 == null) {
                ((EditText) this.this$0._$_findCachedViewById(R.id.etContent)).requestFocus();
                InputMethodUtils.showSoftInput((EditText) this.this$0._$_findCachedViewById(R.id.etContent));
                final ReviewDetailActivity reviewDetailActivity = this.this$0;
                ((EditText) this.this$0._$_findCachedViewById(R.id.etContent)).postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$ReviewDetailActivity$initData$1$Sns2A0SdHSuyYdM-4Krq37Wd89A
                    @Override // java.lang.Runnable
                    public final void run() {
                        ReviewDetailActivity$initData$1.m649onReplyClicked$lambda0(ReviewDetailActivity.this, this, i);
                    }
                }, 500L);
            } else if (Intrinsics.areEqual(reviewRecord2.getType(), "html")) {
                this.this$0.goFullReview();
            } else {
                ((EditText) this.this$0._$_findCachedViewById(R.id.etContent)).requestFocus();
                InputMethodUtils.showSoftInput((EditText) this.this$0._$_findCachedViewById(R.id.etContent));
                final ReviewDetailActivity reviewDetailActivity2 = this.this$0;
                ((EditText) this.this$0._$_findCachedViewById(R.id.etContent)).postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$ReviewDetailActivity$initData$1$DUn2HTtDvXTdnBMl1oJmpT9a5s0
                    @Override // java.lang.Runnable
                    public final void run() {
                        ReviewDetailActivity$initData$1.m650onReplyClicked$lambda1(ReviewDetailActivity.this, this, i);
                    }
                }, 500L);
            }
        } else if (reviewModel.getItemType() == 4) {
            ReviewDetailActivity reviewDetailActivity3 = this.this$0;
            reviewModel2 = reviewDetailActivity3.topReviewModel;
            reviewDetailActivity3.currPid = reviewModel2 != null ? reviewModel2.getPid() : null;
            ReviewRecordUtils reviewRecordUtils3 = ReviewRecordUtils.Companion.get();
            str = this.this$0.currPid;
            ReviewRecordModel reviewRecord3 = reviewRecordUtils3.getReviewRecord(str, "html");
            if (reviewRecord3 != null) {
                String content2 = reviewRecord3.getContent();
                if (content2 != null && !StringsKt.isBlank(content2)) {
                    z = false;
                }
                if (!z) {
                    this.this$0.goFullReview();
                    return;
                }
            }
            ReviewRecordUtils reviewRecordUtils4 = ReviewRecordUtils.Companion.get();
            str2 = this.this$0.currPid;
            ReviewRecordModel reviewRecord4 = reviewRecordUtils4.getReviewRecord(str2, "text");
            if (reviewRecord4 == null) {
                this.this$0.isReplyTo = false;
                ((EditText) this.this$0._$_findCachedViewById(R.id.etContent)).setText("");
                ((EditText) this.this$0._$_findCachedViewById(R.id.etContent)).requestFocus();
                InputMethodUtils.showSoftInput((EditText) this.this$0._$_findCachedViewById(R.id.etContent));
            } else if (!Intrinsics.areEqual(reviewRecord4.getType(), "text")) {
                this.this$0.goFullReview();
            } else {
                ((EditText) this.this$0._$_findCachedViewById(R.id.etContent)).setText(reviewRecord4.getContent());
                ((EditText) this.this$0._$_findCachedViewById(R.id.etContent)).setSelection(reviewRecord4.getContent().length());
                ((EditText) this.this$0._$_findCachedViewById(R.id.etContent)).requestFocus();
                InputMethodUtils.showSoftInput((EditText) this.this$0._$_findCachedViewById(R.id.etContent));
                this.this$0.isReplyTo = false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onReplyClicked$lambda-0  reason: not valid java name */
    public static final void m649onReplyClicked$lambda0(ReviewDetailActivity this$0, ReviewDetailActivity$initData$1 this$1, int i) {
        ReviewDetailActivity.ReviewFragment reviewFragment;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        int[] iArr = new int[2];
        LinearLayout linearLayout = (LinearLayout) this$0._$_findCachedViewById(R.id.container);
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.getLocationOnScreen(iArr);
        CommonExtKt.logD(this$1, Intrinsics.stringPlus("container y: ", Integer.valueOf(iArr[1])));
        reviewFragment = this$0.fragment;
        if (reviewFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
            reviewFragment = null;
        }
        reviewFragment.recyclerViewScroll(iArr[1], i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onReplyClicked$lambda-1  reason: not valid java name */
    public static final void m650onReplyClicked$lambda1(ReviewDetailActivity this$0, ReviewDetailActivity$initData$1 this$1, int i) {
        ReviewDetailActivity.ReviewFragment reviewFragment;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        int[] iArr = new int[2];
        LinearLayout linearLayout = (LinearLayout) this$0._$_findCachedViewById(R.id.container);
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.getLocationOnScreen(iArr);
        CommonExtKt.logD(this$1, Intrinsics.stringPlus("container y: ", Integer.valueOf(iArr[1])));
        reviewFragment = this$0.fragment;
        if (reviewFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
            reviewFragment = null;
        }
        reviewFragment.recyclerViewScroll(iArr[1], i);
    }

    @Override // com.movieboxpro.android.listener.OnReplyClickListener
    public void onMoreActionClicked(ReviewModel reviewModel, View view, int i) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.this$0.openPopMenu(reviewModel, view, i);
    }
}
