package com.movieboxpro.android.view.fragment.home;

import android.content.Context;
import android.widget.TextView;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.model.TimeLineModel;
import com.movieboxpro.android.utils.CommonExtKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* compiled from: TvGuideFragment.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Lcom/movieboxpro/android/model/TimeLineModel;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class TvGuideFragment$initData$1 extends Lambda implements Function2<BaseViewHolder, TimeLineModel, Unit> {
    public static final TvGuideFragment$initData$1 INSTANCE = new TvGuideFragment$initData$1();

    TvGuideFragment$initData$1() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(BaseViewHolder baseViewHolder, TimeLineModel timeLineModel) {
        invoke2(baseViewHolder, timeLineModel);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(BaseViewHolder helper, TimeLineModel item) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        TextView textView = (TextView) helper.getView(R.id.tvDay);
        helper.setText(R.id.tvWeek, item.getWeekCode());
        textView.setText(String.valueOf(item.getDayNumber()));
        if (item.isSelect()) {
            textView.setBackgroundResource(R.drawable.yelow_circle_shape);
            CommonExtKt.textColor(textView, R.color.color_main);
            return;
        }
        CommonExtKt.textColor(textView, R.color.white);
        if (item.isToday()) {
            textView.setBackgroundResource(R.drawable.yelow_1border_circle_shape);
            return;
        }
        Context context = App.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext()");
        textView.setBackgroundColor(CommonExtKt.colorInt(context, (int) R.color.transparent));
    }
}
