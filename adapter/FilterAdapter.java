package com.movieboxpro.android.adapter;

import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.model.FilterModel;
import java.util.List;
/* loaded from: classes3.dex */
public class FilterAdapter extends BaseQuickAdapter<FilterModel, BaseViewHolder> {
    public FilterAdapter(List<FilterModel> list) {
        super(R.layout.adapter_filter_item, list);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder baseViewHolder, FilterModel filterModel) {
        TextView textView = (TextView) baseViewHolder.getView(R.id.textView);
        textView.setText(filterModel.getText());
        textView.setSelected(filterModel.isSelect());
    }
}
