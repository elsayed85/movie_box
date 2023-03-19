package com.movieboxpro.android.view.fragment.setting;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.core.app.NotificationCompat;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.event.ChangeDotNumEvent;
import com.movieboxpro.android.event.RefreshMsgDotEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.APIService;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.MessageResponse;
import com.movieboxpro.android.model.MessagesModel;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.view.activity.review.PandaForumAuthorizeActivity;
import com.movieboxpro.android.view.activity.review.ReviewListFragment;
import com.movieboxpro.android.view.activity.review.UserInfoActivity;
import com.movieboxpro.android.view.activity.user.ChatActivity;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
/* compiled from: MessagesListFragment.kt */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 &2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001&B\u0005¢\u0006\u0002\u0010\u0004J\u001c\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\r0\fH\u0014J\b\u0010\u000e\u001a\u00020\u000fH\u0014J\u0012\u0010\u0010\u001a\u00020\n2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\u0018\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0003H\u0014J\u000e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0014J\u001a\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\r2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0002H\u0014J\b\u0010\u001c\u001a\u00020\u001dH\u0014J\b\u0010\u001e\u001a\u00020\u000fH\u0014J\b\u0010\u001f\u001a\u00020 H\u0014J\b\u0010!\u001a\u00020\"H\u0014J\u0010\u0010#\u001a\u00020\n2\u0006\u0010$\u001a\u00020%H\u0007R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/movieboxpro/android/view/fragment/setting/MessagesListFragment;", "Lcom/movieboxpro/android/view/activity/review/ReviewListFragment;", "Lcom/movieboxpro/android/model/MessagesModel;", "Lcom/movieboxpro/android/model/MessageResponse;", "()V", "bbsInfo", "Lcom/movieboxpro/android/model/user/UserModel$BBsInfo;", "userInfo", "Lcom/movieboxpro/android/model/user/UserModel$UserData;", "addOnItemClickViews", "", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "enableEventBus", "", "getBundle", "arguments", "Landroid/os/Bundle;", "getData", "", TtmlNode.TAG_P, "getServiceData", "Lio/reactivex/Observable;", "", "initHolder", "helper", "item", "initItemLayout", "", "isOpenUpFetch", "onItemChildClick", "Lcom/chad/library/adapter/base/listener/OnItemChildClickListener;", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "onRefreshDot", NotificationCompat.CATEGORY_EVENT, "Lcom/movieboxpro/android/event/RefreshMsgDotEvent;", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class MessagesListFragment extends ReviewListFragment<MessagesModel, MessageResponse> {
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
    protected boolean enableEventBus() {
        return true;
    }

    @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
    protected int initItemLayout() {
        return R.layout.adapter_messages_item;
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
        return service.getMsgNotice(str, "mypm", bbs_uid, author, auth, authkey, bBsInfo2.getFormhash(), CommonUtils.getBBSApiAPPID(), "privatepm", this.mCurrentPage, this.mPageSize);
    }

    @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
    protected void getBundle(Bundle bundle) {
        this.mPageClass = MessageResponse.class;
        this.mClass = MessagesModel.class;
        UserModel.UserData userData = App.getUserData();
        Intrinsics.checkNotNullExpressionValue(userData, "getUserData()");
        this.userInfo = userData;
        UserModel.BBsInfo bBsInfo = App.getBBsInfo();
        Intrinsics.checkNotNullExpressionValue(bBsInfo, "getBBsInfo()");
        this.bbsInfo = bBsInfo;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
    public List<MessagesModel> getData(MessageResponse messageResponse) {
        List<MessagesModel> list = messageResponse == null ? null : messageResponse.getList();
        return list == null ? new ArrayList() : list;
    }

    @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
    protected OnItemChildClickListener onItemChildClick() {
        return new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.fragment.setting.-$$Lambda$MessagesListFragment$OxlCRySHOmIXA-84wmFhlvbPK1I
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MessagesListFragment.m1265onItemChildClick$lambda1(MessagesListFragment.this, baseQuickAdapter, view, i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onItemChildClick$lambda-1  reason: not valid java name */
    public static final void m1265onItemChildClick$lambda1(MessagesListFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        MessagesModel messagesModel = (MessagesModel) this$0.mAdapter.getItem(i);
        if (messagesModel == null) {
            return;
        }
        if (App.getBBsInfo() != null) {
            UserInfoActivity.Companion companion = UserInfoActivity.Companion;
            Context context = this$0.getContext();
            String authorid = messagesModel.getAuthorid();
            Intrinsics.checkNotNullExpressionValue(authorid, "it.authorid");
            companion.start(context, authorid);
            return;
        }
        PandaForumAuthorizeActivity.Companion.start(this$0.getContext());
    }

    @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
    protected OnItemClickListener onItemClick() {
        return new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.setting.-$$Lambda$MessagesListFragment$6uN6jVoUEXvnfX2iEIR1UnivTkg
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MessagesListFragment.m1266onItemClick$lambda3(MessagesListFragment.this, baseQuickAdapter, view, i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onItemClick$lambda-3  reason: not valid java name */
    public static final void m1266onItemClick$lambda3(MessagesListFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        MessagesModel messagesModel = (MessagesModel) this$0.mAdapter.getItem(i);
        if (messagesModel == null) {
            return;
        }
        if (messagesModel.getIsnew() == 1) {
            ChatActivity.Companion companion = ChatActivity.Companion;
            Context context = this$0.getContext();
            String msgtoid = messagesModel.getMsgtoid();
            Intrinsics.checkNotNullExpressionValue(msgtoid, "it.msgtoid");
            String tousername = messagesModel.getTousername();
            companion.start(context, msgtoid, tousername != null ? tousername : "", i);
            return;
        }
        ChatActivity.Companion companion2 = ChatActivity.Companion;
        Context context2 = this$0.getContext();
        String msgtoid2 = messagesModel.getMsgtoid();
        Intrinsics.checkNotNullExpressionValue(msgtoid2, "it.msgtoid");
        String tousername2 = messagesModel.getTousername();
        ChatActivity.Companion.start$default(companion2, context2, msgtoid2, tousername2 == null ? "" : tousername2, 0, 8, null);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onRefreshDot(RefreshMsgDotEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        MessagesModel messagesModel = (MessagesModel) this.mAdapter.getItem(event.getPosition());
        if (messagesModel == null) {
            return;
        }
        EventBus.getDefault().post(new ChangeDotNumEvent(1, -1));
        messagesModel.setIsnew(0);
        this.mAdapter.notifyItemChanged(event.getPosition());
    }

    @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
    protected void addOnItemClickViews(BaseQuickAdapter<MessagesModel, BaseViewHolder> adapter) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        adapter.addChildClickViewIds(R.id.ivAvatar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.view.activity.review.ReviewListFragment
    public void initHolder(BaseViewHolder helper, MessagesModel messagesModel) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        if (messagesModel == null) {
            return;
        }
        helper.setText(R.id.tvName, messagesModel.getTousername());
        helper.setText(R.id.tvTime, TimeUtils.formatTime(messagesModel.getDateline() * 1000));
        helper.setText(R.id.tvMsg, messagesModel.getMessage());
        GlideUtils.load(getContext(), messagesModel.getAvatar(), (ImageView) helper.getView(R.id.ivAvatar), (int) R.mipmap.ic_panda_forum_default_avatar);
        View view = helper.getView(R.id.view);
        if (messagesModel.getIsnew() == 1) {
            CommonExtKt.visible(view);
        } else {
            CommonExtKt.gone(view);
        }
    }

    /* compiled from: MessagesListFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/movieboxpro/android/view/fragment/setting/MessagesListFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/fragment/setting/MessagesListFragment;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MessagesListFragment newInstance() {
            MessagesListFragment messagesListFragment = new MessagesListFragment();
            messagesListFragment.setArguments(new Bundle());
            return messagesListFragment;
        }
    }
}
