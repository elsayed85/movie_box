package com.movieboxpro.android.utils.unrar;

import java.io.File;
/* loaded from: classes3.dex */
public interface UnrarCallback {
    boolean isNextVolumeReady(File file);

    void volumeProgressChanged(long j, long j2);
}
