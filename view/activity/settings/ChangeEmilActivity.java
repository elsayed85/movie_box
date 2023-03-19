package com.movieboxpro.android.view.activity.settings;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.mvp.BaseMvpActivity;
import com.movieboxpro.android.event.OnChangeNameEmailEvent;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.settings.ChangeEmilContract;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;
/* compiled from: ChangeEmilActivity.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0002H\u0014J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\tH\u0014J\b\u0010\n\u001a\u00020\u0007H\u0014J\b\u0010\u000b\u001a\u00020\u0007H\u0014J\b\u0010\f\u001a\u00020\u0007H\u0014J\b\u0010\r\u001a\u00020\u000eH\u0014J\b\u0010\u000f\u001a\u00020\u0007H\u0014¨\u0006\u0011"}, d2 = {"Lcom/movieboxpro/android/view/activity/settings/ChangeEmilActivity;", "Lcom/movieboxpro/android/base/mvp/BaseMvpActivity;", "Lcom/movieboxpro/android/view/activity/settings/ChangeEmilPresenter;", "Lcom/movieboxpro/android/view/activity/settings/ChangeEmilContract$View;", "()V", "bindPresenter", "changeEmailSuccess", "", "getLayoutResId", "", "initData", "initListener", "initView", "isNeedLoadData", "", "requestData", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ChangeEmilActivity extends BaseMvpActivity<ChangeEmilPresenter> implements ChangeEmilContract.View {
    public static final Companion Companion = new Companion(null);
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

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
        return R.layout.activity_change_emil;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initData() {
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
        ((TextView) _$_findCachedViewById(R.id.tv_title)).setText("Modify Email");
        TextView tv_right = (TextView) _$_findCachedViewById(R.id.tv_right);
        Intrinsics.checkNotNullExpressionValue(tv_right, "tv_right");
        CommonExtKt.visible(tv_right);
        ((TextView) _$_findCachedViewById(R.id.tv_right)).setText("Commit");
        List<UserModel.Bind> bind = App.getUserData().getBind();
        if (bind != null && (!bind.isEmpty())) {
            ((TextView) _$_findCachedViewById(R.id.tvGoogleEmail)).setText(bind.get(0).email);
        }
        ((EditText) _$_findCachedViewById(R.id.etEmail)).setText(App.getUserData().email);
        ((EditText) _$_findCachedViewById(R.id.etEmail)).setSelection(((EditText) _$_findCachedViewById(R.id.etEmail)).getText().length());
        ((EditText) _$_findCachedViewById(R.id.etEmail)).requestFocus();
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initListener() {
        ((LinearLayout) _$_findCachedViewById(R.id.ll_back)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$ChangeEmilActivity$9M6B8bJWNAUtxyjt6tXhPBtaAwU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChangeEmilActivity.m770initListener$lambda0(ChangeEmilActivity.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.ll_right)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$ChangeEmilActivity$MDbZEfTI8_RTBGgpEjOJFyJm1JY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChangeEmilActivity.m771initListener$lambda1(ChangeEmilActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m770initListener$lambda0(ChangeEmilActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m771initListener$lambda1(ChangeEmilActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String obj = ((EditText) this$0._$_findCachedViewById(R.id.etEmail)).getText().toString();
        if (CommonUtils.isEmail(obj)) {
            ((ChangeEmilPresenter) this$0.mPresenter).changeEmail(obj);
        } else {
            ToastUtils.showShort("Invalid email", new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    public ChangeEmilPresenter bindPresenter() {
        return new ChangeEmilPresenter(this);
    }

    /* compiled from: ChangeEmilActivity.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/view/activity/settings/ChangeEmilActivity$Companion;", "", "()V", TtmlNode.START, "", "context", "Landroid/content/Context;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context) {
            Intent intent = new Intent(context, ChangeEmilActivity.class);
            if (context == null) {
                return;
            }
            context.startActivity(intent);
        }
    }

    @Override // com.movieboxpro.android.view.activity.settings.ChangeEmilContract.View
    public void changeEmailSuccess() {
        App.updateUser(((EditText) _$_findCachedViewById(R.id.etEmail)).getText().toString(), 0);
        EventBus.getDefault().post(new OnChangeNameEmailEvent());
        ToastUtils.showShort("Modify successfully", new Object[0]);
        finish();
    }
}
