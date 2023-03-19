package com.movieboxpro.android.view.activity.user;

import androidx.core.app.NotificationCompat;
import com.movieboxpro.android.base.mvp.BaseContract;
import com.movieboxpro.android.model.ChatMsgModel;
import java.util.List;
import kotlin.Metadata;
/* compiled from: ChatContract.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004"}, d2 = {"Lcom/movieboxpro/android/view/activity/user/ChatContract;", "", "Presenter", "View", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface ChatContract {

    /* compiled from: ChatContract.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001JH\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH&J8\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0006H&¨\u0006\u0012"}, d2 = {"Lcom/movieboxpro/android/view/activity/user/ChatContract$Presenter;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BasePresenter;", "Lcom/movieboxpro/android/view/activity/user/ChatContract$View;", "getMsg", "", "uid", "", "toUid", "username", "auth", "authkey", "formhash", "page", "", "pageSize", "sendMsg", "message", "users", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface Presenter extends BaseContract.BasePresenter<View> {
        void getMsg(String str, String str2, String str3, String str4, String str5, String str6, int i, int i2);

        void sendMsg(String str, String str2, String str3, String str4, String str5, String str6);
    }

    /* compiled from: ChatContract.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0016\u0010\u0007\u001a\u00020\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH&J\u0016\u0010\u000b\u001a\u00020\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\fH&J\b\u0010\r\u001a\u00020\u0003H&¨\u0006\u000e"}, d2 = {"Lcom/movieboxpro/android/view/activity/user/ChatContract$View;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BaseView;", "hideSwipeView", "", "sendComplete", NotificationCompat.CATEGORY_MESSAGE, "", "showMoreMsg", "list", "", "Lcom/movieboxpro/android/model/ChatMsgModel;", "showMsg", "", "showSwipeView", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface View extends BaseContract.BaseView {
        void hideSwipeView();

        void sendComplete(String str);

        void showMoreMsg(List<? extends ChatMsgModel> list);

        void showMsg(List<ChatMsgModel> list);

        void showSwipeView();
    }
}
