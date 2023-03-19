package com.movieboxpro.android.service;

import com.hyqq.dlan.DLNAManager;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.service.DLNACastService;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.PrefsUtils;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DLNACastService.kt */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DLNACastService$startTimer$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ boolean $stopSelf;
    final /* synthetic */ DLNACastService this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DLNACastService$startTimer$1(DLNACastService dLNACastService, boolean z) {
        super(0);
        this.this$0 = dLNACastService;
        this.$stopSelf = z;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2() {
        try {
            ArrayList arrayList = this.this$0.mDeviceInfoList;
            DLNAManager dLNAManager = null;
            if (arrayList == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mDeviceInfoList");
                arrayList = null;
            }
            if (arrayList.isEmpty()) {
                DLNAManager dLNAManager2 = this.this$0.mDLNAManager;
                if (dLNAManager2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mDLNAManager");
                } else {
                    dLNAManager = dLNAManager2;
                }
                dLNAManager.stopBrowser();
                if (this.$stopSelf) {
                    PrefsUtils.getInstance().putInt(Constant.Prefs.DLNA_CAST_SEARCH_TIME, PrefsUtils.getInstance().getInt(Constant.Prefs.DLNA_CAST_SEARCH_TIME, 0) + 1);
                    this.this$0.stopSelf();
                }
            } else {
                DLNAManager dLNAManager3 = this.this$0.mDLNAManager;
                if (dLNAManager3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mDLNAManager");
                } else {
                    dLNAManager = dLNAManager3;
                }
                dLNAManager.stopBrowser();
            }
            DLNACastService.CastListener castListener = this.this$0.castListener;
            if (castListener != null) {
                castListener.searchCompleted();
            }
            CommonExtKt.logD(this.this$0, "Search complete");
        } catch (Exception unused) {
        }
    }
}
