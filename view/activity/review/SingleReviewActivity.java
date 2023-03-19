package com.movieboxpro.android.view.activity.review;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.lxj.xpopup.XPopup;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseSimpleActivity;
import com.movieboxpro.android.base.CommBaseAdapter;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpUtils;
import com.movieboxpro.android.http.ServerException;
import com.movieboxpro.android.listener.ImageClickJSBridge;
import com.movieboxpro.android.model.BBsResponseModel;
import com.movieboxpro.android.model.ReviewModel;
import com.movieboxpro.android.model.ReviewRecordModel;
import com.movieboxpro.android.model.SingleReviewModel;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.InputMethodUtils;
import com.movieboxpro.android.utils.ReviewRecordUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ShellUtil;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.utils.XpopImageLoader;
import com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity;
import com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity;
import com.movieboxpro.android.view.activity.movielist.MovieListDetailActivity;
import com.movieboxpro.android.view.activity.review.FullReplyActivity;
import com.movieboxpro.android.view.dialog.SingleReviewDialogFragment;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import org.eclipse.jetty.http.MimeTypes;
/* compiled from: SingleReviewActivity.kt */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0017\n\u0002\u0010!\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0004\u0018\u0000 D2\u00020\u0001:\u0003DEFB\u0005¢\u0006\u0002\u0010\u0002J¬\u0001\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00122\b\u0010\u0018\u001a\u0004\u0018\u00010\u00122\b\u0010\u0019\u001a\u0004\u0018\u00010\u00122\b\u0010\u001a\u001a\u0004\u0018\u00010\u00122\b\u0010\u001b\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u001c\u001a\u00020\u00122\b\u0010\u001d\u001a\u0004\u0018\u00010\u00122\b\u0010\u001e\u001a\u0004\u0018\u00010\u00122\b\u0010\u001f\u001a\u0004\u0018\u00010\u00122\b\u0010 \u001a\u0004\u0018\u00010\u00122\b\u0010!\u001a\u0004\u0018\u00010\u00122\b\u0010\"\u001a\u0004\u0018\u00010\u00122\u0006\u0010#\u001a\u00020\u0016H\u0002J\u0018\u0010$\u001a\u00020\u00102\u0006\u0010%\u001a\u00020\u00052\u0006\u0010&\u001a\u00020\u0016H\u0002J\b\u0010'\u001a\u00020\u0012H\u0002J\b\u0010(\u001a\u00020\u0016H\u0016J\b\u0010)\u001a\u00020\u0010H\u0002J\b\u0010*\u001a\u00020\u0016H\u0014J\b\u0010+\u001a\u00020\u0010H\u0002J\u0016\u0010,\u001a\u00020\u00102\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00050.H\u0002J\b\u0010/\u001a\u00020\u0010H\u0016J\b\u00100\u001a\u00020\u0010H\u0016J\b\u00101\u001a\u00020\u0010H\u0016J\"\u00102\u001a\u00020\u00102\u0006\u00103\u001a\u00020\u00162\u0006\u00104\u001a\u00020\u00162\b\u00105\u001a\u0004\u0018\u000106H\u0014J\u001a\u00107\u001a\u00020\u00102\b\u0010%\u001a\u0004\u0018\u00010\u00052\u0006\u00108\u001a\u000209H\u0002J\b\u0010:\u001a\u00020\u0010H\u0002J\u0018\u0010;\u001a\u00020\u00102\u0006\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020\u0012H\u0002J\b\u0010?\u001a\u00020\u0010H\u0002J\u0010\u0010@\u001a\u00020\u00102\u0006\u0010<\u001a\u00020=H\u0003J \u0010A\u001a\u00020\u00102\u000e\u0010B\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010C2\u0006\u0010&\u001a\u00020\u0016H\u0002R\u0016\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006G"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/SingleReviewActivity;", "Lcom/movieboxpro/android/base/BaseSimpleActivity;", "()V", "adapter", "Lcom/movieboxpro/android/base/CommBaseAdapter;", "Lcom/movieboxpro/android/model/ReviewModel;", "chromeClient", "Landroid/webkit/WebChromeClient;", "jsBridge", "Lcom/movieboxpro/android/listener/ImageClickJSBridge;", "popupMenu", "Landroid/widget/PopupMenu;", "reviewModel", "webviewClient", "Landroid/webkit/WebViewClient;", "addReply", "", "uid", "", "username", "tid", "htmlOn", "", "auth", "authkey", "formash", FirebaseAnalytics.Param.CONTENT, "userFile", "repquote", "authorId", "pid", "authorUsername", "authorAvatar", "authorTime", "authorContent", "reply", "doLike", "model", "position", "getBBSApiAPPID", "getLayoutResId", "getReviewDetail", "getStatusColor", "goFullReview", "initAdapter", "list", "", "initData", "initListener", "initView", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "openPopMenu", "view", "Landroid/view/View;", "saveContent", "setHtml", "webView", "Landroid/webkit/WebView;", "html", "setRecordReview", "setUpWebView", "toImageShow", "images", "", "Companion", "MyWebChromeClient", "MyWebViewClient", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SingleReviewActivity extends BaseSimpleActivity {
    public static final Companion Companion = new Companion(null);
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private CommBaseAdapter<ReviewModel> adapter;
    private WebChromeClient chromeClient;
    private ImageClickJSBridge jsBridge;
    private PopupMenu popupMenu;
    private ReviewModel reviewModel;
    private WebViewClient webviewClient;

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
        return R.layout.fragment_single_review;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    protected int getStatusColor() {
        return R.color.color_main;
    }

    /* compiled from: SingleReviewActivity.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n¨\u0006\u000b"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/SingleReviewActivity$Companion;", "", "()V", TtmlNode.START, "", "context", "Landroid/app/Activity;", "pid", "", "requestCode", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Activity context, String pid, int i) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(pid, "pid");
            Intent intent = new Intent(context, SingleReviewActivity.class);
            intent.putExtra("pid", pid);
            context.startActivityForResult(intent, i);
        }
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initListener() {
        ((ImageView) _$_findCachedViewById(R.id.ivBack)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SingleReviewActivity$b1oTrmmBkPjgm0dL0R8keaLAzSg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SingleReviewActivity.m753initListener$lambda0(SingleReviewActivity.this, view);
            }
        });
        InputMethodUtils.registerSoftInputChangedListener(this, new InputMethodUtils.OnSoftInputChangedListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SingleReviewActivity$apMuDU-Ynll3ZyILQf2ZeaTbjJM
            @Override // com.movieboxpro.android.utils.InputMethodUtils.OnSoftInputChangedListener
            public final void onSoftInputChanged(int i) {
                SingleReviewActivity.m754initListener$lambda1(SingleReviewActivity.this, i);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.llReview)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SingleReviewActivity$1qOzbSd7HzkukEQNXjQmtmiEOKY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SingleReviewActivity.m755initListener$lambda2(SingleReviewActivity.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivSend)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SingleReviewActivity$Mxbx0XDeOtiJcwUXP0SmVEGf7Ak
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SingleReviewActivity.m756initListener$lambda4(SingleReviewActivity.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivFullReply)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SingleReviewActivity$TF3LeFFreZ3VztVkgawJYa3MRz4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SingleReviewActivity.m757initListener$lambda5(SingleReviewActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m753initListener$lambda0(SingleReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        InputMethodUtils.hideSoftInput(this$0);
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m754initListener$lambda1(SingleReviewActivity this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i > 100) {
            LinearLayout llBottom = (LinearLayout) this$0._$_findCachedViewById(R.id.llBottom);
            Intrinsics.checkNotNullExpressionValue(llBottom, "llBottom");
            CommonExtKt.gone(llBottom);
            LinearLayout llEdit = (LinearLayout) this$0._$_findCachedViewById(R.id.llEdit);
            Intrinsics.checkNotNullExpressionValue(llEdit, "llEdit");
            CommonExtKt.visible(llEdit);
            this$0.setRecordReview();
            return;
        }
        LinearLayout llBottom2 = (LinearLayout) this$0._$_findCachedViewById(R.id.llBottom);
        Intrinsics.checkNotNullExpressionValue(llBottom2, "llBottom");
        CommonExtKt.visible(llBottom2);
        LinearLayout llEdit2 = (LinearLayout) this$0._$_findCachedViewById(R.id.llEdit);
        Intrinsics.checkNotNullExpressionValue(llEdit2, "llEdit");
        CommonExtKt.gone(llEdit2);
        this$0.saveContent();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m755initListener$lambda2(SingleReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ReviewRecordUtils reviewRecordUtils = ReviewRecordUtils.Companion.get();
        ReviewModel reviewModel = this$0.reviewModel;
        ReviewRecordModel reviewRecord = reviewRecordUtils.getReviewRecord(reviewModel == null ? null : reviewModel.getPid(), "text");
        if (reviewRecord != null) {
            ((EditText) this$0._$_findCachedViewById(R.id.etContent)).setText(reviewRecord.getContent());
            ((EditText) this$0._$_findCachedViewById(R.id.etContent)).setSelection(reviewRecord.getContent().length());
            ((EditText) this$0._$_findCachedViewById(R.id.etContent)).requestFocus();
            InputMethodUtils.showSoftInput((EditText) this$0._$_findCachedViewById(R.id.etContent));
            return;
        }
        ((EditText) this$0._$_findCachedViewById(R.id.etContent)).setText("");
        ((EditText) this$0._$_findCachedViewById(R.id.etContent)).requestFocus();
        InputMethodUtils.showSoftInput((EditText) this$0._$_findCachedViewById(R.id.etContent));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-4  reason: not valid java name */
    public static final void m756initListener$lambda4(SingleReviewActivity this$0, View view) {
        String for_quote;
        String author;
        String format;
        String for_quote2;
        String for_quote3;
        String substring;
        String author2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String obj = ((EditText) this$0._$_findCachedViewById(R.id.etContent)).getText().toString();
        if (StringsKt.isBlank(obj)) {
            return;
        }
        UserModel.UserData userData = App.getUserData();
        Intrinsics.checkNotNullExpressionValue(userData, "getUserData()");
        UserModel.BBsInfo bBsInfo = App.getBBsInfo();
        ReviewModel reviewModel = this$0.reviewModel;
        String str = "";
        if (((reviewModel == null || (for_quote = reviewModel.getFor_quote()) == null) ? 0 : for_quote.length()) > 50) {
            ReviewModel reviewModel2 = this$0.reviewModel;
            if (reviewModel2 == null || (for_quote3 = reviewModel2.getFor_quote()) == null) {
                substring = "";
            } else {
                substring = for_quote3.substring(0, 50);
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Object[] objArr = new Object[3];
            ReviewModel reviewModel3 = this$0.reviewModel;
            if (reviewModel3 != null && (author2 = reviewModel3.getAuthor()) != null) {
                str = author2;
            }
            objArr[0] = str;
            objArr[1] = TimeUtils.formatTime3(System.currentTimeMillis());
            objArr[2] = substring;
            format = String.format("%s replied at %s\n%s", Arrays.copyOf(objArr, 3));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        } else {
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            Object[] objArr2 = new Object[3];
            ReviewModel reviewModel4 = this$0.reviewModel;
            if (reviewModel4 == null || (author = reviewModel4.getAuthor()) == null) {
                author = "";
            }
            objArr2[0] = author;
            objArr2[1] = TimeUtils.formatTime3(System.currentTimeMillis());
            ReviewModel reviewModel5 = this$0.reviewModel;
            if (reviewModel5 != null && (for_quote2 = reviewModel5.getFor_quote()) != null) {
                str = for_quote2;
            }
            objArr2[2] = str;
            format = String.format("%s replied at %s\n%s", Arrays.copyOf(objArr2, 3));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        }
        String str2 = format;
        ReviewModel reviewModel6 = this$0.reviewModel;
        if (reviewModel6 == null) {
            return;
        }
        this$0.addReply(bBsInfo.getUid(), userData.username, reviewModel6.getTid(), 0, bBsInfo.getAuth(), bBsInfo.getAuthkey(), bBsInfo.getFormhash(), obj, "", str2, reviewModel6.getAuthorid(), reviewModel6.getPid(), reviewModel6.getAuthor(), reviewModel6.getAvatar(), String.valueOf(reviewModel6.getAdd_time()), reviewModel6.getFor_quote(), 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-5  reason: not valid java name */
    public static final void m757initListener$lambda5(SingleReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.goFullReview();
    }

    private final void saveContent() {
        String obj = ((EditText) _$_findCachedViewById(R.id.etContent)).getText().toString();
        ReviewRecordUtils reviewRecordUtils = ReviewRecordUtils.Companion.get();
        ReviewModel reviewModel = this.reviewModel;
        reviewRecordUtils.saveReviewRecord(reviewModel == null ? null : reviewModel.getPid(), "text", obj);
    }

    private final void setRecordReview() {
        ReviewRecordUtils reviewRecordUtils = ReviewRecordUtils.Companion.get();
        ReviewModel reviewModel = this.reviewModel;
        ReviewRecordModel reviewRecord = reviewRecordUtils.getReviewRecord(reviewModel == null ? null : reviewModel.getPid(), "text");
        if (reviewRecord == null) {
            ((EditText) _$_findCachedViewById(R.id.etContent)).setText("");
            return;
        }
        ((EditText) _$_findCachedViewById(R.id.etContent)).setText(reviewRecord.getContent());
        ((EditText) _$_findCachedViewById(R.id.etContent)).setSelection(reviewRecord.getContent().length());
    }

    private final void goFullReview() {
        String for_quote;
        String author;
        String for_quote2;
        String format;
        String pid;
        String for_quote3;
        String substring;
        String author2;
        ReviewModel reviewModel = this.reviewModel;
        if (((reviewModel == null || (for_quote = reviewModel.getFor_quote()) == null) ? 0 : for_quote.length()) > 50) {
            ReviewModel reviewModel2 = this.reviewModel;
            if (reviewModel2 == null || (for_quote3 = reviewModel2.getFor_quote()) == null) {
                substring = "";
            } else {
                substring = for_quote3.substring(0, 50);
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Object[] objArr = new Object[3];
            ReviewModel reviewModel3 = this.reviewModel;
            if (reviewModel3 == null || (author2 = reviewModel3.getAuthor()) == null) {
                author2 = "";
            }
            objArr[0] = author2;
            objArr[1] = TimeUtils.formatTime4(System.currentTimeMillis());
            objArr[2] = Intrinsics.stringPlus(substring, "...");
            format = String.format("%s replied at %s\n%s", Arrays.copyOf(objArr, 3));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        } else {
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            Object[] objArr2 = new Object[3];
            ReviewModel reviewModel4 = this.reviewModel;
            if (reviewModel4 == null || (author = reviewModel4.getAuthor()) == null) {
                author = "";
            }
            objArr2[0] = author;
            objArr2[1] = TimeUtils.formatTime4(System.currentTimeMillis());
            ReviewModel reviewModel5 = this.reviewModel;
            if (reviewModel5 == null || (for_quote2 = reviewModel5.getFor_quote()) == null) {
                for_quote2 = "";
            }
            objArr2[2] = for_quote2;
            format = String.format("%s replied at %s\n%s", Arrays.copyOf(objArr2, 3));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        }
        String str = format;
        FullReplyActivity.Companion companion = FullReplyActivity.Companion;
        SingleReviewActivity singleReviewActivity = this;
        String obj = ((EditText) _$_findCachedViewById(R.id.etContent)).getText().toString();
        ReviewModel reviewModel6 = this.reviewModel;
        companion.start(singleReviewActivity, obj, (reviewModel6 == null || (pid = reviewModel6.getPid()) == null) ? "" : pid, this.reviewModel, str, 1, 1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1 && i2 == -1) {
            setResult(-1);
            finish();
        } else if (i == 2 && i2 == -1) {
            getReviewDetail();
        }
    }

    private final void addReply(String str, String str2, String str3, int i, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, int i2) {
        Observable<R> map = Http.getService().reply(API.BBS_URL, "sendreply", str2, str, str3, i, str4, str5, str6, "", "yes", "", URLEncoder.encode(Pattern.compile(ShellUtil.COMMAND_LINE_END).matcher(str7).replaceAll(""), "UTF-8"), "", str8, str9, str10, getBBSApiAPPID(), str11, str12, str13, str14, str15, i2, 1).map($$Lambda$SingleReviewActivity$UKHC2dJyR_koYftkWx7Ao0_de8.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(map, "getService().reply(API.B…s.java)\n                }");
        RxSubscribersKt.transform(map, this).subscribe(new Consumer() { // from class: com.movieboxpro.android.view.activity.review.SingleReviewActivity$addReply$$inlined$transformSubscribe$default$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(T it) {
                ReviewModel reviewModel;
                Intrinsics.checkNotNullExpressionValue(it, "it");
                BBsResponseModel bBsResponseModel = (BBsResponseModel) it;
                if (Intrinsics.areEqual("post_reply_succeed", bBsResponseModel.getMessage().getMessageval())) {
                    ReviewRecordUtils reviewRecordUtils = ReviewRecordUtils.Companion.get();
                    reviewModel = SingleReviewActivity.this.reviewModel;
                    reviewRecordUtils.deleteReviewRecord(reviewModel == null ? null : reviewModel.getPid());
                    SingleReviewActivity.this.setResult(-1);
                    SingleReviewActivity.this.finish();
                } else {
                    ToastUtils.showShort(bBsResponseModel.getMessage().getMessageval(), new Object[0]);
                }
                SingleReviewActivity.this.hideLoadingView();
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.SingleReviewActivity$addReply$$inlined$transformSubscribe$default$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                ApiException handleException = ApiException.handleException(th);
                Intrinsics.checkNotNullExpressionValue(handleException, "handleException(it)");
                SingleReviewActivity.this.hideLoadingView();
                ToastUtils.showShort(Intrinsics.stringPlus("Send Failed:", handleException.getMessage()), new Object[0]);
                if (th instanceof ServerException) {
                    ServerException serverException = (ServerException) th;
                }
            }
        }, new Action() { // from class: com.movieboxpro.android.view.activity.review.SingleReviewActivity$addReply$$inlined$transformSubscribe$default$3
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.SingleReviewActivity$addReply$$inlined$transformSubscribe$default$4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                SingleReviewActivity.this.showLoadingView();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: addReply$lambda-6  reason: not valid java name */
    public static final BBsResponseModel m750addReply$lambda6(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return (BBsResponseModel) JSON.parseObject(it, BBsResponseModel.class);
    }

    private final String getBBSApiAPPID() {
        long currentTime = TimeUtils.getCurrentTime() / 1000;
        String md5 = HttpUtils.md5("27");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.CHINA, "%d%s", Arrays.copyOf(new Object[]{Long.valueOf(currentTime), md5}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        return format;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initData() {
        getReviewDetail();
    }

    private final void getReviewDetail() {
        if (App.getBBsInfo() == null) {
            PandaForumAuthorizeActivity.Companion.start(this);
            return;
        }
        Observable<R> compose = Http.getService().getReviewByPid(API.BBS_URL, "get_thread_pid", getIntent().getStringExtra("pid"), App.getBBsInfo().getBbs_uid()).compose(RxUtils.rxTranslate2Bean(SingleReviewModel.class));
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
        RxSubscribersKt.transform(compose, this).subscribe(new Consumer() { // from class: com.movieboxpro.android.view.activity.review.SingleReviewActivity$getReviewDetail$$inlined$subscribeToBean$default$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(T it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                SingleReviewModel singleReviewModel = (SingleReviewModel) it;
                if (singleReviewModel.getList() == null || singleReviewModel.getList().size() <= 0) {
                    return;
                }
                SingleReviewActivity.this.hideLoadingView();
                ReviewModel item = singleReviewModel.getList().get(0);
                SingleReviewActivity.this.reviewModel = item;
                SingleReviewActivity singleReviewActivity = SingleReviewActivity.this;
                Intrinsics.checkNotNullExpressionValue(item, "item");
                singleReviewActivity.initAdapter(CollectionsKt.arrayListOf(item));
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.SingleReviewActivity$getReviewDetail$$inlined$subscribeToBean$default$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                ApiException handleException = ApiException.handleException(th);
                Intrinsics.checkNotNullExpressionValue(handleException, "handleException(it)");
                SingleReviewActivity.this.hideLoadingView();
                ToastUtils.showShort(Intrinsics.stringPlus("Load failed:", handleException.getMessage()), new Object[0]);
                if (th instanceof ServerException) {
                    ServerException serverException = (ServerException) th;
                }
            }
        }, new Action() { // from class: com.movieboxpro.android.view.activity.review.SingleReviewActivity$getReviewDetail$$inlined$subscribeToBean$default$3
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.SingleReviewActivity$getReviewDetail$$inlined$subscribeToBean$default$4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                SingleReviewActivity.this.showLoadingView();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initAdapter(List<ReviewModel> list) {
        ReviewModel reviewModel = this.reviewModel;
        if (Intrinsics.areEqual(reviewModel == null ? null : reviewModel.getMessage_type(), "text")) {
            this.adapter = new CommBaseAdapter<>(R.layout.adapter_review_detail_item, new SingleReviewActivity$initAdapter$1(this), list);
        } else {
            ReviewModel reviewModel2 = this.reviewModel;
            if (Intrinsics.areEqual(reviewModel2 != null ? reviewModel2.getMessage_type() : null, "html")) {
                this.adapter = new CommBaseAdapter<>(R.layout.adapter_review_top_webview_item, new SingleReviewActivity$initAdapter$2(this), list);
            }
        }
        CommBaseAdapter<ReviewModel> commBaseAdapter = this.adapter;
        Intrinsics.checkNotNull(commBaseAdapter);
        commBaseAdapter.addChildClickViewIds(R.id.llLike, R.id.llReview, R.id.ivAvatar, R.id.ivMore);
        ((RecyclerView) _$_findCachedViewById(R.id.recyclerView)).setLayoutManager(new LinearLayoutManager(this));
        ((RecyclerView) _$_findCachedViewById(R.id.recyclerView)).setAdapter(this.adapter);
        CommBaseAdapter<ReviewModel> commBaseAdapter2 = this.adapter;
        Intrinsics.checkNotNull(commBaseAdapter2);
        commBaseAdapter2.setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SingleReviewActivity$HDlsZ-puQ7tpzTze18i2bShqCD4
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                SingleReviewActivity.m752initAdapter$lambda14(SingleReviewActivity.this, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initAdapter$lambda-14  reason: not valid java name */
    public static final void m752initAdapter$lambda14(SingleReviewActivity this$0, BaseQuickAdapter noName_0, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(view, "view");
        ReviewModel reviewModel = this$0.reviewModel;
        if (reviewModel == null) {
            return;
        }
        int id = view.getId();
        if (id == R.id.ivMore) {
            this$0.openPopMenu(reviewModel, view);
        } else if (id == R.id.llLike) {
            if (App.getBBsInfo() != null) {
                if (reviewModel.getStatus() != 1) {
                    this$0.doLike(reviewModel, i);
                    return;
                } else {
                    ToastUtils.showShort("You have already liked", new Object[0]);
                    return;
                }
            }
            PandaForumAuthorizeActivity.Companion.start(this$0);
        } else if (id != R.id.llReview) {
        } else {
            ReviewRecordUtils reviewRecordUtils = ReviewRecordUtils.Companion.get();
            ReviewModel reviewModel2 = this$0.reviewModel;
            ReviewRecordModel reviewRecord = reviewRecordUtils.getReviewRecord(reviewModel2 == null ? null : reviewModel2.getPid(), "text");
            if (reviewRecord != null) {
                ((EditText) this$0._$_findCachedViewById(R.id.etContent)).setText(reviewRecord.getContent());
                ((EditText) this$0._$_findCachedViewById(R.id.etContent)).setSelection(reviewRecord.getContent().length());
                ((EditText) this$0._$_findCachedViewById(R.id.etContent)).requestFocus();
                InputMethodUtils.showSoftInput((EditText) this$0._$_findCachedViewById(R.id.etContent));
                return;
            }
            ((EditText) this$0._$_findCachedViewById(R.id.etContent)).setText("");
            ((EditText) this$0._$_findCachedViewById(R.id.etContent)).requestFocus();
            InputMethodUtils.showSoftInput((EditText) this$0._$_findCachedViewById(R.id.etContent));
        }
    }

    private final void openPopMenu(final ReviewModel reviewModel, View view) {
        Menu menu;
        if (App.getBBsInfo() == null) {
            PandaForumAuthorizeActivity.Companion.start(this);
            return;
        }
        PopupMenu popupMenu = new PopupMenu(this, view);
        this.popupMenu = popupMenu;
        Intrinsics.checkNotNull(popupMenu);
        popupMenu.inflate(R.menu.review_more_action_menu);
        final String bbs_uid = App.getBBsInfo().getBbs_uid();
        PopupMenu popupMenu2 = this.popupMenu;
        MenuItem findItem = (popupMenu2 == null || (menu = popupMenu2.getMenu()) == null) ? null : menu.findItem(R.id.edit);
        if (findItem != null) {
            findItem.setVisible(Intrinsics.areEqual(bbs_uid, reviewModel != null ? reviewModel.getAuthorid() : null));
        }
        PopupMenu popupMenu3 = this.popupMenu;
        Intrinsics.checkNotNull(popupMenu3);
        popupMenu3.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SingleReviewActivity$ngrUL4kyyTOnaDoRYw5REUPVAeQ
            @Override // android.widget.PopupMenu.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                boolean m762openPopMenu$lambda16;
                m762openPopMenu$lambda16 = SingleReviewActivity.m762openPopMenu$lambda16(ReviewModel.this, this, bbs_uid, menuItem);
                return m762openPopMenu$lambda16;
            }
        });
        PopupMenu popupMenu4 = this.popupMenu;
        Intrinsics.checkNotNull(popupMenu4);
        popupMenu4.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: openPopMenu$lambda-16  reason: not valid java name */
    public static final boolean m762openPopMenu$lambda16(ReviewModel reviewModel, SingleReviewActivity this$0, String str, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int itemId = menuItem.getItemId();
        if (itemId == R.id.edit) {
            if (reviewModel == null) {
                return true;
            }
            String pid = reviewModel.getPid();
            Intrinsics.checkNotNullExpressionValue(pid, "model.pid");
            EditReviewActivity.Companion.start(this$0, pid, 2, (r12 & 8) != 0 ? false : false, str);
            return true;
        } else if (itemId != R.id.report) {
            return false;
        } else {
            if (App.getBBsInfo() != null) {
                ReportReviewActivity.Companion.start(this$0, reviewModel == null ? null : reviewModel.getPid(), reviewModel == null ? null : reviewModel.getTid(), reviewModel != null ? reviewModel.getFid() : null);
                return true;
            }
            PandaForumAuthorizeActivity.Companion.start(this$0);
            return true;
        }
    }

    private final void doLike(final ReviewModel reviewModel, final int i) {
        UserModel.BBsInfo bBsInfo = App.getBBsInfo();
        Observable<R> map = Http.getService().doLike(API.BBS_URL, "postreview", reviewModel.getTid(), reviewModel.getPid(), "add", bBsInfo.getAuth(), bBsInfo.getAuthkey(), bBsInfo.getFormhash()).map($$Lambda$SingleReviewActivity$Kz360zVjR_VLFtfbECo2vNrth4.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(map, "getService().doLike(API.…s.java)\n                }");
        RxSubscribersKt.transform(map, this).subscribe(new Consumer() { // from class: com.movieboxpro.android.view.activity.review.SingleReviewActivity$doLike$$inlined$transformSubscribe$default$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(T it) {
                CommBaseAdapter commBaseAdapter;
                Intrinsics.checkNotNullExpressionValue(it, "it");
                BBsResponseModel bBsResponseModel = (BBsResponseModel) it;
                String messageval = bBsResponseModel.getMessage().getMessageval();
                if (Intrinsics.areEqual(messageval, "recommend_succeed")) {
                    ReviewModel.this.setStatus(1);
                    if (ReviewModel.this.getStatus() == 1) {
                        ReviewModel.this.setSupport(ReviewModel.this.getSupport() + 1);
                    } else {
                        ReviewModel.this.setSupport(ReviewModel.this.getSupport() - 1);
                    }
                    commBaseAdapter = this.adapter;
                    if (commBaseAdapter == null) {
                        return;
                    }
                    commBaseAdapter.notifyItemChanged(i);
                } else if (Intrinsics.areEqual(messageval, "recommend_duplicate")) {
                    ToastUtils.showShort("You have already rated this thread", new Object[0]);
                } else {
                    ToastUtils.showShort(bBsResponseModel.getMessage().getMessagestr(), new Object[0]);
                }
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.SingleReviewActivity$doLike$$inlined$transformSubscribe$default$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                ApiException handleException = ApiException.handleException(th);
                Intrinsics.checkNotNullExpressionValue(handleException, "handleException(it)");
                ToastUtils.showShort(Intrinsics.stringPlus("like failed:", handleException.getMessage()), new Object[0]);
                if (th instanceof ServerException) {
                    ServerException serverException = (ServerException) th;
                }
            }
        }, new Action() { // from class: com.movieboxpro.android.view.activity.review.SingleReviewActivity$doLike$$inlined$transformSubscribe$default$3
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.SingleReviewActivity$doLike$$inlined$transformSubscribe$default$4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: doLike$lambda-17  reason: not valid java name */
    public static final BBsResponseModel m751doLike$lambda17(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return (BBsResponseModel) JSON.parseObject(it, BBsResponseModel.class);
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initView() {
        ImageView ivPrePage = (ImageView) _$_findCachedViewById(R.id.ivPrePage);
        Intrinsics.checkNotNullExpressionValue(ivPrePage, "ivPrePage");
        CommonExtKt.gone(ivPrePage);
        TextView tvPageInfo = (TextView) _$_findCachedViewById(R.id.tvPageInfo);
        Intrinsics.checkNotNullExpressionValue(tvPageInfo, "tvPageInfo");
        CommonExtKt.gone(tvPageInfo);
        ImageView ivNextPage = (ImageView) _$_findCachedViewById(R.id.ivNextPage);
        Intrinsics.checkNotNullExpressionValue(ivNextPage, "ivNextPage");
        CommonExtKt.gone(ivNextPage);
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
        String format = String.format("%s%s%s", Arrays.copyOf(new Object[]{"<style>img{width: 100%;display: block;}a:link{color: #007AFF;}body{margin:0px;padding:0px;font-size:15px;line-height:21px;}*{background:#1D1D1D;color:rgba(255,255,255,0.70)}</style>", Constant.CLICK_EVENT_JS, str}, 3));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        webView.loadDataWithBaseURL(null, format, MimeTypes.TEXT_HTML, "utf-8", null);
        webView.setFocusable(true);
        webView.setFocusableInTouchMode(true);
    }

    private final void setUpWebView(WebView webView) {
        webView.setHorizontalFadingEdgeEnabled(false);
        webView.setVerticalFadingEdgeEnabled(false);
        webView.setOverScrollMode(2);
        webView.setOnTouchListener($$Lambda$SingleReviewActivity$kfTEh3t7aHkRI0zmCzIlbgXYTrk.INSTANCE);
        if (this.jsBridge == null) {
            ImageClickJSBridge imageClickJSBridge = new ImageClickJSBridge();
            this.jsBridge = imageClickJSBridge;
            Intrinsics.checkNotNull(imageClickJSBridge);
            imageClickJSBridge.setListener(new ImageClickJSBridge.OnImageClickListener() { // from class: com.movieboxpro.android.view.activity.review.SingleReviewActivity$setUpWebView$2
                @Override // com.movieboxpro.android.listener.ImageClickJSBridge.OnImageClickListener
                public void onAvatarClick(String str) {
                    if (str != null) {
                        if (App.getBBsInfo() != null) {
                            UserInfoActivity.Companion.start(SingleReviewActivity.this, str);
                        } else {
                            PandaForumAuthorizeActivity.Companion.start(SingleReviewActivity.this);
                        }
                    }
                }

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
                                        MovieDetailActivity.start(SingleReviewActivity.this, str2);
                                        return;
                                    }
                                    return;
                                case 50:
                                    if (str3.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                                        TvDetailActivity.start(SingleReviewActivity.this, str2);
                                        return;
                                    }
                                    return;
                                case 51:
                                    if (str3.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                                        MovieListDetailActivity.start(SingleReviewActivity.this, str2, "", "");
                                        return;
                                    }
                                    return;
                                default:
                                    return;
                            }
                        }
                        ArrayList arrayList = new ArrayList();
                        int length2 = array.length;
                        int i5 = 0;
                        while (i5 < length2) {
                            String str5 = array[i5];
                            i5++;
                            List split$default3 = StringsKt.split$default((CharSequence) str5, new String[]{","}, false, 0, 6, (Object) null);
                            if (split$default3.size() == 3 && Intrinsics.areEqual(split$default3.get(0), "null") && Intrinsics.areEqual(split$default3.get(1), "null")) {
                                arrayList.add(split$default3.get(2));
                            }
                        }
                        Iterator it = arrayList.iterator();
                        int i6 = 0;
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            Object next = it.next();
                            int i7 = i6 + 1;
                            if (i6 < 0) {
                                CollectionsKt.throwIndexOverflow();
                            }
                            if (Intrinsics.areEqual((String) next, url)) {
                                i = i6;
                                break;
                            }
                            i6 = i7;
                        }
                        SingleReviewActivity.this.toImageShow(arrayList, i);
                    }
                }

                @Override // com.movieboxpro.android.listener.ImageClickJSBridge.OnImageClickListener
                public void onReviewClick(String str) {
                    String str2 = str;
                    if (str2 == null || StringsKt.isBlank(str2)) {
                        return;
                    }
                    SingleReviewDialogFragment.Companion.newInstance(str).show(SingleReviewActivity.this.getSupportFragmentManager(), "SingleReviewDialogFragment");
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
    /* renamed from: setUpWebView$lambda-21  reason: not valid java name */
    public static final boolean m763setUpWebView$lambda21(View view, MotionEvent motionEvent) {
        view.requestFocus();
        return motionEvent.getAction() == 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void toImageShow(List<String> list, int i) {
        if (list != null) {
            if (list.size() == 1) {
                new XPopup.Builder(this).asImageViewer(null, list.get(0), new XpopImageLoader()).show();
            } else {
                new XPopup.Builder(this).asImageViewer(null, i, list, null, new XpopImageLoader()).show();
            }
        }
    }

    /* compiled from: SingleReviewActivity.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/SingleReviewActivity$MyWebChromeClient;", "Landroid/webkit/WebChromeClient;", "(Lcom/movieboxpro/android/view/activity/review/SingleReviewActivity;)V", "onJsAlert", "", "view", "Landroid/webkit/WebView;", "url", "", "message", "result", "Landroid/webkit/JsResult;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public final class MyWebChromeClient extends WebChromeClient {
        final /* synthetic */ SingleReviewActivity this$0;

        public MyWebChromeClient(SingleReviewActivity this$0) {
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
    /* compiled from: SingleReviewActivity.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/SingleReviewActivity$MyWebViewClient;", "Landroid/webkit/WebViewClient;", "(Lcom/movieboxpro/android/view/activity/review/SingleReviewActivity;)V", "shouldOverrideUrlLoading", "", "view", "Landroid/webkit/WebView;", "url", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public final class MyWebViewClient extends WebViewClient {
        final /* synthetic */ SingleReviewActivity this$0;

        public MyWebViewClient(SingleReviewActivity this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Intrinsics.checkNotNullParameter(view, "view");
            Intrinsics.checkNotNullParameter(url, "url");
            SystemUtils.startBrowser((Activity) this.this$0, url);
            return true;
        }
    }
}
