package com.movieboxpro.android.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.stats.CodePackage;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.BaseSimpleActivity;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.MsgHintDialog;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.functions.Consumer;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: PermissionRequestActivity.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\u0007H\u0016J\b\u0010\t\u001a\u00020\u0007H\u0016J\u001b\u0010\n\u001a\u00020\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0003¢\u0006\u0002\u0010\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/movieboxpro/android/view/activity/PermissionRequestActivity;", "Lcom/movieboxpro/android/base/BaseSimpleActivity;", "()V", IjkMediaMeta.IJKM_KEY_TYPE, "", "getLayoutResId", "initData", "", "initListener", "initView", "requestPermission", "permission", "", "", "([Ljava/lang/String;)V", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PermissionRequestActivity extends BaseSimpleActivity {
    public static final int CAMERA = 3;
    public static final int CHOOSE_COVER = 4;
    public static final int CHOOSE_IMAGE = 2;
    public static final Companion Companion = new Companion(null);
    public static final int DOWNLOAD_SUBTITLE = 5;
    public static final int LOCATION = 1;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private int type;

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
        return R.layout.activity_permission_reqest;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initView() {
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initListener() {
        ((LinearLayout) _$_findCachedViewById(R.id.ll_back)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$PermissionRequestActivity$PBD8J52xwIloNkfThfdb70QtGzU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PermissionRequestActivity.m187initListener$lambda0(PermissionRequestActivity.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btnAccess)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$PermissionRequestActivity$WqPT1QR1yzjCC96HmmoEVW7sn5I
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PermissionRequestActivity.m188initListener$lambda1(PermissionRequestActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m187initListener$lambda0(PermissionRequestActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m188initListener$lambda1(PermissionRequestActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = this$0.type;
        if (i == 1) {
            this$0.requestPermission(new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"});
        } else if (i == 2) {
            this$0.requestPermission(new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CAMERA"});
        } else if (i == 3) {
            this$0.requestPermission(new String[]{"android.permission.CAMERA"});
        } else if (i == 4 || i == 5) {
            this$0.requestPermission(new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"});
        }
    }

    private final void requestPermission(String[] strArr) {
        new RxPermissions(this).requestEachCombined((String[]) Arrays.copyOf(strArr, strArr.length)).subscribe(new Consumer() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$PermissionRequestActivity$BnpNvX-s9cDPrBKeBADI3-dBNZo
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                PermissionRequestActivity.m191requestPermission$lambda3(PermissionRequestActivity.this, (Permission) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: requestPermission$lambda-3  reason: not valid java name */
    public static final void m191requestPermission$lambda3(final PermissionRequestActivity this$0, Permission permission) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (permission.granted) {
            this$0.setResult(-1);
            this$0.finish();
        } else if (permission.shouldShowRequestPermissionRationale) {
        } else {
            MsgHintDialog.Builder title = new MsgHintDialog.Builder(this$0).setTitle("Note");
            title.setContent(((Object) ((TextView) this$0._$_findCachedViewById(R.id.tvDesc)).getText()) + ",you have denied permission,go setting?").setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$PermissionRequestActivity$4m-hcpQprBDZrSMxpP8puCos2Gw
                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                public final void onClick() {
                    PermissionRequestActivity.m192requestPermission$lambda3$lambda2(PermissionRequestActivity.this);
                }
            }).create().show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: requestPermission$lambda-3$lambda-2  reason: not valid java name */
    public static final void m192requestPermission$lambda3$lambda2(PermissionRequestActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", this$0.getPackageName(), null));
            this$0.startActivityForResult(intent, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initData() {
        int intExtra = getIntent().getIntExtra(IjkMediaMeta.IJKM_KEY_TYPE, 0);
        this.type = intExtra;
        if (intExtra == 1) {
            GlideUtils.load((Activity) this, (int) R.mipmap.ic_permission_location, (ImageView) _$_findCachedViewById(R.id.imageView));
            ((TextView) _$_findCachedViewById(R.id.tvDesc)).setText("To obtain a more precise location to select the server, please allow access to Location");
        } else if (intExtra == 2) {
            GlideUtils.load((Activity) this, (int) R.mipmap.ic_permission_image, (ImageView) _$_findCachedViewById(R.id.imageView));
            ((TextView) _$_findCachedViewById(R.id.tvDesc)).setText("To select pictures and take photos, please allow access to Album and Camera");
        } else if (intExtra == 3) {
            GlideUtils.load((Activity) this, (int) R.mipmap.ic_permission_camera, (ImageView) _$_findCachedViewById(R.id.imageView));
            ((TextView) _$_findCachedViewById(R.id.tvDesc)).setText("To scan the QR code, please allow access to Camera");
        } else if (intExtra == 4) {
            GlideUtils.load((Activity) this, (int) R.mipmap.ic_permission_storage, (ImageView) _$_findCachedViewById(R.id.imageView));
            ((TextView) _$_findCachedViewById(R.id.tvDesc)).setText("To choose cover and crop image, please allow access to Storage");
        } else if (intExtra != 5) {
        } else {
            GlideUtils.load((Activity) this, (int) R.mipmap.ic_permission_storage, (ImageView) _$_findCachedViewById(R.id.imageView));
            ((TextView) _$_findCachedViewById(R.id.tvDesc)).setText("To download subtitle, please allow access to Storage");
        }
    }

    /* compiled from: PermissionRequestActivity.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004J\u001e\u0010\t\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/movieboxpro/android/view/activity/PermissionRequestActivity$Companion;", "", "()V", "CAMERA", "", "CHOOSE_COVER", "CHOOSE_IMAGE", "DOWNLOAD_SUBTITLE", CodePackage.LOCATION, TtmlNode.START, "", "context", "Landroid/app/Activity;", IjkMediaMeta.IJKM_KEY_TYPE, "requestCode", "fragment", "Landroidx/fragment/app/Fragment;", "startIntent", "Landroid/content/Intent;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Activity context, int i, int i2) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(context, PermissionRequestActivity.class);
            intent.putExtra(IjkMediaMeta.IJKM_KEY_TYPE, i);
            context.startActivityForResult(intent, i2);
        }

        public final Intent startIntent(Fragment fragment, int i) {
            Intrinsics.checkNotNullParameter(fragment, "fragment");
            Intent intent = new Intent(fragment.getContext(), PermissionRequestActivity.class);
            intent.putExtra(IjkMediaMeta.IJKM_KEY_TYPE, i);
            return intent;
        }

        public final void start(Fragment fragment, int i, int i2) {
            Intrinsics.checkNotNullParameter(fragment, "fragment");
            Intent intent = new Intent(fragment.getContext(), PermissionRequestActivity.class);
            intent.putExtra(IjkMediaMeta.IJKM_KEY_TYPE, i);
            fragment.startActivityForResult(intent, i2);
        }
    }
}
