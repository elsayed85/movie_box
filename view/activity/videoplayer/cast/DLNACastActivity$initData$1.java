package com.movieboxpro.android.view.activity.videoplayer.cast;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.hyqq.dlan.bean.DeviceInfo;
import com.movieboxpro.android.R;
import com.movieboxpro.android.utils.CommonExtKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* compiled from: DLNACastActivity.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Lcom/hyqq/dlan/bean/DeviceInfo;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class DLNACastActivity$initData$1 extends Lambda implements Function2<BaseViewHolder, DeviceInfo, Unit> {
    final /* synthetic */ DLNACastActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DLNACastActivity$initData$1(DLNACastActivity dLNACastActivity) {
        super(2);
        this.this$0 = dLNACastActivity;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(BaseViewHolder baseViewHolder, DeviceInfo deviceInfo) {
        invoke2(baseViewHolder, deviceInfo);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(BaseViewHolder helper, DeviceInfo item) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        DLNACastActivity dLNACastActivity = this.this$0;
        helper.setText(R.id.tvName, item.getName());
        ImageView imageView = (ImageView) helper.getView(R.id.ivConnected);
        if (item.isConnected()) {
            CommonExtKt.visible(imageView);
        } else {
            CommonExtKt.gone(imageView);
        }
        LinearLayout linearLayout = (LinearLayout) helper.getView(R.id.linearLayout);
        if (item.isSelected()) {
            linearLayout.setBackgroundColor(CommonExtKt.colorInt((Context) dLNACastActivity, (int) R.color.color_main_back));
        } else {
            linearLayout.setBackgroundColor(CommonExtKt.colorInt((Context) dLNACastActivity, (int) R.color.transparent));
        }
    }
}
