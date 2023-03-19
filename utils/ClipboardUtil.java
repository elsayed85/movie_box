package com.movieboxpro.android.utils;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import com.movieboxpro.android.app.App;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes3.dex */
public class ClipboardUtil {
    public static List<String> getClipboardContent() {
        CharSequence text;
        ClipboardManager clipboardManager = (ClipboardManager) App.getContext().getSystemService("clipboard");
        if (clipboardManager != null && clipboardManager.hasPrimaryClip()) {
            ClipData primaryClip = clipboardManager.getPrimaryClip();
            ClipDescription description = primaryClip.getDescription();
            int itemCount = primaryClip.getItemCount();
            if (itemCount > 0) {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < itemCount; i++) {
                    String mimeType = description.getMimeType(i);
                    ClipData.Item itemAt = primaryClip.getItemAt(i);
                    if (itemAt != null && mimeType.equals("text/plain") && (text = itemAt.getText()) != null) {
                        arrayList.add(text.toString());
                    }
                }
                return arrayList;
            }
        }
        return null;
    }

    public static void copyContent(Context context, String str) {
        ((ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(null, str));
    }
}
