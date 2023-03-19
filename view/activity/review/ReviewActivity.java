package com.movieboxpro.android.view.activity.review;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
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
import com.gyf.immersionbar.ImmersionBar;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.ImageViewerPopupView;
import com.lxj.xpopup.interfaces.OnSrcViewUpdateListener;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.mvp.BaseMvpActivity;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.APIService;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.ServerException;
import com.movieboxpro.android.listener.OnReplyClickListener;
import com.movieboxpro.android.model.BBsResponseModel;
import com.movieboxpro.android.model.ReviewModel;
import com.movieboxpro.android.model.ReviewPageModel;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.InputMethodUtils;
import com.movieboxpro.android.utils.ReviewRecordUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.ShellUtil;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.utils.XpopImageLoader;
import com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity;
import com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity;
import com.movieboxpro.android.view.activity.movielist.MovieListDetailActivity;
import com.movieboxpro.android.view.activity.review.ReviewActivity;
import com.movieboxpro.android.view.activity.review.ReviewContract;
import com.movieboxpro.android.view.activity.review.ReviewDetailActivity;
import com.movieboxpro.android.view.activity.review.UserInfoActivity;
import com.movieboxpro.android.view.dialog.BottomSheetListDialog;
import com.movieboxpro.android.view.widget.SquareImageView;
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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.seamless.xhtml.XHTMLElement;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: ReviewActivity.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 ,2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0003,-.B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0015\u001a\u00020\u0002H\u0014J\b\u0010\u0016\u001a\u00020\u0006H\u0014J\b\u0010\u0017\u001a\u00020\u0006H\u0014J\b\u0010\u0018\u001a\u00020\u0019H\u0014J\b\u0010\u001a\u001a\u00020\u0019H\u0014J\b\u0010\u001b\u001a\u00020\u0019H\u0014J\b\u0010\u001c\u001a\u00020\rH\u0014J\"\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u00062\b\u0010 \u001a\u0004\u0018\u00010!H\u0014J\"\u0010\"\u001a\u00020\u00192\b\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u0006H\u0002J\b\u0010(\u001a\u00020\u0019H\u0014J\u0010\u0010)\u001a\u00020\u00192\u0006\u0010*\u001a\u00020\u000bH\u0016J\b\u0010+\u001a\u00020\u0019H\u0003R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/ReviewActivity;", "Lcom/movieboxpro/android/base/mvp/BaseMvpActivity;", "Lcom/movieboxpro/android/view/activity/review/ReviewPresenter;", "Lcom/movieboxpro/android/view/activity/review/ReviewContract$View;", "()V", "boxType", "", "editPosition", "fragment", "Lcom/movieboxpro/android/view/activity/review/ReviewActivity$ReviewFragment;", "id", "", "isMusicType", "", "popupMenu", "Landroid/widget/PopupMenu;", "sortPopup", "title", "videoPoster", "videoScore", "videoYear", "bindPresenter", "getLayoutResId", "getStatusColor", "initData", "", "initListener", "initView", "isNeedLoadData", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "openPopMenu", "model", "Lcom/movieboxpro/android/model/ReviewModel;", "view", "Landroid/view/View;", "position", "requestData", "reviewSuccess", "videoId", "showSortPopup", "Companion", "ReviewFragment", "SetReviewInfoListener", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ReviewActivity extends BaseMvpActivity<ReviewPresenter> implements ReviewContract.View {
    public static final Companion Companion = new Companion(null);
    private ReviewFragment fragment;
    private boolean isMusicType;
    private PopupMenu popupMenu;
    private PopupMenu sortPopup;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String id = "";
    private int boxType = 1;
    private String title = "";
    private String videoPoster = "";
    private String videoYear = "";
    private String videoScore = "";
    private int editPosition = -1;

    /* compiled from: ReviewActivity.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/ReviewActivity$SetReviewInfoListener;", "", "setPageInfo", "", "totalPage", "", "currPage", "setReviewCount", "count", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface SetReviewInfoListener {
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
        return R.layout.activity_review;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected int getStatusColor() {
        return R.color.color_main;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initView() {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected boolean isNeedLoadData() {
        return false;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void requestData() {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initData() {
        String stringExtra = getIntent().getStringExtra("id");
        if (stringExtra == null) {
            stringExtra = "";
        }
        this.id = stringExtra;
        String stringExtra2 = getIntent().getStringExtra("title");
        if (stringExtra2 == null) {
            stringExtra2 = "";
        }
        this.title = stringExtra2;
        this.videoPoster = getIntent().getStringExtra("videoPoster");
        this.videoYear = getIntent().getStringExtra("videoYear");
        String stringExtra3 = getIntent().getStringExtra("videoScore");
        this.videoScore = stringExtra3 != null ? stringExtra3 : "";
        this.boxType = getIntent().getIntExtra("boxType", 1);
        this.isMusicType = getIntent().getBooleanExtra("isMusicType", false);
        this.fragment = ReviewFragment.Companion.newInstance(this.id, this.boxType, this.isMusicType);
        ((TextView) _$_findCachedViewById(R.id.tvReview)).setText("Review");
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        ReviewFragment reviewFragment = this.fragment;
        ReviewFragment reviewFragment2 = null;
        if (reviewFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
            reviewFragment = null;
        }
        FragmentUtils.add(supportFragmentManager, reviewFragment, (int) R.id.reviewContainer);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("Review of %s", Arrays.copyOf(new Object[]{this.title}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        ((TextView) _$_findCachedViewById(R.id.tvTitle)).setText(format);
        ReviewFragment reviewFragment3 = this.fragment;
        if (reviewFragment3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
            reviewFragment3 = null;
        }
        reviewFragment3.setReplyListener(new ReviewActivity$initData$1(this));
        ReviewFragment reviewFragment4 = this.fragment;
        if (reviewFragment4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
        } else {
            reviewFragment2 = reviewFragment4;
        }
        reviewFragment2.setReviewCountListener(new SetReviewInfoListener() { // from class: com.movieboxpro.android.view.activity.review.ReviewActivity$initData$2
            @Override // com.movieboxpro.android.view.activity.review.ReviewActivity.SetReviewInfoListener
            public void setReviewCount(String count) {
                Intrinsics.checkNotNullParameter(count, "count");
            }

            @Override // com.movieboxpro.android.view.activity.review.ReviewActivity.SetReviewInfoListener
            public void setPageInfo(int i, int i2) {
                if (i == 0) {
                    ImageView ivPrePage = (ImageView) ReviewActivity.this._$_findCachedViewById(R.id.ivPrePage);
                    Intrinsics.checkNotNullExpressionValue(ivPrePage, "ivPrePage");
                    CommonExtKt.invisible(ivPrePage);
                    ImageView ivNextPage = (ImageView) ReviewActivity.this._$_findCachedViewById(R.id.ivNextPage);
                    Intrinsics.checkNotNullExpressionValue(ivNextPage, "ivNextPage");
                    CommonExtKt.invisible(ivNextPage);
                    TextView tvPageInfo = (TextView) ReviewActivity.this._$_findCachedViewById(R.id.tvPageInfo);
                    Intrinsics.checkNotNullExpressionValue(tvPageInfo, "tvPageInfo");
                    CommonExtKt.invisible(tvPageInfo);
                } else if (i == 1) {
                    ((ImageView) ReviewActivity.this._$_findCachedViewById(R.id.ivPrePage)).setEnabled(false);
                    ((ImageView) ReviewActivity.this._$_findCachedViewById(R.id.ivNextPage)).setEnabled(false);
                    TextView tvPageInfo2 = (TextView) ReviewActivity.this._$_findCachedViewById(R.id.tvPageInfo);
                    Intrinsics.checkNotNullExpressionValue(tvPageInfo2, "tvPageInfo");
                    CommonExtKt.visible(tvPageInfo2);
                    ImageView ivPrePage2 = (ImageView) ReviewActivity.this._$_findCachedViewById(R.id.ivPrePage);
                    Intrinsics.checkNotNullExpressionValue(ivPrePage2, "ivPrePage");
                    CommonExtKt.visible(ivPrePage2);
                    ImageView ivNextPage2 = (ImageView) ReviewActivity.this._$_findCachedViewById(R.id.ivNextPage);
                    Intrinsics.checkNotNullExpressionValue(ivNextPage2, "ivNextPage");
                    CommonExtKt.visible(ivNextPage2);
                } else if (i2 == 1) {
                    ((ImageView) ReviewActivity.this._$_findCachedViewById(R.id.ivPrePage)).setEnabled(false);
                    ((ImageView) ReviewActivity.this._$_findCachedViewById(R.id.ivNextPage)).setEnabled(true);
                    TextView tvPageInfo3 = (TextView) ReviewActivity.this._$_findCachedViewById(R.id.tvPageInfo);
                    Intrinsics.checkNotNullExpressionValue(tvPageInfo3, "tvPageInfo");
                    CommonExtKt.visible(tvPageInfo3);
                    ImageView ivPrePage3 = (ImageView) ReviewActivity.this._$_findCachedViewById(R.id.ivPrePage);
                    Intrinsics.checkNotNullExpressionValue(ivPrePage3, "ivPrePage");
                    CommonExtKt.visible(ivPrePage3);
                    ImageView ivNextPage3 = (ImageView) ReviewActivity.this._$_findCachedViewById(R.id.ivNextPage);
                    Intrinsics.checkNotNullExpressionValue(ivNextPage3, "ivNextPage");
                    CommonExtKt.visible(ivNextPage3);
                } else if (i2 == i) {
                    ((ImageView) ReviewActivity.this._$_findCachedViewById(R.id.ivPrePage)).setEnabled(true);
                    ((ImageView) ReviewActivity.this._$_findCachedViewById(R.id.ivNextPage)).setEnabled(false);
                    TextView tvPageInfo4 = (TextView) ReviewActivity.this._$_findCachedViewById(R.id.tvPageInfo);
                    Intrinsics.checkNotNullExpressionValue(tvPageInfo4, "tvPageInfo");
                    CommonExtKt.visible(tvPageInfo4);
                    ImageView ivPrePage4 = (ImageView) ReviewActivity.this._$_findCachedViewById(R.id.ivPrePage);
                    Intrinsics.checkNotNullExpressionValue(ivPrePage4, "ivPrePage");
                    CommonExtKt.visible(ivPrePage4);
                    ImageView ivNextPage4 = (ImageView) ReviewActivity.this._$_findCachedViewById(R.id.ivNextPage);
                    Intrinsics.checkNotNullExpressionValue(ivNextPage4, "ivNextPage");
                    CommonExtKt.visible(ivNextPage4);
                } else {
                    ((ImageView) ReviewActivity.this._$_findCachedViewById(R.id.ivPrePage)).setEnabled(true);
                    ((ImageView) ReviewActivity.this._$_findCachedViewById(R.id.ivNextPage)).setEnabled(true);
                    TextView tvPageInfo5 = (TextView) ReviewActivity.this._$_findCachedViewById(R.id.tvPageInfo);
                    Intrinsics.checkNotNullExpressionValue(tvPageInfo5, "tvPageInfo");
                    CommonExtKt.visible(tvPageInfo5);
                    ImageView ivPrePage5 = (ImageView) ReviewActivity.this._$_findCachedViewById(R.id.ivPrePage);
                    Intrinsics.checkNotNullExpressionValue(ivPrePage5, "ivPrePage");
                    CommonExtKt.visible(ivPrePage5);
                    ImageView ivNextPage5 = (ImageView) ReviewActivity.this._$_findCachedViewById(R.id.ivNextPage);
                    Intrinsics.checkNotNullExpressionValue(ivNextPage5, "ivNextPage");
                    CommonExtKt.visible(ivNextPage5);
                }
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                String format2 = String.format("%s/%s", Arrays.copyOf(new Object[]{Integer.valueOf(i2), Integer.valueOf(i)}, 2));
                Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
                ((TextView) ReviewActivity.this._$_findCachedViewById(R.id.tvPageInfo)).setText(format2);
            }
        });
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initListener() {
        final int navigationBarHeight = ImmersionBar.getNavigationBarHeight(this);
        ((LinearLayout) _$_findCachedViewById(R.id.container)).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$ReviewActivity$t4TfaoGwvvnexDdC43g-gdPWPKA
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                ReviewActivity.m600initListener$lambda0(ReviewActivity.this, navigationBarHeight);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.llReview)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$ReviewActivity$dC28JvpyJrGyEMWhlKY030ig0pA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReviewActivity.m601initListener$lambda1(ReviewActivity.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivBack)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$ReviewActivity$j9tPqprYIne-mpWAMBY7DkkoDlI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReviewActivity.m602initListener$lambda2(ReviewActivity.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivPrePage)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$ReviewActivity$-_stu6pBEWlDbN57Eq3pZ245L_M
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReviewActivity.m603initListener$lambda3(ReviewActivity.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivNextPage)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$ReviewActivity$wa8V-sZpST9GhvD71qn5LVER1Ac
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReviewActivity.m604initListener$lambda4(ReviewActivity.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tvPageInfo)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$ReviewActivity$ra36O9Al1v2Pb1Qs9pWNq840frQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReviewActivity.m605initListener$lambda6(ReviewActivity.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.llSort)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$ReviewActivity$8oeSq3EhuV4sUHo8Sq0wZXPoHew
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReviewActivity.m607initListener$lambda7(ReviewActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m600initListener$lambda0(ReviewActivity this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Rect rect = new Rect();
        ((LinearLayout) this$0._$_findCachedViewById(R.id.container)).getWindowVisibleDisplayFrame(rect);
        if ((((LinearLayout) this$0._$_findCachedViewById(R.id.container)).getRootView().getHeight() - rect.bottom) - i > 100) {
            LinearLayout llBottom = (LinearLayout) this$0._$_findCachedViewById(R.id.llBottom);
            Intrinsics.checkNotNullExpressionValue(llBottom, "llBottom");
            CommonExtKt.gone(llBottom);
            LinearLayout llEdit = (LinearLayout) this$0._$_findCachedViewById(R.id.llEdit);
            Intrinsics.checkNotNullExpressionValue(llEdit, "llEdit");
            CommonExtKt.visible(llEdit);
            return;
        }
        LinearLayout llBottom2 = (LinearLayout) this$0._$_findCachedViewById(R.id.llBottom);
        Intrinsics.checkNotNullExpressionValue(llBottom2, "llBottom");
        CommonExtKt.visible(llBottom2);
        LinearLayout llEdit2 = (LinearLayout) this$0._$_findCachedViewById(R.id.llEdit);
        Intrinsics.checkNotNullExpressionValue(llEdit2, "llEdit");
        CommonExtKt.gone(llEdit2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m601initListener$lambda1(ReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (App.getBBsInfo() == null) {
            PandaForumAuthorizeActivity.Companion.start(this$0);
            return;
        }
        String str = this$0.id;
        int i = this$0.boxType;
        String str2 = this$0.title;
        String str3 = this$0.videoPoster;
        String str4 = this$0.videoYear;
        SendReviewActivity.Companion.start(this$0, "", str, i, str2, str3, str4, this$0.videoScore, str4, this$0.isMusicType, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m602initListener$lambda2(ReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        InputMethodUtils.hideSoftInput(this$0);
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-3  reason: not valid java name */
    public static final void m603initListener$lambda3(ReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ReviewFragment reviewFragment = this$0.fragment;
        if (reviewFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
            reviewFragment = null;
        }
        reviewFragment.loadPrePageData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-4  reason: not valid java name */
    public static final void m604initListener$lambda4(ReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ReviewFragment reviewFragment = this$0.fragment;
        if (reviewFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
            reviewFragment = null;
        }
        reviewFragment.loadNextPageData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-6  reason: not valid java name */
    public static final void m605initListener$lambda6(final ReviewActivity this$0, View view) {
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
        new BottomSheetListDialog.BottomListSheetBuilder(this$0).addItems(arrayList).setOnSheetItemClickListener(new BottomSheetListDialog.BottomListSheetBuilder.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$ReviewActivity$VCUkHIxYjD3xXmqspeUpOq2mUAI
            @Override // com.movieboxpro.android.view.dialog.BottomSheetListDialog.BottomListSheetBuilder.OnSheetItemClickListener
            public final void onClick(BottomSheetListDialog bottomSheetListDialog, View view2, int i3, String str) {
                ReviewActivity.m606initListener$lambda6$lambda5(ReviewActivity.this, bottomSheetListDialog, view2, i3, str);
            }
        }).build().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-6$lambda-5  reason: not valid java name */
    public static final void m606initListener$lambda6$lambda5(ReviewActivity this$0, BottomSheetListDialog bottomSheetListDialog, View view, int i, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetListDialog.dismiss();
        ReviewFragment reviewFragment = this$0.fragment;
        if (reviewFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
            reviewFragment = null;
        }
        reviewFragment.loadPageData(i + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-7  reason: not valid java name */
    public static final void m607initListener$lambda7(ReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showSortPopup();
    }

    @Override // com.movieboxpro.android.view.activity.review.ReviewContract.View
    public void reviewSuccess(String videoId) {
        Intrinsics.checkNotNullParameter(videoId, "videoId");
        ReviewRecordUtils.Companion.get().deleteVideoReviewRecord(this.boxType, videoId);
        ReviewFragment reviewFragment = this.fragment;
        if (reviewFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
            reviewFragment = null;
        }
        reviewFragment.reload();
        ((EditText) _$_findCachedViewById(R.id.etContent)).setText("");
    }

    private final void showSortPopup() {
        if (this.sortPopup == null) {
            PopupMenu popupMenu = new PopupMenu(this, (TextView) _$_findCachedViewById(R.id.tvSort));
            this.sortPopup = popupMenu;
            Intrinsics.checkNotNull(popupMenu);
            popupMenu.inflate(R.menu.sort_review_menu);
            PopupMenu popupMenu2 = this.sortPopup;
            Intrinsics.checkNotNull(popupMenu2);
            popupMenu2.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$ReviewActivity$UA5gcGtfsGf7yQJ_LVxR-zb71qQ
                @Override // android.widget.PopupMenu.OnMenuItemClickListener
                public final boolean onMenuItemClick(MenuItem menuItem) {
                    boolean m614showSortPopup$lambda8;
                    m614showSortPopup$lambda8 = ReviewActivity.m614showSortPopup$lambda8(ReviewActivity.this, menuItem);
                    return m614showSortPopup$lambda8;
                }
            });
        }
        PopupMenu popupMenu3 = this.sortPopup;
        Intrinsics.checkNotNull(popupMenu3);
        popupMenu3.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showSortPopup$lambda-8  reason: not valid java name */
    public static final boolean m614showSortPopup$lambda8(ReviewActivity this$0, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ReviewFragment reviewFragment = null;
        switch (menuItem.getItemId()) {
            case R.id.lastReview /* 2131297099 */:
                ReviewFragment reviewFragment2 = this$0.fragment;
                if (reviewFragment2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("fragment");
                } else {
                    reviewFragment = reviewFragment2;
                }
                reviewFragment.changeOrder(4);
                ((TextView) this$0._$_findCachedViewById(R.id.tvSort)).setText("Last Post");
                break;
            case R.id.like /* 2131297122 */:
                ReviewFragment reviewFragment3 = this$0.fragment;
                if (reviewFragment3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("fragment");
                } else {
                    reviewFragment = reviewFragment3;
                }
                reviewFragment.changeOrder(2);
                ((TextView) this$0._$_findCachedViewById(R.id.tvSort)).setText("Like");
                break;
            case R.id.normal /* 2131297536 */:
                ReviewFragment reviewFragment4 = this$0.fragment;
                if (reviewFragment4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("fragment");
                } else {
                    reviewFragment = reviewFragment4;
                }
                reviewFragment.changeOrder(0);
                ((TextView) this$0._$_findCachedViewById(R.id.tvSort)).setText("Latest");
                break;
            case R.id.reverse /* 2131297640 */:
                ReviewFragment reviewFragment5 = this$0.fragment;
                if (reviewFragment5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("fragment");
                } else {
                    reviewFragment = reviewFragment5;
                }
                reviewFragment.changeOrder(1);
                ((TextView) this$0._$_findCachedViewById(R.id.tvSort)).setText("Oldest");
                break;
            case R.id.review /* 2131297642 */:
                ReviewFragment reviewFragment6 = this$0.fragment;
                if (reviewFragment6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("fragment");
                } else {
                    reviewFragment = reviewFragment6;
                }
                reviewFragment.changeOrder(3);
                ((TextView) this$0._$_findCachedViewById(R.id.tvSort)).setText("Comments");
                break;
            default:
                return false;
        }
        return true;
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
        UserModel.BBsInfo bBsInfo = App.getBBsInfo();
        final String bbs_uid = bBsInfo == null ? null : bBsInfo.getBbs_uid();
        PopupMenu popupMenu2 = this.popupMenu;
        MenuItem findItem = (popupMenu2 == null || (menu = popupMenu2.getMenu()) == null) ? null : menu.findItem(R.id.edit);
        if (findItem != null) {
            findItem.setVisible(Intrinsics.areEqual(bbs_uid, reviewModel != null ? reviewModel.getAuthorid() : null));
        }
        PopupMenu popupMenu3 = this.popupMenu;
        Intrinsics.checkNotNull(popupMenu3);
        popupMenu3.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$ReviewActivity$juSHnP_DbGiLKUXq4XIw0iDmzN4
            @Override // android.widget.PopupMenu.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                boolean m613openPopMenu$lambda10;
                m613openPopMenu$lambda10 = ReviewActivity.m613openPopMenu$lambda10(ReviewModel.this, this, bbs_uid, i, menuItem);
                return m613openPopMenu$lambda10;
            }
        });
        PopupMenu popupMenu4 = this.popupMenu;
        Intrinsics.checkNotNull(popupMenu4);
        popupMenu4.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: openPopMenu$lambda-10  reason: not valid java name */
    public static final boolean m613openPopMenu$lambda10(ReviewModel reviewModel, ReviewActivity this$0, String str, int i, MenuItem menuItem) {
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
            String pid = reviewModel.getPid();
            Intrinsics.checkNotNullExpressionValue(pid, "model.pid");
            EditReviewActivity.Companion.start(this$0, pid, 2, (r12 & 8) != 0 ? false : false, str);
            this$0.editPosition = i;
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    public ReviewPresenter bindPresenter() {
        return new ReviewPresenter(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            ReviewFragment reviewFragment = null;
            if (i == 1) {
                ReviewFragment reviewFragment2 = this.fragment;
                if (reviewFragment2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("fragment");
                } else {
                    reviewFragment = reviewFragment2;
                }
                reviewFragment.reload();
            } else if (i != 2) {
            } else {
                String stringExtra = intent == null ? null : intent.getStringExtra("title");
                String stringExtra2 = intent == null ? null : intent.getStringExtra(FirebaseAnalytics.Param.CONTENT);
                ReviewFragment reviewFragment3 = this.fragment;
                if (reviewFragment3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("fragment");
                } else {
                    reviewFragment = reviewFragment3;
                }
                reviewFragment.updateItem(stringExtra, stringExtra2, this.editPosition);
            }
        }
    }

    /* compiled from: ReviewActivity.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JB\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bJJ\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010¨\u0006\u0011"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/ReviewActivity$Companion;", "", "()V", TtmlNode.START, "", "context", "Landroid/content/Context;", "id", "", "boxType", "", "title", "poster", "year", FirebaseAnalytics.Param.SCORE, "isMusicType", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context, String str, int i, String str2, String poster, String year, String score, boolean z) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(poster, "poster");
            Intrinsics.checkNotNullParameter(year, "year");
            Intrinsics.checkNotNullParameter(score, "score");
            Intent intent = new Intent(context, ReviewActivity.class);
            intent.putExtra("id", str);
            intent.putExtra("boxType", i);
            intent.putExtra("title", str2);
            intent.putExtra("videoPoster", poster);
            intent.putExtra("videoYear", year);
            intent.putExtra("videoScore", score);
            intent.putExtra("isMusicType", z);
            context.startActivity(intent);
        }

        public final void start(Context context, String str, int i, String str2, String poster, String year, String score) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(poster, "poster");
            Intrinsics.checkNotNullParameter(year, "year");
            Intrinsics.checkNotNullParameter(score, "score");
            Intent intent = new Intent(context, ReviewActivity.class);
            intent.putExtra("id", str);
            intent.putExtra("boxType", i);
            intent.putExtra("title", str2);
            intent.putExtra("videoPoster", poster);
            intent.putExtra("videoYear", year);
            intent.putExtra("videoScore", score);
            context.startActivity(intent);
        }
    }

    /* compiled from: ReviewActivity.kt */
    @Metadata(d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 S2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001SB\u0005¢\u0006\u0002\u0010\u0004J\u001c\u0010\u0014\u001a\u00020\u00152\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00180\u0017H\u0014J\u000e\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u0006J\u0018\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u0006H\u0002J\u0018\u0010\u001e\u001a\u00020\u00152\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010 H\u0014J\b\u0010!\u001a\u00020\fH\u0014J\u0012\u0010\"\u001a\u00020\u00152\b\u0010#\u001a\u0004\u0018\u00010$H\u0014J\u0010\u0010%\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u0002H\u0014J\u0018\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00020 2\b\u0010(\u001a\u0004\u0018\u00010\u0003H\u0014J\u000e\u0010)\u001a\b\u0012\u0004\u0012\u00020\n0*H\u0014J\u0006\u0010+\u001a\u00020\u0006J\u001a\u0010,\u001a\u00020\u00152\u0006\u0010-\u001a\u00020\u00182\b\u0010.\u001a\u0004\u0018\u00010\u0002H\u0014J\b\u0010/\u001a\u00020\u0006H\u0014J\u0012\u00100\u001a\u00020\u00062\b\u0010&\u001a\u0004\u0018\u00010\u0002H\u0014J\u0010\u00101\u001a\u00020\u00152\u0006\u00102\u001a\u000203H\u0014J\b\u00104\u001a\u00020\fH\u0014J\u0006\u00105\u001a\u00020\u0015J\u000e\u00106\u001a\u00020\u00152\u0006\u00107\u001a\u00020\u0006J\u0006\u00108\u001a\u00020\u0015J\b\u00109\u001a\u00020:H\u0014J\b\u0010;\u001a\u00020<H\u0014J\u0016\u0010=\u001a\u00020\u00152\u0006\u0010>\u001a\u00020\u00062\u0006\u0010?\u001a\u00020\u0006J\u0016\u0010@\u001a\u00020\u00152\f\u0010A\u001a\b\u0012\u0004\u0012\u00020\u00020BH\u0014J\u0006\u0010C\u001a\u00020\u0015J\b\u0010D\u001a\u00020\u0006H\u0014J\u0012\u0010E\u001a\u00020\u00152\b\u0010(\u001a\u0004\u0018\u00010\u0003H\u0014J\u000e\u0010F\u001a\u00020\u00152\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010G\u001a\u00020\u00152\u0006\u0010\r\u001a\u00020\u0011J2\u0010H\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u00062\b\u0010I\u001a\u0004\u0018\u00010J2\u000e\u0010K\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010L2\u0006\u0010M\u001a\u00020NH\u0002J\"\u0010O\u001a\u00020\u00152\b\u0010P\u001a\u0004\u0018\u00010\n2\b\u0010Q\u001a\u0004\u0018\u00010\n2\u0006\u0010R\u001a\u00020\u0006R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006T"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/ReviewActivity$ReviewFragment;", "Lcom/movieboxpro/android/view/activity/review/ReviewListFragment;", "Lcom/movieboxpro/android/model/ReviewModel;", "Lcom/movieboxpro/android/model/ReviewPageModel;", "()V", "bbsId", "", "boxType", "currPage", "id", "", "isMusicType", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/listener/OnReplyClickListener;", "orderId", "setReviewInfoListener", "Lcom/movieboxpro/android/view/activity/review/ReviewActivity$SetReviewInfoListener;", "totalPage", "uid", "addOnItemClickViews", "", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "changeOrder", "order", "doLike", "model", "position", "doSomethingWithData", "list", "", "enableMultiAdapter", "getBundle", "arguments", "Landroid/os/Bundle;", "getCurrPage", "t", "getData", TtmlNode.TAG_P, "getServiceData", "Lio/reactivex/Observable;", "getTotalPage", "initHolder", "helper", "item", "initItemLayout", "initItemType", "initRecyclerView", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "isOpenLoadMore", "loadNextPageData", "loadPageData", "page", "loadPrePageData", "onItemChildClick", "Lcom/chad/library/adapter/base/listener/OnItemChildClickListener;", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "recyclerViewScroll", "y", "editY", "registerItemType", "multiTypeDelegate", "Lcom/chad/library/adapter/base/delegate/BaseMultiTypeDelegate;", "reload", "setEmptyLayoutId", "setPageInfo", "setReplyListener", "setReviewCountListener", "toImageShow", "view", "Landroid/view/View;", "images", "", "imageView", "Landroid/widget/ImageView;", "updateItem", "title", NotificationCompat.CATEGORY_MESSAGE, "editPosition", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class ReviewFragment extends ReviewListFragment<ReviewModel, ReviewPageModel> {
        public static final Companion Companion = new Companion(null);
        private String id;
        private boolean isMusicType;
        private OnReplyClickListener listener;
        private SetReviewInfoListener setReviewInfoListener;
        private int totalPage;
        public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
        private int boxType = 1;
        private int bbsId = 2;
        private String uid = "";
        private int orderId = 4;
        private int currPage = 1;

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
        protected boolean isOpenLoadMore() {
            return true;
        }

        @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment, com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
        public /* synthetic */ void onDestroyView() {
            super.onDestroyView();
            _$_clearFindViewByIdCache();
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
            String bbs_uid;
            APIService service = Http.getService();
            String str = API.BBS_URL;
            UserModel.BBsInfo bBsInfo = App.getBBsInfo();
            String str2 = "qaq";
            if (bBsInfo != null && (bbs_uid = bBsInfo.getBbs_uid()) != null) {
                str2 = bbs_uid;
            }
            return service.GetReviewListById(str, "get_threadlist_by_videoid", str2, this.id, this.boxType, this.bbsId, 1, this.mCurrentPage, this.mPageSize, this.orderId);
        }

        public final void updateItem(String str, String str2, int i) {
            ReviewModel reviewModel = (ReviewModel) this.mAdapter.getItemOrNull(i);
            if (reviewModel == null) {
                return;
            }
            String str3 = str;
            boolean z = false;
            if (!(str3 == null || StringsKt.isBlank(str3))) {
                reviewModel.setSubject(str);
            }
            String str4 = str2;
            if (!((str4 == null || StringsKt.isBlank(str4)) ? true : true)) {
                reviewModel.setContent(str2);
            }
            this.mAdapter.notifyItemChanged(i);
        }

        public final void changeOrder(int i) {
            this.orderId = i;
            startRefresh();
        }

        public final void recyclerViewScroll(int i, int i2) {
            this.mRecyclerView.scrollBy(0, i2 - i);
        }

        @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
        protected void initRecyclerView(RecyclerView recyclerView) {
            Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.movieboxpro.android.view.activity.review.ReviewActivity$ReviewFragment$initRecyclerView$1
                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrollStateChanged(RecyclerView recyclerView2, int i) {
                    Intrinsics.checkNotNullParameter(recyclerView2, "recyclerView");
                }

                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrolled(RecyclerView recyclerView2, int i, int i2) {
                    ReviewActivity.SetReviewInfoListener setReviewInfoListener;
                    int i3;
                    int i4;
                    Intrinsics.checkNotNullParameter(recyclerView2, "recyclerView");
                    RecyclerView.LayoutManager layoutManager = recyclerView2.getLayoutManager();
                    if (layoutManager == null) {
                        throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
                    }
                    int findLastCompletelyVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
                    ReviewModel reviewModel = (ReviewModel) ReviewActivity.ReviewFragment.this.mAdapter.getItemOrNull(findLastCompletelyVisibleItemPosition);
                    if (reviewModel == null) {
                        int i5 = findLastCompletelyVisibleItemPosition - 1;
                        if (CommonExtKt.checkIndexLegal(i5, ReviewActivity.ReviewFragment.this.mAdapter.getData())) {
                            reviewModel = (ReviewModel) ReviewActivity.ReviewFragment.this.mAdapter.getItem(i5);
                        }
                    }
                    if ((reviewModel == null ? 0 : reviewModel.getPage()) != 0) {
                        ReviewActivity.ReviewFragment.this.currPage = reviewModel != null ? reviewModel.getPage() : 0;
                    }
                    setReviewInfoListener = ReviewActivity.ReviewFragment.this.setReviewInfoListener;
                    if (setReviewInfoListener == null) {
                        return;
                    }
                    i3 = ReviewActivity.ReviewFragment.this.totalPage;
                    i4 = ReviewActivity.ReviewFragment.this.currPage;
                    setReviewInfoListener.setPageInfo(i3, i4);
                }
            });
        }

        @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
        protected OnItemClickListener onItemClick() {
            return new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$ReviewActivity$ReviewFragment$nH9zIbUiKiw1vh5luvgcksaUskw
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    ReviewActivity.ReviewFragment.m619onItemClick$lambda2(ReviewActivity.ReviewFragment.this, baseQuickAdapter, view, i);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onItemClick$lambda-2  reason: not valid java name */
        public static final void m619onItemClick$lambda2(ReviewFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
            Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
            ReviewModel reviewModel = (ReviewModel) this$0.mAdapter.getItem(i);
            if (reviewModel == null) {
                return;
            }
            ReviewDetailActivity.Companion companion = ReviewDetailActivity.Companion;
            Context context = this$0.getContext();
            String subject = reviewModel.getSubject();
            Intrinsics.checkNotNullExpressionValue(subject, "it.subject");
            companion.start(context, subject, reviewModel.getTid(), reviewModel, (r12 & 16) != 0 ? false : false);
        }

        public final int getTotalPage() {
            return this.totalPage;
        }

        public final void reload() {
            startRefresh();
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

        private final void doLike(final ReviewModel reviewModel, final int i) {
            UserModel.BBsInfo bBsInfo = App.getBBsInfo();
            if (bBsInfo != null) {
                Observable<R> map = Http.getService().doLike(API.BBS_URL, "postreview", reviewModel.getTid(), reviewModel.getPid(), "add", bBsInfo.getAuth(), bBsInfo.getAuthkey(), bBsInfo.getFormhash()).map($$Lambda$ReviewActivity$ReviewFragment$osxt_wkisXCqRHLIki3t3qYfAxU.INSTANCE);
                Intrinsics.checkNotNullExpressionValue(map, "getService().doLike(API.…                        }");
                RxSubscribersKt.transform(map, this).subscribe(new Consumer() { // from class: com.movieboxpro.android.view.activity.review.ReviewActivity$ReviewFragment$doLike$$inlined$transformSubscribe$default$1
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(T it) {
                        Intrinsics.checkNotNullExpressionValue(it, "it");
                        BBsResponseModel bBsResponseModel = (BBsResponseModel) it;
                        String messageval = bBsResponseModel.getMessage().getMessageval();
                        if (Intrinsics.areEqual(messageval, "recommend_succeed")) {
                            ReviewModel.this.setStatus(1);
                            if (ReviewModel.this.getStatus() == 1) {
                                ReviewModel.this.setRecommends(ReviewModel.this.getRecommends() + 1);
                            } else {
                                ReviewModel.this.setRecommends(ReviewModel.this.getRecommends() - 1);
                            }
                            this.mAdapter.notifyItemChanged(i);
                        } else if (Intrinsics.areEqual(messageval, "recommend_duplicate")) {
                            ToastUtils.showShort("You have already rated this post", new Object[0]);
                        } else {
                            ToastUtils.showShort(bBsResponseModel.getMessage().getMessagestr(), new Object[0]);
                        }
                    }
                }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.ReviewActivity$ReviewFragment$doLike$$inlined$transformSubscribe$default$2
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Throwable th) {
                        ApiException handleException = ApiException.handleException(th);
                        Intrinsics.checkNotNullExpressionValue(handleException, "handleException(it)");
                        ToastUtils.showShort(Intrinsics.stringPlus("like failed:", handleException.getMessage()), new Object[0]);
                        if (th instanceof ServerException) {
                            ServerException serverException = (ServerException) th;
                        }
                    }
                }, new Action() { // from class: com.movieboxpro.android.view.activity.review.ReviewActivity$ReviewFragment$doLike$$inlined$transformSubscribe$default$3
                    @Override // io.reactivex.functions.Action
                    public final void run() {
                    }
                }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.ReviewActivity$ReviewFragment$doLike$$inlined$transformSubscribe$default$4
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Disposable it) {
                        Intrinsics.checkNotNullExpressionValue(it, "it");
                    }
                });
                return;
            }
            PandaForumAuthorizeActivity.Companion.start(getContext());
            ToastUtils.showShort("Forum authentication error", new Object[0]);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: doLike$lambda-3  reason: not valid java name */
        public static final BBsResponseModel m615doLike$lambda3(String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (BBsResponseModel) JSON.parseObject(it, BBsResponseModel.class);
        }

        @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
        protected OnItemChildClickListener onItemChildClick() {
            return new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$ReviewActivity$ReviewFragment$0lII72-zr8ipwOuyBKvbxZ85yIk
                @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
                public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    ReviewActivity.ReviewFragment.m618onItemChildClick$lambda8(ReviewActivity.ReviewFragment.this, baseQuickAdapter, view, i);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onItemChildClick$lambda-8  reason: not valid java name */
        public static final void m618onItemChildClick$lambda8(ReviewFragment this$0, BaseQuickAdapter noName_0, View view, int i) {
            String type;
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
            Intrinsics.checkNotNullParameter(view, "view");
            ReviewModel reviewModel = (ReviewModel) this$0.mAdapter.getItem(i);
            if (reviewModel == null) {
                return;
            }
            switch (view.getId()) {
                case R.id.imageView /* 2131296850 */:
                    if (reviewModel.getAltImages() == null || reviewModel.getAltImages().size() <= 0 || (type = reviewModel.getAltImages().get(0).getType()) == null) {
                        return;
                    }
                    switch (type.hashCode()) {
                        case 49:
                            if (type.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                                MovieDetailActivity.start(this$0.getContext(), reviewModel.getAltImages().get(0).getId());
                                return;
                            }
                            return;
                        case 50:
                            if (type.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                                TvDetailActivity.start(this$0.getContext(), reviewModel.getAltImages().get(0).getId());
                                return;
                            }
                            return;
                        case 51:
                            if (type.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                                MovieListDetailActivity.start(this$0.getContext(), reviewModel.getAltImages().get(0).getId(), "", "");
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                case R.id.imageViewOne /* 2131296853 */:
                    this$0.toImageShow(0, view, reviewModel.getOriginImages(), (ImageView) view);
                    return;
                case R.id.imageViewThree /* 2131296854 */:
                    this$0.toImageShow(2, view, reviewModel.getOriginImages(), (ImageView) view);
                    return;
                case R.id.imageViewTwo /* 2131296855 */:
                    this$0.toImageShow(1, view, reviewModel.getOriginImages(), (ImageView) view);
                    return;
                case R.id.ivAvatar /* 2131296923 */:
                    if (App.getBBsInfo() != null) {
                        UserInfoActivity.Companion companion = UserInfoActivity.Companion;
                        Context context = this$0.getContext();
                        String authorid = reviewModel.getAuthorid();
                        Intrinsics.checkNotNullExpressionValue(authorid, "model.authorid");
                        companion.start(context, authorid);
                        return;
                    }
                    PandaForumAuthorizeActivity.Companion.start(this$0.getContext());
                    return;
                case R.id.ivMore /* 2131296970 */:
                    OnReplyClickListener onReplyClickListener = this$0.listener;
                    if (onReplyClickListener == null) {
                        return;
                    }
                    onReplyClickListener.onMoreActionClicked(reviewModel, view, i);
                    return;
                case R.id.llLike /* 2131297190 */:
                    if (reviewModel.getStatus() != 1) {
                        this$0.doLike(reviewModel, i);
                        return;
                    } else {
                        ToastUtils.showShort("You have already liked", new Object[0]);
                        return;
                    }
                case R.id.llReview /* 2131297215 */:
                    View viewByPosition = this$0.mAdapter.getViewByPosition(i, R.id.line);
                    int[] iArr = new int[2];
                    Intrinsics.checkNotNull(viewByPosition);
                    viewByPosition.getLocationOnScreen(iArr);
                    OnReplyClickListener onReplyClickListener2 = this$0.listener;
                    if (onReplyClickListener2 != null) {
                        onReplyClickListener2.onReplyClicked(iArr[1], reviewModel);
                    }
                    CommonExtKt.logD(this$0, 'x' + iArr[0] + " y" + iArr[1]);
                    if (App.getBBsInfo() == null) {
                        PandaForumAuthorizeActivity.Companion.start(this$0.getContext());
                        return;
                    }
                    ReviewDetailActivity.Companion companion2 = ReviewDetailActivity.Companion;
                    Context context2 = this$0.getContext();
                    String subject = reviewModel.getSubject();
                    Intrinsics.checkNotNullExpressionValue(subject, "model.subject");
                    companion2.start(context2, subject, reviewModel.getTid(), reviewModel, true);
                    return;
                default:
                    return;
            }
        }

        private final void toImageShow(int i, View view, List<String> list, final ImageView imageView) {
            if (list != null && list.size() == 1) {
                new XPopup.Builder(getContext()).asImageViewer(view instanceof ImageView ? (ImageView) view : null, list.get(0), new XpopImageLoader()).show();
            } else if (view == null) {
            } else {
                XPopup.Builder builder = new XPopup.Builder(getContext());
                if (view == null) {
                    throw new NullPointerException("null cannot be cast to non-null type android.widget.ImageView");
                }
                builder.asImageViewer((ImageView) view, i, list, new OnSrcViewUpdateListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$ReviewActivity$ReviewFragment$QzH-6iAW-x7rXmr3EAEBHRMnVPU
                    @Override // com.lxj.xpopup.interfaces.OnSrcViewUpdateListener
                    public final void onSrcViewUpdate(ImageViewerPopupView imageViewerPopupView, int i2) {
                        ReviewActivity.ReviewFragment.m620toImageShow$lambda10$lambda9(imageView, imageViewerPopupView, i2);
                    }
                }, new XpopImageLoader()).show();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: toImageShow$lambda-10$lambda-9  reason: not valid java name */
        public static final void m620toImageShow$lambda10$lambda9(ImageView imageView, ImageViewerPopupView popupView, int i) {
            Intrinsics.checkNotNullParameter(imageView, "$imageView");
            Intrinsics.checkNotNullParameter(popupView, "popupView");
            if (i == 0) {
                popupView.updateSrcView(imageView);
            } else if (i == 1) {
                popupView.updateSrcView(imageView);
            } else if (i != 2) {
            } else {
                popupView.updateSrcView(imageView);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
        public int getCurrPage(ReviewModel t) {
            Intrinsics.checkNotNullParameter(t, "t");
            return t.getPage();
        }

        @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
        protected void doSomethingWithData(List<ReviewModel> list) {
            int parseInt;
            int parseInt2;
            if (this.mCurrentPage == 1) {
                this.mRecyclerView.scrollToPosition(0);
            }
            if (list == null) {
                return;
            }
            for (ReviewModel reviewModel : list) {
                reviewModel.setPage(this.mCurrentPage);
                if (Intrinsics.areEqual(reviewModel.getMessage_type(), "html")) {
                    Elements imgs = Jsoup.parseBodyFragment(reviewModel.getMessage()).body().select("img");
                    Intrinsics.checkNotNullExpressionValue(imgs, "imgs");
                    int i = 0;
                    boolean z = true;
                    for (Element element : imgs) {
                        int i2 = i + 1;
                        if (i < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        Element element2 = element;
                        String attr = element2.attr("aid");
                        if (!(attr == null || attr.length() == 0)) {
                            if (i == 0) {
                                z = true;
                            }
                            if (reviewModel.getImages() == null) {
                                reviewModel.setImages(new ArrayList());
                            }
                            if (reviewModel.getOriginImages() == null) {
                                reviewModel.setOriginImages(new ArrayList());
                            }
                            String widthAttr = element2.attr("w");
                            String str = widthAttr;
                            if (str == null || StringsKt.isBlank(str)) {
                                parseInt = 0;
                            } else {
                                Intrinsics.checkNotNullExpressionValue(widthAttr, "widthAttr");
                                parseInt = Integer.parseInt(widthAttr);
                            }
                            String heightAttr = element2.attr(XHTMLElement.XPATH_PREFIX);
                            if (str == null || StringsKt.isBlank(str)) {
                                parseInt2 = 0;
                            } else {
                                Intrinsics.checkNotNullExpressionValue(heightAttr, "heightAttr");
                                parseInt2 = Integer.parseInt(heightAttr);
                            }
                            reviewModel.getImages().add(new ReviewModel.ImageModel(element2.attr("data-src"), parseInt2, parseInt));
                            reviewModel.getOriginImages().add(element2.attr("src"));
                        } else {
                            if (i == 0) {
                                z = false;
                            }
                            if (reviewModel.getAltImages() == null) {
                                reviewModel.setAltImages(new ArrayList());
                            }
                            reviewModel.getAltImages().add(new ReviewModel.AltTypeItem(element2.attr(IjkMediaMeta.IJKM_KEY_TYPE), element2.attr("src"), element2.attr("videoid")));
                        }
                        i = i2;
                    }
                    List<ReviewModel.ImageModel> images = reviewModel.getImages();
                    if (images == null || images.isEmpty()) {
                        if (reviewModel.getAltImages() != null && reviewModel.getAltImages().size() > 0) {
                            String type = reviewModel.getAltImages().get(0).getType();
                            if (type != null) {
                                switch (type.hashCode()) {
                                    case 49:
                                        if (type.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                                            reviewModel.setItemType(400);
                                            break;
                                        } else {
                                            break;
                                        }
                                    case 50:
                                        if (type.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                                            reviewModel.setItemType(400);
                                            break;
                                        } else {
                                            break;
                                        }
                                    case 51:
                                        if (type.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                                            reviewModel.setItemType(500);
                                            break;
                                        } else {
                                            break;
                                        }
                                }
                            }
                            reviewModel.setItemType(1);
                        } else {
                            reviewModel.setItemType(1);
                        }
                    } else if (z) {
                        int size = reviewModel.getImages().size();
                        if (size == 0) {
                            reviewModel.setItemType(1);
                        } else if (size == 1) {
                            reviewModel.setItemType(300);
                        } else if (size == 2) {
                            reviewModel.setItemType(200);
                        } else {
                            reviewModel.setItemType(100);
                        }
                    } else if (reviewModel.getAltImages() != null && reviewModel.getAltImages().size() > 0) {
                        String type2 = reviewModel.getAltImages().get(0).getType();
                        if (type2 != null) {
                            switch (type2.hashCode()) {
                                case 49:
                                    if (type2.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                                        reviewModel.setItemType(400);
                                        break;
                                    } else {
                                        break;
                                    }
                                case 50:
                                    if (type2.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                                        reviewModel.setItemType(400);
                                        break;
                                    } else {
                                        break;
                                    }
                                case 51:
                                    if (type2.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                                        reviewModel.setItemType(500);
                                        break;
                                    } else {
                                        break;
                                    }
                            }
                        }
                        reviewModel.setItemType(1);
                    } else {
                        reviewModel.setItemType(1);
                    }
                } else {
                    reviewModel.setItemType(1);
                }
            }
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
            multiTypeDelegate.addItemType(1, R.layout.adapter_review_item);
            multiTypeDelegate.addItemType(100, R.layout.adapter_three_image_review_item);
            multiTypeDelegate.addItemType(300, R.layout.adapter_single_image_review_item);
            multiTypeDelegate.addItemType(200, R.layout.adapter_two_image_review_item);
            multiTypeDelegate.addItemType(400, R.layout.adapter_alt_video_item);
            multiTypeDelegate.addItemType(500, R.layout.adapter_alt_movie_list_item);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
        public List<ReviewModel> getData(ReviewPageModel reviewPageModel) {
            if (reviewPageModel != null) {
                SetReviewInfoListener setReviewInfoListener = this.setReviewInfoListener;
                if (setReviewInfoListener != null) {
                    String count = reviewPageModel.getCount();
                    if (count == null) {
                        count = "0";
                    }
                    setReviewInfoListener.setReviewCount(count);
                }
                this.totalPage = reviewPageModel.getTotalPage();
                List<ReviewModel> list = reviewPageModel.getList();
                Intrinsics.checkNotNullExpressionValue(list, "{\n                setRev…     p.list\n            }");
                return list;
            }
            return new ArrayList();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
        public void setPageInfo(ReviewPageModel reviewPageModel) {
            this.currPage = this.mCurrentPage;
            SetReviewInfoListener setReviewInfoListener = this.setReviewInfoListener;
            if (setReviewInfoListener == null) {
                return;
            }
            setReviewInfoListener.setPageInfo(reviewPageModel == null ? 0 : reviewPageModel.getTotalPage(), this.mCurrentPage);
        }

        @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
        protected void getBundle(Bundle bundle) {
            String str = App.getUserData().uid_v2;
            if (str == null) {
                str = "";
            }
            this.uid = str;
            this.mClass = ReviewModel.class;
            this.mPageClass = ReviewPageModel.class;
            if (bundle == null) {
                return;
            }
            this.id = bundle.getString("id");
            int i = bundle.getInt("boxType");
            this.boxType = i;
            if (i == 1) {
                this.bbsId = 2;
            } else if (i == 2) {
                this.bbsId = 36;
            } else if (i == 3) {
                this.bbsId = 37;
            }
            boolean z = bundle.getBoolean("isMusicType", false);
            this.isMusicType = z;
            if (z) {
                this.bbsId = 57;
            }
        }

        @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
        protected void addOnItemClickViews(BaseQuickAdapter<ReviewModel, BaseViewHolder> adapter) {
            Intrinsics.checkNotNullParameter(adapter, "adapter");
            adapter.addChildClickViewIds(R.id.llLike, R.id.llReview, R.id.ivAvatar, R.id.ivMore, R.id.imageViewOne, R.id.imageViewTwo, R.id.imageViewThree, R.id.imageView);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
        public void initHolder(BaseViewHolder helper, ReviewModel reviewModel) {
            Intrinsics.checkNotNullParameter(helper, "helper");
            if (reviewModel == null) {
                return;
            }
            ImageView imageView = (ImageView) helper.getView(R.id.ivAvatar);
            TextView textView = (TextView) helper.getView(R.id.tvNickname);
            TextView textView2 = (TextView) helper.getView(R.id.tvTime);
            ImageView imageView2 = (ImageView) helper.getView(R.id.ivLike);
            TextView textView3 = (TextView) helper.getView(R.id.tvLikeNum);
            TextView textView4 = (TextView) helper.getView(R.id.tvTitle);
            TextView textView5 = (TextView) helper.getView(R.id.tvReplyNum);
            if (reviewModel.getReplies() == 0) {
                CommonExtKt.invisible(textView5);
            } else {
                CommonExtKt.visible(textView5);
                textView5.setText(String.valueOf(reviewModel.getReplies()));
            }
            if (reviewModel.getRecommends() == 0) {
                CommonExtKt.invisible(textView3);
            } else {
                CommonExtKt.visible(textView3);
                textView3.setText(String.valueOf(reviewModel.getRecommends()));
            }
            textView4.setText(reviewModel.getSubject());
            GlideUtils.load(getContext(), reviewModel.getAvatar(), imageView, (int) R.drawable.ic_default_avatar);
            textView.setMaxLines(999);
            textView.setText(reviewModel.getAuthor());
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format("%s", Arrays.copyOf(new Object[]{TimeUtils.INSTANCE.formatReviewTime(reviewModel.getAdd_time() * 1000)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            textView2.setText(format);
            textView.setMaxLines(1);
            if (reviewModel.getStatus() == 1) {
                imageView2.setImageResource(R.mipmap.ic_liked);
                CommonExtKt.textColor(textView3, R.color.color_main_blue);
            } else {
                imageView2.setImageResource(R.mipmap.ic_do_like);
                CommonExtKt.textColor(textView3, R.color.white_70alpha);
            }
            TextView textView6 = (TextView) helper.getView(R.id.tvContent);
            String content = reviewModel.getContent();
            if (content == null || content.length() == 0) {
                CommonExtKt.gone(textView6);
            } else {
                CommonExtKt.visible(textView6);
                String content2 = reviewModel.getContent();
                Intrinsics.checkNotNullExpressionValue(content2, "it.content");
                if (StringsKt.endsWith$default(content2, ShellUtil.COMMAND_LINE_END, false, 2, (Object) null) && reviewModel.getContent().length() - 2 <= reviewModel.getContent().length() && reviewModel.getContent().length() - 2 >= 0) {
                    String content3 = reviewModel.getContent();
                    Intrinsics.checkNotNullExpressionValue(content3, "it.content");
                    String substring = content3.substring(0, reviewModel.getContent().length() - 2);
                    Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                    reviewModel.setContent(substring);
                }
                textView6.setText(reviewModel.getContent());
            }
            int itemType = reviewModel.getItemType();
            if (itemType == 100) {
                SquareImageView squareImageView = (SquareImageView) helper.getView(R.id.imageViewOne);
                SquareImageView squareImageView2 = (SquareImageView) helper.getView(R.id.imageViewTwo);
                SquareImageView squareImageView3 = (SquareImageView) helper.getView(R.id.imageViewThree);
                View view = helper.getView(R.id.view);
                TextView textView7 = (TextView) helper.getView(R.id.tvLeftNum);
                if (reviewModel.getImages() != null && reviewModel.getImages().size() >= 3) {
                    CommonExtKt.visible(squareImageView);
                    CommonExtKt.visible(squareImageView2);
                    CommonExtKt.visible(squareImageView3);
                    ReviewModel.ImageModel imageModel = reviewModel.getImages().get(0);
                    ReviewModel.ImageModel imageModel2 = reviewModel.getImages().get(1);
                    ReviewModel.ImageModel imageModel3 = reviewModel.getImages().get(2);
                    GlideUtils.loadWithCornerOriginSize(getContext(), imageModel.getUrl(), squareImageView, 5, imageModel.getWidth(), imageModel.getHeight());
                    GlideUtils.loadWithCornerOriginSize(getContext(), imageModel2.getUrl(), squareImageView2, 5, imageModel2.getWidth(), imageModel2.getHeight());
                    GlideUtils.loadWithCornerOriginSize(getContext(), imageModel3.getUrl(), squareImageView3, 5, imageModel3.getWidth(), imageModel3.getHeight());
                    if (reviewModel.getImages().size() > 3) {
                        CommonExtKt.visible(view);
                        CommonExtKt.visible(textView7);
                        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                        String format2 = String.format("+%s", Arrays.copyOf(new Object[]{Integer.valueOf(reviewModel.getImages().size() - 3)}, 1));
                        Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
                        textView7.setText(format2);
                        return;
                    }
                    CommonExtKt.gone(view);
                    CommonExtKt.gone(textView7);
                    return;
                }
                CommonExtKt.gone(squareImageView);
                CommonExtKt.gone(squareImageView2);
                CommonExtKt.gone(squareImageView3);
                CommonExtKt.gone(view);
                CommonExtKt.gone(textView7);
            } else if (itemType == 200) {
                SquareImageView squareImageView4 = (SquareImageView) helper.getView(R.id.imageViewOne);
                SquareImageView squareImageView5 = (SquareImageView) helper.getView(R.id.imageViewTwo);
                if (reviewModel.getImages() != null && reviewModel.getImages().size() >= 2) {
                    CommonExtKt.visible(squareImageView4);
                    CommonExtKt.visible(squareImageView5);
                    ReviewModel.ImageModel imageModel4 = reviewModel.getImages().get(0);
                    ReviewModel.ImageModel imageModel5 = reviewModel.getImages().get(1);
                    GlideUtils.loadWithCornerOriginSize(getContext(), imageModel4.getUrl(), squareImageView4, 5, imageModel4.getWidth(), imageModel4.getHeight());
                    GlideUtils.loadWithCornerOriginSize(getContext(), imageModel5.getUrl(), squareImageView5, 5, imageModel5.getWidth(), imageModel5.getHeight());
                    return;
                }
                CommonExtKt.gone(squareImageView4);
                CommonExtKt.gone(squareImageView5);
            } else if (itemType != 300) {
                if (itemType == 400) {
                    ImageView imageView3 = (ImageView) helper.getView(R.id.imageView);
                    if (reviewModel.getAltImages() != null && reviewModel.getAltImages().size() > 0) {
                        CommonExtKt.visible(imageView3);
                        GlideUtils.loadWithCorner(getContext(), reviewModel.getAltImages().get(0).getUrl(), imageView3, 5);
                        return;
                    }
                    CommonExtKt.gone(imageView3);
                } else if (itemType != 500) {
                } else {
                    ImageView imageView4 = (ImageView) helper.getView(R.id.imageView);
                    if (reviewModel.getAltImages() != null && reviewModel.getAltImages().size() > 0) {
                        CommonExtKt.visible(imageView4);
                        GlideUtils.loadWithCorner(getContext(), reviewModel.getAltImages().get(0).getUrl(), imageView4, 5);
                        return;
                    }
                    CommonExtKt.gone(imageView4);
                }
            } else {
                AppCompatImageView appCompatImageView = (AppCompatImageView) helper.getView(R.id.imageViewOne);
                if (reviewModel.getImages().size() > 0) {
                    CommonExtKt.visible(appCompatImageView);
                    int width = reviewModel.getImages().get(0).getWidth();
                    int height = reviewModel.getImages().get(0).getHeight();
                    ViewGroup.LayoutParams layoutParams = appCompatImageView.getLayoutParams();
                    if (width >= height) {
                        if (width / 2 <= height) {
                            int dp2Px = CommonExtKt.dp2Px(150);
                            layoutParams.width = dp2Px;
                            if (width != 0) {
                                layoutParams.height = (dp2Px * height) / width;
                            } else {
                                layoutParams.height = CommonExtKt.dp2Px(200);
                            }
                        } else {
                            layoutParams.width = CommonExtKt.dp2Px(200);
                            layoutParams.height = CommonExtKt.dp2Px(200);
                        }
                    } else if (height / 2 <= width) {
                        int dp2Px2 = CommonExtKt.dp2Px(150);
                        layoutParams.width = dp2Px2;
                        if (width != 0) {
                            layoutParams.height = (dp2Px2 * height) / width;
                        } else {
                            layoutParams.height = CommonExtKt.dp2Px(200);
                        }
                    } else {
                        layoutParams.width = CommonExtKt.dp2Px(200);
                        layoutParams.height = CommonExtKt.dp2Px(200);
                    }
                    appCompatImageView.setLayoutParams(layoutParams);
                    if (width == 0) {
                        width = CommonExtKt.dp2Px(150);
                    }
                    int i = width;
                    if (height == 0) {
                        height = CommonExtKt.dp2Px(150);
                    }
                    GlideUtils.loadWithCornerOriginSize(getContext(), reviewModel.getImages().get(0).getUrl(), appCompatImageView, 5, i, height);
                    return;
                }
                CommonExtKt.gone(appCompatImageView);
            }
        }

        /* compiled from: ReviewActivity.kt */
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n¨\u0006\u000b"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/ReviewActivity$ReviewFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/activity/review/ReviewActivity$ReviewFragment;", "id", "", "boxType", "", "isMusicType", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        /* loaded from: classes3.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final ReviewFragment newInstance(String str, int i, boolean z) {
                ReviewFragment reviewFragment = new ReviewFragment();
                Bundle bundle = new Bundle();
                bundle.putString("id", str);
                bundle.putInt("boxType", i);
                bundle.putBoolean("isMusicType", z);
                reviewFragment.setArguments(bundle);
                return reviewFragment;
            }
        }
    }
}
