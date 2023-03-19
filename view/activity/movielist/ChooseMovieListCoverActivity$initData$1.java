package com.movieboxpro.android.view.activity.movielist;

import android.widget.ImageView;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.GlideUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* compiled from: ChooseMovieListCoverActivity.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class ChooseMovieListCoverActivity$initData$1 extends Lambda implements Function2<BaseViewHolder, String, Unit> {
    final /* synthetic */ ChooseMovieListCoverActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChooseMovieListCoverActivity$initData$1(ChooseMovieListCoverActivity chooseMovieListCoverActivity) {
        super(2);
        this.this$0 = chooseMovieListCoverActivity;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(BaseViewHolder baseViewHolder, String str) {
        invoke2(baseViewHolder, str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(BaseViewHolder helper, String str) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        GlideUtils.loadWithCorner(this.this$0, str, (ImageView) helper.getView(R.id.imageView), DensityUtils.dp2px(this.this$0, 4.0f), R.drawable.ic_default);
    }
}
