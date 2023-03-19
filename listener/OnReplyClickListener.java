package com.movieboxpro.android.listener;

import android.view.View;
import com.movieboxpro.android.model.ReviewModel;
/* loaded from: classes3.dex */
public interface OnReplyClickListener {
    void goSingleReview(String str);

    void onMoreActionClicked(ReviewModel reviewModel, View view, int i);

    void onReplyClicked(int i, ReviewModel reviewModel);
}
