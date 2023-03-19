package com.movieboxpro.android.view.dialog;

import com.avery.subtitle.model.Subtitle;
import com.movieboxpro.android.base.CommBaseAdapter;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.view.fragment.TranscodeOnlySubtitleFragment;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PreviewLocalSubtitleDialog.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/movieboxpro/android/http/ApiException;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PreviewLocalSubtitleDialog$transcodeSubtitle$2 extends Lambda implements Function1<ApiException, Unit> {
    final /* synthetic */ PreviewLocalSubtitleDialog this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PreviewLocalSubtitleDialog$transcodeSubtitle$2(PreviewLocalSubtitleDialog previewLocalSubtitleDialog) {
        super(1);
        this.this$0 = previewLocalSubtitleDialog;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(ApiException apiException) {
        invoke2(apiException);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(ApiException it) {
        String filePath;
        int boxType;
        String id;
        int season;
        int episode;
        Intrinsics.checkNotNullParameter(it, "it");
        this.this$0.hideLoadingView();
        TranscodeOnlySubtitleFragment.Companion companion = TranscodeOnlySubtitleFragment.Companion;
        filePath = this.this$0.getFilePath();
        boxType = this.this$0.getBoxType();
        id = this.this$0.getId();
        season = this.this$0.getSeason();
        episode = this.this$0.getEpisode();
        TranscodeOnlySubtitleFragment newInstance = companion.newInstance(filePath, boxType, id, season, episode);
        final PreviewLocalSubtitleDialog previewLocalSubtitleDialog = this.this$0;
        newInstance.setListener(new TranscodeOnlySubtitleFragment.OnSelectSubtitleListener() { // from class: com.movieboxpro.android.view.dialog.PreviewLocalSubtitleDialog$transcodeSubtitle$2.1
            @Override // com.movieboxpro.android.view.fragment.TranscodeOnlySubtitleFragment.OnSelectSubtitleListener
            public void onSelectSubtitle(List<Subtitle> subtitles) {
                CommBaseAdapter commBaseAdapter;
                Intrinsics.checkNotNullParameter(subtitles, "subtitles");
                commBaseAdapter = PreviewLocalSubtitleDialog.this.adapter;
                if (commBaseAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commBaseAdapter = null;
                }
                commBaseAdapter.setList(subtitles);
            }
        });
        newInstance.show(this.this$0.getChildFragmentManager(), TranscodeOnlySubtitleFragment.class.getSimpleName());
    }
}
