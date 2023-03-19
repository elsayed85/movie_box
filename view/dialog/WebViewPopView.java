package com.movieboxpro.android.view.dialog;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.lxj.xpopup.core.BottomPopupView;
import com.movieboxpro.android.R;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.DensityUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: WebViewPopView.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0014J\b\u0010\n\u001a\u00020\tH\u0014J\b\u0010\u000b\u001a\u00020\fH\u0014R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/movieboxpro/android/view/dialog/WebViewPopView;", "Lcom/lxj/xpopup/core/BottomPopupView;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "url", "", "(Landroid/content/Context;Ljava/lang/String;)V", "getImplLayoutId", "", "getMaxHeight", "onCreate", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WebViewPopView extends BottomPopupView {
    public Map<Integer, View> _$_findViewCache;
    private String url;

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lxj.xpopup.core.BottomPopupView, com.lxj.xpopup.core.BasePopupView
    public int getImplLayoutId() {
        return R.layout.fragment_webview_bottom_sheet;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WebViewPopView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
        this.url = "";
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WebViewPopView(Context context, String url) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(url, "url");
        this._$_findViewCache = new LinkedHashMap();
        this.url = "";
        this.url = url;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lxj.xpopup.core.BasePopupView
    public void onCreate() {
        super.onCreate();
        final WebView webView = (WebView) findViewById(R.id.webView);
        webView.setBackgroundColor(0);
        webView.getBackground().setAlpha(0);
        String str = this.url;
        if (str == null) {
            str = "";
        }
        webView.loadUrl(str);
        webView.getSettings().setSupportZoom(true);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        webView.setWebViewClient(new WebViewClient() { // from class: com.movieboxpro.android.view.dialog.WebViewPopView$onCreate$1
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(url, "url");
                if (Build.VERSION.SDK_INT < 26) {
                    view.loadUrl(url);
                    return true;
                }
                return false;
            }

            @Override // android.webkit.WebViewClient
            public void onPageStarted(WebView webView2, String str2, Bitmap bitmap) {
                super.onPageStarted(webView2, str2, bitmap);
                ProgressBar progressBar2 = progressBar;
                Intrinsics.checkNotNullExpressionValue(progressBar2, "progressBar");
                CommonExtKt.visible(progressBar2);
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView2, String str2) {
                super.onPageFinished(webView2, str2);
                ProgressBar progressBar2 = progressBar;
                Intrinsics.checkNotNullExpressionValue(progressBar2, "progressBar");
                CommonExtKt.gone(progressBar2);
                WebView webView3 = webView;
                Intrinsics.checkNotNullExpressionValue(webView3, "webView");
                CommonExtKt.visible(webView3);
            }
        });
        ((ImageView) findViewById(R.id.ivClose)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$WebViewPopView$miEpaLWcK0ggC-7b_Tvf2NrPXYI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WebViewPopView.m1140onCreate$lambda0(WebViewPopView.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onCreate$lambda-0  reason: not valid java name */
    public static final void m1140onCreate$lambda0(WebViewPopView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lxj.xpopup.core.BasePopupView
    public int getMaxHeight() {
        double screenHeight = DensityUtils.getScreenHeight(getContext());
        Double.isNaN(screenHeight);
        return (int) (screenHeight * 0.85d);
    }
}
