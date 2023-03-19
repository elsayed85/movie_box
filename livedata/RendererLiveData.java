package com.movieboxpro.android.livedata;

import androidx.lifecycle.MutableLiveData;
import kotlin.Metadata;
import org.videolan.libvlc.RendererItem;
/* compiled from: RendererLiveData.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/livedata/RendererLiveData;", "Landroidx/lifecycle/MutableLiveData;", "Lorg/videolan/libvlc/RendererItem;", "()V", "setValue", "", "value", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RendererLiveData extends MutableLiveData<RendererItem> {
    @Override // androidx.lifecycle.MutableLiveData, androidx.lifecycle.LiveData
    public void setValue(RendererItem rendererItem) {
        RendererItem value = getValue();
        if (value != null) {
            value.release();
        }
        if (rendererItem != null) {
            rendererItem.retain();
        }
        super.setValue((RendererLiveData) rendererItem);
    }
}
