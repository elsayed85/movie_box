package com.movieboxpro.android.view.fragment;

import com.avery.subtitle.model.Subtitle;
import com.movieboxpro.android.view.fragment.TranscodeSubtitleFragment;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* compiled from: TranscodeSubtitleFragment.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class TranscodeSubtitleFragment$initData$1$onDone$3 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ List<Subtitle> $subtitles;
    final /* synthetic */ TranscodeSubtitleFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TranscodeSubtitleFragment$initData$1$onDone$3(TranscodeSubtitleFragment transcodeSubtitleFragment, List<Subtitle> list) {
        super(1);
        this.this$0 = transcodeSubtitleFragment;
        this.$subtitles = list;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(String str) {
        invoke2(str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(String it) {
        TranscodeSubtitleFragment.OnSelectSubtitleListener onSelectSubtitleListener;
        Intrinsics.checkNotNullParameter(it, "it");
        this.this$0.hideLoadingView();
        onSelectSubtitleListener = this.this$0.listener;
        if (onSelectSubtitleListener != null) {
            ArrayList arrayList = this.$subtitles;
            if (arrayList == null) {
                arrayList = new ArrayList();
            }
            onSelectSubtitleListener.onSelectSubtitle(arrayList);
        }
        this.this$0.dismissAllowingStateLoss();
    }
}
