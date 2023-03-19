package com.movieboxpro.android.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Base64;
import androidx.core.app.NotificationCompat;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.hyqq.dlan.DLNAManager;
import com.hyqq.dlan.DLNAPlayer;
import com.hyqq.dlan.bean.DeviceInfo;
import com.hyqq.dlan.listener.DLNAControlCallback;
import com.hyqq.dlan.listener.DLNADeviceConnectListener;
import com.hyqq.dlan.listener.DLNARegistryListener;
import com.hyqq.dlan.listener.DLNAStateCallback;
import com.movieboxpro.android.event.DLNACastInitEvent;
import com.movieboxpro.android.model.DownloadInfo;
import com.movieboxpro.android.service.DLNACastService;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.RxUtils;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.SubscribersKt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.fourthline.cling.model.action.ActionInvocation;
import org.fourthline.cling.model.types.UDAServiceType;
import org.fourthline.cling.support.model.MediaInfo;
import org.fourthline.cling.support.model.PositionInfo;
import org.fourthline.cling.support.model.TransportInfo;
import org.fourthline.cling.support.model.TransportState;
import org.greenrobot.eventbus.EventBus;
/* compiled from: DLNACastService.kt */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u000234B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\u0006\u0010\u001f\u001a\u00020\u001eJ\u0010\u0010 \u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\tH\u0002J\u0006\u0010\"\u001a\u00020\u001eJ\b\u0010#\u001a\u00020\u001eH\u0002J\u0014\u0010$\u001a\u0004\u0018\u00010%2\b\u0010&\u001a\u0004\u0018\u00010'H\u0016J\b\u0010(\u001a\u00020\u001eH\u0016J\b\u0010)\u001a\u00020\u001eH\u0016J\u000e\u0010*\u001a\u00020\u001e2\u0006\u0010\u001c\u001a\u00020\fJ\u0010\u0010+\u001a\u00020\u001e2\b\u0010,\u001a\u0004\u0018\u00010\u0004J\b\u0010-\u001a\u00020\u001eH\u0002J\u0010\u0010.\u001a\u00020\u001e2\u0006\u0010/\u001a\u00020\tH\u0002J\u0010\u00100\u001a\u00020\u001e2\u0006\u00101\u001a\u000202H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000bj\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r`\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\r0\u0017j\b\u0012\u0004\u0012\u00020\r`\u0018X\u0082.¢\u0006\u0002\n\u0000R*\u0010\u0019\u001a\u001e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u000bj\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f`\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Lcom/movieboxpro/android/service/DLNACastService;", "Landroid/app/Service;", "()V", "castListener", "Lcom/movieboxpro/android/service/DLNACastService$CastListener;", "disposable", "Lio/reactivex/disposables/Disposable;", "getPositionDisposable", "isEventPost", "", "mConnectDeviceList", "Ljava/util/HashMap;", "", "Lcom/hyqq/dlan/bean/DeviceInfo;", "Lkotlin/collections/HashMap;", "mDLNAManager", "Lcom/hyqq/dlan/DLNAManager;", "mDLNAPlayer", "Lcom/hyqq/dlan/DLNAPlayer;", "mDLNARegistryListener", "Lcom/hyqq/dlan/listener/DLNARegistryListener;", "mDeviceInfo", "mDeviceInfoList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "mPlayMediaName", "searchDelay", "", "url", "getMediaInfo", "", "getPosition", "getTransportInfo", "play", "getVolume", "initDlNA", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "setCurrUrl", "setUpdateListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "startGetPosition", "startTimer", "stopSelf", "unbindService", "conn", "Landroid/content/ServiceConnection;", "CastBinder", "CastListener", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DLNACastService extends Service {
    private CastListener castListener;
    private Disposable disposable;
    private Disposable getPositionDisposable;
    private boolean isEventPost;
    private HashMap<String, DeviceInfo> mConnectDeviceList;
    private DLNAManager mDLNAManager;
    private DLNAPlayer mDLNAPlayer;
    private DLNARegistryListener mDLNARegistryListener;
    private DeviceInfo mDeviceInfo;
    private ArrayList<DeviceInfo> mDeviceInfoList;
    private HashMap<String, String> mPlayMediaName;
    private long searchDelay = 30;
    private String url = "";

    /* compiled from: DLNACastService.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0018\u0010\u0007\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\bH&J\b\u0010\t\u001a\u00020\u0003H&J\b\u0010\n\u001a\u00020\u0003H&J\b\u0010\u000b\u001a\u00020\u0003H&J\u0012\u0010\f\u001a\u00020\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u000eH&J\b\u0010\u000f\u001a\u00020\u0003H&J\u0018\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H&J\b\u0010\u0014\u001a\u00020\u0003H&J\b\u0010\u0015\u001a\u00020\u0003H&J\u0018\u0010\u0016\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\bH&J\u0010\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u000eH&J\u0010\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001bH&J\b\u0010\u001c\u001a\u00020\u0003H&J\b\u0010\u001d\u001a\u00020\u0003H&J,\u0010\u001e\u001a\u00020\u00032\"\u0010\u001f\u001a\u001e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00050 j\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u0005`!H&¨\u0006\""}, d2 = {"Lcom/movieboxpro/android/service/DLNACastService$CastListener;", "", "connectFailed", "", "info", "Lcom/hyqq/dlan/bean/DeviceInfo;", "connectSuccess", "disConnect", "", "loadingState", "pauseState", "playComplete", "playFailed", NotificationCompat.CATEGORY_MESSAGE, "", "playState", "positionUpdate", "durationSeconds", "", "positionSeconds", "searchCompleted", "searchFailed", "searchSuccess", "setMediaName", "name", "setVolume", MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, "", "startPlay", "stopState", "updateConnectDevice", "map", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface CastListener {
        void connectFailed(DeviceInfo deviceInfo);

        void connectSuccess(DeviceInfo deviceInfo);

        void disConnect(List<? extends DeviceInfo> list);

        void loadingState();

        void pauseState();

        void playComplete();

        void playFailed(String str);

        void playState();

        void positionUpdate(long j, long j2);

        void searchCompleted();

        void searchFailed();

        void searchSuccess(List<? extends DeviceInfo> list);

        void setMediaName(String str);

        void setVolume(int i);

        void startPlay();

        void stopState();

        void updateConnectDevice(HashMap<String, DeviceInfo> hashMap);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return new CastBinder(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getMediaInfo() {
        DLNAPlayer dLNAPlayer = this.mDLNAPlayer;
        if (dLNAPlayer == null) {
            return;
        }
        dLNAPlayer.getMediaInfo(new DLNAControlCallback() { // from class: com.movieboxpro.android.service.DLNACastService$getMediaInfo$1
            @Override // com.hyqq.dlan.listener.DLNAControlCallback
            public void onSuccess(ActionInvocation<? extends org.fourthline.cling.model.meta.Service<?, ?>> actionInvocation) {
            }

            @Override // com.hyqq.dlan.listener.DLNAControlCallback
            public void onReceived(ActionInvocation<? extends org.fourthline.cling.model.meta.Service<?, ?>> actionInvocation, Object... extra) {
                String str;
                String str2;
                String str3;
                DLNACastService.CastListener castListener;
                Intrinsics.checkNotNullParameter(extra, "extra");
                try {
                    if (!(extra.length == 0)) {
                        Object obj = extra[0];
                        HashMap hashMap = null;
                        MediaInfo mediaInfo = obj instanceof MediaInfo ? (MediaInfo) obj : null;
                        if (mediaInfo != null) {
                            String currentURI = mediaInfo.getCurrentURI();
                            str = DLNACastService.this.url;
                            if (!Intrinsics.areEqual(currentURI, str) && (castListener = DLNACastService.this.castListener) != null) {
                                castListener.startPlay();
                            }
                            StringBuilder sb = new StringBuilder();
                            sb.append("url:");
                            sb.append((Object) currentURI);
                            sb.append(" currUrl:");
                            str2 = DLNACastService.this.url;
                            sb.append((Object) str2);
                            CommonExtKt.logD(this, sb.toString());
                            DLNACastService.CastListener castListener2 = DLNACastService.this.castListener;
                            if (castListener2 == null) {
                                return;
                            }
                            HashMap hashMap2 = DLNACastService.this.mPlayMediaName;
                            if (hashMap2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mPlayMediaName");
                            } else {
                                hashMap = hashMap2;
                            }
                            str3 = DLNACastService.this.url;
                            String str4 = (String) hashMap.get(str3);
                            if (str4 == null) {
                                str4 = "";
                            }
                            castListener2.setMediaName(str4);
                        }
                    }
                } catch (Exception unused) {
                }
            }

            @Override // com.hyqq.dlan.listener.DLNAControlCallback
            public void onFailure(ActionInvocation<? extends org.fourthline.cling.model.meta.Service<?, ?>> actionInvocation, int i, String str) {
                CommonExtKt.logD(this, Intrinsics.stringPlus("getMediaInfo error:", str));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getTransportInfo(final boolean z) {
        DLNAPlayer dLNAPlayer = this.mDLNAPlayer;
        if (dLNAPlayer == null) {
            return;
        }
        dLNAPlayer.getTransportInfo(new DLNAControlCallback() { // from class: com.movieboxpro.android.service.DLNACastService$getTransportInfo$1
            @Override // com.hyqq.dlan.listener.DLNAControlCallback
            public void onSuccess(ActionInvocation<? extends org.fourthline.cling.model.meta.Service<?, ?>> actionInvocation) {
            }

            @Override // com.hyqq.dlan.listener.DLNAControlCallback
            public void onReceived(ActionInvocation<? extends org.fourthline.cling.model.meta.Service<?, ?>> actionInvocation, Object... extra) {
                String str;
                Intrinsics.checkNotNullParameter(extra, "extra");
                try {
                    if (!(extra.length == 0)) {
                        Object obj = extra[0];
                        HashMap hashMap = null;
                        TransportInfo transportInfo = obj instanceof TransportInfo ? (TransportInfo) obj : null;
                        if (transportInfo == null) {
                            return;
                        }
                        DLNACastService dLNACastService = DLNACastService.this;
                        boolean z2 = z;
                        TransportState currentTransportState = transportInfo.getCurrentTransportState();
                        if (currentTransportState != TransportState.PAUSED_PLAYBACK && currentTransportState != TransportState.PAUSED_RECORDING) {
                            if (currentTransportState == TransportState.PLAYING) {
                                DLNACastService.CastListener castListener = dLNACastService.castListener;
                                if (castListener != null) {
                                    castListener.playState();
                                }
                                DLNAPlayer dLNAPlayer2 = dLNACastService.mDLNAPlayer;
                                if (dLNAPlayer2 != null) {
                                    dLNAPlayer2.setCurrentState(1);
                                }
                                dLNACastService.startGetPosition();
                                dLNACastService.getMediaInfo();
                            } else if (currentTransportState == TransportState.STOPPED) {
                                DLNACastService.CastListener castListener2 = dLNACastService.castListener;
                                if (castListener2 != null) {
                                    castListener2.stopState();
                                }
                                if (z2) {
                                    DLNACastService.CastListener castListener3 = dLNACastService.castListener;
                                    if (castListener3 != null) {
                                        HashMap hashMap2 = dLNACastService.mPlayMediaName;
                                        if (hashMap2 == null) {
                                            Intrinsics.throwUninitializedPropertyAccessException("mPlayMediaName");
                                        } else {
                                            hashMap = hashMap2;
                                        }
                                        str = dLNACastService.url;
                                        String str2 = (String) hashMap.get(str);
                                        if (str2 == null) {
                                            str2 = "";
                                        }
                                        castListener3.setMediaName(str2);
                                    }
                                    DLNACastService.CastListener castListener4 = dLNACastService.castListener;
                                    if (castListener4 != null) {
                                        castListener4.startPlay();
                                    }
                                }
                            } else {
                                DLNACastService.CastListener castListener5 = dLNACastService.castListener;
                                if (castListener5 != null) {
                                    castListener5.stopState();
                                }
                            }
                            dLNACastService.getVolume();
                        }
                        DLNACastService.CastListener castListener6 = dLNACastService.castListener;
                        if (castListener6 != null) {
                            castListener6.pauseState();
                        }
                        DLNAPlayer dLNAPlayer3 = dLNACastService.mDLNAPlayer;
                        if (dLNAPlayer3 != null) {
                            dLNAPlayer3.setCurrentState(2);
                        }
                        dLNACastService.getPosition();
                        dLNACastService.getMediaInfo();
                        dLNACastService.getVolume();
                    }
                } catch (Exception unused) {
                }
            }

            @Override // com.hyqq.dlan.listener.DLNAControlCallback
            public void onFailure(ActionInvocation<? extends org.fourthline.cling.model.meta.Service<?, ?>> actionInvocation, int i, String str) {
                CommonExtKt.logD(this, Intrinsics.stringPlus("getTransportInfo error:", str));
            }
        });
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        try {
            this.mDeviceInfoList = new ArrayList<>();
            this.mConnectDeviceList = new HashMap<>();
            this.mPlayMediaName = new HashMap<>();
            this.mDLNAManager = new DLNAManager(this, new DLNAStateCallback() { // from class: com.movieboxpro.android.service.DLNACastService$onCreate$1
                @Override // com.hyqq.dlan.listener.DLNAStateCallback
                public void onDisconnected() {
                }

                @Override // com.hyqq.dlan.listener.DLNAStateCallback
                public void onConnected() {
                    DLNACastService.this.initDlNA();
                    DLNACastService.this.startTimer(true);
                    EventBus.getDefault().post(new DLNACastInitEvent());
                }
            });
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startTimer(boolean z) {
        Disposable disposable = this.disposable;
        if (disposable != null) {
            disposable.dispose();
        }
        Observable<R> compose = Observable.timer(this.searchDelay, TimeUnit.SECONDS).compose(RxUtils.rxSchedulerHelper());
        Intrinsics.checkNotNullExpressionValue(compose, "timer(searchDelay, TimeU…tils.rxSchedulerHelper())");
        this.disposable = SubscribersKt.subscribeBy$default(compose, (Function1) null, new DLNACastService$startTimer$1(this, z), DLNACastService$startTimer$2.INSTANCE, 1, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initDlNA() {
        DLNAPlayer dLNAPlayer = new DLNAPlayer(this);
        this.mDLNAPlayer = dLNAPlayer;
        if (dLNAPlayer != null) {
            dLNAPlayer.setConnectListener(new DLNADeviceConnectListener() { // from class: com.movieboxpro.android.service.DLNACastService$initDlNA$1
                @Override // com.hyqq.dlan.listener.DLNADeviceConnectListener
                public void onDisconnect(DeviceInfo deviceInfo, int i, int i2) {
                }

                @Override // com.hyqq.dlan.listener.DLNADeviceConnectListener
                public void onConnect(DeviceInfo deviceInfo, int i) {
                    try {
                        DLNACastService.this.mDeviceInfo = deviceInfo;
                        HashMap<String, DeviceInfo> hashMap = null;
                        if (deviceInfo != null) {
                            HashMap hashMap2 = DLNACastService.this.mConnectDeviceList;
                            if (hashMap2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mConnectDeviceList");
                                hashMap2 = null;
                            }
                            String udn = deviceInfo.getDevice().getIdentity().getUdn().toString();
                            Intrinsics.checkNotNullExpressionValue(udn, "it.device.identity.udn.toString()");
                            hashMap2.put(udn, deviceInfo);
                        }
                        DLNACastService.CastListener castListener = DLNACastService.this.castListener;
                        if (castListener != null) {
                            castListener.connectSuccess(deviceInfo);
                        }
                        DLNACastService.this.startTimer(false);
                        DLNACastService.this.getTransportInfo(true);
                        DLNACastService.CastListener castListener2 = DLNACastService.this.castListener;
                        if (castListener2 == null) {
                            return;
                        }
                        HashMap<String, DeviceInfo> hashMap3 = DLNACastService.this.mConnectDeviceList;
                        if (hashMap3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mConnectDeviceList");
                        } else {
                            hashMap = hashMap3;
                        }
                        castListener2.updateConnectDevice(hashMap);
                    } catch (Exception unused) {
                    }
                }
            });
        }
        this.mDLNARegistryListener = new DLNARegistryListener() { // from class: com.movieboxpro.android.service.DLNACastService$initDlNA$2
            @Override // com.hyqq.dlan.listener.DLNARegistryListener
            public void onDeviceUpdate(List<DeviceInfo> list) {
            }

            @Override // com.hyqq.dlan.listener.DLNARegistryListener
            public void onDeviceChanged(List<DeviceInfo> list) {
                if (list != null) {
                    try {
                        ArrayList arrayList = DLNACastService.this.mDeviceInfoList;
                        ArrayList arrayList2 = null;
                        if (arrayList == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mDeviceInfoList");
                            arrayList = null;
                        }
                        arrayList.clear();
                        DLNACastService dLNACastService = DLNACastService.this;
                        for (DeviceInfo deviceInfo : list) {
                            if (deviceInfo.getDevice().findService(new UDAServiceType("RenderingControl")) != null) {
                                HashMap hashMap = dLNACastService.mConnectDeviceList;
                                if (hashMap == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("mConnectDeviceList");
                                    hashMap = null;
                                }
                                if (hashMap.containsKey(deviceInfo.getDevice().getIdentity().getUdn().toString())) {
                                    deviceInfo.setConnected(true);
                                }
                                ArrayList arrayList3 = dLNACastService.mDeviceInfoList;
                                if (arrayList3 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("mDeviceInfoList");
                                    arrayList3 = null;
                                }
                                arrayList3.add(deviceInfo);
                            }
                        }
                        DLNACastService.CastListener castListener = DLNACastService.this.castListener;
                        if (castListener == null) {
                            return;
                        }
                        ArrayList arrayList4 = DLNACastService.this.mDeviceInfoList;
                        if (arrayList4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mDeviceInfoList");
                        } else {
                            arrayList2 = arrayList4;
                        }
                        castListener.searchSuccess(arrayList2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        DLNAManager dLNAManager = this.mDLNAManager;
        if (dLNAManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDLNAManager");
            dLNAManager = null;
        }
        dLNAManager.registerListener(this.mDLNARegistryListener);
    }

    public final void setUpdateListener(CastListener castListener) {
        this.castListener = castListener;
        if (castListener == null) {
            return;
        }
        ArrayList<DeviceInfo> arrayList = this.mDeviceInfoList;
        if (arrayList == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDeviceInfoList");
            arrayList = null;
        }
        castListener.searchSuccess(arrayList);
    }

    public final void getPosition() {
        try {
            DLNAPlayer dLNAPlayer = this.mDLNAPlayer;
            if (dLNAPlayer == null) {
                return;
            }
            dLNAPlayer.getPositionInfo(new DLNAControlCallback() { // from class: com.movieboxpro.android.service.DLNACastService$getPosition$1
                @Override // com.hyqq.dlan.listener.DLNAControlCallback
                public void onFailure(ActionInvocation<? extends org.fourthline.cling.model.meta.Service<?, ?>> actionInvocation, int i, String str) {
                }

                @Override // com.hyqq.dlan.listener.DLNAControlCallback
                public void onSuccess(ActionInvocation<? extends org.fourthline.cling.model.meta.Service<?, ?>> actionInvocation) {
                }

                @Override // com.hyqq.dlan.listener.DLNAControlCallback
                public void onReceived(ActionInvocation<? extends org.fourthline.cling.model.meta.Service<?, ?>> actionInvocation, Object... extra) {
                    DLNACastService.CastListener castListener;
                    Intrinsics.checkNotNullParameter(extra, "extra");
                    DLNACastService dLNACastService = DLNACastService.this;
                    if (!(extra.length == 0)) {
                        Object obj = extra[0];
                        PositionInfo positionInfo = obj instanceof PositionInfo ? (PositionInfo) obj : null;
                        if (positionInfo == null || (castListener = dLNACastService.castListener) == null) {
                            return;
                        }
                        castListener.positionUpdate(positionInfo.getTrackDurationSeconds(), positionInfo.getTrackElapsedSeconds());
                    }
                }
            });
        } catch (Exception unused) {
        }
    }

    public final void getVolume() {
        try {
            DLNAPlayer dLNAPlayer = this.mDLNAPlayer;
            if (dLNAPlayer == null) {
                return;
            }
            dLNAPlayer.getVolume(new DLNAControlCallback() { // from class: com.movieboxpro.android.service.DLNACastService$getVolume$1
                @Override // com.hyqq.dlan.listener.DLNAControlCallback
                public void onFailure(ActionInvocation<? extends org.fourthline.cling.model.meta.Service<?, ?>> actionInvocation, int i, String str) {
                }

                @Override // com.hyqq.dlan.listener.DLNAControlCallback
                public void onSuccess(ActionInvocation<? extends org.fourthline.cling.model.meta.Service<?, ?>> actionInvocation) {
                }

                @Override // com.hyqq.dlan.listener.DLNAControlCallback
                public void onReceived(ActionInvocation<? extends org.fourthline.cling.model.meta.Service<?, ?>> actionInvocation, Object... extra) {
                    DLNACastService.CastListener castListener;
                    Intrinsics.checkNotNullParameter(extra, "extra");
                    DLNACastService dLNACastService = DLNACastService.this;
                    if (!(!(extra.length == 0)) || (castListener = dLNACastService.castListener) == null) {
                        return;
                    }
                    Object obj = extra[0];
                    Integer num = obj instanceof Integer ? (Integer) obj : null;
                    castListener.setVolume(num != null ? num.intValue() : 0);
                }
            });
        } catch (Exception unused) {
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void unbindService(ServiceConnection conn) {
        Intrinsics.checkNotNullParameter(conn, "conn");
        this.castListener = null;
        super.unbindService(conn);
    }

    /* compiled from: DLNACastService.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0005\b\u0096\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bJ\u0006\u0010\n\u001a\u00020\u0004J&\u0010\u000b\u001a\"\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0006\u0018\u00010\fj\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\rJ\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u000fJ\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\u0004J\u0006\u0010\u0013\u001a\u00020\u0004J\u0016\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bJ\u0006\u0010\u0015\u001a\u00020\u0004J\u000e\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\bJ\u000e\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\u001b\u001a\u00020\u0004J\b\u0010\u001c\u001a\u00020\u0004H\u0002J\u0006\u0010\u001d\u001a\u00020\u0004J\u0006\u0010\u001e\u001a\u00020\u0004¨\u0006\u001f"}, d2 = {"Lcom/movieboxpro/android/service/DLNACastService$CastBinder;", "Landroid/os/Binder;", "(Lcom/movieboxpro/android/service/DLNACastService;)V", "connect", "", "info", "Lcom/hyqq/dlan/bean/DeviceInfo;", "url", "", "name", "getConnectDeviceInfo", "getConnectInfo", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "getDeviceInfo", "", "getService", "Lcom/movieboxpro/android/service/DLNACastService;", "mute", DownloadInfo.DOWNLOAD_PAUSE, "play", "resume", "seekTo", "progress", "setVolume", MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, "", "startSearch", "stopGetPosition", "stopPlay", "stopSearch", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public class CastBinder extends Binder {
        final /* synthetic */ DLNACastService this$0;

        public CastBinder(DLNACastService this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        public final DLNACastService getService() {
            return this.this$0;
        }

        public final void connect(DeviceInfo info, String url, String name) {
            Intrinsics.checkNotNullParameter(info, "info");
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(name, "name");
            try {
                this.this$0.url = url;
                HashMap hashMap = this.this$0.mPlayMediaName;
                if (hashMap == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mPlayMediaName");
                    hashMap = null;
                }
                hashMap.put(url, name);
                if (!info.isConnected()) {
                    DLNAPlayer dLNAPlayer = this.this$0.mDLNAPlayer;
                    if (dLNAPlayer == null) {
                        return;
                    }
                    dLNAPlayer.connect(info);
                    return;
                }
                CastListener castListener = this.this$0.castListener;
                if (castListener != null) {
                    castListener.connectSuccess(info);
                }
                DLNAPlayer dLNAPlayer2 = this.this$0.mDLNAPlayer;
                if (dLNAPlayer2 != null) {
                    dLNAPlayer2.setDevice(info);
                }
                this.this$0.getTransportInfo(true);
            } catch (Exception unused) {
            }
        }

        public final void getConnectDeviceInfo() {
            try {
                startSearch();
                this.this$0.startTimer(false);
                DeviceInfo deviceInfo = this.this$0.mDeviceInfo;
                if (deviceInfo != null && deviceInfo.isConnected()) {
                    this.this$0.getTransportInfo(false);
                }
            } catch (Exception unused) {
            }
        }

        public final void startSearch() {
            try {
                DLNAManager dLNAManager = this.this$0.mDLNAManager;
                if (dLNAManager == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mDLNAManager");
                    dLNAManager = null;
                }
                dLNAManager.startBrowser();
                this.this$0.startTimer(false);
            } catch (Exception unused) {
            }
        }

        public final void stopSearch() {
            try {
                DLNAManager dLNAManager = this.this$0.mDLNAManager;
                if (dLNAManager == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mDLNAManager");
                    dLNAManager = null;
                }
                dLNAManager.stopBrowser();
            } catch (Exception unused) {
            }
        }

        public final void resume() {
            try {
                DLNAPlayer dLNAPlayer = this.this$0.mDLNAPlayer;
                if (dLNAPlayer == null) {
                    return;
                }
                final DLNACastService dLNACastService = this.this$0;
                dLNAPlayer.play(new DLNAControlCallback() { // from class: com.movieboxpro.android.service.DLNACastService$CastBinder$resume$1
                    @Override // com.hyqq.dlan.listener.DLNAControlCallback
                    public void onSuccess(ActionInvocation<? extends org.fourthline.cling.model.meta.Service<?, ?>> actionInvocation) {
                        CommonExtKt.logD(this, "play success");
                        DLNACastService.CastListener castListener = DLNACastService.this.castListener;
                        if (castListener != null) {
                            castListener.playState();
                        }
                        DLNACastService.this.startGetPosition();
                    }

                    @Override // com.hyqq.dlan.listener.DLNAControlCallback
                    public void onReceived(ActionInvocation<? extends org.fourthline.cling.model.meta.Service<?, ?>> actionInvocation, Object... extra) {
                        Intrinsics.checkNotNullParameter(extra, "extra");
                        CommonExtKt.logD(this, "play onReceived");
                    }

                    @Override // com.hyqq.dlan.listener.DLNAControlCallback
                    public void onFailure(ActionInvocation<? extends org.fourthline.cling.model.meta.Service<?, ?>> actionInvocation, int i, String str) {
                        CommonExtKt.logD(this, "play onFailure");
                        DLNACastService.CastListener castListener = DLNACastService.this.castListener;
                        if (castListener == null) {
                            return;
                        }
                        castListener.playFailed(str);
                    }
                });
            } catch (Exception unused) {
            }
        }

        public final void play(String url, String name) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(name, "name");
            try {
                HashMap hashMap = this.this$0.mPlayMediaName;
                if (hashMap == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mPlayMediaName");
                    hashMap = null;
                }
                hashMap.put(url, name);
                this.this$0.url = url;
                CastListener castListener = this.this$0.castListener;
                if (castListener != null) {
                    castListener.loadingState();
                }
                com.hyqq.dlan.bean.MediaInfo mediaInfo = new com.hyqq.dlan.bean.MediaInfo();
                if (!TextUtils.isEmpty(url)) {
                    byte[] bytes = url.getBytes(Charsets.UTF_8);
                    Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                    mediaInfo.setMediaId(Base64.encodeToString(bytes, 2));
                    mediaInfo.setUri(url);
                }
                mediaInfo.setMediaName(name);
                mediaInfo.setMediaType(2);
                DLNAPlayer dLNAPlayer = this.this$0.mDLNAPlayer;
                if (dLNAPlayer != null) {
                    dLNAPlayer.setDataSource(mediaInfo);
                }
                DLNAPlayer dLNAPlayer2 = this.this$0.mDLNAPlayer;
                if (dLNAPlayer2 == null) {
                    return;
                }
                final DLNACastService dLNACastService = this.this$0;
                dLNAPlayer2.start(new DLNAControlCallback() { // from class: com.movieboxpro.android.service.DLNACastService$CastBinder$play$1
                    @Override // com.hyqq.dlan.listener.DLNAControlCallback
                    public void onSuccess(ActionInvocation<? extends org.fourthline.cling.model.meta.Service<?, ?>> actionInvocation) {
                        CommonExtKt.logD(this, "play success");
                        DLNACastService.this.startGetPosition();
                        DLNACastService.CastListener castListener2 = DLNACastService.this.castListener;
                        if (castListener2 == null) {
                            return;
                        }
                        castListener2.playState();
                    }

                    @Override // com.hyqq.dlan.listener.DLNAControlCallback
                    public void onReceived(ActionInvocation<? extends org.fourthline.cling.model.meta.Service<?, ?>> actionInvocation, Object... extra) {
                        Intrinsics.checkNotNullParameter(extra, "extra");
                        CommonExtKt.logD(this, "play onReceived");
                    }

                    @Override // com.hyqq.dlan.listener.DLNAControlCallback
                    public void onFailure(ActionInvocation<? extends org.fourthline.cling.model.meta.Service<?, ?>> actionInvocation, int i, String str) {
                        CommonExtKt.logD(this, "play onFailure");
                        DLNACastService.CastListener castListener2 = DLNACastService.this.castListener;
                        if (castListener2 == null) {
                            return;
                        }
                        castListener2.playFailed(str);
                    }
                });
            } catch (Exception unused) {
            }
        }

        public final void pause() {
            try {
                DLNAPlayer dLNAPlayer = this.this$0.mDLNAPlayer;
                if (dLNAPlayer == null) {
                    return;
                }
                final DLNACastService dLNACastService = this.this$0;
                dLNAPlayer.pause(new DLNAControlCallback() { // from class: com.movieboxpro.android.service.DLNACastService$CastBinder$pause$1
                    @Override // com.hyqq.dlan.listener.DLNAControlCallback
                    public void onSuccess(ActionInvocation<? extends org.fourthline.cling.model.meta.Service<?, ?>> actionInvocation) {
                        CommonExtKt.logD(this, "pause success");
                        DLNACastService.CastBinder.this.stopGetPosition();
                        DLNACastService.CastListener castListener = dLNACastService.castListener;
                        if (castListener == null) {
                            return;
                        }
                        castListener.pauseState();
                    }

                    @Override // com.hyqq.dlan.listener.DLNAControlCallback
                    public void onReceived(ActionInvocation<? extends org.fourthline.cling.model.meta.Service<?, ?>> actionInvocation, Object... extra) {
                        Intrinsics.checkNotNullParameter(extra, "extra");
                        CommonExtKt.logD(this, "pause onReceived");
                    }

                    @Override // com.hyqq.dlan.listener.DLNAControlCallback
                    public void onFailure(ActionInvocation<? extends org.fourthline.cling.model.meta.Service<?, ?>> actionInvocation, int i, String str) {
                        CommonExtKt.logD(this, "pause onFailure");
                    }
                });
            } catch (Exception unused) {
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void stopGetPosition() {
            Disposable disposable = this.this$0.getPositionDisposable;
            if (disposable == null) {
                return;
            }
            disposable.dispose();
        }

        public final void stopPlay() {
            try {
                DLNAPlayer dLNAPlayer = this.this$0.mDLNAPlayer;
                if (dLNAPlayer == null) {
                    return;
                }
                final DLNACastService dLNACastService = this.this$0;
                dLNAPlayer.stop(new DLNAControlCallback() { // from class: com.movieboxpro.android.service.DLNACastService$CastBinder$stopPlay$1
                    @Override // com.hyqq.dlan.listener.DLNAControlCallback
                    public void onSuccess(ActionInvocation<? extends org.fourthline.cling.model.meta.Service<?, ?>> actionInvocation) {
                        CommonExtKt.logD(this, "stop success");
                        DLNACastService.CastBinder.this.stopGetPosition();
                        DLNACastService.CastListener castListener = dLNACastService.castListener;
                        if (castListener == null) {
                            return;
                        }
                        castListener.stopState();
                    }

                    @Override // com.hyqq.dlan.listener.DLNAControlCallback
                    public void onReceived(ActionInvocation<? extends org.fourthline.cling.model.meta.Service<?, ?>> actionInvocation, Object... extra) {
                        Intrinsics.checkNotNullParameter(extra, "extra");
                        CommonExtKt.logD(this, "stop onReceived");
                    }

                    @Override // com.hyqq.dlan.listener.DLNAControlCallback
                    public void onFailure(ActionInvocation<? extends org.fourthline.cling.model.meta.Service<?, ?>> actionInvocation, int i, String str) {
                        CommonExtKt.logD(this, "stop onFailure");
                    }
                });
            } catch (Exception unused) {
            }
        }

        public final void setVolume(int i) {
            try {
                DLNAPlayer dLNAPlayer = this.this$0.mDLNAPlayer;
                if (dLNAPlayer == null) {
                    return;
                }
                dLNAPlayer.setVolume(i, new DLNAControlCallback() { // from class: com.movieboxpro.android.service.DLNACastService$CastBinder$setVolume$1
                    @Override // com.hyqq.dlan.listener.DLNAControlCallback
                    public void onSuccess(ActionInvocation<? extends org.fourthline.cling.model.meta.Service<?, ?>> actionInvocation) {
                        CommonExtKt.logD(this, "setVolume success");
                    }

                    @Override // com.hyqq.dlan.listener.DLNAControlCallback
                    public void onReceived(ActionInvocation<? extends org.fourthline.cling.model.meta.Service<?, ?>> actionInvocation, Object... extra) {
                        Intrinsics.checkNotNullParameter(extra, "extra");
                        CommonExtKt.logD(this, "setVolume onReceived");
                    }

                    @Override // com.hyqq.dlan.listener.DLNAControlCallback
                    public void onFailure(ActionInvocation<? extends org.fourthline.cling.model.meta.Service<?, ?>> actionInvocation, int i2, String str) {
                        CommonExtKt.logD(this, "setVolume onFailure");
                    }
                });
            } catch (Exception unused) {
            }
        }

        public final void mute() {
            try {
                DLNAPlayer dLNAPlayer = this.this$0.mDLNAPlayer;
                if (dLNAPlayer == null) {
                    return;
                }
                dLNAPlayer.mute(true, new DLNAControlCallback() { // from class: com.movieboxpro.android.service.DLNACastService$CastBinder$mute$1
                    @Override // com.hyqq.dlan.listener.DLNAControlCallback
                    public void onSuccess(ActionInvocation<? extends org.fourthline.cling.model.meta.Service<?, ?>> actionInvocation) {
                        CommonExtKt.logD(this, "mute success");
                    }

                    @Override // com.hyqq.dlan.listener.DLNAControlCallback
                    public void onReceived(ActionInvocation<? extends org.fourthline.cling.model.meta.Service<?, ?>> actionInvocation, Object... extra) {
                        Intrinsics.checkNotNullParameter(extra, "extra");
                        CommonExtKt.logD(this, "mute onReceived");
                    }

                    @Override // com.hyqq.dlan.listener.DLNAControlCallback
                    public void onFailure(ActionInvocation<? extends org.fourthline.cling.model.meta.Service<?, ?>> actionInvocation, int i, String str) {
                        CommonExtKt.logD(this, "mute onFailure");
                    }
                });
            } catch (Exception unused) {
            }
        }

        public final void seekTo(String progress) {
            Intrinsics.checkNotNullParameter(progress, "progress");
            try {
                DLNAPlayer dLNAPlayer = this.this$0.mDLNAPlayer;
                if (dLNAPlayer == null) {
                    return;
                }
                dLNAPlayer.seekTo(progress, new DLNAControlCallback() { // from class: com.movieboxpro.android.service.DLNACastService$CastBinder$seekTo$1
                    @Override // com.hyqq.dlan.listener.DLNAControlCallback
                    public void onSuccess(ActionInvocation<? extends org.fourthline.cling.model.meta.Service<?, ?>> actionInvocation) {
                        CommonExtKt.logD(this, "seekTo success");
                    }

                    @Override // com.hyqq.dlan.listener.DLNAControlCallback
                    public void onReceived(ActionInvocation<? extends org.fourthline.cling.model.meta.Service<?, ?>> actionInvocation, Object... extra) {
                        Intrinsics.checkNotNullParameter(extra, "extra");
                        CommonExtKt.logD(this, "seekTo onReceived");
                    }

                    @Override // com.hyqq.dlan.listener.DLNAControlCallback
                    public void onFailure(ActionInvocation<? extends org.fourthline.cling.model.meta.Service<?, ?>> actionInvocation, int i, String str) {
                        CommonExtKt.logD(this, "seekTo onFailure");
                    }
                });
            } catch (Exception unused) {
            }
        }

        public final List<DeviceInfo> getDeviceInfo() {
            ArrayList arrayList = this.this$0.mDeviceInfoList;
            if (arrayList == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mDeviceInfoList");
                arrayList = null;
            }
            return arrayList;
        }

        public final HashMap<String, DeviceInfo> getConnectInfo() {
            HashMap<String, DeviceInfo> hashMap = this.this$0.mConnectDeviceList;
            if (hashMap == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mConnectDeviceList");
                return null;
            }
            return hashMap;
        }
    }

    public final void setCurrUrl(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        this.url = url;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startGetPosition() {
        CommonExtKt.logD(this, "startGetPosition");
        Disposable disposable = this.getPositionDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        Observable.interval(1L, TimeUnit.SECONDS).subscribe(new Observer<Long>() { // from class: com.movieboxpro.android.service.DLNACastService$startGetPosition$1
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable e) {
                Intrinsics.checkNotNullParameter(e, "e");
            }

            @Override // io.reactivex.Observer
            public /* bridge */ /* synthetic */ void onNext(Long l) {
                onNext(l.longValue());
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                DLNACastService.this.getPositionDisposable = d;
            }

            public void onNext(long j) {
                DLNACastService.this.getPosition();
            }
        });
    }

    @Override // android.app.Service
    public void onDestroy() {
        try {
            DLNAPlayer dLNAPlayer = this.mDLNAPlayer;
            if (dLNAPlayer != null) {
                dLNAPlayer.destroy();
            }
            DLNAManager dLNAManager = this.mDLNAManager;
            DLNAManager dLNAManager2 = null;
            if (dLNAManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mDLNAManager");
                dLNAManager = null;
            }
            dLNAManager.unregisterListener(this.mDLNARegistryListener);
            DLNAManager dLNAManager3 = this.mDLNAManager;
            if (dLNAManager3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mDLNAManager");
            } else {
                dLNAManager2 = dLNAManager3;
            }
            dLNAManager2.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Disposable disposable = this.disposable;
        if (disposable != null) {
            disposable.dispose();
        }
        Disposable disposable2 = this.getPositionDisposable;
        if (disposable2 != null) {
            disposable2.dispose();
        }
        super.onDestroy();
    }
}
