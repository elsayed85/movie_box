package com.movieboxpro.android.view.activity.vlcvideoplayer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.dueeeke.model.MediaQualityInfo;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.AudioTrackItemAdapter;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseLazyFragment;
import com.movieboxpro.android.base.CommBaseAdapter;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.AudioTrackItem;
import com.movieboxpro.android.model.AudioTrackModel;
import com.movieboxpro.android.model.AudioTrackResponse;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.EventUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.view.activity.user.VipActivity;
import com.movieboxpro.android.view.videocontroller.VlcPlayer;
import com.uber.autodispose.ObservableSubscribeProxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.interfaces.IMedia;
/* compiled from: AudioTracksFragment.kt */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 82\u00020\u0001:\u000289B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004H\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u001eH\u0002J\b\u0010 \u001a\u00020\u001eH\u0002J\u0006\u0010!\u001a\u00020\"J \u0010#\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\u00042\u0006\u0010&\u001a\u00020'H\u0002J&\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-2\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J\b\u00100\u001a\u00020\u001eH\u0016J\u001a\u00101\u001a\u00020\u001e2\u0006\u00102\u001a\u00020)2\b\u0010.\u001a\u0004\u0018\u00010/H\u0016JB\u00103\u001a\u00020\u001e2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0016\u00104\u001a\u0012\u0012\u0004\u0012\u0002050\u0006j\b\u0012\u0004\u0012\u000205`\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\t\u001a\u00020\u00042\u0006\u00106\u001a\u00020\u0004J\u000e\u00107\u001a\u00020\u001e2\u0006\u0010\u0011\u001a\u00020\u0012R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lcom/movieboxpro/android/view/activity/vlcvideoplayer/AudioTracksFragment;", "Lcom/movieboxpro/android/base/BaseLazyFragment;", "()V", "audioTrackIndex", "", "audioTrackList", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/AudioTrackModel;", "Lkotlin/collections/ArrayList;", "boxType", "episode", "id", "", "itemAdapter", "Lcom/movieboxpro/android/base/CommBaseAdapter;", "Lcom/movieboxpro/android/model/AudioTrackItem;", "lastPos", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/activity/vlcvideoplayer/AudioTracksFragment$OnAudioTrackListener;", "maxId", "media", "Lorg/videolan/libvlc/Media;", "mediaQuality", "Lcom/dueeeke/model/MediaQualityInfo;", "onlineTracks", "season", "getAudioTrackInfo", "Lorg/videolan/libvlc/interfaces/IMedia$AudioTrack;", FirebaseAnalytics.Param.INDEX, "getAudioTracks", "", "initData", "initListener", "isFragmentVisible", "", "itemClick", "item", "position", "adapter", "Lcom/movieboxpro/android/adapter/AudioTrackItemAdapter;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onFragmentResume", "onViewCreated", "view", "resetAudioTracks", "tracks", "Lorg/videolan/libvlc/MediaPlayer$TrackDescription;", "pos", "setListener", "Companion", "OnAudioTrackListener", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AudioTracksFragment extends BaseLazyFragment {
    public static final Companion Companion = new Companion(null);
    private String id;
    private CommBaseAdapter<AudioTrackItem> itemAdapter;
    private OnAudioTrackListener listener;
    private int maxId;
    private Media media;
    private MediaQualityInfo mediaQuality;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private int lastPos = -1;
    private ArrayList<AudioTrackModel> audioTrackList = new ArrayList<>();
    private int season = 1;
    private int episode = 1;
    private int boxType = 1;
    private int audioTrackIndex = 1;
    private ArrayList<AudioTrackModel> onlineTracks = new ArrayList<>();

    /* compiled from: AudioTracksFragment.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J4\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u0005H&¨\u0006\u000e"}, d2 = {"Lcom/movieboxpro/android/view/activity/vlcvideoplayer/AudioTracksFragment$OnAudioTrackListener;", "", "onAdjustAudioDelay", "", "ossId", "", "onAudioClick", "id", "", "url", "isAdded", "", "needVip", "language", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface OnAudioTrackListener {
        void onAdjustAudioDelay(String str);

        void onAudioClick(int i, String str, boolean z, boolean z2, String str2);
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    @Override // com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final void setListener(OnAudioTrackListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_audio_tracks, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        initData();
        initListener();
        getAudioTracks();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void itemClick(AudioTrackModel audioTrackModel, int i, AudioTrackItemAdapter audioTrackItemAdapter) {
        boolean z;
        boolean z2;
        if (audioTrackModel.isSelect()) {
            return;
        }
        if (audioTrackModel.getVip_only() == 1 && App.getUserData().isvip != 1) {
            Context context = getContext();
            if (context == null) {
                return;
            }
            context.startActivity(new Intent(getContext(), VipActivity.class));
            return;
        }
        CommBaseAdapter<AudioTrackItem> commBaseAdapter = this.itemAdapter;
        CommBaseAdapter<AudioTrackItem> commBaseAdapter2 = null;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("itemAdapter");
            commBaseAdapter = null;
        }
        for (AudioTrackItem audioTrackItem : commBaseAdapter.getData()) {
            ArrayList<AudioTrackModel> tracks = audioTrackItem.getTracks();
            if (tracks != null) {
                for (AudioTrackModel audioTrackModel2 : tracks) {
                    audioTrackModel2.setSelect(false);
                }
            }
        }
        CommBaseAdapter<AudioTrackItem> commBaseAdapter3 = this.itemAdapter;
        if (commBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("itemAdapter");
        } else {
            commBaseAdapter2 = commBaseAdapter3;
        }
        commBaseAdapter2.notifyDataSetChanged();
        if (audioTrackModel.getVip_only() == 1) {
            if (App.getUserData().isvip == 1) {
                if (audioTrackModel.getId() == 0) {
                    int i2 = this.maxId + 1;
                    this.maxId = i2;
                    audioTrackModel.setId(i2);
                    z2 = false;
                } else {
                    z2 = true;
                }
                audioTrackModel.setSelect(true);
                this.lastPos = i;
                String download_url = audioTrackModel.getDownload_url();
                if (!(download_url == null || StringsKt.isBlank(download_url))) {
                    Context context2 = getContext();
                    if (context2 != null) {
                        CommonExtKt.onMobEvent(context2, "choose_online_audio_track");
                    }
                    EventUtils.event("选择在线音轨");
                }
                OnAudioTrackListener onAudioTrackListener = this.listener;
                if (onAudioTrackListener == null) {
                    return;
                }
                onAudioTrackListener.onAudioClick(audioTrackModel.getId(), audioTrackModel.getDownload_url(), z2, audioTrackModel.getVip_only() == 1, audioTrackModel.getLang());
                return;
            }
            Context context3 = getContext();
            if (context3 == null) {
                return;
            }
            context3.startActivity(new Intent(getContext(), VipActivity.class));
            return;
        }
        if (audioTrackModel.getId() == 0) {
            int i3 = this.maxId + 1;
            this.maxId = i3;
            audioTrackModel.setId(i3);
            z = false;
        } else {
            z = true;
        }
        audioTrackModel.setSelect(true);
        this.lastPos = i;
        OnAudioTrackListener onAudioTrackListener2 = this.listener;
        if (onAudioTrackListener2 == null) {
            return;
        }
        onAudioTrackListener2.onAudioClick(audioTrackModel.getId(), audioTrackModel.getDownload_url(), z, audioTrackModel.getVip_only() == 1, audioTrackModel.getLang());
    }

    public final void resetAudioTracks(MediaQualityInfo mediaQualityInfo, ArrayList<MediaPlayer.TrackDescription> tracks, String str, int i, int i2) {
        Intrinsics.checkNotNullParameter(tracks, "tracks");
        CommBaseAdapter<AudioTrackItem> commBaseAdapter = null;
        if (getContext() != null) {
            VlcPlayerInstance vlcPlayerInstance = VlcPlayerInstance.INSTANCE;
            Context context = App.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext()");
            IMedia media = vlcPlayerInstance.getInstance(context).getMediaPlayer().getMedia();
            this.media = media instanceof Media ? (Media) media : null;
        }
        this.mediaQuality = mediaQualityInfo;
        this.audioTrackList.clear();
        this.id = str;
        this.boxType = i;
        this.audioTrackIndex = i2;
        ArrayList arrayList = new ArrayList();
        for (MediaPlayer.TrackDescription trackDescription : tracks) {
            if (trackDescription.id != -1) {
                AudioTrackModel audioTrackModel = new AudioTrackModel();
                audioTrackModel.setName(trackDescription.name);
                audioTrackModel.setFid(String.valueOf(mediaQualityInfo == null ? null : Integer.valueOf(mediaQualityInfo.fid)));
                audioTrackModel.setVideo_file_name("Internal");
                audioTrackModel.setTime(mediaQualityInfo == null ? null : mediaQualityInfo.getTime());
                audioTrackModel.setId(trackDescription.id);
                arrayList.add(audioTrackModel);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (AudioTrackModel audioTrackModel2 : this.onlineTracks) {
            audioTrackModel2.setId(0);
            audioTrackModel2.setSelect(false);
            if (Intrinsics.areEqual(audioTrackModel2.getFid(), String.valueOf(mediaQualityInfo == null ? null : Integer.valueOf(mediaQualityInfo.fid)))) {
                arrayList2.add(audioTrackModel2);
            }
        }
        for (AudioTrackModel audioTrackModel3 : this.onlineTracks) {
            audioTrackModel3.setId(0);
            if (!Intrinsics.areEqual(audioTrackModel3.getFid(), String.valueOf(mediaQualityInfo == null ? null : Integer.valueOf(mediaQualityInfo.fid)))) {
                arrayList2.add(audioTrackModel3);
            }
        }
        this.onlineTracks.clear();
        this.onlineTracks.addAll(arrayList2);
        this.audioTrackList.addAll(arrayList);
        if (!this.audioTrackList.isEmpty()) {
            this.maxId = ((AudioTrackModel) CollectionsKt.last((List<? extends Object>) this.audioTrackList)).getId();
        }
        Iterator<AudioTrackModel> it = this.audioTrackList.iterator();
        int i3 = 0;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            int i4 = i3 + 1;
            AudioTrackModel next = it.next();
            if (next.getId() == this.audioTrackIndex) {
                next.setSelect(true);
                this.lastPos = i3;
                break;
            }
            i3 = i4;
        }
        int i5 = 0;
        for (Object obj : this.audioTrackList) {
            int i6 = i5 + 1;
            if (i5 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            AudioTrackModel audioTrackModel4 = (AudioTrackModel) obj;
            IMedia.AudioTrack audioTrackInfo = getAudioTrackInfo(i6, audioTrackModel4.getId());
            if (audioTrackInfo != null) {
                if (audioTrackInfo.bitrate == 0) {
                    audioTrackModel4.setBitstream(null);
                } else {
                    audioTrackModel4.setBitstream(String.valueOf(audioTrackInfo.bitrate / 1000));
                }
                audioTrackModel4.setChannel(String.valueOf(audioTrackInfo.channels));
                audioTrackModel4.setLang(audioTrackInfo.language);
                audioTrackModel4.setFormat(audioTrackInfo.originalCodec);
            }
            i5 = i6;
        }
        this.audioTrackList.addAll(this.onlineTracks);
        ArrayList arrayList3 = new ArrayList();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj2 : this.audioTrackList) {
            String video_file_name = ((AudioTrackModel) obj2).getVideo_file_name();
            Object obj3 = linkedHashMap.get(video_file_name);
            if (obj3 == null) {
                obj3 = (List) new ArrayList();
                linkedHashMap.put(video_file_name, obj3);
            }
            ((List) obj3).add(obj2);
        }
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            arrayList3.add(new AudioTrackItem((String) entry.getKey(), TimeUtils.formatTime3(((AudioTrackModel) ((List) entry.getValue()).get(0)).getAdd_time() * 1000), new ArrayList((Collection) entry.getValue())));
        }
        CommBaseAdapter<AudioTrackItem> commBaseAdapter2 = this.itemAdapter;
        if (commBaseAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("itemAdapter");
        } else {
            commBaseAdapter = commBaseAdapter2;
        }
        commBaseAdapter.setList(arrayList3);
    }

    private final void initListener() {
        ((TextView) _$_findCachedViewById(R.id.tvTryAgain)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.vlcvideoplayer.-$$Lambda$AudioTracksFragment$Due_KK1G5fgpFLktKugYemYJi7E
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudioTracksFragment.m890initListener$lambda9(AudioTracksFragment.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.llAudioDelay)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.vlcvideoplayer.-$$Lambda$AudioTracksFragment$MqHjnhE8WKTjd6mmQB2DF1Z4Ll4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudioTracksFragment.m889initListener$lambda13(AudioTracksFragment.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-9  reason: not valid java name */
    public static final void m890initListener$lambda9(AudioTracksFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TextView tvTryAgain = (TextView) this$0._$_findCachedViewById(R.id.tvTryAgain);
        Intrinsics.checkNotNullExpressionValue(tvTryAgain, "tvTryAgain");
        CommonExtKt.gone(tvTryAgain);
        this$0.getAudioTracks();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-13  reason: not valid java name */
    public static final void m889initListener$lambda13(AudioTracksFragment this$0, View view) {
        OnAudioTrackListener onAudioTrackListener;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CommBaseAdapter<AudioTrackItem> commBaseAdapter = this$0.itemAdapter;
        AudioTrackModel audioTrackModel = null;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("itemAdapter");
            commBaseAdapter = null;
        }
        for (AudioTrackItem audioTrackItem : commBaseAdapter.getData()) {
            ArrayList<AudioTrackModel> tracks = audioTrackItem.getTracks();
            if (tracks != null) {
                for (AudioTrackModel audioTrackModel2 : tracks) {
                    if (audioTrackModel2.isSelect()) {
                        audioTrackModel = audioTrackModel2;
                    }
                }
            }
        }
        if (audioTrackModel == null || (onAudioTrackListener = this$0.listener) == null) {
            return;
        }
        onAudioTrackListener.onAdjustAudioDelay(audioTrackModel.getOss_fid());
    }

    public final boolean isFragmentVisible() {
        return isVisible();
    }

    private final void getAudioTracks() {
        Object as = Http.getService().getAudioTracks(API.BASE_URL, this.boxType == 1 ? "Movie_audio" : "TV_audio", App.getUserData().uid_v2, this.boxType == 1 ? this.id : "", this.boxType == 2 ? this.id : "", this.season, this.episode).compose(RxUtils.rxTranslate2Bean(AudioTrackResponse.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(as, "getService().getAudioTra…bindLifecycleOwner(this))");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, new AudioTracksFragment$getAudioTracks$1(this), null, new AudioTracksFragment$getAudioTracks$2(this), null, new AudioTracksFragment$getAudioTracks$3(this), 10, null);
    }

    private final IMedia.AudioTrack getAudioTrackInfo(int i, int i2) {
        Media media = this.media;
        if (media == null) {
            return null;
        }
        IMedia.Track track = media.getTrack(i);
        if ((track instanceof IMedia.AudioTrack) && track.id == i2) {
            return (IMedia.AudioTrack) track;
        }
        int i3 = 0;
        int trackCount = media.getTrackCount();
        while (i3 < trackCount) {
            int i4 = i3 + 1;
            IMedia.Track track2 = media.getTrack(i3);
            if ((track2 instanceof IMedia.AudioTrack) && track2.id == i2) {
                return (IMedia.AudioTrack) track2;
            }
            i3 = i4;
        }
        return null;
    }

    @Override // com.movieboxpro.android.base.BaseLazyFragment
    public void onFragmentResume() {
        super.onFragmentResume();
        VlcPlayer vlcPlayerInstance = VlcPlayerInstance.INSTANCE.getInstance();
        long audioDelay = vlcPlayerInstance == null ? 0L : vlcPlayerInstance.getAudioDelay();
        if (audioDelay == 0) {
            TextView tvAudioDelay = (TextView) _$_findCachedViewById(R.id.tvAudioDelay);
            Intrinsics.checkNotNullExpressionValue(tvAudioDelay, "tvAudioDelay");
            CommonExtKt.gone(tvAudioDelay);
            return;
        }
        TextView tvAudioDelay2 = (TextView) _$_findCachedViewById(R.id.tvAudioDelay);
        Intrinsics.checkNotNullExpressionValue(tvAudioDelay2, "tvAudioDelay");
        CommonExtKt.visible(tvAudioDelay2);
        if (audioDelay > 0) {
            ((TextView) _$_findCachedViewById(R.id.tvAudioDelay)).setText('+' + audioDelay + "ms");
            return;
        }
        ((TextView) _$_findCachedViewById(R.id.tvAudioDelay)).setText('-' + audioDelay + "ms");
    }

    private final void initData() {
        LinearLayout llAudioDelay = (LinearLayout) _$_findCachedViewById(R.id.llAudioDelay);
        Intrinsics.checkNotNullExpressionValue(llAudioDelay, "llAudioDelay");
        CommonExtKt.visible(llAudioDelay);
        CommBaseAdapter<AudioTrackItem> commBaseAdapter = null;
        if (getContext() != null) {
            VlcPlayerInstance vlcPlayerInstance = VlcPlayerInstance.INSTANCE;
            Context context = App.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext()");
            IMedia media = vlcPlayerInstance.getInstance(context).getMediaPlayer().getMedia();
            this.media = media instanceof Media ? (Media) media : null;
        }
        Bundle arguments = getArguments();
        this.mediaQuality = arguments == null ? null : (MediaQualityInfo) arguments.getParcelable("quality");
        Bundle arguments2 = getArguments();
        this.id = arguments2 == null ? null : arguments2.getString("id");
        Bundle arguments3 = getArguments();
        this.season = arguments3 == null ? 1 : arguments3.getInt("season");
        Bundle arguments4 = getArguments();
        this.episode = arguments4 == null ? 1 : arguments4.getInt("episode");
        Bundle arguments5 = getArguments();
        this.boxType = arguments5 == null ? 0 : arguments5.getInt("boxType", 0);
        Bundle arguments6 = getArguments();
        this.audioTrackIndex = arguments6 == null ? 1 : arguments6.getInt("pos", 0);
        Bundle arguments7 = getArguments();
        ArrayList<AudioTrackModel> parcelableArrayList = arguments7 == null ? null : arguments7.getParcelableArrayList("data");
        if (parcelableArrayList == null) {
            parcelableArrayList = new ArrayList<>();
        }
        this.audioTrackList = parcelableArrayList;
        int i = 0;
        for (Object obj : parcelableArrayList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            AudioTrackModel audioTrackModel = (AudioTrackModel) obj;
            IMedia.AudioTrack audioTrackInfo = getAudioTrackInfo(i2, audioTrackModel.getId());
            if (audioTrackInfo != null) {
                if (audioTrackInfo.bitrate == 0) {
                    audioTrackModel.setBitstream(null);
                } else {
                    audioTrackModel.setBitstream(String.valueOf(audioTrackInfo.bitrate / 1000));
                }
                audioTrackModel.setChannel(String.valueOf(audioTrackInfo.channels));
                audioTrackModel.setLang(audioTrackInfo.language);
                audioTrackModel.setFormat(audioTrackInfo.originalCodec);
            }
            i = i2;
        }
        if (!this.audioTrackList.isEmpty()) {
            this.maxId = ((AudioTrackModel) CollectionsKt.last((List<? extends Object>) this.audioTrackList)).getId();
        }
        Iterator<AudioTrackModel> it = this.audioTrackList.iterator();
        int i3 = 0;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            int i4 = i3 + 1;
            AudioTrackModel next = it.next();
            if (next.getId() == this.audioTrackIndex) {
                next.setSelect(true);
                this.lastPos = i3;
                break;
            }
            i3 = i4;
        }
        ArrayList arrayList = new ArrayList();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj2 : this.audioTrackList) {
            String video_file_name = ((AudioTrackModel) obj2).getVideo_file_name();
            Object obj3 = linkedHashMap.get(video_file_name);
            if (obj3 == null) {
                obj3 = (List) new ArrayList();
                linkedHashMap.put(video_file_name, obj3);
            }
            ((List) obj3).add(obj2);
        }
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            arrayList.add(new AudioTrackItem((String) entry.getKey(), TimeUtils.formatTime3(((AudioTrackModel) ((List) entry.getValue()).get(0)).getAdd_time() * 1000), new ArrayList((Collection) entry.getValue())));
        }
        this.itemAdapter = new CommBaseAdapter<>(R.layout.adapter_audio_track, new AudioTracksFragment$initData$5(this), arrayList);
        ((RecyclerView) _$_findCachedViewById(R.id.recyclerView)).setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.recyclerView);
        Intrinsics.checkNotNullExpressionValue(recyclerView, "recyclerView");
        CommonExtKt.disableRefreshAnimation(recyclerView);
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.recyclerView);
        CommBaseAdapter<AudioTrackItem> commBaseAdapter2 = this.itemAdapter;
        if (commBaseAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("itemAdapter");
        } else {
            commBaseAdapter = commBaseAdapter2;
        }
        recyclerView2.setAdapter(commBaseAdapter);
    }

    /* compiled from: AudioTracksFragment.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JV\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0016\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u000e¨\u0006\u0012"}, d2 = {"Lcom/movieboxpro/android/view/activity/vlcvideoplayer/AudioTracksFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/activity/vlcvideoplayer/AudioTracksFragment;", "mediaQuality", "Lcom/dueeeke/model/MediaQualityInfo;", "tracks", "Ljava/util/ArrayList;", "Lorg/videolan/libvlc/MediaPlayer$TrackDescription;", "Lkotlin/collections/ArrayList;", "id", "", "boxType", "", "pos", "season", "episode", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AudioTracksFragment newInstance(MediaQualityInfo mediaQualityInfo, ArrayList<MediaPlayer.TrackDescription> tracks, String str, int i, int i2, int i3, int i4) {
            Intrinsics.checkNotNullParameter(tracks, "tracks");
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
            for (MediaPlayer.TrackDescription trackDescription : tracks) {
                if (trackDescription.id != -1) {
                    AudioTrackModel audioTrackModel = new AudioTrackModel();
                    audioTrackModel.setName(trackDescription.name);
                    audioTrackModel.setFid(String.valueOf(mediaQualityInfo == null ? null : Integer.valueOf(mediaQualityInfo.fid)));
                    audioTrackModel.setVideo_file_name(mediaQualityInfo == null ? null : mediaQualityInfo.getFileName());
                    audioTrackModel.setTime(mediaQualityInfo != null ? mediaQualityInfo.getTime() : null);
                    audioTrackModel.setId(trackDescription.id);
                    arrayList.add(audioTrackModel);
                }
            }
            AudioTracksFragment audioTracksFragment = new AudioTracksFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("data", arrayList);
            bundle.putString("id", str);
            bundle.putInt("boxType", i);
            bundle.putInt("pos", i2);
            bundle.putInt("season", i3);
            bundle.putInt("episode", i4);
            bundle.putParcelable("quality", mediaQualityInfo);
            audioTracksFragment.setArguments(bundle);
            return audioTracksFragment;
        }
    }
}
