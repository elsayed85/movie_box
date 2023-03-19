package com.movieboxpro.android.view.activity.review;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.TabLayoutPagerAdapter;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.mvp.BaseMvpActivity;
import com.movieboxpro.android.model.UserProfileResponse;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.view.activity.review.UserInfoContract;
import com.movieboxpro.android.view.activity.user.ChatActivity;
import com.movieboxpro.android.view.fragment.MovieListList2Fragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: UserInfoActivity.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00162\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u0016B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\b\u001a\u00020\u0002H\u0014J\b\u0010\t\u001a\u00020\nH\u0014J\b\u0010\u000b\u001a\u00020\fH\u0014J\b\u0010\r\u001a\u00020\fH\u0014J\b\u0010\u000e\u001a\u00020\fH\u0014J\b\u0010\u000f\u001a\u00020\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u0010H\u0014J\b\u0010\u0012\u001a\u00020\fH\u0014J\u0010\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/UserInfoActivity;", "Lcom/movieboxpro/android/base/mvp/BaseMvpActivity;", "Lcom/movieboxpro/android/view/activity/review/UserInfoPresenter;", "Lcom/movieboxpro/android/view/activity/review/UserInfoContract$View;", "()V", "uid", "", "username", "bindPresenter", "getLayoutResId", "", "initData", "", "initListener", "initView", "isFullScreen", "", "isNeedLoadData", "requestData", "showUserInfo", "userInfo", "Lcom/movieboxpro/android/model/UserProfileResponse;", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UserInfoActivity extends BaseMvpActivity<UserInfoPresenter> implements UserInfoContract.View {
    public static final Companion Companion = new Companion(null);
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String uid = "";
    private String username = "";

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
        return R.layout.activity_user_info;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected boolean isFullScreen() {
        return true;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected boolean isNeedLoadData() {
        return true;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initView() {
        DensityUtils.addMarginTopEqualStatusBarHeight((ImageView) _$_findCachedViewById(R.id.ivBack), this);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initData() {
        String stringExtra = getIntent().getStringExtra("uid");
        if (stringExtra == null) {
            stringExtra = "";
        }
        this.uid = stringExtra;
    }

    @Override // com.movieboxpro.android.view.activity.review.UserInfoContract.View
    public void showUserInfo(UserProfileResponse userInfo) {
        Intrinsics.checkNotNullParameter(userInfo, "userInfo");
        try {
            GlideUtils.load((Activity) this, userInfo.getVariables().getSpace().getAvatar(), (ImageView) ((CircleImageView) _$_findCachedViewById(R.id.ivAvatar)), (int) R.mipmap.ic_panda_forum_default_avatar);
            ((TextView) _$_findCachedViewById(R.id.tvNickname)).setText(userInfo.getVariables().getSpace().getUsername());
            this.username = userInfo.getVariables().getSpace().getUsername();
            if (App.getBBsInfo() != null && Intrinsics.areEqual(this.uid, App.getBBsInfo().getBbs_uid())) {
                ImageView ivSendMsg = (ImageView) _$_findCachedViewById(R.id.ivSendMsg);
                Intrinsics.checkNotNullExpressionValue(ivSendMsg, "ivSendMsg");
                CommonExtKt.gone(ivSendMsg);
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(UserCommentFragment.Companion.newInstance(this.uid));
            arrayList.add(MovieListList2Fragment.newInstance("mine", userInfo.getVariables().getSpace().getMbp_uid(), true));
            TabLayoutPagerAdapter tabLayoutPagerAdapter = new TabLayoutPagerAdapter(getSupportFragmentManager(), arrayList, new String[]{"Comment", "MovieLists"});
            ((ViewPager) _$_findCachedViewById(R.id.viewPager)).setOffscreenPageLimit(arrayList.size());
            ((ViewPager) _$_findCachedViewById(R.id.viewPager)).setAdapter(tabLayoutPagerAdapter);
            ((SmartTabLayout) _$_findCachedViewById(R.id.tabLayout)).setViewPager((ViewPager) _$_findCachedViewById(R.id.viewPager));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initListener() {
        ((ImageView) _$_findCachedViewById(R.id.ivBack)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$UserInfoActivity$OdbNkk70Zm0G0r7F0kZVBcjx20U
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserInfoActivity.m765initListener$lambda0(UserInfoActivity.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivSendMsg)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$UserInfoActivity$3Rt_OLO-ksMm8pvvmO81at84rvg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserInfoActivity.m766initListener$lambda1(UserInfoActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m765initListener$lambda0(UserInfoActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m766initListener$lambda1(UserInfoActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ChatActivity.Companion companion = ChatActivity.Companion;
        UserInfoActivity userInfoActivity = this$0;
        String str = this$0.uid;
        String str2 = this$0.username;
        if (str2 == null) {
            str2 = "";
        }
        ChatActivity.Companion.start$default(companion, userInfoActivity, str, str2, 0, 8, null);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void requestData() {
        ((UserInfoPresenter) this.mPresenter).getUserInfo(this.uid);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    public UserInfoPresenter bindPresenter() {
        return new UserInfoPresenter(this);
    }

    /* compiled from: UserInfoActivity.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/UserInfoActivity$Companion;", "", "()V", TtmlNode.START, "", "context", "Landroid/content/Context;", "uid", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context, String uid) {
            Intrinsics.checkNotNullParameter(uid, "uid");
            Intent intent = new Intent(context, UserInfoActivity.class);
            intent.putExtra("uid", uid);
            if (context == null) {
                return;
            }
            context.startActivity(intent);
        }
    }
}
