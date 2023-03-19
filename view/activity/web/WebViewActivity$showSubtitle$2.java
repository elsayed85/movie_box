package com.movieboxpro.android.view.activity.web;

import com.movieboxpro.android.base.BaseObserver;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.ShowSubtitleDialog;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: WebViewActivity.kt */
@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0012\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0002H\u0016J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"com/movieboxpro/android/view/activity/web/WebViewActivity$showSubtitle$2", "Lcom/movieboxpro/android/base/BaseObserver;", "", "onComplete", "", "onError", "e", "Lcom/movieboxpro/android/http/ApiException;", "onNext", "t", "onSubscribe", "d", "Lio/reactivex/disposables/Disposable;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WebViewActivity$showSubtitle$2 extends BaseObserver<String> {
    final /* synthetic */ String $contentDisposition;
    final /* synthetic */ String $mimeType;
    final /* synthetic */ String $url;
    final /* synthetic */ WebViewActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WebViewActivity$showSubtitle$2(WebViewActivity webViewActivity, String str, String str2, String str3) {
        this.this$0 = webViewActivity;
        this.$url = str;
        this.$contentDisposition = str2;
        this.$mimeType = str3;
    }

    @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
    public void onSubscribe(Disposable d) {
        Intrinsics.checkNotNullParameter(d, "d");
        super.onSubscribe(d);
        this.this$0.showLoadingView();
    }

    @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
    public void onNext(String t) {
        Intrinsics.checkNotNullParameter(t, "t");
        super.onNext((WebViewActivity$showSubtitle$2) t);
        final ShowSubtitleDialog showSubtitleDialog = new ShowSubtitleDialog(this.this$0, t);
        final WebViewActivity webViewActivity = this.this$0;
        final String str = this.$url;
        final String str2 = this.$contentDisposition;
        final String str3 = this.$mimeType;
        showSubtitleDialog.setDownloadListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.web.-$$Lambda$WebViewActivity$showSubtitle$2$54_bI4Bn11B9cUunDVJYaohFZoQ
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public final void onClick() {
                WebViewActivity$showSubtitle$2.m915onNext$lambda0(WebViewActivity.this, str, str2, str3, showSubtitleDialog);
            }
        });
        showSubtitleDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onNext$lambda-0  reason: not valid java name */
    public static final void m915onNext$lambda0(WebViewActivity this$0, String str, String contentDisposition, String mimeType, ShowSubtitleDialog dialog) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(contentDisposition, "$contentDisposition");
        Intrinsics.checkNotNullParameter(mimeType, "$mimeType");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        if (CommonUtils.havePermissions(this$0, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"})) {
            this$0.downloadBySystem(str, contentDisposition, mimeType);
            dialog.dismiss();
            return;
        }
        this$0.downloadBySystem(str, contentDisposition, mimeType);
    }

    @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
    public void onComplete() {
        super.onComplete();
        this.this$0.hideLoadingView();
    }

    @Override // com.movieboxpro.android.base.BaseObserver
    public void onError(ApiException apiException) {
        this.this$0.hideLoadingView();
        ToastUtils.showShort("can't load subtitle:", new Object[0]);
    }
}
