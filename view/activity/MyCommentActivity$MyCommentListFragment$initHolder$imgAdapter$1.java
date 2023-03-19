package com.movieboxpro.android.view.activity;

import android.widget.ImageView;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.model.ImageItem;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.view.activity.MyCommentActivity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MyCommentActivity.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "helper2", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item2", "Lcom/movieboxpro/android/model/ImageItem;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MyCommentActivity$MyCommentListFragment$initHolder$imgAdapter$1 extends Lambda implements Function2<BaseViewHolder, ImageItem, Unit> {
    final /* synthetic */ MyCommentActivity.MyCommentListFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MyCommentActivity$MyCommentListFragment$initHolder$imgAdapter$1(MyCommentActivity.MyCommentListFragment myCommentListFragment) {
        super(2);
        this.this$0 = myCommentListFragment;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(BaseViewHolder baseViewHolder, ImageItem imageItem) {
        invoke2(baseViewHolder, imageItem);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(BaseViewHolder helper2, ImageItem item2) {
        Intrinsics.checkNotNullParameter(helper2, "helper2");
        Intrinsics.checkNotNullParameter(item2, "item2");
        GlideUtils.loadWithCornerOriginSize(this.this$0.getContext(), item2.getUrl(), (ImageView) helper2.getView(R.id.imageView), 5, item2.getWidth(), item2.getHeight());
    }
}
