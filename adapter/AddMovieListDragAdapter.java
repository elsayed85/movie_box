package com.movieboxpro.android.adapter;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.model.movie.NormalFilmModel;
import java.util.List;
/* loaded from: classes3.dex */
public class AddMovieListDragAdapter extends BaseItemDraggableAdapter<NormalFilmModel, BaseViewHolder> {
    private OnAfterEditTextChangedListener onAfterEditTextChangedListener;

    /* loaded from: classes3.dex */
    public interface OnAfterEditTextChangedListener {
        void onTextChanged(int i, String str);
    }

    public AddMovieListDragAdapter(int i, List<NormalFilmModel> list) {
        super(i, list);
    }

    public void setOnAfterEditTextChangedListener(OnAfterEditTextChangedListener onAfterEditTextChangedListener) {
        this.onAfterEditTextChangedListener = onAfterEditTextChangedListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00e0  */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void convert(final com.chad.library.adapter.base.viewholder.BaseViewHolder r8, com.movieboxpro.android.model.movie.NormalFilmModel r9) {
        /*
            Method dump skipped, instructions count: 340
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.adapter.AddMovieListDragAdapter.convert(com.chad.library.adapter.base.viewholder.BaseViewHolder, com.movieboxpro.android.model.movie.NormalFilmModel):void");
    }
}
