package com.movieboxpro.android.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.R;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.view.dialog.DialogAction;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: LoadingStatusView.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u0012\u001a\u00020\u0013J\u0010\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u000e\u0010\u0017\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\u001b\u001a\u00020\u0013R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/movieboxpro/android/view/widget/LoadingStatusView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/DialogAction$ActionListener;", "llRetry", "Landroid/widget/LinearLayout;", "progressView", "Landroid/widget/ProgressBar;", "tvErrorMsg", "Landroid/widget/TextView;", "tvRetry", "hideLoading", "", "initView", "view", "Landroid/view/View;", "setListener", "showError", NotificationCompat.CATEGORY_MESSAGE, "", "showLoading", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class LoadingStatusView extends FrameLayout {
    public Map<Integer, View> _$_findViewCache;
    private DialogAction.ActionListener listener;
    private LinearLayout llRetry;
    private ProgressBar progressView;
    private TextView tvErrorMsg;
    private TextView tvRetry;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LoadingStatusView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LoadingStatusView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

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

    public /* synthetic */ LoadingStatusView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoadingStatusView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
        View view = LayoutInflater.from(context).inflate(R.layout.loading_status_view, this);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        initView(view);
    }

    public final void setListener(DialogAction.ActionListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    private final void initView(View view) {
        View findViewById = view.findViewById(R.id.progressBar);
        Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById(R.id.progressBar)");
        this.progressView = (ProgressBar) findViewById;
        View findViewById2 = view.findViewById(R.id.llRetry);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "view.findViewById(R.id.llRetry)");
        this.llRetry = (LinearLayout) findViewById2;
        View findViewById3 = view.findViewById(R.id.tvErrorMsg);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "view.findViewById(R.id.tvErrorMsg)");
        this.tvErrorMsg = (TextView) findViewById3;
        View findViewById4 = view.findViewById(R.id.tvRetry);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "view.findViewById(R.id.tvRetry)");
        TextView textView = (TextView) findViewById4;
        this.tvRetry = textView;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvRetry");
            textView = null;
        }
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.widget.-$$Lambda$LoadingStatusView$N0quJle4zGYmndNrv1Xn3i_4h0U
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                LoadingStatusView.m1430initView$lambda0(LoadingStatusView.this, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-0  reason: not valid java name */
    public static final void m1430initView$lambda0(LoadingStatusView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DialogAction.ActionListener actionListener = this$0.listener;
        if (actionListener == null) {
            return;
        }
        actionListener.onClick();
    }

    public final void hideLoading() {
        setVisibility(8);
    }

    public final void showLoading() {
        setVisibility(0);
        ProgressBar progressBar = this.progressView;
        LinearLayout linearLayout = null;
        if (progressBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressView");
            progressBar = null;
        }
        CommonExtKt.visible(progressBar);
        LinearLayout linearLayout2 = this.llRetry;
        if (linearLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("llRetry");
        } else {
            linearLayout = linearLayout2;
        }
        CommonExtKt.gone(linearLayout);
    }

    public final void showError(String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        setVisibility(0);
        ProgressBar progressBar = this.progressView;
        LinearLayout linearLayout = null;
        if (progressBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressView");
            progressBar = null;
        }
        CommonExtKt.gone(progressBar);
        TextView textView = this.tvErrorMsg;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvErrorMsg");
            textView = null;
        }
        textView.setText(msg);
        LinearLayout linearLayout2 = this.llRetry;
        if (linearLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("llRetry");
        } else {
            linearLayout = linearLayout2;
        }
        CommonExtKt.visible(linearLayout);
    }
}
