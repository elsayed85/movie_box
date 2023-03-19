package com.movieboxpro.android.view.activity.web;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.LongSparseArray;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseSimpleActivity;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.service.FileDownloadService;
import com.movieboxpro.android.utils.ClipboardUtil;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.EventUtils;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.ReaderUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.utils.ZipUtils;
import com.movieboxpro.android.view.activity.PermissionRequestActivity;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import okhttp3.ResponseBody;
import retrofit2.Response;
/* compiled from: WebViewActivity.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 ,2\u00020\u0001:\u0001,B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0007H\u0002J&\u0010\u0011\u001a\u00020\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u00072\b\u0010\u0014\u001a\u0004\u0018\u00010\u0007H\u0003J\b\u0010\u0015\u001a\u00020\u000fH\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u000fH\u0017J\b\u0010\u0019\u001a\u00020\u000fH\u0016J\b\u0010\u001a\u001a\u00020\u000fH\u0016J\u0012\u0010\u001b\u001a\u00020\u000f2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0007H\u0002J\b\u0010\u001d\u001a\u00020\u000fH\u0002J\b\u0010\u001e\u001a\u00020\u000fH\u0014J\u001a\u0010\u001f\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u00172\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\b\u0010#\u001a\u00020\u000fH\u0014J\b\u0010$\u001a\u00020\u000fH\u0014J\u0010\u0010%\u001a\u00020\u000f2\u0006\u0010&\u001a\u00020\u0007H\u0002J\b\u0010'\u001a\u00020\u000fH\u0002J\u0014\u0010(\u001a\u0004\u0018\u00010\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u0007H\u0002J\"\u0010)\u001a\u00020\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u0007H\u0002J&\u0010+\u001a\u00020\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u00072\b\u0010\u0014\u001a\u0004\u0018\u00010\u0007H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/movieboxpro/android/view/activity/web/WebViewActivity;", "Lcom/movieboxpro/android/base/BaseSimpleActivity;", "()V", "CONTENT_DISPOSITION_PATTERN", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "dir", "", "fileSparseArray", "Landroid/util/LongSparseArray;", "isGoogle", "", "popupMenu", "Landroid/widget/PopupMenu;", "createSubtitleFile", "", FileDownloadService.PARAMS_KEY_PATH, "downloadBySystem", "url", "contentDisposition", "mimetype", "getFileName", "getLayoutResId", "", "initData", "initListener", "initView", "initWebVIew", "webUrl", "moveSubtitleFile", "onDestroy", "onKeyDown", "keyCode", NotificationCompat.CATEGORY_EVENT, "Landroid/view/KeyEvent;", "onPause", "onResume", "openBrowser", "targetUrl", "openMenu", "parseContentDisposition", "showSubtitle", "mimeType", "startDownload", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WebViewActivity extends BaseSimpleActivity {
    public static final Companion Companion = new Companion(null);
    private boolean isGoogle;
    private PopupMenu popupMenu;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String dir = "";
    private final LongSparseArray<String> fileSparseArray = new LongSparseArray<>();
    private final Pattern CONTENT_DISPOSITION_PATTERN = Pattern.compile("attachment;\\s*filename\\s*=\\s*(\"?)([^\"]*)\\1\\s*$", 2);

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: createSubtitleFile$lambda-11$lambda-10  reason: not valid java name */
    public static final boolean m901createSubtitleFile$lambda11$lambda10() {
        return true;
    }

    private final void getFileName() {
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
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

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public int getLayoutResId() {
        return R.layout.activity_web_view;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initView() {
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initListener() {
        ((ImageView) _$_findCachedViewById(R.id.ivBack)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.web.-$$Lambda$WebViewActivity$rcefToN-VYmiJp-FPgHKfrK5BMg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WebViewActivity.m902initListener$lambda0(WebViewActivity.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivClose)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.web.-$$Lambda$WebViewActivity$49rhkcZLZB31Zoxfm3i5kTYlY8w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WebViewActivity.m903initListener$lambda1(WebViewActivity.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivMore)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.web.-$$Lambda$WebViewActivity$ES0KScGAjBSRR5hfCtWTWMdXJ1U
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WebViewActivity.m904initListener$lambda2(WebViewActivity.this, view);
            }
        });
        ((WebView) _$_findCachedViewById(R.id.webView)).setDownloadListener(new DownloadListener() { // from class: com.movieboxpro.android.view.activity.web.-$$Lambda$WebViewActivity$njq-QbqNVM163XT379dyzMifYTc
            @Override // android.webkit.DownloadListener
            public final void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                WebViewActivity.m905initListener$lambda3(WebViewActivity.this, str, str2, str3, str4, j);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m902initListener$lambda0(WebViewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (((WebView) this$0._$_findCachedViewById(R.id.webView)).canGoBack()) {
            ((WebView) this$0._$_findCachedViewById(R.id.webView)).goBack();
        } else {
            this$0.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m903initListener$lambda1(WebViewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m904initListener$lambda2(WebViewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.openMenu();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-3  reason: not valid java name */
    public static final void m905initListener$lambda3(WebViewActivity this$0, String str, String str2, String str3, String str4, long j) {
        String str5;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String parseContentDisposition = this$0.parseContentDisposition(str3);
        if (parseContentDisposition == null) {
            parseContentDisposition = "";
        }
        if (parseContentDisposition.length() == 0) {
            parseContentDisposition = URLUtil.guessFileName(str, str3, str4);
            Intrinsics.checkNotNullExpressionValue(parseContentDisposition, "guessFileName(url, contentDisposition, mimeType)");
        }
        String extensionName = FileUtils.getExtensionName(parseContentDisposition);
        if (extensionName != null) {
            switch (extensionName.hashCode()) {
                case 96897:
                    str5 = "ass";
                    extensionName.equals(str5);
                    return;
                case 112675:
                    if (!extensionName.equals("rar")) {
                        return;
                    }
                    this$0.downloadBySystem(str, str3, str4);
                    return;
                case 114165:
                    str5 = "srt";
                    extensionName.equals(str5);
                    return;
                case 120609:
                    if (!extensionName.equals("zip")) {
                        return;
                    }
                    this$0.downloadBySystem(str, str3, str4);
                    return;
                default:
                    return;
            }
        }
    }

    private final String parseContentDisposition(String str) {
        try {
            Matcher matcher = this.CONTENT_DISPOSITION_PATTERN.matcher(str);
            Intrinsics.checkNotNullExpressionValue(matcher, "CONTENT_DISPOSITION_PATT…tcher(contentDisposition)");
            if (matcher.find()) {
                return matcher.group(2);
            }
            return null;
        } catch (IllegalStateException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showSubtitle(final String str, String str2, String str3) {
        ((ObservableSubscribeProxy) Http.getService().getOpenSubtitle(str).map(new Function() { // from class: com.movieboxpro.android.view.activity.web.-$$Lambda$WebViewActivity$gqYtGhd7SsEGMczul36zbDjrO9A
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                String m911showSubtitle$lambda4;
                m911showSubtitle$lambda4 = WebViewActivity.m911showSubtitle$lambda4(str, (ResponseBody) obj);
                return m911showSubtitle$lambda4;
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new WebViewActivity$showSubtitle$2(this, str, str2, str3));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showSubtitle$lambda-4  reason: not valid java name */
    public static final String m911showSubtitle$lambda4(String str, ResponseBody it) {
        String substring;
        Intrinsics.checkNotNullParameter(it, "it");
        StringBuilder sb = new StringBuilder();
        sb.append(Constant.DIR_DOWNLOAD);
        sb.append('/');
        if (str == null) {
            substring = null;
        } else {
            substring = str.substring(StringsKt.lastIndexOf$default((CharSequence) str, "/", 0, false, 6, (Object) null) + 1);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
        }
        sb.append((Object) substring);
        File chapterFile = ReaderUtils.getChapterFile(sb.toString());
        FileUtils.writeFileFromBytesByStream(chapterFile, it.bytes());
        String readFile2String = FileUtils.readFile2String(chapterFile, FileUtils.getCharset(chapterFile.getPath()));
        chapterFile.delete();
        return readFile2String;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void downloadBySystem(String str, String str2, String str3) {
        if (CommonUtils.havePermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"})) {
            startDownload(str, str2, str3);
        } else {
            PermissionRequestActivity.Companion.start(this, 5, 100);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x003f, code lost:
        if (r10.equals("zip") == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0049, code lost:
        if (r10.equals("srt") == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0052, code lost:
        if (r10.equals("rar") == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005b, code lost:
        if (r10.equals("ass") == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x005e, code lost:
        r10 = new java.io.File(com.movieboxpro.android.app.Constant.DIR_WEB_DOWNLOAD_SUBTITLE, r0);
        r11 = new kotlin.jvm.internal.Ref.BooleanRef();
        r9 = io.reactivex.Observable.just(r9).map(new com.movieboxpro.android.view.activity.web.$$Lambda$WebViewActivity$69KkpjqRmP8LYuVmJfHWMUpeXOg(r9, r11, r10)).compose(com.movieboxpro.android.utils.RxUtils.rxSchedulerHelper()).as(com.movieboxpro.android.utils.RxUtils.bindLifecycleOwner(r8));
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, "just(url)\n              …bindLifecycleOwner(this))");
        com.movieboxpro.android.utils.RxSubscribersKt.subscribeTo$default((com.uber.autodispose.ObservableSubscribeProxy) r9, new com.movieboxpro.android.view.activity.web.WebViewActivity$startDownload$2(r8), null, new com.movieboxpro.android.view.activity.web.WebViewActivity$startDownload$3(r8), null, new com.movieboxpro.android.view.activity.web.WebViewActivity$startDownload$4(r8, r11), 10, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:?, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void startDownload(final java.lang.String r9, java.lang.String r10, java.lang.String r11) {
        /*
            r8 = this;
            java.lang.String r0 = r8.parseContentDisposition(r10)
            if (r0 != 0) goto L8
            java.lang.String r0 = ""
        L8:
            r1 = r0
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            int r1 = r1.length()
            r2 = 0
            if (r1 != 0) goto L14
            r1 = 1
            goto L15
        L14:
            r1 = 0
        L15:
            if (r1 == 0) goto L20
            java.lang.String r0 = android.webkit.URLUtil.guessFileName(r9, r10, r11)
            java.lang.String r10 = "guessFileName(url, contentDisposition, mimetype)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r10)
        L20:
            java.lang.String r10 = com.movieboxpro.android.utils.FileUtils.getExtensionName(r0)
            java.io.File r11 = new java.io.File
            java.lang.String r1 = com.movieboxpro.android.app.Constant.DIR_WEB_DOWNLOAD_SUBTITLE
            r11.<init>(r1)
            com.movieboxpro.android.utils.FileUtils.createOrExistsDir(r11)
            if (r10 == 0) goto Lb5
            int r11 = r10.hashCode()
            switch(r11) {
                case 96897: goto L55;
                case 112675: goto L4c;
                case 114165: goto L43;
                case 120609: goto L39;
                default: goto L37;
            }
        L37:
            goto Lb5
        L39:
            java.lang.String r11 = "zip"
            boolean r10 = r10.equals(r11)
            if (r10 != 0) goto L5e
            goto Lb5
        L43:
            java.lang.String r11 = "srt"
            boolean r10 = r10.equals(r11)
            if (r10 != 0) goto L5e
            goto Lb5
        L4c:
            java.lang.String r11 = "rar"
            boolean r10 = r10.equals(r11)
            if (r10 != 0) goto L5e
            goto Lb5
        L55:
            java.lang.String r11 = "ass"
            boolean r10 = r10.equals(r11)
            if (r10 != 0) goto L5e
            goto Lb5
        L5e:
            java.io.File r10 = new java.io.File
            java.lang.String r11 = com.movieboxpro.android.app.Constant.DIR_WEB_DOWNLOAD_SUBTITLE
            r10.<init>(r11, r0)
            kotlin.jvm.internal.Ref$BooleanRef r11 = new kotlin.jvm.internal.Ref$BooleanRef
            r11.<init>()
            io.reactivex.Observable r0 = io.reactivex.Observable.just(r9)
            com.movieboxpro.android.view.activity.web.-$$Lambda$WebViewActivity$69KkpjqRmP8LYuVmJfHWMUpeXOg r1 = new com.movieboxpro.android.view.activity.web.-$$Lambda$WebViewActivity$69KkpjqRmP8LYuVmJfHWMUpeXOg
            r1.<init>()
            io.reactivex.Observable r9 = r0.map(r1)
            io.reactivex.ObservableTransformer r10 = com.movieboxpro.android.utils.RxUtils.rxSchedulerHelper()
            io.reactivex.Observable r9 = r9.compose(r10)
            r10 = r8
            androidx.lifecycle.LifecycleOwner r10 = (androidx.lifecycle.LifecycleOwner) r10
            com.uber.autodispose.AutoDisposeConverter r10 = com.movieboxpro.android.utils.RxUtils.bindLifecycleOwner(r10)
            io.reactivex.ObservableConverter r10 = (io.reactivex.ObservableConverter) r10
            java.lang.Object r9 = r9.as(r10)
            java.lang.String r10 = "just(url)\n              …bindLifecycleOwner(this))"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r10)
            r0 = r9
            com.uber.autodispose.ObservableSubscribeProxy r0 = (com.uber.autodispose.ObservableSubscribeProxy) r0
            com.movieboxpro.android.view.activity.web.WebViewActivity$startDownload$2 r9 = new com.movieboxpro.android.view.activity.web.WebViewActivity$startDownload$2
            r9.<init>(r8)
            r1 = r9
            kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
            r2 = 0
            com.movieboxpro.android.view.activity.web.WebViewActivity$startDownload$3 r9 = new com.movieboxpro.android.view.activity.web.WebViewActivity$startDownload$3
            r9.<init>(r8)
            r3 = r9
            kotlin.jvm.functions.Function1 r3 = (kotlin.jvm.functions.Function1) r3
            r4 = 0
            com.movieboxpro.android.view.activity.web.WebViewActivity$startDownload$4 r9 = new com.movieboxpro.android.view.activity.web.WebViewActivity$startDownload$4
            r9.<init>(r8, r11)
            r5 = r9
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5
            r6 = 10
            r7 = 0
            com.movieboxpro.android.utils.RxSubscribersKt.subscribeTo$default(r0, r1, r2, r3, r4, r5, r6, r7)
            goto Lbc
        Lb5:
            java.lang.Object[] r9 = new java.lang.Object[r2]
            java.lang.String r10 = "Only support zip,srt,ass file"
            com.movieboxpro.android.utils.ToastUtils.showShort(r10, r9)
        Lbc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.view.activity.web.WebViewActivity.startDownload(java.lang.String, java.lang.String, java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startDownload$lambda-5  reason: not valid java name */
    public static final File m912startDownload$lambda5(String str, Ref.BooleanRef downloadSuccess, File file, String it) {
        boolean z;
        Intrinsics.checkNotNullParameter(downloadSuccess, "$downloadSuccess");
        Intrinsics.checkNotNullParameter(file, "$file");
        Intrinsics.checkNotNullParameter(it, "it");
        Response<ResponseBody> execute = Http.getService().download(str).execute();
        if (execute.isSuccessful()) {
            ResponseBody body = execute.body();
            FileUtils.writeFileFromBytesByStream(file, body == null ? null : body.bytes());
            z = true;
        } else {
            z = false;
        }
        downloadSuccess.element = z;
        return file;
    }

    private final void openMenu() {
        if (this.popupMenu == null) {
            PopupMenu popupMenu = new PopupMenu(this, (ImageView) _$_findCachedViewById(R.id.ivMore));
            this.popupMenu = popupMenu;
            if (popupMenu != null) {
                popupMenu.inflate(R.menu.web_view_more_menu);
            }
            PopupMenu popupMenu2 = this.popupMenu;
            if (popupMenu2 != null) {
                popupMenu2.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: com.movieboxpro.android.view.activity.web.-$$Lambda$WebViewActivity$xsKAWk6cb59YNfqlMJVD_QrVO_s
                    @Override // android.widget.PopupMenu.OnMenuItemClickListener
                    public final boolean onMenuItemClick(MenuItem menuItem) {
                        boolean m910openMenu$lambda6;
                        m910openMenu$lambda6 = WebViewActivity.m910openMenu$lambda6(WebViewActivity.this, menuItem);
                        return m910openMenu$lambda6;
                    }
                });
            }
        }
        PopupMenu popupMenu3 = this.popupMenu;
        if (popupMenu3 == null) {
            return;
        }
        popupMenu3.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: openMenu$lambda-6  reason: not valid java name */
    public static final boolean m910openMenu$lambda6(WebViewActivity this$0, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int itemId = menuItem.getItemId();
        if (itemId == R.id.copy) {
            ClipboardUtil.copyContent(this$0, ((WebView) this$0._$_findCachedViewById(R.id.webView)).getUrl());
            ToastUtils.showShort("copy successfully", new Object[0]);
        } else if (itemId == R.id.default_browser) {
            String url = ((WebView) this$0._$_findCachedViewById(R.id.webView)).getUrl();
            if (url == null) {
                url = "";
            }
            this$0.openBrowser(url);
        } else if (itemId != R.id.refresh) {
            return false;
        } else {
            ((WebView) this$0._$_findCachedViewById(R.id.webView)).reload();
        }
        return true;
    }

    private final void openBrowser(String str) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        startActivity(intent);
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initData() {
        String stringExtra = getIntent().getStringExtra("URL");
        if (stringExtra == null) {
            stringExtra = "";
        }
        String str = stringExtra;
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "subscene", false, 2, (Object) null)) {
            ((TextView) _$_findCachedViewById(R.id.tvTitle)).setText("Subscene");
            CommonExtKt.onMobEvent(this, "SubsceneSubtitlesSearch");
            EventUtils.event("Subscene字幕搜索");
            this.isGoogle = false;
        } else if (StringsKt.contains$default((CharSequence) str, (CharSequence) "google", false, 2, (Object) null)) {
            ((TextView) _$_findCachedViewById(R.id.tvTitle)).setText("Google");
            CommonExtKt.onMobEvent(this, "GoogleSubtitlesSearch");
            EventUtils.event("谷歌搜索字幕");
            this.isGoogle = true;
        }
        String stringExtra2 = getIntent().getStringExtra("DIR");
        this.dir = stringExtra2 != null ? stringExtra2 : "";
        initWebVIew(stringExtra);
    }

    private final void initWebVIew(String str) {
        WebView webView = (WebView) _$_findCachedViewById(R.id.webView);
        if (str == null) {
            str = "";
        }
        webView.loadUrl(str);
        ((WebView) _$_findCachedViewById(R.id.webView)).getSettings().setSupportZoom(true);
        ((WebView) _$_findCachedViewById(R.id.webView)).getSettings().setJavaScriptEnabled(true);
        ((WebView) _$_findCachedViewById(R.id.webView)).setWebViewClient(new WebViewClient() { // from class: com.movieboxpro.android.view.activity.web.WebViewActivity$initWebVIew$1
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(url, "url");
                return false;
            }

            @Override // android.webkit.WebViewClient
            public void onPageStarted(WebView webView2, String str2, Bitmap bitmap) {
                super.onPageStarted(webView2, str2, bitmap);
                ProgressBar progressBar = (ProgressBar) WebViewActivity.this._$_findCachedViewById(R.id.progressBar);
                if (progressBar != null) {
                    CommonExtKt.visible(progressBar);
                }
                ProgressBar progressBar2 = (ProgressBar) WebViewActivity.this._$_findCachedViewById(R.id.progressBar);
                if (progressBar2 == null) {
                    return;
                }
                progressBar2.setProgress(0);
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView2, String str2) {
                super.onPageFinished(webView2, str2);
                ProgressBar progressBar = (ProgressBar) WebViewActivity.this._$_findCachedViewById(R.id.progressBar);
                if (progressBar != null) {
                    CommonExtKt.gone(progressBar);
                }
                ProgressBar progressBar2 = (ProgressBar) WebViewActivity.this._$_findCachedViewById(R.id.progressBar);
                if (progressBar2 != null) {
                    progressBar2.setProgress(100);
                }
                String guessFileName = URLUtil.guessFileName(str2, "", "");
                if (guessFileName == null) {
                    guessFileName = "";
                }
                String extensionName = FileUtils.getExtensionName(guessFileName);
                if (Intrinsics.areEqual("srt", extensionName) || Intrinsics.areEqual("ass", extensionName)) {
                    WebViewActivity.this.showSubtitle(str2, "", "");
                }
            }
        });
        ((WebView) _$_findCachedViewById(R.id.webView)).setWebChromeClient(new WebChromeClient() { // from class: com.movieboxpro.android.view.activity.web.WebViewActivity$initWebVIew$2
            @Override // android.webkit.WebChromeClient
            public void onProgressChanged(WebView webView2, int i) {
                super.onProgressChanged(webView2, i);
                ((ProgressBar) WebViewActivity.this._$_findCachedViewById(R.id.progressBar)).setProgress(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseSimpleActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    private final void moveSubtitleFile() {
        File[] listFiles = new File(this.dir).listFiles();
        if (listFiles == null) {
            return;
        }
        int i = 0;
        int length = listFiles.length;
        while (i < length) {
            File file = listFiles[i];
            i++;
            if (file.isDirectory()) {
                List<File> listFilesInDirWithFilter = FileUtils.listFilesInDirWithFilter(file, $$Lambda$WebViewActivity$vWu3MWtYiT_9n6KDUimmuP4gZc.INSTANCE, true);
                if (listFilesInDirWithFilter != null) {
                    for (File file2 : listFilesInDirWithFilter) {
                        FileUtils.move(file2, new File(this.dir + ((Object) File.separator) + ((Object) file2.getName())), null);
                    }
                }
                file.delete();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: moveSubtitleFile$lambda-9$lambda-7  reason: not valid java name */
    public static final boolean m909moveSubtitleFile$lambda9$lambda7(File file) {
        String extensionName = FileUtils.getExtensionName(file.getName());
        return StringsKt.equals("srt", extensionName, true) || StringsKt.equals("ass", extensionName, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void createSubtitleFile(final String str) {
        ((ObservableSubscribeProxy) Observable.just(str).map(new Function() { // from class: com.movieboxpro.android.view.activity.web.-$$Lambda$WebViewActivity$jmopvVZGW0ArXrB5Laulnl1J0vQ
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                Boolean m900createSubtitleFile$lambda11;
                m900createSubtitleFile$lambda11 = WebViewActivity.m900createSubtitleFile$lambda11(str, this, (String) obj);
                return m900createSubtitleFile$lambda11;
            }
        }).compose(RxUtils.rxSchedulerComputeHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new WebViewActivity$createSubtitleFile$2(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: createSubtitleFile$lambda-11  reason: not valid java name */
    public static final Boolean m900createSubtitleFile$lambda11(String str, WebViewActivity this$0, String it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        String extensionName = FileUtils.getExtensionName(str == null ? "" : str);
        File file = new File(str);
        boolean z = true;
        if (Intrinsics.areEqual(extensionName, "zip")) {
            ZipUtils.unzipFile(str, this$0.dir);
            this$0.moveSubtitleFile();
            z = FileUtils.delete(file);
        } else if (Intrinsics.areEqual(extensionName, "rar")) {
            FileUtils.unrar(file.getPath(), this$0.dir);
            FileUtils.delete(file);
            this$0.moveSubtitleFile();
        } else {
            z = FileUtils.copyOrMoveFile(file, new File(this$0.dir + ((Object) File.separator) + ((Object) file.getName())), $$Lambda$WebViewActivity$KjHeYnm5vToWk6xBTsDaadRI8kk.INSTANCE, true);
        }
        return Boolean.valueOf(z);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && ((WebView) _$_findCachedViewById(R.id.webView)).canGoBack()) {
            ((WebView) _$_findCachedViewById(R.id.webView)).goBack();
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseSimpleActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        ((WebView) _$_findCachedViewById(R.id.webView)).onPause();
        super.onPause();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseSimpleActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        ((WebView) _$_findCachedViewById(R.id.webView)).onResume();
        super.onResume();
    }

    /* compiled from: WebViewActivity.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bJ \u0010\n\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b¨\u0006\u000b"}, d2 = {"Lcom/movieboxpro/android/view/activity/web/WebViewActivity$Companion;", "", "()V", "starter", "", "context", "Landroid/content/Context;", "url", "", "dir", "starterWithFlag", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void starter(Context context, String url, String dir) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(dir, "dir");
            Intent intent = new Intent(context, WebViewActivity.class);
            intent.putExtra("URL", url);
            intent.putExtra("DIR", dir);
            if (context == null) {
                return;
            }
            context.startActivity(intent);
        }

        public final void starterWithFlag(Context context, String url, String dir) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(dir, "dir");
            Intent intent = new Intent(context, WebViewActivity.class);
            intent.putExtra("URL", url);
            intent.putExtra("DIR", dir);
            intent.setFlags(268435456);
            if (context == null) {
                return;
            }
            context.startActivity(intent);
        }
    }
}
