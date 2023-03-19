package com.movieboxpro.android.view.activity.user;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.ChatAdapter;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.mvp.BaseMvpActivity;
import com.movieboxpro.android.event.RefreshMsgDotEvent;
import com.movieboxpro.android.model.ChatMsgModel;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.review.PandaForumAuthorizeActivity;
import com.movieboxpro.android.view.activity.review.UserInfoActivity;
import com.movieboxpro.android.view.activity.user.ChatContract;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;
/* compiled from: ChatActivity.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\u0018\u0000 &2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001&B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0013\u001a\u00020\u0002H\u0014J\b\u0010\u0014\u001a\u00020\nH\u0014J\b\u0010\u0015\u001a\u00020\nH\u0014J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0017H\u0014J\b\u0010\u0019\u001a\u00020\u0017H\u0014J\b\u0010\u001a\u001a\u00020\u0017H\u0014J\b\u0010\u001b\u001a\u00020\fH\u0014J\b\u0010\u001c\u001a\u00020\u0017H\u0014J\u0010\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u0010H\u0016J\u0016\u0010\u001f\u001a\u00020\u00172\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!H\u0016J\u0016\u0010#\u001a\u00020\u00172\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0$H\u0016J\b\u0010%\u001a\u00020\u0017H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/movieboxpro/android/view/activity/user/ChatActivity;", "Lcom/movieboxpro/android/base/mvp/BaseMvpActivity;", "Lcom/movieboxpro/android/view/activity/user/ChatPresenter;", "Lcom/movieboxpro/android/view/activity/user/ChatContract$View;", "()V", "adapter", "Lcom/movieboxpro/android/adapter/ChatAdapter;", "bbsInfo", "Lcom/movieboxpro/android/model/user/UserModel$BBsInfo;", "currPage", "", "isPostEvent", "", "pageSize", "position", "uid", "", "userInfo", "Lcom/movieboxpro/android/model/user/UserModel$UserData;", "bindPresenter", "getLayoutResId", "getStatusColor", "hideSwipeView", "", "initData", "initListener", "initView", "isNeedLoadData", "requestData", "sendComplete", NotificationCompat.CATEGORY_MESSAGE, "showMoreMsg", "list", "", "Lcom/movieboxpro/android/model/ChatMsgModel;", "showMsg", "", "showSwipeView", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ChatActivity extends BaseMvpActivity<ChatPresenter> implements ChatContract.View {
    public static final Companion Companion = new Companion(null);
    private ChatAdapter adapter;
    private UserModel.BBsInfo bbsInfo;
    private boolean isPostEvent;
    private UserModel.UserData userInfo;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String uid = "";
    private int currPage = 1;
    private int pageSize = 10;
    private int position = -1;

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
        return R.layout.activity_chat;
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
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) _$_findCachedViewById(R.id.swipeRefreshLayout);
        Intrinsics.checkNotNullExpressionValue(swipeRefreshLayout, "swipeRefreshLayout");
        CommonExtKt.initColor(swipeRefreshLayout);
        ((TextView) _$_findCachedViewById(R.id.tvTitle)).setText(getIntent().getStringExtra("username"));
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initData() {
        if (App.getBBsInfo() == null) {
            ToastUtils.showShort("Load failed", new Object[0]);
            PandaForumAuthorizeActivity.Companion.start((Context) this, false);
            finish();
            return;
        }
        UserModel.BBsInfo bBsInfo = App.getBBsInfo();
        Intrinsics.checkNotNullExpressionValue(bBsInfo, "getBBsInfo()");
        this.bbsInfo = bBsInfo;
        UserModel.UserData userData = App.getUserData();
        Intrinsics.checkNotNullExpressionValue(userData, "getUserData()");
        this.userInfo = userData;
        String stringExtra = getIntent().getStringExtra("uid");
        if (stringExtra == null) {
            stringExtra = "";
        }
        this.uid = stringExtra;
        this.position = getIntent().getIntExtra("position", -1);
        getWindow().setSoftInputMode(19);
        this.adapter = new ChatAdapter();
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.recyclerView);
        Intrinsics.checkNotNullExpressionValue(recyclerView, "recyclerView");
        CommonExtKt.initLinearAndMargin$default(recyclerView, this, false, 24, false, 2, null);
        RecyclerView.LayoutManager layoutManager = ((RecyclerView) _$_findCachedViewById(R.id.recyclerView)).getLayoutManager();
        if (layoutManager == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
        }
        ((LinearLayoutManager) layoutManager).setStackFromEnd(true);
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.recyclerView);
        ChatAdapter chatAdapter = this.adapter;
        UserModel.BBsInfo bBsInfo2 = null;
        if (chatAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            chatAdapter = null;
        }
        recyclerView2.setAdapter(chatAdapter);
        ChatPresenter chatPresenter = (ChatPresenter) this.mPresenter;
        UserModel.BBsInfo bBsInfo3 = this.bbsInfo;
        if (bBsInfo3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bbsInfo");
            bBsInfo3 = null;
        }
        String bbs_uid = bBsInfo3.getBbs_uid();
        Intrinsics.checkNotNullExpressionValue(bbs_uid, "bbsInfo.bbs_uid");
        String str = this.uid;
        UserModel.UserData userData2 = this.userInfo;
        if (userData2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userInfo");
            userData2 = null;
        }
        String str2 = userData2.username;
        Intrinsics.checkNotNullExpressionValue(str2, "userInfo.username");
        UserModel.BBsInfo bBsInfo4 = this.bbsInfo;
        if (bBsInfo4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bbsInfo");
            bBsInfo4 = null;
        }
        String auth = bBsInfo4.getAuth();
        Intrinsics.checkNotNullExpressionValue(auth, "bbsInfo.auth");
        UserModel.BBsInfo bBsInfo5 = this.bbsInfo;
        if (bBsInfo5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bbsInfo");
            bBsInfo5 = null;
        }
        String authkey = bBsInfo5.getAuthkey();
        Intrinsics.checkNotNullExpressionValue(authkey, "bbsInfo.authkey");
        UserModel.BBsInfo bBsInfo6 = this.bbsInfo;
        if (bBsInfo6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bbsInfo");
        } else {
            bBsInfo2 = bBsInfo6;
        }
        String formhash = bBsInfo2.getFormhash();
        Intrinsics.checkNotNullExpressionValue(formhash, "bbsInfo.formhash");
        chatPresenter.getMsg(bbs_uid, str, str2, auth, authkey, formhash, this.currPage, this.pageSize);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initListener() {
        ((ImageView) _$_findCachedViewById(R.id.ivBack)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.user.-$$Lambda$ChatActivity$MdlmWnLWz-gcNu2K4u70EqxF4Eg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChatActivity.m857initListener$lambda0(ChatActivity.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivSend)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.user.-$$Lambda$ChatActivity$-fFO-O1D7Dm2CQ-szXZBCp_Aeng
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChatActivity.m858initListener$lambda1(ChatActivity.this, view);
            }
        });
        ((EditText) _$_findCachedViewById(R.id.etContent)).setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.view.activity.user.-$$Lambda$ChatActivity$iKSXOdYplVFKxGzyiECsXW2S_sU
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                ChatActivity.m859initListener$lambda2(ChatActivity.this, view, z);
            }
        });
        ((SwipeRefreshLayout) _$_findCachedViewById(R.id.swipeRefreshLayout)).setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.movieboxpro.android.view.activity.user.-$$Lambda$ChatActivity$W4fFTTKuHWGd0231m7viqM3VqZA
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                ChatActivity.m860initListener$lambda3(ChatActivity.this);
            }
        });
        ChatAdapter chatAdapter = this.adapter;
        if (chatAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            chatAdapter = null;
        }
        chatAdapter.setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.activity.user.-$$Lambda$ChatActivity$QAfNlAIxC8SZU7PWH67s-Ng_3j8
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ChatActivity.m861initListener$lambda4(ChatActivity.this, baseQuickAdapter, view, i);
            }
        });
    }

    /* renamed from: initListener$lambda-0 */
    public static final void m857initListener$lambda0(ChatActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* renamed from: initListener$lambda-1 */
    public static final void m858initListener$lambda1(ChatActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String obj = ((EditText) this$0._$_findCachedViewById(R.id.etContent)).getText().toString();
        if (!StringsKt.isBlank(obj)) {
            ChatPresenter chatPresenter = (ChatPresenter) this$0.mPresenter;
            String str = this$0.uid;
            UserModel.BBsInfo bBsInfo = this$0.bbsInfo;
            UserModel.BBsInfo bBsInfo2 = null;
            if (bBsInfo == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bbsInfo");
                bBsInfo = null;
            }
            String auth = bBsInfo.getAuth();
            Intrinsics.checkNotNullExpressionValue(auth, "bbsInfo.auth");
            UserModel.BBsInfo bBsInfo3 = this$0.bbsInfo;
            if (bBsInfo3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bbsInfo");
                bBsInfo3 = null;
            }
            String authkey = bBsInfo3.getAuthkey();
            Intrinsics.checkNotNullExpressionValue(authkey, "bbsInfo.authkey");
            UserModel.BBsInfo bBsInfo4 = this$0.bbsInfo;
            if (bBsInfo4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bbsInfo");
            } else {
                bBsInfo2 = bBsInfo4;
            }
            String formhash = bBsInfo2.getFormhash();
            Intrinsics.checkNotNullExpressionValue(formhash, "bbsInfo.formhash");
            chatPresenter.sendMsg(str, auth, authkey, formhash, obj, "ABCD");
            ((EditText) this$0._$_findCachedViewById(R.id.etContent)).setText("");
        }
    }

    /* renamed from: initListener$lambda-2 */
    public static final void m859initListener$lambda2(ChatActivity this$0, View view, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            RecyclerView recyclerView = (RecyclerView) this$0._$_findCachedViewById(R.id.recyclerView);
            ChatAdapter chatAdapter = this$0.adapter;
            if (chatAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                chatAdapter = null;
            }
            recyclerView.scrollToPosition(chatAdapter.getData().size() - 1);
        }
    }

    /* renamed from: initListener$lambda-3 */
    public static final void m860initListener$lambda3(ChatActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.currPage++;
        ChatPresenter chatPresenter = (ChatPresenter) this$0.mPresenter;
        UserModel.UserData userData = this$0.userInfo;
        UserModel.BBsInfo bBsInfo = null;
        if (userData == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userInfo");
            userData = null;
        }
        String str = userData.uid_v2;
        Intrinsics.checkNotNullExpressionValue(str, "userInfo.uid_v2");
        String str2 = this$0.uid;
        UserModel.UserData userData2 = this$0.userInfo;
        if (userData2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userInfo");
            userData2 = null;
        }
        String str3 = userData2.username;
        Intrinsics.checkNotNullExpressionValue(str3, "userInfo.username");
        UserModel.BBsInfo bBsInfo2 = this$0.bbsInfo;
        if (bBsInfo2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bbsInfo");
            bBsInfo2 = null;
        }
        String auth = bBsInfo2.getAuth();
        Intrinsics.checkNotNullExpressionValue(auth, "bbsInfo.auth");
        UserModel.BBsInfo bBsInfo3 = this$0.bbsInfo;
        if (bBsInfo3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bbsInfo");
            bBsInfo3 = null;
        }
        String authkey = bBsInfo3.getAuthkey();
        Intrinsics.checkNotNullExpressionValue(authkey, "bbsInfo.authkey");
        UserModel.BBsInfo bBsInfo4 = this$0.bbsInfo;
        if (bBsInfo4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bbsInfo");
        } else {
            bBsInfo = bBsInfo4;
        }
        String formhash = bBsInfo.getFormhash();
        Intrinsics.checkNotNullExpressionValue(formhash, "bbsInfo.formhash");
        chatPresenter.getMsg(str, str2, str3, auth, authkey, formhash, this$0.currPage, this$0.pageSize);
    }

    /* renamed from: initListener$lambda-4 */
    public static final void m861initListener$lambda4(ChatActivity this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        ChatAdapter chatAdapter = this$0.adapter;
        if (chatAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            chatAdapter = null;
        }
        ChatMsgModel item = chatAdapter.getItem(i);
        if (item != null) {
            if (App.getBBsInfo() != null) {
                String authorid = item.getAuthorid();
                Intrinsics.checkNotNullExpressionValue(authorid, "item.authorid");
                UserInfoActivity.Companion.start(this$0, authorid);
                return;
            }
            PandaForumAuthorizeActivity.Companion.start(this$0);
        }
    }

    @Override // com.movieboxpro.android.view.activity.user.ChatContract.View
    public void showMsg(List<ChatMsgModel> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        if (this.position != -1 && !this.isPostEvent) {
            this.isPostEvent = true;
            EventBus.getDefault().post(new RefreshMsgDotEvent(this.position));
        }
        ChatAdapter chatAdapter = this.adapter;
        if (chatAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            chatAdapter = null;
        }
        chatAdapter.setList(list);
        if (list.size() < this.pageSize) {
            ((SwipeRefreshLayout) _$_findCachedViewById(R.id.swipeRefreshLayout)).setEnabled(false);
        }
    }

    @Override // com.movieboxpro.android.view.activity.user.ChatContract.View
    public void showMoreMsg(List<? extends ChatMsgModel> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        ChatAdapter chatAdapter = this.adapter;
        if (chatAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            chatAdapter = null;
        }
        chatAdapter.addData(0, (Collection) list);
        if (list.size() < this.pageSize) {
            ((SwipeRefreshLayout) _$_findCachedViewById(R.id.swipeRefreshLayout)).setEnabled(false);
        }
        RecyclerView.LayoutManager layoutManager = ((RecyclerView) _$_findCachedViewById(R.id.recyclerView)).getLayoutManager();
        if (layoutManager == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
        }
        ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(list.size() - 1, 0);
    }

    @Override // com.movieboxpro.android.view.activity.user.ChatContract.View
    public void sendComplete(String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        ChatMsgModel chatMsgModel = new ChatMsgModel();
        chatMsgModel.setMessage(msg);
        UserModel.UserData userData = this.userInfo;
        ChatAdapter chatAdapter = null;
        if (userData == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userInfo");
            userData = null;
        }
        chatMsgModel.setAvatar(userData.avatar);
        chatMsgModel.setDateline(System.currentTimeMillis() / 1000);
        chatMsgModel.setIsYou(0);
        ChatAdapter chatAdapter2 = this.adapter;
        if (chatAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            chatAdapter2 = null;
        }
        chatAdapter2.addData((ChatAdapter) chatMsgModel);
        RecyclerView.LayoutManager layoutManager = ((RecyclerView) _$_findCachedViewById(R.id.recyclerView)).getLayoutManager();
        if (layoutManager == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
        }
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
        ChatAdapter chatAdapter3 = this.adapter;
        if (chatAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            chatAdapter = chatAdapter3;
        }
        linearLayoutManager.scrollToPositionWithOffset(chatAdapter.getData().size() - 1, 0);
    }

    @Override // com.movieboxpro.android.view.activity.user.ChatContract.View
    public void showSwipeView() {
        ((SwipeRefreshLayout) _$_findCachedViewById(R.id.swipeRefreshLayout)).setRefreshing(true);
    }

    @Override // com.movieboxpro.android.view.activity.user.ChatContract.View
    public void hideSwipeView() {
        ((SwipeRefreshLayout) _$_findCachedViewById(R.id.swipeRefreshLayout)).setRefreshing(false);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    public ChatPresenter bindPresenter() {
        return new ChatPresenter(this);
    }

    /* compiled from: ChatActivity.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J*\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/view/activity/user/ChatActivity$Companion;", "", "()V", TtmlNode.START, "", "context", "Landroid/content/Context;", "uid", "", "username", "position", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ void start$default(Companion companion, Context context, String str, String str2, int i, int i2, Object obj) {
            if ((i2 & 8) != 0) {
                i = -1;
            }
            companion.start(context, str, str2, i);
        }

        public final void start(Context context, String uid, String username, int i) {
            Intrinsics.checkNotNullParameter(uid, "uid");
            Intrinsics.checkNotNullParameter(username, "username");
            Intent intent = new Intent(context, ChatActivity.class);
            intent.putExtra("uid", uid);
            intent.putExtra("username", username);
            intent.putExtra("position", i);
            if (context == null) {
                return;
            }
            context.startActivity(intent);
        }
    }
}
