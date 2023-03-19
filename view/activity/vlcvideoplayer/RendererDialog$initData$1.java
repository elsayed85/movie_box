package com.movieboxpro.android.view.activity.vlcvideoplayer;

import android.widget.TextView;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.libvlc.RendererItem;
/* compiled from: RendererDialog.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Lorg/videolan/libvlc/RendererItem;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class RendererDialog$initData$1 extends Lambda implements Function2<BaseViewHolder, RendererItem, Unit> {
    final /* synthetic */ RendererDialog this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RendererDialog$initData$1(RendererDialog rendererDialog) {
        super(2);
        this.this$0 = rendererDialog;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(BaseViewHolder baseViewHolder, RendererItem rendererItem) {
        invoke2(baseViewHolder, rendererItem);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(BaseViewHolder helper, RendererItem item) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        TextView textView = (TextView) helper.getView(R.id.tvName);
        if (Intrinsics.areEqual(item, VideoActivityFactory.rendererLiveData.getValue())) {
            textView.setTextColor(CommonExtKt.colorInt(this.this$0, (int) R.color.color_main_blue));
        } else {
            textView.setTextColor(CommonExtKt.colorInt(this.this$0, (int) R.color.white_30alpha));
        }
        helper.setText(R.id.tvName, item.displayName);
    }
}
