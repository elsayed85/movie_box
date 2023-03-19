package com.movieboxpro.android.view.fragment;

import com.dueeeke.model.EncodeModel;
import com.movieboxpro.android.databinding.FragmentTranscodeSubtitleBinding;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.videocontroller.TransCodingSubtitleController;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TranscodeOnlySubtitleFragment.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/movieboxpro/android/http/ApiException;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TranscodeOnlySubtitleFragment$getTranscodeData$3 extends Lambda implements Function1<ApiException, Unit> {
    final /* synthetic */ ArrayList<EncodeModel> $codes;
    final /* synthetic */ TranscodeOnlySubtitleFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TranscodeOnlySubtitleFragment$getTranscodeData$3(TranscodeOnlySubtitleFragment transcodeOnlySubtitleFragment, ArrayList<EncodeModel> arrayList) {
        super(1);
        this.this$0 = transcodeOnlySubtitleFragment;
        this.$codes = arrayList;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(ApiException apiException) {
        invoke2(apiException);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(ApiException it) {
        FragmentTranscodeSubtitleBinding fragmentTranscodeSubtitleBinding;
        FragmentTranscodeSubtitleBinding fragmentTranscodeSubtitleBinding2;
        Intrinsics.checkNotNullParameter(it, "it");
        this.this$0.hideLoadingView();
        ToastUtils.showShort(Intrinsics.stringPlus("Load failed:", it.getMessage()), new Object[0]);
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
        fragmentTranscodeSubtitleBinding3.controller.setSubtitleData(new ArrayList(), this.$codes);
    }
}
