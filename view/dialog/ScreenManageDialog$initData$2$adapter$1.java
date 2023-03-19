package com.movieboxpro.android.view.dialog;

import android.widget.TextView;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.model.DeviceModelResponse;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* compiled from: ScreenManageDialog.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "helper2", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item2", "Lcom/movieboxpro/android/model/DeviceModelResponse$DeviceModel;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class ScreenManageDialog$initData$2$adapter$1 extends Lambda implements Function2<BaseViewHolder, DeviceModelResponse.DeviceModel, Unit> {
    public static final ScreenManageDialog$initData$2$adapter$1 INSTANCE = new ScreenManageDialog$initData$2$adapter$1();

    ScreenManageDialog$initData$2$adapter$1() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(BaseViewHolder baseViewHolder, DeviceModelResponse.DeviceModel deviceModel) {
        invoke2(baseViewHolder, deviceModel);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(BaseViewHolder helper2, DeviceModelResponse.DeviceModel deviceModel) {
        Intrinsics.checkNotNullParameter(helper2, "helper2");
        helper2.setText(R.id.textView, deviceModel.getDevice_name() + ' ' + ((Object) deviceModel.getDevice_model()));
        TextView textView = (TextView) helper2.getView(R.id.tvStop);
    }
}
