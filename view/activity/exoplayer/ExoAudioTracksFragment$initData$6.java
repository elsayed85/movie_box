package com.movieboxpro.android.view.activity.exoplayer;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.AudioTrackItemAdapter;
import com.movieboxpro.android.model.AudioTrackItem;
import com.movieboxpro.android.model.AudioTrackModel;
import com.movieboxpro.android.utils.CommonExtKt;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ExoAudioTracksFragment.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Lcom/movieboxpro/android/model/AudioTrackItem;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ExoAudioTracksFragment$initData$6 extends Lambda implements Function2<BaseViewHolder, AudioTrackItem, Unit> {
    final /* synthetic */ ExoAudioTracksFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExoAudioTracksFragment$initData$6(ExoAudioTracksFragment exoAudioTracksFragment) {
        super(2);
        this.this$0 = exoAudioTracksFragment;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(BaseViewHolder baseViewHolder, AudioTrackItem audioTrackItem) {
        invoke2(baseViewHolder, audioTrackItem);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(BaseViewHolder helper, final AudioTrackItem item) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        RecyclerView recyclerView = (RecyclerView) helper.getView(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.this$0.getContext()));
        final AudioTrackItemAdapter audioTrackItemAdapter = new AudioTrackItemAdapter(item.getTracks());
        final ExoAudioTracksFragment exoAudioTracksFragment = this.this$0;
        audioTrackItemAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.-$$Lambda$ExoAudioTracksFragment$initData$6$FseF5t8EDc5gvREP07tvW-MJie8
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ExoAudioTracksFragment$initData$6.m363invoke$lambda1(AudioTrackItem.this, exoAudioTracksFragment, audioTrackItemAdapter, baseQuickAdapter, view, i);
            }
        });
        recyclerView.setAdapter(audioTrackItemAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        helper.setText(R.id.tvName, item.getTitle());
        TextView textView = (TextView) helper.getView(R.id.tvTime);
        FrameLayout frameLayout = (FrameLayout) helper.getView(R.id.frameLayout);
        if (helper.getAbsoluteAdapterPosition() == 0) {
            CommonExtKt.gone(frameLayout);
        } else {
            CommonExtKt.visible(frameLayout);
            helper.setText(R.id.tvTime, item.getTime());
        }
        helper.setVisible(R.id.ivMore, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: invoke$lambda-1  reason: not valid java name */
    public static final void m363invoke$lambda1(AudioTrackItem item, ExoAudioTracksFragment this$0, AudioTrackItemAdapter adapter, BaseQuickAdapter noName_0, View noName_1, int i) {
        AudioTrackModel audioTrackModel;
        Intrinsics.checkNotNullParameter(item, "$item");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(adapter, "$adapter");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        ArrayList<AudioTrackModel> tracks = item.getTracks();
        if (tracks == null || (audioTrackModel = tracks.get(i)) == null) {
            return;
        }
        this$0.itemClick(audioTrackModel, i, adapter);
    }
}
