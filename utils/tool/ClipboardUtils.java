package com.movieboxpro.android.utils.tool;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
/* loaded from: classes3.dex */
public class ClipboardUtils {
    private ClipboardUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void copyText(CharSequence charSequence) {
        ((ClipboardManager) Utils.getApp().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("text", charSequence));
    }

    public static CharSequence getText() {
        ClipData primaryClip = ((ClipboardManager) Utils.getApp().getSystemService("clipboard")).getPrimaryClip();
        if (primaryClip == null || primaryClip.getItemCount() <= 0) {
            return null;
        }
        return primaryClip.getItemAt(0).coerceToText(Utils.getApp());
    }

    public static void copyUri(Uri uri) {
        ((ClipboardManager) Utils.getApp().getSystemService("clipboard")).setPrimaryClip(ClipData.newUri(Utils.getApp().getContentResolver(), "uri", uri));
    }

    public static Uri getUri() {
        ClipData primaryClip = ((ClipboardManager) Utils.getApp().getSystemService("clipboard")).getPrimaryClip();
        if (primaryClip == null || primaryClip.getItemCount() <= 0) {
            return null;
        }
        return primaryClip.getItemAt(0).getUri();
    }

    public static void copyIntent(Intent intent) {
        ((ClipboardManager) Utils.getApp().getSystemService("clipboard")).setPrimaryClip(ClipData.newIntent("intent", intent));
    }

    public static Intent getIntent() {
        ClipData primaryClip = ((ClipboardManager) Utils.getApp().getSystemService("clipboard")).getPrimaryClip();
        if (primaryClip == null || primaryClip.getItemCount() <= 0) {
            return null;
        }
        return primaryClip.getItemAt(0).getIntent();
    }
}
