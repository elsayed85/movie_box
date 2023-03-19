package com.movieboxpro.android.view.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.BuildConfig;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseBindingBottomDialogFragment;
import com.movieboxpro.android.base.CommBaseAdapter;
import com.movieboxpro.android.model.DownloadInfo;
import com.movieboxpro.android.model.DownloadPath;
import com.movieboxpro.android.service.FileDownloadService;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.SDCardUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
/* compiled from: DownloadPathDialog.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00182\u00020\u0001:\u0002\u0017\u0018B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\u000bH\u0016J\b\u0010\r\u001a\u00020\u000bH\u0016J$\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u000e\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\tR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/movieboxpro/android/view/dialog/DownloadPathDialog;", "Lcom/movieboxpro/android/base/BaseBindingBottomDialogFragment;", "()V", "adapter", "Lcom/movieboxpro/android/base/CommBaseAdapter;", "Lcom/movieboxpro/android/model/DownloadPath;", "lastPos", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/DownloadPathDialog$ChooseDownloadPathListener;", "initData", "", "initListener", "initView", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "setListener", "ChooseDownloadPathListener", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DownloadPathDialog extends BaseBindingBottomDialogFragment {
    public static final Companion Companion = new Companion(null);
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private CommBaseAdapter<DownloadPath> adapter;
    private int lastPos;
    private ChooseDownloadPathListener listener;

    /* compiled from: DownloadPathDialog.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/view/dialog/DownloadPathDialog$ChooseDownloadPathListener;", "", "onChoose", "", "internal", "", FileDownloadService.PARAMS_KEY_PATH, "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface ChooseDownloadPathListener {
        void onChoose(boolean z, String str);
    }

    @Override // com.movieboxpro.android.base.BaseBindingBottomDialogFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.movieboxpro.android.base.BaseBindingBottomDialogFragment
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

    @Override // com.movieboxpro.android.base.BaseBindingBottomDialogFragment
    public void initView() {
    }

    @Override // com.movieboxpro.android.base.BaseBindingBottomDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final void setListener(ChooseDownloadPathListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View inflate = inflater.inflate(R.layout.dialog_download_path, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflater.inflate(R.layou…oad_path,container,false)");
        return inflate;
    }

    @Override // com.movieboxpro.android.base.BaseBindingBottomDialogFragment
    public void initListener() {
        CommBaseAdapter<DownloadPath> commBaseAdapter = this.adapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseAdapter = null;
        }
        commBaseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$DownloadPathDialog$I8F7nmnMq8OaOgsJU2f6zhuWG_g
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                DownloadPathDialog.m966initListener$lambda0(DownloadPathDialog.this, baseQuickAdapter, view, i);
            }
        });
        View view = getView();
        if (view == null) {
            return;
        }
        ((TextView) view.findViewById(R.id.tvCancel)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$DownloadPathDialog$wlI3LGkJzgPdNf1Svjgqc9-oWuo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                DownloadPathDialog.m967initListener$lambda3$lambda1(DownloadPathDialog.this, view2);
            }
        });
        ((TextView) view.findViewById(R.id.tvChoose)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$DownloadPathDialog$AioVDK9MRAOE4XvipvaLaMpMjmE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                DownloadPathDialog.m968initListener$lambda3$lambda2(DownloadPathDialog.this, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m966initListener$lambda0(DownloadPathDialog this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        if (this$0.lastPos == i) {
            return;
        }
        CommBaseAdapter<DownloadPath> commBaseAdapter = this$0.adapter;
        CommBaseAdapter<DownloadPath> commBaseAdapter2 = null;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseAdapter = null;
        }
        commBaseAdapter.getItem(i).setSelect(true);
        CommBaseAdapter<DownloadPath> commBaseAdapter3 = this$0.adapter;
        if (commBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseAdapter3 = null;
        }
        commBaseAdapter3.getItem(this$0.lastPos).setSelect(false);
        CommBaseAdapter<DownloadPath> commBaseAdapter4 = this$0.adapter;
        if (commBaseAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseAdapter4 = null;
        }
        commBaseAdapter4.notifyItemChanged(i);
        CommBaseAdapter<DownloadPath> commBaseAdapter5 = this$0.adapter;
        if (commBaseAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            commBaseAdapter2 = commBaseAdapter5;
        }
        commBaseAdapter2.notifyItemChanged(this$0.lastPos);
        this$0.lastPos = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-3$lambda-1  reason: not valid java name */
    public static final void m967initListener$lambda3$lambda1(DownloadPathDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-3$lambda-2  reason: not valid java name */
    public static final void m968initListener$lambda3$lambda2(DownloadPathDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ChooseDownloadPathListener chooseDownloadPathListener = this$0.listener;
        if (chooseDownloadPathListener != null) {
            boolean z = this$0.lastPos == 0;
            CommBaseAdapter<DownloadPath> commBaseAdapter = this$0.adapter;
            if (commBaseAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                commBaseAdapter = null;
            }
            String path = commBaseAdapter.getItem(this$0.lastPos).getPath();
            if (path == null) {
                path = "";
            }
            chooseDownloadPathListener.onChoose(z, path);
        }
        this$0.dismiss();
    }

    @Override // com.movieboxpro.android.base.BaseBindingBottomDialogFragment
    public void initData() {
        ArrayList arrayList = new ArrayList();
        String str = Constant.DIR + ((Object) File.separator) + DownloadInfo.DOWNLOAD;
        Bundle arguments = getArguments();
        CommBaseAdapter<DownloadPath> commBaseAdapter = null;
        String string = arguments == null ? null : arguments.getString(FileDownloadService.PARAMS_KEY_PATH);
        arrayList.add(new DownloadPath(str, "Internal Storage", FileUtils.byte2FitMemorySize(FileUtils.getFsAvailableSize(str)), Intrinsics.areEqual(str, string)));
        List<String> paths = SDCardUtils.getMountedSDCardPath();
        Intrinsics.checkNotNullExpressionValue(paths, "paths");
        for (String it : paths) {
            Intrinsics.checkNotNullExpressionValue(it, "it");
            if (!StringsKt.startsWith$default(it, "/storage/emulated/0", false, 2, (Object) null)) {
                String str2 = it + ((Object) File.separator) + "Android/data" + ((Object) File.separator) + BuildConfig.APPLICATION_ID + ((Object) File.separator) + DownloadInfo.DOWNLOAD;
                if (!new File(str2).exists()) {
                    new File(str2).mkdirs();
                }
                arrayList.add(new DownloadPath(str2, "SD Card", FileUtils.byte2FitMemorySize(FileUtils.getFsAvailableSize(it)), Intrinsics.areEqual(string, str2)));
            }
        }
        ArrayList arrayList2 = arrayList;
        Iterator it2 = arrayList2.iterator();
        int i = 0;
        while (true) {
            if (!it2.hasNext()) {
                i = -1;
                break;
            } else if (((DownloadPath) it2.next()).getSelect()) {
                break;
            } else {
                i++;
            }
        }
        this.lastPos = i;
        if (i == -1) {
            this.lastPos = 0;
            ((DownloadPath) arrayList.get(0)).setSelect(true);
        }
        View view = getView();
        if (view == null) {
            return;
        }
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        this.adapter = new CommBaseAdapter<>(R.layout.adapter_download_path, DownloadPathDialog$initData$3$1.INSTANCE, arrayList2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Intrinsics.checkNotNullExpressionValue(recyclerView, "recyclerView");
        CommonExtKt.disableRefreshAnimation(recyclerView);
        CommBaseAdapter<DownloadPath> commBaseAdapter2 = this.adapter;
        if (commBaseAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            commBaseAdapter = commBaseAdapter2;
        }
        recyclerView.setAdapter(commBaseAdapter);
    }

    /* compiled from: DownloadPathDialog.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/view/dialog/DownloadPathDialog$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/DownloadPathDialog;", FileDownloadService.PARAMS_KEY_PATH, "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DownloadPathDialog newInstance(String str) {
            DownloadPathDialog downloadPathDialog = new DownloadPathDialog();
            downloadPathDialog.setArguments(CommonExtKt.bundleOf(TuplesKt.to(FileDownloadService.PARAMS_KEY_PATH, str)));
            return downloadPathDialog;
        }
    }
}
