package com.movieboxpro.android.view.activity.vlcvideoplayer;

import android.view.View;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.CommBaseAdapter;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory;
import com.movieboxpro.android.view.dialog.BaseCenterDialogFragment;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.RendererItem;
/* compiled from: RendererDialog.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\tH\u0016J\b\u0010\r\u001a\u00020\tH\u0016J\u000e\u0010\u000e\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/movieboxpro/android/view/activity/vlcvideoplayer/RendererDialog;", "Lcom/movieboxpro/android/view/dialog/BaseCenterDialogFragment;", "()V", "adapter", "Lcom/movieboxpro/android/base/CommBaseAdapter;", "Lorg/videolan/libvlc/RendererItem;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/activity/vlcvideoplayer/RendererDialog$OnRendererSelectListener;", "initData", "", "initLayoutId", "", "initListener", "initView", "setListener", "OnRendererSelectListener", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RendererDialog extends BaseCenterDialogFragment {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private CommBaseAdapter<RendererItem> adapter;
    private OnRendererSelectListener listener;

    /* compiled from: RendererDialog.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/movieboxpro/android/view/activity/vlcvideoplayer/RendererDialog$OnRendererSelectListener;", "", "onRendererSelect", "", "rendererItem", "Lorg/videolan/libvlc/RendererItem;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface OnRendererSelectListener {
        void onRendererSelect(RendererItem rendererItem);
    }

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
    public int initLayoutId() {
        return R.layout.dialog_renderer;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment
    public void initListener() {
        CommBaseAdapter<RendererItem> commBaseAdapter = this.adapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseAdapter = null;
        }
        CommonExtKt.itemClick(commBaseAdapter, new RendererDialog$initListener$1(this));
        ((TextView) _$_findCachedViewById(R.id.tvDisconnect)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.vlcvideoplayer.-$$Lambda$RendererDialog$ywt8fA5j2VAJZHQab6u-ooYOess
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RendererDialog.m897initListener$lambda0(RendererDialog.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m897initListener$lambda0(RendererDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        VideoActivityFactory.rendererLiveData.setValue((RendererItem) null);
        OnRendererSelectListener onRendererSelectListener = this$0.listener;
        if (onRendererSelectListener != null) {
            onRendererSelectListener.onRendererSelect(null);
        }
        this$0.dismissAllowingStateLoss();
    }

    public final void setListener(OnRendererSelectListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment
    public void initData() {
        this.adapter = new CommBaseAdapter<>(R.layout.adapter_renderer_item, new RendererDialog$initData$1(this), RendererDelegate.INSTANCE.getRenderers().getValue());
        ((RecyclerView) _$_findCachedViewById(R.id.recyclerView)).setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.recyclerView);
        Intrinsics.checkNotNullExpressionValue(recyclerView, "recyclerView");
        CommonExtKt.disableRefreshAnimation(recyclerView);
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.recyclerView);
        CommBaseAdapter<RendererItem> commBaseAdapter = this.adapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseAdapter = null;
        }
        recyclerView2.setAdapter(commBaseAdapter);
        RendererDelegate.INSTANCE.getRenderers().observe(this, new Observer() { // from class: com.movieboxpro.android.view.activity.vlcvideoplayer.-$$Lambda$RendererDialog$UcZs8FVf-0hf_FeS8ifjnD8vv20
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                RendererDialog.m896initData$lambda1(RendererDialog.this, (List) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-1  reason: not valid java name */
    public static final void m896initData$lambda1(RendererDialog this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CommBaseAdapter<RendererItem> commBaseAdapter = this$0.adapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseAdapter = null;
        }
        commBaseAdapter.setList(list);
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment
    public void initView() {
        if (VideoActivityFactory.rendererLiveData != null) {
            ((TextView) _$_findCachedViewById(R.id.tvDisconnect)).setEnabled(VideoActivityFactory.rendererLiveData.getValue() != null);
        }
    }
}
