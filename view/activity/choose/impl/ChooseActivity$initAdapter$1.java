package com.movieboxpro.android.view.activity.choose.impl;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
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
import com.movieboxpro.android.view.widget.CustomRecyclerView;
import java.util.ArrayList;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ChooseActivity.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "holder", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Lcom/movieboxpro/android/model/BaseMediaModel$DownloadFile;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ChooseActivity$initAdapter$1 extends Lambda implements Function2<BaseViewHolder, BaseMediaModel.DownloadFile, Unit> {
    final /* synthetic */ ChooseActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChooseActivity$initAdapter$1(ChooseActivity chooseActivity) {
        super(2);
        this.this$0 = chooseActivity;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(BaseViewHolder baseViewHolder, BaseMediaModel.DownloadFile downloadFile) {
        invoke2(baseViewHolder, downloadFile);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(final BaseViewHolder holder, BaseMediaModel.DownloadFile item) {
        Context context;
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.viewType == 1) {
            holder.setText(R.id.tvName, item.filename);
            holder.setText(R.id.tvTime, TimeUtils.formatTime3(item.dateline * 1000));
            ImageView imageView = (ImageView) holder.getView(R.id.ivMore);
            if (!item.hasOrg) {
                CommonExtKt.visible(imageView);
            } else if (item.originShow) {
                CommonExtKt.gone(imageView);
            } else {
                CommonExtKt.visible(imageView);
            }
        } else if (item.viewType == 2) {
            LinearLayout linearLayout = (LinearLayout) holder.getView(R.id.linearLayout);
            if (item.original != 1) {
                this.this$0.setViewVisibility(linearLayout, true);
            } else if (item.originShow) {
                this.this$0.setViewVisibility(linearLayout, true);
            } else {
                this.this$0.setViewVisibility(linearLayout, false);
            }
            View view = holder.getView(R.id.viewLine);
            if (item.lastItem) {
                CommonExtKt.invisible(view);
            } else {
                CommonExtKt.visible(view);
            }
            StringBuilder sb = new StringBuilder();
            String str = item.real_quality;
            Intrinsics.checkNotNullExpressionValue(str, "item.real_quality");
            Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
            String upperCase = str.toUpperCase(locale);
            Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(locale)");
            sb.append(upperCase);
            sb.append(" · ");
            sb.append((Object) item.size);
            holder.setText(R.id.tvSize, sb.toString());
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
            String str2 = item.real_quality;
            if (str2 != null) {
                switch (str2.hashCode()) {
                    case 1687:
                        if (str2.equals("4K")) {
                            videoTagModel4.setImgId(R.mipmap.ic_choose_4k);
                            break;
                        }
                        break;
                    case 1811:
                        if (str2.equals("8K")) {
                            videoTagModel4.setImgId(R.mipmap.ic_choose_8k);
                            break;
                        }
                        break;
                    case 110308:
                        if (str2.equals("org")) {
                            videoTagModel4.setImgId(R.mipmap.ic_origin_rate);
                            break;
                        }
                        break;
                    case 1572835:
                        if (str2.equals("360p")) {
                            videoTagModel4.setImgId(R.mipmap.ic_choose_sd);
                            break;
                        }
                        break;
                    case 1688155:
                        if (str2.equals("720p")) {
                            videoTagModel4.setImgId(R.mipmap.ic_choose_hd);
                            break;
                        }
                        break;
                    case 46737913:
                        if (str2.equals("1080p")) {
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
            String str3 = item.bitstream;
            if (str3 == null || str3.length() == 0) {
                videoTagModel7.setType(0);
            } else {
                videoTagModel7.setType(1);
                videoTagModel7.setRate(item.bitstream);
            }
            arrayList.add(videoTagModel7);
            ImageView imageView2 = (ImageView) holder.getView(R.id.ivVip);
            if (item.vip_only == 1) {
                CommonExtKt.visible(imageView2);
            } else {
                CommonExtKt.gone(imageView2);
            }
            CustomRecyclerView customRecyclerView = (CustomRecyclerView) holder.getView(R.id.recyclerView);
            BaseQuickAdapter<VideoTagModel, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<VideoTagModel, BaseViewHolder>(arrayList) { // from class: com.movieboxpro.android.view.activity.choose.impl.ChooseActivity$initAdapter$1$tagAdapter$1
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
                    ImageView imageView3 = (ImageView) holder2.getView(R.id.imageView);
                    LinearLayout linearLayout2 = (LinearLayout) holder2.getView(R.id.ll_rate);
                    TextView textView = (TextView) holder2.getView(R.id.tv_rate);
                    if (item2.getType() == 1) {
                        CommonExtKt.gone(imageView3);
                        CommonExtKt.visible(linearLayout2);
                        textView.setText(item2.getRate());
                        return;
                    }
                    CommonExtKt.visible(imageView3);
                    CommonExtKt.gone(linearLayout2);
                    GlideUtils.load(getContext(), item2.getImgId(), imageView3);
                }
            };
            customRecyclerView.setLayoutManager(new FlowLayoutManager());
            Object tag = customRecyclerView.getTag();
            if (tag == null || !((Boolean) tag).booleanValue()) {
                context = this.this$0.context;
                customRecyclerView.addItemDecoration(new SpaceItemDecoration(DensityUtils.dp2px(context, 5.0f)));
                customRecyclerView.setTag(true);
            }
            customRecyclerView.setNestedScrollingEnabled(false);
            customRecyclerView.setAdapter(baseQuickAdapter);
            customRecyclerView.setEnabled(false);
            customRecyclerView.setItemClick(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.choose.impl.-$$Lambda$ChooseActivity$initAdapter$1$7hN5wfTFidyCQVd-1gJ7IK4J_oc
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    ChooseActivity$initAdapter$1.m338invoke$lambda0(BaseViewHolder.this, view2);
                }
            });
            baseQuickAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.choose.impl.-$$Lambda$ChooseActivity$initAdapter$1$9bNhhqECr8S_RznYQI_XNgCDkGE
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view2, int i) {
                    ChooseActivity$initAdapter$1.m339invoke$lambda1(BaseViewHolder.this, baseQuickAdapter2, view2, i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: invoke$lambda-0  reason: not valid java name */
    public static final void m338invoke$lambda0(BaseViewHolder holder, View view) {
        Intrinsics.checkNotNullParameter(holder, "$holder");
        holder.itemView.performClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: invoke$lambda-1  reason: not valid java name */
    public static final void m339invoke$lambda1(BaseViewHolder holder, BaseQuickAdapter noName_0, View view, int i) {
        Intrinsics.checkNotNullParameter(holder, "$holder");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(view, "view");
        holder.itemView.performClick();
    }
}
