package com.movieboxpro.android.view.activity.review;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
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
import androidx.fragment.app.FragmentActivity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.engine.ImageEngine;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseSimpleActivity;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.event.MovieListSelectedEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpUploadBBsRequest;
import com.movieboxpro.android.http.HttpUtils;
import com.movieboxpro.android.http.ServerException;
import com.movieboxpro.android.model.BBsResponseModel;
import com.movieboxpro.android.model.ReviewRecordModel;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.model.movie.NormalFilmModel;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.service.FileDownloadService;
import com.movieboxpro.android.utils.ChooseImageGlideEngine;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.GlideUtils;
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
/* compiled from: SendActorReviewActivity.kt */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 C2\u00020\u0001:\u0001CB\u0005¢\u0006\u0002\u0010\u0002J&\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u00042\b\u0010\u001f\u001a\u0004\u0018\u00010\u00042\b\u0010 \u001a\u0004\u0018\u00010\u0004H\u0002J\b\u0010!\u001a\u00020\u001dH\u0002J\u0010\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u0004H\u0002J\b\u0010$\u001a\u00020\fH\u0014J\u0010\u0010%\u001a\u00020\u001d2\u0006\u0010&\u001a\u00020'H\u0002J\u0016\u0010(\u001a\u00020\u001d2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*H\u0002J\b\u0010,\u001a\u00020\u0004H\u0002J\b\u0010-\u001a\u00020\nH\u0016J\b\u0010.\u001a\u00020\nH\u0014J\b\u0010/\u001a\u00020\u001dH\u0016J\b\u00100\u001a\u00020\u001dH\u0016J\b\u00101\u001a\u00020\u001dH\u0002J\b\u00102\u001a\u00020\u001dH\u0016J\"\u00103\u001a\u00020\u001d2\u0006\u00104\u001a\u00020\n2\u0006\u00105\u001a\u00020\n2\b\u00106\u001a\u0004\u0018\u000107H\u0014J\b\u00108\u001a\u00020\u001dH\u0014J\u0010\u00109\u001a\u00020\u001d2\u0006\u0010&\u001a\u00020:H\u0007J\u0018\u0010;\u001a\u00020\u001d2\u000e\u0010<\u001a\n\u0012\u0004\u0012\u00020=\u0018\u00010\u0013H\u0002J\b\u0010>\u001a\u00020\u001dH\u0002J\b\u0010?\u001a\u00020\u001dH\u0002J\u0010\u0010@\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0004H\u0002J$\u0010@\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00130A2\f\u0010B\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R.\u0010\r\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000ej\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0010\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u000ej\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004`\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0018\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u000ej\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004`\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006D"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/SendActorReviewActivity;", "Lcom/movieboxpro/android/base/BaseSimpleActivity;", "()V", "actorAvatar", "", "actorJob", "actorName", "bbsInfo", "Lcom/movieboxpro/android/model/user/UserModel$BBsInfo;", "boxType", "", "finishSelf", "", "idMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "imageMap", "isKeyboardVisible", "lubanImages", "", "Ljava/io/File;", "popAddVideo", "Landroid/widget/PopupWindow;", "popFont", "thumbImageMap", "userModel", "Lcom/movieboxpro/android/model/user/UserModel;", "videoId", "addReview", "", "html", "title", "userFile", "closePop", "compress", FileDownloadService.PARAMS_KEY_PATH, "enableEventBus", "getAltMovieListImg", "item", "Lcom/movieboxpro/android/model/movie/MovieListModel$MovieListItem;", "getAltVideos", "videos", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/movie/NormalFilmModel;", "getBBSApiAPPID", "getLayoutResId", "getStatusColor", "initData", "initListener", "initRichEditor", "initView", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onDestroy", "onMovieListSelected", "Lcom/movieboxpro/android/event/MovieListSelectedEvent;", "processSelectedImage", "imagePath", "Lcom/huantansheng/easyphotos/models/album/entity/Photo;", "showAddVideoPop", "showFontPop", "uploadImages", "Lio/reactivex/Observable;", "files", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SendActorReviewActivity extends BaseSimpleActivity {
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
    private String videoId;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private HashMap<String, String> imageMap = new HashMap<>();
    private HashMap<String, String> thumbImageMap = new HashMap<>();
    private int boxType = 1;
    private String actorName = "";
    private String actorJob = "";
    private String actorAvatar = "";

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
        return R.layout.activity_send_review;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    protected int getStatusColor() {
        return R.color.color_main;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initListener() {
        ((LinearLayout) _$_findCachedViewById(R.id.ll_back)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SendActorReviewActivity$_wz-NSmeFT5Ps5NhhIA7ye_oVIU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SendActorReviewActivity.m663initListener$lambda0(SendActorReviewActivity.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivInsertImage)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SendActorReviewActivity$pfjpvTFF7FtX0GB04uiEhgUxjyQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SendActorReviewActivity.m664initListener$lambda1(SendActorReviewActivity.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.ll_right)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SendActorReviewActivity$m1UGOh-P7om4D0Pv_8RiXwGSOyo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SendActorReviewActivity.m665initListener$lambda2(SendActorReviewActivity.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivTextStyle)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SendActorReviewActivity$QoFGU5g_dGPuGC6chRliFYAyAlI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SendActorReviewActivity.m666initListener$lambda3(SendActorReviewActivity.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivInsertLink)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SendActorReviewActivity$rbsCeUjtfMQp41RIqzz1ha5BW0g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SendActorReviewActivity.m667initListener$lambda4(SendActorReviewActivity.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.linearLayout)).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SendActorReviewActivity$cCecuPdFL9PI-g91hxLq-yizIyw
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                SendActorReviewActivity.m668initListener$lambda5(SendActorReviewActivity.this);
            }
        });
        ((EditText) _$_findCachedViewById(R.id.etTitle)).setOnEditorActionListener($$Lambda$SendActorReviewActivity$PXToCJBDN1qlT8VRmga3r2Sf3sE.INSTANCE);
        ((EditText) _$_findCachedViewById(R.id.etTitle)).addTextChangedListener(new TextWatcher() { // from class: com.movieboxpro.android.view.activity.review.SendActorReviewActivity$initListener$8
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
                    TextView tvTitleSize = (TextView) SendActorReviewActivity.this._$_findCachedViewById(R.id.tvTitleSize);
                    Intrinsics.checkNotNullExpressionValue(tvTitleSize, "tvTitleSize");
                    CommonExtKt.invisible(tvTitleSize);
                    return;
                }
                TextView tvTitleSize2 = (TextView) SendActorReviewActivity.this._$_findCachedViewById(R.id.tvTitleSize);
                Intrinsics.checkNotNullExpressionValue(tvTitleSize2, "tvTitleSize");
                CommonExtKt.visible(tvTitleSize2);
                TextView textView = (TextView) SendActorReviewActivity.this._$_findCachedViewById(R.id.tvTitleSize);
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
    public static final void m663initListener$lambda0(SendActorReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        InputMethodUtils.hideSoftInput(this$0);
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m664initListener$lambda1(SendActorReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        EasyPhotos.createAlbum((FragmentActivity) this$0, true, (ImageEngine) ChooseImageGlideEngine.getInstance()).setFileProviderAuthority("com.movieboxpro.android.fileProvider").setCount(9).start(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m665initListener$lambda2(SendActorReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String html = ((MyRichEditor) this$0._$_findCachedViewById(R.id.richEditor)).getHtml();
        String obj = ((EditText) this$0._$_findCachedViewById(R.id.etTitle)).getText().toString();
        if (StringsKt.isBlank(obj)) {
            ToastUtils.showShort("Title cannot be blank", new Object[0]);
            return;
        }
        String str = html;
        if (str == null || StringsKt.isBlank(str)) {
            ToastUtils.showShort("Content cannot be blank", new Object[0]);
        } else if (this$0.imageMap.isEmpty()) {
            this$0.addReview(html, obj, "");
            InputMethodUtils.hideSoftInput(this$0);
        } else {
            Intrinsics.checkNotNullExpressionValue(html, "html");
            this$0.uploadImages(html);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-3  reason: not valid java name */
    public static final void m666initListener$lambda3(SendActorReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showFontPop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-4  reason: not valid java name */
    public static final void m667initListener$lambda4(SendActorReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showAddVideoPop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-5  reason: not valid java name */
    public static final void m668initListener$lambda5(SendActorReviewActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Rect rect = new Rect();
        ((LinearLayout) this$0._$_findCachedViewById(R.id.linearLayout)).getWindowVisibleDisplayFrame(rect);
        SendActorReviewActivity sendActorReviewActivity = this$0;
        boolean z = false;
        if ((((LinearLayout) this$0._$_findCachedViewById(R.id.linearLayout)).getRootView().getHeight() - rect.bottom) - (SystemUtils.hasNavBar(sendActorReviewActivity) ? SystemUtils.getNavBarHeight(sendActorReviewActivity) : 0) > 0) {
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
    public static final boolean m669initListener$lambda6(TextView textView, int i, KeyEvent keyEvent) {
        return keyEvent.getKeyCode() == 66;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v4, types: [T, java.lang.String] */
    public final void addReview(String str, final String str2, final String str3) {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = str;
        objectRef.element = Pattern.compile(ShellUtil.COMMAND_LINE_END).matcher((CharSequence) objectRef.element).replaceAll("");
        CommonExtKt.logD(this, Intrinsics.stringPlus("send html:", objectRef.element));
        Intrinsics.checkNotNullExpressionValue(App.getUserData(), "getUserData()");
        final UserModel.BBsInfo bBsInfo = App.getBBsInfo();
        final File file = new File(Constant.DIR_CACHE, "image.jpg");
        ((ObservableSubscribeProxy) Observable.just(this.actorAvatar).map(new Function() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SendActorReviewActivity$UQnWw-pMBju96OhFeAxWvKPVWf0
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                Boolean m655addReview$lambda7;
                m655addReview$lambda7 = SendActorReviewActivity.m655addReview$lambda7(SendActorReviewActivity.this, file, (String) obj);
                return m655addReview$lambda7;
            }
        }).flatMap(new Function() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SendActorReviewActivity$adg2BxbQvKRx5gxjW5jzCd2z0-Q
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                ObservableSource m656addReview$lambda8;
                m656addReview$lambda8 = SendActorReviewActivity.m656addReview$lambda8(file, bBsInfo, str2, objectRef, this, str3, (Boolean) obj);
                return m656addReview$lambda8;
            }
        }).map($$Lambda$SendActorReviewActivity$EcENqWhwxR4aCLvcnm7LyQOVxXo.INSTANCE).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<BBsResponseModel>() { // from class: com.movieboxpro.android.view.activity.review.SendActorReviewActivity$addReview$4
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                SendActorReviewActivity.this.showLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(BBsResponseModel model) {
                int i;
                String str4;
                Intrinsics.checkNotNullParameter(model, "model");
                if (model.getMessage() != null) {
                    if (Intrinsics.areEqual("post_newthread_succeed", model.getMessage().getMessageval())) {
                        ReviewRecordUtils reviewRecordUtils = ReviewRecordUtils.Companion.get();
                        i = SendActorReviewActivity.this.boxType;
                        str4 = SendActorReviewActivity.this.videoId;
                        reviewRecordUtils.deleteVideoReviewRecord(i, str4);
                        SendActorReviewActivity.this.finishSelf = true;
                        SendActorReviewActivity.this.setResult(-1);
                        SendActorReviewActivity.this.finish();
                    } else if (Intrinsics.areEqual("post_flood_ctrl", model.getMessage().getMessageval())) {
                        ToastUtils.showShort("Sorry, your two comments are less than 15 seconds apart. Please wait a moment.", new Object[0]);
                    } else {
                        ToastUtils.showShort("Send Failed", new Object[0]);
                    }
                } else {
                    ToastUtils.showShort("Send Failed", new Object[0]);
                }
                SendActorReviewActivity.this.hideLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                Intrinsics.checkNotNullParameter(e, "e");
                SendActorReviewActivity.this.hideLoadingView();
                ToastUtils.showShort(Intrinsics.stringPlus("Send Failed:", e.getMessage()), new Object[0]);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: addReview$lambda-7  reason: not valid java name */
    public static final Boolean m655addReview$lambda7(SendActorReviewActivity this$0, File file, String it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(file, "$file");
        Intrinsics.checkNotNullParameter(it, "it");
        return Boolean.valueOf(FileUtils.bitmapToFile(GlideUtils.loadWithCircleFile(this$0, it), file));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: addReview$lambda-8  reason: not valid java name */
    public static final ObservableSource m656addReview$lambda8(File file, UserModel.BBsInfo bBsInfo, String str, Ref.ObjectRef content, SendActorReviewActivity this$0, String str2, Boolean it) {
        Intrinsics.checkNotNullParameter(file, "$file");
        Intrinsics.checkNotNullParameter(content, "$content");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        HttpUploadBBsRequest addBaseParams = new HttpUploadBBsRequest(null, 1, null).addBaseParams("newthread", "image/jpg", file, "img");
        String bbs_uid = bBsInfo.getBbs_uid();
        Intrinsics.checkNotNullExpressionValue(bbs_uid, "bbsInfo.bbs_uid");
        HttpUploadBBsRequest addParam = addBaseParams.addParam("uid", bbs_uid);
        String auth = bBsInfo.getAuth();
        Intrinsics.checkNotNullExpressionValue(auth, "bbsInfo.auth");
        HttpUploadBBsRequest addParam2 = addParam.addParam("auth", auth);
        String authkey = bBsInfo.getAuthkey();
        Intrinsics.checkNotNullExpressionValue(authkey, "bbsInfo.authkey");
        HttpUploadBBsRequest addParam3 = addParam2.addParam("authkey", authkey);
        String author = bBsInfo.getAuthor();
        Intrinsics.checkNotNullExpressionValue(author, "bbsInfo.author");
        HttpUploadBBsRequest addParam4 = addParam3.addParam("username", author).addParam("fid", 41);
        String formhash = bBsInfo.getFormhash();
        Intrinsics.checkNotNullExpressionValue(formhash, "bbsInfo.formhash");
        HttpUploadBBsRequest addParam5 = addParam4.addParam("formhash", formhash);
        if (str == null) {
            str = "";
        }
        HttpUploadBBsRequest addParam6 = addParam5.addParam("title", str);
        String encode = URLEncoder.encode((String) content.element, "UTF-8");
        Intrinsics.checkNotNullExpressionValue(encode, "encode(content, \"UTF-8\")");
        HttpUploadBBsRequest addParam7 = addParam6.addParam(FirebaseAnalytics.Param.CONTENT, encode);
        String str3 = this$0.videoId;
        if (str3 == null) {
            str3 = "";
        }
        HttpUploadBBsRequest addParam8 = addParam7.addParam("videoid", str3).addParam(IjkMediaMeta.IJKM_KEY_TYPE, "4").addParam("unusefiles", "");
        if (str2 == null) {
            str2 = "";
        }
        HttpUploadBBsRequest addParam9 = addParam8.addParam("usefiles", str2).addParam("topicsubmit", "yes").addParam("apiappid", this$0.getBBSApiAPPID()).addParam(FirebaseAnalytics.Param.LOCATION, "");
        String str4 = this$0.actorName;
        if (str4 == null) {
            str4 = "";
        }
        HttpUploadBBsRequest addParam10 = addParam9.addParam("tags", str4).addParam("addfeed", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        String str5 = this$0.actorName;
        if (str5 == null) {
            str5 = "";
        }
        HttpUploadBBsRequest addParam11 = addParam10.addParam("actor_name", str5);
        String str6 = this$0.actorJob;
        return addParam11.addParam("actor_job", str6 != null ? str6 : "").executeUploadObservable2();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: addReview$lambda-9  reason: not valid java name */
    public static final BBsResponseModel m657addReview$lambda9(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return (BBsResponseModel) JSON.parseObject(it, BBsResponseModel.class);
    }

    private final void uploadImages(final String str) {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        Object as = Observable.just(str).map(new Function() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SendActorReviewActivity$i5sJA-wMRJH3QmKsYNnwtXCCXNM
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                List m702uploadImages$lambda12;
                m702uploadImages$lambda12 = SendActorReviewActivity.m702uploadImages$lambda12(str, this, (String) obj);
                return m702uploadImages$lambda12;
            }
        }).subscribeOn(Schedulers.computation()).flatMap(new Function() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SendActorReviewActivity$Q7b2mQBo9crBNdJZ4SwLYApZN44
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                ObservableSource m703uploadImages$lambda13;
                m703uploadImages$lambda13 = SendActorReviewActivity.m703uploadImages$lambda13(SendActorReviewActivity.this, (List) obj);
                return m703uploadImages$lambda13;
            }
        }).map(new Function() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SendActorReviewActivity$LO01alui9WXdbILsrLfEddlQbvE
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                String m704uploadImages$lambda15;
                m704uploadImages$lambda15 = SendActorReviewActivity.m704uploadImages$lambda15(Ref.ObjectRef.this, str, (List) obj);
                return m704uploadImages$lambda15;
            }
        }).observeOn(AndroidSchedulers.mainThread()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(as, "just(html)\n             …bindLifecycleOwner(this))");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, new SendActorReviewActivity$uploadImages$4(this), null, new SendActorReviewActivity$uploadImages$5(this), null, new SendActorReviewActivity$uploadImages$6(this, objectRef), 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: uploadImages$lambda-12  reason: not valid java name */
    public static final List m702uploadImages$lambda12(String html, SendActorReviewActivity this$0, String it) {
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
    public static final ObservableSource m703uploadImages$lambda13(SendActorReviewActivity this$0, List it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.lubanImages = it;
        return this$0.uploadImages(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: uploadImages$lambda-15  reason: not valid java name */
    public static final String m704uploadImages$lambda15(Ref.ObjectRef idList, String html, List it) {
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
        Observable<List<String>> observable = ObservableKt.toObservable(list).flatMap(new Function() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SendActorReviewActivity$kzjBTxICeTEG4eBYxfcaCeM0Px4
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                ObservableSource m705uploadImages$lambda17;
                m705uploadImages$lambda17 = SendActorReviewActivity.m705uploadImages$lambda17(SendActorReviewActivity.this, (File) obj);
                return m705uploadImages$lambda17;
            }
        }).toList().toObservable();
        Intrinsics.checkNotNullExpressionValue(observable, "files.toObservable().fla…          .toObservable()");
        return observable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: uploadImages$lambda-17  reason: not valid java name */
    public static final ObservableSource m705uploadImages$lambda17(SendActorReviewActivity this$0, File it) {
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
        return executeUploadObservable != null ? executeUploadObservable.map($$Lambda$SendActorReviewActivity$izJFuqRi38di2yFcbgkuQW0WWY.INSTANCE) : null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: uploadImages$lambda-17$lambda-16  reason: not valid java name */
    public static final String m706uploadImages$lambda17$lambda16(String json) {
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
        this.actorName = getIntent().getStringExtra("actorName");
        this.actorJob = getIntent().getStringExtra("actorJob");
        this.actorAvatar = getIntent().getStringExtra("actorAvatar");
        this.videoId = getIntent().getStringExtra("actorId");
        boolean z = true;
        this.boxType = getIntent().getIntExtra("boxType", 1);
        this.userModel = App.getUserModel();
        this.bbsInfo = App.getBBsInfo();
        ReviewRecordModel videoReviewRecord = ReviewRecordUtils.Companion.get().getVideoReviewRecord(this.boxType, this.videoId);
        if (videoReviewRecord != null) {
            ((EditText) _$_findCachedViewById(R.id.etTitle)).setText(videoReviewRecord.getTitle());
            ((EditText) _$_findCachedViewById(R.id.etTitle)).setSelection(videoReviewRecord.getTitle().length());
            String title = videoReviewRecord.getTitle();
            if (title == null || title.length() == 0) {
                String content = videoReviewRecord.getContent();
                if (content != null && content.length() != 0) {
                    z = false;
                }
                if (z) {
                    ((EditText) _$_findCachedViewById(R.id.etTitle)).requestFocus();
                    ((EditText) _$_findCachedViewById(R.id.etTitle)).postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SendActorReviewActivity$AIZLoIg4RKw3p0lNwhwT-9rWOtY
                        @Override // java.lang.Runnable
                        public final void run() {
                            SendActorReviewActivity.m660initData$lambda18(SendActorReviewActivity.this);
                        }
                    }, 300L);
                    if (Intrinsics.areEqual(videoReviewRecord.getType(), "html") || videoReviewRecord.getImgMap() == null || videoReviewRecord.getThumbImageMap() == null) {
                        return;
                    }
                    HashMap<String, String> imgMap = videoReviewRecord.getImgMap();
                    Intrinsics.checkNotNullExpressionValue(imgMap, "videoReviewRecord.imgMap");
                    this.imageMap = imgMap;
                    HashMap<String, String> thumbImageMap = videoReviewRecord.getThumbImageMap();
                    Intrinsics.checkNotNullExpressionValue(thumbImageMap, "videoReviewRecord.thumbImageMap");
                    this.thumbImageMap = thumbImageMap;
                    return;
                }
            }
            ((MyRichEditor) _$_findCachedViewById(R.id.richEditor)).requestFocus();
            ((MyRichEditor) _$_findCachedViewById(R.id.richEditor)).focusEditor();
            ((MyRichEditor) _$_findCachedViewById(R.id.richEditor)).postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SendActorReviewActivity$eiH1tGzj3Gm0wVhiJd1rmMnceMs
                @Override // java.lang.Runnable
                public final void run() {
                    SendActorReviewActivity.m661initData$lambda19(SendActorReviewActivity.this);
                }
            }, 300L);
            if (videoReviewRecord.getContent() == null) {
                videoReviewRecord.setContent("");
            }
            ((MyRichEditor) _$_findCachedViewById(R.id.richEditor)).insertHtml(videoReviewRecord.getContent());
            if (Intrinsics.areEqual(videoReviewRecord.getType(), "html")) {
                return;
            }
            return;
        }
        ((EditText) _$_findCachedViewById(R.id.etTitle)).requestFocus();
        ((EditText) _$_findCachedViewById(R.id.etTitle)).postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SendActorReviewActivity$r4CMFSjOY6mDKSyTsqNWZwGSheU
            @Override // java.lang.Runnable
            public final void run() {
                SendActorReviewActivity.m662initData$lambda20(SendActorReviewActivity.this);
            }
        }, 300L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-18  reason: not valid java name */
    public static final void m660initData$lambda18(SendActorReviewActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        InputMethodUtils.showSoftInput((EditText) this$0._$_findCachedViewById(R.id.etTitle));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-19  reason: not valid java name */
    public static final void m661initData$lambda19(SendActorReviewActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        InputMethodUtils.showSoftInput((MyRichEditor) this$0._$_findCachedViewById(R.id.richEditor));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-20  reason: not valid java name */
    public static final void m662initData$lambda20(SendActorReviewActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        InputMethodUtils.showSoftInput((EditText) this$0._$_findCachedViewById(R.id.etTitle));
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initView() {
        ((TextView) _$_findCachedViewById(R.id.tv_title)).setText("New Thread");
        ((ImageView) _$_findCachedViewById(R.id.iv_right)).setImageResource(R.mipmap.ic_send_msg);
        ImageView iv_right = (ImageView) _$_findCachedViewById(R.id.iv_right);
        Intrinsics.checkNotNullExpressionValue(iv_right, "iv_right");
        CommonExtKt.visible(iv_right);
        ((FrameLayout) _$_findCachedViewById(R.id.frameLayout)).setBackgroundColor(CommonExtKt.colorInt((Context) this, (int) R.color.color_main));
        initRichEditor();
        ((MyRichEditor) _$_findCachedViewById(R.id.richEditor)).setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SendActorReviewActivity$KPmEdEPsuSsFRuc-tW8XaySSF2E
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                SendActorReviewActivity.m670initView$lambda21(SendActorReviewActivity.this, view, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-21  reason: not valid java name */
    public static final void m670initView$lambda21(SendActorReviewActivity this$0, View view, boolean z) {
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
        ((TextView) inflate.findViewById(R.id.tvSearch)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SendActorReviewActivity$dYmY4zbs6wo0-K5Ob0uqlG_OhP4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SendActorReviewActivity.m689showAddVideoPop$lambda22(SendActorReviewActivity.this, view);
            }
        });
        ((TextView) inflate.findViewById(R.id.tvHistory)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SendActorReviewActivity$_E6CvdldnUyLBYNMCmDI95irTUs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SendActorReviewActivity.m690showAddVideoPop$lambda23(SendActorReviewActivity.this, view);
            }
        });
        ((TextView) inflate.findViewById(R.id.tvFavorite)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SendActorReviewActivity$EgCXuHytoJ_vR7Pv80pNYTjLV6g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SendActorReviewActivity.m691showAddVideoPop$lambda24(SendActorReviewActivity.this, view);
            }
        });
        ((TextView) inflate.findViewById(R.id.tvMovieLists)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SendActorReviewActivity$HaeZGYvnM9Ny-80Ay4EZwOixPDw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SendActorReviewActivity.m692showAddVideoPop$lambda25(SendActorReviewActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showAddVideoPop$lambda-22  reason: not valid java name */
    public static final void m689showAddVideoPop$lambda22(SendActorReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AltVideosActivity.Companion.start(this$0, 1, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showAddVideoPop$lambda-23  reason: not valid java name */
    public static final void m690showAddVideoPop$lambda23(SendActorReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AltVideosActivity.Companion.start(this$0, 2, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showAddVideoPop$lambda-24  reason: not valid java name */
    public static final void m691showAddVideoPop$lambda24(SendActorReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AltVideosActivity.Companion.start(this$0, 3, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showAddVideoPop$lambda-25  reason: not valid java name */
    public static final void m692showAddVideoPop$lambda25(SendActorReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AltMovieListActivity.Companion.start(this$0);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onMovieListSelected(final MovieListSelectedEvent item) {
        Intrinsics.checkNotNullParameter(item, "item");
        ((MyRichEditor) _$_findCachedViewById(R.id.richEditor)).postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SendActorReviewActivity$OV34eJGIkW2XahceS-_JQkAWoC0
            @Override // java.lang.Runnable
            public final void run() {
                SendActorReviewActivity.m687onMovieListSelected$lambda26(SendActorReviewActivity.this, item);
            }
        }, 300L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onMovieListSelected$lambda-26  reason: not valid java name */
    public static final void m687onMovieListSelected$lambda26(SendActorReviewActivity this$0, MovieListSelectedEvent item) {
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
        ((ImageButton) inflate.findViewById(R.id.action_text_bold)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SendActorReviewActivity$Wn8GXVKXPAsPUuUcP7yD57uf4Q4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SendActorReviewActivity.m693showFontPop$lambda27(SendActorReviewActivity.this, view);
            }
        });
        ((ImageButton) inflate.findViewById(R.id.action_text_italic)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SendActorReviewActivity$MgDl9NEVMXgEA9O6rn_mgsq33ig
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SendActorReviewActivity.m694showFontPop$lambda28(SendActorReviewActivity.this, view);
            }
        });
        ((ImageButton) inflate.findViewById(R.id.action_text_underline)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SendActorReviewActivity$4FRHuvBkVeQVAOdKpSOrcGbJnr8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SendActorReviewActivity.m695showFontPop$lambda29(SendActorReviewActivity.this, view);
            }
        });
        ((ImageButton) inflate.findViewById(R.id.action_text_h1)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SendActorReviewActivity$VqUwsW1NJj1HgRQ9DbGJ5g-JUkg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SendActorReviewActivity.m696showFontPop$lambda30(SendActorReviewActivity.this, view);
            }
        });
        ((ImageButton) inflate.findViewById(R.id.action_text_h2)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SendActorReviewActivity$k4kgP59mI7ultX5IpdJUqPxiaKk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SendActorReviewActivity.m697showFontPop$lambda31(SendActorReviewActivity.this, view);
            }
        });
        ((ImageButton) inflate.findViewById(R.id.action_text_h3)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SendActorReviewActivity$J6JopAc_NZWf1XNG9UUn62DPpG0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SendActorReviewActivity.m698showFontPop$lambda32(SendActorReviewActivity.this, view);
            }
        });
        ((ImageButton) inflate.findViewById(R.id.action_text_left)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SendActorReviewActivity$L4rgmXz4NACCm2QkrXl236TV1dQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SendActorReviewActivity.m699showFontPop$lambda33(SendActorReviewActivity.this, view);
            }
        });
        ((ImageButton) inflate.findViewById(R.id.action_text_center)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SendActorReviewActivity$48NG-CdLI_zYCDvoS-YvUWSxkVQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SendActorReviewActivity.m700showFontPop$lambda34(SendActorReviewActivity.this, view);
            }
        });
        ((ImageButton) inflate.findViewById(R.id.action_text_right)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SendActorReviewActivity$Kp4x-FUGhyK9fp5v_7NPp9TuTMI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SendActorReviewActivity.m701showFontPop$lambda35(SendActorReviewActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showFontPop$lambda-27  reason: not valid java name */
    public static final void m693showFontPop$lambda27(SendActorReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((MyRichEditor) this$0._$_findCachedViewById(R.id.richEditor)).setBold();
        this$0.closePop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showFontPop$lambda-28  reason: not valid java name */
    public static final void m694showFontPop$lambda28(SendActorReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((MyRichEditor) this$0._$_findCachedViewById(R.id.richEditor)).setItalic();
        this$0.closePop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showFontPop$lambda-29  reason: not valid java name */
    public static final void m695showFontPop$lambda29(SendActorReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((MyRichEditor) this$0._$_findCachedViewById(R.id.richEditor)).setUnderline();
        this$0.closePop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showFontPop$lambda-30  reason: not valid java name */
    public static final void m696showFontPop$lambda30(SendActorReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((MyRichEditor) this$0._$_findCachedViewById(R.id.richEditor)).setHeading(1);
        this$0.closePop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showFontPop$lambda-31  reason: not valid java name */
    public static final void m697showFontPop$lambda31(SendActorReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((MyRichEditor) this$0._$_findCachedViewById(R.id.richEditor)).setHeading(2);
        this$0.closePop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showFontPop$lambda-32  reason: not valid java name */
    public static final void m698showFontPop$lambda32(SendActorReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((MyRichEditor) this$0._$_findCachedViewById(R.id.richEditor)).setHeading(3);
        this$0.closePop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showFontPop$lambda-33  reason: not valid java name */
    public static final void m699showFontPop$lambda33(SendActorReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((MyRichEditor) this$0._$_findCachedViewById(R.id.richEditor)).setAlignLeft();
        this$0.closePop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showFontPop$lambda-34  reason: not valid java name */
    public static final void m700showFontPop$lambda34(SendActorReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((MyRichEditor) this$0._$_findCachedViewById(R.id.richEditor)).setAlignCenter();
        this$0.closePop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showFontPop$lambda-35  reason: not valid java name */
    public static final void m701showFontPop$lambda35(SendActorReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((MyRichEditor) this$0._$_findCachedViewById(R.id.richEditor)).setAlignRight();
        this$0.closePop();
    }

    private final void initRichEditor() {
        int dp2Px = CommonExtKt.dp2Px(5);
        ((MyRichEditor) _$_findCachedViewById(R.id.richEditor)).setPadding(dp2Px, dp2Px, dp2Px, dp2Px);
        ((MyRichEditor) _$_findCachedViewById(R.id.richEditor)).setPlaceholder("Add Review");
        ((MyRichEditor) _$_findCachedViewById(R.id.richEditor)).setEditorBackgroundColor(CommonExtKt.colorInt((Context) this, (int) R.color.color_main));
        ((MyRichEditor) _$_findCachedViewById(R.id.richEditor)).setEditorFontColor(-1);
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
        Observable<R> compose = Http.getService().GetAltMovieListImage(API.BBS_URL, "createMovielistImg", lid, movieListItem.getName(), movieListItem.getUsername(), str3, movieListItem.getAvatar()).compose(RxUtils.rxTranslate2Bean(HashMap.class));
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
        RxSubscribersKt.transform(compose, this).subscribe(new Consumer() { // from class: com.movieboxpro.android.view.activity.review.SendActorReviewActivity$getAltMovieListImg$$inlined$subscribeToBean$default$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(T it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                HashMap hashMap = (HashMap) it;
                SendActorReviewActivity.this.hideLoadingView();
                ((MyRichEditor) SendActorReviewActivity.this._$_findCachedViewById(R.id.richEditor)).insertHtml("<div class=\"img\"><img src=\"" + hashMap.get("url") + "\"  videoid=\"" + hashMap.get("id") + "\"  type=\"" + hashMap.get(IjkMediaMeta.IJKM_KEY_TYPE) + "\" alt=\"picvision\"  style= \"margin-top:10px;max-width:100%;\"/></div><br>");
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.SendActorReviewActivity$getAltMovieListImg$$inlined$subscribeToBean$default$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                ApiException handleException = ApiException.handleException(th);
                Intrinsics.checkNotNullExpressionValue(handleException, "handleException(it)");
                SendActorReviewActivity.this.hideLoadingView();
                ToastUtils.showShort(Intrinsics.stringPlus("Load failed:", handleException.getMessage()), new Object[0]);
                if (th instanceof ServerException) {
                    ServerException serverException = (ServerException) th;
                }
            }
        }, new Action() { // from class: com.movieboxpro.android.view.activity.review.SendActorReviewActivity$getAltMovieListImg$$inlined$subscribeToBean$default$3
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.SendActorReviewActivity$getAltMovieListImg$$inlined$subscribeToBean$default$4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                SendActorReviewActivity.this.showLoadingView();
            }
        });
    }

    private final void getAltVideos(ArrayList<NormalFilmModel> arrayList) {
        Observable flatMap = ObservableKt.toObservable(arrayList).flatMap($$Lambda$SendActorReviewActivity$VQoAZIiLq3CorBunHBmA2QQsg8.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(flatMap, "videos.toObservable()\n  …t.year)\n                }");
        Observable compose = flatMap.compose(RxUtils.rxTranslate2Bean(HashMap.class));
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
        Observable observable = compose.map($$Lambda$SendActorReviewActivity$errpSDyGjcrXjuI2UfFAPi51SwQ.INSTANCE).toList().toObservable();
        Intrinsics.checkNotNullExpressionValue(observable, "videos.toObservable()\n  …          .toObservable()");
        RxSubscribersKt.transform(observable, this).subscribe(new Consumer() { // from class: com.movieboxpro.android.view.activity.review.SendActorReviewActivity$getAltVideos$$inlined$transformSubscribe$default$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(T it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                SendActorReviewActivity.this.hideLoadingView();
                for (Triple triple : (List) it) {
                    ((MyRichEditor) SendActorReviewActivity.this._$_findCachedViewById(R.id.richEditor)).insertHtml("<div class=\"img\"><img src=\"" + triple.getFirst() + "\"  videoid=\"" + triple.getSecond() + "\"  type=\"" + triple.getThird() + "\" alt=\"picvision\"  style= \"margin-top:10px;max-width:100%;\"/></div><br>");
                }
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.SendActorReviewActivity$getAltVideos$$inlined$transformSubscribe$default$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                ApiException handleException = ApiException.handleException(th);
                Intrinsics.checkNotNullExpressionValue(handleException, "handleException(it)");
                SendActorReviewActivity.this.hideLoadingView();
                ToastUtils.showShort(Intrinsics.stringPlus("Load failed:", handleException.getMessage()), new Object[0]);
                if (th instanceof ServerException) {
                    ServerException serverException = (ServerException) th;
                }
            }
        }, new Action() { // from class: com.movieboxpro.android.view.activity.review.SendActorReviewActivity$getAltVideos$$inlined$transformSubscribe$default$3
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.SendActorReviewActivity$getAltVideos$$inlined$transformSubscribe$default$4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                SendActorReviewActivity.this.showLoadingView();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getAltVideos$lambda-40  reason: not valid java name */
    public static final ObservableSource m658getAltVideos$lambda40(NormalFilmModel it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (it.getImdb_rating() == null) {
            it.setImdb_rating("0");
        }
        return Http.getService().GetAltVideos(API.BBS_URL, "createVideoImage", String.valueOf(it.getId()), it.getTitle(), it.getPoster(), it.getImdb_rating(), App.deviceLang, it.getBox_type(), it.getYear());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getAltVideos$lambda-41  reason: not valid java name */
    public static final Triple m659getAltVideos$lambda41(HashMap it) {
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
        Observable map = Observable.fromIterable(list).map(new Function() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$SendActorReviewActivity$hkC8bN62XTcVcMYteRZRiJq0QnM
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                Pair m688processSelectedImage$lambda46;
                m688processSelectedImage$lambda46 = SendActorReviewActivity.m688processSelectedImage$lambda46(SendActorReviewActivity.this, (Photo) obj);
                return m688processSelectedImage$lambda46;
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "fromIterable(imagePath)\n…      }\n                }");
        RxSubscribersKt.transformCompute(map, this).subscribe(new Consumer() { // from class: com.movieboxpro.android.view.activity.review.SendActorReviewActivity$processSelectedImage$$inlined$transformSubscribeCompute$default$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(T it) {
                HashMap hashMap;
                HashMap hashMap2;
                Intrinsics.checkNotNullExpressionValue(it, "it");
                Pair pair = (Pair) it;
                SendActorReviewActivity.this.hideLoadingView();
                ((MyRichEditor) SendActorReviewActivity.this._$_findCachedViewById(R.id.richEditor)).insertImage((String) pair.getFirst(), "picvision", "margin-top:10px;max-width:50%;");
                hashMap = SendActorReviewActivity.this.imageMap;
                String name = new File((String) pair.getSecond()).getName();
                Intrinsics.checkNotNullExpressionValue(name, "File(pair.second).name");
                Object second = pair.getSecond();
                Intrinsics.checkNotNullExpressionValue(second, "pair.second");
                hashMap.put(name, second);
                hashMap2 = SendActorReviewActivity.this.thumbImageMap;
                Object first = pair.getFirst();
                Intrinsics.checkNotNullExpressionValue(first, "pair.first");
                String name2 = new File((String) pair.getSecond()).getName();
                Intrinsics.checkNotNullExpressionValue(name2, "File(pair.second).name");
                hashMap2.put(first, name2);
                CommonExtKt.logD(SendActorReviewActivity.this, (String) pair.getFirst());
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.SendActorReviewActivity$processSelectedImage$$inlined$transformSubscribeCompute$default$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                ApiException handleException = ApiException.handleException(th);
                Intrinsics.checkNotNullExpressionValue(handleException, "handleException(it)");
                SendActorReviewActivity.this.hideLoadingView();
                ToastUtils.showShort(Intrinsics.stringPlus("Load failed:", handleException.getMessage()), new Object[0]);
                if (th instanceof ServerException) {
                    ServerException serverException = (ServerException) th;
                }
            }
        }, new Action() { // from class: com.movieboxpro.android.view.activity.review.SendActorReviewActivity$processSelectedImage$$inlined$transformSubscribeCompute$default$3
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.SendActorReviewActivity$processSelectedImage$$inlined$transformSubscribeCompute$default$4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                SendActorReviewActivity.this.showLoadingView();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: processSelectedImage$lambda-46  reason: not valid java name */
    public static final Pair m688processSelectedImage$lambda46(SendActorReviewActivity this$0, Photo it) {
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
            String obj = ((EditText) _$_findCachedViewById(R.id.etTitle)).getText().toString();
            String html = ((MyRichEditor) _$_findCachedViewById(R.id.richEditor)).getHtml();
            if (this.imageMap.isEmpty()) {
                ReviewRecordUtils.Companion.get().saveVideoReviewRecord(this.boxType, this.videoId, obj, html, "text", null, null);
            } else {
                ReviewRecordUtils.Companion.get().saveVideoReviewRecord(this.boxType, this.videoId, obj, html, "html", this.imageMap, this.thumbImageMap);
            }
        }
        super.onDestroy();
    }

    /* compiled from: SendActorReviewActivity.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JF\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0011\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/SendActorReviewActivity$Companion;", "", "()V", "REQUEST_CODE_CHOOSE", "", "REQUEST_CODE_MOVIE_LISTS", "REQUEST_CODE_VIDEO", TtmlNode.START, "", "context", "Landroid/app/Activity;", "text", "", "actorId", "actorName", "actor_job", "actorAvatar", "requestCode", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Activity activity, String text, String actorId, String str, String str2, String str3, int i) {
            Intrinsics.checkNotNullParameter(text, "text");
            Intrinsics.checkNotNullParameter(actorId, "actorId");
            Intent intent = new Intent(activity, SendActorReviewActivity.class);
            intent.putExtra("text", text);
            intent.putExtra("actorId", actorId);
            intent.putExtra("actorName", str);
            intent.putExtra("actorJob", str2);
            intent.putExtra("actorAvatar", str3);
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
