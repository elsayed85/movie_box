package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.adorkable.iosdialog.ActionSheetDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.huantansheng.easyphotos.Builder.AlbumBuilder;
import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.engine.ImageEngine;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.ImageViewerPopupView;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.lxj.xpopup.interfaces.OnSrcViewUpdateListener;
import com.lxj.xpopup.widget.SmartDragLayout;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.base.CommBaseAdapter;
import com.movieboxpro.android.base.CommBaseLoadMoreAdapter;
import com.movieboxpro.android.base.mvp.BaseDialogMvpFragment;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.http.HttpUploadRequest;
import com.movieboxpro.android.model.ImageItem;
import com.movieboxpro.android.model.ReportReason;
import com.movieboxpro.android.model.ReviewImage;
import com.movieboxpro.android.model.ReviewItem;
import com.movieboxpro.android.model.ReviewResponse;
import com.movieboxpro.android.model.UploadImgResponse;
import com.movieboxpro.android.utils.ApiUtils;
import com.movieboxpro.android.utils.ChooseImageGlideEngine;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.KeyboardUtils;
import com.movieboxpro.android.utils.LinearItemDecoration;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SpanUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.utils.XpopImageLoader;
import com.movieboxpro.android.view.activity.ReplyActivity;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.ReportReviewDialogFragment;
import com.movieboxpro.android.view.dialog.ReviewDialogContract;
import com.movieboxpro.android.view.dialog.ReviewDialogFragment;
import com.movieboxpro.android.view.widget.CustomLoadMoreView;
import com.movieboxpro.android.view.widget.GridSpacingItemDecoration;
import com.movieboxpro.android.view.widget.UserAvatarView;
import com.snowtop.diskpanda.view.widget.at.MethodContext;
import com.snowtop.diskpanda.view.widget.at.User;
import com.snowtop.diskpanda.view.widget.at.Weibo;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import top.zibin.luban.Luban;
/* compiled from: ReviewDialogFragment.kt */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 F2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0002FGB\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u001a\u001a\u00020\tH\u0014J\b\u0010\u001b\u001a\u00020\u0002H\u0014J\u001a\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\t2\b\u0010\u001f\u001a\u0004\u0018\u00010\fH\u0002J\b\u0010 \u001a\u00020\u001dH\u0016J\b\u0010!\u001a\u00020\u001dH\u0016J\b\u0010\"\u001a\u00020\u001dH\u0014J\b\u0010#\u001a\u00020\u001dH\u0014J\b\u0010$\u001a\u00020\u001dH\u0014J\b\u0010%\u001a\u00020\u001dH\u0014J\b\u0010&\u001a\u00020\u001dH\u0016J\b\u0010'\u001a\u00020\u0014H\u0014J\b\u0010(\u001a\u00020\u0014H\u0014J\"\u0010)\u001a\u00020\u001d2\u0006\u0010*\u001a\u00020\t2\u0006\u0010+\u001a\u00020\t2\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\b\u0010.\u001a\u00020\u001dH\u0016J\u0010\u0010/\u001a\u00020\u001d2\u0006\u00100\u001a\u000201H\u0016J\b\u00102\u001a\u00020\u001dH\u0016J\b\u0010\u0015\u001a\u00020\u001dH\u0002J\b\u00103\u001a\u00020\u001dH\u0016J\b\u00104\u001a\u00020\u001dH\u0002J\u000e\u00105\u001a\u00020\u001d2\u0006\u0010\r\u001a\u00020\u000eJ\b\u00106\u001a\u00020\u001dH\u0016J\u0016\u00107\u001a\u00020\u001d2\f\u00108\u001a\b\u0012\u0004\u0012\u00020\u000709H\u0016J \u0010:\u001a\u00020\u001d2\f\u00108\u001a\b\u0012\u0004\u0012\u00020;092\b\u0010\u001f\u001a\u0004\u0018\u00010\fH\u0016J\u0010\u0010<\u001a\u00020\u001d2\u0006\u0010=\u001a\u00020\fH\u0016J\u0016\u0010>\u001a\u00020\u001d2\f\u00108\u001a\b\u0012\u0004\u0012\u00020\u000709H\u0016J2\u0010?\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\t2\b\u0010@\u001a\u0004\u0018\u00010A2\u000e\u0010B\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010C2\u0006\u0010D\u001a\u00020EH\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0082.¢\u0006\u0002\n\u0000¨\u0006H"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ReviewDialogFragment;", "Lcom/movieboxpro/android/base/mvp/BaseDialogMvpFragment;", "Lcom/movieboxpro/android/view/dialog/ReviewDialogPresenter;", "Lcom/movieboxpro/android/view/dialog/ReviewDialogContract$View;", "()V", "adapter", "Lcom/movieboxpro/android/base/CommBaseLoadMoreAdapter;", "Lcom/movieboxpro/android/model/ReviewItem;", "boxType", "", "currPage", "currSort", "", "dismissListener", "Lcom/movieboxpro/android/view/dialog/DialogAction$ActionListener;", "id", "methodContext", "Lcom/snowtop/diskpanda/view/widget/at/MethodContext;", "pageSize", "register", "", "registerListener", "replyId", "sendImageAdapter", "Lcom/movieboxpro/android/base/CommBaseAdapter;", "Lcom/movieboxpro/android/model/ReviewImage;", "bindLayout", "bindPresenter", "deleteComment", "", "position", "commentId", "dismiss", "hideLoading", "initData", "initListener", "initView", "loadData", "loadMoreFailed", "needExpand", "needFullScreen", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onDestroy", "onDismiss", "dialog", "Landroid/content/DialogInterface;", "onStop", "reviewSuccess", "sendReview", "setListener", "showLoading", "showMoreReviews", "list", "", "showReportReason", "Lcom/movieboxpro/android/model/ReportReason;", "showReviewNum", "num", "showReviews", "toImageShow", "view", "Landroid/view/View;", "images", "", "imageView", "Landroid/widget/ImageView;", "Companion", "ReviewListFragment", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ReviewDialogFragment extends BaseDialogMvpFragment<ReviewDialogPresenter> implements ReviewDialogContract.View {
    public static final Companion Companion = new Companion(null);
    public static final int REQUEST_CODE_CHOOSE = 100;
    private CommBaseLoadMoreAdapter<ReviewItem> adapter;
    private int boxType;
    private DialogAction.ActionListener dismissListener;
    private String id;
    private boolean register;
    private boolean registerListener;
    private CommBaseAdapter<ReviewImage> sendImageAdapter;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private int currPage = 1;
    private int pageSize = 10;
    private String replyId = "";
    private final MethodContext methodContext = new MethodContext();
    private String currSort = "update_time_desc";

    @JvmStatic
    public static final ReviewDialogFragment newInstance(String str, int i) {
        return Companion.newInstance(str, i);
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

    @Override // com.movieboxpro.android.base.mvp.BaseDialogMvpFragment
    protected int bindLayout() {
        return R.layout.dialog_review;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseDialogMvpFragment
    protected boolean needExpand() {
        return true;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseDialogMvpFragment
    protected boolean needFullScreen() {
        return true;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseDialogMvpFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final void setListener(DialogAction.ActionListener dismissListener) {
        Intrinsics.checkNotNullParameter(dismissListener, "dismissListener");
        this.dismissListener = dismissListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseDialogMvpFragment
    public ReviewDialogPresenter bindPresenter() {
        return new ReviewDialogPresenter(this);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseDialogMvpFragment
    protected void initListener() {
        ((ImageView) _$_findCachedViewById(R.id.ivSort)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ReviewDialogFragment$lzXv60KnDW3sQJcdhejuMUdHYnQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReviewDialogFragment.m1060initListener$lambda0(ReviewDialogFragment.this, view);
            }
        });
        CommBaseLoadMoreAdapter<ReviewItem> commBaseLoadMoreAdapter = this.adapter;
        CommBaseAdapter<ReviewImage> commBaseAdapter = null;
        if (commBaseLoadMoreAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseLoadMoreAdapter = null;
        }
        commBaseLoadMoreAdapter.getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ReviewDialogFragment$3Gx4NcRCu5JZwD5mmT5XXRDywSY
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                ReviewDialogFragment.m1061initListener$lambda1(ReviewDialogFragment.this);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.llReview)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ReviewDialogFragment$ClQmOYuXVGReXHgeQCNzVbtl2HQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReviewDialogFragment.m1066initListener$lambda2(ReviewDialogFragment.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivAt)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ReviewDialogFragment$AjI4iMYbDfBOFoAcEh5-OVuTx30
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReviewDialogFragment.m1067initListener$lambda3(ReviewDialogFragment.this, view);
            }
        });
        CommBaseLoadMoreAdapter<ReviewItem> commBaseLoadMoreAdapter2 = this.adapter;
        if (commBaseLoadMoreAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseLoadMoreAdapter2 = null;
        }
        commBaseLoadMoreAdapter2.setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ReviewDialogFragment$LNcymD5PU_qLm-MpgmjujA1_lq8
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ReviewDialogFragment.m1068initListener$lambda8(ReviewDialogFragment.this, baseQuickAdapter, view, i);
            }
        });
        CommBaseAdapter<ReviewImage> commBaseAdapter2 = this.sendImageAdapter;
        if (commBaseAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sendImageAdapter");
            commBaseAdapter2 = null;
        }
        commBaseAdapter2.setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ReviewDialogFragment$gziFtw2g-kgnkAU9cnO7BkCW6LY
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ReviewDialogFragment.m1073initListener$lambda9(ReviewDialogFragment.this, baseQuickAdapter, view, i);
            }
        });
        CommBaseLoadMoreAdapter<ReviewItem> commBaseLoadMoreAdapter3 = this.adapter;
        if (commBaseLoadMoreAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseLoadMoreAdapter3 = null;
        }
        CommonExtKt.itemClick(commBaseLoadMoreAdapter3, new ReviewDialogFragment$initListener$7(this));
        EditText etContent = (EditText) _$_findCachedViewById(R.id.etContent);
        Intrinsics.checkNotNullExpressionValue(etContent, "etContent");
        etContent.addTextChangedListener(new TextWatcher() { // from class: com.movieboxpro.android.view.dialog.ReviewDialogFragment$initListener$$inlined$doAfterTextChanged$1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                ImageView imageView = (ImageView) ReviewDialogFragment.this._$_findCachedViewById(R.id.ivSend);
                String obj = editable == null ? null : editable.toString();
                imageView.setEnabled(!(obj == null || StringsKt.isBlank(obj)));
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tvReply)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ReviewDialogFragment$sOUqpNPxgWc_quRaBlAXK-eOApY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReviewDialogFragment.m1062initListener$lambda11(ReviewDialogFragment.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivSend)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ReviewDialogFragment$wjxAumquNXyEiSVfKfALA1ThNb8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReviewDialogFragment.m1063initListener$lambda12(ReviewDialogFragment.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivImage)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ReviewDialogFragment$FO1CHNzMx0rjQKEMEV58umJOg3w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReviewDialogFragment.m1064initListener$lambda13(ReviewDialogFragment.this, view);
            }
        });
        CommBaseAdapter<ReviewImage> commBaseAdapter3 = this.sendImageAdapter;
        if (commBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sendImageAdapter");
        } else {
            commBaseAdapter = commBaseAdapter3;
        }
        commBaseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ReviewDialogFragment$4Eqi_bRtnu7UtE3bsE4Uy7xMs00
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ReviewDialogFragment.m1065initListener$lambda15(ReviewDialogFragment.this, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m1060initListener$lambda0(ReviewDialogFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (Intrinsics.areEqual(this$0.currSort, "update_time_desc")) {
            ((ImageView) this$0._$_findCachedViewById(R.id.ivSort)).setImageResource(R.mipmap.ic_review_sort_time);
            PrefsUtils.getInstance().putString(Constant.Prefs.REVIEW_SORT, "dateline_asc");
            this$0.currSort = "dateline_asc";
        } else {
            ((ImageView) this$0._$_findCachedViewById(R.id.ivSort)).setImageResource(R.mipmap.ic_review_sort_default);
            this$0.currSort = "update_time_desc";
            PrefsUtils.getInstance().putString(Constant.Prefs.REVIEW_SORT, "update_time_desc");
        }
        this$0.currPage = 1;
        ((ReviewDialogPresenter) this$0.mPresenter).getReviews(this$0.id, this$0.boxType, this$0.currPage, this$0.pageSize, this$0.currSort);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m1061initListener$lambda1(ReviewDialogFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.currPage++;
        ((ReviewDialogPresenter) this$0.mPresenter).getMoreReviews(this$0.id, this$0.boxType, this$0.currPage, this$0.pageSize, this$0.currSort);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m1066initListener$lambda2(ReviewDialogFragment this$0, View view) {
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
    /* renamed from: initListener$lambda-3  reason: not valid java name */
    public static final void m1067initListener$lambda3(ReviewDialogFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (((User[]) ((EditText) this$0._$_findCachedViewById(R.id.etContent)).getText().getSpans(0, ((EditText) this$0._$_findCachedViewById(R.id.etContent)).length(), User.class)).length >= 10) {
            ToastUtils.showShort("Choose up to 10 at a time", new Object[0]);
        } else {
            KeyboardUtils.hideSoftInput((EditText) this$0._$_findCachedViewById(R.id.etContent));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-8  reason: not valid java name */
    public static final void m1068initListener$lambda8(final ReviewDialogFragment this$0, BaseQuickAdapter noName_0, View view, final int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(view, "view");
        CommBaseLoadMoreAdapter<ReviewItem> commBaseLoadMoreAdapter = this$0.adapter;
        CommBaseLoadMoreAdapter<ReviewItem> commBaseLoadMoreAdapter2 = null;
        if (commBaseLoadMoreAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseLoadMoreAdapter = null;
        }
        final ReviewItem item = commBaseLoadMoreAdapter.getItem(i);
        switch (view.getId()) {
            case R.id.avatarView /* 2131296424 */:
                if (((User[]) ((EditText) this$0._$_findCachedViewById(R.id.etContent)).getText().getSpans(0, ((EditText) this$0._$_findCachedViewById(R.id.etContent)).length(), User.class)).length >= 10) {
                    ToastUtils.showShort("Choose up to 10 at a time", new Object[0]);
                    return;
                } else if (((EditText) this$0._$_findCachedViewById(R.id.etContent)).isFocused()) {
                    String uid = item.getUid();
                    if (uid == null) {
                        uid = "";
                    }
                    String username = item.getUsername();
                    User user = new User(uid, username != null ? username : "");
                    Editable text = ((EditText) this$0._$_findCachedViewById(R.id.etContent)).getText();
                    if (text == null) {
                        throw new NullPointerException("null cannot be cast to non-null type android.text.SpannableStringBuilder");
                    }
                    ((SpannableStringBuilder) text).append((CharSequence) this$0.methodContext.newSpannable(user)).append((CharSequence) " ");
                    return;
                } else {
                    return;
                }
            case R.id.ivAction /* 2131296911 */:
                if (Intrinsics.areEqual(item.getUid(), App.getUserData().getUid())) {
                    new XPopup.Builder(this$0.getContext()).atView(view).asAttachList(new String[]{"Delete"}, null, new OnSelectListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ReviewDialogFragment$ckMlF6JaOfwofwhIBIU4uVAQTRo
                        @Override // com.lxj.xpopup.interfaces.OnSelectListener
                        public final void onSelect(int i2, String str) {
                            ReviewDialogFragment.m1070initListener$lambda8$lambda5(ReviewDialogFragment.this, i, item, i2, str);
                        }
                    }).show();
                    return;
                }
                XPopup.Builder atView = new XPopup.Builder(this$0.getContext()).atView(view);
                String[] strArr = new String[2];
                strArr[0] = "Report";
                strArr[1] = item.getBlocked() == 1 ? "Unblock" : "Block";
                atView.asAttachList(strArr, null, new OnSelectListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ReviewDialogFragment$ltMOPqIwpP7E1sOWiY3_dejZCe4
                    @Override // com.lxj.xpopup.interfaces.OnSelectListener
                    public final void onSelect(int i2, String str) {
                        ReviewDialogFragment.m1071initListener$lambda8$lambda7(ReviewDialogFragment.this, item, i2, str);
                    }
                }).show();
                return;
            case R.id.llLike /* 2131297190 */:
                Integer support_status = item.getSupport_status();
                if (support_status != null && support_status.intValue() == 0) {
                    Integer support = item.getSupport();
                    item.setSupport(support == null ? null : Integer.valueOf(support.intValue() + 1));
                    item.setSupport_status(1);
                    CommBaseLoadMoreAdapter<ReviewItem> commBaseLoadMoreAdapter3 = this$0.adapter;
                    if (commBaseLoadMoreAdapter3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    } else {
                        commBaseLoadMoreAdapter2 = commBaseLoadMoreAdapter3;
                    }
                    commBaseLoadMoreAdapter2.notifyItemChanged(i);
                    ((ReviewDialogPresenter) this$0.mPresenter).likeReview(item.getComment_id(), 1, this$0.boxType);
                    return;
                }
                Integer support_status2 = item.getSupport_status();
                if (support_status2 != null && support_status2.intValue() == 1) {
                    Integer support2 = item.getSupport();
                    item.setSupport(support2 == null ? null : Integer.valueOf(support2.intValue() - 1));
                    item.setSupport_status(0);
                    CommBaseLoadMoreAdapter<ReviewItem> commBaseLoadMoreAdapter4 = this$0.adapter;
                    if (commBaseLoadMoreAdapter4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    } else {
                        commBaseLoadMoreAdapter2 = commBaseLoadMoreAdapter4;
                    }
                    commBaseLoadMoreAdapter2.notifyItemChanged(i);
                    ((ReviewDialogPresenter) this$0.mPresenter).likeReview(item.getComment_id(), 0, this$0.boxType);
                    return;
                }
                return;
            case R.id.llReply /* 2131297211 */:
                SpanUtils with = SpanUtils.with((TextView) this$0._$_findCachedViewById(R.id.tvReplyAt));
                Intrinsics.checkNotNullExpressionValue(with, "with(tvReplyAt)");
                SpanUtils addText = CommonExtKt.addText(with, "Reply ", 16, R.color.white_70alpha);
                String username2 = item.getUsername();
                CommonExtKt.addText(addText, username2 != null ? username2 : "", 16, R.color.color_main_blue).create();
                TextView tvReplyAt = (TextView) this$0._$_findCachedViewById(R.id.tvReplyAt);
                Intrinsics.checkNotNullExpressionValue(tvReplyAt, "tvReplyAt");
                CommonExtKt.visible(tvReplyAt);
                TextView tvReply = (TextView) this$0._$_findCachedViewById(R.id.tvReply);
                Intrinsics.checkNotNullExpressionValue(tvReply, "tvReply");
                CommonExtKt.gone(tvReply);
                this$0.replyId = item.getComment_id();
                ((EditText) this$0._$_findCachedViewById(R.id.etContent)).postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ReviewDialogFragment$D8gRE-FAI4nUTYmIHriJrAVtBEE
                    @Override // java.lang.Runnable
                    public final void run() {
                        ReviewDialogFragment.m1069initListener$lambda8$lambda4(ReviewDialogFragment.this);
                    }
                }, 200L);
                KeyboardUtils.showSoftInput((EditText) this$0._$_findCachedViewById(R.id.etContent));
                return;
            case R.id.recyclerView /* 2131297621 */:
                ReplyActivity.Companion.start(this$0.getContext(), this$0.id, this$0.boxType, item.getComment_id(), item);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-8$lambda-4  reason: not valid java name */
    public static final void m1069initListener$lambda8$lambda4(ReviewDialogFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((SmartDragLayout) this$0._$_findCachedViewById(R.id.dragLayout)).open();
        ((EditText) this$0._$_findCachedViewById(R.id.etContent)).requestFocus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-8$lambda-5  reason: not valid java name */
    public static final void m1070initListener$lambda8$lambda5(ReviewDialogFragment this$0, int i, ReviewItem item, int i2, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(item, "$item");
        this$0.deleteComment(i, item.getComment_id());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-8$lambda-7  reason: not valid java name */
    public static final void m1071initListener$lambda8$lambda7(final ReviewDialogFragment this$0, ReviewItem item, int i, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(item, "$item");
        if (i == 0) {
            ((ReviewDialogPresenter) this$0.mPresenter).getReportReason(item.getComment_id());
        } else if (i != 1) {
        } else {
            BlockUserDialog newInstance = BlockUserDialog.Companion.newInstance(item.getUid(), item.getUsername(), item.getBlocked());
            newInstance.setListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ReviewDialogFragment$kmzcUQo2Hbma7JVSnrAm4vPJDrE
                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                public final void onClick() {
                    ReviewDialogFragment.m1072initListener$lambda8$lambda7$lambda6(ReviewDialogFragment.this);
                }
            });
            FragmentManager childFragmentManager = this$0.getChildFragmentManager();
            Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
            newInstance.show(childFragmentManager, BlockUserDialog.class.getSimpleName());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-8$lambda-7$lambda-6  reason: not valid java name */
    public static final void m1072initListener$lambda8$lambda7$lambda6(ReviewDialogFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.currPage = 1;
        ((ReviewDialogPresenter) this$0.mPresenter).getReviews(this$0.id, this$0.boxType, this$0.currPage, this$0.pageSize, this$0.currSort);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-9  reason: not valid java name */
    public static final void m1073initListener$lambda9(ReviewDialogFragment this$0, BaseQuickAdapter noName_0, View view, int i) {
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

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-11  reason: not valid java name */
    public static final void m1062initListener$lambda11(ReviewDialogFragment this$0, View view) {
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
    /* renamed from: initListener$lambda-12  reason: not valid java name */
    public static final void m1063initListener$lambda12(ReviewDialogFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.sendReview();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-13  reason: not valid java name */
    public static final void m1064initListener$lambda13(ReviewDialogFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        KeyboardUtils.hideSoftInput((EditText) this$0._$_findCachedViewById(R.id.etContent));
        AlbumBuilder fileProviderAuthority = EasyPhotos.createAlbum((Fragment) this$0, true, (ImageEngine) ChooseImageGlideEngine.getInstance()).setFileProviderAuthority("com.movieboxpro.android.fileProvider");
        CommBaseAdapter<ReviewImage> commBaseAdapter = this$0.sendImageAdapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sendImageAdapter");
            commBaseAdapter = null;
        }
        fileProviderAuthority.setCount(4 - commBaseAdapter.getData().size()).start(100);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-15  reason: not valid java name */
    public static final void m1065initListener$lambda15(ReviewDialogFragment this$0, BaseQuickAdapter noName_0, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(view, "view");
        ArrayList arrayList = new ArrayList();
        CommBaseAdapter<ReviewImage> commBaseAdapter = this$0.sendImageAdapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sendImageAdapter");
            commBaseAdapter = null;
        }
        for (ReviewImage reviewImage : commBaseAdapter.getData()) {
            arrayList.add(reviewImage.getUri());
        }
        new XPopup.Builder(this$0.getContext()).asImageViewer(null, i, arrayList, null, new XpopImageLoader()).show();
    }

    private final void deleteComment(int i, String str) {
        ReviewDialogFragment reviewDialogFragment = this;
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.Companion.post(reviewDialogFragment, ApiUtils.INSTANCE.deleteComment(this.boxType)).param("comment_id", str).asRequest(), reviewDialogFragment), new ReviewDialogFragment$deleteComment$1(this), null, new ReviewDialogFragment$deleteComment$2(this), null, new ReviewDialogFragment$deleteComment$3(this, i), 10, null);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStop() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            KeyboardUtils.unregisterSoftInputChangedListener(activity.getWindow());
        }
        super.onStop();
    }

    @Override // androidx.fragment.app.DialogFragment
    public void dismiss() {
        EditText editText = (EditText) _$_findCachedViewById(R.id.etContent);
        if (editText != null) {
            KeyboardUtils.hideSoftInput(editText);
        }
        super.dismiss();
    }

    @Override // com.movieboxpro.android.base.mvp.BaseDialogMvpFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    private final void sendReview() {
        Editable text = ((EditText) _$_findCachedViewById(R.id.etContent)).getText();
        int i = 0;
        User[] userArr = (User[]) text.getSpans(0, ((EditText) _$_findCachedViewById(R.id.etContent)).length(), User.class);
        if (userArr != null) {
            int length = userArr.length;
            while (i < length) {
                User user = userArr[i];
                i++;
                text.replace(text.getSpanStart(user), text.getSpanEnd(user), "[@]" + user.getId() + "[/@]");
            }
        }
        String obj = ((EditText) _$_findCachedViewById(R.id.etContent)).getText().toString();
        ((EditText) _$_findCachedViewById(R.id.etContent)).setText("");
        CommBaseAdapter<ReviewImage> commBaseAdapter = this.sendImageAdapter;
        CommBaseAdapter<ReviewImage> commBaseAdapter2 = null;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sendImageAdapter");
            commBaseAdapter = null;
        }
        if (commBaseAdapter.getData().isEmpty()) {
            ((ReviewDialogPresenter) this.mPresenter).sendReview(this.replyId, this.id, obj, this.boxType, new ArrayList<>());
            return;
        }
        CommBaseAdapter<ReviewImage> commBaseAdapter3 = this.sendImageAdapter;
        if (commBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sendImageAdapter");
        } else {
            commBaseAdapter2 = commBaseAdapter3;
        }
        Object as = Observable.just(commBaseAdapter2.getData()).map($$Lambda$ReviewDialogFragment$LrkgRmzKyvtOeoKRQH0d_uPZYM.INSTANCE).map(new Function() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ReviewDialogFragment$7_ZLJr4E5q66G9kqwv_RwfjSa0A
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj2) {
                List m1088sendReview$lambda21;
                m1088sendReview$lambda21 = ReviewDialogFragment.m1088sendReview$lambda21(ReviewDialogFragment.this, (ArrayList) obj2);
                return m1088sendReview$lambda21;
            }
        }).flatMap($$Lambda$ReviewDialogFragment$xEIbzpyOOXv7ignOOWPTRrVnsMA.INSTANCE).flatMap(new Function() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ReviewDialogFragment$qCRifBi1cAzPAqpr5oE-q975_lU
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj2) {
                ObservableSource m1090sendReview$lambda23;
                m1090sendReview$lambda23 = ReviewDialogFragment.m1090sendReview$lambda23(ReviewDialogFragment.this, (File) obj2);
                return m1090sendReview$lambda23;
            }
        }).toList().toObservable().map($$Lambda$ReviewDialogFragment$0nD4sJVxqJYBm7DVg1AN7g5BM0.INSTANCE).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(as, "just(sendImageAdapter.da…bindLifecycleOwner(this))");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, new ReviewDialogFragment$sendReview$7(this), null, new ReviewDialogFragment$sendReview$8(this), null, new ReviewDialogFragment$sendReview$9(this, obj), 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendReview$lambda-20  reason: not valid java name */
    public static final ArrayList m1087sendReview$lambda20(List it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ArrayList arrayList = new ArrayList();
        Iterator it2 = it.iterator();
        while (it2.hasNext()) {
            arrayList.add(((ReviewImage) it2.next()).getPath());
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendReview$lambda-21  reason: not valid java name */
    public static final List m1088sendReview$lambda21(ReviewDialogFragment this$0, ArrayList it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        return Luban.with(this$0.getContext()).load(it).get();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendReview$lambda-22  reason: not valid java name */
    public static final ObservableSource m1089sendReview$lambda22(List it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Observable.fromIterable(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendReview$lambda-23  reason: not valid java name */
    public static final ObservableSource m1090sendReview$lambda23(ReviewDialogFragment this$0, File it) {
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
        HttpUploadRequest addParam = addBaseParams.addParam("mid", str);
        String str2 = this$0.id;
        if (str2 == null) {
            str2 = "";
        }
        HttpUploadRequest addParam2 = addParam.addParam("pid", str2);
        String str3 = this$0.id;
        return addParam2.addParam("actor_id", str3 != null ? str3 : "").addParam("box_type", Integer.valueOf(this$0.boxType)).addParam("width", Integer.valueOf(i2)).addParam("height", Integer.valueOf(i)).asRequest().compose(RxUtils.rxTranslate2Bean(UploadImgResponse.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendReview$lambda-25  reason: not valid java name */
    public static final ArrayList m1091sendReview$lambda25(List it) {
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

    @Override // com.movieboxpro.android.base.mvp.BaseDialogMvpFragment
    protected void initData() {
        Bundle arguments = getArguments();
        CommBaseAdapter<ReviewImage> commBaseAdapter = null;
        this.id = arguments == null ? null : arguments.getString("id");
        Bundle arguments2 = getArguments();
        this.boxType = arguments2 == null ? 0 : arguments2.getInt("boxType", 0);
        ((ReviewDialogPresenter) this.mPresenter).getReviewNum(this.id, this.boxType);
        ((ImageView) _$_findCachedViewById(R.id.ivSend)).setEnabled(false);
        this.adapter = new CommBaseLoadMoreAdapter<>(R.layout.adapter_reviews_item, new ReviewDialogFragment$initData$1(this), null, 4, null);
        ((RecyclerView) _$_findCachedViewById(R.id.recyclerView)).setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.recyclerView);
        Intrinsics.checkNotNullExpressionValue(recyclerView, "recyclerView");
        CommonExtKt.disableRefreshAnimation(recyclerView);
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.recyclerView);
        CommBaseLoadMoreAdapter<ReviewItem> commBaseLoadMoreAdapter = this.adapter;
        if (commBaseLoadMoreAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseLoadMoreAdapter = null;
        }
        recyclerView2.setAdapter(commBaseLoadMoreAdapter);
        CommBaseLoadMoreAdapter<ReviewItem> commBaseLoadMoreAdapter2 = this.adapter;
        if (commBaseLoadMoreAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseLoadMoreAdapter2 = null;
        }
        commBaseLoadMoreAdapter2.getLoadMoreModule().setEnableLoadMore(true);
        CommBaseLoadMoreAdapter<ReviewItem> commBaseLoadMoreAdapter3 = this.adapter;
        if (commBaseLoadMoreAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseLoadMoreAdapter3 = null;
        }
        commBaseLoadMoreAdapter3.getLoadMoreModule().setAutoLoadMore(true);
        CommBaseLoadMoreAdapter<ReviewItem> commBaseLoadMoreAdapter4 = this.adapter;
        if (commBaseLoadMoreAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseLoadMoreAdapter4 = null;
        }
        commBaseLoadMoreAdapter4.getLoadMoreModule().setLoadMoreView(new CustomLoadMoreView());
        CommBaseLoadMoreAdapter<ReviewItem> commBaseLoadMoreAdapter5 = this.adapter;
        if (commBaseLoadMoreAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseLoadMoreAdapter5 = null;
        }
        commBaseLoadMoreAdapter5.addChildClickViewIds(R.id.llReply, R.id.llLike, R.id.recyclerView, R.id.ivAction);
        CommBaseLoadMoreAdapter<ReviewItem> commBaseLoadMoreAdapter6 = this.adapter;
        if (commBaseLoadMoreAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseLoadMoreAdapter6 = null;
        }
        commBaseLoadMoreAdapter6.addChildClickViewIds(R.id.avatarView);
        CommBaseAdapter<ReviewImage> commBaseAdapter2 = new CommBaseAdapter<>(R.layout.adapter_send_review_item, new ReviewDialogFragment$initData$2(this), null, 4, null);
        this.sendImageAdapter = commBaseAdapter2;
        if (commBaseAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sendImageAdapter");
            commBaseAdapter2 = null;
        }
        commBaseAdapter2.addChildClickViewIds(R.id.ivDelete);
        ((RecyclerView) _$_findCachedViewById(R.id.rvImage)).setLayoutManager(new GridLayoutManager(getContext(), 4));
        ((RecyclerView) _$_findCachedViewById(R.id.rvImage)).addItemDecoration(new GridSpacingItemDecoration(4, CommonExtKt.dp2Px(8), true));
        RecyclerView rvImage = (RecyclerView) _$_findCachedViewById(R.id.rvImage);
        Intrinsics.checkNotNullExpressionValue(rvImage, "rvImage");
        CommonExtKt.disableRefreshAnimation(rvImage);
        RecyclerView recyclerView3 = (RecyclerView) _$_findCachedViewById(R.id.rvImage);
        CommBaseAdapter<ReviewImage> commBaseAdapter3 = this.sendImageAdapter;
        if (commBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sendImageAdapter");
        } else {
            commBaseAdapter = commBaseAdapter3;
        }
        recyclerView3.setAdapter(commBaseAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void toImageShow(int i, View view, List<String> list, ImageView imageView) {
        new XPopup.Builder(getContext()).asImageViewer(null, i, list, null, new XpopImageLoader()).show();
    }

    @Override // com.movieboxpro.android.base.mvp.BaseDialogMvpFragment
    protected void initView() {
        Window window;
        if (Intrinsics.areEqual(PrefsUtils.getInstance().getString(Constant.Prefs.REVIEW_SORT, "update_time_desc"), "update_time_desc")) {
            this.currSort = "update_time_desc";
            ((ImageView) _$_findCachedViewById(R.id.ivSort)).setImageResource(R.mipmap.ic_review_sort_default);
        } else {
            this.currSort = "dateline_asc";
            ((ImageView) _$_findCachedViewById(R.id.ivSort)).setImageResource(R.mipmap.ic_review_sort_time);
        }
        Dialog dialog = getDialog();
        if (dialog != null && (window = dialog.getWindow()) != null) {
            window.setSoftInputMode(18);
        }
        this.methodContext.setMethod(Weibo.INSTANCE);
        MethodContext methodContext = this.methodContext;
        EditText etContent = (EditText) _$_findCachedViewById(R.id.etContent);
        Intrinsics.checkNotNullExpressionValue(etContent, "etContent");
        methodContext.init(etContent);
        ((SmartDragLayout) _$_findCachedViewById(R.id.dragLayout)).enableDrag(true);
        ((SmartDragLayout) _$_findCachedViewById(R.id.dragLayout)).hasShadowBg(false);
        ((SmartDragLayout) _$_findCachedViewById(R.id.dragLayout)).open();
        ((SmartDragLayout) _$_findCachedViewById(R.id.dragLayout)).setOnCloseListener(new SmartDragLayout.OnCloseListener() { // from class: com.movieboxpro.android.view.dialog.ReviewDialogFragment$initView$1
            @Override // com.lxj.xpopup.widget.SmartDragLayout.OnCloseListener
            public void onOpen() {
            }

            @Override // com.lxj.xpopup.widget.SmartDragLayout.OnCloseListener
            public void onClose() {
                ReviewDialogFragment.this.dismiss();
            }
        });
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        DialogAction.ActionListener actionListener = this.dismissListener;
        if (actionListener != null) {
            actionListener.onClick();
        }
        super.onDismiss(dialog);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseDialogMvpFragment
    protected void loadData() {
        ((ReviewDialogPresenter) this.mPresenter).getReviews(this.id, this.boxType, this.currPage, this.pageSize, this.currSort);
    }

    @Override // com.movieboxpro.android.view.dialog.ReviewDialogContract.View
    public void reviewSuccess() {
        this.currPage = 1;
        ((ReviewDialogPresenter) this.mPresenter).getReviews(this.id, this.boxType, this.currPage, this.pageSize, this.currSort);
        ((ReviewDialogPresenter) this.mPresenter).getReviewNum(this.id, this.boxType);
        CommBaseAdapter<ReviewImage> commBaseAdapter = this.sendImageAdapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sendImageAdapter");
            commBaseAdapter = null;
        }
        commBaseAdapter.setList(new ArrayList());
        ((EditText) _$_findCachedViewById(R.id.etContent)).setText("");
        TextView tvReplyAt = (TextView) _$_findCachedViewById(R.id.tvReplyAt);
        Intrinsics.checkNotNullExpressionValue(tvReplyAt, "tvReplyAt");
        CommonExtKt.gone(tvReplyAt);
        this.replyId = "";
        ((RecyclerView) _$_findCachedViewById(R.id.recyclerView)).postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ReviewDialogFragment$5WkykM0DAtc1-JgWDg47P3NhiZE
            @Override // java.lang.Runnable
            public final void run() {
                ReviewDialogFragment.m1086reviewSuccess$lambda26(ReviewDialogFragment.this);
            }
        }, 200L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: reviewSuccess$lambda-26  reason: not valid java name */
    public static final void m1086reviewSuccess$lambda26(ReviewDialogFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        KeyboardUtils.hideSoftInput((EditText) this$0._$_findCachedViewById(R.id.etContent));
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) ((RecyclerView) this$0._$_findCachedViewById(R.id.recyclerView)).getLayoutManager();
        if (linearLayoutManager == null) {
            return;
        }
        linearLayoutManager.scrollToPositionWithOffset(0, 0);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseDialogMvpFragment, com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void showLoading() {
        ProgressBar loading = (ProgressBar) _$_findCachedViewById(R.id.loading);
        Intrinsics.checkNotNullExpressionValue(loading, "loading");
        CommonExtKt.visible(loading);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseDialogMvpFragment, com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void hideLoading() {
        ProgressBar loading = (ProgressBar) _$_findCachedViewById(R.id.loading);
        Intrinsics.checkNotNullExpressionValue(loading, "loading");
        CommonExtKt.gone(loading);
    }

    private final void registerListener() {
        FragmentActivity activity;
        if (this.register || (activity = getActivity()) == null) {
            return;
        }
        this.register = true;
        KeyboardUtils.registerSoftInputChangedListener(activity.getWindow(), new KeyboardUtils.OnSoftInputChangedListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ReviewDialogFragment$-2NPkikQ_6-mrxXRowc8HnO_BE4
            @Override // com.movieboxpro.android.utils.KeyboardUtils.OnSoftInputChangedListener
            public final void onSoftInputChanged(int i) {
                ReviewDialogFragment.m1085registerListener$lambda28$lambda27(ReviewDialogFragment.this, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: registerListener$lambda-28$lambda-27  reason: not valid java name */
    public static final void m1085registerListener$lambda28$lambda27(ReviewDialogFragment this$0, int i) {
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
            RecyclerView recyclerView = (RecyclerView) this$0._$_findCachedViewById(R.id.rvImage);
            if (recyclerView == null) {
                return;
            }
            CommonExtKt.visible(recyclerView);
            return;
        }
        TextView textView3 = (TextView) this$0._$_findCachedViewById(R.id.tvReplyAt);
        if (textView3 != null) {
            CommonExtKt.gone(textView3);
        }
        RecyclerView recyclerView2 = (RecyclerView) this$0._$_findCachedViewById(R.id.rvImage);
        if (recyclerView2 == null) {
            return;
        }
        CommonExtKt.gone(recyclerView2);
    }

    @Override // com.movieboxpro.android.view.dialog.ReviewDialogContract.View
    public void showReviews(List<ReviewItem> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        ((SmartDragLayout) _$_findCachedViewById(R.id.dragLayout)).open();
        CommBaseLoadMoreAdapter<ReviewItem> commBaseLoadMoreAdapter = null;
        if (list.isEmpty()) {
            LayoutInflater from = LayoutInflater.from(getContext());
            ViewParent parent = ((RecyclerView) _$_findCachedViewById(R.id.recyclerView)).getParent();
            if (parent != null) {
                View view = from.inflate(R.layout.empty_view_layout, (ViewGroup) parent, false);
                ((TextView) view.findViewById(R.id.empty_text)).setText("No Reviews");
                CommBaseLoadMoreAdapter<ReviewItem> commBaseLoadMoreAdapter2 = this.adapter;
                if (commBaseLoadMoreAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                } else {
                    commBaseLoadMoreAdapter = commBaseLoadMoreAdapter2;
                }
                Intrinsics.checkNotNullExpressionValue(view, "view");
                commBaseLoadMoreAdapter.setEmptyView(view);
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup");
        }
        CommBaseLoadMoreAdapter<ReviewItem> commBaseLoadMoreAdapter3 = this.adapter;
        if (commBaseLoadMoreAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseLoadMoreAdapter3 = null;
        }
        commBaseLoadMoreAdapter3.removeEmptyView();
        CommBaseLoadMoreAdapter<ReviewItem> commBaseLoadMoreAdapter4 = this.adapter;
        if (commBaseLoadMoreAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            commBaseLoadMoreAdapter = commBaseLoadMoreAdapter4;
        }
        commBaseLoadMoreAdapter.setList(list);
        ((RecyclerView) _$_findCachedViewById(R.id.recyclerView)).postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ReviewDialogFragment$Bwirs2OIkOZdo9E0grNGwx0GLwI
            @Override // java.lang.Runnable
            public final void run() {
                ReviewDialogFragment.m1093showReviews$lambda29(ReviewDialogFragment.this);
            }
        }, 500L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showReviews$lambda-29  reason: not valid java name */
    public static final void m1093showReviews$lambda29(ReviewDialogFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!this$0.registerListener) {
            this$0.registerListener();
            this$0.registerListener = true;
        }
        SmartDragLayout smartDragLayout = (SmartDragLayout) this$0._$_findCachedViewById(R.id.dragLayout);
        if (smartDragLayout == null) {
            return;
        }
        smartDragLayout.smoothScroll(((SmartDragLayout) this$0._$_findCachedViewById(R.id.dragLayout)).getMeasuredHeight(), true);
    }

    @Override // com.movieboxpro.android.view.dialog.ReviewDialogContract.View
    public void showMoreReviews(List<ReviewItem> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        CommBaseLoadMoreAdapter<ReviewItem> commBaseLoadMoreAdapter = null;
        if (list.isEmpty()) {
            if (this.currPage == 2) {
                CommBaseLoadMoreAdapter<ReviewItem> commBaseLoadMoreAdapter2 = this.adapter;
                if (commBaseLoadMoreAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                } else {
                    commBaseLoadMoreAdapter = commBaseLoadMoreAdapter2;
                }
                commBaseLoadMoreAdapter.getLoadMoreModule().loadMoreEnd(true);
                return;
            }
            CommBaseLoadMoreAdapter<ReviewItem> commBaseLoadMoreAdapter3 = this.adapter;
            if (commBaseLoadMoreAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                commBaseLoadMoreAdapter3 = null;
            }
            BaseLoadMoreModule.loadMoreEnd$default(commBaseLoadMoreAdapter3.getLoadMoreModule(), false, 1, null);
            return;
        }
        CommBaseLoadMoreAdapter<ReviewItem> commBaseLoadMoreAdapter4 = this.adapter;
        if (commBaseLoadMoreAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseLoadMoreAdapter4 = null;
        }
        commBaseLoadMoreAdapter4.removeEmptyView();
        CommBaseLoadMoreAdapter<ReviewItem> commBaseLoadMoreAdapter5 = this.adapter;
        if (commBaseLoadMoreAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseLoadMoreAdapter5 = null;
        }
        commBaseLoadMoreAdapter5.addData(list);
        CommBaseLoadMoreAdapter<ReviewItem> commBaseLoadMoreAdapter6 = this.adapter;
        if (commBaseLoadMoreAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            commBaseLoadMoreAdapter = commBaseLoadMoreAdapter6;
        }
        commBaseLoadMoreAdapter.getLoadMoreModule().loadMoreComplete();
    }

    @Override // com.movieboxpro.android.view.dialog.ReviewDialogContract.View
    public void showReviewNum(String num) {
        Intrinsics.checkNotNullParameter(num, "num");
        if (Intrinsics.areEqual(num, "0") || Intrinsics.areEqual(num, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
            ((TextView) _$_findCachedViewById(R.id.tvReviewNum)).setText(Intrinsics.stringPlus(num, " comment"));
        } else {
            ((TextView) _$_findCachedViewById(R.id.tvReviewNum)).setText(Intrinsics.stringPlus(num, " comments"));
        }
    }

    @Override // com.movieboxpro.android.view.dialog.ReviewDialogContract.View
    public void loadMoreFailed() {
        CommBaseLoadMoreAdapter<ReviewItem> commBaseLoadMoreAdapter = this.adapter;
        if (commBaseLoadMoreAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseLoadMoreAdapter = null;
        }
        commBaseLoadMoreAdapter.getLoadMoreModule().loadMoreFail();
        this.currPage--;
    }

    @Override // com.movieboxpro.android.view.dialog.ReviewDialogContract.View
    public void showReportReason(final List<ReportReason> list, final String str) {
        Intrinsics.checkNotNullParameter(list, "list");
        list.add(new ReportReason("0", "Other"));
        List<ReportReason> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        for (ReportReason reportReason : list2) {
            arrayList.add(reportReason.getReason());
        }
        new ActionSheetDialog(getContext()).builder().setCancelable(true).setCanceledOnTouchOutside(true).addSheetItems(arrayList, new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ReviewDialogFragment$K5kQKPL8awHEM7ISoegyFGKFJaw
            @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
            public final void onClick(int i) {
                ReviewDialogFragment.m1092showReportReason$lambda31(str, list, this, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showReportReason$lambda-31  reason: not valid java name */
    public static final void m1092showReportReason$lambda31(String str, List list, ReviewDialogFragment this$0, int i) {
        Intrinsics.checkNotNullParameter(list, "$list");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ReportReviewDialogFragment.Companion companion = ReportReviewDialogFragment.Companion;
        if (str == null) {
            str = "";
        }
        String id = ((ReportReason) list.get(i - 1)).getId();
        companion.newInstance(str, id != null ? id : "", this$0.boxType).show(this$0.getChildFragmentManager(), ReportReviewDialogFragment.class.getSimpleName());
    }

    @Override // androidx.fragment.app.Fragment
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
            RecyclerView rvImage = (RecyclerView) _$_findCachedViewById(R.id.rvImage);
            Intrinsics.checkNotNullExpressionValue(rvImage, "rvImage");
            CommonExtKt.visible(rvImage);
            CommBaseAdapter<ReviewImage> commBaseAdapter = this.sendImageAdapter;
            if (commBaseAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sendImageAdapter");
                commBaseAdapter = null;
            }
            commBaseAdapter.addData(arrayList);
            ((RecyclerView) _$_findCachedViewById(R.id.recyclerView)).postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ReviewDialogFragment$kdzWSI2SHsgE8JOWaXdkZcyzAY8
                @Override // java.lang.Runnable
                public final void run() {
                    ReviewDialogFragment.m1084onActivityResult$lambda33(ReviewDialogFragment.this);
                }
            }, 500L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onActivityResult$lambda-33  reason: not valid java name */
    public static final void m1084onActivityResult$lambda33(ReviewDialogFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((SmartDragLayout) this$0._$_findCachedViewById(R.id.dragLayout)).requestLayout();
    }

    /* compiled from: ReviewDialogFragment.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ReviewDialogFragment$Companion;", "", "()V", "REQUEST_CODE_CHOOSE", "", "newInstance", "Lcom/movieboxpro/android/view/dialog/ReviewDialogFragment;", "fid", "", "boxType", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final ReviewDialogFragment newInstance(String str, int i) {
            ReviewDialogFragment reviewDialogFragment = new ReviewDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString("id", str);
            bundle.putInt("boxType", i);
            reviewDialogFragment.setArguments(bundle);
            return reviewDialogFragment;
        }
    }

    /* compiled from: ReviewDialogFragment.kt */
    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001f2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u001fB\u0005¢\u0006\u0002\u0010\u0004J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014J\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0003H\u0014J\u000e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0011H\u0014J\u0018\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0002H\u0014J\b\u0010\u0016\u001a\u00020\u0006H\u0014J2\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00062\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u000e\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/movieboxpro/android/view/dialog/ReviewDialogFragment$ReviewListFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/ReviewItem;", "Lcom/movieboxpro/android/model/ReviewResponse;", "()V", "boxType", "", "fid", "", "getBundle", "", "arguments", "Landroid/os/Bundle;", "getData", "", "model", "getServiceData", "Lio/reactivex/Observable;", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "initItemLayout", "toImageShow", "position", "view", "Landroid/view/View;", "images", "", "imageView", "Landroid/widget/ImageView;", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class ReviewListFragment extends BaseListFragment<ReviewItem, ReviewResponse> {
        public static final Companion Companion = new Companion(null);
        public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
        private int boxType;
        private String fid;

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
        protected int initItemLayout() {
            return R.layout.adapter_reviews_item;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment, com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
        public /* synthetic */ void onDestroyView() {
            super.onDestroyView();
            _$_clearFindViewByIdCache();
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected Observable<String> getServiceData() {
            return HttpRequest.Companion.post(this, ApiUtils.INSTANCE.getReviews(this.boxType)).param("box_type", Integer.valueOf(this.boxType)).param("mid", Integer.valueOf(getId())).param("pid", Integer.valueOf(getId())).param("actor_id", Integer.valueOf(getId())).param("page", Integer.valueOf(this.mCurrentPage)).param("pagelimit", Integer.valueOf(this.mPageSize)).asRequest();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public List<ReviewItem> getData(ReviewResponse model) {
            Intrinsics.checkNotNullParameter(model, "model");
            List<ReviewItem> list = model.getList();
            if (list == null) {
                list = new ArrayList<>();
            }
            return new ArrayList(list);
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void getBundle(Bundle bundle) {
            this.mClass = ReviewItem.class;
            this.mPageClass = ReviewResponse.class;
            this.fid = bundle == null ? null : bundle.getString("id");
            this.boxType = bundle != null ? bundle.getInt("boxType", 0) : 0;
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
                builder.asImageViewer((ImageView) view, i, list, new OnSrcViewUpdateListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ReviewDialogFragment$ReviewListFragment$tTL3Xe_cknkj5XA-EQIQ0bK5O8k
                    @Override // com.lxj.xpopup.interfaces.OnSrcViewUpdateListener
                    public final void onSrcViewUpdate(ImageViewerPopupView imageViewerPopupView, int i2) {
                        ReviewDialogFragment.ReviewListFragment.m1097toImageShow$lambda1$lambda0(imageView, imageViewerPopupView, i2);
                    }
                }, new XpopImageLoader()).show();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: toImageShow$lambda-1$lambda-0  reason: not valid java name */
        public static final void m1097toImageShow$lambda1$lambda0(ImageView imageView, ImageViewerPopupView popupView, int i) {
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
        @Override // com.movieboxpro.android.base.BaseListFragment
        public void initHolder(BaseViewHolder helper, ReviewItem item) {
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            helper.setText(R.id.tvUsername, item.getUsername());
            helper.setText(R.id.tvTime, TimeUtils.INSTANCE.formatTime2(item.getDateline() * 1000));
            ((UserAvatarView) helper.getView(R.id.avatarView)).setAvatar(item.getAvatar(), item.getUsername());
            RecyclerView recyclerView = (RecyclerView) helper.getView(R.id.recyclerView);
            List<ImageItem> img_list = item.getImg_list();
            if (img_list == null || img_list.isEmpty()) {
                CommonExtKt.gone(recyclerView);
                return;
            }
            CommonExtKt.visible(recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
            if (recyclerView.getTag() == null) {
                recyclerView.addItemDecoration(new LinearItemDecoration(0, 10, false));
                recyclerView.setTag("added");
            }
            CommBaseAdapter commBaseAdapter = new CommBaseAdapter(R.layout.adapter_review_image_item, new ReviewDialogFragment$ReviewListFragment$initHolder$imgAdapter$1(this), new ArrayList(item.getImg_list()));
            recyclerView.setAdapter(commBaseAdapter);
            final ArrayList arrayList = new ArrayList();
            for (ImageItem imageItem : item.getImg_list()) {
                String url = imageItem.getUrl();
                if (url == null) {
                    url = "";
                }
                arrayList.add(url);
            }
            commBaseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ReviewDialogFragment$ReviewListFragment$NUYOty-tNub7ZCKYAjoui0Xg57E
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    ReviewDialogFragment.ReviewListFragment.m1094initHolder$lambda3(ReviewDialogFragment.ReviewListFragment.this, arrayList, baseQuickAdapter, view, i);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: initHolder$lambda-3  reason: not valid java name */
        public static final void m1094initHolder$lambda3(ReviewListFragment this$0, ArrayList images, BaseQuickAdapter noName_0, View view, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(images, "$images");
            Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
            Intrinsics.checkNotNullParameter(view, "view");
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            Intrinsics.checkNotNullExpressionValue(imageView, "imageView");
            this$0.toImageShow(i, imageView, images, imageView);
        }

        /* compiled from: ReviewDialogFragment.kt */
        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ReviewDialogFragment$ReviewListFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/ReviewDialogFragment$ReviewListFragment;", "fid", "", "boxType", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        /* loaded from: classes3.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final ReviewListFragment newInstance(String str, int i) {
                ReviewListFragment reviewListFragment = new ReviewListFragment();
                Bundle bundle = new Bundle();
                bundle.putString("id", str);
                bundle.putInt("boxType", i);
                reviewListFragment.setArguments(bundle);
                return reviewListFragment;
            }
        }
    }
}
