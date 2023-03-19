package com.movieboxpro.android.view.activity.web;

import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.EventUtils;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.MsgHintDialog;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: WebViewActivity.kt */
@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0002H\u0016J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"com/movieboxpro/android/view/activity/web/WebViewActivity$createSubtitleFile$2", "Lio/reactivex/Observer;", "", "onComplete", "", "onError", "e", "", "onNext", "t", "onSubscribe", "d", "Lio/reactivex/disposables/Disposable;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WebViewActivity$createSubtitleFile$2 implements Observer<Boolean> {
    final /* synthetic */ WebViewActivity this$0;

    @Override // io.reactivex.Observer
    public void onComplete() {
    }

    @Override // io.reactivex.Observer
    public void onSubscribe(Disposable d) {
        Intrinsics.checkNotNullParameter(d, "d");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WebViewActivity$createSubtitleFile$2(WebViewActivity webViewActivity) {
        this.this$0 = webViewActivity;
    }

    @Override // io.reactivex.Observer
    public /* bridge */ /* synthetic */ void onNext(Boolean bool) {
        onNext(bool.booleanValue());
    }

    public void onNext(boolean z) {
        boolean z2;
        MsgHintDialog.Builder content = new MsgHintDialog.Builder(this.this$0).setContent("Download successfully,Do you want to check the subtitle now?");
        final WebViewActivity webViewActivity = this.this$0;
        content.setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.web.-$$Lambda$WebViewActivity$createSubtitleFile$2$xCQ-pODg9KfdVh4TvK5mSKXUwkI
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public final void onClick() {
                WebViewActivity$createSubtitleFile$2.m914onNext$lambda0(WebViewActivity.this);
            }
        }).create().show();
        z2 = this.this$0.isGoogle;
        if (z2) {
            CommonExtKt.onMobEvent(this.this$0, "GoogleSubtitles_Downloaded");
            EventUtils.event("Google字幕下载");
            return;
        }
        CommonExtKt.onMobEvent(this.this$0, "SubsceneSubtitles_Downloaded");
        EventUtils.event("Subscene字幕下载");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onNext$lambda-0  reason: not valid java name */
    public static final void m914onNext$lambda0(WebViewActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    @Override // io.reactivex.Observer
    public void onError(Throwable e) {
        Intrinsics.checkNotNullParameter(e, "e");
        new MsgHintDialog.Builder(this.this$0).setContent(Intrinsics.stringPlus("Download failed reason:", e.getMessage())).setNegativeGone().create().show();
    }
}
