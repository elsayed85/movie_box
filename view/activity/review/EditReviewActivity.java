package com.movieboxpro.android.view.activity.review;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.exifinterface.media.ExifInterface;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseSimpleActivity;
import com.movieboxpro.android.event.MovieListSelectedEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.APIService;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpUploadBBsRequest;
import com.movieboxpro.android.http.HttpUtils;
import com.movieboxpro.android.http.ServerException;
import com.movieboxpro.android.model.ReviewModel;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.model.movie.NormalFilmModel;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.service.FileDownloadService;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.HexDump;
import com.movieboxpro.android.utils.InputMethodUtils;
import com.movieboxpro.android.utils.MD5Util;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ShellUtil;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
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
/* compiled from: EditReviewActivity.kt */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 F2\u00020\u0001:\u0001FB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010!\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020\u000bH\u0002J\b\u0010#\u001a\u00020\bH\u0014J\u0010\u0010$\u001a\u00020 2\u0006\u0010%\u001a\u00020&H\u0002J\u0016\u0010'\u001a\u00020 2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)H\u0002J\b\u0010+\u001a\u00020\u000bH\u0002J\b\u0010,\u001a\u00020 H\u0002J\b\u0010-\u001a\u00020\u0006H\u0016J\b\u0010.\u001a\u00020\u0006H\u0014J\b\u0010/\u001a\u00020 H\u0016J\b\u00100\u001a\u00020 H\u0016J\b\u00101\u001a\u00020 H\u0002J\b\u00102\u001a\u00020 H\u0016J\"\u00103\u001a\u00020 2\u0006\u00104\u001a\u00020\u00062\u0006\u00105\u001a\u00020\u00062\b\u00106\u001a\u0004\u0018\u000107H\u0014J\u0010\u00108\u001a\u00020 2\u0006\u0010%\u001a\u000209H\u0007J\u0012\u0010:\u001a\u00020 2\b\u0010;\u001a\u0004\u0018\u00010\u000bH\u0002J\b\u0010<\u001a\u00020 H\u0003J\b\u0010=\u001a\u00020 H\u0002J\b\u0010>\u001a\u00020 H\u0002J&\u0010?\u001a\u00020 2\b\u0010@\u001a\u0004\u0018\u00010\u000b2\b\u0010A\u001a\u0004\u0018\u00010\u000b2\b\u0010B\u001a\u0004\u0018\u00010\u000bH\u0002J\u0010\u0010C\u001a\u00020 2\u0006\u0010@\u001a\u00020\u000bH\u0002J$\u0010C\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u00120D2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R.\u0010\f\u001a\"\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0018\u00010\rj\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0018\u0001`\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u000f\u001a\u001e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\rj\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b`\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u001b\u001a\u001e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\rj\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b`\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006G"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/EditReviewActivity;", "Lcom/movieboxpro/android/base/BaseSimpleActivity;", "()V", "bbsInfo", "Lcom/movieboxpro/android/model/user/UserModel$BBsInfo;", "boxType", "", "editReviewDetail", "", "finishSelf", "for_quote", "", "idMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "imageMap", "isKeyboardVisible", "lubanImages", "", "Ljava/io/File;", "pid", "popAddVideo", "Landroid/widget/PopupWindow;", "popFont", "quote", "reviewModel", "Lcom/movieboxpro/android/model/ReviewModel;", "thumbImageMap", "userModel", "Lcom/movieboxpro/android/model/user/UserModel;", "videoId", "closePop", "", "compress", FileDownloadService.PARAMS_KEY_PATH, "enableEventBus", "getAltMovieListImg", "item", "Lcom/movieboxpro/android/model/movie/MovieListModel$MovieListItem;", "getAltVideos", "videos", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/movie/NormalFilmModel;", "getBBSApiAPPID", "getEditContent", "getLayoutResId", "getStatusColor", "initData", "initListener", "initRichEditor", "initView", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onMovieListSelected", "Lcom/movieboxpro/android/event/MovieListSelectedEvent;", "processSelectedImage", "imagePath", "requestReadWritePermission", "showAddVideoPop", "showFontPop", "updateReview", "html", "title", "userFile", "uploadImages", "Lio/reactivex/Observable;", "files", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class EditReviewActivity extends BaseSimpleActivity {
    public static final Companion Companion = new Companion(null);
    public static final int REQUEST_CODE_CHOOSE = 1;
    public static final int REQUEST_CODE_MOVIE_LISTS = 3;
    public static final int REQUEST_CODE_VIDEO = 2;
    private UserModel.BBsInfo bbsInfo;
    private boolean editReviewDetail;
    private boolean finishSelf;
    private HashMap<String, String> idMap;
    private boolean isKeyboardVisible;
    private List<? extends File> lubanImages;
    private String pid;
    private PopupWindow popAddVideo;
    private PopupWindow popFont;
    private ReviewModel reviewModel;
    private UserModel userModel;
    private String videoId;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private HashMap<String, String> imageMap = new HashMap<>();
    private HashMap<String, String> thumbImageMap = new HashMap<>();
    private int boxType = 1;
    private String quote = "";
    private String for_quote = "";

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
        return R.layout.activity_edit_review;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    protected int getStatusColor() {
        return R.color.color_main;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initListener() {
        ((LinearLayout) _$_findCachedViewById(R.id.ll_back)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$EditReviewActivity$EIlJKyECq4OXvAc35KXiAYGhZlo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditReviewActivity.m434initListener$lambda0(EditReviewActivity.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivInsertImage)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$EditReviewActivity$bqI914uibKK6K2ubL3cpZkyysEo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditReviewActivity.m435initListener$lambda1(EditReviewActivity.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.ll_right)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$EditReviewActivity$Y9REMQK0EgEbs0Cwwzmms4iU8k8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditReviewActivity.m436initListener$lambda2(EditReviewActivity.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivTextStyle)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$EditReviewActivity$bfWuuazjoNlgzvQuRhymGSoO-yE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditReviewActivity.m437initListener$lambda3(EditReviewActivity.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivInsertLink)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$EditReviewActivity$2TioY4Qb_o16uhK_rf66_sfSICE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditReviewActivity.m438initListener$lambda4(EditReviewActivity.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.linearLayout)).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$EditReviewActivity$xLoznsLbJp6VxfaN3epv_S2n-9A
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                EditReviewActivity.m439initListener$lambda5(EditReviewActivity.this);
            }
        });
        ((EditText) _$_findCachedViewById(R.id.etTitle)).setOnEditorActionListener($$Lambda$EditReviewActivity$PtUZlnOEoFy5AsPZtyysIpZ5QJw.INSTANCE);
        ((EditText) _$_findCachedViewById(R.id.etTitle)).addTextChangedListener(new TextWatcher() { // from class: com.movieboxpro.android.view.activity.review.EditReviewActivity$initListener$8
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                String obj;
                Integer num = null;
                String obj2 = editable == null ? null : editable.toString();
                if (obj2 == null || StringsKt.isBlank(obj2)) {
                    TextView tvTitleSize = (TextView) EditReviewActivity.this._$_findCachedViewById(R.id.tvTitleSize);
                    Intrinsics.checkNotNullExpressionValue(tvTitleSize, "tvTitleSize");
                    CommonExtKt.invisible(tvTitleSize);
                    return;
                }
                TextView tvTitleSize2 = (TextView) EditReviewActivity.this._$_findCachedViewById(R.id.tvTitleSize);
                Intrinsics.checkNotNullExpressionValue(tvTitleSize2, "tvTitleSize");
                CommonExtKt.visible(tvTitleSize2);
                TextView textView = (TextView) EditReviewActivity.this._$_findCachedViewById(R.id.tvTitleSize);
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Object[] objArr = new Object[1];
                if (editable != null && (obj = editable.toString()) != null) {
                    num = Integer.valueOf(obj.length());
                }
                objArr[0] = num;
                String format = String.format("%s/140", Arrays.copyOf(objArr, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                textView.setText(format);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m434initListener$lambda0(EditReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        InputMethodUtils.hideSoftInput(this$0);
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m435initListener$lambda1(EditReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.requestReadWritePermission();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m436initListener$lambda2(EditReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String html = ((MyRichEditor) this$0._$_findCachedViewById(R.id.richEditor)).getHtml();
        String obj = ((EditText) this$0._$_findCachedViewById(R.id.etTitle)).getText().toString();
        boolean z = true;
        if ((obj.length() == 0) && !this$0.editReviewDetail) {
            ToastUtils.showShort("Title cannot be blank", new Object[0]);
            return;
        }
        String str = html;
        if (str != null && str.length() != 0) {
            z = false;
        }
        if (z) {
            ToastUtils.showShort("Content cannot be blank", new Object[0]);
        } else if (this$0.imageMap.isEmpty()) {
            this$0.updateReview(html, obj, "");
            InputMethodUtils.hideSoftInput(this$0);
        } else {
            Intrinsics.checkNotNullExpressionValue(html, "html");
            this$0.uploadImages(html);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-3  reason: not valid java name */
    public static final void m437initListener$lambda3(EditReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showFontPop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-4  reason: not valid java name */
    public static final void m438initListener$lambda4(EditReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showAddVideoPop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-5  reason: not valid java name */
    public static final void m439initListener$lambda5(EditReviewActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Rect rect = new Rect();
        ((LinearLayout) this$0._$_findCachedViewById(R.id.linearLayout)).getWindowVisibleDisplayFrame(rect);
        EditReviewActivity editReviewActivity = this$0;
        boolean z = false;
        if ((((LinearLayout) this$0._$_findCachedViewById(R.id.linearLayout)).getRootView().getHeight() - rect.bottom) - (SystemUtils.hasNavBar(editReviewActivity) ? SystemUtils.getNavBarHeight(editReviewActivity) : 0) > 0) {
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
    /* renamed from: initListener$lambda-6  reason: not valid java name */
    public static final boolean m440initListener$lambda6(TextView textView, int i, KeyEvent keyEvent) {
        return keyEvent.getKeyCode() == 66;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateReview(String str, String str2, String str3) {
        String content;
        String replaceAll = Pattern.compile(ShellUtil.COMMAND_LINE_END).matcher(str).replaceAll("");
        ReviewModel reviewModel = this.reviewModel;
        if (reviewModel == null || (content = reviewModel.getContent()) == null) {
            content = "";
        }
        String stringPlus = Intrinsics.stringPlus(content, replaceAll);
        CommonExtKt.logD(this, Intrinsics.stringPlus("send html:", stringPlus));
        String str4 = this.quote;
        String stringPlus2 = Intrinsics.stringPlus(stringPlus, str4 != null ? str4 : "");
        UserModel.BBsInfo bBsInfo = App.getBBsInfo();
        APIService service = Http.getService();
        String str5 = API.BBS_URL;
        String auth = bBsInfo.getAuth();
        String authkey = bBsInfo.getAuthkey();
        String formhash = bBsInfo.getFormhash();
        ReviewModel reviewModel2 = this.reviewModel;
        String fid = reviewModel2 == null ? null : reviewModel2.getFid();
        ReviewModel reviewModel3 = this.reviewModel;
        String tid = reviewModel3 == null ? null : reviewModel3.getTid();
        ReviewModel reviewModel4 = this.reviewModel;
        Observable<R> compose = service.updateReview(str5, "updatemythread", auth, authkey, formhash, fid, tid, reviewModel4 == null ? null : reviewModel4.getPid(), str2, URLEncoder.encode(stringPlus2, "UTF-8"), "", str3, "yes", "").compose(RxUtils.rxTranslate2Bean(HashMap.class));
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
        RxSubscribersKt.transform(compose, this).subscribe(new Consumer() { // from class: com.movieboxpro.android.view.activity.review.EditReviewActivity$updateReview$$inlined$subscribeToBean$default$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(T it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                HashMap hashMap = (HashMap) it;
                EditReviewActivity.this.hideLoadingView();
                Intent intent = new Intent();
                intent.putExtra("title", (String) hashMap.get("title"));
                intent.putExtra("message", (String) hashMap.get("message"));
                intent.putExtra(FirebaseAnalytics.Param.CONTENT, (String) hashMap.get(FirebaseAnalytics.Param.CONTENT));
                EditReviewActivity.this.setResult(-1, intent);
                EditReviewActivity.this.finish();
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.EditReviewActivity$updateReview$$inlined$subscribeToBean$default$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                ApiException handleException = ApiException.handleException(th);
                Intrinsics.checkNotNullExpressionValue(handleException, "handleException(it)");
                EditReviewActivity.this.hideLoadingView();
                ToastUtils.showShort(Intrinsics.stringPlus("Send Failed:", handleException.getMessage()), new Object[0]);
                if (th instanceof ServerException) {
                    ServerException serverException = (ServerException) th;
                }
            }
        }, new Action() { // from class: com.movieboxpro.android.view.activity.review.EditReviewActivity$updateReview$$inlined$subscribeToBean$default$3
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.EditReviewActivity$updateReview$$inlined$subscribeToBean$default$4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                EditReviewActivity.this.showLoadingView();
            }
        });
    }

    private final void requestReadWritePermission() {
        startActivityForResult(Intent.createChooser(new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI), "Choose"), 1);
    }

    private final void uploadImages(final String str) {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        Object as = Observable.just(str).map(new Function() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$EditReviewActivity$AgE1CVnp2jY3ZcurBN3uqghiVac
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                List m464uploadImages$lambda12;
                m464uploadImages$lambda12 = EditReviewActivity.m464uploadImages$lambda12(str, this, (String) obj);
                return m464uploadImages$lambda12;
            }
        }).subscribeOn(Schedulers.computation()).flatMap(new Function() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$EditReviewActivity$WbjSoSiyo1gy-6AwrGpba7zCPNg
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                ObservableSource m465uploadImages$lambda13;
                m465uploadImages$lambda13 = EditReviewActivity.m465uploadImages$lambda13(EditReviewActivity.this, (List) obj);
                return m465uploadImages$lambda13;
            }
        }).map(new Function() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$EditReviewActivity$JCbc9u7DC1J3Din4TfVvU-nwyWE
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                String m466uploadImages$lambda15;
                m466uploadImages$lambda15 = EditReviewActivity.m466uploadImages$lambda15(Ref.ObjectRef.this, str, (List) obj);
                return m466uploadImages$lambda15;
            }
        }).observeOn(AndroidSchedulers.mainThread()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(as, "just(html)\n             …bindLifecycleOwner(this))");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, new EditReviewActivity$uploadImages$4(this), null, new EditReviewActivity$uploadImages$5(this), null, new EditReviewActivity$uploadImages$6(this, objectRef), 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: uploadImages$lambda-12  reason: not valid java name */
    public static final List m464uploadImages$lambda12(String html, EditReviewActivity this$0, String it) {
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
    public static final ObservableSource m465uploadImages$lambda13(EditReviewActivity this$0, List it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.lubanImages = it;
        return this$0.uploadImages(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: uploadImages$lambda-15  reason: not valid java name */
    public static final String m466uploadImages$lambda15(Ref.ObjectRef idList, String html, List it) {
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
        Observable<List<String>> observable = ObservableKt.toObservable(list).flatMap(new Function() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$EditReviewActivity$O8-qXI4lsXdsdjxVGDQo6YSHB8s
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                ObservableSource m467uploadImages$lambda17;
                m467uploadImages$lambda17 = EditReviewActivity.m467uploadImages$lambda17(EditReviewActivity.this, (File) obj);
                return m467uploadImages$lambda17;
            }
        }).toList().toObservable();
        Intrinsics.checkNotNullExpressionValue(observable, "files.toObservable().fla…          .toObservable()");
        return observable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: uploadImages$lambda-17  reason: not valid java name */
    public static final ObservableSource m467uploadImages$lambda17(EditReviewActivity this$0, File it) {
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
        return executeUploadObservable != null ? executeUploadObservable.map($$Lambda$EditReviewActivity$Os8J0UTXuOqvLwqEo4CR7iVmeww.INSTANCE) : null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: uploadImages$lambda-17$lambda-16  reason: not valid java name */
    public static final String m468uploadImages$lambda17$lambda16(String json) {
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
        this.pid = getIntent().getStringExtra("pid");
        this.videoId = getIntent().getStringExtra("pid");
        this.boxType = getIntent().getIntExtra("boxType", 1);
        this.userModel = App.getUserModel();
        this.bbsInfo = App.getBBsInfo();
        boolean booleanExtra = getIntent().getBooleanExtra("reviewDetail", false);
        this.editReviewDetail = booleanExtra;
        if (booleanExtra) {
            EditText etTitle = (EditText) _$_findCachedViewById(R.id.etTitle);
            Intrinsics.checkNotNullExpressionValue(etTitle, "etTitle");
            CommonExtKt.gone(etTitle);
            TextView tvTitleSize = (TextView) _$_findCachedViewById(R.id.tvTitleSize);
            Intrinsics.checkNotNullExpressionValue(tvTitleSize, "tvTitleSize");
            CommonExtKt.gone(tvTitleSize);
        }
        getEditContent();
    }

    private final void getEditContent() {
        Observable<R> compose = Http.getService().getEditContent(API.BBS_URL, "editmythread", getIntent().getStringExtra("bbsId"), this.pid).compose(RxUtils.rxTranslate2Bean(ReviewModel.class));
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
        RxSubscribersKt.transform(compose, this).subscribe(new Consumer() { // from class: com.movieboxpro.android.view.activity.review.EditReviewActivity$getEditContent$$inlined$subscribeToBean$default$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(T it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                ReviewModel reviewModel = (ReviewModel) it;
                EditReviewActivity.this.hideLoadingView();
                EditReviewActivity.this.reviewModel = reviewModel;
                ((EditText) EditReviewActivity.this._$_findCachedViewById(R.id.etTitle)).setText(reviewModel.getTitle());
                ((MyRichEditor) EditReviewActivity.this._$_findCachedViewById(R.id.richEditor)).requestFocus();
                ((MyRichEditor) EditReviewActivity.this._$_findCachedViewById(R.id.richEditor)).focusEditor();
                String replaceAll = Pattern.compile("\\\\").matcher(Pattern.compile(ShellUtil.COMMAND_LINE_END).matcher(reviewModel.getMessage()).replaceAll("")).replaceAll("\\\\\\\\");
                CommonExtKt.logD(EditReviewActivity.this, Intrinsics.stringPlus("insert html:", replaceAll));
                final String replaceAll2 = Pattern.compile("'").matcher(replaceAll).replaceAll("\\\\'");
                CommonExtKt.logD(EditReviewActivity.this, Intrinsics.stringPlus("insert html:", replaceAll2));
                final EditReviewActivity editReviewActivity = EditReviewActivity.this;
                ((MyRichEditor) EditReviewActivity.this._$_findCachedViewById(R.id.richEditor)).postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.activity.review.EditReviewActivity$getEditContent$2$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        ((MyRichEditor) EditReviewActivity.this._$_findCachedViewById(R.id.richEditor)).insertHtml(replaceAll2);
                    }
                }, 300L);
                EditReviewActivity.this.quote = reviewModel.getQuote();
                EditReviewActivity.this.for_quote = reviewModel.getFor_quote();
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.EditReviewActivity$getEditContent$$inlined$subscribeToBean$default$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Intrinsics.checkNotNullExpressionValue(ApiException.handleException(th), "handleException(it)");
                EditReviewActivity.this.hideLoadingView();
                if (th instanceof ServerException) {
                    ServerException serverException = (ServerException) th;
                }
            }
        }, new Action() { // from class: com.movieboxpro.android.view.activity.review.EditReviewActivity$getEditContent$$inlined$subscribeToBean$default$3
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.EditReviewActivity$getEditContent$$inlined$subscribeToBean$default$4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                EditReviewActivity.this.showLoadingView();
            }
        });
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initView() {
        ((TextView) _$_findCachedViewById(R.id.tv_title)).setText("Edit");
        ((ImageView) _$_findCachedViewById(R.id.iv_right)).setImageResource(R.mipmap.ic_send_msg);
        ImageView iv_right = (ImageView) _$_findCachedViewById(R.id.iv_right);
        Intrinsics.checkNotNullExpressionValue(iv_right, "iv_right");
        CommonExtKt.visible(iv_right);
        ((FrameLayout) _$_findCachedViewById(R.id.frameLayout)).setBackgroundColor(CommonExtKt.colorInt((Context) this, (int) R.color.color_main));
        initRichEditor();
        ((MyRichEditor) _$_findCachedViewById(R.id.richEditor)).setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$EditReviewActivity$3cwr52KXgIs_VtjTDZsFULuZ4xU
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                EditReviewActivity.m441initView$lambda21(EditReviewActivity.this, view, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-21  reason: not valid java name */
    public static final void m441initView$lambda21(EditReviewActivity this$0, View view, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            LinearLayout linearLayout = (LinearLayout) this$0._$_findCachedViewById(R.id.linearLayout);
            Intrinsics.checkNotNullExpressionValue(linearLayout, "linearLayout");
            CommonExtKt.visible(linearLayout);
            return;
        }
        LinearLayout linearLayout2 = (LinearLayout) this$0._$_findCachedViewById(R.id.linearLayout);
        Intrinsics.checkNotNullExpressionValue(linearLayout2, "linearLayout");
        CommonExtKt.gone(linearLayout2);
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
        ((TextView) inflate.findViewById(R.id.tvSearch)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$EditReviewActivity$Mi0B_AXxeg09yR8BBuhf_2D1vcM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditReviewActivity.m451showAddVideoPop$lambda22(EditReviewActivity.this, view);
            }
        });
        ((TextView) inflate.findViewById(R.id.tvHistory)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$EditReviewActivity$OHUSzoD-vFhgZqCbZEmNZDmSmvA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditReviewActivity.m452showAddVideoPop$lambda23(EditReviewActivity.this, view);
            }
        });
        ((TextView) inflate.findViewById(R.id.tvFavorite)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$EditReviewActivity$TmDHepW2uWcGpNK1gV2rbYZcjkc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditReviewActivity.m453showAddVideoPop$lambda24(EditReviewActivity.this, view);
            }
        });
        ((TextView) inflate.findViewById(R.id.tvMovieLists)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$EditReviewActivity$vJzQRlm90yADt3Ru_Ti8cgv8OYQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditReviewActivity.m454showAddVideoPop$lambda25(EditReviewActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showAddVideoPop$lambda-22  reason: not valid java name */
    public static final void m451showAddVideoPop$lambda22(EditReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AltVideosActivity.Companion.start(this$0, 1, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showAddVideoPop$lambda-23  reason: not valid java name */
    public static final void m452showAddVideoPop$lambda23(EditReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AltVideosActivity.Companion.start(this$0, 2, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showAddVideoPop$lambda-24  reason: not valid java name */
    public static final void m453showAddVideoPop$lambda24(EditReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AltVideosActivity.Companion.start(this$0, 3, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showAddVideoPop$lambda-25  reason: not valid java name */
    public static final void m454showAddVideoPop$lambda25(EditReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AltMovieListActivity.Companion.start(this$0);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onMovieListSelected(MovieListSelectedEvent item) {
        Intrinsics.checkNotNullParameter(item, "item");
        MovieListModel.MovieListItem item2 = item.getItem();
        Intrinsics.checkNotNullExpressionValue(item2, "item.item");
        getAltMovieListImg(item2);
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
        ((ImageButton) inflate.findViewById(R.id.action_text_bold)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$EditReviewActivity$86TdvL_DYDebE8fJVBxczeM4AK0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditReviewActivity.m455showFontPop$lambda26(EditReviewActivity.this, view);
            }
        });
        ((ImageButton) inflate.findViewById(R.id.action_text_italic)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$EditReviewActivity$ljqH4smrKuJ1_m1xBMC7BBMBCGg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditReviewActivity.m456showFontPop$lambda27(EditReviewActivity.this, view);
            }
        });
        ((ImageButton) inflate.findViewById(R.id.action_text_underline)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$EditReviewActivity$tQv_itSFcpujS0SRXbsgirAwVkg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditReviewActivity.m457showFontPop$lambda28(EditReviewActivity.this, view);
            }
        });
        ((ImageButton) inflate.findViewById(R.id.action_text_h1)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$EditReviewActivity$luKejPqvk2j_beIyVemtMiIpPIU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditReviewActivity.m458showFontPop$lambda29(EditReviewActivity.this, view);
            }
        });
        ((ImageButton) inflate.findViewById(R.id.action_text_h2)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$EditReviewActivity$tFizcdd3EzEwOUuOOBI_rqMI7YA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditReviewActivity.m459showFontPop$lambda30(EditReviewActivity.this, view);
            }
        });
        ((ImageButton) inflate.findViewById(R.id.action_text_h3)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$EditReviewActivity$jalz_82nt0kggIlLNNbunlEvlTM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditReviewActivity.m460showFontPop$lambda31(EditReviewActivity.this, view);
            }
        });
        ((ImageButton) inflate.findViewById(R.id.action_text_left)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$EditReviewActivity$L9f1y8uFLNVj_KiTGVUjTHcG_oY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditReviewActivity.m461showFontPop$lambda32(EditReviewActivity.this, view);
            }
        });
        ((ImageButton) inflate.findViewById(R.id.action_text_center)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$EditReviewActivity$LGAW7FRSyiWLzVES-h7GDzUZCys
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditReviewActivity.m462showFontPop$lambda33(EditReviewActivity.this, view);
            }
        });
        ((ImageButton) inflate.findViewById(R.id.action_text_right)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$EditReviewActivity$-Z0i4W60CBaL1j1ZUyDN6Lo2Z3U
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditReviewActivity.m463showFontPop$lambda34(EditReviewActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showFontPop$lambda-26  reason: not valid java name */
    public static final void m455showFontPop$lambda26(EditReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((MyRichEditor) this$0._$_findCachedViewById(R.id.richEditor)).setBold();
        this$0.closePop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showFontPop$lambda-27  reason: not valid java name */
    public static final void m456showFontPop$lambda27(EditReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((MyRichEditor) this$0._$_findCachedViewById(R.id.richEditor)).setItalic();
        this$0.closePop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showFontPop$lambda-28  reason: not valid java name */
    public static final void m457showFontPop$lambda28(EditReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((MyRichEditor) this$0._$_findCachedViewById(R.id.richEditor)).setUnderline();
        this$0.closePop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showFontPop$lambda-29  reason: not valid java name */
    public static final void m458showFontPop$lambda29(EditReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((MyRichEditor) this$0._$_findCachedViewById(R.id.richEditor)).setHeading(1);
        this$0.closePop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showFontPop$lambda-30  reason: not valid java name */
    public static final void m459showFontPop$lambda30(EditReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((MyRichEditor) this$0._$_findCachedViewById(R.id.richEditor)).setHeading(2);
        this$0.closePop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showFontPop$lambda-31  reason: not valid java name */
    public static final void m460showFontPop$lambda31(EditReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((MyRichEditor) this$0._$_findCachedViewById(R.id.richEditor)).setHeading(3);
        this$0.closePop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showFontPop$lambda-32  reason: not valid java name */
    public static final void m461showFontPop$lambda32(EditReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((MyRichEditor) this$0._$_findCachedViewById(R.id.richEditor)).setAlignLeft();
        this$0.closePop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showFontPop$lambda-33  reason: not valid java name */
    public static final void m462showFontPop$lambda33(EditReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((MyRichEditor) this$0._$_findCachedViewById(R.id.richEditor)).setAlignCenter();
        this$0.closePop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showFontPop$lambda-34  reason: not valid java name */
    public static final void m463showFontPop$lambda34(EditReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((MyRichEditor) this$0._$_findCachedViewById(R.id.richEditor)).setAlignRight();
        this$0.closePop();
    }

    private final void initRichEditor() {
        int dp2Px = CommonExtKt.dp2Px(5);
        ((MyRichEditor) _$_findCachedViewById(R.id.richEditor)).setPadding(dp2Px, dp2Px, dp2Px, dp2Px);
        ((MyRichEditor) _$_findCachedViewById(R.id.richEditor)).setPlaceholder("Add a reply");
        ((MyRichEditor) _$_findCachedViewById(R.id.richEditor)).setEditorBackgroundColor(CommonExtKt.colorInt((Context) this, (int) R.color.color_main));
        ((MyRichEditor) _$_findCachedViewById(R.id.richEditor)).focusEditor();
        ((MyRichEditor) _$_findCachedViewById(R.id.richEditor)).setEditorFontColor(-1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            if (i != 1) {
                if (i != 2) {
                    return;
                }
                ArrayList<NormalFilmModel> parcelableArrayListExtra = intent != null ? intent.getParcelableArrayListExtra("data") : null;
                if (parcelableArrayListExtra == null) {
                    return;
                }
                getAltVideos(parcelableArrayListExtra);
                return;
            }
            Uri data = intent != null ? intent.getData() : null;
            if (data != null) {
                String[] strArr = {"_data"};
                Cursor query = getContentResolver().query(data, strArr, null, null, null);
                if (query != null) {
                    query.moveToFirst();
                    processSelectedImage(query.getString(query.getColumnIndex(strArr[0])));
                    query.close();
                }
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
        Observable<R> compose = Http.getService().GetAltMovieListImage(API.BBS_URL, "createMovielistImg", lid, movieListItem.getName(), movieListItem.getUsername(), str3, movieListItem.getAvatar()).compose(RxUtils.rxTranslate2Bean(HashMap.class));
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
        RxSubscribersKt.transform(compose, this).subscribe(new Consumer() { // from class: com.movieboxpro.android.view.activity.review.EditReviewActivity$getAltMovieListImg$$inlined$subscribeToBean$default$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(T it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                HashMap hashMap = (HashMap) it;
                EditReviewActivity.this.hideLoadingView();
                ((MyRichEditor) EditReviewActivity.this._$_findCachedViewById(R.id.richEditor)).insertHtml("<div class=\"img\"><img src=\"" + hashMap.get("url") + "\"  videoid=\"" + hashMap.get("id") + "\"  type=\"" + hashMap.get(IjkMediaMeta.IJKM_KEY_TYPE) + "\" alt=\"picvision\"  style= \"margin-top:10px;max-width:100%;\"/></div>");
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.EditReviewActivity$getAltMovieListImg$$inlined$subscribeToBean$default$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                ApiException handleException = ApiException.handleException(th);
                Intrinsics.checkNotNullExpressionValue(handleException, "handleException(it)");
                EditReviewActivity.this.hideLoadingView();
                ToastUtils.showShort(Intrinsics.stringPlus("Load failed:", handleException.getMessage()), new Object[0]);
                if (th instanceof ServerException) {
                    ServerException serverException = (ServerException) th;
                }
            }
        }, new Action() { // from class: com.movieboxpro.android.view.activity.review.EditReviewActivity$getAltMovieListImg$$inlined$subscribeToBean$default$3
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.EditReviewActivity$getAltMovieListImg$$inlined$subscribeToBean$default$4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                EditReviewActivity.this.showLoadingView();
            }
        });
    }

    private final void getAltVideos(ArrayList<NormalFilmModel> arrayList) {
        Observable flatMap = ObservableKt.toObservable(arrayList).flatMap($$Lambda$EditReviewActivity$Kpe0ruxY0Q39DaBfIWg2hGu4cnY.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(flatMap, "videos.toObservable()\n  …t.year)\n                }");
        Observable compose = flatMap.compose(RxUtils.rxTranslate2Bean(HashMap.class));
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
        Observable observable = compose.map($$Lambda$EditReviewActivity$jBUqDYxTIsOYavrpiKj5Npm3d8E.INSTANCE).toList().toObservable();
        Intrinsics.checkNotNullExpressionValue(observable, "videos.toObservable()\n  …          .toObservable()");
        RxSubscribersKt.transform(observable, this).subscribe(new Consumer() { // from class: com.movieboxpro.android.view.activity.review.EditReviewActivity$getAltVideos$$inlined$transformSubscribe$default$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(T it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                EditReviewActivity.this.hideLoadingView();
                for (Triple triple : (List) it) {
                    ((MyRichEditor) EditReviewActivity.this._$_findCachedViewById(R.id.richEditor)).insertHtml("<div class=\"img\"><img src=\"" + triple.getFirst() + "\"  videoid=\"" + triple.getSecond() + "\"  type=\"" + triple.getThird() + "\" alt=\"picvision\"  style= \"margin-top:10px;max-width:100%;\"/></div>");
                }
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.EditReviewActivity$getAltVideos$$inlined$transformSubscribe$default$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                ApiException handleException = ApiException.handleException(th);
                Intrinsics.checkNotNullExpressionValue(handleException, "handleException(it)");
                EditReviewActivity.this.hideLoadingView();
                ToastUtils.showShort(Intrinsics.stringPlus("Load failed:", handleException.getMessage()), new Object[0]);
                if (th instanceof ServerException) {
                    ServerException serverException = (ServerException) th;
                }
            }
        }, new Action() { // from class: com.movieboxpro.android.view.activity.review.EditReviewActivity$getAltVideos$$inlined$transformSubscribe$default$3
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.EditReviewActivity$getAltVideos$$inlined$transformSubscribe$default$4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                EditReviewActivity.this.showLoadingView();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getAltVideos$lambda-39  reason: not valid java name */
    public static final ObservableSource m432getAltVideos$lambda39(NormalFilmModel it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (it.getImdb_rating() == null) {
            it.setImdb_rating("0");
        }
        return Http.getService().GetAltVideos(API.BBS_URL, "createVideoImage", String.valueOf(it.getId()), it.getTitle(), it.getPoster(), it.getImdb_rating(), App.deviceLang, it.getBox_type(), it.getYear());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getAltVideos$lambda-40  reason: not valid java name */
    public static final Triple m433getAltVideos$lambda40(HashMap it) {
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

    private final void processSelectedImage(final String str) {
        if (new File(str).length() > 10485760) {
            ToastUtils.showShort("Image size cannot exceed 10mb", new Object[0]);
        } else if (str == null) {
        } else {
            Observable map = Observable.just(str).map(new Function() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$EditReviewActivity$zmskRhBPeNsVqazwkcXPmY6Vj9g
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    String m450processSelectedImage$lambda49$lambda45;
                    m450processSelectedImage$lambda49$lambda45 = EditReviewActivity.m450processSelectedImage$lambda49$lambda45(EditReviewActivity.this, (String) obj);
                    return m450processSelectedImage$lambda49$lambda45;
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "just<String>(it)\n       …  }\n                    }");
            RxSubscribersKt.transformCompute(map, this).subscribe(new Consumer() { // from class: com.movieboxpro.android.view.activity.review.EditReviewActivity$processSelectedImage$lambda-49$$inlined$transformSubscribeCompute$default$1
                @Override // io.reactivex.functions.Consumer
                public final void accept(T it) {
                    HashMap hashMap;
                    HashMap hashMap2;
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    String str2 = (String) it;
                    EditReviewActivity.this.hideLoadingView();
                    ((MyRichEditor) EditReviewActivity.this._$_findCachedViewById(R.id.richEditor)).insertImage(str2, "picvision", "margin-top:10px;max-width:50%;");
                    hashMap = EditReviewActivity.this.imageMap;
                    String name = new File(str).getName();
                    Intrinsics.checkNotNullExpressionValue(name, "File(imagePath).name");
                    hashMap.put(name, str);
                    hashMap2 = EditReviewActivity.this.thumbImageMap;
                    String name2 = new File(str).getName();
                    Intrinsics.checkNotNullExpressionValue(name2, "File(imagePath).name");
                    hashMap2.put(str2, name2);
                    CommonExtKt.logD(EditReviewActivity.this, str2);
                }
            }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.EditReviewActivity$processSelectedImage$lambda-49$$inlined$transformSubscribeCompute$default$2
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th) {
                    ApiException handleException = ApiException.handleException(th);
                    Intrinsics.checkNotNullExpressionValue(handleException, "handleException(it)");
                    EditReviewActivity.this.hideLoadingView();
                    ToastUtils.showShort(Intrinsics.stringPlus("Load failed:", handleException.getMessage()), new Object[0]);
                    if (th instanceof ServerException) {
                        ServerException serverException = (ServerException) th;
                    }
                }
            }, new Action() { // from class: com.movieboxpro.android.view.activity.review.EditReviewActivity$processSelectedImage$lambda-49$$inlined$transformSubscribeCompute$default$3
                @Override // io.reactivex.functions.Action
                public final void run() {
                }
            }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.EditReviewActivity$processSelectedImage$lambda-49$$inlined$transformSubscribeCompute$default$4
                @Override // io.reactivex.functions.Consumer
                public final void accept(Disposable it) {
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    EditReviewActivity.this.showLoadingView();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: processSelectedImage$lambda-49$lambda-45  reason: not valid java name */
    public static final String m450processSelectedImage$lambda49$lambda45(EditReviewActivity this$0, String path) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(path, "path");
        if (this$0.thumbImageMap.containsKey(new File(path).getName())) {
            String str = this$0.thumbImageMap.get(new File(path).getName());
            return str == null ? "" : str;
        }
        return this$0.compress(path);
    }

    /* compiled from: EditReviewActivity.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J4\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/EditReviewActivity$Companion;", "", "()V", "REQUEST_CODE_CHOOSE", "", "REQUEST_CODE_MOVIE_LISTS", "REQUEST_CODE_VIDEO", TtmlNode.START, "", "context", "Landroid/app/Activity;", "pid", "", "requestCode", "reviewDetail", "", "bbsId", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Activity activity, String pid, int i, boolean z, String str) {
            Intrinsics.checkNotNullParameter(pid, "pid");
            Intent intent = new Intent(activity, EditReviewActivity.class);
            intent.putExtra("pid", pid);
            intent.putExtra("reviewDetail", z);
            intent.putExtra("bbsId", str);
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
