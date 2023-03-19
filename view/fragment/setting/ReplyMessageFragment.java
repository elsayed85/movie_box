package com.movieboxpro.android.view.fragment.setting;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.event.ChangeDotNumEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.APIService;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.ServerException;
import com.movieboxpro.android.model.ReviewModel;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.review.PandaForumAuthorizeActivity;
import com.movieboxpro.android.view.activity.review.ReviewDetailActivity;
import com.movieboxpro.android.view.activity.review.ReviewListFragment;
import com.movieboxpro.android.view.activity.review.UserInfoActivity;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;
/* compiled from: ReplyMessageFragment.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 )2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001)B\u0005¢\u0006\u0002\u0010\u0004J\u001c\u0010\u000b\u001a\u00020\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000f0\u000eH\u0014J\u0018\u0010\u0010\u001a\u00020\f2\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0012H\u0014J\u0012\u0010\u0013\u001a\u00020\f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\u0018\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u000e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00030\u001bH\u0014J\b\u0010\u001c\u001a\u00020\fH\u0002J\u001a\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\u000f2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0002H\u0014J\b\u0010 \u001a\u00020\u0019H\u0014J\b\u0010!\u001a\u00020\"H\u0014J\b\u0010#\u001a\u00020\"H\u0014J\b\u0010$\u001a\u00020%H\u0014J\b\u0010&\u001a\u00020'H\u0014J\b\u0010(\u001a\u00020\fH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/movieboxpro/android/view/fragment/setting/ReplyMessageFragment;", "Lcom/movieboxpro/android/view/activity/review/ReviewListFragment;", "Lcom/movieboxpro/android/model/ReviewModel;", "", "()V", "bbsInfo", "Lcom/movieboxpro/android/model/user/UserModel$BBsInfo;", "kProgressHUD", "Lcom/kaopiz/kprogresshud/KProgressHUD;", "userInfo", "Lcom/movieboxpro/android/model/user/UserModel$UserData;", "addOnItemClickViews", "", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "doSomethingWithData", "list", "", "getBundle", "arguments", "Landroid/os/Bundle;", "getCommentPage", "model", "mPageSize", "", "getServiceData", "Lio/reactivex/Observable;", "hideLoadingView", "initHolder", "helper", "item", "initItemLayout", "isNeedTest", "", "isOpenUpFetch", "onItemChildClick", "Lcom/chad/library/adapter/base/listener/OnItemChildClickListener;", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "showLoadingView", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ReplyMessageFragment extends ReviewListFragment<ReviewModel, String> {
    public static final Companion Companion = new Companion(null);
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private UserModel.BBsInfo bbsInfo;
    private KProgressHUD kProgressHUD;
    private UserModel.UserData userInfo;

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
    protected int initItemLayout() {
        return R.layout.adapter_reply_message_item;
    }

    @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
    protected boolean isNeedTest() {
        return false;
    }

    @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
    protected boolean isOpenUpFetch() {
        return false;
    }

    @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment, com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
    protected Observable<String> getServiceData() {
        APIService service = Http.getService();
        String str = API.BBS_URL;
        UserModel.BBsInfo bBsInfo = this.bbsInfo;
        UserModel.BBsInfo bBsInfo2 = null;
        if (bBsInfo == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bbsInfo");
            bBsInfo = null;
        }
        String bbs_uid = bBsInfo.getBbs_uid();
        UserModel.BBsInfo bBsInfo3 = this.bbsInfo;
        if (bBsInfo3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bbsInfo");
            bBsInfo3 = null;
        }
        String author = bBsInfo3.getAuthor();
        UserModel.BBsInfo bBsInfo4 = this.bbsInfo;
        if (bBsInfo4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bbsInfo");
            bBsInfo4 = null;
        }
        String auth = bBsInfo4.getAuth();
        UserModel.BBsInfo bBsInfo5 = this.bbsInfo;
        if (bBsInfo5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bbsInfo");
            bBsInfo5 = null;
        }
        String authkey = bBsInfo5.getAuthkey();
        UserModel.BBsInfo bBsInfo6 = this.bbsInfo;
        if (bBsInfo6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bbsInfo");
        } else {
            bBsInfo2 = bBsInfo6;
        }
        return service.getNotice(str, "mynotice", bbs_uid, author, auth, authkey, bBsInfo2.getFormhash(), CommonUtils.getBBSApiAPPID(), "post", "quote", this.mCurrentPage, this.mPageSize);
    }

    @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
    protected OnItemClickListener onItemClick() {
        return new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.setting.-$$Lambda$ReplyMessageFragment$nHQQVWJOJ7ZAAKi6vrs-YkUl4vM
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ReplyMessageFragment.m1270onItemClick$lambda1(ReplyMessageFragment.this, baseQuickAdapter, view, i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onItemClick$lambda-1  reason: not valid java name */
    public static final void m1270onItemClick$lambda1(ReplyMessageFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        ReviewModel reviewModel = (ReviewModel) this$0.mAdapter.getItem(i);
        if (reviewModel == null) {
            return;
        }
        this$0.getCommentPage(reviewModel, this$0.mPageSize);
    }

    @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
    protected OnItemChildClickListener onItemChildClick() {
        return new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.fragment.setting.-$$Lambda$ReplyMessageFragment$Cp9E84f-PZExT-LQM5hD7eplW8w
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ReplyMessageFragment.m1269onItemChildClick$lambda3(ReplyMessageFragment.this, baseQuickAdapter, view, i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onItemChildClick$lambda-3  reason: not valid java name */
    public static final void m1269onItemChildClick$lambda3(ReplyMessageFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        ReviewModel reviewModel = (ReviewModel) this$0.mAdapter.getItem(i);
        if (reviewModel == null) {
            return;
        }
        if (App.getBBsInfo() != null) {
            UserInfoActivity.Companion companion = UserInfoActivity.Companion;
            Context context = this$0.getContext();
            String authorid = reviewModel.getAuthorid();
            Intrinsics.checkNotNullExpressionValue(authorid, "it.authorid");
            companion.start(context, authorid);
            return;
        }
        PandaForumAuthorizeActivity.Companion.start(this$0.getContext());
    }

    private final void getCommentPage(final ReviewModel reviewModel, int i) {
        Observable<R> compose = Http.getService().getCommentPage(API.BBS_URL, "get_comment_page", reviewModel.getPid(), reviewModel.getTid(), i).compose(RxUtils.rxTranslate2Bean(HashMap.class));
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
        RxSubscribersKt.transform(compose, this).subscribe(new Consumer() { // from class: com.movieboxpro.android.view.fragment.setting.ReplyMessageFragment$getCommentPage$$inlined$subscribeToBean$default$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(T it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                HashMap hashMap = (HashMap) it;
                ReplyMessageFragment.this.hideLoadingView();
                ReviewDetailActivity.Companion companion = ReviewDetailActivity.Companion;
                Context context = ReplyMessageFragment.this.getContext();
                String subject = reviewModel.getSubject();
                String tid = reviewModel.getTid();
                ReviewModel reviewModel2 = reviewModel;
                String str = (String) hashMap.get("page");
                int parseInt = str == null ? 0 : Integer.parseInt(str);
                String str2 = (String) hashMap.get("position");
                companion.start(context, subject, tid, reviewModel2, parseInt, str2 == null ? 0 : Integer.parseInt(str2));
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.fragment.setting.ReplyMessageFragment$getCommentPage$$inlined$subscribeToBean$default$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                ApiException handleException = ApiException.handleException(th);
                Intrinsics.checkNotNullExpressionValue(handleException, "handleException(it)");
                ReplyMessageFragment.this.hideLoadingView();
                ToastUtils.showShort(handleException.getMessage(), new Object[0]);
                if (th instanceof ServerException) {
                    ServerException serverException = (ServerException) th;
                }
            }
        }, new Action() { // from class: com.movieboxpro.android.view.fragment.setting.ReplyMessageFragment$getCommentPage$$inlined$subscribeToBean$default$3
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.fragment.setting.ReplyMessageFragment$getCommentPage$$inlined$subscribeToBean$default$4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                ReplyMessageFragment.this.showLoadingView();
            }
        });
    }

    @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
    protected void doSomethingWithData(List<ReviewModel> list) {
        if (this.mCurrentPage == 1) {
            EventBus.getDefault().post(new ChangeDotNumEvent(0, 0));
        }
    }

    @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
    protected void getBundle(Bundle bundle) {
        this.mClass = ReviewModel.class;
        UserModel.UserData userData = App.getUserData();
        Intrinsics.checkNotNullExpressionValue(userData, "getUserData()");
        this.userInfo = userData;
        UserModel.BBsInfo bBsInfo = App.getBBsInfo();
        Intrinsics.checkNotNullExpressionValue(bBsInfo, "getBBsInfo()");
        this.bbsInfo = bBsInfo;
    }

    @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
    protected void addOnItemClickViews(BaseQuickAdapter<ReviewModel, BaseViewHolder> adapter) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        adapter.addChildClickViewIds(R.id.ivAvatar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
    public void initHolder(BaseViewHolder helper, ReviewModel reviewModel) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        if (reviewModel == null) {
            return;
        }
        helper.setText(R.id.tvName, reviewModel.getAuthor());
        String dateline = reviewModel.getDateline();
        Intrinsics.checkNotNullExpressionValue(dateline, "it.dateline");
        helper.setText(R.id.tvTime, Intrinsics.stringPlus("· ", TimeUtils.formatTime(Long.parseLong(dateline) * 1000)));
        GlideUtils.load(getContext(), reviewModel.getAvatar(), (ImageView) helper.getView(R.id.ivAvatar), (int) R.mipmap.ic_panda_forum_default_avatar);
        String content = reviewModel.getContent();
        if (content == null || StringsKt.isBlank(content)) {
            helper.setText(R.id.tvNote, "(photo)");
        } else {
            helper.setText(R.id.tvNote, reviewModel.getContent());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideLoadingView() {
        if (this.kProgressHUD == null && getContext() != null) {
            this.kProgressHUD = KProgressHUD.create(getContext()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(1).setDimAmount(0.0f);
        }
        KProgressHUD kProgressHUD = this.kProgressHUD;
        if (kProgressHUD == null) {
            return;
        }
        kProgressHUD.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showLoadingView() {
        if (this.kProgressHUD == null && getContext() != null) {
            this.kProgressHUD = KProgressHUD.create(getContext()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(1).setDimAmount(0.0f);
        }
        KProgressHUD kProgressHUD = this.kProgressHUD;
        if (kProgressHUD == null) {
            return;
        }
        kProgressHUD.show();
    }

    /* compiled from: ReplyMessageFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/movieboxpro/android/view/fragment/setting/ReplyMessageFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/fragment/setting/ReplyMessageFragment;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ReplyMessageFragment newInstance() {
            ReplyMessageFragment replyMessageFragment = new ReplyMessageFragment();
            replyMessageFragment.setArguments(new Bundle());
            return replyMessageFragment;
        }
    }
}
