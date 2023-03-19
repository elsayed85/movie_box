package com.movieboxpro.android.view.activity.settings;

import com.github.mikephil.charting.utils.Utils;
import com.movieboxpro.android.base.CommMultiBaseAdapter;
import com.movieboxpro.android.base.mvp.BaseContract;
import com.movieboxpro.android.model.common.NetTestModel;
import com.movieboxpro.android.utils.SystemUtils;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.StringCompanionObject;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TestSpeedActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0003*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "kotlin.jvm.PlatformType", "invoke", "(Lkotlin/Unit;)V"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TestSpeedActivity$sendTestInfo$3 extends Lambda implements Function1<Unit, Unit> {
    final /* synthetic */ TestSpeedActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TestSpeedActivity$sendTestInfo$3(TestSpeedActivity testSpeedActivity) {
        super(1);
        this.this$0 = testSpeedActivity;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Unit unit) {
        invoke2(unit);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(Unit unit) {
        String addHeadTestSpeedInfo;
        CommMultiBaseAdapter commMultiBaseAdapter;
        double d;
        double d2;
        String str;
        String str2;
        BaseContract.BasePresenter basePresenter;
        String str3;
        int i;
        String addTestSpeedInfo;
        StringBuilder sb = new StringBuilder();
        addHeadTestSpeedInfo = this.this$0.addHeadTestSpeedInfo("Server", "IP", "Speed");
        sb.append(addHeadTestSpeedInfo);
        commMultiBaseAdapter = this.this$0.adapter;
        if (commMultiBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter = null;
        }
        TestSpeedActivity testSpeedActivity = this.this$0;
        String str4 = "";
        int i2 = 0;
        for (NetTestModel netTestModel : commMultiBaseAdapter.getData()) {
            if (netTestModel.getSpeeds() == null || netTestModel.getSpeeds().size() <= 5) {
                i = 0;
            } else {
                List<Integer> speeds = netTestModel.getSpeeds();
                i = (int) (speeds == null ? Utils.DOUBLE_EPSILON : CollectionsKt.averageOfInt(speeds));
            }
            if (i > i2) {
                str4 = netTestModel.country;
                Intrinsics.checkNotNullExpressionValue(str4, "it.country");
                i2 = i;
            }
            String str5 = netTestModel.domain;
            if (str5 == null) {
                str5 = "";
            }
            String domainIp = netTestModel.getDomainIp();
            if (domainIp == null) {
                domainIp = "";
            }
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format("%sKB/s", Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            addTestSpeedInfo = testSpeedActivity.addTestSpeedInfo(str5, domainIp, format);
            sb.append(addTestSpeedInfo);
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("(api)Test_network:\n");
        sb2.append((Object) SystemUtils.getMsg2());
        sb2.append("\nlatitude:");
        d = this.this$0.latitude;
        sb2.append(d);
        sb2.append(" longitude:");
        d2 = this.this$0.longitude;
        sb2.append(d2);
        sb2.append("\nipLocation:");
        str = this.this$0.ipLocation;
        sb2.append(str);
        sb2.append("\ngpsLocation:");
        str2 = this.this$0.gpsLocation;
        sb2.append((Object) str2);
        sb2.append("\n\n\n\n");
        sb2.append((Object) sb);
        String sb3 = sb2.toString();
        basePresenter = this.this$0.mPresenter;
        StringBuilder sb4 = new StringBuilder();
        sb4.append("Speed test:");
        str3 = this.this$0.ipLocation;
        sb4.append(str3);
        sb4.append(' ');
        sb4.append(str4);
        sb4.append(' ');
        sb4.append(i2);
        sb4.append("KB/s");
        ((TestSpeedPresenter) basePresenter).sendTestInfo(sb3, sb4.toString());
    }
}
