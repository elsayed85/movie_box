package com.movieboxpro.android.view.activity.choose.impl;

import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.movieboxpro.android.app.AppManager;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.EventUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.movie.MovieChooseActivity;
import com.movieboxpro.android.view.dialog.PlayAddToCastDialog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;
/* compiled from: ChooseActivity.kt */
@Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016¨\u0006\u0005"}, d2 = {"com/movieboxpro/android/view/activity/choose/impl/ChooseActivity$getSrt$dialog$1", "Lcom/movieboxpro/android/view/dialog/PlayAddToCastDialog$OnPlayAddListener;", "onAdd", "", "onPlay", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ChooseActivity$getSrt$dialog$1 implements PlayAddToCastDialog.OnPlayAddListener {
    final /* synthetic */ MediaQueueItem $queueItem;
    final /* synthetic */ RemoteMediaClient $remoteMediaClient;
    final /* synthetic */ ChooseActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChooseActivity$getSrt$dialog$1(RemoteMediaClient remoteMediaClient, MediaQueueItem mediaQueueItem, ChooseActivity chooseActivity) {
        this.$remoteMediaClient = remoteMediaClient;
        this.$queueItem = mediaQueueItem;
        this.this$0 = chooseActivity;
    }

    @Override // com.movieboxpro.android.view.dialog.PlayAddToCastDialog.OnPlayAddListener
    public void onPlay() {
        if (this.$remoteMediaClient.getMediaQueue() != null && this.$remoteMediaClient.getMediaQueue().getItemCount() > 0) {
            int i = -1;
            if (this.$remoteMediaClient.getCurrentItem() != null) {
                MediaQueueItem currentItem = this.$remoteMediaClient.getCurrentItem();
                i = currentItem != null ? currentItem.getItemId() : 0;
            }
            PendingResult<RemoteMediaClient.MediaChannelResult> queueInsertAndPlayItem = this.$remoteMediaClient.queueInsertAndPlayItem(this.$queueItem, i, new JSONObject());
            Intrinsics.checkNotNullExpressionValue(queueInsertAndPlayItem, "remoteMediaClient.queueI…, insertId, JSONObject())");
            final ChooseActivity chooseActivity = this.this$0;
            queueInsertAndPlayItem.setResultCallback(new ResultCallback() { // from class: com.movieboxpro.android.view.activity.choose.impl.-$$Lambda$ChooseActivity$getSrt$dialog$1$vMN3pRwmlOgB1yrks7RpUD62aJ0
                @Override // com.google.android.gms.common.api.ResultCallback
                public final void onResult(Result result) {
                    ChooseActivity$getSrt$dialog$1.m337onPlay$lambda0(ChooseActivity.this, (RemoteMediaClient.MediaChannelResult) result);
                }
            });
            AppManager.finish(MovieChooseActivity.class);
            return;
        }
        CommonExtKt.onMobEvent(this.this$0, "StartChromeCast");
        EventUtils.event("使用ChromeCast");
        this.$remoteMediaClient.queueLoad(new MediaQueueItem[]{this.$queueItem}, 0, 0, new JSONObject());
        this.this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onPlay$lambda-0  reason: not valid java name */
    public static final void m337onPlay$lambda0(ChooseActivity this$0, RemoteMediaClient.MediaChannelResult mediaChannelResult) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mediaChannelResult, "mediaChannelResult");
        if (mediaChannelResult.getStatus().isSuccess()) {
            this$0.finish();
        } else {
            ToastUtils.showShort("Play failed", new Object[0]);
        }
    }

    @Override // com.movieboxpro.android.view.dialog.PlayAddToCastDialog.OnPlayAddListener
    public void onAdd() {
        if (this.$remoteMediaClient.getMediaQueue() != null && this.$remoteMediaClient.getMediaQueue().getItemCount() > 0) {
            PendingResult<RemoteMediaClient.MediaChannelResult> queueAppendItem = this.$remoteMediaClient.queueAppendItem(this.$queueItem, new JSONObject());
            Intrinsics.checkNotNullExpressionValue(queueAppendItem, "remoteMediaClient.queueA…(queueItem, JSONObject())");
            final ChooseActivity chooseActivity = this.this$0;
            queueAppendItem.setResultCallback(new ResultCallback() { // from class: com.movieboxpro.android.view.activity.choose.impl.-$$Lambda$ChooseActivity$getSrt$dialog$1$x358ikoIx25qQvCH1sRebVem-QU
                @Override // com.google.android.gms.common.api.ResultCallback
                public final void onResult(Result result) {
                    ChooseActivity$getSrt$dialog$1.m336onAdd$lambda1(ChooseActivity.this, (RemoteMediaClient.MediaChannelResult) result);
                }
            });
            AppManager.finish(MovieChooseActivity.class);
            return;
        }
        CommonExtKt.onMobEvent(this.this$0, "StartChromeCast");
        EventUtils.event("使用ChromeCast");
        this.$remoteMediaClient.queueLoad(new MediaQueueItem[]{this.$queueItem}, 0, 0, new JSONObject());
        this.this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onAdd$lambda-1  reason: not valid java name */
    public static final void m336onAdd$lambda1(ChooseActivity this$0, RemoteMediaClient.MediaChannelResult mediaChannelResult) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mediaChannelResult, "mediaChannelResult");
        if (mediaChannelResult.getStatus().isSuccess()) {
            ToastUtils.showShort("Add to queue successfully", new Object[0]);
            this$0.finish();
            return;
        }
        ToastUtils.showShort("Play failed", new Object[0]);
    }
}
