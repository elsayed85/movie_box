package com.movieboxpro.android.view.activity.review;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.FragmentActivity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.engine.ImageEngine;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseSimpleActivity;
import com.movieboxpro.android.event.MovieListSelectedEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpUploadBBsRequest;
import com.movieboxpro.android.http.HttpUtils;
import com.movieboxpro.android.http.ServerException;
import com.movieboxpro.android.model.BBsResponseModel;
import com.movieboxpro.android.model.ReviewModel;
import com.movieboxpro.android.model.ReviewRecordModel;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.model.movie.NormalFilmModel;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.service.FileDownloadService;
import com.movieboxpro.android.utils.ChooseImageGlideEngine;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.HexDump;
import com.movieboxpro.android.utils.InputMethodUtils;
import com.movieboxpro.android.utils.MD5Util;
import com.movieboxpro.android.utils.ReviewRecordUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ShellUtil;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.rxkotlin.ObservableKt;
import io.reactivex.schedulers.Schedulers;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import top.zibin.luban.Luban;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: FullReplyActivity.kt */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 ?2\u00020\u0001:\u0001?B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\bH\u0002J\b\u0010\u001d\u001a\u00020\u0018H\u0002J\u0010\u0010\u001e\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\bH\u0002J\b\u0010 \u001a\u00020\u0006H\u0014J\u0010\u0010!\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020#H\u0002J\u0016\u0010$\u001a\u00020\u00182\f\u0010%\u001a\b\u0012\u0004\u0012\u00020'0&H\u0002J\b\u0010(\u001a\u00020\bH\u0002J\b\u0010)\u001a\u00020\u001bH\u0016J\b\u0010*\u001a\u00020\u001bH\u0014J\b\u0010+\u001a\u00020\u0018H\u0016J\b\u0010,\u001a\u00020\u0018H\u0016J\b\u0010-\u001a\u00020\u0018H\u0002J\b\u0010.\u001a\u00020\u0018H\u0016J\"\u0010/\u001a\u00020\u00182\u0006\u00100\u001a\u00020\u001b2\u0006\u00101\u001a\u00020\u001b2\b\u00102\u001a\u0004\u0018\u000103H\u0014J\b\u00104\u001a\u00020\u0018H\u0014J\u0010\u00105\u001a\u00020\u00182\u0006\u0010\"\u001a\u000206H\u0007J\u0018\u00107\u001a\u00020\u00182\u000e\u00108\u001a\n\u0012\u0004\u0012\u000209\u0018\u00010\u000fH\u0002J\b\u0010:\u001a\u00020\u0018H\u0002J\b\u0010;\u001a\u00020\u0018H\u0002J\u0010\u0010<\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\bH\u0002J$\u0010<\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u000f0=2\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R.\u0010\t\u001a\"\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\nj\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u0001`\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\f\u001a\u001e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\nj\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b`\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0014\u001a\u001e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\nj\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b`\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006@"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/FullReplyActivity;", "Lcom/movieboxpro/android/base/BaseSimpleActivity;", "()V", "bbsInfo", "Lcom/movieboxpro/android/model/user/UserModel$BBsInfo;", "finishSelf", "", "id", "", "idMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "imageMap", "isKeyboardVisible", "lubanImages", "", "Ljava/io/File;", "popAddVideo", "Landroid/widget/PopupWindow;", "popFont", "thumbImageMap", "userModel", "Lcom/movieboxpro/android/model/user/UserModel;", "addReply", "", "html", "htmlOn", "", "userFile", "closePop", "compress", FileDownloadService.PARAMS_KEY_PATH, "enableEventBus", "getAltMovieListImg", "item", "Lcom/movieboxpro/android/model/movie/MovieListModel$MovieListItem;", "getAltVideos", "videos", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/movie/NormalFilmModel;", "getBBSApiAPPID", "getLayoutResId", "getStatusColor", "initData", "initListener", "initRichEditor", "initView", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onDestroy", "onMovieListSelected", "Lcom/movieboxpro/android/event/MovieListSelectedEvent;", "processSelectedImage", "imagePath", "Lcom/huantansheng/easyphotos/models/album/entity/Photo;", "showAddVideoPop", "showFontPop", "uploadImages", "Lio/reactivex/Observable;", "files", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class FullReplyActivity extends BaseSimpleActivity {
    public static final Companion Companion = new Companion(null);
    public static final int REQUEST_CODE_CHOOSE = 1;
    public static final int REQUEST_CODE_MOVIE_LISTS = 3;
    public static final int REQUEST_CODE_VIDEO = 2;
    private UserModel.BBsInfo bbsInfo;
    private boolean finishSelf;
    private HashMap<String, String> idMap;
    private boolean isKeyboardVisible;
    private List<? extends File> lubanImages;
    private PopupWindow popAddVideo;
    private PopupWindow popFont;
    private UserModel userModel;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private HashMap<String, String> imageMap = new HashMap<>();
    private HashMap<String, String> thumbImageMap = new HashMap<>();
    private String id = "";

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
    protected boolean enableEventBus() {
        return true;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public int getLayoutResId() {
        return R.layout.fragment_full_reply;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    protected int getStatusColor() {
        return R.color.color_main;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initListener() {
        ((LinearLayout) _$_findCachedViewById(R.id.ll_back)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$FullReplyActivity$zywV6zYO50T372nwpBzt7qe9-Ck
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FullReplyActivity.m513initListener$lambda0(FullReplyActivity.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivInsertImage)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$FullReplyActivity$LmrOa_EHQ8AB2F0wM7D5RZ3rFxk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FullReplyActivity.m514initListener$lambda1(FullReplyActivity.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.iv_right)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$FullReplyActivity$B8bazqVIeuJ8Drcb_HtFeoDv7Ng
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FullReplyActivity.m515initListener$lambda2(FullReplyActivity.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivTextStyle)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$FullReplyActivity$aWym_KFzx9_ST-EP9Go5YEtj6n8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FullReplyActivity.m516initListener$lambda3(FullReplyActivity.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivInsertLink)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$FullReplyActivity$1pHYHHjQT6otgLkKnedz7aF-G3g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FullReplyActivity.m517initListener$lambda4(FullReplyActivity.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.linearLayout)).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$FullReplyActivity$9FqYAK7KNIYvWkXy55gPSVclpvc
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                FullReplyActivity.m518initListener$lambda5(FullReplyActivity.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m513initListener$lambda0(FullReplyActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        InputMethodUtils.hideSoftInput(this$0);
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m514initListener$lambda1(FullReplyActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        EasyPhotos.createAlbum((FragmentActivity) this$0, true, (ImageEngine) ChooseImageGlideEngine.getInstance()).setFileProviderAuthority("com.movieboxpro.android.fileProvider").setCount(9).start(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m515initListener$lambda2(FullReplyActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String html = ((MyRichEditor) this$0._$_findCachedViewById(R.id.richEditor)).getHtml();
        String str = html;
        if (str == null || StringsKt.isBlank(str)) {
            return;
        }
        if (this$0.imageMap.isEmpty()) {
            InputMethodUtils.hideSoftInput(this$0);
            Intrinsics.checkNotNullExpressionValue(html, "html");
            this$0.addReply(html, 0, "");
            return;
        }
        Intrinsics.checkNotNullExpressionValue(html, "html");
        this$0.uploadImages(html);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-3  reason: not valid java name */
    public static final void m516initListener$lambda3(FullReplyActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showFontPop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-4  reason: not valid java name */
    public static final void m517initListener$lambda4(FullReplyActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showAddVideoPop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-5  reason: not valid java name */
    public static final void m518initListener$lambda5(FullReplyActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Rect rect = new Rect();
        ((LinearLayout) this$0._$_findCachedViewById(R.id.linearLayout)).getWindowVisibleDisplayFrame(rect);
        FullReplyActivity fullReplyActivity = this$0;
        boolean z = false;
        if ((((LinearLayout) this$0._$_findCachedViewById(R.id.linearLayout)).getRootView().getHeight() - rect.bottom) - (SystemUtils.hasNavBar(fullReplyActivity) ? SystemUtils.getNavBarHeight(fullReplyActivity) : 0) > 0) {
            if (!this$0.isKeyboardVisible) {
                this$0.closePop();
            }
            z = true;
        } else if (this$0.isKeyboardVisible) {
            this$0.closePop();
        }
        this$0.isKeyboardVisible = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addReply(String str, int i, String str2) {
        if (Intrinsics.areEqual("<br>", str)) {
            return;
        }
        String replaceAll = Pattern.compile(ShellUtil.COMMAND_LINE_END).matcher(str).replaceAll("");
        Intrinsics.checkNotNullExpressionValue(replaceAll, "m.replaceAll(\"\")");
        CommonExtKt.logD(this, Intrinsics.stringPlus("send html:", replaceAll));
        UserModel.UserData userData = App.getUserData();
        Intrinsics.checkNotNullExpressionValue(userData, "getUserData()");
        UserModel.BBsInfo bBsInfo = App.getBBsInfo();
        Serializable serializableExtra = getIntent().getSerializableExtra("data");
        ReviewModel reviewModel = serializableExtra instanceof ReviewModel ? (ReviewModel) serializableExtra : null;
        String stringExtra = getIntent().getStringExtra("repquote");
        int intExtra = getIntent().getIntExtra("reply", 0);
        if (reviewModel == null) {
            ToastUtils.showShort("Failed:null", new Object[0]);
            return;
        }
        Observable<R> map = Http.getService().reply(API.BBS_URL, "sendreply", userData.username, userData.uid_v2, reviewModel.getTid(), i, bBsInfo.getAuth(), bBsInfo.getAuthkey(), bBsInfo.getFormhash(), "", "yes", "", URLEncoder.encode(replaceAll, "UTF-8"), "", str2, stringExtra, reviewModel.getAuthorid(), getBBSApiAPPID(), reviewModel.getPid(), reviewModel.getUsername(), reviewModel.getAvatar(), String.valueOf(reviewModel.getDbdateline()), reviewModel.getFor_quote(), intExtra, 1).map($$Lambda$FullReplyActivity$wLhnVDx5xXrMaAhvsmOgURIyyNg.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(map, "getService().reply(API.B…s.java)\n                }");
        RxSubscribersKt.transform(map, this).subscribe(new Consumer() { // from class: com.movieboxpro.android.view.activity.review.FullReplyActivity$addReply$$inlined$transformSubscribe$default$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(T it) {
                String str3;
                Intrinsics.checkNotNullExpressionValue(it, "it");
                BBsResponseModel bBsResponseModel = (BBsResponseModel) it;
                if (Intrinsics.areEqual("post_reply_succeed", bBsResponseModel.getMessage().getMessageval())) {
                    ReviewRecordUtils reviewRecordUtils = ReviewRecordUtils.Companion.get();
                    str3 = FullReplyActivity.this.id;
                    reviewRecordUtils.deleteReviewRecord(str3);
                    FullReplyActivity.this.finishSelf = true;
                    FullReplyActivity.this.setResult(-1);
                    FullReplyActivity.this.finish();
                } else if (Intrinsics.areEqual("post_message_tooshort", bBsResponseModel.getMessage().getMessageval())) {
                    ToastUtils.showShort("Send failed", new Object[0]);
                } else {
                    ToastUtils.showShort("Send Failed", new Object[0]);
                }
                FullReplyActivity.this.hideLoadingView();
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.FullReplyActivity$addReply$$inlined$transformSubscribe$default$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                ApiException handleException = ApiException.handleException(th);
                Intrinsics.checkNotNullExpressionValue(handleException, "handleException(it)");
                FullReplyActivity.this.hideLoadingView();
                ToastUtils.showShort(Intrinsics.stringPlus("Send Failed:", handleException.getMessage()), new Object[0]);
                if (th instanceof ServerException) {
                    ServerException serverException = (ServerException) th;
                }
            }
        }, new Action() { // from class: com.movieboxpro.android.view.activity.review.FullReplyActivity$addReply$$inlined$transformSubscribe$default$3
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.FullReplyActivity$addReply$$inlined$transformSubscribe$default$4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                FullReplyActivity.this.showLoadingView();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: addReply$lambda-6  reason: not valid java name */
    public static final BBsResponseModel m510addReply$lambda6(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return (BBsResponseModel) JSON.parseObject(it, BBsResponseModel.class);
    }

    private final void uploadImages(final String str) {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        Object as = Observable.just(str).map(new Function() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$FullReplyActivity$i7_6WPWzbFspQddFex-Mxb5qLKc
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                List m546uploadImages$lambda12;
                m546uploadImages$lambda12 = FullReplyActivity.m546uploadImages$lambda12(str, this, (String) obj);
                return m546uploadImages$lambda12;
            }
        }).subscribeOn(Schedulers.computation()).flatMap(new Function() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$FullReplyActivity$1KdGjoA6FSDONvi-EW7LBA46CD8
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                ObservableSource m547uploadImages$lambda13;
                m547uploadImages$lambda13 = FullReplyActivity.m547uploadImages$lambda13(FullReplyActivity.this, (List) obj);
                return m547uploadImages$lambda13;
            }
        }).map(new Function() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$FullReplyActivity$ishAwoTCrlRaicb7qH5kg2kSWRM
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                String m548uploadImages$lambda15;
                m548uploadImages$lambda15 = FullReplyActivity.m548uploadImages$lambda15(Ref.ObjectRef.this, str, (List) obj);
                return m548uploadImages$lambda15;
            }
        }).observeOn(AndroidSchedulers.mainThread()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(as, "just(html)\n             …bindLifecycleOwner(this))");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, new FullReplyActivity$uploadImages$4(this), null, new FullReplyActivity$uploadImages$5(this), null, new FullReplyActivity$uploadImages$6(this, objectRef), 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: uploadImages$lambda-12  reason: not valid java name */
    public static final List m546uploadImages$lambda12(String html, FullReplyActivity this$0, String it) {
        Intrinsics.checkNotNullParameter(html, "$html");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        Elements<Element> select = Jsoup.parseBodyFragment(html).body().select("img");
        ArrayList<String> arrayList = new ArrayList();
        if (select != null) {
            for (Element element : select) {
                String src = element.attr("src");
                Intrinsics.checkNotNullExpressionValue(src, "src");
                if (StringsKt.startsWith$default(src, "/storage", false, 2, (Object) null)) {
                    arrayList.add(src);
                }
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (String str : arrayList) {
            HashMap<String, String> hashMap = this$0.imageMap;
            String str2 = this$0.thumbImageMap.get(str);
            String str3 = "";
            if (str2 == null) {
                str2 = "";
            }
            String str4 = hashMap.get(str2);
            if (str4 != null) {
                str3 = str4;
            }
            arrayList2.add(str3);
        }
        return Luban.with(this$0).setTargetDir(Constant.DIR_THUMB).load(arrayList2).get();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: uploadImages$lambda-13  reason: not valid java name */
    public static final ObservableSource m547uploadImages$lambda13(FullReplyActivity this$0, List it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.lubanImages = it;
        return this$0.uploadImages(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: uploadImages$lambda-15  reason: not valid java name */
    public static final String m548uploadImages$lambda15(Ref.ObjectRef idList, String html, List it) {
        Intrinsics.checkNotNullParameter(idList, "$idList");
        Intrinsics.checkNotNullParameter(html, "$html");
        Intrinsics.checkNotNullParameter(it, "it");
        idList.element = it;
        Element body = Jsoup.parseBodyFragment(html).body();
        Elements imgs = body.select("img");
        Intrinsics.checkNotNullExpressionValue(imgs, "imgs");
        int i = 0;
        int i2 = 0;
        for (Element element : imgs) {
            int i3 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            Element element2 = element;
            String src = element2.attr("src");
            Intrinsics.checkNotNullExpressionValue(src, "src");
            if (StringsKt.startsWith$default(src, "/storage", false, 2, (Object) null)) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("[attach]%s[/attach]", Arrays.copyOf(new Object[]{it.get(i2)}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                Element element3 = new Element(Tag.valueOf(TtmlNode.TAG_DIV), "");
                element3.text(format);
                element2.replaceWith(element3);
            } else {
                i2--;
            }
            i2++;
            i = i3;
        }
        return body.html();
    }

    private final Observable<List<String>> uploadImages(List<? extends File> list) {
        Observable<List<String>> observable = ObservableKt.toObservable(list).flatMap(new Function() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$FullReplyActivity$TIINSflmJ2ZJHcxHzJlmQi0Ehqs
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                ObservableSource m549uploadImages$lambda17;
                m549uploadImages$lambda17 = FullReplyActivity.m549uploadImages$lambda17(FullReplyActivity.this, (File) obj);
                return m549uploadImages$lambda17;
            }
        }).toList().toObservable();
        Intrinsics.checkNotNullExpressionValue(observable, "files.toObservable().fla…          .toObservable()");
        return observable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: uploadImages$lambda-17  reason: not valid java name */
    public static final ObservableSource m549uploadImages$lambda17(FullReplyActivity this$0, File it) {
        String author;
        String auth;
        String authkey;
        String formhash;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        HttpUploadBBsRequest addBaseParams = new HttpUploadBBsRequest(null, 1, null).addBaseParams("forumupload", "image/jpg", it, "Filedata");
        UserModel.BBsInfo bBsInfo = this$0.bbsInfo;
        if (bBsInfo == null || (author = bBsInfo.getAuthor()) == null) {
            author = "";
        }
        HttpUploadBBsRequest addParam = addBaseParams.addParam("username", author);
        UserModel.BBsInfo bBsInfo2 = this$0.bbsInfo;
        if (bBsInfo2 == null || (auth = bBsInfo2.getAuth()) == null) {
            auth = "";
        }
        HttpUploadBBsRequest addParam2 = addParam.addParam("auth", auth);
        UserModel.BBsInfo bBsInfo3 = this$0.bbsInfo;
        if (bBsInfo3 == null || (authkey = bBsInfo3.getAuthkey()) == null) {
            authkey = "";
        }
        HttpUploadBBsRequest addParam3 = addParam2.addParam("authkey", authkey).addParam("type_id", ExifInterface.GPS_MEASUREMENT_2D).addParam("apiappid", this$0.getBBSApiAPPID());
        UserModel.BBsInfo bBsInfo4 = this$0.bbsInfo;
        if (bBsInfo4 == null || (formhash = bBsInfo4.getFormhash()) == null) {
            formhash = "";
        }
        Observable<String> executeUploadObservable = addParam3.addParam("formhash", formhash).addParam("hash", "").bindLifecycle(this$0).executeUploadObservable();
        return executeUploadObservable != null ? executeUploadObservable.map($$Lambda$FullReplyActivity$xFP6kaNHOGbAlu5Ogi4vGOoUVE.INSTANCE) : null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: uploadImages$lambda-17$lambda-16  reason: not valid java name */
    public static final String m550uploadImages$lambda17$lambda16(String json) {
        Intrinsics.checkNotNullParameter(json, "json");
        HashMap hashMap = (HashMap) JSON.parseObject(json, HashMap.class);
        if (hashMap != null) {
            Object obj = hashMap.get("upload_info");
            if (obj == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.alibaba.fastjson.JSONObject");
            }
            Object obj2 = ((JSONObject) obj).get("upload_id");
            if (obj2 != null) {
                return (String) obj2;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
        return "";
    }

    private final String getBBSApiAPPID() {
        long currentTime = TimeUtils.getCurrentTime() / 1000;
        String md5 = HttpUtils.md5("27");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.CHINA, "%d%s", Arrays.copyOf(new Object[]{Long.valueOf(currentTime), md5}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        return format;
    }

    private final void closePop() {
        PopupWindow popupWindow = this.popAddVideo;
        if (popupWindow != null) {
            Intrinsics.checkNotNull(popupWindow);
            if (popupWindow.isShowing()) {
                PopupWindow popupWindow2 = this.popAddVideo;
                Intrinsics.checkNotNull(popupWindow2);
                popupWindow2.dismiss();
                this.popAddVideo = null;
            }
        }
        PopupWindow popupWindow3 = this.popFont;
        if (popupWindow3 != null) {
            Intrinsics.checkNotNull(popupWindow3);
            if (popupWindow3.isShowing()) {
                PopupWindow popupWindow4 = this.popFont;
                Intrinsics.checkNotNull(popupWindow4);
                popupWindow4.dismiss();
                this.popFont = null;
            }
        }
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initData() {
        String stringExtra = getIntent().getStringExtra("id");
        if (stringExtra == null) {
            stringExtra = "";
        }
        this.id = stringExtra;
        this.userModel = App.getUserModel();
        this.bbsInfo = App.getBBsInfo();
        String stringExtra2 = getIntent().getStringExtra("text");
        ReviewRecordModel reviewRecord = ReviewRecordUtils.Companion.get().getReviewRecord(this.id, "html");
        if (reviewRecord != null) {
            String content = reviewRecord.getContent();
            if (!(content == null || StringsKt.isBlank(content))) {
                if (Intrinsics.areEqual(reviewRecord.getType(), "html") && reviewRecord.getImgMap() != null && reviewRecord.getThumbImageMap() != null) {
                    HashMap<String, String> imgMap = reviewRecord.getImgMap();
                    Intrinsics.checkNotNullExpressionValue(imgMap, "reviewRecord.imgMap");
                    this.imageMap = imgMap;
                    HashMap<String, String> thumbImageMap = reviewRecord.getThumbImageMap();
                    Intrinsics.checkNotNullExpressionValue(thumbImageMap, "reviewRecord.thumbImageMap");
                    this.thumbImageMap = thumbImageMap;
                }
                if (reviewRecord.getContent() == null) {
                    reviewRecord.setContent("");
                }
                ((MyRichEditor) _$_findCachedViewById(R.id.richEditor)).insertHtml(reviewRecord.getContent());
                return;
            }
            ((MyRichEditor) _$_findCachedViewById(R.id.richEditor)).insertHtml(stringExtra2);
            return;
        }
        ((MyRichEditor) _$_findCachedViewById(R.id.richEditor)).insertHtml(stringExtra2);
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initView() {
        ((TextView) _$_findCachedViewById(R.id.tv_title)).setText("Reply");
        ((ImageView) _$_findCachedViewById(R.id.iv_right)).setImageResource(R.mipmap.ic_send_msg);
        ImageView iv_right = (ImageView) _$_findCachedViewById(R.id.iv_right);
        Intrinsics.checkNotNullExpressionValue(iv_right, "iv_right");
        CommonExtKt.visible(iv_right);
        ((FrameLayout) _$_findCachedViewById(R.id.frameLayout)).setBackgroundColor(CommonExtKt.colorInt((Context) this, (int) R.color.color_main));
        initRichEditor();
    }

    private final void showAddVideoPop() {
        PopupWindow popupWindow = this.popAddVideo;
        if (popupWindow != null) {
            Intrinsics.checkNotNull(popupWindow);
            if (popupWindow.isShowing()) {
                PopupWindow popupWindow2 = this.popAddVideo;
                Intrinsics.checkNotNull(popupWindow2);
                popupWindow2.dismiss();
                this.popAddVideo = null;
                return;
            }
        }
        View inflate = LayoutInflater.from(this).inflate(R.layout.edit_add_video_layout, (ViewGroup) null, false);
        PopupWindow popupWindow3 = new PopupWindow(inflate, CommonExtKt.dp2Px(270), CommonExtKt.dp2Px(50));
        this.popAddVideo = popupWindow3;
        Intrinsics.checkNotNull(popupWindow3);
        popupWindow3.setBackgroundDrawable(new ColorDrawable(0));
        PopupWindow popupWindow4 = this.popAddVideo;
        Intrinsics.checkNotNull(popupWindow4);
        popupWindow4.setOutsideTouchable(true);
        PopupWindow popupWindow5 = this.popAddVideo;
        Intrinsics.checkNotNull(popupWindow5);
        popupWindow5.setFocusable(true);
        PopupWindow popupWindow6 = this.popAddVideo;
        Intrinsics.checkNotNull(popupWindow6);
        popupWindow6.showAsDropDown((ImageView) _$_findCachedViewById(R.id.ivInsertLink), CommonExtKt.dp2Px(90) * (-1), CommonExtKt.dp2Px(75) * (-1));
        ((TextView) inflate.findViewById(R.id.tvSearch)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$FullReplyActivity$VpQEIEWWYmFEA7ZXDThSZXmV_Hs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FullReplyActivity.m533showAddVideoPop$lambda18(FullReplyActivity.this, view);
            }
        });
        ((TextView) inflate.findViewById(R.id.tvHistory)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$FullReplyActivity$njWJl2IaYEhuTDxHFmYnJNeeo-E
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FullReplyActivity.m534showAddVideoPop$lambda19(FullReplyActivity.this, view);
            }
        });
        ((TextView) inflate.findViewById(R.id.tvFavorite)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$FullReplyActivity$7TcewvaUoFOPNsE5nA-HJmYE318
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FullReplyActivity.m535showAddVideoPop$lambda20(FullReplyActivity.this, view);
            }
        });
        ((TextView) inflate.findViewById(R.id.tvMovieLists)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$FullReplyActivity$onHTkfar50JWsPpZnv9V198NzCA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FullReplyActivity.m536showAddVideoPop$lambda21(FullReplyActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showAddVideoPop$lambda-18  reason: not valid java name */
    public static final void m533showAddVideoPop$lambda18(FullReplyActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AltVideosActivity.Companion.start(this$0, 1, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showAddVideoPop$lambda-19  reason: not valid java name */
    public static final void m534showAddVideoPop$lambda19(FullReplyActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AltVideosActivity.Companion.start(this$0, 2, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showAddVideoPop$lambda-20  reason: not valid java name */
    public static final void m535showAddVideoPop$lambda20(FullReplyActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AltVideosActivity.Companion.start(this$0, 3, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showAddVideoPop$lambda-21  reason: not valid java name */
    public static final void m536showAddVideoPop$lambda21(FullReplyActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AltMovieListActivity.Companion.start(this$0);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onMovieListSelected(final MovieListSelectedEvent item) {
        Intrinsics.checkNotNullParameter(item, "item");
        ((MyRichEditor) _$_findCachedViewById(R.id.richEditor)).postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$FullReplyActivity$foNZ7jU82aXp30eSBaCU_NU2nvk
            @Override // java.lang.Runnable
            public final void run() {
                FullReplyActivity.m531onMovieListSelected$lambda22(FullReplyActivity.this, item);
            }
        }, 300L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onMovieListSelected$lambda-22  reason: not valid java name */
    public static final void m531onMovieListSelected$lambda22(FullReplyActivity this$0, MovieListSelectedEvent item) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(item, "$item");
        MovieListModel.MovieListItem item2 = item.getItem();
        Intrinsics.checkNotNullExpressionValue(item2, "item.item");
        this$0.getAltMovieListImg(item2);
    }

    private final void showFontPop() {
        PopupWindow popupWindow = this.popFont;
        if (popupWindow != null) {
            Intrinsics.checkNotNull(popupWindow);
            if (popupWindow.isShowing()) {
                PopupWindow popupWindow2 = this.popFont;
                Intrinsics.checkNotNull(popupWindow2);
                popupWindow2.dismiss();
                this.popFont = null;
                return;
            }
        }
        View inflate = LayoutInflater.from(this).inflate(R.layout.layout_editor_text, (ViewGroup) null, false);
        PopupWindow popupWindow3 = new PopupWindow(inflate, CommonExtKt.dp2Px(270), CommonExtKt.dp2Px(50));
        this.popFont = popupWindow3;
        Intrinsics.checkNotNull(popupWindow3);
        popupWindow3.setBackgroundDrawable(new ColorDrawable(0));
        PopupWindow popupWindow4 = this.popFont;
        Intrinsics.checkNotNull(popupWindow4);
        popupWindow4.setOutsideTouchable(true);
        PopupWindow popupWindow5 = this.popFont;
        Intrinsics.checkNotNull(popupWindow5);
        popupWindow5.setFocusable(true);
        PopupWindow popupWindow6 = this.popFont;
        Intrinsics.checkNotNull(popupWindow6);
        popupWindow6.showAsDropDown((ImageView) _$_findCachedViewById(R.id.ivTextStyle), CommonExtKt.dp2Px(80) * (-1), CommonExtKt.dp2Px(75) * (-1));
        ((ImageButton) inflate.findViewById(R.id.action_text_bold)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$FullReplyActivity$00R4Ax2BiQQiILq2HgrDpyKpUTk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FullReplyActivity.m537showFontPop$lambda23(FullReplyActivity.this, view);
            }
        });
        ((ImageButton) inflate.findViewById(R.id.action_text_italic)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$FullReplyActivity$3BPnZWpNgsHjzbvRPRwmL1B5n50
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FullReplyActivity.m538showFontPop$lambda24(FullReplyActivity.this, view);
            }
        });
        ((ImageButton) inflate.findViewById(R.id.action_text_underline)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$FullReplyActivity$EY9H9Jpj6omSIL55SRPNKvI-B84
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FullReplyActivity.m539showFontPop$lambda25(FullReplyActivity.this, view);
            }
        });
        ((ImageButton) inflate.findViewById(R.id.action_text_h1)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$FullReplyActivity$C07ijtWp8ZuEgIGIlTuk2RVsJcM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FullReplyActivity.m540showFontPop$lambda26(FullReplyActivity.this, view);
            }
        });
        ((ImageButton) inflate.findViewById(R.id.action_text_h2)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$FullReplyActivity$IrHe_TdkfAJzOtrRlVTabEJ60MU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FullReplyActivity.m541showFontPop$lambda27(FullReplyActivity.this, view);
            }
        });
        ((ImageButton) inflate.findViewById(R.id.action_text_h3)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$FullReplyActivity$wQFpR5IQ2AmJSGTQOfUhhb30_Ug
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FullReplyActivity.m542showFontPop$lambda28(FullReplyActivity.this, view);
            }
        });
        ((ImageButton) inflate.findViewById(R.id.action_text_left)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$FullReplyActivity$Y8fiP3AruJ6CPvsRTyXpqkFe5XI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FullReplyActivity.m543showFontPop$lambda29(FullReplyActivity.this, view);
            }
        });
        ((ImageButton) inflate.findViewById(R.id.action_text_center)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$FullReplyActivity$O60eV4rAVBJb0wy5pEGbskJsAA4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FullReplyActivity.m544showFontPop$lambda30(FullReplyActivity.this, view);
            }
        });
        ((ImageButton) inflate.findViewById(R.id.action_text_right)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$FullReplyActivity$4C1Pgfst66oOANyyXJSOiaNvH9I
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FullReplyActivity.m545showFontPop$lambda31(FullReplyActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showFontPop$lambda-23  reason: not valid java name */
    public static final void m537showFontPop$lambda23(FullReplyActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((MyRichEditor) this$0._$_findCachedViewById(R.id.richEditor)).setBold();
        this$0.closePop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showFontPop$lambda-24  reason: not valid java name */
    public static final void m538showFontPop$lambda24(FullReplyActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((MyRichEditor) this$0._$_findCachedViewById(R.id.richEditor)).setItalic();
        this$0.closePop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showFontPop$lambda-25  reason: not valid java name */
    public static final void m539showFontPop$lambda25(FullReplyActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((MyRichEditor) this$0._$_findCachedViewById(R.id.richEditor)).setUnderline();
        this$0.closePop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showFontPop$lambda-26  reason: not valid java name */
    public static final void m540showFontPop$lambda26(FullReplyActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((MyRichEditor) this$0._$_findCachedViewById(R.id.richEditor)).setHeading(1);
        this$0.closePop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showFontPop$lambda-27  reason: not valid java name */
    public static final void m541showFontPop$lambda27(FullReplyActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((MyRichEditor) this$0._$_findCachedViewById(R.id.richEditor)).setHeading(2);
        this$0.closePop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showFontPop$lambda-28  reason: not valid java name */
    public static final void m542showFontPop$lambda28(FullReplyActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((MyRichEditor) this$0._$_findCachedViewById(R.id.richEditor)).setHeading(3);
        this$0.closePop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showFontPop$lambda-29  reason: not valid java name */
    public static final void m543showFontPop$lambda29(FullReplyActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((MyRichEditor) this$0._$_findCachedViewById(R.id.richEditor)).setAlignLeft();
        this$0.closePop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showFontPop$lambda-30  reason: not valid java name */
    public static final void m544showFontPop$lambda30(FullReplyActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((MyRichEditor) this$0._$_findCachedViewById(R.id.richEditor)).setAlignCenter();
        this$0.closePop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showFontPop$lambda-31  reason: not valid java name */
    public static final void m545showFontPop$lambda31(FullReplyActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((MyRichEditor) this$0._$_findCachedViewById(R.id.richEditor)).setAlignRight();
        this$0.closePop();
    }

    private final void initRichEditor() {
        int dp2Px = CommonExtKt.dp2Px(10);
        ((MyRichEditor) _$_findCachedViewById(R.id.richEditor)).setPadding(dp2Px, dp2Px, dp2Px, dp2Px);
        ((MyRichEditor) _$_findCachedViewById(R.id.richEditor)).setPlaceholder("Add a reply");
        ((MyRichEditor) _$_findCachedViewById(R.id.richEditor)).postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$FullReplyActivity$wNpJzNzo2Kaga2bV_-vD9nQzBBo
            @Override // java.lang.Runnable
            public final void run() {
                FullReplyActivity.m519initRichEditor$lambda32(FullReplyActivity.this);
            }
        }, 300L);
        ((MyRichEditor) _$_findCachedViewById(R.id.richEditor)).setEditorBackgroundColor(CommonExtKt.colorInt((Context) this, (int) R.color.color_main));
        ((MyRichEditor) _$_findCachedViewById(R.id.richEditor)).focusEditor();
        ((MyRichEditor) _$_findCachedViewById(R.id.richEditor)).setEditorFontColor(-1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initRichEditor$lambda-32  reason: not valid java name */
    public static final void m519initRichEditor$lambda32(FullReplyActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        InputMethodUtils.showSoftInput((MyRichEditor) this$0._$_findCachedViewById(R.id.richEditor));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            if (i == 1) {
                if (intent != null) {
                    processSelectedImage(intent.getParcelableArrayListExtra(EasyPhotos.RESULT_PHOTOS));
                }
            } else if (i != 2) {
            } else {
                ArrayList<NormalFilmModel> parcelableArrayListExtra = intent == null ? null : intent.getParcelableArrayListExtra("data");
                if (parcelableArrayListExtra == null) {
                    return;
                }
                getAltVideos(parcelableArrayListExtra);
            }
        }
    }

    private final void getAltMovieListImg(MovieListModel.MovieListItem movieListItem) {
        String lid;
        String str = "";
        if (movieListItem.getAvatar() == null) {
            movieListItem.setAvatar("");
        }
        if (movieListItem.getUsername() == null) {
            movieListItem.setUsername("");
        }
        boolean z = false;
        if (movieListItem.getImgArr() == null) {
            str = movieListItem.getCover();
            Intrinsics.checkNotNullExpressionValue(str, "item.cover");
        } else {
            List<String> imgArr = movieListItem.getImgArr();
            Intrinsics.checkNotNullExpressionValue(imgArr, "item.imgArr");
            if (!imgArr.isEmpty()) {
                String str2 = movieListItem.getImgArr().get(0);
                Intrinsics.checkNotNullExpressionValue(str2, "item.imgArr[0]");
                str = str2;
            }
        }
        String str3 = str;
        String lid2 = movieListItem.getLid();
        if ((lid2 == null || lid2.length() == 0) ? true : true) {
            lid = movieListItem.getId();
        } else {
            lid = movieListItem.getLid();
        }
        ((ObservableSubscribeProxy) Http.getService().GetAltMovieListImage(API.BBS_URL, "createMovielistImg", lid, movieListItem.getName(), movieListItem.getUsername(), str3, movieListItem.getAvatar()).compose(RxUtils.rxTranslate2Bean(HashMap.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<HashMap<?, ?>>() { // from class: com.movieboxpro.android.view.activity.review.FullReplyActivity$getAltMovieListImg$1
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                FullReplyActivity.this.showLoadingView();
                CommonExtKt.logD(this, "show loading");
            }

            @Override // io.reactivex.Observer
            public void onNext(HashMap<?, ?> model) {
                Intrinsics.checkNotNullParameter(model, "model");
                CommonExtKt.logD(this, "hide loading");
                MyRichEditor myRichEditor = (MyRichEditor) FullReplyActivity.this._$_findCachedViewById(R.id.richEditor);
                StringBuilder sb = new StringBuilder();
                sb.append("<div class=\"img\"><img src=\"");
                HashMap<?, ?> hashMap = model;
                Object obj = hashMap.get("url");
                if (obj == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                }
                sb.append((String) obj);
                sb.append("\"  videoid=\"");
                Object obj2 = hashMap.get("id");
                if (obj2 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                }
                sb.append((String) obj2);
                sb.append("\"  type=\"");
                Object obj3 = hashMap.get(IjkMediaMeta.IJKM_KEY_TYPE);
                if (obj3 != null) {
                    sb.append((String) obj3);
                    sb.append("\" alt=\"picvision\"  style= \"margin-top:10px;max-width:100%;\"/></div><br><br>");
                    myRichEditor.insertHtml(sb.toString());
                    FullReplyActivity.this.hideLoadingView();
                    return;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable e) {
                Intrinsics.checkNotNullParameter(e, "e");
                ToastUtils.showShort(Intrinsics.stringPlus("Load failed:", e.getMessage()), new Object[0]);
                FullReplyActivity.this.hideLoadingView();
            }
        });
    }

    private final void getAltVideos(ArrayList<NormalFilmModel> arrayList) {
        Observable flatMap = ObservableKt.toObservable(arrayList).flatMap($$Lambda$FullReplyActivity$9sAXVfHppqZ9HrKCmpVFOL5QNi8.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(flatMap, "videos.toObservable()\n  …t.year)\n                }");
        Observable compose = flatMap.compose(RxUtils.rxTranslate2Bean(HashMap.class));
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
        Observable observable = compose.map($$Lambda$FullReplyActivity$BGxynmezYism1YEzVz_VYbiKn4.INSTANCE).toList().toObservable();
        Intrinsics.checkNotNullExpressionValue(observable, "videos.toObservable()\n  …          .toObservable()");
        RxSubscribersKt.transform(observable, this).subscribe(new Consumer() { // from class: com.movieboxpro.android.view.activity.review.FullReplyActivity$getAltVideos$$inlined$transformSubscribe$default$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(T it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                FullReplyActivity.this.hideLoadingView();
                for (Triple triple : (List) it) {
                    ((MyRichEditor) FullReplyActivity.this._$_findCachedViewById(R.id.richEditor)).insertHtml("<div class=\"img\"><img src=\"" + triple.getFirst() + "\"  videoid=\"" + triple.getSecond() + "\"  type=\"" + triple.getThird() + "\" alt=\"picvision\"  style= \"margin-top:10px;max-width:100%;\"/></div><br><br>");
                }
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.FullReplyActivity$getAltVideos$$inlined$transformSubscribe$default$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                ApiException handleException = ApiException.handleException(th);
                Intrinsics.checkNotNullExpressionValue(handleException, "handleException(it)");
                FullReplyActivity.this.hideLoadingView();
                ToastUtils.showShort(Intrinsics.stringPlus("Load failed:", handleException.getMessage()), new Object[0]);
                if (th instanceof ServerException) {
                    ServerException serverException = (ServerException) th;
                }
            }
        }, new Action() { // from class: com.movieboxpro.android.view.activity.review.FullReplyActivity$getAltVideos$$inlined$transformSubscribe$default$3
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.FullReplyActivity$getAltVideos$$inlined$transformSubscribe$default$4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                FullReplyActivity.this.showLoadingView();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getAltVideos$lambda-34  reason: not valid java name */
    public static final ObservableSource m511getAltVideos$lambda34(NormalFilmModel it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (it.getImdb_rating() == null) {
            it.setImdb_rating("0");
        }
        return Http.getService().GetAltVideos(API.BBS_URL, "createVideoImage", String.valueOf(it.getId()), it.getTitle(), it.getPoster(), it.getImdb_rating(), App.deviceLang, it.getBox_type(), it.getYear());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getAltVideos$lambda-35  reason: not valid java name */
    public static final Triple m512getAltVideos$lambda35(HashMap it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return new Triple(it.get("url"), it.get("id"), it.get(IjkMediaMeta.IJKM_KEY_TYPE));
    }

    private final String compress(String str) {
        File file = new File(str);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        options.inJustDecodeBounds = false;
        options.inSampleSize = 4;
        Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        decodeFile.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        String str2 = Constant.DIR_THUMB + ((Object) File.separator) + (HexDump.toHexString(MD5Util.md5(str)) + '_' + ((Object) file.getName()));
        File file2 = new File(str2);
        if (!file2.getParentFile().exists()) {
            file2.getParentFile().mkdirs();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
            fileOutputStream.flush();
            fileOutputStream.close();
            if (decodeFile != null && !decodeFile.isRecycled()) {
                decodeFile.recycle();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return str2;
    }

    private final void processSelectedImage(List<? extends Photo> list) {
        Observable map = Observable.fromIterable(list).map(new Function() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$FullReplyActivity$JgVkwXdHcqIUAoF_72n_wDNil5k
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                Pair m532processSelectedImage$lambda40;
                m532processSelectedImage$lambda40 = FullReplyActivity.m532processSelectedImage$lambda40(FullReplyActivity.this, (Photo) obj);
                return m532processSelectedImage$lambda40;
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "fromIterable(imagePath)\n…      }\n                }");
        RxSubscribersKt.transformCompute(map, this).subscribe(new Consumer() { // from class: com.movieboxpro.android.view.activity.review.FullReplyActivity$processSelectedImage$$inlined$transformSubscribeCompute$default$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(T it) {
                HashMap hashMap;
                HashMap hashMap2;
                Intrinsics.checkNotNullExpressionValue(it, "it");
                Pair pair = (Pair) it;
                FullReplyActivity.this.hideLoadingView();
                ((MyRichEditor) FullReplyActivity.this._$_findCachedViewById(R.id.richEditor)).insertImage((String) pair.getFirst(), "picvision", "margin-top:10px;max-width:50%;");
                hashMap = FullReplyActivity.this.imageMap;
                String name = new File((String) pair.getSecond()).getName();
                Intrinsics.checkNotNullExpressionValue(name, "File(pair.second).name");
                Object second = pair.getSecond();
                Intrinsics.checkNotNullExpressionValue(second, "pair.second");
                hashMap.put(name, second);
                hashMap2 = FullReplyActivity.this.thumbImageMap;
                Object first = pair.getFirst();
                Intrinsics.checkNotNullExpressionValue(first, "pair.first");
                String name2 = new File((String) pair.getSecond()).getName();
                Intrinsics.checkNotNullExpressionValue(name2, "File(pair.second).name");
                hashMap2.put(first, name2);
                CommonExtKt.logD(FullReplyActivity.this, (String) pair.getFirst());
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.FullReplyActivity$processSelectedImage$$inlined$transformSubscribeCompute$default$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                ApiException handleException = ApiException.handleException(th);
                Intrinsics.checkNotNullExpressionValue(handleException, "handleException(it)");
                FullReplyActivity.this.hideLoadingView();
                ToastUtils.showShort(Intrinsics.stringPlus("Load failed:", handleException.getMessage()), new Object[0]);
                if (th instanceof ServerException) {
                    ServerException serverException = (ServerException) th;
                }
            }
        }, new Action() { // from class: com.movieboxpro.android.view.activity.review.FullReplyActivity$processSelectedImage$$inlined$transformSubscribeCompute$default$3
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.FullReplyActivity$processSelectedImage$$inlined$transformSubscribeCompute$default$4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                FullReplyActivity.this.showLoadingView();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: processSelectedImage$lambda-40  reason: not valid java name */
    public static final Pair m532processSelectedImage$lambda40(FullReplyActivity this$0, Photo it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        if (this$0.thumbImageMap.containsKey(new File(it.path).getName())) {
            String str = this$0.thumbImageMap.get(new File(it.path).getName());
            if (str == null) {
                str = "";
            }
            return new Pair(str, it.path);
        }
        String str2 = it.path;
        Intrinsics.checkNotNullExpressionValue(str2, "it.path");
        return new Pair(this$0.compress(str2), it.path);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseSimpleActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        if (!this.finishSelf) {
            String html = ((MyRichEditor) _$_findCachedViewById(R.id.richEditor)).getHtml();
            if (Intrinsics.areEqual(html, "<br>")) {
                html = "";
            }
            ReviewRecordUtils.Companion.get().saveReviewRecord(this.id, "html", html, this.imageMap, this.thumbImageMap);
        }
        InputMethodUtils.hideSoftInput(this);
        super.onDestroy();
    }

    /* compiled from: FullReplyActivity.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JM\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0012\u001a\u00020\u0004¢\u0006\u0002\u0010\u0013R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/FullReplyActivity$Companion;", "", "()V", "REQUEST_CODE_CHOOSE", "", "REQUEST_CODE_MOVIE_LISTS", "REQUEST_CODE_VIDEO", TtmlNode.START, "", "context", "Landroid/app/Activity;", "text", "", "id", "model", "Lcom/movieboxpro/android/model/ReviewModel;", "repquote", "reply", "requestCode", "(Landroid/app/Activity;Ljava/lang/String;Ljava/lang/String;Lcom/movieboxpro/android/model/ReviewModel;Ljava/lang/String;Ljava/lang/Integer;I)V", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Activity activity, String text, String id, ReviewModel reviewModel, String str, Integer num, int i) {
            Intrinsics.checkNotNullParameter(text, "text");
            Intrinsics.checkNotNullParameter(id, "id");
            Intent intent = new Intent(activity, FullReplyActivity.class);
            intent.putExtra("text", text);
            intent.putExtra("id", id);
            intent.putExtra("data", reviewModel);
            intent.putExtra("repquote", str);
            intent.putExtra("reply", num);
            if (activity != null) {
                activity.startActivityForResult(intent, i);
            }
            if (activity == null) {
                return;
            }
            activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }
}
