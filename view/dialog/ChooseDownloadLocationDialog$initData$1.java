package com.movieboxpro.android.view.dialog;

import android.widget.ImageView;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.model.DownloadLocation;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.GlideUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* compiled from: ChooseDownloadLocationDialog.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Lcom/movieboxpro/android/model/DownloadLocation;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class ChooseDownloadLocationDialog$initData$1 extends Lambda implements Function2<BaseViewHolder, DownloadLocation, Unit> {
    final /* synthetic */ ChooseDownloadLocationDialog this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChooseDownloadLocationDialog$initData$1(ChooseDownloadLocationDialog chooseDownloadLocationDialog) {
        super(2);
        this.this$0 = chooseDownloadLocationDialog;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(BaseViewHolder baseViewHolder, DownloadLocation downloadLocation) {
        invoke2(baseViewHolder, downloadLocation);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(BaseViewHolder helper, DownloadLocation item) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        if (Intrinsics.areEqual(item.getName(), "Internal Storage")) {
            GlideUtils.load(this.this$0.getContext(), (int) R.mipmap.ic_internal_storage, (ImageView) helper.getView(R.id.imageView));
        } else {
            GlideUtils.load(this.this$0.getContext(), (int) R.mipmap.ic_sd_card, (ImageView) helper.getView(R.id.imageView));
        }
        helper.setText(R.id.tvName, item.getName());
        helper.setText(R.id.tvSize, ((Object) FileUtils.byte2FitMemorySize(item.getFreeSize())) + " Free, " + ((Object) FileUtils.byte2FitMemorySize(item.getTotalSize())) + " Total");
        ImageView imageView = (ImageView) helper.getView(R.id.ivSelect);
        if (item.isSelect()) {
            CommonExtKt.visible(imageView);
        } else {
            CommonExtKt.gone(imageView);
        }
    }
}
