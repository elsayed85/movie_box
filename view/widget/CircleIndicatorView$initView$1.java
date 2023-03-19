package com.movieboxpro.android.view.widget;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* compiled from: CircleIndicatorView.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class CircleIndicatorView$initView$1 extends Lambda implements Function2<BaseViewHolder, Boolean, Unit> {
    public static final CircleIndicatorView$initView$1 INSTANCE = new CircleIndicatorView$initView$1();

    CircleIndicatorView$initView$1() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(BaseViewHolder baseViewHolder, Boolean bool) {
        invoke(baseViewHolder, bool.booleanValue());
        return Unit.INSTANCE;
    }

    public final void invoke(BaseViewHolder helper, boolean z) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        helper.getView(R.id.view).setSelected(z);
    }
}
