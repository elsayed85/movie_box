package com.movieboxpro.android.view.videocontroller.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.dueeeke.model.MediaQualityInfo;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.library.flowlayout.FlowLayoutManager;
import com.library.flowlayout.SpaceItemDecoration;
import com.movieboxpro.android.R;
import com.movieboxpro.android.model.VideoTagModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.view.widget.CustomRecyclerView;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
/* compiled from: VideoDefinitionFragment.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "holder", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Lcom/dueeeke/model/MediaQualityInfo;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class VideoDefinitionFragment$initData$4 extends Lambda implements Function2<BaseViewHolder, MediaQualityInfo, Unit> {
    final /* synthetic */ VideoDefinitionFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoDefinitionFragment$initData$4(VideoDefinitionFragment videoDefinitionFragment) {
        super(2);
        this.this$0 = videoDefinitionFragment;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(BaseViewHolder baseViewHolder, MediaQualityInfo mediaQualityInfo) {
        invoke2(baseViewHolder, mediaQualityInfo);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(final BaseViewHolder holder, MediaQualityInfo item) {
        Object tag;
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        boolean z = true;
        if (item.getViewType() == 1) {
            holder.setText(R.id.tvName, item.getFileName());
            holder.setText(R.id.tvTime, item.getTime());
            ImageView imageView = (ImageView) holder.getView(R.id.ivMore);
            if (!item.isHasOrg()) {
                CommonExtKt.gone(imageView);
            } else if (item.isOriginShow()) {
                CommonExtKt.gone(imageView);
            } else {
                CommonExtKt.visible(imageView);
            }
        } else if (item.getViewType() == 4) {
            ImageView imageView2 = (ImageView) holder.getView(R.id.ivSelect);
            TextView textView = (TextView) holder.getView(R.id.tvQuality);
            if (item.isSelect()) {
                CommonExtKt.visible(imageView2);
                CommonExtKt.visible(textView);
                textView.setText(item.getReal_quality());
                this.this$0.selectedPosition = holder.getAdapterPosition();
            } else {
                CommonExtKt.gone(textView);
                CommonExtKt.gone(imageView2);
            }
            TextView textView2 = (TextView) holder.getView(R.id.tvSpeed);
            String speed = item.getSpeed();
            if (speed != null && !StringsKt.isBlank(speed)) {
                z = false;
            }
            if (z) {
                CommonExtKt.gone(textView2);
                return;
            }
            CommonExtKt.visible(textView2);
            textView2.setText(Intrinsics.stringPlus("Last Speed Test: ", item.getSpeed()));
        } else if (item.getViewType() == 2) {
            LinearLayout linearLayout = (LinearLayout) holder.getView(R.id.linearLayout);
            if (item.getOriginal() != 1) {
                this.this$0.setViewVisibility(linearLayout, true);
            } else if (item.isOriginShow()) {
                this.this$0.setViewVisibility(linearLayout, true);
            } else {
                this.this$0.setViewVisibility(linearLayout, false);
            }
            ImageView imageView3 = (ImageView) holder.getView(R.id.ivSelect);
            if (item.isSelect()) {
                CommonExtKt.visible(imageView3);
                this.this$0.selectedPosition = holder.getAdapterPosition();
            } else {
                CommonExtKt.gone(imageView3);
            }
            View view = holder.getView(R.id.viewLine);
            if (item.isLastItem()) {
                CommonExtKt.invisible(view);
            } else {
                CommonExtKt.visible(view);
            }
            holder.setText(R.id.tvSize, item.getSize());
            final ArrayList arrayList = new ArrayList();
            if (item.getH265() == 1) {
                VideoTagModel videoTagModel = new VideoTagModel();
                videoTagModel.setImgId(R.mipmap.ic_choose_fromat);
                arrayList.add(videoTagModel);
            } else {
                VideoTagModel videoTagModel2 = new VideoTagModel();
                videoTagModel2.setImgId(R.mipmap.ic_choose_fromat2);
                arrayList.add(videoTagModel2);
            }
            if (item.getOriginal() == 1) {
                VideoTagModel videoTagModel3 = new VideoTagModel();
                videoTagModel3.setImgId(R.mipmap.ic_origin_rate);
                arrayList.add(videoTagModel3);
            }
            VideoTagModel videoTagModel4 = new VideoTagModel();
            String real_quality = item.getReal_quality();
            if (real_quality != null) {
                switch (real_quality.hashCode()) {
                    case 1687:
                        if (real_quality.equals("4K")) {
                            videoTagModel4.setImgId(R.mipmap.ic_choose_4k);
                            break;
                        }
                        break;
                    case 1811:
                        if (real_quality.equals("8K")) {
                            videoTagModel4.setImgId(R.mipmap.ic_choose_8k);
                            break;
                        }
                        break;
                    case 110308:
                        if (real_quality.equals("org")) {
                            videoTagModel4.setImgId(R.mipmap.ic_origin_rate);
                            break;
                        }
                        break;
                    case 1572835:
                        if (real_quality.equals("360p")) {
                            videoTagModel4.setImgId(R.mipmap.ic_choose_sd);
                            break;
                        }
                        break;
                    case 1688155:
                        if (real_quality.equals("720p")) {
                            videoTagModel4.setImgId(R.mipmap.ic_choose_hd);
                            break;
                        }
                        break;
                    case 46737913:
                        if (real_quality.equals("1080p")) {
                            videoTagModel4.setImgId(R.mipmap.ic_choose_fullhd);
                            break;
                        }
                        break;
                }
            }
            arrayList.add(videoTagModel4);
            if (item.getHdr() == 1) {
                VideoTagModel videoTagModel5 = new VideoTagModel();
                videoTagModel5.setImgId(R.mipmap.ic_video_hdr);
                arrayList.add(videoTagModel5);
            }
            if (item.getColorbit() > 8) {
                VideoTagModel videoTagModel6 = new VideoTagModel();
                if (item.getColorbit() == 10) {
                    videoTagModel6.setImgId(R.mipmap.ic_color_bit_10);
                } else if (item.getColorbit() == 12) {
                    videoTagModel6.setImgId(R.mipmap.ic_color_bit_12);
                }
                arrayList.add(videoTagModel6);
            }
            VideoTagModel videoTagModel7 = new VideoTagModel();
            String bitstream = item.getBitstream();
            if (bitstream == null || bitstream.length() == 0) {
                videoTagModel7.setType(0);
            } else {
                videoTagModel7.setType(1);
                videoTagModel7.setRate(item.getBitstream());
            }
            arrayList.add(videoTagModel7);
            ImageView imageView4 = (ImageView) holder.getView(R.id.ivVip);
            int isVip = item.getIsVip();
            if (isVip == null) {
                isVip = 0;
            }
            if (Intrinsics.areEqual(isVip, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                String path = item.getPath();
                if (path != null && StringsKt.startsWith$default(path, "http", false, 2, (Object) null)) {
                    CommonExtKt.visible(imageView4);
                    CustomRecyclerView customRecyclerView = (CustomRecyclerView) holder.getView(R.id.recyclerView);
                    BaseQuickAdapter<VideoTagModel, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<VideoTagModel, BaseViewHolder>(arrayList) { // from class: com.movieboxpro.android.view.videocontroller.fragment.VideoDefinitionFragment$initData$4$tagAdapter$1
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
                            ImageView imageView5 = (ImageView) holder2.getView(R.id.imageView);
                            LinearLayout linearLayout2 = (LinearLayout) holder2.getView(R.id.ll_rate);
                            TextView textView3 = (TextView) holder2.getView(R.id.tv_rate);
                            if (item2.getType() == 1) {
                                CommonExtKt.gone(imageView5);
                                CommonExtKt.visible(linearLayout2);
                                textView3.setText(item2.getRate());
                                return;
                            }
                            CommonExtKt.visible(imageView5);
                            CommonExtKt.gone(linearLayout2);
                            GlideUtils.load(getContext(), item2.getImgId(), imageView5);
                        }
                    };
                    customRecyclerView.setLayoutManager(new FlowLayoutManager());
                    tag = customRecyclerView.getTag();
                    if (tag != null || !((Boolean) tag).booleanValue()) {
                        customRecyclerView.addItemDecoration(new SpaceItemDecoration(DensityUtils.dp2px(this.this$0.getContext(), 5.0f)));
                        customRecyclerView.setTag(true);
                    }
                    customRecyclerView.setNestedScrollingEnabled(false);
                    customRecyclerView.setAdapter(baseQuickAdapter);
                    customRecyclerView.setItemClick(new View.OnClickListener() { // from class: com.movieboxpro.android.view.videocontroller.fragment.-$$Lambda$VideoDefinitionFragment$initData$4$stjnNNkcyvrE7kBw10Ul32ly-Gw
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view2) {
                            VideoDefinitionFragment$initData$4.m1415invoke$lambda0(BaseViewHolder.this, view2);
                        }
                    });
                    baseQuickAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.videocontroller.fragment.-$$Lambda$VideoDefinitionFragment$initData$4$F3K9Ex9Wma94ASX7UgVAiUKqqS0
                        @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                        public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view2, int i) {
                            VideoDefinitionFragment$initData$4.m1416invoke$lambda1(BaseViewHolder.this, baseQuickAdapter2, view2, i);
                        }
                    });
                }
            }
            CommonExtKt.gone(imageView4);
            CustomRecyclerView customRecyclerView2 = (CustomRecyclerView) holder.getView(R.id.recyclerView);
            BaseQuickAdapter<VideoTagModel, BaseViewHolder> baseQuickAdapter2 = new BaseQuickAdapter<VideoTagModel, BaseViewHolder>(arrayList) { // from class: com.movieboxpro.android.view.videocontroller.fragment.VideoDefinitionFragment$initData$4$tagAdapter$1
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
                    ImageView imageView5 = (ImageView) holder2.getView(R.id.imageView);
                    LinearLayout linearLayout2 = (LinearLayout) holder2.getView(R.id.ll_rate);
                    TextView textView3 = (TextView) holder2.getView(R.id.tv_rate);
                    if (item2.getType() == 1) {
                        CommonExtKt.gone(imageView5);
                        CommonExtKt.visible(linearLayout2);
                        textView3.setText(item2.getRate());
                        return;
                    }
                    CommonExtKt.visible(imageView5);
                    CommonExtKt.gone(linearLayout2);
                    GlideUtils.load(getContext(), item2.getImgId(), imageView5);
                }
            };
            customRecyclerView2.setLayoutManager(new FlowLayoutManager());
            tag = customRecyclerView2.getTag();
            if (tag != null) {
            }
            customRecyclerView2.addItemDecoration(new SpaceItemDecoration(DensityUtils.dp2px(this.this$0.getContext(), 5.0f)));
            customRecyclerView2.setTag(true);
            customRecyclerView2.setNestedScrollingEnabled(false);
            customRecyclerView2.setAdapter(baseQuickAdapter2);
            customRecyclerView2.setItemClick(new View.OnClickListener() { // from class: com.movieboxpro.android.view.videocontroller.fragment.-$$Lambda$VideoDefinitionFragment$initData$4$stjnNNkcyvrE7kBw10Ul32ly-Gw
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    VideoDefinitionFragment$initData$4.m1415invoke$lambda0(BaseViewHolder.this, view2);
                }
            });
            baseQuickAdapter2.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.videocontroller.fragment.-$$Lambda$VideoDefinitionFragment$initData$4$F3K9Ex9Wma94ASX7UgVAiUKqqS0
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter22, View view2, int i) {
                    VideoDefinitionFragment$initData$4.m1416invoke$lambda1(BaseViewHolder.this, baseQuickAdapter22, view2, i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: invoke$lambda-0  reason: not valid java name */
    public static final void m1415invoke$lambda0(BaseViewHolder holder, View view) {
        Intrinsics.checkNotNullParameter(holder, "$holder");
        holder.itemView.performClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: invoke$lambda-1  reason: not valid java name */
    public static final void m1416invoke$lambda1(BaseViewHolder holder, BaseQuickAdapter noName_0, View view, int i) {
        Intrinsics.checkNotNullParameter(holder, "$holder");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(view, "view");
        holder.itemView.performClick();
    }
}
