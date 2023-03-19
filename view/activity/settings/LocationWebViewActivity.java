package com.movieboxpro.android.view.activity.settings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.PermissionInterceptor;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.BaseSimpleActivity;
import com.movieboxpro.android.utils.ClipboardUtil;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.MsgHintDialog;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.functions.Consumer;
import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Typography;
/* compiled from: LocationWebViewActivity.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u000fH\u0016J\b\u0010\u0011\u001a\u00020\u000fH\u0016J\"\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\r2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\b\u0010\u0017\u001a\u00020\u000fH\u0002J\u001b\u0010\u0018\u001a\u00020\u000f2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0003¢\u0006\u0002\u0010\u0019R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082.¢\u0006\u0004\n\u0002\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/movieboxpro/android/view/activity/settings/LocationWebViewActivity;", "Lcom/movieboxpro/android/base/BaseSimpleActivity;", "()V", "agentWeb", "Lcom/just/agentweb/AgentWeb;", "permissions", "", "", "[Ljava/lang/String;", "popupMenu", "Landroid/widget/PopupMenu;", "url", "getLayoutResId", "", "initData", "", "initListener", "initView", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "openMenu", "requestLocationPermission", "([Ljava/lang/String;)V", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class LocationWebViewActivity extends BaseSimpleActivity {
    public static final Companion Companion = new Companion(null);
    private AgentWeb agentWeb;
    private String[] permissions;
    private PopupMenu popupMenu;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String url = "";

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
        return R.layout.activity_location_web_view;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m789initListener$lambda0(LocationWebViewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initListener() {
        ((LinearLayout) _$_findCachedViewById(R.id.ll_back)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$LocationWebViewActivity$tAm9LaGS4IaV3tezE-ObqFvTMfk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LocationWebViewActivity.m789initListener$lambda0(LocationWebViewActivity.this, view);
            }
        });
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initData() {
        String stringExtra = getIntent().getStringExtra("url");
        if (stringExtra == null) {
            stringExtra = "";
        }
        this.url = stringExtra;
        AgentWeb go = AgentWeb.with(this).setAgentWebParent((LinearLayout) _$_findCachedViewById(R.id.linearLayout), new LinearLayout.LayoutParams(-1, -1)).useDefaultIndicator().setPermissionInterceptor(new PermissionInterceptor() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$LocationWebViewActivity$CWu84lmEof0wzx12IEHX-Mjyfc4
            @Override // com.just.agentweb.PermissionInterceptor
            public final boolean intercept(String str, String[] strArr, String str2) {
                boolean m788initData$lambda1;
                m788initData$lambda1 = LocationWebViewActivity.m788initData$lambda1(LocationWebViewActivity.this, str, strArr, str2);
                return m788initData$lambda1;
            }
        }).createAgentWeb().ready().go(this.url);
        Intrinsics.checkNotNullExpressionValue(go, "with(this)\n             …\n                .go(url)");
        this.agentWeb = go;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-1  reason: not valid java name */
    public static final boolean m788initData$lambda1(LocationWebViewActivity this$0, String str, String[] permissions, String str2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(permissions, "permissions");
        this$0.permissions = permissions;
        this$0.requestLocationPermission(permissions);
        return true;
    }

    private final void requestLocationPermission(final String[] strArr) {
        new RxPermissions(this).requestEachCombined((String[]) Arrays.copyOf(strArr, strArr.length)).subscribe(new Consumer() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$LocationWebViewActivity$CRSyJdY1FzEBnApjoZRdAOY7CzU
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                LocationWebViewActivity.m796requestLocationPermission$lambda6(LocationWebViewActivity.this, strArr, (Permission) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: requestLocationPermission$lambda-6  reason: not valid java name */
    public static final void m796requestLocationPermission$lambda6(final LocationWebViewActivity this$0, final String[] permissions, Permission permission) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(permissions, "$permissions");
        if (permission.granted) {
            AgentWeb agentWeb = this$0.agentWeb;
            if (agentWeb == null) {
                Intrinsics.throwUninitializedPropertyAccessException("agentWeb");
                agentWeb = null;
            }
            agentWeb.getUrlLoader().reload();
        } else if (permission.shouldShowRequestPermissionRationale) {
            new MsgHintDialog.Builder(this$0).setTitle("Note").setContent("We need location permission to create invitation code!").setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$LocationWebViewActivity$adMA2T7Ocm44hlP6ZDCMbtR0zMs
                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                public final void onClick() {
                    LocationWebViewActivity.m797requestLocationPermission$lambda6$lambda2(LocationWebViewActivity.this, permissions);
                }
            }).setNegativeActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$LocationWebViewActivity$9koKPZHge1gbMwMZUga1CYHtiwY
                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                public final void onClick() {
                    LocationWebViewActivity.m798requestLocationPermission$lambda6$lambda3(LocationWebViewActivity.this);
                }
            }).create().show();
        } else {
            new MsgHintDialog.Builder(this$0).setTitle("Note").setContent("We need location permission to create invitation code,you have denied permission,go setting?").setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$LocationWebViewActivity$COnJr2eazigT-Z0MjzzXkzgAgDs
                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                public final void onClick() {
                    LocationWebViewActivity.m799requestLocationPermission$lambda6$lambda4(LocationWebViewActivity.this);
                }
            }).setNegativeActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$LocationWebViewActivity$Y_CnRVHpn7EIRowLxJ2P4CQT8AE
                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                public final void onClick() {
                    LocationWebViewActivity.m800requestLocationPermission$lambda6$lambda5(LocationWebViewActivity.this);
                }
            }).create().show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: requestLocationPermission$lambda-6$lambda-2  reason: not valid java name */
    public static final void m797requestLocationPermission$lambda6$lambda2(LocationWebViewActivity this$0, String[] permissions) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(permissions, "$permissions");
        this$0.requestLocationPermission(permissions);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: requestLocationPermission$lambda-6$lambda-3  reason: not valid java name */
    public static final void m798requestLocationPermission$lambda6$lambda3(LocationWebViewActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: requestLocationPermission$lambda-6$lambda-4  reason: not valid java name */
    public static final void m799requestLocationPermission$lambda6$lambda4(LocationWebViewActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", this$0.getPackageName(), null));
            this$0.startActivityForResult(intent, 100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: requestLocationPermission$lambda-6$lambda-5  reason: not valid java name */
    public static final void m800requestLocationPermission$lambda6$lambda5(LocationWebViewActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initView() {
        ImageView iv_right = (ImageView) _$_findCachedViewById(R.id.iv_right);
        Intrinsics.checkNotNullExpressionValue(iv_right, "iv_right");
        CommonExtKt.visible(iv_right);
        ((ImageView) _$_findCachedViewById(R.id.iv_right)).setImageResource(R.mipmap.ic_more);
        ((ImageView) _$_findCachedViewById(R.id.iv_right)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$LocationWebViewActivity$fPwyjuq9Z3Y8XUp4UFz7uSpOK2I
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LocationWebViewActivity.m790initView$lambda7(LocationWebViewActivity.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tv_title)).setText("Create invitation code");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-7  reason: not valid java name */
    public static final void m790initView$lambda7(LocationWebViewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.openMenu();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 100) {
            String[] strArr = this.permissions;
            if (strArr == null) {
                Intrinsics.throwUninitializedPropertyAccessException("permissions");
                strArr = null;
            }
            requestLocationPermission(strArr);
        }
    }

    private final void openMenu() {
        if (this.popupMenu == null) {
            PopupMenu popupMenu = new PopupMenu(this, (ImageView) _$_findCachedViewById(R.id.iv_right));
            this.popupMenu = popupMenu;
            if (popupMenu != null) {
                popupMenu.inflate(R.menu.web_view_more_menu);
            }
            PopupMenu popupMenu2 = this.popupMenu;
            if (popupMenu2 != null) {
                popupMenu2.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$LocationWebViewActivity$uQ57aXSP-FvlgKzMShpXU4vwYhw
                    @Override // android.widget.PopupMenu.OnMenuItemClickListener
                    public final boolean onMenuItemClick(MenuItem menuItem) {
                        boolean m795openMenu$lambda8;
                        m795openMenu$lambda8 = LocationWebViewActivity.m795openMenu$lambda8(LocationWebViewActivity.this, menuItem);
                        return m795openMenu$lambda8;
                    }
                });
            }
        }
        PopupMenu popupMenu3 = this.popupMenu;
        if (popupMenu3 == null) {
            return;
        }
        popupMenu3.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: openMenu$lambda-8  reason: not valid java name */
    public static final boolean m795openMenu$lambda8(LocationWebViewActivity this$0, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int itemId = menuItem.getItemId();
        if (itemId == R.id.copy) {
            ClipboardUtil.copyContent(this$0, this$0.url);
            ToastUtils.showShort("copy successfully", new Object[0]);
        } else if (itemId == R.id.default_browser) {
            SystemUtils.startBrowser((Activity) this$0, this$0.url);
        } else if (itemId != R.id.refresh) {
            return false;
        } else {
            AgentWeb agentWeb = this$0.agentWeb;
            if (agentWeb == null) {
                Intrinsics.throwUninitializedPropertyAccessException("agentWeb");
                agentWeb = null;
            }
            agentWeb.getUrlLoader().reload();
        }
        return true;
    }

    /* compiled from: LocationWebViewActivity.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/view/activity/settings/LocationWebViewActivity$Companion;", "", "()V", TtmlNode.START, "", "context", "Landroid/content/Context;", "url", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context, String url) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(url, "url");
            int i = 0;
            Pair[] pairArr = {TuplesKt.to("url", url)};
            Intent intent = new Intent(context, LocationWebViewActivity.class);
            Bundle bundle = new Bundle(1);
            while (i < 1) {
                Pair pair = pairArr[i];
                i++;
                String str = (String) pair.component1();
                Object component2 = pair.component2();
                if (component2 == null) {
                    bundle.putString(str, null);
                } else if (component2 instanceof Boolean) {
                    bundle.putBoolean(str, ((Boolean) component2).booleanValue());
                } else if (component2 instanceof Byte) {
                    bundle.putByte(str, ((Number) component2).byteValue());
                } else if (component2 instanceof Character) {
                    bundle.putChar(str, ((Character) component2).charValue());
                } else if (component2 instanceof Double) {
                    bundle.putDouble(str, ((Number) component2).doubleValue());
                } else if (component2 instanceof Float) {
                    bundle.putFloat(str, ((Number) component2).floatValue());
                } else if (component2 instanceof Integer) {
                    bundle.putInt(str, ((Number) component2).intValue());
                } else if (component2 instanceof Long) {
                    bundle.putLong(str, ((Number) component2).longValue());
                } else if (component2 instanceof Short) {
                    bundle.putShort(str, ((Number) component2).shortValue());
                } else if (component2 instanceof Bundle) {
                    bundle.putBundle(str, (Bundle) component2);
                } else if (component2 instanceof CharSequence) {
                    bundle.putCharSequence(str, (CharSequence) component2);
                } else if (component2 instanceof Parcelable) {
                    bundle.putParcelable(str, (Parcelable) component2);
                } else if (component2 instanceof boolean[]) {
                    bundle.putBooleanArray(str, (boolean[]) component2);
                } else if (component2 instanceof byte[]) {
                    bundle.putByteArray(str, (byte[]) component2);
                } else if (component2 instanceof char[]) {
                    bundle.putCharArray(str, (char[]) component2);
                } else if (component2 instanceof double[]) {
                    bundle.putDoubleArray(str, (double[]) component2);
                } else if (component2 instanceof float[]) {
                    bundle.putFloatArray(str, (float[]) component2);
                } else if (component2 instanceof int[]) {
                    bundle.putIntArray(str, (int[]) component2);
                } else if (component2 instanceof long[]) {
                    bundle.putLongArray(str, (long[]) component2);
                } else if (component2 instanceof short[]) {
                    bundle.putShortArray(str, (short[]) component2);
                } else if (component2 instanceof Object[]) {
                    Class<?> componentType = component2.getClass().getComponentType();
                    Intrinsics.checkNotNull(componentType);
                    if (Parcelable.class.isAssignableFrom(componentType)) {
                        if (component2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<android.os.Parcelable>");
                        }
                        bundle.putParcelableArray(str, (Parcelable[]) component2);
                    } else if (String.class.isAssignableFrom(componentType)) {
                        if (component2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.String>");
                        }
                        bundle.putStringArray(str, (String[]) component2);
                    } else if (CharSequence.class.isAssignableFrom(componentType)) {
                        if (component2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.CharSequence>");
                        }
                        bundle.putCharSequenceArray(str, (CharSequence[]) component2);
                    } else if (Serializable.class.isAssignableFrom(componentType)) {
                        bundle.putSerializable(str, (Serializable) component2);
                    } else {
                        String canonicalName = componentType.getCanonicalName();
                        throw new IllegalArgumentException("Illegal value array type " + ((Object) canonicalName) + " for key \"" + str + Typography.quote);
                    }
                } else if (component2 instanceof Serializable) {
                    bundle.putSerializable(str, (Serializable) component2);
                } else if (Build.VERSION.SDK_INT >= 18 && (component2 instanceof Binder)) {
                    bundle.putBinder(str, (IBinder) component2);
                } else if (Build.VERSION.SDK_INT >= 21 && (component2 instanceof Size)) {
                    bundle.putSize(str, (Size) component2);
                } else if (Build.VERSION.SDK_INT >= 21 && (component2 instanceof SizeF)) {
                    bundle.putSizeF(str, (SizeF) component2);
                } else {
                    String canonicalName2 = component2.getClass().getCanonicalName();
                    throw new IllegalArgumentException("Illegal value type " + ((Object) canonicalName2) + " for key \"" + str + Typography.quote);
                }
            }
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    }
}
