package com.movieboxpro.android.view.dialog;

import com.movieboxpro.android.event.RefreshSkipTimeEvent;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.dialog.SkipTimeFragment;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.greenrobot.eventbus.EventBus;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SkipTimeFragment.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SkipTimeFragment$initListener$4$4 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ SkipTimeFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SkipTimeFragment$initListener$4$4(SkipTimeFragment skipTimeFragment) {
        super(1);
        this.this$0 = skipTimeFragment;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(String str) {
        invoke2(str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(String str) {
        boolean z;
        int i;
        SkipTimeFragment.SkipTimeSettingFragment skipTimeSettingFragment;
        SkipTimeFragment.SkipTimeSettingFragment skipTimeSettingFragment2;
        this.this$0.hideLoadingView();
        ToastUtils.showShort("Saved", new Object[0]);
        EventBus eventBus = EventBus.getDefault();
        z = this.this$0.openingLoaded;
        if (z) {
            skipTimeSettingFragment = this.this$0.startFragment;
            SkipTimeFragment.SkipTimeSettingFragment skipTimeSettingFragment3 = null;
            if (skipTimeSettingFragment == null) {
                Intrinsics.throwUninitializedPropertyAccessException("startFragment");
                skipTimeSettingFragment = null;
            }
            if (skipTimeSettingFragment.isScrolled()) {
                skipTimeSettingFragment2 = this.this$0.startFragment;
                if (skipTimeSettingFragment2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("startFragment");
                } else {
                    skipTimeSettingFragment3 = skipTimeSettingFragment2;
                }
                i = skipTimeSettingFragment3.getSelectTime();
                eventBus.post(new RefreshSkipTimeEvent(i));
                this.this$0.dismissAllowingStateLoss();
            }
        }
        i = -1;
        eventBus.post(new RefreshSkipTimeEvent(i));
        this.this$0.dismissAllowingStateLoss();
    }
}
