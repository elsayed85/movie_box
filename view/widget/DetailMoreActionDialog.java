package com.movieboxpro.android.view.widget;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import com.movieboxpro.android.R;
import com.movieboxpro.android.view.dialog.BaseCenterDialog;
import com.movieboxpro.android.view.dialog.DialogAction;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: DetailMoreActionDialog.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\tH\u0016J\b\u0010\u000f\u001a\u00020\tH\u0016R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/movieboxpro/android/view/widget/DetailMoreActionDialog;", "Lcom/movieboxpro/android/view/dialog/BaseCenterDialog;", "context", "Landroid/content/Context;", "shareListener", "Lcom/movieboxpro/android/view/dialog/DialogAction$ActionListener;", "feedbackListener", "(Landroid/content/Context;Lcom/movieboxpro/android/view/dialog/DialogAction$ActionListener;Lcom/movieboxpro/android/view/dialog/DialogAction$ActionListener;)V", "initData", "", "initDialogWidth", "", "initLayoutId", "", "initListener", "initView", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DetailMoreActionDialog extends BaseCenterDialog {
    private final DialogAction.ActionListener feedbackListener;
    private final DialogAction.ActionListener shareListener;

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialog
    public void initData() {
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialog
    public float initDialogWidth() {
        return 0.6f;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialog
    public int initLayoutId() {
        return R.layout.dialog_detail_more_action;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialog
    public void initListener() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DetailMoreActionDialog(Context context, DialogAction.ActionListener shareListener, DialogAction.ActionListener feedbackListener) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(shareListener, "shareListener");
        Intrinsics.checkNotNullParameter(feedbackListener, "feedbackListener");
        this.shareListener = shareListener;
        this.feedbackListener = feedbackListener;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialog
    public void initView() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.llShare);
        if (linearLayout != null) {
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.widget.-$$Lambda$DetailMoreActionDialog$CxP2T3rwwEBrtr8yjvTtjtXKjsQ
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DetailMoreActionDialog.m1427initView$lambda0(DetailMoreActionDialog.this, view);
                }
            });
        }
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.llFeedback);
        if (linearLayout2 == null) {
            return;
        }
        linearLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.widget.-$$Lambda$DetailMoreActionDialog$2vWKVlZx6m6hu9m1wEWhUN1eHD4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DetailMoreActionDialog.m1428initView$lambda1(DetailMoreActionDialog.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-0  reason: not valid java name */
    public static final void m1427initView$lambda0(DetailMoreActionDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.shareListener.onClick();
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-1  reason: not valid java name */
    public static final void m1428initView$lambda1(DetailMoreActionDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.feedbackListener.onClick();
        this$0.dismiss();
    }
}
