package com.movieboxpro.android.view.fragment;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.PendingResult;
import com.movieboxpro.android.adapter.MyMediaQueueAdapter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.json.JSONObject;
/* compiled from: CastPlayListFragment.kt */
@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J,\u0010\b\u001a\u00020\u00032\b\u0010\t\u001a\u0004\u0018\u00010\u00052\u0006\u0010\n\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\f\u001a\u00020\u0007H\u0016J\u001a\u0010\r\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\u000e"}, d2 = {"com/movieboxpro/android/view/fragment/CastPlayListFragment$onViewCreated$2", "Lcom/chad/library/adapter/base/listener/OnItemDragListener;", "onItemDragEnd", "", "viewHolder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "pos", "", "onItemDragMoving", "source", "from", TypedValues.AttributesType.S_TARGET, TypedValues.TransitionType.S_TO, "onItemDragStart", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CastPlayListFragment$onViewCreated$2 implements OnItemDragListener {
    final /* synthetic */ Ref.IntRef $moveId;
    final /* synthetic */ Ref.IntRef $startPos;
    final /* synthetic */ CastPlayListFragment this$0;

    @Override // com.chad.library.adapter.base.listener.OnItemDragListener
    public void onItemDragMoving(RecyclerView.ViewHolder viewHolder, int i, RecyclerView.ViewHolder viewHolder2, int i2) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CastPlayListFragment$onViewCreated$2(Ref.IntRef intRef, CastPlayListFragment castPlayListFragment, Ref.IntRef intRef2) {
        this.$moveId = intRef;
        this.this$0 = castPlayListFragment;
        this.$startPos = intRef2;
    }

    @Override // com.chad.library.adapter.base.listener.OnItemDragListener
    public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int i) {
        MyMediaQueueAdapter myMediaQueueAdapter;
        Ref.IntRef intRef = this.$moveId;
        myMediaQueueAdapter = this.this$0.adapter;
        if (myMediaQueueAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            myMediaQueueAdapter = null;
        }
        MediaQueueItem itemOrNull = myMediaQueueAdapter.getItemOrNull(i);
        intRef.element = itemOrNull == null ? -1 : itemOrNull.getItemId();
        this.$startPos.element = i;
    }

    @Override // com.chad.library.adapter.base.listener.OnItemDragListener
    public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int i) {
        RemoteMediaClient remoteMediaClient;
        if (this.$startPos.element != i) {
            remoteMediaClient = this.this$0.remoteMediaClient;
            PendingResult<RemoteMediaClient.MediaChannelResult> queueMoveItemToNewIndex = remoteMediaClient == null ? null : remoteMediaClient.queueMoveItemToNewIndex(this.$moveId.element, i, new JSONObject());
            if (queueMoveItemToNewIndex == null) {
                return;
            }
            queueMoveItemToNewIndex.setResultCallback($$Lambda$CastPlayListFragment$onViewCreated$2$ZxnchtKCq2AdUu9dRIrW1BcdOg.INSTANCE);
        }
    }
}
