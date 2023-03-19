package com.movieboxpro.android.view.activity;

import com.movieboxpro.android.base.CommBaseAdapter;
import com.movieboxpro.android.view.fragment.TranscodeSubtitleFragment;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PreviewSubtitleDialog.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PreviewSubtitleDialog$initListener$2$3 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ PreviewSubtitleDialog this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PreviewSubtitleDialog$initListener$2$3(PreviewSubtitleDialog previewSubtitleDialog) {
        super(1);
        this.this$0 = previewSubtitleDialog;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(String str) {
        invoke2(str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(String it) {
        TranscodeSubtitleFragment.OnSelectSubtitleListener onSelectSubtitleListener;
        CommBaseAdapter commBaseAdapter;
        Intrinsics.checkNotNullParameter(it, "it");
        this.this$0.hideLoadingView();
        onSelectSubtitleListener = this.this$0.selectListener;
        if (onSelectSubtitleListener != null) {
            commBaseAdapter = this.this$0.adapter;
            if (commBaseAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                commBaseAdapter = null;
            }
            onSelectSubtitleListener.onSelectSubtitle(commBaseAdapter.getData());
        }
        this.this$0.dismissAllowingStateLoss();
    }
}
