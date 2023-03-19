package com.movieboxpro.android.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.library.flowlayout.FlowLayoutManager;
import com.library.flowlayout.SpaceItemDecoration;
import com.movieboxpro.android.R;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.VideoTagModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.TimeUtils;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ChooseDownloadQualityAdapter.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0002H\u0014J\u000e\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006J\u001a\u0010\f\u001a\u00020\b2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0006H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/movieboxpro/android/adapter/ChooseDownloadQualityAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/movieboxpro/android/model/BaseMediaModel$DownloadFile;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "()V", "showOrg", "", "convert", "", "holder", "item", "setShowOrg", "setViewVisibility", "view", "Landroid/view/View;", "visible", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ChooseDownloadQualityAdapter extends BaseQuickAdapter<BaseMediaModel.DownloadFile, BaseViewHolder> {
    private boolean showOrg;

    public ChooseDownloadQualityAdapter() {
        super(R.layout.adapter_definition_item, null, 2, null);
    }

    public final void setShowOrg(boolean z) {
        this.showOrg = z;
    }

    private final void setViewVisibility(View view, boolean z) {
        ViewGroup.LayoutParams layoutParams = view == null ? null : view.getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView.LayoutParams");
        }
        RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
        if (z) {
            layoutParams2.height = -2;
            layoutParams2.width = -1;
            CommonExtKt.visible(view);
        } else {
            CommonExtKt.gone(view);
            layoutParams2.height = 0;
            layoutParams2.width = 0;
        }
        view.setLayoutParams(layoutParams2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, BaseMediaModel.DownloadFile item) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        ConstraintLayout constraintLayout = (ConstraintLayout) holder.getView(R.id.constraintLayout);
        if (item.original != 1) {
            CommonExtKt.visible(constraintLayout);
        } else if (this.showOrg) {
            setViewVisibility(constraintLayout, true);
        } else {
            setViewVisibility(constraintLayout, false);
        }
        final ArrayList arrayList = new ArrayList();
        if (item.h265 == 1) {
            VideoTagModel videoTagModel = new VideoTagModel();
            videoTagModel.setImgId(R.mipmap.ic_choose_fromat);
            arrayList.add(videoTagModel);
        } else {
            VideoTagModel videoTagModel2 = new VideoTagModel();
            videoTagModel2.setImgId(R.mipmap.ic_choose_fromat2);
            arrayList.add(videoTagModel2);
        }
        if (item.original == 1) {
            VideoTagModel videoTagModel3 = new VideoTagModel();
            videoTagModel3.setImgId(R.mipmap.ic_origin_rate);
            arrayList.add(videoTagModel3);
        }
        VideoTagModel videoTagModel4 = new VideoTagModel();
        String str = item.real_quality;
        if (str != null) {
            switch (str.hashCode()) {
                case 1687:
                    if (str.equals("4K")) {
                        videoTagModel4.setImgId(R.mipmap.ic_choose_4k);
                        break;
                    }
                    break;
                case 1811:
                    if (str.equals("8K")) {
                        videoTagModel4.setImgId(R.mipmap.ic_choose_8k);
                        break;
                    }
                    break;
                case 110308:
                    if (str.equals("org")) {
                        videoTagModel4.setImgId(R.mipmap.ic_origin_rate);
                        break;
                    }
                    break;
                case 1572835:
                    if (str.equals("360p")) {
                        videoTagModel4.setImgId(R.mipmap.ic_choose_sd);
                        break;
                    }
                    break;
                case 1688155:
                    if (str.equals("720p")) {
                        videoTagModel4.setImgId(R.mipmap.ic_choose_hd);
                        break;
                    }
                    break;
                case 46737913:
                    if (str.equals("1080p")) {
                        videoTagModel4.setImgId(R.mipmap.ic_choose_fullhd);
                        break;
                    }
                    break;
            }
        }
        arrayList.add(videoTagModel4);
        if (item.hdr == 1) {
            VideoTagModel videoTagModel5 = new VideoTagModel();
            videoTagModel5.setImgId(R.mipmap.ic_video_hdr);
            arrayList.add(videoTagModel5);
        }
        if (item.colorbit > 8) {
            VideoTagModel videoTagModel6 = new VideoTagModel();
            if (item.colorbit == 10) {
                videoTagModel6.setImgId(R.mipmap.ic_color_bit_10);
            } else if (item.colorbit == 12) {
                videoTagModel6.setImgId(R.mipmap.ic_color_bit_12);
            }
            arrayList.add(videoTagModel6);
        }
        VideoTagModel videoTagModel7 = new VideoTagModel();
        String str2 = item.bitstream;
        if (str2 == null || str2.length() == 0) {
            videoTagModel7.setType(0);
        } else {
            videoTagModel7.setType(1);
            videoTagModel7.setRate(item.bitstream);
        }
        arrayList.add(videoTagModel7);
        VideoTagModel videoTagModel8 = new VideoTagModel();
        if (item.vip_only == 1) {
            videoTagModel8.setImgId(R.mipmap.ic_choose_vip);
            arrayList.add(videoTagModel8);
        }
        RecyclerView recyclerView = (RecyclerView) holder.getView(R.id.recyclerView);
        BaseQuickAdapter<VideoTagModel, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<VideoTagModel, BaseViewHolder>(arrayList) { // from class: com.movieboxpro.android.adapter.ChooseDownloadQualityAdapter$convert$tagAdapter$1
            final /* synthetic */ ArrayList<VideoTagModel> $tagList;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(R.layout.adapter_video_tag_item, arrayList);
                this.$tagList = arrayList;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder holder2, VideoTagModel item2) {
                Intrinsics.checkNotNullParameter(holder2, "holder");
                Intrinsics.checkNotNullParameter(item2, "item");
                ImageView imageView = (ImageView) holder2.getView(R.id.imageView);
                LinearLayout linearLayout = (LinearLayout) holder2.getView(R.id.ll_rate);
                TextView textView = (TextView) holder2.getView(R.id.tv_rate);
                if (item2.getType() == 1) {
                    CommonExtKt.gone(imageView);
                    CommonExtKt.visible(linearLayout);
                    textView.setText(item2.getRate());
                    return;
                }
                CommonExtKt.visible(imageView);
                CommonExtKt.gone(linearLayout);
                GlideUtils.load(getContext(), item2.getImgId(), imageView);
            }
        };
        recyclerView.setLayoutManager(new FlowLayoutManager());
        Object tag = recyclerView.getTag();
        if (tag == null || !((Boolean) tag).booleanValue()) {
            recyclerView.addItemDecoration(new SpaceItemDecoration(DensityUtils.dp2px(getContext(), 5.0f)));
            recyclerView.setTag(true);
        }
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(baseQuickAdapter);
        holder.setText(R.id.tv_name, item.filename);
        holder.setText(R.id.tv_size, item.size);
        holder.setText(R.id.tv_time, TimeUtils.formatTime3(item.dateline * 1000));
        CommonExtKt.gone((ImageView) holder.getView(R.id.iv_selected));
    }
}
