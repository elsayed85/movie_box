package com.movieboxpro.android.view.fragment;

import com.dueeeke.model.EncodeModel;
import com.dueeeke.model.SrtPraseModel;
import com.movieboxpro.android.databinding.FragmentTranscodeSubtitleBinding;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.view.videocontroller.TransCodingSubtitleController;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TranscodeSubtitleFragment.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012>\u0010\u0002\u001a:\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0004 \u0007*\u001c\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "it", "Lkotlin/Pair;", "", "Lcom/dueeeke/model/EncodeModel;", "Lcom/dueeeke/model/SrtPraseModel;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TranscodeSubtitleFragment$getTranscodeData$5 extends Lambda implements Function1<Pair<? extends List<? extends EncodeModel>, ? extends List<? extends SrtPraseModel>>, Unit> {
    final /* synthetic */ TranscodeSubtitleFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TranscodeSubtitleFragment$getTranscodeData$5(TranscodeSubtitleFragment transcodeSubtitleFragment) {
        super(1);
        this.this$0 = transcodeSubtitleFragment;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Pair<? extends List<? extends EncodeModel>, ? extends List<? extends SrtPraseModel>> pair) {
        invoke2(pair);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(Pair<? extends List<? extends EncodeModel>, ? extends List<? extends SrtPraseModel>> pair) {
        FragmentTranscodeSubtitleBinding fragmentTranscodeSubtitleBinding;
        FragmentTranscodeSubtitleBinding fragmentTranscodeSubtitleBinding2;
        this.this$0.hideLoadingView();
        fragmentTranscodeSubtitleBinding = this.this$0.binding;
        FragmentTranscodeSubtitleBinding fragmentTranscodeSubtitleBinding3 = null;
        if (fragmentTranscodeSubtitleBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentTranscodeSubtitleBinding = null;
        }
        TransCodingSubtitleController transCodingSubtitleController = fragmentTranscodeSubtitleBinding.controller;
        Intrinsics.checkNotNullExpressionValue(transCodingSubtitleController, "binding.controller");
        CommonExtKt.visible(transCodingSubtitleController);
        fragmentTranscodeSubtitleBinding2 = this.this$0.binding;
        if (fragmentTranscodeSubtitleBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentTranscodeSubtitleBinding3 = fragmentTranscodeSubtitleBinding2;
        }
        fragmentTranscodeSubtitleBinding3.controller.setSubtitleData((List) pair.getSecond(), (List) pair.getFirst());
    }
}
