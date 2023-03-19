package com.movieboxpro.android.view.activity.review;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.movieboxpro.android.R;
import com.movieboxpro.android.listener.OnReplyClickListener;
import com.movieboxpro.android.model.ReviewModel;
import com.movieboxpro.android.utils.InputMethodUtils;
import com.movieboxpro.android.view.activity.review.ActorReviewActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ActorReviewActivity.kt */
@Metadata(d1 = {"\u0000-\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\"\u0010\u0006\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001a\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\u0010"}, d2 = {"com/movieboxpro/android/view/activity/review/ActorReviewActivity$initData$1", "Lcom/movieboxpro/android/listener/OnReplyClickListener;", "goSingleReview", "", "pid", "", "onMoreActionClicked", "model", "Lcom/movieboxpro/android/model/ReviewModel;", "view", "Landroid/view/View;", "position", "", "onReplyClicked", "y", "request", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ActorReviewActivity$initData$1 implements OnReplyClickListener {
    final /* synthetic */ ActorReviewActivity this$0;

    @Override // com.movieboxpro.android.listener.OnReplyClickListener
    public void goSingleReview(String str) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ActorReviewActivity$initData$1(ActorReviewActivity actorReviewActivity) {
        this.this$0 = actorReviewActivity;
    }

    @Override // com.movieboxpro.android.listener.OnReplyClickListener
    public void onReplyClicked(final int i, ReviewModel reviewModel) {
        ((EditText) this.this$0._$_findCachedViewById(R.id.etContent)).requestFocus();
        InputMethodUtils.showSoftInput((EditText) this.this$0._$_findCachedViewById(R.id.etContent));
        final ActorReviewActivity actorReviewActivity = this.this$0;
        ((EditText) this.this$0._$_findCachedViewById(R.id.etContent)).postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$ActorReviewActivity$initData$1$aLBXfqWyk_2RERdaY1tNZYHfF4U
            @Override // java.lang.Runnable
            public final void run() {
                ActorReviewActivity$initData$1.m426onReplyClicked$lambda0(ActorReviewActivity.this, i);
            }
        }, 500L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onReplyClicked$lambda-0  reason: not valid java name */
    public static final void m426onReplyClicked$lambda0(ActorReviewActivity this$0, int i) {
        ActorReviewActivity.ReviewFragment reviewFragment;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int[] iArr = new int[2];
        LinearLayout linearLayout = (LinearLayout) this$0._$_findCachedViewById(R.id.container);
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.getLocationOnScreen(iArr);
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
