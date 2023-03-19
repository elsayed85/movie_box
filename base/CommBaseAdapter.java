package com.movieboxpro.android.base;

import androidx.exifinterface.media.ExifInterface;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.movieboxpro.android.utils.databinding.ReflectExtKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: CommBaseAdapter.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0006\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00030\u0002BY\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00126\u0010\u0006\u001a2\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0007\u0012\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u000e¢\u0006\u0002\u0010\u000fJ\u001d\u0010\u0012\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\u0013RA\u0010\u0006\u001a2\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/movieboxpro/android/base/CommBaseAdapter;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "id", "", ReflectExtKt.BIND_NAME, "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "helper", "item", "", "list", "", "(ILkotlin/jvm/functions/Function2;Ljava/util/List;)V", "getBind", "()Lkotlin/jvm/functions/Function2;", "convert", "(Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;Ljava/lang/Object;)V", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CommBaseAdapter<T> extends BaseQuickAdapter<T, com.chad.library.adapter.base.viewholder.BaseViewHolder> {
    private final Function2<com.chad.library.adapter.base.viewholder.BaseViewHolder, T, Unit> bind;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public CommBaseAdapter(int i, Function2<? super com.chad.library.adapter.base.viewholder.BaseViewHolder, ? super T, Unit> bind, List<T> list) {
        super(i, list);
        Intrinsics.checkNotNullParameter(bind, "bind");
        this.bind = bind;
    }

    public /* synthetic */ CommBaseAdapter(int i, Function2 function2, ArrayList arrayList, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, function2, (i2 & 4) != 0 ? new ArrayList() : arrayList);
    }

    public final Function2<com.chad.library.adapter.base.viewholder.BaseViewHolder, T, Unit> getBind() {
        return this.bind;
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    protected void convert(com.chad.library.adapter.base.viewholder.BaseViewHolder helper, T t) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        this.bind.invoke(helper, t);
    }
}
