package com.movieboxpro.android.utils;

import com.movieboxpro.android.app.App;
import java.io.File;
import java.util.ArrayList;
import kotlin.Metadata;
import master.flame.danmaku.danmaku.parser.IDataSource;
import org.videolan.libvlc.util.AndroidUtil;
/* compiled from: VLCOptions.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/utils/VLCOptions;", "", "()V", "libOptions", "Ljava/util/ArrayList;", "", "getLibOptions", "()Ljava/util/ArrayList;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class VLCOptions {
    public static final VLCOptions INSTANCE = new VLCOptions();

    private VLCOptions() {
    }

    public final ArrayList<String> getLibOptions() {
        ArrayList<String> arrayList = new ArrayList<>(50);
        if (AndroidUtil.isLolliPopOrLater) {
            arrayList.add("--audio-time-stretch");
        } else {
            arrayList.add("--no-audio-time-stretch");
        }
        arrayList.add("--avcodec-skiploopfilter");
        arrayList.add("-1");
        arrayList.add("--avcodec-skip-frame");
        arrayList.add("0");
        arrayList.add("--stats");
        arrayList.add("--audio-resampler");
        arrayList.add("soxr");
        arrayList.add("file_crypt,none");
        arrayList.add("--stats");
        arrayList.add("--tone-mapping=1");
        arrayList.add("--android-display-chroma");
        arrayList.add("RV16");
        arrayList.add("--sout-chromecast-conversion-quality=0");
        arrayList.add("--sout-keep");
        arrayList.add("--smb-force-v1");
        arrayList.add(AndroidUtil.isMarshMallowOrLater ? "file_crypt,none" : "file_plaintext,none");
        arrayList.add("--keystore-file");
        arrayList.add("-vv");
        arrayList.add(new File(App.getContext().getDir("keystore", 0), IDataSource.SCHEME_FILE_TAG).getAbsolutePath());
        return arrayList;
    }
}
