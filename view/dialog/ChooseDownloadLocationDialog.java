package com.movieboxpro.android.view.dialog;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.CommBaseAdapter;
import com.movieboxpro.android.model.DownloadInfo;
import com.movieboxpro.android.model.DownloadLocation;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.MemoryUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ChooseDownloadLocationDialog.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0002J\b\u0010\f\u001a\u00020\u000bH\u0016J\b\u0010\r\u001a\u00020\u0007H\u0016J\b\u0010\u000e\u001a\u00020\u000bH\u0016J\b\u0010\u000f\u001a\u00020\u000bH\u0016J\b\u0010\u0010\u001a\u00020\u000bH\u0016J\u000e\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\tR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChooseDownloadLocationDialog;", "Lcom/movieboxpro/android/view/dialog/BaseCenterDialogFragment;", "()V", "adapter", "Lcom/movieboxpro/android/base/CommBaseAdapter;", "Lcom/movieboxpro/android/model/DownloadLocation;", "lastPos", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/ChooseDownloadLocationDialog$ChooseDownloadLocationListener;", "getLocations", "", "initData", "initLayoutId", "initListener", "initView", "onResume", "setListener", "ChooseDownloadLocationListener", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ChooseDownloadLocationDialog extends BaseCenterDialogFragment {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private CommBaseAdapter<DownloadLocation> adapter;
    private int lastPos;
    private ChooseDownloadLocationListener listener;

    /* compiled from: ChooseDownloadLocationDialog.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChooseDownloadLocationDialog$ChooseDownloadLocationListener;", "", "onChooseDownload", "", "downloadLocation", "Lcom/movieboxpro/android/model/DownloadLocation;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface ChooseDownloadLocationListener {
        void onChooseDownload(DownloadLocation downloadLocation);
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
        return R.layout.dialog_choose_download_location;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment
    public void initView() {
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final void setListener(ChooseDownloadLocationListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment
    public void initListener() {
        CommBaseAdapter<DownloadLocation> commBaseAdapter = this.adapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseAdapter = null;
        }
        commBaseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ChooseDownloadLocationDialog$VMApAjNZPsLzGozW044Zc_n9fUc
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ChooseDownloadLocationDialog.m949initListener$lambda0(ChooseDownloadLocationDialog.this, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m949initListener$lambda0(ChooseDownloadLocationDialog this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        CommBaseAdapter<DownloadLocation> commBaseAdapter = this$0.adapter;
        CommBaseAdapter<DownloadLocation> commBaseAdapter2 = null;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseAdapter = null;
        }
        DownloadLocation item = commBaseAdapter.getItem(i);
        item.setSelect(!item.isSelect());
        CommBaseAdapter<DownloadLocation> commBaseAdapter3 = this$0.adapter;
        if (commBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseAdapter3 = null;
        }
        DownloadLocation itemOrNull = commBaseAdapter3.getItemOrNull(this$0.lastPos);
        if (itemOrNull != null) {
            itemOrNull.setSelect(false);
        }
        CommBaseAdapter<DownloadLocation> commBaseAdapter4 = this$0.adapter;
        if (commBaseAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseAdapter4 = null;
        }
        commBaseAdapter4.notifyItemChanged(this$0.lastPos);
        CommBaseAdapter<DownloadLocation> commBaseAdapter5 = this$0.adapter;
        if (commBaseAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            commBaseAdapter2 = commBaseAdapter5;
        }
        commBaseAdapter2.notifyItemChanged(i);
        this$0.lastPos = i;
        if (Intrinsics.areEqual(item.getName(), "Internal Storage")) {
            PrefsUtils.getInstance().putBoolean(Constant.Prefs.INTERNAL_STORAGE, true);
            Constant.DIR_DOWNLOAD = item.getFile().getPath();
            PrefsUtils.getInstance().putString(Constant.Prefs.DOWNLOAD_DIR, item.getFile().getPath());
        } else {
            PrefsUtils.getInstance().putBoolean(Constant.Prefs.INTERNAL_STORAGE, false);
            item.getFile().mkdirs();
            FileUtils.createOrExistsDir(item.getFile());
            Constant.DIR_DOWNLOAD = item.getFile().getPath();
            PrefsUtils.getInstance().putString(Constant.Prefs.DOWNLOAD_DIR, item.getFile().getPath());
        }
        ChooseDownloadLocationListener chooseDownloadLocationListener = this$0.listener;
        if (chooseDownloadLocationListener != null) {
            chooseDownloadLocationListener.onChooseDownload(item);
        }
        this$0.dismiss();
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment
    public void initData() {
        this.adapter = new CommBaseAdapter<>(R.layout.adapter_choose_location, new ChooseDownloadLocationDialog$initData$1(this), null, 4, null);
        ((RecyclerView) _$_findCachedViewById(R.id.recyclerView)).setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.recyclerView);
        Intrinsics.checkNotNullExpressionValue(recyclerView, "recyclerView");
        CommonExtKt.disableRefreshAnimation(recyclerView);
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.recyclerView);
        CommBaseAdapter<DownloadLocation> commBaseAdapter = this.adapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseAdapter = null;
        }
        recyclerView2.setAdapter(commBaseAdapter);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        getLocations();
    }

    private final void getLocations() {
        ArrayList arrayList = new ArrayList();
        DownloadLocation downloadLocation = new DownloadLocation();
        downloadLocation.setName("Internal Storage");
        downloadLocation.setTotalSize(MemoryUtils.getInternalToatalSize(getContext()));
        downloadLocation.setFreeSize(MemoryUtils.getAvailSpaceSize(getContext()));
        File externalFilesDir = App.getContext().getExternalFilesDir("");
        StringBuilder sb = new StringBuilder();
        CommBaseAdapter<DownloadLocation> commBaseAdapter = null;
        sb.append((Object) (externalFilesDir == null ? null : externalFilesDir.getParent()));
        sb.append((Object) File.separator);
        sb.append(DownloadInfo.DOWNLOAD);
        downloadLocation.setFile(new File(sb.toString()));
        arrayList.add(downloadLocation);
        File[] listFiles = new File("/storage").listFiles();
        if (listFiles != null) {
            int length = listFiles.length;
            int i = 0;
            while (i < length) {
                File file = listFiles[i];
                i++;
                if (!Intrinsics.areEqual(file.getName(), "self") && !Intrinsics.areEqual(file.getName(), "emulated") && !Intrinsics.areEqual(file.getName(), "knox-emulated") && !Intrinsics.areEqual(file.getName(), "sdcard0") && !Intrinsics.areEqual(file.getName(), TtmlNode.RUBY_CONTAINER)) {
                    DownloadLocation downloadLocation2 = new DownloadLocation();
                    downloadLocation2.setName("SDCARD");
                    downloadLocation2.setTotalSize(FileUtils.getFsTotalSize(file.getPath()));
                    downloadLocation2.setFreeSize(FileUtils.getFsAvailableSize(file.getPath()));
                    downloadLocation2.setFile(new File(file, getString(R.string.app_name)));
                    arrayList.add(downloadLocation2);
                    if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.INTERNAL_STORAGE, true)) {
                        this.lastPos = 0;
                        downloadLocation.setSelect(true);
                    } else {
                        this.lastPos = 1;
                        downloadLocation.setSelect(false);
                        downloadLocation2.setSelect(true);
                    }
                }
            }
        }
        CommBaseAdapter<DownloadLocation> commBaseAdapter2 = this.adapter;
        if (commBaseAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            commBaseAdapter = commBaseAdapter2;
        }
        commBaseAdapter.setList(arrayList);
    }
}
