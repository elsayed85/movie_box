package com.movieboxpro.android.view.dialog;

import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.avery.subtitle.model.Subtitle;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.CommBaseAdapter;
import com.movieboxpro.android.databinding.DialogPreviewLocalSubtitleBinding;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.SpanUtils;
import com.movieboxpro.android.view.fragment.TranscodeOnlySubtitleFragment;
import com.movieboxpro.android.view.widget.CustomClickableSpan;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PreviewLocalSubtitleDialog.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012P\u0010\u0002\u001aL\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004 \u0005*&\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004\u0018\u00010\u0003j\u0012\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004\u0018\u0001`\u00060\u0003j\u0010\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004`\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "it", "Ljava/util/ArrayList;", "Lcom/avery/subtitle/model/Subtitle;", "kotlin.jvm.PlatformType", "Lkotlin/collections/ArrayList;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PreviewLocalSubtitleDialog$transcodeSubtitle$4 extends Lambda implements Function1<ArrayList<Subtitle>, Unit> {
    final /* synthetic */ PreviewLocalSubtitleDialog this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PreviewLocalSubtitleDialog$transcodeSubtitle$4(PreviewLocalSubtitleDialog previewLocalSubtitleDialog) {
        super(1);
        this.this$0 = previewLocalSubtitleDialog;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(ArrayList<Subtitle> arrayList) {
        invoke2(arrayList);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(ArrayList<Subtitle> arrayList) {
        DialogPreviewLocalSubtitleBinding dialogPreviewLocalSubtitleBinding;
        DialogPreviewLocalSubtitleBinding dialogPreviewLocalSubtitleBinding2;
        DialogPreviewLocalSubtitleBinding dialogPreviewLocalSubtitleBinding3;
        DialogPreviewLocalSubtitleBinding dialogPreviewLocalSubtitleBinding4;
        CommBaseAdapter commBaseAdapter;
        dialogPreviewLocalSubtitleBinding = this.this$0.binding;
        CommBaseAdapter commBaseAdapter2 = null;
        if (dialogPreviewLocalSubtitleBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPreviewLocalSubtitleBinding = null;
        }
        LinearLayout linearLayout = dialogPreviewLocalSubtitleBinding.llConfirm;
        Intrinsics.checkNotNullExpressionValue(linearLayout, "binding.llConfirm");
        CommonExtKt.visible(linearLayout);
        dialogPreviewLocalSubtitleBinding2 = this.this$0.binding;
        if (dialogPreviewLocalSubtitleBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPreviewLocalSubtitleBinding2 = null;
        }
        SpanUtils with = SpanUtils.with(dialogPreviewLocalSubtitleBinding2.tvTranscode);
        Intrinsics.checkNotNullExpressionValue(with, "with(binding.tvTranscode)");
        SpanUtils addText = CommonExtKt.addText(CommonExtKt.addText(with, "Messy Subtitles? ", 14, R.color.white_87alpha), "Transcode", 14, R.color.color_main_blue);
        int colorInt = CommonExtKt.colorInt(this.this$0, (int) R.color.color_main_blue);
        final PreviewLocalSubtitleDialog previewLocalSubtitleDialog = this.this$0;
        addText.setClickSpan(new CustomClickableSpan(colorInt) { // from class: com.movieboxpro.android.view.dialog.PreviewLocalSubtitleDialog$transcodeSubtitle$4.1
            @Override // android.text.style.ClickableSpan
            public void onClick(View p0) {
                String filePath;
                int boxType;
                String id;
                int season;
                int episode;
                Intrinsics.checkNotNullParameter(p0, "p0");
                TranscodeOnlySubtitleFragment.Companion companion = TranscodeOnlySubtitleFragment.Companion;
                filePath = PreviewLocalSubtitleDialog.this.getFilePath();
                boxType = PreviewLocalSubtitleDialog.this.getBoxType();
                id = PreviewLocalSubtitleDialog.this.getId();
                season = PreviewLocalSubtitleDialog.this.getSeason();
                episode = PreviewLocalSubtitleDialog.this.getEpisode();
                TranscodeOnlySubtitleFragment newInstance = companion.newInstance(filePath, boxType, id, season, episode);
                final PreviewLocalSubtitleDialog previewLocalSubtitleDialog2 = PreviewLocalSubtitleDialog.this;
                newInstance.setListener(new TranscodeOnlySubtitleFragment.OnSelectSubtitleListener() { // from class: com.movieboxpro.android.view.dialog.PreviewLocalSubtitleDialog$transcodeSubtitle$4$1$onClick$1
                    @Override // com.movieboxpro.android.view.fragment.TranscodeOnlySubtitleFragment.OnSelectSubtitleListener
                    public void onSelectSubtitle(List<Subtitle> subtitles) {
                        CommBaseAdapter commBaseAdapter3;
                        Intrinsics.checkNotNullParameter(subtitles, "subtitles");
                        commBaseAdapter3 = PreviewLocalSubtitleDialog.this.adapter;
                        if (commBaseAdapter3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("adapter");
                            commBaseAdapter3 = null;
                        }
                        commBaseAdapter3.setList(subtitles);
                    }
                });
                newInstance.show(PreviewLocalSubtitleDialog.this.getChildFragmentManager(), TranscodeOnlySubtitleFragment.class.getSimpleName());
            }
        }).setBold().create();
        this.this$0.hideLoadingView();
        this.this$0.adapter = new CommBaseAdapter(R.layout.adapter_simple_subtitle_item, AnonymousClass2.INSTANCE, arrayList);
        dialogPreviewLocalSubtitleBinding3 = this.this$0.binding;
        if (dialogPreviewLocalSubtitleBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPreviewLocalSubtitleBinding3 = null;
        }
        dialogPreviewLocalSubtitleBinding3.recyclerView.setLayoutManager(new LinearLayoutManager(this.this$0.getContext()));
        dialogPreviewLocalSubtitleBinding4 = this.this$0.binding;
        if (dialogPreviewLocalSubtitleBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPreviewLocalSubtitleBinding4 = null;
        }
        RecyclerView recyclerView = dialogPreviewLocalSubtitleBinding4.recyclerView;
        commBaseAdapter = this.this$0.adapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            commBaseAdapter2 = commBaseAdapter;
        }
        recyclerView.setAdapter(commBaseAdapter2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PreviewLocalSubtitleDialog.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Lcom/avery/subtitle/model/Subtitle;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
    /* renamed from: com.movieboxpro.android.view.dialog.PreviewLocalSubtitleDialog$transcodeSubtitle$4$2  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass2 extends Lambda implements Function2<BaseViewHolder, Subtitle, Unit> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        AnonymousClass2() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(BaseViewHolder baseViewHolder, Subtitle subtitle) {
            invoke2(baseViewHolder, subtitle);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(BaseViewHolder helper, Subtitle item) {
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            helper.setText(R.id.textView, Html.fromHtml(item.content));
        }
    }
}
