package com.movieboxpro.android.receiver;

import android.content.Context;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.db.entity.Download;
import com.movieboxpro.android.model.DownloadModel;
import com.movieboxpro.android.utils.tool.NetworkUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: MovieDownloadReceiver.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "invoke", "(Ljava/lang/Long;)V"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class MovieDownloadReceiver$onReceive$1 extends Lambda implements Function1<Long, Unit> {
    final /* synthetic */ Context $context;
    final /* synthetic */ Download $item;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MovieDownloadReceiver$onReceive$1(Download download, Context context) {
        super(1);
        this.$item = download;
        this.$context = context;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Long l) {
        invoke2(l);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(Long l) {
        if (NetworkUtils.isWifiConnected()) {
            new DownloadModel(this.$item).startService(Constant.ACTION.MOVIE_DOWNLOAD, this.$context);
        }
    }
}
