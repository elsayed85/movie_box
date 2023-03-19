package com.movieboxpro.android.view.activity.user;

import androidx.lifecycle.LifecycleOwner;
import com.alibaba.fastjson.JSON;
import com.movieboxpro.android.base.mvp.BasePresenter;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpUtils;
import com.movieboxpro.android.http.ServerException;
import com.movieboxpro.android.model.BBsResponseModel;
import com.movieboxpro.android.model.ChatMsgModel;
import com.movieboxpro.android.model.ChatMsgResponseModel;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.user.ChatContract;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
/* compiled from: ChatPresenter.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0002JH\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0016J8\u0010\u0014\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\bH\u0016¨\u0006\u0017"}, d2 = {"Lcom/movieboxpro/android/view/activity/user/ChatPresenter;", "Lcom/movieboxpro/android/base/mvp/BasePresenter;", "Lcom/movieboxpro/android/view/activity/user/ChatContract$View;", "Lcom/movieboxpro/android/view/activity/user/ChatContract$Presenter;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "(Landroidx/lifecycle/LifecycleOwner;)V", "getBBSApiAPPID", "", "getMsg", "", "uid", "toUid", "username", "auth", "authkey", "formhash", "page", "", "pageSize", "sendMsg", "message", "users", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ChatPresenter extends BasePresenter<ChatContract.View> implements ChatContract.Presenter {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChatPresenter(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
    }

    @Override // com.movieboxpro.android.view.activity.user.ChatContract.Presenter
    public void getMsg(String uid, String toUid, String username, String auth, String authkey, String formhash, final int i, int i2) {
        Intrinsics.checkNotNullParameter(uid, "uid");
        Intrinsics.checkNotNullParameter(toUid, "toUid");
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(auth, "auth");
        Intrinsics.checkNotNullParameter(authkey, "authkey");
        Intrinsics.checkNotNullParameter(formhash, "formhash");
        Observable<String> chatMsg = Http.getService().getChatMsg(API.BBS_URL, "mypm_view", uid, username, "view", toUid, auth, authkey, formhash, getBBSApiAPPID(), i, i2);
        LifecycleOwner mLifecycleOwner = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner, "mLifecycleOwner");
        Observable<R> compose = chatMsg.compose(RxUtils.rxTranslate2Bean(ChatMsgResponseModel.class));
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
        RxSubscribersKt.transform(compose, mLifecycleOwner).subscribe(new Consumer() { // from class: com.movieboxpro.android.view.activity.user.ChatPresenter$getMsg$$inlined$subscribeToBean$default$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(T it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                ChatMsgResponseModel chatMsgResponseModel = (ChatMsgResponseModel) it;
                if (i == 1) {
                    List<ChatMsgModel> list = chatMsgResponseModel.getList();
                    Intrinsics.checkNotNullExpressionValue(list, "it.list");
                    this.getView().showMsg(list);
                } else {
                    List<ChatMsgModel> list2 = chatMsgResponseModel.getList();
                    Intrinsics.checkNotNullExpressionValue(list2, "it.list");
                    this.getView().showMoreMsg(list2);
                }
                this.getView().hideSwipeView();
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.user.ChatPresenter$getMsg$$inlined$subscribeToBean$default$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                ApiException handleException = ApiException.handleException(th);
                Intrinsics.checkNotNullExpressionValue(handleException, "handleException(it)");
                ChatPresenter.this.getView().hideSwipeView();
                ToastUtils.showShort(Intrinsics.stringPlus("Load failed:", handleException.getMessage()), new Object[0]);
                if (th instanceof ServerException) {
                    ServerException serverException = (ServerException) th;
                }
            }
        }, new Action() { // from class: com.movieboxpro.android.view.activity.user.ChatPresenter$getMsg$$inlined$subscribeToBean$default$3
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.user.ChatPresenter$getMsg$$inlined$subscribeToBean$default$4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                ChatPresenter.this.getView().showSwipeView();
            }
        });
    }

    @Override // com.movieboxpro.android.view.activity.user.ChatContract.Presenter
    public void sendMsg(String uid, String auth, String authkey, String formhash, final String message, String users) {
        Intrinsics.checkNotNullParameter(uid, "uid");
        Intrinsics.checkNotNullParameter(auth, "auth");
        Intrinsics.checkNotNullParameter(authkey, "authkey");
        Intrinsics.checkNotNullParameter(formhash, "formhash");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(users, "users");
        Observable<R> map = Http.getService().sendMsg(API.BBS_URL, "sendpm", uid, auth, authkey, formhash, message, 0, users, "", getBBSApiAPPID()).map($$Lambda$ChatPresenter$N3OlFAHwygntED8mIJHs44ojI20.INSTANCE);
        LifecycleOwner mLifecycleOwner = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(map, "map {\n                  …s.java)\n                }");
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner, "mLifecycleOwner");
        RxSubscribersKt.transform(map, mLifecycleOwner).subscribe(new Consumer() { // from class: com.movieboxpro.android.view.activity.user.ChatPresenter$sendMsg$$inlined$transformSubscribe$default$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(T it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                BBsResponseModel bBsResponseModel = (BBsResponseModel) it;
                if (Intrinsics.areEqual(bBsResponseModel.getMessage().getMessageval(), "do_success")) {
                    ChatPresenter.this.getView().sendComplete(message);
                } else if (Intrinsics.areEqual(bBsResponseModel.getMessage().getMessageval(), "message_can_not_send_2")) {
                    ToastUtils.showShort(bBsResponseModel.getMessage().getMessagestr(), new Object[0]);
                } else {
                    ToastUtils.showShort(Intrinsics.stringPlus("Send failed:", bBsResponseModel.getMessage().getMessagestr()), new Object[0]);
                }
                ChatPresenter.this.getView().hideLoadingView();
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.user.ChatPresenter$sendMsg$$inlined$transformSubscribe$default$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                ApiException handleException = ApiException.handleException(th);
                Intrinsics.checkNotNullExpressionValue(handleException, "handleException(it)");
                ChatPresenter.this.getView().hideLoadingView();
                ToastUtils.showShort(Intrinsics.stringPlus("Send failed:", handleException.getMessage()), new Object[0]);
                if (th instanceof ServerException) {
                    ServerException serverException = (ServerException) th;
                }
            }
        }, new Action() { // from class: com.movieboxpro.android.view.activity.user.ChatPresenter$sendMsg$$inlined$transformSubscribe$default$3
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.user.ChatPresenter$sendMsg$$inlined$transformSubscribe$default$4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                ChatPresenter.this.getView().showLoadingView();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendMsg$lambda-3  reason: not valid java name */
    public static final BBsResponseModel m865sendMsg$lambda3(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return (BBsResponseModel) JSON.parseObject(it, BBsResponseModel.class);
    }

    private final String getBBSApiAPPID() {
        long currentTime = TimeUtils.getCurrentTime() / 1000;
        String md5 = HttpUtils.md5("27");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.CHINA, "%d%s", Arrays.copyOf(new Object[]{Long.valueOf(currentTime), md5}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        return format;
    }
}
