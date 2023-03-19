package com.movieboxpro.android.view.dialog;

import android.widget.TextView;
import com.movieboxpro.android.databinding.AddTvWatchPlanPopLayoutBinding;
import com.movieboxpro.android.listener.AddWatchPlanListener;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.TimeUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AddMovieWatchDialog.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AddMovieWatchDialog$changeWatched$3 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ int $watched;
    final /* synthetic */ AddMovieWatchDialog this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddMovieWatchDialog$changeWatched$3(AddMovieWatchDialog addMovieWatchDialog, int i) {
        super(1);
        this.this$0 = addMovieWatchDialog;
        this.$watched = i;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(String str) {
        invoke2(str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(String it) {
        AddTvWatchPlanPopLayoutBinding addTvWatchPlanPopLayoutBinding;
        long time;
        AddWatchPlanListener addWatchPlanListener;
        long time2;
        Intrinsics.checkNotNullParameter(it, "it");
        this.this$0.hideLoadingView();
        this.this$0.setWatched(this.$watched);
        addTvWatchPlanPopLayoutBinding = this.this$0.binding;
        if (addTvWatchPlanPopLayoutBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            addTvWatchPlanPopLayoutBinding = null;
        }
        int i = this.$watched;
        AddMovieWatchDialog addMovieWatchDialog = this.this$0;
        if (i == 0) {
            TextView tvWaitingAdded = addTvWatchPlanPopLayoutBinding.tvWaitingAdded;
            Intrinsics.checkNotNullExpressionValue(tvWaitingAdded, "tvWaitingAdded");
            CommonExtKt.visible(tvWaitingAdded);
            TextView tvWaitingTime = addTvWatchPlanPopLayoutBinding.tvWaitingTime;
            Intrinsics.checkNotNullExpressionValue(tvWaitingTime, "tvWaitingTime");
            CommonExtKt.visible(tvWaitingTime);
            TextView tvWatchedAdded = addTvWatchPlanPopLayoutBinding.tvWatchedAdded;
            Intrinsics.checkNotNullExpressionValue(tvWatchedAdded, "tvWatchedAdded");
            CommonExtKt.gone(tvWatchedAdded);
            TextView tvWatchedTime = addTvWatchPlanPopLayoutBinding.tvWatchedTime;
            Intrinsics.checkNotNullExpressionValue(tvWatchedTime, "tvWatchedTime");
            CommonExtKt.gone(tvWatchedTime);
            TextView textView = addTvWatchPlanPopLayoutBinding.tvWaitingTime;
            TimeUtils timeUtils = TimeUtils.INSTANCE;
            time = addMovieWatchDialog.getTime();
            textView.setText(timeUtils.formatPlayTime(time * 1000));
        } else if (i == 1) {
            TextView tvWatchedAdded2 = addTvWatchPlanPopLayoutBinding.tvWatchedAdded;
            Intrinsics.checkNotNullExpressionValue(tvWatchedAdded2, "tvWatchedAdded");
            CommonExtKt.visible(tvWatchedAdded2);
            TextView tvWatchedTime2 = addTvWatchPlanPopLayoutBinding.tvWatchedTime;
            Intrinsics.checkNotNullExpressionValue(tvWatchedTime2, "tvWatchedTime");
            CommonExtKt.visible(tvWatchedTime2);
            TextView tvWaitingAdded2 = addTvWatchPlanPopLayoutBinding.tvWaitingAdded;
            Intrinsics.checkNotNullExpressionValue(tvWaitingAdded2, "tvWaitingAdded");
            CommonExtKt.gone(tvWaitingAdded2);
            TextView tvWaitingTime2 = addTvWatchPlanPopLayoutBinding.tvWaitingTime;
            Intrinsics.checkNotNullExpressionValue(tvWaitingTime2, "tvWaitingTime");
            CommonExtKt.gone(tvWaitingTime2);
            TextView textView2 = addTvWatchPlanPopLayoutBinding.tvWatchedTime;
            TimeUtils timeUtils2 = TimeUtils.INSTANCE;
            time2 = addMovieWatchDialog.getTime();
            textView2.setText(timeUtils2.formatPlayTime(time2 * 1000));
        }
        addWatchPlanListener = this.this$0.listener;
        if (addWatchPlanListener == null) {
            return;
        }
        addWatchPlanListener.onWatchChanged(this.$watched);
    }
}
