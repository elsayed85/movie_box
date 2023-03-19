package com.movieboxpro.android.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.CommBaseAdapter;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.ServerException;
import com.movieboxpro.android.listener.ImageClickJSBridge;
import com.movieboxpro.android.model.ReviewModel;
import com.movieboxpro.android.model.SingleReviewModel;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity;
import com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity;
import com.movieboxpro.android.view.activity.movielist.MovieListDetailActivity;
import com.movieboxpro.android.view.activity.review.ImageShowActivity;
import com.movieboxpro.android.view.activity.review.PandaForumAuthorizeActivity;
import com.movieboxpro.android.view.activity.review.UserInfoActivity;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import org.eclipse.jetty.http.MimeTypes;
/* compiled from: SingleReviewDialogFragment.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 \"2\u00020\u0001:\u0003\"#$B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u000eH\u0002J\u0012\u0010\u0010\u001a\u00020\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0012\u0010\u0013\u001a\u00020\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J&\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u001a\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u00152\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0018\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010!\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001eH\u0003R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/movieboxpro/android/view/dialog/SingleReviewDialogFragment;", "Landroidx/appcompat/app/AppCompatDialogFragment;", "()V", "chromeClient", "Landroid/webkit/WebChromeClient;", "jsBridge", "Lcom/movieboxpro/android/listener/ImageClickJSBridge;", "webviewClient", "Landroid/webkit/WebViewClient;", "getContextRect", "", "activity", "Landroid/app/Activity;", "getReview", "", "initData", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreate", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "onViewCreated", "view", "setHtml", "webView", "Landroid/webkit/WebView;", "html", "", "setUpWebView", "Companion", "MyWebChromeClient", "MyWebViewClient", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SingleReviewDialogFragment extends AppCompatDialogFragment {
    public static final Companion Companion = new Companion(null);
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private WebChromeClient chromeClient;
    private ImageClickJSBridge jsBridge;
    private WebViewClient webviewClient;

    private final void initData() {
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    /* compiled from: SingleReviewDialogFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/view/dialog/SingleReviewDialogFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/SingleReviewDialogFragment;", "pid", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SingleReviewDialogFragment newInstance(String pid) {
            Intrinsics.checkNotNullParameter(pid, "pid");
            SingleReviewDialogFragment singleReviewDialogFragment = new SingleReviewDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString("pid", pid);
            singleReviewDialogFragment.setArguments(bundle);
            return singleReviewDialogFragment;
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(0, R.style.BottomSheetFullScreenDialog);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        Dialog dialog = getDialog();
        Window window = dialog == null ? null : dialog.getWindow();
        if (window != null) {
            window.requestFeature(1);
            int i = 0;
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams attributes = window.getAttributes();
            Context context = getContext();
            Intrinsics.checkNotNull(context);
            window.setBackgroundDrawable(ContextCompat.getDrawable(context, 17170445));
            if (getActivity() != null) {
                FragmentActivity activity = getActivity();
                Intrinsics.checkNotNull(activity);
                Intrinsics.checkNotNullExpressionValue(activity, "activity!!");
                i = getContextRect(activity);
            }
            if (i == 0) {
                i = -1;
            }
            window.setLayout(-1, i);
            attributes.dimAmount = 0.0f;
            attributes.gravity = 80;
            window.setAttributes(attributes);
        }
        super.onActivityCreated(bundle);
    }

    private final int getContextRect(Activity activity) {
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        double height = rect.height();
        Double.isNaN(height);
        return (int) (height * 0.7d);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_single_review, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        initData();
        getReview();
    }

    private final void getReview() {
        Bundle arguments = getArguments();
        Observable<R> compose = Http.getService().getReviewByPid(API.BBS_URL, "get_thread_pid", arguments == null ? null : arguments.getString("pid"), App.getUserData().uid_v2).compose(RxUtils.rxTranslate2Bean(SingleReviewModel.class));
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
        RxSubscribersKt.transform(compose, this).subscribe(new Consumer() { // from class: com.movieboxpro.android.view.dialog.SingleReviewDialogFragment$getReview$$inlined$subscribeToBean$default$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(T it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                SingleReviewModel singleReviewModel = (SingleReviewModel) it;
                if (singleReviewModel.getList() == null || singleReviewModel.getList().size() <= 0) {
                    return;
                }
                ReviewModel item = singleReviewModel.getList().get(0);
                Intrinsics.checkNotNullExpressionValue(item, "item");
                CommBaseAdapter commBaseAdapter = new CommBaseAdapter(R.layout.adapter_review_webview_item, new SingleReviewDialogFragment$getReview$2$adapter$1(SingleReviewDialogFragment.this), CollectionsKt.arrayListOf(item));
                commBaseAdapter.addChildClickViewIds(R.id.llLike, R.id.llReview, R.id.ivAvatar);
                ((RecyclerView) SingleReviewDialogFragment.this._$_findCachedViewById(R.id.recyclerView)).setLayoutManager(new LinearLayoutManager(SingleReviewDialogFragment.this.getContext()));
                ((RecyclerView) SingleReviewDialogFragment.this._$_findCachedViewById(R.id.recyclerView)).setAdapter(commBaseAdapter);
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.dialog.SingleReviewDialogFragment$getReview$$inlined$subscribeToBean$default$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Intrinsics.checkNotNullExpressionValue(ApiException.handleException(th), "handleException(it)");
                if (th instanceof ServerException) {
                    ServerException serverException = (ServerException) th;
                }
            }
        }, new Action() { // from class: com.movieboxpro.android.view.dialog.SingleReviewDialogFragment$getReview$$inlined$subscribeToBean$default$3
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.dialog.SingleReviewDialogFragment$getReview$$inlined$subscribeToBean$default$4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setHtml(WebView webView, String str) {
        setUpWebView(webView);
        String CLICK_EVENT_JS = Constant.CLICK_EVENT_JS;
        Intrinsics.checkNotNullExpressionValue(CLICK_EVENT_JS, "CLICK_EVENT_JS");
        if (StringsKt.isBlank(CLICK_EVENT_JS)) {
            Constant.CLICK_EVENT_JS = CommonUtils.inputStream2String(getResources().getAssets().open("ClickEvent.js"), "utf-8");
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%s%s%s", Arrays.copyOf(new Object[]{"<style>img{width: 100%;display: block;}a:link{color: #007AFF;}body{margin:0px;padding:0px;font-weight:bold;font-size:14px;line-height:21px;}*{background:#1D1D1D;color:rgba(255,255,255,0.38)}</style>", Constant.CLICK_EVENT_JS, str}, 3));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        webView.loadDataWithBaseURL(null, format, MimeTypes.TEXT_HTML, "utf-8", null);
        webView.setFocusable(true);
        webView.setFocusableInTouchMode(true);
    }

    private final void setUpWebView(WebView webView) {
        webView.setHorizontalFadingEdgeEnabled(false);
        webView.setVerticalFadingEdgeEnabled(false);
        webView.setOverScrollMode(2);
        webView.setOnTouchListener($$Lambda$SingleReviewDialogFragment$yfMTf714bwFLrOD9RhWY0pJ_xuU.INSTANCE);
        if (this.jsBridge == null) {
            ImageClickJSBridge imageClickJSBridge = new ImageClickJSBridge();
            this.jsBridge = imageClickJSBridge;
            Intrinsics.checkNotNull(imageClickJSBridge);
            imageClickJSBridge.setListener(new ImageClickJSBridge.OnImageClickListener() { // from class: com.movieboxpro.android.view.dialog.SingleReviewDialogFragment$setUpWebView$2
                @Override // com.movieboxpro.android.listener.ImageClickJSBridge.OnImageClickListener
                public void onAvatarClick(String str) {
                    if (str != null) {
                        if (App.getBBsInfo() != null) {
                            UserInfoActivity.Companion.start(SingleReviewDialogFragment.this.getContext(), str);
                        } else {
                            PandaForumAuthorizeActivity.Companion.start(SingleReviewDialogFragment.this.getContext());
                        }
                    }
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.movieboxpro.android.listener.ImageClickJSBridge.OnImageClickListener
                public void onImageClick(String[] array, String url) {
                    Intrinsics.checkNotNullParameter(array, "array");
                    Intrinsics.checkNotNullParameter(url, "url");
                    int length = array.length;
                    int i = 0;
                    int i2 = 0;
                    int i3 = 0;
                    while (true) {
                        if (i2 >= length) {
                            i3 = -1;
                            break;
                        }
                        String str = array[i2];
                        i2++;
                        int i4 = i3 + 1;
                        List split$default = StringsKt.split$default((CharSequence) str, new String[]{","}, false, 0, 6, (Object) null);
                        if (split$default.size() == 3 && Intrinsics.areEqual(url, split$default.get(2))) {
                            break;
                        }
                        i3 = i4;
                    }
                    if (i3 < 0 || i3 >= array.length) {
                        return;
                    }
                    List split$default2 = StringsKt.split$default((CharSequence) array[i3], new String[]{","}, false, 0, 6, (Object) null);
                    if (split$default2.size() == 3) {
                        String str2 = (String) split$default2.get(0);
                        String str3 = (String) split$default2.get(1);
                        String str4 = (String) split$default2.get(2);
                        if (!Intrinsics.areEqual(str2, "null") && !Intrinsics.areEqual(str3, "null")) {
                            switch (str3.hashCode()) {
                                case 49:
                                    if (str3.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                                        MovieDetailActivity.start(SingleReviewDialogFragment.this.getContext(), str2);
                                        return;
                                    }
                                    return;
                                case 50:
                                    if (str3.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                                        TvDetailActivity.start(SingleReviewDialogFragment.this.getContext(), str2);
                                        return;
                                    }
                                    return;
                                case 51:
                                    if (str3.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                                        MovieListDetailActivity.start(SingleReviewDialogFragment.this.getContext(), str2, "", "");
                                        return;
                                    }
                                    return;
                                default:
                                    return;
                            }
                        }
                        ArrayList arrayList = new ArrayList();
                        int length2 = array.length;
                        while (i < length2) {
                            String str5 = array[i];
                            i++;
                            List split$default3 = StringsKt.split$default((CharSequence) str5, new String[]{","}, false, 0, 6, (Object) null);
                            if (split$default3.size() == 3) {
                                arrayList.add(split$default3.get(2));
                            }
                        }
                        Context context = SingleReviewDialogFragment.this.getContext();
                        if (context == null) {
                            return;
                        }
                        ImageShowActivity.Companion.start(context, i3, arrayList);
                    }
                }

                @Override // com.movieboxpro.android.listener.ImageClickJSBridge.OnImageClickListener
                public void onReviewClick(String str) {
                    String str2 = str;
                    if (str2 == null || StringsKt.isBlank(str2)) {
                        return;
                    }
                    SingleReviewDialogFragment.Companion.newInstance(str).show(SingleReviewDialogFragment.this.getChildFragmentManager(), "SingleReviewDialogFragment");
                }
            });
        }
        if (this.chromeClient == null) {
            this.chromeClient = new MyWebChromeClient(this);
        }
        if (this.webviewClient == null) {
            this.webviewClient = new MyWebViewClient(this);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            WebView.setWebContentsDebuggingEnabled(App.isDebug);
        }
        webView.setWebChromeClient(this.chromeClient);
        WebViewClient webViewClient = this.webviewClient;
        Intrinsics.checkNotNull(webViewClient);
        webView.setWebViewClient(webViewClient);
        ImageClickJSBridge imageClickJSBridge2 = this.jsBridge;
        Intrinsics.checkNotNull(imageClickJSBridge2);
        webView.addJavascriptInterface(imageClickJSBridge2, "OpenImageBridge");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setAppCacheEnabled(false);
        webView.getSettings().setCacheMode(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setUpWebView$lambda-3  reason: not valid java name */
    public static final boolean m1112setUpWebView$lambda3(View view, MotionEvent motionEvent) {
        view.requestFocus();
        return motionEvent.getAction() == 2;
    }

    /* compiled from: SingleReviewDialogFragment.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/view/dialog/SingleReviewDialogFragment$MyWebChromeClient;", "Landroid/webkit/WebChromeClient;", "(Lcom/movieboxpro/android/view/dialog/SingleReviewDialogFragment;)V", "onJsAlert", "", "view", "Landroid/webkit/WebView;", "url", "", "message", "result", "Landroid/webkit/JsResult;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public final class MyWebChromeClient extends WebChromeClient {
        final /* synthetic */ SingleReviewDialogFragment this$0;

        public MyWebChromeClient(SingleReviewDialogFragment this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // android.webkit.WebChromeClient
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Intrinsics.checkNotNullParameter(view, "view");
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(message, "message");
            Intrinsics.checkNotNullParameter(result, "result");
            if (StringsKt.startsWith$default(message, "@", false, 2, (Object) null)) {
                Intrinsics.checkNotNullExpressionValue(message.substring(1), "this as java.lang.String).substring(startIndex)");
                return true;
            }
            return super.onJsAlert(view, url, message, result);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: SingleReviewDialogFragment.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/view/dialog/SingleReviewDialogFragment$MyWebViewClient;", "Landroid/webkit/WebViewClient;", "(Lcom/movieboxpro/android/view/dialog/SingleReviewDialogFragment;)V", "shouldOverrideUrlLoading", "", "view", "Landroid/webkit/WebView;", "url", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public final class MyWebViewClient extends WebViewClient {
        final /* synthetic */ SingleReviewDialogFragment this$0;

        public MyWebViewClient(SingleReviewDialogFragment this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Intrinsics.checkNotNullParameter(view, "view");
            Intrinsics.checkNotNullParameter(url, "url");
            SystemUtils.startBrowser((Activity) this.this$0.getActivity(), url);
            return true;
        }
    }
}
