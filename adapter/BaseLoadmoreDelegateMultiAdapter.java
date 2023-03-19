package com.movieboxpro.android.adapter;

import androidx.exifinterface.media.ExifInterface;
import com.chad.library.adapter.base.BaseDelegateMultiAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.module.UpFetchModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* compiled from: BaseLoadmoreDelegateMultiAdapter.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00042\u00020\u00052\u00020\u0006B\u0017\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\b¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/adapter/BaseLoadmoreDelegateMultiAdapter;", ExifInterface.GPS_DIRECTION_TRUE, "VH", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "Lcom/chad/library/adapter/base/BaseDelegateMultiAdapter;", "Lcom/chad/library/adapter/base/module/LoadMoreModule;", "Lcom/chad/library/adapter/base/module/UpFetchModule;", "data", "", "(Ljava/util/List;)V", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class BaseLoadmoreDelegateMultiAdapter<T, VH extends BaseViewHolder> extends BaseDelegateMultiAdapter<T, VH> implements LoadMoreModule, UpFetchModule {
    public BaseLoadmoreDelegateMultiAdapter() {
        this(null, 1, null);
    }

    public BaseLoadmoreDelegateMultiAdapter(List<T> list) {
        super(list);
    }

    public /* synthetic */ BaseLoadmoreDelegateMultiAdapter(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list);
    }
}
