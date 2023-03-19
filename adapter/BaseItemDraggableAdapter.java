package com.movieboxpro.android.adapter;

import androidx.exifinterface.media.ExifInterface;
import com.chad.library.adapter.base.module.DraggableModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* compiled from: BaseItemDraggableAdapter.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00042\u00020\u0005B!\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\t¢\u0006\u0002\u0010\nR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/movieboxpro/android/adapter/BaseItemDraggableAdapter;", ExifInterface.GPS_DIRECTION_TRUE, "VH", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "Lcom/movieboxpro/android/adapter/BaseLoadMoreAdapter;", "Lcom/chad/library/adapter/base/module/DraggableModule;", "layoutResId", "", "data", "", "(ILjava/util/List;)V", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class BaseItemDraggableAdapter<T, VH extends BaseViewHolder> extends BaseLoadMoreAdapter<T, VH> implements DraggableModule {
    private final int layoutResId;

    public /* synthetic */ BaseItemDraggableAdapter(int i, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? null : list);
    }

    public BaseItemDraggableAdapter(int i, List<T> list) {
        super(i, list);
        this.layoutResId = i;
    }
}
