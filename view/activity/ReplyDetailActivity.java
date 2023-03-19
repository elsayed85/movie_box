package com.movieboxpro.android.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Size;
import android.util.SizeF;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.adorkable.iosdialog.ActionSheetDialog;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.gyf.immersionbar.ImmersionBar;
import com.huantansheng.easyphotos.Builder.AlbumBuilder;
import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.engine.ImageEngine;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.ImageViewerPopupView;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.lxj.xpopup.interfaces.OnSrcViewUpdateListener;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.base.CommBaseAdapter;
import com.movieboxpro.android.base.mvp.BaseMvpActivity;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.http.HttpUploadRequest;
import com.movieboxpro.android.model.AtItem;
import com.movieboxpro.android.model.Comment;
import com.movieboxpro.android.model.ImageItem;
import com.movieboxpro.android.model.ListResponse;
import com.movieboxpro.android.model.ReportReason;
import com.movieboxpro.android.model.ReviewImage;
import com.movieboxpro.android.model.ReviewItem;
import com.movieboxpro.android.model.ReviewResponse;
import com.movieboxpro.android.model.UploadImgResponse;
import com.movieboxpro.android.utils.ApiUtils;
import com.movieboxpro.android.utils.ChooseImageGlideEngine;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.utils.KeyboardUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ScreenUtils;
import com.movieboxpro.android.utils.SpanUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.utils.XpopImageLoader;
import com.movieboxpro.android.view.activity.ReplyDetailActivity;
import com.movieboxpro.android.view.activity.ReplyDetailContract;
import com.movieboxpro.android.view.dialog.BlockUserDialog;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.ReportReviewDialogFragment;
import com.movieboxpro.android.view.widget.CustomClickableSpan;
import com.movieboxpro.android.view.widget.GridSpacingItemDecoration;
import com.movieboxpro.android.view.widget.UserAvatarView;
import com.movieboxpro.android.view.widget.textview.QMUISpanTouchFixTextView;
import com.movieboxpro.android.view.widget.textview.QMUITouchableSpan;
import com.snowtop.diskpanda.view.widget.at.MethodContext;
import com.snowtop.diskpanda.view.widget.at.User;
import com.snowtop.diskpanda.view.widget.at.Weibo;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import top.zibin.luban.Luban;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: ReplyDetailActivity.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 +2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0002+,B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0014\u001a\u00020\u0002H\u0014J\b\u0010\u0015\u001a\u00020\u0006H\u0014J\b\u0010\u0016\u001a\u00020\u0017H\u0014J\u0010\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u0017H\u0014J\b\u0010\u001c\u001a\u00020\u0017H\u0014J\b\u0010\u001d\u001a\u00020\u001eH\u0014J\b\u0010\u001f\u001a\u00020\u001eH\u0014J\"\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\u00062\b\u0010#\u001a\u0004\u0018\u00010$H\u0014J\b\u0010%\u001a\u00020\u0017H\u0014J\b\u0010&\u001a\u00020\u0017H\u0014J\b\u0010'\u001a\u00020\u0017H\u0014J\b\u0010(\u001a\u00020\u0017H\u0016J\b\u0010)\u001a\u00020\u0017H\u0002J\u0010\u0010*\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u001aH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082.¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/movieboxpro/android/view/activity/ReplyDetailActivity;", "Lcom/movieboxpro/android/base/mvp/BaseMvpActivity;", "Lcom/movieboxpro/android/view/activity/ReplyDetailPresenter;", "Lcom/movieboxpro/android/view/activity/ReplyDetailContract$View;", "()V", "boxType", "", "commentId", "", "currReplyPos", "currSort", "fragment", "Lcom/movieboxpro/android/view/activity/ReplyDetailActivity$ReplyDetailListFragment;", "id", "methodContext", "Lcom/snowtop/diskpanda/view/widget/at/MethodContext;", "replyId", "sendImageAdapter", "Lcom/movieboxpro/android/base/CommBaseAdapter;", "Lcom/movieboxpro/android/model/ReviewImage;", "bindPresenter", "getLayoutResId", "initData", "", "initFragment", "item", "Lcom/movieboxpro/android/model/ReviewItem;", "initListener", "initView", "isFullScreen", "", "isNeedLoadData", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onDestroy", "onStop", "requestData", "reviewSuccess", "sendReview", "showReview", "Companion", "ReplyDetailListFragment", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ReplyDetailActivity extends BaseMvpActivity<ReplyDetailPresenter> implements ReplyDetailContract.View {
    public static final Companion Companion = new Companion(null);
    private ReplyDetailListFragment fragment;
    private CommBaseAdapter<ReviewImage> sendImageAdapter;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String replyId = "";
    private int currReplyPos = -1;
    private String id = "";
    private int boxType = 1;
    private String commentId = "";
    private final MethodContext methodContext = new MethodContext();
    private String currSort = "";

    @JvmStatic
    public static final void start(Context context, String str, int i, String str2, ReviewItem reviewItem) {
        Companion.start(context, str, i, str2, reviewItem);
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
        return R.layout.activity_reply_detail;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected boolean isFullScreen() {
        return true;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected boolean isNeedLoadData() {
        return false;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void requestData() {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initListener() {
        ((ImageView) _$_findCachedViewById(R.id.iv_right)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyDetailActivity$EokiX9BSYn8kYW0rlp7hfc_C5-U
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReplyDetailActivity.m229initListener$lambda0(ReplyDetailActivity.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.llReview)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyDetailActivity$A0siTGKcDxDwpV6HjkG_R3iW2Lo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReplyDetailActivity.m230initListener$lambda1(ReplyDetailActivity.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivAt)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyDetailActivity$WcfUfdbrvUKDR6quedRXwMbR9Cs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReplyDetailActivity.m231initListener$lambda2(ReplyDetailActivity.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.ll_back)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyDetailActivity$bv35NdBTXNZPLPHYbkqtuK9pFX8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReplyDetailActivity.m232initListener$lambda3(ReplyDetailActivity.this, view);
            }
        });
        EditText etContent = (EditText) _$_findCachedViewById(R.id.etContent);
        Intrinsics.checkNotNullExpressionValue(etContent, "etContent");
        etContent.addTextChangedListener(new TextWatcher() { // from class: com.movieboxpro.android.view.activity.ReplyDetailActivity$initListener$$inlined$doAfterTextChanged$1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                ImageView imageView = (ImageView) ReplyDetailActivity.this._$_findCachedViewById(R.id.ivSend);
                String obj = editable == null ? null : editable.toString();
                imageView.setEnabled(!(obj == null || StringsKt.isBlank(obj)));
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivSend)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyDetailActivity$D1mQEkQck3W3GDnYklUA4btOW5c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReplyDetailActivity.m233initListener$lambda5(ReplyDetailActivity.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivImage)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyDetailActivity$hREKOfHM-ekrA5mmnTo28QBtMN8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReplyDetailActivity.m234initListener$lambda6(ReplyDetailActivity.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tvReply)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyDetailActivity$2-PDGSNnuXAMgOypqEoWn8Ijq0M
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReplyDetailActivity.m235initListener$lambda7(ReplyDetailActivity.this, view);
            }
        });
        KeyboardUtils.registerSoftInputChangedListener(getWindow(), new KeyboardUtils.OnSoftInputChangedListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyDetailActivity$UkHlOQRZZGWLOed_ydERBNoCAKg
            @Override // com.movieboxpro.android.utils.KeyboardUtils.OnSoftInputChangedListener
            public final void onSoftInputChanged(int i) {
                ReplyDetailActivity.m236initListener$lambda8(ReplyDetailActivity.this, i);
            }
        });
        CommBaseAdapter<ReviewImage> commBaseAdapter = this.sendImageAdapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sendImageAdapter");
            commBaseAdapter = null;
        }
        commBaseAdapter.setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyDetailActivity$VT6-j6qQUZNWq_Ng2gMNoAxVfY4
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ReplyDetailActivity.m237initListener$lambda9(ReplyDetailActivity.this, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m229initListener$lambda0(ReplyDetailActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (Intrinsics.areEqual(this$0.currSort, "update_time_desc")) {
            ((ImageView) this$0._$_findCachedViewById(R.id.iv_right)).setImageResource(R.mipmap.ic_review_sort_time);
            PrefsUtils.getInstance().putString(Constant.Prefs.REVIEW_SORT, "dateline_asc");
            this$0.currSort = "dateline_asc";
        } else {
            ((ImageView) this$0._$_findCachedViewById(R.id.iv_right)).setImageResource(R.mipmap.ic_review_sort_default);
            this$0.currSort = "update_time_desc";
            PrefsUtils.getInstance().putString(Constant.Prefs.REVIEW_SORT, "update_time_desc");
        }
        ReplyDetailListFragment replyDetailListFragment = this$0.fragment;
        if (replyDetailListFragment == null) {
            return;
        }
        replyDetailListFragment.refreshSort(this$0.currSort);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m230initListener$lambda1(ReplyDetailActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (((TextView) this$0._$_findCachedViewById(R.id.tvReply)).getVisibility() == 8) {
            this$0.replyId = "";
            KeyboardUtils.showSoftInput((EditText) this$0._$_findCachedViewById(R.id.etContent));
            TextView tvReply = (TextView) this$0._$_findCachedViewById(R.id.tvReply);
            Intrinsics.checkNotNullExpressionValue(tvReply, "tvReply");
            CommonExtKt.gone(tvReply);
            EditText etContent = (EditText) this$0._$_findCachedViewById(R.id.etContent);
            Intrinsics.checkNotNullExpressionValue(etContent, "etContent");
            CommonExtKt.visible(etContent);
            ((EditText) this$0._$_findCachedViewById(R.id.etContent)).requestFocus();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m231initListener$lambda2(ReplyDetailActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (((User[]) ((EditText) this$0._$_findCachedViewById(R.id.etContent)).getText().getSpans(0, ((EditText) this$0._$_findCachedViewById(R.id.etContent)).length(), User.class)).length >= 10) {
            ToastUtils.showShort("Choose up to 10 at a time", new Object[0]);
            return;
        }
        KeyboardUtils.hideSoftInput((EditText) this$0._$_findCachedViewById(R.id.etContent));
        Object as = Observable.timer(200L, TimeUnit.MILLISECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this$0));
        Intrinsics.checkNotNullExpressionValue(as, "timer(200, TimeUnit.MILL…bindLifecycleOwner(this))");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, null, null, null, null, ReplyDetailActivity$initListener$3$1.INSTANCE, 15, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-3  reason: not valid java name */
    public static final void m232initListener$lambda3(ReplyDetailActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-5  reason: not valid java name */
    public static final void m233initListener$lambda5(ReplyDetailActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.sendReview();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-6  reason: not valid java name */
    public static final void m234initListener$lambda6(ReplyDetailActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        KeyboardUtils.hideSoftInput((EditText) this$0._$_findCachedViewById(R.id.etContent));
        AlbumBuilder fileProviderAuthority = EasyPhotos.createAlbum((FragmentActivity) this$0, true, (ImageEngine) ChooseImageGlideEngine.getInstance()).setFileProviderAuthority("com.movieboxpro.android.fileProvider");
        CommBaseAdapter<ReviewImage> commBaseAdapter = this$0.sendImageAdapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sendImageAdapter");
            commBaseAdapter = null;
        }
        fileProviderAuthority.setCount(4 - commBaseAdapter.getData().size()).start(100);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-7  reason: not valid java name */
    public static final void m235initListener$lambda7(ReplyDetailActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.replyId = "";
        KeyboardUtils.showSoftInput((EditText) this$0._$_findCachedViewById(R.id.etContent));
        TextView tvReply = (TextView) this$0._$_findCachedViewById(R.id.tvReply);
        Intrinsics.checkNotNullExpressionValue(tvReply, "tvReply");
        CommonExtKt.gone(tvReply);
        EditText etContent = (EditText) this$0._$_findCachedViewById(R.id.etContent);
        Intrinsics.checkNotNullExpressionValue(etContent, "etContent");
        CommonExtKt.visible(etContent);
        ((EditText) this$0._$_findCachedViewById(R.id.etContent)).requestFocus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-8  reason: not valid java name */
    public static final void m236initListener$lambda8(ReplyDetailActivity this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i > 100) {
            String str = this$0.replyId;
            if (str == null || StringsKt.isBlank(str)) {
                TextView textView = (TextView) this$0._$_findCachedViewById(R.id.tvReplyAt);
                if (textView != null) {
                    CommonExtKt.gone(textView);
                }
            } else {
                TextView textView2 = (TextView) this$0._$_findCachedViewById(R.id.tvReplyAt);
                if (textView2 != null) {
                    CommonExtKt.visible(textView2);
                }
            }
            EditText editText = (EditText) this$0._$_findCachedViewById(R.id.etContent);
            if (editText != null) {
                CommonExtKt.visible(editText);
            }
            TextView textView3 = (TextView) this$0._$_findCachedViewById(R.id.tvReply);
            if (textView3 == null) {
                return;
            }
            CommonExtKt.gone(textView3);
            return;
        }
        TextView textView4 = (TextView) this$0._$_findCachedViewById(R.id.tvReplyAt);
        if (textView4 != null) {
            CommonExtKt.gone(textView4);
        }
        EditText editText2 = (EditText) this$0._$_findCachedViewById(R.id.etContent);
        if (editText2 != null) {
            CommonExtKt.gone(editText2);
        }
        TextView textView5 = (TextView) this$0._$_findCachedViewById(R.id.tvReply);
        if (textView5 == null) {
            return;
        }
        CommonExtKt.visible(textView5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-9  reason: not valid java name */
    public static final void m237initListener$lambda9(ReplyDetailActivity this$0, BaseQuickAdapter noName_0, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(view, "view");
        CommBaseAdapter<ReviewImage> commBaseAdapter = this$0.sendImageAdapter;
        CommBaseAdapter<ReviewImage> commBaseAdapter2 = null;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sendImageAdapter");
            commBaseAdapter = null;
        }
        commBaseAdapter.getItem(i);
        if (view.getId() == R.id.ivDelete) {
            CommBaseAdapter<ReviewImage> commBaseAdapter3 = this$0.sendImageAdapter;
            if (commBaseAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sendImageAdapter");
            } else {
                commBaseAdapter2 = commBaseAdapter3;
            }
            commBaseAdapter2.removeAt(i);
        }
    }

    private final void sendReview() {
        Editable text = ((EditText) _$_findCachedViewById(R.id.etContent)).getText();
        boolean z = false;
        User[] userArr = (User[]) text.getSpans(0, ((EditText) _$_findCachedViewById(R.id.etContent)).length(), User.class);
        if (userArr != null) {
            int length = userArr.length;
            int i = 0;
            while (i < length) {
                User user = userArr[i];
                i++;
                text.replace(text.getSpanStart(user), text.getSpanEnd(user), "[@]" + user.getId() + "[/@]");
            }
        }
        String obj = ((EditText) _$_findCachedViewById(R.id.etContent)).getText().toString();
        ((EditText) _$_findCachedViewById(R.id.etContent)).setText("");
        String str = this.replyId;
        if ((str == null || StringsKt.isBlank(str)) ? true : true) {
            this.replyId = this.commentId;
        }
        CommBaseAdapter<ReviewImage> commBaseAdapter = this.sendImageAdapter;
        CommBaseAdapter<ReviewImage> commBaseAdapter2 = null;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sendImageAdapter");
            commBaseAdapter = null;
        }
        if (commBaseAdapter.getData().isEmpty()) {
            ((ReplyDetailPresenter) this.mPresenter).sendReview(this.replyId, this.id, obj, this.boxType, new ArrayList<>());
            return;
        }
        CommBaseAdapter<ReviewImage> commBaseAdapter3 = this.sendImageAdapter;
        if (commBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sendImageAdapter");
        } else {
            commBaseAdapter2 = commBaseAdapter3;
        }
        Object as = Observable.just(commBaseAdapter2.getData()).map($$Lambda$ReplyDetailActivity$LbCW6oUbII_OWofwf625uLmbdRY.INSTANCE).map(new Function() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyDetailActivity$PIoyoW4s07xj8PnYuFAMtAr5NPw
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj2) {
                List m246sendReview$lambda13;
                m246sendReview$lambda13 = ReplyDetailActivity.m246sendReview$lambda13(ReplyDetailActivity.this, (ArrayList) obj2);
                return m246sendReview$lambda13;
            }
        }).flatMap($$Lambda$ReplyDetailActivity$V40igP_YcjJdSNNmx9etib4kQ0c.INSTANCE).flatMap(new Function() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyDetailActivity$pVxi001AlnNuOdnXsOV9un1D5pk
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj2) {
                ObservableSource m248sendReview$lambda15;
                m248sendReview$lambda15 = ReplyDetailActivity.m248sendReview$lambda15(ReplyDetailActivity.this, (File) obj2);
                return m248sendReview$lambda15;
            }
        }).toList().toObservable().map($$Lambda$ReplyDetailActivity$3AuSrOLnBkxjVwUUE0JQ8uhvfU.INSTANCE).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(as, "just(sendImageAdapter.da…bindLifecycleOwner(this))");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, new ReplyDetailActivity$sendReview$7(this), null, new ReplyDetailActivity$sendReview$8(this), null, new ReplyDetailActivity$sendReview$9(this, obj), 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendReview$lambda-12  reason: not valid java name */
    public static final ArrayList m245sendReview$lambda12(List it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ArrayList arrayList = new ArrayList();
        Iterator it2 = it.iterator();
        while (it2.hasNext()) {
            arrayList.add(((ReviewImage) it2.next()).getPath());
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendReview$lambda-13  reason: not valid java name */
    public static final List m246sendReview$lambda13(ReplyDetailActivity this$0, ArrayList it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        return Luban.with(this$0).load(it).get();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendReview$lambda-14  reason: not valid java name */
    public static final ObservableSource m247sendReview$lambda14(List it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Observable.fromIterable(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendReview$lambda-15  reason: not valid java name */
    public static final ObservableSource m248sendReview$lambda15(ReplyDetailActivity this$0, File it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(it.getPath(), options);
        int i = options.outHeight;
        int i2 = options.outWidth;
        HttpUploadRequest addBaseParams = new HttpUploadRequest(null, 1, null).addBaseParams(ApiUtils.INSTANCE.uploadReviewImage(this$0.boxType), "image/jpg", it, "comment_img");
        String str = this$0.id;
        if (str == null) {
            str = "";
        }
        return addBaseParams.addParam("mid", str).addParam("pid", this$0.id).addParam("actor_id", this$0.id).addParam("box_type", Integer.valueOf(this$0.boxType)).addParam("width", Integer.valueOf(i2)).addParam("height", Integer.valueOf(i)).asRequest().compose(RxUtils.rxTranslate2Bean(UploadImgResponse.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendReview$lambda-17  reason: not valid java name */
    public static final ArrayList m249sendReview$lambda17(List it) {
        String img_id;
        Intrinsics.checkNotNullParameter(it, "it");
        ArrayList arrayList = new ArrayList();
        Iterator it2 = it.iterator();
        while (it2.hasNext()) {
            UploadImgResponse.UploadImage img = ((UploadImgResponse) it2.next()).getImg();
            String str = "";
            if (img != null && (img_id = img.getImg_id()) != null) {
                str = img_id;
            }
            arrayList.add(str);
        }
        return arrayList;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initView() {
        ((FrameLayout) _$_findCachedViewById(R.id.frameLayout)).setBackgroundColor(CommonExtKt.colorInt((Context) this, (int) R.color.color_main));
        this.methodContext.setMethod(Weibo.INSTANCE);
        MethodContext methodContext = this.methodContext;
        EditText etContent = (EditText) _$_findCachedViewById(R.id.etContent);
        Intrinsics.checkNotNullExpressionValue(etContent, "etContent");
        methodContext.init(etContent);
        ((ImageView) _$_findCachedViewById(R.id.ivSend)).setEnabled(false);
        ((TextView) _$_findCachedViewById(R.id.tv_title)).setText("Comment");
        int dp2Px = CommonExtKt.dp2Px(5);
        ((ImageView) _$_findCachedViewById(R.id.iv_right)).setPadding(dp2Px, dp2Px, dp2Px, dp2Px);
        ImageView iv_right = (ImageView) _$_findCachedViewById(R.id.iv_right);
        Intrinsics.checkNotNullExpressionValue(iv_right, "iv_right");
        CommonExtKt.visible(iv_right);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initData() {
        if (Intrinsics.areEqual(PrefsUtils.getInstance().getString(Constant.Prefs.REVIEW_SORT, "update_time_desc"), "update_time_desc")) {
            this.currSort = "update_time_desc";
            ((ImageView) _$_findCachedViewById(R.id.iv_right)).setImageResource(R.mipmap.ic_review_sort_default);
        } else {
            this.currSort = "dateline_asc";
            ((ImageView) _$_findCachedViewById(R.id.iv_right)).setImageResource(R.mipmap.ic_review_sort_time);
        }
        String stringExtra = getIntent().getStringExtra("id");
        if (stringExtra == null) {
            stringExtra = "";
        }
        this.id = stringExtra;
        this.boxType = getIntent().getIntExtra("boxType", 1);
        this.commentId = getIntent().getStringExtra("commentId");
        CommBaseAdapter<ReviewImage> commBaseAdapter = new CommBaseAdapter<>(R.layout.adapter_send_review_item, new ReplyDetailActivity$initData$1(this), null, 4, null);
        this.sendImageAdapter = commBaseAdapter;
        CommBaseAdapter<ReviewImage> commBaseAdapter2 = null;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sendImageAdapter");
            commBaseAdapter = null;
        }
        commBaseAdapter.addChildClickViewIds(R.id.flDelete);
        ((RecyclerView) _$_findCachedViewById(R.id.rvImage)).setLayoutManager(new GridLayoutManager(this, 3));
        ((RecyclerView) _$_findCachedViewById(R.id.rvImage)).addItemDecoration(new GridSpacingItemDecoration(3, CommonExtKt.dp2Px(8), true));
        RecyclerView rvImage = (RecyclerView) _$_findCachedViewById(R.id.rvImage);
        Intrinsics.checkNotNullExpressionValue(rvImage, "rvImage");
        CommonExtKt.disableRefreshAnimation(rvImage);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvImage);
        CommBaseAdapter<ReviewImage> commBaseAdapter3 = this.sendImageAdapter;
        if (commBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sendImageAdapter");
            commBaseAdapter3 = null;
        }
        recyclerView.setAdapter(commBaseAdapter3);
        CommBaseAdapter<ReviewImage> commBaseAdapter4 = this.sendImageAdapter;
        if (commBaseAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sendImageAdapter");
        } else {
            commBaseAdapter2 = commBaseAdapter4;
        }
        commBaseAdapter2.addChildClickViewIds(R.id.ivDelete);
        ReviewItem reviewItem = (ReviewItem) getIntent().getParcelableExtra("data");
        if (reviewItem != null) {
            initFragment(reviewItem);
        } else {
            ((ReplyDetailPresenter) this.mPresenter).getCommentDetail(this.commentId, this.boxType);
        }
    }

    private final void initFragment(ReviewItem reviewItem) {
        ReplyDetailListFragment newInstance = ReplyDetailListFragment.Companion.newInstance(this.id, this.boxType, this.commentId, reviewItem, this.currSort);
        this.fragment = newInstance;
        Intrinsics.checkNotNull(newInstance);
        newInstance.setListener(new ReplyDetailActivity$initFragment$1(this));
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        ReplyDetailListFragment replyDetailListFragment = this.fragment;
        Intrinsics.checkNotNull(replyDetailListFragment);
        FragmentUtils.add(supportFragmentManager, replyDetailListFragment, (int) R.id.container);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    public ReplyDetailPresenter bindPresenter() {
        return new ReplyDetailPresenter(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        KeyboardUtils.unregisterSoftInputChangedListener(getWindow());
        super.onStop();
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override // com.movieboxpro.android.view.activity.ReplyDetailContract.View
    public void reviewSuccess() {
        ((EditText) _$_findCachedViewById(R.id.etContent)).setText("");
        ((EditText) _$_findCachedViewById(R.id.etContent)).postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyDetailActivity$Wc_ZxiiNrlreceRaU-JB90c_dgM
            @Override // java.lang.Runnable
            public final void run() {
                ReplyDetailActivity.m244reviewSuccess$lambda18(ReplyDetailActivity.this);
            }
        }, 500L);
        TextView tvReplyAt = (TextView) _$_findCachedViewById(R.id.tvReplyAt);
        Intrinsics.checkNotNullExpressionValue(tvReplyAt, "tvReplyAt");
        CommonExtKt.gone(tvReplyAt);
        if (!Intrinsics.areEqual(this.replyId, this.commentId)) {
            Companion.start(this, this.id, this.boxType, this.replyId, null);
        }
        ReplyDetailListFragment replyDetailListFragment = this.fragment;
        if (replyDetailListFragment != null) {
            replyDetailListFragment.reviewRefresh(this.currReplyPos, Intrinsics.areEqual(this.replyId, this.commentId), this.replyId);
        }
        this.replyId = "";
        CommBaseAdapter<ReviewImage> commBaseAdapter = this.sendImageAdapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sendImageAdapter");
            commBaseAdapter = null;
        }
        commBaseAdapter.setList(new ArrayList());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: reviewSuccess$lambda-18  reason: not valid java name */
    public static final void m244reviewSuccess$lambda18(ReplyDetailActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        KeyboardUtils.hideSoftInput((EditText) this$0._$_findCachedViewById(R.id.etContent));
    }

    @Override // com.movieboxpro.android.view.activity.ReplyDetailContract.View
    public void showReview(ReviewItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        initFragment(item);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 100 && intent != null) {
            ArrayList<Photo> parcelableArrayListExtra = intent.getParcelableArrayListExtra(EasyPhotos.RESULT_PHOTOS);
            ArrayList arrayList = new ArrayList();
            if (parcelableArrayListExtra != null) {
                for (Photo photo : parcelableArrayListExtra) {
                    ReviewImage reviewImage = new ReviewImage();
                    reviewImage.setUri(photo.uri);
                    reviewImage.setPath(photo.path);
                    arrayList.add(reviewImage);
                }
            }
            CommBaseAdapter<ReviewImage> commBaseAdapter = this.sendImageAdapter;
            if (commBaseAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sendImageAdapter");
                commBaseAdapter = null;
            }
            commBaseAdapter.addData(arrayList);
            RecyclerView rvImage = (RecyclerView) _$_findCachedViewById(R.id.rvImage);
            Intrinsics.checkNotNullExpressionValue(rvImage, "rvImage");
            CommonExtKt.visible(rvImage);
        }
    }

    /* compiled from: ReplyDetailActivity.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J8\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0007J,\u0010\u000e\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\b¨\u0006\u000f"}, d2 = {"Lcom/movieboxpro/android/view/activity/ReplyDetailActivity$Companion;", "", "()V", TtmlNode.START, "", "context", "Landroid/content/Context;", "id", "", "boxType", "", "commentId", "reviewModel", "Lcom/movieboxpro/android/model/ReviewItem;", "startNewTask", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final void start(Context context, String str, int i, String str2, ReviewItem reviewItem) {
            int i2 = 0;
            Pair[] pairArr = {TuplesKt.to("id", str), TuplesKt.to("boxType", Integer.valueOf(i)), TuplesKt.to("commentId", str2), TuplesKt.to("data", reviewItem)};
            Intent intent = new Intent(context, ReplyDetailActivity.class);
            Bundle bundle = new Bundle(4);
            while (i2 < 4) {
                Pair pair = pairArr[i2];
                i2++;
                String str3 = (String) pair.component1();
                Object component2 = pair.component2();
                if (component2 == null) {
                    bundle.putString(str3, null);
                } else if (component2 instanceof Boolean) {
                    bundle.putBoolean(str3, ((Boolean) component2).booleanValue());
                } else if (component2 instanceof Byte) {
                    bundle.putByte(str3, ((Number) component2).byteValue());
                } else if (component2 instanceof Character) {
                    bundle.putChar(str3, ((Character) component2).charValue());
                } else if (component2 instanceof Double) {
                    bundle.putDouble(str3, ((Number) component2).doubleValue());
                } else if (component2 instanceof Float) {
                    bundle.putFloat(str3, ((Number) component2).floatValue());
                } else if (component2 instanceof Integer) {
                    bundle.putInt(str3, ((Number) component2).intValue());
                } else if (component2 instanceof Long) {
                    bundle.putLong(str3, ((Number) component2).longValue());
                } else if (component2 instanceof Short) {
                    bundle.putShort(str3, ((Number) component2).shortValue());
                } else if (component2 instanceof Bundle) {
                    bundle.putBundle(str3, (Bundle) component2);
                } else if (component2 instanceof CharSequence) {
                    bundle.putCharSequence(str3, (CharSequence) component2);
                } else if (component2 instanceof Parcelable) {
                    bundle.putParcelable(str3, (Parcelable) component2);
                } else if (component2 instanceof boolean[]) {
                    bundle.putBooleanArray(str3, (boolean[]) component2);
                } else if (component2 instanceof byte[]) {
                    bundle.putByteArray(str3, (byte[]) component2);
                } else if (component2 instanceof char[]) {
                    bundle.putCharArray(str3, (char[]) component2);
                } else if (component2 instanceof double[]) {
                    bundle.putDoubleArray(str3, (double[]) component2);
                } else if (component2 instanceof float[]) {
                    bundle.putFloatArray(str3, (float[]) component2);
                } else if (component2 instanceof int[]) {
                    bundle.putIntArray(str3, (int[]) component2);
                } else if (component2 instanceof long[]) {
                    bundle.putLongArray(str3, (long[]) component2);
                } else if (component2 instanceof short[]) {
                    bundle.putShortArray(str3, (short[]) component2);
                } else if (component2 instanceof Object[]) {
                    Class<?> componentType = component2.getClass().getComponentType();
                    Intrinsics.checkNotNull(componentType);
                    if (Parcelable.class.isAssignableFrom(componentType)) {
                        if (component2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<android.os.Parcelable>");
                        }
                        bundle.putParcelableArray(str3, (Parcelable[]) component2);
                    } else if (String.class.isAssignableFrom(componentType)) {
                        if (component2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.String>");
                        }
                        bundle.putStringArray(str3, (String[]) component2);
                    } else if (CharSequence.class.isAssignableFrom(componentType)) {
                        if (component2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.CharSequence>");
                        }
                        bundle.putCharSequenceArray(str3, (CharSequence[]) component2);
                    } else if (Serializable.class.isAssignableFrom(componentType)) {
                        bundle.putSerializable(str3, (Serializable) component2);
                    } else {
                        String canonicalName = componentType.getCanonicalName();
                        throw new IllegalArgumentException("Illegal value array type " + ((Object) canonicalName) + " for key \"" + str3 + Typography.quote);
                    }
                } else if (component2 instanceof Serializable) {
                    bundle.putSerializable(str3, (Serializable) component2);
                } else if (Build.VERSION.SDK_INT >= 18 && (component2 instanceof Binder)) {
                    bundle.putBinder(str3, (IBinder) component2);
                } else if (Build.VERSION.SDK_INT >= 21 && (component2 instanceof Size)) {
                    bundle.putSize(str3, (Size) component2);
                } else if (Build.VERSION.SDK_INT >= 21 && (component2 instanceof SizeF)) {
                    bundle.putSizeF(str3, (SizeF) component2);
                } else {
                    String canonicalName2 = component2.getClass().getCanonicalName();
                    throw new IllegalArgumentException("Illegal value type " + ((Object) canonicalName2) + " for key \"" + str3 + Typography.quote);
                }
            }
            intent.putExtras(bundle);
            if (context == null) {
                return;
            }
            context.startActivity(intent);
        }

        public final void startNewTask(Context context, String str, int i, String str2) {
            int i2 = 0;
            Pair[] pairArr = {TuplesKt.to("id", str), TuplesKt.to("boxType", Integer.valueOf(i)), TuplesKt.to("commentId", str2)};
            Intent intent = new Intent(context, ReplyDetailActivity.class);
            Bundle bundle = new Bundle(3);
            while (i2 < 3) {
                Pair pair = pairArr[i2];
                i2++;
                String str3 = (String) pair.component1();
                Object component2 = pair.component2();
                if (component2 == null) {
                    bundle.putString(str3, null);
                } else if (component2 instanceof Boolean) {
                    bundle.putBoolean(str3, ((Boolean) component2).booleanValue());
                } else if (component2 instanceof Byte) {
                    bundle.putByte(str3, ((Number) component2).byteValue());
                } else if (component2 instanceof Character) {
                    bundle.putChar(str3, ((Character) component2).charValue());
                } else if (component2 instanceof Double) {
                    bundle.putDouble(str3, ((Number) component2).doubleValue());
                } else if (component2 instanceof Float) {
                    bundle.putFloat(str3, ((Number) component2).floatValue());
                } else if (component2 instanceof Integer) {
                    bundle.putInt(str3, ((Number) component2).intValue());
                } else if (component2 instanceof Long) {
                    bundle.putLong(str3, ((Number) component2).longValue());
                } else if (component2 instanceof Short) {
                    bundle.putShort(str3, ((Number) component2).shortValue());
                } else if (component2 instanceof Bundle) {
                    bundle.putBundle(str3, (Bundle) component2);
                } else if (component2 instanceof CharSequence) {
                    bundle.putCharSequence(str3, (CharSequence) component2);
                } else if (component2 instanceof Parcelable) {
                    bundle.putParcelable(str3, (Parcelable) component2);
                } else if (component2 instanceof boolean[]) {
                    bundle.putBooleanArray(str3, (boolean[]) component2);
                } else if (component2 instanceof byte[]) {
                    bundle.putByteArray(str3, (byte[]) component2);
                } else if (component2 instanceof char[]) {
                    bundle.putCharArray(str3, (char[]) component2);
                } else if (component2 instanceof double[]) {
                    bundle.putDoubleArray(str3, (double[]) component2);
                } else if (component2 instanceof float[]) {
                    bundle.putFloatArray(str3, (float[]) component2);
                } else if (component2 instanceof int[]) {
                    bundle.putIntArray(str3, (int[]) component2);
                } else if (component2 instanceof long[]) {
                    bundle.putLongArray(str3, (long[]) component2);
                } else if (component2 instanceof short[]) {
                    bundle.putShortArray(str3, (short[]) component2);
                } else if (component2 instanceof Object[]) {
                    Class<?> componentType = component2.getClass().getComponentType();
                    Intrinsics.checkNotNull(componentType);
                    if (Parcelable.class.isAssignableFrom(componentType)) {
                        if (component2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<android.os.Parcelable>");
                        }
                        bundle.putParcelableArray(str3, (Parcelable[]) component2);
                    } else if (String.class.isAssignableFrom(componentType)) {
                        if (component2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.String>");
                        }
                        bundle.putStringArray(str3, (String[]) component2);
                    } else if (CharSequence.class.isAssignableFrom(componentType)) {
                        if (component2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.CharSequence>");
                        }
                        bundle.putCharSequenceArray(str3, (CharSequence[]) component2);
                    } else if (Serializable.class.isAssignableFrom(componentType)) {
                        bundle.putSerializable(str3, (Serializable) component2);
                    } else {
                        String canonicalName = componentType.getCanonicalName();
                        throw new IllegalArgumentException("Illegal value array type " + ((Object) canonicalName) + " for key \"" + str3 + Typography.quote);
                    }
                } else if (component2 instanceof Serializable) {
                    bundle.putSerializable(str3, (Serializable) component2);
                } else if (Build.VERSION.SDK_INT >= 18 && (component2 instanceof Binder)) {
                    bundle.putBinder(str3, (IBinder) component2);
                } else if (Build.VERSION.SDK_INT >= 21 && (component2 instanceof Size)) {
                    bundle.putSize(str3, (Size) component2);
                } else if (Build.VERSION.SDK_INT >= 21 && (component2 instanceof SizeF)) {
                    bundle.putSizeF(str3, (SizeF) component2);
                } else {
                    String canonicalName2 = component2.getClass().getCanonicalName();
                    throw new IllegalArgumentException("Illegal value type " + ((Object) canonicalName2) + " for key \"" + str3 + Typography.quote);
                }
            }
            intent.putExtras(bundle);
            intent.setFlags(268435456);
            if (context == null) {
                return;
            }
            context.startActivity(intent);
        }
    }

    /* compiled from: ReplyDetailActivity.kt */
    @Metadata(d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 N2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002NOB\u0005¢\u0006\u0002\u0010\u0004J\u001c\u0010\u0015\u001a\u00020\u00162\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00190\u0018H\u0014J\u001a\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J\b\u0010\u001c\u001a\u00020\u0016H\u0014J\b\u0010\u001d\u001a\u00020\u001eH\u0014J\u0012\u0010\u001f\u001a\u00020\u00162\b\u0010 \u001a\u0004\u0018\u00010!H\u0014J\u0016\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00020#2\u0006\u0010$\u001a\u00020\u0003H\u0014J\u0012\u0010%\u001a\u00020\u00162\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J\u000e\u0010&\u001a\b\u0012\u0004\u0012\u00020\n0'H\u0014J\u001c\u0010(\u001a\u00020\u00162\b\u0010\f\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J\u0018\u0010)\u001a\u00020\u00162\u0006\u0010*\u001a\u00020\u00192\u0006\u0010+\u001a\u00020\u0002H\u0014J\b\u0010,\u001a\u00020\bH\u0014J\u0010\u0010-\u001a\u00020\b2\u0006\u0010$\u001a\u00020\u0002H\u0014J \u0010.\u001a\u00020\u00162\u0006\u0010*\u001a\u00020\u00192\u0006\u0010+\u001a\u00020\u00022\u0006\u0010/\u001a\u00020\u0002H\u0002J\u0010\u00100\u001a\u00020\u00162\u0006\u00101\u001a\u000202H\u0014J\b\u00103\u001a\u00020\u001eH\u0014J\b\u00104\u001a\u00020\u001eH\u0014J\b\u00105\u001a\u000206H\u0014J\b\u00107\u001a\u000208H\u0014J\u000e\u00109\u001a\u00020\u00162\u0006\u0010\u000f\u001a\u00020\nJ\u0016\u0010:\u001a\u00020\u00162\f\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00020<H\u0014J@\u0010=\u001a\u00020\u00162\b\u0010>\u001a\u0004\u0018\u00010\u00062\b\u0010?\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u001b\u001a\u00020\b2\b\u0010@\u001a\u0004\u0018\u00010\n2\u0006\u0010A\u001a\u00020\bH\u0002J \u0010B\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010C\u001a\u00020\u001e2\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u000e\u0010D\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\u000eJ \u0010E\u001a\u00020\u00162\f\u0010F\u001a\b\u0012\u0004\u0012\u00020G0#2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J2\u0010H\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\b2\b\u0010>\u001a\u0004\u0018\u00010\u00062\u000e\u0010I\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010J2\u0006\u0010K\u001a\u00020LH\u0002J\b\u0010M\u001a\u00020\u001eH\u0014R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0011j\b\u0012\u0004\u0012\u00020\u0002`\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006P"}, d2 = {"Lcom/movieboxpro/android/view/activity/ReplyDetailActivity$ReplyDetailListFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/ReviewItem;", "Lcom/movieboxpro/android/model/ReviewResponse;", "()V", "bottomViewLine", "Landroid/view/View;", "boxType", "", "commentId", "", "footView", "id", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/activity/ReplyDetailActivity$ReplyDetailListFragment$LoadingListener;", "sort", "topList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "upCurrPage", "upPageSize", "addOnItemClickViews", "", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "deleteComment", "position", "doUpFetchLoading", "enableMultiAdapter", "", "getBundle", "arguments", "Landroid/os/Bundle;", "getData", "", "model", "getReportReason", "getServiceData", "Lio/reactivex/Observable;", "getTopReviews", "initHolder", "helper", "item", "initItemLayout", "initItemType", "initItemView", "item2", "initRecyclerView", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "isOpenRefresh", "isUpFetchEnable", "onItemChildClick", "Lcom/chad/library/adapter/base/listener/OnItemChildClickListener;", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "refreshSort", "registerItemType", "multiTypeDelegate", "Lcom/chad/library/adapter/base/delegate/BaseMultiTypeDelegate;", "reportPopup", "view", "uid", "username", "blocked", "reviewRefresh", "refresh", "setListener", "showReportReason", "list", "Lcom/movieboxpro/android/model/ReportReason;", "toImageShow", "images", "", "imageView", "Landroid/widget/ImageView;", "upFetchLoading", "Companion", "LoadingListener", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class ReplyDetailListFragment extends BaseListFragment<ReviewItem, ReviewResponse> {
        public static final Companion Companion = new Companion(null);
        private View bottomViewLine;
        private String commentId;
        private View footView;
        private LoadingListener listener;
        public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
        private final ArrayList<ReviewItem> topList = new ArrayList<>();
        private int upCurrPage = 1;
        private int upPageSize = 10;
        private String id = "";
        private int boxType = 1;
        private String sort = "";

        /* compiled from: ReplyDetailActivity.kt */
        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H&J\b\u0010\u0007\u001a\u00020\u0003H&J\u001a\u0010\b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\nH&J$\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\r\u001a\u00020\nH&J\b\u0010\u000e\u001a\u00020\u0003H&¨\u0006\u000f"}, d2 = {"Lcom/movieboxpro/android/view/activity/ReplyDetailActivity$ReplyDetailListFragment$LoadingListener;", "", "at", "", "commentId", "", "username", "hideLoading", "likeReview", "support", "", "onReply", "id", "position", "showLoading", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        /* loaded from: classes3.dex */
        public interface LoadingListener {
            void at(String str, String str2);

            void hideLoading();

            void likeReview(String str, int i);

            void onReply(String str, String str2, int i);

            void showLoading();
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

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected boolean enableMultiAdapter() {
            return true;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected int initItemLayout() {
            return -1;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected boolean isOpenRefresh() {
            return false;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected boolean isUpFetchEnable() {
            return true;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment, com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
        public /* synthetic */ void onDestroyView() {
            super.onDestroyView();
            _$_clearFindViewByIdCache();
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected boolean upFetchLoading() {
            return true;
        }

        /* compiled from: ReplyDetailActivity.kt */
        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J2\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0006¨\u0006\r"}, d2 = {"Lcom/movieboxpro/android/view/activity/ReplyDetailActivity$ReplyDetailListFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/activity/ReplyDetailActivity$ReplyDetailListFragment;", "id", "", "boxType", "", "commentId", "reviewModel", "Lcom/movieboxpro/android/model/ReviewItem;", "sort", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        /* loaded from: classes3.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final ReplyDetailListFragment newInstance(String str, int i, String str2, ReviewItem reviewModel, String sort) {
                Intrinsics.checkNotNullParameter(reviewModel, "reviewModel");
                Intrinsics.checkNotNullParameter(sort, "sort");
                ReplyDetailListFragment replyDetailListFragment = new ReplyDetailListFragment();
                replyDetailListFragment.setArguments(CommonExtKt.bundleOf(TuplesKt.to("id", str), TuplesKt.to("boxType", Integer.valueOf(i)), TuplesKt.to("commentId", str2), TuplesKt.to("data", reviewModel), TuplesKt.to("sort", sort)));
                return replyDetailListFragment;
            }
        }

        public final void refreshSort(String sort) {
            Intrinsics.checkNotNullParameter(sort, "sort");
            this.sort = sort;
            ReplyDetailListFragment replyDetailListFragment = this;
            Observable<R> compose = HttpRequest.Companion.post(replyDetailListFragment, ApiUtils.INSTANCE.getCommentById(this.boxType)).param("box_type", Integer.valueOf(this.boxType)).param("mid", this.id).param("pid", this.id).param("actor_id", this.id).param("comment_id", this.commentId).param(IjkMediaMeta.IJKM_KEY_TYPE, (Object) 2).param("page", (Object) 1).param("pagelimit", Integer.valueOf(this.mPageSize)).param("sort", sort).asRequest().compose(RxUtils.rxTranslate2Bean(ReviewResponse.class));
            Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
            RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(compose, replyDetailListFragment), new ReplyDetailActivity$ReplyDetailListFragment$refreshSort$1(this), null, new ReplyDetailActivity$ReplyDetailListFragment$refreshSort$2(this), null, new ReplyDetailActivity$ReplyDetailListFragment$refreshSort$3(this), 10, null);
        }

        public final void setListener(LoadingListener listener) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            this.listener = listener;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected Observable<String> getServiceData() {
            return HttpRequest.Companion.post(this, ApiUtils.INSTANCE.getCommentById(this.boxType)).param("box_type", Integer.valueOf(this.boxType)).param("mid", this.id).param("pid", this.id).param("actor_id", this.id).param("comment_id", this.commentId).param(IjkMediaMeta.IJKM_KEY_TYPE, (Object) 2).param("page", Integer.valueOf(this.mCurrentPage)).param("pagelimit", Integer.valueOf(this.mPageSize)).param("sort", this.sort).asRequest();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public int initItemType(ReviewItem model) {
            Intrinsics.checkNotNullParameter(model, "model");
            return model.getItemType();
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void doUpFetchLoading() {
            this.upCurrPage++;
            ReplyDetailListFragment replyDetailListFragment = this;
            Observable<R> compose = HttpRequest.Companion.post(replyDetailListFragment, ApiUtils.INSTANCE.getCommentById(this.boxType)).param("box_type", Integer.valueOf(this.boxType)).param("mid", this.id).param("pid", this.id).param("actor_id", this.id).param("comment_id", this.commentId).param(IjkMediaMeta.IJKM_KEY_TYPE, (Object) 0).param("page", Integer.valueOf(this.upCurrPage)).param("pagelimit", Integer.valueOf(this.upPageSize)).asRequest().compose(RxUtils.rxTranslate2Bean(ReviewResponse.class));
            Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
            RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(compose, replyDetailListFragment), new ReplyDetailActivity$ReplyDetailListFragment$doUpFetchLoading$1(this), null, new ReplyDetailActivity$ReplyDetailListFragment$doUpFetchLoading$2(this), null, new ReplyDetailActivity$ReplyDetailListFragment$doUpFetchLoading$3(this), 10, null);
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void registerItemType(BaseMultiTypeDelegate<ReviewItem> multiTypeDelegate) {
            Intrinsics.checkNotNullParameter(multiTypeDelegate, "multiTypeDelegate");
            multiTypeDelegate.addItemType(0, R.layout.adapter_review_top_item);
            multiTypeDelegate.addItemType(2, R.layout.adapter_review_top_main_item);
            multiTypeDelegate.addItemType(1, R.layout.adapter_reviews_detail_item);
            multiTypeDelegate.addItemType(3, R.layout.adapter_reviews_detail_nested_item);
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void getBundle(Bundle bundle) {
            String string;
            String string2;
            this.mClass = ReviewItem.class;
            this.mPageClass = ReviewResponse.class;
            String str = "";
            if (bundle == null || (string = bundle.getString("id")) == null) {
                string = "";
            }
            this.id = string;
            this.boxType = bundle != null ? bundle.getInt("boxType", 1) : 1;
            this.commentId = bundle == null ? null : bundle.getString("commentId");
            if (bundle != null && (string2 = bundle.getString("sort")) != null) {
                str = string2;
            }
            this.sort = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void getTopReviews(String str, String str2) {
            ViewGroup.LayoutParams layoutParams;
            if (!this.mAdapter.getData().isEmpty()) {
                View viewByPosition = this.mAdapter.getViewByPosition(0, R.id.line);
                int[] iArr = new int[2];
                if (viewByPosition != null) {
                    viewByPosition.getLocationOnScreen(iArr);
                }
                if (this.mAdapter.getData().size() >= 2) {
                    View viewByPosition2 = this.mAdapter.getViewByPosition(this.mAdapter.getData().size() - 1, R.id.line);
                    int[] iArr2 = new int[2];
                    if (viewByPosition2 != null) {
                        viewByPosition2.getLocationOnScreen(iArr2);
                    }
                    if (iArr2[1] != 0) {
                        int screenHeight = ((ScreenUtils.getScreenHeight() - iArr2[1]) - CommonExtKt.dp2Px(48)) - ImmersionBar.getStatusBarHeight(this);
                        View view = this.footView;
                        layoutParams = view != null ? view.getLayoutParams() : null;
                        if (layoutParams != null) {
                            layoutParams.height = screenHeight;
                        }
                        View view2 = this.footView;
                        if (view2 != null) {
                            view2.setLayoutParams(layoutParams);
                        }
                    } else {
                        View view3 = this.footView;
                        layoutParams = view3 != null ? view3.getLayoutParams() : null;
                        if (layoutParams != null) {
                            layoutParams.height = 0;
                        }
                        View view4 = this.footView;
                        if (view4 != null) {
                            view4.setLayoutParams(layoutParams);
                        }
                    }
                } else {
                    int screenHeight2 = ((ScreenUtils.getScreenHeight() - iArr[1]) - CommonExtKt.dp2Px(48)) - ImmersionBar.getStatusBarHeight(this);
                    View view5 = this.footView;
                    layoutParams = view5 != null ? view5.getLayoutParams() : null;
                    if (layoutParams != null) {
                        layoutParams.height = screenHeight2;
                    }
                    View view6 = this.footView;
                    if (view6 != null) {
                        view6.setLayoutParams(layoutParams);
                    }
                }
            }
            ReplyDetailListFragment replyDetailListFragment = this;
            Observable<R> compose = HttpRequest.Companion.post(replyDetailListFragment, ApiUtils.INSTANCE.getCommentById(this.boxType)).param("box_type", Integer.valueOf(this.boxType)).param("mid", str).param("pid", str).param("actor_id", str).param("comment_id", str2).param(IjkMediaMeta.IJKM_KEY_TYPE, (Object) 0).param("page", Integer.valueOf(this.upCurrPage)).param("pagelimit", Integer.valueOf(this.upPageSize)).asRequest().compose(RxUtils.rxTranslate2Bean(ReviewResponse.class));
            Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
            RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(compose, replyDetailListFragment), new ReplyDetailActivity$ReplyDetailListFragment$getTopReviews$1(this), null, new ReplyDetailActivity$ReplyDetailListFragment$getTopReviews$2(this), null, new ReplyDetailActivity$ReplyDetailListFragment$getTopReviews$3(this), 10, null);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public List<ReviewItem> getData(ReviewResponse model) {
            Intrinsics.checkNotNullParameter(model, "model");
            List<ReviewItem> list = model.getList();
            if (list != null) {
                for (ReviewItem reviewItem : list) {
                    List<ReviewItem> son_reply = reviewItem.getSon_reply();
                    if (son_reply == null || son_reply.isEmpty()) {
                        reviewItem.setItemType(1);
                    } else {
                        reviewItem.setItemType(3);
                    }
                }
            }
            if (this.mCurrentPage == 1) {
                ArrayList arrayList = new ArrayList();
                if (this.topList.isEmpty()) {
                    Object as = Observable.timer(500L, TimeUnit.MILLISECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
                    Intrinsics.checkNotNullExpressionValue(as, "timer(500, TimeUnit.MILL…bindLifecycleOwner(this))");
                    RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, null, null, null, null, new ReplyDetailActivity$ReplyDetailListFragment$getData$2(this), 15, null);
                    this.topList.clear();
                    Bundle arguments = getArguments();
                    ReviewItem reviewItem2 = arguments == null ? null : (ReviewItem) arguments.getParcelable("data");
                    Intrinsics.checkNotNull(reviewItem2);
                    Intrinsics.checkNotNullExpressionValue(reviewItem2, "arguments?.getParcelable<ReviewItem>(\"data\")!!");
                    reviewItem2.setItemType(2);
                    arrayList.add(reviewItem2);
                    List<ReviewItem> list2 = model.getList();
                    if (list2 == null) {
                        list2 = new ArrayList<>();
                    }
                    arrayList.addAll(list2);
                } else {
                    List<ReviewItem> list3 = model.getList();
                    if (list3 == null) {
                        list3 = new ArrayList<>();
                    }
                    arrayList.addAll(list3);
                }
                return arrayList;
            }
            List<ReviewItem> list4 = model.getList();
            if (list4 == null) {
                list4 = new ArrayList<>();
            }
            return new ArrayList(list4);
        }

        public final void reviewRefresh(int i, boolean z, String str) {
            ReviewItem reviewItem;
            ReviewItem reviewItem2;
            ReviewItem reviewItem3;
            if (!z) {
                ReviewItem reviewItem4 = (ReviewItem) this.mAdapter.getItemOrNull(i);
                if (reviewItem4 != null) {
                    if (Intrinsics.areEqual(reviewItem4.getComment_id(), str)) {
                        ReviewItem reviewItem5 = (ReviewItem) this.mAdapter.getItem(i);
                        Integer reply = ((ReviewItem) this.mAdapter.getItem(i)).getReply();
                        reviewItem5.setReply(reply != null ? Integer.valueOf(reply.intValue() + 1) : null);
                        this.mAdapter.notifyItemChanged(i);
                        return;
                    }
                    List<ReviewItem> son_reply = ((ReviewItem) this.mAdapter.getItem(i)).getSon_reply();
                    if (son_reply != null && (reviewItem = (ReviewItem) CollectionsKt.firstOrNull((List<? extends Object>) son_reply)) != null) {
                        Integer reply2 = reviewItem.getReply();
                        reviewItem.setReply(reply2 != null ? Integer.valueOf(reply2.intValue() + 1) : null);
                    }
                    this.mAdapter.notifyItemChanged(i);
                    return;
                }
                return;
            }
            ReplyDetailListFragment replyDetailListFragment = this;
            Observable<R> compose = HttpRequest.Companion.post(replyDetailListFragment, ApiUtils.INSTANCE.getCommentById(this.boxType)).param("box_type", Integer.valueOf(this.boxType)).param("mid", this.id).param("pid", this.id).param("actor_id", this.id).param("comment_id", str).param(IjkMediaMeta.IJKM_KEY_TYPE, (Object) 2).param("page", (Object) 1).param("pagelimit", Integer.valueOf(this.mPageSize)).param("sort", this.sort).asRequest().compose(RxUtils.rxTranslate2Bean(ReviewResponse.class));
            Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
            RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(compose, replyDetailListFragment), new ReplyDetailActivity$ReplyDetailListFragment$reviewRefresh$1(this), null, new ReplyDetailActivity$ReplyDetailListFragment$reviewRefresh$2(this), null, new ReplyDetailActivity$ReplyDetailListFragment$reviewRefresh$3(this), 10, null);
            if (i == -1 || (reviewItem2 = (ReviewItem) this.mAdapter.getItemOrNull(i)) == null) {
                return;
            }
            if (Intrinsics.areEqual(reviewItem2.getComment_id(), str)) {
                ReviewItem reviewItem6 = (ReviewItem) this.mAdapter.getItem(i);
                Integer reply3 = ((ReviewItem) this.mAdapter.getItem(i)).getReply();
                reviewItem6.setReply(reply3 != null ? Integer.valueOf(reply3.intValue() + 1) : null);
                this.mAdapter.notifyItemChanged(i);
                return;
            }
            List<ReviewItem> son_reply2 = ((ReviewItem) this.mAdapter.getItem(i)).getSon_reply();
            if (son_reply2 != null && (reviewItem3 = (ReviewItem) CollectionsKt.firstOrNull((List<? extends Object>) son_reply2)) != null) {
                Integer reply4 = reviewItem3.getReply();
                reviewItem3.setReply(reply4 != null ? Integer.valueOf(reply4.intValue() + 1) : null);
            }
            this.mAdapter.notifyItemChanged(i);
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
                builder.asImageViewer((ImageView) view, i, list, new OnSrcViewUpdateListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyDetailActivity$ReplyDetailListFragment$B5QNZ-VWjgUuOjgN0Is-pRtxO68
                    @Override // com.lxj.xpopup.interfaces.OnSrcViewUpdateListener
                    public final void onSrcViewUpdate(ImageViewerPopupView imageViewerPopupView, int i2) {
                        ReplyDetailActivity.ReplyDetailListFragment.m266toImageShow$lambda4$lambda3(imageView, imageViewerPopupView, i2);
                    }
                }, new XpopImageLoader()).show();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: toImageShow$lambda-4$lambda-3  reason: not valid java name */
        public static final void m266toImageShow$lambda4$lambda3(ImageView imageView, ImageViewerPopupView popupView, int i) {
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

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void initRecyclerView(RecyclerView recyclerView) {
            Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
            LayoutInflater from = LayoutInflater.from(getContext());
            ViewParent parent = recyclerView.getParent();
            if (parent == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup");
            }
            View view = from.inflate(R.layout.review_footer_view, (ViewGroup) parent, false);
            this.footView = view;
            BaseQuickAdapter<T, BaseViewHolder> mAdapter = this.mAdapter;
            Intrinsics.checkNotNullExpressionValue(mAdapter, "mAdapter");
            Intrinsics.checkNotNullExpressionValue(view, "view");
            BaseQuickAdapter.addFooterView$default(mAdapter, view, 0, 0, 6, null);
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected OnItemChildClickListener onItemChildClick() {
            return new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyDetailActivity$ReplyDetailListFragment$AJDTmk_lDc9j9cdEM9hw78mLdyc
                @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
                public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    ReplyDetailActivity.ReplyDetailListFragment.m260onItemChildClick$lambda10(ReplyDetailActivity.ReplyDetailListFragment.this, baseQuickAdapter, view, i);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onItemChildClick$lambda-10  reason: not valid java name */
        public static final void m260onItemChildClick$lambda10(ReplyDetailListFragment this$0, BaseQuickAdapter noName_0, View view, int i) {
            ReviewItem reviewItem;
            LoadingListener loadingListener;
            ReviewItem reviewItem2;
            ReviewItem reviewItem3;
            ReviewItem reviewItem4;
            ReviewItem reviewItem5;
            LoadingListener loadingListener2;
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
            Intrinsics.checkNotNullParameter(view, "view");
            ReviewItem reviewItem6 = (ReviewItem) this$0.mAdapter.getItem(i);
            switch (view.getId()) {
                case R.id.avatarView /* 2131296424 */:
                    LoadingListener loadingListener3 = this$0.listener;
                    if (loadingListener3 == null) {
                        return;
                    }
                    loadingListener3.at(reviewItem6.getComment_id(), reviewItem6.getUsername());
                    return;
                case R.id.avatarView2 /* 2131296425 */:
                    List<ReviewItem> son_reply = reviewItem6.getSon_reply();
                    if (son_reply == null || (reviewItem = (ReviewItem) CollectionsKt.firstOrNull((List<? extends Object>) son_reply)) == null || (loadingListener = this$0.listener) == null) {
                        return;
                    }
                    loadingListener.at(reviewItem.getComment_id(), reviewItem.getUsername());
                    return;
                case R.id.container1 /* 2131296553 */:
                    ReplyDetailActivity.Companion.start(this$0.getContext(), this$0.id, this$0.boxType, reviewItem6.getComment_id(), reviewItem6);
                    return;
                case R.id.container2 /* 2131296554 */:
                    List<ReviewItem> son_reply2 = reviewItem6.getSon_reply();
                    if (son_reply2 == null || (reviewItem2 = (ReviewItem) CollectionsKt.firstOrNull((List<? extends Object>) son_reply2)) == null) {
                        return;
                    }
                    ReplyDetailActivity.Companion.start(this$0.getContext(), this$0.id, this$0.boxType, reviewItem2.getComment_id(), reviewItem2);
                    return;
                case R.id.ivAction /* 2131296911 */:
                    this$0.reportPopup(view, reviewItem6.getUid(), reviewItem6.getComment_id(), i, reviewItem6.getUsername(), reviewItem6.getBlocked());
                    return;
                case R.id.ivAction2 /* 2131296912 */:
                    List<ReviewItem> son_reply3 = reviewItem6.getSon_reply();
                    if (son_reply3 == null || (reviewItem3 = (ReviewItem) CollectionsKt.firstOrNull((List<? extends Object>) son_reply3)) == null) {
                        return;
                    }
                    this$0.reportPopup(view, reviewItem3.getUid(), reviewItem3.getComment_id(), i, reviewItem6.getUsername(), reviewItem6.getBlocked());
                    return;
                case R.id.llLike /* 2131297190 */:
                    Integer support_status = reviewItem6.getSupport_status();
                    if (support_status != null && support_status.intValue() == 0) {
                        reviewItem6.setSupport_status(1);
                        Integer support = reviewItem6.getSupport();
                        reviewItem6.setSupport(support != null ? Integer.valueOf(support.intValue() + 1) : null);
                        this$0.mAdapter.notifyItemChanged(i);
                        LoadingListener loadingListener4 = this$0.listener;
                        if (loadingListener4 == null) {
                            return;
                        }
                        loadingListener4.likeReview(reviewItem6.getComment_id(), 1);
                        return;
                    }
                    Integer support_status2 = reviewItem6.getSupport_status();
                    if (support_status2 != null && support_status2.intValue() == 1) {
                        reviewItem6.setSupport_status(0);
                        Integer support2 = reviewItem6.getSupport();
                        reviewItem6.setSupport(support2 != null ? Integer.valueOf(support2.intValue() - 1) : null);
                        this$0.mAdapter.notifyItemChanged(i);
                        LoadingListener loadingListener5 = this$0.listener;
                        if (loadingListener5 == null) {
                            return;
                        }
                        loadingListener5.likeReview(reviewItem6.getComment_id(), 0);
                        return;
                    }
                    return;
                case R.id.llLike2 /* 2131297191 */:
                    List<ReviewItem> son_reply4 = reviewItem6.getSon_reply();
                    if (son_reply4 == null || (reviewItem4 = (ReviewItem) CollectionsKt.firstOrNull((List<? extends Object>) son_reply4)) == null) {
                        return;
                    }
                    Integer support_status3 = reviewItem4.getSupport_status();
                    if (support_status3 != null && support_status3.intValue() == 0) {
                        reviewItem4.setSupport_status(1);
                        Integer support3 = reviewItem4.getSupport();
                        reviewItem4.setSupport(support3 != null ? Integer.valueOf(support3.intValue() + 1) : null);
                        this$0.mAdapter.notifyItemChanged(i);
                        LoadingListener loadingListener6 = this$0.listener;
                        if (loadingListener6 == null) {
                            return;
                        }
                        loadingListener6.likeReview(reviewItem4.getComment_id(), 1);
                        return;
                    }
                    Integer support_status4 = reviewItem4.getSupport_status();
                    if (support_status4 != null && support_status4.intValue() == 1) {
                        reviewItem4.setSupport_status(0);
                        Integer support4 = reviewItem4.getSupport();
                        reviewItem4.setSupport(support4 != null ? Integer.valueOf(support4.intValue() - 1) : null);
                        this$0.mAdapter.notifyItemChanged(i);
                        LoadingListener loadingListener7 = this$0.listener;
                        if (loadingListener7 == null) {
                            return;
                        }
                        loadingListener7.likeReview(reviewItem4.getComment_id(), 0);
                        return;
                    }
                    return;
                case R.id.llReply /* 2131297211 */:
                    LoadingListener loadingListener8 = this$0.listener;
                    if (loadingListener8 == null) {
                        return;
                    }
                    loadingListener8.onReply(reviewItem6.getComment_id(), reviewItem6.getUsername(), i);
                    return;
                case R.id.llReply2 /* 2131297212 */:
                    List<ReviewItem> son_reply5 = reviewItem6.getSon_reply();
                    if (son_reply5 == null || (reviewItem5 = (ReviewItem) CollectionsKt.firstOrNull((List<? extends Object>) son_reply5)) == null || (loadingListener2 = this$0.listener) == null) {
                        return;
                    }
                    loadingListener2.onReply(reviewItem5.getComment_id(), reviewItem5.getUsername(), i);
                    return;
                default:
                    return;
            }
        }

        private final void reportPopup(View view, final String str, final String str2, final int i, final String str3, final int i2) {
            if (Intrinsics.areEqual(str, App.getUserData().getUid())) {
                new XPopup.Builder(getContext()).atView(view).asAttachList(new String[]{"Delete"}, null, new OnSelectListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyDetailActivity$ReplyDetailListFragment$O0uTS5zMmopgiVFZCD2l60pSZQQ
                    @Override // com.lxj.xpopup.interfaces.OnSelectListener
                    public final void onSelect(int i3, String str4) {
                        ReplyDetailActivity.ReplyDetailListFragment.m262reportPopup$lambda11(ReplyDetailActivity.ReplyDetailListFragment.this, i, str2, i3, str4);
                    }
                }).show();
                return;
            }
            XPopup.Builder atView = new XPopup.Builder(getContext()).atView(view);
            String[] strArr = new String[2];
            strArr[0] = "Report";
            strArr[1] = i2 == 1 ? "Unblock" : "Block";
            atView.asAttachList(strArr, null, new OnSelectListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyDetailActivity$ReplyDetailListFragment$YHr7QnX4ceRR1N0IwW0wrhlhwEs
                @Override // com.lxj.xpopup.interfaces.OnSelectListener
                public final void onSelect(int i3, String str4) {
                    ReplyDetailActivity.ReplyDetailListFragment.m263reportPopup$lambda13(ReplyDetailActivity.ReplyDetailListFragment.this, str2, str, str3, i2, i3, str4);
                }
            }).show();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: reportPopup$lambda-11  reason: not valid java name */
        public static final void m262reportPopup$lambda11(ReplyDetailListFragment this$0, int i, String str, int i2, String str2) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.deleteComment(i, str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: reportPopup$lambda-13  reason: not valid java name */
        public static final void m263reportPopup$lambda13(final ReplyDetailListFragment this$0, String str, String str2, String str3, int i, int i2, String str4) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            if (i2 == 0) {
                this$0.getReportReason(str);
            } else if (i2 != 1) {
            } else {
                BlockUserDialog newInstance = BlockUserDialog.Companion.newInstance(str2, str3, i);
                newInstance.setListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyDetailActivity$ReplyDetailListFragment$vDize5-GcWlNeMArJTB8OqnL6eY
                    @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                    public final void onClick() {
                        ReplyDetailActivity.ReplyDetailListFragment.m264reportPopup$lambda13$lambda12(ReplyDetailActivity.ReplyDetailListFragment.this);
                    }
                });
                FragmentManager childFragmentManager = this$0.getChildFragmentManager();
                Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
                newInstance.show(childFragmentManager, BlockUserDialog.class.getSimpleName());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: reportPopup$lambda-13$lambda-12  reason: not valid java name */
        public static final void m264reportPopup$lambda13$lambda12(ReplyDetailListFragment this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.startRefresh();
        }

        private final void getReportReason(String str) {
            Observable<R> compose = HttpRequest.Companion.post("Comment_reason_list").asRequest().compose(RxUtils.rxTranslate2Bean(String.class));
            Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
            Object as = compose.map($$Lambda$ReplyDetailActivity$ReplyDetailListFragment$U8J4pj3gSCn3kCJpurEzqbnsHUQ.INSTANCE).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
            Intrinsics.checkNotNullExpressionValue(as, "HttpRequest.post(\"Commen…bindLifecycleOwner(this))");
            RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, new ReplyDetailActivity$ReplyDetailListFragment$getReportReason$2(this), null, new ReplyDetailActivity$ReplyDetailListFragment$getReportReason$3(this), null, new ReplyDetailActivity$ReplyDetailListFragment$getReportReason$4(this, str), 10, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: getReportReason$lambda-14  reason: not valid java name */
        public static final ArrayList m252getReportReason$lambda14(String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            Object parseObject = JSON.parseObject(it, RxUtils.buildType(ListResponse.class, ReportReason.class), new Feature[0]);
            Intrinsics.checkNotNullExpressionValue(parseObject, "parseObject(\n           …  )\n                    )");
            return ((ListResponse) parseObject).getList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void showReportReason(final List<ReportReason> list, final String str) {
            list.add(new ReportReason("0", "Other"));
            List<ReportReason> list2 = list;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            for (ReportReason reportReason : list2) {
                arrayList.add(reportReason.getReason());
            }
            new ActionSheetDialog(getContext()).builder().setCancelable(true).setCanceledOnTouchOutside(true).addSheetItems(arrayList, new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyDetailActivity$ReplyDetailListFragment$6W7RjxtG-hKumb4uwY-Gbj5VOF4
                @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
                public final void onClick(int i) {
                    ReplyDetailActivity.ReplyDetailListFragment.m265showReportReason$lambda16(str, list, this, i);
                }
            }).show();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: showReportReason$lambda-16  reason: not valid java name */
        public static final void m265showReportReason$lambda16(String str, List list, ReplyDetailListFragment this$0, int i) {
            Intrinsics.checkNotNullParameter(list, "$list");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            ReportReviewDialogFragment.Companion companion = ReportReviewDialogFragment.Companion;
            if (str == null) {
                str = "";
            }
            String id = ((ReportReason) list.get(i - 1)).getId();
            companion.newInstance(str, id != null ? id : "", this$0.boxType).show(this$0.getChildFragmentManager(), ReportReviewDialogFragment.class.getSimpleName());
        }

        private final void deleteComment(int i, String str) {
            ReplyDetailListFragment replyDetailListFragment = this;
            RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.Companion.post(replyDetailListFragment, ApiUtils.INSTANCE.deleteComment(this.boxType)).param("comment_id", str).asRequest(), replyDetailListFragment), ReplyDetailActivity$ReplyDetailListFragment$deleteComment$1.INSTANCE, null, null, null, new ReplyDetailActivity$ReplyDetailListFragment$deleteComment$2(this, i), 14, null);
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected OnItemClickListener onItemClick() {
            return new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyDetailActivity$ReplyDetailListFragment$aHQQ44RCUvmzCW2MUR7JveLIIxY
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    ReplyDetailActivity.ReplyDetailListFragment.m261onItemClick$lambda17(ReplyDetailActivity.ReplyDetailListFragment.this, baseQuickAdapter, view, i);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onItemClick$lambda-17  reason: not valid java name */
        public static final void m261onItemClick$lambda17(ReplyDetailListFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
            Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
            ReviewItem reviewItem = (ReviewItem) this$0.mAdapter.getItem(i);
            if (Intrinsics.areEqual(this$0.commentId, reviewItem.getComment_id())) {
                return;
            }
            ReplyDetailActivity.Companion.start(this$0.getContext(), this$0.id, this$0.boxType, reviewItem.getComment_id(), reviewItem);
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void addOnItemClickViews(BaseQuickAdapter<ReviewItem, BaseViewHolder> adapter) {
            Intrinsics.checkNotNullParameter(adapter, "adapter");
            adapter.addChildClickViewIds(R.id.llReply, R.id.llLike, R.id.avatarView, R.id.ivAction, R.id.container1, R.id.container2, R.id.llReply2, R.id.llLike2, R.id.avatarView2, R.id.ivAction2);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public void initHolder(BaseViewHolder helper, ReviewItem item) {
            Integer is_delete;
            Integer is_delete2;
            Integer is_delete3;
            Integer is_delete4;
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            int itemType = item.getItemType();
            if (itemType != 0) {
                if (itemType != 1) {
                    if (itemType != 2) {
                        if (itemType != 3) {
                            return;
                        }
                    }
                }
                TextView textView = (TextView) helper.getView(R.id.tvReplyStack);
                Integer is_delete5 = item.is_delete();
                if (is_delete5 != null && is_delete5.intValue() == 0) {
                    TextView textView2 = textView;
                    CommonExtKt.visible(textView2);
                    List<AtItem> at = item.getAt();
                    if (at == null || at.isEmpty()) {
                        CommonExtKt.gone(textView2);
                    } else {
                        CommonExtKt.visible(textView2);
                        SpanUtils with = SpanUtils.with(textView);
                        Intrinsics.checkNotNullExpressionValue(with, "with(reply)");
                        SpanUtils addText = CommonExtKt.addText(with, "Replying to ", 14, R.color.white_70alpha);
                        for (AtItem atItem : item.getAt()) {
                            CommonExtKt.addText(addText, Intrinsics.stringPlus("@", atItem.getUsername()), 14, R.color.color_main_blue).append(" ");
                        }
                        addText.create();
                    }
                } else {
                    CommonExtKt.gone(textView);
                }
                ImageView imageView = (ImageView) helper.getView(R.id.ivAction);
                if (Intrinsics.areEqual(item.getUid(), App.getUserData().uid_v2) && (is_delete4 = item.is_delete()) != null && is_delete4.intValue() == 1) {
                    CommonExtKt.gone(imageView);
                } else {
                    CommonExtKt.visible(imageView);
                }
                QMUISpanTouchFixTextView qMUISpanTouchFixTextView = (QMUISpanTouchFixTextView) helper.getView(R.id.tvContent);
                qMUISpanTouchFixTextView.setMovementMethodDefault();
                qMUISpanTouchFixTextView.setNeedForceEventToParent(true);
                QMUISpanTouchFixTextView qMUISpanTouchFixTextView2 = qMUISpanTouchFixTextView;
                SpanUtils span = SpanUtils.with(qMUISpanTouchFixTextView2);
                Integer is_delete6 = item.is_delete();
                if (is_delete6 != null && is_delete6.intValue() == 0) {
                    List<Comment> comment = item.getComment();
                    if (comment != null) {
                        for (Comment comment2 : comment) {
                            String uid = comment2.getUid();
                            if (uid == null || StringsKt.isBlank(uid)) {
                                Intrinsics.checkNotNullExpressionValue(span, "span");
                                CommonExtKt.addText(span, String.valueOf(comment2.getText()), 14, R.color.white30_transparent);
                            } else {
                                String stringPlus = Intrinsics.stringPlus("@", comment2.getUsername());
                                if (stringPlus == null) {
                                    stringPlus = "";
                                }
                                SpanUtils append = span.append(stringPlus);
                                final int colorInt = CommonExtKt.colorInt(this, (int) R.color.color_main_blue);
                                append.setClickSpan(new CustomClickableSpan(colorInt) { // from class: com.movieboxpro.android.view.activity.ReplyDetailActivity$ReplyDetailListFragment$initHolder$6$1
                                    @Override // android.text.style.ClickableSpan
                                    public void onClick(View widget) {
                                        Intrinsics.checkNotNullParameter(widget, "widget");
                                        ToastUtils.showShort("点击", new Object[0]);
                                    }
                                }).setForegroundColor(CommonExtKt.colorInt(this, (int) R.color.color_main_blue)).setFontSize(14, true);
                            }
                        }
                        Unit unit = Unit.INSTANCE;
                    }
                    span.create();
                } else {
                    SpanUtils with2 = SpanUtils.with(qMUISpanTouchFixTextView2);
                    Intrinsics.checkNotNullExpressionValue(with2, "with(content)");
                    CommonExtKt.addText(with2, "This review is deleted by user.", 14, R.color.white30_transparent).setItalic().create();
                }
                LinearLayout linearLayout = (LinearLayout) helper.getView(R.id.llLike);
                Integer support_status = item.getSupport_status();
                linearLayout.setSelected(support_status != null && support_status.intValue() == 1);
                TextView textView3 = (TextView) helper.getView(R.id.tvLikeNum);
                Integer support = item.getSupport();
                if ((support == null ? 0 : support.intValue()) > 0) {
                    CommonExtKt.visible(textView3);
                    textView3.setText(String.valueOf(item.getSupport()));
                } else {
                    CommonExtKt.gone(textView3);
                }
                TextView textView4 = (TextView) helper.getView(R.id.tvReplyNum);
                Integer reply = item.getReply();
                if ((reply == null ? 0 : reply.intValue()) > 0) {
                    CommonExtKt.visible(textView4);
                    textView4.setText(String.valueOf(item.getReply()));
                } else {
                    CommonExtKt.gone(textView4);
                }
                helper.setText(R.id.tvUsername, item.getUsername());
                helper.setText(R.id.tvTime, TimeUtils.formatTime(item.getDateline() * 1000));
                ((UserAvatarView) helper.getView(R.id.avatarView)).setAvatar(item.getAvatar(), item.getUsername());
                RecyclerView recyclerView = (RecyclerView) helper.getView(R.id.recyclerView);
                recyclerView.setNestedScrollingEnabled(false);
                List<ImageItem> img_list = item.getImg_list();
                if ((img_list == null || img_list.isEmpty()) || ((is_delete3 = item.is_delete()) != null && is_delete3.intValue() == 1)) {
                    CommonExtKt.gone(recyclerView);
                } else {
                    CommonExtKt.visible(recyclerView);
                    recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
                    if (recyclerView.getTag() == null) {
                        recyclerView.addItemDecoration(new GridSpacingItemDecoration(4, CommonExtKt.dp2Px(10), false));
                        recyclerView.setTag("added");
                    }
                    CommBaseAdapter commBaseAdapter = new CommBaseAdapter(R.layout.adapter_review_image_item, new ReplyDetailActivity$ReplyDetailListFragment$initHolder$imgAdapter$2(this), item.getImg_list());
                    recyclerView.setAdapter(commBaseAdapter);
                    final ArrayList arrayList = new ArrayList();
                    for (ImageItem imageItem : item.getImg_list()) {
                        String url = imageItem.getUrl();
                        if (url == null) {
                            url = "";
                        }
                        arrayList.add(url);
                    }
                    commBaseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyDetailActivity$ReplyDetailListFragment$8JH63lGv7EOIWLfq8QOSmACL_6I
                        @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                        public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                            ReplyDetailActivity.ReplyDetailListFragment.m254initHolder$lambda25(ReplyDetailActivity.ReplyDetailListFragment.this, arrayList, baseQuickAdapter, view, i);
                        }
                    });
                }
                if (item.getItemType() == 3) {
                    List<ReviewItem> son_reply = item.getSon_reply();
                    if ((son_reply == null ? null : (ReviewItem) CollectionsKt.firstOrNull((List<? extends Object>) son_reply)) != null) {
                        List<ReviewItem> son_reply2 = item.getSon_reply();
                        ReviewItem reviewItem = son_reply2 == null ? null : (ReviewItem) CollectionsKt.first((List<? extends Object>) son_reply2);
                        Intrinsics.checkNotNull(reviewItem);
                        initItemView(helper, reviewItem, item);
                        return;
                    }
                    return;
                }
                return;
            }
            TextView textView5 = (TextView) helper.getView(R.id.tvReplyStack);
            Integer is_delete7 = item.is_delete();
            if (is_delete7 != null && is_delete7.intValue() == 0) {
                TextView textView6 = textView5;
                CommonExtKt.visible(textView6);
                List<AtItem> at2 = item.getAt();
                if (at2 == null || at2.isEmpty()) {
                    CommonExtKt.gone(textView6);
                } else {
                    CommonExtKt.visible(textView6);
                    int i = item.getItemType() == 2 ? 16 : 14;
                    SpanUtils with3 = SpanUtils.with(textView5);
                    Intrinsics.checkNotNullExpressionValue(with3, "with(reply)");
                    SpanUtils addText2 = CommonExtKt.addText(with3, "Replying to ", i, R.color.gray_999);
                    for (AtItem atItem2 : item.getAt()) {
                        CommonExtKt.addText(addText2, Intrinsics.stringPlus("@", atItem2.getUsername()), i, R.color.color_main_blue).append(" ");
                    }
                    addText2.create();
                }
            } else {
                CommonExtKt.gone(textView5);
            }
            ImageView imageView2 = (ImageView) helper.getView(R.id.ivAction);
            if (Intrinsics.areEqual(item.getUid(), App.getUserData().uid_v2) && (is_delete2 = item.is_delete()) != null && is_delete2.intValue() == 1) {
                CommonExtKt.gone(imageView2);
            } else {
                CommonExtKt.visible(imageView2);
            }
            LinearLayout linearLayout2 = (LinearLayout) helper.getView(R.id.llLike);
            Integer support_status2 = item.getSupport_status();
            linearLayout2.setSelected(support_status2 != null && support_status2.intValue() == 1);
            TextView textView7 = (TextView) helper.getView(R.id.tvLikeNum);
            Integer support2 = item.getSupport();
            if ((support2 == null ? 0 : support2.intValue()) > 0) {
                CommonExtKt.visible(textView7);
                textView7.setText(String.valueOf(item.getSupport()));
            } else {
                CommonExtKt.gone(textView7);
            }
            TextView textView8 = (TextView) helper.getView(R.id.tvReplyNum);
            Integer reply2 = item.getReply();
            if ((reply2 == null ? 0 : reply2.intValue()) > 0) {
                CommonExtKt.visible(textView8);
                textView8.setText(String.valueOf(item.getReply()));
            } else {
                CommonExtKt.gone(textView8);
            }
            helper.setText(R.id.tvUsername, item.getUsername());
            helper.setText(R.id.tvTime, TimeUtils.formatTime(item.getDateline() * 1000));
            ((UserAvatarView) helper.getView(R.id.avatarView)).setAvatar(item.getAvatar(), item.getUsername());
            QMUISpanTouchFixTextView qMUISpanTouchFixTextView3 = (QMUISpanTouchFixTextView) helper.getView(R.id.tvContent);
            qMUISpanTouchFixTextView3.setMovementMethodDefault();
            qMUISpanTouchFixTextView3.setNeedForceEventToParent(true);
            QMUISpanTouchFixTextView qMUISpanTouchFixTextView4 = qMUISpanTouchFixTextView3;
            SpanUtils span2 = SpanUtils.with(qMUISpanTouchFixTextView4);
            int i2 = item.getItemType() == 2 ? 20 : 14;
            Integer is_delete8 = item.is_delete();
            if (is_delete8 != null && is_delete8.intValue() == 0) {
                List<Comment> comment3 = item.getComment();
                if (comment3 != null) {
                    for (Comment comment4 : comment3) {
                        String uid2 = comment4.getUid();
                        if (uid2 == null || StringsKt.isBlank(uid2)) {
                            Intrinsics.checkNotNullExpressionValue(span2, "span");
                            CommonExtKt.addText(span2, String.valueOf(comment4.getText()), i2, R.color.white30_transparent);
                        } else {
                            String stringPlus2 = Intrinsics.stringPlus("@", comment4.getUsername());
                            if (stringPlus2 == null) {
                                stringPlus2 = "";
                            }
                            SpanUtils append2 = span2.append(stringPlus2);
                            final int colorInt2 = CommonExtKt.colorInt(this, (int) R.color.color_main_blue);
                            final int colorInt3 = CommonExtKt.colorInt(this, (int) R.color.color_main_blue);
                            final int colorInt4 = CommonExtKt.colorInt(this, (int) R.color.transparent);
                            final int colorInt5 = CommonExtKt.colorInt(this, (int) R.color.transparent);
                            append2.setClickSpan(new QMUITouchableSpan(colorInt2, colorInt3, colorInt4, colorInt5) { // from class: com.movieboxpro.android.view.activity.ReplyDetailActivity$ReplyDetailListFragment$initHolder$2$1
                                @Override // com.movieboxpro.android.view.widget.textview.QMUITouchableSpan
                                public void onSpanClick(View view) {
                                    ToastUtils.showShort("点击", new Object[0]);
                                }
                            }).setForegroundColor(CommonExtKt.colorInt(this, (int) R.color.color_main_blue)).setFontSize(i2, true);
                        }
                    }
                    Unit unit2 = Unit.INSTANCE;
                }
                span2.create();
            } else {
                SpanUtils with4 = SpanUtils.with(qMUISpanTouchFixTextView4);
                Intrinsics.checkNotNullExpressionValue(with4, "with(content)");
                CommonExtKt.addText(with4, "This review is deleted by user.", i2, R.color.white30_transparent).setItalic().create();
            }
            RecyclerView recyclerView2 = (RecyclerView) helper.getView(R.id.recyclerView);
            recyclerView2.setNestedScrollingEnabled(false);
            List<ImageItem> img_list2 = item.getImg_list();
            if ((img_list2 == null || img_list2.isEmpty()) || ((is_delete = item.is_delete()) != null && is_delete.intValue() == 1)) {
                CommonExtKt.gone(recyclerView2);
            } else {
                CommonExtKt.visible(recyclerView2);
                recyclerView2.setLayoutManager(new GridLayoutManager(getContext(), 4));
                if (recyclerView2.getTag() == null) {
                    recyclerView2.addItemDecoration(new GridSpacingItemDecoration(4, CommonExtKt.dp2Px(10), false));
                    recyclerView2.setTag("added");
                }
                CommBaseAdapter commBaseAdapter2 = new CommBaseAdapter(R.layout.adapter_review_image_item, new ReplyDetailActivity$ReplyDetailListFragment$initHolder$imgAdapter$1(this), item.getImg_list());
                recyclerView2.setAdapter(commBaseAdapter2);
                final ArrayList arrayList2 = new ArrayList();
                for (ImageItem imageItem2 : item.getImg_list()) {
                    String url2 = imageItem2.getUrl();
                    if (url2 == null) {
                        url2 = "";
                    }
                    arrayList2.add(url2);
                }
                commBaseAdapter2.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyDetailActivity$ReplyDetailListFragment$CMnErIFLv300AjilgUZX9yf9lVc
                    @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                    public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i3) {
                        ReplyDetailActivity.ReplyDetailListFragment.m253initHolder$lambda21(ReplyDetailActivity.ReplyDetailListFragment.this, arrayList2, baseQuickAdapter, view, i3);
                    }
                });
            }
            View view = helper.getView(R.id.topLine);
            View view2 = helper.getView(R.id.bottomLine);
            if (this.topList.size() == 0) {
                CommonExtKt.gone(view);
                CommonExtKt.gone(view2);
            } else {
                if (helper.getAdapterPosition() == this.topList.size()) {
                    CommonExtKt.gone(view2);
                } else {
                    CommonExtKt.visible(view2);
                }
                if (helper.getAdapterPosition() == 0) {
                    CommonExtKt.visible(view2);
                    CommonExtKt.gone(view);
                } else {
                    CommonExtKt.visible(view);
                }
            }
            this.bottomViewLine = helper.getView(R.id.line);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: initHolder$lambda-21  reason: not valid java name */
        public static final void m253initHolder$lambda21(ReplyDetailListFragment this$0, ArrayList images, BaseQuickAdapter noName_0, View view, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(images, "$images");
            Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
            Intrinsics.checkNotNullParameter(view, "view");
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            Intrinsics.checkNotNullExpressionValue(imageView, "imageView");
            this$0.toImageShow(i, imageView, images, imageView);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: initHolder$lambda-25  reason: not valid java name */
        public static final void m254initHolder$lambda25(ReplyDetailListFragment this$0, ArrayList images, BaseQuickAdapter noName_0, View view, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(images, "$images");
            Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
            Intrinsics.checkNotNullParameter(view, "view");
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            Intrinsics.checkNotNullExpressionValue(imageView, "imageView");
            this$0.toImageShow(i, imageView, images, imageView);
        }

        private final void initItemView(BaseViewHolder baseViewHolder, ReviewItem reviewItem, ReviewItem reviewItem2) {
            Integer is_delete;
            Integer is_delete2;
            TextView textView = (TextView) baseViewHolder.getView(R.id.tvReplyStack2);
            Integer is_delete3 = reviewItem.is_delete();
            if (is_delete3 != null && is_delete3.intValue() == 0) {
                TextView textView2 = textView;
                CommonExtKt.visible(textView2);
                List<AtItem> at = reviewItem.getAt();
                if (at == null || at.isEmpty()) {
                    CommonExtKt.gone(textView2);
                } else {
                    CommonExtKt.visible(textView2);
                    SpanUtils with = SpanUtils.with(textView);
                    Intrinsics.checkNotNullExpressionValue(with, "with(reply)");
                    SpanUtils addText = CommonExtKt.addText(with, "Replying to ", 14, R.color.white_70alpha);
                    for (AtItem atItem : reviewItem.getAt()) {
                        CommonExtKt.addText(addText, Intrinsics.stringPlus("@", atItem.getUsername()), 14, R.color.color_main_blue).append(" ");
                    }
                    addText.create();
                }
            } else {
                CommonExtKt.gone(textView);
            }
            ImageView imageView = (ImageView) baseViewHolder.getView(R.id.ivAction2);
            if (Intrinsics.areEqual(reviewItem.getUid(), App.getUserData().uid_v2) && (is_delete2 = reviewItem.is_delete()) != null && is_delete2.intValue() == 0) {
                CommonExtKt.visible(imageView);
            } else {
                CommonExtKt.gone(imageView);
            }
            LinearLayout linearLayout = (LinearLayout) baseViewHolder.getView(R.id.llLike2);
            Integer support_status = reviewItem.getSupport_status();
            linearLayout.setSelected(support_status != null && support_status.intValue() == 1);
            QMUISpanTouchFixTextView qMUISpanTouchFixTextView = (QMUISpanTouchFixTextView) baseViewHolder.getView(R.id.tvContent2);
            qMUISpanTouchFixTextView.setMovementMethodDefault();
            qMUISpanTouchFixTextView.setNeedForceEventToParent(true);
            QMUISpanTouchFixTextView qMUISpanTouchFixTextView2 = qMUISpanTouchFixTextView;
            SpanUtils span = SpanUtils.with(qMUISpanTouchFixTextView2);
            Integer is_delete4 = reviewItem.is_delete();
            if (is_delete4 != null && is_delete4.intValue() == 0) {
                List<Comment> comment = reviewItem.getComment();
                if (comment != null) {
                    for (Comment comment2 : comment) {
                        String uid = comment2.getUid();
                        if (uid == null || StringsKt.isBlank(uid)) {
                            Intrinsics.checkNotNullExpressionValue(span, "span");
                            CommonExtKt.addText(span, String.valueOf(comment2.getText()), 14, R.color.white30_transparent);
                        } else {
                            String stringPlus = Intrinsics.stringPlus("@", comment2.getUsername());
                            if (stringPlus == null) {
                                stringPlus = "";
                            }
                            SpanUtils append = span.append(stringPlus);
                            final int colorInt = CommonExtKt.colorInt(this, (int) R.color.color_main_blue);
                            append.setClickSpan(new CustomClickableSpan(colorInt) { // from class: com.movieboxpro.android.view.activity.ReplyDetailActivity$ReplyDetailListFragment$initItemView$2$1
                                @Override // android.text.style.ClickableSpan
                                public void onClick(View widget) {
                                    Intrinsics.checkNotNullParameter(widget, "widget");
                                    ToastUtils.showShort("点击", new Object[0]);
                                }
                            }).setForegroundColor(CommonExtKt.colorInt(this, (int) R.color.color_main_blue)).setFontSize(14, true);
                        }
                    }
                }
                span.create();
            } else {
                SpanUtils with2 = SpanUtils.with(qMUISpanTouchFixTextView2);
                Intrinsics.checkNotNullExpressionValue(with2, "with(content)");
                CommonExtKt.addText(with2, "This review is deleted by user.", 14, R.color.white30_transparent).setItalic().create();
            }
            LinearLayout linearLayout2 = (LinearLayout) baseViewHolder.getView(R.id.llMore);
            View view = baseViewHolder.getView(R.id.bottomLine);
            Integer reply = reviewItem2.getReply();
            if ((reply == null ? 0 : reply.intValue()) > 1) {
                CommonExtKt.visible(linearLayout2);
                CommonExtKt.gone(view);
            } else {
                CommonExtKt.visible(view);
                CommonExtKt.gone(linearLayout2);
            }
            TextView textView3 = (TextView) baseViewHolder.getView(R.id.tvLikeNum2);
            Integer support = reviewItem.getSupport();
            if ((support == null ? 0 : support.intValue()) > 0) {
                CommonExtKt.visible(textView3);
                textView3.setText(String.valueOf(reviewItem.getSupport()));
            } else {
                CommonExtKt.gone(textView3);
            }
            TextView textView4 = (TextView) baseViewHolder.getView(R.id.tvReplyNum2);
            Integer reply2 = reviewItem.getReply();
            if ((reply2 == null ? 0 : reply2.intValue()) > 0) {
                CommonExtKt.visible(textView4);
                textView4.setText(String.valueOf(reviewItem.getReply()));
            } else {
                CommonExtKt.gone(textView4);
            }
            baseViewHolder.setText(R.id.tvUsername2, reviewItem.getUsername());
            baseViewHolder.setText(R.id.tvTime2, TimeUtils.formatTime(reviewItem.getDateline() * 1000));
            ((UserAvatarView) baseViewHolder.getView(R.id.avatarView2)).setAvatar(reviewItem.getAvatar(), reviewItem.getUsername());
            RecyclerView recyclerView = (RecyclerView) baseViewHolder.getView(R.id.recyclerView2);
            recyclerView.setNestedScrollingEnabled(false);
            List<ImageItem> img_list = reviewItem.getImg_list();
            if ((img_list == null || img_list.isEmpty()) || ((is_delete = reviewItem.is_delete()) != null && is_delete.intValue() == 1)) {
                CommonExtKt.gone(recyclerView);
                return;
            }
            CommonExtKt.visible(recyclerView);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
            if (recyclerView.getTag() == null) {
                recyclerView.addItemDecoration(new GridSpacingItemDecoration(4, CommonExtKt.dp2Px(10), false));
                recyclerView.setTag("added");
            }
            CommBaseAdapter commBaseAdapter = new CommBaseAdapter(R.layout.adapter_review_image_item, new ReplyDetailActivity$ReplyDetailListFragment$initItemView$imgAdapter$1(this), reviewItem.getImg_list());
            recyclerView.setAdapter(commBaseAdapter);
            final ArrayList arrayList = new ArrayList();
            for (ImageItem imageItem : reviewItem.getImg_list()) {
                String url = imageItem.getUrl();
                if (url == null) {
                    url = "";
                }
                arrayList.add(url);
            }
            commBaseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyDetailActivity$ReplyDetailListFragment$NizgR9WozjkYPdlhDInK-p7QM2A
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view2, int i) {
                    ReplyDetailActivity.ReplyDetailListFragment.m255initItemView$lambda29(ReplyDetailActivity.ReplyDetailListFragment.this, arrayList, baseQuickAdapter, view2, i);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: initItemView$lambda-29  reason: not valid java name */
        public static final void m255initItemView$lambda29(ReplyDetailListFragment this$0, ArrayList images, BaseQuickAdapter noName_0, View view, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(images, "$images");
            Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
            Intrinsics.checkNotNullParameter(view, "view");
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            Intrinsics.checkNotNullExpressionValue(imageView, "imageView");
            this$0.toImageShow(i, imageView, images, imageView);
        }
    }
}
