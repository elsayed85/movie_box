package com.movieboxpro.android.adapter;

import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.model.ActorModel;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.GlideUtils;
import java.util.List;
/* loaded from: classes3.dex */
public class ActorAdapter extends BaseQuickAdapter<ActorModel, BaseViewHolder> {
    public ActorAdapter(List<ActorModel> list) {
        super(R.layout.adapter_actor_item, list);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder baseViewHolder, ActorModel actorModel) {
        baseViewHolder.setText(R.id.tvName, actorModel.getName());
        TextView textView = (TextView) baseViewHolder.getView(R.id.tvJob);
        if (TextUtils.isEmpty(actorModel.getType())) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
            textView.setText(actorModel.getType());
        }
        AppCompatTextView appCompatTextView = (AppCompatTextView) baseViewHolder.getView(R.id.tvNameFirst);
        ImageView imageView = (ImageView) baseViewHolder.getView(R.id.ivAvatar);
        if (actorModel.isMore()) {
            appCompatTextView.setVisibility(8);
            imageView.setVisibility(0);
            GlideUtils.loadWithCircle(getContext(), (int) R.mipmap.ic_actors_more, imageView, 88, (int) R.drawable.image_loading_placeholer);
        } else if (TextUtils.isEmpty(actorModel.getAvatar())) {
            appCompatTextView.setVisibility(0);
            appCompatTextView.setText(CommonUtils.getNameFirstLetter(actorModel.getName()));
            imageView.setVisibility(8);
        } else {
            appCompatTextView.setVisibility(8);
            imageView.setVisibility(0);
            GlideUtils.loadWithCircle(getContext(), actorModel.getAvatar(), imageView, 88, (int) R.drawable.image_loading_placeholer);
        }
    }
}
