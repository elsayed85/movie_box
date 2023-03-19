package com.movieboxpro.android.view.activity;

import android.widget.CompoundButton;
import androidx.appcompat.widget.SwitchCompat;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.model.common.Device;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.TimeUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* compiled from: TvDeviceActivity.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Lcom/movieboxpro/android/model/common/Device;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class TvDeviceActivity$getDevices$4$adapter$1 extends Lambda implements Function2<BaseViewHolder, Device, Unit> {
    final /* synthetic */ TvDeviceActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TvDeviceActivity$getDevices$4$adapter$1(TvDeviceActivity tvDeviceActivity) {
        super(2);
        this.this$0 = tvDeviceActivity;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(BaseViewHolder baseViewHolder, Device device) {
        invoke2(baseViewHolder, device);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(BaseViewHolder helper, final Device item) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        helper.setText(R.id.ic_device_Model, item.model);
        helper.setText(R.id.ic_device_name, item.name);
        helper.setText(R.id.ic_device_last_time, TimeUtils.formatTime3(item.end_time.longValue() * 1000));
        SwitchCompat switchCompat = (SwitchCompat) helper.getView(R.id.switchCompat);
        switchCompat.setChecked(item.super_child_mode == 1);
        final TvDeviceActivity tvDeviceActivity = this.this$0;
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$TvDeviceActivity$getDevices$4$adapter$1$warzZMupnmlllIvLaNVLc4gJdhA
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                TvDeviceActivity$getDevices$4$adapter$1.m307invoke$lambda0(Device.this, tvDeviceActivity, compoundButton, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: invoke$lambda-0  reason: not valid java name */
    public static final void m307invoke$lambda0(Device item, TvDeviceActivity this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(item, "$item");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.Companion.post("Device_super_child_mode").param(Constant.Prefs.SUPER_CHILD_MODE, Integer.valueOf(z ? 1 : 0)).param("option_open_udid", item.open_udid).asRequest(), this$0), new TvDeviceActivity$getDevices$4$adapter$1$1$1(this$0), null, new TvDeviceActivity$getDevices$4$adapter$1$1$2(this$0), null, new TvDeviceActivity$getDevices$4$adapter$1$1$3(this$0), 10, null);
    }
}
