package com.movieboxpro.android.view.activity.review;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.ServerException;
import com.movieboxpro.android.model.ReviewModel;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.review.ReviewDetailActivity;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
/* compiled from: UserCommentFragment.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001e2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u001eB\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0006\u001a\u00020\u0007H\u0014J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0014J\u0018\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u000e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011H\u0014J\u0018\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0002H\u0014J\b\u0010\u0016\u001a\u00020\u000fH\u0014J\u0012\u0010\u0017\u001a\u00020\u000f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0002H\u0014J\b\u0010\u0019\u001a\u00020\u001aH\u0014J\u0016\u0010\u001b\u001a\u00020\t2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00020\u001dH\u0014R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/UserCommentFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/ReviewModel;", "", "()V", "uid", "enableMultiAdapter", "", "getBundle", "", "arguments", "Landroid/os/Bundle;", "getCommentPage", "model", "mPageSize", "", "getServiceData", "Lio/reactivex/Observable;", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "initItemLayout", "initItemType", "t", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "registerItemType", "multiTypeDelegate", "Lcom/chad/library/adapter/base/delegate/BaseMultiTypeDelegate;", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UserCommentFragment extends BaseListFragment<ReviewModel, String> {
    public static final Companion Companion = new Companion(null);
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String uid = "";

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

    @Override // com.movieboxpro.android.base.BaseListFragment, com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    /* compiled from: UserCommentFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/UserCommentFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/activity/review/UserCommentFragment;", "uid", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final UserCommentFragment newInstance(String uid) {
            Intrinsics.checkNotNullParameter(uid, "uid");
            UserCommentFragment userCommentFragment = new UserCommentFragment();
            Bundle bundle = new Bundle();
            bundle.putString("uid", uid);
            userCommentFragment.setArguments(bundle);
            return userCommentFragment;
        }
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected Observable<String> getServiceData() {
        return Http.getService().getAllReviewByUid(API.BBS_URL, "getAllThreadByUid", this.uid, TtmlNode.COMBINE_ALL, this.mCurrentPage, this.mPageSize);
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected OnItemClickListener onItemClick() {
        return new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$UserCommentFragment$bezjxUj1EJ7uRjkV8UM18ElgLwA
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                UserCommentFragment.m764onItemClick$lambda1(UserCommentFragment.this, baseQuickAdapter, view, i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onItemClick$lambda-1  reason: not valid java name */
    public static final void m764onItemClick$lambda1(UserCommentFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        ReviewModel reviewModel = (ReviewModel) this$0.mAdapter.getItem(i);
        if (reviewModel == null) {
            return;
        }
        int first = reviewModel.getFirst();
        if (first == 0) {
            this$0.getCommentPage(reviewModel, this$0.mPageSize);
        } else if (first != 1) {
        } else {
            ReviewDetailActivity.Companion companion = ReviewDetailActivity.Companion;
            Context context = this$0.getContext();
            String subject = reviewModel.getSubject();
            Intrinsics.checkNotNullExpressionValue(subject, "it.subject");
            companion.start(context, subject, reviewModel.getTid(), reviewModel, false);
        }
    }

    private final void getCommentPage(final ReviewModel reviewModel, int i) {
        Observable<R> compose = Http.getService().getCommentPage(API.BBS_URL, "get_comment_page", reviewModel.getPid(), reviewModel.getTid(), i).compose(RxUtils.rxTranslate2Bean(HashMap.class));
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
        RxSubscribersKt.transform(compose, this).subscribe(new Consumer() { // from class: com.movieboxpro.android.view.activity.review.UserCommentFragment$getCommentPage$$inlined$subscribeToBean$default$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(T it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                HashMap hashMap = (HashMap) it;
                UserCommentFragment.this.hideLoadingView();
                ReviewDetailActivity.Companion companion = ReviewDetailActivity.Companion;
                Context context = UserCommentFragment.this.getContext();
                String subject = reviewModel.getSubject();
                String tid = reviewModel.getTid();
                ReviewModel reviewModel2 = reviewModel;
                String str = (String) hashMap.get("page");
                int parseInt = str == null ? 0 : Integer.parseInt(str);
                String str2 = (String) hashMap.get("position");
                companion.start(context, subject, tid, reviewModel2, parseInt, str2 == null ? 0 : Integer.parseInt(str2));
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.UserCommentFragment$getCommentPage$$inlined$subscribeToBean$default$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                ApiException handleException = ApiException.handleException(th);
                Intrinsics.checkNotNullExpressionValue(handleException, "handleException(it)");
                UserCommentFragment.this.hideLoadingView();
                ToastUtils.showShort(handleException.getMessage(), new Object[0]);
                if (th instanceof ServerException) {
                    ServerException serverException = (ServerException) th;
                }
            }
        }, new Action() { // from class: com.movieboxpro.android.view.activity.review.UserCommentFragment$getCommentPage$$inlined$subscribeToBean$default$3
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.UserCommentFragment$getCommentPage$$inlined$subscribeToBean$default$4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                UserCommentFragment.this.showLoadingView();
            }
        });
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void registerItemType(BaseMultiTypeDelegate<ReviewModel> multiTypeDelegate) {
        Intrinsics.checkNotNullParameter(multiTypeDelegate, "multiTypeDelegate");
        multiTypeDelegate.addItemType(0, R.layout.adapter_user_comment_item);
        multiTypeDelegate.addItemType(1, R.layout.adapter_user_post_item);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseListFragment
    public int initItemType(ReviewModel reviewModel) {
        if (reviewModel == null) {
            return 1;
        }
        return reviewModel.getFirst();
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void getBundle(Bundle bundle) {
        String string;
        this.mClass = ReviewModel.class;
        String str = "";
        if (bundle != null && (string = bundle.getString("uid")) != null) {
            str = string;
        }
        this.uid = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseListFragment
    public void initHolder(BaseViewHolder helper, ReviewModel item) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        TextView textView = (TextView) helper.getView(R.id.tvContent);
        TextView textView2 = (TextView) helper.getView(R.id.tvTime);
        String content = item.getContent();
        if (content == null || StringsKt.isBlank(content)) {
            textView.setText("(photo)");
        } else {
            textView.setText(item.getContent());
        }
        int first = item.getFirst();
        if (first == 0) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format("%s Reply", Arrays.copyOf(new Object[]{TimeUtils.INSTANCE.formatReviewTime(item.getAdd_time() * 1000)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            textView2.setText(format);
        } else if (first != 1) {
        } else {
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            String format2 = String.format("%s Post", Arrays.copyOf(new Object[]{TimeUtils.INSTANCE.formatReviewTime(item.getAdd_time() * 1000)}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
            textView2.setText(format2);
            GlideUtils.loadWithCorner(getContext(), item.getPoster(), (ImageView) helper.getView(R.id.ivPoster), 2);
            ((TextView) helper.getView(R.id.tvTitle)).setText(item.getSubject());
        }
    }
}
