package com.movieboxpro.android.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.db.entity.Download;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import io.reactivex.Observable;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
/* compiled from: MovieDownloadReceiver.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/receiver/MovieDownloadReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MovieDownloadReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        if (Intrinsics.areEqual(intent.getAction(), Constant.ACTION.DOWNLOAD_MOVIE_FAILURE)) {
            List<Download> selectByTvId = App.getDB().downloadDao().selectByTvId(intent.getStringExtra(Constant.Download.PARAMS_KEY_MOVIE_ID), intent.getIntExtra(Constant.Download.PARAMS_KEY_MOVIE_TYPE, 1));
            if (selectByTvId == null || !(!selectByTvId.isEmpty())) {
                return;
            }
            Download download = selectByTvId.get(0);
            String failReason = download.getFailReason();
            if (failReason != null && StringsKt.contains$default((CharSequence) failReason, (CharSequence) "I/O error during system call, Software caused connection abort", false, 2, (Object) null)) {
                Observable<R> compose = Observable.timer(2L, TimeUnit.SECONDS).compose(RxUtils.rxSchedulerHelper());
                Intrinsics.checkNotNullExpressionValue(compose, "timer(2,TimeUnit.SECONDS…tils.rxSchedulerHelper())");
                RxSubscribersKt.subscribeTo$default(compose, null, null, null, new MovieDownloadReceiver$onReceive$1(download, context), 7, null);
            }
        }
    }
}
