package com.movieboxpro.android.view.videocontroller;

import android.content.Context;
import android.os.Bundle;
import com.avery.subtitle.model.Subtitle;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.view.activity.Video.VideoPlayerActivity;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: PopPlayerManager.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\bJ\u0018\u0010\t\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\t\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rJ^\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\r2\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00142\u0006\u0010\u0016\u001a\u00020\u00122\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u00182\b\u0010\u001a\u001a\u0004\u0018\u00010\u0018R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/movieboxpro/android/view/videocontroller/PopPlayerManager;", "", "()V", "popupManager", "Lcom/movieboxpro/android/view/videocontroller/PopupManager;", "isPopShow", "", "removePopPlayer", "", "setNewPlay", "bundle", "Landroid/os/Bundle;", "videoModel", "Lcom/movieboxpro/android/model/BaseMediaModel;", "showPopPlayer", "context", "Landroid/content/Context;", "boxType", "", "subtitles", "", "Lcom/avery/subtitle/model/Subtitle;", "subtitleDelay", "fid", "", "language", "audioTrackUrl", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PopPlayerManager {
    public static final Companion Companion = new Companion(null);
    private static final Lazy<PopPlayerManager> instance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) PopPlayerManager$Companion$instance$2.INSTANCE);
    private PopupManager popupManager;

    public /* synthetic */ PopPlayerManager(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public static final PopPlayerManager getInstance() {
        return Companion.getInstance();
    }

    public final void setNewPlay() {
    }

    private PopPlayerManager() {
    }

    /* compiled from: PopPlayerManager.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R!\u0010\u0003\u001a\u00020\u00048FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\b\u0010\t\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/view/videocontroller/PopPlayerManager$Companion;", "", "()V", "instance", "Lcom/movieboxpro/android/view/videocontroller/PopPlayerManager;", "getInstance$annotations", "getInstance", "()Lcom/movieboxpro/android/view/videocontroller/PopPlayerManager;", "instance$delegate", "Lkotlin/Lazy;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public static /* synthetic */ void getInstance$annotations() {
        }

        private Companion() {
        }

        public final PopPlayerManager getInstance() {
            return (PopPlayerManager) PopPlayerManager.instance$delegate.getValue();
        }
    }

    public final boolean isPopShow() {
        return this.popupManager != null;
    }

    public final void setNewPlay(BaseMediaModel videoModel) {
        Intrinsics.checkNotNullParameter(videoModel, "videoModel");
        Bundle bundle = new Bundle();
        bundle.putSerializable("videoplayer_params", videoModel);
        bundle.putString(VideoPlayerActivity.VIDEO_ID, videoModel.id);
        bundle.putString("poster", videoModel.poster);
        PopupManager popupManager = this.popupManager;
        if (popupManager == null) {
            return;
        }
        popupManager.setNewPlay(bundle, 1, videoModel);
    }

    public final void setNewPlay(Bundle bundle, BaseMediaModel videoModel) {
        Intrinsics.checkNotNullParameter(videoModel, "videoModel");
        PopupManager popupManager = this.popupManager;
        if (popupManager == null) {
            return;
        }
        popupManager.setNewPlay(bundle, 2, videoModel);
    }

    public final void showPopPlayer(Context context, Bundle bundle, int i, BaseMediaModel videoModel, List<? extends Subtitle> list, int i2, String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(videoModel, "videoModel");
        if (this.popupManager == null) {
            PopupManager popupManager = new PopupManager(context, bundle, i, videoModel, list, i2, str, str2, str3);
            this.popupManager = popupManager;
            Intrinsics.checkNotNull(popupManager);
            popupManager.showPopup();
        }
    }

    public final void removePopPlayer() {
        PopupManager popupManager = this.popupManager;
        if (popupManager != null) {
            popupManager.removePopup();
        }
        this.popupManager = null;
    }
}
