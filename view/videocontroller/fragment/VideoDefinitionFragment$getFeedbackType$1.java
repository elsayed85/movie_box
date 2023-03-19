package com.movieboxpro.android.view.videocontroller.fragment;

import com.adorkable.iosdialog.ActionSheetDialog;
import com.movieboxpro.android.base.BaseObserver;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.model.common.Feedback;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.ToastUtils;
import io.reactivex.disposables.Disposable;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: VideoDefinitionFragment.kt */
@Metadata(d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u0016\u0010\t\u001a\u00020\u00052\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"com/movieboxpro/android/view/videocontroller/fragment/VideoDefinitionFragment$getFeedbackType$1", "Lcom/movieboxpro/android/base/BaseObserver;", "", "Lcom/movieboxpro/android/model/common/Feedback;", "onComplete", "", "onError", "e", "Lcom/movieboxpro/android/http/ApiException;", "onNext", "list", "onSubscribe", "d", "Lio/reactivex/disposables/Disposable;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class VideoDefinitionFragment$getFeedbackType$1 extends BaseObserver<List<? extends Feedback>> {
    final /* synthetic */ VideoDefinitionFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public VideoDefinitionFragment$getFeedbackType$1(VideoDefinitionFragment videoDefinitionFragment) {
        this.this$0 = videoDefinitionFragment;
    }

    @Override // com.movieboxpro.android.base.BaseObserver
    public void onError(ApiException apiException) {
        ToastUtils.showShort(Intrinsics.stringPlus("load error", apiException == null ? null : apiException.getMessage()), new Object[0]);
    }

    @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
    public void onSubscribe(Disposable d) {
        Intrinsics.checkNotNullParameter(d, "d");
        super.onSubscribe(d);
        this.this$0.showLoadingView();
    }

    @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
    public void onNext(List<? extends Feedback> list) {
        ActionSheetDialog actionSheetDialog;
        ActionSheetDialog actionSheetDialog2;
        ActionSheetDialog actionSheetDialog3;
        Intrinsics.checkNotNullParameter(list, "list");
        super.onNext((VideoDefinitionFragment$getFeedbackType$1) list);
        VideoDefinitionFragment videoDefinitionFragment = this.this$0;
        ActionSheetDialog canceledOnTouchOutside = new ActionSheetDialog(videoDefinitionFragment.getContext()).builder().setCancelable(true).setCanceledOnTouchOutside(true);
        Intrinsics.checkNotNullExpressionValue(canceledOnTouchOutside, "ActionSheetDialog(contex…celedOnTouchOutside(true)");
        videoDefinitionFragment.actionDialog = canceledOnTouchOutside;
        final VideoDefinitionFragment videoDefinitionFragment2 = this.this$0;
        Iterator<T> it = list.iterator();
        while (true) {
            actionSheetDialog = null;
            if (!it.hasNext()) {
                break;
            }
            final Feedback feedback = (Feedback) it.next();
            actionSheetDialog3 = videoDefinitionFragment2.actionDialog;
            if (actionSheetDialog3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("actionDialog");
            } else {
                actionSheetDialog = actionSheetDialog3;
            }
            actionSheetDialog.addSheetItem(feedback.name, ActionSheetDialog.SheetItemColor.White, new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.videocontroller.fragment.-$$Lambda$VideoDefinitionFragment$getFeedbackType$1$1_0ZM1Hg5-NQKCMoJ97SAZcbEBA
                @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
                public final void onClick(int i) {
                    VideoDefinitionFragment$getFeedbackType$1.m1414onNext$lambda1$lambda0(Feedback.this, videoDefinitionFragment2, i);
                }
            });
        }
        actionSheetDialog2 = this.this$0.actionDialog;
        if (actionSheetDialog2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("actionDialog");
        } else {
            actionSheetDialog = actionSheetDialog2;
        }
        actionSheetDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onNext$lambda-1$lambda-0  reason: not valid java name */
    public static final void m1414onNext$lambda1$lambda0(Feedback feedback, VideoDefinitionFragment this$0, int i) {
        Intrinsics.checkNotNullParameter(feedback, "$feedback");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (feedback.ftid == 0) {
            this$0.showFeedbackDialog(feedback);
            return;
        }
        int i2 = feedback.state;
        int i3 = feedback.ftid;
        String msg = SystemUtils.getMsg(this$0.getActivity());
        Intrinsics.checkNotNullExpressionValue(msg, "getMsg(activity)");
        this$0.feedbackError(i2, i3, msg);
    }

    @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
    public void onComplete() {
        super.onComplete();
        this.this$0.hideLoadingView();
    }
}
