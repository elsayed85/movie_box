package com.movieboxpro.android.view.dialog;

import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.model.MovieListFilter;
import com.movieboxpro.android.utils.CommonExtKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MovieListSortDialog.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Lcom/movieboxpro/android/model/MovieListFilter;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MovieListSortDialog$initView$2 extends Lambda implements Function2<BaseViewHolder, MovieListFilter, Unit> {
    final /* synthetic */ MovieListSortDialog this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MovieListSortDialog$initView$2(MovieListSortDialog movieListSortDialog) {
        super(2);
        this.this$0 = movieListSortDialog;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(BaseViewHolder baseViewHolder, MovieListFilter movieListFilter) {
        invoke2(baseViewHolder, movieListFilter);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(BaseViewHolder helper, MovieListFilter item) {
        int i;
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        AppCompatImageView appCompatImageView = (AppCompatImageView) helper.getView(R.id.ivSelect);
        TextView textView = (TextView) helper.getView(R.id.tvTitle);
        i = this.this$0.selectPos;
        if (i == helper.getAdapterPosition()) {
            CommonExtKt.visible(appCompatImageView);
            textView.setTextColor(CommonExtKt.colorInt(this.this$0, (int) R.color.white_87alpha));
        } else {
            CommonExtKt.invisible(appCompatImageView);
            textView.setTextColor(CommonExtKt.colorInt(this.this$0, (int) R.color.white_38alpha));
        }
        textView.setText(item.getTitle());
    }
}
