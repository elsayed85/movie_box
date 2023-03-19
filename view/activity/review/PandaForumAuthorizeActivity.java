package com.movieboxpro.android.view.activity.review;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes;
import com.google.android.gms.common.Scopes;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseSimpleActivity;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.ServerException;
import com.movieboxpro.android.model.BBsInfoModel;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SpanUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.MsgHintDialog;
import com.uber.autodispose.ObservableSubscribeProxy;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
/* compiled from: PandaForumAuthorizeActivity.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\u0006H\u0016J\b\u0010\n\u001a\u00020\u0006H\u0016J\b\u0010\u000b\u001a\u00020\u0006H\u0016J0\u0010\f\u001a\u00020\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u000eH\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0014J\"\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/PandaForumAuthorizeActivity;", "Lcom/movieboxpro/android/base/BaseSimpleActivity;", "()V", "googleSignInClient", "Lcom/google/android/gms/auth/api/signin/GoogleSignInClient;", "cancelBind", "", "getLayoutResId", "", "initData", "initListener", "initView", "loginToPandaForum", "name", "", "email", Scopes.OPEN_ID, "avatar", "needFullscreen", "", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PandaForumAuthorizeActivity extends BaseSimpleActivity {
    public static final Companion Companion = new Companion(null);
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private GoogleSignInClient googleSignInClient;

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
    public int getLayoutResId() {
        return R.layout.activity_panda_forum_authorize;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    protected boolean needFullscreen() {
        return true;
    }

    /* compiled from: PandaForumAuthorizeActivity.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u000bJ\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\u0007\u001a\u00020\b¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/PandaForumAuthorizeActivity$Companion;", "", "()V", TtmlNode.START, "", "context", "Landroid/app/Activity;", NotificationCompat.CATEGORY_STATUS, "", "requestCode", "", "Landroid/content/Context;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void start(Activity activity, int i) {
            start$default(this, activity, false, i, 2, null);
        }

        private Companion() {
        }

        public static /* synthetic */ void start$default(Companion companion, Activity activity, boolean z, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                z = false;
            }
            companion.start(activity, z, i);
        }

        public final void start(Activity activity, boolean z, int i) {
            Intent intent = new Intent(activity, PandaForumAuthorizeActivity.class);
            intent.putExtra(NotificationCompat.CATEGORY_STATUS, z);
            if (activity == null) {
                return;
            }
            activity.startActivityForResult(intent, i);
        }

        public static /* synthetic */ void start$default(Companion companion, Context context, boolean z, int i, Object obj) {
            if ((i & 2) != 0) {
                z = false;
            }
            companion.start(context, z);
        }

        public final void start(Context context, boolean z) {
            Intent intent = new Intent(context, PandaForumAuthorizeActivity.class);
            intent.putExtra(NotificationCompat.CATEGORY_STATUS, z);
            if (context == null) {
                return;
            }
            context.startActivity(intent);
        }

        public final void start(Context context) {
            Intent intent = new Intent(context, PandaForumAuthorizeActivity.class);
            if (context == null) {
                return;
            }
            context.startActivity(intent);
        }
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initListener() {
        ((ImageView) _$_findCachedViewById(R.id.ivBack)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$PandaForumAuthorizeActivity$o5YhFiWK1ldP9BPbFXXxK0Kdkrk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PandaForumAuthorizeActivity.m589initListener$lambda0(PandaForumAuthorizeActivity.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivLogout)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$PandaForumAuthorizeActivity$fAvW5tr7aqXlwxcBSprhRdbDfRQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PandaForumAuthorizeActivity.m590initListener$lambda2(PandaForumAuthorizeActivity.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tvSignIn)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$PandaForumAuthorizeActivity$3vEXTzpZNDaVoRRunmD8gka7jNI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PandaForumAuthorizeActivity.m592initListener$lambda3(PandaForumAuthorizeActivity.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tvPandaForum)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$PandaForumAuthorizeActivity$dbdFy09CXNn2brHgYZpsofkFT10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PandaForumAuthorizeActivity.m593initListener$lambda4(PandaForumAuthorizeActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m589initListener$lambda0(PandaForumAuthorizeActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m590initListener$lambda2(final PandaForumAuthorizeActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        new MsgHintDialog.Builder(this$0).setTitle("Note").setContent("Are you sure to cancel bind?").setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$PandaForumAuthorizeActivity$pl_W3QkbgMuqGq5FW-IQfSraUds
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public final void onClick() {
                PandaForumAuthorizeActivity.m591initListener$lambda2$lambda1(PandaForumAuthorizeActivity.this);
            }
        }).create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2$lambda-1  reason: not valid java name */
    public static final void m591initListener$lambda2$lambda1(PandaForumAuthorizeActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.cancelBind();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-3  reason: not valid java name */
    public static final void m592initListener$lambda3(PandaForumAuthorizeActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showLoadingView();
        GoogleSignInClient googleSignInClient = this$0.googleSignInClient;
        if (googleSignInClient == null) {
            Intrinsics.throwUninitializedPropertyAccessException("googleSignInClient");
            googleSignInClient = null;
        }
        Intent signInIntent = googleSignInClient.getSignInIntent();
        Intrinsics.checkNotNullExpressionValue(signInIntent, "googleSignInClient.signInIntent");
        this$0.startActivityForResult(signInIntent, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-4  reason: not valid java name */
    public static final void m593initListener$lambda4(PandaForumAuthorizeActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SystemUtils.startBrowser((Activity) this$0, Constant.PANDA_BBS_URL);
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initData() {
        ((TextView) _$_findCachedViewById(R.id.tvConnectedHint)).setText("The Best Forum of Movie & TV Show");
        SpanUtils.with((TextView) _$_findCachedViewById(R.id.tvPandaForum)).append("PandaForum").setFontSize(12, true).setForegroundColor(CommonExtKt.colorInt((Context) this, (int) R.color.color_main_blue)).setUnderline().create();
        if (getIntent().getBooleanExtra(NotificationCompat.CATEGORY_STATUS, false)) {
            ((TextView) _$_findCachedViewById(R.id.tvTitle)).setText("You Have Connected");
            ((TextView) _$_findCachedViewById(R.id.tvHint)).setText("You will not be able to post\n or reply after you cancel the\n connection.");
            TextView tvSignIn = (TextView) _$_findCachedViewById(R.id.tvSignIn);
            Intrinsics.checkNotNullExpressionValue(tvSignIn, "tvSignIn");
            CommonExtKt.gone(tvSignIn);
            FrameLayout frameLayout = (FrameLayout) _$_findCachedViewById(R.id.frameLayout);
            Intrinsics.checkNotNullExpressionValue(frameLayout, "frameLayout");
            CommonExtKt.visible(frameLayout);
            LinearLayout llNotConnectHint = (LinearLayout) _$_findCachedViewById(R.id.llNotConnectHint);
            Intrinsics.checkNotNullExpressionValue(llNotConnectHint, "llNotConnectHint");
            CommonExtKt.gone(llNotConnectHint);
            ImageView ivLogout = (ImageView) _$_findCachedViewById(R.id.ivLogout);
            Intrinsics.checkNotNullExpressionValue(ivLogout, "ivLogout");
            CommonExtKt.visible(ivLogout);
            ((TextView) _$_findCachedViewById(R.id.tvNickname)).setText(App.getBBsInfo().getAuthor());
            ((TextView) _$_findCachedViewById(R.id.tvBbsId)).setText(Intrinsics.stringPlus("UID ", App.getBBsInfo().getBbs_uid()));
            GlideUtils.load((Activity) this, App.getBBsInfo().getAvatar(), (ImageView) ((CircleImageView) _$_findCachedViewById(R.id.ivAvatar)), (int) R.mipmap.ic_panda_forum_default_avatar);
            ((TextView) _$_findCachedViewById(R.id.tvTime)).setText(TimeUtils.formatTime3(App.getBBsInfo().getAdd_time() * 1000));
            return;
        }
        ImageView ivLogout2 = (ImageView) _$_findCachedViewById(R.id.ivLogout);
        Intrinsics.checkNotNullExpressionValue(ivLogout2, "ivLogout");
        CommonExtKt.gone(ivLogout2);
        FrameLayout frameLayout2 = (FrameLayout) _$_findCachedViewById(R.id.frameLayout);
        Intrinsics.checkNotNullExpressionValue(frameLayout2, "frameLayout");
        CommonExtKt.gone(frameLayout2);
        LinearLayout llNotConnectHint2 = (LinearLayout) _$_findCachedViewById(R.id.llNotConnectHint);
        Intrinsics.checkNotNullExpressionValue(llNotConnectHint2, "llNotConnectHint");
        CommonExtKt.visible(llNotConnectHint2);
        GoogleSignInOptions build = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder(GoogleSignInOpti…                 .build()");
        GoogleSignInClient client = GoogleSignIn.getClient((Activity) this, build);
        Intrinsics.checkNotNullExpressionValue(client, "getClient(this, gso)");
        this.googleSignInClient = client;
    }

    private final void cancelBind() {
        RxSubscribersKt.transformMsg(Http.getService().Unbind_bbs_user(API.BASE_URL, "Unbind_bbs_user", App.getUserData().uid_v2, App.getBBsInfo().getBbs_uid()), this).subscribe(new Consumer() { // from class: com.movieboxpro.android.view.activity.review.PandaForumAuthorizeActivity$cancelBind$$inlined$subscribeToMsg$default$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(T it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                String str = (String) it;
                PandaForumAuthorizeActivity.this.hideLoadingView();
                UserModel.UserData userData = App.getUserData();
                Intrinsics.checkNotNullExpressionValue(userData, "getUserData()");
                userData.setBbs_bind(null);
                App.updateUser(userData);
                PandaForumAuthorizeActivity.this.setResult(-1);
                PandaForumAuthorizeActivity.this.finish();
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.PandaForumAuthorizeActivity$cancelBind$$inlined$subscribeToMsg$default$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                ApiException handleException = ApiException.handleException(th);
                Intrinsics.checkNotNullExpressionValue(handleException, "handleException(it)");
                PandaForumAuthorizeActivity.this.hideLoadingView();
                ToastUtils.showShort(Intrinsics.stringPlus("Unbind failed:", handleException.getMessage()), new Object[0]);
                if (th instanceof ServerException) {
                    ServerException serverException = (ServerException) th;
                }
            }
        }, new Action() { // from class: com.movieboxpro.android.view.activity.review.PandaForumAuthorizeActivity$cancelBind$$inlined$subscribeToMsg$default$3
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.PandaForumAuthorizeActivity$cancelBind$$inlined$subscribeToMsg$default$4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                PandaForumAuthorizeActivity.this.showLoadingView();
            }
        });
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initView() {
        DensityUtils.addMarginTopEqualStatusBarHeight((ImageView) _$_findCachedViewById(R.id.ivBack), this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1) {
            try {
                GoogleSignInAccount result = GoogleSignIn.getSignedInAccountFromIntent(intent).getResult(com.google.android.gms.common.api.ApiException.class);
                Uri uri = null;
                String displayName = result == null ? null : result.getDisplayName();
                String email = result == null ? null : result.getEmail();
                String id = result == null ? null : result.getId();
                if (result != null) {
                    uri = result.getPhotoUrl();
                }
                loginToPandaForum(displayName, email, id, String.valueOf(uri));
            } catch (com.google.android.gms.common.api.ApiException e) {
                String statusCodeString = GoogleSignInStatusCodes.getStatusCodeString(e.getStatusCode());
                Intrinsics.checkNotNullExpressionValue(statusCodeString, "getStatusCodeString(e.statusCode)");
                ToastUtils.showShort(statusCodeString, new Object[0]);
                hideLoadingView();
            }
        }
    }

    private final void loginToPandaForum(String str, String str2, String str3, String str4) {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        if (Intrinsics.areEqual(str4, "null")) {
            str4 = App.getUserData().avatar;
        }
        ((ObservableSubscribeProxy) Http.getService().loginToPandaForum(API.BBS_URL, "google_login", str, str2, str3, App.getUserData().uid_v2, str4).compose(RxUtils.rxTranslate2Bean(BBsInfoModel.class)).flatMap(new Function() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$PandaForumAuthorizeActivity$WQJMNhK8KGKsG0QTLckDJRZhvoo
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                ObservableSource m595loginToPandaForum$lambda8;
                m595loginToPandaForum$lambda8 = PandaForumAuthorizeActivity.m595loginToPandaForum$lambda8(Ref.ObjectRef.this, (BBsInfoModel) obj);
                return m595loginToPandaForum$lambda8;
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<String>() { // from class: com.movieboxpro.android.view.activity.review.PandaForumAuthorizeActivity$loginToPandaForum$2
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(String model) {
                BBsInfoModel.BBsInfo loginInfo;
                BBsInfoModel.Member member;
                BBsInfoModel.Member member2;
                Intrinsics.checkNotNullParameter(model, "model");
                PandaForumAuthorizeActivity.this.hideLoadingView();
                UserModel.BBsInfo bBsInfo = new UserModel.BBsInfo();
                BBsInfoModel bBsInfoModel = objectRef.element;
                String str5 = null;
                BBsInfoModel.Variables variables = (bBsInfoModel == null || (loginInfo = bBsInfoModel.getLoginInfo()) == null) ? null : loginInfo.getVariables();
                bBsInfo.setAuth(variables == null ? null : variables.getAuth());
                bBsInfo.setAuthkey(variables == null ? null : variables.getAuthkey());
                bBsInfo.setBbs_uid(variables == null ? null : variables.getMember_uid());
                bBsInfo.setFormhash(variables == null ? null : variables.getFormhash());
                bBsInfo.setUid(App.getUserData().uid_v2);
                BBsInfoModel bBsInfoModel2 = objectRef.element;
                bBsInfo.setAuthor((bBsInfoModel2 == null || (member = bBsInfoModel2.getMember()) == null) ? null : member.getUsername());
                BBsInfoModel bBsInfoModel3 = objectRef.element;
                if (bBsInfoModel3 != null && (member2 = bBsInfoModel3.getMember()) != null) {
                    str5 = member2.getAvatar();
                }
                bBsInfo.setAvatar(str5);
                App.updateUser(bBsInfo);
                ToastUtils.showShort("Authorize successfully", new Object[0]);
                PandaForumAuthorizeActivity.this.setResult(-1);
                PandaForumAuthorizeActivity.this.finish();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                Intrinsics.checkNotNullParameter(e, "e");
                PandaForumAuthorizeActivity.this.hideLoadingView();
                String message = e.getMessage();
                if (message == null) {
                    message = "";
                }
                ToastUtils.showShort(Intrinsics.stringPlus("Authorize failed:", message), new Object[0]);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: loginToPandaForum$lambda-8  reason: not valid java name */
    public static final ObservableSource m595loginToPandaForum$lambda8(Ref.ObjectRef bbsInfoModel, BBsInfoModel it) {
        Intrinsics.checkNotNullParameter(bbsInfoModel, "$bbsInfoModel");
        Intrinsics.checkNotNullParameter(it, "it");
        bbsInfoModel.element = it;
        return Http.getService().Bind_bbs_user(API.BASE_URL, "Bind_bbs_user", App.getUserData().uid_v2, it.getLoginInfo().getVariables().getMember_uid(), it.getLoginInfo().getVariables().getAuth(), it.getLoginInfo().getVariables().getAuthkey(), it.getLoginInfo().getVariables().getFormhash()).compose(RxUtils.rxTranslateMsg());
    }
}
