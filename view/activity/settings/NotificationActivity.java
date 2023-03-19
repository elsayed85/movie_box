package com.movieboxpro.android.view.activity.settings;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.viewpager.widget.ViewPager;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.TabLayoutPagerAdapter;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseSimpleActivity;
import com.movieboxpro.android.event.ChangeDotNumEvent;
import com.movieboxpro.android.event.OnNoticeRefreshEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.APIService;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.ServerException;
import com.movieboxpro.android.model.NoticeCountModel;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.view.fragment.setting.CollectNoticeFragment;
import com.movieboxpro.android.view.fragment.setting.LikeNoticeFragment;
import com.movieboxpro.android.view.fragment.setting.MessagesListFragment;
import com.movieboxpro.android.view.fragment.setting.ReplyMessageFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
/* compiled from: NotificationActivity.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0014J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\u0006H\u0014J\b\u0010\n\u001a\u00020\bH\u0016J\b\u0010\u000b\u001a\u00020\bH\u0016J\b\u0010\f\u001a\u00020\bH\u0016J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J\b\u0010\u0010\u001a\u00020\bH\u0014J\u0018\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0006H\u0002¨\u0006\u0015"}, d2 = {"Lcom/movieboxpro/android/view/activity/settings/NotificationActivity;", "Lcom/movieboxpro/android/base/BaseSimpleActivity;", "()V", "enableEventBus", "", "getLayoutResId", "", "getNoticeCount", "", "getStatusColor", "initData", "initListener", "initView", "onChangeDotNumber", NotificationCompat.CATEGORY_EVENT, "Lcom/movieboxpro/android/event/ChangeDotNumEvent;", "onPause", "setDotNum", "position", "dotNum", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class NotificationActivity extends BaseSimpleActivity {
    public static final Companion Companion = new Companion(null);
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
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

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    protected boolean enableEventBus() {
        return true;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public int getLayoutResId() {
        return R.layout.activity_notification;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    protected int getStatusColor() {
        return R.color.color_main;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initListener() {
        ((LinearLayout) _$_findCachedViewById(R.id.ll_back)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$NotificationActivity$cEYixy-a7mAAQNpW4_9qGawqhzQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NotificationActivity.m801initListener$lambda0(NotificationActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m801initListener$lambda0(NotificationActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initData() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(ReplyMessageFragment.Companion.newInstance());
        arrayList.add(MessagesListFragment.Companion.newInstance());
        arrayList.add(LikeNoticeFragment.Companion.newInstance());
        arrayList.add(CollectNoticeFragment.Companion.newInstance());
        TabLayoutPagerAdapter tabLayoutPagerAdapter = new TabLayoutPagerAdapter(getSupportFragmentManager(), arrayList, new String[]{"Reply", "Message", "Like", "List"});
        ((ViewPager) _$_findCachedViewById(R.id.viewPager)).setOffscreenPageLimit(arrayList.size());
        ((ViewPager) _$_findCachedViewById(R.id.viewPager)).setAdapter(tabLayoutPagerAdapter);
        ((SmartTabLayout) _$_findCachedViewById(R.id.tabLayout)).setViewPager((ViewPager) _$_findCachedViewById(R.id.viewPager));
        getNoticeCount();
    }

    private final void getNoticeCount() {
        String bbs_uid;
        APIService service = Http.getService();
        String str = API.BBS_URL;
        UserModel.BBsInfo bBsInfo = App.getBBsInfo();
        String str2 = "";
        if (bBsInfo != null && (bbs_uid = bBsInfo.getBbs_uid()) != null) {
            str2 = bbs_uid;
        }
        Observable<R> compose = service.getNoticeCount(str, "get_newnotice_count", str2, "total").compose(RxUtils.rxTranslate2Bean(NoticeCountModel.class));
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
        RxSubscribersKt.transform(compose, this).subscribe(new Consumer() { // from class: com.movieboxpro.android.view.activity.settings.NotificationActivity$getNoticeCount$$inlined$subscribeToBean$default$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(T it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                NoticeCountModel noticeCountModel = (NoticeCountModel) it;
                NotificationActivity.this.setDotNum(0, noticeCountModel.getReply_newCount());
                NotificationActivity.this.setDotNum(1, noticeCountModel.getPm_newCount());
                NotificationActivity.this.setDotNum(2, noticeCountModel.getLike_newCount());
                NotificationActivity.this.setDotNum(3, noticeCountModel.getList_newCount());
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.settings.NotificationActivity$getNoticeCount$$inlined$subscribeToBean$default$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Intrinsics.checkNotNullExpressionValue(ApiException.handleException(th), "handleException(it)");
                if (th instanceof ServerException) {
                    ServerException serverException = (ServerException) th;
                }
            }
        }, new Action() { // from class: com.movieboxpro.android.view.activity.settings.NotificationActivity$getNoticeCount$$inlined$subscribeToBean$default$3
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.settings.NotificationActivity$getNoticeCount$$inlined$subscribeToBean$default$4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onChangeDotNumber(ChangeDotNumEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        setDotNum(event.getPosition(), event.getNum());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setDotNum(int i, int i2) {
        View tabAt = ((SmartTabLayout) _$_findCachedViewById(R.id.tabLayout)).getTabAt(i);
        if (tabAt != null) {
            TextView textView = (TextView) tabAt.findViewById(R.id.tab_count);
            String obj = textView.getText().toString();
            int parseInt = StringsKt.isBlank(obj) ? 0 : Integer.parseInt(obj);
            if (i2 == -1) {
                i2 = parseInt - 1;
            }
            if (i2 <= 0) {
                Intrinsics.checkNotNullExpressionValue(textView, "textView");
                CommonExtKt.gone(textView);
                return;
            }
            Intrinsics.checkNotNullExpressionValue(textView, "textView");
            CommonExtKt.visible(textView);
            textView.setText(String.valueOf(i2));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseSimpleActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        EventBus.getDefault().post(new OnNoticeRefreshEvent());
        super.onPause();
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initView() {
        ((TextView) _$_findCachedViewById(R.id.tv_title)).setText("Notification");
        ((FrameLayout) _$_findCachedViewById(R.id.frameLayout)).setBackgroundColor(CommonExtKt.colorInt((Context) this, (int) R.color.color_main));
    }

    /* compiled from: NotificationActivity.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/view/activity/settings/NotificationActivity$Companion;", "", "()V", TtmlNode.START, "", "context", "Landroid/content/Context;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context) {
            Intent intent = new Intent(context, NotificationActivity.class);
            if (context == null) {
                return;
            }
            context.startActivity(intent);
        }
    }
}
