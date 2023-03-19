package com.movieboxpro.android.view.widget;

import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.config.ConfigKey;
import com.movieboxpro.android.config.ConfigUtils;
import com.movieboxpro.android.model.common.UpdateInfo;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.UpdateSheetDialog;
import com.movieboxpro.android.view.widget.FileDownloader;
import java.io.File;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
/* compiled from: UpdateChecker.kt */
@Metadata(d1 = {"\u0000;\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u0018\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0018\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\fH\u0003J\u0018\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0018\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\t¨\u0006\u0017"}, d2 = {"Lcom/movieboxpro/android/view/widget/UpdateChecker;", "", "()V", "id", "", "isUpdateShowing", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "com/movieboxpro/android/view/widget/UpdateChecker$listener$1", "Lcom/movieboxpro/android/view/widget/UpdateChecker$listener$1;", "checkDownloadedAPk", "updateInfo", "Lcom/movieboxpro/android/model/common/UpdateInfo;", "checkUpdate", "", "context", "Landroidx/fragment/app/FragmentActivity;", "checkWebUpdate", "downloadApk", "Landroid/content/Context;", "requestPermission", "showUpdateDialog", "startDownload", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UpdateChecker {
    private static final int id = 1000;
    private static boolean isUpdateShowing;
    public static final UpdateChecker INSTANCE = new UpdateChecker();
    private static final UpdateChecker$listener$1 listener = new FileDownloader.DownloadListener() { // from class: com.movieboxpro.android.view.widget.UpdateChecker$listener$1
        @Override // com.movieboxpro.android.view.widget.FileDownloader.DownloadListener
        public void onFailure(int i, String str) {
            FileDownloader.DownloadListener.DefaultImpls.onFailure(this, i, str);
        }

        @Override // com.movieboxpro.android.view.widget.FileDownloader.DownloadListener
        public void onProgress(int i, int i2) {
            FileDownloader.DownloadListener.DefaultImpls.onProgress(this, i, i2);
        }

        @Override // com.movieboxpro.android.view.widget.FileDownloader.DownloadListener
        public void onStarted(int i) {
            FileDownloader.DownloadListener.DefaultImpls.onStarted(this, i);
        }

        @Override // com.movieboxpro.android.view.widget.FileDownloader.DownloadListener
        public void onSuccess(int i, String path) {
            Intrinsics.checkNotNullParameter(path, "path");
            File file = new File(path);
            String lowerCase = FilesKt.getExtension(file).toLowerCase();
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
            if (Intrinsics.areEqual(lowerCase, "apk")) {
                SystemUtils.installAPK(file);
            }
        }
    };

    private UpdateChecker() {
    }

    public final void checkUpdate(FragmentActivity context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (ConfigUtils.containsConfig(ConfigKey.APK_LATEST_VERSION) && ConfigUtils.containsConfig(ConfigKey.APK_VERSION) && ConfigUtils.readIntegerConfig(ConfigKey.APK_LATEST_VERSION, App.versionCode) > App.versionCode) {
            UpdateInfo updateInfo = new UpdateInfo();
            updateInfo.title = ConfigUtils.readStringConfig(ConfigKey.APK_TITLE);
            updateInfo.version = ConfigUtils.readStringConfig(ConfigKey.APK_VERSION);
            updateInfo.apkSize = ConfigUtils.readStringConfig(ConfigKey.APK_SIZE);
            updateInfo.updateTime = ConfigUtils.readStringConfig(ConfigKey.APK_TIME);
            updateInfo.apkUrl = ConfigUtils.readStringConfig(ConfigKey.APK_URL);
            updateInfo.md5 = ConfigUtils.readStringConfig(ConfigKey.APK_MD5);
            updateInfo.description = ConfigUtils.readStringConfig(ConfigKey.APK_INFO_NEW);
            updateInfo.apkPath = Constant.DIR_DOWNLOAD + "MovieBoxPro-v" + ((Object) updateInfo.version) + ".apk";
            showUpdateDialog(context, updateInfo);
        }
    }

    public final void checkWebUpdate(FragmentActivity context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (ConfigUtils.containsConfig(ConfigKey.WEB_APK_LATEST_VERSION) && ConfigUtils.containsConfig(ConfigKey.APK_VERSION) && ConfigUtils.readIntegerConfig(ConfigKey.WEB_APK_LATEST_VERSION, App.versionCode) > App.versionCode) {
            UpdateInfo updateInfo = new UpdateInfo();
            updateInfo.title = ConfigUtils.readStringConfig(ConfigKey.APK_TITLE);
            updateInfo.version = ConfigUtils.readStringConfig(ConfigKey.APK_VERSION);
            updateInfo.apkSize = ConfigUtils.readStringConfig(ConfigKey.APK_SIZE);
            updateInfo.updateTime = ConfigUtils.readStringConfig(ConfigKey.APK_TIME);
            updateInfo.apkUrl = ConfigUtils.readStringConfig(ConfigKey.APK_URL);
            updateInfo.md5 = ConfigUtils.readStringConfig(ConfigKey.APK_MD5);
            updateInfo.description = ConfigUtils.readStringConfig(ConfigKey.APK_INFO_NEW);
            updateInfo.apkPath = Constant.DIR_DOWNLOAD + "MovieBoxPro-v" + ((Object) updateInfo.version) + ".apk";
            showUpdateDialog(context, updateInfo);
        }
    }

    private final boolean checkDownloadedAPk(UpdateInfo updateInfo) {
        File file = new File(updateInfo.apkPath);
        if (file.exists() && !file.isDirectory()) {
            file.delete();
            return false;
        }
        return false;
    }

    private final void showUpdateDialog(final FragmentActivity fragmentActivity, final UpdateInfo updateInfo) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("Version：%s<br>Size：%s<br>Time：%s<br><br>Introduce：<br><br>%s", Arrays.copyOf(new Object[]{updateInfo.version, updateInfo.apkSize, updateInfo.updateTime, updateInfo.description}, 4));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        new UpdateSheetDialog.Builder(fragmentActivity).setTitle(updateInfo.title).setContent(format).setPositiveText(checkDownloadedAPk(updateInfo) ? "Immediate Installation" : "Download Now").setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.widget.-$$Lambda$UpdateChecker$NRGEmGUCsAtxr5hiK4qHax7h8bg
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public final void onClick() {
                UpdateChecker.m1433showUpdateDialog$lambda0(FragmentActivity.this, updateInfo);
            }
        }).create().show();
        isUpdateShowing = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showUpdateDialog$lambda-0  reason: not valid java name */
    public static final void m1433showUpdateDialog$lambda0(FragmentActivity context, UpdateInfo updateInfo) {
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(updateInfo, "$updateInfo");
        INSTANCE.requestPermission(context, updateInfo);
    }

    private final void requestPermission(FragmentActivity fragmentActivity, UpdateInfo updateInfo) {
        startDownload(fragmentActivity, updateInfo);
    }

    private final void startDownload(Context context, UpdateInfo updateInfo) {
        isUpdateShowing = false;
        if (checkDownloadedAPk(updateInfo)) {
            SystemUtils.installAPK(context, new File(updateInfo.apkPath));
        } else {
            downloadApk(context, updateInfo);
        }
    }

    private final void downloadApk(Context context, UpdateInfo updateInfo) {
        FileDownloader fileDownloader = new FileDownloader();
        fileDownloader.setId(1000);
        fileDownloader.setUrl(updateInfo.apkUrl);
        fileDownloader.setPath(updateInfo.apkPath);
        fileDownloader.setMd5(updateInfo.md5);
        fileDownloader.setListener(listener);
        fileDownloader.download(context, "UPDATING");
    }
}
