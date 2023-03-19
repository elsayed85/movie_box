package com.movieboxpro.android.utils;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import androidx.core.content.FileProvider;
import java.io.File;
/* loaded from: classes3.dex */
public class IntentUtils {
    public static Intent getInstallAppIntent(String str) {
        return getInstallAppIntent(getFileByPath(str), false);
    }

    public static Intent getInstallAppIntent(File file) {
        return getInstallAppIntent(file, false);
    }

    public static Intent getInstallAppIntent(String str, boolean z) {
        return getInstallAppIntent(getFileByPath(str), z);
    }

    public static Intent getInstallAppIntent(File file, boolean z) {
        Uri uriForFile;
        if (file == null) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        if (Build.VERSION.SDK_INT < 24) {
            uriForFile = Uri.fromFile(file);
        } else {
            intent.setFlags(1);
            uriForFile = FileProvider.getUriForFile(Utils.getApp(), "com.movieboxpro.android.fileProvider", file);
        }
        intent.setDataAndType(uriForFile, "application/vnd.android.package-archive");
        return getIntent(intent, z);
    }

    private static File getFileByPath(String str) {
        if (isSpace(str)) {
            return null;
        }
        return new File(str);
    }

    private static Intent getIntent(Intent intent, boolean z) {
        return z ? intent.addFlags(268435456) : intent;
    }

    private static boolean isSpace(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
