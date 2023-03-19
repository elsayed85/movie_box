package com.movieboxpro.android.view.activity.videoplayer;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import com.avery.subtitle.model.Subtitle;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.BaseFullScreenDialogFragment;
import com.movieboxpro.android.utils.CommonExtKt;
import com.weigan.loopview.LoopView;
import com.weigan.loopview.OnItemSelectedListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: LastSaidFragment.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u0000 \u00122\u00020\u0001:\u0002\u0012\u0013B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u0006H\u0014J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\fH\u0016J\b\u0010\u000e\u001a\u00020\fH\u0016J\u000e\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\u0004J\u0014\u0010\u0010\u001a\u00020\f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\bR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/movieboxpro/android/view/activity/videoplayer/LastSaidFragment;", "Lcom/movieboxpro/android/base/BaseFullScreenDialogFragment;", "()V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/activity/videoplayer/LastSaidFragment$OnConfirmListener;", "selectPos", "", "subtitles", "", "Lcom/avery/subtitle/model/Subtitle;", "bindLayout", "initData", "", "initListener", "initView", "setConfirmLastSaidListener", "setSubtitleData", "list", "Companion", "OnConfirmListener", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class LastSaidFragment extends BaseFullScreenDialogFragment {
    public static final Companion Companion = new Companion(null);
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private OnConfirmListener listener;
    private int selectPos;
    private List<Subtitle> subtitles;

    /* compiled from: LastSaidFragment.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/view/activity/videoplayer/LastSaidFragment$OnConfirmListener;", "", "onConfirmLastSaid", "", "position", "", MediaTrack.ROLE_SUBTITLE, "Lcom/avery/subtitle/model/Subtitle;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface OnConfirmListener {
        void onConfirmLastSaid(int i, Subtitle subtitle);
    }

    @Override // com.movieboxpro.android.base.BaseFullScreenDialogFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.movieboxpro.android.base.BaseFullScreenDialogFragment
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

    @Override // com.movieboxpro.android.base.BaseFullScreenDialogFragment
    protected int bindLayout() {
        return R.layout.fragment_last_said;
    }

    @Override // com.movieboxpro.android.base.BaseFullScreenDialogFragment
    public void initView() {
    }

    @Override // com.movieboxpro.android.base.BaseFullScreenDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.movieboxpro.android.base.BaseFullScreenDialogFragment
    public void initData() {
        LoopView loopView = (LoopView) _$_findCachedViewById(R.id.loopView);
        Context context = getContext();
        loopView.setDividerColor(context == null ? -1 : CommonExtKt.colorInt(context, (int) R.color.color_main_blue));
        ((LoopView) _$_findCachedViewById(R.id.loopView)).setTextSize(14.0f);
        LoopView loopView2 = (LoopView) _$_findCachedViewById(R.id.loopView);
        Context context2 = getContext();
        loopView2.setCenterTextColor(context2 != null ? CommonExtKt.colorInt(context2, (int) R.color.white) : -1);
        Bundle arguments = getArguments();
        boolean z = arguments == null ? false : arguments.getBoolean("isLand", false);
        Bundle arguments2 = getArguments();
        this.selectPos = arguments2 != null ? arguments2.getInt("position", 0) : 0;
        if (z) {
            ((LoopView) _$_findCachedViewById(R.id.loopView)).setItemsVisibleCount(13);
        } else {
            ((LoopView) _$_findCachedViewById(R.id.loopView)).setItemsVisibleCount(27);
        }
        ArrayList arrayList = new ArrayList();
        List<Subtitle> list = this.subtitles;
        if (list != null) {
            for (Subtitle subtitle : list) {
                arrayList.add(Html.fromHtml(subtitle.content).toString());
            }
        }
        ((LoopView) _$_findCachedViewById(R.id.loopView)).setItems(arrayList);
        ((LoopView) _$_findCachedViewById(R.id.loopView)).setInitPosition(this.selectPos);
    }

    public final void setSubtitleData(List<Subtitle> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.subtitles = list;
    }

    public final void setConfirmLastSaidListener(OnConfirmListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    @Override // com.movieboxpro.android.base.BaseFullScreenDialogFragment
    public void initListener() {
        ((LoopView) _$_findCachedViewById(R.id.loopView)).setListener(new OnItemSelectedListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.-$$Lambda$LastSaidFragment$qFjC80FZAym0OqFoAPX5d40ZRoA
            @Override // com.weigan.loopview.OnItemSelectedListener
            public final void onItemSelected(int i) {
                LastSaidFragment.m871initListener$lambda1(LastSaidFragment.this, i);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btnCancel)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.-$$Lambda$LastSaidFragment$aLnXj5QjTfCqaefs9k1883VeQFs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LastSaidFragment.m872initListener$lambda2(LastSaidFragment.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btnOk)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.-$$Lambda$LastSaidFragment$4Bh2qEtGY6M_FCLdKSUqydsg8l0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LastSaidFragment.m873initListener$lambda3(LastSaidFragment.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m871initListener$lambda1(LastSaidFragment this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.selectPos = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m872initListener$lambda2(LastSaidFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-3  reason: not valid java name */
    public static final void m873initListener$lambda3(LastSaidFragment this$0, View view) {
        OnConfirmListener onConfirmListener;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (CommonExtKt.checkIndexLegal(this$0.selectPos, this$0.subtitles) && (onConfirmListener = this$0.listener) != null) {
            int i = this$0.selectPos;
            List<Subtitle> list = this$0.subtitles;
            onConfirmListener.onConfirmLastSaid(i, list == null ? null : list.get(i));
        }
        this$0.dismiss();
    }

    /* compiled from: LastSaidFragment.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/view/activity/videoplayer/LastSaidFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/activity/videoplayer/LastSaidFragment;", "position", "", "isLand", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final LastSaidFragment newInstance(int i, boolean z) {
            Bundle bundleOf = CommonExtKt.bundleOf(TuplesKt.to("position", Integer.valueOf(i)), TuplesKt.to("isLand", Boolean.valueOf(z)));
            LastSaidFragment lastSaidFragment = new LastSaidFragment();
            lastSaidFragment.setArguments(bundleOf);
            return lastSaidFragment;
        }
    }
}
