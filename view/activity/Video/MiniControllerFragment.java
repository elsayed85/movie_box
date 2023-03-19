package com.movieboxpro.android.view.activity.Video;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.media.ImageHints;
import com.google.android.gms.cast.framework.media.MediaQueue;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIMediaController;
import com.google.android.gms.cast.framework.media.widget.ControlButtonsContainer;
import com.movieboxpro.android.R;
import com.movieboxpro.android.event.OnCastStartEvent;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.view.fragment.CastPlayListFragment;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
/* compiled from: MiniControllerFragment.kt */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0010\u0010\u001c\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\u0005H\u0016J\b\u0010\u001e\u001a\u00020\u0005H\u0016J\u0010\u0010\u001f\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u0005H\u0016J\b\u0010 \u001a\u00020\u0017H\u0016J\b\u0010!\u001a\u00020\u0019H\u0002J\u0010\u0010\"\u001a\u00020\u00192\u0006\u0010#\u001a\u00020$H\u0007J&\u0010%\u001a\u0004\u0018\u00010\u001b2\u0006\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010)2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\b\u0010,\u001a\u00020\u0019H\u0016J\b\u0010-\u001a\u00020\u0019H\u0016J\u001a\u0010.\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\u001c\u0010/\u001a\u00020\u00192\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0002J\u0012\u00100\u001a\u00020\u00192\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lcom/movieboxpro/android/view/activity/Video/MiniControllerFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/google/android/gms/cast/framework/media/widget/ControlButtonsContainer;", "()V", "currId", "", "disposable", "Lio/reactivex/disposables/Disposable;", "imageViews", "", "Landroid/widget/ImageView;", "[Landroid/widget/ImageView;", "mediaQueue", "Lcom/google/android/gms/cast/framework/media/MediaQueue;", "mediaQueueCallback", "Lcom/google/android/gms/cast/framework/media/MediaQueue$Callback;", "mediaStatus", "Lcom/google/android/gms/cast/MediaStatus;", "remoteCallback", "Lcom/google/android/gms/cast/framework/media/RemoteMediaClient$Callback;", "remoteMediaClient", "Lcom/google/android/gms/cast/framework/media/RemoteMediaClient;", "uiMediaController", "Lcom/google/android/gms/cast/framework/media/uicontroller/UIMediaController;", "bindView", "", "view", "Landroid/view/View;", "getButtonImageViewAt", "p0", "getButtonSlotCount", "getButtonTypeAt", "getUIMediaController", "initListener", "onCastStart", NotificationCompat.CATEGORY_EVENT, "Lcom/movieboxpro/android/event/OnCastStartEvent;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onStart", "onViewCreated", "setCallback", "setControllerUi", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class MiniControllerFragment extends Fragment implements ControlButtonsContainer {
    private Disposable disposable;
    private MediaQueue mediaQueue;
    private MediaQueue.Callback mediaQueueCallback;
    private MediaStatus mediaStatus;
    private RemoteMediaClient.Callback remoteCallback;
    private RemoteMediaClient remoteMediaClient;
    private UIMediaController uiMediaController;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private final ImageView[] imageViews = new ImageView[3];
    private int currId = -1;

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

    @Override // com.google.android.gms.cast.framework.media.widget.ControlButtonsContainer
    public int getButtonSlotCount() {
        return 3;
    }

    @Override // com.google.android.gms.cast.framework.media.widget.ControlButtonsContainer
    public int getButtonTypeAt(int i) {
        return 0;
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.mini_controller, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        bindView(view);
        initListener();
    }

    private final void initListener() {
        ((ImageView) _$_findCachedViewById(R.id.ivQueue)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.Video.-$$Lambda$MiniControllerFragment$9tDqyZh7gb_5Tgt3cFOaDs6JYwE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MiniControllerFragment.m308initListener$lambda0(MiniControllerFragment.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m308initListener$lambda0(MiniControllerFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CastPlayListFragment castPlayListFragment = new CastPlayListFragment();
        castPlayListFragment.setMediaQueue(this$0.mediaQueue, this$0.mediaStatus, this$0.currId, this$0.remoteMediaClient);
        castPlayListFragment.show(this$0.getChildFragmentManager(), "MiniControllerFragment");
    }

    private final void bindView(View view) {
        UIMediaController uIMediaController;
        CommonExtKt.gone(view);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            UIMediaController uIMediaController2 = new UIMediaController(activity);
            this.uiMediaController = uIMediaController2;
            if (uIMediaController2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("uiMediaController");
                uIMediaController2 = null;
            }
            uIMediaController2.bindViewVisibilityToMediaSession(view, 8);
            UIMediaController uIMediaController3 = this.uiMediaController;
            if (uIMediaController3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("uiMediaController");
                uIMediaController3 = null;
            }
            uIMediaController3.bindTextViewToMetadataOfCurrentItem((TextView) _$_findCachedViewById(R.id.tvTitle), MediaMetadata.KEY_TITLE);
            UIMediaController uIMediaController4 = this.uiMediaController;
            if (uIMediaController4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("uiMediaController");
                uIMediaController4 = null;
            }
            uIMediaController4.bindProgressBar((ProgressBar) _$_findCachedViewById(R.id.progressBar));
            UIMediaController uIMediaController5 = this.uiMediaController;
            if (uIMediaController5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("uiMediaController");
                uIMediaController5 = null;
            }
            uIMediaController5.bindViewToLaunchExpandedController((ConstraintLayout) _$_findCachedViewById(R.id.container));
            Context context = getContext();
            if (context != null) {
                Drawable drawable = ContextCompat.getDrawable(context, R.mipmap.ic_cast_play);
                Drawable drawable2 = ContextCompat.getDrawable(context, R.mipmap.ic_cast_pause);
                Drawable drawable3 = ContextCompat.getDrawable(context, R.mipmap.ic_cast_pause);
                UIMediaController uIMediaController6 = this.uiMediaController;
                if (uIMediaController6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("uiMediaController");
                    uIMediaController = null;
                } else {
                    uIMediaController = uIMediaController6;
                }
                Intrinsics.checkNotNull(drawable);
                Intrinsics.checkNotNull(drawable2);
                Intrinsics.checkNotNull(drawable3);
                uIMediaController.bindImageViewToPlayPauseToggle((ImageView) _$_findCachedViewById(R.id.ivPlayAndPause), drawable, drawable2, drawable3, (ProgressBar) _$_findCachedViewById(R.id.loading), true);
            }
            ((ObservableSubscribeProxy) Observable.interval(1L, 1L, TimeUnit.SECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<Long>() { // from class: com.movieboxpro.android.view.activity.Video.MiniControllerFragment$bindView$2
                @Override // io.reactivex.Observer
                public void onError(Throwable e) {
                    Intrinsics.checkNotNullParameter(e, "e");
                }

                @Override // io.reactivex.Observer
                public /* bridge */ /* synthetic */ void onNext(Long l) {
                    onNext(l.longValue());
                }

                @Override // io.reactivex.Observer
                public void onComplete() {
                    Log.d("MiniControllerFragment", "onComplete");
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable d) {
                    Intrinsics.checkNotNullParameter(d, "d");
                    MiniControllerFragment.this.disposable = d;
                }

                public void onNext(long j) {
                    UIMediaController uIMediaController7;
                    Disposable disposable;
                    UIMediaController uIMediaController8;
                    RemoteMediaClient remoteMediaClient;
                    MediaQueue mediaQueue;
                    RemoteMediaClient remoteMediaClient2;
                    RemoteMediaClient remoteMediaClient3;
                    uIMediaController7 = MiniControllerFragment.this.uiMediaController;
                    if (uIMediaController7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("uiMediaController");
                        uIMediaController7 = null;
                    }
                    if (uIMediaController7.isActive()) {
                        Log.d("MiniControllerFragment", "uiMediaController isActive");
                        disposable = MiniControllerFragment.this.disposable;
                        if (disposable != null) {
                            disposable.dispose();
                        }
                        MiniControllerFragment miniControllerFragment = MiniControllerFragment.this;
                        uIMediaController8 = miniControllerFragment.uiMediaController;
                        if (uIMediaController8 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("uiMediaController");
                            uIMediaController8 = null;
                        }
                        miniControllerFragment.remoteMediaClient = uIMediaController8.getRemoteMediaClient();
                        MiniControllerFragment miniControllerFragment2 = MiniControllerFragment.this;
                        remoteMediaClient = miniControllerFragment2.remoteMediaClient;
                        miniControllerFragment2.mediaQueue = remoteMediaClient != null ? remoteMediaClient.getMediaQueue() : null;
                        MiniControllerFragment miniControllerFragment3 = MiniControllerFragment.this;
                        mediaQueue = miniControllerFragment3.mediaQueue;
                        remoteMediaClient2 = MiniControllerFragment.this.remoteMediaClient;
                        miniControllerFragment3.setCallback(mediaQueue, remoteMediaClient2);
                        MiniControllerFragment miniControllerFragment4 = MiniControllerFragment.this;
                        remoteMediaClient3 = miniControllerFragment4.remoteMediaClient;
                        miniControllerFragment4.setControllerUi(remoteMediaClient3);
                    }
                }
            });
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onCastStart(OnCastStartEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        UIMediaController uIMediaController = this.uiMediaController;
        if (uIMediaController == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uiMediaController");
            uIMediaController = null;
        }
        if (uIMediaController.isActive()) {
            Disposable disposable = this.disposable;
            if (disposable != null) {
                disposable.dispose();
            }
            Log.d("MiniControllerFragment", "onCastStart");
            Disposable disposable2 = this.disposable;
            if (disposable2 != null) {
                disposable2.dispose();
            }
            UIMediaController uIMediaController2 = this.uiMediaController;
            if (uIMediaController2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("uiMediaController");
                uIMediaController2 = null;
            }
            RemoteMediaClient remoteMediaClient = uIMediaController2.getRemoteMediaClient();
            this.remoteMediaClient = remoteMediaClient;
            MediaQueue mediaQueue = remoteMediaClient != null ? remoteMediaClient.getMediaQueue() : null;
            this.mediaQueue = mediaQueue;
            setCallback(mediaQueue, this.remoteMediaClient);
            setControllerUi(this.remoteMediaClient);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setControllerUi(RemoteMediaClient remoteMediaClient) {
        MediaStatus mediaStatus;
        MediaInfo mediaInfo;
        int[] itemIds;
        MediaQueueItem currentItem;
        int i;
        int[] itemIds2;
        int[] itemIds3;
        int[] itemIds4;
        Integer num = null;
        MediaMetadata metadata = (remoteMediaClient == null || (mediaStatus = remoteMediaClient.getMediaStatus()) == null || (mediaInfo = mediaStatus.getMediaInfo()) == null) ? null : mediaInfo.getMetadata();
        this.mediaQueue = remoteMediaClient == null ? null : remoteMediaClient.getMediaQueue();
        MediaStatus mediaStatus2 = remoteMediaClient == null ? null : remoteMediaClient.getMediaStatus();
        MediaQueue mediaQueue = this.mediaQueue;
        if ((mediaQueue == null || (itemIds = mediaQueue.getItemIds()) == null || itemIds.length != 0) ? false : true) {
            return;
        }
        this.mediaStatus = remoteMediaClient == null ? null : remoteMediaClient.getMediaStatus();
        Integer valueOf = (remoteMediaClient == null || (currentItem = remoteMediaClient.getCurrentItem()) == null) ? null : Integer.valueOf(currentItem.getItemId());
        MediaQueue mediaQueue2 = this.mediaQueue;
        if (mediaQueue2 != null && (itemIds4 = mediaQueue2.getItemIds()) != null) {
            int length = itemIds4.length;
            int i2 = 0;
            i = 0;
            while (i2 < length) {
                int i3 = itemIds4[i2];
                i2++;
                int i4 = i + 1;
                if (valueOf != null && valueOf.intValue() == i3) {
                    break;
                }
                i = i4;
            }
        }
        i = -1;
        this.currId = mediaStatus2 != null ? mediaStatus2.getCurrentItemId() : -1;
        String string = metadata == null ? null : metadata.getString(MediaMetadata.KEY_SUBTITLE);
        Context context = getContext();
        if (context != null) {
            UIMediaController uIMediaController = this.uiMediaController;
            if (uIMediaController == null) {
                Intrinsics.throwUninitializedPropertyAccessException("uiMediaController");
                uIMediaController = null;
            }
            uIMediaController.bindImageViewToImageOfCurrentItem((ImageView) _$_findCachedViewById(R.id.ivTvImage), new ImageHints(2, DensityUtils.dp2px(context, 96.0f), DensityUtils.dp2px(context, 58.0f)), R.drawable.ic_default_land);
        }
        if (string != null && !Intrinsics.areEqual(string, "null")) {
            TextView textView = (TextView) _$_findCachedViewById(R.id.tvInfo);
            if (textView == null) {
                return;
            }
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Object[] objArr = new Object[3];
            objArr[0] = string;
            objArr[1] = Integer.valueOf(i + 1);
            MediaQueue mediaQueue3 = this.mediaQueue;
            if (mediaQueue3 != null && (itemIds3 = mediaQueue3.getItemIds()) != null) {
                num = Integer.valueOf(itemIds3.length);
            }
            objArr[2] = num;
            String format = String.format("%s Queue %s/%s", Arrays.copyOf(objArr, 3));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            textView.setText(format);
            return;
        }
        TextView textView2 = (TextView) _$_findCachedViewById(R.id.tvInfo);
        if (textView2 == null) {
            return;
        }
        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
        Object[] objArr2 = new Object[2];
        objArr2[0] = Integer.valueOf(i + 1);
        MediaQueue mediaQueue4 = this.mediaQueue;
        if (mediaQueue4 != null && (itemIds2 = mediaQueue4.getItemIds()) != null) {
            num = Integer.valueOf(itemIds2.length);
        }
        objArr2[1] = num;
        String format2 = String.format("Queue %s/%s", Arrays.copyOf(objArr2, 2));
        Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
        textView2.setText(format2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCallback(MediaQueue mediaQueue, final RemoteMediaClient remoteMediaClient) {
        RemoteMediaClient.Callback callback = new RemoteMediaClient.Callback() { // from class: com.movieboxpro.android.view.activity.Video.MiniControllerFragment$setCallback$1
            @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
            public void onQueueStatusUpdated() {
                super.onQueueStatusUpdated();
                MiniControllerFragment.this.setControllerUi(remoteMediaClient);
            }

            @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
            public void onStatusUpdated() {
                super.onStatusUpdated();
            }
        };
        this.remoteCallback = callback;
        MediaQueue.Callback callback2 = null;
        if (remoteMediaClient != null) {
            if (callback == null) {
                Intrinsics.throwUninitializedPropertyAccessException("remoteCallback");
                callback = null;
            }
            remoteMediaClient.registerCallback(callback);
        }
        MediaQueue.Callback callback3 = new MediaQueue.Callback() { // from class: com.movieboxpro.android.view.activity.Video.MiniControllerFragment$setCallback$2
            @Override // com.google.android.gms.cast.framework.media.MediaQueue.Callback
            public void mediaQueueChanged() {
                super.mediaQueueChanged();
                MiniControllerFragment.this.setControllerUi(remoteMediaClient);
            }

            @Override // com.google.android.gms.cast.framework.media.MediaQueue.Callback
            public void itemsReloaded() {
                super.itemsReloaded();
            }
        };
        this.mediaQueueCallback = callback3;
        if (mediaQueue == null) {
            return;
        }
        if (callback3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaQueueCallback");
        } else {
            callback2 = callback3;
        }
        mediaQueue.registerCallback(callback2);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (EventBus.getDefault().isRegistered(this)) {
            return;
        }
        EventBus.getDefault().register(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        Disposable disposable;
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        Disposable disposable2 = this.disposable;
        boolean z = false;
        if (disposable2 != null && disposable2.isDisposed()) {
            z = true;
        }
        if (!z && (disposable = this.disposable) != null) {
            disposable.dispose();
        }
        Log.d("MiniControllerFragment", "ondestroy");
        UIMediaController uIMediaController = this.uiMediaController;
        RemoteMediaClient.Callback callback = null;
        if (uIMediaController != null) {
            if (uIMediaController == null) {
                Intrinsics.throwUninitializedPropertyAccessException("uiMediaController");
                uIMediaController = null;
            }
            uIMediaController.dispose();
        }
        MediaQueue mediaQueue = this.mediaQueue;
        if (mediaQueue != null) {
            MediaQueue.Callback callback2 = this.mediaQueueCallback;
            if (callback2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaQueueCallback");
                callback2 = null;
            }
            mediaQueue.unregisterCallback(callback2);
        }
        RemoteMediaClient remoteMediaClient = this.remoteMediaClient;
        if (remoteMediaClient == null) {
            return;
        }
        RemoteMediaClient.Callback callback3 = this.remoteCallback;
        if (callback3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("remoteCallback");
        } else {
            callback = callback3;
        }
        remoteMediaClient.unregisterCallback(callback);
    }

    @Override // com.google.android.gms.cast.framework.media.widget.ControlButtonsContainer
    public UIMediaController getUIMediaController() {
        UIMediaController uIMediaController = this.uiMediaController;
        if (uIMediaController == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uiMediaController");
            return null;
        }
        return uIMediaController;
    }

    @Override // com.google.android.gms.cast.framework.media.widget.ControlButtonsContainer
    public ImageView getButtonImageViewAt(int i) throws IndexOutOfBoundsException {
        ImageView imageView = this.imageViews[i];
        Intrinsics.checkNotNull(imageView);
        return imageView;
    }
}
