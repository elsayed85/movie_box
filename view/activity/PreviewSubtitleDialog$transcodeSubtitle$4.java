package com.movieboxpro.android.view.activity;

import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.avery.subtitle.model.Subtitle;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.CommBaseAdapter;
import com.movieboxpro.android.databinding.DialogPreviewSubtitleBinding;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.SpanUtils;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.widget.CustomClickableSpan;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PreviewSubtitleDialog.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012P\u0010\u0002\u001aL\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004 \u0005*&\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004\u0018\u00010\u0003j\u0012\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004\u0018\u0001`\u00060\u0003j\u0010\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004`\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "it", "Ljava/util/ArrayList;", "Lcom/avery/subtitle/model/Subtitle;", "kotlin.jvm.PlatformType", "Lkotlin/collections/ArrayList;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PreviewSubtitleDialog$transcodeSubtitle$4 extends Lambda implements Function1<ArrayList<Subtitle>, Unit> {
    final /* synthetic */ PreviewSubtitleDialog this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PreviewSubtitleDialog$transcodeSubtitle$4(PreviewSubtitleDialog previewSubtitleDialog) {
        super(1);
        this.this$0 = previewSubtitleDialog;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(ArrayList<Subtitle> arrayList) {
        invoke2(arrayList);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(ArrayList<Subtitle> arrayList) {
        DialogPreviewSubtitleBinding dialogPreviewSubtitleBinding;
        DialogPreviewSubtitleBinding dialogPreviewSubtitleBinding2;
        DialogPreviewSubtitleBinding dialogPreviewSubtitleBinding3;
        DialogPreviewSubtitleBinding dialogPreviewSubtitleBinding4;
        CommBaseAdapter commBaseAdapter;
        dialogPreviewSubtitleBinding = this.this$0.binding;
        CommBaseAdapter commBaseAdapter2 = null;
        if (dialogPreviewSubtitleBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPreviewSubtitleBinding = null;
        }
        LinearLayout linearLayout = dialogPreviewSubtitleBinding.llConfirm;
        Intrinsics.checkNotNullExpressionValue(linearLayout, "binding.llConfirm");
        CommonExtKt.visible(linearLayout);
        dialogPreviewSubtitleBinding2 = this.this$0.binding;
        if (dialogPreviewSubtitleBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPreviewSubtitleBinding2 = null;
        }
        SpanUtils with = SpanUtils.with(dialogPreviewSubtitleBinding2.tvTranscode);
        Intrinsics.checkNotNullExpressionValue(with, "with(binding.tvTranscode)");
        SpanUtils addText = CommonExtKt.addText(CommonExtKt.addText(with, "Messy Subtitles? ", 14, R.color.white_87alpha), "Transcode", 14, R.color.color_main_blue);
        int colorInt = CommonExtKt.colorInt(this.this$0, (int) R.color.color_main_blue);
        final PreviewSubtitleDialog previewSubtitleDialog = this.this$0;
        addText.setClickSpan(new CustomClickableSpan(colorInt) { // from class: com.movieboxpro.android.view.activity.PreviewSubtitleDialog$transcodeSubtitle$4.1
            @Override // android.text.style.ClickableSpan
            public void onClick(View p0) {
                DialogAction.ActionListener actionListener;
                Intrinsics.checkNotNullParameter(p0, "p0");
                actionListener = PreviewSubtitleDialog.this.listener;
                if (actionListener != null) {
                    actionListener.onClick();
                }
                PreviewSubtitleDialog.this.dismissAllowingStateLoss();
            }
        }).setBold().create();
        this.this$0.hideLoadingView();
        this.this$0.adapter = new CommBaseAdapter(R.layout.adapter_simple_subtitle_item, AnonymousClass2.INSTANCE, arrayList);
        dialogPreviewSubtitleBinding3 = this.this$0.binding;
        if (dialogPreviewSubtitleBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPreviewSubtitleBinding3 = null;
        }
        dialogPreviewSubtitleBinding3.recyclerView.setLayoutManager(new LinearLayoutManager(this.this$0.getContext()));
        dialogPreviewSubtitleBinding4 = this.this$0.binding;
        if (dialogPreviewSubtitleBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPreviewSubtitleBinding4 = null;
        }
        RecyclerView recyclerView = dialogPreviewSubtitleBinding4.recyclerView;
        commBaseAdapter = this.this$0.adapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            commBaseAdapter2 = commBaseAdapter;
        }
        recyclerView.setAdapter(commBaseAdapter2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PreviewSubtitleDialog.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Lcom/avery/subtitle/model/Subtitle;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
    /* renamed from: com.movieboxpro.android.view.activity.PreviewSubtitleDialog$transcodeSubtitle$4$2  reason: invalid class name */
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
