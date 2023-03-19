package com.movieboxpro.android.view.activity.web;

import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: WebViewActivity.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Ljava/io/File;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WebViewActivity$startDownload$4 extends Lambda implements Function1<File, Unit> {
    final /* synthetic */ Ref.BooleanRef $downloadSuccess;
    final /* synthetic */ WebViewActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WebViewActivity$startDownload$4(WebViewActivity webViewActivity, Ref.BooleanRef booleanRef) {
        super(1);
        this.this$0 = webViewActivity;
        this.$downloadSuccess = booleanRef;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(File file) {
        invoke2(file);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(File file) {
        this.this$0.hideLoadingView();
        if (this.$downloadSuccess.element) {
            this.this$0.createSubtitleFile(file.getPath());
        }
    }
}
