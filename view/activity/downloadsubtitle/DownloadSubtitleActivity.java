package com.movieboxpro.android.view.activity.downloadsubtitle;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.dueeeke.model.LanguageModel;
import com.dueeeke.model.ResponseSubtitleRecord;
import com.dueeeke.model.SRTModel;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.mvp.BaseMvpActivity;
import com.movieboxpro.android.model.DownloadingSubtitleModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.EventUtils;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.downloadsubtitle.DownloadSubtitleContract;
import com.movieboxpro.android.view.activity.subtitlecheck.SubtitleCheckActivity;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.beanutils.PropertyUtils;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: DownloadSubtitleActivity.kt */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 22\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u00012B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u001b\u001a\u00020\u0002H\u0014J \u0010\u001c\u001a\u00020\u001d2\u0016\u0010\u001e\u001a\u0012\u0012\u0004\u0012\u00020\u00180\u001fj\b\u0012\u0004\u0012\u00020\u0018` H\u0002J\u0016\u0010!\u001a\u00020\u001d2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00060\u0010H\u0016J\b\u0010#\u001a\u00020\rH\u0014J\b\u0010$\u001a\u00020\u001dH\u0002J\b\u0010%\u001a\u00020\u001dH\u0014J\b\u0010&\u001a\u00020\u001dH\u0014J\b\u0010'\u001a\u00020\u001dH\u0014J\"\u0010(\u001a\u00020\u001d2\u0006\u0010)\u001a\u00020\r2\u0006\u0010*\u001a\u00020\r2\b\u0010+\u001a\u0004\u0018\u00010,H\u0014J\b\u0010-\u001a\u00020\u001dH\u0015J\b\u0010.\u001a\u00020\u001dH\u0003J\u0010\u0010/\u001a\u00020\u001d2\u0006\u00100\u001a\u000201H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R6\u0010\u000e\u001a*\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000fj\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u0010`\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\r@CX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u000b0\tX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lcom/movieboxpro/android/view/activity/downloadsubtitle/DownloadSubtitleActivity;", "Lcom/movieboxpro/android/base/mvp/BaseMvpActivity;", "Lcom/movieboxpro/android/view/activity/downloadsubtitle/DownloadSubtitlePresenter;", "Lcom/movieboxpro/android/view/activity/downloadsubtitle/DownloadSubtitleContract$View;", "()V", "currLanguage", "", "downloadDir", "languageAdapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/dueeeke/model/LanguageModel;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "lastPosition", "", "map", "Ljava/util/HashMap;", "", "Lcom/dueeeke/model/SRTModel$SubTitles;", "Lkotlin/collections/HashMap;", "value", "selectedCount", "setSelectedCount", "(I)V", "subtitleAdapter", "Lcom/movieboxpro/android/model/DownloadingSubtitleModel;", IjkMediaMeta.IJKM_KEY_TYPE, "viewPosition", "bindPresenter", "checkSubtitleComplete", "", "subtitles", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "downloadSubtitlesComplete", "sidList", "getLayoutResId", "initAdapter", "initData", "initListener", "initView", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "requestData", "requestPermission", "showSubtitle", "model", "Lcom/dueeeke/model/ResponseSubtitleRecord;", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DownloadSubtitleActivity extends BaseMvpActivity<DownloadSubtitlePresenter> implements DownloadSubtitleContract.View {
    public static final Companion Companion = new Companion(null);
    private BaseQuickAdapter<LanguageModel, BaseViewHolder> languageAdapter;
    private int lastPosition;
    private int selectedCount;
    private BaseQuickAdapter<DownloadingSubtitleModel, BaseViewHolder> subtitleAdapter;
    private int type;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private final HashMap<String, List<SRTModel.SubTitles>> map = new HashMap<>();
    private String downloadDir = "";
    private int viewPosition = -1;
    private String currLanguage = "";

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

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

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected int getLayoutResId() {
        return R.layout.activity_download_subtitle;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initView() {
    }

    private final void setSelectedCount(int i) {
        this.selectedCount = i;
        if (i == 0) {
            ((TextView) _$_findCachedViewById(R.id.tvDownload)).setText("Download");
            ((TextView) _$_findCachedViewById(R.id.tvDownload)).setEnabled(false);
            ((TextView) _$_findCachedViewById(R.id.tvAll)).setText("Select All");
            return;
        }
        BaseQuickAdapter<DownloadingSubtitleModel, BaseViewHolder> baseQuickAdapter = this.subtitleAdapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
            baseQuickAdapter = null;
        }
        if (i == baseQuickAdapter.getData().size()) {
            ((TextView) _$_findCachedViewById(R.id.tvDownload)).setText("Download (" + this.selectedCount + PropertyUtils.MAPPED_DELIM2);
            ((TextView) _$_findCachedViewById(R.id.tvDownload)).setEnabled(true);
            ((TextView) _$_findCachedViewById(R.id.tvAll)).setText("Deselect All");
            return;
        }
        ((TextView) _$_findCachedViewById(R.id.tvDownload)).setText("Download (" + this.selectedCount + PropertyUtils.MAPPED_DELIM2);
        ((TextView) _$_findCachedViewById(R.id.tvDownload)).setEnabled(true);
        ((TextView) _$_findCachedViewById(R.id.tvAll)).setText("Select All");
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initListener() {
        ((ImageView) _$_findCachedViewById(R.id.ivBack)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.downloadsubtitle.-$$Lambda$DownloadSubtitleActivity$bIUvFNvnMHJh8Vu-SgDvKMtIF8E
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DownloadSubtitleActivity.m343initListener$lambda0(DownloadSubtitleActivity.this, view);
            }
        });
        BaseQuickAdapter<LanguageModel, BaseViewHolder> baseQuickAdapter = this.languageAdapter;
        BaseQuickAdapter<DownloadingSubtitleModel, BaseViewHolder> baseQuickAdapter2 = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.downloadsubtitle.-$$Lambda$DownloadSubtitleActivity$TfSB_3uQ1f1AFNeVQv1pgur0MhA
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter3, View view, int i) {
                DownloadSubtitleActivity.m344initListener$lambda2(DownloadSubtitleActivity.this, baseQuickAdapter3, view, i);
            }
        });
        BaseQuickAdapter<DownloadingSubtitleModel, BaseViewHolder> baseQuickAdapter3 = this.subtitleAdapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
            baseQuickAdapter3 = null;
        }
        baseQuickAdapter3.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.downloadsubtitle.-$$Lambda$DownloadSubtitleActivity$VpemwYlvKJF6b7EJwjSsAtZv2Dw
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter4, View view, int i) {
                DownloadSubtitleActivity.m345initListener$lambda3(DownloadSubtitleActivity.this, baseQuickAdapter4, view, i);
            }
        });
        BaseQuickAdapter<DownloadingSubtitleModel, BaseViewHolder> baseQuickAdapter4 = this.subtitleAdapter;
        if (baseQuickAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter4;
        }
        baseQuickAdapter2.setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.activity.downloadsubtitle.-$$Lambda$DownloadSubtitleActivity$mVwo_eSAFNpRYdEX6F5OqDzQaXg
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter5, View view, int i) {
                DownloadSubtitleActivity.m346initListener$lambda4(DownloadSubtitleActivity.this, baseQuickAdapter5, view, i);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tvAll)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.downloadsubtitle.-$$Lambda$DownloadSubtitleActivity$Myl_07hefm5G6Ez5WZRv_4b0n8w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DownloadSubtitleActivity.m347initListener$lambda7(DownloadSubtitleActivity.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tvDownload)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.downloadsubtitle.-$$Lambda$DownloadSubtitleActivity$nO7Gnqvlx8jttFRNBCxoQKv3bX8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DownloadSubtitleActivity.m348initListener$lambda8(DownloadSubtitleActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m343initListener$lambda0(DownloadSubtitleActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m344initListener$lambda2(DownloadSubtitleActivity this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        if (this$0.lastPosition == i) {
            return;
        }
        BaseQuickAdapter<LanguageModel, BaseViewHolder> baseQuickAdapter = this$0.languageAdapter;
        BaseQuickAdapter<DownloadingSubtitleModel, BaseViewHolder> baseQuickAdapter2 = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
            baseQuickAdapter = null;
        }
        LanguageModel itemOrNull = baseQuickAdapter.getItemOrNull(this$0.lastPosition);
        if (itemOrNull != null) {
            itemOrNull.isSelected = false;
        }
        BaseQuickAdapter<LanguageModel, BaseViewHolder> baseQuickAdapter3 = this$0.languageAdapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
            baseQuickAdapter3 = null;
        }
        LanguageModel item = baseQuickAdapter3.getItem(i);
        if (item != null) {
            item.isSelected = true;
        }
        BaseQuickAdapter<LanguageModel, BaseViewHolder> baseQuickAdapter4 = this$0.languageAdapter;
        if (baseQuickAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
            baseQuickAdapter4 = null;
        }
        baseQuickAdapter4.notifyItemChanged(this$0.lastPosition);
        BaseQuickAdapter<LanguageModel, BaseViewHolder> baseQuickAdapter5 = this$0.languageAdapter;
        if (baseQuickAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
            baseQuickAdapter5 = null;
        }
        baseQuickAdapter5.notifyItemChanged(i);
        ArrayList<DownloadingSubtitleModel> arrayList = new ArrayList<>();
        List<SRTModel.SubTitles> list = this$0.map.get(item == null ? null : item.language);
        if (list != null) {
            for (SRTModel.SubTitles subTitles : list) {
                arrayList.add(new DownloadingSubtitleModel(subTitles));
            }
        }
        this$0.checkSubtitleComplete(arrayList);
        BaseQuickAdapter<DownloadingSubtitleModel, BaseViewHolder> baseQuickAdapter6 = this$0.subtitleAdapter;
        if (baseQuickAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter6;
        }
        baseQuickAdapter2.setNewData(arrayList);
        this$0.lastPosition = i;
        this$0.setSelectedCount(0);
        String str = item.language;
        Intrinsics.checkNotNullExpressionValue(str, "currModel.language");
        this$0.currLanguage = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-3  reason: not valid java name */
    public static final void m345initListener$lambda3(DownloadSubtitleActivity this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        BaseQuickAdapter<DownloadingSubtitleModel, BaseViewHolder> baseQuickAdapter = this$0.subtitleAdapter;
        BaseQuickAdapter<DownloadingSubtitleModel, BaseViewHolder> baseQuickAdapter2 = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
            baseQuickAdapter = null;
        }
        DownloadingSubtitleModel item = baseQuickAdapter.getItem(i);
        boolean z = false;
        if (item != null && item.isComplete()) {
            return;
        }
        if (item != null) {
            item.setSelected(!item.isSelected());
        }
        BaseQuickAdapter<DownloadingSubtitleModel, BaseViewHolder> baseQuickAdapter3 = this$0.subtitleAdapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter3;
        }
        baseQuickAdapter2.notifyItemChanged(i);
        if (item != null && item.isSelected()) {
            z = true;
        }
        if (z) {
            this$0.setSelectedCount(this$0.selectedCount + 1);
        } else {
            this$0.setSelectedCount(this$0.selectedCount - 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-4  reason: not valid java name */
    public static final void m346initListener$lambda4(DownloadSubtitleActivity this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        String str;
        String str2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        BaseQuickAdapter<DownloadingSubtitleModel, BaseViewHolder> baseQuickAdapter = this$0.subtitleAdapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
            baseQuickAdapter = null;
        }
        DownloadingSubtitleModel item = baseQuickAdapter.getItem(i);
        SRTModel.SubTitles subTitles = item != null ? item.getSubTitles() : null;
        this$0.viewPosition = i;
        SubtitleCheckActivity.Companion companion = SubtitleCheckActivity.Companion;
        DownloadSubtitleActivity downloadSubtitleActivity = this$0;
        int i2 = subTitles == null ? 0 : subTitles.count;
        if (subTitles == null || (str = subTitles.sid) == null) {
            str = "";
        }
        companion.starter(downloadSubtitleActivity, i2, str, this$0.type, (subTitles == null || (str2 = subTitles.file_path) == null) ? "" : str2, item == null ? false : item.isComplete());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-7  reason: not valid java name */
    public static final void m347initListener$lambda7(DownloadSubtitleActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = this$0.selectedCount;
        BaseQuickAdapter<DownloadingSubtitleModel, BaseViewHolder> baseQuickAdapter = this$0.subtitleAdapter;
        BaseQuickAdapter<DownloadingSubtitleModel, BaseViewHolder> baseQuickAdapter2 = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
            baseQuickAdapter = null;
        }
        if (i == baseQuickAdapter.getData().size()) {
            BaseQuickAdapter<DownloadingSubtitleModel, BaseViewHolder> baseQuickAdapter3 = this$0.subtitleAdapter;
            if (baseQuickAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
                baseQuickAdapter3 = null;
            }
            for (DownloadingSubtitleModel downloadingSubtitleModel : baseQuickAdapter3.getData()) {
                downloadingSubtitleModel.setSelected(false);
                this$0.setSelectedCount(0);
            }
        } else {
            BaseQuickAdapter<DownloadingSubtitleModel, BaseViewHolder> baseQuickAdapter4 = this$0.subtitleAdapter;
            if (baseQuickAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
                baseQuickAdapter4 = null;
            }
            for (DownloadingSubtitleModel downloadingSubtitleModel2 : baseQuickAdapter4.getData()) {
                downloadingSubtitleModel2.setSelected(true);
                BaseQuickAdapter<DownloadingSubtitleModel, BaseViewHolder> baseQuickAdapter5 = this$0.subtitleAdapter;
                if (baseQuickAdapter5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
                    baseQuickAdapter5 = null;
                }
                this$0.setSelectedCount(baseQuickAdapter5.getData().size());
            }
        }
        BaseQuickAdapter<DownloadingSubtitleModel, BaseViewHolder> baseQuickAdapter6 = this$0.subtitleAdapter;
        if (baseQuickAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter6;
        }
        baseQuickAdapter2.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-8  reason: not valid java name */
    public static final void m348initListener$lambda8(DownloadSubtitleActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String stringExtra = this$0.getIntent().getStringExtra("Id");
        String stringExtra2 = this$0.getIntent().getStringExtra("Name");
        int intExtra = this$0.getIntent().getIntExtra("Season", 0);
        int intExtra2 = this$0.getIntent().getIntExtra("Episode", 0);
        if (intExtra == 0 && intExtra2 == 0) {
            this$0.type = 1;
            this$0.downloadDir = Constant.DIR_UPLOAD_MOVIE_SUBTITLE + ((Object) File.separator) + ((Object) stringExtra) + ((Object) File.separator) + ((Object) stringExtra2) + ((Object) File.separator) + this$0.currLanguage;
        } else {
            this$0.downloadDir = Constant.DIR_UPLOAD_TV_SUBTITLE + ((Object) File.separator) + ((Object) stringExtra) + ((Object) File.separator) + ((Object) stringExtra2) + ((Object) File.separator) + "Season " + intExtra + ((Object) File.separator) + "Episode " + intExtra2 + ((Object) File.separator) + this$0.currLanguage;
        }
        this$0.requestPermission();
    }

    private final void requestPermission() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        BaseQuickAdapter<DownloadingSubtitleModel, BaseViewHolder> baseQuickAdapter = this.subtitleAdapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
            baseQuickAdapter = null;
        }
        for (DownloadingSubtitleModel downloadingSubtitleModel : baseQuickAdapter.getData()) {
            if (!downloadingSubtitleModel.isComplete() && downloadingSubtitleModel.isSelected()) {
                arrayList.add(downloadingSubtitleModel.getSubTitles().file_path);
                arrayList2.add(downloadingSubtitleModel.getSubTitles().sid);
            }
        }
        ((DownloadSubtitlePresenter) this.mPresenter).downloadSubtitles(arrayList, arrayList2, this.downloadDir);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initData() {
        CommonExtKt.onMobEvent(this, "EnterSubtitlesDownload");
        EventUtils.event("进入字幕下载界面");
        initAdapter();
    }

    private final void initAdapter() {
        final ArrayList arrayList = new ArrayList();
        this.languageAdapter = new BaseQuickAdapter<LanguageModel, BaseViewHolder>(arrayList) { // from class: com.movieboxpro.android.view.activity.downloadsubtitle.DownloadSubtitleActivity$initAdapter$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder holder, LanguageModel item) {
                Intrinsics.checkNotNullParameter(holder, "holder");
                Intrinsics.checkNotNullParameter(item, "item");
                TextView textView = (TextView) holder.getView(R.id.textView);
                textView.setSelected(item.isSelected);
                textView.setText(item.language);
            }
        };
        DownloadSubtitleActivity downloadSubtitleActivity = this;
        ((RecyclerView) _$_findCachedViewById(R.id.languageRv)).setLayoutManager(new LinearLayoutManager(downloadSubtitleActivity));
        RecyclerView languageRv = (RecyclerView) _$_findCachedViewById(R.id.languageRv);
        Intrinsics.checkNotNullExpressionValue(languageRv, "languageRv");
        CommonExtKt.disableRefreshAnimation(languageRv);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.languageRv);
        BaseQuickAdapter<LanguageModel, BaseViewHolder> baseQuickAdapter = this.languageAdapter;
        BaseQuickAdapter<DownloadingSubtitleModel, BaseViewHolder> baseQuickAdapter2 = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
            baseQuickAdapter = null;
        }
        recyclerView.setAdapter(baseQuickAdapter);
        final ArrayList arrayList2 = new ArrayList();
        BaseQuickAdapter<DownloadingSubtitleModel, BaseViewHolder> baseQuickAdapter3 = new BaseQuickAdapter<DownloadingSubtitleModel, BaseViewHolder>(arrayList2) { // from class: com.movieboxpro.android.view.activity.downloadsubtitle.DownloadSubtitleActivity$initAdapter$2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder holder, DownloadingSubtitleModel item) {
                String str;
                Intrinsics.checkNotNullParameter(holder, "holder");
                Intrinsics.checkNotNullParameter(item, "item");
                SRTModel.SubTitles subTitles = item.getSubTitles();
                String str2 = null;
                if (subTitles != null && (str = subTitles.file_path) != null) {
                    String str3 = subTitles.file_path;
                    str2 = str.substring(str3 == null ? 0 : StringsKt.lastIndexOf$default((CharSequence) str3, "/", 0, false, 6, (Object) null) + 1);
                    Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String).substring(startIndex)");
                }
                holder.setText(R.id.tvName, str2);
                ImageView imageView = (ImageView) holder.getView(R.id.ivComplete);
                CheckBox checkBox = (CheckBox) holder.getView(R.id.checkBox);
                if (item.isComplete()) {
                    CommonExtKt.visible(imageView);
                    CommonExtKt.gone(checkBox);
                    return;
                }
                CommonExtKt.gone(imageView);
                CommonExtKt.visible(checkBox);
                checkBox.setChecked(item.isSelected());
            }
        };
        this.subtitleAdapter = baseQuickAdapter3;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
            baseQuickAdapter3 = null;
        }
        baseQuickAdapter3.addChildClickViewIds(R.id.ivViewSubtitle);
        ((RecyclerView) _$_findCachedViewById(R.id.subtitleRv)).setLayoutManager(new LinearLayoutManager(downloadSubtitleActivity));
        RecyclerView subtitleRv = (RecyclerView) _$_findCachedViewById(R.id.subtitleRv);
        Intrinsics.checkNotNullExpressionValue(subtitleRv, "subtitleRv");
        CommonExtKt.disableRefreshAnimation(subtitleRv);
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.subtitleRv);
        BaseQuickAdapter<DownloadingSubtitleModel, BaseViewHolder> baseQuickAdapter4 = this.subtitleAdapter;
        if (baseQuickAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter4;
        }
        recyclerView2.setAdapter(baseQuickAdapter2);
    }

    @Override // com.movieboxpro.android.view.activity.downloadsubtitle.DownloadSubtitleContract.View
    public void showSubtitle(ResponseSubtitleRecord model) {
        Intrinsics.checkNotNullParameter(model, "model");
        ArrayList arrayList = new ArrayList();
        List<SRTModel> list = model.getList();
        Intrinsics.checkNotNullExpressionValue(list, "model.list");
        String str = "";
        int i = 0;
        for (Object obj : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            SRTModel sRTModel = (SRTModel) obj;
            LanguageModel languageModel = new LanguageModel(sRTModel.language);
            if (i == 0) {
                str = sRTModel.language;
                Intrinsics.checkNotNullExpressionValue(str, "srtModel.language");
                languageModel.isSelected = true;
            }
            arrayList.add(languageModel);
            String str2 = sRTModel.language;
            Intrinsics.checkNotNullExpressionValue(str2, "srtModel.language");
            List<SRTModel.SubTitles> list2 = sRTModel.subtitles;
            Intrinsics.checkNotNullExpressionValue(list2, "srtModel.subtitles");
            this.map.put(str2, list2);
            i = i2;
        }
        ArrayList<DownloadingSubtitleModel> arrayList2 = new ArrayList<>();
        BaseQuickAdapter<LanguageModel, BaseViewHolder> baseQuickAdapter = this.languageAdapter;
        BaseQuickAdapter<DownloadingSubtitleModel, BaseViewHolder> baseQuickAdapter2 = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setNewData(arrayList);
        this.currLanguage = str;
        List<SRTModel.SubTitles> list3 = this.map.get(str);
        if (list3 != null) {
            for (SRTModel.SubTitles subTitles : list3) {
                arrayList2.add(new DownloadingSubtitleModel(subTitles));
            }
        }
        checkSubtitleComplete(arrayList2);
        BaseQuickAdapter<DownloadingSubtitleModel, BaseViewHolder> baseQuickAdapter3 = this.subtitleAdapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter3;
        }
        baseQuickAdapter2.setNewData(arrayList2);
    }

    private final void checkSubtitleComplete(ArrayList<DownloadingSubtitleModel> arrayList) {
        List<File> listFilesInDir = FileUtils.listFilesInDir(new File(this.downloadDir));
        if (listFilesInDir == null) {
            return;
        }
        for (File file : listFilesInDir) {
            String name = file.getName();
            Intrinsics.checkNotNullExpressionValue(name, "it.name");
            List split$default = StringsKt.split$default((CharSequence) name, new String[]{"_"}, false, 0, 6, (Object) null);
            if (!split$default.isEmpty()) {
                int i = 0;
                String str = (String) split$default.get(0);
                int i2 = -1;
                Iterator<T> it = arrayList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Object next = it.next();
                    int i3 = i + 1;
                    if (i < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    if (Intrinsics.areEqual(((DownloadingSubtitleModel) next).getSubTitles().sid, str)) {
                        i2 = i;
                        break;
                    }
                    i = i3;
                }
                if (i2 >= 0 && i2 < arrayList.size()) {
                    arrayList.get(i2).setComplete(true);
                }
            }
        }
    }

    @Override // com.movieboxpro.android.view.activity.downloadsubtitle.DownloadSubtitleContract.View
    public void downloadSubtitlesComplete(List<String> sidList) {
        Intrinsics.checkNotNullParameter(sidList, "sidList");
        BaseQuickAdapter<DownloadingSubtitleModel, BaseViewHolder> baseQuickAdapter = this.subtitleAdapter;
        BaseQuickAdapter<DownloadingSubtitleModel, BaseViewHolder> baseQuickAdapter2 = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
            baseQuickAdapter = null;
        }
        ArrayList<DownloadingSubtitleModel> arrayList = new ArrayList<>(baseQuickAdapter.getData());
        checkSubtitleComplete(arrayList);
        BaseQuickAdapter<DownloadingSubtitleModel, BaseViewHolder> baseQuickAdapter3 = this.subtitleAdapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter3;
        }
        baseQuickAdapter2.setNewData(arrayList);
        setSelectedCount(0);
        ToastUtils.showShort("Download successfully", new Object[0]);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void requestData() {
        String stringExtra = getIntent().getStringExtra("Id");
        String stringExtra2 = getIntent().getStringExtra("Name");
        String stringExtra3 = getIntent().getStringExtra("Fid");
        int intExtra = getIntent().getIntExtra("Season", 0);
        int intExtra2 = getIntent().getIntExtra("Episode", 0);
        if (intExtra == 0 && intExtra2 == 0) {
            this.type = 1;
            this.downloadDir = Constant.DIR_UPLOAD_MOVIE_SUBTITLE + ((Object) File.separator) + ((Object) stringExtra) + ((Object) File.separator) + ((Object) stringExtra2) + ((Object) File.separator) + ((Object) App.deviceLang);
        } else {
            this.downloadDir = Constant.DIR_UPLOAD_TV_SUBTITLE + ((Object) File.separator) + ((Object) stringExtra) + ((Object) File.separator) + ((Object) stringExtra2) + ((Object) File.separator) + "Season " + intExtra + ((Object) File.separator) + "Episode " + intExtra2 + ((Object) File.separator) + ((Object) App.deviceLang);
        }
        DownloadSubtitlePresenter downloadSubtitlePresenter = (DownloadSubtitlePresenter) this.mPresenter;
        if (stringExtra == null) {
            stringExtra = "";
        }
        if (stringExtra3 == null) {
            stringExtra3 = "";
        }
        downloadSubtitlePresenter.loadSubtitle(stringExtra, stringExtra3, intExtra, intExtra2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        String str;
        String str2;
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            BaseQuickAdapter<DownloadingSubtitleModel, BaseViewHolder> baseQuickAdapter = this.subtitleAdapter;
            if (baseQuickAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
                baseQuickAdapter = null;
            }
            DownloadingSubtitleModel itemOrNull = baseQuickAdapter.getItemOrNull(this.viewPosition);
            SRTModel.SubTitles subTitles = itemOrNull != null ? itemOrNull.getSubTitles() : null;
            DownloadSubtitlePresenter downloadSubtitlePresenter = (DownloadSubtitlePresenter) this.mPresenter;
            String[] strArr = new String[1];
            String str3 = "";
            if (subTitles == null || (str = subTitles.file_path) == null) {
                str = "";
            }
            strArr[0] = str;
            ArrayList arrayListOf = CollectionsKt.arrayListOf(strArr);
            String[] strArr2 = new String[1];
            if (subTitles != null && (str2 = subTitles.sid) != null) {
                str3 = str2;
            }
            strArr2[0] = str3;
            downloadSubtitlePresenter.downloadSubtitles(arrayListOf, CollectionsKt.arrayListOf(strArr2), this.downloadDir);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    public DownloadSubtitlePresenter bindPresenter() {
        return new DownloadSubtitlePresenter(this);
    }

    /* compiled from: DownloadSubtitleActivity.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J>\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\fH\u0007¨\u0006\u000e"}, d2 = {"Lcom/movieboxpro/android/view/activity/downloadsubtitle/DownloadSubtitleActivity$Companion;", "", "()V", "starter", "", "context", "Landroid/content/Context;", "name", "", "id", "fid", "season", "", "episode", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void starter(Context context, String name, String id, String fid) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(fid, "fid");
            starter$default(this, context, name, id, fid, 0, 0, 48, null);
        }

        public final void starter(Context context, String name, String id, String fid, int i) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(fid, "fid");
            starter$default(this, context, name, id, fid, i, 0, 32, null);
        }

        private Companion() {
        }

        public static /* synthetic */ void starter$default(Companion companion, Context context, String str, String str2, String str3, int i, int i2, int i3, Object obj) {
            companion.starter(context, str, str2, str3, (i3 & 16) != 0 ? 0 : i, (i3 & 32) != 0 ? 0 : i2);
        }

        public final void starter(Context context, String name, String id, String fid, int i, int i2) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(fid, "fid");
            Intent intent = new Intent(context, DownloadSubtitleActivity.class);
            intent.putExtra("Name", name);
            intent.putExtra("Id", id);
            intent.putExtra("Fid", fid);
            intent.putExtra("Season", i);
            intent.putExtra("Episode", i2);
            if (context == null) {
                return;
            }
            context.startActivity(intent);
        }
    }
}
