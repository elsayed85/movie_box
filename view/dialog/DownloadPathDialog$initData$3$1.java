package com.movieboxpro.android.view.dialog;

import androidx.appcompat.widget.AppCompatImageView;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.BuildConfig;
import com.movieboxpro.android.R;
import com.movieboxpro.android.model.DownloadInfo;
import com.movieboxpro.android.model.DownloadPath;
import com.movieboxpro.android.utils.CommonExtKt;
import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* compiled from: DownloadPathDialog.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Lcom/movieboxpro/android/model/DownloadPath;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class DownloadPathDialog$initData$3$1 extends Lambda implements Function2<BaseViewHolder, DownloadPath, Unit> {
    public static final DownloadPathDialog$initData$3$1 INSTANCE = new DownloadPathDialog$initData$3$1();

    DownloadPathDialog$initData$3$1() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(BaseViewHolder baseViewHolder, DownloadPath downloadPath) {
        invoke2(baseViewHolder, downloadPath);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(BaseViewHolder helper, DownloadPath item) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        helper.setText(R.id.tvTitle, item.getTitle());
        if (helper.getAdapterPosition() != 0) {
            helper.setText(R.id.tvPath, "Android/data" + ((Object) File.separator) + BuildConfig.APPLICATION_ID + ((Object) File.separator) + DownloadInfo.DOWNLOAD);
        } else {
            helper.setText(R.id.tvPath, "");
        }
        helper.setText(R.id.tvSpace, Intrinsics.stringPlus("Free:", item.getFreeSpace()));
        AppCompatImageView appCompatImageView = (AppCompatImageView) helper.getView(R.id.ivSelect);
        if (item.getSelect()) {
            CommonExtKt.visible(appCompatImageView);
        } else {
            CommonExtKt.invisible(appCompatImageView);
        }
    }
}
