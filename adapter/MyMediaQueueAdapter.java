package com.movieboxpro.android.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.airbnb.lottie.LottieAnimationView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.DraggableModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.framework.media.MediaQueue;
import com.movieboxpro.android.R;
import com.movieboxpro.android.utils.GlideUtils;
import java.util.ArrayList;
/* loaded from: classes3.dex */
public class MyMediaQueueAdapter extends BaseQuickAdapter<MediaQueueItem, BaseViewHolder> implements DraggableModule {
    private MediaQueue.Callback callback;
    private Context context;
    private int currId;
    private int currPlayPosition;
    private MediaQueue mediaQueue;

    public MyMediaQueueAdapter(Context context, MediaQueue mediaQueue, int i) {
        super(R.layout.adapter_cast_list_item, new ArrayList());
        this.currPlayPosition = -1;
        ArrayList arrayList = new ArrayList();
        if (mediaQueue != null) {
            for (int i2 = 0; i2 < mediaQueue.getItemCount(); i2++) {
                arrayList.add(mediaQueue.getItemAtIndex(i2));
            }
            setNewData(arrayList);
        }
        this.mediaQueue = mediaQueue;
        initCallback();
        this.context = context;
        this.currId = i;
    }

    public void setNewPlayId(int i, int i2) {
        this.currId = i;
        notifyItemChanged(this.currPlayPosition);
        notifyItemChanged(i2);
        this.currPlayPosition = i2;
    }

    private void initCallback() {
        MediaQueue.Callback callback = new MediaQueue.Callback() { // from class: com.movieboxpro.android.adapter.MyMediaQueueAdapter.1
            @Override // com.google.android.gms.cast.framework.media.MediaQueue.Callback
            public void mediaQueueChanged() {
                super.mediaQueueChanged();
            }

            @Override // com.google.android.gms.cast.framework.media.MediaQueue.Callback
            public void itemsReloaded() {
                MyMediaQueueAdapter.this.notifyDataSetChanged();
            }

            @Override // com.google.android.gms.cast.framework.media.MediaQueue.Callback
            public void itemsInsertedInRange(int i, int i2) {
                MyMediaQueueAdapter.this.notifyItemRangeInserted(i, i2);
            }

            @Override // com.google.android.gms.cast.framework.media.MediaQueue.Callback
            public void itemsUpdatedAtIndexes(int[] iArr) {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < MyMediaQueueAdapter.this.mediaQueue.getItemCount(); i++) {
                    arrayList.add(MyMediaQueueAdapter.this.mediaQueue.getItemAtIndex(i));
                }
                MyMediaQueueAdapter.this.setNewData(arrayList);
                for (int i2 : iArr) {
                    MyMediaQueueAdapter.this.notifyItemChanged(i2);
                }
            }

            @Override // com.google.android.gms.cast.framework.media.MediaQueue.Callback
            public void itemsRemovedAtIndexes(int[] iArr) {
                if (iArr.length > 1) {
                    MyMediaQueueAdapter.this.notifyDataSetChanged();
                    return;
                }
                for (int i : iArr) {
                    MyMediaQueueAdapter.this.notifyItemRemoved(i);
                }
            }
        };
        this.callback = callback;
        MediaQueue mediaQueue = this.mediaQueue;
        if (mediaQueue != null) {
            mediaQueue.registerCallback(callback);
        }
    }

    public void unRegisterCallback() {
        MediaQueue mediaQueue = this.mediaQueue;
        if (mediaQueue != null) {
            mediaQueue.unregisterCallback(this.callback);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder baseViewHolder, MediaQueueItem mediaQueueItem) {
        ImageView imageView = (ImageView) baseViewHolder.getView(R.id.ivTvImage);
        ImageView imageView2 = (ImageView) baseViewHolder.getView(R.id.ivMovieImage);
        if (mediaQueueItem != null) {
            MediaMetadata metadata = mediaQueueItem.getMedia().getMetadata();
            String string = metadata.getString(MediaMetadata.KEY_SUBTITLE);
            if (!TextUtils.isEmpty(string) && !"null".equals(string)) {
                baseViewHolder.setText(R.id.tvInfo, string);
                if (metadata.getImages() != null && metadata.getImages().size() > 0) {
                    GlideUtils.load(this.context, metadata.getImages().get(0).getUrl().toString(), imageView);
                }
                imageView2.setVisibility(8);
                imageView.setVisibility(0);
            } else {
                if (metadata.getImages() != null && metadata.getImages().size() > 0) {
                    GlideUtils.load(this.context, metadata.getImages().get(0).getUrl().toString(), imageView2);
                }
                imageView2.setVisibility(0);
                imageView.setVisibility(8);
            }
            TextView textView = (TextView) baseViewHolder.getView(R.id.tvDelete);
            baseViewHolder.setText(R.id.tvTitle, metadata.getString(MediaMetadata.KEY_TITLE));
            LottieAnimationView lottieAnimationView = (LottieAnimationView) baseViewHolder.getView(R.id.lottieView);
            if (mediaQueueItem.getItemId() == this.currId) {
                lottieAnimationView.setVisibility(0);
                lottieAnimationView.setAnimation("playing.json");
                lottieAnimationView.playAnimation();
                baseViewHolder.getView(R.id.container).setBackgroundColor(ContextCompat.getColor(this.context, R.color.color_main_back));
                this.currPlayPosition = baseViewHolder.getAdapterPosition();
                textView.setVisibility(8);
                return;
            }
            baseViewHolder.getView(R.id.container).setBackgroundColor(ContextCompat.getColor(this.context, R.color.color_main));
            lottieAnimationView.setVisibility(4);
            textView.setVisibility(0);
        }
    }
}
