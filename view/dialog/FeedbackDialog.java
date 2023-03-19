package com.movieboxpro.android.view.dialog;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialog;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.R;
import com.movieboxpro.android.utils.ToastUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
/* compiled from: FeedbackDialog.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0012B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\b\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\fH\u0002J\u0012\u0010\u000e\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\u000e\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/movieboxpro/android/view/dialog/FeedbackDialog;", "Landroidx/appcompat/app/AppCompatDialog;", "activity", "Landroid/app/Activity;", "hint", "", "(Landroid/app/Activity;Ljava/lang/String;)V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/FeedbackDialog$OnMessageListener;", "getContextRect", "", "initDialog", "", "initView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setMessageListener", "OnMessageListener", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FeedbackDialog extends AppCompatDialog {
    private final Activity activity;
    private final String hint;
    private OnMessageListener listener;

    /* compiled from: FeedbackDialog.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/movieboxpro/android/view/dialog/FeedbackDialog$OnMessageListener;", "", "onMessage", "", "message", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface OnMessageListener {
        void onMessage(String str);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FeedbackDialog(Activity activity, String hint) {
        super(activity, R.style.BottomSheetFullScreenDialog);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(hint, "hint");
        this.activity = activity;
        this.hint = hint;
    }

    public /* synthetic */ FeedbackDialog(Activity activity, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(activity, (i & 2) != 0 ? "" : str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initDialog();
        initView();
    }

    public final void setMessageListener(OnMessageListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    private final void initView() {
        setContentView(LayoutInflater.from(getContext()).inflate(R.layout.activity_custom_feedback, (ViewGroup) null));
        if (!StringsKt.isBlank(this.hint)) {
            ((TextView) findViewById(R.id.tvHint)).setText(this.hint);
        }
        ((TextView) findViewById(R.id.tvCancel)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$FeedbackDialog$VWbWH9q220J0PgNYIHcCUzanCPk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FeedbackDialog.m973initView$lambda0(FeedbackDialog.this, view);
            }
        });
        ((TextView) findViewById(R.id.tvSend)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$FeedbackDialog$hjizhLrxZln3SaexTVvx-7H5GkM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FeedbackDialog.m974initView$lambda1(FeedbackDialog.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-0  reason: not valid java name */
    public static final void m973initView$lambda0(FeedbackDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-1  reason: not valid java name */
    public static final void m974initView$lambda1(FeedbackDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (((EditText) this$0.findViewById(R.id.editText)).getText().toString().length() == 0) {
            ToastUtils.showShort("empty problem", new Object[0]);
            return;
        }
        OnMessageListener onMessageListener = this$0.listener;
        if (onMessageListener != null) {
            onMessageListener.onMessage(((EditText) this$0.findViewById(R.id.editText)).getText().toString());
        }
        this$0.dismiss();
    }

    private final void initDialog() {
        View decorView;
        Window window = getWindow();
        if (window != null) {
            window.requestFeature(1);
        }
        Window window2 = getWindow();
        if (window2 != null && (decorView = window2.getDecorView()) != null) {
            decorView.setPadding(0, 0, 0, 0);
        }
        Window window3 = getWindow();
        WindowManager.LayoutParams attributes = window3 == null ? null : window3.getAttributes();
        Window window4 = getWindow();
        if (window4 != null) {
            window4.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), 17170445));
        }
        int contextRect = getContextRect(this.activity);
        Window window5 = getWindow();
        if (window5 != null) {
            if (contextRect == 0) {
                contextRect = -1;
            }
            window5.setLayout(-1, contextRect);
        }
        if (attributes != null) {
            attributes.dimAmount = 0.0f;
        }
        if (attributes != null) {
            attributes.gravity = 80;
        }
        Window window6 = getWindow();
        if (window6 == null) {
            return;
        }
        window6.setAttributes(attributes);
    }

    private final int getContextRect(Activity activity) {
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.height();
    }
}
