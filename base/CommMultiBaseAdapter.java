package com.movieboxpro.android.base;

import androidx.exifinterface.media.ExifInterface;
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate;
import com.movieboxpro.android.adapter.BaseLoadmoreDelegateMultiAdapter;
import com.movieboxpro.android.utils.databinding.ReflectExtKt;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: CommMultiBaseAdapter.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00030\u0002Bk\u0012'\u0010\u0004\u001a#\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\t0\u0005\u0012!\u0010\n\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\u000b\u0012\u0018\u0010\u000e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\u00100\u000f¢\u0006\u0002\u0010\u0011J\u001d\u0010\u0016\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\u0018R2\u0010\u0004\u001a#\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\t0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R,\u0010\n\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0019"}, d2 = {"Lcom/movieboxpro/android/base/CommMultiBaseAdapter;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/movieboxpro/android/adapter/BaseLoadmoreDelegateMultiAdapter;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", ReflectExtKt.BIND_NAME, "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "helper", "", IjkMediaMeta.IJKM_KEY_TYPE, "Lkotlin/Function1;", "t", "", "typePair", "", "Lkotlin/Pair;", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Ljava/util/List;)V", "getBind", "()Lkotlin/jvm/functions/Function2;", "getType", "()Lkotlin/jvm/functions/Function1;", "convert", "item", "(Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;Ljava/lang/Object;)V", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CommMultiBaseAdapter<T> extends BaseLoadmoreDelegateMultiAdapter<T, com.chad.library.adapter.base.viewholder.BaseViewHolder> {
    private final Function2<com.chad.library.adapter.base.viewholder.BaseViewHolder, T, Unit> bind;
    private final Function1<T, Integer> type;

    public final Function2<com.chad.library.adapter.base.viewholder.BaseViewHolder, T, Unit> getBind() {
        return this.bind;
    }

    public final Function1<T, Integer> getType() {
        return this.type;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public CommMultiBaseAdapter(Function2<? super com.chad.library.adapter.base.viewholder.BaseViewHolder, ? super T, Unit> bind, Function1<? super T, Integer> type, List<Pair<Integer, Integer>> typePair) {
        super(null);
        Intrinsics.checkNotNullParameter(bind, "bind");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(typePair, "typePair");
        this.bind = bind;
        this.type = type;
        setMultiTypeDelegate(new BaseMultiTypeDelegate<T>(this) { // from class: com.movieboxpro.android.base.CommMultiBaseAdapter.1
            final /* synthetic */ CommMultiBaseAdapter<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(null, 1, null);
                this.this$0 = this;
            }

            @Override // com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate
            public int getItemType(List<? extends T> data, int i) {
                Intrinsics.checkNotNullParameter(data, "data");
                return this.this$0.getType().invoke(data.get(i)).intValue();
            }
        });
        Iterator<T> it = typePair.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            BaseMultiTypeDelegate<T> multiTypeDelegate = getMultiTypeDelegate();
            Intrinsics.checkNotNull(multiTypeDelegate);
            multiTypeDelegate.addItemType(((Number) pair.getFirst()).intValue(), ((Number) pair.getSecond()).intValue());
        }
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    protected void convert(com.chad.library.adapter.base.viewholder.BaseViewHolder helper, T t) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        this.bind.invoke(helper, t);
    }
}
