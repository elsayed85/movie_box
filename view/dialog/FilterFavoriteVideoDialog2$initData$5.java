package com.movieboxpro.android.view.dialog;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.model.FilterCountry;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.GlideUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
/* compiled from: FilterFavoriteVideoDialog2.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Lcom/movieboxpro/android/model/FilterCountry;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class FilterFavoriteVideoDialog2$initData$5 extends Lambda implements Function2<BaseViewHolder, FilterCountry, Unit> {
    final /* synthetic */ FilterFavoriteVideoDialog2 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FilterFavoriteVideoDialog2$initData$5(FilterFavoriteVideoDialog2 filterFavoriteVideoDialog2) {
        super(2);
        this.this$0 = filterFavoriteVideoDialog2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(BaseViewHolder baseViewHolder, FilterCountry filterCountry) {
        invoke2(baseViewHolder, filterCountry);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(BaseViewHolder helper, FilterCountry item) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        TextView textView = (TextView) helper.getView(R.id.textView);
        textView.setText(item.getCountry());
        textView.setSelected(item.isSelect());
        ((LinearLayout) helper.getView(R.id.linearLayout)).setSelected(item.isSelect());
        ImageView imageView = (ImageView) helper.getView(R.id.imageView);
        if (item.getIcon() != null) {
            GlideUtils.load(this.this$0.getContext(), item.getIcon(), imageView);
        } else {
            GlideUtils.load(this.this$0.getContext(), item.getResId(), imageView);
        }
        String country = item.getCountry();
        Intrinsics.checkNotNullExpressionValue(country, "item.country");
        if (StringsKt.contains$default((CharSequence) country, (CharSequence) "More", false, 2, (Object) null)) {
            CommonExtKt.gone(imageView);
        } else {
            CommonExtKt.visible(imageView);
        }
    }
}
