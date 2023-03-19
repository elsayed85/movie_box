package com.movieboxpro.android.view.activity.settings;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseSimpleActivity;
import com.movieboxpro.android.event.SuperChildModeChangedEvent;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.view.widget.LockPatternView;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;
/* compiled from: SuperChildModePasswordActivity.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0002J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\u000bH\u0016J\b\u0010\r\u001a\u00020\u000bH\u0016J\b\u0010\u000e\u001a\u00020\u000bH\u0002J\b\u0010\u000f\u001a\u00020\u000bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/movieboxpro/android/view/activity/settings/SuperChildModePasswordActivity;", "Lcom/movieboxpro/android/base/BaseSimpleActivity;", "()V", "password", "", "getLayoutResId", "", "getPasswordString", "numbers", "", "initData", "", "initListener", "initView", "setActionInvisible", "setActionVisible", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SuperChildModePasswordActivity extends BaseSimpleActivity {
    public static final Companion Companion = new Companion(null);
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String password = "";

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
        return R.layout.activity_child_mode_password;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initData() {
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initListener() {
        ((LinearLayout) _$_findCachedViewById(R.id.ll_back)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$SuperChildModePasswordActivity$gzt-rKMApaDUfKfgTmXcnxJmuBo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SuperChildModePasswordActivity.m803initListener$lambda0(SuperChildModePasswordActivity.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tvConfirm)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$SuperChildModePasswordActivity$SGOuuAqjueTpiWvxD0LPNAra358
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SuperChildModePasswordActivity.m804initListener$lambda1(SuperChildModePasswordActivity.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tvReset)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$SuperChildModePasswordActivity$YH91CCTLzoTuPa3LYMeGoiUqgNM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SuperChildModePasswordActivity.m805initListener$lambda2(SuperChildModePasswordActivity.this, view);
            }
        });
        ((LockPatternView) _$_findCachedViewById(R.id.lockView)).setGestureCallback(new LockPatternView.GestureCallback() { // from class: com.movieboxpro.android.view.activity.settings.SuperChildModePasswordActivity$initListener$4
            @Override // com.movieboxpro.android.view.widget.LockPatternView.GestureCallback
            public void onNodeConnected(int[] numbers) {
                Intrinsics.checkNotNullParameter(numbers, "numbers");
            }

            @Override // com.movieboxpro.android.view.widget.LockPatternView.GestureCallback
            public void onGestureFinished(int[] numbers) {
                String str;
                String str2;
                String passwordString;
                String passwordString2;
                Intrinsics.checkNotNullParameter(numbers, "numbers");
                if (numbers.length == 1) {
                    return;
                }
                str = SuperChildModePasswordActivity.this.password;
                if (str.length() == 0) {
                    SuperChildModePasswordActivity.this.setActionVisible();
                    ((TextView) SuperChildModePasswordActivity.this._$_findCachedViewById(R.id.tvConfirm)).setEnabled(false);
                    SuperChildModePasswordActivity superChildModePasswordActivity = SuperChildModePasswordActivity.this;
                    passwordString2 = superChildModePasswordActivity.getPasswordString(numbers);
                    superChildModePasswordActivity.password = passwordString2;
                    ((TextView) SuperChildModePasswordActivity.this._$_findCachedViewById(R.id.tvHint)).setText("Draw pattern again to confirm.");
                    return;
                }
                str2 = SuperChildModePasswordActivity.this.password;
                passwordString = SuperChildModePasswordActivity.this.getPasswordString(numbers);
                if (!Intrinsics.areEqual(str2, passwordString)) {
                    ((LockPatternView) SuperChildModePasswordActivity.this._$_findCachedViewById(R.id.lockView)).setErrorStatus(ContextCompat.getDrawable(SuperChildModePasswordActivity.this, R.drawable.node_active_error), Color.parseColor("#9A3535"));
                    return;
                }
                ((LockPatternView) SuperChildModePasswordActivity.this._$_findCachedViewById(R.id.lockView)).setFinishGone(false);
                ((TextView) SuperChildModePasswordActivity.this._$_findCachedViewById(R.id.tvConfirm)).setEnabled(true);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m803initListener$lambda0(SuperChildModePasswordActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m804initListener$lambda1(SuperChildModePasswordActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PrefsUtils.getInstance().putString(Constant.Prefs.SUPER_CHILD_MODE_PASSWORD, this$0.password);
        PrefsUtils.getInstance().putBoolean(Constant.Prefs.SUPER_CHILD_MODE, true);
        this$0.setResult(-1);
        EventBus.getDefault().post(new SuperChildModeChangedEvent(true));
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m805initListener$lambda2(SuperChildModePasswordActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setActionInvisible();
        ((LockPatternView) this$0._$_findCachedViewById(R.id.lockView)).setFinishGone(true);
        this$0.password = "";
        ((TextView) this$0._$_findCachedViewById(R.id.tvHint)).setText("Please draw a pattern.");
        ((LockPatternView) this$0._$_findCachedViewById(R.id.lockView)).resetNodes();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setActionVisible() {
        LinearLayout llAction = (LinearLayout) _$_findCachedViewById(R.id.llAction);
        Intrinsics.checkNotNullExpressionValue(llAction, "llAction");
        CommonExtKt.visible(llAction);
        View viewLine = _$_findCachedViewById(R.id.viewLine);
        Intrinsics.checkNotNullExpressionValue(viewLine, "viewLine");
        CommonExtKt.visible(viewLine);
    }

    private final void setActionInvisible() {
        LinearLayout llAction = (LinearLayout) _$_findCachedViewById(R.id.llAction);
        Intrinsics.checkNotNullExpressionValue(llAction, "llAction");
        CommonExtKt.invisible(llAction);
        View viewLine = _$_findCachedViewById(R.id.viewLine);
        Intrinsics.checkNotNullExpressionValue(viewLine, "viewLine");
        CommonExtKt.invisible(viewLine);
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initView() {
        ((TextView) _$_findCachedViewById(R.id.tv_title)).setText("Super Child Mode");
    }

    /* compiled from: SuperChildModePasswordActivity.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/view/activity/settings/SuperChildModePasswordActivity$Companion;", "", "()V", TtmlNode.START, "", "context", "Landroid/app/Activity;", "requestCode", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Activity context, int i) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivityForResult(new Intent(context, SuperChildModePasswordActivity.class), i);
            context.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getPasswordString(int[] iArr) {
        int length = iArr.length;
        String str = "";
        int i = 0;
        while (i < length) {
            int i2 = iArr[i];
            i++;
            str = Intrinsics.stringPlus(str, Integer.valueOf(i2));
        }
        return str;
    }
}
