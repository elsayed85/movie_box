package com.movieboxpro.android.adapter;

import androidx.exifinterface.media.ExifInterface;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.chad.library.adapter.base.module.DraggableModule;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.module.UpFetchModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: BaseDragLoadMoreAdapter.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u0007B!\u0012\b\b\u0001\u0010\b\u001a\u00020\t\u0012\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u000b¢\u0006\u0002\u0010\fJ\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/movieboxpro/android/adapter/BaseDragLoadMoreAdapter;", ExifInterface.GPS_DIRECTION_TRUE, "VH", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/chad/library/adapter/base/module/LoadMoreModule;", "Lcom/chad/library/adapter/base/module/UpFetchModule;", "Lcom/chad/library/adapter/base/module/DraggableModule;", "layoutResId", "", "data", "", "(ILjava/util/List;)V", "getLoadMore", "Lcom/chad/library/adapter/base/module/BaseLoadMoreModule;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class BaseDragLoadMoreAdapter<T, VH extends BaseViewHolder> extends BaseQuickAdapter<T, VH> implements LoadMoreModule, UpFetchModule, DraggableModule {
    private final int layoutResId;

    public /* synthetic */ BaseDragLoadMoreAdapter(int i, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? null : list);
    }

    public BaseDragLoadMoreAdapter(int i, List<T> list) {
        super(i, list);
        this.layoutResId = i;
    }

    public final BaseLoadMoreModule getLoadMore() {
        BaseLoadMoreModule loadMoreModule = getLoadMoreModule();
        Intrinsics.checkNotNull(loadMoreModule);
        return loadMoreModule;
    }
}
