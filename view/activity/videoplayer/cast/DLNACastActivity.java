package com.movieboxpro.android.view.activity.videoplayer.cast;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.constraintlayout.motion.widget.Key;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.hyqq.dlan.bean.DeviceInfo;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.BaseSimpleActivity;
import com.movieboxpro.android.base.CommBaseAdapter;
import com.movieboxpro.android.event.DLNACastInitEvent;
import com.movieboxpro.android.service.DLNACastService;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.utils.Utils;
import java.util.Formatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: DLNACastActivity.kt */
@Metadata(d1 = {"\u0000s\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0007*\u0001\u001d\u0018\u0000 92\u00020\u0001:\u00019B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\"\u001a\u00020#H\u0002J\b\u0010$\u001a\u00020\u0007H\u0014J\u0010\u0010%\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u0005H\u0002J\b\u0010'\u001a\u00020\u0010H\u0016J\b\u0010(\u001a\u00020#H\u0016J\b\u0010)\u001a\u00020#H\u0016J\b\u0010*\u001a\u00020#H\u0016J\u0010\u0010+\u001a\u00020#2\u0006\u0010,\u001a\u00020-H\u0007J\b\u0010.\u001a\u00020#H\u0014J\u0010\u0010/\u001a\u00020#2\u0006\u00100\u001a\u00020\u0010H\u0002J\u0010\u00101\u001a\u00020\u00182\u0006\u00102\u001a\u000203H\u0002J\b\u00104\u001a\u00020#H\u0002J\u0010\u00105\u001a\u00020#2\u0006\u00106\u001a\u00020\u0010H\u0002J\u0010\u00107\u001a\u00020#2\u0006\u00108\u001a\u00020\u0010H\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0018\u00010\tR\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0012\u001a\u00060\u0013j\u0002`\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001eR\u000e\u0010\u001f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lcom/movieboxpro/android/view/activity/videoplayer/cast/DLNACastActivity;", "Lcom/movieboxpro/android/base/BaseSimpleActivity;", "()V", "adapter", "Lcom/movieboxpro/android/base/CommBaseAdapter;", "Lcom/hyqq/dlan/bean/DeviceInfo;", "canceled", "", "castBinder", "Lcom/movieboxpro/android/service/DLNACastService$CastBinder;", "Lcom/movieboxpro/android/service/DLNACastService;", "castListener", "Lcom/movieboxpro/android/service/DLNACastService$CastListener;", "isBindService", "isMute", "lastPosition", "", "lastVolume", "mFormatBuilder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "mFormatter", "Ljava/util/Formatter;", "name", "", "objectAnimator", "Landroid/animation/ObjectAnimator;", "searchState", "serviceConnection", "com/movieboxpro/android/view/activity/videoplayer/cast/DLNACastActivity$serviceConnection$1", "Lcom/movieboxpro/android/view/activity/videoplayer/cast/DLNACastActivity$serviceConnection$1;", NotificationCompat.CATEGORY_STATUS, "tabPosition", "url", "bindLeCastService", "", "enableEventBus", "findExist", "item", "getLayoutResId", "initData", "initListener", "initView", "onCastInit", NotificationCompat.CATEGORY_EVENT, "Lcom/movieboxpro/android/event/DLNACastInitEvent;", "onDestroy", "setVolumeSeek", MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, "stringForTime", "time", "", "switchHintState", "switchPlayStatus", IjkMediaMeta.IJKM_KEY_TYPE, "switchSearchHint", "state", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class DLNACastActivity extends BaseSimpleActivity {
    public static final Companion Companion = new Companion(null);
    public static final int LOADING = 3;
    public static final int PAUSE = 2;
    public static final int PLAY = 1;
    public static final int STOP = 4;
    private CommBaseAdapter<DeviceInfo> adapter;
    private boolean canceled;
    private DLNACastService.CastBinder castBinder;
    private boolean isBindService;
    private boolean isMute;
    private int lastVolume;
    private ObjectAnimator objectAnimator;
    private int tabPosition;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String url = "";
    private String name = "";
    private int searchState = 1;
    private int status = 4;
    private int lastPosition = -1;
    private DLNACastService.CastListener castListener = new DLNACastActivity$castListener$1(this);
    private final StringBuilder mFormatBuilder = new StringBuilder();
    private Formatter mFormatter = new Formatter(this.mFormatBuilder, Locale.getDefault());
    private final DLNACastActivity$serviceConnection$1 serviceConnection = new ServiceConnection() { // from class: com.movieboxpro.android.view.activity.videoplayer.cast.DLNACastActivity$serviceConnection$1
        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            DLNACastActivity.this.castBinder = null;
            DLNACastActivity.this.castListener = null;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            DLNACastService.CastBinder castBinder;
            DLNACastService.CastBinder castBinder2;
            DLNACastService.CastBinder castBinder3;
            DLNACastService service;
            String str;
            DLNACastService service2;
            DLNACastService.CastListener castListener;
            DLNACastActivity.this.isBindService = true;
            DLNACastActivity.this.castBinder = iBinder instanceof DLNACastService.CastBinder ? (DLNACastService.CastBinder) iBinder : null;
            DLNACastActivity.this.switchHintState();
            castBinder = DLNACastActivity.this.castBinder;
            if (castBinder != null && (service2 = castBinder.getService()) != null) {
                castListener = DLNACastActivity.this.castListener;
                service2.setUpdateListener(castListener);
            }
            castBinder2 = DLNACastActivity.this.castBinder;
            if (castBinder2 != null && (service = castBinder2.getService()) != null) {
                str = DLNACastActivity.this.url;
                service.setCurrUrl(str);
            }
            castBinder3 = DLNACastActivity.this.castBinder;
            if (castBinder3 == null) {
                return;
            }
            castBinder3.getConnectDeviceInfo();
        }
    };

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    protected boolean enableEventBus() {
        return true;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public int getLayoutResId() {
        return R.layout.activity_dlna_cast;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initListener() {
        ((LinearLayout) _$_findCachedViewById(R.id.ll_back)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.cast.-$$Lambda$DLNACastActivity$wY4HO18cmqw9s81anon-FZbXtYU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DLNACastActivity.m874initListener$lambda0(DLNACastActivity.this, view);
            }
        });
        CommBaseAdapter<DeviceInfo> commBaseAdapter = this.adapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseAdapter = null;
        }
        commBaseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.cast.-$$Lambda$DLNACastActivity$TZB8UeBqn3KaUznp-I9ehoWoXDs
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                DLNACastActivity.m875initListener$lambda2(DLNACastActivity.this, baseQuickAdapter, view, i);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivVoice)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.cast.-$$Lambda$DLNACastActivity$UFihLFbfN17ttx4zTfEOFrxt2U0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DLNACastActivity.m876initListener$lambda3(DLNACastActivity.this, view);
            }
        });
        ((AppCompatSeekBar) _$_findCachedViewById(R.id.volumeSeekBar)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.cast.DLNACastActivity$initListener$4
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                DLNACastService.CastBinder castBinder;
                boolean z;
                Intrinsics.checkNotNullParameter(seekBar, "seekBar");
                int progress = seekBar.getProgress();
                castBinder = DLNACastActivity.this.castBinder;
                if (castBinder != null) {
                    castBinder.setVolume(progress);
                }
                DLNACastActivity.this.lastVolume = progress;
                DLNACastActivity dLNACastActivity = DLNACastActivity.this;
                if (progress == 0) {
                    ((ImageView) dLNACastActivity._$_findCachedViewById(R.id.ivVoice)).setImageResource(R.mipmap.ic_cast_mute);
                    z = true;
                } else {
                    ((ImageView) dLNACastActivity._$_findCachedViewById(R.id.ivVoice)).setImageResource(R.mipmap.ic_cast_voice);
                    z = false;
                }
                dLNACastActivity.isMute = z;
            }
        });
        ((AppCompatSeekBar) _$_findCachedViewById(R.id.seekBar)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.cast.DLNACastActivity$initListener$5
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                DLNACastService.CastBinder castBinder;
                Intrinsics.checkNotNullParameter(seekBar, "seekBar");
                StringBuilder sb = new StringBuilder();
                Formatter formatter = new Formatter(sb, Locale.getDefault());
                int progress = seekBar.getProgress();
                int i = progress % 60;
                int i2 = (progress / 60) % 60;
                int i3 = progress / 3600;
                sb.setLength(0);
                castBinder = DLNACastActivity.this.castBinder;
                if (castBinder == null) {
                    return;
                }
                String formatter2 = formatter.format("%d:%02d:%02d", Integer.valueOf(i3), Integer.valueOf(i2), Integer.valueOf(i)).toString();
                Intrinsics.checkNotNullExpressionValue(formatter2, "format.format(\"%d:%02d:%…utes, seconds).toString()");
                castBinder.seekTo(formatter2);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivPlayAndPause)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.cast.-$$Lambda$DLNACastActivity$ZAVgw9w6HCcnaGaFxMtKFM71pvc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DLNACastActivity.m877initListener$lambda4(DLNACastActivity.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tvSearchHint)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.cast.-$$Lambda$DLNACastActivity$riwZRAsmQMrJHZRujcobS0iHBUU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DLNACastActivity.m878initListener$lambda5(DLNACastActivity.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivStop)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.cast.-$$Lambda$DLNACastActivity$USC2fnOyutbaVKKtYVk2nTWmyzY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DLNACastActivity.m879initListener$lambda6(DLNACastActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m874initListener$lambda0(DLNACastActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m875initListener$lambda2(DLNACastActivity this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        DLNACastService.CastBinder castBinder;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        if (this$0.lastPosition == i) {
            return;
        }
        CommBaseAdapter<DeviceInfo> commBaseAdapter = this$0.adapter;
        CommBaseAdapter<DeviceInfo> commBaseAdapter2 = null;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseAdapter = null;
        }
        DeviceInfo item = commBaseAdapter.getItem(i);
        if (item != null) {
            item.setSelected(true);
        }
        if (this$0.lastPosition != -1) {
            CommBaseAdapter<DeviceInfo> commBaseAdapter3 = this$0.adapter;
            if (commBaseAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                commBaseAdapter3 = null;
            }
            DeviceInfo item2 = commBaseAdapter3.getItem(this$0.lastPosition);
            if (item2 != null) {
                item2.setSelected(false);
            }
        }
        this$0.lastPosition = i;
        if (item != null && (castBinder = this$0.castBinder) != null) {
            String str = this$0.url;
            String str2 = this$0.name;
            if (str2 == null) {
                str2 = "";
            }
            castBinder.connect(item, str, str2);
        }
        CommBaseAdapter<DeviceInfo> commBaseAdapter4 = this$0.adapter;
        if (commBaseAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            commBaseAdapter2 = commBaseAdapter4;
        }
        commBaseAdapter2.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-3  reason: not valid java name */
    public static final void m876initListener$lambda3(DLNACastActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DLNACastService.CastBinder castBinder = this$0.castBinder;
        if (castBinder != null) {
            castBinder.mute();
        }
        if (this$0.isMute) {
            this$0.isMute = false;
            ((AppCompatSeekBar) this$0._$_findCachedViewById(R.id.volumeSeekBar)).setProgress(this$0.lastVolume);
            DLNACastService.CastBinder castBinder2 = this$0.castBinder;
            if (castBinder2 != null) {
                castBinder2.setVolume(this$0.lastVolume);
            }
            ((ImageView) this$0._$_findCachedViewById(R.id.ivVoice)).setImageResource(R.mipmap.ic_cast_voice);
            return;
        }
        this$0.isMute = true;
        ((AppCompatSeekBar) this$0._$_findCachedViewById(R.id.volumeSeekBar)).setProgress(0);
        DLNACastService.CastBinder castBinder3 = this$0.castBinder;
        if (castBinder3 != null) {
            castBinder3.setVolume(0);
        }
        ((ImageView) this$0._$_findCachedViewById(R.id.ivVoice)).setImageResource(R.mipmap.ic_cast_mute);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-4  reason: not valid java name */
    public static final void m877initListener$lambda4(DLNACastActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = this$0.status;
        boolean z = true;
        if (i == 1) {
            DLNACastService.CastBinder castBinder = this$0.castBinder;
            if (castBinder == null) {
                return;
            }
            castBinder.pause();
        } else if (i == 2) {
            DLNACastService.CastBinder castBinder2 = this$0.castBinder;
            if (castBinder2 == null) {
                return;
            }
            castBinder2.resume();
        } else if (i == 4) {
            DLNACastService.CastBinder castBinder3 = this$0.castBinder;
            List<DeviceInfo> deviceInfo = castBinder3 == null ? null : castBinder3.getDeviceInfo();
            if (deviceInfo != null && !deviceInfo.isEmpty()) {
                z = false;
            }
            if (!z) {
                DLNACastService.CastBinder castBinder4 = this$0.castBinder;
                if (castBinder4 == null) {
                    return;
                }
                String str = this$0.url;
                String str2 = this$0.name;
                if (str2 == null) {
                    str2 = "";
                }
                castBinder4.play(str, str2);
                return;
            }
            ToastUtils.showShort("Please connect a device", new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-5  reason: not valid java name */
    public static final void m878initListener$lambda5(DLNACastActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.searchState == 2) {
            DLNACastService.CastBinder castBinder = this$0.castBinder;
            if (castBinder != null) {
                castBinder.startSearch();
            }
            this$0.switchSearchHint(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-6  reason: not valid java name */
    public static final void m879initListener$lambda6(DLNACastActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DLNACastService.CastBinder castBinder = this$0.castBinder;
        if (castBinder == null) {
            return;
        }
        castBinder.stopPlay();
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initData() {
        ((TextView) _$_findCachedViewById(R.id.tv_title)).setText("NativeCast");
        String stringExtra = getIntent().getStringExtra("url");
        if (stringExtra == null) {
            stringExtra = "";
        }
        this.url = stringExtra;
        String stringExtra2 = getIntent().getStringExtra("name");
        this.name = stringExtra2 != null ? stringExtra2 : "";
        ((TextView) _$_findCachedViewById(R.id.tvName)).setText(this.name);
        this.adapter = new CommBaseAdapter<>(R.layout.adapter_cast_device_item, new DLNACastActivity$initData$1(this), null, 4, null);
        ((RecyclerView) _$_findCachedViewById(R.id.recyclerView)).setLayoutManager(new LinearLayoutManager(this));
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.recyclerView);
        Intrinsics.checkNotNullExpressionValue(recyclerView, "recyclerView");
        CommonExtKt.disableRefreshAnimation(recyclerView);
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.recyclerView);
        CommBaseAdapter<DeviceInfo> commBaseAdapter = this.adapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseAdapter = null;
        }
        recyclerView2.setAdapter(commBaseAdapter);
        bindLeCastService();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void switchSearchHint(int i) {
        this.searchState = i;
        if (i != 1) {
            if (i != 2) {
                return;
            }
            this.canceled = true;
            ((TextView) _$_findCachedViewById(R.id.tvSearchHint)).setText("Search completed,Click to search");
            return;
        }
        this.canceled = false;
        ObjectAnimator objectAnimator = this.objectAnimator;
        if (objectAnimator != null) {
            objectAnimator.start();
        }
        ((TextView) _$_findCachedViewById(R.id.tvSearchHint)).setText("Searching for Devices…");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean findExist(DeviceInfo deviceInfo) {
        CommBaseAdapter<DeviceInfo> commBaseAdapter = this.adapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseAdapter = null;
        }
        for (DeviceInfo deviceInfo2 : commBaseAdapter.getData()) {
            if (Intrinsics.areEqual(deviceInfo2.getDevice().getIdentity().getUdn().toString(), deviceInfo.getDevice().getIdentity().getUdn().toString())) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setVolumeSeek(final int i) {
        runOnUiThread(new Runnable() { // from class: com.movieboxpro.android.view.activity.videoplayer.cast.-$$Lambda$DLNACastActivity$dcP4TC_5JVNcygmsGzMfw7sbv8c
            @Override // java.lang.Runnable
            public final void run() {
                DLNACastActivity.m882setVolumeSeek$lambda8(DLNACastActivity.this, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setVolumeSeek$lambda-8  reason: not valid java name */
    public static final void m882setVolumeSeek$lambda8(DLNACastActivity this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((AppCompatSeekBar) this$0._$_findCachedViewById(R.id.volumeSeekBar)).setProgress(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String stringForTime(long j) {
        long j2 = 60;
        long j3 = j % j2;
        long j4 = (j / j2) % j2;
        long j5 = j / 3600;
        this.mFormatBuilder.setLength(0);
        if (j5 > 0) {
            String formatter = this.mFormatter.format("%d:%02d:%02d", Long.valueOf(j5), Long.valueOf(j4), Long.valueOf(j3)).toString();
            Intrinsics.checkNotNullExpressionValue(formatter, "{\n            mFormatter…nds).toString()\n        }");
            return formatter;
        }
        String formatter2 = this.mFormatter.format("%02d:%02d", Long.valueOf(j4), Long.valueOf(j3)).toString();
        Intrinsics.checkNotNullExpressionValue(formatter2, "{\n            mFormatter…nds).toString()\n        }");
        return formatter2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void switchPlayStatus(final int i) {
        runOnUiThread(new Runnable() { // from class: com.movieboxpro.android.view.activity.videoplayer.cast.-$$Lambda$DLNACastActivity$SN_qSy9XHlEG7q4y4EQm3v3dfv0
            @Override // java.lang.Runnable
            public final void run() {
                DLNACastActivity.m883switchPlayStatus$lambda9(i, this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: switchPlayStatus$lambda-9  reason: not valid java name */
    public static final void m883switchPlayStatus$lambda9(int i, DLNACastActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == 1) {
            this$0.status = 1;
            ((ImageView) this$0._$_findCachedViewById(R.id.ivPlayAndPause)).setImageResource(R.mipmap.ic_cast_pause_status);
            ImageView ivPlayAndPause = (ImageView) this$0._$_findCachedViewById(R.id.ivPlayAndPause);
            Intrinsics.checkNotNullExpressionValue(ivPlayAndPause, "ivPlayAndPause");
            CommonExtKt.visible(ivPlayAndPause);
            ProgressBar loading = (ProgressBar) this$0._$_findCachedViewById(R.id.loading);
            Intrinsics.checkNotNullExpressionValue(loading, "loading");
            CommonExtKt.gone(loading);
        } else if (i == 2) {
            this$0.status = 2;
            ((ImageView) this$0._$_findCachedViewById(R.id.ivPlayAndPause)).setImageResource(R.mipmap.ic_cast_play_status);
            ImageView ivPlayAndPause2 = (ImageView) this$0._$_findCachedViewById(R.id.ivPlayAndPause);
            Intrinsics.checkNotNullExpressionValue(ivPlayAndPause2, "ivPlayAndPause");
            CommonExtKt.visible(ivPlayAndPause2);
            ProgressBar loading2 = (ProgressBar) this$0._$_findCachedViewById(R.id.loading);
            Intrinsics.checkNotNullExpressionValue(loading2, "loading");
            CommonExtKt.invisible(loading2);
        } else if (i == 3) {
            this$0.status = 3;
            ImageView ivPlayAndPause3 = (ImageView) this$0._$_findCachedViewById(R.id.ivPlayAndPause);
            Intrinsics.checkNotNullExpressionValue(ivPlayAndPause3, "ivPlayAndPause");
            CommonExtKt.invisible(ivPlayAndPause3);
            ProgressBar loading3 = (ProgressBar) this$0._$_findCachedViewById(R.id.loading);
            Intrinsics.checkNotNullExpressionValue(loading3, "loading");
            CommonExtKt.visible(loading3);
        } else if (i != 4) {
        } else {
            this$0.status = 4;
            ((ImageView) this$0._$_findCachedViewById(R.id.ivPlayAndPause)).setImageResource(R.mipmap.ic_cast_play_status);
            ImageView ivPlayAndPause4 = (ImageView) this$0._$_findCachedViewById(R.id.ivPlayAndPause);
            Intrinsics.checkNotNullExpressionValue(ivPlayAndPause4, "ivPlayAndPause");
            CommonExtKt.visible(ivPlayAndPause4);
            ProgressBar loading4 = (ProgressBar) this$0._$_findCachedViewById(R.id.loading);
            Intrinsics.checkNotNullExpressionValue(loading4, "loading");
            CommonExtKt.invisible(loading4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void switchHintState() {
        DLNACastService.CastBinder castBinder = this.castBinder;
        HashMap<String, DeviceInfo> connectInfo = castBinder == null ? null : castBinder.getConnectInfo();
        boolean z = false;
        if (connectInfo != null && (!connectInfo.isEmpty())) {
            z = true;
        }
        if (z) {
            TextView textView = (TextView) _$_findCachedViewById(R.id.tvConnectHint);
            if (textView != null) {
                CommonExtKt.gone(textView);
            }
            ConstraintLayout clPlayControl = (ConstraintLayout) _$_findCachedViewById(R.id.clPlayControl);
            Intrinsics.checkNotNullExpressionValue(clPlayControl, "clPlayControl");
            CommonExtKt.visible(clPlayControl);
            return;
        }
        TextView textView2 = (TextView) _$_findCachedViewById(R.id.tvConnectHint);
        if (textView2 != null) {
            CommonExtKt.visible(textView2);
        }
        ConstraintLayout clPlayControl2 = (ConstraintLayout) _$_findCachedViewById(R.id.clPlayControl);
        Intrinsics.checkNotNullExpressionValue(clPlayControl2, "clPlayControl");
        CommonExtKt.gone(clPlayControl2);
    }

    private final void bindLeCastService() {
        if (CommonUtils.isServiceRunning(DLNACastService.class)) {
            bindService(new Intent(this, DLNACastService.class), this.serviceConnection, 1);
        } else if (Utils.isAppForeground()) {
            startService(new Intent(this, DLNACastService.class));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onCastInit(DLNACastInitEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        bindService(new Intent(this, DLNACastService.class), this.serviceConnection, 1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseSimpleActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        try {
            if (this.isBindService) {
                unbindService(this.serviceConnection);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObjectAnimator objectAnimator = this.objectAnimator;
        if (objectAnimator != null) {
            objectAnimator.removeAllListeners();
        }
        this.objectAnimator = null;
        super.onDestroy();
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initView() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat((ImageView) _$_findCachedViewById(R.id.ivSearch), Key.ROTATION, 360.0f);
        this.objectAnimator = ofFloat;
        Intrinsics.checkNotNull(ofFloat);
        ofFloat.setDuration(1500L);
        ObjectAnimator objectAnimator = this.objectAnimator;
        Intrinsics.checkNotNull(objectAnimator);
        objectAnimator.setRepeatMode(1);
        ObjectAnimator objectAnimator2 = this.objectAnimator;
        Intrinsics.checkNotNull(objectAnimator2);
        objectAnimator2.setRepeatCount(-1);
        ObjectAnimator objectAnimator3 = this.objectAnimator;
        Intrinsics.checkNotNull(objectAnimator3);
        objectAnimator3.start();
        ObjectAnimator objectAnimator4 = this.objectAnimator;
        Intrinsics.checkNotNull(objectAnimator4);
        objectAnimator4.addListener(new Animator.AnimatorListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.cast.DLNACastActivity$initView$1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }

            /* JADX WARN: Code restructure failed: missing block: B:4:0x0008, code lost:
                r1 = r0.this$0.objectAnimator;
             */
            @Override // android.animation.Animator.AnimatorListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onAnimationRepeat(android.animation.Animator r1) {
                /*
                    r0 = this;
                    com.movieboxpro.android.view.activity.videoplayer.cast.DLNACastActivity r1 = com.movieboxpro.android.view.activity.videoplayer.cast.DLNACastActivity.this
                    boolean r1 = com.movieboxpro.android.view.activity.videoplayer.cast.DLNACastActivity.access$getCanceled$p(r1)
                    if (r1 == 0) goto L14
                    com.movieboxpro.android.view.activity.videoplayer.cast.DLNACastActivity r1 = com.movieboxpro.android.view.activity.videoplayer.cast.DLNACastActivity.this
                    android.animation.ObjectAnimator r1 = com.movieboxpro.android.view.activity.videoplayer.cast.DLNACastActivity.access$getObjectAnimator$p(r1)
                    if (r1 != 0) goto L11
                    goto L14
                L11:
                    r1.cancel()
                L14:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.view.activity.videoplayer.cast.DLNACastActivity$initView$1.onAnimationRepeat(android.animation.Animator):void");
            }
        });
    }

    /* compiled from: DLNACastActivity.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/movieboxpro/android/view/activity/videoplayer/cast/DLNACastActivity$Companion;", "", "()V", "LOADING", "", "PAUSE", "PLAY", "STOP", TtmlNode.START, "", "context", "Landroid/content/Context;", "url", "", "name", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context, String str, String str2) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(context, DLNACastActivity.class);
            intent.putExtra("url", str);
            intent.putExtra("name", str2);
            context.startActivity(intent);
        }
    }
}
