package com.movieboxpro.android.view.activity.vlcvideoplayer;

import android.widget.ProgressBar;
import com.dueeeke.model.MediaQualityInfo;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.CommBaseAdapter;
import com.movieboxpro.android.model.AudioTrackItem;
import com.movieboxpro.android.model.AudioTrackModel;
import com.movieboxpro.android.model.AudioTrackResponse;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.TimeUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AudioTracksFragment.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/movieboxpro/android/model/AudioTrackResponse;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AudioTracksFragment$getAudioTracks$3 extends Lambda implements Function1<AudioTrackResponse, Unit> {
    final /* synthetic */ AudioTracksFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AudioTracksFragment$getAudioTracks$3(AudioTracksFragment audioTracksFragment) {
        super(1);
        this.this$0 = audioTracksFragment;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(AudioTrackResponse audioTrackResponse) {
        invoke2(audioTrackResponse);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(AudioTrackResponse audioTrackResponse) {
        ArrayList arrayList;
        ArrayList arrayList2;
        ArrayList arrayList3;
        CommBaseAdapter commBaseAdapter;
        MediaQualityInfo mediaQualityInfo;
        MediaQualityInfo mediaQualityInfo2;
        ProgressBar progressBar = (ProgressBar) this.this$0._$_findCachedViewById(R.id.progressBar);
        Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
        CommonExtKt.gone(progressBar);
        ArrayList arrayList4 = new ArrayList();
        List<AudioTrackModel> audio_list = audioTrackResponse.getAudio_list();
        CommBaseAdapter commBaseAdapter2 = null;
        if (audio_list != null) {
            AudioTracksFragment audioTracksFragment = this.this$0;
            for (AudioTrackModel audioTrackModel : audio_list) {
                audioTrackModel.setId(0);
                String bitstream = audioTrackModel.getBitstream();
                Intrinsics.checkNotNullExpressionValue(bitstream, "item.bitstream");
                audioTrackModel.setBitstream(StringsKt.trim((CharSequence) StringsKt.replace$default(bitstream, "kb/s", "", false, 4, (Object) null)).toString());
                String fid = audioTrackModel.getFid();
                mediaQualityInfo2 = audioTracksFragment.mediaQuality;
                if (Intrinsics.areEqual(fid, String.valueOf(mediaQualityInfo2 == null ? null : Integer.valueOf(mediaQualityInfo2.fid)))) {
                    arrayList4.add(audioTrackModel);
                }
            }
        }
        List<AudioTrackModel> audio_list2 = audioTrackResponse.getAudio_list();
        if (audio_list2 != null) {
            AudioTracksFragment audioTracksFragment2 = this.this$0;
            for (AudioTrackModel audioTrackModel2 : audio_list2) {
                String fid2 = audioTrackModel2.getFid();
                mediaQualityInfo = audioTracksFragment2.mediaQuality;
                if (!Intrinsics.areEqual(fid2, String.valueOf(mediaQualityInfo == null ? null : Integer.valueOf(mediaQualityInfo.fid)))) {
                    arrayList4.add(audioTrackModel2);
                }
            }
        }
        arrayList = this.this$0.onlineTracks;
        ArrayList arrayList5 = arrayList4;
        arrayList.addAll(arrayList5);
        arrayList2 = this.this$0.audioTrackList;
        arrayList2.addAll(arrayList5);
        ArrayList arrayList6 = new ArrayList();
        arrayList3 = this.this$0.audioTrackList;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : arrayList3) {
            String video_file_name = ((AudioTrackModel) obj).getVideo_file_name();
            Object obj2 = linkedHashMap.get(video_file_name);
            if (obj2 == null) {
                obj2 = (List) new ArrayList();
                linkedHashMap.put(video_file_name, obj2);
            }
            ((List) obj2).add(obj);
        }
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            arrayList6.add(new AudioTrackItem((String) entry.getKey(), TimeUtils.formatTime3(((AudioTrackModel) ((List) entry.getValue()).get(0)).getAdd_time() * 1000), new ArrayList((Collection) entry.getValue())));
        }
        commBaseAdapter = this.this$0.itemAdapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("itemAdapter");
        } else {
            commBaseAdapter2 = commBaseAdapter;
        }
        commBaseAdapter2.setList(arrayList6);
    }
}
