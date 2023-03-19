package com.movieboxpro.android.view.fragment.setting;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.event.ChangeDotNumEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.APIService;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.ReviewModel;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.view.activity.movielist.MovieListDetailActivity;
import com.movieboxpro.android.view.activity.review.PandaForumAuthorizeActivity;
import com.movieboxpro.android.view.activity.review.ReviewListFragment;
import com.movieboxpro.android.view.activity.review.UserInfoActivity;
import io.reactivex.Observable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;
/* compiled from: CollectNoticeFragment.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \"2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\"B\u0005¢\u0006\u0002\u0010\u0004J\u001c\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\r0\fH\u0014J\u0018\u0010\u000e\u001a\u00020\n2\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0010H\u0014J\u0012\u0010\u0011\u001a\u00020\n2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\u000e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\u0015H\u0014J\u001a\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\r2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0002H\u0014J\b\u0010\u0019\u001a\u00020\u001aH\u0014J\b\u0010\u001b\u001a\u00020\u001cH\u0014J\b\u0010\u001d\u001a\u00020\u001cH\u0014J\b\u0010\u001e\u001a\u00020\u001fH\u0014J\b\u0010 \u001a\u00020!H\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/movieboxpro/android/view/fragment/setting/CollectNoticeFragment;", "Lcom/movieboxpro/android/view/activity/review/ReviewListFragment;", "Lcom/movieboxpro/android/model/ReviewModel;", "", "()V", "bbsInfo", "Lcom/movieboxpro/android/model/user/UserModel$BBsInfo;", "userInfo", "Lcom/movieboxpro/android/model/user/UserModel$UserData;", "addOnItemClickViews", "", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "doSomethingWithData", "list", "", "getBundle", "arguments", "Landroid/os/Bundle;", "getServiceData", "Lio/reactivex/Observable;", "initHolder", "helper", "item", "initItemLayout", "", "isNeedTest", "", "isOpenUpFetch", "onItemChildClick", "Lcom/chad/library/adapter/base/listener/OnItemChildClickListener;", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CollectNoticeFragment extends ReviewListFragment<ReviewModel, String> {
    public static final Companion Companion = new Companion(null);
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private UserModel.BBsInfo bbsInfo;
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
        return service.getNotice(str, "mynotice", bbs_uid, author, auth, authkey, bBsInfo2.getFormhash(), CommonUtils.getBBSApiAPPID(), "list", "quote", this.mCurrentPage, this.mPageSize);
    }

    @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
    protected OnItemClickListener onItemClick() {
        return new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.setting.-$$Lambda$CollectNoticeFragment$hTy8h6ewrXxiCbeP0nSh1mOXgac
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                CollectNoticeFragment.m1261onItemClick$lambda1(CollectNoticeFragment.this, baseQuickAdapter, view, i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onItemClick$lambda-1  reason: not valid java name */
    public static final void m1261onItemClick$lambda1(CollectNoticeFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        ReviewModel reviewModel = (ReviewModel) this$0.mAdapter.getItem(i);
        if (reviewModel == null) {
            return;
        }
        MovieListDetailActivity.start(this$0.getContext(), reviewModel.getLid(), "", "");
    }

    @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
    protected OnItemChildClickListener onItemChildClick() {
        return new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.fragment.setting.-$$Lambda$CollectNoticeFragment$E-_sHBrqnmzuACy-p-JqCh5lXKE
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                CollectNoticeFragment.m1260onItemChildClick$lambda3(CollectNoticeFragment.this, baseQuickAdapter, view, i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onItemChildClick$lambda-3  reason: not valid java name */
    public static final void m1260onItemChildClick$lambda3(CollectNoticeFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
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

    @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
    protected void doSomethingWithData(List<ReviewModel> list) {
        if (this.mCurrentPage == 1) {
            EventBus.getDefault().post(new ChangeDotNumEvent(3, 0));
        }
    }

    @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
    protected void getBundle(Bundle bundle) {
        this.mClass = ReviewModel.class;
        this.mPageSize = 10;
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
        adapter.addChildClickViewIds(R.id.ivReply, R.id.ivAvatar);
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
        helper.setText(R.id.tvNote, reviewModel.getNote());
    }

    /* compiled from: CollectNoticeFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/movieboxpro/android/view/fragment/setting/CollectNoticeFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/fragment/setting/CollectNoticeFragment;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final CollectNoticeFragment newInstance() {
            CollectNoticeFragment collectNoticeFragment = new CollectNoticeFragment();
            collectNoticeFragment.setArguments(new Bundle());
            return collectNoticeFragment;
        }
    }
}
