package com.movieboxpro.android.view.activity;

import android.widget.TextView;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.model.DownloadQualityRule;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.SpanUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* compiled from: DownloadResolutionActivity.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Lcom/movieboxpro/android/model/DownloadQualityRule;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class DownloadResolutionActivity$initData$adapter$1 extends Lambda implements Function2<BaseViewHolder, DownloadQualityRule, Unit> {
    public static final DownloadResolutionActivity$initData$adapter$1 INSTANCE = new DownloadResolutionActivity$initData$adapter$1();

    DownloadResolutionActivity$initData$adapter$1() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(BaseViewHolder baseViewHolder, DownloadQualityRule downloadQualityRule) {
        invoke2(baseViewHolder, downloadQualityRule);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(BaseViewHolder helper, DownloadQualityRule item) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        TextView textView = (TextView) helper.getView(R.id.tvName);
        if (Intrinsics.areEqual(item.getQuality(), "ORG")) {
            SpanUtils with = SpanUtils.with(textView);
            Intrinsics.checkNotNullExpressionValue(with, "with(tvName)");
            String name = item.getName();
            SpanUtils bold = CommonExtKt.addText(with, name != null ? name : "", 16, R.color.white_80alpha).setBold();
            Intrinsics.checkNotNullExpressionValue(bold, "with(tvName)\n           ….white_80alpha).setBold()");
            CommonExtKt.addText(bold, " (Original Video File)", 11, R.color.white_70alpha).create();
            return;
        }
        SpanUtils with2 = SpanUtils.with(textView);
        Intrinsics.checkNotNullExpressionValue(with2, "with(tvName)");
        String name2 = item.getName();
        CommonExtKt.addText(with2, name2 != null ? name2 : "", 16, R.color.white_80alpha).setBold().create();
    }
}
