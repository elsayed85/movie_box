package com.movieboxpro.android.view.activity.review;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.lxj.xpopup.XPopup;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.mvp.BaseMvpActivity;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.APIService;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.ServerException;
import com.movieboxpro.android.listener.ImageClickJSBridge;
import com.movieboxpro.android.listener.OnReplyClickListener;
import com.movieboxpro.android.model.BBsResponseModel;
import com.movieboxpro.android.model.ReviewDetailPageModel;
import com.movieboxpro.android.model.ReviewModel;
import com.movieboxpro.android.model.ReviewRecordModel;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.CheckUtils;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.InputMethodUtils;
import com.movieboxpro.android.utils.ReviewRecordUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.ShellUtil;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.utils.XpopImageLoader;
import com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity;
import com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity;
import com.movieboxpro.android.view.activity.movielist.MovieListDetailActivity;
import com.movieboxpro.android.view.activity.review.EditReviewActivity;
import com.movieboxpro.android.view.activity.review.MovieListReviewActivity;
import com.movieboxpro.android.view.activity.review.MovieListReviewContract;
import com.movieboxpro.android.view.activity.review.UserInfoActivity;
import com.movieboxpro.android.view.dialog.BottomSheetListDialog;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import org.eclipse.jetty.http.MimeTypes;
/* compiled from: MovieListReviewActivity.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 42\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0003456B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u001c\u001a\u00020\u0002H\u0014J\b\u0010\u001d\u001a\u00020\fH\u0014J\b\u0010\u001e\u001a\u00020\fH\u0014J\b\u0010\u001f\u001a\u00020 H\u0002J\b\u0010!\u001a\u00020 H\u0014J\b\u0010\"\u001a\u00020 H\u0014J\b\u0010#\u001a\u00020 H\u0014J\b\u0010$\u001a\u00020\u0012H\u0014J\"\u0010%\u001a\u00020 2\u0006\u0010&\u001a\u00020\f2\u0006\u0010'\u001a\u00020\f2\b\u0010(\u001a\u0004\u0018\u00010)H\u0014J\b\u0010*\u001a\u00020 H\u0014J\"\u0010+\u001a\u00020 2\b\u0010,\u001a\u0004\u0018\u00010\n2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\fH\u0002J\b\u00100\u001a\u00020 H\u0014J\b\u00101\u001a\u00020 H\u0016J\b\u00102\u001a\u00020 H\u0002J\b\u00103\u001a\u00020 H\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00067"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/MovieListReviewActivity;", "Lcom/movieboxpro/android/base/mvp/BaseMvpActivity;", "Lcom/movieboxpro/android/view/activity/review/MovieListReviewPresenter;", "Lcom/movieboxpro/android/view/activity/review/MovieListReviewContract$View;", "()V", "bbsInfo", "Lcom/movieboxpro/android/model/user/UserModel$BBsInfo;", "currPid", "", "currReviewModel", "Lcom/movieboxpro/android/model/ReviewModel;", "editPosition", "", "email", "fragment", "Lcom/movieboxpro/android/view/activity/review/MovieListReviewActivity$ReviewFragment;", "id", "isReplyTo", "", "listAvatar", "listDesc", "listImg", "listTitle", "popupMenu", "Landroid/widget/PopupMenu;", "topReviewModel", "userId", "username", "bindPresenter", "getLayoutResId", "getStatusColor", "goFullReview", "", "initData", "initListener", "initView", "isNeedLoadData", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onDestroy", "openPopMenu", "model", "view", "Landroid/view/View;", "position", "requestData", "reviewSuccess", "saveContent", "setRecordReview", "Companion", "ReviewFragment", "SetReviewInfoListener", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MovieListReviewActivity extends BaseMvpActivity<MovieListReviewPresenter> implements MovieListReviewContract.View {
    public static final Companion Companion = new Companion(null);
    private UserModel.BBsInfo bbsInfo;
    private ReviewModel currReviewModel;
    private ReviewFragment fragment;
    private boolean isReplyTo;
    private PopupMenu popupMenu;
    private ReviewModel topReviewModel;
    private String userId;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String id = "";
    private String currPid = "";
    private int editPosition = -1;
    private String listTitle = "";
    private String listImg = "";
    private String listDesc = "";
    private String username = "";
    private String listAvatar = "";
    private String email = "";

    /* compiled from: MovieListReviewActivity.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH&¨\u0006\r"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/MovieListReviewActivity$SetReviewInfoListener;", "", "setOriginReviewModel", "", "model", "Lcom/movieboxpro/android/model/ReviewModel;", "setPageInfo", "totalPage", "", "currPage", "setReviewCount", "count", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface SetReviewInfoListener {
        void setOriginReviewModel(ReviewModel reviewModel);

        void setPageInfo(int i, int i2);

        void setReviewCount(String str);
    }

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

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected int getLayoutResId() {
        return R.layout.activity_review_detail;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected int getStatusColor() {
        return R.color.color_main;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected boolean isNeedLoadData() {
        return false;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void requestData() {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initView() {
        ((FrameLayout) _$_findCachedViewById(R.id.frameLayout)).setBackgroundColor(CommonExtKt.colorInt((Context) this, (int) R.color.color_main));
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initData() {
        this.id = getIntent().getStringExtra("id");
        this.listTitle = getIntent().getStringExtra("title");
        this.listDesc = getIntent().getStringExtra("listDesc");
        this.listImg = getIntent().getStringExtra("listImg");
        this.username = getIntent().getStringExtra("username");
        this.email = getIntent().getStringExtra("email");
        this.userId = getIntent().getStringExtra("userId");
        this.listAvatar = getIntent().getStringExtra("avatar");
        Serializable serializableExtra = getIntent().getSerializableExtra("bbsInfo");
        ReviewFragment reviewFragment = null;
        this.bbsInfo = serializableExtra instanceof UserModel.BBsInfo ? (UserModel.BBsInfo) serializableExtra : null;
        ((TextView) _$_findCachedViewById(R.id.tv_title)).setText(getIntent().getStringExtra("title"));
        this.fragment = ReviewFragment.Companion.newInstance(this.id, getIntent().getIntExtra("page", 0), getIntent().getIntExtra("position", 0), this.listImg, this.listTitle, this.username);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        ReviewFragment reviewFragment2 = this.fragment;
        if (reviewFragment2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
            reviewFragment2 = null;
        }
        FragmentUtils.add(supportFragmentManager, reviewFragment2, (int) R.id.reviewContainer);
        ReviewFragment reviewFragment3 = this.fragment;
        if (reviewFragment3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
            reviewFragment3 = null;
        }
        reviewFragment3.setReplyListener(new MovieListReviewActivity$initData$1(this));
        ReviewFragment reviewFragment4 = this.fragment;
        if (reviewFragment4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
        } else {
            reviewFragment = reviewFragment4;
        }
        reviewFragment.setReviewCountListener(new SetReviewInfoListener() { // from class: com.movieboxpro.android.view.activity.review.MovieListReviewActivity$initData$2
            @Override // com.movieboxpro.android.view.activity.review.MovieListReviewActivity.SetReviewInfoListener
            public void setReviewCount(String count) {
                Intrinsics.checkNotNullParameter(count, "count");
            }

            @Override // com.movieboxpro.android.view.activity.review.MovieListReviewActivity.SetReviewInfoListener
            public void setOriginReviewModel(ReviewModel reviewModel) {
                MovieListReviewActivity.this.topReviewModel = reviewModel;
            }

            @Override // com.movieboxpro.android.view.activity.review.MovieListReviewActivity.SetReviewInfoListener
            public void setPageInfo(int i, int i2) {
                if (i == 0) {
                    ImageView ivPrePage = (ImageView) MovieListReviewActivity.this._$_findCachedViewById(R.id.ivPrePage);
                    Intrinsics.checkNotNullExpressionValue(ivPrePage, "ivPrePage");
                    CommonExtKt.invisible(ivPrePage);
                    ImageView ivNextPage = (ImageView) MovieListReviewActivity.this._$_findCachedViewById(R.id.ivNextPage);
                    Intrinsics.checkNotNullExpressionValue(ivNextPage, "ivNextPage");
                    CommonExtKt.invisible(ivNextPage);
                    TextView tvPageInfo = (TextView) MovieListReviewActivity.this._$_findCachedViewById(R.id.tvPageInfo);
                    Intrinsics.checkNotNullExpressionValue(tvPageInfo, "tvPageInfo");
                    CommonExtKt.invisible(tvPageInfo);
                } else if (i == 1) {
                    ((ImageView) MovieListReviewActivity.this._$_findCachedViewById(R.id.ivPrePage)).setEnabled(false);
                    ((ImageView) MovieListReviewActivity.this._$_findCachedViewById(R.id.ivNextPage)).setEnabled(false);
                    TextView tvPageInfo2 = (TextView) MovieListReviewActivity.this._$_findCachedViewById(R.id.tvPageInfo);
                    Intrinsics.checkNotNullExpressionValue(tvPageInfo2, "tvPageInfo");
                    CommonExtKt.visible(tvPageInfo2);
                    ImageView ivPrePage2 = (ImageView) MovieListReviewActivity.this._$_findCachedViewById(R.id.ivPrePage);
                    Intrinsics.checkNotNullExpressionValue(ivPrePage2, "ivPrePage");
                    CommonExtKt.visible(ivPrePage2);
                    ImageView ivNextPage2 = (ImageView) MovieListReviewActivity.this._$_findCachedViewById(R.id.ivNextPage);
                    Intrinsics.checkNotNullExpressionValue(ivNextPage2, "ivNextPage");
                    CommonExtKt.visible(ivNextPage2);
                } else if (i2 == 1) {
                    ((ImageView) MovieListReviewActivity.this._$_findCachedViewById(R.id.ivPrePage)).setEnabled(false);
                    ((ImageView) MovieListReviewActivity.this._$_findCachedViewById(R.id.ivNextPage)).setEnabled(true);
                    TextView tvPageInfo3 = (TextView) MovieListReviewActivity.this._$_findCachedViewById(R.id.tvPageInfo);
                    Intrinsics.checkNotNullExpressionValue(tvPageInfo3, "tvPageInfo");
                    CommonExtKt.visible(tvPageInfo3);
                    ImageView ivPrePage3 = (ImageView) MovieListReviewActivity.this._$_findCachedViewById(R.id.ivPrePage);
                    Intrinsics.checkNotNullExpressionValue(ivPrePage3, "ivPrePage");
                    CommonExtKt.visible(ivPrePage3);
                    ImageView ivNextPage3 = (ImageView) MovieListReviewActivity.this._$_findCachedViewById(R.id.ivNextPage);
                    Intrinsics.checkNotNullExpressionValue(ivNextPage3, "ivNextPage");
                    CommonExtKt.visible(ivNextPage3);
                } else if (i2 == i) {
                    ((ImageView) MovieListReviewActivity.this._$_findCachedViewById(R.id.ivPrePage)).setEnabled(true);
                    ((ImageView) MovieListReviewActivity.this._$_findCachedViewById(R.id.ivNextPage)).setEnabled(false);
                    TextView tvPageInfo4 = (TextView) MovieListReviewActivity.this._$_findCachedViewById(R.id.tvPageInfo);
                    Intrinsics.checkNotNullExpressionValue(tvPageInfo4, "tvPageInfo");
                    CommonExtKt.visible(tvPageInfo4);
                    ImageView ivPrePage4 = (ImageView) MovieListReviewActivity.this._$_findCachedViewById(R.id.ivPrePage);
                    Intrinsics.checkNotNullExpressionValue(ivPrePage4, "ivPrePage");
                    CommonExtKt.visible(ivPrePage4);
                    ImageView ivNextPage4 = (ImageView) MovieListReviewActivity.this._$_findCachedViewById(R.id.ivNextPage);
                    Intrinsics.checkNotNullExpressionValue(ivNextPage4, "ivNextPage");
                    CommonExtKt.visible(ivNextPage4);
                } else {
                    ((ImageView) MovieListReviewActivity.this._$_findCachedViewById(R.id.ivPrePage)).setEnabled(true);
                    ((ImageView) MovieListReviewActivity.this._$_findCachedViewById(R.id.ivNextPage)).setEnabled(true);
                    TextView tvPageInfo5 = (TextView) MovieListReviewActivity.this._$_findCachedViewById(R.id.tvPageInfo);
                    Intrinsics.checkNotNullExpressionValue(tvPageInfo5, "tvPageInfo");
                    CommonExtKt.visible(tvPageInfo5);
                    ImageView ivPrePage5 = (ImageView) MovieListReviewActivity.this._$_findCachedViewById(R.id.ivPrePage);
                    Intrinsics.checkNotNullExpressionValue(ivPrePage5, "ivPrePage");
                    CommonExtKt.visible(ivPrePage5);
                    ImageView ivNextPage5 = (ImageView) MovieListReviewActivity.this._$_findCachedViewById(R.id.ivNextPage);
                    Intrinsics.checkNotNullExpressionValue(ivNextPage5, "ivNextPage");
                    CommonExtKt.visible(ivNextPage5);
                }
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("%s/%s", Arrays.copyOf(new Object[]{Integer.valueOf(i2), Integer.valueOf(i)}, 2));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                ((TextView) MovieListReviewActivity.this._$_findCachedViewById(R.id.tvPageInfo)).setText(format);
            }
        });
        if (getIntent().getBooleanExtra("review", false)) {
            ((EditText) _$_findCachedViewById(R.id.etContent)).postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$MovieListReviewActivity$37axZhp08bOMMno8fhVX6-8p-X0
                @Override // java.lang.Runnable
                public final void run() {
                    MovieListReviewActivity.m555initData$lambda0(MovieListReviewActivity.this);
                }
            }, 300L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-0  reason: not valid java name */
    public static final void m555initData$lambda0(MovieListReviewActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.isReplyTo = false;
        ((EditText) this$0._$_findCachedViewById(R.id.etContent)).setText("");
        ((EditText) this$0._$_findCachedViewById(R.id.etContent)).requestFocus();
        InputMethodUtils.showSoftInput((EditText) this$0._$_findCachedViewById(R.id.etContent));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void openPopMenu(final ReviewModel reviewModel, View view, final int i) {
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
        popupMenu3.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$MovieListReviewActivity$WKVTHfmKCogNG_xktq9JY6qIWO4
            @Override // android.widget.PopupMenu.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                boolean m570openPopMenu$lambda2;
                m570openPopMenu$lambda2 = MovieListReviewActivity.m570openPopMenu$lambda2(ReviewModel.this, this, i, bbs_uid, menuItem);
                return m570openPopMenu$lambda2;
            }
        });
        PopupMenu popupMenu4 = this.popupMenu;
        Intrinsics.checkNotNull(popupMenu4);
        popupMenu4.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: openPopMenu$lambda-2  reason: not valid java name */
    public static final boolean m570openPopMenu$lambda2(ReviewModel reviewModel, MovieListReviewActivity this$0, int i, String str, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int itemId = menuItem.getItemId();
        if (itemId != R.id.edit) {
            if (itemId != R.id.report) {
                return false;
            }
            if (App.getBBsInfo() != null) {
                ReportReviewActivity.Companion.start(this$0, reviewModel == null ? null : reviewModel.getPid(), reviewModel == null ? null : reviewModel.getTid(), reviewModel != null ? reviewModel.getFid() : null);
                return true;
            }
            PandaForumAuthorizeActivity.Companion.start(this$0);
            return true;
        } else if (reviewModel == null) {
            return true;
        } else {
            if (i == 0) {
                EditReviewActivity.Companion companion = EditReviewActivity.Companion;
                String pid = reviewModel.getPid();
                Intrinsics.checkNotNullExpressionValue(pid, "pid");
                companion.start(this$0, pid, 3, false, str);
            } else {
                EditReviewActivity.Companion companion2 = EditReviewActivity.Companion;
                String pid2 = reviewModel.getPid();
                Intrinsics.checkNotNullExpressionValue(pid2, "pid");
                companion2.start(this$0, pid2, 3, true, str);
            }
            this$0.editPosition = i;
            return true;
        }
    }

    private final void saveContent() {
        ReviewRecordUtils.Companion.get().saveReviewRecord(this.currPid, "text", ((EditText) _$_findCachedViewById(R.id.etContent)).getText().toString());
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initListener() {
        InputMethodUtils.registerSoftInputChangedListener(this, new InputMethodUtils.OnSoftInputChangedListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$MovieListReviewActivity$q09EkgvitwW0W6fAXVObU2E5IIc
            @Override // com.movieboxpro.android.utils.InputMethodUtils.OnSoftInputChangedListener
            public final void onSoftInputChanged(int i) {
                MovieListReviewActivity.m558initListener$lambda3(MovieListReviewActivity.this, i);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivFullReply)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$MovieListReviewActivity$K4Vb0rF8veuJz0BUgFVFRe1vUZY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MovieListReviewActivity.m559initListener$lambda4(MovieListReviewActivity.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivSend)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$MovieListReviewActivity$IuEvKz9yIqBoDIxs7WBaPYbgLX0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MovieListReviewActivity.m560initListener$lambda5(MovieListReviewActivity.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.llReview)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$MovieListReviewActivity$Ct2DBSDfpUIHC46NenffRkGPDfI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MovieListReviewActivity.m561initListener$lambda6(MovieListReviewActivity.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.ll_back)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$MovieListReviewActivity$L5YYfUP71PmiQbtcOSN7bX2W9wc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MovieListReviewActivity.m562initListener$lambda7(MovieListReviewActivity.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivPrePage)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$MovieListReviewActivity$f1YDR9yFYEeR2esnM7LMxSI-XRs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MovieListReviewActivity.m563initListener$lambda8(MovieListReviewActivity.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivNextPage)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$MovieListReviewActivity$bx1Sh0Q6EV70b_MYIQVcExfIWQs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MovieListReviewActivity.m564initListener$lambda9(MovieListReviewActivity.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tvPageInfo)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$MovieListReviewActivity$MLVBDeWryL-i_6iQPRLrB9INIcw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MovieListReviewActivity.m556initListener$lambda11(MovieListReviewActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-3  reason: not valid java name */
    public static final void m558initListener$lambda3(MovieListReviewActivity this$0, int i) {
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
    /* renamed from: initListener$lambda-4  reason: not valid java name */
    public static final void m559initListener$lambda4(MovieListReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.goFullReview();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-5  reason: not valid java name */
    public static final void m560initListener$lambda5(MovieListReviewActivity this$0, View view) {
        ReviewModel reviewModel;
        String str;
        int i;
        String tid;
        String pid;
        String for_quote;
        String author;
        String for_quote2;
        String format;
        String for_quote3;
        String substring;
        String author2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String obj = ((EditText) this$0._$_findCachedViewById(R.id.etContent)).getText().toString();
        if (StringsKt.isBlank(obj)) {
            return;
        }
        Intrinsics.checkNotNullExpressionValue(App.getUserData(), "getUserData()");
        UserModel.BBsInfo bBsInfo = App.getBBsInfo();
        if (this$0.isReplyTo) {
            reviewModel = this$0.currReviewModel;
            if (((reviewModel == null || (for_quote = reviewModel.getFor_quote()) == null) ? 0 : for_quote.length()) > 50) {
                if (reviewModel == null || (for_quote3 = reviewModel.getFor_quote()) == null) {
                    substring = "";
                } else {
                    substring = for_quote3.substring(0, 50);
                    Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Object[] objArr = new Object[3];
                if (reviewModel == null || (author2 = reviewModel.getAuthor()) == null) {
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
                if (reviewModel == null || (author = reviewModel.getAuthor()) == null) {
                    author = "";
                }
                objArr2[0] = author;
                objArr2[1] = TimeUtils.formatTime4(System.currentTimeMillis());
                if (reviewModel == null || (for_quote2 = reviewModel.getFor_quote()) == null) {
                    for_quote2 = "";
                }
                objArr2[2] = for_quote2;
                format = String.format("%s replied at %s\n%s", Arrays.copyOf(objArr2, 3));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            }
            str = format;
            i = 1;
        } else {
            reviewModel = this$0.topReviewModel;
            str = "";
            i = 0;
        }
        MovieListReviewPresenter movieListReviewPresenter = (MovieListReviewPresenter) this$0.mPresenter;
        String bbs_uid = bBsInfo.getBbs_uid();
        String author3 = bBsInfo.getAuthor();
        if (reviewModel == null || (tid = reviewModel.getTid()) == null) {
            tid = "";
        }
        String auth = bBsInfo.getAuth();
        String authkey = bBsInfo.getAuthkey();
        String formhash = bBsInfo.getFormhash();
        String authorid = reviewModel == null ? null : reviewModel.getAuthorid();
        if (reviewModel == null || (pid = reviewModel.getPid()) == null) {
            pid = "";
        }
        movieListReviewPresenter.addReply(bbs_uid, author3, tid, 0, auth, authkey, formhash, obj, "", str, authorid, pid, reviewModel == null ? null : reviewModel.getUsername(), reviewModel == null ? null : reviewModel.getAvatar(), reviewModel == null ? null : Long.valueOf(reviewModel.getDbdateline()).toString(), reviewModel == null ? null : reviewModel.getFor_quote(), i, this$0.listTitle, this$0.listImg, this$0.listDesc, this$0.id, this$0.email, this$0.bbsInfo, this$0.userId, this$0.username, this$0.listAvatar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-6  reason: not valid java name */
    public static final void m561initListener$lambda6(MovieListReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (App.getBBsInfo() == null) {
            PandaForumAuthorizeActivity.Companion.start(this$0);
            return;
        }
        ReviewModel reviewModel = this$0.topReviewModel;
        this$0.currPid = reviewModel == null ? null : reviewModel.getPid();
        ReviewRecordModel reviewRecord = ReviewRecordUtils.Companion.get().getReviewRecord(this$0.currPid, "html");
        if (reviewRecord != null) {
            String content = reviewRecord.getContent();
            if (!(content == null || StringsKt.isBlank(content))) {
                this$0.goFullReview();
                return;
            }
        }
        ReviewRecordModel reviewRecord2 = ReviewRecordUtils.Companion.get().getReviewRecord(this$0.currPid, "text");
        if (reviewRecord2 != null) {
            if (Intrinsics.areEqual(reviewRecord2.getType(), "text")) {
                ((EditText) this$0._$_findCachedViewById(R.id.etContent)).setText(reviewRecord2.getContent());
                ((EditText) this$0._$_findCachedViewById(R.id.etContent)).setSelection(reviewRecord2.getContent().length());
                ((EditText) this$0._$_findCachedViewById(R.id.etContent)).requestFocus();
                InputMethodUtils.showSoftInput((EditText) this$0._$_findCachedViewById(R.id.etContent));
                this$0.isReplyTo = false;
                return;
            }
            this$0.goFullReview();
            return;
        }
        this$0.isReplyTo = false;
        ((EditText) this$0._$_findCachedViewById(R.id.etContent)).setText("");
        ((EditText) this$0._$_findCachedViewById(R.id.etContent)).requestFocus();
        InputMethodUtils.showSoftInput((EditText) this$0._$_findCachedViewById(R.id.etContent));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-7  reason: not valid java name */
    public static final void m562initListener$lambda7(MovieListReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        InputMethodUtils.hideSoftInput(this$0);
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-8  reason: not valid java name */
    public static final void m563initListener$lambda8(MovieListReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ReviewFragment reviewFragment = this$0.fragment;
        if (reviewFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
            reviewFragment = null;
        }
        reviewFragment.loadPrePageData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-9  reason: not valid java name */
    public static final void m564initListener$lambda9(MovieListReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ReviewFragment reviewFragment = this$0.fragment;
        if (reviewFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
            reviewFragment = null;
        }
        reviewFragment.loadNextPageData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-11  reason: not valid java name */
    public static final void m556initListener$lambda11(final MovieListReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ReviewFragment reviewFragment = this$0.fragment;
        ReviewFragment reviewFragment2 = null;
        if (reviewFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
            reviewFragment = null;
        }
        if (reviewFragment.getTotalPage() == 1) {
            return;
        }
        ReviewFragment reviewFragment3 = this$0.fragment;
        if (reviewFragment3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
            reviewFragment3 = null;
        }
        ArrayList arrayList = new ArrayList(reviewFragment3.getTotalPage());
        ReviewFragment reviewFragment4 = this$0.fragment;
        if (reviewFragment4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
        } else {
            reviewFragment2 = reviewFragment4;
        }
        int totalPage = reviewFragment2.getTotalPage();
        if (1 <= totalPage) {
            int i = 1;
            while (true) {
                int i2 = i + 1;
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("%s page", Arrays.copyOf(new Object[]{String.valueOf(i)}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                arrayList.add(format);
                if (i == totalPage) {
                    break;
                }
                i = i2;
            }
        }
        new BottomSheetListDialog.BottomListSheetBuilder(this$0).addItems(arrayList).setOnSheetItemClickListener(new BottomSheetListDialog.BottomListSheetBuilder.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$MovieListReviewActivity$yD1IgwmYK5haxIAGD2D-OGU5DIk
            @Override // com.movieboxpro.android.view.dialog.BottomSheetListDialog.BottomListSheetBuilder.OnSheetItemClickListener
            public final void onClick(BottomSheetListDialog bottomSheetListDialog, View view2, int i3, String str) {
                MovieListReviewActivity.m557initListener$lambda11$lambda10(MovieListReviewActivity.this, bottomSheetListDialog, view2, i3, str);
            }
        }).build().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-11$lambda-10  reason: not valid java name */
    public static final void m557initListener$lambda11$lambda10(MovieListReviewActivity this$0, BottomSheetListDialog bottomSheetListDialog, View view, int i, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetListDialog.dismiss();
        ReviewFragment reviewFragment = this$0.fragment;
        if (reviewFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
            reviewFragment = null;
        }
        reviewFragment.loadPageData(i + 1);
    }

    private final void setRecordReview() {
        ReviewRecordModel reviewRecord = ReviewRecordUtils.Companion.get().getReviewRecord(this.currPid, "text");
        if (reviewRecord == null) {
            ((EditText) _$_findCachedViewById(R.id.etContent)).setText("");
            return;
        }
        ((EditText) _$_findCachedViewById(R.id.etContent)).setText(reviewRecord.getContent());
        ((EditText) _$_findCachedViewById(R.id.etContent)).setSelection(reviewRecord.getContent().length());
    }

    @Override // com.movieboxpro.android.view.activity.review.MovieListReviewContract.View
    public void reviewSuccess() {
        ((EditText) _$_findCachedViewById(R.id.etContent)).postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$MovieListReviewActivity$gl-K32az1omFL5wMmwQupHUmBI0
            @Override // java.lang.Runnable
            public final void run() {
                MovieListReviewActivity.m571reviewSuccess$lambda12(MovieListReviewActivity.this);
            }
        }, 500L);
        ReviewFragment reviewFragment = this.fragment;
        if (reviewFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
            reviewFragment = null;
        }
        reviewFragment.reloadAfterPage();
        ((EditText) _$_findCachedViewById(R.id.etContent)).setText("");
        InputMethodUtils.hideSoftInput(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: reviewSuccess$lambda-12  reason: not valid java name */
    public static final void m571reviewSuccess$lambda12(MovieListReviewActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ReviewRecordUtils.Companion.get().deleteReviewRecord(this$0.currPid);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void goFullReview() {
        FullMovieListReplyActivity.Companion.start(this, this.currPid, ((EditText) _$_findCachedViewById(R.id.etContent)).getText().toString(), 4);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    public MovieListReviewPresenter bindPresenter() {
        return new MovieListReviewPresenter(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        ReviewModel reviewModel;
        String str;
        int i3;
        String tid;
        String pid;
        String for_quote;
        String author;
        String for_quote2;
        String format;
        String for_quote3;
        String substring;
        String author2;
        super.onActivityResult(i, i2, intent);
        ReviewFragment reviewFragment = null;
        ReviewFragment reviewFragment2 = null;
        ReviewFragment reviewFragment3 = null;
        if (i == 1 && i2 == -1) {
            ReviewFragment reviewFragment4 = this.fragment;
            if (reviewFragment4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fragment");
            } else {
                reviewFragment = reviewFragment4;
            }
            reviewFragment.reloadAfterPage();
            ((EditText) _$_findCachedViewById(R.id.etContent)).setText("");
            InputMethodUtils.hideSoftInput(this);
        } else if (i == 2 && i2 == -1) {
            ReviewFragment reviewFragment5 = this.fragment;
            if (reviewFragment5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fragment");
            } else {
                reviewFragment2 = reviewFragment5;
            }
            reviewFragment2.reloadAfterPage();
            InputMethodUtils.hideSoftInput(this);
        } else if (i == 3 && i2 == -1) {
            String stringExtra = intent == null ? null : intent.getStringExtra("message");
            ReviewFragment reviewFragment6 = this.fragment;
            if (reviewFragment6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fragment");
            } else {
                reviewFragment3 = reviewFragment6;
            }
            reviewFragment3.updateItem(stringExtra, this.editPosition);
        } else if (i == 4 && i2 == -1) {
            this.currPid = this.id;
            String stringExtra2 = intent == null ? null : intent.getStringExtra(FirebaseAnalytics.Param.CONTENT);
            Integer valueOf = intent == null ? null : Integer.valueOf(intent.getIntExtra("htmlOn", 0));
            String stringExtra3 = intent == null ? null : intent.getStringExtra("userFile");
            Intrinsics.checkNotNullExpressionValue(App.getUserData(), "getUserData()");
            UserModel.BBsInfo bBsInfo = App.getBBsInfo();
            if (this.isReplyTo) {
                reviewModel = this.currReviewModel;
                if (((reviewModel == null || (for_quote = reviewModel.getFor_quote()) == null) ? 0 : for_quote.length()) > 50) {
                    if (reviewModel == null || (for_quote3 = reviewModel.getFor_quote()) == null) {
                        substring = "";
                    } else {
                        substring = for_quote3.substring(0, 50);
                        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    Object[] objArr = new Object[3];
                    if (reviewModel == null || (author2 = reviewModel.getAuthor()) == null) {
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
                    if (reviewModel == null || (author = reviewModel.getAuthor()) == null) {
                        author = "";
                    }
                    objArr2[0] = author;
                    objArr2[1] = TimeUtils.formatTime4(System.currentTimeMillis());
                    if (reviewModel == null || (for_quote2 = reviewModel.getFor_quote()) == null) {
                        for_quote2 = "";
                    }
                    objArr2[2] = for_quote2;
                    format = String.format("%s replied at %s\n%s", Arrays.copyOf(objArr2, 3));
                    Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                }
                str = format;
                i3 = 1;
            } else {
                reviewModel = this.topReviewModel;
                str = "";
                i3 = 0;
            }
            ((MovieListReviewPresenter) this.mPresenter).addReply(bBsInfo.getBbs_uid(), bBsInfo.getAuthor(), (reviewModel == null || (tid = reviewModel.getTid()) == null) ? "" : tid, valueOf == null ? 0 : valueOf.intValue(), bBsInfo.getAuth(), bBsInfo.getAuthkey(), bBsInfo.getFormhash(), stringExtra2, stringExtra3, str, reviewModel == null ? null : reviewModel.getAuthorid(), (reviewModel == null || (pid = reviewModel.getPid()) == null) ? "" : pid, reviewModel == null ? null : reviewModel.getUsername(), reviewModel == null ? null : reviewModel.getAvatar(), reviewModel == null ? null : Long.valueOf(reviewModel.getDbdateline()).toString(), reviewModel != null ? reviewModel.getFor_quote() : null, i3, this.listTitle, this.listImg, this.listDesc, this.id, this.email, this.bbsInfo, this.userId, this.username, this.listAvatar);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        saveContent();
        super.onDestroy();
    }

    /* compiled from: MovieListReviewActivity.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002Jh\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\n\u001a\u0004\u0018\u00010\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\b2\b\u0010\f\u001a\u0004\u0018\u00010\b2\b\u0010\r\u001a\u0004\u0018\u00010\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\bJ>\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015¨\u0006\u0017"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/MovieListReviewActivity$Companion;", "", "()V", TtmlNode.START, "", "context", "Landroid/content/Context;", "title", "", "id", "listImg", "listDesc", "username", "email", "bbsInfo", "Lcom/movieboxpro/android/model/user/UserModel$BBsInfo;", "userId", "avatar", "data", "Lcom/movieboxpro/android/model/ReviewModel;", "page", "", "position", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context, String title, String str, String str2, String str3, String str4, String str5, UserModel.BBsInfo bBsInfo, String str6, String str7) {
            Intrinsics.checkNotNullParameter(title, "title");
            String str8 = str;
            if (str8 == null || StringsKt.isBlank(str8)) {
                ToastUtils.showShort("Can't load this post", new Object[0]);
                return;
            }
            Intent intent = new Intent(context, MovieListReviewActivity.class);
            intent.putExtra("id", str);
            intent.putExtra("title", title);
            intent.putExtra("listImg", str2);
            intent.putExtra("listDesc", str3);
            intent.putExtra("username", str4);
            intent.putExtra("email", str5);
            intent.putExtra("bbsInfo", bBsInfo);
            intent.putExtra("userId", str6);
            intent.putExtra("avatar", str7);
            if (context == null) {
                return;
            }
            context.startActivity(intent);
        }

        public final void start(Context context, String str, String str2, ReviewModel reviewModel, int i, int i2) {
            String str3 = str2;
            if (str3 == null || StringsKt.isBlank(str3)) {
                ToastUtils.showShort("Can't load this post", new Object[0]);
                return;
            }
            Intent intent = new Intent(context, MovieListReviewActivity.class);
            intent.putExtra("id", str2);
            intent.putExtra("data", reviewModel);
            intent.putExtra("title", str);
            intent.putExtra("review", false);
            intent.putExtra("page", i);
            intent.putExtra("position", i2);
            if (context == null) {
                return;
            }
            context.startActivity(intent);
        }
    }

    /* compiled from: MovieListReviewActivity.kt */
    @Metadata(d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0007\u0018\u0000 d2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0003defB\u0005¢\u0006\u0002\u0010\u0004J\u001c\u0010\"\u001a\u00020#2\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020&0%H\u0014J \u0010'\u001a\u00020#2\u0006\u0010(\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010)\u001a\u00020\fH\u0002J\u0018\u0010*\u001a\u00020#2\u000e\u0010+\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010,H\u0014J\b\u0010-\u001a\u00020\u000eH\u0014J\u0012\u0010.\u001a\u00020#2\b\u0010/\u001a\u0004\u0018\u000100H\u0014J\u0012\u00101\u001a\u00020\n2\b\u00102\u001a\u0004\u0018\u00010\u0002H\u0014J\u0018\u00103\u001a\b\u0012\u0004\u0012\u00020\u00020,2\b\u00104\u001a\u0004\u0018\u00010\u0003H\u0014J\u000e\u00105\u001a\b\u0012\u0004\u0012\u00020\f06H\u0014J\u0006\u00107\u001a\u00020\nJ\u001a\u00108\u001a\u00020#2\u0006\u00109\u001a\u00020&2\b\u0010:\u001a\u0004\u0018\u00010\u0002H\u0014J\b\u0010;\u001a\u00020\nH\u0014J\u0012\u0010<\u001a\u00020\n2\b\u00102\u001a\u0004\u0018\u00010\u0002H\u0014J\u0010\u0010=\u001a\u00020#2\u0006\u0010>\u001a\u00020?H\u0014J\b\u0010@\u001a\u00020\u000eH\u0014J\b\u0010A\u001a\u00020\u000eH\u0014J\u0006\u0010B\u001a\u00020#J\u000e\u0010C\u001a\u00020#2\u0006\u0010\u0017\u001a\u00020\nJ\u0006\u0010D\u001a\u00020#J\b\u0010E\u001a\u00020#H\u0014J\b\u0010F\u001a\u00020GH\u0014J\b\u0010H\u001a\u00020IH\u0014J\b\u0010J\u001a\u00020#H\u0014J\u0016\u0010K\u001a\u00020#2\u0006\u0010L\u001a\u00020\n2\u0006\u0010M\u001a\u00020\nJ\u0016\u0010N\u001a\u00020#2\f\u0010O\u001a\b\u0012\u0004\u0012\u00020\u00020PH\u0014J\u0006\u0010Q\u001a\u00020#J\u0010\u0010R\u001a\u00020\f2\u0006\u0010S\u001a\u00020\fH\u0002J\b\u0010T\u001a\u00020\nH\u0014J \u0010U\u001a\u00020#2\u0006\u0010V\u001a\u00020W2\u0006\u0010X\u001a\u00020\f2\u0006\u0010Y\u001a\u00020\nH\u0002J\u0012\u0010Z\u001a\u00020#2\b\u00104\u001a\u0004\u0018\u00010\u0003H\u0014J\u000e\u0010[\u001a\u00020#2\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\\\u001a\u00020#2\u0006\u0010\u0015\u001a\u00020\u001bJ\u0010\u0010]\u001a\u00020#2\u0006\u0010V\u001a\u00020WH\u0003J \u0010^\u001a\u00020#2\u000e\u0010_\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010`2\u0006\u0010\u0018\u001a\u00020\nH\u0002J\u0018\u0010a\u001a\u00020#2\b\u0010b\u001a\u0004\u0018\u00010\f2\u0006\u0010c\u001a\u00020\nR\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006g"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/MovieListReviewActivity$ReviewFragment;", "Lcom/movieboxpro/android/view/activity/review/ReviewListFragment;", "Lcom/movieboxpro/android/model/ReviewModel;", "Lcom/movieboxpro/android/model/ReviewDetailPageModel;", "()V", "bbsInfo", "Lcom/movieboxpro/android/model/user/UserModel$BBsInfo;", "chromeClient", "Landroid/webkit/WebChromeClient;", "currPage", "", "id", "", "isNeedScrollEnd", "", "jsBridge", "Lcom/movieboxpro/android/listener/ImageClickJSBridge;", "likeCount", "likeStatus", "listImg", "listTitle", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/listener/OnReplyClickListener;", "page", "position", "replyCount", "setReviewInfoListener", "Lcom/movieboxpro/android/view/activity/review/MovieListReviewActivity$SetReviewInfoListener;", "totalPage", "userModel", "Lcom/movieboxpro/android/model/user/UserModel;", "username", "webviewClient", "Landroid/webkit/WebViewClient;", "addOnItemClickViews", "", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "doLike", "model", "action", "doSomethingWithData", "list", "", "enableMultiAdapter", "getBundle", "arguments", "Landroid/os/Bundle;", "getCurrPage", "t", "getData", TtmlNode.TAG_P, "getServiceData", "Lio/reactivex/Observable;", "getTotalPage", "initHolder", "helper", "item", "initItemLayout", "initItemType", "initRecyclerView", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "isNeedLoadDataAuto", "isOpenLoadMore", "loadNextPageData", "loadPageData", "loadPrePageData", "onFirstLoadComplete", "onItemChildClick", "Lcom/chad/library/adapter/base/listener/OnItemChildClickListener;", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "onLoadComplete", "recyclerViewScroll", "y", "editY", "registerItemType", "multiTypeDelegate", "Lcom/chad/library/adapter/base/delegate/BaseMultiTypeDelegate;", "reloadAfterPage", "replaceURL", "text", "setEmptyLayoutId", "setHtml", "webView", "Landroid/webkit/WebView;", "html", "textSize", "setPageInfo", "setReplyListener", "setReviewCountListener", "setUpWebView", "toImageShow", "images", "", "updateItem", NotificationCompat.CATEGORY_MESSAGE, "editPosition", "Companion", "MyWebChromeClient", "MyWebViewClient", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class ReviewFragment extends ReviewListFragment<ReviewModel, ReviewDetailPageModel> {
        public static final Companion Companion = new Companion(null);
        private UserModel.BBsInfo bbsInfo;
        private WebChromeClient chromeClient;
        private String id;
        private boolean isNeedScrollEnd;
        private ImageClickJSBridge jsBridge;
        private int likeCount;
        private int likeStatus;
        private OnReplyClickListener listener;
        private int page;
        private int position;
        private int replyCount;
        private SetReviewInfoListener setReviewInfoListener;
        private UserModel userModel;
        private WebViewClient webviewClient;
        public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
        private int totalPage = 1;
        private int currPage = 1;
        private String listTitle = "";
        private String listImg = "";
        private String username = "";

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

        @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
        protected boolean enableMultiAdapter() {
            return true;
        }

        @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
        protected int initItemLayout() {
            return -1;
        }

        @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
        protected boolean isNeedLoadDataAuto() {
            return false;
        }

        @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
        protected boolean isOpenLoadMore() {
            return true;
        }

        @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment, com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
        public /* synthetic */ void onDestroyView() {
            super.onDestroyView();
            _$_clearFindViewByIdCache();
        }

        @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
        protected void onFirstLoadComplete() {
        }

        @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
        protected int setEmptyLayoutId() {
            return R.layout.review_empty_layout;
        }

        public final void setReplyListener(OnReplyClickListener listener) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            this.listener = listener;
        }

        public final void setReviewCountListener(SetReviewInfoListener listener) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            this.setReviewInfoListener = listener;
        }

        @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
        protected Observable<String> getServiceData() {
            APIService service = Http.getService();
            String str = API.BBS_URL;
            UserModel.BBsInfo bBsInfo = this.bbsInfo;
            String auth = bBsInfo == null ? null : bBsInfo.getAuth();
            UserModel.BBsInfo bBsInfo2 = this.bbsInfo;
            String authkey = bBsInfo2 == null ? null : bBsInfo2.getAuthkey();
            String str2 = this.id;
            int i = this.mCurrentPage;
            int i2 = this.mPageSize;
            UserModel.BBsInfo bBsInfo3 = this.bbsInfo;
            return service.movieListReviewDetail(str, "movielist_viewthread", auth, authkey, str2, i, i2, bBsInfo3 == null ? null : bBsInfo3.getFormhash());
        }

        public final void recyclerViewScroll(int i, int i2) {
            this.mRecyclerView.scrollBy(0, i2 - i);
        }

        public final int getTotalPage() {
            return this.totalPage;
        }

        public final void reloadAfterPage() {
            this.isNeedScrollEnd = true;
            if (this.totalPage == 0) {
                this.totalPage = 1;
            }
            this.mCurrentPage = this.totalPage;
            loadExactPageData(this.mCurrentPage);
        }

        public final void updateItem(String str, int i) {
            ReviewModel reviewModel = (ReviewModel) this.mAdapter.getItem(i);
            if (reviewModel == null) {
                return;
            }
            String str2 = str;
            if (!(str2 == null || StringsKt.isBlank(str2))) {
                reviewModel.setMessage(str);
            }
            this.mAdapter.notifyItemChanged(i);
        }

        public final void loadPrePageData() {
            refreshPageData(this.currPage - 1);
        }

        public final void loadNextPageData() {
            refreshPageData(this.currPage + 1);
        }

        public final void loadPageData(int i) {
            refreshPageData(i);
        }

        private final void doLike(final ReviewModel reviewModel, final int i, final String str) {
            UserModel.BBsInfo bBsInfo = App.getBBsInfo();
            Observable<R> map = Http.getService().doLike(API.BBS_URL, "postreview", reviewModel.getTid(), reviewModel.getPid(), str, bBsInfo.getAuth(), bBsInfo.getAuthkey(), bBsInfo.getFormhash()).map($$Lambda$MovieListReviewActivity$ReviewFragment$pp0tnD0i86EnTF5skF1GY5bR7M8.INSTANCE);
            Intrinsics.checkNotNullExpressionValue(map, "getService().doLike(API.…va)\n                    }");
            RxSubscribersKt.transform(map, this).subscribe(new Consumer() { // from class: com.movieboxpro.android.view.activity.review.MovieListReviewActivity$ReviewFragment$doLike$$inlined$transformSubscribe$default$1
                @Override // io.reactivex.functions.Consumer
                public final void accept(T it) {
                    int i2;
                    int i3;
                    int i4;
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    BBsResponseModel bBsResponseModel = (BBsResponseModel) it;
                    if (Intrinsics.areEqual(str, "support")) {
                        String messageval = bBsResponseModel.getMessage().getMessageval();
                        if (Intrinsics.areEqual(messageval, "thread_poll_succeed")) {
                            reviewModel.getPostreview().setSupport_status(1);
                            if (reviewModel.getPostreview().getSupport_status() == 1) {
                                ReviewModel.Postreview postreview = reviewModel.getPostreview();
                                postreview.setSupport(postreview.getSupport() + 1);
                            } else {
                                ReviewModel.Postreview postreview2 = reviewModel.getPostreview();
                                postreview2.setSupport(postreview2.getSupport() - 1);
                            }
                            this.mAdapter.notifyItemChanged(i);
                            return;
                        } else if (Intrinsics.areEqual(messageval, "noreply_voted_error")) {
                            ToastUtils.showShort("You have already liked this comment", new Object[0]);
                            return;
                        } else {
                            ToastUtils.showShort(bBsResponseModel.getMessage().getMessagestr(), new Object[0]);
                            return;
                        }
                    }
                    String messageval2 = bBsResponseModel.getMessage().getMessageval();
                    if (!Intrinsics.areEqual(messageval2, "recommend_succeed")) {
                        if (Intrinsics.areEqual(messageval2, "recommend_duplicate")) {
                            ToastUtils.showShort("You have already liked this post", new Object[0]);
                            return;
                        } else {
                            ToastUtils.showShort(bBsResponseModel.getMessage().getMessagestr(), new Object[0]);
                            return;
                        }
                    }
                    this.likeStatus = 1;
                    i2 = this.likeStatus;
                    if (i2 == 1) {
                        i4 = this.likeCount;
                        this.likeCount = i4 + 1;
                    } else {
                        i3 = this.likeCount;
                        this.likeCount = i3 - 1;
                    }
                    this.mAdapter.notifyItemChanged(i);
                }
            }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.MovieListReviewActivity$ReviewFragment$doLike$$inlined$transformSubscribe$default$2
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th) {
                    Intrinsics.checkNotNullExpressionValue(ApiException.handleException(th), "handleException(it)");
                    if (th instanceof ServerException) {
                        ServerException serverException = (ServerException) th;
                    }
                }
            }, new Action() { // from class: com.movieboxpro.android.view.activity.review.MovieListReviewActivity$ReviewFragment$doLike$$inlined$transformSubscribe$default$3
                @Override // io.reactivex.functions.Action
                public final void run() {
                }
            }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.MovieListReviewActivity$ReviewFragment$doLike$$inlined$transformSubscribe$default$4
                @Override // io.reactivex.functions.Consumer
                public final void accept(Disposable it) {
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: doLike$lambda-1  reason: not valid java name */
        public static final BBsResponseModel m572doLike$lambda1(String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (BBsResponseModel) JSON.parseObject(it, BBsResponseModel.class);
        }

        @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
        protected OnItemClickListener onItemClick() {
            return new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$MovieListReviewActivity$ReviewFragment$35jI66JD902494ZITVCDosz35Ow
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    MovieListReviewActivity.ReviewFragment.m577onItemClick$lambda5(MovieListReviewActivity.ReviewFragment.this, baseQuickAdapter, view, i);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onItemClick$lambda-5  reason: not valid java name */
        public static final void m577onItemClick$lambda5(ReviewFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
            Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
            if (i == 0) {
                MovieListDetailActivity.start(this$0.getContext(), this$0.id, "", "");
            }
        }

        @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
        protected OnItemChildClickListener onItemChildClick() {
            return new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$MovieListReviewActivity$ReviewFragment$WyViPmBZut0jE4CHSQYPP7SM5zI
                @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
                public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    MovieListReviewActivity.ReviewFragment.m576onItemChildClick$lambda7(MovieListReviewActivity.ReviewFragment.this, baseQuickAdapter, view, i);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onItemChildClick$lambda-7  reason: not valid java name */
        public static final void m576onItemChildClick$lambda7(ReviewFragment this$0, BaseQuickAdapter noName_0, View view, int i) {
            String authorid;
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
            Intrinsics.checkNotNullParameter(view, "view");
            ReviewModel item = (ReviewModel) this$0.mAdapter.getItem(i);
            switch (view.getId()) {
                case R.id.ivAvatar /* 2131296923 */:
                    if (App.getBBsInfo() != null) {
                        UserInfoActivity.Companion companion = UserInfoActivity.Companion;
                        Context context = this$0.getContext();
                        String str = "";
                        if (item != null && (authorid = item.getAuthorid()) != null) {
                            str = authorid;
                        }
                        companion.start(context, str);
                        return;
                    }
                    PandaForumAuthorizeActivity.Companion.start(this$0.getContext());
                    return;
                case R.id.ivMore /* 2131296970 */:
                    OnReplyClickListener onReplyClickListener = this$0.listener;
                    if (onReplyClickListener == null) {
                        return;
                    }
                    onReplyClickListener.onMoreActionClicked(item, view, i);
                    return;
                case R.id.llLike /* 2131297190 */:
                    if (item == null) {
                        return;
                    }
                    if (App.getBBsInfo() != null) {
                        if (item.getItemType() == 4) {
                            Intrinsics.checkNotNullExpressionValue(item, "item");
                            this$0.doLike(item, i, "add");
                            return;
                        }
                        Intrinsics.checkNotNullExpressionValue(item, "item");
                        this$0.doLike(item, i, "support");
                        return;
                    }
                    PandaForumAuthorizeActivity.Companion.start(this$0.getContext());
                    return;
                case R.id.llReview /* 2131297215 */:
                    if (App.getBBsInfo() == null) {
                        PandaForumAuthorizeActivity.Companion.start(this$0.getContext());
                        return;
                    }
                    View viewByPosition = this$0.mAdapter.getViewByPosition(i, R.id.line);
                    int[] iArr = new int[2];
                    Intrinsics.checkNotNull(viewByPosition);
                    viewByPosition.getLocationOnScreen(iArr);
                    OnReplyClickListener onReplyClickListener2 = this$0.listener;
                    if (onReplyClickListener2 != null) {
                        onReplyClickListener2.onReplyClicked(iArr[1], item);
                    }
                    CommonExtKt.logD(this$0, 'x' + iArr[0] + " y" + iArr[1]);
                    return;
                default:
                    return;
            }
        }

        @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
        protected void initRecyclerView(RecyclerView recyclerView) {
            Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.movieboxpro.android.view.activity.review.MovieListReviewActivity$ReviewFragment$initRecyclerView$1
                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrollStateChanged(RecyclerView recyclerView2, int i) {
                    Intrinsics.checkNotNullParameter(recyclerView2, "recyclerView");
                }

                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrolled(RecyclerView recyclerView2, int i, int i2) {
                    MovieListReviewActivity.SetReviewInfoListener setReviewInfoListener;
                    int i3;
                    int i4;
                    Intrinsics.checkNotNullParameter(recyclerView2, "recyclerView");
                    RecyclerView.LayoutManager layoutManager = recyclerView2.getLayoutManager();
                    if (layoutManager == null) {
                        throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
                    }
                    int findLastCompletelyVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
                    ReviewModel reviewModel = (ReviewModel) MovieListReviewActivity.ReviewFragment.this.mAdapter.getItemOrNull(findLastCompletelyVisibleItemPosition);
                    if (reviewModel == null) {
                        int i5 = findLastCompletelyVisibleItemPosition - 1;
                        if (CommonExtKt.checkIndexLegal(i5, MovieListReviewActivity.ReviewFragment.this.mAdapter.getData())) {
                            reviewModel = (ReviewModel) MovieListReviewActivity.ReviewFragment.this.mAdapter.getItem(i5);
                        }
                    }
                    if ((reviewModel == null ? 0 : reviewModel.getPage()) != 0) {
                        MovieListReviewActivity.ReviewFragment.this.currPage = reviewModel != null ? reviewModel.getPage() : 0;
                    }
                    setReviewInfoListener = MovieListReviewActivity.ReviewFragment.this.setReviewInfoListener;
                    if (setReviewInfoListener == null) {
                        return;
                    }
                    i3 = MovieListReviewActivity.ReviewFragment.this.totalPage;
                    i4 = MovieListReviewActivity.ReviewFragment.this.currPage;
                    setReviewInfoListener.setPageInfo(i3, i4);
                }
            });
        }

        @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
        protected void onLoadComplete() {
            if (this.isNeedScrollEnd) {
                this.mRecyclerView.postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$MovieListReviewActivity$ReviewFragment$FL4EXjYk-ZD44fIsphkIANQQ5Ps
                    @Override // java.lang.Runnable
                    public final void run() {
                        MovieListReviewActivity.ReviewFragment.m578onLoadComplete$lambda8(MovieListReviewActivity.ReviewFragment.this);
                    }
                }, 300L);
            }
            if (this.page != 0) {
                this.mRecyclerView.postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$MovieListReviewActivity$ReviewFragment$0RcuRjm-qcnOT0iy3jS93sddrKA
                    @Override // java.lang.Runnable
                    public final void run() {
                        MovieListReviewActivity.ReviewFragment.m579onLoadComplete$lambda9(MovieListReviewActivity.ReviewFragment.this);
                    }
                }, 300L);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onLoadComplete$lambda-8  reason: not valid java name */
        public static final void m578onLoadComplete$lambda8(ReviewFragment this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            RecyclerView.LayoutManager layoutManager = this$0.mRecyclerView.getLayoutManager();
            if (layoutManager == null) {
                throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
            }
            ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(this$0.mAdapter.getData().size() - 1, 0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onLoadComplete$lambda-9  reason: not valid java name */
        public static final void m579onLoadComplete$lambda9(ReviewFragment this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            RecyclerView.LayoutManager layoutManager = this$0.mRecyclerView.getLayoutManager();
            if (layoutManager == null) {
                throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
            }
            ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(this$0.position, 0);
            this$0.page = 0;
        }

        @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
        protected void doSomethingWithData(List<ReviewModel> list) {
            if (list != null) {
                for (ReviewModel reviewModel : list) {
                    reviewModel.setPage(this.mCurrentPage);
                    if (Intrinsics.areEqual(reviewModel.getMessage_type(), "html")) {
                        reviewModel.setItemType(2);
                    } else if (CheckUtils.hasUrl(reviewModel.getMessage())) {
                        String message = reviewModel.getMessage();
                        Intrinsics.checkNotNullExpressionValue(message, "it.message");
                        reviewModel.setMessage(replaceURL(message));
                        reviewModel.setItemType(2);
                    } else {
                        reviewModel.setItemType(1);
                    }
                }
            }
            if (this.mCurrentPage != 1 || list == null || list.size() <= 0) {
                return;
            }
            list.get(0).setItemType(4);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
        public int initItemType(ReviewModel reviewModel) {
            if (reviewModel == null) {
                return 1;
            }
            return reviewModel.getItemType();
        }

        @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
        protected void registerItemType(BaseMultiTypeDelegate<ReviewModel> multiTypeDelegate) {
            Intrinsics.checkNotNullParameter(multiTypeDelegate, "multiTypeDelegate");
            multiTypeDelegate.addItemType(4, R.layout.adapter_movie_list_review_top_item);
            multiTypeDelegate.addItemType(1, R.layout.adapter_movie_list_review_detail_item);
            multiTypeDelegate.addItemType(2, R.layout.adapter_movie_list_review_webview_item);
            multiTypeDelegate.addItemType(3, R.layout.adapter_special_review_item);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
        public int getCurrPage(ReviewModel reviewModel) {
            if (reviewModel == null) {
                return 0;
            }
            return reviewModel.getPage();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
        public List<ReviewModel> getData(ReviewDetailPageModel reviewDetailPageModel) {
            ReviewDetailPageModel.Variables variables;
            ReviewDetailPageModel.Variables variables2;
            ReviewDetailPageModel.Variables variables3;
            List<ReviewModel> postlist;
            ReviewDetailPageModel.Variables variables4;
            ReviewDetailPageModel.PostInfo thread;
            SetReviewInfoListener setReviewInfoListener;
            ReviewDetailPageModel.Variables variables5;
            List<ReviewModel> postlist2;
            int i = 1;
            List<ReviewModel> list = null;
            if (this.mCurrentPage == 1) {
                if (((reviewDetailPageModel == null || (variables3 = reviewDetailPageModel.getVariables()) == null || (postlist = variables3.getPostlist()) == null) ? 0 : postlist.size()) > 0 && (setReviewInfoListener = this.setReviewInfoListener) != null) {
                    setReviewInfoListener.setOriginReviewModel((reviewDetailPageModel == null || (variables5 = reviewDetailPageModel.getVariables()) == null || (postlist2 = variables5.getPostlist()) == null) ? null : postlist2.get(0));
                }
                if (reviewDetailPageModel != null && (variables4 = reviewDetailPageModel.getVariables()) != null && (thread = variables4.getThread()) != null) {
                    this.replyCount = thread.getReplies();
                    this.likeCount = thread.getRecommends();
                    this.likeStatus = thread.getRecommend();
                }
            }
            if (reviewDetailPageModel != null && (variables2 = reviewDetailPageModel.getVariables()) != null) {
                i = variables2.getTotalPage();
            }
            this.totalPage = i;
            if (reviewDetailPageModel != null && (variables = reviewDetailPageModel.getVariables()) != null) {
                list = variables.getPostlist();
            }
            return list == null ? new ArrayList() : list;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
        public void setPageInfo(ReviewDetailPageModel reviewDetailPageModel) {
            ReviewDetailPageModel.Variables variables;
            this.currPage = this.mCurrentPage;
            SetReviewInfoListener setReviewInfoListener = this.setReviewInfoListener;
            if (setReviewInfoListener == null) {
                return;
            }
            int i = 0;
            if (reviewDetailPageModel != null && (variables = reviewDetailPageModel.getVariables()) != null) {
                i = variables.getTotalPage();
            }
            setReviewInfoListener.setPageInfo(i, this.mCurrentPage);
        }

        @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
        protected void getBundle(Bundle bundle) {
            this.mClass = ReviewModel.class;
            this.mPageClass = ReviewDetailPageModel.class;
            int i = bundle == null ? 0 : bundle.getInt("page", 0);
            this.page = i;
            if (i != 0) {
                this.mCurrentPage = i;
                this.position = bundle != null ? bundle.getInt("position", 0) : 0;
            }
            this.userModel = App.getUserModel();
            this.bbsInfo = App.getBBsInfo();
            if (bundle == null) {
                return;
            }
            this.id = bundle.getString("id");
            String string = bundle.getString("listImg");
            if (string == null) {
                string = "";
            }
            this.listImg = string;
            String string2 = bundle.getString("listTitle");
            if (string2 == null) {
                string2 = "";
            }
            this.listTitle = string2;
            String string3 = bundle.getString("username");
            this.username = string3 != null ? string3 : "";
        }

        @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
        protected void addOnItemClickViews(BaseQuickAdapter<ReviewModel, BaseViewHolder> adapter) {
            Intrinsics.checkNotNullParameter(adapter, "adapter");
            adapter.addChildClickViewIds(R.id.llLike, R.id.llReview, R.id.ivAvatar, R.id.ivMore);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
        public void initHolder(BaseViewHolder helper, ReviewModel reviewModel) {
            Intrinsics.checkNotNullParameter(helper, "helper");
            if (reviewModel == null) {
                return;
            }
            int itemType = reviewModel.getItemType();
            if (itemType == 1) {
                TextView textView = (TextView) helper.getView(R.id.tvNickname);
                ImageView imageView = (ImageView) helper.getView(R.id.ivLike);
                TextView textView2 = (TextView) helper.getView(R.id.tvLikeNum);
                ImageView imageView2 = (ImageView) helper.getView(R.id.ivReview);
                TextView textView3 = (TextView) helper.getView(R.id.tvReplyNum);
                ImageView imageView3 = (ImageView) helper.getView(R.id.ivMore);
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("#%s", Arrays.copyOf(new Object[]{Integer.valueOf(reviewModel.getPosition())}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                ((TextView) helper.getView(R.id.tvFloor)).setText(format);
                GlideUtils.load(getContext(), reviewModel.getAvatar(), (ImageView) helper.getView(R.id.ivAvatar), (int) R.drawable.ic_default_avatar);
                textView.setMaxLines(999);
                textView.setText(reviewModel.getAuthor());
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                String format2 = String.format("%s", Arrays.copyOf(new Object[]{TimeUtils.INSTANCE.formatReviewTime(reviewModel.getDbdateline() * 1000)}, 1));
                Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
                ((TextView) helper.getView(R.id.tvTime)).setText(format2);
                textView.setMaxLines(1);
                textView2.setText(String.valueOf(reviewModel.getPostreview().getSupport()));
                helper.setText(R.id.tvContent, Html.fromHtml(Pattern.compile(ShellUtil.COMMAND_LINE_END).matcher(reviewModel.getMessage()).replaceAll("<br>")));
                if (reviewModel.getPostreview().getSupport() == 0) {
                    CommonExtKt.invisible(textView2);
                } else {
                    CommonExtKt.visible(textView2);
                    textView2.setText(String.valueOf(reviewModel.getPostreview().getSupport()));
                }
                if (reviewModel.getPostreview().getSupport_status() == 1) {
                    imageView.setImageResource(R.mipmap.ic_liked);
                    CommonExtKt.textColor(textView2, R.color.color_main_blue);
                    return;
                }
                imageView.setImageResource(R.mipmap.ic_do_like);
                CommonExtKt.textColor(textView2, R.color.white_70alpha);
            } else if (itemType != 2) {
                if (itemType != 4) {
                    return;
                }
                GlideUtils.loadWithCorner(getContext(), this.listImg, (ImageView) helper.getView(R.id.imageView), CommonExtKt.dp2Px(4));
                ((TextView) helper.getView(R.id.tvName)).setText(this.listTitle);
                ((TextView) helper.getView(R.id.tvUsername)).setText(this.username);
            } else {
                TextView textView4 = (TextView) helper.getView(R.id.tvNickname);
                ImageView imageView4 = (ImageView) helper.getView(R.id.ivLike);
                TextView textView5 = (TextView) helper.getView(R.id.tvLikeNum);
                ImageView imageView5 = (ImageView) helper.getView(R.id.ivReview);
                TextView textView6 = (TextView) helper.getView(R.id.tvReplyNum);
                ImageView imageView6 = (ImageView) helper.getView(R.id.ivMore);
                StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
                String format3 = String.format("#%s", Arrays.copyOf(new Object[]{Integer.valueOf(reviewModel.getPosition())}, 1));
                Intrinsics.checkNotNullExpressionValue(format3, "format(format, *args)");
                ((TextView) helper.getView(R.id.tvFloor)).setText(format3);
                GlideUtils.load(getContext(), reviewModel.getAvatar(), (ImageView) helper.getView(R.id.ivAvatar), (int) R.drawable.ic_default_avatar);
                textView4.setMaxLines(999);
                textView4.setText(reviewModel.getAuthor());
                StringCompanionObject stringCompanionObject4 = StringCompanionObject.INSTANCE;
                String format4 = String.format("%s", Arrays.copyOf(new Object[]{TimeUtils.INSTANCE.formatReviewTime(reviewModel.getDbdateline() * 1000)}, 1));
                Intrinsics.checkNotNullExpressionValue(format4, "format(format, *args)");
                ((TextView) helper.getView(R.id.tvTime)).setText(format4);
                textView4.setMaxLines(1);
                textView5.setText(String.valueOf(reviewModel.getPostreview().getSupport()));
                WebView webView = (WebView) helper.getView(R.id.webView);
                webView.setBackgroundColor(0);
                webView.setHorizontalScrollBarEnabled(false);
                webView.setVerticalScrollBarEnabled(false);
                String message = reviewModel.getMessage();
                Intrinsics.checkNotNullExpressionValue(message, "it.message");
                setHtml(webView, message, 15);
                if (reviewModel.getPostreview().getSupport() == 0) {
                    CommonExtKt.invisible(textView5);
                } else {
                    CommonExtKt.visible(textView5);
                    textView5.setText(String.valueOf(reviewModel.getPostreview().getSupport()));
                }
                if (reviewModel.getPostreview().getSupport_status() == 1) {
                    imageView4.setImageResource(R.mipmap.ic_liked);
                    CommonExtKt.textColor(textView5, R.color.color_main_blue);
                    return;
                }
                imageView4.setImageResource(R.mipmap.ic_do_like);
                CommonExtKt.textColor(textView5, R.color.white_70alpha);
            }
        }

        private final String replaceURL(String str) {
            Pattern compile = Pattern.compile(CheckUtils.PATTERN_URL_FULL);
            StringBuffer stringBuffer = new StringBuffer();
            Matcher matcher = compile.matcher(str);
            while (matcher.find()) {
                String group = matcher.group();
                matcher.appendReplacement(stringBuffer, "<a href=\"" + ((Object) group) + "\">" + ((Object) group) + "</a>");
            }
            String stringBuffer2 = stringBuffer.toString();
            Intrinsics.checkNotNullExpressionValue(stringBuffer2, "sb.toString()");
            return stringBuffer2;
        }

        private final void setHtml(WebView webView, String str, int i) {
            setUpWebView(webView);
            String str2 = "<style>img{width: 100%;display: block;}a:link{color: #007AFF;}body{margin:0px;padding:0px;font-size:" + i + "px;line-height:21px;}*{background:#1D1D1D;color:rgba(255,255,255,0.70)}</style>";
            String CLICK_EVENT_JS = Constant.CLICK_EVENT_JS;
            Intrinsics.checkNotNullExpressionValue(CLICK_EVENT_JS, "CLICK_EVENT_JS");
            if (StringsKt.isBlank(CLICK_EVENT_JS)) {
                Constant.CLICK_EVENT_JS = CommonUtils.inputStream2String(getResources().getAssets().open("ClickEvent.js"), "utf-8");
            }
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format("%s%s%s", Arrays.copyOf(new Object[]{str2, Constant.CLICK_EVENT_JS, str}, 3));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            webView.loadDataWithBaseURL(null, format, MimeTypes.TEXT_HTML, "utf-8", null);
            webView.setFocusable(true);
            webView.setFocusableInTouchMode(true);
        }

        private final void setUpWebView(WebView webView) {
            webView.setHorizontalFadingEdgeEnabled(false);
            webView.setVerticalFadingEdgeEnabled(false);
            webView.setOverScrollMode(2);
            webView.setOnTouchListener($$Lambda$MovieListReviewActivity$ReviewFragment$xRRP5D7a7n72Sx5K9W4g7D9peQ.INSTANCE);
            if (this.jsBridge == null) {
                ImageClickJSBridge imageClickJSBridge = new ImageClickJSBridge();
                this.jsBridge = imageClickJSBridge;
                Intrinsics.checkNotNull(imageClickJSBridge);
                imageClickJSBridge.setListener(new ImageClickJSBridge.OnImageClickListener() { // from class: com.movieboxpro.android.view.activity.review.MovieListReviewActivity$ReviewFragment$setUpWebView$2
                    @Override // com.movieboxpro.android.listener.ImageClickJSBridge.OnImageClickListener
                    public void onAvatarClick(String str) {
                        if (str != null) {
                            if (App.getBBsInfo() != null) {
                                UserInfoActivity.Companion.start(MovieListReviewActivity.ReviewFragment.this.getContext(), str);
                            } else {
                                PandaForumAuthorizeActivity.Companion.start(MovieListReviewActivity.ReviewFragment.this.getContext());
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
                            if (!Intrinsics.areEqual(str2, "null") && !Intrinsics.areEqual(str3, "null")) {
                                switch (str3.hashCode()) {
                                    case 49:
                                        if (str3.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                                            MovieDetailActivity.start(MovieListReviewActivity.ReviewFragment.this.getContext(), str2);
                                            return;
                                        }
                                        return;
                                    case 50:
                                        if (str3.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                                            TvDetailActivity.start(MovieListReviewActivity.ReviewFragment.this.getContext(), str2);
                                            return;
                                        }
                                        return;
                                    case 51:
                                        if (str3.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                                            MovieListDetailActivity.start(MovieListReviewActivity.ReviewFragment.this.getContext(), str2, "", "");
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
                                String str4 = array[i5];
                                i5++;
                                List split$default3 = StringsKt.split$default((CharSequence) str4, new String[]{","}, false, 0, 6, (Object) null);
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
                            MovieListReviewActivity.ReviewFragment.this.toImageShow(arrayList, i);
                        }
                    }

                    /* JADX WARN: Code restructure failed: missing block: B:11:0x0012, code lost:
                        r0 = r1.this$0.listener;
                     */
                    @Override // com.movieboxpro.android.listener.ImageClickJSBridge.OnImageClickListener
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public void onReviewClick(java.lang.String r2) {
                        /*
                            r1 = this;
                            r0 = r2
                            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
                            if (r0 == 0) goto Le
                            boolean r0 = kotlin.text.StringsKt.isBlank(r0)
                            if (r0 == 0) goto Lc
                            goto Le
                        Lc:
                            r0 = 0
                            goto Lf
                        Le:
                            r0 = 1
                        Lf:
                            if (r0 == 0) goto L12
                            return
                        L12:
                            com.movieboxpro.android.view.activity.review.MovieListReviewActivity$ReviewFragment r0 = com.movieboxpro.android.view.activity.review.MovieListReviewActivity.ReviewFragment.this
                            com.movieboxpro.android.listener.OnReplyClickListener r0 = com.movieboxpro.android.view.activity.review.MovieListReviewActivity.ReviewFragment.access$getListener$p(r0)
                            if (r0 != 0) goto L1b
                            goto L1e
                        L1b:
                            r0.goSingleReview(r2)
                        L1e:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.view.activity.review.MovieListReviewActivity$ReviewFragment$setUpWebView$2.onReviewClick(java.lang.String):void");
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
        /* renamed from: setUpWebView$lambda-14  reason: not valid java name */
        public static final boolean m580setUpWebView$lambda14(View view, MotionEvent motionEvent) {
            view.requestFocus();
            return motionEvent.getAction() == 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void toImageShow(List<String> list, int i) {
            if (list != null) {
                if (list.size() == 1) {
                    new XPopup.Builder(getContext()).asImageViewer(null, list.get(0), new XpopImageLoader()).show();
                } else {
                    new XPopup.Builder(getContext()).asImageViewer(null, i, list, null, new XpopImageLoader()).show();
                }
            }
        }

        /* compiled from: MovieListReviewActivity.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/MovieListReviewActivity$ReviewFragment$MyWebChromeClient;", "Landroid/webkit/WebChromeClient;", "(Lcom/movieboxpro/android/view/activity/review/MovieListReviewActivity$ReviewFragment;)V", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        /* loaded from: classes3.dex */
        public final class MyWebChromeClient extends WebChromeClient {
            final /* synthetic */ ReviewFragment this$0;

            public MyWebChromeClient(ReviewFragment this$0) {
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                this.this$0 = this$0;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* compiled from: MovieListReviewActivity.kt */
        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/MovieListReviewActivity$ReviewFragment$MyWebViewClient;", "Landroid/webkit/WebViewClient;", "(Lcom/movieboxpro/android/view/activity/review/MovieListReviewActivity$ReviewFragment;)V", "shouldOverrideUrlLoading", "", "view", "Landroid/webkit/WebView;", "url", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        /* loaded from: classes3.dex */
        public final class MyWebViewClient extends WebViewClient {
            final /* synthetic */ ReviewFragment this$0;

            public MyWebViewClient(ReviewFragment this$0) {
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

        /* compiled from: MovieListReviewActivity.kt */
        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J>\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\u00062\b\u0010\f\u001a\u0004\u0018\u00010\u0006¨\u0006\r"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/MovieListReviewActivity$ReviewFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/activity/review/MovieListReviewActivity$ReviewFragment;", "id", "", "page", "", "position", "listImg", "listTitle", "listUsername", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        /* loaded from: classes3.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final ReviewFragment newInstance(String str, int i, int i2, String str2, String str3, String str4) {
                ReviewFragment reviewFragment = new ReviewFragment();
                Bundle bundle = new Bundle();
                bundle.putString("id", str);
                bundle.putInt("page", i);
                bundle.putInt("position", i2);
                bundle.putString("listImg", str2);
                bundle.putString("listTitle", str3);
                bundle.putString("username", str4);
                reviewFragment.setArguments(bundle);
                return reviewFragment;
            }
        }
    }
}
