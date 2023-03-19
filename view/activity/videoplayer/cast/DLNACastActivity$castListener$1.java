package com.movieboxpro.android.view.activity.videoplayer.cast;

import android.widget.TextView;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.core.app.NotificationCompat;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import com.hyqq.dlan.bean.DeviceInfo;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.CommBaseAdapter;
import com.movieboxpro.android.service.DLNACastService;
import com.movieboxpro.android.utils.ToastUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: DLNACastActivity.kt */
@Metadata(d1 = {"\u0000?\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0018\u0010\u0007\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\bH\u0016J\b\u0010\t\u001a\u00020\u0003H\u0016J\b\u0010\n\u001a\u00020\u0003H\u0016J\b\u0010\u000b\u001a\u00020\u0003H\u0016J\u0012\u0010\f\u001a\u00020\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0003H\u0016J\u0018\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0016J\b\u0010\u0014\u001a\u00020\u0003H\u0016J\b\u0010\u0015\u001a\u00020\u0003H\u0016J\u0018\u0010\u0016\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\bH\u0016J\u0010\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u000eH\u0016J\u0010\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u0003H\u0016J\b\u0010\u001d\u001a\u00020\u0003H\u0016J\u001c\u0010\u001e\u001a\u00020\u00032\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00050 H\u0016¨\u0006!"}, d2 = {"com/movieboxpro/android/view/activity/videoplayer/cast/DLNACastActivity$castListener$1", "Lcom/movieboxpro/android/service/DLNACastService$CastListener;", "connectFailed", "", "info", "Lcom/hyqq/dlan/bean/DeviceInfo;", "connectSuccess", "disConnect", "", "loadingState", "pauseState", "playComplete", "playFailed", NotificationCompat.CATEGORY_MESSAGE, "", "playState", "positionUpdate", "durationSeconds", "", "positionSeconds", "searchCompleted", "searchFailed", "searchSuccess", "setMediaName", "name", "setVolume", MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, "", "startPlay", "stopState", "updateConnectDevice", "map", "Ljava/util/HashMap;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DLNACastActivity$castListener$1 implements DLNACastService.CastListener {
    final /* synthetic */ DLNACastActivity this$0;

    @Override // com.movieboxpro.android.service.DLNACastService.CastListener
    public void disConnect(List<? extends DeviceInfo> list) {
    }

    @Override // com.movieboxpro.android.service.DLNACastService.CastListener
    public void playComplete() {
    }

    @Override // com.movieboxpro.android.service.DLNACastService.CastListener
    public void searchFailed() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DLNACastActivity$castListener$1(DLNACastActivity dLNACastActivity) {
        this.this$0 = dLNACastActivity;
    }

    @Override // com.movieboxpro.android.service.DLNACastService.CastListener
    public void setMediaName(final String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        final DLNACastActivity dLNACastActivity = this.this$0;
        dLNACastActivity.runOnUiThread(new Runnable() { // from class: com.movieboxpro.android.view.activity.videoplayer.cast.-$$Lambda$DLNACastActivity$castListener$1$9a6yqbmmJ53l31wlGL0n85GwYls
            @Override // java.lang.Runnable
            public final void run() {
                DLNACastActivity$castListener$1.m887setMediaName$lambda0(DLNACastActivity.this, name);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setMediaName$lambda-0  reason: not valid java name */
    public static final void m887setMediaName$lambda0(DLNACastActivity this$0, String name) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(name, "$name");
        ((TextView) this$0._$_findCachedViewById(R.id.tvName)).setText(name);
    }

    @Override // com.movieboxpro.android.service.DLNACastService.CastListener
    public void updateConnectDevice(HashMap<String, DeviceInfo> map) {
        CommBaseAdapter commBaseAdapter;
        CommBaseAdapter commBaseAdapter2;
        Intrinsics.checkNotNullParameter(map, "map");
        commBaseAdapter = this.this$0.adapter;
        CommBaseAdapter commBaseAdapter3 = null;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseAdapter = null;
        }
        for (DeviceInfo deviceInfo : commBaseAdapter.getData()) {
            if (map.containsKey(deviceInfo.getDevice().getIdentity().getUdn().toString())) {
                deviceInfo.setConnected(true);
            }
        }
        commBaseAdapter2 = this.this$0.adapter;
        if (commBaseAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            commBaseAdapter3 = commBaseAdapter2;
        }
        commBaseAdapter3.notifyDataSetChanged();
    }

    @Override // com.movieboxpro.android.service.DLNACastService.CastListener
    public void startPlay() {
        DLNACastService.CastBinder castBinder;
        String str;
        String str2;
        castBinder = this.this$0.castBinder;
        if (castBinder == null) {
            return;
        }
        str = this.this$0.url;
        str2 = this.this$0.name;
        if (str2 == null) {
            str2 = "";
        }
        castBinder.play(str, str2);
    }

    @Override // com.movieboxpro.android.service.DLNACastService.CastListener
    public void setVolume(int i) {
        this.this$0.setVolumeSeek(i);
    }

    @Override // com.movieboxpro.android.service.DLNACastService.CastListener
    public void connectSuccess(DeviceInfo deviceInfo) {
        this.this$0.switchHintState();
    }

    @Override // com.movieboxpro.android.service.DLNACastService.CastListener
    public void connectFailed(DeviceInfo deviceInfo) {
        ToastUtils.showShort("Connect failed", new Object[0]);
    }

    @Override // com.movieboxpro.android.service.DLNACastService.CastListener
    public void searchSuccess(List<? extends DeviceInfo> list) {
        CommBaseAdapter commBaseAdapter;
        boolean findExist;
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            DLNACastActivity dLNACastActivity = this.this$0;
            for (DeviceInfo deviceInfo : list) {
                findExist = dLNACastActivity.findExist(deviceInfo);
                if (!findExist) {
                    arrayList.add(deviceInfo);
                }
            }
            commBaseAdapter = this.this$0.adapter;
            if (commBaseAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                commBaseAdapter = null;
            }
            commBaseAdapter.addData((Collection) arrayList);
        }
        if (list == null) {
            return;
        }
        DLNACastActivity dLNACastActivity2 = this.this$0;
        int i = 0;
        for (Object obj : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            if (((DeviceInfo) obj).isSelected()) {
                dLNACastActivity2.lastPosition = i;
            }
            i = i2;
        }
    }

    @Override // com.movieboxpro.android.service.DLNACastService.CastListener
    public void searchCompleted() {
        this.this$0.switchSearchHint(2);
    }

    @Override // com.movieboxpro.android.service.DLNACastService.CastListener
    public void playState() {
        this.this$0.switchPlayStatus(1);
    }

    @Override // com.movieboxpro.android.service.DLNACastService.CastListener
    public void loadingState() {
        this.this$0.switchPlayStatus(3);
    }

    @Override // com.movieboxpro.android.service.DLNACastService.CastListener
    public void pauseState() {
        this.this$0.switchPlayStatus(2);
    }

    @Override // com.movieboxpro.android.service.DLNACastService.CastListener
    public void stopState() {
        this.this$0.switchPlayStatus(4);
        final DLNACastActivity dLNACastActivity = this.this$0;
        dLNACastActivity.runOnUiThread(new Runnable() { // from class: com.movieboxpro.android.view.activity.videoplayer.cast.-$$Lambda$DLNACastActivity$castListener$1$DBvTX9Brnhi0mSUf9kNXPAQg53k
            @Override // java.lang.Runnable
            public final void run() {
                DLNACastActivity$castListener$1.m888stopState$lambda4(DLNACastActivity.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: stopState$lambda-4  reason: not valid java name */
    public static final void m888stopState$lambda4(DLNACastActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((TextView) this$0._$_findCachedViewById(R.id.tvCurrTime)).setText("");
        ((TextView) this$0._$_findCachedViewById(R.id.tvDurationTime)).setText("");
        ((AppCompatSeekBar) this$0._$_findCachedViewById(R.id.seekBar)).setProgress(0);
    }

    @Override // com.movieboxpro.android.service.DLNACastService.CastListener
    public void playFailed(String str) {
        ToastUtils.showShort(str, new Object[0]);
    }

    @Override // com.movieboxpro.android.service.DLNACastService.CastListener
    public void positionUpdate(final long j, final long j2) {
        int i;
        i = this.this$0.status;
        if (i == 1) {
            final DLNACastActivity dLNACastActivity = this.this$0;
            dLNACastActivity.runOnUiThread(new Runnable() { // from class: com.movieboxpro.android.view.activity.videoplayer.cast.-$$Lambda$DLNACastActivity$castListener$1$RNL_8M-rfEiTcjv98cvNQqZvggo
                @Override // java.lang.Runnable
                public final void run() {
                    DLNACastActivity$castListener$1.m885positionUpdate$lambda5(DLNACastActivity.this, j, j2);
                }
            });
            return;
        }
        final DLNACastActivity dLNACastActivity2 = this.this$0;
        dLNACastActivity2.runOnUiThread(new Runnable() { // from class: com.movieboxpro.android.view.activity.videoplayer.cast.-$$Lambda$DLNACastActivity$castListener$1$dhi5zxeBaO8uBN9BEBDWAQr93kk
            @Override // java.lang.Runnable
            public final void run() {
                DLNACastActivity$castListener$1.m886positionUpdate$lambda6(DLNACastActivity.this, j, j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: positionUpdate$lambda-5  reason: not valid java name */
    public static final void m885positionUpdate$lambda5(DLNACastActivity this$0, long j, long j2) {
        String stringForTime;
        String stringForTime2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((AppCompatSeekBar) this$0._$_findCachedViewById(R.id.seekBar)).setMax((int) j);
        ((AppCompatSeekBar) this$0._$_findCachedViewById(R.id.seekBar)).setProgress((int) j2);
        stringForTime = this$0.stringForTime(j2);
        ((TextView) this$0._$_findCachedViewById(R.id.tvCurrTime)).setText(stringForTime);
        stringForTime2 = this$0.stringForTime(j);
        ((TextView) this$0._$_findCachedViewById(R.id.tvDurationTime)).setText(stringForTime2);
        this$0.switchPlayStatus(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: positionUpdate$lambda-6  reason: not valid java name */
    public static final void m886positionUpdate$lambda6(DLNACastActivity this$0, long j, long j2) {
        String stringForTime;
        String stringForTime2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((AppCompatSeekBar) this$0._$_findCachedViewById(R.id.seekBar)).setMax((int) j);
        ((AppCompatSeekBar) this$0._$_findCachedViewById(R.id.seekBar)).setProgress((int) j2);
        stringForTime = this$0.stringForTime(j2);
        ((TextView) this$0._$_findCachedViewById(R.id.tvCurrTime)).setText(stringForTime);
        stringForTime2 = this$0.stringForTime(j);
        ((TextView) this$0._$_findCachedViewById(R.id.tvDurationTime)).setText(stringForTime2);
    }
}
