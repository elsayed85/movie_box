package com.movieboxpro.android.view.dialog;

import android.os.Bundle;
import android.view.View;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.model.DownloadInfo;
import com.movieboxpro.android.utils.CommonExtKt;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: TrailerPlayerDialogFragment.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u0000 \t2\u00020\u0001:\u0002\t\nB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0004H\u0016J\b\u0010\b\u001a\u00020\u0004H\u0016¨\u0006\u000b"}, d2 = {"Lcom/movieboxpro/android/view/dialog/TrailerPlayerDialogFragment;", "Lcom/movieboxpro/android/view/dialog/BaseCenterDialogFragment;", "()V", "initData", "", "initLayoutId", "", "initListener", "initView", "Companion", "YoutubePlayerFragment", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TrailerPlayerDialogFragment extends BaseCenterDialogFragment {
    public static final Companion Companion = new Companion(null);
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment
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

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment
    public void initData() {
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment
    public int initLayoutId() {
        return R.layout.fragment_trailer_player;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment
    public void initListener() {
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment
    public void initView() {
        YoutubePlayerFragment youtubePlayerFragment = (YoutubePlayerFragment) getChildFragmentManager().findFragmentById(R.id.video_fragment_container);
        if (youtubePlayerFragment == null) {
            return;
        }
        Bundle arguments = getArguments();
        youtubePlayerFragment.setVideoId(arguments == null ? null : arguments.getString("videoId"));
    }

    /* compiled from: TrailerPlayerDialogFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/view/dialog/TrailerPlayerDialogFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/TrailerPlayerDialogFragment;", "videoId", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TrailerPlayerDialogFragment newInstance(String str) {
            TrailerPlayerDialogFragment trailerPlayerDialogFragment = new TrailerPlayerDialogFragment();
            trailerPlayerDialogFragment.setArguments(CommonExtKt.bundleOf(TuplesKt.to("videoId", str)));
            return trailerPlayerDialogFragment;
        }
    }

    /* compiled from: TrailerPlayerDialogFragment.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\b\u0010\f\u001a\u00020\tH\u0016J\u001c\u0010\r\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\"\u0010\u0012\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0006\u0010\u0015\u001a\u00020\tJ\u0010\u0010\u0016\u001a\u00020\t2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/movieboxpro/android/view/dialog/TrailerPlayerDialogFragment$YoutubePlayerFragment;", "Lcom/google/android/youtube/player/YouTubePlayerFragment;", "Lcom/google/android/youtube/player/YouTubePlayer$OnInitializedListener;", "()V", "player", "Lcom/google/android/youtube/player/YouTubePlayer;", "videoId", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onInitializationFailure", "provider", "Lcom/google/android/youtube/player/YouTubePlayer$Provider;", "result", "Lcom/google/android/youtube/player/YouTubeInitializationResult;", "onInitializationSuccess", "restored", "", DownloadInfo.DOWNLOAD_PAUSE, "setVideoId", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class YoutubePlayerFragment extends YouTubePlayerFragment implements YouTubePlayer.OnInitializedListener {
        public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
        private YouTubePlayer player;
        private String videoId;

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

        @Override // com.google.android.youtube.player.YouTubePlayerFragment, android.app.Fragment
        public /* synthetic */ void onDestroyView() {
            super.onDestroyView();
            _$_clearFindViewByIdCache();
        }

        @Override // com.google.android.youtube.player.YouTubePlayerFragment, android.app.Fragment
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            initialize(Constant.GOOGLE_YOUTUBE_KEY, this);
        }

        @Override // com.google.android.youtube.player.YouTubePlayerFragment, android.app.Fragment
        public void onDestroy() {
            YouTubePlayer youTubePlayer = this.player;
            if (youTubePlayer != null) {
                youTubePlayer.release();
            }
            super.onDestroy();
        }

        public final void setVideoId(String str) {
            if (str == null || Intrinsics.areEqual(str, this.videoId)) {
                return;
            }
            this.videoId = str;
            YouTubePlayer youTubePlayer = this.player;
            if (youTubePlayer == null) {
                return;
            }
            youTubePlayer.cueVideo(str);
        }

        public final void pause() {
            YouTubePlayer youTubePlayer = this.player;
            if (youTubePlayer == null) {
                return;
            }
            youTubePlayer.pause();
        }

        @Override // com.google.android.youtube.player.YouTubePlayer.OnInitializedListener
        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean z) {
            String str;
            Intrinsics.checkNotNullParameter(player, "player");
            this.player = player;
            player.addFullscreenControlFlag(8);
            if (z || (str = this.videoId) == null) {
                return;
            }
            player.cueVideo(str);
        }

        @Override // com.google.android.youtube.player.YouTubePlayer.OnInitializedListener
        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
            this.player = null;
        }
    }
}
