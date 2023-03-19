package com.movieboxpro.android.presenter;

import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.cast.MediaTrack;
/* loaded from: classes3.dex */
public class ChromeCastPresenter {
    public static MediaTrack buildTrack(long j, String str, String str2, String str3, String str4, String str5) {
        int i;
        int i2 = 1;
        if ("text".equals(str)) {
            i = 1;
        } else if ("video".equals(str)) {
            i = 3;
        } else {
            i = "audio".equals(str) ? 2 : 0;
        }
        if (str2 == null || "captions".equals(str)) {
            i2 = 2;
        } else if (!MediaTrack.ROLE_SUBTITLE.equals(str)) {
            i2 = 0;
        }
        return new MediaTrack.Builder(j, i).setName(str4).setSubtype(i2).setContentType(MimeTypes.TEXT_VTT).setContentId(str3).setLanguage(str5).build();
    }
}
