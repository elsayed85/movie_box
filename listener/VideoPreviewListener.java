package com.movieboxpro.android.listener;

import android.widget.SeekBar;
/* loaded from: classes3.dex */
public interface VideoPreviewListener {
    void onProgressChanged(SeekBar seekBar, int i, int i2);

    void startDrag();

    void stopDrag();
}
