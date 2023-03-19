package com.movieboxpro.android.adapter;

import android.text.Html;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.dueeeke.model.SrtPraseModel;
import com.movieboxpro.android.R;
import java.util.List;
/* loaded from: classes3.dex */
public class SimpleSubtitleAdapter extends BaseQuickAdapter<SrtPraseModel, BaseViewHolder> {
    public SimpleSubtitleAdapter(List<SrtPraseModel> list) {
        super(R.layout.adapter_simple_subtitle_item, list);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder baseViewHolder, SrtPraseModel srtPraseModel) {
        baseViewHolder.setText(R.id.textView, Html.fromHtml(srtPraseModel.getSrtBody()));
    }
}
