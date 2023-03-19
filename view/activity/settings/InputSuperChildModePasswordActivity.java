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
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.view.widget.LockPatternView;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: InputSuperChildModePasswordActivity.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\f\u001a\u00020\nH\u0016¨\u0006\u000e"}, d2 = {"Lcom/movieboxpro/android/view/activity/settings/InputSuperChildModePasswordActivity;", "Lcom/movieboxpro/android/base/BaseSimpleActivity;", "()V", "getLayoutResId", "", "getPasswordString", "", "numbers", "", "initData", "", "initListener", "initView", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class InputSuperChildModePasswordActivity extends BaseSimpleActivity {
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
    public int getLayoutResId() {
        return R.layout.activity_input_child_mode_password;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initData() {
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initListener() {
        ((LinearLayout) _$_findCachedViewById(R.id.ll_back)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$InputSuperChildModePasswordActivity$f7QXSauZL49LpzHdRAcQmPvhPWk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InputSuperChildModePasswordActivity.m783initListener$lambda0(InputSuperChildModePasswordActivity.this, view);
            }
        });
        ((LockPatternView) _$_findCachedViewById(R.id.lockView)).setGestureCallback(new LockPatternView.GestureCallback() { // from class: com.movieboxpro.android.view.activity.settings.InputSuperChildModePasswordActivity$initListener$2
            @Override // com.movieboxpro.android.view.widget.LockPatternView.GestureCallback
            public void onNodeConnected(int[] numbers) {
                Intrinsics.checkNotNullParameter(numbers, "numbers");
                if (((TextView) InputSuperChildModePasswordActivity.this._$_findCachedViewById(R.id.tvWrongHint)).getVisibility() == 0) {
                    TextView tvWrongHint = (TextView) InputSuperChildModePasswordActivity.this._$_findCachedViewById(R.id.tvWrongHint);
                    Intrinsics.checkNotNullExpressionValue(tvWrongHint, "tvWrongHint");
                    CommonExtKt.gone(tvWrongHint);
                }
            }

            @Override // com.movieboxpro.android.view.widget.LockPatternView.GestureCallback
            public void onGestureFinished(int[] numbers) {
                String passwordString;
                Intrinsics.checkNotNullParameter(numbers, "numbers");
                if (numbers.length == 1) {
                    return;
                }
                String string = PrefsUtils.getInstance().getString(Constant.Prefs.SUPER_CHILD_MODE_PASSWORD);
                passwordString = InputSuperChildModePasswordActivity.this.getPasswordString(numbers);
                if (Intrinsics.areEqual(string, passwordString)) {
                    InputSuperChildModePasswordActivity.this.setResult(-1);
                    InputSuperChildModePasswordActivity.this.finish();
                    return;
                }
                ((LockPatternView) InputSuperChildModePasswordActivity.this._$_findCachedViewById(R.id.lockView)).setErrorStatus(ContextCompat.getDrawable(InputSuperChildModePasswordActivity.this, R.drawable.node_active_error), Color.parseColor("#9A3535"));
                TextView tvWrongHint = (TextView) InputSuperChildModePasswordActivity.this._$_findCachedViewById(R.id.tvWrongHint);
                Intrinsics.checkNotNullExpressionValue(tvWrongHint, "tvWrongHint");
                CommonExtKt.visible(tvWrongHint);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m783initListener$lambda0(InputSuperChildModePasswordActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initView() {
        CommonExtKt.initImmersionBar$default(this, 0, 1, null);
        ((TextView) _$_findCachedViewById(R.id.tv_title)).setText("Child Mode");
    }

    /* compiled from: InputSuperChildModePasswordActivity.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/view/activity/settings/InputSuperChildModePasswordActivity$Companion;", "", "()V", TtmlNode.START, "", "activity", "Landroid/app/Activity;", "code", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Activity activity, int i) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            activity.startActivityForResult(new Intent(activity, InputSuperChildModePasswordActivity.class), i);
            activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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
