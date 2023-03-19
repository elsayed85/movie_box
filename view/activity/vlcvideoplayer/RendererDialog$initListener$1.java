package com.movieboxpro.android.view.activity.vlcvideoplayer;

import com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory;
import com.movieboxpro.android.view.activity.vlcvideoplayer.RendererDialog;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.libvlc.RendererItem;
/* compiled from: RendererDialog.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "item", "Lorg/videolan/libvlc/RendererItem;", "<anonymous parameter 1>", "", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class RendererDialog$initListener$1 extends Lambda implements Function2<RendererItem, Integer, Unit> {
    final /* synthetic */ RendererDialog this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RendererDialog$initListener$1(RendererDialog rendererDialog) {
        super(2);
        this.this$0 = rendererDialog;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(RendererItem rendererItem, Integer num) {
        invoke(rendererItem, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(RendererItem item, int i) {
        RendererDialog.OnRendererSelectListener onRendererSelectListener;
        Intrinsics.checkNotNullParameter(item, "item");
        VideoActivityFactory.rendererLiveData.setValue(item);
        onRendererSelectListener = this.this$0.listener;
        if (onRendererSelectListener != null) {
            onRendererSelectListener.onRendererSelect(item);
        }
        this.this$0.dismissAllowingStateLoss();
    }
}
