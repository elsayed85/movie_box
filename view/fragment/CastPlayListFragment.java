package com.movieboxpro.android.view.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.module.BaseDraggableModule;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.media.MediaQueue;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.MyMediaQueueAdapter;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.ToastUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.json.JSONObject;
/* compiled from: CastPlayListFragment.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000  2\u00020\u0001:\u0001 B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0012\u0010\u0014\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J&\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010\u001b\u001a\u00020\u0011H\u0016J\u001a\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u00162\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J,\u0010\u001e\u001a\u00020\u00112\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u001f\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/movieboxpro/android/view/fragment/CastPlayListFragment;", "Landroidx/appcompat/app/AppCompatDialogFragment;", "()V", "adapter", "Lcom/movieboxpro/android/adapter/MyMediaQueueAdapter;", "currId", "", "mediaQueue", "Lcom/google/android/gms/cast/framework/media/MediaQueue;", "mediaStatus", "Lcom/google/android/gms/cast/MediaStatus;", "remoteMediaClient", "Lcom/google/android/gms/cast/framework/media/RemoteMediaClient;", "getContextRect", "activity", "Landroid/app/Activity;", "onActivityCreated", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreate", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "onDestroy", "onViewCreated", "view", "setMediaQueue", "id", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CastPlayListFragment extends AppCompatDialogFragment {
    public static final Companion Companion = new Companion(null);
    private MyMediaQueueAdapter adapter;
    private MediaQueue mediaQueue;
    private MediaStatus mediaStatus;
    private RemoteMediaClient remoteMediaClient;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
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

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    /* compiled from: CastPlayListFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/view/fragment/CastPlayListFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/fragment/CastPlayListFragment;", "data", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final CastPlayListFragment newInstance(String data) {
            Intrinsics.checkNotNullParameter(data, "data");
            CastPlayListFragment castPlayListFragment = new CastPlayListFragment();
            Bundle bundle = new Bundle();
            bundle.putString("data", data);
            castPlayListFragment.setArguments(bundle);
            return castPlayListFragment;
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(0, R.style.BottomSheetFullScreenDialog);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    public final void setMediaQueue(MediaQueue mediaQueue, MediaStatus mediaStatus, int i, RemoteMediaClient remoteMediaClient) {
        this.mediaQueue = mediaQueue;
        this.mediaStatus = mediaStatus;
        this.currId = i;
        this.remoteMediaClient = remoteMediaClient;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        if (getActivity() != null) {
            Dialog dialog = getDialog();
            Window window = dialog == null ? null : dialog.getWindow();
            if (window != null) {
                window.requestFeature(1);
                window.getDecorView().setPadding(0, 0, 0, 0);
                WindowManager.LayoutParams attributes = window.getAttributes();
                Context context = getContext();
                Intrinsics.checkNotNull(context);
                window.setBackgroundDrawable(ContextCompat.getDrawable(context, 17170445));
                FragmentActivity activity = getActivity();
                Intrinsics.checkNotNull(activity);
                Intrinsics.checkNotNullExpressionValue(activity, "activity!!");
                int contextRect = getContextRect(activity);
                if (contextRect == 0) {
                    contextRect = -1;
                }
                window.setLayout(-1, contextRect);
                attributes.dimAmount = 0.0f;
                attributes.gravity = 80;
                window.setAttributes(attributes);
            }
        }
        return inflater.inflate(R.layout.cast_play_list_layout, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        ((ImageView) _$_findCachedViewById(R.id.ivClose)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$CastPlayListFragment$GvIKEhCuSWzg8GPisfJ2bJC8KlY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CastPlayListFragment.m1146onViewCreated$lambda0(CastPlayListFragment.this, view2);
            }
        });
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.recyclerView);
        Intrinsics.checkNotNullExpressionValue(recyclerView, "recyclerView");
        CommonExtKt.disableRefreshAnimation(recyclerView);
        MyMediaQueueAdapter myMediaQueueAdapter = new MyMediaQueueAdapter(getContext(), this.mediaQueue, this.currId);
        this.adapter = myMediaQueueAdapter;
        MyMediaQueueAdapter myMediaQueueAdapter2 = null;
        if (myMediaQueueAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            myMediaQueueAdapter = null;
        }
        myMediaQueueAdapter.addChildClickViewIds(R.id.tvDelete, R.id.container);
        MyMediaQueueAdapter myMediaQueueAdapter3 = this.adapter;
        if (myMediaQueueAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            myMediaQueueAdapter3 = null;
        }
        BaseDraggableModule draggableModule = myMediaQueueAdapter3.getDraggableModule();
        Intrinsics.checkNotNull(draggableModule);
        draggableModule.setDragEnabled(true);
        MyMediaQueueAdapter myMediaQueueAdapter4 = this.adapter;
        if (myMediaQueueAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            myMediaQueueAdapter4 = null;
        }
        BaseDraggableModule draggableModule2 = myMediaQueueAdapter4.getDraggableModule();
        Intrinsics.checkNotNull(draggableModule2);
        draggableModule2.setSwipeEnabled(false);
        ((RecyclerView) _$_findCachedViewById(R.id.recyclerView)).setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.recyclerView);
        MyMediaQueueAdapter myMediaQueueAdapter5 = this.adapter;
        if (myMediaQueueAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            myMediaQueueAdapter5 = null;
        }
        recyclerView2.setAdapter(myMediaQueueAdapter5);
        Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = -1;
        Ref.IntRef intRef2 = new Ref.IntRef();
        MyMediaQueueAdapter myMediaQueueAdapter6 = this.adapter;
        if (myMediaQueueAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            myMediaQueueAdapter6 = null;
        }
        BaseDraggableModule draggableModule3 = myMediaQueueAdapter6.getDraggableModule();
        Intrinsics.checkNotNull(draggableModule3);
        draggableModule3.setOnItemDragListener(new CastPlayListFragment$onViewCreated$2(intRef, this, intRef2));
        MyMediaQueueAdapter myMediaQueueAdapter7 = this.adapter;
        if (myMediaQueueAdapter7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            myMediaQueueAdapter2 = myMediaQueueAdapter7;
        }
        myMediaQueueAdapter2.setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$CastPlayListFragment$bAPVCB4js9nNRody1kJDD4V1clc
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view2, int i) {
                CastPlayListFragment.m1147onViewCreated$lambda4(CastPlayListFragment.this, baseQuickAdapter, view2, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onViewCreated$lambda-0  reason: not valid java name */
    public static final void m1146onViewCreated$lambda0(CastPlayListFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onViewCreated$lambda-4  reason: not valid java name */
    public static final void m1147onViewCreated$lambda4(final CastPlayListFragment this$0, BaseQuickAdapter adapter, View currView, final int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(currView, "currView");
        int id = currView.getId();
        MyMediaQueueAdapter myMediaQueueAdapter = null;
        PendingResult<RemoteMediaClient.MediaChannelResult> pendingResult = null;
        if (id == R.id.container) {
            MyMediaQueueAdapter myMediaQueueAdapter2 = this$0.adapter;
            if (myMediaQueueAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                myMediaQueueAdapter2 = null;
            }
            final MediaQueueItem item = myMediaQueueAdapter2.getItem(i);
            if (item == null) {
                return;
            }
            RemoteMediaClient remoteMediaClient = this$0.remoteMediaClient;
            PendingResult<RemoteMediaClient.MediaChannelResult> queueJumpToItem = remoteMediaClient != null ? remoteMediaClient.queueJumpToItem(item.getItemId(), new JSONObject()) : null;
            if (queueJumpToItem == null) {
                return;
            }
            queueJumpToItem.setResultCallback(new ResultCallback() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$CastPlayListFragment$PviNiadZ6KoXbfkac7LfSUoiHU0
                @Override // com.google.android.gms.common.api.ResultCallback
                public final void onResult(Result result) {
                    CastPlayListFragment.m1149onViewCreated$lambda4$lambda3$lambda2(CastPlayListFragment.this, item, i, (RemoteMediaClient.MediaChannelResult) result);
                }
            });
        } else if (id != R.id.tvDelete) {
        } else {
            RemoteMediaClient remoteMediaClient2 = this$0.remoteMediaClient;
            if (remoteMediaClient2 != null) {
                MyMediaQueueAdapter myMediaQueueAdapter3 = this$0.adapter;
                if (myMediaQueueAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                } else {
                    myMediaQueueAdapter = myMediaQueueAdapter3;
                }
                MediaQueueItem item2 = myMediaQueueAdapter.getItem(i);
                pendingResult = remoteMediaClient2.queueRemoveItem(item2 == null ? -1 : item2.getItemId(), new JSONObject());
            }
            if (pendingResult == null) {
                return;
            }
            pendingResult.setResultCallback(new ResultCallback() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$CastPlayListFragment$xO7GOl8ISpAeM0cptHCsL0dP9-8
                @Override // com.google.android.gms.common.api.ResultCallback
                public final void onResult(Result result) {
                    CastPlayListFragment.m1148onViewCreated$lambda4$lambda1(CastPlayListFragment.this, i, (RemoteMediaClient.MediaChannelResult) result);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onViewCreated$lambda-4$lambda-1  reason: not valid java name */
    public static final void m1148onViewCreated$lambda4$lambda1(CastPlayListFragment this$0, int i, RemoteMediaClient.MediaChannelResult it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        if (it.getStatus().isSuccess()) {
            MyMediaQueueAdapter myMediaQueueAdapter = this$0.adapter;
            if (myMediaQueueAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                myMediaQueueAdapter = null;
            }
            myMediaQueueAdapter.remove(i);
            ToastUtils.showShort("Delete successfully", new Object[0]);
            return;
        }
        ToastUtils.showShort("Delete failed", new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onViewCreated$lambda-4$lambda-3$lambda-2  reason: not valid java name */
    public static final void m1149onViewCreated$lambda4$lambda3$lambda2(CastPlayListFragment this$0, MediaQueueItem item, int i, RemoteMediaClient.MediaChannelResult it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(item, "$item");
        Intrinsics.checkNotNullParameter(it, "it");
        if (it.getStatus().isSuccess()) {
            MyMediaQueueAdapter myMediaQueueAdapter = this$0.adapter;
            if (myMediaQueueAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                myMediaQueueAdapter = null;
            }
            myMediaQueueAdapter.setNewPlayId(item.getItemId(), i);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        MyMediaQueueAdapter myMediaQueueAdapter = this.adapter;
        if (myMediaQueueAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            myMediaQueueAdapter = null;
        }
        myMediaQueueAdapter.unRegisterCallback();
    }

    private final int getContextRect(Activity activity) {
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.height();
    }
}
