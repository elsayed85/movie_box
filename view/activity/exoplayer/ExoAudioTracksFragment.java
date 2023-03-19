package com.movieboxpro.android.view.activity.exoplayer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.dueeeke.model.MediaQualityInfo;
import com.google.android.exoplayer2.Bundleable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
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
import com.movieboxpro.android.model.ExoAudioTrackInfo;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.EventUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.view.activity.user.VipActivity;
import com.movieboxpro.android.view.fragment.AudioSynDialog;
import com.uber.autodispose.ObservableSubscribeProxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
/* compiled from: ExoAudioTracksFragment.kt */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 02\u00020\u0001:\u000201B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u001aH\u0002J\b\u0010\u001c\u001a\u00020\u001aH\u0002J\u0006\u0010\u001d\u001a\u00020\u001eJ \u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\u00072\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020#H\u0002J&\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010)2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\b\u0010,\u001a\u00020\u001aH\u0016J\u001a\u0010-\u001a\u00020\u001a2\u0006\u0010.\u001a\u00020%2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\u000e\u0010/\u001a\u00020\u001a2\u0006\u0010\u0012\u001a\u00020\u0013R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lcom/movieboxpro/android/view/activity/exoplayer/ExoAudioTracksFragment;", "Lcom/movieboxpro/android/base/BaseLazyFragment;", "()V", "audioTrackIndex", "", "audioTrackList", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/AudioTrackModel;", "Lkotlin/collections/ArrayList;", "boxType", "currAudioId", "", "episode", "id", "itemAdapter", "Lcom/movieboxpro/android/base/CommBaseAdapter;", "Lcom/movieboxpro/android/model/AudioTrackItem;", "lastPos", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/activity/exoplayer/ExoAudioTracksFragment$OnAudioTrackListener;", "maxId", "mediaQuality", "Lcom/dueeeke/model/MediaQualityInfo;", "onlineTracks", "season", "getAudioTracks", "", "initData", "initListener", "isFragmentVisible", "", "itemClick", "item", "position", "adapter", "Lcom/movieboxpro/android/adapter/AudioTrackItemAdapter;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onFragmentResume", "onViewCreated", "view", "setListener", "Companion", "OnAudioTrackListener", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ExoAudioTracksFragment extends BaseLazyFragment {
    public static final Companion Companion = new Companion(null);
    private String currAudioId;
    private String id;
    private CommBaseAdapter<AudioTrackItem> itemAdapter;
    private OnAudioTrackListener listener;
    private int maxId;
    private MediaQualityInfo mediaQuality;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private int lastPos = -1;
    private ArrayList<AudioTrackModel> audioTrackList = new ArrayList<>();
    private int season = 1;
    private int episode = 1;
    private int boxType = 1;
    private int audioTrackIndex = 1;
    private ArrayList<AudioTrackModel> onlineTracks = new ArrayList<>();

    /* compiled from: ExoAudioTracksFragment.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J@\u0010\u0006\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005H&J \u0010\u000f\u001a\u00020\u00032\u0016\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u00120\u0011j\b\u0012\u0004\u0012\u00020\u0012`\u0013H&¨\u0006\u0014"}, d2 = {"Lcom/movieboxpro/android/view/activity/exoplayer/ExoAudioTracksFragment$OnAudioTrackListener;", "", "onAdjustAudioDelay", "", "ossId", "", "onAudioClick", "trackGroup", "Lcom/google/android/exoplayer2/source/TrackGroup;", "url", "isAdded", "", "needVip", "audioTrackId", "language", "onOnlineAudios", "audioTracks", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/AudioTrackModel;", "Lkotlin/collections/ArrayList;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface OnAudioTrackListener {
        void onAdjustAudioDelay(String str);

        void onAudioClick(TrackGroup trackGroup, String str, boolean z, boolean z2, String str2, String str3);

        void onOnlineAudios(ArrayList<AudioTrackModel> arrayList);
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
    }

    private final void initListener() {
        ((TextView) _$_findCachedViewById(R.id.tvTryAgain)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.-$$Lambda$ExoAudioTracksFragment$tIfgIZh5beFn4ebxcQTTvtpETts
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ExoAudioTracksFragment.m359initListener$lambda0(ExoAudioTracksFragment.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.llAudioSyn)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.-$$Lambda$ExoAudioTracksFragment$n30R0yWsiRKQNYiJPumRNJUBqSM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ExoAudioTracksFragment.m360initListener$lambda1(ExoAudioTracksFragment.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.llAudioDelay)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.exoplayer.-$$Lambda$ExoAudioTracksFragment$xcrOsWm-v7Og2wXQCM9nd3dk5QA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ExoAudioTracksFragment.m361initListener$lambda5(ExoAudioTracksFragment.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m359initListener$lambda0(ExoAudioTracksFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TextView tvTryAgain = (TextView) this$0._$_findCachedViewById(R.id.tvTryAgain);
        Intrinsics.checkNotNullExpressionValue(tvTryAgain, "tvTryAgain");
        CommonExtKt.gone(tvTryAgain);
        this$0.getAudioTracks();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m360initListener$lambda1(ExoAudioTracksFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        new AudioSynDialog().show(this$0.getChildFragmentManager(), AudioSynDialog.class.getSimpleName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-5  reason: not valid java name */
    public static final void m361initListener$lambda5(ExoAudioTracksFragment this$0, View view) {
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
                String download_url = audioTrackModel.getDownload_url();
                if (download_url == null || StringsKt.isBlank(download_url)) {
                    LinearLayout llAudioDelay = (LinearLayout) _$_findCachedViewById(R.id.llAudioDelay);
                    Intrinsics.checkNotNullExpressionValue(llAudioDelay, "llAudioDelay");
                    CommonExtKt.gone(llAudioDelay);
                    LinearLayout llAudioSyn = (LinearLayout) _$_findCachedViewById(R.id.llAudioSyn);
                    Intrinsics.checkNotNullExpressionValue(llAudioSyn, "llAudioSyn");
                    CommonExtKt.gone(llAudioSyn);
                } else {
                    LinearLayout llAudioDelay2 = (LinearLayout) _$_findCachedViewById(R.id.llAudioDelay);
                    Intrinsics.checkNotNullExpressionValue(llAudioDelay2, "llAudioDelay");
                    CommonExtKt.visible(llAudioDelay2);
                    LinearLayout llAudioSyn2 = (LinearLayout) _$_findCachedViewById(R.id.llAudioSyn);
                    Intrinsics.checkNotNullExpressionValue(llAudioSyn2, "llAudioSyn");
                    CommonExtKt.visible(llAudioSyn2);
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
                onAudioTrackListener.onAudioClick(null, audioTrackModel.getDownload_url(), z2, audioTrackModel.getVip_only() == 1, audioTrackModel.getOss_fid(), audioTrackModel.getLang());
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
        String download_url2 = audioTrackModel.getDownload_url();
        if (download_url2 == null || StringsKt.isBlank(download_url2)) {
            LinearLayout llAudioDelay3 = (LinearLayout) _$_findCachedViewById(R.id.llAudioDelay);
            Intrinsics.checkNotNullExpressionValue(llAudioDelay3, "llAudioDelay");
            CommonExtKt.gone(llAudioDelay3);
            LinearLayout llAudioSyn3 = (LinearLayout) _$_findCachedViewById(R.id.llAudioSyn);
            Intrinsics.checkNotNullExpressionValue(llAudioSyn3, "llAudioSyn");
            CommonExtKt.gone(llAudioSyn3);
        } else {
            LinearLayout llAudioDelay4 = (LinearLayout) _$_findCachedViewById(R.id.llAudioDelay);
            Intrinsics.checkNotNullExpressionValue(llAudioDelay4, "llAudioDelay");
            CommonExtKt.visible(llAudioDelay4);
            LinearLayout llAudioSyn4 = (LinearLayout) _$_findCachedViewById(R.id.llAudioSyn);
            Intrinsics.checkNotNullExpressionValue(llAudioSyn4, "llAudioSyn");
            CommonExtKt.visible(llAudioSyn4);
        }
        OnAudioTrackListener onAudioTrackListener2 = this.listener;
        if (onAudioTrackListener2 == null) {
            return;
        }
        Bundleable.Creator<TrackGroup> creator = TrackGroup.CREATOR;
        Bundle trackGroup = audioTrackModel.getExoAudioTrackInfo().getTrackGroup();
        Intrinsics.checkNotNull(trackGroup);
        onAudioTrackListener2.onAudioClick(creator.fromBundle(trackGroup), audioTrackModel.getDownload_url(), z, audioTrackModel.getVip_only() == 1, audioTrackModel.getOss_fid(), audioTrackModel.getLang());
    }

    public final boolean isFragmentVisible() {
        return isVisible();
    }

    private final void getAudioTracks() {
        Object as = Http.getService().getAudioTracks(API.BASE_URL, this.boxType == 1 ? "Movie_audio" : "TV_audio", App.getUserData().uid_v2, this.boxType == 1 ? this.id : "", this.boxType == 2 ? this.id : "", this.season, this.episode).compose(RxUtils.rxTranslate2Bean(AudioTrackResponse.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(as, "getService().getAudioTra…bindLifecycleOwner(this))");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, new ExoAudioTracksFragment$getAudioTracks$1(this), null, new ExoAudioTracksFragment$getAudioTracks$2(this), null, new ExoAudioTracksFragment$getAudioTracks$3(this), 10, null);
    }

    @Override // com.movieboxpro.android.base.BaseLazyFragment
    public void onFragmentResume() {
        super.onFragmentResume();
    }

    private final void initData() {
        Bundle arguments = getArguments();
        CommBaseAdapter<AudioTrackItem> commBaseAdapter = null;
        this.mediaQuality = arguments == null ? null : (MediaQualityInfo) arguments.getParcelable("quality");
        Bundle arguments2 = getArguments();
        this.id = arguments2 == null ? null : arguments2.getString("id");
        Bundle arguments3 = getArguments();
        boolean z = true;
        this.season = arguments3 == null ? 1 : arguments3.getInt("season");
        Bundle arguments4 = getArguments();
        this.episode = arguments4 == null ? 1 : arguments4.getInt("episode");
        Bundle arguments5 = getArguments();
        this.boxType = arguments5 == null ? 0 : arguments5.getInt("boxType", 0);
        Bundle arguments6 = getArguments();
        this.audioTrackIndex = arguments6 == null ? 1 : arguments6.getInt("pos", 0);
        Bundle arguments7 = getArguments();
        this.currAudioId = arguments7 == null ? null : arguments7.getString("ossFid");
        Bundle arguments8 = getArguments();
        this.audioTrackIndex = arguments8 != null ? arguments8.getInt("groupIndex", -1) : -1;
        Bundle arguments9 = getArguments();
        ArrayList<AudioTrackModel> parcelableArrayList = arguments9 == null ? null : arguments9.getParcelableArrayList("data");
        if (parcelableArrayList == null) {
            parcelableArrayList = new ArrayList<>();
        }
        this.audioTrackList = parcelableArrayList;
        Bundle arguments10 = getArguments();
        ArrayList<AudioTrackModel> parcelableArrayList2 = arguments10 == null ? null : arguments10.getParcelableArrayList("onlineTracks");
        if (parcelableArrayList2 == null) {
            parcelableArrayList2 = new ArrayList<>();
        }
        this.onlineTracks = parcelableArrayList2;
        parcelableArrayList2.clear();
        String str = this.currAudioId;
        if (str == null || StringsKt.isBlank(str)) {
            Iterator<AudioTrackModel> it = this.audioTrackList.iterator();
            int i = 0;
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                int i2 = i + 1;
                AudioTrackModel next = it.next();
                if (next.getExoAudioTrackInfo() != null && i == this.audioTrackIndex) {
                    next.setSelect(true);
                    this.lastPos = i;
                    break;
                }
                i = i2;
            }
            for (AudioTrackModel audioTrackModel : this.onlineTracks) {
                audioTrackModel.setSelect(false);
            }
            LinearLayout llAudioDelay = (LinearLayout) _$_findCachedViewById(R.id.llAudioDelay);
            Intrinsics.checkNotNullExpressionValue(llAudioDelay, "llAudioDelay");
            CommonExtKt.gone(llAudioDelay);
            LinearLayout llAudioSyn = (LinearLayout) _$_findCachedViewById(R.id.llAudioSyn);
            Intrinsics.checkNotNullExpressionValue(llAudioSyn, "llAudioSyn");
            CommonExtKt.gone(llAudioSyn);
        } else {
            for (AudioTrackModel audioTrackModel2 : this.onlineTracks) {
                audioTrackModel2.setSelect(false);
            }
            for (AudioTrackModel audioTrackModel3 : this.onlineTracks) {
                if (audioTrackModel3.getOss_fid() == this.currAudioId) {
                    audioTrackModel3.setSelect(true);
                    LinearLayout llAudioDelay2 = (LinearLayout) _$_findCachedViewById(R.id.llAudioDelay);
                    Intrinsics.checkNotNullExpressionValue(llAudioDelay2, "llAudioDelay");
                    CommonExtKt.visible(llAudioDelay2);
                    LinearLayout llAudioSyn2 = (LinearLayout) _$_findCachedViewById(R.id.llAudioSyn);
                    Intrinsics.checkNotNullExpressionValue(llAudioSyn2, "llAudioSyn");
                    CommonExtKt.visible(llAudioSyn2);
                }
            }
        }
        ArrayList<AudioTrackModel> arrayList = this.onlineTracks;
        if (arrayList != null && !arrayList.isEmpty()) {
            z = false;
        }
        if (z) {
            getAudioTracks();
        } else {
            ProgressBar progressBar = (ProgressBar) _$_findCachedViewById(R.id.progressBar);
            Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
            CommonExtKt.gone(progressBar);
            this.audioTrackList.addAll(this.onlineTracks);
        }
        ArrayList arrayList2 = new ArrayList();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : this.audioTrackList) {
            String video_file_name = ((AudioTrackModel) obj).getVideo_file_name();
            Object obj2 = linkedHashMap.get(video_file_name);
            if (obj2 == null) {
                obj2 = (List) new ArrayList();
                linkedHashMap.put(video_file_name, obj2);
            }
            ((List) obj2).add(obj);
        }
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            arrayList2.add(new AudioTrackItem((String) entry.getKey(), TimeUtils.formatTime3(((AudioTrackModel) ((List) entry.getValue()).get(0)).getAdd_time() * 1000), new ArrayList((Collection) entry.getValue())));
        }
        this.itemAdapter = new CommBaseAdapter<>(R.layout.adapter_audio_track, new ExoAudioTracksFragment$initData$6(this), arrayList2);
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

    /* compiled from: ExoAudioTracksFragment.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002Jp\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u001a\u0010\n\u001a\u0016\u0012\u0004\u0012\u00020\u000b\u0018\u00010\bj\n\u0012\u0004\u0012\u00020\u000b\u0018\u0001`\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u000e2\b\b\u0002\u0010\u0013\u001a\u00020\u00102\b\b\u0002\u0010\u0014\u001a\u00020\u0010¨\u0006\u0015"}, d2 = {"Lcom/movieboxpro/android/view/activity/exoplayer/ExoAudioTracksFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/activity/exoplayer/ExoAudioTracksFragment;", "mediaQuality", "Lcom/dueeeke/model/MediaQualityInfo;", "tracks", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/ExoAudioTrackInfo;", "onlineAudioTracks", "Lcom/movieboxpro/android/model/AudioTrackModel;", "Lkotlin/collections/ArrayList;", "id", "", "boxType", "", "groupIndex", "ossFid", "season", "episode", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ExoAudioTracksFragment newInstance(MediaQualityInfo mediaQualityInfo, ArrayList<ExoAudioTrackInfo> tracks, ArrayList<AudioTrackModel> arrayList, String str, int i, int i2, String ossFid, int i3, int i4) {
            Intrinsics.checkNotNullParameter(tracks, "tracks");
            Intrinsics.checkNotNullParameter(ossFid, "ossFid");
            ArrayList<? extends Parcelable> arrayList2 = new ArrayList<>();
            for (ExoAudioTrackInfo exoAudioTrackInfo : tracks) {
                AudioTrackModel audioTrackModel = new AudioTrackModel();
                Bundleable.Creator<Format> creator = Format.CREATOR;
                Bundle format = exoAudioTrackInfo.getFormat();
                Intrinsics.checkNotNull(format);
                Format fromBundle = creator.fromBundle(format);
                Intrinsics.checkNotNullExpressionValue(fromBundle, "CREATOR.fromBundle(it.format!!)");
                Format format2 = fromBundle;
                audioTrackModel.setLang(format2.language);
                audioTrackModel.setChannel(String.valueOf(format2.channelCount));
                audioTrackModel.setExoAudioTrackInfo(exoAudioTrackInfo);
                Integer num = null;
                audioTrackModel.setVideo_file_name(mediaQualityInfo == null ? null : mediaQualityInfo.getFileName());
                if (mediaQualityInfo != null) {
                    num = Integer.valueOf(mediaQualityInfo.fid);
                }
                audioTrackModel.setFid(String.valueOf(num));
                arrayList2.add(audioTrackModel);
            }
            ExoAudioTracksFragment exoAudioTracksFragment = new ExoAudioTracksFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("data", arrayList2);
            bundle.putParcelableArrayList("onlineTracks", arrayList);
            bundle.putString("id", str);
            bundle.putInt("boxType", i);
            bundle.putString("ossFid", ossFid);
            bundle.putInt("season", i3);
            bundle.putInt("groupIndex", i2);
            bundle.putInt("episode", i4);
            bundle.putParcelable("quality", mediaQualityInfo);
            exoAudioTracksFragment.setArguments(bundle);
            return exoAudioTracksFragment;
        }
    }
}
