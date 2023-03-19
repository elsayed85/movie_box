package com.movieboxpro.android.view.videocontroller.fragment;

import com.movieboxpro.android.R;
import com.movieboxpro.android.view.videocontroller.CcController;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SubtitleListFragment.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SubtitleListFragment$initListener$2$3 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ int $position;
    final /* synthetic */ int $status;
    final /* synthetic */ SubtitleListFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SubtitleListFragment$initListener$2$3(SubtitleListFragment subtitleListFragment, int i, int i2) {
        super(1);
        this.this$0 = subtitleListFragment;
        this.$position = i;
        this.$status = i2;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(String str) {
        invoke2(str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        this.this$0.hideLoading();
        ((CcController) this.this$0._$_findCachedViewById(R.id.subtitleController)).updateLikeStatus(this.$position, this.$status);
    }
}
