package com.movieboxpro.android.view.dialog;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.FileProvider;
import com.movieboxpro.android.R;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.ToastUtils;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
/* compiled from: ChooseCastAppDialog.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\tH\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\tH\u0016J\b\u0010\u000e\u001a\u00020\tH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0014J\b\u0010\u0011\u001a\u00020\tH\u0016J\u0018\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0007H\u0002J\u0010\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0007H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChooseCastAppDialog;", "Lcom/movieboxpro/android/view/dialog/BaseCenterDialogFragment;", "()V", "isAllCastInstalled", "", "isCastifyInstalled", "url", "", "initData", "", "initInstallStatus", "initLayoutId", "", "initListener", "initView", "maxHeight", "", "onResume", "startGooglePlay", "context", "Landroid/content/Context;", "packageName", "startOpenFile", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ChooseCastAppDialog extends BaseCenterDialogFragment {
    public static final Companion Companion = new Companion(null);
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private boolean isAllCastInstalled;
    private boolean isCastifyInstalled;
    private String url;

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment
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

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment
    public int initLayoutId() {
        return R.layout.dialog_choose_cast_app;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment
    public void initView() {
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment
    protected float maxHeight() {
        return 0.0f;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment
    public void initListener() {
        ((LinearLayout) _$_findCachedViewById(R.id.llAllCast)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ChooseCastAppDialog$oTwa5h85OQdBAZj9Jz67VGYC0G4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChooseCastAppDialog.m937initListener$lambda0(ChooseCastAppDialog.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.llCastify)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ChooseCastAppDialog$_ok-6hWoiwKyMes9plY-XIxxOC0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChooseCastAppDialog.m938initListener$lambda1(ChooseCastAppDialog.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tvInstallAllCast)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ChooseCastAppDialog$5oOx7pneUfd77BxrELWTO_ujgto
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChooseCastAppDialog.m939initListener$lambda2(ChooseCastAppDialog.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tvInstallCastify)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ChooseCastAppDialog$ef2Ph75WC8K1zDu1UTKzPXzxPU0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChooseCastAppDialog.m940initListener$lambda3(ChooseCastAppDialog.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tvCast)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ChooseCastAppDialog$qmxqM7cWzhLY825qsU94gJS5ieg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChooseCastAppDialog.m941initListener$lambda4(ChooseCastAppDialog.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m937initListener$lambda0(ChooseCastAppDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isAllCastInstalled) {
            String str = this$0.url;
            if (str != null && StringsKt.startsWith$default(str, "http", false, 2, (Object) null)) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.SEND");
                intent.setPackage("com.koushikdutta.cast");
                String str2 = this$0.url;
                if (str2 == null) {
                    str2 = "";
                }
                intent.putExtra("android.intent.extra.TEXT", str2);
                intent.setType("text/plain");
                Context context = this$0.getContext();
                if (context != null) {
                    context.startActivity(Intent.createChooser(intent, "Share"));
                }
            } else {
                this$0.startOpenFile("com.koushikdutta.cast");
            }
            this$0.dismiss();
            return;
        }
        Context context2 = this$0.getContext();
        Intrinsics.checkNotNull(context2);
        Intrinsics.checkNotNullExpressionValue(context2, "context!!");
        this$0.startGooglePlay(context2, "com.koushikdutta.cast");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m938initListener$lambda1(ChooseCastAppDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isCastifyInstalled) {
            String str = this$0.url;
            if (str != null && StringsKt.startsWith$default(str, "http", false, 2, (Object) null)) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.SEND");
                intent.setPackage("com.castify");
                String str2 = this$0.url;
                if (str2 == null) {
                    str2 = "";
                }
                intent.putExtra("android.intent.extra.TEXT", str2);
                intent.setType("text/plain");
                Context context = this$0.getContext();
                if (context != null) {
                    context.startActivity(Intent.createChooser(intent, "Share"));
                }
            } else {
                this$0.startOpenFile("com.castify");
            }
            this$0.dismiss();
            return;
        }
        Context context2 = this$0.getContext();
        Intrinsics.checkNotNull(context2);
        Intrinsics.checkNotNullExpressionValue(context2, "context!!");
        this$0.startGooglePlay(context2, "com.castify");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m939initListener$lambda2(ChooseCastAppDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Context context = this$0.getContext();
        Intrinsics.checkNotNull(context);
        Intrinsics.checkNotNullExpressionValue(context, "context!!");
        this$0.startGooglePlay(context, "com.koushikdutta.cast");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-3  reason: not valid java name */
    public static final void m940initListener$lambda3(ChooseCastAppDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Context context = this$0.getContext();
        Intrinsics.checkNotNull(context);
        Intrinsics.checkNotNullExpressionValue(context, "context!!");
        this$0.startGooglePlay(context, "com.castify");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-4  reason: not valid java name */
    public static final void m941initListener$lambda4(ChooseCastAppDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.setAction("android.intent.action.VIEW");
        try {
            intent.setDataAndType(Uri.parse(this$0.url), "video/mp4");
            Context context = this$0.getContext();
            if (context != null) {
                context.startActivity(intent);
            }
            Intent.createChooser(intent, "Choose app to cast");
        } catch (Exception unused) {
            ToastUtils.showShort("No app available", new Object[0]);
        }
    }

    private final void startOpenFile(String str) {
        Uri fromFile;
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.setAction("android.intent.action.VIEW");
        if (Build.VERSION.SDK_INT >= 24) {
            Context context = getContext();
            Intrinsics.checkNotNull(context);
            fromFile = FileProvider.getUriForFile(context, "com.movieboxpro.android.fileProvider", new File(this.url));
            intent.addFlags(3);
        } else {
            fromFile = Uri.fromFile(new File(this.url));
        }
        intent.setDataAndType(fromFile, "video/*");
        try {
            Context context2 = getContext();
            Intrinsics.checkNotNull(context2);
            context2.startActivity(Intent.createChooser(intent, "Share"));
        } catch (Exception unused) {
            ToastUtils.showShort("No app can cast this video！", new Object[0]);
        }
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment
    public void initData() {
        Bundle arguments = getArguments();
        this.url = arguments == null ? null : arguments.getString("url");
    }

    private final void initInstallStatus() {
        if (CommonUtils.isAppInstalled("com.koushikdutta.cast")) {
            this.isAllCastInstalled = true;
            TextView tvInstallAllCast = (TextView) _$_findCachedViewById(R.id.tvInstallAllCast);
            Intrinsics.checkNotNullExpressionValue(tvInstallAllCast, "tvInstallAllCast");
            CommonExtKt.gone(tvInstallAllCast);
        } else {
            this.isAllCastInstalled = false;
            TextView tvInstallAllCast2 = (TextView) _$_findCachedViewById(R.id.tvInstallAllCast);
            Intrinsics.checkNotNullExpressionValue(tvInstallAllCast2, "tvInstallAllCast");
            CommonExtKt.visible(tvInstallAllCast2);
        }
        if (CommonUtils.isAppInstalled("com.castify")) {
            this.isCastifyInstalled = true;
            TextView tvInstallCastify = (TextView) _$_findCachedViewById(R.id.tvInstallCastify);
            Intrinsics.checkNotNullExpressionValue(tvInstallCastify, "tvInstallCastify");
            CommonExtKt.gone(tvInstallCastify);
            return;
        }
        this.isCastifyInstalled = false;
        TextView tvInstallCastify2 = (TextView) _$_findCachedViewById(R.id.tvInstallCastify);
        Intrinsics.checkNotNullExpressionValue(tvInstallCastify2, "tvInstallCastify");
        CommonExtKt.visible(tvInstallCastify2);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        initInstallStatus();
    }

    /* compiled from: ChooseCastAppDialog.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChooseCastAppDialog$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/ChooseCastAppDialog;", "url", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ChooseCastAppDialog newInstance(String str) {
            ChooseCastAppDialog chooseCastAppDialog = new ChooseCastAppDialog();
            chooseCastAppDialog.setArguments(CommonExtKt.bundleOf(TuplesKt.to("url", str)));
            return chooseCastAppDialog;
        }
    }

    private final void startGooglePlay(Context context, String str) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(Intrinsics.stringPlus("market://details?id=", str)));
            intent.setPackage("com.android.vending");
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
            } else {
                SystemUtils.startBrowser(context, Intrinsics.stringPlus("https://play.google.com/store/apps/details?id=", str));
            }
        } catch (ActivityNotFoundException unused) {
            SystemUtils.startBrowser(context, Intrinsics.stringPlus("https://play.google.com/store/apps/details?id=", str));
        }
    }
}
