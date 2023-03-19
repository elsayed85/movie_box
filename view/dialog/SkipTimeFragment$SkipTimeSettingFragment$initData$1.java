package com.movieboxpro.android.view.dialog;

import android.view.View;
import android.widget.ImageView;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.model.VideoThumb;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.view.dialog.SkipTimeFragment;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* compiled from: SkipTimeFragment.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Lcom/movieboxpro/android/model/VideoThumb;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class SkipTimeFragment$SkipTimeSettingFragment$initData$1 extends Lambda implements Function2<BaseViewHolder, VideoThumb, Unit> {
    final /* synthetic */ SkipTimeFragment.SkipTimeSettingFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SkipTimeFragment$SkipTimeSettingFragment$initData$1(SkipTimeFragment.SkipTimeSettingFragment skipTimeSettingFragment) {
        super(2);
        this.this$0 = skipTimeSettingFragment;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(BaseViewHolder baseViewHolder, VideoThumb videoThumb) {
        invoke2(baseViewHolder, videoThumb);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(final BaseViewHolder helper, VideoThumb item) {
        int i;
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        GlideUtils.load(this.this$0.getContext(), item.getUrl(), (ImageView) helper.getView(R.id.ivPoster));
        helper.setText(R.id.tvTime, TimeUtils.getTime(item.getSeconds()));
        ImageView imageView = (ImageView) helper.getView(R.id.ivChoose);
        i = this.this$0.mostChooseTime;
        if (i == item.getSeconds()) {
            CommonExtKt.visible(imageView);
        } else {
            CommonExtKt.gone(imageView);
        }
        View view = helper.itemView;
        final SkipTimeFragment.SkipTimeSettingFragment skipTimeSettingFragment = this.this$0;
        view.post(new Runnable() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$SkipTimeFragment$SkipTimeSettingFragment$initData$1$py7-u6qDh26jp_CxJp7rNeeVej4
            @Override // java.lang.Runnable
            public final void run() {
                SkipTimeFragment$SkipTimeSettingFragment$initData$1.m1128invoke$lambda0(SkipTimeFragment.SkipTimeSettingFragment.this, helper);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: invoke$lambda-0  reason: not valid java name */
    public static final void m1128invoke$lambda0(SkipTimeFragment.SkipTimeSettingFragment this$0, BaseViewHolder helper) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(helper, "$helper");
        this$0.imageItemWidth = helper.itemView.getWidth();
    }
}
