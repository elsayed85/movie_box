package com.movieboxpro.android.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.R;
import com.movieboxpro.android.utils.CommonExtKt;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: WebViewBottomSheetFragment.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u0012\u0010\u000b\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J&\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u001a\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\r2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\u0015"}, d2 = {"Lcom/movieboxpro/android/view/dialog/WebViewBottomSheetFragment;", "Landroidx/fragment/app/DialogFragment;", "()V", "getContextRect", "", "activity", "Landroid/app/Activity;", "onActivityCreated", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreate", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "onViewCreated", "view", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WebViewBottomSheetFragment extends DialogFragment {
    public static final Companion Companion = new Companion(null);
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_webview_bottom_sheet, viewGroup, false);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(0, R.style.BottomSheetFullScreenDialog);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        Dialog dialog = getDialog();
        Window window = dialog == null ? null : dialog.getWindow();
        if (window != null) {
            window.requestFeature(1);
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams attributes = window.getAttributes();
            Context context = getContext();
            Intrinsics.checkNotNull(context);
            window.setBackgroundDrawable(ContextCompat.getDrawable(context, 17170445));
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity);
            Intrinsics.checkNotNullExpressionValue(activity, "activity!!");
            int contextRect = getContextRect(activity) - CommonExtKt.dp2Px(70);
            if (contextRect == 0) {
                contextRect = -1;
            }
            window.setLayout(-1, contextRect);
            attributes.dimAmount = 0.5f;
            attributes.gravity = 80;
            window.setAttributes(attributes);
        }
        super.onActivityCreated(bundle);
    }

    private final int getContextRect(Activity activity) {
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.height();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        ((WebView) _$_findCachedViewById(R.id.webView)).setBackgroundColor(0);
        ((WebView) _$_findCachedViewById(R.id.webView)).getBackground().setAlpha(0);
        Bundle arguments = getArguments();
        String string = arguments == null ? null : arguments.getString("url");
        WebView webView = (WebView) _$_findCachedViewById(R.id.webView);
        if (string == null) {
            string = "";
        }
        webView.loadUrl(string);
        ((WebView) _$_findCachedViewById(R.id.webView)).getSettings().setSupportZoom(true);
        ((ImageView) _$_findCachedViewById(R.id.ivClose)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$WebViewBottomSheetFragment$zi62ETBfr1GsQGFgVplgj6YOLOs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                WebViewBottomSheetFragment.m1138onViewCreated$lambda0(WebViewBottomSheetFragment.this, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onViewCreated$lambda-0  reason: not valid java name */
    public static final void m1138onViewCreated$lambda0(WebViewBottomSheetFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    /* compiled from: WebViewBottomSheetFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/view/dialog/WebViewBottomSheetFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/WebViewBottomSheetFragment;", "url", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final WebViewBottomSheetFragment newInstance(String str) {
            WebViewBottomSheetFragment webViewBottomSheetFragment = new WebViewBottomSheetFragment();
            Bundle bundle = new Bundle();
            bundle.putString("url", str);
            webViewBottomSheetFragment.setArguments(bundle);
            return webViewBottomSheetFragment;
        }
    }
}
