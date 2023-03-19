package com.movieboxpro.android.http;

import com.movieboxpro.android.utils.HexDump;
import com.movieboxpro.android.utils.MD5Util;
import com.movieboxpro.android.utils.MLog;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/* loaded from: classes3.dex */
public class SignUtils {
    private static final String SECURE_STR = "";
    private static final String TAG = "SignUtils";

    public static String getSignature(List<String> list) {
        if (list != null) {
            Collections.sort(list, new Comparator<String>() { // from class: com.movieboxpro.android.http.SignUtils.1
                @Override // java.util.Comparator
                public int compare(String str, String str2) {
                    return str.compareTo(str2);
                }
            });
            String str = "";
            for (int i = 0; i < list.size(); i++) {
                str = i < list.size() - 1 ? str + list.get(i) + "&" : str + list.get(i);
            }
            String str2 = str + "";
            MLog.d(TAG, "preSignature: " + str2);
            return HexDump.toHexString(MD5Util.md5(str2)).toUpperCase();
        }
        return null;
    }
}
