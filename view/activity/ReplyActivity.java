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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
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
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.base.CommBaseAdapter;
import com.movieboxpro.android.base.mvp.BaseMvpActivity;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.http.HttpUploadRequest;
import com.movieboxpro.android.listener.ReviewListener;
import com.movieboxpro.android.model.AtItem;
import com.movieboxpro.android.model.Comment;
import com.movieboxpro.android.model.ImageItem;
import com.movieboxpro.android.model.ReviewImage;
import com.movieboxpro.android.model.ReviewItem;
import com.movieboxpro.android.model.ReviewResponse;
import com.movieboxpro.android.model.UploadImgResponse;
import com.movieboxpro.android.utils.ApiUtils;
import com.movieboxpro.android.utils.ChooseImageGlideEngine;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.utils.KeyboardUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SpanUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.utils.XpopImageLoader;
import com.movieboxpro.android.view.activity.ReplyActivity;
import com.movieboxpro.android.view.activity.ReplyContract;
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
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import top.zibin.luban.Luban;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: ReplyActivity.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 %2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0002%&B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0012\u001a\u00020\u0002H\u0014J\b\u0010\u0013\u001a\u00020\u0006H\u0014J\b\u0010\u0014\u001a\u00020\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u0015H\u0014J\b\u0010\u0017\u001a\u00020\u0015H\u0014J\b\u0010\u0018\u001a\u00020\u0019H\u0014J\b\u0010\u001a\u001a\u00020\u0019H\u0014J\"\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u00062\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014J\b\u0010 \u001a\u00020\u0015H\u0014J\b\u0010!\u001a\u00020\u0015H\u0014J\b\u0010\"\u001a\u00020\u0015H\u0014J\b\u0010#\u001a\u00020\u0015H\u0016J\b\u0010$\u001a\u00020\u0015H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082.¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/movieboxpro/android/view/activity/ReplyActivity;", "Lcom/movieboxpro/android/base/mvp/BaseMvpActivity;", "Lcom/movieboxpro/android/view/activity/ReplyPresenter;", "Lcom/movieboxpro/android/view/activity/ReplyContract$View;", "()V", "boxType", "", "commentId", "", "fragment", "Lcom/movieboxpro/android/view/activity/ReplyActivity$ReplyListFragment;", "id", "methodContext", "Lcom/snowtop/diskpanda/view/widget/at/MethodContext;", "replyId", "sendImageAdapter", "Lcom/movieboxpro/android/base/CommBaseAdapter;", "Lcom/movieboxpro/android/model/ReviewImage;", "bindPresenter", "getLayoutResId", "initData", "", "initListener", "initView", "isFullScreen", "", "isNeedLoadData", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onDestroy", "onStop", "requestData", "reviewSuccess", "sendReview", "Companion", "ReplyListFragment", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ReplyActivity extends BaseMvpActivity<ReplyPresenter> implements ReplyContract.View {
    public static final Companion Companion = new Companion(null);
    private int boxType;
    private ReplyListFragment fragment;
    private String id;
    private CommBaseAdapter<ReviewImage> sendImageAdapter;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String replyId = "";
    private String commentId = "";
    private final MethodContext methodContext = new MethodContext();

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
        return R.layout.activity_reply;
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
        ((LinearLayout) _$_findCachedViewById(R.id.llReview)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyActivity$U002f_BkfMV0qD4NHlUZzHueSmc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReplyActivity.m199initListener$lambda0(ReplyActivity.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.ll_back)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyActivity$6koJlQYn71DvzHP7qJBqC013veU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReplyActivity.m200initListener$lambda1(ReplyActivity.this, view);
            }
        });
        EditText etContent = (EditText) _$_findCachedViewById(R.id.etContent);
        Intrinsics.checkNotNullExpressionValue(etContent, "etContent");
        etContent.addTextChangedListener(new TextWatcher() { // from class: com.movieboxpro.android.view.activity.ReplyActivity$initListener$$inlined$doAfterTextChanged$1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                ImageView imageView = (ImageView) ReplyActivity.this._$_findCachedViewById(R.id.ivSend);
                String obj = editable == null ? null : editable.toString();
                imageView.setEnabled(!(obj == null || StringsKt.isBlank(obj)));
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivSend)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyActivity$i1psthjSVoOMznrRnpPtwcdRqd4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReplyActivity.m201initListener$lambda3(ReplyActivity.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivImage)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyActivity$HyY7M84kV4Jb1B-408eHd2uW710
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReplyActivity.m202initListener$lambda4(ReplyActivity.this, view);
            }
        });
        CommBaseAdapter<ReviewImage> commBaseAdapter = this.sendImageAdapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sendImageAdapter");
            commBaseAdapter = null;
        }
        commBaseAdapter.setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyActivity$eDfrfVlY_qlB207Laev_lUTAATw
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ReplyActivity.m203initListener$lambda5(ReplyActivity.this, baseQuickAdapter, view, i);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tvReply)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyActivity$Dy1Bfo1RS2H4-RD9Nqrix0zm8Hk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReplyActivity.m204initListener$lambda6(ReplyActivity.this, view);
            }
        });
        KeyboardUtils.registerSoftInputChangedListener(this, new KeyboardUtils.OnSoftInputChangedListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyActivity$VMM6hpu7XmawmmW6MzzVf0xYtj8
            @Override // com.movieboxpro.android.utils.KeyboardUtils.OnSoftInputChangedListener
            public final void onSoftInputChanged(int i) {
                ReplyActivity.m205initListener$lambda7(ReplyActivity.this, i);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivAt)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyActivity$Sqn5FBoXarUXKATK5Kd-6h8QIJ0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReplyActivity.m206initListener$lambda8(ReplyActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m199initListener$lambda0(ReplyActivity this$0, View view) {
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
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m200initListener$lambda1(ReplyActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-3  reason: not valid java name */
    public static final void m201initListener$lambda3(ReplyActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.sendReview();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-4  reason: not valid java name */
    public static final void m202initListener$lambda4(ReplyActivity this$0, View view) {
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
    /* renamed from: initListener$lambda-5  reason: not valid java name */
    public static final void m203initListener$lambda5(ReplyActivity this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        CommBaseAdapter<ReviewImage> commBaseAdapter = this$0.sendImageAdapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sendImageAdapter");
            commBaseAdapter = null;
        }
        commBaseAdapter.removeAt(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-6  reason: not valid java name */
    public static final void m204initListener$lambda6(ReplyActivity this$0, View view) {
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
    /* renamed from: initListener$lambda-7  reason: not valid java name */
    public static final void m205initListener$lambda7(ReplyActivity this$0, int i) {
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
    /* renamed from: initListener$lambda-8  reason: not valid java name */
    public static final void m206initListener$lambda8(ReplyActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (((User[]) ((EditText) this$0._$_findCachedViewById(R.id.etContent)).getText().getSpans(0, ((EditText) this$0._$_findCachedViewById(R.id.etContent)).length(), User.class)).length >= 10) {
            ToastUtils.showShort("Choose up to 10 at a time", new Object[0]);
            return;
        }
        KeyboardUtils.hideSoftInput((EditText) this$0._$_findCachedViewById(R.id.etContent));
        Object as = Observable.timer(200L, TimeUnit.MILLISECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this$0));
        Intrinsics.checkNotNullExpressionValue(as, "timer(200,TimeUnit.MILLI…bindLifecycleOwner(this))");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, null, null, null, null, ReplyActivity$initListener$9$1.INSTANCE, 15, null);
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
            ((ReplyPresenter) this.mPresenter).sendReview(this.replyId, this.id, obj, this.boxType, new ArrayList<>());
            return;
        }
        CommBaseAdapter<ReviewImage> commBaseAdapter3 = this.sendImageAdapter;
        if (commBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sendImageAdapter");
        } else {
            commBaseAdapter2 = commBaseAdapter3;
        }
        Object as = Observable.just(commBaseAdapter2.getData()).map($$Lambda$ReplyActivity$LkodFaPdIq1gKAWW3E0TYFtPwQ.INSTANCE).map(new Function() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyActivity$yKRAqr8uK2PxTglYBdkUbePa9_k
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj2) {
                List m214sendReview$lambda12;
                m214sendReview$lambda12 = ReplyActivity.m214sendReview$lambda12(ReplyActivity.this, (ArrayList) obj2);
                return m214sendReview$lambda12;
            }
        }).flatMap($$Lambda$ReplyActivity$DbssROTXcZupBZrQrSC8LeQbsU8.INSTANCE).flatMap(new Function() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyActivity$btRkNrtzANdKU-fSPZ7Tg6DmnaQ
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj2) {
                ObservableSource m216sendReview$lambda14;
                m216sendReview$lambda14 = ReplyActivity.m216sendReview$lambda14(ReplyActivity.this, (File) obj2);
                return m216sendReview$lambda14;
            }
        }).toList().toObservable().map($$Lambda$ReplyActivity$67b44SAbSsLXqDws2fgq1uhu7t0.INSTANCE).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(as, "just(sendImageAdapter.da…bindLifecycleOwner(this))");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, new ReplyActivity$sendReview$7(this), null, new ReplyActivity$sendReview$8(this), null, new ReplyActivity$sendReview$9(this, obj), 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendReview$lambda-11  reason: not valid java name */
    public static final ArrayList m213sendReview$lambda11(List it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ArrayList arrayList = new ArrayList();
        Iterator it2 = it.iterator();
        while (it2.hasNext()) {
            arrayList.add(((ReviewImage) it2.next()).getPath());
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendReview$lambda-12  reason: not valid java name */
    public static final List m214sendReview$lambda12(ReplyActivity this$0, ArrayList it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        return Luban.with(this$0).load(it).get();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendReview$lambda-13  reason: not valid java name */
    public static final ObservableSource m215sendReview$lambda13(List it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Observable.fromIterable(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendReview$lambda-14  reason: not valid java name */
    public static final ObservableSource m216sendReview$lambda14(ReplyActivity this$0, File it) {
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
    /* renamed from: sendReview$lambda-16  reason: not valid java name */
    public static final ArrayList m217sendReview$lambda16(List it) {
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
    }

    @Override // com.movieboxpro.android.view.activity.ReplyContract.View
    public void reviewSuccess() {
        ((EditText) _$_findCachedViewById(R.id.etContent)).setText("");
        ReplyListFragment replyListFragment = this.fragment;
        if (replyListFragment != null) {
            replyListFragment.startRefresh();
        }
        CommBaseAdapter<ReviewImage> commBaseAdapter = this.sendImageAdapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sendImageAdapter");
            commBaseAdapter = null;
        }
        commBaseAdapter.setList(new ArrayList());
        ((EditText) _$_findCachedViewById(R.id.etContent)).postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyActivity$6DTwCi_eCpbTCBGPaNKQ9POkeuY
            @Override // java.lang.Runnable
            public final void run() {
                ReplyActivity.m212reviewSuccess$lambda17(ReplyActivity.this);
            }
        }, 500L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: reviewSuccess$lambda-17  reason: not valid java name */
    public static final void m212reviewSuccess$lambda17(ReplyActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) ((RecyclerView) this$0._$_findCachedViewById(R.id.recyclerView)).getLayoutManager();
        if (linearLayoutManager != null) {
            linearLayoutManager.scrollToPositionWithOffset(0, 0);
        }
        KeyboardUtils.hideSoftInput((EditText) this$0._$_findCachedViewById(R.id.etContent));
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initData() {
        this.id = getIntent().getStringExtra("id");
        this.boxType = getIntent().getIntExtra("boxType", 1);
        this.commentId = getIntent().getStringExtra("commentId");
        ReplyListFragment.Companion companion = ReplyListFragment.Companion;
        String str = this.id;
        int i = this.boxType;
        String str2 = this.commentId;
        Parcelable parcelableExtra = getIntent().getParcelableExtra("data");
        Intrinsics.checkNotNull(parcelableExtra);
        Intrinsics.checkNotNullExpressionValue(parcelableExtra, "intent.getParcelableExtra(\"data\")!!");
        ReplyListFragment newInstance = companion.newInstance(str, i, str2, (ReviewItem) parcelableExtra);
        this.fragment = newInstance;
        Intrinsics.checkNotNull(newInstance);
        newInstance.setListener(new ReplyActivity$initData$1(this));
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        ReplyListFragment replyListFragment = this.fragment;
        Intrinsics.checkNotNull(replyListFragment);
        FragmentUtils.add(supportFragmentManager, replyListFragment, (int) R.id.container);
        CommBaseAdapter<ReviewImage> commBaseAdapter = new CommBaseAdapter<>(R.layout.adapter_send_review_item, new ReplyActivity$initData$2(this), null, 4, null);
        this.sendImageAdapter = commBaseAdapter;
        CommBaseAdapter<ReviewImage> commBaseAdapter2 = null;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sendImageAdapter");
            commBaseAdapter = null;
        }
        commBaseAdapter.addChildClickViewIds(R.id.flDelete);
        ((RecyclerView) _$_findCachedViewById(R.id.rvImage)).setLayoutManager(new GridLayoutManager(this, 4));
        ((RecyclerView) _$_findCachedViewById(R.id.rvImage)).addItemDecoration(new GridSpacingItemDecoration(4, CommonExtKt.dp2Px(8), true));
        RecyclerView rvImage = (RecyclerView) _$_findCachedViewById(R.id.rvImage);
        Intrinsics.checkNotNullExpressionValue(rvImage, "rvImage");
        CommonExtKt.disableRefreshAnimation(rvImage);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvImage);
        CommBaseAdapter<ReviewImage> commBaseAdapter3 = this.sendImageAdapter;
        if (commBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sendImageAdapter");
        } else {
            commBaseAdapter2 = commBaseAdapter3;
        }
        recyclerView.setAdapter(commBaseAdapter2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    public ReplyPresenter bindPresenter() {
        return new ReplyPresenter(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        KeyboardUtils.unregisterSoftInputChangedListener(getWindow());
        super.onStop();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
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

    /* compiled from: ReplyActivity.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J4\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\b2\u0006\u0010\f\u001a\u00020\r¨\u0006\u000e"}, d2 = {"Lcom/movieboxpro/android/view/activity/ReplyActivity$Companion;", "", "()V", TtmlNode.START, "", "context", "Landroid/content/Context;", "id", "", "boxType", "", "commentId", "reviewModel", "Lcom/movieboxpro/android/model/ReviewItem;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context, String str, int i, String str2, ReviewItem reviewModel) {
            Intrinsics.checkNotNullParameter(reviewModel, "reviewModel");
            int i2 = 0;
            Pair[] pairArr = {TuplesKt.to("id", str), TuplesKt.to("boxType", Integer.valueOf(i)), TuplesKt.to("commentId", str2), TuplesKt.to("data", reviewModel)};
            Intent intent = new Intent(context, ReplyActivity.class);
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
    }

    /* compiled from: ReplyActivity.kt */
    @Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 .2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001.B\u0005¢\u0006\u0002\u0010\u0004J\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0014J\u001c\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00150\u0014H\u0014J\u001a\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002J\u0012\u0010\u0018\u001a\u00020\u00122\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\u0016\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00020\r2\u0006\u0010\u001c\u001a\u00020\u0003H\u0014J\u000e\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\b0\u001eH\u0014J\u0018\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\u00152\u0006\u0010!\u001a\u00020\u0002H\u0014J\b\u0010\"\u001a\u00020\u0006H\u0014J\b\u0010#\u001a\u00020$H\u0014J\b\u0010%\u001a\u00020&H\u0014J\u000e\u0010'\u001a\u00020\u00122\u0006\u0010\n\u001a\u00020\u000bJ2\u0010(\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00062\b\u0010)\u001a\u0004\u0018\u00010\u000e2\u000e\u0010*\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010+2\u0006\u0010,\u001a\u00020-H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/movieboxpro/android/view/activity/ReplyActivity$ReplyListFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/ReviewItem;", "Lcom/movieboxpro/android/model/ReviewResponse;", "()V", "boxType", "", "commentId", "", "id", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/listener/ReviewListener;", "addHeaderView", "", "Landroid/view/View;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "addOnItemClickViews", "", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "deleteComment", "position", "getBundle", "arguments", "Landroid/os/Bundle;", "getData", "model", "getServiceData", "Lio/reactivex/Observable;", "initHolder", "helper", "item", "initItemLayout", "onItemChildClick", "Lcom/chad/library/adapter/base/listener/OnItemChildClickListener;", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "setListener", "toImageShow", "view", "images", "", "imageView", "Landroid/widget/ImageView;", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class ReplyListFragment extends BaseListFragment<ReviewItem, ReviewResponse> {
        public static final Companion Companion = new Companion(null);
        public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
        private int boxType = 1;
        private String commentId;
        private String id;
        private ReviewListener listener;

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
            return R.layout.adapter_reviews_detail_item;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment, com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
        public /* synthetic */ void onDestroyView() {
            super.onDestroyView();
            _$_clearFindViewByIdCache();
        }

        /* compiled from: ReplyActivity.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J*\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u00062\u0006\u0010\n\u001a\u00020\u000b¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/view/activity/ReplyActivity$ReplyListFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/activity/ReplyActivity$ReplyListFragment;", "id", "", "boxType", "", "commentId", "reviewModel", "Lcom/movieboxpro/android/model/ReviewItem;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        /* loaded from: classes3.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final ReplyListFragment newInstance(String str, int i, String str2, ReviewItem reviewModel) {
                Intrinsics.checkNotNullParameter(reviewModel, "reviewModel");
                ReplyListFragment replyListFragment = new ReplyListFragment();
                replyListFragment.setArguments(CommonExtKt.bundleOf(TuplesKt.to("id", str), TuplesKt.to("boxType", Integer.valueOf(i)), TuplesKt.to("commentId", str2), TuplesKt.to("data", reviewModel)));
                return replyListFragment;
            }
        }

        public final void setListener(ReviewListener listener) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            this.listener = listener;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected Observable<String> getServiceData() {
            return HttpRequest.Companion.post(this, ApiUtils.INSTANCE.getCommentById(this.boxType)).param("box_type", Integer.valueOf(this.boxType)).param("mid", this.id).param("pid", this.id).param("actor_id", this.id).param("comment_id", this.commentId).param(IjkMediaMeta.IJKM_KEY_TYPE, (Object) 2).param("page", Integer.valueOf(this.mCurrentPage)).param("pagelimit", Integer.valueOf(this.mPageSize)).asRequest();
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void getBundle(Bundle bundle) {
            this.mClass = ReviewItem.class;
            this.mPageClass = ReviewResponse.class;
            this.id = bundle == null ? null : bundle.getString("id");
            this.boxType = bundle != null ? bundle.getInt("boxType", 1) : 1;
            this.commentId = bundle != null ? bundle.getString("commentId") : null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public List<ReviewItem> getData(ReviewResponse model) {
            Intrinsics.checkNotNullParameter(model, "model");
            if (this.mCurrentPage == 1) {
                List<ReviewItem> list = model.getList();
                if (list == null) {
                    list = new ArrayList<>();
                }
                ArrayList arrayList = new ArrayList(list);
                Bundle arguments = getArguments();
                ReviewItem reviewItem = arguments == null ? null : (ReviewItem) arguments.getParcelable("data");
                Intrinsics.checkNotNull(reviewItem);
                arrayList.add(0, reviewItem);
                return arrayList;
            }
            List<ReviewItem> list2 = model.getList();
            if (list2 == null) {
                list2 = new ArrayList<>();
            }
            return new ArrayList(list2);
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected OnItemClickListener onItemClick() {
            return new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyActivity$ReplyListFragment$3FpawiYcwGhDDufBhpnV52T6Oko
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    ReplyActivity.ReplyListFragment.m225onItemClick$lambda0(ReplyActivity.ReplyListFragment.this, baseQuickAdapter, view, i);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onItemClick$lambda-0  reason: not valid java name */
        public static final void m225onItemClick$lambda0(ReplyListFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
            Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
            if (i == 0) {
                return;
            }
            ReviewItem reviewItem = (ReviewItem) this$0.mAdapter.getItem(i);
            ReplyDetailActivity.Companion.start(this$0.getContext(), this$0.id, this$0.boxType, reviewItem.getComment_id(), reviewItem);
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected OnItemChildClickListener onItemChildClick() {
            return new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyActivity$ReplyListFragment$6RlyM5EhCbCMHSjRx-4w8fr4W8g
                @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
                public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    ReplyActivity.ReplyListFragment.m223onItemChildClick$lambda2(ReplyActivity.ReplyListFragment.this, baseQuickAdapter, view, i);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onItemChildClick$lambda-2  reason: not valid java name */
        public static final void m223onItemChildClick$lambda2(final ReplyListFragment this$0, BaseQuickAdapter noName_0, View view, final int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
            Intrinsics.checkNotNullParameter(view, "view");
            final ReviewItem reviewItem = (ReviewItem) this$0.mAdapter.getItem(i);
            switch (view.getId()) {
                case R.id.avatarView /* 2131296424 */:
                    ReviewListener reviewListener = this$0.listener;
                    if (reviewListener == null) {
                        return;
                    }
                    reviewListener.at(reviewItem.getComment_id(), reviewItem.getUsername());
                    return;
                case R.id.ivAction /* 2131296911 */:
                    new XPopup.Builder(this$0.getContext()).atView(view).asAttachList(new String[]{"Delete"}, null, new OnSelectListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyActivity$ReplyListFragment$kUEhrybYyK20P0N3HNB7CxbR9JA
                        @Override // com.lxj.xpopup.interfaces.OnSelectListener
                        public final void onSelect(int i2, String str) {
                            ReplyActivity.ReplyListFragment.m224onItemChildClick$lambda2$lambda1(ReplyActivity.ReplyListFragment.this, i, reviewItem, i2, str);
                        }
                    }).show();
                    return;
                case R.id.llLike /* 2131297190 */:
                    Integer support_status = reviewItem.getSupport_status();
                    if (support_status != null && support_status.intValue() == 0) {
                        reviewItem.setSupport_status(1);
                        Integer support = reviewItem.getSupport();
                        reviewItem.setSupport(support != null ? Integer.valueOf(support.intValue() + 1) : null);
                        this$0.mAdapter.notifyItemChanged(i);
                        ReviewListener reviewListener2 = this$0.listener;
                        if (reviewListener2 == null) {
                            return;
                        }
                        reviewListener2.likeReview(reviewItem.getComment_id(), 1);
                        return;
                    }
                    Integer support_status2 = reviewItem.getSupport_status();
                    if (support_status2 != null && support_status2.intValue() == 1) {
                        reviewItem.setSupport_status(0);
                        Integer support2 = reviewItem.getSupport();
                        reviewItem.setSupport(support2 != null ? Integer.valueOf(support2.intValue() - 1) : null);
                        this$0.mAdapter.notifyItemChanged(i);
                        ReviewListener reviewListener3 = this$0.listener;
                        if (reviewListener3 == null) {
                            return;
                        }
                        reviewListener3.likeReview(reviewItem.getComment_id(), 0);
                        return;
                    }
                    return;
                case R.id.llReply /* 2131297211 */:
                    ReviewListener reviewListener4 = this$0.listener;
                    if (reviewListener4 == null) {
                        return;
                    }
                    reviewListener4.onReply(reviewItem.getComment_id(), reviewItem.getUsername());
                    return;
                default:
                    return;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onItemChildClick$lambda-2$lambda-1  reason: not valid java name */
        public static final void m224onItemChildClick$lambda2$lambda1(ReplyListFragment this$0, int i, ReviewItem reviewItem, int i2, String str) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.deleteComment(i, reviewItem.getComment_id());
        }

        private final void deleteComment(int i, String str) {
            ReplyListFragment replyListFragment = this;
            RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.Companion.post(replyListFragment, ApiUtils.INSTANCE.deleteComment(this.boxType)).param("comment_id", str).asRequest(), replyListFragment), ReplyActivity$ReplyListFragment$deleteComment$1.INSTANCE, null, null, null, new ReplyActivity$ReplyListFragment$deleteComment$2(this, i), 14, null);
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected List<View> addHeaderView(RecyclerView recyclerView) {
            Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
            Bundle arguments = getArguments();
            ReviewItem reviewItem = arguments == null ? null : (ReviewItem) arguments.getParcelable("data");
            Intrinsics.checkNotNull(reviewItem);
            Intrinsics.checkNotNullExpressionValue(reviewItem, "arguments?.getParcelable<ReviewItem>(\"data\")!!");
            LayoutInflater from = LayoutInflater.from(getContext());
            ViewParent parent = recyclerView.getParent();
            if (parent == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup");
            }
            final View inflate = from.inflate(R.layout.reply_top_review, (ViewGroup) parent, false);
            ((TextView) inflate.findViewById(R.id.tvUsername)).setText(reviewItem.getUsername());
            ((TextView) inflate.findViewById(R.id.tvTime)).setText(TimeUtils.formatTime(reviewItem.getDateline() * 1000));
            TextView textView = (TextView) inflate.findViewById(R.id.tvContent);
            ((UserAvatarView) inflate.findViewById(R.id.avatarView)).setAvatar(reviewItem.getAvatar(), reviewItem.getUsername());
            RecyclerView rvImage = (RecyclerView) inflate.findViewById(R.id.recyclerView);
            List<ImageItem> img_list = reviewItem.getImg_list();
            if (img_list == null || img_list.isEmpty()) {
                Intrinsics.checkNotNullExpressionValue(rvImage, "rvImage");
                CommonExtKt.gone(rvImage);
            } else {
                Intrinsics.checkNotNullExpressionValue(rvImage, "rvImage");
                CommonExtKt.visible(rvImage);
                rvImage.addItemDecoration(new GridSpacingItemDecoration(4, CommonExtKt.dp2Px(10), false));
                rvImage.setLayoutManager(new GridLayoutManager(getContext(), 4));
                CommBaseAdapter commBaseAdapter = new CommBaseAdapter(R.layout.adapter_review_image_item, new ReplyActivity$ReplyListFragment$addHeaderView$imgAdapter$1(this), reviewItem.getImg_list());
                rvImage.setAdapter(commBaseAdapter);
                final ArrayList arrayList = new ArrayList();
                for (ImageItem imageItem : reviewItem.getImg_list()) {
                    String url = imageItem.getUrl();
                    if (url == null) {
                        url = "";
                    }
                    arrayList.add(url);
                }
                commBaseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyActivity$ReplyListFragment$d9C4Oa--sVUzh_uphAzDEKXIX5A
                    @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                    public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                        ReplyActivity.ReplyListFragment.m218addHeaderView$lambda4(inflate, this, arrayList, baseQuickAdapter, view, i);
                    }
                });
            }
            return new ArrayList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: addHeaderView$lambda-4  reason: not valid java name */
        public static final void m218addHeaderView$lambda4(View view, ReplyListFragment this$0, ArrayList images, BaseQuickAdapter noName_0, View noName_1, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(images, "$images");
            Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
            Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            Intrinsics.checkNotNullExpressionValue(imageView, "imageView");
            this$0.toImageShow(i, imageView, images, imageView);
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void addOnItemClickViews(BaseQuickAdapter<ReviewItem, BaseViewHolder> adapter) {
            Intrinsics.checkNotNullParameter(adapter, "adapter");
            adapter.addChildClickViewIds(R.id.llReply, R.id.llLike, R.id.avatarView, R.id.ivAction);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public void initHolder(BaseViewHolder helper, ReviewItem item) {
            Integer is_delete;
            Integer is_delete2;
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            ConstraintLayout constraintLayout = (ConstraintLayout) helper.getView(R.id.container);
            if (helper.getAdapterPosition() == 0) {
                constraintLayout.setBackgroundColor(CommonExtKt.colorInt(this, (int) R.color.color_main));
            } else {
                constraintLayout.setBackgroundColor(CommonExtKt.colorInt(this, (int) R.color.color_main_back));
            }
            ImageView imageView = (ImageView) helper.getView(R.id.ivAction);
            if (Intrinsics.areEqual(item.getUid(), App.getUserData().uid_v2) && (is_delete2 = item.is_delete()) != null && is_delete2.intValue() == 0) {
                CommonExtKt.visible(imageView);
            } else {
                CommonExtKt.gone(imageView);
            }
            QMUISpanTouchFixTextView qMUISpanTouchFixTextView = (QMUISpanTouchFixTextView) helper.getView(R.id.tvContent);
            qMUISpanTouchFixTextView.setMovementMethodDefault();
            qMUISpanTouchFixTextView.setNeedForceEventToParent(true);
            QMUISpanTouchFixTextView qMUISpanTouchFixTextView2 = qMUISpanTouchFixTextView;
            SpanUtils span = SpanUtils.with(qMUISpanTouchFixTextView2);
            Integer is_delete3 = item.is_delete();
            int i = R.color.white30_transparent;
            if (is_delete3 != null && is_delete3.intValue() == 0) {
                List<Comment> comment = item.getComment();
                if (comment != null) {
                    for (Comment comment2 : comment) {
                        String uid = comment2.getUid();
                        if (uid == null || StringsKt.isBlank(uid)) {
                            Intrinsics.checkNotNullExpressionValue(span, "span");
                            CommonExtKt.addText(span, String.valueOf(comment2.getText()), 16, i);
                        } else {
                            String stringPlus = Intrinsics.stringPlus("@", comment2.getUsername());
                            if (stringPlus == null) {
                                stringPlus = "";
                            }
                            SpanUtils append = span.append(stringPlus);
                            final int colorInt = CommonExtKt.colorInt(this, (int) R.color.color_main_blue);
                            final int colorInt2 = CommonExtKt.colorInt(this, (int) R.color.color_main_blue);
                            final int colorInt3 = CommonExtKt.colorInt(this, (int) R.color.transparent);
                            final int colorInt4 = CommonExtKt.colorInt(this, (int) R.color.transparent);
                            append.setClickSpan(new QMUITouchableSpan(colorInt, colorInt2, colorInt3, colorInt4) { // from class: com.movieboxpro.android.view.activity.ReplyActivity$ReplyListFragment$initHolder$1$1
                                @Override // com.movieboxpro.android.view.widget.textview.QMUITouchableSpan
                                public void onSpanClick(View view) {
                                    ToastUtils.showShort("点击", new Object[0]);
                                }
                            }).setForegroundColor(CommonExtKt.colorInt(this, (int) R.color.color_main_blue)).setFontSize(16, true);
                        }
                        i = R.color.white30_transparent;
                    }
                }
                span.create();
            } else {
                SpanUtils with = SpanUtils.with(qMUISpanTouchFixTextView2);
                Intrinsics.checkNotNullExpressionValue(with, "with(content)");
                CommonExtKt.addText(with, "This review is deleted by user.", 16, R.color.white30_transparent).setItalic().create();
            }
            TextView textView = (TextView) helper.getView(R.id.tvReplyStack);
            Integer is_delete4 = item.is_delete();
            if (is_delete4 != null && is_delete4.intValue() == 0) {
                TextView textView2 = textView;
                CommonExtKt.visible(textView2);
                List<AtItem> at = item.getAt();
                if (at == null || at.isEmpty()) {
                    CommonExtKt.gone(textView2);
                } else {
                    CommonExtKt.visible(textView2);
                    SpanUtils with2 = SpanUtils.with(textView);
                    Intrinsics.checkNotNullExpressionValue(with2, "with(reply)");
                    SpanUtils addText = CommonExtKt.addText(with2, "Reply ", 16, R.color.white30_transparent);
                    for (AtItem atItem : item.getAt()) {
                        CommonExtKt.addText(addText, Intrinsics.stringPlus("@", atItem.getUsername()), 16, R.color.color_main_blue).append(" ");
                    }
                    addText.create();
                }
            } else {
                CommonExtKt.gone(textView);
            }
            LinearLayout linearLayout = (LinearLayout) helper.getView(R.id.llLike);
            Integer support_status = item.getSupport_status();
            linearLayout.setSelected(support_status != null && support_status.intValue() == 1);
            helper.setText(R.id.tvLikeNum, String.valueOf(item.getSupport()));
            helper.setText(R.id.tvReplyNum, String.valueOf(item.getReply()));
            helper.setText(R.id.tvUsername, item.getUsername());
            helper.setText(R.id.tvTime, TimeUtils.formatTime(item.getDateline() * 1000));
            ((UserAvatarView) helper.getView(R.id.avatarView)).setAvatar(item.getAvatar(), item.getUsername());
            RecyclerView recyclerView = (RecyclerView) helper.getView(R.id.recyclerView);
            recyclerView.setNestedScrollingEnabled(false);
            List<ImageItem> img_list = item.getImg_list();
            if ((img_list == null || img_list.isEmpty()) || ((is_delete = item.is_delete()) != null && is_delete.intValue() == 1)) {
                CommonExtKt.gone(recyclerView);
                return;
            }
            CommonExtKt.visible(recyclerView);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
            if (recyclerView.getTag() == null) {
                recyclerView.addItemDecoration(new GridSpacingItemDecoration(4, CommonExtKt.dp2Px(10), false));
                recyclerView.setTag("added");
            }
            CommBaseAdapter commBaseAdapter = new CommBaseAdapter(R.layout.adapter_review_image_item, new ReplyActivity$ReplyListFragment$initHolder$imgAdapter$1(this), item.getImg_list());
            recyclerView.setAdapter(commBaseAdapter);
            final ArrayList arrayList = new ArrayList();
            for (ImageItem imageItem : item.getImg_list()) {
                String url = imageItem.getUrl();
                if (url == null) {
                    url = "";
                }
                arrayList.add(url);
            }
            commBaseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyActivity$ReplyListFragment$NZgXi-v6dtHuUjTShUc0K9nkohI
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i2) {
                    ReplyActivity.ReplyListFragment.m219initHolder$lambda8(ReplyActivity.ReplyListFragment.this, arrayList, baseQuickAdapter, view, i2);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: initHolder$lambda-8  reason: not valid java name */
        public static final void m219initHolder$lambda8(ReplyListFragment this$0, ArrayList images, BaseQuickAdapter noName_0, View view, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(images, "$images");
            Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
            Intrinsics.checkNotNullParameter(view, "view");
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            Intrinsics.checkNotNullExpressionValue(imageView, "imageView");
            this$0.toImageShow(i, imageView, images, imageView);
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
                builder.asImageViewer((ImageView) view, i, list, new OnSrcViewUpdateListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ReplyActivity$ReplyListFragment$8FexgAFJkDXF9CYjY5kHAJh7r_Q
                    @Override // com.lxj.xpopup.interfaces.OnSrcViewUpdateListener
                    public final void onSrcViewUpdate(ImageViewerPopupView imageViewerPopupView, int i2) {
                        ReplyActivity.ReplyListFragment.m226toImageShow$lambda10$lambda9(imageView, imageViewerPopupView, i2);
                    }
                }, new XpopImageLoader()).show();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: toImageShow$lambda-10$lambda-9  reason: not valid java name */
        public static final void m226toImageShow$lambda10$lambda9(ImageView imageView, ImageViewerPopupView popupView, int i) {
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
    }
}
