package com.movieboxpro.android.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.google.firebase.analytics.FirebaseAnalytics;
import master.flame.danmaku.danmaku.parser.IDataSource;
/* loaded from: classes3.dex */
public class ContentUtils {
    public static String getRealPath(Context context, Uri uri) {
        if (uri != null) {
            if (uri.getScheme().compareTo(FirebaseAnalytics.Param.CONTENT) == 0) {
                Cursor query = context.getContentResolver().query(uri, null, null, null, null);
                if (query == null || !query.moveToFirst()) {
                    return null;
                }
                String string = query.getString(query.getColumnIndexOrThrow("_data"));
                query.close();
                return string;
            } else if (uri.getScheme().compareTo(IDataSource.SCHEME_FILE_TAG) == 0) {
                uri.toString();
                return uri.toString().replace("file://", "");
            } else {
                return null;
            }
        }
        return null;
    }
}
