package com.movieboxpro.android.view.videocontroller;

import com.avery.subtitle.widget.SimpleSubtitleView;
import com.dueeeke.model.ResponseSubtitleRecord;
import com.dueeeke.model.SRTModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PopupManager.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/dueeeke/model/ResponseSubtitleRecord;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PopupManager$getSubtitles$2 extends Lambda implements Function1<ResponseSubtitleRecord, Unit> {
    final /* synthetic */ PopupManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PopupManager$getSubtitles$2(PopupManager popupManager) {
        super(1);
        this.this$0 = popupManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: invoke$lambda-1$lambda-0  reason: not valid java name */
    public static final void m1347invoke$lambda1$lambda0() {
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(ResponseSubtitleRecord responseSubtitleRecord) {
        invoke2(responseSubtitleRecord);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(ResponseSubtitleRecord responseSubtitleRecord) {
        SimpleSubtitleView simpleSubtitleView;
        SRTModel.SubTitles select = responseSubtitleRecord.getSelect();
        if (select == null) {
            return;
        }
        simpleSubtitleView = this.this$0.subtitleView;
        if (simpleSubtitleView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleView");
            simpleSubtitleView = null;
        }
        simpleSubtitleView.setSubtitlePath(select.file_path, $$Lambda$PopupManager$getSubtitles$2$I7aCJIFcKss8KtKM9SWDdmnkGfI.INSTANCE);
    }
}
