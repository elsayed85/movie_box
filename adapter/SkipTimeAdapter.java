package com.movieboxpro.android.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.model.SkipTimeItem;
import com.movieboxpro.android.utils.CommonExtKt;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: SkipTimeAdapter.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B-\u0012\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0005j\b\u0012\u0004\u0012\u00020\u0002`\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\nJ\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0002H\u0014R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/movieboxpro/android/adapter/SkipTimeAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/movieboxpro/android/model/SkipTimeItem;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "data", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "itemWidth", "", "max", "(Ljava/util/ArrayList;II)V", "convert", "", "holder", "item", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SkipTimeAdapter extends BaseQuickAdapter<SkipTimeItem, BaseViewHolder> {
    private final int itemWidth;
    private final int max;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SkipTimeAdapter(ArrayList<SkipTimeItem> data, int i, int i2) {
        super(R.layout.adapter_skip_time_item, data);
        Intrinsics.checkNotNullParameter(data, "data");
        this.itemWidth = i;
        this.max = i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, final SkipTimeItem item) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        TextView textView = (TextView) holder.getView(R.id.textView);
        final View view = holder.getView(R.id.view);
        final FrameLayout frameLayout = (FrameLayout) holder.getView(R.id.flView);
        if (this.max == 0) {
            CommonExtKt.invisible(view);
        } else if (item.getTotal() == 0) {
            CommonExtKt.invisible(view);
        } else {
            frameLayout.post(new Runnable() { // from class: com.movieboxpro.android.adapter.-$$Lambda$SkipTimeAdapter$F9J2y8ru-KXrthqqauH5S6g2dos
                @Override // java.lang.Runnable
                public final void run() {
                    SkipTimeAdapter.m63convert$lambda1(view, frameLayout, item, this);
                }
            });
        }
        textView.setText(String.valueOf(item.getTime()));
        LinearLayout linearLayout = (LinearLayout) holder.getView(R.id.linearLayout);
        ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width = this.itemWidth;
            linearLayout.setLayoutParams(layoutParams);
            if (item.getTime() == -1) {
                CommonExtKt.invisible(linearLayout);
                return;
            } else {
                CommonExtKt.visible(linearLayout);
                return;
            }
        }
        throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: convert$lambda-1  reason: not valid java name */
    public static final void m63convert$lambda1(View view, FrameLayout flView, SkipTimeItem item, SkipTimeAdapter this$0) {
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(flView, "$flView");
        Intrinsics.checkNotNullParameter(item, "$item");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height = (flView.getHeight() * item.getTotal()) / this$0.max;
            view.setLayoutParams(layoutParams);
            CommonExtKt.visible(view);
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
    }
}
