package com.movieboxpro.android.adapter;

import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.SpanUtils;
import java.util.List;
/* loaded from: classes3.dex */
public class MovieListItemAdapter extends BaseQuickAdapter<MovieListModel.MovieListItem, BaseViewHolder> {
    public MovieListItemAdapter(List<MovieListModel.MovieListItem> list) {
        super(R.layout.adapter_detail_movie_list_image_item, list);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder baseViewHolder, MovieListModel.MovieListItem movieListItem) {
        TextView textView = (TextView) baseViewHolder.getView(R.id.tv_num);
        TextView textView2 = (TextView) baseViewHolder.getView(R.id.tv_name);
        if (!movieListItem.isMore()) {
            textView2.setVisibility(0);
            textView.setVisibility(0);
            SpanUtils.with(textView).append(String.valueOf(movieListItem.getCount())).setFontSize(12, true).setForegroundColor(ContextCompat.getColor(App.getContext(), R.color.white)).setTypeface(Typeface.DEFAULT_BOLD).setShadow(3.0f, 0.0f, 4.0f, Color.parseColor("#80000000")).create();
            List<String> imgArr = movieListItem.getImgArr();
            if (imgArr != null && imgArr.size() > 0) {
                GlideUtils.loadPost(getContext(), imgArr.get(0), (ImageView) baseViewHolder.getView(R.id.imageView), 8);
            }
            SpanUtils.with(textView2).append(String.valueOf(movieListItem.getName())).setFontSize(14, true).setForegroundColor(ContextCompat.getColor(App.getContext(), R.color.white)).setTypeface(Typeface.DEFAULT_BOLD).setShadow(3.0f, 0.0f, 4.0f, Color.parseColor("#80000000")).setBold().create();
            return;
        }
        GlideUtils.load(getContext(), (int) R.mipmap.ic_movielist_more, (ImageView) baseViewHolder.getView(R.id.imageView));
        textView2.setVisibility(8);
        textView.setVisibility(8);
    }
}
