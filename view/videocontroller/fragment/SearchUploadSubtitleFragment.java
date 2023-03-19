package com.movieboxpro.android.view.videocontroller.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.mvp.BaseContract;
import com.movieboxpro.android.base.mvp.BaseMvpFragment;
import com.movieboxpro.android.model.WifiUploadSubtitleModel;
import com.movieboxpro.android.service.UploadErrorInfoService;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.EventUtils;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.utils.UriUtils;
import com.movieboxpro.android.utils.tool.NetworkUtils;
import com.movieboxpro.android.view.activity.managesubtitle.ManageSubtitleActivity;
import com.movieboxpro.android.view.activity.web.WebViewActivity;
import com.movieboxpro.android.view.videocontroller.activity.WifiUploadSubtitleActivity;
import com.movieboxpro.android.view.videocontroller.fragment.SearchUploadSubtitleContract;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import master.flame.danmaku.danmaku.parser.IDataSource;
/* compiled from: SearchUploadSubtitleFragment.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0003\u0018\u0000 /2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0002/0B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0016\u001a\u00020\u0010H\u0014J\b\u0010\u0017\u001a\u00020\u0002H\u0014J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0010H\u0002J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u001cH\u0014J\b\u0010 \u001a\u00020\u001cH\u0014J\b\u0010!\u001a\u00020\u001cH\u0002J\b\u0010\"\u001a\u00020\u001cH\u0014J\b\u0010#\u001a\u00020\u001cH\u0014J\"\u0010$\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020\u00102\u0006\u0010&\u001a\u00020\u00102\b\u0010'\u001a\u0004\u0018\u00010(H\u0016J\b\u0010)\u001a\u00020\u001cH\u0016J\u0010\u0010*\u001a\u00020\u001c2\b\u0010\u000b\u001a\u0004\u0018\u00010\fJ\u000e\u0010+\u001a\u00020\u001c2\u0006\u0010\u001a\u001a\u00020\u0010J\u001e\u0010,\u001a\u00020\u001c2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00070.2\u0006\u0010\u0012\u001a\u00020\u0010H\u0016R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0011R\u000e\u0010\u0015\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lcom/movieboxpro/android/view/videocontroller/fragment/SearchUploadSubtitleFragment;", "Lcom/movieboxpro/android/base/mvp/BaseMvpFragment;", "Lcom/movieboxpro/android/view/videocontroller/fragment/SearchUploadSubtitlePresenter;", "Lcom/movieboxpro/android/view/videocontroller/fragment/SearchUploadSubtitleContract$View;", "()V", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/movieboxpro/android/model/WifiUploadSubtitleModel;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "bundle", "Landroid/os/Bundle;", "callback", "Lcom/movieboxpro/android/view/videocontroller/fragment/SearchUploadSubtitleFragment$SearchUploadSubtitleCallback;", "dir", "", "episode", "", "Ljava/lang/Integer;", "lastSelectedPosition", "name", "season", "selectedPath", "bindLayout", "bindPresenter", "checkIndexLegal", "", "position", "copySubtitle", "", "uri", "Landroid/net/Uri;", "initData", "initListener", "initSaveDir", "initView", "loadData", "onActivityResult", "requestCode", "resultCode", "intent", "Landroid/content/Intent;", "onResume", "setSearchUploadSubtitleCallback", "setSelectedSubtitle", "showSubtitles", "models", "", "Companion", "SearchUploadSubtitleCallback", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SearchUploadSubtitleFragment extends BaseMvpFragment<SearchUploadSubtitlePresenter> implements SearchUploadSubtitleContract.View {
    public static final Companion Companion = new Companion(null);
    public static final String EPISODE = "episode";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String SEASON = "season";
    private BaseQuickAdapter<WifiUploadSubtitleModel, BaseViewHolder> adapter;
    private Bundle bundle;
    private SearchUploadSubtitleCallback callback;
    private Integer episode;
    private String name;
    private Integer season;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private int lastSelectedPosition = -1;
    private String dir = "";
    private String selectedPath = "";

    /* compiled from: SearchUploadSubtitleFragment.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\b\u0010\b\u001a\u00020\u0003H&J\u001a\u0010\t\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\n\u001a\u00020\u000bH&¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/view/videocontroller/fragment/SearchUploadSubtitleFragment$SearchUploadSubtitleCallback;", "", "selectedSubtitle", "", IDataSource.SCHEME_FILE_TAG, "Ljava/io/File;", "sid", "", "startOpenSubtitleSite", "viewSearchUploadSubtitle", "position", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface SearchUploadSubtitleCallback {
        void selectedSubtitle(File file, String str);

        void startOpenSubtitleSite();

        void viewSearchUploadSubtitle(File file, int i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: copySubtitle$lambda-11$lambda-10  reason: not valid java name */
    public static final boolean m1369copySubtitle$lambda11$lambda10() {
        return true;
    }

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

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected int bindLayout() {
        return R.layout.fragment_search_upload_subtitle;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment, com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final void setSearchUploadSubtitleCallback(SearchUploadSubtitleCallback searchUploadSubtitleCallback) {
        this.callback = searchUploadSubtitleCallback;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    public SearchUploadSubtitlePresenter bindPresenter() {
        return new SearchUploadSubtitlePresenter(this);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void initListener() {
        ((FrameLayout) _$_findCachedViewById(R.id.flOpenSubtitle)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.videocontroller.fragment.-$$Lambda$SearchUploadSubtitleFragment$gsRqDjUxiZwYmY4H1_Oc8CW8m5k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SearchUploadSubtitleFragment.m1370initListener$lambda0(SearchUploadSubtitleFragment.this, view);
            }
        });
        ((FrameLayout) _$_findCachedViewById(R.id.flUploadWifi)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.videocontroller.fragment.-$$Lambda$SearchUploadSubtitleFragment$HItmIZP3_aNZrEZfdB2wET209YI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SearchUploadSubtitleFragment.m1371initListener$lambda1(SearchUploadSubtitleFragment.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivSetting)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.videocontroller.fragment.-$$Lambda$SearchUploadSubtitleFragment$YI9PMH7ykZYGtsQaTtvROth5FQ8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SearchUploadSubtitleFragment.m1372initListener$lambda2(SearchUploadSubtitleFragment.this, view);
            }
        });
        ((FrameLayout) _$_findCachedViewById(R.id.flGoDir)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.videocontroller.fragment.-$$Lambda$SearchUploadSubtitleFragment$lbmeEYOCQAC4B17Isorm9RTG5Gs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SearchUploadSubtitleFragment.m1373initListener$lambda4(SearchUploadSubtitleFragment.this, view);
            }
        });
        ((FrameLayout) _$_findCachedViewById(R.id.flSubscene)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.videocontroller.fragment.-$$Lambda$SearchUploadSubtitleFragment$UGzyZPOywBcGRG98EzjE51FSQ_Y
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SearchUploadSubtitleFragment.m1374initListener$lambda5(SearchUploadSubtitleFragment.this, view);
            }
        });
        ((FrameLayout) _$_findCachedViewById(R.id.flGoogle)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.videocontroller.fragment.-$$Lambda$SearchUploadSubtitleFragment$Jf0c_LWSplbt2ouIb9NJInpaU5Y
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SearchUploadSubtitleFragment.m1375initListener$lambda6(SearchUploadSubtitleFragment.this, view);
            }
        });
        BaseQuickAdapter<WifiUploadSubtitleModel, BaseViewHolder> baseQuickAdapter = this.adapter;
        BaseQuickAdapter<WifiUploadSubtitleModel, BaseViewHolder> baseQuickAdapter2 = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.videocontroller.fragment.-$$Lambda$SearchUploadSubtitleFragment$q4CCPvWQAYe1uyU2uxoTtiSc-U4
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter3, View view, int i) {
                SearchUploadSubtitleFragment.m1376initListener$lambda7(SearchUploadSubtitleFragment.this, baseQuickAdapter3, view, i);
            }
        });
        BaseQuickAdapter<WifiUploadSubtitleModel, BaseViewHolder> baseQuickAdapter3 = this.adapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter3;
        }
        baseQuickAdapter2.setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.videocontroller.fragment.-$$Lambda$SearchUploadSubtitleFragment$mekI0T6UZxmMC6pndn1qiqD3r6k
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter4, View view, int i) {
                SearchUploadSubtitleFragment.m1377initListener$lambda8(SearchUploadSubtitleFragment.this, baseQuickAdapter4, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m1370initListener$lambda0(SearchUploadSubtitleFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Context context = this$0.getContext();
        if (context != null) {
            CommonExtKt.onMobEvent(context, "OpenSubtitlesSearch");
        }
        EventUtils.event("OpenSubtitles搜索字幕");
        SearchUploadSubtitleCallback searchUploadSubtitleCallback = this$0.callback;
        if (searchUploadSubtitleCallback == null) {
            return;
        }
        searchUploadSubtitleCallback.startOpenSubtitleSite();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m1371initListener$lambda1(SearchUploadSubtitleFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        WifiUploadSubtitleActivity.Companion companion = WifiUploadSubtitleActivity.Companion;
        Context context = this$0.getContext();
        Bundle bundle = this$0.bundle;
        if (bundle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bundle");
            bundle = null;
        }
        companion.starter(context, bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m1372initListener$lambda2(SearchUploadSubtitleFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ManageSubtitleActivity.Companion.starter(this$0.getContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-4  reason: not valid java name */
    public static final void m1373initListener$lambda4(SearchUploadSubtitleFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("*/*");
        intent.addCategory("android.intent.category.OPENABLE");
        this$0.startActivityForResult(intent, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-5  reason: not valid java name */
    public static final void m1374initListener$lambda5(SearchUploadSubtitleFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        WebViewActivity.Companion.starter(this$0.getContext(), Intrinsics.stringPlus("https://subscene.com/subtitles/searchbytitle?query=", this$0.name), this$0.dir);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-6  reason: not valid java name */
    public static final void m1375initListener$lambda6(SearchUploadSubtitleFragment this$0, View view) {
        Integer num;
        String str;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Integer num2 = this$0.season;
        if ((num2 != null && num2.intValue() == 0) || ((num = this$0.episode) != null && num.intValue() == 0)) {
            str = "https://www.google.com/search?q=" + ((Object) this$0.name) + " filetype:srt";
        } else {
            str = "https://www.google.com/search?q=filetype:srt " + ((Object) this$0.name) + " season " + this$0.season + " episode " + this$0.episode;
        }
        WebViewActivity.Companion.starter(this$0.getContext(), str, this$0.dir);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-7  reason: not valid java name */
    public static final void m1376initListener$lambda7(SearchUploadSubtitleFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        String path;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        BaseQuickAdapter<WifiUploadSubtitleModel, BaseViewHolder> baseQuickAdapter = this$0.adapter;
        BaseQuickAdapter<WifiUploadSubtitleModel, BaseViewHolder> baseQuickAdapter2 = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        WifiUploadSubtitleModel item = baseQuickAdapter.getItem(i);
        File file = item.getFile();
        String fileExt = FileUtils.fileExt(file == null ? null : file.getName());
        if (Intrinsics.areEqual(fileExt, "ass")) {
            SearchUploadSubtitleCallback searchUploadSubtitleCallback = this$0.callback;
            if (searchUploadSubtitleCallback == null) {
                return;
            }
            searchUploadSubtitleCallback.viewSearchUploadSubtitle(item.getFile(), i);
        } else if (Intrinsics.areEqual(fileExt, "srt")) {
            if (NetworkUtils.isConnected()) {
                SearchUploadSubtitleCallback searchUploadSubtitleCallback2 = this$0.callback;
                if (searchUploadSubtitleCallback2 == null) {
                    return;
                }
                searchUploadSubtitleCallback2.viewSearchUploadSubtitle(item.getFile(), i);
                return;
            }
            SearchUploadSubtitleCallback searchUploadSubtitleCallback3 = this$0.callback;
            String str = "";
            if (searchUploadSubtitleCallback3 != null) {
                searchUploadSubtitleCallback3.selectedSubtitle(item.getFile(), "");
            }
            int i2 = this$0.lastSelectedPosition;
            if (i == i2) {
                return;
            }
            if (i2 != -1) {
                BaseQuickAdapter<WifiUploadSubtitleModel, BaseViewHolder> baseQuickAdapter3 = this$0.adapter;
                if (baseQuickAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    baseQuickAdapter3 = null;
                }
                WifiUploadSubtitleModel itemOrNull = baseQuickAdapter3.getItemOrNull(this$0.lastSelectedPosition);
                if (itemOrNull != null) {
                    itemOrNull.setChecked(!itemOrNull.getChecked());
                }
            }
            item.setChecked(!item.getChecked());
            BaseQuickAdapter<WifiUploadSubtitleModel, BaseViewHolder> baseQuickAdapter4 = this$0.adapter;
            if (baseQuickAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                baseQuickAdapter2 = baseQuickAdapter4;
            }
            baseQuickAdapter2.notifyDataSetChanged();
            this$0.lastSelectedPosition = i;
            File file2 = item.getFile();
            if (file2 != null && (path = file2.getPath()) != null) {
                str = path;
            }
            this$0.selectedPath = str;
        } else {
            ToastUtils.showShort("unsupported subtitle type", new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-8  reason: not valid java name */
    public static final void m1377initListener$lambda8(SearchUploadSubtitleFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        BaseQuickAdapter<WifiUploadSubtitleModel, BaseViewHolder> baseQuickAdapter = this$0.adapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        WifiUploadSubtitleModel item = baseQuickAdapter.getItem(i);
        SearchUploadSubtitleCallback searchUploadSubtitleCallback = this$0.callback;
        if (searchUploadSubtitleCallback == null) {
            return;
        }
        searchUploadSubtitleCallback.viewSearchUploadSubtitle(item.getFile(), i);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void initData() {
        initSaveDir();
        final ArrayList arrayList = new ArrayList();
        BaseQuickAdapter<WifiUploadSubtitleModel, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<WifiUploadSubtitleModel, BaseViewHolder>(arrayList) { // from class: com.movieboxpro.android.view.videocontroller.fragment.SearchUploadSubtitleFragment$initData$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder holder, WifiUploadSubtitleModel item) {
                File parentFile;
                String name;
                Intrinsics.checkNotNullParameter(holder, "holder");
                Intrinsics.checkNotNullParameter(item, "item");
                holder.setText(R.id.tvName, item.getName());
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Object[] objArr = new Object[3];
                File file = item.getFile();
                String str = "";
                if (file != null && (parentFile = file.getParentFile()) != null && (name = parentFile.getName()) != null) {
                    str = name;
                }
                objArr[0] = str;
                objArr[1] = item.getTime();
                objArr[2] = item.getSize();
                String format = String.format("%s  %s  %s", Arrays.copyOf(objArr, 3));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                holder.setText(R.id.tvSubtitleFileInfo, format);
                ImageView imageView = (ImageView) holder.getView(R.id.iv_selected);
                if (item.getChecked()) {
                    imageView.setVisibility(0);
                } else {
                    imageView.setVisibility(8);
                }
            }
        };
        this.adapter = baseQuickAdapter;
        BaseQuickAdapter<WifiUploadSubtitleModel, BaseViewHolder> baseQuickAdapter2 = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.addChildClickViewIds(R.id.iv_view_subtitle);
        ((RecyclerView) _$_findCachedViewById(R.id.recyclerView)).setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.recyclerView);
        BaseQuickAdapter<WifiUploadSubtitleModel, BaseViewHolder> baseQuickAdapter3 = this.adapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter3;
        }
        recyclerView.setAdapter(baseQuickAdapter2);
    }

    private final void initSaveDir() {
        Integer num;
        String str;
        String string;
        Bundle arguments = getArguments();
        String str2 = "";
        if (arguments != null && (string = arguments.getString("id")) != null) {
            str2 = string;
        }
        this.season = arguments == null ? null : Integer.valueOf(arguments.getInt("season"));
        this.episode = arguments == null ? null : Integer.valueOf(arguments.getInt("episode"));
        this.name = arguments != null ? arguments.getString("name") : null;
        Integer num2 = this.season;
        if ((num2 == null || num2.intValue() != 0) && ((num = this.episode) == null || num.intValue() != 0)) {
            str = Constant.DIR_UPLOAD_TV_SUBTITLE + ((Object) File.separator) + str2 + ((Object) File.separator) + ((Object) this.name) + ((Object) File.separator) + "Season " + this.season + ((Object) File.separator) + "Episode " + this.episode;
        } else {
            str = Constant.DIR_UPLOAD_MOVIE_SUBTITLE + ((Object) File.separator) + str2 + ((Object) File.separator) + ((Object) this.name);
        }
        this.dir = str;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void initView() {
        ((RecyclerView) _$_findCachedViewById(R.id.recyclerView)).setHasFixedSize(true);
        ((RecyclerView) _$_findCachedViewById(R.id.recyclerView)).setNestedScrollingEnabled(false);
        RecyclerView.ItemAnimator itemAnimator = ((RecyclerView) _$_findCachedViewById(R.id.recyclerView)).getItemAnimator();
        if (itemAnimator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
        }
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void loadData() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            arguments = new Bundle();
        }
        this.bundle = arguments;
        ((SearchUploadSubtitlePresenter) this.mPresenter).loadSubtitle(this.dir, this.selectedPath);
    }

    @Override // com.movieboxpro.android.view.videocontroller.fragment.SearchUploadSubtitleContract.View
    public void showSubtitles(List<WifiUploadSubtitleModel> models, int i) {
        Intrinsics.checkNotNullParameter(models, "models");
        BaseQuickAdapter<WifiUploadSubtitleModel, BaseViewHolder> baseQuickAdapter = this.adapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setList(models);
        this.lastSelectedPosition = i;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment, com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        loadData();
    }

    public final void setSelectedSubtitle(int i) {
        String path;
        if (checkIndexLegal(i)) {
            BaseQuickAdapter<WifiUploadSubtitleModel, BaseViewHolder> baseQuickAdapter = null;
            if (checkIndexLegal(this.lastSelectedPosition)) {
                BaseQuickAdapter<WifiUploadSubtitleModel, BaseViewHolder> baseQuickAdapter2 = this.adapter;
                if (baseQuickAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    baseQuickAdapter2 = null;
                }
                baseQuickAdapter2.getData().get(this.lastSelectedPosition).setChecked(false);
                BaseQuickAdapter<WifiUploadSubtitleModel, BaseViewHolder> baseQuickAdapter3 = this.adapter;
                if (baseQuickAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    baseQuickAdapter3 = null;
                }
                baseQuickAdapter3.notifyItemChanged(this.lastSelectedPosition);
            }
            this.lastSelectedPosition = i;
            BaseQuickAdapter<WifiUploadSubtitleModel, BaseViewHolder> baseQuickAdapter4 = this.adapter;
            if (baseQuickAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                baseQuickAdapter4 = null;
            }
            WifiUploadSubtitleModel wifiUploadSubtitleModel = baseQuickAdapter4.getData().get(i);
            wifiUploadSubtitleModel.setChecked(true);
            BaseQuickAdapter<WifiUploadSubtitleModel, BaseViewHolder> baseQuickAdapter5 = this.adapter;
            if (baseQuickAdapter5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                baseQuickAdapter = baseQuickAdapter5;
            }
            baseQuickAdapter.notifyItemChanged(i);
            File file = wifiUploadSubtitleModel.getFile();
            String str = "";
            if (file != null && (path = file.getPath()) != null) {
                str = path;
            }
            this.selectedPath = str;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        Uri data;
        super.onActivityResult(i, i2, intent);
        boolean z = true;
        if (i == 1 && i2 == -1) {
            String str = null;
            if (intent != null && (data = intent.getData()) != null) {
                str = data.getPath();
            }
            FileUtils.getExtensionName(str == null ? "" : str);
            String str2 = str;
            if (str2 != null && str2.length() != 0) {
                z = false;
            }
            if (z) {
                ToastUtils.showShort("load subtitle failed:path is empty", new Object[0]);
            } else if (intent.getData() == null) {
            } else {
                Uri data2 = intent.getData();
                Intrinsics.checkNotNull(data2);
                Intrinsics.checkNotNullExpressionValue(data2, "intent.data!!");
                copySubtitle(data2);
            }
        }
    }

    private final void copySubtitle(final Uri uri) {
        ((ObservableSubscribeProxy) Observable.just(uri).map(new Function() { // from class: com.movieboxpro.android.view.videocontroller.fragment.-$$Lambda$SearchUploadSubtitleFragment$OL40jK9JF9L8xDS2R_L4NytcTxk
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                Boolean m1368copySubtitle$lambda11;
                m1368copySubtitle$lambda11 = SearchUploadSubtitleFragment.m1368copySubtitle$lambda11(uri, this, (Uri) obj);
                return m1368copySubtitle$lambda11;
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<Boolean>() { // from class: com.movieboxpro.android.view.videocontroller.fragment.SearchUploadSubtitleFragment$copySubtitle$2
            @Override // io.reactivex.Observer
            public /* bridge */ /* synthetic */ void onNext(Boolean bool) {
                onNext(bool.booleanValue());
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                SearchUploadSubtitleFragment.this.hideLoadingView();
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                SearchUploadSubtitleFragment.this.showLoadingView();
            }

            public void onNext(boolean z) {
                BaseContract.BasePresenter basePresenter;
                String str;
                String str2;
                if (z) {
                    basePresenter = SearchUploadSubtitleFragment.this.mPresenter;
                    str = SearchUploadSubtitleFragment.this.dir;
                    str2 = SearchUploadSubtitleFragment.this.selectedPath;
                    ((SearchUploadSubtitlePresenter) basePresenter).loadSubtitle(str, str2);
                    return;
                }
                ToastUtils.showShort("copy subtitle failed", new Object[0]);
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable e) {
                Intrinsics.checkNotNullParameter(e, "e");
                SearchUploadSubtitleFragment.this.hideLoadingView();
                ToastUtils.showShort(Intrinsics.stringPlus("copy subtitle failed ", e.getMessage()), new Object[0]);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: copySubtitle$lambda-11  reason: not valid java name */
    public static final Boolean m1368copySubtitle$lambda11(Uri uri, SearchUploadSubtitleFragment this$0, Uri it) {
        Intrinsics.checkNotNullParameter(uri, "$uri");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        File uri2FileCopy = UriUtils.uri2FileCopy(uri);
        boolean copyOrMoveFile = FileUtils.copyOrMoveFile(uri2FileCopy, new File(this$0.dir + ((Object) File.separator) + ((Object) uri2FileCopy.getName())), $$Lambda$SearchUploadSubtitleFragment$ClfRUyjEzn8ltez3KNuVKxwjmAg.INSTANCE, false);
        uri2FileCopy.delete();
        return Boolean.valueOf(copyOrMoveFile);
    }

    private final boolean checkIndexLegal(int i) {
        if (i >= 0) {
            BaseQuickAdapter<WifiUploadSubtitleModel, BaseViewHolder> baseQuickAdapter = this.adapter;
            if (baseQuickAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                baseQuickAdapter = null;
            }
            if (i < baseQuickAdapter.getData().size()) {
                return true;
            }
        }
        return false;
    }

    /* compiled from: SearchUploadSubtitleFragment.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/view/videocontroller/fragment/SearchUploadSubtitleFragment$Companion;", "", "()V", UploadErrorInfoService.EPISODE, "", UploadErrorInfoService.ID, "NAME", UploadErrorInfoService.SEASON, "newInstance", "Lcom/movieboxpro/android/view/videocontroller/fragment/SearchUploadSubtitleFragment;", "bundle", "Landroid/os/Bundle;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SearchUploadSubtitleFragment newInstance(Bundle bundle) {
            SearchUploadSubtitleFragment searchUploadSubtitleFragment = new SearchUploadSubtitleFragment();
            searchUploadSubtitleFragment.setArguments(bundle);
            return searchUploadSubtitleFragment;
        }
    }
}
